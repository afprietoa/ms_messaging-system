package com.makaia.express.controllers;

import com.makaia.express.dto.ShipmentDTO;
import com.makaia.express.modules.Packet;
import com.makaia.express.modules.Request.TrackingRequest;
import com.makaia.express.modules.Shipment;
import com.makaia.express.services.ShipmentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message ="Everything is Ok"),
            @ApiResponse(code = 404, message ="That's an error in the client service"),
            @ApiResponse(code = 500, message ="That's an internal error"),
    })
    @ApiOperation(value="List's shipments", notes= "this searches all shipments", response = Shipment.class)
    @GetMapping("/shipment/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Shipment> getAllShipment(){
        return this.shipmentService.index();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message ="Everything is Ok"),
            @ApiResponse(code = 404, message ="That's an error in the client service"),
            @ApiResponse(code = 500, message ="That's an internal error"),
    })
    @ApiOperation(value="shipment object", notes= "this searches a shipment by id", response = Shipment.class)
    @GetMapping("/shipment/by_id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Shipment> getShipmentById(@ApiParam(value = "shipment id", required = true) @PathVariable("id") int id){
        return this.shipmentService.show(id);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message ="Everything is Ok"),
            @ApiResponse(code = 404, message ="That's an error in the client service"),
            @ApiResponse(code = 500, message ="That's an internal error"),
    })
    @ApiOperation(value="shipment list", notes= "this searches a list of shipment by tracking object", response = Shipment.class)
    @GetMapping("/shipment/by_tracking")
    public List<ShipmentDTO> getShipmentsByState(@RequestBody TrackingRequest trackingRequest){
        return this.shipmentService.indexByState(trackingRequest);
    }

    @ApiResponses(value={
            @ApiResponse( code = 201, message = "created shipment success")
    })
    @ApiOperation(value="shipment", notes= "this create a shipment", response = Shipment.class)
    @PostMapping("/shipment/customer/{idCustomer}")
    @ResponseStatus(HttpStatus.CREATED)
    public Shipment insertShipment(@ApiParam(value = "shipment object", required = true) @RequestBody Shipment shipment, @ApiParam(value = "customer id", required = true) @PathVariable("idCustomer") int idCustomer){
        return this.shipmentService.create(shipment, idCustomer);
    }

    @PutMapping("/shipment/{id}/employee/{idEmployee}")
    @ResponseStatus(HttpStatus.CREATED)
    public Shipment updateShipment(@ApiParam(value = "shipment id", required = true) @PathVariable("id") String id, @ApiParam(value = "shipment object", required = true) @RequestBody Shipment shipment, @ApiParam(value = "employee id", required = true) @PathVariable("idEmployee") int idEmployee){
        return this.shipmentService.update(id, shipment, idEmployee);
    }

    @DeleteMapping("/shipment/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteShipment(@ApiParam(value = "shipment id", required = true) @PathVariable("id") String id){
        return this.shipmentService.delete(id);
    }
}
