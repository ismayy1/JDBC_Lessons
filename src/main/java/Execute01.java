import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1. Register Driver (not compulsory if you're using version later than 7)
        Class.forName("org.postgresql.Driver");

        //2. Create Connection (DB Connection settings)
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JDBC_01",
                "dev_user", "dev_user");

        //3. Create Statement - to execute SQL queries
        Statement statement = connection.createStatement();

        //to test if the connection was successful
        System.out.println("Connection successful");

        //4. Using statement execute queries
        //TASK:1. create a table named "employee" with column names of : "employee_id", "employee_name", "salary"
//        boolean result1 = statement.execute("CREATE TABLE employee(employee_id INT, employee_name VARCHAR(50), salary REAL)");
        boolean result1 = statement.execute("CREATE TABLE IF NOT EXISTS employee(employee_id INT, employee_name VARCHAR(50), salary REAL)");

        /*
            execute() returns boolean value
            if it is used fo DDL queries returns false
            if it is used to DQL queries return true / false
        */

        System.out.println("result1 = " + result1);

        //TASK 2: add Varchar (20) column name “city” to employee table
        String query2 = "ALTER TABLE employee ADD COLUMN IF NOT EXISTS city VARCHAR(20)";
        boolean result2 = statement.execute(query2);

        System.out.println("result2 = " + result2);

        //TASK 3: Delete employee table from SCHEMA
        String query3 = "DROP TABLE employee";
        boolean result3 = statement.execute(query3);
        System.out.println("result3 = " + result3);

        //5. Close connection and statement
        statement.close();
        connection.close();

    }
}
