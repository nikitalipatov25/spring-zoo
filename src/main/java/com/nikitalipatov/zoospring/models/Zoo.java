package com.nikitalipatov.zoospring.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "zoo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Zoo {

    @Id
    @GeneratedValue
    private int id;

    private Animal animal;

    public Zoo(Animal animal) {
        this.animal = animal;
    }
}
