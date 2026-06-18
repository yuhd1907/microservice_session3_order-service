package ra.edu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.edu.dto.OrderRequestDTO;
import ra.edu.entity.Order;
import ra.edu.exception.ResourceNotFoundException;
import ra.edu.repository.OrderRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order createOrder(OrderRequestDTO requestDTO) {
        // Giả lập giá lấy từ Product Service
        double mockPrice = 100.0;
        double totalAmount = requestDTO.getQuantity() * mockPrice;

        Order order = Order.builder()
                .customerId(requestDTO.getCustomerId())
                .productId(requestDTO.getProductId())
                .quantity(requestDTO.getQuantity())
                .orderDate(LocalDateTime.now())
                .totalAmount(totalAmount)
                .build();

        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));
    }
}
