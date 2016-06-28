/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.SpeciesReference;

/**
 *
 * @author acastano
 */
public class SparseGraphAdyacency {

    public static void main(String[] args) {
//        Arc arco1 = new Arc("A", "B", "react1");
//        arco1.getReactions().add(new Reaction());
//        Arc arco2 = new Arc("A", "B", "react2");
//
//        Map<Arc, Arc> arcs = new HashMap<>();
//        arcs.put(arco1, arco1);
//        System.out.println(arcs.containsKey(arco2));
//
//        System.out.println(arco1.equals(arco2));
//        System.out.println(arco1.hashCode() == arco2.hashCode());
    }

    //private final ArrayList<String> nodes = new ArrayList<>();
    Map<String, Arc> arcs = new HashMap<>();

    public void add_reaction(Reaction react, boolean include_reaction) {

        for (int i = 0; i < react.getReactantCount(); i++) {
            SpeciesReference reaccionante = react.getReactant(i);
            for (int j = 0; j < react.getProductCount(); j++) {
                SpeciesReference producto = react.getProduct(j);
                //  forward
                Arc arc = arcs.get(reaccionante.getId() + "_" + producto.getId());
                if (arc == null) {
                    arc = new Arc(reaccionante.getId(), producto.getId());
                    arcs.put(reaccionante.getId() + "_" + producto.getId(), arc);
                } else {
                    int gg = 9;
                }
                if (include_reaction) {
                    arc.getReactions().add(react);
                }
                // reversoble
                if (react.isReversible()) {
                    arc = arcs.get(producto.getId() + "_" + reaccionante.getId());
                    if (arc == null) {
                        arc = new Arc(producto.getId(), reaccionante.getId());
                        arcs.put(producto.getId() + "_" + reaccionante.getId(), arc);
                    } else {
                        int gg = 9;
                    }
                    if (include_reaction) {
                        arc.getReactions().add(react);
                    }

                }
            }
        }
    }

    /**
     * @return the nodes
     */
    public Map<String, Arc> getArcs() {
        return arcs;
    }

    public static class Arc {

        private String node1;
        private String node2;
        //  private EnumDirection direction;
        private ArrayList<Reaction> reactions = new ArrayList<Reaction>();

        public Arc(String node1, String node2) {
            this.node1 = node1;
            this.node2 = node2;
//            this.direction = direction;
        }

        @Override
        public int hashCode() {
            return (node1 + "_" + node2).hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Arc other = (Arc) obj;
            if (!Objects.equals(this.node1, other.node1)) {
                return false;
            }
            if (!Objects.equals(this.node2, other.node2)) {
                return false;
            }
            return true;
        }

        /**
         * @return the node1
         */
        public String getNode1() {
            return node1;
        }

        /**
         * @param node1 the node1 to set
         */
        public void setNode1(String node1) {
            this.node1 = node1;
        }

        /**
         * @return the node2
         */
        public String getNode2() {
            return node2;
        }

        /**
         * @param node2 the node2 to set
         */
        public void setNode2(String node2) {
            this.node2 = node2;
        }

//        /**
//         * @return the direction
//         */
//        public EnumDirection getDirection() {
//            return direction;
//        }
//
//        /**
//         * @param direction the direction to set
//         */
//        public void setDirection(EnumDirection direction) {
//            this.direction = direction;
//        }
        /**
         * @return the reactions
         */
        public ArrayList<Reaction> getReactions() {
            return reactions;
        }

        public enum EnumDirection {

            forward, both
        };

    }

}
