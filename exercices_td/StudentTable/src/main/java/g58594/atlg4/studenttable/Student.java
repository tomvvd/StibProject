package g58594.atlg4.studenttable;

public class Student {
    private int numero;
    private String prenom;
    private String nom;

    public Student(int numero, String prenom, String nom) {
        this.numero = numero;
        this.prenom = prenom;
        this.nom = nom;
    }

    public int getNumero() {
        return numero;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }
}
