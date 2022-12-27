import java.util.HashMap;
import java.util.ArrayList;


/**
 * Bevat een lijst van materialen
 * Elk materiaal bestaat uit een ArrayList waarin zijn eigenschappen kunnen worden opgenomen. 
 * Momenteel staat er nog maar 1 eigenschap in de arraylist, namelijk dichtheid.
 * 
 * @Auteurs: Vander Mynsbrugge Jorrit, Van Damme Wim, Vandermeersch Antoine
 * @Versie: 2006-05-11
 */

public class Materialenlijst
{
     private HashMap materialenlijst;
     private ArrayList kurk;
     private ArrayList eikenhout;
     private ArrayList asfalt;
     private ArrayList glas;
     private ArrayList aluminium;
     private ArrayList steen;
     private ArrayList ijzer;
          

    /**
     * Constructor voor objecten van de klasse materiaal.
     */
    public Materialenlijst()
    {
        // initialise instance variables
        materialenlijst = new HashMap();
        kurk = new ArrayList();
        eikenhout = new ArrayList();
        asfalt = new ArrayList();
        glas = new ArrayList();
        aluminium = new ArrayList();
        steen = new ArrayList();
        ijzer = new ArrayList();
       
        materialenlijst.put("kurk",kurk);
        materialenlijst.put("eikenhout",eikenhout);
        materialenlijst.put("asfalt",asfalt);
        materialenlijst.put("glas",glas);
        materialenlijst.put("aluminium",aluminium);
        materialenlijst.put("steen",steen);
        materialenlijst.put("ijzer",ijzer);      
        
        kurk.add(new Integer(130));
        eikenhout.add(new Integer(800));
        asfalt.add(new Integer(1300));
        glas.add(new Integer(2600));
        aluminium.add(new Integer(2700));
        steen.add(new Integer(2800));
        ijzer.add(new Integer(7800));
    }

    /**
     * Zoekt de dichtheid van een bepaald type materiaal op.
     * 
     * @param  String materiaal: type materiaal
     * @return int getalwaarde: de dichtheid van het materiaal
     */
    public int getDichtheid(String materiaal)
    {
        return ((Integer)((ArrayList) materialenlijst.get(materiaal)).get(0)).intValue();
    }
    
}
