package com.example.evidencepojisteni.models.services;


import org.apache.coyote.BadRequestException;

public interface Service <T> {
    /**
     * Find entity in database or throw exception
     * @param id = entity id
     * @return entity
     */
    T getEntityOrThrow(Long id);

    /**
     * Delete entity from database
     * @param id entity id
     */
    void delete(Long id);

    /**
     * Save entity in database
     * @param type = entity type
     * @throws BadRequestException
     */
    void save(T type) throws BadRequestException;
}
