package org.example.controller;

import java.util.Optional;

public interface ProductRepository {
    Optional<Producto> findById(Long id);
    Producto save(Producto producto);
    boolean existsById(Long id);
    void deleteById(Long id);
}
