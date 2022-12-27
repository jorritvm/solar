import java.awt.Color;
import solar.gui.SolarGui;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import solar.util.Vector3D;


/**
 * Deze klasse is de hoofdklasse van het Solar project. Beheer initialiseert de gui, inclusief de knoppen ervan en verwerkt alle input die gegeven wordt via deze knoppen
 * tot een grafische output. De klasse zorgt er m.a.w. voor dat voorwerpen en hun schaduwen getekend en gewijzigd kunnen worden, afhankelijk van het juiste tijdstip en 
 * de parameters ingegeven door de gebruiker. Voorts voorziet de klasse nog enkele functionaliteiten zoals het opvragen van het zwaarste voorwerp, het gemiddelde gewicht etc.
 * 
 * @author Jorrit Vander Mynsbrugge, Wim Van Damme, Antoine Vandermeersch
 * @version 2006-05-11
 */
public class Beheer
{
 
    private SolarGui gui;
    private Zon zon;
    private Vector3D coZon;
    
    private Voorwerp tempVoorwerp;
    
    private ArrayList voorwerpenVerzameling;
    private ArrayList origineleHoekpuntenVerzameling;
    
    private ArrayList vlakken;
    private Vector3D hoekpunten;
    private Voorwerp behandeldVoorwerp;
    private int x;
    
    private int index;
    private String naamVanTeWijzigenObject;

    
     /**
     * Constructor voor de klasse Beheer.
     */
    
    public Beheer()
    {
         voorwerpenVerzameling = new ArrayList();    
             
         x = 1;

         gui = new SolarGui("SolarGui Project", 1000, 800);
         zon = new Zon(gui.getDaySlider().getValue(),(gui.getTimeSlider().getValue()*5-gui.getTimeSlider().getValue()*5%60)/60,(gui.getTimeSlider().getValue()*5)%60,gui.getLatitudeSlider().getValue());
         coZon = zon.getCoordinatenZon();
         initialiseerKnoppen();
         
    }

    
     /**
     * Initialiseert de verscheidene knoppen door ActionListeners en Changelisteners aan te maken en
     * definieert de acties die ondernomen worden bij het gebruiken van een van de knoppen.
     * 
     * @param      none
     * @return     void
     */
    
