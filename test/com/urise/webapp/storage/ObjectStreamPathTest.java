package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.ObjectStream;

public class ObjectStreamPathTest extends AbstractStorageTest {

    public ObjectStreamPathTest() {
        super(new PathStorage(STORAGE_PATH, new ObjectStream()));
    }
}