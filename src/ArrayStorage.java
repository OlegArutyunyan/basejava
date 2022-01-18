import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i <= storage.length; i++) {
            if (storage[i] != null) {
                storage[i] = null;
            } else {
                break;
            }
        }
    }

    void save(Resume r) {
        for (int i = 0; i <= storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (Resume n : storage) {
            if (n == null) {
                break;
            } else if (n.toString().equals(uuid)) {
                return n;
            }
        }
        return null;
    }

    void delete(String uuid) {
        int i = 0;
        for (Resume n : storage) {
            if (n == null) {
                break;
            } else if (n.toString().equals(uuid)) {
                for (int j = i; j <= 10000; j++) {
                    storage[j] = storage[j + 1];
                    if (storage[j + 1] == null) {
                        break;
                    }
                }
                break;
            }
            i++;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int i = 0;
        for (Resume n : storage) {
            if (n != null) {
                i++;
            } else {
                break;
            }
        }
        return Arrays.copyOfRange(storage, 0, i);
    }

    int size() {
        int i = 0;
        for (Resume n : storage) {
            if (n != null) {
                i++;
            } else {
                break;
            }
        }
        return i;
    }
}
