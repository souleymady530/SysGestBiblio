/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import classes.ConnectionClasse;
import static fenetres.Accueil.cnx;
import static fenetres.Accueil.ps;
import static fenetres.Accueil.res;
import static fenetres.Accueil.st;
import java.awt.HeadlessException;
import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class Emprunt 
{
    private int id;
    private String adh,lv;
    private String dateEmprunt,dateRetour;

    public Emprunt() {
    }

    public Emprunt(String adh, String lv, String dateEmprunt, String dateRetour) {
        this.adh = adh;
        this.lv = lv;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    public Emprunt(int id, String adh, String lv, String dateEmprunt, String dateRetour) {
        this.id = id;
        this.adh = adh;
        this.lv = lv;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdh() {
        return adh;
    }

    public void setAdh(String adh) {
        this.adh = adh;
    }

    public String getLv() {
        return lv;
    }

    public void setLv(String lv) {
        this.lv = lv;
    }

    public String getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(String dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public String getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(String dateRetour) {
        this.dateRetour = dateRetour;
    }
    
    
    
     public static void tousLesRetards(DefaultTableModel tbm)
    {
        tbm.setRowCount(0);
        cnx=ConnectionClasse.seConnecter();
         try
         {
            st=cnx.createStatement();
            //SELECT * FROM emprunts INNER JOIN livres ON (emprunts.lv=livres.code) WHERE adh=\""+mat+"\""
            res=st.executeQuery("SELECT * FROM emprunts INNER JOIN livres ON (emprunts.lv=livres.code)");  
            int i=0;
            while(res.next())
            {
                java.sql.Date jdateR=Date.valueOf(res.getString("dateRetour")),today=java.sql.Date.valueOf(LocalDate.now());
                
               
                if(jdateR.before(today))
                {
                   tbm.addRow(new Object[]{false,res.getString("lv"),res.getString("titre"),res.getString("adh"),res.getString("dateEmprunt"),res.getString("dateRetour")});
                   i++;
                }
            }
            if(i==0)
            {
                JOptionPane.showMessageDialog(null,"Il n'y a aucun retard pour l'instant","SyGestBiblio/Information",JOptionPane.INFORMATION_MESSAGE);  
            }
            st.close();
            res.close();
          }
         catch(SQLException e)
         {
             e.printStackTrace();
         }
    }
    
     
     
     
    
    public void Enregistrement()
    {
         cnx=ConnectionClasse.seConnecter();
        try
        {
            ps=cnx.prepareStatement("INSERT INTO emprunts(adh,lv,dateEmprunt,dateRetour) VALUES(?,?,?,?)");
            ps.setString(1,adh);
            ps.setString(2, lv);
            ps.setString(3, dateEmprunt);
            ps.setString(4, dateRetour);
            ps.execute();           
            ps.close();
            
            
          ps.close();
            JOptionPane.showMessageDialog(null,"Enregistrement de l' emprunts effectué avec succès","SyGestBiblio/Information",JOptionPane.INFORMATION_MESSAGE);  
        }
        catch(Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Echec de l'enregistrement de l'emprunt. Une erreur est survernue.","SyGestBiblio/Information",JOptionPane.WARNING_MESSAGE);  
        }
    }
    
    public static void Suppression(String matricule,String codeLivre)
    {
        cnx=ConnectionClasse.seConnecter();
        try
        {
            ps=cnx.prepareStatement("DELETE FROM emprunts WHERE adh=? AND lv=?");
            ps.setString(1, matricule);
            ps.setString(2, codeLivre);
            ps.executeUpdate();
            ps.close();
           JOptionPane.showMessageDialog(null, "Livre retourné","SyGestBiblio/Information",JOptionPane.INFORMATION_MESSAGE);
              
          res.close();   
        }
        catch(HeadlessException | SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "","SyGestBiblio/Information",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
    
        public static void exportationListe()
   {
       try
        {
        //Cette classe permet de creer le fichier excel lesadherents.xsl lesadherents.xls
            WritableWorkbook wtable=Workbook.createWorkbook(new File("C://Users/Public/lesemprunts_sauvegarde.xls"));
        //on se position alors sur la premiere feuille excel c a d l'onglet 1//on cree le premier onglet
            WritableSheet ws=wtable.createSheet("Liste_des_emprunts_de_livres", 0);
        //Mettre un font sur l'entete
            WritableFont font=new WritableFont(WritableFont.TIMES,12,WritableFont.BOLD,true,UnderlineStyle.NO_UNDERLINE,Colour.BLUE);
            WritableCellFormat format=new WritableCellFormat(font);
            Label adh,lv,dE,dR;
            
            //(titre,edition,auteur,nbreExemplaire,code)
            
            adh=new Label(0,0,"Matricule adherent",format);
            lv=new Label(1,0,"Code de livre",format);
            
            dE=new Label(2,0,"Date Emprunt",format);
           dR=new Label(3,0,"Date Retour",format);
           
             ws.addCell(adh);
            ws.addCell(lv);
            ws.addCell(dE);
            ws.addCell(dR);
          
          
           
          
            
           
        
          
           cnx=ConnectionClasse.seConnecter();
                st=cnx.createStatement();
                res=st.executeQuery("SELECT * FROM emrpunts");
                int i=1;
           while(res.next())
           {
                adh=new Label(0,i,res.getString("adh"),format);
                lv=new Label(1,i,res.getString("lv"),format);

                dE=new Label(2,i,res.getString("date emrpunt"),format);
                dR=new Label(3,i,String.valueOf(res.getInt("Date retour")),format);
                
                ws.addCell(adh);
                ws.addCell(lv);
                ws.addCell(dE);
                ws.addCell(dR);
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
