/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import classes.ConnectionClasse;
import static fenetres.Accueil.*;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;

/**
 *
 * @author ofilw
 */
public class ImportExcelAdherent 
{
    String nom,prenom,genre,tel1,tel2,numeroPiece,matricule,dnaissance,dadheration,tab[][];
    boolean emprunt,ajour;
    public void importer(File fl)
    {
      try
      {
          String filename=fl.getName();
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

               
                 
           if(tab[0][0].equals("Nom")  & tab[0][1].equals("Prenom") & tab[0][2].equals("Genre") & tab[0][3].equals("Date de naissance") &tab[0][4].equals("Numero CNIB") & tab[0][5].equals("A emprunte") & tab[0][6].equals("A jour des cotisations") & tab[0][7].equals("Telephone 1") & tab[0][8].equals("Telephone 2"))
           {
              

               for (int i = 1; i < nl; i++)
               {
                 matricule=chargerNumeroCarte();
                 nom=tab[i][0];
                 prenom=tab[i][1];
                 genre=tab[i][2];
                  dnaissance=tab[i][3];
                  numeroPiece=tab[i][4];

                    if(tab[i][5].equals("OUI")||tab[i][5].equals("oui")||tab[i][5].equals("Oui"))
                 {
                     emprunt=true;
                 }
                 else
                 {
                    emprunt=false; 
                 }




                  if(tab[i][6].equals("OUI")||tab[i][6].equals("oui")||tab[i][6].equals("Oui"))
                 {
                     ajour=true;
                 }
                 else
                 {
                    ajour=false; 
                 }
                 tel1=tab[i][7];
                 tel2=tab[i][8];




                Adherent ad=new Adherent(nom,prenom,genre,tel1,tel2,dnaissance,matricule,emprunt,numeroPiece,ajour);
                ad.Enregistrement();
                        JOptionPane.showMessageDialog(null, "Importation Reussie" ,"Information",JOptionPane.INFORMATION_MESSAGE);

               }
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
           
                    
                  
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return num;
    }
}
