package com.urise.webapp.storage;

public class ObjectStreamStorageFileTest extends AbstractStorageTest {

    public ObjectStreamStorageFileTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamStorage()));
    }
}