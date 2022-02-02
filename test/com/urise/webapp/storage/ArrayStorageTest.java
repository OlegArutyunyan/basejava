package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ArrayStorageTest extends AbstractArrayStorageTest{

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
}