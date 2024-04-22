import java.sql.*;

public class Transaction01 {
    public static void main(String[] args) throws Exception {

        //2. Create Connection (DB Connection settings)
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JDBC_01",
                "dev_user", "dev_user");

        //3. Create Statement - to execute SQL queries
        Statement statement = connection.createStatement();

        //TASK-1. Transfer amount of 1000 from account_nu:1234 to account_nu:5678
        String query1 = "UPDATE accounts SET amount = amount+? WHERE account_nu = ?";
        PreparedStatement preparedStatement1 = connection.prepareStatement(query1);

        connection.setAutoCommit(false);

        //save point
        Savepoint sp = null;

        try {

            // in the case we have other transactions which won't affect the following transactions
            // set the save point, and pass this savePoint to the catch() part as a parameter
            sp = connection.setSavepoint(); // rollBack until this point

            //first transaction
            preparedStatement1.setDouble(1, -1000);
            preparedStatement1.setInt(2, 1234);
            preparedStatement1.executeUpdate();

            //scenario: after the transaction1, smth happened, and we couldn't run the transaction2
            if (false) {
                throw new Exception();  //goal is to stop the app here
            }

            //second transaction
            preparedStatement1.setDouble(1, 1000);
            preparedStatement1.setInt(2, 5678);
            preparedStatement1.executeUpdate();

            //if there's no issue until this point, it'll be committed
            connection.commit();

        } catch (Exception e) {
            //and pass the savepoint as the parameter
            connection.rollback(sp); //cancels all transaction and sets everything to its initial position
        }



        //5. Close connection and statement
        statement.close();
        connection.close();


    }
}
