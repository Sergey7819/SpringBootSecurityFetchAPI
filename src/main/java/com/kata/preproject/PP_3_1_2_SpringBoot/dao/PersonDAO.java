package com.kata.preproject.PP_3_1_2_SpringBoot.dao;

import com.kata.preproject.PP_3_1_2_SpringBoot.models.Person;

import java.util.List;

public interface PersonDAO {
    List<Person> getAllPeople();
    Person getUser(int id);

    void save(Person person);


    void delete(int id);
}
