package com.binary.uniTech.service.user;


import com.binary.uniTech.entity.User;
import com.binary.uniTech.exception.UserNotFoundException;
import com.binary.uniTech.exception.error.ErrorMessage;
import com.binary.uniTech.mapper.UserMapper;
import com.binary.uniTech.repository.UserRepository;
import com.binary.uniTech.request.user.UserUpdateRequest;
import com.binary.uniTech.response.user.UserUpdateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserUpdateService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserUpdateResponse update(UserUpdateRequest updateRequest){
        User user = userRepository.findById(updateRequest.getId())
                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));

        if (Objects.nonNull(updateRequest.getUserName())) {
            user.setUserName(updateRequest.getUserName());
        }
        if (Objects.nonNull(updateRequest.getUserPin())) {
            user.setUserPin(updateRequest.getUserPin());
        }
        if (Objects.nonNull(updateRequest.getEmail())) {
            user.setEmail(updateRequest.getEmail());
        }
        if (Objects.nonNull(updateRequest.getPassword())) {
            user.setPassword(updateRequest.getPassword());
        }
        if (Objects.nonNull(updateRequest.getEmailVerified())) {
            user.setEmailVerified(updateRequest.getEmailVerified());
        }
        if (Objects.nonNull(updateRequest.getFkAccountId())) {
            user.setFkAccountId(updateRequest.getFkAccountId());
        }

        userRepository.save(user);
        log.info("user updated {}", user);
        return userMapper.updateToResponse(user);
    }
}
