package com.makaia.express;

import com.makaia.express.modules.Customer;
import com.makaia.express.modules.Packet;
import com.makaia.express.modules.Shipment;
import com.makaia.express.modules.common.Size;
import com.makaia.express.modules.common.State;
import com.makaia.express.repositories.PacketRepository;
import com.makaia.express.repositories.ShipmentRepository;
import com.makaia.express.services.PacketService;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PacketServiceTest {
    PacketRepository packetRepository;
    PacketService packetService;
    ShipmentRepository shipmentRepository;

    @Before
    public void setUp(){
        this.shipmentRepository = mock(ShipmentRepository.class);
        this.packetRepository = mock(PacketRepository.class);
        this.packetService = new PacketService(packetRepository, shipmentRepository);
    }

    @Test
    public void validatePacket(){
        Packet packet = new Packet(1, String.valueOf(Size.MEDIUM), 2.0, 100.0);
        Customer customer = new Customer(1, "Pepito", "Perez", 3208665231l, "peto@example.com", "Calle falsa 123", "Medellin");
        Shipment shipment = new Shipment(1, "Medellin", "Bogota", "calle 46 # 69-90", "Juan Manuel", 3216884514l, 2, String.valueOf(State.DELIVERED), 100.0, customer);

        when(shipmentRepository.findById(1)).thenReturn(Optional.of(shipment));

        Packet pack = packetService.create(packet, shipment.getGuideNumber());



        assertTrue((pack.getCode() instanceof Integer && pack.getPacketType()!=null &&
                    pack.getWeight() instanceof Double && pack.getDeclaredValue() instanceof Double));
    }
}