    private void initialiseerKnoppen()
    {               
         
     /**
     * Wanneer op de knop wordt gedrukt wordt de gebruiker gevraagd om parameters in te geven en wordt in de methode makeKubus een Kubus object aangemaakt op basis van deze 
     * parameters, indien de positieparameters niet zorgen voor een snijding met een reeds bestaand object en indien de z coordinaat niet kleiner is dan 0. 
     * Als dit niet het geval is wordt het Kubus object verder verwerkt in de methode tekenVoorwerp().
     * 
     */               
        
        gui.getKubusKnop().addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e)
             {
                 String[] antwoorden = new String[7];
                 String[] tmp1 = new String[]{"naam","kleur","materiaal","x positie","y positie","z positie","lengte"};
                 String[] tmp2 = new String[]{"Kubus"+ x,"rood","kurk","0","0","0","50"};
                 antwoorden = gui.vraagInfo("Vul onderstaande velden in","Maak een nieuwe kubus aan",tmp1,tmp2);
                 if(Double.parseDouble(antwoorden[5])<0){System.out.println("U kan geen ondergrondse voorwerpen definieren.");}
                 else{
                     
                 makeKubus(antwoorden[0], antwoorden[1], antwoorden[2], Double.parseDouble(antwoorden[3]), Double.parseDouble(antwoorden[4]), Double.parseDouble(antwoorden[5]), Integer.parseInt(antwoorden[6]));
                 x++;
                 index = voorwerpenVerzameling.indexOf(behandeldVoorwerp);   
                 if(!controleerOpSnijden()) 
                 {
                     tekenVoorwerp();
                    }
                else
                {
                     System.out.println("ze snijden");
                     voorwerpenVerzameling.remove(voorwerpenVerzameling.size()-1);
                }
             }
            }
         });

     /**
     * Wanneer op de knop wordt gedrukt wordt de gebruiker gevraagd om parameters in te geven en wordt in de methode makeBalk een Balk object aangemaakt op basis van deze 
     * parameters, indien de positieparameters niet zorgen voor een snijding met een reeds bestaand object en indien de z coordinaat niet kleiner is dan 0. 
     * Als dit niet het geval is wordt het Balk object verder verwerkt in de methode tekenVoorwerp().
     * 
     */                  
              
         gui.getBalkKnop().addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e)
             {
                 String[] antwoorden = new String[9];
                 String[] tmp1 = new String[]{"naam","kleur","materiaal","x positie","y positie","z positie","lengte","breedte","hoogte"};
                 String[] tmp2 = new String[]{"Balk"+ x,"rood","kurk","0","0","0","50","40","30"};
                 antwoorden = gui.vraagInfo("Vul onderstaande velden in","Maak een nieuwe balk aan",tmp1,tmp2);
                 if(Double.parseDouble(antwoorden[5])<0){System.out.println("U kan geen ondergrondse voorwerpen definieren.");}
                 else{
                 makeBalk(antwoorden[0],antwoorden[1],antwoorden[2], Double.parseDouble(antwoorden[3]), Double.parseDouble(antwoorden[4]), Double.parseDouble(antwoorden[5]) ,Integer.parseInt(antwoorden[6]),Integer.parseInt(antwoorden[7]),Integer.parseInt(antwoorden[8]));

                 x++;       
                 index = voorwerpenVerzameling.indexOf(behandeldVoorwerp);   
                 if(!controleerOpSnijden()) 
                 {
                     tekenVoorwerp();
                            
                 }
                 else
                 {
                     System.out.println("ze snijden");
                     voorwerpenVerzameling.remove(voorwerpenVerzameling.size()-1);
                  }
                }
                }
         });
     /**
     * Wanneer op de knop wordt gedrukt wordt de gebruiker gevraagd om parameters in te geven en wordt in de methode makePrisma een Prisma object aangemaakt op basis van deze 
     * parameters, indien de positieparameters niet zorgen voor een snijding met een reeds bestaand object en indien de z coordinaat niet kleiner is dan 0. 
     * Als dit niet het geval is wordt het Prisma object verder verwerkt in de methode tekenVoorwerp().
     * 
     */                  
                   
         gui.getPrismaKnop().addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e)
             {
                 String[] antwoorden = new String[9];
                 String[] tmp1 = new String[]{"naam","kleur","materiaal","x positie","y positie","z positie","lengte","breedte","hoogte"};
                 String[] tmp2 = new String[]{"Prisma"+x,"rood","kurk","0","0","0","30","50","60"};
                 antwoorden = gui.vraagInfo("Vul onderstaande velden in","Maak een nieuwe prisma aan",tmp1,tmp2);
                 if(Double.parseDouble(antwoorden[5])<0){System.out.println("U kan geen ondergrondse voorwerpen definieren.");}
                 else{
                 makePrisma(antwoorden[0],antwoorden[1],antwoorden[2],Double.parseDouble(antwoorden[3]), Double.parseDouble(antwoorden[4]), Double.parseDouble(antwoorden[5]),Integer.parseInt(antwoorden[6]),Integer.parseInt(antwoorden[7]),Integer.parseInt(antwoorden[8]));
           
                 x++;
                 
                 index = voorwerpenVerzameling.indexOf(behandeldVoorwerp);           
                 
                 if(!controleerOpSnijden()) 
                 {
                     tekenVoorwerp();
                 }
                 else
                 {
                     System.out.println("ze snijden");
                     voorwerpenVerzameling.remove(voorwerpenVerzameling.size()-1);
                 }
             }   
            }
          });
     /**
     * Wanneer op de knop wordt gedrukt wordt de gebruiker gevraagd om parameters in te geven en wordt in de methode makePiramide een Piramide object aangemaakt op basis van deze 
     * parameters, indien de positieparameters niet zorgen voor een snijding met een reeds bestaand object en indien de z coordinaat niet kleiner is dan 0. 
     * Als dit niet het geval is wordt het Piramide object verder verwerkt in de methode tekenVoorwerp().
     * 
     */          
                     
         gui.getPiramideKnop().addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e)
            {
                String[] antwoorden = new String[8];
                String[] tmp1 = new String[]{"naam","kleur","materiaal","x positie","y positie","z positie","lengte","hoogte"};
                String[] tmp2 = new String[]{"Piramide"+x,"rood","kurk","0","0","0","30","50"};
                antwoorden = gui.vraagInfo("Vul onderstaande velden in","Maak een nieuwe piramide aan",tmp1,tmp2);
                if(Double.parseDouble(antwoorden[5])<0){System.out.println("U kan geen ondergrondse voorwerpen definieren.");}
                 else{
                
                makePiramide(antwoorden[0],antwoorden[1],antwoorden[2],Double.parseDouble(antwoorden[3]), Double.parseDouble(antwoorden[4]), Double.parseDouble(antwoorden[5]),Integer.parseInt(antwoorden[6]),Integer.parseInt(antwoorden[7]));
            
                
                x++;        
                
                index = voorwerpenVerzameling.indexOf(behandeldVoorwerp);   
                
                if(!controleerOpSnijden()) 
                {
                    tekenVoorwerp();
                }
                else
                {       
                    System.out.println("ze snijden");
                    voorwerpenVerzameling.remove(voorwerpenVerzameling.size()-1);
                }
            }
           }
         });
     /**
     *  De Editeerknop werkt samen met de ObjectenComboBox. Wanneer een object is geselecteerd in de combobox en wanneer men dan op de Editeerknop drukt, wordt dit object
     *  intern geselecteerd door het op te halen uit de voorwerpenVerzameling. Hierna wordt de methode vraagWijzigingen opgeroepen. 
     * 
     */               
        
         gui.getEditeerKnop().addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e)
             {
                 naamVanTeWijzigenObject = (String) (gui.getObjectenComboBox().getSelectedItem());
                 int z =0;
         
                 while(z<voorwerpenVerzameling.size())
                 {
                    String namenControle = ((Voorwerp)voorwerpenVerzameling.get(z)).getNaam();
                    if(namenControle.equals(naamVanTeWijzigenObject))
                    {
                        index = z;
                        behandeldVoorwerp = (Voorwerp) voorwerpenVerzameling.get(index);              
                    }
                    z++; 
                 }
                 vraagWijzigingen();
            }
         });
     /**
     *  De Deleteknop werkt samen met de ObjectenComboBox. Wanneer een object is geselecteerd in de combobox en wanneer men dan op de Deleteknop drukt, wordt dit object
     *  intern geselecteerd door het op te halen uit de voorwerpenVerzameling. Hierna worden alle vlakken die bij dit voorwerp horen verwijderd in de GUI en wordt het object
     *  uit de voorwerpenVerzameling en de ObjectenComboBox verwijderd.
     * 
     */               
         gui.getDeleteKnop().addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e)
             {
                 naamVanTeWijzigenObject = (String) (gui.getObjectenComboBox().getSelectedItem());
                 int z =0;
                 
                 while(z<voorwerpenVerzameling.size())
                 {
                     String namenControle = ((Voorwerp)voorwerpenVerzameling.get(z)).getNaam();
                     if(namenControle.equals(naamVanTeWijzigenObject))
                     {
                         index = z;
                         gui.getObjectenComboBox().removeItem(namenControle);
                         behandeldVoorwerp = (Voorwerp) voorwerpenVerzameling.get(index);
                         verwijderVoorwerp();
                     }
                     z++; 
                }                                        
            }
         });
     /**
     *  De LatitudeSlider heeft de mogelijkheid om de breedtegraad te wijzigen. Wanneer de gebruiker de slider versleept en loslaat, wordt de GUI aangepast op basis van 
     *  de nieuw verkregen breedtegraad. Dit verloopt door de zoncoordinaat te wijzigen en de methode pasVoorwerpAan() toe te passen op elk object in de voorwerpenVerzameling. 
     * 
     */               
         gui.getLatitudeSlider().addChangeListener(new ChangeListener(){
             public void stateChanged(ChangeEvent e)
             {                                             
                 if(!gui.getLatitudeSlider().getValueIsAdjusting())
                 {
                     int nieuweBreedtegraad = gui.getLatitudeSlider().getValue();            
                     zon.setBreedteGraad(nieuweBreedtegraad);
                     zon.doeBerekeningen();
                     
                     for(int i = 0;i<voorwerpenVerzameling.size();i++)
                     {
                         index = i;
                         behandeldVoorwerp = (Voorwerp) voorwerpenVerzameling.get(i);           
                         behandeldVoorwerp.setCoordinatenZon(zon.getCoordinatenZon());
                         pasVoorwerpAan();
                     }
                 }    
                 coZon = zon.getCoordinatenZon();
             }
         });
     /**
     *  De TimeSlider heeft de mogelijkheid om de breedtegraad te wijzigen. Wanneer de gebruiker de slider versleept en loslaat, wordt de GUI aangepast op basis van 
     *  het nieuw verkregen tijdstip. Dit verloopt door de zoncoordinaat te wijzigen en de methode pasVoorwerpAan() toe te passen op elk object in de voorwerpenVerzameling. 
     * 
     */              
         gui.getTimeSlider().addChangeListener(new ChangeListener(){
             public void stateChanged(ChangeEvent e)
             {                                             
                 if(!gui.getTimeSlider().getValueIsAdjusting())
                 {
                     int tijd = gui.getTimeSlider().getValue();   
                     zon.setMinuten((tijd*5)%60);
                     zon.setUur(((tijd*5)-(tijd*5)%60)/60);                     
                     zon.doeBerekeningen();
                     
                     for(int i = 0;i<voorwerpenVerzameling.size();i++)
                     {
                         index = i;
                         behandeldVoorwerp = (Voorwerp) voorwerpenVerzameling.get(i);
                         behandeldVoorwerp.setCoordinatenZon(zon.getCoordinatenZon());
                         pasVoorwerpAan();
                     }
                 }       
                 coZon = zon.getCoordinatenZon();
             }
                
                
            });
     /**
     *  De DaySlider heeft de mogelijkheid om de breedtegraad te wijzigen. Wanneer de gebruiker de slider versleept en loslaat, wordt de GUI aangepast op basis van 
     *  de nieuw verkregen dag. Dit verloopt door de zoncoordinaat te wijzigen en de methode pasVoorwerpAan() toe te passen op elk object in de voorwerpenVerzameling. 
     * 
     */                 
          gui.getDaySlider().addChangeListener(new ChangeListener(){
             public void stateChanged(ChangeEvent e)
             {                                             
                 if(!gui.getDaySlider().getValueIsAdjusting())
                 {
                     int dagNummer = gui.getDaySlider().getValue();    
                     zon.setDagNummer(dagNummer);
                     zon.doeBerekeningen();
                     
                     for(int i = 0;i<voorwerpenVerzameling.size();i++)
                     {
                         index = i;
                         behandeldVoorwerp = (Voorwerp) voorwerpenVerzameling.get(i);
                         behandeldVoorwerp.setCoordinatenZon(zon.getCoordinatenZon());
                         pasVoorwerpAan();
                     }
                 }     
                 coZon = zon.getCoordinatenZon();
             }
         });
    }
     /**
     * Maakt een nieuw Kubusobject aan op basis van de door de gebruiker ingegeven parameters en voegt dit object toe aan de voorwerpenVerzameling.
     * 
     * 
     * @param       naam - String die de naam van het aan te maken voorwerp bevat
     *              kleur - String die de naam van de kleur van het aan te maken voorwerp bevat
     *              materiaal - String die het materiaaltype van de kleur van het aan te maken voorwerp bevat
     *              x - double die de x coordinaat in het assestelsel van de GUI van het aan te maken voorwerp bevat
     *              y - double die de y coordinaat in het assestelsel van de GUI van het aan te maken voorwerp bevat
     *              z - double die de z coordinaat in het assestelsel van de GUI van het aan te maken voorwerp bevat
     *              lengte - int die de lengte van een zijde van het Kubusobject bepaald
     * @return      void
     */
    private void makeKubus(String naam, String kleur, String materiaal, double x, double y, double z, int lengte)
    {
        behandeldVoorwerp = new Kubus(coZon,kleur,materiaal,x,y,z,lengte);
        behandeldVoorwerp.setName(naam);
        voorwerpenVerzameling.add(behandeldVoorwerp);
    }
     /**
     * Maakt een nieuw Balkobject aan op basis van de door de gebruiker ingegeven parameters en voegt dit object toe aan de voorwerpenVerzameling.
     * 
     * 
     * @param       naam - String die de naam van het aan te maken voorwerp bevat
     *              kleur - String die de naam van de kleur van het aan te maken voorwerp bevat
     *              materiaal - String die het materiaaltype van de kleur van het aan te maken voorwerp bevat
     *              x - double die de x coordinaat in het assestelsel van de GUI van het aan te maken voorwerp bevat
     *              y - double die de y coordinaat in het assestelsel van de GUI van het aan te maken voorwerp bevat
     *              z - double die de z coordinaat in het assestelsel van de GUI van het aan te maken voorwerp bevat
     *              lengte - int die de lengte van het Balkobject bepaalt
     *              breedte - int die de breedte van het Balkobject bepaalt
     *              hoogte - int die de hoogte van het Balkobject bepaalt
     * @return      void
     */   
    private void makeBalk(String naam,String kleur,String materiaal, double x, double y, double z, int lengte, int breedte, int hoogte)
    {
        behandeldVoorwerp = new Balk(coZon,kleur,materiaal,x,y,z,lengte,breedte,hoogte);
        behandeldVoorwerp.setName(naam);       
        voorwerpenVerzameling.add(behandeldVoorwerp);
    }
     /**
     * Maakt een nieuw Prismaobject aan op basis van de door de gebruiker ingegeven parameters en voegt dit object toe aan de voorwerpenVerzameling.
     * 
     * 
     * @param       naam - String die de naam van het aan te maken voorwerp bevat
     *              kleur - String die de naam van de kleur van het aan te maken voorwerp bevat
     *              materiaal - String die het materiaaltype van de kleur van het aan te maken voorwerp bevat
     *              x - double die de x coordinaat in het assestelsel van de GUI van het aan te maken voorwerp bevat
     *              y - double die de y coordinaat in het assestelsel van de GUI van het aan te maken voorwerp bevat
     *              z - double die de z coordinaat in het assestelsel van de GUI van het aan te maken voorwerp bevat
     *              lengte - int die de lengte van het Prismaobject bepaalt
     *              breedte - int die de breedte van het Prismaobject bepaalt
     *              hoogte - int die de hoogte van het Prismaobject bepaalt
     * @return      void
     */       
    private void makePrisma(String naam,String kleur,String materiaal, double x, double y, double z,int lengte, int breedte, int hoogte)
    {
        behandeldVoorwerp = new Prisma(coZon,kleur,materiaal,x,y,z,lengte,breedte,hoogte);
        behandeldVoorwerp.setName(naam);     
        voorwerpenVerzameling.add(behandeldVoorwerp);
    }
     /**
     * Maakt een nieuw Piramideobject aan op basis van de door de gebruiker ingegeven parameters en voegt dit object toe aan de voorwerpenVerzameling.
     * 
     * 
     * @param       naam - String die de naam van het aan te maken voorwerp bevat
     *              kleur - String die de naam van de kleur van het aan te maken voorwerp bevat
     *              materiaal - String die het materiaaltype van de kleur van het aan te maken voorwerp bevat
     *              x - double die de x coordinaat in het assestelsel van de GUI van het aan te maken voorwerp bevat
     *              y - double die de y coordinaat in het assestelsel van de GUI van het aan te maken voorwerp bevat
     *              z - double die de z coordinaat in het assestelsel van de GUI van het aan te maken voorwerp bevat
     *              lengte - int die de lengte van een zijde van het grondvlak van het Piramideobject bepaalt
     *              hoogte - int die de hoogte van het Piramideobject bepaalt
     * @return      void
     */       
    private void makePiramide(String naam,String kleur,String materiaal, double x, double y, double z, int lengte, int hoogte)
    {
        behandeldVoorwerp = new Piramide(coZon,kleur,materiaal,x,y,z,lengte,hoogte);        
        behandeldVoorwerp.setName(naam);
        voorwerpenVerzameling.add(behandeldVoorwerp);
    }
     /**
     * Verwerkt het nieuw aangemaakte voorwerp door het te tekenen, zijn schaduwen aan te maken en deze ook te tekenen en uiteindelijk het object toe te voegen aan de
     * ObjectenComboBox. 
     * 
     * @param       none
     * @return      void
     */           
    private void tekenVoorwerp()
    {
        Vlak tempvlak;
        Vector3D[] tempHoekpunten;
        Vector3D tempNormaal;
        Color kleur;
        
        vlakken = behandeldVoorwerp.getVlakken();
        kleur = behandeldVoorwerp.getKleurVoorwerp();         
              
        for(int z = 0; z<vlakken.size(); z++)
        {
            tempvlak = (Vlak) vlakken.get(z);
            tempHoekpunten = tempvlak.getHoekpunten();
            
            tempNormaal = tempvlak.getNormaal();
            
            int belichting = getBelichting(tempNormaal);
            
            gui.voegVlakToe(tempHoekpunten, belichting, kleur, tempNormaal);
        }

        vlakken = behandeldVoorwerp.getSchaduwVlakken();
        
        for(int z = 0; z<vlakken.size(); z++)
        {
            tempvlak = (Vlak) vlakken.get(z);
            tempHoekpunten = tempvlak.getHoekpunten();
            
            tempNormaal = tempvlak.getNormaal();
            int belichting = getSchaduwBelichting();
        
            gui.voegVlakToe(tempHoekpunten, belichting, tempvlak.getKleur(), tempNormaal); 
        }
        gui.getObjectenComboBox().addItem(behandeldVoorwerp.getNaam());     
    }
     /**
     * Deze methode vraagt, via die GUI methode vraagInfo en op basis van het type voorwerp, de gebruiker om de parameters van een reeds bestaand object te wijzigen. Hierna 
     * worden deze nieuwe parameters toegepast op het voorwerp en wordt, indien het voorwerp niet snijdt met een reeds bestaand voorwerp de methode wijzigVoorwerp opgeroepen.
     * @param       none
     * @return      void
     */       
    private void vraagWijzigingen()
    {
        Double xco = new Double(behandeldVoorwerp.getLocatie().getX());
        Double yco = new Double(behandeldVoorwerp.getLocatie().getY());
        Double zco = new Double(behandeldVoorwerp.getLocatie().getZ());
        Integer lengte = new Integer(behandeldVoorwerp.getLbh()[0]);
        Integer breedte = new Integer(behandeldVoorwerp.getLbh()[1]);
        Integer hoogte = new Integer(behandeldVoorwerp.getLbh()[2]);
        int[] dimensies = new int[3];
        String[] wijzigingen = new String[8];
        
        if(behandeldVoorwerp instanceof Kubus)                                            
        {
            wijzigingen = new String[7];

            String[] tmp1 = new String[]{"naam","kleur","materiaal","x positie","y positie","z positie","lengte"};
            String[] tmp2 = new String[]{behandeldVoorwerp.getNaam(),behandeldVoorwerp.getKleur(),behandeldVoorwerp.getMateriaal(),xco.toString(),yco.toString(),zco.toString(),lengte.toString()};
            wijzigingen = gui.vraagInfo("Pas onderstaande velden aan.","Wijzig de bestaande kubus.",tmp1,tmp2);           

            dimensies[0]=Integer.parseInt(wijzigingen[6]);
            dimensies[1]=Integer.parseInt(wijzigingen[6]);
            dimensies[2]=Integer.parseInt(wijzigingen[6]);
            
            tempVoorwerp = new Kubus(coZon,"rood","kurk",0,0,0,1);

        }
        if(behandeldVoorwerp instanceof Balk && !(behandeldVoorwerp instanceof Kubus))          
        {
            wijzigingen = new String[9];
            String[] tmp1 = new String[]{"naam","kleur","materiaal","x positie","y positie","z positie","lengte","breedte","hoogte"};
            String[] tmp2 = new String[]{behandeldVoorwerp.getNaam(),behandeldVoorwerp.getKleur(),behandeldVoorwerp.getMateriaal(),xco.toString(),yco.toString(),zco.toString(),lengte.toString(),breedte.toString(),hoogte.toString()};
            wijzigingen = gui.vraagInfo("Pas onderstaande velden aan.","Wijzig de bestaande balk.",tmp1,tmp2);    
            
            dimensies[0]=Integer.parseInt(wijzigingen[6]);
            dimensies[1]=Integer.parseInt(wijzigingen[7]);
            dimensies[2]=Integer.parseInt(wijzigingen[8]);

            tempVoorwerp = new Balk(coZon,"rood","kurk",0,0,0,1,1,1);
        }
        if(behandeldVoorwerp instanceof Piramide)                                               
        {
            wijzigingen = new String[8];
            String[] tmp1 = new String[]{"naam","kleur","materiaal","x positie","y positie","z positie","lengte","hoogte"};
            String[] tmp2 = new String[]{behandeldVoorwerp.getNaam(),behandeldVoorwerp.getKleur(),behandeldVoorwerp.getMateriaal(),xco.toString(),yco.toString(),zco.toString(),lengte.toString(),hoogte.toString()};
            wijzigingen = gui.vraagInfo("Pas onderstaande velden aan.","Wijzig de bestaande piramide.",tmp1,tmp2);  
            
            dimensies[0]=Integer.parseInt(wijzigingen[6]);
            dimensies[1]=Integer.parseInt(wijzigingen[6]);
            dimensies[2]=Integer.parseInt(wijzigingen[7]);
            
            tempVoorwerp = new Piramide(coZon,"rood","kurk",0,0,0,1,1);
        }
        if(behandeldVoorwerp instanceof Prisma)                                            
        {
            wijzigingen = new String[9];
            String[] tmp1 = new String[]{"naam","kleur","materiaal","x positie","y positie","z positie","lengte","breedte","hoogte"};
            String[] tmp2 = new String[]{behandeldVoorwerp.getNaam(),behandeldVoorwerp.getKleur(),behandeldVoorwerp.getMateriaal(),xco.toString(),yco.toString(),zco.toString(),lengte.toString(),breedte.toString(),hoogte.toString()};
            wijzigingen = gui.vraagInfo("Pas onderstaande velden aan.","Wijzig de bestaande prisma.",tmp1,tmp2);           
            
            dimensies[0]=Integer.parseInt(wijzigingen[6]);
            dimensies[1]=Integer.parseInt(wijzigingen[7]);
            dimensies[2]=Integer.parseInt(wijzigingen[8]);
            
            tempVoorwerp = new Prisma(coZon,"rood","kurk",0,0,0,1,1,1);
        }
        gui.getObjectenComboBox().removeItem(behandeldVoorwerp.getNaam());
        gui.getObjectenComboBox().addItem(wijzigingen[0]);
        behandeldVoorwerp.setName(wijzigingen[0]);
        behandeldVoorwerp.setKleurVoorwerp(wijzigingen[1]);
        behandeldVoorwerp.setDimensies(dimensies);      
        behandeldVoorwerp.setLocatie(Double.parseDouble(wijzigingen[3]),Double.parseDouble(wijzigingen[4]),Double.parseDouble(wijzigingen[5]));
        behandeldVoorwerp.setMateriaal(wijzigingen[2]);
        
        //we maken een nieuw voorwerp dat eruit ziet zoals behandeldvoorwerp zou moeten. Van hieruit kunnen we de veldjes overkopieren naar behandeldvoorwerp en zo toch onze originele vectoren behouden
        tempVoorwerp.setDimensies(dimensies);                      
        tempVoorwerp.setLocatie(Double.parseDouble(wijzigingen[3]),Double.parseDouble(wijzigingen[4]),Double.parseDouble(wijzigingen[5]));
        tempVoorwerp.zoekHoekpunten();
        tempVoorwerp.maakVlakken();
        tempVoorwerp.maakSchaduwVlakken();
        
                    
        if(!controleerOpSnijden()) 
        {
              wijzigVoorwerp();                                
        }
        else
        {                                    
              System.out.println("ze snijden");
        }      
    }
     /**
     * Deze methode kopieert een bestaand object, maar met gewijzigde zoncoordinaat, opdat dit gekopieerde object gebruikt kan worden om wijzigingen toe te brengen in de 
     * methode wijzigVoorwerp.
     * 
     * @param       none
     * @return      void
     */          
    private void pasVoorwerpAan()
    {
        if(behandeldVoorwerp instanceof Kubus) tempVoorwerp = new Kubus(zon.getCoordinatenZon(),behandeldVoorwerp.getKleur(),behandeldVoorwerp.getMateriaal(),behandeldVoorwerp.getLocatie().getX(),behandeldVoorwerp.getLocatie().getY(),behandeldVoorwerp.getLocatie().getZ(),behandeldVoorwerp.getLbh()[0]); 
        if(behandeldVoorwerp instanceof Balk && !(behandeldVoorwerp instanceof Kubus)) tempVoorwerp = new Balk(zon.getCoordinatenZon(),behandeldVoorwerp.getKleur(),behandeldVoorwerp.getMateriaal(),behandeldVoorwerp.getLocatie().getX(),behandeldVoorwerp.getLocatie().getY(),behandeldVoorwerp.getLocatie().getZ(),behandeldVoorwerp.getLbh()[0],behandeldVoorwerp.getLbh()[1],behandeldVoorwerp.getLbh()[2]); 
        if(behandeldVoorwerp instanceof Piramide) tempVoorwerp = new Piramide(zon.getCoordinatenZon(),behandeldVoorwerp.getKleur(),behandeldVoorwerp.getMateriaal(),behandeldVoorwerp.getLocatie().getX(),behandeldVoorwerp.getLocatie().getY(),behandeldVoorwerp.getLocatie().getZ(),behandeldVoorwerp.getLbh()[0],behandeldVoorwerp.getLbh()[2]); 
        if(behandeldVoorwerp instanceof Prisma) tempVoorwerp = new Prisma(zon.getCoordinatenZon(),behandeldVoorwerp.getKleur(),behandeldVoorwerp.getMateriaal(),behandeldVoorwerp.getLocatie().getX(),behandeldVoorwerp.getLocatie().getY(),behandeldVoorwerp.getLocatie().getZ(),behandeldVoorwerp.getLbh()[0],behandeldVoorwerp.getLbh()[1],behandeldVoorwerp.getLbh()[2]); 
        
        wijzigVoorwerp();
    }
     /**
     * Deze methode wijzigt een voorwerp en zijn schaduwen op basis van de wijzigingen die eerder werden opgegeven door de gebruiker. 
     * @param       none
     * @return      void
     */          
    private void wijzigVoorwerp()                
    {
        //nu stellen we de coordinaten van de vlakken van behandeldvoorwerp in op hun nieuwe waarden
        for (int i = 0; i<behandeldVoorwerp.getVlakken().size();i++)
        {
            for (int p = 0; p<((Vlak) behandeldVoorwerp.getVlakken().get(i)).getHoekpunten().length;p++)
            {
                ((Vlak) behandeldVoorwerp.getVlakken().get(i)).getHoekpunten()[p].setX(((Vlak) tempVoorwerp.getVlakken().get(i)).getHoekpunten()[p].getX());
                ((Vlak) behandeldVoorwerp.getVlakken().get(i)).getHoekpunten()[p].setY(((Vlak) tempVoorwerp.getVlakken().get(i)).getHoekpunten()[p].getY());
                ((Vlak) behandeldVoorwerp.getVlakken().get(i)).getHoekpunten()[p].setZ(((Vlak) tempVoorwerp.getVlakken().get(i)).getHoekpunten()[p].getZ());
            }
        }
        
        //nu stellen we de coordinaten van de schaduwvlakken van behandeldvoorwerp in op hun nieuwe waarden
        for (int i = 0; i<behandeldVoorwerp.getSchaduwVlakken().size();i++)
        {
            for (int p = 0; p<((Vlak) behandeldVoorwerp.getSchaduwVlakken().get(i)).getHoekpunten().length;p++)
            {
                ((Vlak) behandeldVoorwerp.getSchaduwVlakken().get(i)).getHoekpunten()[p].setX(((Vlak) tempVoorwerp.getSchaduwVlakken().get(i)).getHoekpunten()[p].getX());
                ((Vlak) behandeldVoorwerp.getSchaduwVlakken().get(i)).getHoekpunten()[p].setY(((Vlak) tempVoorwerp.getSchaduwVlakken().get(i)).getHoekpunten()[p].getY());
                ((Vlak) behandeldVoorwerp.getSchaduwVlakken().get(i)).getHoekpunten()[p].setZ(((Vlak) tempVoorwerp.getSchaduwVlakken().get(i)).getHoekpunten()[p].getZ());
            }
        }
        
        //we gaan over tot het tekenen
        Vlak tempvlak;
        Vector3D tempNormaal;
        vlakken = new ArrayList();
        Vector3D[] tempHoekpunten;
        Color kleur;  
             
        vlakken = behandeldVoorwerp.getVlakken();                                 
        kleur = behandeldVoorwerp.getKleurVoorwerp();

        for(int z = 0; z<vlakken.size(); z++)
        {   
            tempvlak = (Vlak) vlakken.get(z);
            tempHoekpunten = tempvlak.getHoekpunten();
            tempNormaal = tempvlak.getNormaal();
            int belichting = getBelichting(tempNormaal);
            gui.veranderVlak(tempHoekpunten, belichting, kleur, tempNormaal);
        }                  
                          
             
        vlakken = new ArrayList();
        vlakken = behandeldVoorwerp.getSchaduwVlakken();
             
        for(int z = 0; z<vlakken.size(); z++)
       {   
           tempvlak = (Vlak) vlakken.get(z);
           tempHoekpunten = tempvlak.getHoekpunten();
           tempNormaal = tempvlak.getNormaal();
           int schaduwBelichting = getSchaduwBelichting();
           gui.veranderVlak(tempHoekpunten, schaduwBelichting, Color.gray, tempNormaal);
        }             
        voorwerpenVerzameling.set(index,behandeldVoorwerp);
  
    }
     /**
     * Deze methode verwijderd een voorwerp uit de GUI, de ObjectenComboBox en de voorwerpenVerzameling.
     * 
     * @param       none
     * @return      void
     */          
    private void verwijderVoorwerp()
    {
        Vlak tempvlak;             
        vlakken = new ArrayList();
        vlakken=behandeldVoorwerp.getVlakken();                               
        Vector3D[] tempHoekpunten;
             
        for(int z = 0; z<vlakken.size(); z++)
        {                       
            gui.verwijderVlak(((Vlak) vlakken.get(z)).getHoekpunten());
        }                  
             
        vlakken = new ArrayList();
        vlakken = behandeldVoorwerp.getSchaduwVlakken();
             
       for(int z = 0; z<vlakken.size(); z++)
       {                     
           gui.verwijderVlak(((Vlak) vlakken.get(z)).getHoekpunten());
       }                  
             
       voorwerpenVerzameling.remove(index);          
    }
     /**
     * Deze methode zoekt het zwaarste bestaande voorwerp en retourneert de naam ervan
     * 
     * @param       none
     * @return      String - de naam van het zwaarste voorwerp
     */          
    public String getZwaarsteVoorwerp()
    {
        double zwaarsteVoorwerp = 0;
        String zwaarsteVoorwerpNaam = new String();
        for(int z=0;z<voorwerpenVerzameling.size();z++)
        {
            if(((Voorwerp)voorwerpenVerzameling.get(z)).getMassa()>zwaarsteVoorwerp)
            {
                zwaarsteVoorwerp = ((Voorwerp)voorwerpenVerzameling.get(z)).getMassa();
                zwaarsteVoorwerpNaam = ((Voorwerp)voorwerpenVerzameling.get(z)).getNaam();
            }           
        }
        return zwaarsteVoorwerpNaam;
    }
     /**
     * Deze methode berekent de gemiddelde massa van alle bestaande voorwerpen en retourneert deze waarde
     * 
     * @param       none
     * @return      String
     */         
    public double getGemiddeldeMassa()
    {
        double totaleMassa = 0;
        for(int z = 0; z<voorwerpenVerzameling.size();z++)
        {
            totaleMassa = totaleMassa + ((Voorwerp)voorwerpenVerzameling.get(z)).getMassa();
        }
        return totaleMassa/voorwerpenVerzameling.size();                      
            
    }
     /**
     * Deze methode berekent de gemiddelde massa van alle bestaande voorwerpen en retourneert deze waarde
     * 
     * @param       none
     * @return      double - de gemiddelde massa
     */             
    public int getAantalVoorwerpen()
    {
        return voorwerpenVerzameling.size();
    }
     /**
     * Deze methode berekent het gemiddelde volume van alle bestaande voorwerpen en retourneert deze waarde
     * 
     * @param       none
     * @return      double - het gemiddelde volume
     */     
    public double getGemiddeldVolume()
    {
        double totaleVolume = 0;
        for(int z = 0; z<voorwerpenVerzameling.size();z++)
        {
            totaleVolume = totaleVolume + ((Voorwerp)voorwerpenVerzameling.get(z)).getVolume();
        }
        return totaleVolume/voorwerpenVerzameling.size();                      
            
    }
     /**
     * Deze methode loopt door de voorwerpenVerzameling en controleert via de methode getSnijdenVlakken op snijdingen tussen elk van deze Voorwerpen en het te 
     * behandelen voorwerp. Uiteraard controleert het voorwerp niet op snijdingen met zichzelf. 
     * 
     * @param       none
     * @return      boolean - true als er snijdingen met een bestaand voorwerp worden gedetecteerd, anders false
     */     
    private boolean controleerOpSnijden()
    {
        if (voorwerpenVerzameling.size() == 1) return false;
        
        for(int i = 0;i<voorwerpenVerzameling.size();i++)
        {
            if(i == index)
            {
                return false;
            }
            
            if (getSnijdenVlakken(behandeldVoorwerp, (Voorwerp) voorwerpenVerzameling.get(i))) {
                return true;   
            }
        }
        return false;
    }     
    
     /**
     * Deze methode controleert op snijdingen tussen de twee meegegeven voorwerpen.
     * 
     * @param       voorw1 - het Voorwerp in behandeling
     *              voorw2 - het Voorwerp opgehaald uit de voorwerpenVerzameling in de methode controleerOpSnijden
     * @return      boolean - true als er snijdingen tussen de twee objecten worden gedetecteerd, anders false
     */     
    
    private boolean getSnijdenVlakken(Voorwerp voorw1, Voorwerp voorw2)
    {
        int vw1_xco = (int) voorw1.getLocatie().getX();
        int vw1_yco = (int) voorw1.getLocatie().getY();
        int vw1_zco = (int) voorw1.getLocatie().getZ();
        
        int vw2_xco = (int) voorw2.getLocatie().getX();
        int vw2_yco = (int) voorw2.getLocatie().getY();
        int vw2_zco = (int) voorw2.getLocatie().getZ();
        
        int vw1_lengte = voorw1.getLbh()[0];
        int vw1_breedte = voorw1.getLbh()[1];
        int vw1_hoogte = voorw1.getLbh()[2];

        int vw2_lengte = voorw2.getLbh()[0];
        int vw2_breedte = voorw2.getLbh()[1];
        int vw2_hoogte = voorw2.getLbh()[2];
        
        int vw1_x_bound1= vw1_xco - vw1_lengte/2;
        int vw1_x_bound2= vw1_xco + vw1_lengte/2;
        int vw2_x_bound1= vw2_xco - vw2_lengte/2;
        int vw2_x_bound2= vw2_xco + vw2_lengte/2;

        int vw1_y_bound1= vw1_yco - vw1_breedte/2;
        int vw1_y_bound2= vw1_yco + vw1_breedte/2;
        int vw2_y_bound1= vw2_yco - vw2_breedte/2;
        int vw2_y_bound2= vw2_yco + vw2_breedte/2;
            
        int vw1_z_bound1= vw1_zco;
        int vw1_z_bound2= vw1_zco + vw1_hoogte;
        int vw2_z_bound1= vw2_zco;
        int vw2_z_bound2= vw2_zco + vw2_hoogte;    

        //we controleren of vw2 ergens coordinaten heeft die binnen het gebied van vw1 liggen        
        if (((vw2_x_bound1 <=  vw1_x_bound2) && (vw2_x_bound1 >=  vw1_x_bound1)) || ((vw2_x_bound2 <=  vw1_x_bound2) && (vw2_x_bound2 >=  vw1_x_bound1))) {
         
            //de xcoordinaten 'overlappen', doe de test nu voor y
            if (((vw2_y_bound1 <=  vw1_y_bound2) && (vw2_y_bound1 >=  vw1_y_bound1)) || ((vw2_y_bound2 <=  vw1_y_bound2) && (vw2_y_bound2 >=  vw1_y_bound1))) {
            
                //de x en y coordinaten 'overlappen', doe de test nu tenslotte voor z
                if (((vw2_z_bound1 <=  vw1_z_bound2) && (vw2_z_bound1 >=  vw1_z_bound1)) || ((vw2_z_bound2 <= vw1_z_bound2) && (vw2_z_bound2 >= vw1_z_bound1))) {
                
                    //de x en y en z coordinaten overlappen, dit kan enkel betekenen dat de objecten overlappen
                    return true;                
                }
            }
        }
        
        //indien vorige test niet resulteerde in true mogen we toch nog niet false besluiten, dit komt omdat vw1 volledig in vw2 kan gelegen zijn, wat we ook niet willen.
        //we controleren of vw1 ergens coordinaten heeft die binnen het gebied van vw2 liggen        
        if (((vw1_x_bound1 <=  vw2_x_bound2) && (vw1_x_bound1 >=  vw2_x_bound1)) || ((vw1_x_bound2 <=  vw2_x_bound2) && (vw1_x_bound2 >=  vw2_x_bound1))) {
         
            //de xcoordinaten 'overlappen', doe de test nu voor y
            if (((vw1_y_bound1 <=  vw2_y_bound2) && (vw1_y_bound1 >=  vw2_y_bound1)) || ((vw1_y_bound2 <=  vw2_y_bound2) && (vw1_y_bound2 >=  vw2_y_bound1))) {
            
                //de x en y coordinaten 'overlappen', doe de test nu tenslotte voor z
                if (((vw1_z_bound1 <=  vw2_z_bound2) && (vw1_z_bound1 >=  vw2_z_bound1)) || ((vw1_z_bound2 <=  vw2_z_bound2) && (vw1_z_bound2 >=  vw2_z_bound1))) {
                
                    //de x en y en z coordinaten overlappen, dit kan enkel betekenen dat de objecten overlappen
                    return true ;              
                }
            }
        }
        //als we hier zijn zijn we zeker dat ze niet snijden, de vlakken van de omhullende balk kunnen echter wel nog ALLEMAAL rakend zijn. Dit mogen we niet toelaten
       if ((vw1_x_bound1 ==  vw2_x_bound1) && (vw1_x_bound2 ==  vw2_x_bound2)) {
         
            //de xcoordinaten 'overlappen', doe de test nu voor y
            if ((vw1_y_bound1 ==  vw2_y_bound1) && (vw1_y_bound2 ==  vw2_y_bound2)) {
            
                //de x en y coordinaten 'overlappen', doe de test nu tenslotte voor z
                if ((vw1_z_bound1 ==  vw2_z_bound1) && (vw1_z_bound2 ==  vw2_z_bound2)) {
                
                    //de x en y en z coordinaten overlappen, dit kan enkel betekenen dat de objecten overlappen
                    return true ;              
                }
            }
        }
        
        //als we tot hier zijn geraakt zijn we veilig, de voorwerpen overlappen niet
        return false;
        
    }
     /**
     * Deze methode bepaalt de belichtingsgraad van een vlak op basis van zijn normaal. De belichtingsgraad is nul als de z coordinaat van de zon kleiner dan nul is.
     * 
     * @param       vlakNormaal - Vector3D die de normaal van een vlak voorstelt
     * @return      int - de belichtingsgraad
     */        
    private int getBelichting(Vector3D vlakNormaal)
    {
        double alfa = Math.toDegrees(Math.acos(vlakNormaal.cosinus(zon.getCoordinatenZon())));
        if(alfa>90)
        {
            return 0;
        }
            
        Double belichting = new Double((100 - alfa)/10);
        
        if(zon.getCoordinatenZon().getZ()<=0)
        {
           return 0;
        }               
        
        return belichting.intValue();
    }
     /**
     * Deze methode bepaalt de belichtingsgraad van een schaduwvlak. Deze is standaard 5, maar krijgt de waarde 0 wanneer de zon onder is.
     * 
     * @param       none
     * @return      int - de belichtingsgraad van het schaduwvlak
     */        
    private int getSchaduwBelichting()
    {
        if(zon.getCoordinatenZon().getZ()>0)
        {
            return 5;
        }
        else
        {
            return 0;
        }
    }
        
    
    

}
