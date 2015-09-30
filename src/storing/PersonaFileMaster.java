/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storing;

import core.IReadable;
import core.IStorable;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.Persona;

/**
 *
 * @author Adiel
 */
public class PersonaFileMaster  {

 
    
    public PersonaFileMaster() {
        
    }

  
    public void store(RandomAccessFile raf, Persona res) {
        
         
        try {
            String nt = res.getCedula().substring(0,Math.min(11, res.getCedula().length()));
             while(nt.length()<11)
                nt += " ";
            raf.write(nt.substring(0,11).getBytes());
            nt = res.getNombre().substring(0,Math.min(20, res.getNombre().length()));
            while(nt.length()<20)
                nt += " ";
            raf.write(nt.getBytes());
            raf.writeInt(res.getEdad());
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (IOException ex) {
            Logger.getLogger(PersonaFileMaster.class.getName()).log(Level.SEVERE, null, ex);
        }    
      
    }

  
    public Persona read(RandomAccessFile raf) {
        Persona p = new Persona(null, null, 0);
         try {
            byte[] temp = new byte[11];
            raf.read(temp);
            p.setCedula(new String(temp));
            temp = new byte[20];
            raf.read(temp);
            p.setNombre( new String(temp));
            p.setEdad( raf.readInt() );
            
        } catch (IOException ex) {
            Logger.getLogger(PersonaFileMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return p;
    }
    
    
    
}
