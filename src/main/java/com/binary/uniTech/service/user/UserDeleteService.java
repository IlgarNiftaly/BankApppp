package com.binary.uniTech.service.user;

import com.binary.uniTech.entity.User;
import com.binary.uniTech.repository.UserRepository;
import com.binary.uniTech.request.user.UserDeleteRequest;
import com.binary.uniTech.response.user.UserDeleteResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDeleteService {
    private final UserRepository userRepository;


    public void deleteAll(){
        userRepository.deleteAll();
    }

    public void delete(UserDeleteRequest deleteRequest){
//        User user = userRepository.findByUserPin(deleteRequest.getUserPin());
        userRepository.delete(userRepository.findByUserPin(deleteRequest.getUserPin()));
    }

    public void deleteById(UserDeleteRequest deleteRequest){
        userRepository.deleteById(deleteRequest.getId());
    }

    public void deleteByUserPin(UserDeleteRequest deleteRequest){
        userRepository.deleteByUserPin(deleteRequest.getUserPin());
    }

}
