/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Games;
import domain.comparatorCrescentes.*;
import domain.comparatorDecrescentes.*;
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
public class GamesFileDao {

    public void salvarGames(Vector<Games> games) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileOutputStream arquivo = new FileOutputStream(GamesFileInformation.getCaminhoArquivo() + GamesFileInformation.getNomeArquivo());

        //Classe responsavel por inserir os objetos
        ObjectOutputStream objGravar = new ObjectOutputStream(arquivo);

        //Grava o objeto de vetor de games no arquivo
        objGravar.writeObject(games);
        objGravar.flush();
        objGravar.close();
        arquivo.flush();
        arquivo.close();
    }

    private boolean estaArquivoVazio() throws FileNotFoundException, IOException {
        //Carrega o arquivo
        String local_arquivo = GamesFileInformation.getCaminhoArquivo() + GamesFileInformation.getNomeArquivo();
        FileInputStream arquivoLeitura = new FileInputStream(local_arquivo);
        boolean estaVazio = (arquivoLeitura.read() == -1);
        arquivoLeitura.close();
        return estaVazio;
    }

    public Vector<Games> obterGames() throws FileNotFoundException, IOException, ClassNotFoundException {
        //Carrega o arquivo
        if (estaArquivoVazio()) {
            return new Vector();
        } else {
            //Classe responsavel por recuperar os objetos do arquivo
            String local_arquivo = GamesFileInformation.getCaminhoArquivo() + GamesFileInformation.getNomeArquivo();
            FileInputStream arquivoLeitura = new FileInputStream(local_arquivo);
            ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
            Vector<Games> vetor = (Vector<Games>) objLeitura.readObject();
            objLeitura.close();
            arquivoLeitura.close();
            return vetor;
        }
    }

   
    public Vector<Games> obterGame(String coluna, boolean crescente) throws FileNotFoundException, IOException, ClassNotFoundException {
        Vector<Games> games = this.obterGames();
        if (coluna.equals("lancamento")){
            if (crescente) Collections.sort(games, new GamesPorLancamentoCrescente());
            else Collections.sort(games, new GamesPorLancamentoDecrescente());
        }
        else if (coluna.equals("metacritic")){
            if (crescente) Collections.sort(games, new GamesPorMetaCriticCrescemte());
            else Collections.sort(games, new GamesPorMetaCriticDecrescente());
        }
        else if (coluna.equals("nome")){
            if (crescente) Collections.sort(games, new GamesPorNomeCrescente());
            else Collections.sort(games, new GamesPorNomeDecrescente());
        }
        return games;
    }
}
