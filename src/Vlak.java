import solar.util.Vector3D;
import java.awt.Color;

/**
 * Instanties van de klasse Vlak zijn volwaardige veelhoeken, die kunnen hun kleur, hoekpunten en normaal retourneren.
 * 
 * @author      Jorrit Vander Mynsbrugge, Wim Vandamme, Antoine Vermeersch
 * @version     2006-11-05
 */
public class Vlak
{
    private Vector3D[] hoekpunten;
    private int lichtsterkte;
    private Color kleur;
    
    /**
     * constructor voor objecten van de klasse Vlak
     * 
     * @param       kleur - bevat de kleur
     * @param       veelhoek - bevat de hoekpunten van het vlak
     */
    public Vlak(Color kleur,Vector3D[] veelhoek)
    {
           hoekpunten = veelhoek;
           this.kleur = kleur;
    }
    
    /**
     * retourneert de hoekpunten van het vlak
     * 
     * @param       none   
     * @return      hoekpunten - Vector3D[] met hoekpunten van het voorwerp (in de juiste volgorde)
     */
    public Vector3D[] getHoekpunten()
    {
        return hoekpunten;
    }
    
    /**
     * stelt de hoekpunten in voor het vlak
     * 
     * @param       nieuwHoekpunten - Vector3D[] met de nieuwe hoekpunten voor het vlak
     * @return      void
     */
    public void setHoekpunten(Vector3D[] nieuwHoekpunten)
    {
        hoekpunten = nieuwHoekpunten;
    }
    
    /**
     * berekent en retourneert de normaal van het vlak
     * 
     * @param       none
     * @return      Vector3D die de normaal van het vlak bevat
     */
    public Vector3D getNormaal()
    {
        double x,y,z;
        
        x = hoekpunten[1].getX()-hoekpunten[0].getX();
        y = hoekpunten[1].getY()-hoekpunten[0].getY();
        z = hoekpunten[1].getZ()-hoekpunten[0].getZ();
        Vector3D vectoralfa = new Vector3D(x,y,z);
        
        x = hoekpunten[2].getX()-hoekpunten[1].getX();
        y = hoekpunten[2].getY()-hoekpunten[1].getY();
        z = hoekpunten[2].getZ()-hoekpunten[1].getZ();
        Vector3D vectorbeta = new Vector3D(x,y,z);        
        
        return vectoralfa.vectorProduct(vectorbeta);
    }
    
    /**
     * retourneert de kleur van een vlak
     * 
     * @param       none
     * @return      kleur - type: Color
     */
    public Color getKleur()
    {
        return kleur;
    }

}
