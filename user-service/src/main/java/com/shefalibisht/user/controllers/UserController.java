package com.shefalibisht.user.controllers;

import com.shefalibisht.user.exceptions.RecordNotFoundException;
import com.shefalibisht.user.models.CustomerInput;
import com.shefalibisht.user.services.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.client.registration.ClientRegistration;

import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private CustomerServiceImpl userService;

    private ClientRegistration registration;

    public UserController(ClientRegistrationRepository registrations) {
        this.registration = registrations.findByRegistrationId("okta");
    }

    @GetMapping("/api/user")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal OAuth2User user) {
        if (user == null) {
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
            return ResponseEntity.ok().body(user.getAttributes());
        }
    }
    @PostMapping("/api/logout")
    public ResponseEntity<?> logout(HttpServletRequest request,
                                    @AuthenticationPrincipal(expression = "idToken") OidcIdToken idToken) {
        // send logout URL to client so they can initiate logout
        String logoutUrl = this.registration.getProviderDetails()
                .getConfigurationMetadata().get("end_session_endpoint").toString();

        Map<String, String> logoutDetails = new HashMap<>();
        logoutDetails.put("logoutUrl", logoutUrl);
        logoutDetails.put("idToken", idToken.getTokenValue());
        request.getSession(false).invalidate();
        return ResponseEntity.ok().body(logoutDetails);
    }



    @GetMapping({"/all","/testing/all"})
    public String getAllUsers() {
        return "GET_ALL";
    }

    @GetMapping({"/{id}","/testing/{id}"})
    public String getEmployeeById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        return "GET_BY_ID";
    }


    @PostMapping("/testing/post")
    public String createNewUser(@RequestBody CustomerInput userinput) {
        return "POST";

    }

    @PutMapping({"/{id}","/testing/{id}"})
    public String updateExistingNewUser(@PathVariable (value = "id") Long id, @RequestBody CustomerInput userinput) {
        return "PUT";

    }

    @DeleteMapping({"/{id}","/testing/{id}"})
    public String deleteUser(@PathVariable (value = "id") Long id){
        return "DELETE";
    }

}
