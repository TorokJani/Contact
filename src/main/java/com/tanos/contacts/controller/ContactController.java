package com.tanos.contacts.controller;

import com.tanos.contacts.model.Contact;
import com.tanos.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping
    public String listContactsPost(Model model){
        model.addAttribute("contacts",contactService.getContacts());
        return "contact-list";
    }

    @GetMapping
    public String listContacts(Model model){
        model.addAttribute("contacts",contactService.getContacts());
        return "contact-list";
    }

    @GetMapping("/create")
    public String createContactForm(Contact contact){
        return "contact-create";
    }



    @PostMapping("/create")
    public String createContact(@Valid Contact contact, BindingResult bindingResult){

        if ( bindingResult.hasErrors()){
            return "/contact-create";
        }
        contactService.saveContact(contact);

        return "redirect:/contact";
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable long id){
        contactService.deleteContact(id);
        return "redirect:/contact";
    }

    @GetMapping("/edit/{id}")
    public String editContactForm(@PathVariable long id, Model model){
        Contact c= contactService.findById(id);
        model.addAttribute("contact",c);
        return "contact-create";
    }

    @PostMapping("/edit/{id}")
    public String editContact(Contact contact){
        contactService.updateContact(contact);
        return "redirect:/contact";
    }


    @InitBinder
    private void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),true));
    }
}
