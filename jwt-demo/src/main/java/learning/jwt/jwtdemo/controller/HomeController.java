package learning.jwt.jwtdemo.controller;

import learning.jwt.jwtdemo.model.JwtRequest;
import learning.jwt.jwtdemo.model.JwtResponse;
import learning.jwt.jwtdemo.service.UserService;
import learning.jwt.jwtdemo.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "Welcome to Spring Security";
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUserName(), jwtRequest.getPassWord()
                    )
            );
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("Bad Credentials", badCredentialsException);
        }

        final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUserName());

        final String token = jwtUtility.generateToken(userDetails);

        return new JwtResponse(token);
    }
}
