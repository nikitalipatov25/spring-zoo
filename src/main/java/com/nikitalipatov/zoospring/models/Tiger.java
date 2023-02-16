package com.nikitalipatov.zoospring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tiger implements Animal {

    @Serial
    private static final long serialVersionUID = 6529685098267757694L;

    private String name;
    private int legs;
    private String type;
    private String color;
    private String area;

    @Override
    public String toString() {
        return "Tiger, name='" + name + '\'' +
                ", legs=" + legs +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", area='" + area + '\'';
    }

    public void setName(String name) {
        this.name = name;
    }
}