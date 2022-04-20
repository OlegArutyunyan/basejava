package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.Section;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.urise.webapp.ResumeTestData.printContacts;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume", (ps) -> {
            ps.execute();
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume r WHERE r.uuid =?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                Resume r = new Resume(uuid, rs.getString("full_name"));
                do {
                    readContact(r, conn);
                } while (rs.next());

                return r;
            }
        });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                        ps.setString(1, r.getFullName());
                        ps.setString(2, r.getUuid());
                        if (ps.executeUpdate() == 0) {
                            throw new NotExistStorageException(r.getUuid());
                        }
                    }
                    deleteContact(r, conn);
                    writeContact(r, conn);
//                    deleteSection(r, conn);
//                    writeSection(r, conn);

                    return null;
                }
        );
    }


    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE uuid = ?", (ps) -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                        ps.setString(1, r.getUuid());
                        ps.setString(2, r.getFullName());
                        ps.execute();
                    }
                    writeContact(r, conn);
//                    writeSection(r, conn);

                    return null;
                }
        );
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = new ArrayList<>();
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume r ORDER BY full_name,uuid")) {
                ResultSet rs = ps.executeQuery();

                if (!rs.next()) {
                    throw new NotExistStorageException(null);
                }
                do {
                    Resume r = new Resume(rs.getString("uuid"), rs.getString("full_name"));

                    readContact(r, conn);
                    result.add(r);
                } while (rs.next());

            }
            return null;
        });
        return result;
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) AS size FROM resume", (ps) -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt("size") : 0;
        });
    }

    private void readContact(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact c WHERE c.resume_uuid = ?")) {
            ps.setString(1, r.getUuid());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    String value = rs.getString("value");
                    ContactType type = ContactType.valueOf(rs.getString("type"));
                    r.setContact(type, value);
                } while (rs.next());
            }
        }
    }

//    private void readSection(Resume r, Connection conn) throws SQLException {
//        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section s WHERE s.resume_uuid = ?")) {
//            ps.setString(1, r.getUuid());
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                do {
//                    String value = rs.getString("content");
//                    SectionType type = SectionType.valueOf(rs.getString("type"));
//                    //r.setSection(type, value);
//                } while (rs.next());
//            }
//        }
//    }

    private void deleteContact(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid = ?")) {
            ps.setString(1, r.getUuid());
        }
    }

//    private void deleteSection(Resume r, Connection conn) throws SQLException {
//        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM section WHERE resume_uuid = ?")) {
//            ps.setString(1, r.getUuid());
//        }
//    }

    private void writeContact(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void writeSection(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (resume_uuid, type, content) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, Section> e : r.getSections().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                //Section section = e.getValue();
                ps.setString(3, String.valueOf(e.getValue()));                ps.addBatch();
            }

            ps.executeBatch();
        }
    }
}