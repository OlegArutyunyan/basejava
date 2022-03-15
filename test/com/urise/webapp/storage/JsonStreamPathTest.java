package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.JsonStreamSerializer;

public class JsonStreamPathTest extends AbstractStorageTest {

    public JsonStreamPathTest() {
        super(new PathStorage(STORAGE_PATH, new JsonStreamSerializer()));
    }
}