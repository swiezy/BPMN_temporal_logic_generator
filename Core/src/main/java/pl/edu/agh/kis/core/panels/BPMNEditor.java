/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.kis.core.panels;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import static javafx.concurrent.Worker.State.FAILED;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebErrorEvent;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.openide.util.Exceptions;

/**
 *
 * @author Adam Świeżowski <adam.swiezowski+projects [at] gmail [dot] com>
 */
public class BPMNEditor extends javax.swing.JPanel {

    /**
     * Creates new form BPMNEditor
     */
    private final JFXPanel jfxPanel = new JFXPanel();
    private WebEngine engine;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public BPMNEditor() {
        createScene();
        
        initComponents();
        
        add(jfxPanel, BorderLayout.CENTER);
        loadURL("http://google.pl");
    }
    
    private void createScene() {
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                
                WebView view = new WebView();
                engine = view.getEngine();
                
                engine.getLoadWorker()
                        .exceptionProperty()
                        .addListener(new ChangeListener<Throwable>() {
                            
                            public void changed(ObservableValue<? extends Throwable> o, Throwable old, final Throwable value) {
                                if (engine.getLoadWorker().getState() == FAILED) {
                                    SwingUtilities.invokeLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            JOptionPane.showMessageDialog(
                                                    BPMNEditor.this,
                                                    (value != null)
                                                            ? engine.getLocation() + "\n" + value.getMessage()
                                                            : engine.getLocation() + "\nUnexpected error.",
                                                    "Loading error...",
                                                    JOptionPane.ERROR_MESSAGE);
                                        }
                                    });
                                }
                            }
                        });
  
                engine.locationProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue, String oldLoc, String newLoc) {
                        System.out.println("newLoc");
                        if (newLoc.startsWith("data:application/bpmn20-xml;charset=UTF-8,")) {
                            String result = null;
                            try {
                                result = java.net.URLDecoder.decode(newLoc.substring(42), "UTF-8");
                                final JFileChooser fc = new JFileChooser();
                                int returnVal = fc.showSaveDialog(BPMNEditor.this);
                                if (returnVal == JFileChooser.APPROVE_OPTION) {
                                    File bpmnFile = fc.getSelectedFile();
                                    PrintWriter out = new PrintWriter(bpmnFile);
                                    out.write(result);
                                    out.close();
                                }
                                
                            } catch (UnsupportedEncodingException ex) {
                                Exceptions.printStackTrace(ex);
                            } catch (FileNotFoundException ex) {
                                Exceptions.printStackTrace(ex);
                            }
                            
                        }
                        
                    }
                });
                
                jfxPanel.setScene(new Scene(view));
            }
        });
    }
    
    public void loadURL(final String url) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                URI url = null;
                try {
                    url = getClass().getClassLoader().getResource("/bpmn/index.html").toURI();
                } catch (URISyntaxException ex) {
                    Exceptions.printStackTrace(ex);
                }
                engine.load(url.toString());
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(500, 500));
        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}