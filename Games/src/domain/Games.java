package domain;

import java.io.Serializable;

public class Games implements Serializable {
    
    private String nome;
    public int anoLancamento;
    private double notaMetaCritic;
    
    public String getNome() {
        return nome;
    }
    public int getAnoLancamento() {
        return anoLancamento;
    }
    public double getNotaMetaCritic() {
        return notaMetaCritic;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }
    public void setNotaMetaCritic(double notaMetaCritic) {
        this.notaMetaCritic = notaMetaCritic;
    }

}
