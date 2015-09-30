/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbf;

import core.IReadable;
import core.IStorable;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adiel
 */
public class Descriptor implements IStorable, IReadable {

    public static final byte FIELD_TYPE_C = (byte) 'C';
    public static final byte FIELD_TYPE_L = (byte) 'L';
    public static final byte FIELD_TYPE_N = (byte) 'N';
    public static final byte FIELD_TYPE_F = (byte) 'F';
    public static final byte FIELD_TYPE_D = (byte) 'D';
    public static final byte FIELD_TYPE_M = (byte) 'M';

    /* Field struct variables start here */
    byte[] fieldName = new byte[11]; /* 0-10*/

    byte dataType;                    /* 11 */

    int reserv1;                      /* 12-15 */

    int fieldLength;                 /* 16 */

    byte decimalCount;                /* 17 */

    short reserv2;                    /* 18-19 */

    byte workAreaId;                  /* 20 */

    short reserv3;                    /* 21-22 */

    byte setFieldsFlag;               /* 23 */

    byte[] reserv4 = new byte[7];    /* 24-30 */

    byte indexFieldFlag;              /* 31 */
    /* Field struct variables end here */

    /* other class variables */
    int nameNullIndex = 0;

    private int desplazamiento;

    @Override
    public void store(RandomAccessFile raf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void read(RandomAccessFile in) {

        try {
            //Descriptor field = new Descriptor();
            byte t_byte = in.readByte(); /* 0 */

            if (t_byte == (byte) 0x0d) {

                //System.out.println( "End of header found");
                return;
            }

            in.readFully(this.fieldName, 1, 10);	/* 1-10 */

            this.fieldName[0] = t_byte;

            for (int i = 0; i < this.fieldName.length; i++) {

                if (this.fieldName[i] == (byte) 0) {

                    this.nameNullIndex = i;
                    break;
                }
            }

            this.dataType = in.readByte(); /* 11 */

            this.reserv1 = Utils.readLittleEndianInt(in); /* 12-15 */

            this.fieldLength = in.readUnsignedByte();  /* 16 */

            this.decimalCount = in.readByte(); /* 17 */

            this.reserv2 = Utils.readLittleEndianShort(in); /* 18-19 */

            this.workAreaId = in.readByte(); /* 20 */

            this.reserv2 = Utils.readLittleEndianShort(in); /* 21-22 */

            this.setFieldsFlag = in.readByte(); /* 23 */

            in.readFully(this.reserv4); /* 24-30 */

            this.indexFieldFlag = in.readByte(); /* 31 */

            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (IOException ex) {
            Logger.getLogger(Descriptor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the desplazamiento
     */
    public int getDesplazamiento() {
        return desplazamiento;
    }

    /**
     * @param desplazamiento the desplazamiento to set
     */
    public void setDesplazamiento(int desplazamiento) {
        this.desplazamiento = desplazamiento;
    }

}
