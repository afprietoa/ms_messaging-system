package com.makaia.express.repositories;

import com.makaia.express.modules.Shipment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends CrudRepository<Shipment, Integer> {
}
