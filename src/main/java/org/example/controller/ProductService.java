package org.example.controller;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Producto getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Producto createProduct(Producto producto) {
        return productRepository.save(producto);
    }

    public Producto updateProduct(Long id, Producto producto) {
        if (productRepository.existsById(id)) {
            producto.setId(id);
            return productRepository.save(producto);
        }
        return null;
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
