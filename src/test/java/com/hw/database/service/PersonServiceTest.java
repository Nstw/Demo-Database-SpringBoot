package com.hw.database.service;

import com.hw.database.dto.PersonDTO;
import com.hw.database.entity.Person;
import com.hw.database.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPerson_Success() {
        // Arrange
        PersonDTO personDTO = new PersonDTO();
        personDTO.setPersonId("P001");
        personDTO.setName("John Doe");
        personDTO.setAge(30);

        Person person = new Person();
        person.setPersonId("P001");
        person.setName("John Doe");
        person.setAge(30);

        when(personRepository.save(any(Person.class))).thenReturn(person);

        // Act
        PersonDTO result = personService.createPerson(personDTO);

        // Assert
        assertNotNull(result);
        assertEquals("P001", result.getPersonId());
        assertEquals("John Doe", result.getName());
        assertEquals(30, result.getAge());

        verify(personRepository).save(any(Person.class));
    }

    @Test
    void getPersonByPersonId_Success() {
        // Arrange
        Person person = new Person();
        person.setPersonId("P001");
        person.setName("John Doe");
        person.setAge(30);

        when(personRepository.findByPersonId("P001")).thenReturn(Optional.of(person));

        // Act
        Optional<PersonDTO> result = personService.getPersonByPersonId("P001");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("P001", result.get().getPersonId());
        assertEquals("John Doe", result.get().getName());
        assertEquals(30, result.get().getAge());

        verify(personRepository).findByPersonId("P001");
    }

    @Test
    void getPersonByPersonId_NotFound() {
        // Arrange
        when(personRepository.findByPersonId("P001")).thenReturn(Optional.empty());

        // Act
        Optional<PersonDTO> result = personService.getPersonByPersonId("P001");

        // Assert
        assertTrue(result.isEmpty());
        verify(personRepository).findByPersonId("P001");
    }

    @Test
    void getAllPersons_Success() {
        // Arrange
        Person person1 = new Person();
        person1.setPersonId("P001");
        person1.setName("John Doe");
        person1.setAge(30);

        Person person2 = new Person();
        person2.setPersonId("P002");
        person2.setName("Jane Doe");
        person2.setAge(25);

        when(personRepository.findAll()).thenReturn(Arrays.asList(person1, person2));

        // Act
        List<PersonDTO> result = personService.getAllPersons();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("P001", result.get(0).getPersonId());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals(30, result.get(0).getAge());
        assertEquals("P002", result.get(1).getPersonId());
        assertEquals("Jane Doe", result.get(1).getName());
        assertEquals(25, result.get(1).getAge());

        verify(personRepository).findAll();
    }
}