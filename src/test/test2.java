/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.SpeciesReference;
import utils.SparseGraphAdyacency;
import utils.SparseGraphHashing;

/**
 *
 * @author acastano
 */
//  http://authors.library.caltech.edu/24676/3/User_Guide.pdf
public class test2 {

    public static void main(String[] args) {
        SparseGraphAdyacency graph = new SparseGraphAdyacency();
        try {
            int suma2 = 0;
            long time = System.currentTimeMillis();
            for (File f : (new File(System.getProperty("user.dir") + "/sbmls/")).listFiles()) {
                SBMLDocument document = SBMLReader.read(f);

                // System.out.println(document.getChildAt(0).toString());
                Model model = (Model) document.getChildAt(0);
//                System.out.println(model.getName());
//                 System.out.println("Number of Reactions: "+model.getListOfReactions().size());

                for (Reaction reaction : model.getListOfReactions()) {

                    graph.add_reaction(reaction, true);
//                    System.out.println("  Reacción:  " + reaction.getName());
//                    int cantidad_reaccionantes = reaction.getReactantCount();
//                    int cantidad_productos = reaction.getProductCount();
//                    System.out.println("    Cantidad de Reaccionantes:  " + cantidad_reaccionantes);
//                    System.out.println("    Cantidad de Productos:  " + cantidad_productos);
//
//                    suma2 +=cantidad_reaccionantes*cantidad_productos;
//                    
//                    System.out.print("       ");
//                    for (int i = 0; i < cantidad_reaccionantes; i++) {
//                        SpeciesReference sr = reaction.getReactant(i);
//                        System.out.print(sr.getName() + " + ");
//                    }
//                    if (reaction.isReversible()) {
//                        System.out.print(" <--> ");
//                        suma2 +=cantidad_reaccionantes*cantidad_productos;
//                    } else {
//                        System.out.print(" --> ");
//                    }
//
//                    for (int i = 0; i < cantidad_productos; i++) {
//                        SpeciesReference sr = reaction.getProduct(i);
//                        System.out.print(sr.getName() + " + ");
//                    }
//                    System.out.println();

                }

            }
            System.out.println("Time: " + (System.currentTimeMillis() - time));

            System.out.println("-------------------------------------------*************************************");

            int suma = 0;
            Map<String, SparseGraphAdyacency.Arc> arcs = graph.getArcs();
            for (Map.Entry<String, SparseGraphAdyacency.Arc> entrySet : arcs.entrySet()) {
                String key = entrySet.getKey();
                SparseGraphAdyacency.Arc value = entrySet.getValue();

                System.out.println(key + ": " + value.getReactions().size());
                suma += value.getReactions().size();
            }

            System.out.println("Suma: " + suma);

            System.out.println("Suma: " + suma2);

        } catch (XMLStreamException ex) {
            Logger.getLogger(test1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(test1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
