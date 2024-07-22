package com.example.evidencepojisteni.controllers;

import com.example.evidencepojisteni.models.dto.InsuranceDTO;
import com.example.evidencepojisteni.models.dto.RegistrationForm;
import com.example.evidencepojisteni.models.dto.mappers.AddressMapper;
import com.example.evidencepojisteni.models.services.AddressService;
import com.example.evidencepojisteni.models.services.InsuranceService;
import com.example.evidencepojisteni.models.services.UserService;
import static com.example.evidencepojisteni.models.services.Passwords.encrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UserController{

    // Dependency injections section
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;
    @Autowired
    private InsuranceService insuranceService;
    @Autowired
    private AddressMapper addressMapper;
    // end of section

    @GetMapping(value = "/profile")
    public String login(String username, String password, Model model, @ModelAttribute InsuranceDTO insuranceDTO) throws Exception {
        if(userService.doesUserExist(username,password)) {
            if(userService.isUserAdmin(username))
                return "redirect:/admin";
            long id = userService.getIdByUsername(username);
            model.addAttribute("user", userService.getEntityOrThrow(id));
            model.addAttribute("address",addressService.getEntityOrThrow(id));
            if(insuranceService.getListById(id).isEmpty()) {
                model.addAttribute("emptyList","Zatím nemáte žadné pojištění.");
            }
            model.addAttribute("insurance",insuranceService.getListById(id));
            model.addAttribute("userId",id);
            return "/user/profile";
        }
        return "/user/index";
    }

    @PostMapping(value = "/registration")
    public String userRegistration (@ModelAttribute RegistrationForm registrationForm,
                                    @ModelAttribute InsuranceDTO insuranceDTO,
                                    Model model) throws Exception {
        // Check if username and password is not exist in database
        if(!userService.doesUserExist(registrationForm.userDTO.getUsername(),registrationForm.userDTO.getPassword())) {
            // Check if email and date is valid
            if(userService.isEmailAndDateValid(registrationForm.userDTO.getEmail(),registrationForm.userDTO.getDateOfBirth())) {
                model.addAttribute("message","Email již obsazen nebo špatně zadan datům narození.");
                return "/errors/error-400";
            }
            // Create new user of registration form (DTO contains UserDTO and AddressDTO).
            userService.create(registrationForm);
            // Login with autofill their data
            return login(registrationForm.userDTO.getUsername(),registrationForm.userDTO.getPassword(),model, insuranceDTO);
        }
        return "/user/index";
    }


    @GetMapping("admin")
    public String loadDatabase(Model model, @ModelAttribute RegistrationForm registrationForm) {
        model.addAttribute("users", userService.getAllUsers());
        return "/admin/admin";
    }

   @GetMapping(value="/admin/delete/{id}")
   public String deleteUser(@PathVariable Long id, Model model,RedirectAttributes redirectAttributes) {
       userService.delete(id);
       redirectAttributes.addFlashAttribute("success","Uživatel smazan.");
       return "redirect:/admin";
   }

    @PostMapping(value = "/adduser")
    public String addUserManually(
                                  @ModelAttribute RegistrationForm registrationForm,
                                  RedirectAttributes redirectAttributes) throws Exception {
       if(!userService.doesUserExist(registrationForm.userDTO.getUsername(),encrypt(registrationForm.userDTO.getPassword()))) {
            userService.create(registrationForm);
            redirectAttributes.addFlashAttribute("success","Uživatel vytvořen.");
            return "redirect:/admin";
        }
        return "/errors/error";
    }


    @GetMapping(value = "admin/edituser/{id}")
    public String getUserInfo(@PathVariable Long id, Model model, @ModelAttribute RegistrationForm registrationForm) {

        // Define DTO's by user's id
        registrationForm.userDTO = userService.getUsersDTO(id);
        registrationForm.addressDTO = addressMapper.toDTO(addressService.getEntityOrThrow(id));

        //Return attributes to work with in template
        model.addAttribute("user",registrationForm.userDTO);
        model.addAttribute("address",registrationForm.addressDTO);
        return "/admin/edituser";
    }


    @PostMapping(value = "/admin/updateuser/{id}")
    public String updateUser(@PathVariable Long id,
                             @ModelAttribute RegistrationForm registrationForm,
                             @RequestParam(value = "is-admin", required = false) String checkBoxValue,
                             RedirectAttributes redirectAttributes) {
        // Check if our checkbox(string) is not null, then user is admin
        boolean isAdmin = (checkBoxValue != null);
        // Update user and address entities
        userService.updateUserAndAddress(id,registrationForm,isAdmin);
        redirectAttributes.addFlashAttribute("success","Údaje jsou upravené.");
        return "redirect:/admin";
    }

}
