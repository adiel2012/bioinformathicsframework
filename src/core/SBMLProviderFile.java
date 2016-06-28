/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import core.interfaces.ISBMLProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adiel
 */
public class SBMLProviderFile implements ISBMLProvider {

    private File file;
    
    private String text = null;

    public SBMLProviderFile() {
    }

    public SBMLProviderFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String getSGBMLText() {
        if(text!=null)
            return text;
        text = "";
        
        try {
           
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                String next = scan.nextLine();
                text += next + System.getProperty("line.separator");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SBMLProviderFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return text;
    }

    @Override
    public String getName() {
        return file.getName();
    }

}
