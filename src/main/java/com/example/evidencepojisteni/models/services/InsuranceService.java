package com.example.evidencepojisteni.models.services;

import com.example.evidencepojisteni.entities.Insurance;
import com.example.evidencepojisteni.models.dto.InsuranceDTO;
import com.example.evidencepojisteni.models.dto.mappers.InsuranceMapper;
import com.example.evidencepojisteni.repositories.InsuranceRepository;
import lombok.NoArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
@Service
public class InsuranceService implements com.example.evidencepojisteni.models.services.Service<Insurance> {

    // Dependency injections section
    @Autowired
    private InsuranceRepository insuranceRepository;
    @Autowired
    private InsuranceMapper insuranceMapper;
    // end of section


    /**
     * Get list of user's insurances by user's id
     * @param id = user's id
     * @return list of insurances DTO
     */
    public List<InsuranceDTO> getListById(Long id) {
        List<Insurance> insuranceList = insuranceRepository.findAllByUserId(id);
        return insuranceList
                .stream()
                .map(insurance -> insuranceMapper.toDTO(insurance))
                .collect(Collectors.toList());
    }

    /**
     * Check if entered insurance date is valid
     * @param start = date of start insurance(issued)
     * @param end = date of end insurance(expiration)
     * @return true if valid and false if not
     */
    public boolean isDateCorrect(Date start, Date end) {
        return ChronoUnit.DAYS.between(start.toLocalDate(), end.toLocalDate()) > 0;
    }

    /**
     * Save new insurance into database
     * @param insuranceDTO = insurance entered data
     * @throws BadRequestException = if insurance's date is not valid, throw exception
     */
    public void saveInsurance(InsuranceDTO insuranceDTO) throws BadRequestException {
        // Check if date is correct and throws exception if not
        if(!isDateCorrect(insuranceDTO.getDateOfStart(),insuranceDTO.getDateOfEnd()))
            throw new BadRequestException();
        Insurance insurance = insuranceMapper.toEntity(insuranceDTO);
        save(insurance);
    }

    /**
     * Find and delete insurance
     * @param id = insurance id to find in database
     */
    @Override
    public void delete(Long id) {
        Optional<Insurance> optionalInsurance = insuranceRepository.findById(id);
        if(optionalInsurance.isPresent())
            insuranceRepository.deleteById(id);
    }

    @Override
    public void save(Insurance type) {
        insuranceRepository.saveAndFlush(type);
    }

    // Return insurance entity or throw exception
    @Override
    public Insurance getEntityOrThrow(Long id) {
        return insuranceRepository
                .findById(id)
                .orElseThrow()
                ;}


    /**
     * Update existing insurance
     * @param id = insurance id
     * @param insuranceDTO = new insurance data to update existing one
     * @throws BadRequestException = if date is not valid throw exception
     */
    public void updateAndSave(Long id, InsuranceDTO insuranceDTO) throws BadRequestException {
        if(!isDateCorrect(insuranceDTO.getDateOfStart(),insuranceDTO.getDateOfEnd()))
            throw new BadRequestException();
        Insurance insurance = getEntityOrThrow(id);
        insuranceMapper.updateInsuranceEntity(insuranceDTO,insurance);
        insuranceRepository.save(insurance);
    }


    /**
     * Find insurance in database and return as DTO
     * @param id = insurance id
     * @param insuranceDTO = insurance data to update
     * @return updated insurance DTO
     */
    public InsuranceDTO getInsuranceDTO(Long id, InsuranceDTO insuranceDTO) {
        Insurance insurance = getEntityOrThrow(id);
        InsuranceDTO source = insuranceMapper.toDTO(insurance);
        insuranceMapper.updateInsuranceDTO(source,insuranceDTO);
        return insuranceDTO;
    }

}
