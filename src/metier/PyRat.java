package metier;

import java.util.*;

public class PyRat {

    private Set<Point> setFromages;
    private Map<Point, List<Point>> laby;

    /* Méthode appelée une seule fois permettant d'effectuer des traitements "lourds" afin d'augmenter la performace de la méthode turn. */
    public void preprocessing(Map<Point, List<Point>> laby, int labyWidth, int labyHeight, Point position, List<Point> fromages) {
        setFromages = new HashSet<>(fromages);
//        for (Point p : fromages){
//            setFromages.add(p);
//        }
    }

    /* Méthode de test appelant les différentes fonctionnalités à développer.
        @param laby - Map<Point, List<Point>> contenant tout le labyrinthe, c'est-à-dire la liste des Points, et les Points en relation (passages existants)
        @param labyWidth, labyHeight - largeur et hauteur du labyrinthe
        @param position - Point contenant la position actuelle du joueur
        @param fromages - List<Point> contenant la liste de tous les Points contenant un fromage. */
    public void turn(Map<Point, List<Point>> laby, int labyWidth, int labyHeight, Point position, List<Point> fromages) {
        Point pt1 = new Point(2,1);
        Point pt2 = new Point(3,1);
        this.laby = laby;
        System.out.println((fromageIci(pt1, fromages) ? "Il y a un" : "Il n'y a pas de") + " fromage ici, en position " + pt1);
        System.out.println((fromageIci_EnOrdreConstant(pt2) ? "Il y a un" : "Il n'y a pas de") + " fromage ici, en position " + pt2);
        System.out.println((passagePossible(pt1, pt2, laby) ? "Il y a un" : "Il n'y a pas de") + " passage de " + pt1 + " vers " + pt2);
        System.out.println((passagePossible_EnOrdreConstant(pt1, pt2) ? "Il y a un" : "Il n'y a pas de") + " passage de " + pt1 + " vers " + pt2);
        System.out.println("Liste des points inatteignables depuis la position " + position + " : " + pointsInatteignables(position));
    }

    /* Regarde dans la liste des fromages s’il y a un fromage à la position pos.
        @return true s'il y a un fromage à la position pos, false sinon. */
    private boolean fromageIci(Point pos, List<Point> listPoint) {
//        for (int i = 0; i < listPoint.size(); i++){
//            if (listPoint.get(i).equals(pos)){
//                return true;
//            }
//        }
//        return false;
//        if (listPoint.contains(pos)){
//            return true;
//        }
//        return false;
        return listPoint.contains(pos);
    }

    /* Regarde de manière performante (accès en ordre constant) s’il y a un fromage à la position pos.
        @return true s'il y a un fromage à la position pos, false sinon. */
    private boolean fromageIci_EnOrdreConstant(Point pos) {
        return setFromages.contains(pos);

    }

    /* Indique si le joueur peut passer de la position (du Point) « de » au point « a ».
        @return true s'il y a un passage depuis  « de » vers « a ». */
    private boolean passagePossible(Point de, Point a, Map<Point, List<Point>> laby) {
        //System.out.println(laby);

        return laby.get(de).contains(a);
    }

    /* Indique si le joueur peut passer de la position (du Point) « de » au point « a »,
        mais sans devoir parcourir la liste des Points se trouvant dans la Map !
        @return true s'il y a un passage depuis  « de » vers « a ». */
    private boolean passagePossible_EnOrdreConstant(Point de, Point a) {
        return false;
    }

    /* Retourne la liste des points qui ne peuvent pas être atteints depuis la position « pos ».
        @return la liste des points qui ne peuvent pas être atteints depuis la position « pos ». */
    private List<Point> pointsInatteignables(Point pos) {
        List<Point> inatteignables = new ArrayList<>();
        List<Point> chemin = new ArrayList<>();
        parcoursRecursif(pos, chemin);
        for (Point p : laby.keySet()){
            if (!chemin.contains(p)){
                inatteignables.add(p);
            }
        }
        return inatteignables;
    }

    private void parcoursRecursif(Point pos, List<Point> chemin){
        System.out.println(pos);
        chemin.add(pos);
        for (Point voisin : laby.get(pos)){
            if (!chemin.contains(pos)){
                parcoursRecursif(voisin, chemin);
            }
        }
    }
}