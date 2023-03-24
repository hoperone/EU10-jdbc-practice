package jdbctests;

import java.sql.*;

public class TestConnection {
    public static void main(String[] args) throws SQLException {
        //This is called Connection String, we need this info to connect to the database
        String dbUrl = "jdbc:oracle:thin:@100.25.16.0:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        //Connection object is created to connect to the database
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        //Statement object is created to execute the query
        Statement statement = connection.createStatement();
        //ResultSet object is created to store the result of the query
        ResultSet resultSet = statement.executeQuery("SELECT * FROM regions");

        //next() --> move pointer to first row
        //resultSet.next();

        //getting information with column name
        //System.out.println(resultSet.getString("region_name"));

        //getting information with column index(Starts with 1)
        //System.out.println(resultSet.getString(2));

        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2));
        }
        
        //close connections in reverse order
        resultSet.close();
        statement.close();
        connection.close();
    }
}
