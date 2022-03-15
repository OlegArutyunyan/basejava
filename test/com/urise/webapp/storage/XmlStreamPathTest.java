package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.XmlStreamSerializer;

public class XmlStreamPathTest extends AbstractStorageTest {

    public XmlStreamPathTest() {
        super(new PathStorage(STORAGE_PATH, new XmlStreamSerializer()));
    }
}