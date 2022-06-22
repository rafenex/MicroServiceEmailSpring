package br.com.microservice.email.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.microservice.email.enums.StatusEmail;
import br.com.microservice.email.model.EmailModel;
import br.com.microservice.email.repository.EmailRepository;

@Service
public class EmailService {
	
	@Autowired
	EmailRepository emailRepository;
	
	@Autowired
	private JavaMailSender emailSender;


	public EmailModel sendEmail(EmailModel emailModel) {
		emailModel.setSendDateEmail(LocalDateTime.now());
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(emailModel.getEmailFrom());
			message.setTo(emailModel.getEmailTo());
			message.setSubject(emailModel.getSubject());
			message.setText(emailModel.getText());			
			emailSender.send(message);
			
			emailModel.setStatusEmail(StatusEmail.SENT);
			return emailRepository.save(emailModel);
		} catch (MailException e) {
			emailModel.setStatusEmail(StatusEmail.ERROR);
			return emailRepository.save(emailModel);
		}

		
	}

}
