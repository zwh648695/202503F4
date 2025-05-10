package com.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
