package edu.famu.booking.controller;

import edu.famu.booking.model.Hotels;
import edu.famu.booking.model.Users;
import edu.famu.booking.service.UsersService;
import edu.famu.booking.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("user")
public class UsersController {
    private UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllUsers() {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", usersService.getAllUsers(), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getUsersById(@PathVariable String userId) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", usersService.getUsersById(userId), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createNewUser(@RequestBody Users users) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", usersService.createUsers(users), null));
        } catch (ExecutionException e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        } catch (InterruptedException e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse> updateUsers(@PathVariable (name="userId") String id, @RequestBody Map<String,String> data){
        try{
            usersService.updateUsers(id, data);
            return ResponseEntity.ok(new ApiResponse(true, "User successfully updated",null,null));
        } catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUsers(@PathVariable String userId) {
        try {
            usersService.deleteUsers(userId);
            return ResponseEntity.ok(new ApiResponse(true, "User successfully deleted", null, null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        }
    }
}
