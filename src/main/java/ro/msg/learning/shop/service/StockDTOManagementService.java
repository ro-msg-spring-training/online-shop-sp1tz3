package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.repository.RepositoryFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockDTOManagementService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<StockDTO> listStocks(){
        return repositoryFactory.createStockRepository().findAll().stream()
                .map(StockDTO::ofEntity)
                .collect(Collectors.toList());
    }
}

