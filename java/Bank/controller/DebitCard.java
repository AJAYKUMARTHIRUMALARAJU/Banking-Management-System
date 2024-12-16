//package Bank.controller;
//
//import java.io.IOException;
//import java.util.Random;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import Bank.dao.DebitCardDao;
//import Bank.dto.DebitCardDto;
//
//@WebServlet("/DebitCardServlet")
//public class DebitCard extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String name = req.getParameter("name");
//        String email = req.getParameter("email");
//        String phone = req.getParameter("phone");
//
//        try {
//            Long cardNumber = generateRandomCardNumber();
//            int cvv = generateRandomCVV();
//
//            DebitCardDto debitCard = new DebitCardDto(name, email, cardNumber, cvv);
//            DebitCardDao debitCardDao = new DebitCardDao();
//            debitCardDao.createTableIfNotExists();
//            debitCardDao.insertDebitCard(debitCard);
//
//            req.setAttribute("name", debitCard.getName());
//            req.setAttribute("email", debitCard.getEmail());
//            req.setAttribute("phone", phone);
//            req.setAttribute("cardNumber", debitCard.getCardNumber());
//            req.setAttribute("cvv", debitCard.getCvv());
//            req.setAttribute("expiryDate", "12/26");
//
//            req.getRequestDispatcher("DebitCardConfirmation.jsp").forward(req, resp);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            req.setAttribute("error", "An error occurred while processing the debit card request.");
//            req.getRequestDispatcher("DebitCardForm.jsp").include(req, resp);
//        }
//    }
//
//    private Long generateRandomCardNumber() {
//        Random random = new Random();
//        return 1000_0000_0000_0000L + (long) (random.nextDouble() * 9000_0000_0000_0000L);
//    }
//
//    private int generateRandomCVV() {
//        Random random = new Random();
//        return 100 + random.nextInt(900);
//    }
//}
