package com.kata.preproject.PP_3_1_2_SpringBoot.dao;


import com.kata.preproject.PP_3_1_2_SpringBoot.models.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PersonDAOImpl implements PersonDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Person> getAllPeople() {
        return entityManager.createQuery("select p from Person p", Person.class).getResultList();

    }

    @Override
    public Person getUser(int id) {
        return entityManager.find(Person.class, id);
    }

    @Override
    public void save(Person person) {
        entityManager.persist(person);
    }


    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(Person.class, id));
    }

}
