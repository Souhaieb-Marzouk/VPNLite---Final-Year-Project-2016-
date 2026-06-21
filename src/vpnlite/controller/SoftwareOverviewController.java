/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vpnlite.controller;

import com.jfoenix.controls.JFXToggleButton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * FXML Controller class
 *
 * @author souhaieb
 */
public class SoftwareOverviewController implements Initializable {

    private Process p,p1;
    @FXML
    private Button previousButton;
    @FXML
    private Button updateButton;
    Task copyWorker;
    @FXML
    private ImageView testingtoolImg;
    @FXML
    private ImageView ipsecl2tpImg;
    @FXML
    private ImageView pppfamilyImg;
    @FXML
    private Label testingtoolLabel;
    @FXML
    private Label ipsecl2tpLabel;
    @FXML
    private Label pppfamilyLabel;
    @FXML
    private Label strongswanLabel;
    @FXML
    private ImageView testingarrow;
    @FXML
    private ImageView ipsecl2tparrow;
    @FXML
    private ImageView pppfamilyarrow;
    @FXML
    private AnchorPane testingtoolWindow;
    @FXML
    private Label iperf3Label;
    @FXML
    private JFXToggleButton iperf3installField;
    @FXML
    private Label iperfLabel;
    @FXML
    private JFXToggleButton iperfinstallField;
    @FXML
    private Label nmapLabel;
    @FXML
    private JFXToggleButton nmapinstallField;
    @FXML
    private Label ftpLabel;
    @FXML
    private JFXToggleButton ftpinstallField;
    @FXML
    private Label snmpLabel;
    @FXML
    private JFXToggleButton snmpinstallField;
    @FXML
    private Label dnsutilsLabel;
    @FXML
    private JFXToggleButton dsnutilsinstallField;
    @FXML
    private Label wiresharkLabel;
    @FXML
    private JFXToggleButton wiresharkinstallField;
    @FXML
    private AnchorPane ipsecl2tpWindow;
    @FXML
    private Label openswanLabel;
    @FXML
    private JFXToggleButton openswaninstallField;
    @FXML
    private Label xl2tpdLabel;
    @FXML
    private JFXToggleButton xl2tpdinstallField;
    @FXML
    private Label ipsecpppLabel;
    @FXML
    private JFXToggleButton ipsecpppinstallField;
    @FXML
    private Label lsofLabel;
    @FXML
    private JFXToggleButton lsofinstallField;
    @FXML
    private Label pppdLabel;
    @FXML
    private JFXToggleButton pppdinstallField;
    @FXML
    private Label pppoeconfLabel;
    @FXML
    private JFXToggleButton pppoeconfinstallField;
    @FXML
    private Label pptpLabel;
    @FXML
    private JFXToggleButton pptpinstallField;
    @FXML
    private AnchorPane pppfamilyWindow;
    @FXML
    private JFXToggleButton strongswaninstallField;
    @FXML
    private HBox list1;
    @FXML
    private VBox list2;
    @FXML
    private ProgressBar installationprogressBar;
    @FXML
    private Label tracerouteLabel;
    @FXML
    private JFXToggleButton tracerouteinstallField;
    @FXML
    private VBox ppppppoepptpFields;
    @FXML
    private VBox testToolsFields;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    //int compteur = 0;
                    String result ="";
                    p = Runtime.getRuntime().exec("CheckProgram.sh libstrongswan");
                    //InputStream ips=new FileInputStream(result);
                    InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                    BufferedReader br=new BufferedReader(ipsr);
                    String ligne;
                    while ((ligne=br.readLine())!=null)
                    {
                        System.out.println(ligne);
                        if(ligne.equals("libstrongswan is installed")){
                            strongswaninstallField.setText("Installed");
                            strongswaninstallField.setSelected(true);
                        }else if(ligne.equals("nothing")){
                            strongswaninstallField.setText("Uninstalled");
                            strongswaninstallField.setSelected(false);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    String result ="";
                    p = Runtime.getRuntime().exec("CheckProgram.sh xl2tpd");
                    InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                    BufferedReader br=new BufferedReader(ipsr);
                    String ligne;
                    while ((ligne=br.readLine())!=null)
                    {
                        System.out.println(ligne);
                        if(ligne.equals("xl2tpd is installed")){
                            xl2tpdinstallField.setText("Installed");
                            xl2tpdinstallField.setSelected(true);
                        }else if(ligne.equals("nothing")){
                            xl2tpdinstallField.setText("Uninstalled");
                            xl2tpdinstallField.setSelected(false);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    String result ="";
                    p = Runtime.getRuntime().exec("CheckProgram.sh ppp");
                    InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                    BufferedReader br=new BufferedReader(ipsr);
                    String ligne;
                    while ((ligne=br.readLine())!=null)
                    {
                        System.out.println(ligne);
                        if(ligne.equals("ppp is installed")){
                            ipsecpppinstallField.setText("Installed");
                            pppdinstallField.setText("Installed");
                            ipsecpppinstallField.setSelected(true);
                            pppdinstallField.setSelected(true);
                        }else if(ligne.equals("nothing")){
                            ipsecpppinstallField.setText("Uninstalled");
                            pppdinstallField.setText("Installed");
                            ipsecpppinstallField.setSelected(false);
                            pppdinstallField.setSelected(true);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    String result ="";
                    p = Runtime.getRuntime().exec("CheckProgram.sh iperf3");
                    InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                    BufferedReader br=new BufferedReader(ipsr);
                    String ligne;
                    while ((ligne=br.readLine())!=null)
                    {
                        System.out.println(ligne);
                        if(ligne.equals("iperf3 is installed")){
                            iperf3installField.setText("Installed");
                            iperf3installField.setSelected(true);
                        }else if(ligne.equals("nothing")){
                            iperf3installField.setText("Uninstalled");
                            iperf3installField.setSelected(false);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    String result ="";
                    p = Runtime.getRuntime().exec("CheckProgram.sh ftp");
                    InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                    BufferedReader br=new BufferedReader(ipsr);
                    String ligne;
                    while ((ligne=br.readLine())!=null)
                    {
                        System.out.println(ligne);
                        if(ligne.equals("ftp is installed")){
                            ftpinstallField.setText("Installed");
                            ftpinstallField.setSelected(true);
                        }else if(ligne.equals("nothing")){
                            ftpinstallField.setText("Uninstalled");
                            ftpinstallField.setSelected(false);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    String result ="";
                    p = Runtime.getRuntime().exec("CheckProgram.sh nmap");
                    InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                    BufferedReader br=new BufferedReader(ipsr);
                    String ligne;
                    while ((ligne=br.readLine())!=null)
                    {
                        System.out.println(ligne);
                        if(ligne.equals("nmap is installed")){
                            nmapinstallField.setText("Installed");
                            nmapinstallField.setSelected(true);
                        }else if(ligne.equals("nothing")){
                            nmapinstallField.setText("Uninstalled");
                            nmapinstallField.setSelected(false);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    String result ="";
                    p = Runtime.getRuntime().exec("CheckProgram.sh wireshark");
                    InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                    BufferedReader br=new BufferedReader(ipsr);
                    String ligne;
                    while ((ligne=br.readLine())!=null)
                    {
                        System.out.println(ligne);
                        if(ligne.equals("wireshark is installed")){
                            wiresharkinstallField.setText("Installed");
                            wiresharkinstallField.setSelected(true);
                        }else if(ligne.equals("nothing")){
                            wiresharkinstallField.setText("Uninstalled");
                            wiresharkinstallField.setSelected(false);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    String result ="";
                    p = Runtime.getRuntime().exec("CheckProgram.sh snmp");
                    InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                    BufferedReader br=new BufferedReader(ipsr);
                    String ligne;
                    while ((ligne=br.readLine())!=null)
                    {
                        System.out.println(ligne);
                        if(ligne.equals("snmp is installed")){
                            snmpinstallField.setText("Installed");
                            snmpinstallField.setSelected(true);
                        }else if(ligne.equals("nothing")){
                            snmpinstallField.setText("Uninstalled");
                            snmpinstallField.setSelected(false);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    String result ="";
                    p = Runtime.getRuntime().exec("CheckProgram.sh traceroute");
                    InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                    BufferedReader br=new BufferedReader(ipsr);
                    String ligne;
                    while ((ligne=br.readLine())!=null)
                    {
                        System.out.println(ligne);
                        if(ligne.equals("traceroute is installed")){
                            tracerouteinstallField.setText("Installed");
                            tracerouteinstallField.setSelected(true);
                        }else if(ligne.equals("nothing")){
                            tracerouteinstallField.setText("Uninstalled");
                            tracerouteinstallField.setSelected(false);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    String result ="";
                    p = Runtime.getRuntime().exec("CheckProgram.sh dnsutils");
                    InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                    BufferedReader br=new BufferedReader(ipsr);
                    String ligne;
                    while ((ligne=br.readLine())!=null)
                    {
                        System.out.println(ligne);
                        if(ligne.equals("dnsutils is installed")){
                            dsnutilsinstallField.setText("Installed");
                            dsnutilsinstallField.setSelected(true);
                        }else if(ligne.equals("nothing")){
                            dsnutilsinstallField.setText("Uninstalled");
                            dsnutilsinstallField.setSelected(false);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    String result ="";
                    p = Runtime.getRuntime().exec("CheckProgram.sh lsof");
                    InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                    BufferedReader br=new BufferedReader(ipsr);
                    String ligne;
                    while ((ligne=br.readLine())!=null)
                    {
                        System.out.println(ligne);
                        if(ligne.equals("lsof is installed")){
                            lsofinstallField.setText("Installed");
                            lsofinstallField.setSelected(true);
                        }else if(ligne.equals("nothing")){
                            lsofinstallField.setText("Uninstalled");
                            lsofinstallField.setSelected(false);
                        }
                    }
                    } catch (IOException ex) {
                        Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        });
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    String result ="";
                    p = Runtime.getRuntime().exec("CheckProgram.sh openswan");
                    InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                    BufferedReader br=new BufferedReader(ipsr);
                    String ligne;
                    while ((ligne=br.readLine())!=null)
                    {
                        System.out.println(ligne);
                        if(ligne.equals("iproute is installed")){
                            openswaninstallField.setText("Installed");
                            openswaninstallField.setSelected(true);
                        }else if(ligne.equals("nothing")){
                            openswaninstallField.setText("Uninstalled");
                            openswaninstallField.setSelected(false);
                        }
                    }
                    } catch (IOException ex) {
                        Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        });
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    String result ="";
                    p = Runtime.getRuntime().exec("CheckProgram.sh ethtool");
                    InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                    BufferedReader br=new BufferedReader(ipsr);
                    String ligne;
                    while ((ligne=br.readLine())!=null)
                    {
                        System.out.println(ligne);
                        if(ligne.equals("ethtool is installed")){
                            iperfinstallField.setText("Installed");
                            iperfinstallField.setSelected(true);
                        }else if(ligne.equals("nothing")){
                            iperfinstallField.setText("Uninstalled");
                            iperfinstallField.setSelected(false);
                        }
                    }
                    } catch (IOException ex) {
                        Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        });
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    String result ="";
                    p = Runtime.getRuntime().exec("CheckProgram.sh pppoeconf");
                    InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                    BufferedReader br=new BufferedReader(ipsr);
                    String ligne;
                    while ((ligne=br.readLine())!=null)
                    {
                        System.out.println(ligne);
                        if(ligne.equals("pppoeconf is installed")){
                            pppoeconfinstallField.setText("Installed");
                            pppoeconfinstallField.setSelected(true);
                        }else if(ligne.equals("nothing")){
                            pppoeconfinstallField.setText("Uninstalled");
                            pppoeconfinstallField.setSelected(false);
                        }
                    }
                    } catch (IOException ex) {
                        Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        });
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    String result ="";
                    p = Runtime.getRuntime().exec("CheckProgram.sh pptpd");
                    InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                    BufferedReader br=new BufferedReader(ipsr);
                    String ligne;
                    while ((ligne=br.readLine())!=null)
                    {
                        System.out.println(ligne);
                        if(ligne.equals("pptpd is installed")){
                            pptpinstallField.setText("Installed");
                            pptpinstallField.setSelected(true);
                        }else if(ligne.equals("nothing")){
                            pptpinstallField.setText("Uninstalled");
                            pptpinstallField.setSelected(false);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @FXML
    private void previousPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) previousButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vpnlite/view/PrincipalOverview.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setMaximized(true);
        stage.setTitle("VPN Application");
        stage.setScene(new Scene(root));  
        stage.show();
    }

    @FXML
    private void updateSoft(ActionEvent event) {
        //Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Thread t20 = new Thread(){
            @Override
            public void run(){
                try {
                    String result ="";
                    p = Runtime.getRuntime().exec("UPDATE.sh");
                    InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                    BufferedReader br=new BufferedReader(ipsr);
                    String ligne;
                    while ((ligne=br.readLine())!=null)
                    {
                        System.out.println(ligne);
                        list1.setDisable(true);
                        list2.setDisable(true);
                        ipsecl2tpWindow.setDisable(true);
                        testingtoolWindow.setDisable(true);
                        pppfamilyWindow.setDisable(true);
                        previousButton.setDisable(true);
                        installationprogressBar.setVisible(true);
                        result += ligne;
                    }
                    System.out.println("Update is done");
                    list1.setDisable(false);
                    list2.setDisable(false);
                    ipsecl2tpWindow.setDisable(false);
                    testingtoolWindow.setDisable(false);
                    pppfamilyWindow.setDisable(false);
                    previousButton.setDisable(false);
                    installationprogressBar.setVisible(false);
                    } catch (IOException ex) {
                        Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        };
        t20.start();
    }
    
    @FXML
    private void testingtooldisplay(MouseEvent event) {
        ipsecl2tparrow.setVisible(false);
        ipsecl2tpWindow.setVisible(false);
        pppfamilyarrow.setVisible(false);
        pppfamilyWindow.setVisible(false);
        testingarrow.setVisible(true);
        testingtoolWindow.setVisible(true);
    }

    @FXML
    private void testingtoolundisplay(MouseEvent event) {
        testingarrow.setVisible(false);
        testingtoolWindow.setVisible(false);
    }

    @FXML
    private void ipsecl2tpdisplay(MouseEvent event) {
        testingarrow.setVisible(false);
        testingtoolWindow.setVisible(false);
        pppfamilyarrow.setVisible(false);
        pppfamilyWindow.setVisible(false);
        ipsecl2tparrow.setVisible(true);
        ipsecl2tpWindow.setVisible(true);
    }

    @FXML
    private void ipsecl2tpundisplay(MouseEvent event) {
        ipsecl2tparrow.setVisible(false);
        ipsecl2tpWindow.setVisible(false);
    }

    @FXML
    private void pppfamilyundisplay(MouseEvent event) {
        pppfamilyarrow.setVisible(false);
        pppfamilyWindow.setVisible(false);
    }

    @FXML
    private void pppfamilydisplay(MouseEvent event) {
        testingarrow.setVisible(false);
        testingtoolWindow.setVisible(false);
        ipsecl2tparrow.setVisible(false);
        ipsecl2tpWindow.setVisible(false);
        pppfamilyarrow.setVisible(true);
        pppfamilyWindow.setVisible(true);
    }

    @FXML
    private void installUninstallStrongswan(ActionEvent event) {
        /*strongswaninstallField.selectedProperty().addListener(new ChangeListener <Boolean> () {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {*/
                if(strongswaninstallField.isSelected()==true)
                {
                    strongswaninstallField.setText("Installed");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            openswaninstallField.setDisable(true);
                            openswanLabel.setDisable(true);
                            xl2tpdLabel.setDisable(true);
                            xl2tpdinstallField.setDisable(true);
                            ipsecpppLabel.setDisable(true);
                            ipsecpppinstallField.setDisable(true);
                            lsofLabel.setDisable(true);
                            lsofinstallField.setDisable(true);
                            strongswanLabel.setDisable(true);
                            strongswaninstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("InstallStrongswan.sh");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Installation is done");
                            openswaninstallField.setDisable(false);
                            openswanLabel.setDisable(false);
                            xl2tpdLabel.setDisable(false);
                            xl2tpdinstallField.setDisable(false);
                            ipsecpppLabel.setDisable(false);
                            ipsecpppinstallField.setDisable(false);
                            lsofLabel.setDisable(false);
                            lsofinstallField.setDisable(false);
                            strongswanLabel.setDisable(false);
                            strongswaninstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
                else
                {
                    strongswaninstallField.setText("Uninstalled");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            openswaninstallField.setDisable(true);
                            openswanLabel.setDisable(true);
                            xl2tpdLabel.setDisable(true);
                            xl2tpdinstallField.setDisable(true);
                            ipsecpppLabel.setDisable(true);
                            ipsecpppinstallField.setDisable(true);
                            lsofLabel.setDisable(true);
                            lsofinstallField.setDisable(true);
                            strongswanLabel.setDisable(true);
                            strongswaninstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("DelStrongswan.sh");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Uninstallation is done");
                            openswaninstallField.setDisable(false);
                            openswanLabel.setDisable(false);
                            xl2tpdLabel.setDisable(false);
                            xl2tpdinstallField.setDisable(false);
                            ipsecpppLabel.setDisable(false);
                            ipsecpppinstallField.setDisable(false);
                            lsofLabel.setDisable(false);
                            lsofinstallField.setDisable(false);
                            strongswanLabel.setDisable(false);
                            strongswaninstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
    }

    @FXML
    private void installUninstallOpenswan(ActionEvent event) {
                if(openswaninstallField.isSelected()==true)
                {
                    openswaninstallField.setText("Installed");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            openswaninstallField.setDisable(true);
                            openswanLabel.setDisable(true);
                            xl2tpdLabel.setDisable(true);
                            xl2tpdinstallField.setDisable(true);
                            ipsecpppLabel.setDisable(true);
                            ipsecpppinstallField.setDisable(true);
                            lsofLabel.setDisable(true);
                            lsofinstallField.setDisable(true);
                            strongswanLabel.setDisable(true);
                            strongswaninstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get install -yq openswan");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Installation is done");
                            openswaninstallField.setDisable(false);
                            openswanLabel.setDisable(false);
                            xl2tpdLabel.setDisable(false);
                            xl2tpdinstallField.setDisable(false);
                            ipsecpppLabel.setDisable(false);
                            ipsecpppinstallField.setDisable(false);
                            lsofLabel.setDisable(false);
                            lsofinstallField.setDisable(false);
                            strongswanLabel.setDisable(false);
                            strongswaninstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
                else
                {
                    openswaninstallField.setText("Uninstalled");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            openswaninstallField.setDisable(true);
                            openswanLabel.setDisable(true);
                            xl2tpdLabel.setDisable(true);
                            xl2tpdinstallField.setDisable(true);
                            ipsecpppLabel.setDisable(true);
                            ipsecpppinstallField.setDisable(true);
                            lsofLabel.setDisable(true);
                            lsofinstallField.setDisable(true);
                            strongswanLabel.setDisable(true);
                            strongswaninstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get purge -yq openswan");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Uninstallation is done");
                            openswaninstallField.setDisable(false);
                            openswanLabel.setDisable(false);
                            xl2tpdLabel.setDisable(false);
                            xl2tpdinstallField.setDisable(false);
                            ipsecpppLabel.setDisable(false);
                            ipsecpppinstallField.setDisable(false);
                            lsofLabel.setDisable(false);
                            lsofinstallField.setDisable(false);
                            strongswanLabel.setDisable(false);
                            strongswaninstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            nmapinstallField.setText("Uninstalled");
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
    }

    @FXML
    private void installUninstallXl2tpd(ActionEvent event) {
                if(xl2tpdinstallField.isSelected()==true)
                {
                    xl2tpdinstallField.setText("Installed");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            openswaninstallField.setDisable(true);
                            openswanLabel.setDisable(true);
                            xl2tpdLabel.setDisable(true);
                            xl2tpdinstallField.setDisable(true);
                            ipsecpppLabel.setDisable(true);
                            ipsecpppinstallField.setDisable(true);
                            lsofLabel.setDisable(true);
                            lsofinstallField.setDisable(true);
                            strongswanLabel.setDisable(true);
                            strongswaninstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get install -yq xl2tpd");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Installation is done");
                            openswaninstallField.setDisable(false);
                            openswanLabel.setDisable(false);
                            xl2tpdLabel.setDisable(false);
                            xl2tpdinstallField.setDisable(false);
                            ipsecpppLabel.setDisable(false);
                            ipsecpppinstallField.setDisable(false);
                            lsofLabel.setDisable(false);
                            lsofinstallField.setDisable(false);
                            strongswanLabel.setDisable(false);
                            strongswaninstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
                else
                {
                    xl2tpdinstallField.setText("Uninstalled");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            openswaninstallField.setDisable(true);
                            openswanLabel.setDisable(true);
                            xl2tpdLabel.setDisable(true);
                            xl2tpdinstallField.setDisable(true);
                            ipsecpppLabel.setDisable(true);
                            ipsecpppinstallField.setDisable(true);
                            lsofLabel.setDisable(true);
                            lsofinstallField.setDisable(true);
                            strongswanLabel.setDisable(true);
                            strongswaninstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get purge -yq xl2tpd");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Uninstallation is done");
                            openswaninstallField.setDisable(false);
                            openswanLabel.setDisable(false);
                            xl2tpdLabel.setDisable(false);
                            xl2tpdinstallField.setDisable(false);
                            ipsecpppLabel.setDisable(false);
                            ipsecpppinstallField.setDisable(false);
                            lsofLabel.setDisable(false);
                            lsofinstallField.setDisable(false);
                            strongswanLabel.setDisable(false);
                            strongswaninstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
    }

    @FXML
    private void installUninstallPPP(ActionEvent event) {
                if(ipsecpppinstallField.isSelected()==true)
                {
                    ipsecpppinstallField.setText("Installed");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            openswaninstallField.setDisable(true);
                            openswanLabel.setDisable(true);
                            xl2tpdLabel.setDisable(true);
                            xl2tpdinstallField.setDisable(true);
                            ipsecpppLabel.setDisable(true);
                            ipsecpppinstallField.setDisable(true);
                            lsofLabel.setDisable(true);
                            lsofinstallField.setDisable(true);
                            strongswanLabel.setDisable(true);
                            strongswaninstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get install -yq ppp");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Installation is done");
                            openswaninstallField.setDisable(false);
                            openswanLabel.setDisable(false);
                            xl2tpdLabel.setDisable(false);
                            xl2tpdinstallField.setDisable(false);
                            ipsecpppLabel.setDisable(false);
                            ipsecpppinstallField.setDisable(false);
                            lsofLabel.setDisable(false);
                            lsofinstallField.setDisable(false);
                            strongswanLabel.setDisable(false);
                            strongswaninstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
                else
                {
                    ipsecpppinstallField.setText("Uninstalled");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            openswaninstallField.setDisable(true);
                            openswanLabel.setDisable(true);
                            xl2tpdLabel.setDisable(true);
                            xl2tpdinstallField.setDisable(true);
                            ipsecpppLabel.setDisable(true);
                            ipsecpppinstallField.setDisable(true);
                            lsofLabel.setDisable(true);
                            lsofinstallField.setDisable(true);
                            strongswanLabel.setDisable(true);
                            strongswaninstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get purge -yq ppp");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Uninstallation is done");
                            openswaninstallField.setDisable(false);
                            openswanLabel.setDisable(false);
                            xl2tpdLabel.setDisable(false);
                            xl2tpdinstallField.setDisable(false);
                            ipsecpppLabel.setDisable(false);
                            ipsecpppinstallField.setDisable(false);
                            lsofLabel.setDisable(false);
                            lsofinstallField.setDisable(false);
                            strongswanLabel.setDisable(false);
                            strongswaninstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            nmapinstallField.setText("Uninstalled");
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
    }

    @FXML
    private void installUninstallLsof(ActionEvent event) {
                if(lsofinstallField.isSelected()==true)
                {
                    lsofinstallField.setText("Installed");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            openswaninstallField.setDisable(true);
                            openswanLabel.setDisable(true);
                            xl2tpdLabel.setDisable(true);
                            xl2tpdinstallField.setDisable(true);
                            ipsecpppLabel.setDisable(true);
                            ipsecpppinstallField.setDisable(true);
                            lsofLabel.setDisable(true);
                            lsofinstallField.setDisable(true);
                            strongswanLabel.setDisable(true);
                            strongswaninstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get install -yq lsof");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Installation is done");
                            openswaninstallField.setDisable(false);
                            openswanLabel.setDisable(false);
                            xl2tpdLabel.setDisable(false);
                            xl2tpdinstallField.setDisable(false);
                            ipsecpppLabel.setDisable(false);
                            ipsecpppinstallField.setDisable(false);
                            lsofLabel.setDisable(false);
                            lsofinstallField.setDisable(false);
                            strongswanLabel.setDisable(false);
                            strongswaninstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
                else
                {
                    lsofinstallField.setText("Uninstalled");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            openswaninstallField.setDisable(true);
                            openswanLabel.setDisable(true);
                            xl2tpdLabel.setDisable(true);
                            xl2tpdinstallField.setDisable(true);
                            ipsecpppLabel.setDisable(true);
                            ipsecpppinstallField.setDisable(true);
                            lsofLabel.setDisable(true);
                            lsofinstallField.setDisable(true);
                            strongswanLabel.setDisable(true);
                            strongswaninstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get purge -yq lsof");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Uninstallation is done");
                            openswaninstallField.setDisable(false);
                            openswanLabel.setDisable(false);
                            xl2tpdLabel.setDisable(false);
                            xl2tpdinstallField.setDisable(false);
                            ipsecpppLabel.setDisable(false);
                            ipsecpppinstallField.setDisable(false);
                            lsofLabel.setDisable(false);
                            lsofinstallField.setDisable(false);
                            strongswanLabel.setDisable(false);
                            strongswaninstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            nmapinstallField.setText("Uninstalled");
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
    }

    @FXML
    private void installUninstallIperf3(ActionEvent event) {
                if(iperf3installField.isSelected()==true)
                {
                    iperf3installField.setText("Installed");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            testToolsFields.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get install -yq iperf3");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Installation is done");
                            testToolsFields.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
                else
                {
                    iperf3installField.setText("Uninstalled");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            testToolsFields.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get purge -yq iperf3");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Uninstallation is done");
                            testToolsFields.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
    }

    @FXML
    private void installUninstallIperf(ActionEvent event) {
                if(iperfinstallField.isSelected()==true)
                {
                    iperfinstallField.setText("Installed");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            nmapLabel.setDisable(true);
                            nmapinstallField.setDisable(true);
                            iperf3installField.setDisable(true);
                            iperfLabel.setDisable(true);
                            iperf3Label.setDisable(true);
                            snmpLabel.setDisable(true);
                            tracerouteLabel.setDisable(true);
                            wiresharkLabel.setDisable(true);
                            dnsutilsLabel.setDisable(true);
                            ftpLabel.setDisable(true);
                            iperfinstallField.setDisable(true);
                            snmpinstallField.setDisable(true);
                            tracerouteinstallField.setDisable(true);
                            dsnutilsinstallField.setDisable(true);
                            wiresharkinstallField.setDisable(true);
                            ftpinstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get install -yq iperf");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Installation is done");
                            nmapLabel.setDisable(false);
                            nmapinstallField.setDisable(false);
                            iperf3installField.setDisable(false);
                            iperfLabel.setDisable(false);
                            iperf3Label.setDisable(false);
                            snmpLabel.setDisable(false);
                            tracerouteLabel.setDisable(false);
                            wiresharkLabel.setDisable(false);
                            dnsutilsLabel.setDisable(false);
                            ftpLabel.setDisable(false);
                            iperfinstallField.setDisable(false);
                            snmpinstallField.setDisable(false);
                            tracerouteinstallField.setDisable(false);
                            dsnutilsinstallField.setDisable(false);
                            wiresharkinstallField.setDisable(false);
                            ftpinstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
                else
                {
                    iperfinstallField.setText("Uninstalled");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            nmapLabel.setDisable(true);
                            nmapinstallField.setDisable(true);
                            iperf3installField.setDisable(true);
                            iperfLabel.setDisable(true);
                            iperf3Label.setDisable(true);
                            snmpLabel.setDisable(true);
                            tracerouteLabel.setDisable(true);
                            wiresharkLabel.setDisable(true);
                            dnsutilsLabel.setDisable(true);
                            ftpLabel.setDisable(true);
                            iperfinstallField.setDisable(true);
                            snmpinstallField.setDisable(true);
                            tracerouteinstallField.setDisable(true);
                            dsnutilsinstallField.setDisable(true);
                            wiresharkinstallField.setDisable(true);
                            ftpinstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get purge -yq iperf");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Uninstallation is done");
                            nmapLabel.setDisable(false);
                            nmapinstallField.setDisable(false);
                            iperf3installField.setDisable(false);
                            iperfLabel.setDisable(false);
                            iperf3Label.setDisable(false);
                            snmpLabel.setDisable(false);
                            tracerouteLabel.setDisable(false);
                            wiresharkLabel.setDisable(false);
                            dnsutilsLabel.setDisable(false);
                            ftpLabel.setDisable(false);
                            iperfinstallField.setDisable(false);
                            snmpinstallField.setDisable(false);
                            tracerouteinstallField.setDisable(false);
                            dsnutilsinstallField.setDisable(false);
                            wiresharkinstallField.setDisable(false);
                            ftpinstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            nmapinstallField.setText("Uninstalled");
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
    }

    @FXML
    private void installUninstallNmap(ActionEvent event) {
                if(nmapinstallField.isSelected()==true)
                {
                    nmapinstallField.setText("Installed");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            nmapLabel.setDisable(true);
                            nmapinstallField.setDisable(true);
                            iperf3installField.setDisable(true);
                            iperfLabel.setDisable(true);
                            iperf3Label.setDisable(true);
                            snmpLabel.setDisable(true);
                            tracerouteLabel.setDisable(true);
                            wiresharkLabel.setDisable(true);
                            dnsutilsLabel.setDisable(true);
                            ftpLabel.setDisable(true);
                            iperfinstallField.setDisable(true);
                            snmpinstallField.setDisable(true);
                            tracerouteinstallField.setDisable(true);
                            dsnutilsinstallField.setDisable(true);
                            wiresharkinstallField.setDisable(true);
                            ftpinstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get install -yq nmap");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Installation is done");
                            nmapLabel.setDisable(false);
                            nmapinstallField.setDisable(false);
                            iperf3installField.setDisable(false);
                            iperfLabel.setDisable(false);
                            iperf3Label.setDisable(false);
                            snmpLabel.setDisable(false);
                            tracerouteLabel.setDisable(false);
                            wiresharkLabel.setDisable(false);
                            dnsutilsLabel.setDisable(false);
                            ftpLabel.setDisable(false);
                            iperfinstallField.setDisable(false);
                            snmpinstallField.setDisable(false);
                            tracerouteinstallField.setDisable(false);
                            dsnutilsinstallField.setDisable(false);
                            wiresharkinstallField.setDisable(false);
                            ftpinstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
                else
                {
                    nmapinstallField.setText("Uninstalled");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            nmapLabel.setDisable(true);
                            nmapinstallField.setDisable(true);
                            iperf3installField.setDisable(true);
                            iperfLabel.setDisable(true);
                            iperf3Label.setDisable(true);
                            snmpLabel.setDisable(true);
                            tracerouteLabel.setDisable(true);
                            wiresharkLabel.setDisable(true);
                            dnsutilsLabel.setDisable(true);
                            ftpLabel.setDisable(true);
                            iperfinstallField.setDisable(true);
                            snmpinstallField.setDisable(true);
                            tracerouteinstallField.setDisable(true);
                            dsnutilsinstallField.setDisable(true);
                            wiresharkinstallField.setDisable(true);
                            ftpinstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get purge -yq nmap");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Uninstallation is done");
                            nmapLabel.setDisable(false);
                            nmapinstallField.setDisable(false);
                            iperf3installField.setDisable(false);
                            iperfLabel.setDisable(false);
                            iperf3Label.setDisable(false);
                            snmpLabel.setDisable(false);
                            tracerouteLabel.setDisable(false);
                            wiresharkLabel.setDisable(false);
                            dnsutilsLabel.setDisable(false);
                            ftpLabel.setDisable(false);
                            iperfinstallField.setDisable(false);
                            snmpinstallField.setDisable(false);
                            tracerouteinstallField.setDisable(false);
                            dsnutilsinstallField.setDisable(false);
                            wiresharkinstallField.setDisable(false);
                            ftpinstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            nmapinstallField.setText("Uninstalled");
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
    }

    @FXML
    private void installUninstallFTP(ActionEvent event) {
                if(ftpinstallField.isSelected()==true)
                {
                    ftpinstallField.setText("Installed");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            nmapLabel.setDisable(true);
                            nmapinstallField.setDisable(true);
                            iperf3installField.setDisable(true);
                            iperfLabel.setDisable(true);
                            iperf3Label.setDisable(true);
                            snmpLabel.setDisable(true);
                            tracerouteLabel.setDisable(true);
                            wiresharkLabel.setDisable(true);
                            dnsutilsLabel.setDisable(true);
                            ftpLabel.setDisable(true);
                            iperfinstallField.setDisable(true);
                            snmpinstallField.setDisable(true);
                            tracerouteinstallField.setDisable(true);
                            dsnutilsinstallField.setDisable(true);
                            wiresharkinstallField.setDisable(true);
                            ftpinstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get install -yq ftp");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Installation is done");
                            nmapLabel.setDisable(false);
                            nmapinstallField.setDisable(false);
                            iperf3installField.setDisable(false);
                            iperfLabel.setDisable(false);
                            iperf3Label.setDisable(false);
                            snmpLabel.setDisable(false);
                            tracerouteLabel.setDisable(false);
                            wiresharkLabel.setDisable(false);
                            dnsutilsLabel.setDisable(false);
                            ftpLabel.setDisable(false);
                            iperfinstallField.setDisable(false);
                            snmpinstallField.setDisable(false);
                            tracerouteinstallField.setDisable(false);
                            dsnutilsinstallField.setDisable(false);
                            wiresharkinstallField.setDisable(false);
                            ftpinstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
                else
                {
                    ftpinstallField.setText("Uninstalled");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            nmapLabel.setDisable(true);
                            nmapinstallField.setDisable(true);
                            iperf3installField.setDisable(true);
                            iperfLabel.setDisable(true);
                            iperf3Label.setDisable(true);
                            snmpLabel.setDisable(true);
                            tracerouteLabel.setDisable(true);
                            wiresharkLabel.setDisable(true);
                            dnsutilsLabel.setDisable(true);
                            ftpLabel.setDisable(true);
                            iperfinstallField.setDisable(true);
                            snmpinstallField.setDisable(true);
                            tracerouteinstallField.setDisable(true);
                            dsnutilsinstallField.setDisable(true);
                            wiresharkinstallField.setDisable(true);
                            ftpinstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get purge -yq ftp");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Uninstallation is done");
                            nmapLabel.setDisable(false);
                            nmapinstallField.setDisable(false);
                            iperf3installField.setDisable(false);
                            iperfLabel.setDisable(false);
                            iperf3Label.setDisable(false);
                            snmpLabel.setDisable(false);
                            tracerouteLabel.setDisable(false);
                            wiresharkLabel.setDisable(false);
                            dnsutilsLabel.setDisable(false);
                            ftpLabel.setDisable(false);
                            iperfinstallField.setDisable(false);
                            snmpinstallField.setDisable(false);
                            tracerouteinstallField.setDisable(false);
                            dsnutilsinstallField.setDisable(false);
                            wiresharkinstallField.setDisable(false);
                            ftpinstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            nmapinstallField.setText("Uninstalled");
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
    }

    @FXML
    private void installUninstallSnmp(ActionEvent event) {
                if(snmpinstallField.isSelected()==true)
                {
                    snmpinstallField.setText("Installed");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            nmapLabel.setDisable(true);
                            nmapinstallField.setDisable(true);
                            iperf3installField.setDisable(true);
                            iperfLabel.setDisable(true);
                            iperf3Label.setDisable(true);
                            snmpLabel.setDisable(true);
                            tracerouteLabel.setDisable(true);
                            wiresharkLabel.setDisable(true);
                            dnsutilsLabel.setDisable(true);
                            ftpLabel.setDisable(true);
                            iperfinstallField.setDisable(true);
                            snmpinstallField.setDisable(true);
                            tracerouteinstallField.setDisable(true);
                            dsnutilsinstallField.setDisable(true);
                            wiresharkinstallField.setDisable(true);
                            ftpinstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get install -yq snmpd");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Installation is done");
                            nmapLabel.setDisable(false);
                            nmapinstallField.setDisable(false);
                            iperf3installField.setDisable(false);
                            iperfLabel.setDisable(false);
                            iperf3Label.setDisable(false);
                            snmpLabel.setDisable(false);
                            tracerouteLabel.setDisable(false);
                            wiresharkLabel.setDisable(false);
                            dnsutilsLabel.setDisable(false);
                            ftpLabel.setDisable(false);
                            iperfinstallField.setDisable(false);
                            snmpinstallField.setDisable(false);
                            tracerouteinstallField.setDisable(false);
                            dsnutilsinstallField.setDisable(false);
                            wiresharkinstallField.setDisable(false);
                            ftpinstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
                else
                {
                    snmpinstallField.setText("Uninstalled");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            nmapLabel.setDisable(true);
                            nmapinstallField.setDisable(true);
                            iperf3installField.setDisable(true);
                            iperfLabel.setDisable(true);
                            iperf3Label.setDisable(true);
                            snmpLabel.setDisable(true);
                            tracerouteLabel.setDisable(true);
                            wiresharkLabel.setDisable(true);
                            dnsutilsLabel.setDisable(true);
                            ftpLabel.setDisable(true);
                            iperfinstallField.setDisable(true);
                            snmpinstallField.setDisable(true);
                            tracerouteinstallField.setDisable(true);
                            dsnutilsinstallField.setDisable(true);
                            wiresharkinstallField.setDisable(true);
                            ftpinstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("DelSNMP.sh");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Uninstallation is done");
                            nmapLabel.setDisable(false);
                            nmapinstallField.setDisable(false);
                            iperf3installField.setDisable(false);
                            iperfLabel.setDisable(false);
                            iperf3Label.setDisable(false);
                            snmpLabel.setDisable(false);
                            tracerouteLabel.setDisable(false);
                            wiresharkLabel.setDisable(false);
                            dnsutilsLabel.setDisable(false);
                            ftpLabel.setDisable(false);
                            iperfinstallField.setDisable(false);
                            snmpinstallField.setDisable(false);
                            tracerouteinstallField.setDisable(false);
                            dsnutilsinstallField.setDisable(false);
                            wiresharkinstallField.setDisable(false);
                            ftpinstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
    }

    @FXML
    private void installUninstallDNSUtils(ActionEvent event) {
                if(dsnutilsinstallField.isSelected()==true)
                {
                    dsnutilsinstallField.setText("Installed");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            nmapLabel.setDisable(true);
                            nmapinstallField.setDisable(true);
                            iperf3installField.setDisable(true);
                            iperfLabel.setDisable(true);
                            iperf3Label.setDisable(true);
                            snmpLabel.setDisable(true);
                            tracerouteLabel.setDisable(true);
                            wiresharkLabel.setDisable(true);
                            dnsutilsLabel.setDisable(true);
                            ftpLabel.setDisable(true);
                            iperfinstallField.setDisable(true);
                            snmpinstallField.setDisable(true);
                            tracerouteinstallField.setDisable(true);
                            dsnutilsinstallField.setDisable(true);
                            wiresharkinstallField.setDisable(true);
                            ftpinstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get install -yq dnsutils");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Installation is done");
                            nmapLabel.setDisable(false);
                            nmapinstallField.setDisable(false);
                            iperf3installField.setDisable(false);
                            iperfLabel.setDisable(false);
                            iperf3Label.setDisable(false);
                            snmpLabel.setDisable(false);
                            tracerouteLabel.setDisable(false);
                            wiresharkLabel.setDisable(false);
                            dnsutilsLabel.setDisable(false);
                            ftpLabel.setDisable(false);
                            iperfinstallField.setDisable(false);
                            snmpinstallField.setDisable(false);
                            tracerouteinstallField.setDisable(false);
                            dsnutilsinstallField.setDisable(false);
                            wiresharkinstallField.setDisable(false);
                            ftpinstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
                else
                {
                    dsnutilsinstallField.setText("Uninstalled");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            nmapLabel.setDisable(true);
                            nmapinstallField.setDisable(true);
                            iperf3installField.setDisable(true);
                            iperfLabel.setDisable(true);
                            iperf3Label.setDisable(true);
                            snmpLabel.setDisable(true);
                            tracerouteLabel.setDisable(true);
                            wiresharkLabel.setDisable(true);
                            dnsutilsLabel.setDisable(true);
                            ftpLabel.setDisable(true);
                            iperfinstallField.setDisable(true);
                            snmpinstallField.setDisable(true);
                            tracerouteinstallField.setDisable(true);
                            dsnutilsinstallField.setDisable(true);
                            wiresharkinstallField.setDisable(true);
                            ftpinstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get purge -yq dnsutils");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Uninstallation is done");
                            nmapLabel.setDisable(false);
                            nmapinstallField.setDisable(false);
                            iperf3installField.setDisable(false);
                            iperfLabel.setDisable(false);
                            iperf3Label.setDisable(false);
                            snmpLabel.setDisable(false);
                            tracerouteLabel.setDisable(false);
                            wiresharkLabel.setDisable(false);
                            dnsutilsLabel.setDisable(false);
                            ftpLabel.setDisable(false);
                            iperfinstallField.setDisable(false);
                            snmpinstallField.setDisable(false);
                            tracerouteinstallField.setDisable(false);
                            dsnutilsinstallField.setDisable(false);
                            wiresharkinstallField.setDisable(false);
                            ftpinstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
    }

    @FXML
    private void installUninstallTraceroute(ActionEvent event) {
                if(tracerouteinstallField.isSelected()==true)
                {
                    tracerouteinstallField.setText("Installed");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            nmapLabel.setDisable(true);
                            nmapinstallField.setDisable(true);
                            iperf3installField.setDisable(true);
                            iperfLabel.setDisable(true);
                            iperf3Label.setDisable(true);
                            snmpLabel.setDisable(true);
                            wiresharkLabel.setDisable(true);
                            dnsutilsLabel.setDisable(true);
                            ftpLabel.setDisable(true);
                            iperfinstallField.setDisable(true);
                            snmpinstallField.setDisable(true);
                            tracerouteLabel.setDisable(true);
                            tracerouteinstallField.setDisable(true);
                            dsnutilsinstallField.setDisable(true);
                            wiresharkinstallField.setDisable(true);
                            ftpinstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get install -yq traceroute");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Installation is done");
                            nmapLabel.setDisable(false);
                            nmapinstallField.setDisable(false);
                            iperf3installField.setDisable(false);
                            iperfLabel.setDisable(false);
                            iperf3Label.setDisable(false);
                            snmpLabel.setDisable(false);
                            wiresharkLabel.setDisable(false);
                            dnsutilsLabel.setDisable(false);
                            ftpLabel.setDisable(false);
                            iperfinstallField.setDisable(false);
                            snmpinstallField.setDisable(false);
                            tracerouteLabel.setDisable(false);
                            tracerouteinstallField.setDisable(false);
                            dsnutilsinstallField.setDisable(false);
                            wiresharkinstallField.setDisable(false);
                            ftpinstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
                else
                {
                    tracerouteinstallField.setText("Uninstalled");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            nmapLabel.setDisable(true);
                            nmapinstallField.setDisable(true);
                            iperf3installField.setDisable(true);
                            iperfLabel.setDisable(true);
                            iperf3Label.setDisable(true);
                            snmpLabel.setDisable(true);
                            wiresharkLabel.setDisable(true);
                            dnsutilsLabel.setDisable(true);
                            ftpLabel.setDisable(true);
                            iperfinstallField.setDisable(true);
                            snmpinstallField.setDisable(true);
                            tracerouteLabel.setDisable(true);
                            tracerouteinstallField.setDisable(true);
                            dsnutilsinstallField.setDisable(true);
                            wiresharkinstallField.setDisable(true);
                            ftpinstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get purge -yq traceroute");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Uninstallation is done");
                            nmapLabel.setDisable(false);
                            nmapinstallField.setDisable(false);
                            iperf3installField.setDisable(false);
                            iperfLabel.setDisable(false);
                            iperf3Label.setDisable(false);
                            snmpLabel.setDisable(false);
                            wiresharkLabel.setDisable(false);
                            dnsutilsLabel.setDisable(false);
                            ftpLabel.setDisable(false);
                            iperfinstallField.setDisable(false);
                            snmpinstallField.setDisable(false);
                            tracerouteLabel.setDisable(true);
                            tracerouteinstallField.setDisable(true);
                            dsnutilsinstallField.setDisable(false);
                            wiresharkinstallField.setDisable(false);
                            ftpinstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
    }

    @FXML
    private void installUninstallWireshark(ActionEvent event) {
                if(wiresharkinstallField.isSelected()==true)
                {
                    wiresharkinstallField.setText("Installed");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            nmapLabel.setDisable(true);
                            nmapinstallField.setDisable(true);
                            iperf3installField.setDisable(true);
                            iperfLabel.setDisable(true);
                            iperf3Label.setDisable(true);
                            snmpLabel.setDisable(true);
                            tracerouteLabel.setDisable(true);
                            wiresharkLabel.setDisable(true);
                            dnsutilsLabel.setDisable(true);
                            ftpLabel.setDisable(true);
                            iperfinstallField.setDisable(true);
                            snmpinstallField.setDisable(true);
                            tracerouteinstallField.setDisable(true);
                            dsnutilsinstallField.setDisable(true);
                            wiresharkinstallField.setDisable(true);
                            ftpinstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get install -yq wireshark");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Installation is done");
                            nmapLabel.setDisable(false);
                            nmapinstallField.setDisable(false);
                            iperf3installField.setDisable(false);
                            iperfLabel.setDisable(false);
                            iperf3Label.setDisable(false);
                            snmpLabel.setDisable(false);
                            tracerouteLabel.setDisable(false);
                            wiresharkLabel.setDisable(false);
                            dnsutilsLabel.setDisable(false);
                            ftpLabel.setDisable(false);
                            iperfinstallField.setDisable(false);
                            snmpinstallField.setDisable(false);
                            tracerouteinstallField.setDisable(false);
                            dsnutilsinstallField.setDisable(false);
                            wiresharkinstallField.setDisable(false);
                            ftpinstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
                else
                {
                    wiresharkinstallField.setText("Uninstalled");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            nmapLabel.setDisable(true);
                            nmapinstallField.setDisable(true);
                            iperf3installField.setDisable(true);
                            iperfLabel.setDisable(true);
                            iperf3Label.setDisable(true);
                            snmpLabel.setDisable(true);
                            tracerouteLabel.setDisable(true);
                            wiresharkLabel.setDisable(true);
                            dnsutilsLabel.setDisable(true);
                            ftpLabel.setDisable(true);
                            iperfinstallField.setDisable(true);
                            snmpinstallField.setDisable(true);
                            tracerouteinstallField.setDisable(true);
                            dsnutilsinstallField.setDisable(true);
                            wiresharkinstallField.setDisable(true);
                            ftpinstallField.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get purge -yq wireshark");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Uninstallation is done");
                            nmapLabel.setDisable(false);
                            nmapinstallField.setDisable(false);
                            iperf3installField.setDisable(false);
                            iperfLabel.setDisable(false);
                            iperf3Label.setDisable(false);
                            snmpLabel.setDisable(false);
                            tracerouteLabel.setDisable(false);
                            wiresharkLabel.setDisable(false);
                            dnsutilsLabel.setDisable(false);
                            ftpLabel.setDisable(false);
                            iperfinstallField.setDisable(false);
                            snmpinstallField.setDisable(false);
                            tracerouteinstallField.setDisable(false);
                            dsnutilsinstallField.setDisable(false);
                            wiresharkinstallField.setDisable(false);
                            ftpinstallField.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
    }

    @FXML
    private void installUninstallPPPd(ActionEvent event) {
                if(pppdinstallField.isSelected()==true)
                {
                    pppdinstallField.setText("Installed");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            pppdinstallField.setDisable(true);
                            pppdLabel.setDisable(true);
                            pppoeconfinstallField.setDisable(true);
                            pppoeconfLabel.setDisable(true);
                            pptpinstallField.setDisable(true);
                            pptpLabel.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get install -yq pppd");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Installation is done");
                            pppdinstallField.setDisable(false);
                            pppdLabel.setDisable(false);
                            pppoeconfinstallField.setDisable(false);
                            pppoeconfLabel.setDisable(false);
                            pptpinstallField.setDisable(false);
                            pptpLabel.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
                else
                {
                    pppdinstallField.setText("Uninstalled");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            pppdinstallField.setDisable(true);
                            pppdLabel.setDisable(true);
                            pppoeconfinstallField.setDisable(true);
                            pppoeconfLabel.setDisable(true);
                            pptpinstallField.setDisable(true);
                            pptpLabel.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("apt-get purge -yq pppd");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Uninstallation is done");
                            pppdinstallField.setDisable(false);
                            pppdLabel.setDisable(false);
                            pppoeconfinstallField.setDisable(false);
                            pppoeconfLabel.setDisable(false);
                            pptpinstallField.setDisable(false);
                            pptpLabel.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
    }

    @FXML
    private void installUninstallPPPoEConf(ActionEvent event) {
                if(pppoeconfinstallField.isSelected()==true)
                {
                    pppoeconfinstallField.setText("Installed");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            pppdinstallField.setDisable(true);
                            pppdLabel.setDisable(true);
                            pppoeconfinstallField.setDisable(true);
                            pppoeconfLabel.setDisable(true);
                            pptpinstallField.setDisable(true);
                            pptpLabel.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("InstallPPPoE.sh");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Installation is done");
                            pppdinstallField.setDisable(false);
                            pppdLabel.setDisable(false);
                            pppoeconfinstallField.setDisable(false);
                            pppoeconfLabel.setDisable(false);
                            pptpinstallField.setDisable(false);
                            pptpLabel.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
                else
                {
                    pppoeconfinstallField.setText("Uninstalled");
                    Thread t20 = new Thread(){
                    @Override
                    public void run(){
                        try {
                            pppdinstallField.setDisable(true);
                            pppdLabel.setDisable(true);
                            pppoeconfinstallField.setDisable(true);
                            pppoeconfLabel.setDisable(true);
                            pptpinstallField.setDisable(true);
                            pptpLabel.setDisable(true);
                            updateButton.setDisable(true);
                            previousButton.setDisable(true);
                            installationprogressBar.setVisible(true);
                            list1.setDisable(true);
                            list2.setDisable(true);
                            String result ="";
                            p = Runtime.getRuntime().exec("DelPPPoE.sh");
                            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                            BufferedReader br=new BufferedReader(ipsr);
                            String ligne;
                            while ((ligne=br.readLine())!=null)
                            {
                                System.out.println(ligne);
                                result += ligne;
                            }
                            System.out.println("Uninstallation is done");
                            pppdinstallField.setDisable(false);
                            pppdLabel.setDisable(false);
                            pppoeconfinstallField.setDisable(false);
                            pppoeconfLabel.setDisable(false);
                            pptpinstallField.setDisable(false);
                            pptpLabel.setDisable(false);
                            updateButton.setDisable(false);
                            previousButton.setDisable(false);
                            installationprogressBar.setVisible(false);
                            list1.setDisable(false);
                            list2.setDisable(false);
                            } catch (IOException ex) {
                                Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t20.start();
                }
    }

    @FXML
    private void installUninstallPPTP(ActionEvent event) {
        if(pptpinstallField.isSelected()==true)
        {
            pptpinstallField.setText("Installed");
            Thread t20 = new Thread(){
                @Override
                public void run(){
                    try {
                        pppdinstallField.setDisable(true);
                        pppdLabel.setDisable(true);
                        pppoeconfinstallField.setDisable(true);
                        pppoeconfLabel.setDisable(true);
                        pptpinstallField.setDisable(true);
                        pptpLabel.setDisable(true);
                        updateButton.setDisable(true);
                        previousButton.setDisable(true);
                        installationprogressBar.setVisible(true);
                        list1.setDisable(true);
                        list2.setDisable(true);
                        String result ="";
                        p = Runtime.getRuntime().exec("apt-get install -yq pptpd");
                        InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                        BufferedReader br=new BufferedReader(ipsr);
                        String ligne;
                        while ((ligne=br.readLine())!=null)
                        {
                            System.out.println(ligne);
                            result += ligne;
                        }
                        System.out.println("Installation is done");
                        pppdinstallField.setDisable(false);
                        pppdLabel.setDisable(false);
                        pppoeconfinstallField.setDisable(false);
                        pppoeconfLabel.setDisable(false);
                        pptpinstallField.setDisable(false);
                        pptpLabel.setDisable(false);
                        updateButton.setDisable(false);
                        previousButton.setDisable(false);
                        installationprogressBar.setVisible(false);
                        list1.setDisable(false);
                        list2.setDisable(false);
                    } catch (IOException ex) {
                        Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            t20.start();
        } else {
            pptpinstallField.setText("Uninstalled");
            Thread t20 = new Thread(){
                @Override
                public void run(){
                    try {
                        pppdinstallField.setDisable(true);
                        pppdLabel.setDisable(true);
                        pppoeconfinstallField.setDisable(true);
                        pppoeconfLabel.setDisable(true);
                        pptpinstallField.setDisable(true);
                        pptpLabel.setDisable(true);
                        updateButton.setDisable(true);
                        previousButton.setDisable(true);
                        installationprogressBar.setVisible(true);
                        list1.setDisable(true);
                        list2.setDisable(true);
                        String result ="";
                        p = Runtime.getRuntime().exec("apt-get purge -yq pptpd");
                        InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                        BufferedReader br=new BufferedReader(ipsr);
                        String ligne;
                        while ((ligne=br.readLine())!=null)
                        {
                            System.out.println(ligne);
                            result += ligne;
                        }
                        System.out.println("Uninstallation is done");
                        pppdinstallField.setDisable(false);
                        pppdLabel.setDisable(false);
                        pppoeconfinstallField.setDisable(false);
                        pppoeconfLabel.setDisable(false);
                        pptpinstallField.setDisable(false);
                        pptpLabel.setDisable(false);
                        updateButton.setDisable(false);
                        previousButton.setDisable(false);
                        installationprogressBar.setVisible(false);
                        list1.setDisable(false);
                        list2.setDisable(false);
                    } catch (IOException ex) {
                        Logger.getLogger(SoftwareOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            t20.start();
        }
    }

}
