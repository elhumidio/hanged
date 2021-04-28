/*
 * jugadorHumano.java
 *
 * Created on 11 de junio de 2010, 18:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Modelo;


import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno
 */
public class jugadorHumano extends Observable{
    static jugadorHumano miJug;
    private int idJugador;
    private BloqueoPalabra oPalabra;
    private String letraElegida;
    private Hashtable HashLetrasUsadas;
    private Turno t;
    private int aciertos;
    private int intentos;// ver terminar el juego por cant de intentos
    private String stringPalabra;
    private Hashtable tablaLetrasAdivinadas;
    private int largoPalabra;
    private String UltimaLetraJugada;
    private boolean winner;
    private boolean metoca;
    private int cont;
    String arrayLetrasUsadas;




    /** Creates a new instance of jugadorHumano
     * @param tu
     */
    
    public jugadorHumano() {

        this.letraElegida = "";
        this.HashLetrasUsadas = new Hashtable();
        this.idJugador = 1;
        this.aciertos = 0;
        this.tablaLetrasAdivinadas = new Hashtable();
        this.intentos = 0;
        this.setMetoca(false);
        this.cont =0;
        this.addObserver(Turno.getTurno());
        //Cargo la palabra recibida del turno
        this.setStringPalabra(Turno.getTurno().getPalabraBBDD());
        this.arrayLetrasUsadas = "";

    }


 
    
    public void jugarAhorcado() {
      

//            System.out.println("Llegue al metodo run, soy la maquina " + this.getIdJugador());
        

//        while(Turno.getTurno().isHayUnGanador()==false)
//        {
               // this.setLetraElegida("");
                //this.setPalabra(Turno.getTurno().recibirTurno());
                //Turno.getTurno().recibirTurno(this.getIdJugador());
                this.setMetoca(true);
                this.setUltimaLetraJugada(this.getLetraElegida());
                setChanged();
                notifyObservers(this);
                try
                {
                Thread.sleep(1000);
               
                }
                catch (InterruptedException ex)
                {
                Logger.getLogger(jugadorHumano.class.getName()).log(Level.SEVERE, null, ex);
                }

               

                System.out.println("Llegue al turno Soy HUMANO");


               //Obtengo el largo de la palabra
                this.setLargoPalabra(this.getStringPalabra().length());
                System.out.println("El largo de la palabra es " + this.getLargoPalabra());


                
                this.getHashLetrasUsadas().put( this.getCont(), this.getLetraElegida());//uso esto para validar la entrada de letras repetidas
                this.setCont(this.getCont() + 1); //sumo uno a la clave de la hash

                //empiezo la prueba con arrayList
                this.arrayLetrasUsadas += this.getUltimaLetraJugada();
                

                //--------------------------------------------------------

                //me fijo si la letra que saque esta en el string
                for (int i = 0; i < this.getLargoPalabra(); i++)
                {
                    int ifinal = i+1;

//                    if(i== this.largoPalabra)
//                    ifinal =  this.getStringPalabra().length();

                    if (this.getLetraElegida().equals(this.getStringPalabra().substring(i, ifinal)))
                    {
                        
                        this.getTablaLetrasAdivinadas().put(i, this.getLetraElegida());
                        this.setAciertos(this.getAciertos() + 1);
                        System.out.println("Tengo " + getAciertos() + " aciertos");
                        System.out.println("Hasta ahora descubri las siguientes letras: ");
                    }
                }

                //Si la cantidad de aciertos es igual al largo de la palabra el flag winner se vuelve true
                    if (this.getAciertos() == this.getLargoPalabra())
                    {
                        this.setWinner(true);
                        //this.getT().HayUnGanador=true;
                        setChanged();
                        notifyObservers(this);
                        System.out.println("Yo, el jugador Humano Nro " + this.getIdJugador() + " GANE!!!");
                    }

            //Devuelvo el turno y notifico
                    this.setLetraElegida("");
                    Turno.getTurno().devolverTurno();
                    this.setMetoca(false);
                    System.out.println(this.getPalabra());
                    System.out.println("Devolv� el turno");
                    setChanged();
                    notifyObservers(this);



              
            try {
                Thread.sleep(getAciertos() * 300);
            } catch (InterruptedException ex) {
                Logger.getLogger(jugadorHumano.class.getName()).log(Level.SEVERE, null, ex);
            }
   // }//Aqui termina el while



      


    }

public boolean validaLetraAJugar(String letra)
{


    boolean rta = true;
    int flag = 0;
    Enumeration e = this.getHashLetrasUsadas().elements();

    while(e.hasMoreElements())
    {
          String aux = (String) e.nextElement();
          if(aux.equals(letra))
          flag = 1;
          break;
    }
    if(flag == 1)
        rta = false;
    return rta;

}
public boolean validoLetraAJugar(String letra)
{
    boolean rta = true;
    for(int i=0;i<this.arrayLetrasUsadas.length(); i++)
    {
        if(letra.equals(this.arrayLetrasUsadas.substring(i, i+1)))
            rta =false;
    }


    return rta;
}



public void esperar()
{
    Thread.yield();
}

 

