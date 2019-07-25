package com.cogitech.cyberax.service.metier;

import com.cogitech.cyberax.domain.Gagnant;
import com.cogitech.cyberax.domain.Mise;

public interface APIs {
    Boolean miseValide(Mise mise);
    Boolean payement(Gagnant gagnant);
}
