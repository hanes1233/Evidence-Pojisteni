package com.example.evidencepojisteni.models.dto.mappers;

import com.example.evidencepojisteni.entities.Insurance;
import com.example.evidencepojisteni.models.dto.InsuranceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InsuranceMapper {
    /**
     * Converting data to DTO/entity
     */
    Insurance toEntity(InsuranceDTO insuranceDTO);
    InsuranceDTO toDTO(Insurance insurance);
    void updateInsuranceDTO(InsuranceDTO source, @MappingTarget InsuranceDTO target);
    void updateInsuranceEntity (InsuranceDTO source, @MappingTarget Insurance target);
}
