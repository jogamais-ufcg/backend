package com.jogamais.ufcg.services;

import com.jogamais.ufcg.enums.StatusEmail;
import com.jogamais.ufcg.models.Email;
import com.jogamais.ufcg.models.User;
import com.jogamais.ufcg.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Transactional
    public Email sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            emailSender.send(message);

            email.setStatusEmail(StatusEmail.ERROR);
        } catch (MailException e){
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(email);
        }
    }

    public void sendConfirmationEmail(User user) {
        Email email = new Email();
        email.setOwnerRef(user.getId());
        email.setEmailFrom("testejogamais@gmail.com");
        email.setEmailTo(user.getEmail());
        email.setSubject("Confirmação de endereço de e-mail");
        email.setSendDateEmail(LocalDateTime.now());
        String confirmationLink = "http://localhost:3000/cadastro/sucesso?id=" + user.getId();
        email.setText("Por favor, confirme seu e-mail clicando no link: " + confirmationLink);
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(user.getEmail());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            emailSender.send(message);

            email.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e){
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            emailRepository.save(email);
        }
    }

    public Page<Email> findAll(Pageable pageable) {
        return  emailRepository.findAll(pageable);
    }

//    public Optional<EmailModel> findById(UUID emailId) {
//        return emailRepository.findById(emailId);
//    }
}