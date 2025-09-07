package com.atm.app.restATM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class ApiControllers {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}/balance")
    public ResponseEntity<String> getBalance(@PathVariable Long id) throws Exception{
            
        double balance = userService.getBalance(id);
        return ResponseEntity.ok("Your balance is: " + balance + "$");
        
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<String> deposit(@PathVariable Long id, @RequestBody UserDTO userDTO) throws Exception{
            
        User user = userService.deposit(id, userDTO.getBalance());
        return ResponseEntity.ok("You have deposited " + userDTO.getBalance() + "$. New balance: " + user.getBalance() + "$");
        
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable Long id, @RequestBody UserDTO userDTO) throws Exception{
        
        User user = userService.withdraw(id, userDTO.getBalance());
        return ResponseEntity.ok("Withdrawal successful. Remaining balance: " + user.getBalance() + "$");
    }
}

