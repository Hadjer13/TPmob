package dz.esi.commerce.mshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 18/04/2016.
 */
public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> seconnecter = new ArrayList<String>();
        seconnecter.add("Pour se connecter à votre compte:" +
                                "1.Rendez-vous sur Mshop.dz." +
                                "2.En haut de la page d'acceuil.");


        List<String> football = new ArrayList<String>();
        football.add("Brazil");
        football.add("Spain");
        football.add("Germany");
        football.add("Netherlands");
        football.add("Italy");

        List<String> MonMshop = new ArrayList<String>();
        MonMshop.add("Données Personnelles");
        MonMshop.add("Mes commandes");
        MonMshop.add("Mes achats");
        MonMshop.add("Deconnexion");


        expandableListDetail.put("CRICKET TEAMS", seconnecter);
        expandableListDetail.put("FOOTBALL TEAMS", football);
        expandableListDetail.put("MonMshop", MonMshop);
        return expandableListDetail;
    }
}
