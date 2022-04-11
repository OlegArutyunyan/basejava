package com.urise.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlMethod<T> {
    T execute(PreparedStatement ps) throws SQLException;
}
