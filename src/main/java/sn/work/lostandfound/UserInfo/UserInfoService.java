package sn.work.lostandfound.UserInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import sn.work.lostandfound.person.Person;
import sn.work.lostandfound.person.PersonConverter;
import sn.work.lostandfound.person.PersonDto;
import sn.work.lostandfound.person.PersonService;
import sn.work.lostandfound.security.service.JwtService;
import sn.work.lostandfound.utils.UserResponse;

import java.util.Optional;

@Service
public class UserInfoService {

    private final UserInfoRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public UserInfoService(
            UserInfoRepository repository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "user added to system ";
    }

    public UserResponse authenticateAndGetToken(
            AuthRequest authRequest,
            PersonService personService,
            PersonConverter personConverter
            ) throws JsonProcessingException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {

//            ObjectMapper mapper = new ObjectMapper();
            Optional<Person> person = personService.findPersonByEmail(authRequest.getUsername());
            Optional<PersonDto> personDto = person.map(personConverter::convertToDto);

            UserResponse userResponse = new UserResponse();
            userResponse.setToken(jwtService.generateToken(authRequest));
            userResponse.setPerson(personDto);

            return userResponse;
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
