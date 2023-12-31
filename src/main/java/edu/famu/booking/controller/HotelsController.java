package edu.famu.booking.controller;
import edu.famu.booking.model.Hotels;
import edu.famu.booking.service.HotelsService;
import edu.famu.booking.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("hotel")
//hello
public class HotelsController {
    private HotelsService hotelsService;

    public HotelsController(HotelsService hotelsService) {
        this.hotelsService = hotelsService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllHotels() {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", hotelsService.getAllHotels(), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        }
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<ApiResponse> getHotelsById(@PathVariable String hotelId) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", hotelsService.getHotelsById(hotelId), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createNewHotel(@RequestBody Hotels hotel) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", hotelsService.createHotel(hotel), null));
        } catch (ExecutionException e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        } catch (InterruptedException e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        }
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<ApiResponse> updateHotels(@PathVariable (name="userId") String id, @RequestBody Map<String,Object> data){
        try{
            hotelsService.updateHotel(id, data);
            return ResponseEntity.ok(new ApiResponse(true, "Hotel successfully updated",null,null));
        } catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        }
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<ApiResponse> deleteHotels(@PathVariable String hotelId) {
        try {
            hotelsService.deleteHotels(hotelId);
            return ResponseEntity.ok(new ApiResponse(true, "Hotel successfully deleted", null, null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        }
    }


}