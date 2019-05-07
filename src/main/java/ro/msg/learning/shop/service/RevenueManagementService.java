package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Revenue;
import ro.msg.learning.shop.exception.RevenueNotFoundException;
import ro.msg.learning.shop.repository.RepositoryFactory;
import ro.msg.learning.shop.repository.RevenueRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RevenueManagementService {
    private RepositoryFactory repositoryFactory;

    @Transactional
    public List<Revenue> listRevenues(){
        return repositoryFactory.createRevenueRepository().findAll();
    }

    @Transactional
    public Revenue addRevenue(Location location, LocalDate date, BigDecimal sum){
        return repositoryFactory.createRevenueRepository().save(
                new Revenue(location, date, sum)
        );
    }

    @Transactional
    public void removeRevenue(int id){
        RevenueRepository repo = repositoryFactory.createRevenueRepository();
        Revenue revenue = repo.findById(id).orElseThrow(RevenueNotFoundException::new);
        repo.remove(revenue);
    }
}
