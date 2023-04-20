package com.makaia.express.services;

import com.makaia.express.dto.ShipmentDTO;
import com.makaia.express.exceptions.RequestException;
import com.makaia.express.modules.Customer;
import com.makaia.express.modules.Employee;
import com.makaia.express.modules.Packet;
import com.makaia.express.modules.Request.TrackingRequest;
import com.makaia.express.modules.Shipment;
import com.makaia.express.modules.common.Role;
import com.makaia.express.modules.common.Size;
import com.makaia.express.repositories.CustomerRepository;
import com.makaia.express.repositories.EmployeeRepository;
import com.makaia.express.repositories.PacketRepository;
import com.makaia.express.repositories.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShipmentService {

    private ShipmentRepository shipmentRepository;

    private CustomerRepository customerRepository;

    private PacketRepository packetRepository;

    private EmployeeRepository employeeRepository;

    public ShipmentService(ShipmentRepository shipmentRepository,
                           CustomerRepository customerRepository,
                           PacketRepository packetRepository,
                           EmployeeRepository employeeRepository) {
        this.shipmentRepository = shipmentRepository;
        this.customerRepository = customerRepository;
        this.packetRepository = packetRepository;
        this.employeeRepository = employeeRepository;
    }

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

        Optional<Customer> customer = this.customerRepository.findById(idCustomer);

        newShipment.setCustomer(customer.get());

        List<Customer> customerList = (List<Customer>) this.customerRepository.findAll();

        if(newShipment.getGuideNumber() != null){
            Optional<Shipment> tempShipment = this.shipmentRepository.findById(newShipment.getGuideNumber());
            if(tempShipment.isPresent())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "ID is yet in the database.");
        }

        if((newShipment.getOriginCity() != null) && (newShipment.getDestinyCity() != null) &&
                (customerList.contains(customer.get())) &&
                (newShipment.getDestinyAddress() != null) && (newShipment.getRecipientName() != null)){
            this.shipmentRepository.save(newShipment);
            return newShipment;
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
    public  Shipment update(String id, Shipment shipment, int idEmployee){
        Optional<Employee> employee = this.employeeRepository.findById(idEmployee);

        if(employee.isPresent() ||
                employee.get().getEmployeeType() != String.valueOf(Role.DELIVERER) ||
                employee.get().getEmployeeType() != String.valueOf(Role.COORDINATOR)){
            throw new RequestException("Permissions denied!");
        }

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
                this.shipmentRepository.save(tempShipment.get());
                return tempShipment.get();
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

    public List<ShipmentDTO> indexByState(TrackingRequest trackingRequest) {

        Integer idCard = trackingRequest.getIdCardNumber();

       Optional<Customer> customer = customerRepository.findById(idCard);

        return index()
                .stream()
                .filter(shipment -> shipment.getShipmentState().equals(trackingRequest.getShipmentState()) )
                .map(shipment -> new ShipmentDTO(
                        shipment.getCustomer().getIdCardNumber(),
                        shipment.getOriginCity(),
                        shipment.getDestinyCity(),
                        shipment.getDestinyAddress(),
                        shipment.getRecipientName(),
                        shipment.getRecipientContact(),
                        shipment.getPackets().stream().mapToDouble(Packet::getDeclaredValue)
                                .sum(),
                        shipment.getPackets().stream().mapToDouble(Packet::getWeight)
                                .sum(),
                        shipment.getShippingCosts(),
                        shipment.getShipmentState(),
                        shipment.getGuideNumber()

                ))
                .collect(Collectors.toList());
    }


}
