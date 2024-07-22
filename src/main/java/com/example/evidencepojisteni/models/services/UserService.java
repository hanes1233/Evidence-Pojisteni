package com.example.evidencepojisteni.models.services;

import com.example.evidencepojisteni.entities.Address;
import com.example.evidencepojisteni.entities.User;
import com.example.evidencepojisteni.models.dto.RegistrationForm;
import com.example.evidencepojisteni.models.dto.UserDTO;
import com.example.evidencepojisteni.models.dto.mappers.AddressMapper;
import com.example.evidencepojisteni.models.dto.mappers.UserMapper;
import com.example.evidencepojisteni.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static com.example.evidencepojisteni.models.services.Passwords.*;

@Service
@NoArgsConstructor
public class UserService implements com.example.evidencepojisteni.models.services.Service<User> {

    // Dependency injections section
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private AddressService addressService;
    // end of section

    /**
     * Find user entity in database by it's username
     * @param username = user's username
     * @return user
     */
    public User getUserOrThrow(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow();
    }

    // Gets list of all users from database
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Gets users id by username
    public Long getIdByUsername(String userName) {
        User user = getUserOrThrow(userName);
        return user.getId();
    }

    // Save new user into database
    @Override
    public void save(User user) {
        if(userRepository.findByUsername(user.getUsername()).isEmpty())
            userRepository.saveAndFlush(user);
    }
    // Create new user and address in databases
    public void create(RegistrationForm registrationForm)
            throws NoSuchPaddingException,
            IllegalBlockSizeException,
            NoSuchAlgorithmException,
            BadPaddingException,
            InvalidKeyException {
        // Create user's entity from DTO
        User user = userMapper.toEntity(registrationForm.userDTO);

        // Encrypt user's password
        user.setPassword(encrypt(registrationForm.userDTO.getPassword()));
        userRepository.saveAndFlush(user);

        // Get user's id to paste into addressDTO
        long id = userRepository
                .findByUsername(user.getUsername())
                .get()
                .getId();
        registrationForm.addressDTO.setUsersId(id);
        // Converting DTO to address and saving to database
        Address address = addressMapper.toEntity(registrationForm.addressDTO);
        // Save address to database
        addressService.save(address);
    }

    /**
     * Check if user exist in database by its decrypted password and username
     * @param username = user's username
     * @param password = user's password
     * @return true if user exists and false if not
     */
    public boolean doesUserExist(String username, String password) throws Exception {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            String decryptedPassword = decrypt(user.getPassword());
            return (username.equals(user.getUsername()) && password.equals(decryptedPassword));
        }
        return false;
    }

    // Checks if user is admin by its username
    public boolean isUserAdmin(String username) {
        User user = getUserOrThrow(username);
        return user.isAdmin();
    }

    // Delete user from database
    @Override
    public void delete(Long id) {
        if(userRepository.existsById(id))
            userRepository.deleteById(id);
    }

    @Override
    public User getEntityOrThrow(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    // Update user's personal data and address

    /**
     * Update user's data and address
     * @param id = user's id
     * @param registrationForm = DTO containing two DTO's (userDTO and addressDTO)
     * @param isAdmin = boolean value to set user's role
     */
    public void updateUserAndAddress(Long id, RegistrationForm registrationForm, boolean isAdmin) {
        updateUser(id,registrationForm.userDTO,isAdmin);
        registrationForm.addressDTO.setUsersId(id);
        addressService.updateAddress(id,registrationForm.addressDTO);
    }

    /**
     * Update user's data and save into database
     * @param id = user's id
     * @param userDTO = new user's data to update existing one
     * @param isAdmin = user's role
     * @return updated user's DTO
     */
    public UserDTO updateUser(Long id, UserDTO userDTO, boolean isAdmin) {
        User user = getEntityOrThrow(id);
        if(isAdmin)
            userDTO.setAdmin(true);
        userDTO.setPassword(user.getPassword());
        userMapper.updateUsersEntity(userDTO,user);
        userRepository.saveAndFlush(user);
        return userDTO;
    }

    // Checks if email already taken and is age < 90
    public boolean isEmailAndDateValid(String email, Date date) {
        return userRepository
                .findByEmail(email)
                .isPresent() && (ChronoUnit.YEARS.between(LocalDate.now(),date.toLocalDate()) <= 90 && ChronoUnit.YEARS.between(LocalDate.now(),date.toLocalDate()) >= 18);
    }

    public UserDTO getUsersDTO(Long id) {
        User user = getEntityOrThrow(id);
        return userMapper.toDTO(user);
    }
}
