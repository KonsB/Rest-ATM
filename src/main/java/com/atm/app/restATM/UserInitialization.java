package com.atm.app.restATM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserInitialization implements CommandLineRunner{

    @Autowired
    private UserRepo userRepo;

    @Override
    public void run(String... args) throws Exception {
        
        if (userRepo.count() == 0) {
            
            User user1 = new User();
            user1.setFirstName("John");
            user1.setLastName("Doe");
            user1.setBalance(1000.0);
            userRepo.save(user1);

            User user2 = new User();
            user2.setFirstName("Jane");
            user2.setLastName("Smith");
            user2.setBalance(2500.0);
            userRepo.save(user2);

            User user3 = new User();
            user3.setFirstName("Robert");
            user3.setLastName("Johnson");
            user3.setBalance(500.0);
            userRepo.save(user3);

            System.out.println("Sample users initialized successfully!");
        } else {
            System.out.println("Users already exist in the database. Skipping initialization.");
        }
    }
}
