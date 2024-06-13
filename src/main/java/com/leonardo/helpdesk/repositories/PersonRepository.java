package com.leonardo.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardo.helpdesk.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

}
