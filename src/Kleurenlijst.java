import java.awt.Color;
import java.util.HashMap;

/**
 *  Deze klasse bevat een kleurenlijst. Via deze klasse is het heel gemakkelijk de naam van een kleur te mappen met de klasse Kleur.
 * 
 * @author Jorrit Vander Mynsbrugge, Wim Van Damme, Antoine Vandermeersch, 
 * @version 2006-05-11
 */
public class Kleurenlijst
{
    // Een HashMap die de voorgedefinieerde kleuren bevat
    private HashMap kleurLijst;

    /**
     * Constructor voor objecten van de klasse Kleurenlijst
     */
    public Kleurenlijst()
    {
        kleurLijst = new HashMap();
        kleurLijst.put("rood",Color.red);
        kleurLijst.put("blauw",Color.blue);
        kleurLijst.put("magenta",Color.magenta);
        kleurLijst.put("geel",Color.yellow);
    }

    /**
     * retourneert een kleur wanneer een kleurnaam is meegegeven.
     * 
     * @param       kleur - bevat de naam van de kleur - type: string 
     * @return      deKleur - type: Color
     */
    public Color getKleur(String kleur)
    {
        Color deKleur = (Color) kleurLijst.get(kleur);
        return deKleur;
    }
    
}