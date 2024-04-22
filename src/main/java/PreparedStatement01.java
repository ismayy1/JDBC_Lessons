import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //2. Create Connection (DB Connection settings)
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JDBC_01",
                "dev_user", "dev_user");

        //3. Create Statement - to execute SQL queries
        Statement statement = connection.createStatement();

        //normal String Query
//        //TASK-1. Update pass_grade to 475 of Mathematics department (use PreparedStatement)
//        String query1 = "UPDATE departments SET pass_grade = 475 WHERE department = 'Mathematics'";
//        //TASK-2. Update pass_grade to 499 of Mathematics department (use PreparedStatement)

        //Parameterized Query
        //TASK-1. Update pass_grade to 475 of Mathematics department (use PreparedStatement)
        String query1 = "UPDATE departments SET pass_grade = ? WHERE department ILIKE ?";
        //create preparedStatement
        PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
        //set values for the parameters
        preparedStatement1.setInt(1, 475);
        preparedStatement1.setString(2, "mathematics");

        //Updating the database by running executeUpdate()
        int updatedRows1 = preparedStatement1.executeUpdate();
        System.out.println("updatedRows1 = " + updatedRows1);

        //TASK-2. Update pass_grade to 455 of Literature department (use PreparedStatement)
        preparedStatement1.setInt(1, 455);
        preparedStatement1.setString(2, "literature");

        int updatedRows2 = preparedStatement1.executeUpdate();
        System.out.println("updatedRows2 = " + updatedRows2);

        //5. Close connection and statement
        statement.close();
        connection.close();

    }
}
