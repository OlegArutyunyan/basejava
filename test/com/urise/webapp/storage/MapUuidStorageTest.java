package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapUuidStorageTest extends AbstractStorageTest {

    public MapUuidStorageTest() {
        super(new MapUuidStorage());
    }

    @Test
    public void getAllSorted() {
        Resume [] resumeArray =  storage.getAllSorted().toArray(new Resume[0]);

        assertEquals(3, storage.size());
        assertArrayEquals(EXPECTED_RESUME, resumeArray);
    }
}