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
        if (coluna.equals("Código"))
            return "codigo";
        if (coluna.equals("Nome"))
            return "nome";
        if (coluna.equals("Genero"))
            return "genero";
        if (coluna.equals("Produtora"))
            return "produtora";
        if (coluna.equals("Data Compra"))
            return "datacompra";
        return "id";
    }

    public ControladoraGames() {
        this.filmeDao = new GamesFileDao();
    }
    
    private void atualizarFilme(Filme filme, Vector linha){
        filme.setCodigo(linha.get(0).toString());
        filme.setNome(linha.get(1).toString());
        filme.setGenero(linha.get(2).toString());
        filme.setProdutora(linha.get(3).toString());
        filme.setDatacompra(linha.get(4).toString());       
    }
    
    private Vector criarLinhaFilme(Filme filme) {
        Vector linha = new Vector();
        linha.addElement(filme.getCodigo());
        linha.addElement(filme.getNome());
        linha.addElement(filme.getGenero());
        linha.addElement(filme.getProdutora());
        linha.addElement(filme.getDatacompra());
        return linha;
    }
     
    
    public void inserirNovoFilme(Vector linha) throws FileNotFoundException, IOException, ClassNotFoundException{
        Filme filme = new Filme();
        this.atualizarFilme(filme, linha);
        this.filmes.add(filme);
        filmeDao.salvarFilmes(this.filmes);
    }
    
    public void setMarcador(int marcador){
        this.marcador = marcador;
    }

    public void alterarFilme(Vector linha) throws FileNotFoundException, IOException, ClassNotFoundException {
        Filme filme = filmes.get(marcador);
        this.atualizarFilme(filme, linha);
        filmeDao.salvarFilmes(this.filmes);
    }
    
    public void  excluirFilme() throws FileNotFoundException, IOException, ClassNotFoundException{
        filmes.remove(marcador);
        filmeDao.salvarFilmes(this.filmes);
    }
    
    private Vector<Filme> obterFilmes(String coluna, boolean crescente) throws FileNotFoundException, IOException, ClassNotFoundException{
        String nomeColunaBanco = this.obterNomeColunaBanco(coluna);
        filmes = filmeDao.obterFilmes(nomeColunaBanco, crescente);
        return filmes; 
    }

    public Vector obterLinhasFilmes(String coluna, boolean crescente) throws FileNotFoundException, IOException, ClassNotFoundException {
        Vector<Filme> filmes = obterFilmes(coluna, crescente);
        Vector linhas = new Vector();
        
        // Montando as linhas
        for(int i = 0; i < filmes.size(); i++){
            Filme filme = filmes.get(i);
            linhas.addElement(this.criarLinhaFilme(filme));
        }
        return linhas;
    }
}
