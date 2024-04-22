import java.sql.*;

public class ExecuteUpdate {
    public static void main(String[] args) throws SQLException {

        // Step:2 - Create Connection (DB connection settings)
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JDBC_01",
                "dev_user", "dev_user");

        //Step:3 - Create Statement - to execute SQL queries
        Statement statement = con.createStatement();

        //TASK-1. Update salaries of developers whose salaries are less than average salary with average salary
        String query1 = "UPDATE developers SET salary = (SELECT AVG (salary) FROM developers) WHERE salary < (SELECT AVG (salary) FROM developers)";
        int nrOfUpdatedRows = statement.executeUpdate(query1);
        System.out.println("nrOfUpdatedRows = " + nrOfUpdatedRows);

        ResultSet resultSet1 = statement.executeQuery("SELECT id, name, salary FROM developers");

        while (resultSet1.next()) {
            System.out.println(resultSet1.getString("name")
                    + " -- " + resultSet1.getDouble("salary"));
        }

        System.out.println("=========== TASK 2 ===========");
        //TASK-2. Add new developer to developers table
        String query2 = "INSERT INTO developers (name, salary, prog_lang) " +
                "VALUES ('Alan Dawn', 4500, 'Java, Js')";
        int nrOfInserts = statement.executeUpdate(query2);
        System.out.println("nrOfInserts = " + nrOfInserts);

        ResultSet resultSet2 = statement.executeQuery("SELECT id, name, salary FROM developers");

        while (resultSet2.next()) {
            System.out.println(resultSet2.getString("name")
                    + " -- " + resultSet2.getDouble("salary"));
        }

        //TASK-3. DELETE row which has name of 'Alan'
        String query3 = "DELETE FROM developers WHERE name = 'Alan Dawn'";
        int nrOfDeletedRows = statement.executeUpdate(query3);
        System.out.println("nrOfDeletedRows = " + nrOfDeletedRows);


        //Step: 5 - close connection and statement
        statement.close();
        con.close();


    }
}
