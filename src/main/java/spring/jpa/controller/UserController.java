package spring.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.jpa.model.Admin;
import spring.jpa.model.Employe;
import spring.jpa.model.User;
import spring.jpa.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("login") String login, @RequestParam("password") String password, Model model, RedirectAttributes redirectAttributes) {
        User user = userRepository.findByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            if (user instanceof Admin) {
                return "redirect:/admin/dashboard";
            } else if (user instanceof Employe) {
                return "redirect:/employe/dashboard";
            }
        }
        redirectAttributes.addFlashAttribute("error", "Identifiant ou mot de passe incorrect");
        return "redirect:/login";
    }
}
