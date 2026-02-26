package com.converter.docconverter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FeedbackController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/feedback/submit")
    public String submitFeedback(@RequestParam(name = "name", required = false) String name,
                                 @RequestParam(name = "email", required = false) String email,
                                 @RequestParam("message") String message,
                                 Model model) {
        try {
            // Prepare feedback message
            String feedbackMessage = "Name: " + (name != null && !name.isEmpty() ? name : "Anonymous") + "\n"
                    + "Email: " + (email != null && !email.isEmpty() ? email : "Not provided") + "\n\n"
                    + "Message:\n" + message;

            // Create and send email
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo("patelreeta044@gmail.com"); //
            mail.setText(feedbackMessage);

            mailSender.send(mail);

            // Add success flag for frontend
            model.addAttribute("feedbackSuccess", true);

        } catch (Exception e) {
            e.printStackTrace();
            // Add failure flag for frontend
            model.addAttribute("feedbackError", true);
        }

        // Redirect back to home page (you can also return a dedicated Thymeleaf page)
        return "index";  // Make sure this is your homepage template name
    }
}
