package core.download;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author adiel
 */
public class main {

    public static void main(String[] args) {

        try {
            int noOfThreads = 20;
            int maxNoOfTasks = 100;
            ThreadPool pool = new ThreadPool(noOfThreads, maxNoOfTasks);

            File fichero = new File("salida3.txt");
            Scanner scan = new Scanner(fichero);

            while (scan.hasNext()) {
                final String aurl = scan.nextLine();
                pool.execute(new Runnable() {
                    final String val = aurl;

                    @Override
                    public void run() {
                        guardar(val);
                        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                });
            }

            /*       
             for (int i = 0; i < 20; i++) {
             final int b = i;
             pool.execute(new Runnable() {
             final int val = b;

             @Override
             public void run() {
             System.out.println(val);
             // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }
             });

             }*/
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void guardar(String url) {
        
       // System.out.println(url);
        String nombre = url.substring(url.lastIndexOf("mid=")+4);
        System.out.println(nombre);
        
        File f = new File(System.getProperty("user.dir")+"\\sbmls\\"+nombre+".xml");
        if(f.exists()==false){
            String texto = obtener_texto_from_url(url);
            escribir_texto(f,texto);
        }
        else
            System.out.println("En Disco: "+f.getName());
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static String obtener_texto_from_url(String aurl) {
        try {
            URL url = new URL(aurl);    
            Scanner s = new Scanner(url.openStream());
            String res = "";
            while (s.hasNext()) {
                res += s.nextLine()+System.lineSeparator();                
            }
            return res;
        } catch (MalformedURLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    private static void escribir_texto(File f, String texto) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(f));
            bw.write(texto);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
