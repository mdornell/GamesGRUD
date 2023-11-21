package domain.comparatorCrescentes;

import java.util.Comparator;
import domain.Games;

public class GamesPorLancamentoCrescente implements Comparator<Games>{

    @Override
    public int compare(Games o1, Games o2) {
        if(o1.getAnoLancamento() < o2.getAnoLancamento()){
            return 1;
        }else{
            return -1;
        }
    }

}
