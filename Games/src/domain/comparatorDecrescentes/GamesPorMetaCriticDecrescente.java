package domain.comparatorDecrescentes;

import java.util.Comparator;
import domain.Games;

public class GamesPorMetaCriticDecrescente implements Comparator<Games>{

    @Override
    public int compare(Games o1, Games o2) {
        if (o1.getNotaMetaCritic() > o2.getNotaMetaCritic()) {
            return - 1;
        } else if (o1.getNotaMetaCritic() == o2.getNotaMetaCritic()) {
            return 0;
        } else {
            return 1;
        }
    }
    
}
