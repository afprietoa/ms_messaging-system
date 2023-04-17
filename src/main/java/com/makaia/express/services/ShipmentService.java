package com.makaia.express.services;

import com.makaia.express.modules.Customer;
import com.makaia.express.modules.Shipment;
import com.makaia.express.repositories.CustomerRepository;
import com.makaia.express.repositories.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;
    @Autowired
    private CustomerRepository customerRepository;

    /**
     *
     * @return
     */
    public List<Shipment> index(){
        List<Shipment> resultList = (List<Shipment>) this.shipmentRepository.findAll();
        if (resultList.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "There is not any shipment in the list.");
        return  resultList;

    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Shipment> show(int id){
        Optional<Shipment> result = this.shipmentRepository.findById(id);
        if(result.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "The requested shipment.id does not exist.");
        return  result;
    }

    /**
     *
     * @param newShipment
     * @return
     */
    public Shipment create(Shipment newShipment, int idCustomer){
        UUID uuid = UUID.randomUUID();
        newShipment.setGuideNumber(
                uuid.toString().toUpperCase().substring(uuid.toString().lastIndexOf("-") + 1,uuid.toString().length() - 2)
        );
        int code = Math.abs(newShipment.getGuideNumber().hashCode());
        Optional<Customer> customer = this.customerRepository.findById(idCustomer);

        newShipment.setCustomer(customer.get());

        if(newShipment.getGuideNumber() != null){
            Optional<Shipment> tempShipment = this.shipmentRepository.findById(code);
            if(tempShipment.isPresent())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "ID is yet in the database.");
        }

        if((newShipment.getOriginCity() != null) && (newShipment.getDestinyCity() != null) &&
                (newShipment.getDestinyAddress() != null) && (newShipment.getRecipientName() != null)){
            return this.shipmentRepository.save(newShipment);
        }else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Mandatory fields had not been provided.");
    }

    /**
     *
     * @param id
     * @param shipment
     * @return
     */
    public  Shipment update(String id, Shipment shipment){
        int code = Math.abs(id.hashCode());
        if(code > 0){
            Optional<Shipment> tempShipment = this.shipmentRepository.findById(code);
            if(tempShipment.isPresent()){
                if(shipment.getCustomer() != null)
                    tempShipment.get().setCustomer(shipment.getCustomer());
                if (shipment.getOriginCity() != null)
                    tempShipment.get().setOriginCity(shipment.getOriginCity());
                if(shipment.getDestinyCity() != null)
                    tempShipment.get().setDestinyCity(shipment.getDestinyCity());
                if(shipment.getDestinyAddress() != null)
                    tempShipment.get().setDestinyAddress(shipment.getDestinyAddress());
                if(shipment.getRecipientName() != null)
                    tempShipment.get().setRecipientName(shipment.getRecipientName());
                if(shipment.getRecipientContact() != null)
                    tempShipment.get().setRecipientContact(shipment.getRecipientContact());
                if(shipment.getDeliveryHour() != null)
                    tempShipment.get().setDeliveryHour(shipment.getDeliveryHour());
                if(shipment.getShipmentState() != null)
                    tempShipment.get().setShipmentState(shipment.getShipmentState());
                if(shipment.getShippingCosts() != null)
                    tempShipment.get().setShippingCosts(shipment.getShippingCosts());
                if(shipment.getPackets() != null)
                    tempShipment.get().setPackets(shipment.getPackets());
                return this.shipmentRepository.save(tempShipment.get());
            }
            else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Shipment.id does not exist in database.");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Shipment.id cannot be negative.");
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Boolean delete(String id) {
        int code = Math.abs(id.hashCode());
        Boolean success = this.show(code).map( user -> {
            this.shipmentRepository.delete(user);
            return true;
        }).orElse(false);
        if(success)
            return true;
        else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Shipment cannot be deleted.");
    }

}
