package org.example.controller;


import java.util.Optional;

public interface UserRepository {
    Optional<Usuario> findById(Long id);
    Usuario save(Usuario usuario);
    boolean existsById(Long id);
    void deleteById(Long id);
}