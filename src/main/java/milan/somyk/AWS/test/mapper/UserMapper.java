package milan.somyk.AWS.test.mapper;


import milan.somyk.AWS.test.dto.UserDto;
import milan.somyk.AWS.test.document.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toResponseDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
    public User fromDto(UserDto userDto){
        return new User(userDto.getId(), userDto.getUsername(), userDto.getPassword(),userDto.getRole());
    }
}
