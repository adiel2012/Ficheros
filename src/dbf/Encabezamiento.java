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
public class Encabezamiento implements IStorable, IReadable {

    //  http://www.whitetown.com/es/misc/dbf/
    static final byte SIG_DBASE_III = (byte) 0x03;
    /* DBF structure start here */

    private byte signature;              /* 0 */

    private byte year;                   /* 1 */

    private byte month;                  /* 2 */

    private byte day;                    /* 3 */

    private int numberOfRecords;         /* 4-7 */

    short headerLength;          /* 8-9 */

    short recordLength;          /* 10-11 */

    short reserv1;               /* 12-13 */

    byte incompleteTransaction;  /* 14 */

    byte encryptionFlag;         /* 15 */

    int freeRecordThread;        /* 16-19 */

    int reserv2;                 /* 20-23 */

    int reserv3;                 /* 24-27 */

    byte mdxFlag;                /* 28 */

    byte languageDriver;         /* 29 */

    short reserv4;               /* 30-31 */

   
    byte terminator1;            /* n+1 */


//    private int cantidad_descriptores;
    @Override
    public void store(RandomAccessFile raf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void read(RandomAccessFile dataInput) {

        try {
//            dataInput.seek(0);

            setSignature(dataInput.readByte()); /* 0 */

            setYear(dataInput.readByte());      /* 1 */

            setMonth(dataInput.readByte());     /* 2 */

            setDay(dataInput.readByte());       /* 3 */

            setNumberOfRecords(Utils.readLittleEndianInt(dataInput)); /* 4-7 */

            headerLength = Utils.readLittleEndianShort(dataInput); /* 8-9 */

            recordLength = Utils.readLittleEndianShort(dataInput); /* 10-11 */

            reserv1 = Utils.readLittleEndianShort(dataInput);      /* 12-13 */

            incompleteTransaction = dataInput.readByte();           /* 14 */

            encryptionFlag = dataInput.readByte();                  /* 15 */

            freeRecordThread = Utils.readLittleEndianInt(dataInput); /* 16-19 */

            reserv2 = dataInput.readInt();                            /* 20-23 */

            reserv3 = dataInput.readInt();                            /* 24-27 */

            mdxFlag = dataInput.readByte();                           /* 28 */

            languageDriver = dataInput.readByte();                    /* 29 */

            reserv4 = Utils.readLittleEndianShort(dataInput);        /* 30-31 */


//            dataInput.seek(32);
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (IOException ex) {
            Logger.getLogger(Encabezamiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the cantidad_descriptores
     */
    public int getCantidad_descriptores() {
        return headerLength / 32 - 1;
    }

    /**
     * @return the signature
     */
    public byte getSignature() {
        return signature;
    }

    /**
     * @param signature the signature to set
     */
    public void setSignature(byte signature) {
        this.signature = signature;
    }

    /**
     * @return the year
     */
    public byte getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(byte year) {
        this.year = year;
    }

    /**
     * @return the month
     */
    public byte getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(byte month) {
        this.month = month;
    }

    /**
     * @return the day
     */
    public byte getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(byte day) {
        this.day = day;
    }

    /**
     * @return the numberOfRecords
     */
    public int getNumberOfRecords() {
        return numberOfRecords;
    }

    /**
     * @param numberOfRecords the numberOfRecords to set
     */
    public void setNumberOfRecords(int numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }



}
