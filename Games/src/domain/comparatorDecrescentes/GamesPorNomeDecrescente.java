package domain.comparatorDecrescentes;

import java.util.Comparator;
import domain.Games;

public class GamesPorNomeDecrescente implements Comparator<Games>{

    @Override
    public int compare(Games o1, Games o2) {
        return ((-1) * o1.getNome().compareTo(o2.getNome()));
    }
    
}
