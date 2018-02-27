package db;

import java.sql.*;

public class dbUpdate {

    public static void main( String args[] ) {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:./out/swsmr.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO TWEETS (ID,NAME,LOCATION,CONTENT,DATECREATED) " +
                    "VALUES (1, 'Paul', 'Mount Pearl', 'Sup wit it', 'Feb 26, 2018' );";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}