package com.nikitalipatov.zoospring;

import com.nikitalipatov.zoospring.models.*;
import com.nikitalipatov.zoospring.repos.ZooRepository;
import com.nikitalipatov.zoospring.services.ZooServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ZooSpringApplicationTests {

    @InjectMocks
    ZooServiceImpl zooServiceMock;

    @Mock
    private ZooRepository zooRepository;

    @Test
    void findByName() {

        List<Zoo> animals = new ArrayList<>();
        Zoo zoo = new Zoo(new Bear("Rayan", 4, "predator", "black", "America"));
        animals.add(zoo);
        when(zooRepository.findAll()).thenReturn(animals);

        Animal actual = zooServiceMock.findByName("Rayan");

        assertEquals("Rayan", actual.getName());
    }

    @Test
    void findBySign() {

        List<Zoo> animals = new ArrayList<>();
        Zoo wolf1 = new Zoo(new Wolf("Wolf1", 4, "predator", "gray", "Eurasia"));
        Zoo wolf2 = new Zoo(new Wolf("Wolf2", 4, "predator", "black", "Eurasia"));
        Zoo wolf3 = new Zoo(new Wolf("Wolf3", 4, "predator", "white", "America"));
        animals.add(wolf1);
        animals.add(wolf2);
        animals.add(wolf3);
        when(zooRepository.findAll()).thenReturn(animals);

        List<Animal> actual = zooServiceMock.findBySign("area", "Eurasia");

        assertEquals(2, actual.size());
    }

    @Test
    void getByClass() {

        List<Zoo> animals = new ArrayList<>();
        Zoo wolf = new Zoo(new Wolf("Wolf", 4, "predator", "gray", "Eurasia"));
        Zoo tiger = new Zoo(new Tiger("Tiger", 4, "predator", "black", "Eurasia"));
        Zoo bear = new Zoo(new Bear("Bear", 4, "predator", "white", "America"));
        animals.add(wolf);
        animals.add(tiger);
        animals.add(bear);
        when(zooRepository.findAll()).thenReturn(animals);

        List<Animal> actual = zooServiceMock.getByClass("Bear");

        assertEquals(1, actual.size());
    }

    @Test
    void deleteAnimal() {

        List<Zoo> animals = new ArrayList<>();
        Zoo wolf = new Zoo(new Wolf("Wolf", 4, "predator", "gray", "Eurasia"));
        Zoo tiger = new Zoo(new Tiger("Tiger", 4, "predator", "black", "Eurasia"));
        Zoo bear = new Zoo(new Bear("Bear", 4, "predator", "white", "America"));
        animals.add(wolf);
        animals.add(tiger);
        animals.add(bear);
        when(zooRepository.findAll()).thenReturn(animals);

        Mockito.verify(zooServiceMock).deleteAnimal("Bear");
    }

    @Test
    void editAnimal() {
        List<Zoo> animals = new ArrayList<>();
        Zoo wolf = new Zoo(new Wolf("Wolf", 4, "predator", "gray", "Eurasia"));
        Zoo tiger = new Zoo(new Tiger("Tiger", 4, "predator", "black", "Eurasia"));
        Zoo bear = new Zoo(new Bear("Bear", 4, "predator", "white", "America"));
        animals.add(wolf);
        animals.add(tiger);
        animals.add(bear);
        when(zooRepository.findAll()).thenReturn(animals);

        var actual = zooServiceMock.editAnimal("Wolf", "Bob");
        assertEquals("Bob", actual.getName());
    }

    @Test
    void compareAnimals() {
        List<Zoo> animals = new ArrayList<>();
        Zoo wolf = new Zoo(new Wolf("Wolf", 4, "predator", "white", "Eurasia"));
        Zoo tiger = new Zoo(new Tiger("Tiger", 4, "predator", "black", "Eurasia"));
        Zoo bear = new Zoo(new Bear("Bear", 4, "predator", "white", "America"));
        animals.add(wolf);
        animals.add(tiger);
        animals.add(bear);
        when(zooRepository.findAll()).thenReturn(animals);

        var actual = zooServiceMock.compareAnimals("Wolf", "Bear");

        assertEquals(3, actual.size());
    }
}
