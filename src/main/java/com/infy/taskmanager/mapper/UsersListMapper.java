package com.infy.taskmanager.mapper;


import com.infy.taskmanager.dto.UserListDto;
import com.infy.taskmanager.entity.Users;

public class UsersListMapper {
    public static UserListDto mappedToUserDTO (UserListDto userListDto, Users users){

        userListDto.setId(users.getPersonId());
        userListDto.setCustomerName(users.getName());
        userListDto.setDate(users.getDateOfBirth());
        userListDto.setLocation(users.getAddress());
        return userListDto;
    }

}
