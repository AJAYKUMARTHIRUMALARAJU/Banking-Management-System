package Bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bank.dao.TransactionDAO;
import Bank.dto.TransactionDTO;

@WebServlet("/moneyTransfer")
public class MoneyTransferServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TransactionDAO transactionDAO = new TransactionDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long senderAccountNumber = Long.parseLong(request.getParameter("senderAccountNumber"));
            long recipientAccountNumber = Long.parseLong(request.getParameter("recipientAccountNumber"));
            String email = request.getParameter("email");
            String pin = request.getParameter("pin");
            int amount = Integer.parseInt(request.getParameter("amount"));

            // Validate sender account with email
            if (!transactionDAO.validateAccountWithEmail(senderAccountNumber, email)) {
                setErrorMessage(request, response, "Invalid account number or email.");
                return;
            }

            // Validate PIN
            if (!transactionDAO.validateHashedPin(pin, senderAccountNumber)) {
                setErrorMessage(request, response, "Invalid PIN.");
                return;
            }

            // Check if the recipient account exists
            if (!transactionDAO.validateAccountNumber(recipientAccountNumber)) {
                setErrorMessage(request, response, "Recipient account does not exist.");
                return;
            }

            // Check sender's balance
            double senderBalance = transactionDAO.getAccountBalance(senderAccountNumber);
            if (amount <= 0 || amount > senderBalance) {
                setErrorMessage(request, response, "Insufficient funds.");
                return;
            }

            // Insert transaction
            TransactionDTO transaction = new TransactionDTO();
            transaction.setAccountNumber(senderAccountNumber);
            transaction.setRecipientNumber(recipientAccountNumber);
            transaction.setAmount(amount);
            transaction.setTranType("Transfer");
            transaction.setTranTime(java.time.LocalTime.now().toString());
            transaction.setTranDate(java.time.LocalDate.now().toString());
            transaction.setEmail(email);
            transactionDAO.insertTransaction(transaction);

            // Update account balances
            transactionDAO.updateAccountBalance(senderAccountNumber, -amount);
            transactionDAO.updateAccountBalance(recipientAccountNumber, amount);

            request.setAttribute("accountNumber", senderAccountNumber);
            request.setAttribute("success", "Transfer successful.");
            request.getRequestDispatcher("transactionHistory.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            setErrorMessage(request, response, "An error occurred during the transfer.", e);
        }
    }

    private void setErrorMessage(HttpServletRequest request, HttpServletResponse response, String message, Exception e) throws ServletException, IOException {
        request.setAttribute("error", message + " - " + e.getMessage());
        e.printStackTrace();  // Log the exception for debugging
        request.getRequestDispatcher("transfer.jsp").forward(request, response);
    }
}
