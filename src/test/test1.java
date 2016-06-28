/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.SpeciesReference;
import utils.SparseGraphHashing;

/**
 *
 * @author acastano
 */
//  http://authors.library.caltech.edu/24676/3/User_Guide.pdf
public class test1 {

    public static void main(String[] args) {
        SparseGraphHashing graph = new SparseGraphHashing();
        try {
            for (File f : (new File(System.getProperty("user.dir") + "/sbmls/")).listFiles()) {
                SBMLDocument document = SBMLReader.read(new File(System.getProperty("user.dir") + "/sbmls/BMID000000027439.xml"));

                // System.out.println(document.getChildAt(0).toString());
                Model model = (Model) document.getChildAt(0);
                System.out.println(model.getName());
                for (Reaction reaction : model.getListOfReactions()) {

                    graph.add_reaction(reaction);
                    System.out.println("  Reacción:  " + reaction.getName());
                    int cantidad_reaccionantes = reaction.getReactantCount();
                    int cantidad_productos = reaction.getProductCount();
                    System.out.println("    Cantidad de Reaccionantes:  " + cantidad_reaccionantes);
                    System.out.println("    Cantidad de Productos:  " + cantidad_productos);

                    System.out.print("       ");
                    for (int i = 0; i < cantidad_reaccionantes; i++) {
                        SpeciesReference sr = reaction.getReactant(i);
                        System.out.print(sr.getName() + " + ");
                    }
                    if (reaction.isReversible()) {
                        System.out.print(" <--> ");
                    } else {
                        System.out.print(" --> ");
                    }

                    for (int i = 0; i < cantidad_productos; i++) {
                        SpeciesReference sr = reaction.getProduct(i);
                        System.out.print(sr.getName() + " + ");
                    }
                    System.out.println();
                }
            }

        } catch (XMLStreamException ex) {
            Logger.getLogger(test1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(test1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
