package com.hw.database.service;

import com.hw.database.dto.PersonDTO;
import com.hw.database.entity.Person;
import com.hw.database.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public PersonDTO createPerson(PersonDTO personDTO) {
        Person person = new Person();
        person.setPersonId(personDTO.getPersonId());
        person.setName(personDTO.getName());
        person.setAge(personDTO.getAge());

        Person savedPerson = personRepository.save(person);
        return convertToDTO(savedPerson);
    }

    public Optional<PersonDTO> getPersonByPersonId(String personId) {
        return personRepository.findByPersonId(personId)
                .map(this::convertToDTO);
    }

    public List<PersonDTO> getAllPersons() {
        return personRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private PersonDTO convertToDTO(Person person) {
        PersonDTO dto = new PersonDTO();
        dto.setPersonId(person.getPersonId());
        dto.setName(person.getName());
        dto.setAge(person.getAge());
        return dto;
    }
}