/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbmlproviders;

import core.interfaces.ISBMLProvider;
import core.interfaces.ISBMLProviderComposite;
import java.util.ArrayList;

/**
 *
 * @author lab4
 */
public class FakeSBMLProviderComposite implements ISBMLProviderComposite{

    @Override
    public Iterable<ISBMLProvider> getISGBMLProvider() {
        ArrayList<ISBMLProvider> res = new ArrayList<>();
        res.add(new FakeSBMLProvider());
        res.add(new FakeSBMLProvider());
        return res;
    }
    
}
