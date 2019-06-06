package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.dto.SupplierDTO;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.Supplier;
import ro.msg.learning.shop.repository.SupplierRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository repo;

    @Transactional
    public List<SupplierDTO> getAll(){
        Iterable<Supplier> suppliers = repo.findAll();
        List<Supplier> supplierList = new ArrayList<>();
        suppliers.forEach(supplierList::add);
        return supplierList.stream().map(SupplierDTO::ofEntity).collect(Collectors.toList());
    }
}
