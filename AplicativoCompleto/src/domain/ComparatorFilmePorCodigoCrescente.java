/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

import java.util.Comparator;

/**
 *
 * @author Giovany
 */
public class ComparatorFilmePorCodigoCrescente implements Comparator<Filme> {
    public int compare(Filme o1, Filme o2) {
        return o1.getCodigo().compareTo(o2.getCodigo());
    }
}
