package com.pratik.instrument.fetch.repo;

import org.bson.Document;

import java.util.Map;
import java.util.Optional;

public interface YamahaPianoRepository {

    public Optional addYamahaPianoInfo(Document document);

}
