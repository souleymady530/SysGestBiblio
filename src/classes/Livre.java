/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import static classes.ImportExcelAdherent.chargerNumeroCarte;
import static fenetres.Accueil.cnx;
import static fenetres.Accueil.ps;
import static fenetres.Accueil.res;
import static fenetres.Accueil.res2;
import static fenetres.Accueil.st;
import static fenetres.Accueil.st2;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
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
public class Livre 
{
  private  String titre,auteur,maison,code,dateAjout;
  private int exemplaire;
FileInputStream fis;
    ImageIcon ic;
    File fi=null;
    String tp;
    
    public Livre(String titre, String auteur, String maison, String code, int exemplaire) {
        this.titre = titre;
        this.auteur = auteur;
        this.maison = maison;
        this.code = code;
        this.exemplaire = exemplaire;
    }

    public Livre(String titre, String auteur, String maison, String code, String dateAjout, int exemplaire) {
        this.titre = titre;
        this.auteur = auteur;
        this.maison = maison;
        this.code = code;
        this.dateAjout = dateAjout;
        this.exemplaire = exemplaire;
    }

    public Livre() {
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getMaison() {
        return maison;
    }

    public void setMaison(String maison) {
        this.maison = maison;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(String dateAjout) {
        this.dateAjout = dateAjout;
    }

    public int getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(int exemplaire) {
        this.exemplaire = exemplaire;
    }
  
  
    public static void declarerPerdu(String code,String mat)
    {
         cnx=ConnectionClasse.seConnecter();
        try
        {
             st=cnx.createStatement();
          res=st.executeQuery("SELECT nbreExemplaire FROM livres WHERE code=\""+code+"\"");
          if(res.next())
          {
              int nbre=res.getInt("nbreExemplaire");
              
              if(nbre-1>0)
              {
                 
                   ps=cnx.prepareStatement("UPDATE livres set nbreExemplaire=? WHERE code=?");
                   ps.setInt(1, nbre-1);
                   ps.setString(2, code);
                   ps.executeUpdate();
                   JOptionPane.showMessageDialog(null,"Le nombre d'exemplaire de ce livre a été mise a jour","SyGestBiblio/Information",JOptionPane.INFORMATION_MESSAGE);  
                   Emprunt.Suppression(mat, code);
              }
              else
              {
                  Livre lv=new Livre();
                  lv.setCode(code);
                  lv.Suppression();
                JOptionPane.showMessageDialog(null,"Le dernier exemplaire de ce livre vient d'etre supprimé","SyGestBiblio/Information",JOptionPane.INFORMATION_MESSAGE);  
                  Emprunt.Suppression(mat, code);
              }
               st.close();
              ps.close();
              res.close();
             
          }
           
            
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
            ps=cnx.prepareStatement("INSERT INTO livres(titre,edition,auteur,nbreExemplaire,code) VALUES(?,?,?,?,?)");
            ps.setString(1, titre);
            ps.setString(2, maison);
            ps.setString(3, auteur);
            ps.setInt(4, exemplaire);
            ps.setString(5, code);
           
          
            ps.execute();           
            ps.close();
            
            JOptionPane.showMessageDialog(null,"Enregistrement effectué avec succès","SyGestBiblio/Information",JOptionPane.INFORMATION_MESSAGE);  
        }
        catch(Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Echec de l'enregistrement. Veuillez bien verifier les informqtion et reéssayer","SyGestBiblio/Information",JOptionPane.WARNING_MESSAGE);  
        }
        
    }
    
    
    public void Suppression()
    {
         cnx=ConnectionClasse.seConnecter();
        try
        {
            ps=cnx.prepareStatement("DELETE FROM livres WHERE code=?");
            ps.setString(1, code);
            ps.executeUpdate();
            ps.close();
           JOptionPane.showMessageDialog(null, "Livre supprimé avec succès","SyGestBiblio/Information",JOptionPane.INFORMATION_MESSAGE);
                
        }
        catch(HeadlessException | SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de la suppression, veuillez reéssayer","SyGestBiblio/Information",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
    public void Mise_a_jour()
    {
        cnx=ConnectionClasse.seConnecter();
        try
        {
            ps=cnx.prepareStatement("UPDATE livres set titre=?,edition=?,auteur=?,nbreExemplaire=? WHERE code=?");

            
             ps.setString(1, titre);
            ps.setString(2, maison);
            ps.setString(3, auteur);
            ps.setInt(4, exemplaire);
            ps.setString(5, code);
            
            ps.executeUpdate();           
            ps.close();
            JOptionPane.showMessageDialog(null, "Informations modifiée avec succès","SyGestBiblio/Information",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de la mise a jour des informations, veuillez reéssayer","SyGestBiblio/Information",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
    public void Importer()
    {
        
    }
    
    public void Exporter()
    {
        
    }
    
    public void GeModeleExcel()
    {
        
    }
    
    public static void afficher_tous(DefaultTableModel tbm)
    {
        
        tbm.setRowCount(0);
        try
        {
          cnx=ConnectionClasse.seConnecter();
          st=cnx.createStatement();
          res=st.executeQuery("SELECT * FROM livres ORDER BY titre");
          int num=0;
          while(res.next())
          {
              num++;
             tbm.addRow(new Object[]{num,res.getString("code"),res.getString("titre"),res.getString("auteur"),res.getInt("nbreExemplaire"),res.getDate("dateAjout").toString()});
             
              
          }
          st.close();
          res.close();
          if(num==0)
          {
            JOptionPane.showMessageDialog(null,"Aucune donnée a afficher.Pas de livre enregistré!","Information",JOptionPane.YES_OPTION);  
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
            res=st.executeQuery("SELECT id FROM livres ORDER BY id DESC");
            if(res.next())
            {
                index=res.getInt("id");
            }
            
            num="LVS_N_00000";
            
            String  str=String.valueOf(index);
            index++;
             int len=str.length();
              num=num.substring(0,6-len);
            num+="_"+String.valueOf(index);
           
                    
           st.close();
          res.close();        
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return num;
    }
    
    public static Livre getByCode(String code)
    {
         Livre lv=new Livre();
         cnx=ConnectionClasse.seConnecter();
         try
         {
            st=cnx.createStatement();
            res=st.executeQuery("SELECT * FROM livres WHERE code=\'"+code+"\'");  
            if(res.next())
            {
   //public Adherent(String nom, String prenom, String genre,    String tel, String tel2, String dnaissance, String dadheration, String matricule, boolean emprunt, String numeroPiece, boolean ajour) {

                lv=new Livre(res.getString("titre"),res.getString("auteur"),res.getString("edition"),res.getString("code"),res.getInt("nbreExemplaire"));
            }
            else
            {
                           JOptionPane.showMessageDialog(null,"Données corrompues","SyGestBiblio/Information",JOptionPane.OK_OPTION);  
            }
             st.close();
          res.close();
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
         return lv;
    }
    
    
     public void importer(File fl)
    {
      try
      {
          String filename=fl.getName(),tab[][];
         String ext=filename.substring(filename.indexOf("."));
         System.out.print(ext);
       if(ext.equals(".xls") || ext.equals(" .xls") || ext.equals(".xls "))
       {
           System.out.print(ext);
            Workbook wb=Workbook.getWorkbook(fl.getAbsoluteFile());//recupere un fichier workbook depuis le fichier fl
           Sheet sh=wb.getSheet(0);
           tab=new String[sh.getRows()][sh.getColumns()];
           
            int nl=sh.getRows(),nc=sh.getColumns();
                for(int i=0;i<nl;i++)
                {
                    for(int j=0;j<nc;j++)
                    {
                        Cell ce=sh.getCell(j,i);
                        CellType ceType=ce.getType();
                        if (ceType==CellType.LABEL)
                        {
                         tab[i][j]= ce.getContents();
                        }

                        if (ceType==CellType.NUMBER)
                        {
                         tab[i][j]= ce.getContents();
                        }
                    }
                }

                 
                 
           if(tab[0][0].equals("Titre")  & tab[0][1].equals("Maison d'edition") & tab[0][2].equals("Auteur") & tab[0][4].equals("Code") &tab[0][3].equals("Nombre d'exemplaire") )
           {
              

               for (int i = 1; i < nl; i++)
               {
                 String var_code=chargerNumeroCarte(),
                   var_titre=tab[i][0],
                 var_maison=tab[i][1],
                 var_auteur=tab[i][2];
                 
                  int var_exemplaire=Integer.valueOf(tab[i][3]);

                   



                 Livre lv=new Livre(var_titre,var_auteur,var_maison,var_code,var_exemplaire);
               lv.Enregistrement();
                        

               }
               JOptionPane.showMessageDialog(null, "Importation Reussie" ,"Information",JOptionPane.INFORMATION_MESSAGE);
           }
           else
           {
                                  JOptionPane.showMessageDialog(null, "Echec de l'importation, verifiez les noms des colonnes et leurs ordres" ,"Information",JOptionPane.WARNING_MESSAGE);
           }
       }
       else
       {
                   JOptionPane.showMessageDialog(null, "Echec de l'importqtion. L,extension du fichier doit etre de type .xls" ,"Information",JOptionPane.WARNING_MESSAGE);
       }
        
         
          
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
    }
     
     
     
     
      public void creerExcel()
    {
        try
        {
        //Cette classe permet de creer le fichier excel lesadherents.xsl lesadherents.xls
            WritableWorkbook wtable=Workbook.createWorkbook(new File("C://Users/Public/leslivres.xls"));
        //on se position alors sur la premiere feuille excel c a d l'onglet 1//on cree le premier onglet
            WritableSheet ws=wtable.createSheet("Liste_des_livres", 0);
        //Mettre un font sur l'entete
            WritableFont font=new WritableFont(WritableFont.TIMES,12,WritableFont.BOLD,true,UnderlineStyle.NO_UNDERLINE,Colour.BLUE);
            WritableCellFormat format=new WritableCellFormat(font);
            Label lbl_titre,lbl_edition,lbl_auteur,lbl_code,lbl_exemplaire;
            
            lbl_titre=new Label(0,0,"Titre",format);
            lbl_edition=new Label(1,0,"Maison d'edition",format);
            
            lbl_auteur=new Label(2,0,"Auteur",format);
            lbl_code=new Label(4,0,"Code",format);
            lbl_exemplaire=new Label(3,0,"Nombre d'exemplaire",format);
           
            
            ws.addCell(lbl_titre);
            ws.addCell(lbl_edition);
            ws.addCell(lbl_auteur);
            ws.addCell(lbl_code);
            ws.addCell(lbl_exemplaire);
            
          wtable.write();
          wtable.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
        
        
    }
      
     public static void afficher_le_tableau(DefaultTableModel tbm)
     {
         tbm.setRowCount(0);
        try
        {
          cnx=ConnectionClasse.seConnecter();
          st=cnx.createStatement();
          res=st.executeQuery("SELECT * FROM livres ORDER BY titre");
          int num=0;
          while(res.next())
          {
              num++;
             tbm.addRow(new Object[]{num,res.getString("code"),res.getString("titre"),res.getString("auteur"),res.getString("edition"),res.getInt("nbreExemplaire"),res.getDate("dateAjout").toString()});
             
              
          }
          st.close();
          res.close();
          if(num==0)
          {
            JOptionPane.showMessageDialog(null,"Aucune donnée a afficher.Pas de livre enregistré!","Information",JOptionPane.YES_OPTION);  
          }
        }
        catch(HeadlessException | SQLException e)
        {
            e.printStackTrace();
        }
     }
     
     private static int getNombreExemplaireByCode(String code)
     {
         int n=5;
          cnx=ConnectionClasse.seConnecter();
            try
            {
                st=cnx.createStatement();
                res=st.executeQuery("SELECT nbreExemplaire FROM livres WHERE code=\""+code+"\"");
               if(res.next())
               {
                    n=res.getInt("nbreExemplaire");
               }
               st.close();
               res.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
           
         return n;
     }
     
     public static boolean estDisponible(String lv)
     {
        
            cnx=ConnectionClasse.seConnecter();
            try
            {
                st=cnx.createStatement();
                res=st.executeQuery("SELECT lv FROM emprunts WHERE lv=\""+lv+"\"");
                int nombreEmprunt=0;
                while(res.next())
                {
                  nombreEmprunt++;  
                }
                int nbreEx=getNombreExemplaireByCode(lv);
                st.close();
                res.close();
                return nombreEmprunt<nbreEx;
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
           
         
         return true;
     }
     
     
     public static String getCodeByTitre(String titre)
     {
         String code="";
          cnx=ConnectionClasse.seConnecter();
            try
            {
                st=cnx.createStatement();
                res=st.executeQuery("SELECT * FROM livres WHERE titre=\""+titre+"\"");
               if(res.next())
               {
                   //System.out.print(res.next());
                    code=res.getString("code");
                    
               }
                
                st.close();
                res.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            return code;
     }
     
      public static String[][] getAllTitle()
     {
         String titre[][]={{"",""},};
          cnx=ConnectionClasse.seConnecter();
            try
            {
                st2=cnx.createStatement();
                res2=st2.executeQuery("SELECT * FROM livres ");
                int i=0;
               while(res2.next()& !res2.isLast())
               {
                   //System.out.print(res.next());
                   titre[i][0]=res2.getString("code");
                    titre[i][1]=res2.getString("titre");
                    i++;
               }
               
                st2.close();
                res2.close();
            }
            catch(SQLException e)
            {
              
                e.printStackTrace();
            }
            
            return titre;
     }
     
      public static void exportationListe()
   {
       try
        {
        //Cette classe permet de creer le fichier excel lesadherents.xsl lesadherents.xls
            WritableWorkbook wtable=Workbook.createWorkbook(new File("C://Users/Public/leslivres_sauvegarde.xls"));
        //on se position alors sur la premiere feuille excel c a d l'onglet 1//on cree le premier onglet
            WritableSheet ws=wtable.createSheet("Liste_des_livres", 0);
        //Mettre un font sur l'entete
            WritableFont font=new WritableFont(WritableFont.TIMES,12,WritableFont.BOLD,true,UnderlineStyle.NO_UNDERLINE,Colour.BLUE);
            WritableCellFormat format=new WritableCellFormat(font);
            Label titre,code,edition,auteur,nbre;
            
            //(titre,edition,auteur,nbreExemplaire,code)
            
            code=new Label(0,0,"Code",format);
            titre=new Label(1,0,"Titre",format);
            
            edition=new Label(2,0,"Maison d'edition",format);
           auteur=new Label(3,0,"Auteur",format);
            nbre=new Label(4,0,"Nombre d'exemplaire",format);
             ws.addCell(code);
            ws.addCell(titre);
            ws.addCell(edition);
            ws.addCell(auteur);
            ws.addCell(nbre);
          
           
          
            
           
        
          
           cnx=ConnectionClasse.seConnecter();
                st=cnx.createStatement();
                res=st.executeQuery("SELECT * FROM livres");
                int i=1;
           while(res.next())
           {
                code=new Label(0,i,res.getString("code"),format);
                titre=new Label(1,i,res.getString("titre"),format);

                edition=new Label(2,i,res.getString("edition"),format);
                nbre=new Label(3,i,String.valueOf(res.getInt("nbreExemplaire")),format);
                auteur=new Label(4,i,res.getString("auteur"),format);
               

                ws.addCell(code);
                ws.addCell(titre);
                ws.addCell(edition);
                ws.addCell(auteur);
                ws.addCell(nbre);
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
