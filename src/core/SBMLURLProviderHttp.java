/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import core.interfaces.ISBMLProvider;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.net.www.http.HttpClient;

/**
 *
 * @author adiel
 */
public class SBMLURLProviderHttp implements ISBMLProvider {

//    private String name = null;
    private String url = null;
    private File file_local = null;

    private String text = null;

    public SBMLURLProviderHttp( String url, File file_local) {
//        setName(name);
        setUrl(url);
        setUrl_local(file_local);
    }

    public void setUrl_local(File url_local) {
        this.file_local = url_local;
    }

    public File getUrl_local() {
        return file_local;
    }

    @Override
    public String getSGBMLText() {

        if (text != null) {
            return text;
        }
        if (file_local.exists() == false) {
            save_to_disk(url, file_local);
        }
        return (text = getTextFileContent(file_local));
        //return "nada";
    }

    @Override
    public String getName() {
        return file_local.getName();
    }

    /**
     * @param name the name to set
     */


    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public static String getTextRemoteURL(String url) {

        try {

            URL aurl = new URL(url);

            // read text returned by server
            BufferedReader in = new BufferedReader(new InputStreamReader(aurl.openStream()));

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();

        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
        return null;
    }

    private static String getTextFileContent(File url_local) {
        String res = "";
        try {
            Scanner scan = new Scanner(url_local);
            while (scan.hasNext()) {
                String next = scan.nextLine();
                res += next + System.getProperty("line.separator");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SBMLProviderFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    private void save_to_disk(String url, File url_local) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
