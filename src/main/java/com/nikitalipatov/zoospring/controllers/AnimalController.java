package com.nikitalipatov.zoospring.controllers;

import com.nikitalipatov.zoospring.dto.AnimalRecord;
import com.nikitalipatov.zoospring.models.Animal;
import com.nikitalipatov.zoospring.models.Zoo;
import com.nikitalipatov.zoospring.services.ZooService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/animal")
public class AnimalController {

    private final ZooService zooService;

    @GetMapping("/list")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<Zoo> getAnimals() {
        return zooService.getAnimals();
    }

    @GetMapping("/list/{clazz}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<Animal> getByClass(@PathVariable String clazz) {
        return zooService.getByClass(clazz);
    }

    @GetMapping("/{name}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Animal findByName(@PathVariable(name = "name") String name) {
        return zooService.findByName(name);
    }

    @GetMapping("/{sign}/{value}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<Animal> findBySign(
            @PathVariable(name = "sign") String sign,
            @PathVariable(name = "value") String value
    ) {
        return zooService.findBySign(sign, value);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void deleteAnimal(@PathVariable(name = "name") String name) {
        zooService.deleteAnimal(name);
    }

    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Zoo addAnimal(@RequestBody AnimalRecord animal) {
        return zooService.addAnimal(animal.animal(), animal.name(),
                animal.legs(), animal.type(), animal.color(), animal.area());
    }

    @PutMapping("/{name}/to/{newName}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Animal editAnimal(@PathVariable(name = "name") String name,
                             @PathVariable(name = "newName") String newName) {
        return zooService.editAnimal(name, newName);
    }

    @GetMapping(value = "/{first}/compare/{second}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ArrayList<String> compareAnimals(@PathVariable(name = "first") String first,
                                            @PathVariable(name = "second") String second) {
        return zooService.compareAnimals(first, second);
    }
}
