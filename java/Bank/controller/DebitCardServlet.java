package Bank.controller;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bank.dao.DebitCardDao;
import Bank.dao.LoginAccountDao;
import Bank.dto.CreateAccountDto;
import Bank.dto.DebitCardDto;

@WebServlet("/DebitCardServlet")
public class DebitCardServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        HttpSession session = req.getSession();

        try {
            CreateAccountDto userDetails = new LoginAccountDao().getUserDetailsByEmail(email);
            if (userDetails == null) {
                req.setAttribute("error", "User not found.");
                req.getRequestDispatcher("DebitCardFrom.jsp").include(req, resp);
                return;
            }

            Long cardNumber = generateRandomCardNumber();
            int cvv = generateRandomCVV();

            // Create DebitCardDto object
            DebitCardDto debitCard = new DebitCardDto(userDetails.getLast_Name(), userDetails.getEmail(), cardNumber, cvv);

            DebitCardDao debitCardDao = new DebitCardDao();
            debitCardDao.createTableIfNotExists();

            // Check if the card already exists
            DebitCardDto existingCard = debitCardDao.getDebitCardByCardNumber(cardNumber);
            if (existingCard != null) {
                req.setAttribute("error", "A debit card with this number already exists.");
                req.getRequestDispatcher("DebitCardFrom.jsp").include(req, resp);
                return;
            }

            // Insert new debit card
            debitCardDao.insertDebitCard(debitCard);

            req.setAttribute("cardNumber", debitCard.getCardNumber());
            req.setAttribute("cvv", debitCard.getCvv());
            req.setAttribute("expiryDate", "12/26");

            // Set cookies for card information
            setDebitCardCookies(resp, debitCard);

            // Forward to the result page
            req.getRequestDispatcher("debitCardResult.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred while processing the debit card request: " + e.getMessage());
            req.getRequestDispatcher("DebitCardFrom.jsp").include(req, resp);
        }
    }

    private void setDebitCardCookies(HttpServletResponse resp, DebitCardDto debitCard) {
        Cookie cardNumberCookie = new Cookie("cardNumber", debitCard.getCardNumber().toString());
        Cookie cvvCookie = new Cookie("cvv", String.valueOf(debitCard.getCvv()));
        Cookie expiryDateCookie = new Cookie("expiryDate", "12/26");

        int maxAge = 60 * 60 * 24; // 1 day
        cardNumberCookie.setMaxAge(maxAge);
        cvvCookie.setMaxAge(maxAge);
        expiryDateCookie.setMaxAge(maxAge);

        cardNumberCookie.setHttpOnly(true);
        cvvCookie.setHttpOnly(true);
        expiryDateCookie.setHttpOnly(true);
        
        // Set secure flag as needed
        cardNumberCookie.setSecure(false);
        cvvCookie.setSecure(false);
        expiryDateCookie.setSecure(false);

        resp.addCookie(cardNumberCookie);
        resp.addCookie(cvvCookie);
        resp.addCookie(expiryDateCookie);
    }

    private Long generateRandomCardNumber() {
        Random random = new Random();
        return 1000_0000_0000_0000L + (long) (random.nextDouble() * 9000_0000_0000_0000L);
    }

    private int generateRandomCVV() {
        Random random = new Random();
        return 100 + random.nextInt(900);
    }
}
