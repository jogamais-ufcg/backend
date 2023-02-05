package com.jogamais.ufcg.services;

import com.jogamais.ufcg.enums.StatusEmail;
import com.jogamais.ufcg.models.EmailModel;
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
import java.util.Optional;
import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Transactional
    public EmailModel sendEmail(EmailModel emailModel) {
        emailModel.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e){
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(emailModel);
        }
    }

    public void sendConfirmationEmail(User user) {
        EmailModel emailModel = new EmailModel();
        emailModel.setOwnerRef(user.getId());
        emailModel.setEmailFrom("testejogamais@gmail.com");
        emailModel.setEmailTo(user.getEmail());
        emailModel.setSubject("Confirmação de endereço de e-mail");
        emailModel.setSendDateEmail(LocalDateTime.now());
        String confirmationLink = "http://localhost:3000/cadastro/sucesso?id=" + user.getId();
        emailModel.setText("Por favor, confirme seu e-mail clicando no link: " + confirmationLink);
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(user.getEmail());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e){
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            emailRepository.save(emailModel);
        }
    }

    public Page<EmailModel> findAll(Pageable pageable) {
        return  emailRepository.findAll(pageable);
    }

//    public Optional<EmailModel> findById(UUID emailId) {
//        return emailRepository.findById(emailId);
//    }
}