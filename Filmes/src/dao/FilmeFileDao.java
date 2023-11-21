/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.ComparatorFilmePorCodigoCrescente;
import domain.ComparatorFilmePorCodigoDecrescente;
import domain.ComparatorFilmePorGeneroCrescente;
import domain.ComparatorFilmePorGeneroDecrescente;
import domain.ComparatorFilmePorNomeCrescente;
import domain.ComparatorFilmePorNomeDecrescente;
import domain.Filme;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Vector;

/**
 *
 * @author Giovany
 */
public class FilmeFileDao {

    public void salvarFilmes(Vector<Filme> filmes) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileOutputStream arquivo = new FileOutputStream(FilmeFileInformation.getCaminhoArquivo() + FilmeFileInformation.getNomeArquivo());

        //Classe responsavel por inserir os objetos
        ObjectOutputStream objGravar = new ObjectOutputStream(arquivo);

        //Grava o objeto de vetor de filmes no arquivo
        objGravar.writeObject(filmes);
        objGravar.flush();
        objGravar.close();
        arquivo.flush();
        arquivo.close();
    }

    private boolean estaArquivoVazio() throws FileNotFoundException, IOException {
        //Carrega o arquivo
        String local_arquivo = FilmeFileInformation.getCaminhoArquivo() + FilmeFileInformation.getNomeArquivo();
        FileInputStream arquivoLeitura = new FileInputStream(local_arquivo);
        boolean estaVazio = (arquivoLeitura.read() == -1);
        arquivoLeitura.close();
        return estaVazio;
    }

    public Vector<Filme> obterFilmes() throws FileNotFoundException, IOException, ClassNotFoundException {
        //Carrega o arquivo
        if (estaArquivoVazio()) {
            return new Vector();
        } else {
            //Classe responsavel por recuperar os objetos do arquivo
            String local_arquivo = FilmeFileInformation.getCaminhoArquivo() + FilmeFileInformation.getNomeArquivo();
            FileInputStream arquivoLeitura = new FileInputStream(local_arquivo);
            ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
            Vector<Filme> vetor = (Vector<Filme>) objLeitura.readObject();
            objLeitura.close();
            arquivoLeitura.close();
            return vetor;
        }
    }

   
    public Vector<Filme> obterFilmes(String coluna, boolean crescente) throws FileNotFoundException, IOException, ClassNotFoundException {
        Vector<Filme> filmes = this.obterFilmes();
        if (coluna.equals("codigo")){
            if (crescente) Collections.sort(filmes, new ComparatorFilmePorCodigoCrescente());
            else Collections.sort(filmes, new ComparatorFilmePorCodigoDecrescente());
        }
        else if (coluna.equals("nome")){
            if (crescente) Collections.sort(filmes, new ComparatorFilmePorNomeCrescente());
            else Collections.sort(filmes, new ComparatorFilmePorNomeDecrescente());
        }
        else if (coluna.equals("genero")){
            if (crescente) Collections.sort(filmes, new ComparatorFilmePorGeneroCrescente());
            else Collections.sort(filmes, new ComparatorFilmePorGeneroDecrescente());
        }
        else if (coluna.equals("produtora")){
            // Exercício - o aluno deve terminar
        }
        else if (coluna.equals("datacompra")){
            // Exercício - o aluno deve terminar
        }
        return filmes;
    }
}
