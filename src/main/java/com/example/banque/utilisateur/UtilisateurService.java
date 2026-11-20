package com.example.banque.utilisateur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UtilisateurService implements UserDetailsService {
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(final String login)
        throws UsernameNotFoundException{
        Optional<Utilisateur> res = utilisateurRepository.findByLogin(login);
        if(res.isPresent()){
            return new User(res.get().getLogin(), res.get().getMotDePasse(), new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("L'utilisateur n'existe pas");
        }
    }
}
