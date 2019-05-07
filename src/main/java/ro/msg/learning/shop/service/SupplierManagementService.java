package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entity.Supplier;
import ro.msg.learning.shop.exception.SupplierNotFoundException;
import ro.msg.learning.shop.repository.RepositoryFactory;
import ro.msg.learning.shop.repository.SupplierRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierManagementService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Supplier> listSuppliers(){
        return repositoryFactory.createSupplierRepository().findAll();
    }

    @Transactional
    public Supplier addSupplier(String name){
        return repositoryFactory.createSupplierRepository().save(
                new Supplier(name)
        );
    }

    @Transactional
    public void removeSupplier(int id){
        SupplierRepository repo = repositoryFactory.createSupplierRepository();
        Supplier supplier = repo.findById(id).orElseThrow(SupplierNotFoundException::new);
        repo.remove(supplier);
    }

    @Transactional
    public Supplier findByName(String name){
        SupplierRepository repo = repositoryFactory.createSupplierRepository();
        for(Supplier s: repo.findAll()){
            if(s.getName().equals(name))
                return s;
        }
        return null;
    }
}
