package com.IntranetManagement.IntranetManagement.Controller;
import com.IntranetManagement.IntranetManagement.Entities.Department;
import com.IntranetManagement.IntranetManagement.Services.DepartementService;
import com.IntranetManagement.IntranetManagement.Services.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class ViewController {
    private final JwtService jwtService;
    private final DepartementService departementService;

    public ViewController(JwtService jwtService, DepartementService departementService) {
        this.jwtService = jwtService;
        this.departementService=departementService;
    }
        @GetMapping("/home")
        public String homePage(Model model) {
            model.addAttribute("email", null);
            model.addAttribute("isAdmin", null);
            return "home";
    }

    @GetMapping("/")
    public String loginPage() {
        return "Login";
    }

    @GetMapping("/addUserPage")
    public String addUserPage(Model model) {
        List<Department> departments=departementService.findAllActiveDepartments();
        model.addAttribute("departments",departments);
        return "addUser";
    }
}
