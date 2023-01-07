package ma.projet.projetcontrolle.bean;

public class Produit {

    private int id,id_client,id_categorie;
    private String nom;
    private double prix;
    private int paye;

    public Produit() {
    }

    public Produit(int id, int id_client, int id_categorie, String nom, double prix, int paye) {
        this.id = id;
        this.id_client = id_client;
        this.id_categorie = id_categorie;
        this.nom = nom;
        this.prix = prix;
        this.paye = paye;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getPaye() {
        return paye;
    }

    public void setPaye(int paye) {
        this.paye = paye;
    }
}
