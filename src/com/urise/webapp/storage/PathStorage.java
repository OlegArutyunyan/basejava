package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serialization.Serialization;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final Serialization serialization;

    protected PathStorage(String dir, Serialization serialization) {
        this.serialization = serialization;
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    public void clear() {
        createStream().forEach(this::deleteResume);
    }

    @Override
    public int size() {
        return (int) createStream().count();
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void updateResume(Resume r, Path path) {
        try {
            serialization.doWrite(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", r.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected void saveResume(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create Path " + path.getParent(), path.getFileName().toString(), e);
        }
        updateResume(r, path);
    }

    @Override
    protected Resume getResume(Path path) {
        try {
            return serialization.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path read error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void deleteResume(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected List<Resume> createResumeList() {
        return createStream().map(this::getResume).collect(Collectors.toList());
    }

    private Stream<Path> createStream () {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Directory read error", null, e);
        }
    }

}
