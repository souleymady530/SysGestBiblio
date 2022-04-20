/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.*;

/**
 *
 * @author USER
 */
public class ConnectionClasse
{
   static String user=null;
    static String pass=null;
    static java.sql.Connection con=null;
    public static Connection seConnecter()
    {
        user="root";
        pass="";
        if(con==null)
        {
           try
           {
                con=DriverManager.getConnection("jdbc:mysql://localhost:3308/sygestbiblio_data?characterEncoding=utf8", user, pass);
                
           }
           catch(Exception e)
           {
               JOptionPane.showMessageDialog(null, "Erreur de connexion a la base de donnée. Referer vous a la documentation pour corriger", "SyGestBiblio/Information",JOptionPane.INFORMATION_MESSAGE);
               e.printStackTrace();
           }
        }
        return con;
    }
}
