package com.cogitech.cyberax.service;

import com.cogitech.cyberax.domain.Config;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Config}.
 */
public interface ConfigService {

    /**
     * Save a config.
     *
     * @param config the entity to save.
     * @return the persisted entity.
     */
    Config save(Config config);

    /**
     * Get all the configs.
     *
     * @return the list of entities.
     */
    List<Config> findAll();


    /**
     * Get the "id" config.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Config> findOne(Long id);

    /**
     * Delete the "id" config.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
