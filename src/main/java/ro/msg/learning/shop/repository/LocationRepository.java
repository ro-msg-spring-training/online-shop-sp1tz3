package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.entity.Location;
import java.util.List;
import java.util.Optional;

public interface LocationRepository {
    List<Location> findAll();
    Location save(Location location);
    void remove(Location location);
    Optional<Location> findById(int id);
}
