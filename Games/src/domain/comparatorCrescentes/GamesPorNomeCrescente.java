package domain.comparatorCrescentes;

import java.util.Comparator;
import domain.Games;

public class GamesPorNomeCrescente implements Comparator<Games> {
    @Override
    public int compare(Games o1, Games o2) {
        return (o1.getNome().compareTo(o2.getNome()));
    } 
}
