/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbmlproviders;

import core.SBMLProvidersLoader;
import core.interfaces.ISBMLProvider;

/**
 *
 * @author lab4
 */
public class FakeSBMLProvider implements ISBMLProvider{

//    static{
//        // registro el proveedor
//         SGBMLProvidersLoader.getInstance().Register(new FakeSBMLProvider());
//    }
    
    
    @Override
    public String getSGBMLText() {
        return "el contenido";    
    }

    @Override
    public String getName() {
        return "minombre";    
    }
    
}
