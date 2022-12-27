import java.awt.Color;
import solar.util.Vector3D;



/**
 * Balk maakt voorwerpen van type balk aan. Het is een subklasse van Voorwerp.
 * 
 * @Auteurs: Van Damme Wim, Vander Mynsbrugge Jorrit, Vandermeersch Antoine
 * @Versie: 11/05/06
 */
public class Balk extends Voorwerp
{
    // onthoudt de lengte van de balk
    protected int lengte;
    // onthoudt de hoogte van de balk
    protected int hoogte;
    // onthoudt de breedte van de balk
    protected int breedte;
    // een Vector3D array om de verschillende hoekpunten van de balk bij te houden
    protected Vector3D[] hoekpunt;

        

    /**
     * Constructor voor objecten van klasse Balk.
     */

            
    public Balk(Vector3D coZon, String kleur, String materiaal, double x, double y, double z, int lengte, int breedte, int hoogte)
    {
        
        // Parameters als kleur, locatie ankerpunt, zonrichtings-vector en materiaal worden doorgegeven naar de superklasse.
        super(coZon,kleur,materiaal,x,y,z);
        // aanmaken van de afmetingen van de balk.
        this.lengte = lengte;
        this.breedte = breedte;
        this.hoogte = hoogte;
        // aanmaken van een Vector3D[] Array die de hoekpuntcoordinaten van het prisma zal opslaan.;
        hoekpunt = new Vector3D[8];
        // initialiseren van de vlakken van de balk.
        vlakken.add(null);     
        vlakken.add(null);       
        vlakken.add(null);       
        vlakken.add(null);       
        vlakken.add(null);       
        vlakken.add(null);    
        // hoekpunten, vlakken en schduwvlakken worden meteen berekend.        
        zoekHoekpunten();
        maakVlakken();
        maakSchaduwVlakken();
    }
    /**
     * zoekHoekPunten - op basis van de locatie (ankerpunt) en de lengte, breedte, hoogte worden de hoekpunten van de balk samengesteld.
     * 
     * @param   none
     * @return  void
     */    
    public void zoekHoekpunten()
    {
        hoekpunt[0]= new Vector3D(locatie.getX()-lengte/2,locatie.getY()-breedte/2,locatie.getZ());
        hoekpunt[1] = new Vector3D(locatie.getX()+lengte/2,locatie.getY()-breedte/2,locatie.getZ());
        hoekpunt[2] = new Vector3D(locatie.getX()+lengte/2,locatie.getY()+breedte/2,locatie.getZ());
        hoekpunt[3] = new Vector3D(locatie.getX()-lengte/2,locatie.getY()+breedte/2,locatie.getZ());
        hoekpunt[4] = new Vector3D(locatie.getX()-lengte/2,locatie.getY()-breedte/2,locatie.getZ()+hoogte);
        hoekpunt[5] = new Vector3D(locatie.getX()+lengte/2,locatie.getY()-breedte/2,locatie.getZ()+hoogte);
        hoekpunt[6] = new Vector3D(locatie.getX()+lengte/2,locatie.getY()+breedte/2,locatie.getZ()+hoogte);
        hoekpunt[7] = new Vector3D(locatie.getX()-lengte/2,locatie.getY()+breedte/2,locatie.getZ()+hoogte);
    }
    /**
     * maakVlakken - de verschillende vlakken worden rechtstreeks berekend en nadien in de ArrayList van vlakken in de superklasse
     * opgeslagen. Om de conventie (juiste richting normaal) te behouden wordt de 'set'-methode van klasse ArrayList aanroepen, en de
     * volgorde van de vlakken te garanderen.
     * 
     * @param   none
     * @return  void
     */    
    public void maakVlakken()
    { 
        
        // maakt ondervlak en voegt ze toe aan de arraylist vlakken
        Vector3D[] vierhoek = new Vector3D[]{hoekpunt[0],hoekpunt[1],hoekpunt[2],hoekpunt[3]};
        Vlak ondervlak = new Vlak(hoofdkleur,vierhoek);
        vlakken.set(0,ondervlak);      
        
        // maakt bovenvlak en voegt ze toe aan de arraylist vlakken
        vierhoek = new Vector3D[]{hoekpunt[4],hoekpunt[5],hoekpunt[6],hoekpunt[7]};
        Vlak bovenvlak = new Vlak(hoofdkleur,vierhoek);
        vlakken.set(1,bovenvlak);    
               
        // maakt linkerzijvlak voegt ze toe aan de arraylist vlakken
        vierhoek = new Vector3D[]{hoekpunt[0],hoekpunt[1],hoekpunt[5],hoekpunt[4]};
        Vlak linkervlak = new Vlak(hoofdkleur,vierhoek);
        vlakken.set(2,linkervlak);          
        
        // maakt rechterzijvlak en voegt ze toe aan de arraylist vlakken
        vierhoek = new Vector3D[]{hoekpunt[7],hoekpunt[6],hoekpunt[2],hoekpunt[3]};
        Vlak rechtervlak = new Vlak(hoofdkleur,vierhoek);
        vlakken.set(3,rechtervlak);    
        
        // maakt voorvlak en voegt ze toe aan de arraylist vlakken
        vierhoek = new Vector3D[]{hoekpunt[1],hoekpunt[2],hoekpunt[6],hoekpunt[5]};
        Vlak voorvlak = new Vlak(hoofdkleur,vierhoek);
        vlakken.set(4,voorvlak);    
  
           
        // maakt achtervlak en voegt ze toe aan de arraylist vlakken
        vierhoek = new Vector3D[]{hoekpunt[4],hoekpunt[7],hoekpunt[3],hoekpunt[0]};
        Vlak achtervlak = new Vlak(hoofdkleur,vierhoek);
        vlakken.set(5,achtervlak);    
    }
    /**
     * setDimensies - wijzigt de huidige afmetingen van de balk.
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
     * getVolume - met behulp van de volume-definitie van een balk en de huidige afmetingen wordt het volume berekend.
     * 
     * @param   none
     * @return  double volume - totaal volume van de balk
     */    
    public double getVolume()
    {
        double volume = breedte*lengte*hoogte;
        return volume;
    }
    /**
     * getLbh - geeft de afmetingen (lengte-breedte-hoogte) van de balk terug, opgeslagen in een 'intage' array.
     * 
     * @param   none
     * @return  int[] lbh - array met afmetingen van de balk
     */
        public int[] getLbh()
    {
        int[] lbh = new int[3];
        lbh[0] = lengte; 
        lbh[1] = breedte;
        lbh[2] = hoogte;
        return lbh; 
    }
    
}