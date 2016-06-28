/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbmlproviders;

import core.SBMLProviderFile;
import core.SBMLURLProviderHttp;
import core.interfaces.ISBMLProvider;
import core.interfaces.ISBMLProviderComposite;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adiel
 */
public class SBMLMultipleURLProviderComposite implements ISBMLProviderComposite {

    File content;

    public SBMLMultipleURLProviderComposite(File content) {
        this.content = content;
    }

    
    
    
    
    @Override
    public Iterable<ISBMLProvider> getISGBMLProvider() {
         ArrayList<ISBMLProvider> res = new ArrayList<>();
         try {
           
            Scanner scan = new Scanner(content);
            while (scan.hasNext()) {
                String next = scan.nextLine();
                res.add(new SBMLProviderFile(new File(System.getProperty("user.dir")+"\\"+next)));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SBMLProviderFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
//         res.add(new SBMLURLProviderHttp( "https://www.google.com.ec/#gws_rd=ssl",new File(System.getProperty("user.dir")+"\\sbmls\\fichero1.sbml")));
//         res.add(new SBMLURLProviderHttp( "https://search.yahoo.com/search?ei=utf-8&fr=tightropetb&p=stack+overflow&type=11465_060916",new File(System.getProperty("user.dir")+"\\sbmls\\fichero1.sbml")));
//         
         return res;
    }
    
}
