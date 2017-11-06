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

        ResultSet rs = stmt.executeQuery("SELECT GROUPS.NAME, COUNT(*) FROM STUDENTS " +
                "INNER JOIN GROUPS ON STUDENTS.GROUP_ID=GROUPS.GROUP_ID GROUP BY GROUPS.NAME HAVING COUNT(*) < 10");

        System.out.println("Find groups where count of students less than 10!");
        System.out.println("-------------------------------------------------");
        while(rs.next()) {
            String groupName = rs.getString("NAME");
            int count = rs.getInt("COUNT");
            System.out.printf("GROUP NAME: %s, COUNT=%d \n", groupName, count);
        }
        System.out.println();
        conn.commit();
        stmt.execute("DELETE FROM STUDENTS WHERE GROUP_ID IN (SELECT GROUP_ID FROM GROUPS WHERE NAME='SR-01')");

        ResultSet rs2 = stmt.executeQuery("SELECT STUDENTS.FIRST_NAME, STUDENTS.LAST_NAME, GROUPS.NAME FROM STUDENTS " +
                "INNER JOIN GROUPS ON STUDENTS.GROUP_ID=GROUPS.GROUP_ID");
        System.out.println("Delete all students from group SR-01!");
        System.out.println("-------------------------------------");
        while(rs2.next()) {
            String groupName = rs2.getString("NAME");
            String firstName = rs2.getString("FIRST_NAME");
            String lastName = rs2.getString("LAST_NAME");
            System.out.printf("%s, %s, %s \n", firstName, lastName, groupName);
        }
        System.out.println();
        conn.commit();

        ResultSet rs3 = stmt.executeQuery("SELECT * FROM COURSES " +
                "INNER JOIN STUDENTS_COURSES ON COURSES.COURSE_ID=STUDENTS_COURSES.COURSE_ID " +
                "INNER JOIN STUDENTS ON STUDENTS_COURSES.STUDENT_ID=STUDENTS.STUDENT_ID " +
                "WHERE NAME='Programming'");
        System.out.println("Find students FROM 'Programming' course");
        System.out.println("---------------------------------------");
        while(rs3.next()) {
            String course = rs3.getString("NAME");
            String firstName = rs3.getString("FIRST_NAME");
            String lastName = rs3.getString("LAST_NAME");
            System.out.printf("%s, %s, %s\n", course, firstName, lastName);
        }
        System.out.println();
        conn.commit();

        conn.close();
    }

    private static void createTables(Statement stmt) throws SQLException {
        stmt.execute("DROP TABLE IF EXISTS GROUPS, STUDENTS_COURSES, STUDENTS, COURSES");

        stmt.execute("CREATE TABLE IF NOT EXISTS GROUPS (" +
                "GROUP_ID SERIAL NOT NULL PRIMARY KEY, " +
                "NAME VARCHAR(64))"
        );

        stmt.execute("CREATE TABLE IF NOT EXISTS STUDENTS (" +
                "STUDENT_ID SERIAL NOT NULL, " +
                "GROUP_ID INT NOT NULL, " +
                "FIRST_NAME VARCHAR(64), " +
                "LAST_NAME VARCHAR(64), " +
                "PRIMARY KEY(STUDENT_ID), " +
                "FOREIGN KEY (GROUP_ID) REFERENCES GROUPS(GROUP_ID) ON DELETE CASCADE)"
        );

        stmt.execute("CREATE TABLE IF NOT EXISTS COURSES (" +
                "COURSE_ID SERIAL NOT NULL, " +
                "NAME VARCHAR(64), " +
                "DESCRIPTION TEXT, " +
                "PRIMARY KEY (COURSE_ID))"
        );
        //Many to many relationship
        stmt.execute("CREATE TABLE IF NOT EXISTS STUDENTS_COURSES (" +
                "COURSE_ID INT NOT NULL, " +
                "STUDENT_ID INT NOT NULL, " +
                "FOREIGN KEY (COURSE_ID) REFERENCES COURSES(COURSE_ID), " +
                "FOREIGN KEY (STUDENT_ID) REFERENCES STUDENTS(STUDENT_ID) ON DELETE CASCADE)"

        );
    }

    private static void addFakeData(Statement stmt) throws SQLException {
        //GROUPS TABLE
        stmt.execute("INSERT INTO GROUPS (NAME) VALUES ('SR-01'), ('SR-02'), ('DG-01')");
        //STUDENTS TABLE
        stmt.execute("INSERT INTO STUDENTS (GROUP_ID, FIRST_NAME, LAST_NAME) VALUES (2, 'Vetall', 'Vlasenko'), (2, 'Liza', 'Vlasenko'), (3, 'Nastya', 'Vorotnyak'), " +
                "(1, 'Petya', 'Kykywka'), (3, 'Fedor', 'Bobik'), (2, 'Leonid', 'Vlasenko'), (2, 'Lizzet', 'Cristal'), (3, 'Nambida', 'Vendetta'), " +
                "(1, 'Sanya', 'Bydka'), (3, 'Kinfir', 'Zinfir'), (1, 'Bogdan', 'Hmel'), (3, 'Cats', 'Booty'), (2, 'Leonid', 'Ivawenko'), (2, 'Joe', 'Klava'), " +
                "(3, 'Nina', 'Elle'), (1, 'Kolya', 'Spice'), (2, 'Vetall2', 'Vlasenko2'), (2, 'Liza2', 'Vlasenko2'), (3, 'Nastya2', 'Vorotnyak2'), " +
                "(1, 'Petya2', 'Kykywka2'), (3, 'Fedor2', 'Bobik2'), (2, 'Leonid2', 'Vlasenko2'), (2, 'Lizzet2', 'Cristal2'), (3, 'Nambida2', 'Vendetta2'), " +
                "(1, 'Sanya2', 'Bydka2'), (3, 'Kinfir2', 'Zinfir2'), (1, 'Bogdan2', 'Hmel2'), (3, 'Cats2', 'Booty2'), (2, 'Leonid2', 'Ivawenko2'), (2, 'Joe2', 'Klava2'), " +
                "(3, 'Nina2', 'Elle'), (1, 'Kolya2', 'Spice2')");

        //COURSES TABLE
        stmt.execute("INSERT INTO COURSES (NAME, DESCRIPTION) VALUES ('Programming', 'Web development using JAVA'), " +
                "('Cooking', 'Asian hot dishes'), ('Marketing', 'Marketing by Joe Ferdinand approach'), ('Swiming', 'Aqua Aerobica')");
        //STUDENTS_COURSES TABLE
        stmt.execute("INSERT  INTO STUDENTS_COURSES VALUES (1, 1), (1, 4), (1, 7), (1, 9), (1, 13), (1, 14), (1, 15), (1, 18), (1, 20), " +
                "(2, 2), (2, 3), (2, 4), (2, 7), (2, 9), (2, 12), (2, 13), (2, 17), (2, 20), " +
                "(3, 3), (3, 5), (3, 6), (3, 8), (3, 10), (3, 13), (3, 15), (3, 19), (3, 20), " +
                "(4, 1), (4, 2), (4, 4), (4, 15), (4, 16), (4, 19)");
    }
}
