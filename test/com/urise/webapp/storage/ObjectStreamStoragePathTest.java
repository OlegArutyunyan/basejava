package com.urise.webapp.storage;

public class ObjectStreamStoragePathTest extends AbstractStorageTest {

    public ObjectStreamStoragePathTest() {
        super(new PathStorage(STORAGE_PATH, new ObjectStreamStorage()));
    }
}