    /**
     * @return the t
     */
    public Turno getT() {
        return t;
    }

    /**
     * @param t the t to set
     */
    public void setT(Turno t) {
        this.t = t;
    }

    /**
     * @return the oPalabra
     */
    public BloqueoPalabra getPalabra() {
        return oPalabra;
    }

    /**
     * @param oPalabra the oPalabra to set
     */
    public void setPalabra(BloqueoPalabra oPalabra) {
        this.oPalabra = oPalabra;
    }

    /**
     * @return the stringPalabra
     */
    public String getStringPalabra() {
        return stringPalabra;
    }

    /**
     * @param stringPalabra the stringPalabra to set
     */
    public void setStringPalabra(String stringPalabra) {
        this.stringPalabra = stringPalabra;
    }

    /**
     * @return the idJugador
     */
    public int getIdJugador() {
        return idJugador;
    }

    /**
     * @param idJugador the idJugador to set
     */
    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    /**
     * @return the UltimaLetraJugada
     */
    public String getUltimaLetraJugada() {
        return UltimaLetraJugada;
    }

    /**
     * @param UltimaLetraJugada the UltimaLetraJugada to set
     */
    public void setUltimaLetraJugada(String UltimaLetraJugada) {
        this.UltimaLetraJugada = UltimaLetraJugada;
    }

    public void imprimeHashTableLetrasAdivinadas()
    {
        Enumeration e = this.getTablaLetrasAdivinadas().elements();
        while(e.hasMoreElements())
        {
            Object a = e.nextElement();
            System.out.println(a);
        }
   }

    /**
     * @return the letraElegida
     */
    public String getLetraElegida() {
        return letraElegida;
    }

    /**
     * @param letraElegida the letraElegida to set
     */
    public void setLetraElegida(String letraElegida) {
        this.letraElegida = letraElegida;
    }

    /**
     * @return the HashLetrasUsadas
     */
    public Hashtable getHashLetrasUsadas() {
        return HashLetrasUsadas;
    }

    /**
     * @param HashLetrasUsadas the HashLetrasUsadas to set
     */
    public void setHashLetrasUsadas(Hashtable HashLetrasUsadas) {
        this.HashLetrasUsadas = HashLetrasUsadas;
    }

    /**
     * @return the aciertos
     */
    public int getAciertos() {
        return aciertos;
    }

    /**
     * @param aciertos the aciertos to set
     */
    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

    /**
     * @return the intentos
     */
    public int getIntentos() {
        return intentos;
    }

    /**
     * @param intentos the intentos to set
     */
    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    /**
     * @return the tablaLetrasAdivinadas
     */
    public Hashtable getTablaLetrasAdivinadas() {
        return tablaLetrasAdivinadas;
    }

    /**
     * @param tablaLetrasAdivinadas the tablaLetrasAdivinadas to set
     */
    public void setTablaLetrasAdivinadas(Hashtable tablaLetrasAdivinadas) {
        this.tablaLetrasAdivinadas = tablaLetrasAdivinadas;
    }

    /**
     * @return the largoPalabra
     */
    public int getLargoPalabra() {
        return largoPalabra;
    }

    /**
     * @param largoPalabra the largoPalabra to set
     */
    public void setLargoPalabra(int largoPalabra) {
        this.largoPalabra = largoPalabra;
    }

    /**
     * @return the winner
     */
    public boolean isWinner() {
        return winner;
    }

    /**
     * @param winner the winner to set
     */
    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    /**
     * @return the cont
     */
    public int getCont() {
        return cont;
    }

    /**
     * @param cont the cont to set
     */
    public void setCont(int cont) {
        this.cont = cont;
    }

    /**
     * @return the metoca
     */
    public boolean isMetoca() {
        return metoca;
    }

    /**
     * @param metoca the metoca to set
     */
    public void setMetoca(boolean metoca) {
        this.metoca = metoca;
    }






   
}
