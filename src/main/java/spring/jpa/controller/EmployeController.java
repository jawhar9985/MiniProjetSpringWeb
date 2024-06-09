package spring.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.jpa.model.Conge;
import spring.jpa.model.Employe;
import spring.jpa.repository.CongeRepository;
import spring.jpa.repository.EmployeRepository;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/employe")
public class EmployeController {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private CongeRepository congeRepository;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard-employe";
    }

    @GetMapping("/nouveauConge")
    public String showNouveauCongeForm(Model model) {
        model.addAttribute("conge", new Conge());
        return "nouveau-conge";
    }

    @PostMapping("/nouveauConge")
    public String ajouterNouveauConge(@ModelAttribute Conge conge) {
        Employe employe = employeRepository.findById(2L).orElse(null);
        if (employe != null && conge != null) {
            conge.setEmploye(employe);
            conge.setEtat("SOLLICITE");
            congeRepository.save(conge);
        }
        return "redirect:/employe/conges";
    }

    @GetMapping("/conges")
    public String filtrerConges(Model model, @RequestParam(required = false) String etat) {
        List<Conge> conges;

        if (etat != null && !etat.isEmpty()) {
            conges = congeRepository.findByEtat(etat);
        } else {
            conges = congeRepository.findAll();
        }

        model.addAttribute("conges", conges);
        return "liste-conges";
    }
    
    @PostMapping("/annulerConge")
    public String annulerConge(@RequestParam Long id) {
        Conge conge = congeRepository.findById(id).orElse(null);
        if (conge != null && conge.getEtat().equals("SOLLICITE")) {
            conge.setEtat("ANNULE");
            congeRepository.save(conge);
        }
        return "redirect:/employe/conges";
    }

    @PostMapping("/arreterConge")
    public String arreterConge(@RequestParam("id") Long id) {
        Conge conge = congeRepository.findById(id).orElse(null);
        if (conge != null && conge.getEtat().equals("EN_COURS")) {
            conge.setEtat("ARRETE");
            conge.setDateRupture(LocalDate.now()); // Mettre la date actuelle comme date de rupture
            congeRepository.save(conge);
        }
        return "redirect:/employe/conges";
    }

}
