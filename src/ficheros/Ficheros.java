/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficheros;

import dbf.DBFFile;
import java.io.File;

/**
 *
 * @author Adiel
 */
public class Ficheros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        DBFFile ff = new DBFFile(new File("Personas.dbf"));
        ff.load();
        final NewJFrame forma = new NewJFrame();
        forma.getjTable1().setModel(ff);
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                forma.setVisible(true);
            }
        });
        
    }
    
}
