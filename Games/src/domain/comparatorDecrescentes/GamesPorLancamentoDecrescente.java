package domain.comparatorDecrescentes;

import java.util.Comparator;
import domain.Games;

public class GamesPorLancamentoDecrescente implements Comparator<Games>{

    @Override
    public int compare(Games o1, Games o2) {
        if (o1.getAnoLancamento() > o2.getAnoLancamento()) {
            return - 1;
        } else if (o1.getAnoLancamento() == o2.getAnoLancamento()) {
            return 0;
        } else {
            return 1;
        }
    }
    
}
