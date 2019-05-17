package ro.msg.learning.shop.service.strategy;

import ro.msg.learning.shop.dto.OrderInputDTO;
import ro.msg.learning.shop.dto.OrderOutputDTO;
import ro.msg.learning.shop.exception.OrderNotCreatedException;

import java.util.List;

public interface OrderStrategy {
    public List<OrderOutputDTO> makeOrderOutputDTO(OrderInputDTO input) throws OrderNotCreatedException;
}
