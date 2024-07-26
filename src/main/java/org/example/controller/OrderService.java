package org.example.controller;

public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Orden getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Orden createOrder(Orden orden) {
        return orderRepository.save(orden);
    }

    public Orden updateOrder(Long id, Orden orden) {
        if (orderRepository.existsById(id)) {
            orden.setId(id);
            return orderRepository.save(orden);
        }
        return null;
    }

    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}