package core;

import core.interfaces.ISBMLProvider;
import core.interfaces.ISBMLProviderComposite;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConfigurationApp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lab4
 */
public class SBMLProvidersLoader {

    private static SBMLProvidersLoader __instance = null;

    public static SBMLProvidersLoader getInstance() {
        if (__instance == null) {
            __instance = new SBMLProvidersLoader();
        }
        return __instance;
    }

    public void Register(ISBMLProvider provider) {
        providers.add(provider);
    }

    private ArrayList<ISBMLProvider> providers = new ArrayList<>();

    public Iterable<ISBMLProvider> getSGBMLProvider() {

        ArrayList<ISBMLProvider> res = new ArrayList<>();
        
        JarInputStream in = null;
        try {
            //        Package userPackage  = Package.getPackage("");
            //        userPackage.get

            //System.out.println(System.getProperty("user.dir"));
            File f = new File(ConfigurationApp.getInstance().getJarURL());
            in = new JarInputStream(new FileInputStream(f));
            while (true) {
                JarEntry crunchifyJar = in.getNextJarEntry();
                if (crunchifyJar == null) {
                    break;
                }
//                System.out.println(crunchifyJar.getName());
                if ((crunchifyJar.getName().endsWith(".class"))) {
                    String className = crunchifyJar.getName().replaceAll("/", "\\.");
//                    System.out.println(className);
                   if(className.indexOf("sgbmlproviders.")==0){
                        try {
                           Class type = Class.forName(className.substring(0, className.length()-6));
                           if(ISBMLProvider.class.isAssignableFrom(type))                           
                                 res.add((ISBMLProvider) Class.forName(className.substring(0, className.length()-6)).newInstance());
                           else if(ISBMLProviderComposite.class.isAssignableFrom(type)) 
                           {
                              ISBMLProviderComposite composite = ((ISBMLProviderComposite) Class.forName(className.substring(0, className.length()-6)).newInstance());
                               for (ISBMLProvider isp : composite.getISGBMLProvider()) {
                                   res.add(isp);
                               }
                           }
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(SBMLProvidersLoader.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InstantiationException ex) {
                            Logger.getLogger(SBMLProvidersLoader.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(SBMLProvidersLoader.class.getName()).log(Level.SEVERE, null, ex);
                        }
                   }
                    String myClass = className.substring(0, className.lastIndexOf('.'));
//                    System.out.println(myClass);
//                    listofClasses.put(myClass);
                }
            }

            return (Iterable<ISBMLProvider>) res.clone();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SBMLProvidersLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SBMLProvidersLoader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(SBMLProvidersLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return (Iterable<ISBMLProvider>) res.clone();
    }

    private SBMLProvidersLoader() {

    }

}
