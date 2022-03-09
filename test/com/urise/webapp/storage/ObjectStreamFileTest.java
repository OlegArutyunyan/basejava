package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.ObjectStream;

public class ObjectStreamFileTest extends AbstractStorageTest {

    public ObjectStreamFileTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStream()));
    }
}