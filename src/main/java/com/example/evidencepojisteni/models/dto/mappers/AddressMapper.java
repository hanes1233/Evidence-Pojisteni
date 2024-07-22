package com.example.evidencepojisteni.models.dto.mappers;

import com.example.evidencepojisteni.entities.Address;
import com.example.evidencepojisteni.models.dto.AddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    /**
     * Converting data to DTO/entity
     */
    Address toEntity(AddressDTO addressDTO);
    AddressDTO toDTO(Address address);
    void updateAddressEntity (AddressDTO source, @MappingTarget Address target);
}
