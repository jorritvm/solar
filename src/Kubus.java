import java.awt.Color;
import solar.util.Vector3D;


/**
 * Kubus geeft de mogelijkheid objecten van type kubus aan te maken. Het is een subklasse van superklasse Balk, Voorwerp.
 * Een kubus wordt gedefinieerd als een balk met lengte=breedte=hoogte.
 * 
 * @Auteurs: Van Damme Wim, Vander Mynsbrugge Jorrit, Vandermeersch Antoine
 * @Versie: 11/05/06
 */
public class Kubus extends Balk
{
    // geen instantie variabelen zijn nodig deze zijn opgeslagen in superklasse Balk.
    
    /**
     * Constructor voor objecten van klasse Kubus
     */
    public Kubus(Vector3D coZon, String kleur, String materiaal, double x, double y, double z, int lengte)
    {        
         // Alle parameters worden doorgegeven aan superklasse Balk.
         super(coZon,kleur,materiaal,x,y,z,lengte,lengte,lengte);
     }


 

}
