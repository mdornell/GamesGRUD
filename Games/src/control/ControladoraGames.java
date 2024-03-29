/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import dao.*;
import domain.Games;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;


public class ControladoraGames {
    private Vector<Games> games;
    private int marcador;
    GamesFileDao gamesDao;
    
    private String obterNomeColunaBanco(String coluna) {
        if (coluna.equals("Nome"))
            return "nome";
        if (coluna.equals("Ano Lançamento"))
            return "lancamento";
        if (coluna.equals("Meta Critic"))
            return "metacritic";
        return "id";
    }

    public ControladoraGames() {
        this.gamesDao = new GamesFileDao();
    }
    
    private void atualizarGames(Games game, Vector linha) {
        game.setAnoLancamento(Integer.parseInt(linha.get(1).toString()));
        game.setNome(linha.get(0).toString());
        game.setNotaMetaCritic(Double.parseDouble(linha.get(2).toString()));
    }
    
    private Vector criarLinhaGames(Games game) {
        Vector linha = new Vector();
        linha.addElement(game.getNome());
        linha.addElement(game.getAnoLancamento());
        linha.addElement(game.getNotaMetaCritic());
        return linha;
    }
    
    public void inserirNewGame(Vector linha) throws FileNotFoundException, IOException, ClassNotFoundException {
        Games novoGame = new Games();
        this.atualizarGames(novoGame, linha);
        this.games.add(novoGame);
        gamesDao.salvarGames(this.games);
    }
    
    
    public void setMarcador(int marcador){
        this.marcador = marcador;
    }

    public void alterarGames(Vector linha) throws FileNotFoundException, IOException, ClassNotFoundException {
        Games gameAtual = games.get(marcador);
        this.atualizarGames(gameAtual, linha);
        gamesDao.salvarGames(this.games);
    }
    
    
    public void excluirGame() throws FileNotFoundException, IOException, ClassNotFoundException {
        if (marcador >= 0 && marcador < games.size()) {
            games.remove(marcador);
            gamesDao.salvarGames(this.games);
        } else {
            // Tratando o caso em que o marcador está fora dos limites
            System.out.println("Índice inválido para exclusão.");
        }
    }
    
    
    private Vector<Games> obterGames(String coluna, boolean crescente) throws FileNotFoundException, IOException, ClassNotFoundException {
        String nomeColunaBanco = this.obterNomeColunaBanco(coluna);
        games = gamesDao.obterGame(nomeColunaBanco, crescente);
        return games;
    }
    

    public Vector obterLinhasGames(String coluna, boolean crescente) throws FileNotFoundException, IOException, ClassNotFoundException {
        Vector<Games> games = obterGames(coluna, crescente);
        Vector linhas = new Vector();
        
        // Montando as linhas
        for(int i = 0; i < games.size(); i++){
            Games game = games.get(i);
            linhas.addElement(this.criarLinhaGames(game));
        }
        return linhas;
    }
    
}
