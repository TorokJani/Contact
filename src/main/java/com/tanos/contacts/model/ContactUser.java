package com.tanos.contacts.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
public class ContactUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;

    private String password;

    @Email
    private String email;

    private boolean enabled;

    //@Transient
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id")
    )
    private Collection<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      //  return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        Collection<Role> roles = this.getRoles();

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role role: roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}