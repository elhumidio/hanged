package Modelo;



import Datos.CDatos;
import java.sql.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author alumno
 */
 public  class Turno extends Observable implements Observer{
    private static Turno miTurno;

    private Connection con;
    private Statement sentencia;
    ResultSet rs;
    private BloqueoPalabra palabra;
    private int ultimoJug;
    
    private String palabraBBDD;
    private boolean HayUnGanador;
    public static int cuentaID;
    public  boolean leTocaAlHumano;
    public int idLetoca;




    private Turno() {//Clase singleton
        this.leTocaAlHumano = false;
   
        BloqueoPalabra p = new BloqueoPalabra();
        this.setPalabra(p);
        this.setHayUnGanador(false);
        cuentaID =1;
        setChanged();
        notifyObservers();
        try {
           int cant = CDatos.getCDatos().cuentaPalabras();
            this.setPalabraBBDD(CDatos.getCDatos().daPalabra(cant));
        } catch (SQLException ex) {
            Logger.getLogger(Turno.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static Turno getTurno()
    {
        if (miTurno == null)
        miTurno = new Turno();
        return miTurno;
    }


    public synchronized int recibirTurno(int id)
   {
        if(cuentaID == 1){
        this.leTocaAlHumano = true;
        
        setChanged();
        notifyObservers(this);
        }
       
        
      System.out.println("id: " + cuentaID);
      while(this.darTurno()!=id)
      {
        
          try
            {
              
              wait();
              
              
            }
            catch (InterruptedException ex)
            {

            }
      }

      
      
      return this.darTurno();

    }

    public synchronized void devolverTurno()
    {
        //this.setPalabra(p);
        cuentaID++;
        this.leTocaAlHumano = false;
        setChanged();
        notifyObservers(this);
        notifyAll();
       // semaforo.release();
    }

    private int darTurno()
    {
   
        //notifyAll();
        if(this.cuentaID > 3) this.cuentaID =1;

        return this.cuentaID;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public Statement getSentencia() {
        return sentencia;
    }

    public void setSentencia(Statement sentencia) {
        this.sentencia = sentencia;
    }

    public BloqueoPalabra getPalabra() {
        return palabra;
    }

    public void setPalabra(BloqueoPalabra palabra) {
        this.palabra = palabra;
    }

    @Override
    public void update(Observable o, Object jug) {

        if(jug.getClass().getSimpleName().equals("jugadorHumano"))
        {
            jugadorHumano j = (jugadorHumano) jug;
            if(j.isWinner())
            {
                this.setHayUnGanador(true);
            }

        }

        if(jug.getClass().getSimpleName().equals("JugadorMaquina"))
        {
            JugadorMaquina ju = (JugadorMaquina) jug;
            if(ju.isWinner())
            {
                this.setHayUnGanador(true);
            }

        }
    }

    /**
     * @return the HayUnGanador
     */
    public boolean isHayUnGanador() {
        return HayUnGanador;
    }

    /**
     * @param HayUnGanador the HayUnGanador to set
     */
    public void setHayUnGanador(boolean HayUnGanador) {
        this.HayUnGanador = HayUnGanador;
    }

    /**
     * @return the palabraBBDD
     */
    public String getPalabraBBDD() {
        return palabraBBDD;
    }

    /**
     * @param palabraBBDD the palabraBBDD to set
     */
    public void setPalabraBBDD(String palabraBBDD) {
        this.palabraBBDD = palabraBBDD;
    }

    /**
     * @return the ultimoJug
     */
    public int getUltimoJug() {
        return ultimoJug;
    }

    /**
     * @param ultimoJug the ultimoJug to set
     */
    public void setUltimoJug(int ultimoJug) {
        this.ultimoJug = ultimoJug;
    }
  

    int incrementarTurno ()
    {
        if(this.cuentaID>2)
            this.cuentaID =0;
        return this.cuentaID;
    } // para asignar turno por ID


}
