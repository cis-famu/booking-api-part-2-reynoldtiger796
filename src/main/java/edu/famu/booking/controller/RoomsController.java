package edu.famu.booking.controller;
import edu.famu.booking.model.Rooms;
import edu.famu.booking.service.RoomsService;
import edu.famu.booking.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("room")
public class RoomsController {
    private RoomsService roomsService;

    public RoomsController(RoomsService roomsService) {
        this.roomsService = roomsService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllRooms() {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", roomsService.getAllRooms(), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        }
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<ApiResponse> getRoomsById(@PathVariable String roomId) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", roomsService.getRoomsById(roomId), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createNewRoom(@RequestBody Rooms rooms) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", roomsService.createRooms(rooms), null));
        } catch (ExecutionException e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        } catch (InterruptedException e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        }
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<ApiResponse> updateRooms(@PathVariable (name="roomId") String id, @RequestBody Map<String,Object> data){
        try{
            roomsService.updateRooms(id, data);
            return ResponseEntity.ok(new ApiResponse(true, "Room successfully updated",null,null));
        } catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        }
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<ApiResponse> deleteRoom(@PathVariable String roomId) {
        try {
            roomsService.deleteRooms(roomId);
            return ResponseEntity.ok(new ApiResponse(true, "Room successfully deleted", null, null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        }
    }
}
