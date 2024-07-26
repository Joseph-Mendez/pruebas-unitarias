import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.example.controller.Producto;
import org.example.controller.ProductRepository;
import org.example.controller.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProductById_ProductExists() {
        Producto producto = new Producto(1L, "Laptop");
        when(productRepository.findById(1L)).thenReturn(Optional.of(producto));
        Producto result = productService.getProductById(1L);
        assertEquals(producto, result);
    }

    @Test
    public void testGetProductById_ProductDoesNotExist() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        Producto result = productService.getProductById(1L);
        assertNull(result);
    }

    @Test
    public void testCreateProduct() {
        Producto producto = new Producto(null, "Phone");
        Producto savedProducto = new Producto(1L, "Phone");
        when(productRepository.save(producto)).thenReturn(savedProducto);
        Producto result = productService.createProduct(producto);
        assertEquals(savedProducto, result);
    }

    @Test
    public void testUpdateProductProductExists() {
        Producto producto = new Producto(null, "Phone");
        Producto updatedProducto = new Producto(1L, "Phone");
        when(productRepository.existsById(1L)).thenReturn(true);
        when(productRepository.save(producto)).thenReturn(updatedProducto);
        Producto result = productService.updateProduct(1L, producto);
        assertEquals(updatedProducto, result);
    }

    @Test
    public void testUpdateProductProductDoesNotExist() {
        Producto producto = new Producto(null, "Phone");
        when(productRepository.existsById(1L)).thenReturn(false);
        Producto result = productService.updateProduct(1L, producto);
        assertNull(result);
    }

    @Test
    public void testDeleteProductProductExists() {
        when(productRepository.existsById(1L)).thenReturn(true);
        doNothing().when(productRepository).deleteById(1L);
        boolean result = productService.deleteProduct(1L);
        assertTrue(result);
    }

    @Test
    public void testDeleteProductProductDoesNotExist() {
        when(productRepository.existsById(1L)).thenReturn(false);
        boolean result = productService.deleteProduct(1L);
        assertFalse(result);
    }

    @Test
    public void testCreateProductNullProduct() {
        assertThrows(NullPointerException.class, () -> {
            productService.createProduct(null);
        });
    }

    @Test
    public void testUpdateProductInvalidId() {
        Producto producto = new Producto(null, "Phone");
        when(productRepository.existsById(null)).thenReturn(false);
        Producto result = productService.updateProduct(null, producto);
        assertNull(result);
    }

    @Test
    public void testDeleteProductNullId() {
        assertThrows(NullPointerException.class, () -> {
            productService.deleteProduct(null);
        });
    }

    @Test
    public void testGetProductByIdException() {
        when(productRepository.findById(anyLong())).thenThrow(new RuntimeException("Database error"));
        assertThrows(RuntimeException.class, () -> {
            productService.getProductById(1L);
        });
    }
}