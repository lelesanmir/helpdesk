package com.leonardo.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.leonardo.helpdesk.domain.Called;
import com.leonardo.helpdesk.domain.Client;
import com.leonardo.helpdesk.domain.Technician;
import com.leonardo.helpdesk.domain.enums.Priority;
import com.leonardo.helpdesk.domain.enums.Profile;
import com.leonardo.helpdesk.domain.enums.Status;
import com.leonardo.helpdesk.repositories.CalledRepository;
import com.leonardo.helpdesk.repositories.ClientRepository;
import com.leonardo.helpdesk.repositories.TechnicianRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	@Autowired
	private TechnicianRepository technicianRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private CalledRepository calledRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Technician tec1 = new Technician(null, "Leonardo S. Miranda", "63653230268", "leo@gmail.com", "123");
		tec1.addProfile(Profile.ADMIN);

		Client cli1 = new Client(null, "Jeremias Isac", "54398709821", "jeremias@hotmail.com", "321");

		Called c1 = new Called(null, Priority.AVERAGE, Status.PROGRESS, "Called 01", "First called", tec1, cli1);

		technicianRepository.saveAll(Arrays.asList(tec1));
		clientRepository.saveAll(Arrays.asList(cli1));
		calledRepository.saveAll(Arrays.asList(c1));

	}

}
