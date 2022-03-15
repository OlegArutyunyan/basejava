package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.DataStreamSerializer;

public class DataStreamPathTest extends AbstractStorageTest {

    public DataStreamPathTest() {
        super(new PathStorage(STORAGE_PATH, new DataStreamSerializer()));
    }
}