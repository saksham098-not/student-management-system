package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    // CREATE (Postman)
    @PostMapping
    public Users addUser(@RequestBody Users user) {
        user.setActive(true);
        return usersRepository.save(user);
    }

    // CREATE (HTML Form)
    @PostMapping("/form")
    public String addUserForm(@RequestParam String username,
                             @RequestParam String password) {

        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);
        user.setActive(true);

        usersRepository.save(user);

        return "redirect:/students";
    }

    // READ
    @GetMapping
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    // DELETE (UI)
    @GetMapping("/delete/{id}")
    public String deleteUserFromUI(@PathVariable Long id) {
        usersRepository.deleteById(id);
        return "redirect:/students";
    }

    // DELETE (API)
    @DeleteMapping("/{id}")
    public String deleteUserAPI(@PathVariable Long id) {
        usersRepository.deleteById(id);
        return "User deleted";
    }

    // UPDATE ✅
    @PostMapping("/update")
    public String updateUser(@RequestParam Long id,
                             @RequestParam String username,
                             @RequestParam String password) {

        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(username);
        user.setPassword(password);

        usersRepository.save(user);

        return "redirect:/students";
    }
}