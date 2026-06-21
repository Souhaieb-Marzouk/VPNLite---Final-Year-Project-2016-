/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vpnlite.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author sst
 */
public class PrincipalOverviewController implements Initializable {

    @FXML
    private Button nextinterfaceChange;
    @FXML
    private RadioButton clientRadio;
    @FXML
    private RadioButton serverRadio;
    public static boolean R1, R2;
    @FXML
    private Label osLabel;
    @FXML
    private Label osVersionLabel;
    @FXML
    private Label osArchitectureLabel;
    @FXML
    private Label osReleaseLabel;
    @FXML
    private Button softCheckButton;
    private Process p;
    @FXML
    private Button clearScripts;
    
    static String iperf3Installation;
    static String tracerouteInstallation;
    static String wiresharkInstallation;
    static String xl2tpdInstallation;
    static String pppInstallation;
    static String lsofInstallation;
    static String strongswanInstallation;
    static String pppoeconfInstallation;
    static String pptpInstallation;
    static String ethtoolInstallation;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        R1 = false;
        R2 = false;
        ToggleGroup gender = new ToggleGroup();
        clientRadio.setToggleGroup(gender);
        serverRadio.setToggleGroup(gender);
        osLabel.setText(osLabel.getText() + System.getProperty("os.name"));
        osVersionLabel.setText(osVersionLabel.getText() + System.getProperty("os.version"));
        osArchitectureLabel.setText(osArchitectureLabel.getText() + System.getProperty("os.arch"));
        
        Thread t1 = new Thread(){
        @Override
        public void run(){
            try {
                String result ="";
                p = Runtime.getRuntime().exec("cat /etc/issue.net");
                InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                BufferedReader br=new BufferedReader(ipsr);
                String ligne;
                while ((ligne=br.readLine())!=null)
                {
                    osReleaseLabel.setText(osReleaseLabel.getText() + ligne);
                    result += ligne;
                }
                } catch (IOException ex) {
                    Logger.getLogger(PrincipalOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        t1.start();
        
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
                            strongswanInstallation="Installed";
                        }else if(ligne.equals("nothing")){
                            strongswanInstallation="Uninstalled";
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(PrincipalOverviewController.class.getName()).log(Level.SEVERE, null, ex);
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
                            xl2tpdInstallation="Installed";
                        }else if(ligne.equals("nothing")){
                            xl2tpdInstallation="Uninstalled";
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(PrincipalOverviewController.class.getName()).log(Level.SEVERE, null, ex);
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
                            pppInstallation="Installed";
                        }else if(ligne.equals("nothing")){
                            pppInstallation="Uninstalled";
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(PrincipalOverviewController.class.getName()).log(Level.SEVERE, null, ex);
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
                            iperf3Installation="Installed";
                        }else if(ligne.equals("nothing")){
                            iperf3Installation="Uninstalled";
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(PrincipalOverviewController.class.getName()).log(Level.SEVERE, null, ex);
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
                            wiresharkInstallation="Installed";
                        }else if(ligne.equals("nothing")){
                            wiresharkInstallation="Uninstalled";
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(PrincipalOverviewController.class.getName()).log(Level.SEVERE, null, ex);
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
                            tracerouteInstallation="Installed";
                        }else if(ligne.equals("nothing")){
                            tracerouteInstallation="Uninstalled";
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(PrincipalOverviewController.class.getName()).log(Level.SEVERE, null, ex);
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
                            lsofInstallation="Installed";
                        }else if(ligne.equals("nothing")){
                            lsofInstallation="Uninstalled";
                        }
                    }
                    } catch (IOException ex) {
                        Logger.getLogger(PrincipalOverviewController.class.getName()).log(Level.SEVERE, null, ex);
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
                            pppoeconfInstallation="Installed";
                        }else if(ligne.equals("nothing")){
                            pppoeconfInstallation="Uninstalled";
                        }
                    }
                    } catch (IOException ex) {
                        Logger.getLogger(PrincipalOverviewController.class.getName()).log(Level.SEVERE, null, ex);
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
                            pptpInstallation="Installed";
                        }else if(ligne.equals("nothing")){
                            pptpInstallation="Uninstalled";
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(PrincipalOverviewController.class.getName()).log(Level.SEVERE, null, ex);
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
                            ethtoolInstallation="Installed";
                        }else if(ligne.equals("nothing")){
                            ethtoolInstallation="Uninstalled";
                        }
                    }
                    } catch (IOException ex) {
                        Logger.getLogger(PrincipalOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        });
    }    

    @FXML
    private void changeNextInterface(ActionEvent event) throws IOException{
        if(clientRadio.isSelected()){
            R1 = true;
            Stage stage = (Stage) nextinterfaceChange.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vpnlite/view/UtilisateurOverview.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setMaximized(true);
            stage.setTitle("VPN Application");
            stage.setScene(new Scene(root));  
            stage.show();
            
        }else if(serverRadio.isSelected()){
            R2 = true;
            Stage stage = (Stage) nextinterfaceChange.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vpnlite/view/VPNOverview.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setMaximized(true);
            stage.setTitle("VPN Application");
            stage.setScene(new Scene(root));  
            stage.show();
            
        }
    }

    @FXML
    private void checkInstalledProgram(ActionEvent event) throws IOException {
            Stage stage = (Stage) softCheckButton.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vpnlite/view/SoftwareOverview.fxml"));
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
    private void clearScriptsFunction(ActionEvent event) throws IOException {
        p = Runtime.getRuntime().exec("rm -r /usr/local/bin/ExternalIPAddress.sh");
        p = Runtime.getRuntime().exec("rm -r /usr/local/bin/CheckProgram.sh");
        p = Runtime.getRuntime().exec("rm -r /usr/local/bin/iptablesrules.sh");
        p = Runtime.getRuntime().exec("rm -r /usr/local/bin/IPSecL2TPConfig.sh");
        p = Runtime.getRuntime().exec("rm -r /usr/local/bin/InterfaceName.sh");
        p = Runtime.getRuntime().exec("rm -r /usr/local/bin/InternalIPAddress.sh");
        p = Runtime.getRuntime().exec("rm -r /usr/local/bin/OsRelease.sh");
        p = Runtime.getRuntime().exec("rm -r /usr/local/bin/PingTest.sh");
        p = Runtime.getRuntime().exec("rm -r /usr/local/bin/Scripts.sh");
        p = Runtime.getRuntime().exec("rm -r /usr/local/bin/TracerouteTest.sh");
        p = Runtime.getRuntime().exec("rm -r /usr/local/bin/aptgetinstall.sh");
        p = Runtime.getRuntime().exec("rm -r /usr/local/bin/export.sh");
        p = Runtime.getRuntime().exec("rm -r /usr/local/bin/vpnclient.sh");
        p = Runtime.getRuntime().exec("rm -r /usr/local/bin/remoteippptp.sh");
        p = Runtime.getRuntime().exec("rm -r /usr/local/bin/ForwardPolicy.sh");
    }
}
