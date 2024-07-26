package org.example.controller;

import java.util.Objects;

public class Orden {
    private Long id;
    private String description;

    // Constructor, getters, setters, equals, and hashCode

    public Orden(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orden orden = (Orden) o;
        return id.equals(orden.id) && description.equals(orden.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }
}