package com.project.EmployeeApplication.authentication;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"username","role"})
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
    public String loginRequest(@RequestParam String username, @RequestParam String password, ModelMap model,HttpSession session) {
        if (authService.authenticate(username, password)) {
            session.setAttribute("role",authService.getRole());
            model.put("username", username);
            return "welcome";
        }
        model.put("errorMessage","Invalid Credentials! Please try again!");
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutRequest(HttpSession session, ModelMap model) {
        session.invalidate();
        if (model.containsAttribute("username") || model.containsAttribute("role")){
            model.remove("username");
            model.remove("role");
        }
        return "redirect:login";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String goToWelcomePage(ModelMap model) {
        String username = (String) model.get("username");
        if (username != null && !username.isEmpty()) {
            model.put("username", username);
            return "welcome";
        }
        return "redirect:login";
    }


}
