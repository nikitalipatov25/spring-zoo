package com.nikitalipatov.zoospring.services;

import com.nikitalipatov.zoospring.factory.AnimalFactory;
import com.nikitalipatov.zoospring.models.Animal;
import com.nikitalipatov.zoospring.models.Zoo;
import com.nikitalipatov.zoospring.repos.ZooRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.nikitalipatov.zoospring.constants.Constants.*;

@Service
@RequiredArgsConstructor
public class ZooServiceImpl implements ZooService {

    private final ZooRepository zooRepository;
    private final AnimalFactory animalFactory;

    @Override
    public List<Zoo> getAnimals() {
        return zooRepository.findAll();
    }

    @Override
    public List<Animal> getByClass(String clazz) {
        var zoo = zooRepository.findAll();
        List<Animal> animals = new ArrayList<>();
        for (Zoo value : zoo) {
            if (value.getAnimal().getClass().getSimpleName().equals(clazz)) {
                animals.add(value.getAnimal());
            }
        }
        return animals;
    }

    @Override
    public Animal findByName(String name) {
        var result = zooRepository.findAll();
        Animal animal = null;
        for (Zoo zoo : result) {
            if (zoo.getAnimal().getName().equals(name)) {
                animal = zoo.getAnimal();
            }
        }
        if (animal != null) {
            return animal;
        } else throw new NullPointerException("No such animal " + name);
    }

    @Override
    public List<Animal> findBySign(String sign, String value) {
        List<Zoo> zoo = zooRepository.findAll();
        return switch (sign) {
            case LEGS -> getLegs(zoo, value);
            case TYPE -> getType(zoo, value);
            case COLOR -> getColor(zoo, value);
            case AREA -> getArea(zoo, value);
            default -> throw new UnsupportedOperationException("Not supported for " + sign);
        };
    }

    public List<Animal> getLegs(List<Zoo> zoo, String value) {
        List<Animal> animals = new ArrayList<>();
        for (Zoo item : zoo) {
            if (item.getAnimal().getLegs() == Integer.parseInt(value)) {
                animals.add(item.getAnimal());
            }
        }
        return animals;
    }

    public List<Animal> getType(List<Zoo> zoo, String value) {
        List<Animal> animals = new ArrayList<>();
        for (Zoo item : zoo) {
            if (item.getAnimal().getType().equals(value)) {
                animals.add(item.getAnimal());
            }
        }
        return animals;
    }

    public List<Animal> getColor(List<Zoo> zoo, String value) {
        List<Animal> animals = new ArrayList<>();
        for (Zoo item : zoo) {
            if (item.getAnimal().getColor().equals(value)) {
                animals.add(item.getAnimal());
            }
        }
        return animals;
    }

    public List<Animal> getArea(List<Zoo> zoo, String value) {
        List<Animal> animals = new ArrayList<>();
        for (Zoo item : zoo) {
            if (item.getAnimal().getArea().equals(value)) {
                animals.add(item.getAnimal());
            }
        }
        return animals;
    }

    @Override
    public void deleteAnimal(String name) {
        List<Zoo> zoo = zooRepository.findAll();
        for (Zoo value : zoo) {
            if (value.getAnimal().getName().equals(name)) {
                zooRepository.deleteById(value.getId());
            }
        }
    }

    @Override
    public Zoo addAnimal(String animal, String name, int legs,
                         String type, String color, String area) {
        var result = switch (animal) {
            case BEAR -> animalFactory.createBear(name, legs, type, color, area);
            case WOLF -> animalFactory.createWolf(name, legs, type, color, area);
            case TIGER -> animalFactory.createTiger(name, legs, type, color, area);
            default -> throw new UnsupportedOperationException("Not supported for " + animal);
        };
        Zoo addToZoo = new Zoo(result);
        return zooRepository.save(addToZoo);
    }

    @Override
    public Animal editAnimal(String name, String newName) {
        List<Zoo> zoo = zooRepository.findAll();
        Zoo entity = null;
        for (Zoo value : zoo) {
            if (value.getAnimal().getName().equals(name)) {
                value.getAnimal().setName(newName);
                entity = zooRepository.save(value);
            }
        }
        if (entity != null) {
            return entity.getAnimal();
        } else throw new NullPointerException("No such animal " + name);
    }

    @Override
    public ArrayList<String> compareAnimals(String first, String second) {
        List<Zoo> zoo = zooRepository.findAll();

        String[] animal1 = new String[0];
        String[] animal2 = new String[0];

        for (Zoo value : zoo) {
            if (value.getAnimal().getName().equals(first)) {
                animal1 = value.getAnimal().toString().split(", ");
            } else if (value.getAnimal().getName().equals(second)) {
                animal2 = value.getAnimal().toString().split(", ");
            }
        }

        ArrayList<String> plus = new ArrayList<>();
        ArrayList<String> minus = new ArrayList<>();

        if (animal1.length != 0 && animal2.length != 0) {
            for (int i = 0; i < animal1.length; i++) {
                if (animal1[i].equals(animal2[i])) {
                    plus.add(animal1[i]);
                } else {
                    minus.add(animal1[i] + " - " + animal2[i]);
                }
            }
        } else {
            minus.add("Мы не нашли с чем сравнить");
        }
        return minus;
    }
}
