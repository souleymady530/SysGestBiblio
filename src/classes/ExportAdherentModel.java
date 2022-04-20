/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.File;
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
public class ExportAdherentModel
{
    public void creerExcel()
    {
        try
        {
        //Cette classe permet de creer le fichier excel lesadherents.xsl lesadherents.xls
            WritableWorkbook wtable=Workbook.createWorkbook(new File("C://Users/Public/lesadherents.xls"));
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
          wtable.write();
          wtable.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
        
        
    }
    
}
