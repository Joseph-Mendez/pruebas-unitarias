package org.example.controller;

import java.util.Optional;

public interface OrderRepository {
    Optional<Orden> findById(Long id);
    Orden save(Orden orden);
    boolean existsById(Long id);
    void deleteById(Long id);
}