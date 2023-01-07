package ma.projet.projetcontrolle.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import ma.projet.projetcontrolle.bean.Categorie;
import ma.projet.projetcontrolle.bean.Client;

public class Hhelper extends SQLiteOpenHelper {

    public Hhelper(@Nullable Context context) {
        super(context, "ProjetControlle", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE client(_id INTEGER PRIMARY KEY, nom TEXT, prenom TEXT, sexe TEXT,age INTEGER,cin TEXT)");
        db.execSQL("CREATE TABLE categorie(_id INTEGER PRIMARY KEY, nom TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop TABLE IF EXISTS client");
        db.execSQL("Drop TABLE IF EXISTS categorie");
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
        db.close();
        return c;
    }

    public Cursor findAllCategorie(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM categorie",null);
        db.close();
        return c;
    }

    public Client findClientById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.query("client",new String[]{"_id","nom","prenom","sexe","age","cin"},"_id=?",new String[]{String.valueOf(id)},null,null,null);
        cr.moveToFirst();
        Client c = new Client(cr.getInt(0),cr.getString(1),cr.getString(2),cr.getString(3),cr.getInt(4),cr.getString(5));
        db.close();
        return c;
    }

    public Categorie findCategorieById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.query("categorie",new String[]{"_id","nom"},"_id=?",new String[]{String.valueOf(id)},null,null,null);
        cr.moveToFirst();
        Categorie cat = new Categorie(cr.getInt(0),cr.getString(1));
        db.close();
        return cat;
    }
}