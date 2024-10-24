package com.binary.uniTech.service.user;

import com.binary.uniTech.entity.User;
import com.binary.uniTech.exception.UserConflictException;
import com.binary.uniTech.exception.error.ErrorMessage;
import com.binary.uniTech.mapper.UserMapper;
import com.binary.uniTech.repository.UserRepository;
import com.binary.uniTech.request.user.UserRegisterRequest;
import com.binary.uniTech.response.user.UserRegisterResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRegisterService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserEmailService emailService;


    public UserRegisterResponse register(UserRegisterRequest registerRequest){
        log.info("yoxlama 0");
        if(checkUserPin(registerRequest.getUserPin())){
            throw new UserConflictException(ErrorMessage.USERPIN_ALREADY_EXISTS); //
        }
        if(checkEmail(registerRequest.getEmail())){
            throw new UserConflictException(ErrorMessage.EMAIL_ALREADY_EXISTS); //
        }
        log.info("yoxlama 1");
        User user = userMapper.requestToEntity(registerRequest);
        log.info("yoxlama 2");
        User userSave = userRepository.save(user);
        log.info("user in registered {}", user);
        log.info("yoxlama 3");

        emailService.sendEmail("ilqardrob@gmail.com", "This is subjects", "This is body of email");
        System.out.println("Mail sendering");

        log.info("yoxlama 4");
        if(registerRequest.getEmailVerified() == null){
            registerRequest.setEmailVerified(false);
        }
        log.info("yoxlama 5");
        return userMapper.registerToResponse(user);
    }

    private boolean checkUserPin(String userPin){
        return userRepository.findByUserPin(userPin)!= null;
    }
    private boolean checkEmail(String email){
        return userRepository.findByEmail(email)!=null;
    }
}
