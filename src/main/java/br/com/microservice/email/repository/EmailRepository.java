package br.com.microservice.email.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.microservice.email.model.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, UUID>{

}
