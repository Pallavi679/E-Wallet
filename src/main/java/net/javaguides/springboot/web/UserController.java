package net.javaguides.springboot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.javaguides.springboot.config.SecurityConfiguration;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserServiceImpl;

@Controller
public class UserController {

    @Autowired
    private UserRepository userService; // Inject your UserService
    
    @Autowired
    private UserServiceImpl userImp; // Inject your UserService
    @Autowired
    private SecurityConfiguration authenticationFacade; // Inject your AuthenticationFacade

    @PostMapping("/add-money")
    @ResponseBody
    public User addMoney(@RequestParam("amount") int amount) {
        // Get authenticated user
      Authentication authentication = authenticationFacade.getAuthentication();
//      authentication = userImp.updateUserBalance(authentication, amount);
      String username = authentication.getName();
      User user = userService.findByEmail(username);
//        User authenticatedUser = userService.getAuthenticatedUser();

        // Update user's balance (assuming you have a method like updateUserBalance)
        user = userImp.updateUserBalance(user, amount);

        return user;
    }
//   / public User addMoney(@RequestParam("amount") int amount, Model model) {
//        // Retrieve the authenticated user
//        Authentication authentication = authenticationFacade.getAuthentication();
//        authentication = userService.updateUserBalance(authentication, amount);
//        String username = authentication.getName();
//        User user = userService.findByEmail(username);
//        int previousBalance =  user.getBalance();
//        // Update the balance
//        user.setBalance(previousBalance+ amount);
//
//        // Save the updated user to the database
//        userService.save(user);
//        int newBalance = user.getBalance();
//        // Redirect to the home page or any other page
////        return "redirect:/?balance=" + user.getBalance();
//        return authentication;
//    }
}


