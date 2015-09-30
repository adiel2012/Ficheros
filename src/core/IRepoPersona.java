/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;
import pojo.Persona;

/**
 *
 * @author acastano
 */
public interface IRepoPersona {

    void add(Persona p);

    boolean edit(String cedula, Persona p);

    ArrayList<Persona> getList();
    
    Persona getByCedula(String cedula);
    
    boolean deleteByCedula(String cedula);
    
}
