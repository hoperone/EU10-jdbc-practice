package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class jdbc_examples {
    // This is called Connection String, we need this info to connect to the database
    String dbUrl = "jdbc:oracle:thin:@100.25.16.0:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {
        //Connection object is created to connect to the database
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        //Statement object is created to execute the query
        Statement statement = connection.createStatement();
        //ResultSet object is created to store the result of the query
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");

        //next() --> move pointer to first row, next() --> move pointer to next row
        while (resultSet.next()) {
            //getting information with column name
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));
        }

        //close connections in reverse order
        resultSet.close();
        statement.close();
        connection.close();

    }

    @Test
    public void test2() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        //.TYPE_SCROLL_INSENSITIVE is used to make the result set scrollable in case of large result sets (like Oracle)
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");

        //last() --> move pointer to last row
        resultSet.last();

        //get the row count
        int rowCount = resultSet.getRow();
        System.out.println("rowCount = " + rowCount);

        //move pointer to first row after using .last() method
        resultSet.beforeFirst();

        //Print all second culumn Info
        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }


        //close connections in reverse order
        resultSet.close();
        statement.close();
        connection.close();


    }

    @Test
    public void test3() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        //.TYPE_SCROLL_INSENSITIVE is used to make the result set scrollable in case of large result sets (like Oracle)
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");

        //DatabaseMetaData object is created to get information about the database
        DatabaseMetaData databaseMetaData = connection.getMetaData();

        //get user name, database product name, database product version, driver name, driver version
        System.out.println("databaseMetaData.getUserName() = " + databaseMetaData.getUserName());
        System.out.println("databaseMetaData.getDatabaseProductName() = " + databaseMetaData.getDatabaseProductName());
        System.out.println("databaseMetaData.getDatabaseProductVersion() = " + databaseMetaData.getDatabaseProductVersion());
        System.out.println("databaseMetaData.getDriverName() = " + databaseMetaData.getDriverName());
        System.out.println("databaseMetaData.getDriverVersion() = " + databaseMetaData.getDriverVersion());

        //------------------------------------------------------

        //ResultSetMetaData object is created to get information about the result set, number of columns, column names etc
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        //getColumnCount() --> returns the number of columns in the result set
        System.out.println("resultSetMetaData.getColumnCount() = " + resultSetMetaData.getColumnCount());
        //getColumnName() --> returns the name of the column
        System.out.println("resultSetMetaData.getColumnName() = " + resultSetMetaData.getColumnName(1));
        System.out.println("resultSetMetaData.getColumnName() = " + resultSetMetaData.getColumnName(2));

        //Iterate through the result set and print the column names
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            System.out.println("resultSetMetaData.getColumnName() = " + resultSetMetaData.getColumnName(i));
        }


        //close connections in reverse order
        resultSet.close();
        statement.close();
        connection.close();


    }
}
