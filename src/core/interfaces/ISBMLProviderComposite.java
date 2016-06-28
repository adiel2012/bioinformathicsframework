/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.interfaces;

/**
 *
 * @author lab4
 obtiene una coleccion de ISBMLProvider
 */
public interface ISBMLProviderComposite {
    public Iterable<ISBMLProvider> getISGBMLProvider();
}
