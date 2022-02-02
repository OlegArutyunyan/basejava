package com.urise.webapp.storage;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
}