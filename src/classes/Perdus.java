/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import static fenetres.Accueil.cnx;
import static fenetres.Accueil.ps;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class Perdus 
{
    private int id;private String code_livre;

    public Perdus() {
    }

    public Perdus(int id, String code_livre) {
        this.id = id;
        this.code_livre = code_livre;
    }

    public Perdus(String code_livre) {
        this.code_livre = code_livre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode_livre() {
        return code_livre;
    }

    public void setCode_livre(String code_livre) {
        this.code_livre = code_livre;
    }
    
    public void Enregistrement()
    {
         cnx=ConnectionClasse.seConnecter();
        try
        {
            ps=cnx.prepareStatement("INSERT INTO livre_perdus(code_livre) VALUES(?)");
            ps.setString(1,this.code_livre);
            
            ps.execute();           
            ps.close();
            
            
          ps.close();
            JOptionPane.showMessageDialog(null,"Declaré(s) comme perdus","SyGestBiblio/Information",JOptionPane.INFORMATION_MESSAGE);  
        }
        catch(Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Une erreur est survernue.","SyGestBiblio/Information",JOptionPane.WARNING_MESSAGE);  
        }
    }
}
