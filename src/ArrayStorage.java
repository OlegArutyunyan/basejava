import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    public int array_size = 0;

    void clear() {
        for (int i = 0; i < array_size; i++) {
            storage[i] = null;
        }
        array_size = 0;
    }

    void save(Resume r) {
        storage[array_size] = r;
        array_size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < array_size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < array_size; i++) {
            if (storage[i].toString().equals(uuid)) {
                 for (int j = i; j < array_size; j++) {
                    storage[j] = storage[j + 1];
                }
                break;
            }
        }
        array_size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, array_size);
    }

    int size() {
        return array_size;
    }
}
