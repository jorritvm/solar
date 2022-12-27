
import java.awt.Color;
import java.util.ArrayList;
import solar.util.Vector3D;


/**
 * Is de superklasse voor verschillende typen voorwerpen die kunnen getekend worden.
 * 
 * 
 * @Auteurs: Van Damme Wim, Vander Mynsbrugge Jorrit, Vandermeersch Antoine
 * @Versie: 11/05/06
 */


public abstract class Voorwerp
{
    // bepaalt de hoofdkleur voor alle vlakken van het voorwerp
    protected Color hoofdkleur;
    // onthoudt de naam van de gebruikte hoofdkleur
    private String kleur;
    // ArrayList om alle vlakken van het voorwerp bij te houden
    protected ArrayList vlakken;
    // onthoudt de naam aan het voorwerp gegeven
    protected String naam;
    // vector die het ankerpunt van het voorwerp vastlegt
    protected Vector3D locatie;
    // een object van Kleurenlijst met alle beschikbare kleuren
    private Kleurenlijst kleurenlijst;
    // vector die de invalsrichting van de zon vastlegt
    protected Vector3D coZon;
    // ArrayList om alle schaduwvlakken van het voorwerp bij te houden
    protected ArrayList schaduwVlakken;
    // een object van Materialenlijst met alle beschikbare materialen
    private Materialenlijst materialenlijst;
    // onthoudt het materiaal waaruit het voorwerp bestaat
    private String materiaal;
        
    /**
     * Constructor voor objecten van klasse Voorwerp.
     */    

