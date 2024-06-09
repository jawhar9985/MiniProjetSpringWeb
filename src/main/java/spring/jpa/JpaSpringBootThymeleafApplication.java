package spring.jpa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.ListCrudRepository;

import spring.jpa.model.Admin;
import spring.jpa.model.Employe;
import spring.jpa.model.Conge;
import spring.jpa.repository.AdminRepository;
import spring.jpa.repository.EmployeRepository;
import spring.jpa.repository.CongeRepository;


@SpringBootApplication
public class JpaSpringBootThymeleafApplication {
    // Déclarer des références des interfaces "EmployeRepository", "AdminRepository" et "CongeRepository"
    static EmployeRepository employeRepos;
    static AdminRepository adminRepos;
    static CongeRepository congeRepos;

    public static void main(String[] args) {
        // Référencer le contexte
        ApplicationContext contexte = SpringApplication.run(JpaSpringBootThymeleafApplication.class, args);

        // Récupérer des implémentations des interfaces par injection de dépendance
        employeRepos = contexte.getBean(EmployeRepository.class);
        adminRepos = contexte.getBean(AdminRepository.class);
        congeRepos = contexte.getBean(CongeRepository.class);

        // Créer un admin et un employé
        Admin admin = new Admin();
        admin.setCode("ADM001");
        admin.setNom("Lajmi");
        admin.setPrenom("Jawhar");
        admin.setLogin("jawhar123");
        admin.setPassword("123");
        adminRepos.save(admin);

        Employe emp1 = new Employe();
        emp1.setCode("EMP001");
        emp1.setNom("Toumi");
        emp1.setPrenom("Emna");
        emp1.setLogin("emna123");
        emp1.setPassword("123");
        employeRepos.save(emp1);
        
        afficherTousLesEmployes();
        
        
    }
    
  


    static void afficherTousLesEmployes() {
        System.out.println("***************************************");
        System.out.println("Afficher tous les employés...");
        System.out.println("***************************************");

        List<Employe> employes = employeRepos.findAll();
        for (Employe e : employes) {
            System.out.println(e);
        }
        System.out.println("***************************************");
    }

    static void afficherTousLesConges() {
        System.out.println("***************************************");
        System.out.println("Afficher tous les congés...");
        System.out.println("***************************************");

        List<Conge> conges = congeRepos.findAll();
        for (Conge c : conges) {
            System.out.println(c);
        }
        System.out.println("***************************************");
    }
}
