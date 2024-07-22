package com.example.evidencepojisteni.models.dto.mappers;

import com.example.evidencepojisteni.entities.User;
import com.example.evidencepojisteni.models.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    /**
     * Converting data to DTO/entity
     */
    User toEntity(UserDTO userDTO);
    UserDTO toDTO(User user);
    void updateUsersEntity(UserDTO source, @MappingTarget User user);
}
