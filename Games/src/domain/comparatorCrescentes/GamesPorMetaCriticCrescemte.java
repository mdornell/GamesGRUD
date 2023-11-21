package domain.comparatorCrescentes;

import java.util.Comparator;
import domain.Games;

public class GamesPorMetaCriticCrescemte implements Comparator<Games> {

    @Override
    public int compare(Games o1, Games o2) {
        if(o1.getNotaMetaCritic() < o2.getNotaMetaCritic()){
            return 1;
        }else{
            return -1;
        }
    }
    
}