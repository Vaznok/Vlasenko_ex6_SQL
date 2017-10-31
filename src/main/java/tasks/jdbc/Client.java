package tasks.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Client {
    public static void main(String[] args) throws SQLException {
        Connection conn = ConnectionJDBC.getConnection();
        Statement stmt = conn.createStatement();
        createTables(stmt);
        addFakeData(stmt);

        ResultSet rs = stmt.executeQuery("SELECT STUDENT_ID, FIRST_NAME FROM STUDENTS");
        while(rs.next()) {
            int id = rs.getInt("STUDENT_ID");
            String name = rs.getString("FIRST_NAME");
            System.out.printf("%d, %s\n", id, name);
        }

        conn.commit();
        conn.close();
    }

    private static void createTables(Statement stmt) throws SQLException {
        stmt.execute("CREATE TABLE IF NOT EXISTS " +
                "STUDENTS (STUDENT_ID INT PRIMARY KEY, GROUP_ID INT, FIRST_NAME VARCHAR(64), LAST_NAME VARCHAR(64))");
        stmt.execute("CREATE TABLE IF NOT EXISTS GROUPS (" +
                "GROUP_ID INT PRIMARY KEY, " +
                "NAME VARCHAR(64), " +
                "STUDENT_ID INT NOT NULL, " +
                "COURSE_ID INT NOT NULL, " +
                //"PRIMARY KEY (STUDENT_ID, COURSE_ID), " +
                "FOREIGN KEY (STUDENT_ID) REFERENCES STUDENTS(STUDENT_ID), " +
                "FOREIGN KEY (COURSE_ID) REFERENCES COURSES(COURSE_ID))"
        );
        stmt.execute("CREATE TABLE IF NOT EXISTS " +
                "COURSES (COURSE_ID INT PRIMARY KEY, NAME VARCHAR(64), DESCRIPTION TEXT)");
        //Many to many relationship
        /*stmt.execute("CREATE TABLE IF NOT EXISTS STUDENTS_COURSES (" +

        );*/
    }

    private static void addFakeData(Statement stmt) throws SQLException {
        //STUDENTS TABLE
        stmt.execute("INSERT INTO STUDENTS (STUDENT_ID, GROUP_ID, FIRST_NAME, LAST_NAME) VALUES (1, 2, 'Vetall', 'Vlasenko')");
        stmt.execute("INSERT INTO STUDENTS (STUDENT_ID, GROUP_ID, FIRST_NAME, LAST_NAME) VALUES (2, 2, 'Liza', 'Vlasenko')");
        stmt.execute("INSERT INTO STUDENTS (STUDENT_ID, GROUP_ID, FIRST_NAME, LAST_NAME) VALUES (3, 3, 'Nastya', 'Vorotnyak')");
        stmt.execute("INSERT INTO STUDENTS (STUDENT_ID, GROUP_ID, FIRST_NAME, LAST_NAME) VALUES (4, 1, 'Petya', 'Kykywka')");
        stmt.execute("INSERT INTO STUDENTS (STUDENT_ID, GROUP_ID, FIRST_NAME, LAST_NAME) VALUES (5, 3, 'Fedor', 'Bobik')");
        //GROUPS TABLE
        /*stmt.execute("INSERT INTO GROUPS (NAME) VALUE ('Musicians')");
        stmt.execute("INSERT INTO GROUPS (NAME) VALUE ('Footballers')");
        stmt.execute("INSERT INTO GROUPS (NAME) VALUE ('Programmers')");*/
        //COURSES TABLE

    }
}
