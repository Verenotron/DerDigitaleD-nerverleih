package de.hsrm.mi.web.derdigitaledoenerverleih.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hsrm.mi.web.derdigitaledoenerverleih.entities.benutzer.Benutzer;
import de.hsrm.mi.web.derdigitaledoenerverleih.entities.benutzer.BenutzerRepository;


@Service
public class BenutzerUserDetailsService implements UserDetailsService{

    @Autowired BenutzerRepository benutzerRepository;
    @Autowired PasswordEncoder passwordEncoder;

    // @Transactional //stellt sicher, dass die Methode auf getMag nur innerhalb einer offenen Session ausgeführt wird
    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //     UserBuilder userbuilder = User.withUsername(username);
    //     UserDetails user1;
    //     Benutzer user = benutzerRepository.findByLoginName(username).orElseThrow(() -> new RuntimeException("User nicht gefunden"));
    //     if(user.getRolle().contains("ADMIN")){ //equals methode quasi bereits in Contains enthalten. Wort muss exakt so enthalten sein,
    //         user1 = userbuilder.username(user.getLoginName()).password(user.getLosung()) .roles("ADMIN").build();
    //     }else{
    //         throw new UsernameNotFoundException("User nicht gefunden: " + username);
    //     }
        
    //     return user1;
    // }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("admin".equals(username)) {
            return User.withUsername("admin")
                    .password(passwordEncoder.encode("admin"))
                    .roles("ADMIN")
                    .build();
        }
        throw new UsernameNotFoundException("Benutzer nicht gefunden: " + username);
    }

//     @Override
// public UserDetails loadUserByUsername(String username) {
//     return userRepository.findByLoginnameIgnoreCase(username)
//         .orElseThrow(() -> new UsernameNotFoundException("User not found"));
// }
    
}
