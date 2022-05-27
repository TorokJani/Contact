package com.tanos.contacts.service;

import com.tanos.contacts.model.ContactUser;
import com.tanos.contacts.repository.ContactUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Transactional
public class ContactUserDetailsService implements UserDetailsService {

    private ContactUserRepository contactUserRepository;

    @Autowired
    public ContactUserDetailsService(ContactUserRepository contactUserRepository) {
        this.contactUserRepository = contactUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {


        ContactUser user = contactUserRepository.findByUsername(usernameOrEmail);

        if(user==null){
            user = contactUserRepository.findByEmail(usernameOrEmail);

            if(user==null){
                throw new UsernameNotFoundException("Could not find usr with username (or email): " + usernameOrEmail);
            }
        }
        return user;
    }
}
