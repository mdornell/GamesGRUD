/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import windows.JanelaPrincipalFilme;
import windows.JanelaVisualizarFilme;


public class ControladoraPrincipal {
    public static void main(String argsp[])
    {
        JanelaVisualizarFilme janela = new JanelaPrincipalFilme();
        janela.executar();
    }

}
