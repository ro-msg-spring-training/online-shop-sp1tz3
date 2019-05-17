package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.OrderInputDTO;
import ro.msg.learning.shop.dto.OrderOutputDTO;
import ro.msg.learning.shop.entity.OrderDetail;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.exception.AddressNotFoundException;
import ro.msg.learning.shop.exception.CustomerNotFoundException;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.service.strategy.OrderStrategy;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderStrategy strategy;
    private final CustomerRepository customerRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final AddressRepository addressRepository;
    private final StockRepository stockRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public Orders createOrder(OrderInputDTO input){
        List<OrderOutputDTO> outputs = new ArrayList<>();
        try {
            outputs = strategy.makeOrderOutputDTO(input);
        }catch(Exception e){
           e.printStackTrace();
        }

        Orders newOrder = new Orders(
                outputs.get(0).getLocation(),
                customerRepository.findById(1).orElseThrow(CustomerNotFoundException::new),
                input.getTimestamp(),
                addressRepository.findById(input.getDeliveryAddressId()).orElseThrow(AddressNotFoundException::new)
        );
        orderRepository.save(newOrder);
        outputs.forEach(o->{
            for(Stock s: o.getLocation().getStocks()){
                if(s.getProduct().equals(o.getProduct())){
                    if(s.getQuantity().equals(o.getQuantity()))
                        stockRepository.delete(s);
                    else {
                        s.setQuantity(s.getQuantity()-o.getQuantity());
                    }
                }
            }
            orderDetailRepository.save(new OrderDetail(newOrder, o.getProduct(), o.getQuantity()));
        });

        return newOrder;
    }

}
