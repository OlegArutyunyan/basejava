package com.urise.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        ListStorageTest.class,
        MapResumeStorageTest.class,
        MapUuidStorageTest.class,
        SortedArrayStorageTest.class,
        ObjectStreamFileTest.class,
        ObjectStreamPathTest.class,
        XmlStreamPathTest.class,
        JsonStreamPathTest.class,
        DataStreamPathTest.class
})
public class AllStorageTest {

}