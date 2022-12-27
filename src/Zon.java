import solar.util.Vector3D;
import java.math.BigDecimal;

/**
 * Deze klasse stelt de zon voor. De zon wordt aangemaakt door het tijdstip en de locatie van de persoon in te geven.
 * Daarna kan zij haar coordinaten retourneren.
 * 
 * @author Jorrit Vander Mynsbrugge, Wim Van Damme, Antoine Vandermeersch
 * @version 2006-05-11
 */

public class Zon
{
    private double dagNummer;
    private double uur;
    private double minuten;
    private double breedtegraad;
    private double dagenVanafBeginLente;
    private static double totaalAantalDagenInJaar;
    private double declinatie;
    private double tijdshoek;
    private double azimut;
    private double zonnehoogte;
    private static double minutenPerUur;
    private static double zonneAfstand;
        
    
    /**
     * Constructor voor de klasse Zon.
     */
    public Zon(double dagNummer,double uur, double minuten, double breedtegraad)
    {
         this.dagNummer = dagNummer;
         this.uur = uur;
         this.minuten = minuten;
         this.breedtegraad = breedtegraad;      
         totaalAantalDagenInJaar = 365;
         minutenPerUur = 60;
         zonneAfstand = 10000;
         setDagenVanafBeginLente();
         setDeclinatie();
         setTijdsHoek();
         setZonneHoogte();
         setAzimut();
    }
    
    /**
     * stelt het bijhorend veld in op het aantal dagen geteld vanaf begin van de lente.
     * 
     * @param      none
	 * @return     void
     */
    private void setDagenVanafBeginLente()
    {       
        dagenVanafBeginLente = dagNummer-80;
        if(dagenVanafBeginLente<0)
        {
            dagenVanafBeginLente=totaalAantalDagenInJaar+dagenVanafBeginLente;
        }                   
        
    }
    
    /**
     * berekent de declinatie.
     * 
     * @param      none
	 * @return     void
     */
    public void setDeclinatie()
    {
        declinatie = 23.45*Math.sin(2*Math.PI*dagenVanafBeginLente/totaalAantalDagenInJaar);
    }
    
    /** berekent de tijdshoek.
     * 
     * @param      none
	 * @return     void
     */
    private void setTijdsHoek()
    {
        tijdshoek = 15*((uur+minuten/minutenPerUur)-12);
    }
    
    /** berekent de zonnehoogte in graden.
     * 
     * @param      none
	 * @return     void
     */
    public void setZonneHoogte()
    {
        double a = Math.sin(Math.toRadians(breedtegraad))*Math.sin(Math.toRadians(declinatie));
        double b= Math.cos(Math.toRadians(breedtegraad))*Math.cos(Math.toRadians(declinatie))*Math.cos(Math.toRadians(tijdshoek));
        zonnehoogte = Math.toDegrees(Math.asin(a+b));
    }
    
    /** berekent de azimut in graden.
     * 
     * @param      none
	 * @return     void
     */
    public void setAzimut()
    {
        double a = (Math.sin(Math.toRadians(zonnehoogte))*Math.sin(Math.toRadians(breedtegraad))-Math.sin(Math.toRadians(declinatie)))/(Math.cos(Math.toRadians(zonnehoogte))*Math.cos(Math.toRadians(breedtegraad)));
        azimut = tijdshoek/Math.abs(tijdshoek)*Math.toDegrees(Math.acos(a));
        
        if(uur==12 && minuten == 0)
        {
            if(breedtegraad < declinatie)
            {
                azimut = 180;
            }
            else
            {
                azimut=0;
            }
        }
        if(zonnehoogte == 90)
        {
            azimut = 0;
        }
        if(breedtegraad == 90)
        {
            azimut = tijdshoek;
        }
        if(breedtegraad == -90)
        {
            azimut = 180-tijdshoek;
        }
    }
    
    /**
     * retourneert de zonnehoogte.
     * 
     * @param      none
	 * @return     zonnehoogte - double die de zonnehoogte in graden bevat 
     */
    private double getZonnehoogte()
    {
        return zonnehoogte;
    }
    
     /**
     * retourneert de azimut.
     * 
     * @param      none
	 * @return     azimut - double die de azimut in graden bevat 
     */
    private double getAzimut()
    {
        return azimut;
    }    
    
    /** retourneert coordinaten zon.
     * 
     * @param       none
     * @return      Vector3D met coordinaten zon
     */
    public Vector3D getCoordinatenZon()
    {
        double x = zonneAfstand*Math.cos(Math.toRadians(azimut))*Math.cos(Math.toRadians(zonnehoogte));
        double y = zonneAfstand*Math.sin(Math.toRadians(azimut))*Math.cos(Math.toRadians(zonnehoogte));
        double z = zonneAfstand*Math.sin(Math.toRadians(zonnehoogte));
        return new Vector3D(x,y,z);
    }
    
    /** laat doe de velden van de zon opnieuw te berekenen.
     * 
     * @param       none
     * @return      void
     */
    public void doeBerekeningen()
    {
         setDagenVanafBeginLente();
         setDeclinatie();
         setTijdsHoek();
         setZonneHoogte();
         setAzimut();
    }
    
    /** stelt de breedtegraad voor de zon in.
     * 
     * @param       breedtegraad - int die de nieuwe breedtegraad bevat.
     * @return      void
     */
    public void setBreedteGraad(int breedtegraad)
    {
        this.breedtegraad = breedtegraad;
    }
    
    /** stelt het dagnummer in.
     * 
     * @param       dag - int die de nieuwe dag bevat.
     * @return      void
     */
    public void setDagNummer(int dag)
    {
        dagNummer = dag;
    }
    
    /** stelt het uur in.
     * 
     * @param       tijd - int die het nieuwe uur bevat.
     * @return      void
     */    
    public void setUur(int tijd)
    {
        this.uur = tijd;
    }
    
    /** stelt de minuten in.
     * 
     * @param       tijd - int die de nieuwe minuten bevat.
     * @return      void
     */    
    public void setMinuten(int tijd)
    {
        this.minuten = tijd;
    }
    
   
}
       
