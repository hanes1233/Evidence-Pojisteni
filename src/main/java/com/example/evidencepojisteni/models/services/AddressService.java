package com.example.evidencepojisteni.models.services;

import com.example.evidencepojisteni.entities.Address;
import com.example.evidencepojisteni.models.dto.AddressDTO;
import com.example.evidencepojisteni.models.dto.mappers.AddressMapper;
import com.example.evidencepojisteni.repositories.AddressRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@NoArgsConstructor
@Service
public class AddressService implements com.example.evidencepojisteni.models.services.Service<Address> {

    // Dependency injections section
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressMapper addressMapper;
    // end of section


    public void save(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

    // Return address entity or throw exception
    @Override
    public Address getEntityOrThrow(Long id) {
        return addressRepository
                .findByUsersId(id)
                .orElseThrow();
    }

    /**
     * Update user's address
     * @param id = user's id to find address in database
     * @param addressDTO = new address data to update existing one
     * @return new address data
     */
    public AddressDTO updateAddress(Long id, AddressDTO addressDTO) {
        Address address = getEntityOrThrow(id);
        addressMapper.updateAddressEntity(addressDTO,address);
        addressRepository.saveAndFlush(address);
        return addressDTO;
    }

}
