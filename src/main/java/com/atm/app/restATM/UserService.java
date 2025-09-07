package com.atm.app.restATM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User deposit(Long id, double amount) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setBalance(user.getBalance() + amount);
        return userRepo.save(user);
    }

    public User withdraw(Long id, double amount) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (user.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        
        user.setBalance(user.getBalance() - amount);
        return userRepo.save(user);
    }

    public double getBalance(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getBalance();
    }
}
