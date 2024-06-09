package spring.jpa.model;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User {

    // Default constructor
    public Admin() {
        super();
    }

    // Parameterized constructor
    public Admin(String code, String nom, String prenom, String login, String password) {
        super(code, nom, prenom, login, password);
    }
}
