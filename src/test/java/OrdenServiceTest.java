import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.example.controller.Orden;
import org.example.controller.OrderRepository;
import org.example.controller.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class OrdenServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetOrderByIdOrderExists() {
        Orden orden = new Orden(1L, "Order1");
        when(orderRepository.findById(1L)).thenReturn(Optional.of(orden));
        Orden result = orderService.getOrderById(1L);
        assertEquals(orden, result);
    }

    @Test
    public void testGetOrderByIdOrderDoesNotExist() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());
        Orden result = orderService.getOrderById(1L);
        assertNull(result);
    }

    @Test
    public void testCreateOrden() {
        Orden orden = new Orden(null, "Order2");
        Orden savedOrden = new Orden(1L, "Order2");
        when(orderRepository.save(orden)).thenReturn(savedOrden);
        Orden result = orderService.createOrder(orden);
        assertEquals(savedOrden, result);
    }

    @Test
    public void testUpdateOrderOrderExists() {
        Orden orden = new Orden(null, "Order2");
        Orden updatedOrden = new Orden(1L, "Order2");
        when(orderRepository.existsById(1L)).thenReturn(true);
        when(orderRepository.save(orden)).thenReturn(updatedOrden);
        Orden result = orderService.updateOrder(1L, orden);
        assertEquals(updatedOrden, result);
    }

    @Test
    public void testUpdateOrderDoesNotExist() {
        Orden orden = new Orden(null, "Order2");
        when(orderRepository.existsById(1L)).thenReturn(false);
        Orden result = orderService.updateOrder(1L, orden);
        assertNull(result);
    }

    @Test
    public void testDeleteOrderExists() {
        when(orderRepository.existsById(1L)).thenReturn(true);
        doNothing().when(orderRepository).deleteById(1L);
        boolean result = orderService.deleteOrder(1L);
        assertTrue(result);
    }

}