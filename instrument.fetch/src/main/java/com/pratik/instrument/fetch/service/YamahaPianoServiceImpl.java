package com.pratik.instrument.fetch.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.pratik.instrument.fetch.repo.YamahaPianoRepository;
import com.pratik.instrument.fetch.utils.SpecificationHandler;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class YamahaPianoServiceImpl implements  YamahaPianoService{

    private static final Logger logger = LoggerFactory.getLogger(YamahaPianoServiceImpl.class);

    @Autowired
    private YamahaPianoRepository yamahaPianoRepository;

    @Override
    public Optional addYamahaPianoInfo(String searchUrl) {
        try {

            WebClient webClient = new WebClient();
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(false);

            logger.info("Fetching specificaiton info....");
//        String searchUrl = "https://in.yamaha.com/en/products/musical_instruments/pianos/p_series/p-125/specs.html#product-tabs";
//        String searchUrl = "https://in.yamaha.com/en/products/musical_instruments/pianos/p_series/p-121/specs.html#product-tabs";
            HtmlPage htmlPage = webClient.getPage(searchUrl);
            List<HtmlElement> items = (List<HtmlElement>) htmlPage.getByXPath("//td");

            if (items.isEmpty()) {
                logger.info("Unable to fetch specification from website. Aborting!!!!");
                return null;
            }

            Document document = new Document();

            for (HtmlElement element : items) {
                document = document.append(SpecificationHandler.specHeaderToCamel(element.getPreviousSibling().asText()), element.asText());
            }

            items = (List<HtmlElement>) htmlPage.getByXPath("//span[@class='product-name']");

            if (items.isEmpty()) {
                logger.info("Unable to fetch product name from website. Aborting!!!!");
                return null;
            }

            document.append("name", items.get(0).asText());

            return yamahaPianoRepository.addYamahaPianoInfo(document);
        } catch (Exception e) {
            logger.error("Exception occured - " + e.getStackTrace());
        }
        return null;

    }
}
