package com.makaia.express.controllers;

import com.makaia.express.modules.Packet;
import com.makaia.express.services.PacketService;
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
public class PacketController {
    @Autowired
    private PacketService packetService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message ="Everything is Ok"),
            @ApiResponse(code = 404, message ="That's an error in the client service"),
            @ApiResponse(code = 500, message ="That's an internal error"),
    })
    @ApiOperation(value="List's packets", notes= "this searches all packets", response = Packet.class)
    @GetMapping("/packet/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Packet> getAllPackets(){
        return this.packetService.index();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message ="Everything is Ok"),
            @ApiResponse(code = 404, message ="That's an error in the client service"),
            @ApiResponse(code = 500, message ="That's an internal error"),
    })
    @ApiOperation(value="packet object", notes= "this searches a packet by id", response = Packet.class)
    @GetMapping("/packet/by_id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Packet> getPacketById(@PathVariable("id") int id){
        return this.packetService.show(id);
    }

    @ApiResponses(value={
            @ApiResponse( code = 201, message = "created packet success")
    })
    @ApiOperation(value="packet", notes= "this create a packet", response = Packet.class)
    @PostMapping("/packet/shipment/{idShipment}")
    @ResponseStatus(HttpStatus.CREATED)
    public Packet insertPacket(@RequestBody Packet packet, @ApiParam(value = "shipment id", required = true) @PathVariable("idShipment") int idShipment){
        return this.packetService.create(packet, idShipment);
    }

    @PutMapping("/packet/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Packet updatePacket(@PathVariable("id") int id, @RequestBody Packet packet){
        return this.packetService.update(id, packet);
    }

    @DeleteMapping("/packet/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deletePacket(@PathVariable("id") int id){
        return this.packetService.delete(id);
    }
}
