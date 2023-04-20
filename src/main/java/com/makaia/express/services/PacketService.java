package com.makaia.express.services;

import com.makaia.express.modules.Customer;
import com.makaia.express.modules.Packet;
import com.makaia.express.modules.Shipment;
import com.makaia.express.modules.common.Size;
import com.makaia.express.repositories.PacketRepository;
import com.makaia.express.repositories.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PacketService {

    private PacketRepository packetRepository;
    private ShipmentRepository shipmentRepository;

    public PacketService(PacketRepository packetRepository, ShipmentRepository shipmentRepository) {
        this.packetRepository = packetRepository;
        this.shipmentRepository = shipmentRepository;
    }

    /**
     *
     * @return
     */
    public List<Packet> index(){
        List<Packet> resultList = (List<Packet>) this.packetRepository.findAll();
        if (resultList.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "There is not any packet in the list.");
        return  resultList;

    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Packet> show(int id){
        Optional<Packet> result = this.packetRepository.findById(id);
        if(result.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "The requested user.id does not exist.");
        return  result;
    }

    /**
     *
     * @param newPacket
     * @return
     */
    public Packet create(Packet newPacket, int idShipment){

        Optional<Shipment> shipment = this.shipmentRepository.findById(idShipment);

        newPacket.setShipment(shipment.get());

        newPacket.setPacketType(
                newPacket.getWeight()<2 ? String.valueOf(Size.SMALL) :
                newPacket.getWeight()<5 ? String.valueOf(Size.MEDIUM)
                : String.valueOf(Size.SMALL));
        newPacket.setDeclaredValue(
                newPacket.getPacketType() == String.valueOf(Size.SMALL) ? 30.0:
                newPacket.getPacketType() == String.valueOf(Size.MEDIUM) ? 40.0
                                : 50.0);
        if(newPacket.getCode() != null){
            Optional<Packet> tempPacket = this.packetRepository.findById(newPacket.getCode());
            if(tempPacket.isPresent())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "ID is yet in the database.");
        }

        if((newPacket.getPacketType() != null) && (newPacket.getWeight() != null) &&
                (newPacket.getDeclaredValue() != null)){
            this.packetRepository.save(newPacket);
            return newPacket;
        }else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Mandatory fields had not been provided.");
    }

    /**
     *
     * @param id
     * @param packet
     * @return
     */
    public  Packet update(int id, Packet packet){
        if(id > 0){
            Optional<Packet> tempPacket = this.packetRepository.findById(id);
            if(tempPacket.isPresent()){
                if(packet.getPacketType() != null)
                    tempPacket.get().setPacketType(packet.getPacketType());
                if (packet.getWeight() != null)
                    tempPacket.get().setWeight(packet.getWeight());
                if(packet.getDeclaredValue() != null)
                    tempPacket.get().setDeclaredValue(packet.getDeclaredValue());
                this.packetRepository.save(tempPacket.get());
                return tempPacket.get();
            }
            else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Packet.id does not exist in database.");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Packet.id cannot be negative.");
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Boolean delete(int id) {
        Boolean success = this.show(id).map( user -> {
            this.packetRepository.delete(user);
            return true;
        }).orElse(false);
        if(success)
            return true;
        else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Packet cannot be deleted.");
    }
}
