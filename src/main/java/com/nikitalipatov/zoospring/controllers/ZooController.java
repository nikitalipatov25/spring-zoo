package com.nikitalipatov.zoospring.controllers;

import com.nikitalipatov.zoospring.models.Animal;
import com.nikitalipatov.zoospring.factory.AnimalFactory;
import com.nikitalipatov.zoospring.models.Zoo;
import com.nikitalipatov.zoospring.services.ZooService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/zoo")
public class ZooController {

    private final ZooService zooService;

    @GetMapping("/animals")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<Zoo> getAnimals() {
        return zooService.getAnimals();
    }

    @GetMapping("/animals/{clazz}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<Animal> getByClass(@PathVariable String clazz) {
        return zooService.getByClass(clazz);
    }

    @GetMapping("/animal/{name}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Animal findByName(@PathVariable(name = "name")String name) {
        return zooService.findByName(name);
    }

    @GetMapping("/animal/{sign}/{value}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<Animal> findBySign(
            @PathVariable(name = "sign")String sign,
            @PathVariable(name = "value")String value
    ) {
        return zooService.findBySign(sign, value);
    }

    @DeleteMapping("/animal/{name}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Boolean deleteAnimal(@PathVariable(name = "name")String name) {
        return zooService.deleteAnimal(name);

    }

    record animalRecord(String animal, String name, int legs, String type, String color, String area) {}

    @PostMapping("/animal/add")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Zoo addAnimal(@RequestBody animalRecord animal) {
        return zooService.addAnimal(animal.animal, animal.name,
                animal.legs, animal.type, animal.color, animal.area);
    }

    @PutMapping("animal/{name}/to/{newName}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Animal editAnimal(@PathVariable(name = "name")String name,
                                        @PathVariable(name = "newName")String newName) {
        return zooService.editAnimal(name, newName);
    }

    @GetMapping(value = "animal/{first}/compare/{second}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ArrayList<String> compareAnimals(@PathVariable(name = "first")String first,
                                                           @PathVariable(name = "second")String second) {
        return zooService.compareAnimals(first, second);
    }
}
