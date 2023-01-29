package com.kata.preproject.PP_3_1_2_SpringBoot.service;

import com.kata.preproject.PP_3_1_2_SpringBoot.models.Person;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Component
public class PersonServiceImpl implements PersonService {


    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public List<Person> index() {
        return entityManager.createQuery("select p from Person p", Person.class).getResultList();

    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        return entityManager.find(Person.class, id);
    }

    @Transactional
    public void save(Person person) {
        entityManager.persist(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = entityManager.find(Person.class, id);
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    @Transactional
    public void delete(int id) {
        entityManager.remove(entityManager.find(Person.class, id));
    }
}
