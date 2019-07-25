package com.cogitech.cyberax.service.metier;

import com.cogitech.cyberax.domain.Joueur;
import com.cogitech.cyberax.domain.Terminal;

public interface JeuMetier {
    Terminal play(Joueur joueur);
    Terminal miser(Joueur joueur);
    Terminal actuTerminal();
}
