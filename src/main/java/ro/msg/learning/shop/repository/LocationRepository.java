package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entity.Location;

import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {
    @Query(value = "SELECT l FROM Location l JOIN Stock s on l.locationId = s.location.locationId WHERE s.product.productId = :productId AND s.quantity >= :quantity order by s.quantity desc")
    List<Location> findAbundantLocation(@Param("productId") Integer productId, @Param("quantity") Integer quantity);

    @Query(value = "SELECT l FROM Location l JOIN Stock s on l.locationId = s.location.locationId WHERE s.product.productId = :productId AND s.quantity >= :quantity")
    List<Location> findSingleLocation(@Param("productId") Integer productId, @Param("quantity") Integer quantity);
}
