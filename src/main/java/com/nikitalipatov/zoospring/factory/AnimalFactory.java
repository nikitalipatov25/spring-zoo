package com.nikitalipatov.zoospring.factory;

import com.nikitalipatov.zoospring.models.Animal;
import com.nikitalipatov.zoospring.models.Bear;
import com.nikitalipatov.zoospring.models.Tiger;
import com.nikitalipatov.zoospring.models.Wolf;
import org.springframework.stereotype.Component;

@Component
public class AnimalFactory {

    public Animal createWolf(String name, int legs, String type, String color, String area) {
        return new Wolf(name, legs, type, color, area);
    }

    public Animal createBear(String name, int legs, String type, String color, String area) {
        return new Bear(name, legs, type, color, area);
    }

    public Animal createTiger(String name, int legs, String type, String color, String area) {
        return new Tiger(name, legs, type, color, area);
    }

}
