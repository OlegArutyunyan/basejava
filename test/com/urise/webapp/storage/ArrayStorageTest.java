package com.urise.webapp.storage;

import org.junit.Before;
import org.junit.BeforeClass;

public class ArrayStorageTest extends AbstractArrayStorageTest{

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
}