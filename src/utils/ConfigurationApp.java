/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author lab4
 */
public class ConfigurationApp {
    private static ConfigurationApp __instance;
    
    public static ConfigurationApp getInstance(){
        if(__instance == null)
           __instance = new ConfigurationApp();
        return __instance;
    }
    
    private ConfigurationApp(){
        
    }
    
    public enum Enviroment{
        Debug, Develop  
    };
    
    public static Enviroment enviroment = Enviroment.Debug;
    public  String getJarURL(){
        if(enviroment==Enviroment.Develop)
            return System.getProperty("user.dir")+"\\BioInformaticFrameworkBS.jar";
        else
            return System.getProperty("user.dir")+"\\dist\\BioInformaticFrameworkBS.jar";
    }
    
    
    
}
