package com.infy.taskmanager.service.impl;
import com.infy.taskmanager.dto.UserListDto;
import com.infy.taskmanager.dto.UsersDto;
import com.infy.taskmanager.entity.SecurityUser;
import com.infy.taskmanager.entity.Users;
import com.infy.taskmanager.exception.CustomerAlreadyExistsException;
import com.infy.taskmanager.exception.ResourceNotFoundException;
import com.infy.taskmanager.mapper.UsersListMapper;
import com.infy.taskmanager.mapper.UsersMapper;
import com.infy.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    public UsersDto getUserDetails(String userID) {
        boolean isUpdated = false;
        Users user = userRepository.findById(Integer.parseInt(userID)).orElseThrow(() -> new ResourceNotFoundException("User", "UserID", userID));
        UsersDto usersDto = UsersMapper.mappedToUserDTO(new UsersDto(), user);
        return usersDto;
    }
    public void createUser(Users user) {
        Optional<Users> optionalUsers = userRepository.findByMobileNumberOrEmailId(user.getMobileNumber(), user.getEmailId());
        if (optionalUsers.isPresent() == true && optionalUsers.get().getMobileNumber().equalsIgnoreCase(user.getMobileNumber())) {
            throw new CustomerAlreadyExistsException("User already registered with given mobileNumber : " + user.getMobileNumber());
        } else if (optionalUsers.isPresent() == true && optionalUsers.get().getEmailId().equalsIgnoreCase(user.getEmailId())) {
            throw new CustomerAlreadyExistsException("User already registered with given EmailId: " + user.getEmailId());
        }

        user.setAuthorities("ROLE_ADMIN");
        userRepository.save(user);

    }

    public boolean updateUser(UsersDto userDto) {
        boolean isUpdated = false;
        Users existingUser = userRepository.findById(userDto.getPersonId()).orElseThrow(() -> new ResourceNotFoundException("User", "UserID", String.valueOf(userDto.getPersonId())));
        if (existingUser != null) {

            Users user = UsersMapper.mappedToUser(userDto, existingUser);
            userRepository.save(user);
            isUpdated = true;
        }
        return isUpdated;
    }

    public boolean deleteUser(String userID) {
        boolean isDeleted = false;
        Users existingUser = userRepository.findById(Integer.parseInt(userID)).orElseThrow(() -> new ResourceNotFoundException("User", "UserID", userID));
        if (existingUser != null) {
            userRepository.deleteById((Integer.parseInt(userID)));
            isDeleted = true;
        }
        return isDeleted;


    }

    public List<UserListDto> getAllUsers() {

        List<Users> users = (List<Users>) userRepository.findAll();
        List <UserListDto> userListDto = new ArrayList<UserListDto>();

        for (int i =0 ; i<users.size() ; i++)
        {
            Users user = users.get(i);
            UserListDto userDto= UsersListMapper.mappedToUserDTO(new UserListDto(),user);
            userListDto.add(userDto);
        }


        /*users.forEach(
              users1 -> UsersListMapper.mappedToUserDTO(new UserListDto(),users1)

        );*/

        return userListDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<Users> user = userRepository.findByEmailId(username);
        return user.map(SecurityUser::new).orElseThrow(() -> new ResourceNotFoundException("User", "UserID", username));
    }
}
