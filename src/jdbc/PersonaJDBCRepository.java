/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import core.IRepoPersona;
import java.util.ArrayList;
import jdbc.core.JDBCManager;
import pojo.Persona;

/**
 *
 * @author acastano
 */
public class PersonaJDBCRepository implements IRepoPersona {

    @Override
    public void add(Persona p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean edit(String cedula, Persona p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Persona> getList() {
        ArrayList<String[]> data = JDBCManager.getlistgeneric("persona", new String[]{}, new Class[]{String.class, String.class, int.class});
        ArrayList<Persona> res = new ArrayList<>();
        for (String[] personadata : data) {
            Persona p = new Persona(personadata[0], personadata[1], Integer.parseInt(personadata[2]));
            res.add(p);
        }
        return res;
    }

    @Override
    public Persona getByCedula(String cedula) {
        String[] row = JDBCManager.getrowgeneric("persona", new String[]{"cedula", "nombre", "edad"}, new Class[]{String.class, String.class, int.class}, new String[]{"cedula"}, new Class[]{int.class}, new String[]{cedula});
        Persona instance = new Persona(row[0], row[1], Integer.parseInt(row[2]));
        return instance;
    }

    @Override
    public boolean deleteByCedula(String cedula) {
        return JDBCManager.deletegeneric("persona", new String[]{"cedula"}, new Class[]{String.class}, new String[]{cedula})!=0;
 
    }

}
