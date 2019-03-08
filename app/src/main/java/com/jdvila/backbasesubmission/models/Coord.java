package com.jdvila.backbasesubmission.models;

import java.io.Serializable;

public class Coord implements Serializable {
    private final String lon;
    private final String lat;

    public Coord(String lon, String lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public String getLat() {
        return lat;
    }
}

