import java.sql.*;

public class PreparedStatement02 {
    public static void main(String[] args) throws SQLException {

        //2. Create Connection (DB Connection settings)
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JDBC_01",
                "dev_user", "dev_user");

        //3. Create Statement - to execute SQL queries

        System.out.println("============== TASK 1 ==============");
        // TASK-1. Using preparedStatement, delete students who are
        // from Mathematics department, from students table.
        String query1 = "DELETE FROM students WHERE department ILIKE ?";
        PreparedStatement preparedStatement1 = connection.prepareStatement(query1);

        preparedStatement1.setString(1, "mathematics");
        int nrOfUpdatedRows = preparedStatement1.executeUpdate();
        System.out.println("nrOfUpdatedRows = " + nrOfUpdatedRows);

        System.out.println("============== TASK 2 ==============");
        //TASK-2. Insert software engineering department using prepared statement into departments table.
        // (id = 5006, pass_grade=475, campus=‘South’)

        String query2 = "INSERT INTO departments  VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement2 = connection.prepareStatement(query2);

        preparedStatement2.setInt(1, 5006);
        preparedStatement2.setString(2, "Software Engineer");
        preparedStatement2.setInt(3, 745);
        preparedStatement2.setString(4, "South");

        int nrOfInsertedRows = preparedStatement2.executeUpdate();
        System.out.println("nrOfInsertedRows = " + nrOfInsertedRows);

        //5. Close connection and statement
        preparedStatement1.close();
        preparedStatement2.close();

        connection.close();


    }
}
