package spring.jpa.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Conge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;
    private LocalDate dateRupture;

    @Column(length = 20)
    private String etat;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public LocalDate getDateRupture() {
        return dateRupture;
    }

    public String getEtat() {
        return etat;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setDateRupture(LocalDate dateRupture) {
        this.dateRupture = dateRupture;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
}
