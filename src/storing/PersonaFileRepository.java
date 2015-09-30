/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storing;

import core.IRepoPersona;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.Persona;

/**
 *
 * @author Adiel
 */
public class PersonaFileRepository implements IRepoPersona {
    File f;

    public PersonaFileRepository(File f) {
        this.f = f;
    }
    
    
    
    @Override
    public void add(Persona p){
    
        try {
            RandomAccessFile raf = new RandomAccessFile(f, "rw");
            raf.seek(raf.length());
            PersonaFileMaster s = new PersonaFileMaster();
            s.store(raf,p);
            raf.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PersonaFileRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PersonaFileRepository.class.getName()).log(Level.SEVERE, null, ex);
        }       
        
    }
    
    @Override
    public ArrayList<Persona> getList(){
        ArrayList<Persona> resultado = new ArrayList<>();
        try {
            RandomAccessFile raf = new RandomAccessFile(f, "r");
            PersonaFileMaster s = new PersonaFileMaster();
            while(raf.getFilePointer()< raf.length()){
                resultado.add(s.read(raf));
            }            
            raf.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PersonaFileRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PersonaFileRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    @Override
    public boolean edit(String cedula, Persona p){
        boolean detener = false;
        int tamanoBloque = 35;
         try {
            RandomAccessFile raf = new RandomAccessFile(f, "rws");
            PersonaFileMaster s = new PersonaFileMaster();
            int pos=0;
            while(!detener  && raf.getFilePointer()< raf.length()){
                Persona temp = s.read(raf);
                pos+=tamanoBloque;
                if(temp.getCedula().trim().equals(cedula)){                
                    detener=true;
                    pos-=tamanoBloque;
                    raf.seek(pos);
                    s.store(raf,p);
                }
            }            
            raf.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PersonaFileRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PersonaFileRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return detener;
    }

    @Override
    public Persona getByCedula(String cedula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteByCedula(String cedula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
