import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException {

        //1. Register - skipped
        //2. Connection
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JDBC_01",
                "dev_user", "dev_user");

        //3. Statement
        Statement statement = connection.createStatement();
        //4. execute queries

        //TASK-1. GET/SELECT  “country_name” from countries table with ID between 5 and 10
        String query1 = "SELECT id, country_name FROM countries WHERE id BETWEEN 5 AND 10";
        ResultSet resultSet1 = statement.executeQuery(query1);
        resultSet1.next();

        while (resultSet1.next()) {
//            String countryName = resultSet1.getString("country_name");
//            System.out.println("countryName = " + countryName);
            System.out.println("Country name = " + resultSet1.getString("country_name"));
        }

        //TASK - 2: Get "phone_code" and "country_name" from countries table,
        // whose phone code is greater than 'some random nr'
        String query2 = "SELECT phone_code, country_name FROM countries WHERE phone_code > 30";
        ResultSet resultSet2 = statement.executeQuery(query2);

        while (resultSet2.next()) {
            System.out.println("Phone code: " + resultSet2.getInt("phone_code")
                    + ", Country name: " + resultSet2.getString("country_name"));
        }

        //5. Close connection and statement
        statement.close();
        connection.close();

    }
}
