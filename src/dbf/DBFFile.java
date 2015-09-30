/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author Adiel
 */
public class DBFFile implements TableModel {
//  http://www.fpress.com/revista/Num9906/Jun99.htm
    File f;
    Encabezamiento encab = new Encabezamiento();
    ArrayList<Descriptor> descriptores = new ArrayList<>();
    

    public DBFFile(File f) {
        this.f = f;
    }

    public void load() {
        
       
        try {
            System.out.println(f.exists());
            
            RandomAccessFile raf = new RandomAccessFile(f, "r");
            encab.read(raf);
            int desplazamiento = 0;
            for (int i = 0; i < encab.getCantidad_descriptores(); i++) {
                Descriptor desc = new Descriptor();
                desc.read(raf);
                desc.setDesplazamiento(desplazamiento);
                descriptores.add(desc);
                desplazamiento += desc.fieldLength;
            }

            raf.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBFFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DBFFile.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public int getRowCount() {
        return encab.getNumberOfRecords();
    }

    @Override
    public int getColumnCount() {
        return descriptores.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
      
         return new String( descriptores.get(columnIndex).fieldName);
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
       return String.class;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
       return false;
     //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        try {
            int pos = 1+ encab.headerLength+ rowIndex*(encab.recordLength)    +descriptores.get(columnIndex).getDesplazamiento()  ;
            byte[] buffer = new byte[descriptores.get(columnIndex).fieldLength];
            
            RandomAccessFile raf = new RandomAccessFile(f, "r");
            raf.seek(pos);
            raf.read(buffer);
            raf.close();
            return new String(buffer);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBFFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DBFFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
