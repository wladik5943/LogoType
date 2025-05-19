package com.soft_arex.mapper;

import com.soft_arex.entity.User;
import com.soft_arex.user.model.UserCreateRequest;
import com.soft_arex.user.model.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserCreateRequest userCreateRequest);
    UserResponse toResponse(User user);
}
