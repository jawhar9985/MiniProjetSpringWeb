package spring.jpa.model;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.ArrayList;

@Entity
public class Employe extends User {

    private Date dateEmbauchement;

    @OneToMany(mappedBy = "employe", cascade = { CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST })
    private Collection<Conge> conges = new ArrayList<>();

    // Default constructor
    public Employe() {
        super();
    }

    // Parameterized constructor
    public Employe(String code, String nom, String prenom, String login, String password, Date dateEmbauchement) {
        super(code, nom, prenom, login, password);
        this.dateEmbauchement = dateEmbauchement;
    }

    // Additional constructor to initialize with ID (for specific use cases)
    public Employe(Long employeId) {
        this.setId(employeId);
    }

    // Getters
    public Date getDateEmbauchement() {
        return dateEmbauchement;
    }

    public Collection<Conge> getConges() {
        return new ArrayList<>(conges);
    }

    // Setters
    public void setDateEmbauchement(Date dateEmbauchement) {
        this.dateEmbauchement = dateEmbauchement;
    }

    public void setConges(Collection<Conge> conges) {
        this.conges.clear();
        if (conges != null) {
            this.conges.addAll(conges);
        }
    }

    // Methods to manage Conge relationships
    public void addConge(Conge conge) {
        this.conges.add(conge);
        if (conge != null) {
            conge.setEmploye(this);
        }
    }

    public void removeConge(Conge conge) {
        this.conges.remove(conge);
        if (conge != null) {
            conge.setEmploye(null);
        }
    }
}
