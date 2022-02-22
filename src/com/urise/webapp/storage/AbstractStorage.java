package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected static final Comparator<Resume> RESUME_COMPARATOR
            = Comparator.comparing(Resume::getFullName, Comparator.nullsLast(Comparator.naturalOrder()))
            .thenComparing(Resume::getUuid, Comparator.nullsLast(Comparator.naturalOrder()));

    protected abstract boolean isExist(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract void updateResume(Resume r, SK searchKey);

    protected abstract Resume getResume(SK searchKey);

    protected abstract void deleteResume(SK searchKey);

    protected abstract void saveResume(Resume r, SK searchKey);

    protected abstract List<Resume> createResumeList();

    public final void update(Resume r) {
        LOG.info("Update " + r);
        updateResume(r, checkIfNotExist(r.getUuid()));
    }

    public final Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return getResume(checkIfNotExist(uuid));
    }

    public final void delete(String uuid) {
        LOG.info("Delete " + uuid);
        deleteResume(checkIfNotExist(uuid));
    }

    public final void save(Resume r) {
        LOG.info("Save " + r);
        saveResume(r, checkIfExist(r.getUuid()));
    }

    private SK checkIfNotExist(String uuid) {
        SK searchKey = getSearchKey(uuid);

        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK checkIfExist(String uuid) {
        SK searchKey = getSearchKey(uuid);

        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> resumes = createResumeList();
        resumes.sort(RESUME_COMPARATOR);
        return resumes;
    }

}
