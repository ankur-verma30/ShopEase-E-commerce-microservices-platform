package com.shopease.userservice.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    @GetMapping
    public ResponseEntity<Map<String, Object>> health() {
        log.info("GET /health");
        log.info("Health Check is requested");
        return ResponseEntity.ok(
                Map.of(
                        "service", "user-service",
                        "status", "UP-RUNNING",
                        "timestamp", LocalDateTime.now().toString(),
                        "port", 8081
                )
        );
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<List<Map<String, Object>>> getAllUsers() {
        log.info("GET /get-all-users");

        List<Map<String, Object>> users = List.of(
                Map.of("id", 1, "name", "Ankur Verma", "email", "ankur@shopease.com", "role", "CUSTOMER"
                ),
                Map.of("id", 2, "name", "Isha Kumari", "email", "isha@shopease.com", "role", "ADMIN"),
                Map.of("id", 3, "name", "Deepak Yadav", "email", "deepak@shopease.com", "role", "CUSTOMER"),
                Map.of("id", 4, "name", "Harsh", "email", "harsh@shopease.com", "role", "CUSTOMER")
        );
        return ResponseEntity.ok(users);
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<Map<String,Object>> getUserById(@PathVariable Long id){
        log.info("GET /get-user/{}", id);
        log.info("Getting user with id: {}", id);

        if(id>100){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "error","User does not exist",
                            "id",id,
                            "message","No user exist with this ID"
                    )
            );
        }
        return ResponseEntity.ok(Map.of("id", id, "name", "Ankur Verma", "email", "ankur@shopease.com", "role", "CUSTOMER"));
    }


    @PostMapping("/create-user")
    public ResponseEntity<Map<String,Object>>createUser(@RequestBody Map<String,Object> userRequest){
        log.info("POST /create-user");
        log.info("Creating user with data: {} ",userRequest);

        if(!userRequest.containsKey("name") || !userRequest.containsKey("email")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "error","Fields 'name' and 'email' are required to create user"
            ));

        }

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message","User created successfully",
                "id",999,
                "name",userRequest.get("name"),
                "email",userRequest.get("email"),
                "role","CUSTOMER",
                "createdAt",LocalDateTime.now().toString()
        ));
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<Map<String,Object>> updateUser(@PathVariable Long id,
                                                         @RequestBody Map<String,Object> updateRequest){
        log.info("PUT /update-user/{}", id);
        log.info("Updating user with id: {} and data: {}", id, updateRequest);

        return ResponseEntity.ok(Map.of("id", id, "name", "Ankur Verma", "email", "ankur@shopease.com", "role", "CUSTOMER"));
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<Map<String,Object>> deleteUser(@PathVariable Long id){
        log.info("DELETE /delete-user/{}", id);
        log.info("Deleting user with id: {}", id);

        return ResponseEntity.noContent().build();
    }
}
