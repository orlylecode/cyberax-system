package com.cogitech.cyberax.service.impl;

import com.cogitech.cyberax.service.ConfigService;
import com.cogitech.cyberax.domain.Config;
import com.cogitech.cyberax.repository.ConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Config}.
 */
@Service
@Transactional
public class ConfigServiceImpl implements ConfigService {

    private final Logger log = LoggerFactory.getLogger(ConfigServiceImpl.class);

    private final ConfigRepository configRepository;

    public ConfigServiceImpl(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    /**
     * Save a config.
     *
     * @param config the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Config save(Config config) {
        log.debug("Request to save Config : {}", config);
        return configRepository.save(config);
    }

    /**
     * Get all the configs.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Config> findAll() {
        log.debug("Request to get all Configs");
        return configRepository.findAll();
    }


    /**
     * Get one config by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Config> findOne(Long id) {
        log.debug("Request to get Config : {}", id);
        return configRepository.findById(id);
    }

    /**
     * Delete the config by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Config : {}", id);
        configRepository.deleteById(id);
    }
}
