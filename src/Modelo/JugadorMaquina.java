/*
 * JugadorMaquina.java
 *
 * Created on 11 de junio de 2010, 18:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Modelo;

import java.lang.String;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Random;



/**
 *
 * @author alumno
 */
public class JugadorMaquina extends Observable implements Runnable{
    private int idJugadorMaquina;
    private BloqueoPalabra oPalabra;
    private Turno t;
    static int id =2;
    private String stringPalabra;
    private Hashtable HashLetrasUsadas;
    private String alfabeto;
    private String UltimaLetraJugada;
   // String[] ArrayPalabraAdivinada;
    private Hashtable tablaLetrasAdivinadas;
  
    private int aciertos;
    private int intentos;
    private int largoPalabra;
    private boolean winner;
  


   
    Hashtable Hashalfabeto;
    
    /** Creates a new instance of JugadorMaquina
     * @param id
     * @param tu
     */
    public JugadorMaquina() {
    //inicializo el array de letras
        this.alfabeto = "abcdefghijklmnopqrstuvwxyz";
        this.HashLetrasUsadas = new Hashtable();
       // this.Hashalfabeto = new Hashtable();
       
        this.setIdJugadorMaquina(id++);
        this.aciertos = 0;
        this.tablaLetrasAdivinadas = new Hashtable();
        this.intentos = 0;
        this.setStringPalabra(Turno.getTurno().getPalabraBBDD());
        this.addObserver(Turno.getTurno());

       
    }



    @Override
    public void run()
    {
       
       try
       {
          
            System.out.println("Llegue al metodo run, soy la maquina " + this.getIdJugadorMaquina());
            
               while(Turno.getTurno().isHayUnGanador()==false)
               {
                
               // this.setPalabra(Turno.getTurno().recibirTurno());
               int turno= Turno.getTurno().recibirTurno(this.idJugadorMaquina);
               if(Turno.getTurno().isHayUnGanador()){
                    Turno.getTurno().devolverTurno();
                    setChanged();
                    notifyObservers(this);
                }
                else{
              

                setChanged();
                notifyObservers(this);
                Thread.sleep(1000);


                
                //obtengo el largo de la palabra en juego y armo un array de ese largo
                this.setLargoPalabra(this.getStringPalabra().length());
                System.out.println("El largo de la palabra es " + this.getLargoPalabra());
                int indiceletra;
            

            //obtener una letra randomizada del alfabeto y probar si esta en oPalabra
            //no la puedo repetir, asi que la tengo que sacar de mi alfabeto
            //si acerte, ponerla en el string que muestra mis aciertos, si no, sumar un error.
                Random r = new Random();
                indiceletra = r.nextInt(this.getAlfabeto().length()-2);

                //obtuve la letra jugada
                String letraJugada=this.getAlfabeto().substring(indiceletra,indiceletra+1);

                System.out.println("La letra seleccionada al azar es " + letraJugada);
        
               // this.Hashalfabeto.remove(r);


            //La saco del alfabeto
            //-------------------------------------------------------------------
                quitoLetraUsada(letraJugada);
            
                System.out.println(this.getAlfabeto());
                //muestraAlfabetoRestante();


            //---------------------------------------------------------------------
            //Sumo un intento
                this.setIntentos(this.getIntentos() + 1);
            //La guardo en el el Jugador
                this.setUltimaLetraJugada(letraJugada);
 
                for (int i = 0; i < this.getLargoPalabra(); i++)
                {
                    int ifinal = i+1;

//                    if(i== this.largoPalabra)
//                    ifinal =  this.getStringPalabra().length();

                    System.out.println(this.getStringPalabra().substring(i, ifinal));
                
                    if (letraJugada.equals(this.getStringPalabra().substring(i, ifinal)))
                    {
                      
                        this.getTablaLetrasAdivinadas().put(i,letraJugada);
                        this.setAciertos(this.getAciertos() + 1);
                        System.out.println("Tengo " + getAciertos() + " aciertos");
                        System.out.println("Hasta ahora descubri las siguientes letras: ");
                   
                        imprimeHashTableLetrasAdivinadas();
                        System.out.println("-------------------------------------------");

                    }
                }
            //Si la cantidad de aciertos es igual al largo de la palabra el flag winner se vuelve true
                    if (this.getAciertos() == this.getLargoPalabra())
                    {
                        setWinner(true);
                        //this.getT().HayUnGanador=true;
                        setChanged();
                        notifyObservers(this);
                        System.out.println("Yo, el jugador automatizado Nro " + this.getIdJugadorMaquina() + " GANE!!!");
                    }

            //Devuelvo el turno y notifico
                    Turno.getTurno().devolverTurno();
                    System.out.println(this.getPalabra());
                    System.out.println("Devolv� el turno");
                    setChanged();
                    notifyObservers(this);
                    Thread.sleep(getAciertos() * 300);
               }
         
               }//Fin del if -Else
                  
        } catch (InterruptedException ex) {
            
        }
      


    }

 private void quitoLetraUsada(String letraJugada)
 {
    String nuevoAlfabeto = "";
        this.setAlfabeto(this.getAlfabeto().replace(letraJugada.charAt(0), '0'));
                
              for(int i=0; i<this.getAlfabeto().length(); i++)
              {
                   if(this.getAlfabeto().charAt(i) != '0')
                   nuevoAlfabeto += this.getAlfabeto().charAt(i);
              }
                this.setAlfabeto(nuevoAlfabeto);
}
/*private void muestraAlfabetoRestante()
{
   Enumeration e = this.Hashalfabeto.elements();
   String a;
   while(e.hasMoreElements())
   {
       a=   (String) e.nextElement();
       System.out.println(a.toString());
   }
                
}*/

 /*private void quitoLetraUsada(String letraJugada)
 {
     int index = this.alfabeto.indexOf(letraJugada);

     this.Hashalfabeto.remove(index);

    
}*/

/*private boolean verificaLetraEnPalabra(String l)
{
    boolean rta = false;
    String a="";
    Enumeration e = this.StringPalabraHash.elements();
    while(e.hasMoreElements())
    {
        if(a == (String) e.nextElement())
        rta = true;
    }
    
    return rta;
    
}*/


    /**
     * @param idJugadorMaquina the idJugadorMaquina to set
     */
    public void setIdJugadorMaquina(int idJugadorMaquina) {
        this.idJugadorMaquina = idJugadorMaquina;
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
    public void setPalabra(BloqueoPalabra palabra) {
        this.oPalabra = palabra;
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
     * @param stringPalabra the stringPalabra to set
     */


    /**
     * @return the stringPalabra
     */
    public String getPalabraEnJuego() {
        return getStringPalabra();
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

    /**
     * @return the oPalabra
     */
  

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
     * @param oPalabra the oPalabra to set


    /**
     * @return the ArrayPalabraAdivinada
     */
    /*public void imprimeArrayLetrasAdivinadas()
    {
        for(int x=0;x<this.largoPalabra;x++)
        {
            if(this.ArrayPalabraAdivinada[x]!= "0")
            System.out.println(this.ArrayPalabraAdivinada[x] + " - ");
       }
    }*/
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
     * @return the idJugadorMaquina
     */
    public int getIdJugadorMaquina() {
        return idJugadorMaquina;
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
     * @return the alfabeto
     */
    public String getAlfabeto() {
        return alfabeto;
    }

    /**
     * @param alfabeto the alfabeto to set
     */
    public void setAlfabeto(String alfabeto) {
        this.alfabeto = alfabeto;
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
}
