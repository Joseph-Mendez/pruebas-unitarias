package org.example.controller;

import java.util.Objects;

public class Usuario {
    private Long id;
    private String name;

    // Constructor, getters, setters, equals, and hashCode

    public Usuario(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id.equals(usuario.id) && name.equals(usuario.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}