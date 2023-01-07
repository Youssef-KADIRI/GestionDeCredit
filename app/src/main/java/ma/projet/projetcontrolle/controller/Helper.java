package ma.projet.projetcontrolle.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import ma.projet.projetcontrolle.bean.Categorie;
import ma.projet.projetcontrolle.bean.Client;
import ma.projet.projetcontrolle.bean.Produit;

public class Helper extends SQLiteOpenHelper {
    public Helper(@Nullable Context context) {
        super(context, "Projet", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE client(_id INTEGER PRIMARY KEY, nom TEXT, prenom TEXT, sexe TEXT,age INTEGER,cin TEXT)");
        db.execSQL("CREATE TABLE categorie(_id INTEGER PRIMARY KEY, nom TEXT)");
        db.execSQL("CREATE TABLE produit(_id INTEGER PRIMARY KEY,nom TEXT, prix REAL,paye INTEGER,id_client INTEGER,id_categorie INTEGER,FOREIGN KEY (id_client) REFERENCES client(id),FOREIGN KEY (id_categorie) REFERENCES categorie(id))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop TABLE IF EXISTS client");
        db.execSQL("Drop TABLE IF EXISTS categorie");
        db.execSQL("DROP TABLE IF EXISTS produit");
        onCreate(db);
    }

    public void insertClient(Client c){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom",c.getNom());
        cv.put("prenom",c.getPrenom());
        cv.put("sexe",c.getSexe());
        cv.put("age",c.getAge());
        cv.put("cin",c.getCin());
        db.insert("client",null,cv);
        db.close();
    }

    public void insertCategorie(Categorie cat){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom",cat.getNom());
        db.insert("categorie",null,cv);
        db.close();
    }

    public void insertProduit(Produit p){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom",p.getNom());
        cv.put("id_client",p.getId_client());
        cv.put("id_categorie",p.getId_categorie());
        cv.put("prix",p.getPrix());
        cv.put("paye",p.getPaye());
        db.insert("produit",null,cv);
        db.close();
    }

    public Cursor findAllProduit(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM produit",null);
        return c;
    }

    public Cursor findAllProduitDispo(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM produit WHERE id_client=0",null);
        return c;
    }



    public void updateClient(Client c){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom",c.getNom());
        cv.put("prenom",c.getPrenom());
        cv.put("sexe",c.getSexe());
        cv.put("age",c.getAge());
        cv.put("cin",c.getCin());
        db.update("client",cv,"_id=?",new String[]{String.valueOf(c.getId())});
        db.close();
    }

    public void updateCategorie(Categorie cat){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom",cat.getNom());
        db.update("categorie",cv,"_id=?",new String[]{String.valueOf(cat.getId())});
        db.close();
    }

    public void updateProduit(Produit p){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id_client",p.getId_client());
        cv.put("id_categorie",p.getId_categorie());
        cv.put("nom",p.getNom());
        cv.put("prix",p.getPrix());
        cv.put("paye",p.getPaye());
        db.update("produit",cv,"_id=?",new String[]{String.valueOf(p.getId())});
        db.close();
    }

    public void deleteClient(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("client","_id=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteCategorie(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("categorie","_id=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public Cursor findAllClient(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM client",null);
        return c;
    }

    public Cursor findAllCategorie(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM categorie",null);
        return c;
    }

    public Client findClientById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.query("client",new String[]{"_id","nom","prenom","sexe","age","cin"},"_id=?",new String[]{String.valueOf(id)},null,null,null);
        cr.moveToFirst();
        Client c = new Client(cr.getInt(0),cr.getString(1),cr.getString(2),cr.getString(3),cr.getInt(4),cr.getString(5));
        return c;
    }

    public Categorie findCategorieById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.query("categorie",new String[]{"_id","nom"},"_id=?",new String[]{String.valueOf(id)},null,null,null);
        cr.moveToFirst();
        Categorie cat = new Categorie(cr.getInt(0),cr.getString(1));
        return cat;
    }

    public Produit findProduitById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.query("produit",new String[]{"_id","id_client","id_categorie","nom","prix","paye"},"_id=?",new String[]{String.valueOf(id)},null,null,null);
        cr.moveToFirst();
        Produit p = new Produit(cr.getInt(0),cr.getInt(1),cr.getInt(2),cr.getString(3),cr.getDouble(4),cr.getInt(5));
        return p;
    }

    public ArrayList<Categorie> getSpinnerCategorie() {
        ArrayList<Categorie> spincat = new ArrayList<Categorie>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM categorie",null);
        if (c.moveToFirst()) {
            do {
                Categorie cat = new Categorie();
                cat.setId(c.getInt(0));
                cat.setNom(c.getString(1));
                spincat.add(cat);
            } while (c.moveToNext());
        }
        return spincat;
    }

    public ArrayList<Client> getSpinnerClient() {
        ArrayList<Client> spincl = new ArrayList<Client>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM client",null);
        if (c.moveToFirst()) {
            do {
                Client cl = new Client();
                cl.setId(c.getInt(0));
                cl.setNom(c.getString(1));
                spincl.add(cl);
            } while (c.moveToNext());
        }
        return spincl;
    }

    public Cursor findCreditClient(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM produit WHERE id_client="+id+" AND paye=0",null);
        return c;
    }

    public double findCreditClientMontant(int id){
        double montant = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT SUM(prix) FROM produit WHERE id_client="+id+" AND paye=0",null);
        if (c.moveToFirst()) {
            do {
                montant = c.getDouble(0);
            } while (c.moveToNext());
        }
        return montant;
    }

    public double findAllBenifits(){
        double montant = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT SUM(prix) FROM produit WHERE id_client !=0 AND paye=1",null);
        if (c.moveToFirst()) {
            do {
                montant = c.getDouble(0);
            } while (c.moveToNext());
        }
        return montant;
    }

    public double findAllCredit(){
        double montant = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT SUM(prix) FROM produit WHERE id_client !=0 AND paye=0",null);
        if (c.moveToFirst()) {
            do {
                montant = c.getDouble(0);
            } while (c.moveToNext());
        }
        return montant;
    }

    public void updateCredit(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE produit SET paye=1 WHERE _id = "+id);
    }
}
