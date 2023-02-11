package com.kata.preproject.PP_3_1_2_SpringBoot.service;



import com.kata.preproject.PP_3_1_2_SpringBoot.models.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAllPeople();
    Person getUser(int id);

    void save(Person person);

    void update(Person person);

    void delete(int id);
}
