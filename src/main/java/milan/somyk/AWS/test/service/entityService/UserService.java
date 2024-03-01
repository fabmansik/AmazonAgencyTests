package milan.somyk.AWS.test.service.entityService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import milan.somyk.AWS.test.dto.UserDto;
import milan.somyk.AWS.test.dto.response.ResponseContainer;
import milan.somyk.AWS.test.document.User;
import milan.somyk.AWS.test.enums.Role;
import milan.somyk.AWS.test.mapper.UserMapper;
import milan.somyk.AWS.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public ResponseContainer register(UserDto userDto) {
        User user = userMapper.fromDto(userDto);
        ResponseContainer responseContainer = new ResponseContainer();
        if (ObjectUtils.isEmpty(userDto)) {
            log.error("user is null");
            return responseContainer.setErrorMessageAndStatusCode("user is null", HttpStatus.BAD_REQUEST.value());
        }
        if (!StringUtils.hasText(user.getUsername())) {
            log.error("username is null");
            return responseContainer.setErrorMessageAndStatusCode("username is null", HttpStatus.BAD_REQUEST.value());
        }
        if (!StringUtils.hasText(user.getPassword())) {
            log.error("password is null");
            return responseContainer.setErrorMessageAndStatusCode("password is null", HttpStatus.BAD_REQUEST.value());
        }
        User foundUser;
        try {
            foundUser = userRepository.findByUsername(user.getUsername()).orElse(null);
        } catch (Exception e){
            log.error(e.getMessage());
            return responseContainer.setErrorMessageAndStatusCode(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        if(!ObjectUtils.isEmpty(foundUser)){
            log.error("user already exists");
            return responseContainer.setErrorMessageAndStatusCode("user already exists", HttpStatus.BAD_REQUEST.value());
        }
        String encoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoded);
        user.setRole(Role.USER.toString());
        User savedUser = userRepository.save(user);
        responseContainer.setCreatedResult(userMapper.toResponseDto(savedUser));
        return responseContainer;
    }
}
