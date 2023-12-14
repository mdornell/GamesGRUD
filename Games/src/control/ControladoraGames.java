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
        if (coluna.equals("Ano Lançamento"))
            return "lancamento";
        if (coluna.equals("Nome"))
            return "nome";
        if (coluna.equals("Meta Critic"))
            return "metacritic";
        return "id";
    }

    public ControladoraGames() {
        this.gamesDao = new GamesFileDao();
    }
    
    private void atualizarGames(Games game, Vector linha) {
        // Certifique-se de verificar os tipos antes de realizar as conversões
        if (linha.get(0) instanceof Integer) {
            game.setAnoLancamento((Integer) linha.get(0));
        } 
    
        if (linha.get(1) instanceof String) {
            game.setNome((String) linha.get(1));
        } 
    
        if (linha.get(2) instanceof Double) {
            game.setNotaMetaCritic((Double) linha.get(2));
        } 
    }
    
    private Vector<Object> criarLinhaGames(Games game) {
        Vector<Object> linha = new Vector<>();
        linha.add(game.getAnoLancamento());
        linha.add(game.getNome());
        linha.add(game.getNotaMetaCritic());
        return linha;
    }
    
     
    
    public void inserirNovoFilme(Vector linha) throws FileNotFoundException, IOException, ClassNotFoundException {
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
    
    
    public void excluirFilme() throws FileNotFoundException, IOException, ClassNotFoundException {
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
        Vector<Games> gamesResult = gamesDao.obterGame(nomeColunaBanco, crescente);
        return gamesResult;
    }
    

    public Vector obterLinhasGames(String coluna, boolean crescente) throws FileNotFoundException, IOException, ClassNotFoundException {
        Vector<Games> gamesList = obterGames(coluna, crescente);
        Vector linhas = new Vector();
        
        // Montando as linhas
        for(int i = 0; i < gamesList.size(); i++){
            Games game = gamesList.get(i);
            linhas.addElement(this.criarLinhaGames(game));
        }
        return linhas;
    }
    
}
