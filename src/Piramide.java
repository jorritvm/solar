import java.awt.Color;
import solar.util.Vector3D;



/**
 * Piramide maakt voorwerpen van type Piramide aan. Het is een subklasse van superklasse Voorwerp.
 * Een piramide heeft een gelijkzijdig grondvlak en 4 zijvlakken, het tophoekpunt bevindt zich echt boven het ankerpunt.
 * 
 * @auteurs: Van Damme Wim, Vander Mynsbrugge Jorrit, Vandermeersch Antoine
 * @versie: v1 
 */
public class Piramide extends Voorwerp
{
    // onthoudt de lengte van het grondvlak van de piramide
    private int lengte;
    // onthoudt de breedte, is gelijk aan de lengte
    private int breedte;
    // onthoudt de hoogte van de piramide
    private int hoogte;
    // een Vector3D[] array die alle hoekpunten van de piramide bijhoudt
    protected Vector3D[] hoekpunt;

    /**
     * Constructor voor objecten van klasse Piramide.
     */
    public Piramide(Vector3D coZon, String kleur, String materiaal, double x, double y, double z, int lengte, int hoogte)
    {
        
        // Parameters als kleur, locatie ankerpunt, zonrichtings-vector en materiaal worden doorgestuurd naar de superklasse Voorwerp.
        super(coZon,kleur,materiaal,x,y,z);
        // aanmaken van de afmetingen van de piramide.
        this.lengte = lengte;
        this.hoogte = hoogte;
        // initialiseren van de vlakken van de piramide.
        vlakken.add(null);     
        vlakken.add(null);       
        vlakken.add(null);       
        vlakken.add(null);       
        vlakken.add(null);     
        // aanmaken van een Vector3D[] Array die de hoekpunten van de piramide zal opslaan.
        hoekpunt = new Vector3D[5];
        // hoekpunten, vlakken en schaduwvlakken worden meteen berekend.
        zoekHoekpunten();
        maakVlakken();
        maakSchaduwVlakken();
    }
    /**
     * zoekHoekPunten - op basis van de locatie (ankerpunt) en de lengte en hoogte worden de hoekpunten van de piramide samengesteld.
     * 
     * @param   none
     * @return  void
     */    
    public void zoekHoekpunten()
    {
        hoekpunt[0] = new Vector3D(locatie.getX()-lengte/2,locatie.getY()-lengte/2,locatie.getZ());
        hoekpunt[1] = new Vector3D(locatie.getX()+lengte/2,locatie.getY()-lengte/2,locatie.getZ());
        hoekpunt[2] = new Vector3D(locatie.getX()+lengte/2,locatie.getY()+lengte/2,locatie.getZ());    
        hoekpunt[3] = new Vector3D(locatie.getX()-lengte/2,locatie.getY()+lengte/2,locatie.getZ());
        hoekpunt[4] = new Vector3D(locatie.getX(),locatie.getY(),locatie.getZ()+hoogte);
    }
    /**
     * maakVlakken - methodes voor de veschillende vlakken worden uitgevoerd en nadien in de ArrayList van vlakken in de superklasse
     * opgeslagen. Om de conventie (juiste richting normaal) te behouden wordt de 'set'-methode van klasse ArrayList aanroepen, en de
     * volgorde van de vlakken te garanderen.
     * 
     * @param   none
     * @return  void
     */
    public void maakVlakken()
    {
        vlakken.set(0,maakOndervlak());
        vlakken.set(1,maakZijvlakLinks());
        vlakken.set(2,maakZijvlakRechts());
        vlakken.set(3,maakVoorvlak());
        vlakken.set(4,maakAchtervlak());
    }
    /**
     * maakOndervlak - op basis van de hoekpunten wordt het ondervlak gecreerd, voorgesteld door een Vector3d[] Array.
     * 
     * @param   none
     * @return  Vlak ondervlak
     */    
    private Vlak maakOndervlak()
    {
        Vector3D[] vierhoek = new Vector3D[]{hoekpunt[0],hoekpunt[1],hoekpunt[2],hoekpunt[3]};
        Vlak ondervlak = new Vlak(hoofdkleur,vierhoek);
        return ondervlak;
    }
    /**
     * maakZijvlakLinks - op basis van de hoekpunten wordt het linkse zijvlak gecreerd, voorgesteld door een Vector3d[] Array.
     * 
     * @param   none
     * @return  Vlak zijvlakLinks
     */    
  
    private Vlak maakZijvlakLinks()
    {
        Vector3D[] driehoek = new Vector3D[]{hoekpunt[0],hoekpunt[1],hoekpunt[4]};
        Vlak zijvlakLinks = new Vlak(hoofdkleur,driehoek);
        return zijvlakLinks;
    }
    /**
     * maakZijvlakRechts - op basis van de hoekpunten wordt het rechtse zijvlak gecreerd, voorgesteld door een Vector3d[] Array.
     * 
     * @param   none
     * @return  Vlak zijvlakRechts
     */    
    private Vlak maakZijvlakRechts()
    {
        Vector3D[] driehoek = new Vector3D[]{hoekpunt[2],hoekpunt[3],hoekpunt[4]};
        Vlak zijvlakRechts = new Vlak(hoofdkleur,driehoek);
        return zijvlakRechts;
    }
    /**
     * maakVoorvlak - op basis van de hoekpunten wordt het voorvlak gecreerd, voorgesteld door een Vector3d[] Array.
     * 
     * @param   none
     * @return  Vlak voorvlak
     */    
    private Vlak maakVoorvlak()
    {
        Vector3D[] driehoek = new Vector3D[]{hoekpunt[1],hoekpunt[2],hoekpunt[4]};
        Vlak voorvlak = new Vlak(hoofdkleur,driehoek);
        return voorvlak;
    }
    /**
     * maakAchtervlak - op basis van de hoekpunten wordt het ondervlak gecreerd, voorgesteld door een Vector3d[] Array.
     * 
     * @param   none
     * @return  Vlak achtervlak
     */    
    private Vlak maakAchtervlak()
    {
        Vector3D[] driehoek = new Vector3D[]{hoekpunt[3],hoekpunt[0],hoekpunt[4]};
        Vlak achtervlak = new Vlak(hoofdkleur,driehoek);
        return achtervlak;
    }
    /**
     * setDimensies - wijzigt de huidige afmetingen van de piramide.
     * 
     * @param   int[] dimensies - nieuwe afmetingen
     * @return  void
     */    
    public void setDimensies(int[] dimensies) 
    {
        lengte = dimensies[0];
        breedte = dimensies[1];
        hoogte = dimensies[2];
    }
    /**
     * getVolume - met behulp van de volume-definitie van een piramide en de huidige afmetingen wordt het volume berekend.
     * 
     * @param   none
     * @return  double volume - totaal volume van de piramide
     */    
    public double getVolume()
    {
        double volume = lengte*lengte*hoogte/3;
        return volume;
    }   
    /**
     * getLbh - geeft de afmetingen (lengte-lengte-hoogte) van het prima terug, opgeslagen in een 'intage' array.
     * 
     * @param   none
     * @return  int[] lbh - array met afmetingen
     */    
    public int[] getLbh()
    {
        int[] lbh = new int[3];
        lbh[0] = lengte; 
        lbh[1] = lengte;
        lbh[2] = hoogte;
        return lbh; 
    }
        
}