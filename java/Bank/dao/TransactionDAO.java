package Bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.mysql.cj.jdbc.Driver;

import Bank.dto.TransactionDTO;
public class TransactionDAO 
{
	public Connection createConnection() throws Exception
	{
		DriverManager.registerDriver(new Driver());
		Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3307/bank?createDatabaseIfNotExist=true","root","root");
		PreparedStatement ps=c.prepareStatement("create table if not exists transactions (id int auto_increment primary key,account_number varchar(20),amount decimal(10, 2),transaction_type varchar(10),timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
		ps.execute();
		return c;
	}
	public int userTransaction(TransactionDTO transaction) throws Exception {
		Connection c=createConnection();
		PreparedStatement ps = c.prepareStatement("insert into transactions (account_number, amount, transaction_type) VALUES (?, ?, ?)");
        ps.setString(1, transaction.getAccountNumber());
        ps.setDouble(2, transaction.getAmount());
        ps.setString(3, transaction.getTransactionType());
        return ps.executeUpdate();
	}
}
