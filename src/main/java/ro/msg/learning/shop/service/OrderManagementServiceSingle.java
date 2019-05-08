package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.repository.RepositoryFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderManagementServiceSingle{
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public Orders createOrder() {
        ArrayList<Location> locations = new ArrayList<>(repositoryFactory.createLocationRepository().findAll());
        locations.forEach(l->
                System.out.println(l.getName())
        );
        return null;
    }
}
