import java.sql.*;

public class dbSelect {

    public static void main( String args[] ) {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:./out/swsmr.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM TWEETS;" );

            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                String location  = rs.getString("location");
                String  content = rs.getString("content");
                String date = rs.getString("datecreated");

                System.out.println( "ID = " + id );
                System.out.println( "NAME = " + name );
                System.out.println( "LOCATION = " + location );
                System.out.println( "CONTENT = " + content );
                System.out.println( "DATECREATED = " + date );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}