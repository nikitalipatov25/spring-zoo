package com.nikitalipatov.zoospring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Wolf implements Animal {

    @Serial
    private static final long serialVersionUID = 6529685098267757691L;

    private String name;
    private int legs;
    private String type;
    private String color;
    private String area;

    @Override
    public String toString() {
        return "Wolf, name='" + name + '\'' +
                ", legs=" + legs +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", area='" + area + '\'';
    }
}
