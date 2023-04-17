package com.makaia.express.repositories;

import com.makaia.express.modules.Packet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacketRepository extends CrudRepository<Packet, Integer> {
}
