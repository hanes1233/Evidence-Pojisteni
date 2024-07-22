package com.example.evidencepojisteni.controllers;

import com.example.evidencepojisteni.models.dto.InsuranceDTO;
import com.example.evidencepojisteni.models.services.AddressService;
import com.example.evidencepojisteni.models.services.InsuranceService;
import com.example.evidencepojisteni.models.services.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class InsuranceController {

    // Dependency injections
    @Autowired
    private InsuranceService insuranceService;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;


    @GetMapping("/insurance/{id}")
    public String getUserInsuranceInfo(@PathVariable Long id, InsuranceDTO insuranceDTO, Model model) {
        // Return list of user's insurances
        insuranceDTO.setUserId(id);
        model.addAttribute("insurance", insuranceService.getListById(id));
        // Return user entity to work with name and lastname in view
        model.addAttribute("user",userService.getEntityOrThrow(id));
        return "/admin/userinfo";
    }

    @PostMapping(value = "/userinfo/requestinsurrance")
    public String addInsurance(@ModelAttribute InsuranceDTO insuranceDTO,
                               RedirectAttributes redirectAttributes,
                               Model model) throws BadRequestException {
        insuranceService.saveInsurance(insuranceDTO);
        redirectAttributes.addAttribute("userId",insuranceDTO.getUserId());
        redirectAttributes.addFlashAttribute("success","Pojištění uloženo.");
        long userId = insuranceDTO.getUserId();

        // Check if user's role = "admin" and return appropriate template to user
        if(userService.getEntityOrThrow(userId).isAdmin())
            return "redirect:/admin/insurance/{userId}";

        // Redirect objects to work within template
        redirectAttributes.addFlashAttribute("user",userService.getEntityOrThrow(userId));
        redirectAttributes.addFlashAttribute("address",addressService.getEntityOrThrow(userId));
        redirectAttributes.addFlashAttribute("insurance",insuranceService.getListById(userId));
        model.addAttribute("userId",userId);

        // return profile template if user is not admin
        return "redirect:/user/profile";
    }

    @PostMapping("/userinfo/delete/insurance/{id}")
    public String deleteInsurance(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        // Getting users id to paste into return line
        Long usersId = insuranceService.getEntityOrThrow(id).getUserId();

        insuranceService.delete(id);
        redirectAttributes.addAttribute("userId",usersId);
        redirectAttributes.addFlashAttribute("success","Pojištění smazano.");
        return "redirect:/admin/insurance/{userId}";
    }

    @GetMapping("/insurance/update/{id}")
    public String getInsuranceDTO(@PathVariable Long id,
                                          InsuranceDTO insuranceDTO) {
        // Get insurance DTO by id to display in template
        insuranceService.getInsuranceDTO(id,insuranceDTO);
        return "/admin/update";
    }

    @PostMapping(value = "/update/{id}")
    public String updateExistingInsurance(@ModelAttribute InsuranceDTO insuranceDTO,
                                          BindingResult bindingResult,
                                          @PathVariable Long id,
                                          RedirectAttributes redirectAttributes) throws BadRequestException {
        insuranceService.updateAndSave(id,insuranceDTO);
        redirectAttributes.addAttribute("userId",insuranceDTO.getUserId());
        redirectAttributes.addFlashAttribute("success","Údaje jsou upravené.");
        return "redirect:/admin/insurance/{userId}";
    }
}
