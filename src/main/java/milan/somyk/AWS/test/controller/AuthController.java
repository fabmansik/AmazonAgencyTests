package milan.somyk.AWS.test.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import milan.somyk.AWS.test.dto.UserDto;
import milan.somyk.AWS.test.dto.requests.RefreshRequest;
import milan.somyk.AWS.test.dto.requests.SignInRequest;
import milan.somyk.AWS.test.dto.response.ResponseContainer;
import milan.somyk.AWS.test.service.AuthService;
import milan.somyk.AWS.test.service.entityService.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<ResponseContainer> signIn(@Valid @RequestBody SignInRequest signInRequest){
        ResponseContainer responseContainer = authService.login(signInRequest);
        return ResponseEntity.status(responseContainer.getStatusCode()).body(responseContainer);
    }

    @PostMapping("/refresh")
    public ResponseEntity<ResponseContainer> refresh(@Valid @RequestBody RefreshRequest refreshRequest){
        ResponseContainer responseContainer = authService.refresh(refreshRequest);
        return ResponseEntity.status(responseContainer.getStatusCode()).body(responseContainer);
    }
    @PostMapping("/register")
    public ResponseEntity<ResponseContainer> create(@RequestBody @Valid UserDto userDto){
        ResponseContainer responseContainer = userService.register(userDto);
        return ResponseEntity.status(responseContainer.getStatusCode()).body(responseContainer);
    }
}
