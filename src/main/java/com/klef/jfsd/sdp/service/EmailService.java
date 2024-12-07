package com.klef.jfsd.sdp.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendTemporaryPasswordEmail(String name,String username,String recipientEmail, String temporaryPassword) throws MessagingException {
        // Create a MimeMessage
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        // Set the recipient, subject, and HTML content
        helper.setTo(recipientEmail);
        helper.setSubject("You are invited to BeyondEDU :)");

        String emailContent = "<!DOCTYPE html>" +
        	    "<html lang=\"en\">" +
        	    "<head>" +
        	    "    <meta charset=\"UTF-8\">" +
        	    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
        	    "    <title>Welcome to BeyondEDU</title>" +
        	    "    <style>" +
        	    "        body {" +
        	    "            font-family: Arial, sans-serif;" +
        	    "            line-height: 1.6;" +
        	    "            color: #333333;" +
        	    "            margin: 0;" +
        	    "            padding: 0;" +
        	    "        }" +
        	    "        .container {" +
        	    "            max-width: 600px;" +
        	    "            margin: 0 auto;" +
        	    "            padding: 20px;" +
        	    "            background-color: #f9f9f9;" +
        	    "        }" +
        	    "        .header {" +
        	    "            background-color: #4a90e2;" +
        	    "            color: #ffffff;" +
        	    "            text-align: center;" +
        	    "            padding: 20px;" +
        	    "        }" +
        	    "        .content {" +
        	    "            background-color: #ffffff;" +
        	    "            padding: 20px;" +
        	    "            border-radius: 5px;" +
        	    "            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);" +
        	    "        }" +
        	    "        h1 {" +
        	    "            color: #4a90e2;" +
        	    "            margin-top: 0;" +
        	    "        }" +
        	    "        .account-details {" +
        	    "            background-color: #f0f0f0;" +
        	    "            padding: 15px;" +
        	    "            border-radius: 5px;" +
        	    "            margin-bottom: 20px;" +
        	    "        }" +
        	    "        .account-details p {" +
        	    "            margin: 5px 0;" +
        	    "        }" +
        	    "        .instructions {" +
        	    "            margin-bottom: 20px;" +
        	    "        }" +
        	    "        .instructions ol {" +
        	    "            padding-left: 20px;" +
        	    "        }" +
        	    "        .footer {" +
        	    "            text-align: center;" +
        	    "            margin-top: 20px;" +
        	    "            color: #666666;" +
        	    "            font-size: 12px;" +
        	    "        }" +
        	    "        @media only screen and (max-width: 600px) {" +
        	    "            .container {" +
        	    "                width: 100% !important;" +
        	    "            }" +
        	    "        }" +
        	    "    </style>" +
        	    "</head>" +
        	    "<body>" +
        	    "    <div class=\"container\">" +
        	    "        <div class=\"header\">" +
        	    "            <h1>Welcome to BeyondEDU</h1>" +
        	    "        </div>" +
        	    "        <div class=\"content\">" +
        	    "            <p>Dear " + name + ",</p>" + // Replace 'studentName' with the actual variable holding the student's name
        	    "            <p>We are excited to welcome you to BeyondEDU! You have been granted access to our platform, where you can explore and engage with a wealth of educational resources.</p>" +
        	    "            <div class=\"account-details\">" +
        	    "                <h2>Your Account Details:</h2>" +
        	    "                <p><strong>Username:</strong> " + username + "</p>" + // Replace 'username' with the actual variable holding the student's username
        	    "                <p><strong>Temporary Password:</strong> " + temporaryPassword + "</p>" + // 'temporaryPassword' should hold the generated password
        	    "            </div>" +
        	    "            <div class=\"instructions\">" +
        	    "                <h2>Instructions to Access Your Account:</h2>" +
        	    "                <ol>" +
        	    "                    <li>Visit our website at [Website URL].</li>" +
        	    "                    <li>Log in using your username and the temporary password provided above.</li>" +
        	    "                    <li>Upon your first login, you will be prompted to reset your password. Please choose a strong and secure password that you can remember.</li>" +
        	    "                </ol>" +
        	    "            </div>" +
        	    "            <p>If you encounter any issues or have questions, feel free to reach out to our support team at <a href='mailto:support@beyondedu.example.com'>support@beyondedu.example.com</a>.</p>" +
        	    "            <p>We look forward to seeing you on BeyondEDU!</p>" +
        	    "            <p>Best regards,</p>" +
        	    "            <p>The BeyondEDU Team</p>" +
        	    "        </div>" +
        	    "        <div class=\"footer\">" +
        	    "            <p>Contact us: contact@beyondedu.example.com</p>" +
        	    "            <p>Website: <a href='https://beyondedu.example.com'>www.beyondedu.example.com</a></p>" +
        	    "        </div>" +
        	    "    </div>" +
        	    "</body>" +
        	    "</html>";

        // Set the email content as HTML
        helper.setText(emailContent, true);
        // Send the email
        mailSender.send(message);
    }
}
