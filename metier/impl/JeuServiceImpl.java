package com.cogitech.cyberax.service.metier.impl;

import com.cogitech.cyberax.domain.*;
import com.cogitech.cyberax.repository.*;
import com.cogitech.cyberax.service.metier.Config;
import com.cogitech.cyberax.service.metier.JeuMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class JeuServiceImpl implements JeuMetier {
    @Autowired
    private  JeuRepository jeuRepository ;

    @Autowired
    private JoueurRepository joueurRepository ;

    @Autowired
    private MiseRepository miseRepository ;

    @Autowired
    private TerminalRepository terminalRepository;

    @Autowired
    private ListAttenteRepository listAttenteRepository ;

    @Autowired
    private GagnantRepository gagnantRepository ;

    private  Config config ;

    @Override
    public Terminal play(Joueur joueur) {
        Set<Mise> mises = miseRepository.findAllByJoueurAndEtatIsTrue(joueur);
        Jeu jeuEncour = jeuRepository.findFirstByEncourIsTrue();

        // si le joueur a des mises valide , il essaye de jouer
        if (! mises.isEmpty()){
            //s'il ya un jeu en cour

            if (! jeuEncour.equals(null)){
                // s'il ya deja des gagnants
                Set<Gagnant> gagnants =jeuEncour.getGagnants();

                if (gagnants != null ){
                    if (gagnants.size() != 100 ){
                        gagnants.add(joueurToGagnant(joueur , jeuEncour , gagnants.size()+1 ) );
                        jeuRepository.save(jeuEncour) ;
                    }else {

                    }
                }
                //s'il ya pas de gagnants
                else {
                    gagnants.add(joueurToGagnant(joueur , jeuEncour , 1 ) );
                    jeuRepository.save(jeuEncour) ;
                }
            }
            //s'il ya pas de jeu en cour
            else {
                Jeu jeu = new Jeu();
                jeu.setEncour(true);
                jeuRepository.save(jeu);
                play(joueur);
            }
        }

        return null;
    }

    @Override
    public Terminal miser(Joueur joueur) {
        return null;
    }

    @Override
    public Terminal actuTerminal() {
        return null;
    }

    public Gagnant joueurToGagnant(Joueur  joueur , Jeu jeu , Integer position){
        Gagnant gagnant = new Gagnant();
        gagnant.setJeu(jeu);
        //gagnant.setDateGain(new Instant() );
       // gagnant.set
        gagnant.setPosition(position);

        gagnant.setNom(joueur.getNom());
        gagnant.setPrenom(joueur.getPrenom());
        gagnant.setTelephone(joueur.getTelephone());
        return gagnant ;
    }
}
