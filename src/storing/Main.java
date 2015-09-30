/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.Persona;

/**
 *
 * @author Adiel
 */
public class Main {
    public static void main(String[] args){
         
         File file = new File("personas.txt");
         if(file.exists()==false){
            try { 
                 file.createNewFile();
             } catch (IOException ex) {
                 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         PersonaFileRepository repo = new PersonaFileRepository(file);
         
         Persona p = new Persona("222", "Adiel66", 36);
         
         //repo.Save(p);
         repo.edit("4353333333", p);
         
         ArrayList<Persona> lista = repo.getList();
         
         for (Persona persona : lista) {
            System.out.println(persona.getCedula()+ "  "+ persona.getNombre()+ " "+persona.getEdad());
        }
    
    }
}
