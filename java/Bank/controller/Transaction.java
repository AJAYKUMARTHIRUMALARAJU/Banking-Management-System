package Bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bank.dao.TransactionDAO;
import Bank.dto.TransactionDTO;
@WebServlet("/addtransaction")
public class Transaction extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		Date date=new Date();
		Timestamp timestamp=new Timestamp(date.getTime());
		long id=00000001;
		String accountNumber = req.getParameter("accountNumber");
        double amount = Double.parseDouble(req.getParameter("amount"));
        String transactionType = req.getParameter("transactionType");
        resp.setContentType("text/html");
        PrintWriter pw=resp.getWriter();
        TransactionDAO transactiondao=new TransactionDAO();
        TransactionDTO transactiondto=new TransactionDTO(id,accountNumber,amount,transactionType,timestamp);
        try {
			int result=transactiondao.userTransaction(transactiondto);
			if(result>0)
			{
				req.setAttribute("done","transaction done successfully...");
			}
			else
			{
				req.setAttribute("done","");
			}
			resp.sendRedirect("addtransaction?action=view");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String action = req.getParameter("action");
		TransactionDAO transactiondao=new TransactionDAO();
		if ("view".equals(action)) 
		{
			try {
				Connection c=transactiondao.createConnection();
				List<TransactionDTO> transactions = new ArrayList<>();
	            Statement statement =c.createStatement();
	            ResultSet rs = statement.executeQuery("select * from transactions");
	            while (rs.next()) 
	            {
                    TransactionDTO transactiondto = new TransactionDTO(rs.getLong("id"),rs.getString("account_number"),rs.getDouble("amount"),rs.getString("transaction_type"), rs.getTimestamp("timestamp"));
                    transactions.add(transactiondto);
                }
	            req.setAttribute("transactions", transactions);
                RequestDispatcher dispatcher=req.getRequestDispatcher("Transaction.jsp");
                dispatcher.forward(req, resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
