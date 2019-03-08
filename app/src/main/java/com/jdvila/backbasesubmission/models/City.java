package com.jdvila.backbasesubmission.models;

import java.io.Serializable;

public class City implements Comparable<City>, Serializable {

    private final String country;
    private final String name;
    private final int _id;
    private final Coord coord;

    public City(String country, String name, int _id, Coord coord) {
        this.country = country;
        this.name = name;
        this._id = _id;
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public int get_id() {
        return _id;
    }

    public Coord getCoord() {
        return coord;
    }

    @Override
    public int compareTo(City o) {
        int city1 = this.getName().toLowerCase().compareTo(o.getName().toLowerCase());
        if (city1 == 0) {
            int city2 = this.getCountry().toLowerCase().compareTo(o.getCountry().toLowerCase());
            if (city2 == 0) {
                return city2;
            }
        }
        return city1;
    }
}
