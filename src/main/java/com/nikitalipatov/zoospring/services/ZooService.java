package com.nikitalipatov.zoospring.services;

import com.nikitalipatov.zoospring.models.Animal;
import com.nikitalipatov.zoospring.models.Zoo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ZooService {

    Animal findByName(String name);
    List<Zoo> getAnimals();
    List<Animal> getByClass(String clazz);
    List<Animal> findBySign(String sign, String value);
    Boolean deleteAnimal(String name);
    Zoo addAnimal(String animal, String name, int legs, String type, String color, String area);
    Animal editAnimal(String name, String newName);
    ArrayList<String> compareAnimals(String first, String second);
}
