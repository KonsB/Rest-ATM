package com.atm.app.restATM;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiControllers {

    @Autowired
    private UserRepo userRepo;

    @PostMapping(value = "/save")
    public String saveUser(@RequestBody User user){
        userRepo.save(user);
        return "Saved...";
    }

    @GetMapping(value = "/users")
    public List<User> getUsers(){
        return userRepo.findAll();
    }

    @GetMapping(value = "show_balance/{id}")
    public String showBalance(@PathVariable long id){

        double balance = userRepo.findById(id).get().getBalance();
        return "Your balance is: " + balance;
    }

    @PutMapping(value = "deposit/{id}")
    public String deposit(@PathVariable long id, @RequestBody User user){
        User updatedUser = userRepo.findById(id).get();
        updatedUser.setBalance(updatedUser.getBalance() + user.getBalance());
        userRepo.save(updatedUser);

        return "You have deposited " + user.getBalance()+"$";
    }

    @PutMapping(value = "withdraw/{id}")
    public String withdraw(@PathVariable long id, @RequestBody User user){
        User updatedUser = userRepo.findById(id).get();
        double dif = updatedUser.getBalance() - user.getBalance();
        if(dif<0){
            return "Sorry your balance is not enough to withdraw that much money";
        }
        else{
            updatedUser.setBalance(dif);
            userRepo.save(updatedUser);

            return "Take your money";
        }
    }
}
