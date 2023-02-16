package com.nikitalipatov.zoospring.models;

import java.io.Serializable;

public interface Animal extends Serializable {

    String getName();

    public abstract void setName(String name);

    public abstract int getLegs();

    public abstract String getType();

    public abstract String getColor();

    public abstract String getArea();

}
