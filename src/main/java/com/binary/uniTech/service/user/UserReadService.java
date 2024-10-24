package com.binary.uniTech.service.user;

import com.binary.uniTech.entity.User;
import com.binary.uniTech.exception.UserNotFoundException;
import com.binary.uniTech.exception.error.ErrorMessage;
import com.binary.uniTech.mapper.UserMapper;
import com.binary.uniTech.repository.UserRepository;
import com.binary.uniTech.request.user.UserReadRequest;
import com.binary.uniTech.response.user.UserReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserReadService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserReadResponse> readAll(){
        return userMapper.listEntityToListResponse(userRepository.findAll());
    }

    public UserReadResponse readById(UserReadRequest readRequest){
        Optional<User> userOptional = userRepository.findById(readRequest.getId());
        if(userOptional.isEmpty()){
//            throw  new UserNotFoundException(HttpStatus.NOT_FOUND.name(), "this ID does not exist");
            throw new UserNotFoundException(ErrorMessage.USER_NOT_FOUND);
        }
        return userOptional.map(userMapper::readToResponse).orElse(null);
    }

    public List<UserReadResponse> readByUserName(UserReadRequest readRequest){
        if(userRepository.findByUserName(readRequest.getUserName()).isEmpty()){
            throw new UserNotFoundException(ErrorMessage.USERNAME_NOT_FOUND);
        }
        return userMapper.listEntityToListResponse(userRepository.findByUserName(readRequest.getUserName()));
    }

    public UserReadResponse readByUserPin(UserReadRequest readRequest){
        if(userRepository.findByUserPin(readRequest.getUserPin()) == null){
            throw new UserNotFoundException(ErrorMessage.USERPIN_NOT_FOUND);
        }
        return userMapper.readToResponse(userRepository.findByUserPin(readRequest.getUserPin()));
    }

    public UserReadResponse readByEmail(UserReadRequest readRequest){
        if(userRepository.findByEmail(readRequest.getUserName()) == null){
            throw new UserNotFoundException(ErrorMessage.EMAIL_NOT_FOUND);
        }
        return userMapper.readToResponse(userRepository.findByEmail(readRequest.getEmail()));
    }

}
