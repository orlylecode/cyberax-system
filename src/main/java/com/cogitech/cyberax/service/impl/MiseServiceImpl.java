package com.cogitech.cyberax.service.impl;

import com.cogitech.cyberax.service.MiseService;
import com.cogitech.cyberax.domain.Mise;
import com.cogitech.cyberax.repository.MiseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Mise}.
 */
@Service
@Transactional
public class MiseServiceImpl implements MiseService {

    private final Logger log = LoggerFactory.getLogger(MiseServiceImpl.class);

    private final MiseRepository miseRepository;

    public MiseServiceImpl(MiseRepository miseRepository) {
        this.miseRepository = miseRepository;
    }

    /**
     * Save a mise.
     *
     * @param mise the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Mise save(Mise mise) {
        log.debug("Request to save Mise : {}", mise);
        return miseRepository.save(mise);
    }

    /**
     * Get all the mises.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Mise> findAll(Pageable pageable) {
        log.debug("Request to get all Mises");
        return miseRepository.findAll(pageable);
    }


    /**
     * Get one mise by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Mise> findOne(Long id) {
        log.debug("Request to get Mise : {}", id);
        return miseRepository.findById(id);
    }

    /**
     * Delete the mise by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Mise : {}", id);
        miseRepository.deleteById(id);
    }
}
