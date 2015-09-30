/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Laboratorio 3
 */
public class JDBCManager {

    public static String usuario = "root";
    public static String clave = "";
    public static String basedatos = "midb";

    private JDBCManager __instance = new JDBCManager();

    private JDBCManager() {

    }

    public static int insertgeneric(String tablename, String[] fieldnames, Class[] fieldtypes, String[] fieldvalues) {
        try {
            // TODO code application logic here

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/" + basedatos, usuario, clave);
            String query = "insert into " + tablename + " ";
            String fn = "(";
            String fv = "(";
            int i = 0;
            for (String fieldname : fieldnames) {
                if (i != 0) {
                    fn += ",";
                    fv += ",";
                }
                fn += fieldname;
                fv += "?";

                i++;
            }

            fn += ") ";
            fv += ") ";

            query += fn + " values " + fv;
            PreparedStatement st = con.prepareStatement(query);

            for (i = 0; i < fieldnames.length; i++) {
                Class fieldtype = fieldtypes[i];
                String fieldvalue = fieldvalues[i];
                if (fieldtype.equals(String.class)) {
                    st.setString(i + 1, fieldvalue);
                } else if (fieldtype.equals(int.class)) {
                    st.setInt(i + 1, Integer.parseInt(fieldvalue));
                }
            }

            return st.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public static int insertgeneric(String tablename, String[] fieldnames, Class[] fieldtypes, String[] fieldvalues, ArrayList<String> outGeneratedKeys) {
        try {
            // TODO code application logic here

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/" + basedatos, usuario, clave);
            String query = "insert into " + tablename + " ";
            String fn = "(";
            String fv = "(";
            int i = 0;
            for (String fieldname : fieldnames) {
                if (i != 0) {
                    fn += ",";
                    fv += ",";
                }
                fn += fieldname;
                fv += "?";

                i++;
            }

            fn += ") ";
            fv += ") ";

            query += fn + " values " + fv;
            PreparedStatement st = con.prepareStatement(query);

            for (i = 0; i < fieldnames.length; i++) {
                Class fieldtype = fieldtypes[i];
                String fieldvalue = fieldvalues[i];
                if (fieldtype.equals(String.class)) {
                    st.setString(i + 1, fieldvalue);
                } else if (fieldtype.equals(int.class)) {
                    st.setInt(i + 1, Integer.parseInt(fieldvalue));
                }
            }

            int res = st.executeUpdate();

            if (res > 0) {
                int autoIncKeyFromApi = -1;

                ResultSet rs = st.getGeneratedKeys();
                ResultSetMetaData rsMetaData = rs.getMetaData();

                int numberOfColumns = rsMetaData.getColumnCount();
                if (rs.next()) {
                    for (int j = 0; j < numberOfColumns; j++) {
                        autoIncKeyFromApi = rs.getInt(j + 1);
                        outGeneratedKeys.add(String.valueOf(autoIncKeyFromApi));
                    }

                }
            }

            return res;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public static int deletegeneric(String tablename, String[] fieldnames, Class[] fieldtypes, String[] fieldvalues) {
        try {
            // TODO code application logic here

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/" + basedatos, usuario, clave);
            String query = "delete from " + tablename + " where ";
            String condicion = " ";
            int i = 0;
            for (String fieldname : fieldnames) {
                if (i != 0) {
                    condicion += " and ";
                }
                condicion += " " + fieldname + "= ? ";

                i++;
            }

            query += condicion;
            PreparedStatement st = con.prepareStatement(query);

            for (i = 0; i < fieldnames.length; i++) {
                Class fieldtype = fieldtypes[i];
                String fieldvalue = fieldvalues[i];
                if (fieldtype.equals(String.class)) {
                    st.setString(i + 1, fieldvalue);
                } else if (fieldtype.equals(int.class)) {
                    st.setInt(i + 1, Integer.parseInt(fieldvalue));
                }
            }

            return st.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public static int updategeneric(String tablename, String[] whereids, Class[] whereClass, String[] wherefieldvalues, String[] fieldnames, Class[] fieldtypes, String[] fieldvalues) {
        try {
            // TODO code application logic here

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/" + basedatos, usuario, clave);
            String query = "update   " + tablename + " set ";
            String temp = " ";
            int i = 0;
            for (String fieldname : fieldnames) {
                if (i != 0) {
                    temp += " , ";
                }
                temp += " " + fieldname + "= ? ";

                i++;
            }

            query += temp + " where ";

            i = 0;
            for (String fieldname : whereids) {
                if (i != 0) {
                    query += " and ";
                }
                query += " " + fieldname + "= ? ";

                i++;
            }

            PreparedStatement st = con.prepareStatement(query);
            int pp = 1;
            for (i = 0; i < fieldnames.length; i++) {
                Class fieldtype = fieldtypes[i];
                String fieldvalue = fieldvalues[i];
                if (fieldtype.equals(String.class)) {
                    st.setString(pp++, fieldvalue);
                } else if (fieldtype.equals(int.class)) {
                    st.setInt(pp++, Integer.parseInt(fieldvalue));
                }
            }

            for (i = 0; i < whereids.length; i++) {
                Class fieldtype = whereClass[i];
                String fieldvalue = wherefieldvalues[i];
                if (fieldtype.equals(String.class)) {
                    st.setString(pp++, fieldvalue);
                } else if (fieldtype.equals(int.class)) {
                    st.setInt(pp++, Integer.parseInt(fieldvalue));
                }
            }

            return st.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;

    }

    public static ArrayList<String[]> getlistgeneric(String tablename, String[] fieldnames, Class[] fieldtypes) {
        ArrayList<String[]> res = new ArrayList<>();
        try {
            // TODO code application logic here

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/" + basedatos, usuario, clave);

            String fn = "";
            int i = 0;
            for (String fieldname : fieldnames) {
                if (i != 0) {
                    fn += ",";
                }
                fn += fieldname;

                i++;
            }

            String query = "select  " + fn + " from " + tablename + " ";

            PreparedStatement st = con.prepareStatement(query);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String[] row = new String[fieldnames.length];
                for (i = 0; i < fieldnames.length; i++) {
                    Class fieldtype = fieldtypes[i];
                    String fieldname = fieldnames[i];
                    // String fieldvalue = fieldvalues[i];

                    if (fieldtype.equals(String.class)) {
                        row[i] = rs.getString(fieldname);
                    } else if (fieldtype.equals(int.class)) {
                        row[i] = String.valueOf(rs.getInt(fieldname));
                    }
                }//for

                res.add(row);

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }

    public static String[] getrowgeneric(String tablename, String[] fieldnames, Class[] fieldtypes, String[] fieldnamesWhere, Class[] fieldtypesWhere, String[] fieldvaluesWhere) {
        String[] res = null;
        try {
            // TODO code application logic here

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/" + basedatos, usuario, clave);

            String fn = "";
            int i = 0;
            for (String fieldname : fieldnames) {
                if (i != 0) {
                    fn += ",";
                }
                fn += fieldname;

                i++;
            }

            String fnW = "";
            i = 0;
            for (String fieldname : fieldnamesWhere) {
                if (i != 0) {
                    fnW += " and ";
                }
                fnW += fieldname + "=? ";

                i++;
            }

            String query = "select  " + fn + " from " + tablename + " where " + fnW;

            PreparedStatement st = con.prepareStatement(query);

            for (i = 0; i < fieldtypesWhere.length; i++) {
                Class fieldtype = fieldtypesWhere[i];
                String fieldvalue = fieldvaluesWhere[i];
                // String fieldvalue = fieldvalues[i];

                if (fieldtype.equals(String.class)) {
                    //res[i] = rs.getString(fieldname);
                    st.setString(i + 1, fieldvalue);
                } else if (fieldtype.equals(int.class)) {
                    // res[i] = String.valueOf(rs.getInt(fieldname));
                    st.setInt(i + 1, Integer.parseInt(fieldvalue));
                }
            }//for

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                res = new String[fieldnames.length];
                for (i = 0; i < fieldnames.length; i++) {
                    Class fieldtype = fieldtypes[i];
                    String fieldname = fieldnames[i];
                    // String fieldvalue = fieldvalues[i];

                    if (fieldtype.equals(String.class)) {
                        res[i] = rs.getString(fieldname);
                    } else if (fieldtype.equals(int.class)) {
                        res[i] = String.valueOf(rs.getInt(fieldname));
                    }
                }//for

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }

    public static ArrayList<String[]> PreparedQuery(String sql, Class[] Paramstypes, String[] ParamsValues, Class[] Resulttypes) {
        ArrayList<String[]> res = new ArrayList<>();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/" + basedatos, usuario, clave);

            PreparedStatement st = con.prepareStatement(sql);

            for (int i = 0; i < Paramstypes.length; i++) {
                Class fieldtype = Paramstypes[i];
                String fieldvalue = ParamsValues[i];
                // String fieldvalue = fieldvalues[i];

                if (fieldtype.equals(String.class)) {
                    //res[i] = rs.getString(fieldname);
                    st.setString(i + 1, fieldvalue);
                } else if (fieldtype.equals(int.class)) {
                    // res[i] = String.valueOf(rs.getInt(fieldname));
                    st.setInt(i + 1, Integer.parseInt(fieldvalue));
                }
            }//for

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String[] row = new String[Resulttypes.length];
                for (int i = 0; i < Resulttypes.length; i++) {
                    Class fieldtype = Resulttypes[i];
//                    String fieldname = Resultnames[i];
                    // String fieldvalue = fieldvalues[i];

                    if (fieldtype.equals(String.class)) {
                        row[i] = rs.getString(i + 1);
                    } else if (fieldtype.equals(int.class)) {
                        row[i] = String.valueOf(rs.getInt(i + 1));
                    }
                }//for
                res.add(row);

            }

            return res;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;

    }

    
     public static int PreparedExecute(String sql, Class[] Paramstypes, String[] ParamsValues) {
        ArrayList<String[]> res = new ArrayList<>();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/" + basedatos, usuario, clave);

            PreparedStatement st = con.prepareStatement(sql);

            for (int i = 0; i < Paramstypes.length; i++) {
                Class fieldtype = Paramstypes[i];
                String fieldvalue = ParamsValues[i];
                // String fieldvalue = fieldvalues[i];

                if (fieldtype.equals(String.class)) {
                    //res[i] = rs.getString(fieldname);
                    st.setString(i + 1, fieldvalue);
                } else if (fieldtype.equals(int.class)) {
                    // res[i] = String.valueOf(rs.getInt(fieldname));
                    st.setInt(i + 1, Integer.parseInt(fieldvalue));
                }
            }//for

            int affected = st.executeUpdate();
            return affected;            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;

    }

    
    
    public JDBCManager getInstance() {
        if (__instance == null) {
            __instance = new JDBCManager();
        }
        return __instance;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @return the basedatos
     */
    public String getBasedatos() {
        return basedatos;
    }

    /**
     * @param basedatos the basedatos to set
     */
    public void setBasedatos(String basedatos) {
        this.basedatos = basedatos;
    }
}
