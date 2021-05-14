package com.pratik.instrument.fetch.controller;

import com.pratik.instrument.fetch.service.YamahaPianoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController("yamaha/piano/")
public class YamahaPianoController {

    @Autowired
    private YamahaPianoService yamahaPianoService;

    @PostMapping("addSpecs/{url}")
    public void addYamahaPianoInfo (String url) throws IOException {

        yamahaPianoService.addYamahaPianoInfo(url);

    }

}
