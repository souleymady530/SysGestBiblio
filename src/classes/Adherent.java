/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import static fenetres.Accueil.*;
import java.awt.HeadlessException;
import java.io.File;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
        



/**
 *
 * @author USER
 */
public class Adherent 
{
    private String nom;
    private String prenom;
    private String genre;
   
  
   
    private String tel;
    private String tel2;
    private String dnaissance;
    private String dadheration;
    private String matricule;
    private String numeroPiece;boolean emprunt,ajour;
    int verifier=0;
 
    

    public Adherent(String nom, String prenom, String genre, String tel, String tel2, String dnaissance, String matricule, boolean emprunt, String numeroPiece, boolean ajour) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.tel = tel;
        this.tel2 = tel2;
        this.dnaissance = dnaissance;
        this.matricule = matricule;
        this.emprunt = emprunt;
        this.numeroPiece = numeroPiece;
        this.ajour = ajour;
    }

    
    
    public Adherent(String nom, String prenom, String genre,    String tel, String tel2, String dnaissance, String dadheration, String matricule, boolean emprunt, String numeroPiece, boolean ajour) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
       
       
     
        this.tel = tel;
        this.tel2 = tel2;
        this.dnaissance = dnaissance;
        this.dadheration = dadheration;
        this.matricule = matricule;
        this.emprunt = emprunt;
        this.numeroPiece = numeroPiece;
        this.ajour = ajour;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Adherent() {
    }

  
    

   

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getDnaissance() {
        return dnaissance;
    }

    public void setDnaissance(String dnaissance) {
        this.dnaissance = dnaissance;
    }

    public String getDadheration() {
        return dadheration;
    }

    public void setDadheration(String dadheration) {
        this.dadheration = dadheration;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public boolean getEmprunt() {
        return emprunt;
    }

    public void setEmprunt(boolean emprunt) {
        this.emprunt = emprunt;
    }

    public String getNumeroPiece() {
        return numeroPiece;
    }

    public void setNumeroPiece(String numeroPiece) {
        this.numeroPiece = numeroPiece;
    }

    public boolean getAjour() {
        return ajour;
    }

    public void setAjour(boolean ajour) {
        this.ajour = ajour;
    }

    public int getVerifier() {
        return verifier;
    }

    public void setVerifier(int verifier) {
        this.verifier = verifier;
    }
   

    
   
    
    
    public void Enregistrement()
    {
        
        cnx=ConnectionClasse.seConnecter();
        try
        {
            ps=cnx.prepareStatement("INSERT INTO adherents(nom,prenom,genre,dnaissance,numeroPiece,emprunt,ajour,matricule,tel1,tel2) VALUES(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, genre);
            ps.setString(4, dnaissance);
            ps.setString(5, numeroPiece);
           
            ps.setBoolean(6, emprunt);
            ps.setBoolean(7, ajour);
            ps.setString(8, matricule);
            ps.setString(9, tel);
            ps.setString(10, tel2);
            ps.execute();           
            ps.close();
            verifier=1;
            
          ps.close();
            JOptionPane.showMessageDialog(null,"Enregistrement effectué avec succès","SyGestBiblio/Information",JOptionPane.INFORMATION_MESSAGE);  
        }
        catch(Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Echec de l'enregistrement. Veuillez bien verifier les informqtion et reéssayer","SyGestBiblio/Information",JOptionPane.WARNING_MESSAGE);  
        }
    }
   
    
    
    public static Adherent getByMat(String mat)
    {
        Adherent adh=new Adherent();
         cnx=ConnectionClasse.seConnecter();
         try
         {
            st=cnx.createStatement();
            res=st.executeQuery("SELECT * FROM adherents WHERE matricule="+mat);  
            if(res.next())
            {
   //public Adherent(String nom, String prenom, String genre,    String tel, String tel2, String dnaissance, String dadheration, String matricule, boolean emprunt, String numeroPiece, boolean ajour) {

                adh=new Adherent(res.getString("nom"),res.getString("prenom"),res.getString("genre"),res.getString("tel1"),res.getString("tel1"),res.getString("dnaissance").toString(),String.valueOf(res.getDate("dadheration")),res.getString("matricule"),res.getBoolean("emprunt"),res.getString("numeroPiece"),res.getBoolean("ajour"));
            }
            else
            {
                           JOptionPane.showMessageDialog(null,"Données compues","SyGestBiblio/Information",JOptionPane.OK_OPTION);  
            }
             st.close();
          res.close();
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
         return adh;
    }
    
    
    
    
    public void mise_a_jour()
    {
         cnx=ConnectionClasse.seConnecter();
        try
        {
            ps=cnx.prepareStatement("UPDATE adherents set nom=?,prenom=?,genre=?,dnaissance=?,numeroPiece=?,emprunt=?,ajour=?,tel1=?,tel2=? WHERE matricule=?");

            
             ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, genre);
            ps.setString(4, dnaissance);
            ps.setString(5, numeroPiece);
             
              ps.setBoolean(6, emprunt);
               ps.setBoolean(7, ajour);
               
                 ps.setString(8, tel);
                  ps.setString(9, tel2);
             ps.setString(10, matricule);
            ps.executeUpdate();           
            ps.close();
            JOptionPane.showMessageDialog(null, "Informations modifiée avec succès","SyGestBiblio/Information",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    
    
    public void suppresion()
    {
         cnx=ConnectionClasse.seConnecter();
        try
        {
            ps=cnx.prepareStatement("DELETE FROM adherents WHERE matricule=?");
            ps.setString(1, matricule);
            ps.executeUpdate();
            ps.close();
           JOptionPane.showMessageDialog(null, "Adherent supprimé avec succès","SyGestBiblio/Information",JOptionPane.INFORMATION_MESSAGE);
              st.close();
          ps.close();   
        }
        catch(HeadlessException | SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Impossible de supprimer l adherent","SyGestBiblio/Information",JOptionPane.WARNING_MESSAGE);
        }
        
                   
    }
    public static void afficher_tous(DefaultTableModel tbm)
    {
        
        tbm.setRowCount(0);
        try
        {
          cnx=ConnectionClasse.seConnecter();
          st=cnx.createStatement();
          res=st.executeQuery("SELECT * FROM adherents ORDER BY nom,prenom");
          int num=0;
          while(res.next())
          {
              num++;
             tbm.addRow(new Object[]{num,res.getString("matricule"),res.getString("nom")+" "+res.getString("prenom"),res.getBoolean("ajour"),res.getBoolean("emprunt")});
             
              
          }
          st.close();
          res.close();
          if(num==0)
          {
            JOptionPane.showMessageDialog(null,"Aucune donnée a afficher.Pas d'adherent enregistré!","Information",JOptionPane.YES_OPTION);  
          }
        }
        catch(HeadlessException | SQLException e)
        {
            e.printStackTrace();
        }
       
    }
    
    
    
    public static String chargerNumeroCarte()
    {
        String num="";
        //Ici on recupere le dernier ide dans la table ,on multiplie pas trois et on soustrait dans 100. pour 
        try
        {
            int index=0;
            cnx=ConnectionClasse.seConnecter();
            st=cnx.createStatement();
            res=st.executeQuery("SELECT id FROM adherents ORDER BY id DESC");
            if(res.next())
            {
                index=res.getInt("id");
            }
            
            num="000000";
            
            String  str=String.valueOf(index);
            index++;
             int len=str.length();
              num=num.substring(0,6-len);
            num+=String.valueOf(index);
           
                    
                  
             st.close();
          res.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return num;
    }
    
  
    public static boolean peutEmprunt( String mat)
    {
         try
        {
            int index=0;
            cnx=ConnectionClasse.seConnecter();
            st=cnx.createStatement();
            res=st.executeQuery("SELECT adh FROM emprunts WHERE adh=\""+mat+"\"");
            int nombreEmprunt=0;
            while(res.next())
            {
              nombreEmprunt++;  
            }
            if(nombreEmprunt<5)
            {
                return true;
            }
            else
                return false;
           
           
        }
         catch(Exception e)
         {
             e.printStackTrace();
         }
        
        return false;
    }
    
    public static DefaultTableModel  getAllEmprunt(String mat,DefaultTableModel tbm)
    {
        tbm.setRowCount(0);
     
       
         try
        {
            int index=0;
                cnx=ConnectionClasse.seConnecter();
                st=cnx.createStatement();
                res=st.executeQuery("SELECT * FROM emprunts INNER JOIN livres ON (emprunts.lv=livres.code) WHERE adh=\""+mat+"\"");
           while(res.next())
           {
                String code=res.getString("lv"),titre=res.getString("titre"),dateE=res.getString("dateEmprunt"),dateR=res.getString("dateRetour");
                 tbm.addRow(new Object[]{false,code,titre,dateE,dateR});
           }
            
            
            
            
           return tbm;
        }
         catch(SQLException e)
         {
               
             e.printStackTrace();
             
         }
      return tbm;
    }
    
   public static void exportationListe()
   {
       try
        {
        //Cette classe permet de creer le fichier excel lesadherents.xsl lesadherents.xls
            WritableWorkbook wtable=Workbook.createWorkbook(new File("C://Users/Public/lesadherents_sauvegarde.xls"));
        //on se position alors sur la premiere feuille excel c a d l'onglet 1//on cree le premier onglet
            WritableSheet ws=wtable.createSheet("Liste_des_adherents", 0);
        //Mettre un font sur l'entete
            WritableFont font=new WritableFont(WritableFont.TIMES,12,WritableFont.BOLD,true,UnderlineStyle.NO_UNDERLINE,Colour.BLUE);
            WritableCellFormat format=new WritableCellFormat(font);
            Label nom,prenom,genre,date,cnib,emprunt,ajour,tel1,tel2;
            
            nom=new Label(0,0,"Nom",format);
            prenom=new Label(1,0,"Prenom",format);
            
            genre=new Label(2,0,"Genre",format);
            date=new Label(3,0,"Date de naissance",format);
            cnib=new Label(4,0,"Numero CNIB",format);
            emprunt=new Label(5,0,"A Emprunte",format);
            ajour=new Label(6,0,"A jour des cotisations",format);
            tel1=new Label(7,0,"Telephone 1",format);
            
            tel2=new Label(8,0,"Telephone 2",format);
          
            
            ws.addCell(nom);
            ws.addCell(prenom);
            ws.addCell(genre);
            ws.addCell(date);
            ws.addCell(cnib);
            ws.addCell(emprunt);
            ws.addCell(ajour);
            ws.addCell(tel1);
            ws.addCell(tel2);
        
          
          cnx=ConnectionClasse.seConnecter();
                st=cnx.createStatement();
                res=st.executeQuery("SELECT * FROM adherents");
                int i=1;
           while(res.next())
           {
                nom=new Label(0,i,res.getString("nom"),format);
                prenom=new Label(1,i,res.getString("prenom"),format);

                genre=new Label(2,i,res.getString("genre"),format);
                date=new Label(3,i,res.getString("dNaissance"),format);
                cnib=new Label(4,i,res.getString("numeroPiece"),format);
                emprunt=new Label(5,i,String.valueOf(res.getInt("emprunt")),format);
                ajour=new Label(6,i,String.valueOf(res.getInt("ajour")),format);
                tel1=new Label(7,i,res.getString("tel1"),format);
                tel2=new Label(8,i,res.getString("tel2"),format);


                ws.addCell(nom);
                ws.addCell(prenom);
                ws.addCell(genre);
                ws.addCell(date);
                ws.addCell(cnib);
                ws.addCell(emprunt);
                ws.addCell(ajour);
                ws.addCell(tel1);
                ws.addCell(tel2);
              
               i++;
           }
             wtable.write();
          wtable.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
   }
}
