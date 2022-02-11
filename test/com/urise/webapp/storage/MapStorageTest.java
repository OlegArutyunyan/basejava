package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MapStorageTest extends AbstractStorageTest {

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Test
    public void getAll() {
        Resume [] resumeArray =  storage.getAll();
        Arrays.sort(resumeArray);

        assertEquals(3, storage.size());
        assertArrayEquals(EXPECTED_RESUME, resumeArray);
    }
}