package com.binary.uniTech.mapper;

import com.binary.uniTech.entity.User;
import com.binary.uniTech.request.user.UserReadRequest;
import com.binary.uniTech.request.user.UserRegisterRequest;
import com.binary.uniTech.request.user.UserUpdateRequest;
import com.binary.uniTech.response.user.UserDeleteResponse;
import com.binary.uniTech.response.user.UserReadResponse;
import com.binary.uniTech.response.user.UserRegisterResponse;
import com.binary.uniTech.response.user.UserUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User requestToEntity(UserRegisterRequest registerRequest);
    User requestToEntity(UserUpdateRequest updateRequest);
//    List<User> listRequestToListEntity(UserReadRequest readRequest);

    UserRegisterResponse registerToResponse(User user);
    UserReadResponse readToResponse(User user);
    UserUpdateResponse updateToResponse(User user);
    UserDeleteResponse deleteToResponse(User user);


//    UserRegisterResponse loginToResponse(User user);

    List<UserReadResponse> listEntityToListResponse(List<User> user);
}
