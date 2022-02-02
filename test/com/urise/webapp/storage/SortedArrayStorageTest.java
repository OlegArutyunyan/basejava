package com.urise.webapp.storage;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SortedArrayStorageTest extends AbstractArrayStorageTest{
    SortedArrayStorage sortedArrayStorage;

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        sortedArrayStorage = (SortedArrayStorage) storage;
    }

    @Test
    public void saveResume() {
    }
}