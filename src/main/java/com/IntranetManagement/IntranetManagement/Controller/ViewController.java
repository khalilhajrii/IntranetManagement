package com.IntranetManagement.IntranetManagement.Controller;
import com.IntranetManagement.IntranetManagement.Services.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ViewController {
    private final JwtService jwtService;

    public ViewController(JwtService jwtService) {
        this.jwtService = jwtService;
    }
        @GetMapping("/home")
        public String homePage(Model model, HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization").substring(7);
            String email = jwtService.extractEmail(token);
            boolean isAdmin = jwtService.extractIsAdmin(token);
            model.addAttribute("email", email);
            model.addAttribute("isAdmin", isAdmin);
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

            return "home";
        }

    @GetMapping("/")
    public String loginPage() {
        return "Login";
    }

    @GetMapping("/addUserPage")
    public String addUserPage() {
        return "addUser";
    }
}
