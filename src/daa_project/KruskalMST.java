/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daa_project;

/**
 *
 * @author Dell
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KruskalMST extends JFrame {

    private JTextArea resultTextArea;
    private List<Edge> edges;
    private int[] parent;

    public KruskalMST() {
        setTitle("Kruskal's MST");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        edges = new ArrayList<>();
        edges.add(new Edge(0, 3, 15));
        edges.add(new Edge(1, 4, 8));
        edges.add(new Edge(2, 5, 13));
        edges.add(new Edge(3, 6, 14));
        edges.add(new Edge(4, 5, 15));
        edges.add(new Edge(4, 7, 11));
        edges.add(new Edge(5, 8, 6));
        edges.add(new Edge(6, 9, 10));
        edges.add(new Edge(7, 10, 13));
        edges.add(new Edge(7, 11, 17));
        edges.add(new Edge(8, 12, 12));
        edges.add(new Edge(9, 12, 14));
        edges.add(new Edge(10, 13, 8));
        edges.add(new Edge(11, 13, 11));
        edges.add(new Edge(11, 14, 11));
        edges.add(new Edge(12, 14, 9));
        edges.add(new Edge(13, 14, 3));

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        getContentPane().add(new JScrollPane(resultTextArea));

        calculateMinimumSpanningTree();
    }

    private void calculateMinimumSpanningTree() {
        Collections.sort(edges, Comparator.comparingInt(e -> e.weight));
        parent = new int[edges.size()];
        for (int i = 0; i < edges.size(); i++) {
            parent[i] = i;
        }

        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;

        for (Edge edge : edges) {
            int parent1 = findParent(edge.source);
            int parent2 = findParent(edge.destination);

            if (parent1 != parent2) {
                mstEdges.add(edge);
                totalCost += edge.weight;
                parent[parent2] = parent1;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Minimum Cost: ").append(totalCost).append("\n");
        sb.append("Paths with Minimum Weights: \n");
        for (Edge edge : mstEdges) {
            sb.append(edge.source+111).append(" - ").append(edge.destination+111).append(" : ").append(edge.weight).append("\n");
        }

        resultTextArea.setText(sb.toString());
    }

    private int findParent(int vertex) {
        if (parent[vertex] != vertex) {
            parent[vertex] = findParent(parent[vertex]);
        }
        return parent[vertex];
    }

    public static class Edge {
        private int source;
        private int destination;
        private int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Graph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Graph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Graph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Graph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KruskalMST().setVisible(true);
            }
        });
    }
}

