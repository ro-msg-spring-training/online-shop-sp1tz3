package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.exception.OrderDetailNotFoundException;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.repository.RepositoryFactory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailManagementService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<OrderDetail> listOrderDetails(){
        return repositoryFactory.createOrderDetailRepository().findAll();
    }

    @Transactional
    public OrderDetail addOrderDetail(Orders order, Product product, Integer quantity){
        return repositoryFactory.createOrderDetailRepository().save(new OrderDetail(
                order, product, quantity
        ));
    }

    @Transactional
    public void removeOrderDetail(int id){
        OrderDetailRepository repo = repositoryFactory.createOrderDetailRepository();
        OrderDetail orderDetail = repo.findById(id).orElseThrow(OrderDetailNotFoundException::new);
        repo.remove(orderDetail);
    }
}