    public Voorwerp(Vector3D coZon, String kleur, String materiaal, double x, double y, double z)  
    {
        kleurenlijst = new Kleurenlijst();
        vlakken = new ArrayList();
        materialenlijst = new Materialenlijst();
  
        this.coZon = coZon; 
        this.materiaal = materiaal;
        this.kleur = kleur;
        setKleurVoorwerp(kleur);
        setLocatie(x,y,z);        
    }
    /**
     * Wijzig de hoofdkleur van de vlakken van het voorwerp.
     * 
     * @param   String kleur - gewenste kleur
     * @return  void
     */    
    public void setKleurVoorwerp(String kleur)
    {
        hoofdkleur = kleurenlijst.getKleur(kleur);
    }
    /**
     * getKleurVoorwerp - geeft de hoofdkleur van de vlakken van het voorwerp terug.
     * 
     * @return  Color hoofdkleur
     */    
    public Color getKleurVoorwerp()
    {
        return hoofdkleur;
    }
    /**
     * getKleur - geeft de naam van de kleur van het voorwerp terug
     * 
     * @param   none
     * @return  String kleur
     */    
    public String getKleur()
    {
        return kleur;
    }
    /**
     * getVlakken - geeft een ArrayList met daarin de vlakken van het voorwerp, die worden voorgesteld als Vector3D[] arrays.
     * 
     * @param   none
     * @return  ArrayList vlakken
     */    
    public ArrayList getVlakken()
    {
        return vlakken;
    }
    /**
     * setName - gebruikt de ingevoerde parameter om het voorwerp een indentiteit te geven.
     * 
     * @param   String naam - gewenste naam
     * @return  void
     */    
    public void setName(String naam) 
    {
        this.naam = naam;
    }
    /**
     * getNaam - geeft de naam van het voorwerp terug
     * 
     * @param   none
     * @return  String naam - naam van het voorwerp
     */    
    public String getNaam()
    {
        return naam;
    }
    /**
     * setLocatie - wijzigt het ankerpunt van het voorwerp en laat hiermee daardoor translatie van een
     * voorwerp toe.
     * 
     * @param   double x - positie ankerpunt volgens de x-as
     *          double y - positie ankerpunt volgens de y-as
     *          double z - positie ankerpunt volgens de z-as
     * @return  void
     */    
    public void setLocatie(double x, double y, double z)
    {
         locatie = new Vector3D(x,y,z);         
    }
     /**
     * getLocatie - geeft de positie van het ankerpunt van het voorwerp terug.
     * 
     * @param   none
     * @return  Vector3D locatie - vector-uitdrukking van het ankerpunt
     */    
    public Vector3D getLocatie()
    {
        return locatie;
    }
    /**
     * getSchaduwVlakken - geeft de ArrayList met daarin de schaduwvlakken van het voorwerp terug.
     * 
     * @param   none
     * @return  ArrayList schaduwVlakken
     */    
    public ArrayList getSchaduwVlakken()//ZON AANPASSING
    {
        return schaduwVlakken;
    }
    /**
     * setMateriaal - wijzigt het materiaal waaruit het materiaal is opgebouwd.
     * 
     * @param   String materiaal - naam van het gewenste materiaal
     * @return  void
     */    
    public void setMateriaal(String materiaal)
    {
        this.materiaal = materiaal;
    }
    /**
     * getMateriaal - zend de naam van het materiaal waaruit het voorwerp bestaat terug.
     * 
     * @param   none
     * @return  String materiaal
     */    
    public String getMateriaal()
    {
        return materiaal;
    }
    /**
     * getDichtheid - met behulp van het materiaal van het voorwerp, wordt de dichtheid uit de materialenlijst gehaald.
     * 
     * @param   none
     * @return  int dichtheid - dichtheid van het materiaal
     */    
    public int getDichtheid()
    {
        return materialenlijst.getDichtheid(materiaal);               
        
    }
    /**
     * setCoordinatenZon - wijzigt de zonrichtings-vector door verwisseling van de oude Vector3D met de nieuwe ingevoerde Vector3D.
     * 
     * @param   Vector3D nieweCo - nieuwe zonrichtings-vector
     * @return  void
     */    
    public void setCoordinatenZon(Vector3D nieuweCo)
    {
        coZon = nieuweCo;   
    }
    /**
     * maakSchaduwVlakken - berekent met behulp van de zonrichtings-vector en de hoekpunten van het voorwerp de hoekpunten van elk
     * nodig schaduwvlak. In de 2de for-lus worden de schaduw-hoekpunten aangemaakt, opgeslagen in een Vector3D[] Array. In de 1ste for-lus
     * worden deze Vector3d[] Arrays als een object van klasse Vlak opgeslaan. Dit wordt herhaald voor elk vlak van het voorwerp.
     * 
     * @param   none
     * @return  void
     */
    public void maakSchaduwVlakken()//ZON AANPASSING
    {
        schaduwVlakken = new ArrayList();
        for(int i=0;i<vlakken.size();i++)
        {
            Vector3D[] tempHoekpunten = ((Vlak) vlakken.get(i)).getHoekpunten();
            Vector3D[] tempSchaduwvlak = new Vector3D[tempHoekpunten.length];
            for(int j=0;j<tempHoekpunten.length;j++)
            {
                double x2 = tempHoekpunten[j].getX();
                double y2 = tempHoekpunten[j].getY();
                double z2 = tempHoekpunten[j].getZ();
                
                double param = -z2/(z2-coZon.getZ());
                
                double xSchaduw = param*(x2-coZon.getX())+x2;
                double ySchaduw = param*(y2-coZon.getY())+y2;
                double zSchaduw = param*(z2-coZon.getZ())+z2;
                
                tempSchaduwvlak[j] = new Vector3D(xSchaduw,ySchaduw,zSchaduw);
            }
            schaduwVlakken.add(new Vlak(Color.gray,tempSchaduwvlak));
        }
           
    }
    /**
     * getMassa - met behulp van de getVolume methode en de getDichtheid methode wordt de massa
     * berekend.
     * 
     * @param   none
     * @return  double massa - totale massa van de piramide
     */    
    public double getMassa()
    {
        double massa = getVolume()*getDichtheid();
        return massa;
    }      
       
    
    // Roept de methode zoekHoekPunten aan in de subklassen om de hoekpunten te zoeken.
    abstract public void zoekHoekpunten();
    // Roept de methode maakVlakken aan in de subklassen om de vlakken aan te maken.
    abstract public void maakVlakken();
    // Roept de methode setDimensies aan in de subklassen om de afmetingen van het voorwerp te wijzigen.
    abstract public void setDimensies(int[] dimensies);
    // Roept de methode getVolume aan in de subklassen om het volume weer te geven.
    abstract public double getVolume();
    // Roept de methode getLbh aan in de subklassen om de afmetingen van het voorwerp weer te geven: l(engte)-b(reedte)-h(oogte).
    abstract public int[] getLbh();
    



}
