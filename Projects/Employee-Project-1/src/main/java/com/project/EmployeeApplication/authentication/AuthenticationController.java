package com.project.EmployeeApplication.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("username")
public class AuthenticationController {

    private final HardCodedAuthenticationService authService;

    public AuthenticationController(HardCodedAuthenticationService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String goToLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String goToWelcomePage(@RequestParam String username, @RequestParam String password, ModelMap model) {
        if (authService.authenticate(username, password)) {
            model.put("username", username);
            return "welcome";
        }
        model.put("errorMessage","Invalid Credentials! Please try again!");
        return "login";
    }


}