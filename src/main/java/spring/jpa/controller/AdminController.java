package spring.jpa.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;
import spring.jpa.model.Conge;
import spring.jpa.repository.CongeRepository;

@Controller
public class AdminController {
    @Autowired
    private CongeRepository congeRepository;
    @GetMapping("/admin/dashboard")
    public String showDashboard() {
        return "dashboard-admin"; 
    }
    
    @GetMapping("/admin/conges")
    public String listConges(Model model, @RequestParam(required = false) String etat,
            @RequestParam(required = false) String annee) {
        List<Conge> conges;

        if (etat != null && !etat.isEmpty() && annee != null && !annee.isEmpty()) {
            conges = congeRepository.findByEtatAndYear(etat, Integer.parseInt(annee));
        } else if (etat != null && !etat.isEmpty()) {
            conges = congeRepository.findByEtat(etat);
        } else if (annee != null && !annee.isEmpty()) {
            conges = congeRepository.findByYear(Integer.parseInt(annee));
        } else {
            conges = congeRepository.findAll();
        }

        LocalDate currentDate = LocalDate.now();
        for (Conge conge : conges) {
            LocalDate dateDebut = conge.getDateDebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dateFin = conge.getDateFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if ("VALIDE".equals(conge.getEtat()) && dateDebut.equals(currentDate)) {
                conge.setEtat("EN_COURS");
                congeRepository.save(conge);
            }

            if ("EN_COURS".equals(conge.getEtat()) && dateFin.equals(currentDate)) {
                conge.setEtat("FINI");
                congeRepository.save(conge);
            }
        }

        model.addAttribute("conges", conges);
        return "conges";
    }
    
    @PostMapping("/admin/conge/valider")
    public String validerConge(@RequestParam("id") Long id) {
        Conge conge = congeRepository.findById(id).orElse(null);
        if (conge != null && conge.getEtat().equals("SOLLICITE")) {
            conge.setEtat("VALIDE");
            congeRepository.save(conge);
        }
        return "redirect:/admin/conges";
    }
    
    @PostMapping("/admin/conge/refuser")
    public String refuserConge(@RequestParam("id") Long id) {
        Conge conge = congeRepository.findById(id).orElse(null);
        if (conge != null && conge.getEtat().equals("SOLLICITE") || conge.getEtat().equals("VALIDE")) {
            conge.setEtat("REFUSE");
            congeRepository.save(conge);
        }
        return "redirect:/admin/conges";
    }
    @PostMapping("/admin/conge/arreter")
    public String arreterConge(@RequestParam("id") Long id) {
        Conge conge = congeRepository.findById(id).orElse(null);
        if (conge != null && conge.getEtat().equals("EN_COURS")) {
            conge.setEtat("ARRETE");
            conge.setDateRupture(LocalDate.now());
            congeRepository.save(conge);
        }
        return "redirect:/admin/conges";
    }

}
