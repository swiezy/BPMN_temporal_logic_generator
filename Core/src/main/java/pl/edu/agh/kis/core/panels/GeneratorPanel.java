/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.kis.core.panels;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.openide.util.Exceptions;
import pl.edu.agh.kis.core.data.BPMNParser;
import pl.edu.agh.kis.core.data.Edge;
import pl.edu.agh.kis.core.data.AtomNode;
import pl.edu.agh.kis.core.utilities.BpmnFilter;
import pl.edu.agh.kis.core.utilities.ColorStyledDocument;

/**
 *
 * @author Jakub Piotrowski
 */
public class GeneratorPanel extends javax.swing.JPanel {

    private static final String DEFULT_FORMULAS = "/pl/edu/agh/kis/core/panels/defaultFormulas.txt";
    private static final String USER_FORMULAS = "/pl/edu/agh/kis/core/panels/userFormulas.txt";
    private Path defaultPath, userPath;
    private BufferedWriter writer;
    private boolean isDefault;
    private File bpmnFile;
    private Graph<AtomNode, Edge> graph;

    /**
     * Creates new form GeneratorPanel
     */
    public GeneratorPanel() {
        initComponents();
        initResources();
        generateButton.setEnabled(false);
    }

    private void initResources() {
        try {
            defaultPath = Paths.get(getClass().getClassLoader().getResource(DEFULT_FORMULAS).toURI());
            userPath = Paths.get(getClass().getClassLoader().getResource(USER_FORMULAS).toURI());
        } catch (URISyntaxException ex) {
            Exceptions.printStackTrace(ex);
        }

        logicFormulasTextPane.getDocument().addDocumentListener(new LogicFormulasDocumentListener());

        try {
            if (Files.readAllLines(userPath).isEmpty()) {
                loadLogicalFormulas(defaultPath);
                isDefault = true;
                restoreDefaultButton.setEnabled(!isDefault);
            } else {
                loadLogicalFormulas(userPath);
                isDefault = false;
                restoreDefaultButton.setEnabled(!isDefault);
            }
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pathTextField = new javax.swing.JTextField();
        chooseBpmnButton = new javax.swing.JButton();
        graphPanel = new ImagePanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        patternsTextPane = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        generatedLogicTextPane = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        logicFormulasTextPane = new javax.swing.JTextPane();
        chooseLogicFormulasFileButton = new javax.swing.JButton();
        restoreDefaultButton = new javax.swing.JButton();
        generateButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        pathTextField.setText(org.openide.util.NbBundle.getMessage(GeneratorPanel.class, "GeneratorPanel.pathTextField.text")); // NOI18N
        pathTextField.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 0, 5);
        add(pathTextField, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(chooseBpmnButton, org.openide.util.NbBundle.getMessage(GeneratorPanel.class, "GeneratorPanel.chooseBpmnButton.text")); // NOI18N
        chooseBpmnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseBpmnButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 0, 5);
        add(chooseBpmnButton, gridBagConstraints);

        javax.swing.GroupLayout graphPanelLayout = new javax.swing.GroupLayout(graphPanel);
        graphPanel.setLayout(graphPanelLayout);
        graphPanelLayout.setHorizontalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        graphPanelLayout.setVerticalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 20.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 10);
        add(graphPanel, gridBagConstraints);

        jScrollPane2.setViewportView(patternsTextPane);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        add(jScrollPane2, gridBagConstraints);

