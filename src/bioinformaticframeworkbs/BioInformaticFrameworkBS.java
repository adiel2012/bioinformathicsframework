/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioinformaticframeworkbs;

import core.SBMLProvidersLoader;
import core.SBMLURLProviderHttp;
import core.interfaces.ISBMLProvider;

/**
 *
 * @author lab4
 */
public class BioInformaticFrameworkBS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println(SBMLURLProviderHttp.getTextRemoteURL("https://www.google.com.ec/#gws_rd=ssl"));
        
        
        for (ISBMLProvider p : SBMLProvidersLoader.getInstance().getSGBMLProvider()) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Name: "+p.getName());
            System.out.println("Name: "+p.getSGBMLText());
        }
    }
    
}
