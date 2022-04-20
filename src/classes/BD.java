/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class BD 
{
    public static void sauvegarder()
    {
        //importation de la listes des adherents
        Adherent.exportationListe();
        Livre.exportationListe();
       Emprunt.exportationListe();
       JOptionPane.showMessageDialog(null, "Exportation Reussie" ,"Information",JOptionPane.INFORMATION_MESSAGE);

    }
    
    public static void importerTable(File table)
    {
        
    }
    public static void exporterTable(String tableName)
    {
        
    }
}