        jScrollPane3.setViewportView(generatedLogicTextPane);
        generatedLogicTextPane.setDocument(new ColorStyledDocument());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 10);
        add(jScrollPane3, gridBagConstraints);

        jScrollPane1.setViewportView(logicFormulasTextPane);
        logicFormulasTextPane.setDocument(new ColorStyledDocument());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        add(jScrollPane1, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(chooseLogicFormulasFileButton, org.openide.util.NbBundle.getMessage(GeneratorPanel.class, "GeneratorPanel.chooseLogicFormulasFileButton.text")); // NOI18N
        chooseLogicFormulasFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseLogicFormulasFileButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 5);
        add(chooseLogicFormulasFileButton, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(restoreDefaultButton, org.openide.util.NbBundle.getMessage(GeneratorPanel.class, "GeneratorPanel.restoreDefaultButton.text")); // NOI18N
        restoreDefaultButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restoreDefaultButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 10);
        add(restoreDefaultButton, gridBagConstraints);

        generateButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(generateButton, org.openide.util.NbBundle.getMessage(GeneratorPanel.class, "GeneratorPanel.generateButton.text")); // NOI18N
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        add(generateButton, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void chooseBpmnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseBpmnButtonActionPerformed
        final JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new BpmnFilter());
        fc.setAcceptAllFileFilterUsed(false);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            bpmnFile = fc.getSelectedFile();
            pathTextField.setText(bpmnFile.getAbsolutePath());

            generateButton.setEnabled(true);
        }
    }//GEN-LAST:event_chooseBpmnButtonActionPerformed

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        BPMNParser parser = new BPMNParser();
        graph = parser.parseBPMN(bpmnFile);
        paintGraph();
        selectLogicFormulas();
    }//GEN-LAST:event_generateButtonActionPerformed

    private void selectLogicFormulas() {
        if (isDefault) {
            // Set parent formula file to defaultFormulas.txt
            try {
                writer = Files.newBufferedWriter(userPath);
                writer.close();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        } else {
            try {
                // Set parent formula file to userFormulas.txt
                writer = Files.newBufferedWriter(userPath);
                writer.write(logicFormulasTextPane.getText());
                writer.close();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }

    private void chooseLogicFormulasFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseLogicFormulasFileButtonActionPerformed
        final JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                writer = Files.newBufferedWriter(userPath);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
            Consumer<String> textConsumer = (String line) -> {
                try {
                    writer.write(line);
                    writer.newLine();
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            };
            try {
                Files.lines(Paths.get(file.getAbsolutePath())).forEachOrdered(textConsumer);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            } finally {
                try {
                    writer.close();
                    loadLogicalFormulas(userPath);
                    isDefault = false;
                    restoreDefaultButton.setEnabled(!isDefault);
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }//GEN-LAST:event_chooseLogicFormulasFileButtonActionPerformed

    private void restoreDefaultButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreDefaultButtonActionPerformed
        loadLogicalFormulas(defaultPath);
        isDefault = true;
        restoreDefaultButton.setEnabled(!isDefault);
    }//GEN-LAST:event_restoreDefaultButtonActionPerformed

    private void loadLogicalFormulas(Path path) {
        logicFormulasTextPane.setText("");
        StyledDocument doc = logicFormulasTextPane.getStyledDocument();
        Consumer<String> textConsumer = (String line) -> {
            try {
                doc.insertString(doc.getLength(), line + "\n", null);
            } catch (BadLocationException ex) {
                Exceptions.printStackTrace(ex);
            }
        };
        try {
            Files.lines(path).forEachOrdered(textConsumer);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private void paintGraph() {
        // get graphPanel dimension
        Dimension dim = graphPanel.getSize();

        // create layout
        // Jung layouts - http://jung.sourceforge.net/doc/api/edu/uci/ics/jung/algorithms/layout/package-summary.html
        Layout<Node, Edge> layout = new FRLayout(graph);
        layout.setSize(new Dimension((int) dim.getWidth() - (int) (dim.getWidth() * 0.05), (int) dim.getHeight() - (int) (dim.getHeight() * 0.05)));

        // remove old JPanel
        this.remove(graphPanel);

        // create and add new graphPanel
        graphPanel = new BasicVisualizationServer<Node, Edge>(layout);
        graphPanel.addComponentListener(new ComponentAdapter() {
            private Dimension last_size = null;

            @Override
            public void componentResized(ComponentEvent evt) {
                Dimension new_size = evt.getComponent().getSize();
                if (this.last_size == null || !new_size.equals(this.last_size)) {
                    Layout<Node, Edge> layout = ((BasicVisualizationServer) graphPanel).getGraphLayout();
                    try {
                        layout.setSize(new_size);
                        ((BasicVisualizationServer) graphPanel).setGraphLayout(layout);
                        this.last_size = new_size;
                    } catch (UnsupportedOperationException ex) {
                        // Ignore...
                    }
                }
            }
        });
        graphPanel.setPreferredSize(dim);
        GridBagConstraints gridBagConstraints;

        GroupLayout graphPanelLayout = new GroupLayout(graphPanel);
        graphPanel.setLayout(graphPanelLayout);
        graphPanelLayout.setHorizontalGroup(
                graphPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
        );
        graphPanelLayout.setVerticalGroup(
                graphPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 12.0;
        gridBagConstraints.insets = new Insets(10, 5, 5, 10);
        add(graphPanel, gridBagConstraints);

        this.revalidate();
        this.repaint();
    }

    private class LogicFormulasDocumentListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            isDefault = false;
            restoreDefaultButton.setEnabled(!isDefault);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            isDefault = false;
            restoreDefaultButton.setEnabled(!isDefault);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            isDefault = false;
            restoreDefaultButton.setEnabled(!isDefault);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chooseBpmnButton;
    private javax.swing.JButton chooseLogicFormulasFileButton;
    private javax.swing.JButton generateButton;
    private javax.swing.JTextPane generatedLogicTextPane;
    private javax.swing.JPanel graphPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane logicFormulasTextPane;
    private javax.swing.JTextField pathTextField;
    private javax.swing.JTextPane patternsTextPane;
    private javax.swing.JButton restoreDefaultButton;
    // End of variables declaration//GEN-END:variables
}
