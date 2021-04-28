/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Datos;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;


/**
 *
 * @author mondiolo
 */
    public class CDatos {   //Clase Singleton
    private static CDatos misDatos;
    private Connection con;
    private Statement sentencia;
    private ResultSet rs;

    private CDatos()
    {}

    public static CDatos getCDatos()
    {
        if(misDatos == null)
            misDatos = new CDatos();
        return misDatos;
    }


    private void Conectar()
    {
        try {
            setCon((Connection) DriverManager.getConnection("jdbc:mysql://localhost/palabras", "root", ""));
        } catch (SQLException ex) {
           System.out.println("No me pude conectar...");
        }
    }

   public int cuentaPalabras() throws SQLException
   {
        Conectar();
            this.setSentencia((Statement) getCon().createStatement());

            String query1 = "select count(*)from palabras";
            setRs(sentencia.executeQuery(query1));

            int cant=0;
            while(getRs().next())
            {
              cant =Integer.parseInt(getRs().getString(1));

            }
            return cant;
   }
    public String daPalabra(int cant) throws SQLException
    {
            
            
            Random r = new Random();
            int randomInt= r.nextInt(cant-1)+1;

            String query2 = "select palabra from palabras where id=" + randomInt;
            this.setRs(this.getSentencia().executeQuery(query2));
            String palabraRec = null;

            while(getRs().next())
            {
               palabraRec = getRs().getString(1);
            }

            //this.palabra.setPalabra(palabraRec);
            
            return palabraRec;
    }

    /**
     * @return the sentencia
     */
    public Statement getSentencia() {
        return sentencia;
    }

    /**
     * @param sentencia the sentencia to set
     */
    public void setSentencia(Statement sentencia) {
        this.sentencia = sentencia;
    }

    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }

    /**
     * @param con the con to set
     */
    public void setCon(Connection con) {
        this.con = con;
    }

    /**
     * @return the rs
     */
    public ResultSet getRs() {
        return rs;
    }

    /**
     * @param rs the rs to set
     */
    public void setRs(ResultSet rs) {
        this.rs = rs;
    }











}
