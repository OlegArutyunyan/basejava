package com.urise.webapp.storage;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlMethod<T> {
    T execute(PreparedStatement ps) throws SQLException;
}
