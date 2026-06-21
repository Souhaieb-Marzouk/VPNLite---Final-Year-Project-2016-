/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vpnlite.controller;

import com.jfoenix.controls.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.net.MalformedURLException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tray.animations.AnimationType;
import tray.notification.TrayNotification;
import static vpnlite.controller.PrincipalOverviewController.*;
/**
 * FXML Controller class
 *
 * @author Souhaieb
 */
public class VPNOverviewController extends UtilisateurOverviewController implements Initializable {

    private AnchorPane vpnprotocolView;
    @FXML
    private TextField wanipField;
    @FXML
    private ComboBox<String> vpnprotocolField;
    Process p,p1;
    static String ip;
    @FXML
    private JFXButton previousButton;
    @FXML
    private Button userButton;
    @FXML
    private Button vpnButton;
    @FXML
    private Button testsButton;
    public static boolean T1,A,B;
    public static String Q="";
    @FXML
    private Button nextButton;
    @FXML
    private ImageView backimg;
    @FXML
    private AnchorPane l2tpipsecserverWindow;
    @FXML
    private AnchorPane l2tpipsecclientWindow;
    @FXML
    private AnchorPane l2tppppipsecserverWindow;
    @FXML
    private AnchorPane l2tppppipsecclientWindow;
    @FXML
    private AnchorPane pppoepppipserverWindow;
    @FXML
    private AnchorPane pppoepppipclientWindow;
    @FXML
    private AnchorPane pppoepppipsecserverWindow;
    @FXML
    private AnchorPane pppoepppipsecclientWindow;
    @FXML
    private AnchorPane pptpserverWindow;
    @FXML
    private AnchorPane pptpclientWindow;
    @FXML
    private AnchorPane greserverWindow;
    @FXML
    private AnchorPane greclientWindow;
    Alert alert = new Alert(Alert.AlertType.WARNING);
    @FXML
    private JFXButton validatel2tpipsecSButton;
    @FXML
    private JFXButton validatel2tppppipsecSButton;
    @FXML
    private JFXButton validatepptpSButton;
    @FXML
    private JFXButton validategreSButton;
    @FXML
    private ImageView greimg;
    @FXML
    private JFXTextField usernamel2tppppipsecCField;
    @FXML
    private JFXTextField passwordl2tppppipsecCField;
    @FXML
    private JFXButton validatel2tppppipsecCButton;
    @FXML
    private JFXButton validatepppoepppipSButton;
    @FXML
    private JFXTextField localIPAddressgreCField;
    @FXML
    private JFXTextField RemoteIPAddressgreCField;
    @FXML
    private JFXTextField remoteNetAddrgreCField;
    @FXML
    private JFXTextField greIPAddressgreCField;
    @FXML
    private JFXButton validategreCButton;
    @FXML
    private JFXTextField serveradrpptpCField;
    @FXML
    private JFXTextField usernamepptpCField;
    @FXML
    private JFXTextField passwordpptpCField;
    @FXML
    private JFXButton validatepptpCButton;
    @FXML
    private ImageView pptpimg;
    @FXML
    private JFXRadioButton transportl2tppppipsecSField;
    @FXML
    private JFXRadioButton tunnell2tppppipsecSField;
    @FXML
    private JFXTextField leftAdrl2tppppipsecSField;
    @FXML
    private JFXTextField usernamel2tppppipsecSField;
    @FXML
    private JFXTextField passwordl2tppppipsecSField;
    @FXML
    private JFXTextField serverIPAdrl2tppppipsecSField;
    @FXML
    private JFXTextField ipStartl2tppppipsecSField;
    @FXML
    private JFXTextField ipEndl2tppppipsecSField;
    @FXML
    private JFXTextField keyl2tppppipsecCField;
    @FXML
    private JFXTextField ikel2tppppipsecCField;
    @FXML
    private JFXTextField leftAdrl2tppppipsecCField;
    @FXML
    private JFXTextField rightAdrl2tppppipsecCField;
    @FXML
    private JFXTextField usernamepppoepppipSField;
    @FXML
    private JFXTextField passwordpppoepppipSField;
    @FXML
    private TableView<String> userpppoepppipSTable;
    @FXML
    private TableColumn<String, ?> userpppoepppipSColumn;
    @FXML
    private TableColumn<String, ?> passpppoepppipSColumn;
    @FXML
    private JFXButton adduserpppoepppipSButton;
    @FXML
    private JFXRadioButton pappppoepppipSRadio;
    @FXML
    private JFXTextField serverippppoepppipSField;
    @FXML
    private JFXTextField dns1pppoepppipSField;
    @FXML
    private JFXTextField dns2pppoepppipSField;
    @FXML
    private JFXRadioButton chappppoepppipSRadio;
    @FXML
    private JFXTextField localIppptpSField;
    @FXML
    private JFXTextField remoteIppptpSField;
    @FXML
    private JFXTextField userNamepptpSField;
    @FXML
    private JFXTextField passwordpptpSField;
    @FXML
    private JFXTextField localIPAdrgreSField;
    @FXML
    private JFXTextField RemoteIPAdrgreSField;
    @FXML
    private JFXTextField remoteNetAdrgreSField;
    @FXML
    private JFXTextField greIPAdrgreSField;
    @FXML
    private JFXRadioButton transportl2tpipsecSField;
    @FXML
    private JFXRadioButton tunnell2tpipsecSField;
    @FXML
    private JFXTextField keyl2tpipsecSField;
    @FXML
    private JFXTextField ikel2tpipsecSField;
    @FXML
    private JFXTextField leftAdrl2tpipsecSField;
    @FXML
    private JFXTextField leftsubnetl2tpipsecSField;
    @FXML
    private JFXTextField rightAdrl2tpipsecSField;
    @FXML
    private JFXTextField rightsubnetl2tpipsecSField;
    @FXML
    private JFXTextField serverIPAdrl2tpipsecSField;
    @FXML
    private JFXTextField ipStartl2tpipsecSField;
    @FXML
    private JFXTextField ipEndl2tpipsecSField;
    private static String IntName;
    private String L2TPnet = "192.168.42.0/24";
    private String XAUTHnet = "192.168.43.0/24";
    @FXML
    private JFXTextField ippoolpppoepppipSField;
    @FXML
    private ImageView nextimg;
    @FXML
    private JFXTextField keyl2tpipsecCField;
    @FXML
    private JFXTextField ikel2tpipsecCField;
    @FXML
    private JFXTextField leftAdrl2tpipsecCField;
    @FXML
    private JFXTextField rightAdrl2tpipsecCField;
    @FXML
    private JFXButton validatel2tpipsecCButton;
    @FXML
    private HBox xl2tConfigField;
    @FXML
    private HBox userpassl2tppppipsecFields;
    @FXML
    private HBox l2tpconfigl2tppppipsecFields;
    @FXML
    private JFXButton stopl2tpipsecSButton;
    @FXML
    private JFXButton stopl2tppppipsecSButton;
    @FXML
    private JFXButton stopl2tpipsecCButton;
    @FXML
    private JFXButton stopl2tppppipsecCButton;
    @FXML
    private JFXRadioButton clientSideL2TPIPSecButton;
    @FXML
    private JFXRadioButton wanSideL2TPIPSecButton;
    @FXML
    private JFXComboBox<String> keyexl2tpipsecSField;
    @FXML
    private Label keyl2tpipsecSLabel;
    @FXML
    private Label ikel2tpipsecSLabel;
    @FXML
    private Label keyexl2tpipsecSLabel;
    @FXML
    private Label leftAdrl2tpipsecSLabel;
    @FXML
    private Label leftsubnetl2tpipsecSLabel;
    @FXML
    private Label rightAdrl2tpipsecSLabel;
    @FXML
    private Label rightsubnetl2tpipsecSLabel;
    @FXML
    private JFXTextField networkpptpSField;
    @FXML
    private ImageView l2tppppipsecimg;
    @FXML
    private ImageView l2tpipsecimg;
    @FXML
    private JFXButton stoppptpSButton;
    @FXML
    private JFXButton stoppptpCButton;
    @FXML
    private JFXButton stopgreSButton;
    @FXML
    private JFXButton stopgreCButton;
    @FXML
    private HBox pptpserverfields;
    @FXML
    private VBox pptpclientfields;
    @FXML
    private VBox greserverFields;
    @FXML
    private HBox l2tpipsecserverFields;
    @FXML
    private VBox l2tppppipsecserverpppFields;
    @FXML
    private VBox l2tppppipsecserveripsecFields;
    @FXML
    private VBox greclientFields;
    @FXML
    private VBox l2tppppipsecclientpppFields;
    @FXML
    private VBox l2tpipsecclientFields;
    @FXML
    private ProgressBar configurationprogressBar;
    @FXML
    private JFXRadioButton transportl2tpipsecCField;
    @FXML
    private JFXRadioButton tunnell2tpipsecCField;
    @FXML
    private JFXButton aboutButton;
    @FXML
    private StackPane mystackane;
    @FXML
    private GridPane gridVPNWindow;
    Notifications notif;
    @FXML
    private VBox CWSides;
    @FXML
    private HBox AboutPackage;
    @FXML
    private HBox InstallationPackage;
    @FXML
    private JFXButton InstallationButton;
    @FXML
    private HBox pptpfieldsClient;
    @FXML
    private VBox l2tppppipsecclientfields;
    @FXML
    private HBox l2tppppipsecclientfields2;
    @FXML
    private VBox pppoepppserverfields;
    @FXML
    private HBox pppoepppserverfields2;
    @FXML
    private JFXButton stoppppoepppipSButton;
    @FXML
    private VBox pppoepppserverfields1;
    @FXML
    private JFXRadioButton pappppoepppipCField;
    @FXML
    private JFXTextField usernamepppoepppipCField;
    @FXML
    private JFXTextField passwordpppoepppipCField;
    @FXML
    private JFXRadioButton chappppoepppipCField;
    @FXML
    private JFXButton stoppppoepppipCButton;
    @FXML
    private JFXButton validatepppoepppipCButton;
    @FXML
    private HBox pppoepppipclientfields;
    @FXML
    private Label keypppoepppipsecSLabel;
    @FXML
    private JFXTextField keypppoepppipsecSField;
    @FXML
    private Label ikepppoepppipsecSLabel;
    @FXML
    private JFXTextField ikepppoepppipsecSField;
    @FXML
    private Label keyexpppoepppipsecSLabel;
    @FXML
    private JFXComboBox<String> keyexpppoepppipsecSField;
    @FXML
    private Label leftAdrpppoepppipsecSLabel;
    @FXML
    private JFXTextField leftAdrpppoepppipsecSField;
    @FXML
    private Label leftsubnetpppoepppipsecSLabel;
    @FXML
    private JFXTextField leftsubnetpppoepppipsecSField;
    @FXML
    private Label rightAdrpppoepppipsecSLabel;
    @FXML
    private JFXTextField rightAdrpppoepppipsecSField;
    @FXML
    private Label rightsubnetpppoepppipsecSLabel;
    @FXML
    private JFXTextField rightsubnetpppoepppipsecSField;
    Alert alert2 = new Alert(AlertType.CONFIRMATION);
    Optional<ButtonType> result2;
    ButtonType buttonTypeOne = new ButtonType("Client-to-Server");
    ButtonType buttonTypeTwo = new ButtonType("Server-to-Server");
    public static boolean l2tpipsecS, l2tppppipsecS, pppoepppipS, pppoepppipsecS, pptpS, greS, l2tpipsecC, l2tppppipsecC, pppoepppipC, pppoepppipsecC, pptpC, greC;
    @FXML
    private ImageView l2tpipsecclientimg;
    @FXML
    private JFXRadioButton transportl2tppppipsecCField;
    @FXML
    private JFXRadioButton tunnell2tppppipsecCField;
    @FXML
    private ImageView pppoeclientimg;
    @FXML
    private HBox pppoepppipclientfields1;
    @FXML
    private JFXButton stoppppoepppipsecSButton;
    @FXML
    private JFXButton validatepppoepppipsecSButton;
    @FXML
    private JFXRadioButton pappppoepppipsecCField;
    @FXML
    private JFXTextField usernamepppoepppipsecCField;
    @FXML
    private JFXTextField passwordpppoepppipsecCField;
    @FXML
    private JFXRadioButton chappppoepppipsecCField;
    @FXML
    private JFXButton stoppppoepppipsecCButton;
    @FXML
    private JFXButton validatepppoepppipsecCButton;
    @FXML
    private HBox pppoepppipsecC1;
    @FXML
    private Label keypppoepppipsecSLabel1;
    @FXML
    private JFXTextField keypppoepppipsecCField;
    @FXML
    private HBox pppoepppipsecC2;
    @FXML
    private Label ikepppoepppipsecSLabel1;
    @FXML
    private JFXTextField ikepppoepppipsecCField;
    @FXML
    private HBox pppoepppipsecC3;
    @FXML
    private Label keyexpppoepppipsecSLabel1;
    @FXML
    private JFXComboBox<String> keyexpppoepppipsecCField;
    @FXML
    private HBox pppoepppipsecC4;
    @FXML
    private Label leftAdrpppoepppipsecSLabel1;
    @FXML
    private JFXTextField leftAdrpppoepppipsecCField;
    @FXML
    private HBox pppoepppipsecC5;
    @FXML
    private Label leftsubnetpppoepppipsecSLabel1;
    @FXML
    private JFXTextField leftsubnetpppoepppipsecCField;
    @FXML
    private HBox pppoepppipsecC6;
    @FXML
    private Label rightAdrpppoepppipsecSLabel1;
    @FXML
    private JFXTextField rightAdrpppoepppipsecCField;
    @FXML
    private HBox pppoepppipsecC7;
    @FXML
    private Label rightsubnetpppoepppipsecSLabel1;
    @FXML
    private JFXTextField rightsubnetpppoepppipsecCField;
    @FXML
    private JFXButton stoppppoepppipsecCButtonIPSec;
    @FXML
    private JFXButton validatepppoepppipsecCButtonIPSec;
    @FXML
    private HBox pppoepppipsecserverField1;
    @FXML
    private HBox pppoepppipsecserverField2;
    @FXML
    private HBox pppoepppipsecserverField3;
    @FXML
    private HBox pppoepppipsecserverField4;
    @FXML
    private HBox pppoepppipsecserverField5;
    @FXML
    private HBox pppoepppipsecserverField6;
    @FXML
    private HBox pppoepppipsecserverField7;
    @FXML
    private HBox pppoepppipsecC9;
    public static boolean sssss;
    //GRE Client
    static String localipgreclient;
    static String remoteipgreclient;
    static String remotenetworkgreclient;
    static String greipgreclient;
    
    //GRE Server
    static String localipgreserver;
    static String remoteipgreserver;
    static String remotenetworkgreserver;
    static String greipgreserver;
    
    //PPTP Client
    static String serverippptpclient;
    static String usernamepptpclient;
    static String passwordpptpclient;
    
    //PPTP Server
    static String serverippptpserver;
    static String clientpoolpptpserver;
    static String vpnnetworkpptpserver;
    static String usernamepptpserver;
    static String passwordpptpserver;
    
    //PPPoE/PPP/IPSec Client
    static String usernamepppoepppipsecclient;
    static String passwordpppoepppipsecclient;
    static boolean pappppoepppipsecclient;
    static boolean chappppoepppipsecclient;
    static String pappppoepppipsecclientString;
    static String chappppoepppipsecclientString;
    static String ikelifetimepppoepppipsecclient;
    static String keylifetimepppoepppipsecclient;
    static String keyexchangepppoepppipsecclient;
    static String localippppoepppipsecclient;
    static String localnetworkpppoepppipsecclient;
    static String remoteippppoepppipsecclient;
    static String remotenetworkpppoepppipsecclient;
    
    //PPPoE/PPP/IPSec Server
    static String ikelifetimepppoepppipsecserver;
    static String keylifetimepppoepppipsecserver;
    static String keyexchangepppoepppipsecserver;
    static String localippppoepppipsecserver;
    static String localnetworkpppoepppipsecserver;
    static String remoteippppoepppipsecserver;
    static String remotenetworkpppoepppipsecserver;
    
    //PPPoE/PPP/IP Client
    static String usernamepppoepppipclient;
    static String passwordpppoepppipclient;
    static String pappppoepppipclientString;
    static String chappppoepppipclientString;
    static boolean pappppoepppipclient;
    static boolean chappppoepppipclient;
    
    //L2TP/PPP/IPSec Client
    static boolean transportl2tppppipsecclient;
    static boolean tunnell2tppppipsecclient;
    static String transportl2tppppipsecclientString;
    static String tunnell2tppppipsecclientString;
    static String keylifetimel2tppppipsecclient;
    static String localipl2tppppipsecclient;
    static String serveripl2tppppipsecclient;
    static String usernamel2tppppipsecclient;
    static String passwordl2tppppipsecclient;
    static String ikelifetimel2tppppipsecclient;
    
    //L2TP/PPP/IPSec Server
    static boolean transportl2tppppipsecserver;
    static boolean tunnell2tppppipsecserver;
    static String transportl2tppppipsecserverString;
    static String tunnell2tppppipsecserverString;
    static String serveripl2tppppipsecserver;
    static String usernamel2tppppipsecserver;
    static String passwordl2tppppipsecserver;
    
    //L2TP/IPSec Client
    static String transportl2tpipsecclientString;
    static String tunnell2tpipsecclientString;
    static boolean transportl2tpipsecclient;
    static boolean tunnell2tpipsecclient;
    static String keylifetimel2tpipsecclient;
    static String ikelifetimel2tpipsecclient;
    static String localipl2tpipsecclient;
    static String serveripl2tpipsecclient;
    
    //L2TP/IPSec Server
    static String clientsidel2tpipsecserverString;
    static boolean clientsidel2tpipsecserver;
    static String wansidel2tpipsecserverString;
    static boolean wansidel2tpipsecserver;
    static String transportl2tpipsecserverString;
    static boolean transportl2tpipsecserver;
    static String tunnell2tpipsecserverString;
    static boolean tunnell2tpipsecserver;
    static String ikelifetimel2tpipsecserver;
    static String keylifetimel2tpipsecserver;
    static String keyexchangel2tpipsecserver;
    static String localipl2tpipsecserver;
    static String localnetworkl2tpipsecserver;
    static String remoteipl2tpipsecserver;
    static String remotenetworkl2tpipsecserver;
    @FXML
    private ImageView pppoeipsecimg;
    public String IPEx;
    @FXML
    private HBox pppoepppipsecC8;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println("********************************");
        System.out.println("Strongswan is "+strongswanInstallation);
        System.out.println("XL2TP is "+xl2tpdInstallation);
        System.out.println("PPP is "+pppInstallation);
        System.out.println("IPerf3 is "+iperf3Installation);
        System.out.println("Wireshark is "+wiresharkInstallation);
        System.out.println("Traceroute is "+tracerouteInstallation);
        System.out.println("LSOF is "+lsofInstallation);
        System.out.println("PPPoE is "+pppoeconfInstallation);
        System.out.println("PPTP is "+pptpInstallation);
        System.out.println("********************************");
        wanipField.setText(variableGWYIPAddr);
        try {
            String result ="";
            p = Runtime.getRuntime().exec("ExternalIPAddress.sh");
            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
            BufferedReader br=new BufferedReader(ipsr);
            String ligne;
            while ((ligne=br.readLine())!=null)
            {
                IPEx = ligne; //PPTP Server
                
                System.out.println(IPEx);
                result += ligne;
            }
        } catch (IOException ex) {
            Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(R2 == true){
            if(l2tpipsecS == true){
                FXMLLoader loader = new FXMLLoader();
                vpnprotocolField.getItems().clear();
                vpnprotocolField.getItems().addAll("L2TP/IPSec Server","L2TP/PPP/IPSec Server","PPPoE/PPP/IPSec Server","PPTP Server","GRE Server");
                vpnprotocolField.setValue(vpnprotocolField.getItems().get(0));
                
                AboutPackage.setVisible(true);
                InstallationPackage.setVisible(false);
                l2tpipsecserverWindow.setVisible(true);
                l2tpipsecclientWindow.setVisible(false);
                l2tppppipsecserverWindow.setVisible(false);
                l2tppppipsecclientWindow.setVisible(false);
                pppoepppipclientWindow.setVisible(false);
                pppoepppipsecserverWindow.setVisible(false);
                pppoepppipsecclientWindow.setVisible(false);
                pptpserverWindow.setVisible(false);
                pptpclientWindow.setVisible(false);
                greserverWindow.setVisible(false);
                pptpimg.setVisible(false);
                greimg.setVisible(false);
                l2tppppipsecimg.setVisible(false);
                l2tpipsecimg.setVisible(true);
                l2tpipsecclientimg.setVisible(false);
                pppoeclientimg.setVisible(false);
                nextButton.setDisable(false);
                stopl2tpipsecSButton.setDisable(true);
                pppoeipsecimg.setVisible(false);
                
                l2tpipsecserverFields.setDisable(true);
                vpnprotocolField.setDisable(true);
                stopl2tpipsecSButton.setDisable(false);
                validatel2tpipsecSButton.setDisable(true);
                previousButton.setDisable(true);
                configurationprogressBar.setVisible(true);
                
                if(clientsidel2tpipsecserver == true){
                    clientSideL2TPIPSecButton.setSelected(true);
                }
                if(wansidel2tpipsecserver == true){
                    wanSideL2TPIPSecButton.setSelected(true);
                }
                if(transportl2tpipsecserver == true){
                    transportl2tpipsecSField.setSelected(true);
                }
                if(tunnell2tpipsecserver == true){
                    tunnell2tpipsecSField.setSelected(true);
                }
                keyl2tpipsecSField.setText(ikelifetimel2tpipsecserver);
                ikel2tpipsecSField.setText(keylifetimel2tpipsecserver);
                if(keyexchangel2tpipsecserver.equals("ikev1")){
                    keyexl2tpipsecSField.getItems().clear();
                    keyexl2tpipsecSField.getItems().addAll("ikev1", "ikev2");
                    keyexl2tpipsecSField.setValue(vpnprotocolField.getItems().get(0));
                }else{
                    keyexl2tpipsecSField.getItems().clear();
                    keyexl2tpipsecSField.getItems().addAll("ikev1", "ikev2");
                    keyexl2tpipsecSField.setValue(vpnprotocolField.getItems().get(1));
                }
                leftAdrl2tpipsecSField.setText(localipl2tpipsecserver);
                leftsubnetl2tpipsecSField.setText(localnetworkl2tpipsecserver);
                rightAdrl2tpipsecSField.setText(remoteipl2tpipsecserver);
                rightsubnetl2tpipsecSField.setText(remotenetworkl2tpipsecserver);
            }else if(l2tppppipsecS == true){
                FXMLLoader loader = new FXMLLoader();
                vpnprotocolField.getItems().clear();
                vpnprotocolField.getItems().addAll("L2TP/IPSec Server","L2TP/PPP/IPSec Server","PPPoE/PPP/IPSec Server","PPTP Server","GRE Server");
                vpnprotocolField.setValue(vpnprotocolField.getItems().get(1));
                
                AboutPackage.setVisible(true);
                InstallationPackage.setVisible(false);
                l2tpipsecserverWindow.setVisible(false);
                l2tpipsecclientWindow.setVisible(false);
                l2tppppipsecserverWindow.setVisible(true);
                l2tppppipsecclientWindow.setVisible(false);
                pppoepppipclientWindow.setVisible(false);
                pppoepppipsecserverWindow.setVisible(false);
                pppoepppipsecclientWindow.setVisible(false);
                pptpserverWindow.setVisible(false);
                pptpclientWindow.setVisible(false);
                greserverWindow.setVisible(false);
                pptpimg.setVisible(false);
                greimg.setVisible(false);
                l2tppppipsecimg.setVisible(true);
                l2tpipsecimg.setVisible(false);
                l2tpipsecclientimg.setVisible(false);
                pppoeclientimg.setVisible(false);
                nextButton.setDisable(false);
                stopl2tppppipsecSButton.setDisable(true);
                pppoeipsecimg.setVisible(false);
                stopl2tppppipsecSButton.setDisable(false);
                validatel2tppppipsecSButton.setDisable(true);
                l2tppppipsecserverpppFields.setDisable(true);
                l2tppppipsecserveripsecFields.setDisable(true);
                previousButton.setDisable(true);
                configurationprogressBar.setVisible(true);
                vpnprotocolField.setDisable(true);
                
                if(transportl2tppppipsecserver == true){
                    transportl2tppppipsecSField.setSelected(true);
                 }
                 if(tunnell2tppppipsecserver == true){
                    tunnell2tppppipsecSField.setSelected(true);
                 }
                 leftAdrl2tppppipsecSField.setText(serveripl2tppppipsecserver);
                 usernamel2tppppipsecSField.setText(usernamel2tppppipsecserver);
                 passwordl2tppppipsecSField.setText(passwordl2tppppipsecserver);
            }else if(pppoepppipsecS == true){
                FXMLLoader loader = new FXMLLoader();
                vpnprotocolField.getItems().clear();
                vpnprotocolField.getItems().addAll("L2TP/IPSec Server","L2TP/PPP/IPSec Server","PPPoE/PPP/IPSec Server","PPTP Server","GRE Server");
                vpnprotocolField.setValue(vpnprotocolField.getItems().get(2));
                
                AboutPackage.setVisible(true);
                InstallationPackage.setVisible(false);
                l2tpipsecserverWindow.setVisible(false);
                l2tpipsecclientWindow.setVisible(false);
                l2tppppipsecserverWindow.setVisible(false);
                l2tppppipsecclientWindow.setVisible(false);
                pppoepppipclientWindow.setVisible(false);
                pppoepppipsecserverWindow.setVisible(true);
                pppoepppipsecclientWindow.setVisible(false);
                pptpserverWindow.setVisible(false);
                pptpclientWindow.setVisible(false);
                greserverWindow.setVisible(false);
                pptpimg.setVisible(false);
                greimg.setVisible(false);
                l2tppppipsecimg.setVisible(false);
                l2tpipsecimg.setVisible(false);
                l2tpipsecclientimg.setVisible(false);
                pppoeclientimg.setVisible(false);
                nextButton.setDisable(false);
                pppoeipsecimg.setVisible(true);
                pppoepppipsecserverField1.setDisable(true);
                pppoepppipsecserverField2.setDisable(true);
                pppoepppipsecserverField3.setDisable(true);
                pppoepppipsecserverField4.setDisable(true);
                pppoepppipsecserverField5.setDisable(true);
                pppoepppipsecserverField6.setDisable(true);
                pppoepppipsecserverField7.setDisable(true);
                vpnprotocolField.setDisable(true);
                stoppppoepppipsecSButton.setDisable(false);
                previousButton.setDisable(true);
                configurationprogressBar.setVisible(true);
                validatepppoepppipsecSButton.setDisable(true);

                keypppoepppipsecSField.setText(ikelifetimepppoepppipsecserver);
                ikepppoepppipsecSField.setText(keylifetimepppoepppipsecserver);
                if(keyexchangepppoepppipsecserver.equals("ikev1")){
                   keyexpppoepppipsecSField.getItems().clear();
                   keyexpppoepppipsecSField.getItems().addAll("ikev1", "ikev2");
                   keyexpppoepppipsecSField.setValue(vpnprotocolField.getItems().get(0));
                }else{
                   keyexpppoepppipsecSField.getItems().clear();
                   keyexpppoepppipsecSField.getItems().addAll("ikev1", "ikev2");
                   keyexpppoepppipsecSField.setValue(vpnprotocolField.getItems().get(1));
                }
                leftAdrpppoepppipsecSField.setText(localippppoepppipsecserver);
                leftsubnetpppoepppipsecSField.setText(localnetworkpppoepppipsecserver);
                rightAdrpppoepppipsecSField.setText(remoteippppoepppipsecserver);
                rightsubnetpppoepppipsecSField.setText(remotenetworkpppoepppipsecserver);
            }else if(pptpS == true){
                FXMLLoader loader = new FXMLLoader();
                vpnprotocolField.getItems().clear();
                vpnprotocolField.getItems().addAll("L2TP/IPSec Server","L2TP/PPP/IPSec Server","PPPoE/PPP/IPSec Server","PPTP Server","GRE Server");
                vpnprotocolField.setValue(vpnprotocolField.getItems().get(3));
                
                AboutPackage.setVisible(true);
                InstallationPackage.setVisible(false);
                l2tpipsecserverWindow.setVisible(false);
                l2tpipsecclientWindow.setVisible(false);
                l2tppppipsecserverWindow.setVisible(false);
                l2tppppipsecclientWindow.setVisible(false);
                pppoepppipclientWindow.setVisible(false);
                pppoepppipsecserverWindow.setVisible(false);
                pppoepppipsecclientWindow.setVisible(false);
                pptpserverWindow.setVisible(true);
                pptpclientWindow.setVisible(false);
                greserverWindow.setVisible(false);
                pptpimg.setVisible(true);
                greimg.setVisible(false);
                l2tppppipsecimg.setVisible(false);
                l2tpipsecimg.setVisible(false);
                l2tpipsecclientimg.setVisible(false);
                pppoeclientimg.setVisible(false);
                nextButton.setDisable(false);
                stoppptpSButton.setDisable(true);
                pppoeipsecimg.setVisible(false);
                pptpserverfields.setDisable(true);
                vpnprotocolField.setDisable(true);
                previousButton.setDisable(true);
                validatepptpSButton.setDisable(true);
                stoppptpSButton.setDisable(false);
                configurationprogressBar.setVisible(true);
                
                localIppptpSField.setText(serverippptpserver);
                remoteIppptpSField.setText(clientpoolpptpserver);
                networkpptpSField.setText(vpnnetworkpptpserver);
                userNamepptpSField.setText(usernamepptpserver);
                passwordpptpSField.setText(passwordpptpserver);
            }else if(greS == true){
                FXMLLoader loader = new FXMLLoader();
                vpnprotocolField.getItems().clear();
                vpnprotocolField.getItems().addAll("L2TP/IPSec Server","L2TP/PPP/IPSec Server","PPPoE/PPP/IPSec Server","PPTP Server","GRE Server");
                vpnprotocolField.setValue(vpnprotocolField.getItems().get(4));
                
                AboutPackage.setVisible(true);
                InstallationPackage.setVisible(false);
                l2tpipsecserverWindow.setVisible(false);
                l2tpipsecclientWindow.setVisible(false);
                l2tppppipsecserverWindow.setVisible(false);
                l2tppppipsecclientWindow.setVisible(false);
                pppoepppipclientWindow.setVisible(false);
                pppoepppipsecserverWindow.setVisible(false);
                pppoepppipsecclientWindow.setVisible(false);
                pptpserverWindow.setVisible(false);
                pptpclientWindow.setVisible(false);
                greserverWindow.setVisible(true);
                pptpimg.setVisible(false);
                greimg.setVisible(true);
                l2tppppipsecimg.setVisible(false);
                l2tpipsecimg.setVisible(false);
                l2tpipsecclientimg.setVisible(false);
                pppoeclientimg.setVisible(false);
                nextButton.setDisable(false);
                stopgreSButton.setDisable(true);
                pppoeipsecimg.setVisible(false);
                greserverFields.setDisable(true);
                validategreSButton.setDisable(true);
                stopgreSButton.setDisable(false);
                vpnprotocolField.setDisable(true);
                previousButton.setDisable(true);
                configurationprogressBar.setVisible(true);
                
                localIPAdrgreSField.setText(localipgreserver);
                RemoteIPAdrgreSField.setText(remoteipgreserver);
                remoteNetAdrgreSField.setText(remotenetworkgreserver);
                greIPAdrgreSField.setText(greipgreserver);
            }else if(strongswanInstallation.equals("Installed") && xl2tpdInstallation.equals("Installed")){
                FXMLLoader loader = new FXMLLoader();
                vpnprotocolField.getItems().clear();
                vpnprotocolField.getItems().addAll("L2TP/IPSec Server","L2TP/PPP/IPSec Server","PPPoE/PPP/IPSec Server","PPTP Server","GRE Server");
                vpnprotocolField.setValue(vpnprotocolField.getItems().get(0));
                if(vpnprotocolField.getItems().get(0).equalsIgnoreCase("L2TP/IPSec Server")){
                    AboutPackage.setVisible(true);
                    InstallationPackage.setVisible(false);
                    l2tpipsecserverWindow.setVisible(true);
                    l2tpipsecclientWindow.setVisible(false);
                    l2tppppipsecserverWindow.setVisible(false);
                    l2tppppipsecclientWindow.setVisible(false);
                    pppoepppipclientWindow.setVisible(false);
                    pppoepppipsecserverWindow.setVisible(false);
                    pppoepppipsecclientWindow.setVisible(false);
                    pptpserverWindow.setVisible(false);
                    pptpclientWindow.setVisible(false);
                    greserverWindow.setVisible(false);
                    pptpimg.setVisible(false);
                    greimg.setVisible(false);
                    l2tppppipsecimg.setVisible(false);
                    l2tpipsecimg.setVisible(true);
                    l2tpipsecclientimg.setVisible(false);
                    pppoeclientimg.setVisible(false);
                    pppoeipsecimg.setVisible(false);
                    stopl2tpipsecSButton.setDisable(true);
                    Image about = new Image(getClass().getResourceAsStream("/vpnlite/img/About.png"));
                    TrayNotification tray = new TrayNotification();
                    tray.setTitle("L2TP/IPSec Server configuration:");
                    tray.setMessage(" Press on 'About' button for more information");
                    tray.setRectangleFill(Paint.valueOf("#2A9A84"));
                    tray.setImage(about);
                    tray.showAndDismiss(Duration.seconds(5));
                    Q="LIS";
                }
                //GetExternalIp();
                wanipField.setText(variableGWYIPAddr);
            }else{
                FXMLLoader loader = new FXMLLoader();
                vpnprotocolField.getItems().clear();
                vpnprotocolField.getItems().addAll("L2TP/IPSec Server","L2TP/PPP/IPSec Server","PPPoE/PPP/IPSec Server","PPTP Server","GRE Server");
                vpnprotocolField.setValue(vpnprotocolField.getItems().get(0));
                if(vpnprotocolField.getItems().get(0).equalsIgnoreCase("L2TP/IPSec Server")){
                    CWSides.setDisable(true);
                    validatel2tpipsecSButton.setDisable(true);
                    nextButton.setDisable(true);
                    AboutPackage.setVisible(false);
                    InstallationPackage.setVisible(true);
                    
                    l2tpipsecserverWindow.setVisible(true);
                    l2tpipsecclientWindow.setVisible(false);
                    l2tppppipsecserverWindow.setVisible(false);
                    l2tppppipsecclientWindow.setVisible(false);
                    pppoepppipclientWindow.setVisible(false);
                    pppoepppipsecserverWindow.setVisible(false);
                    pppoepppipsecclientWindow.setVisible(false);
                    pptpserverWindow.setVisible(false);
                    pptpclientWindow.setVisible(false);
                    greserverWindow.setVisible(false);
                    pptpimg.setVisible(false);
                    greimg.setVisible(false);
                    l2tppppipsecimg.setVisible(false);
                    l2tpipsecimg.setVisible(true);
                    l2tpipsecclientimg.setVisible(false);
                    pppoeclientimg.setVisible(false);
                    pppoeipsecimg.setVisible(false);
                    stopl2tpipsecSButton.setDisable(true);
                    Image about = new Image(getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
                    TrayNotification tray = new TrayNotification();
                    tray.setTitle("Warning");
                    tray.setMessage("Check if 'L2TP' and 'Strongswan' packages are installed correctly");
                    tray.setRectangleFill(Paint.valueOf("#2A9A84"));
                    tray.setImage(about);
                    tray.showAndDismiss(Duration.seconds(10));
                }
                //GetExternalIp();
                wanipField.setText(variableGWYIPAddr);
            }
        }else if(R1 == true){
            if(l2tpipsecC == true){
                FXMLLoader loader = new FXMLLoader();
                vpnprotocolField.getItems().clear();
                vpnprotocolField.getItems().addAll("L2TP/IPSec Client","L2TP/PPP/IPSec Client","PPPoE/PPP/IP Client","PPPoE/PPP/IPSec Client","PPTP Client");
                vpnprotocolField.setValue(vpnprotocolField.getItems().get(0));
                
                AboutPackage.setVisible(true);
                InstallationPackage.setVisible(false);
                l2tpipsecserverWindow.setVisible(false);
                l2tpipsecclientWindow.setVisible(true);
                l2tppppipsecserverWindow.setVisible(false);
                l2tppppipsecclientWindow.setVisible(false);
                pppoepppipclientWindow.setVisible(false);
                pppoepppipsecserverWindow.setVisible(false);
                pppoepppipsecclientWindow.setVisible(false);
                pptpserverWindow.setVisible(false);
                pptpclientWindow.setVisible(false);
                greserverWindow.setVisible(false);
                pptpimg.setVisible(false);
                greimg.setVisible(false);
                l2tppppipsecimg.setVisible(false);
                l2tpipsecimg.setVisible(false);
                l2tpipsecclientimg.setVisible(true);
                pppoeclientimg.setVisible(false);
                pppoeipsecimg.setVisible(false);
                stopl2tpipsecCButton.setDisable(true);
                
                stopl2tpipsecCButton.setDisable(false);
                validatel2tpipsecCButton.setDisable(true);
                l2tpipsecclientFields.setDisable(true);
                vpnprotocolField.setDisable(true);
                previousButton.setDisable(true);
                configurationprogressBar.setVisible(true);
                
                userButton.setDisable(false);
                if(transportl2tpipsecclient == true){
                   transportl2tpipsecCField.setSelected(true);
                }
                if(tunnell2tpipsecclient == true){
                   tunnell2tpipsecCField.setSelected(true);
                }
                keyl2tpipsecCField.setText(keylifetimel2tpipsecclient);
                ikel2tpipsecCField.setText(ikelifetimel2tpipsecclient);
                leftAdrl2tpipsecCField.setText(localipl2tpipsecclient);
                rightAdrl2tpipsecCField.setText(serveripl2tpipsecclient);
            }else if(l2tppppipsecC == true){
                FXMLLoader loader = new FXMLLoader();
                vpnprotocolField.getItems().clear();
                vpnprotocolField.getItems().addAll("L2TP/IPSec Client","L2TP/PPP/IPSec Client","PPPoE/PPP/IP Client","PPPoE/PPP/IPSec Client","PPTP Client");
                vpnprotocolField.setValue(vpnprotocolField.getItems().get(1));

                AboutPackage.setVisible(true);
                InstallationPackage.setVisible(false);
                l2tpipsecserverWindow.setVisible(false);
                l2tpipsecclientWindow.setVisible(false);
                l2tppppipsecserverWindow.setVisible(false);
                l2tppppipsecclientWindow.setVisible(true);
                pppoepppipclientWindow.setVisible(false);
                pppoepppipsecserverWindow.setVisible(false);
                pppoepppipsecclientWindow.setVisible(false);
                pptpserverWindow.setVisible(false);
                pptpclientWindow.setVisible(false);
                greserverWindow.setVisible(false);
                pptpimg.setVisible(false);
                greimg.setVisible(false);
                l2tppppipsecimg.setVisible(false);
                l2tpipsecimg.setVisible(false);
                l2tpipsecclientimg.setVisible(true);
                pppoeclientimg.setVisible(false);
                stopl2tppppipsecCButton.setDisable(true);
                nextButton.setDisable(false);
                pppoeipsecimg.setVisible(false);
                stopl2tppppipsecCButton.setDisable(false);
                validatel2tppppipsecCButton.setDisable(true);
                l2tppppipsecclientpppFields.setDisable(true);
                l2tppppipsecclientfields.setDisable(true);
                previousButton.setDisable(true);
                configurationprogressBar.setVisible(true);
                vpnprotocolField.setDisable(true);
                
                userButton.setDisable(false);
                if(transportl2tppppipsecclient == true){
                    transportl2tppppipsecCField.setSelected(true);
                 }
                 if(tunnell2tppppipsecclient == true){
                    tunnell2tppppipsecCField.setSelected(true);
                 }
                 keyl2tppppipsecCField.setText(keylifetimel2tppppipsecclient);
                 ikel2tppppipsecCField.setText(ikelifetimel2tppppipsecclient);
                 leftAdrl2tppppipsecCField.setText(localipl2tppppipsecclient);
                 rightAdrl2tppppipsecCField.setText(serveripl2tppppipsecclient);
                 usernamel2tppppipsecCField.setText(usernamel2tppppipsecclient);
                 passwordl2tppppipsecCField.setText(passwordl2tppppipsecclient);
            }else if(pppoepppipC == true){
                FXMLLoader loader = new FXMLLoader();
                vpnprotocolField.getItems().clear();
                vpnprotocolField.getItems().addAll("L2TP/IPSec Client","L2TP/PPP/IPSec Client","PPPoE/PPP/IP Client","PPPoE/PPP/IPSec Client","PPTP Client");
                vpnprotocolField.setValue(vpnprotocolField.getItems().get(2));
                
                AboutPackage.setVisible(true);
                InstallationPackage.setVisible(false);
                l2tpipsecserverWindow.setVisible(false);
                l2tpipsecclientWindow.setVisible(false);
                l2tppppipsecserverWindow.setVisible(false);
                l2tppppipsecclientWindow.setVisible(false);
                pppoepppipclientWindow.setVisible(true);
                pppoepppipsecserverWindow.setVisible(false);
                pppoepppipsecclientWindow.setVisible(false);
                pptpserverWindow.setVisible(false);
                pptpclientWindow.setVisible(false);
                greserverWindow.setVisible(false);
                pptpimg.setVisible(false);
                greimg.setVisible(false);
                l2tppppipsecimg.setVisible(false);
                l2tpipsecimg.setVisible(false);
                l2tpipsecclientimg.setVisible(false);
                pppoeclientimg.setVisible(true);
                nextButton.setDisable(false);
                pppoeipsecimg.setVisible(false);
                pppoepppipclientfields.setDisable(true);
                validatepppoepppipCButton.setDisable(true);
                stoppppoepppipCButton.setDisable(false);
                configurationprogressBar.setVisible(true);
                
                userButton.setDisable(false);
                usernamepppoepppipCField.setText(usernamepppoepppipclient);
                passwordpppoepppipCField.setText(passwordpppoepppipclient);
                if(pappppoepppipclient == true){
                   pappppoepppipCField.setSelected(true);
                }
                if(chappppoepppipclient == true){
                   chappppoepppipCField.setSelected(true);
                }
            }else if(pppoepppipsecC == true){
                FXMLLoader loader = new FXMLLoader();
                vpnprotocolField.getItems().clear();
                vpnprotocolField.getItems().addAll("L2TP/IPSec Client","L2TP/PPP/IPSec Client","PPPoE/PPP/IP Client","PPPoE/PPP/IPSec Client","PPTP Client");
                vpnprotocolField.setValue(vpnprotocolField.getItems().get(3));
                
                AboutPackage.setVisible(true);
                InstallationPackage.setVisible(false);
                l2tpipsecserverWindow.setVisible(false);
                l2tpipsecclientWindow.setVisible(false);
                l2tppppipsecserverWindow.setVisible(false);
                l2tppppipsecclientWindow.setVisible(false);
                pppoepppipclientWindow.setVisible(false);
                pppoepppipsecserverWindow.setVisible(false);
                pppoepppipsecclientWindow.setVisible(true);
                pptpserverWindow.setVisible(false);
                pptpclientWindow.setVisible(false);
                greserverWindow.setVisible(false);
                pptpimg.setVisible(false);
                greimg.setVisible(false);
                l2tppppipsecimg.setVisible(false);
                l2tpipsecimg.setVisible(false);
                l2tpipsecclientimg.setVisible(false);
                pppoeclientimg.setVisible(false);
                pppoeipsecimg.setVisible(true);
                nextButton.setDisable(false);
                pppoepppipsecC8.setDisable(false);
                validatepppoepppipsecCButtonIPSec.setDisable(true);
                validatepppoepppipsecCButton.setDisable(true);
                pppoepppipsecC1.setDisable(true);
                pppoepppipsecC2.setDisable(true);
                pppoepppipsecC3.setDisable(true);
                pppoepppipsecC4.setDisable(true);
                pppoepppipsecC5.setDisable(true);
                pppoepppipsecC6.setDisable(true);
                pppoepppipsecC7.setDisable(true);
                vpnprotocolField.setDisable(true);
                /*
                if(sssss == true){
                    stoppppoepppipsecCButtonIPSec.setDisable(false);
                }*/
                
                
                pppoepppipclientfields1.setDisable(true);
                
                previousButton.setDisable(true);
                configurationprogressBar.setVisible(true);
                
                userButton.setDisable(false);
                
                usernamepppoepppipsecCField.setText(usernamepppoepppipsecclient);
                passwordpppoepppipsecCField.setText(passwordpppoepppipsecclient);
                if(pappppoepppipsecclient == true){
                   pappppoepppipsecCField.setSelected(true);
                }
                if(chappppoepppipsecclient == true){
                   chappppoepppipsecCField.setSelected(true);
                }
                keypppoepppipsecCField.setText(ikelifetimepppoepppipsecclient);
                ikepppoepppipsecCField.setText(keylifetimepppoepppipsecclient);
                if(keyexchangepppoepppipsecclient.equals("ikev1")){
                   keyexpppoepppipsecCField.getItems().clear();
                   keyexpppoepppipsecCField.getItems().addAll("ikev1", "ikev2");
                   keyexpppoepppipsecCField.setValue(vpnprotocolField.getItems().get(0));
                }else{
                   keyexpppoepppipsecCField.getItems().clear();
                   keyexpppoepppipsecCField.getItems().addAll("ikev1", "ikev2");
                   keyexpppoepppipsecCField.setValue(vpnprotocolField.getItems().get(1));
                }
                leftAdrpppoepppipsecCField.setText(localippppoepppipsecclient);
                leftsubnetpppoepppipsecCField.setText(localnetworkpppoepppipsecclient);
                rightAdrpppoepppipsecCField.setText(remoteippppoepppipsecclient);
                rightsubnetpppoepppipsecCField.setText(remotenetworkpppoepppipsecclient);
            }else if(pptpC == true){
                FXMLLoader loader = new FXMLLoader();
                vpnprotocolField.getItems().clear();
                vpnprotocolField.getItems().addAll("L2TP/IPSec Client","L2TP/PPP/IPSec Client","PPPoE/PPP/IP Client","PPPoE/PPP/IPSec Client","PPTP Client");
                vpnprotocolField.setValue(vpnprotocolField.getItems().get(4));
                
                AboutPackage.setVisible(true);
                InstallationPackage.setVisible(false);
                l2tpipsecserverWindow.setVisible(false);
                l2tpipsecclientWindow.setVisible(false);
                l2tppppipsecserverWindow.setVisible(false);
                l2tppppipsecclientWindow.setVisible(false);
                pppoepppipclientWindow.setVisible(false);
                pppoepppipsecserverWindow.setVisible(false);
                pppoepppipsecclientWindow.setVisible(false);
                pptpserverWindow.setVisible(false);
                pptpclientWindow.setVisible(true);
                greserverWindow.setVisible(false);
                pptpimg.setVisible(true);
                greimg.setVisible(false);
                l2tppppipsecimg.setVisible(false);
                l2tpipsecimg.setVisible(false);
                l2tpipsecclientimg.setVisible(false);
                pppoeclientimg.setVisible(false);
                stoppptpCButton.setDisable(true);
                nextButton.setDisable(false);
                pppoeipsecimg.setVisible(false);
                pptpclientfields.setDisable(true);
                stoppptpCButton.setDisable(false);
                validatepptpCButton.setDisable(true);
                previousButton.setDisable(true);
                vpnprotocolField.setDisable(true);
                configurationprogressBar.setVisible(true);
                
                userButton.setDisable(false);
                
                serveradrpptpCField.setText(serverippptpclient);
                usernamepptpCField.setText(usernamepptpclient);
                passwordpptpCField.setText(passwordpptpclient);
            }else if(strongswanInstallation.equals("Installed") && xl2tpdInstallation.equals("Installed")){
                FXMLLoader loader = new FXMLLoader();
                vpnprotocolField.getItems().clear();
                vpnprotocolField.getItems().addAll("L2TP/IPSec Client","L2TP/PPP/IPSec Client","PPPoE/PPP/IP Client","PPPoE/PPP/IPSec Client","PPTP Client");
                vpnprotocolField.setValue(vpnprotocolField.getItems().get(0));
                if(vpnprotocolField.getItems().get(0).equalsIgnoreCase("L2TP/IPSec Client")){
                    AboutPackage.setVisible(true);
                    InstallationPackage.setVisible(false);
                    l2tpipsecserverWindow.setVisible(false);
                    l2tpipsecclientWindow.setVisible(true);
                    l2tppppipsecserverWindow.setVisible(false);
                    l2tppppipsecclientWindow.setVisible(false);
                    pppoepppipclientWindow.setVisible(false);
                    pppoepppipsecserverWindow.setVisible(false);
                    pppoepppipsecclientWindow.setVisible(false);
                    pptpserverWindow.setVisible(false);
                    pptpclientWindow.setVisible(false);
                    greserverWindow.setVisible(false);
                    pptpimg.setVisible(false);
                    greimg.setVisible(false);
                    l2tppppipsecimg.setVisible(false);
                    l2tpipsecimg.setVisible(false);
                    l2tpipsecclientimg.setVisible(true);
                    pppoeclientimg.setVisible(false);
                    pppoeipsecimg.setVisible(false);
                    stopl2tpipsecCButton.setDisable(true);
                    Image about = new Image(getClass().getResourceAsStream("/vpnlite/img/About.png"));
                    TrayNotification tray = new TrayNotification();
                    tray.setTitle("L2TP/IPSec Client configuration:");
                    tray.setMessage(" Press on 'About' button for more information");
                    tray.setRectangleFill(Paint.valueOf("#2A9A84"));
                    tray.setImage(about);
                    tray.showAndDismiss(Duration.seconds(5));
                    Q="LIC";
                }
                //GetExternalIp();
                wanipField.setText(variableGWYIPAddr);
                userButton.setDisable(false);
            }else{
                FXMLLoader loader = new FXMLLoader();
                vpnprotocolField.getItems().clear();
                vpnprotocolField.getItems().addAll("L2TP/IPSec Client","L2TP/PPP/IPSec Client","PPPoE/PPP/IP Client","PPPoE/PPP/IPSec Client","PPTP Client");
                vpnprotocolField.setValue(vpnprotocolField.getItems().get(0));
                if(vpnprotocolField.getItems().get(0).equalsIgnoreCase("L2TP/IPSec Client")){
                    l2tpipsecclientFields.setDisable(true);
                    validatel2tpipsecCButton.setDisable(true);
                    nextButton.setDisable(true);
                    AboutPackage.setVisible(false);
                    InstallationPackage.setVisible(true);
                    
                    l2tpipsecserverWindow.setVisible(false);
                    l2tpipsecclientWindow.setVisible(true);
                    l2tppppipsecserverWindow.setVisible(false);
                    l2tppppipsecclientWindow.setVisible(false);
                    pppoepppipclientWindow.setVisible(false);
                    pppoepppipsecserverWindow.setVisible(false);
                    pppoepppipsecclientWindow.setVisible(false);
                    pptpserverWindow.setVisible(false);
                    pptpclientWindow.setVisible(false);
                    greserverWindow.setVisible(false);
                    pptpimg.setVisible(false);
                    greimg.setVisible(false);
                    l2tppppipsecimg.setVisible(false);
                    l2tpipsecimg.setVisible(false);
                    l2tpipsecclientimg.setVisible(true);
                    pppoeclientimg.setVisible(false);
                    pppoeipsecimg.setVisible(false);
                    stopl2tpipsecCButton.setDisable(true);
                    Image about = new Image(getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
                    TrayNotification tray = new TrayNotification();
                    tray.setTitle("Warning");
                    tray.setMessage("Check if 'L2TP' and 'Strongswan' packages are installed correctly");
                    tray.setRectangleFill(Paint.valueOf("#2A9A84"));
                    tray.setImage(about);
                    tray.showAndDismiss(Duration.seconds(10));
                }
                //GetExternalIp();
                wanipField.setText(variableGWYIPAddr);
                userButton.setDisable(false);
            }
        }
        
        Image user = new Image(
            getClass().getResourceAsStream("/vpnlite/img/User.png"));
        userButton.setGraphic(new ImageView(user));
        userButton.setStyle(
            "-fx-background-radius: 5em; " +
            "-fx-min-width: 30px; " +
            "-fx-min-height: 30px; " +
            "-fx-max-width: 30x; " +
            "-fx-max-height: 30px;"
        );
        
        Image vpn = new Image(
            getClass().getResourceAsStream("/vpnlite/img/Vpn.png"));
        vpnButton.setGraphic(new ImageView(vpn));
        vpnButton.setStyle(
            "-fx-background-radius: 5em; " +
            "-fx-min-width: 30px; " +
            "-fx-min-height: 30px; " +
            "-fx-max-width: 30x; " +
            "-fx-max-height: 30px;"
        );
        
        Image tests = new Image(
            getClass().getResourceAsStream("/vpnlite/img/Tests.png"));
        testsButton.setGraphic(new ImageView(tests));
        testsButton.setStyle(
            "-fx-background-radius: 5em; " +
            "-fx-min-width: 30px; " +
            "-fx-min-height: 30px; " +
            "-fx-max-width: 30x; " +
            "-fx-max-height: 30px;"
        );
        
        Image refresh = new Image(
            getClass().getResourceAsStream("/vpnlite/img/About.png"));
        aboutButton.setGraphic(new ImageView(refresh));
        
        vpnButton.setStyle("-fx-base: #b6e7c9;");
        
        Thread t1 = new Thread(){
        @Override
        public void run(){
            try {
                
                String result ="";
                p = Runtime.getRuntime().exec("InternalIPAddress.sh");
                InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                BufferedReader br=new BufferedReader(ipsr);
                String ligne;
                while ((ligne=br.readLine())!=null)
                {
                    leftAdrl2tppppipsecSField.setText(ligne);
                    leftAdrl2tppppipsecCField.setText(ligne);
                    leftAdrl2tpipsecCField.setText(ligne);
                    leftAdrl2tpipsecSField.setText(ligne);
                    localIppptpSField.setText(ligne); // PPTP Server
                    localIPAdrgreSField.setText(ligne); // GRE Server
                    localIPAddressgreCField.setText(ligne); // GRE Client
                    result += ligne;
                }
                } catch (IOException ex) {
                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        t1.start();
        ToggleGroup gender = new ToggleGroup();
        transportl2tppppipsecSField.setToggleGroup(gender);
        tunnell2tppppipsecSField.setToggleGroup(gender);
        ToggleGroup gender2 = new ToggleGroup();
        transportl2tpipsecSField.setToggleGroup(gender2);
        tunnell2tpipsecSField.setToggleGroup(gender2);
        ToggleGroup gender3 = new ToggleGroup();
        pappppoepppipSRadio.setToggleGroup(gender3);
        chappppoepppipSRadio.setToggleGroup(gender3);
        ToggleGroup gender4 = new ToggleGroup();
        clientSideL2TPIPSecButton.setToggleGroup(gender4);
        wanSideL2TPIPSecButton.setToggleGroup(gender4);
        ToggleGroup gender5 = new ToggleGroup();
        tunnell2tpipsecCField.setToggleGroup(gender5);
        transportl2tpipsecCField.setToggleGroup(gender5);
        ToggleGroup gender6 = new ToggleGroup();
        transportl2tppppipsecCField.setToggleGroup(gender6);
        tunnell2tppppipsecCField.setToggleGroup(gender6);
        ToggleGroup gender7 = new ToggleGroup();
        pappppoepppipsecCField.setToggleGroup(gender7);
        chappppoepppipsecCField.setToggleGroup(gender7);
        ToggleGroup gender8 = new ToggleGroup();
        pappppoepppipCField.setToggleGroup(gender8);
        chappppoepppipCField.setToggleGroup(gender8);

        try {
            String result ="";
            p = Runtime.getRuntime().exec("ethpool.sh 200-220");
            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
            BufferedReader br=new BufferedReader(ipsr);
            String ligne;
            while ((ligne=br.readLine())!=null)
            {
                remoteIppptpSField.setText(ligne); //PPTP Server
                result += ligne;
            }
        } catch (IOException ex) {
            Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String result ="";
            p = Runtime.getRuntime().exec("ethpool.sh 0");
            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
            BufferedReader br=new BufferedReader(ipsr);
            String ligne;
            while ((ligne=br.readLine())!=null)
            {
                networkpptpSField.setText(ligne+"/24"); //PPTP Server
                result += ligne;
            }
        } catch (IOException ex) {
            Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        keyexl2tpipsecSField.getItems().addAll("ikev1", "ikev2");
        keyexl2tpipsecSField.setValue("ikev1");
        keyexpppoepppipsecCField.getItems().addAll("ikev1", "ikev2");
        keyexpppoepppipsecCField.setValue("ikev1");
        keyexpppoepppipsecSField.getItems().addAll("ikev1", "ikev2");
        keyexpppoepppipsecSField.setValue("ikev1");
    }
    
    @FXML
    private void choosedVpn(ActionEvent event) {
            String item = vpnprotocolField.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader();
            if(item.equalsIgnoreCase("L2TP/IPSec Server") ){
                if(strongswanInstallation.equals("Installed") && xl2tpdInstallation.equals("Installed")){
                    AboutPackage.setVisible(true);
                    InstallationPackage.setVisible(false);
                    l2tpipsecserverWindow.setVisible(true);
                    l2tpipsecclientWindow.setVisible(false);
                    l2tppppipsecserverWindow.setVisible(false);
                    l2tppppipsecclientWindow.setVisible(false);
                    pppoepppipclientWindow.setVisible(false);
                    pppoepppipsecserverWindow.setVisible(false);
                    pppoepppipsecclientWindow.setVisible(false);
                    pptpserverWindow.setVisible(false);
                    pptpclientWindow.setVisible(false);
                    greserverWindow.setVisible(false);
                    pptpimg.setVisible(false);
                    greimg.setVisible(false);
                    l2tppppipsecimg.setVisible(false);
                    l2tpipsecimg.setVisible(true);
                    l2tpipsecclientimg.setVisible(false);
                    pppoeclientimg.setVisible(false);
                    pppoeipsecimg.setVisible(false);
                    stopl2tpipsecSButton.setDisable(true);
                    Image notifimg = new Image(
                        getClass().getResourceAsStream("/vpnlite/img/About.png"));
                    Node notimg = new ImageView(notifimg);
                    notif = Notifications.create()
                        .graphic(notimg)
                        .title("L2TP/IPSec Server configuration:")
                        .text(" Press on 'About' button for more information")
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.CENTER)
                        .darkStyle()
                        .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            mystackane.setVisible(true);
                            JFXDialogLayout content = new JFXDialogLayout();
                            content.setStyle("-fx-background-image: url(\"/vpnlite/img/backgroundApp.png\");");
                            Text t1 = new Text();
                            t1.setText("About L2TP/IPSec Server");
                            t1.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 19));
                            t1.setFill(Color.BLACK);
                            content.setHeading(t1);
                            Text t2 = new Text();
                            t2.setText("シ\tFor L2TP/IPSec Server, you can use «Site-to-Site» or «Client-To-Site» configuration\n\n"
                                    + "✗\tFor «Site-to-Site» Configuration:\n\n\t\t➺ You can use only «TUNNEL» Mode\n\n"
                                    + "\t\t➺ Example:\n\t\t\tFrom Server (Select WAN Side + Tunnel Option) ⇌ From Client (Select Client Side + Tunnel Option)\n\n"
                                    + "✗\tFor «Client-To-Site» Configuration:\n\n\t\t➺ You can use «TRANSPORT» or «TUNNEL» Modes\n\n"
                                    + "\t\t➺ Example:\n\t\t\tFrom Server (Select WAN Side + Tunnel/Transport Option)\n\n"
                                    + "➤\tFor «TUNNEL» Mode:\n\n\t\t➺ WAN Side: it is better to leave the default settings of «Local/Remote Network» and «Remote IP Address»\n\n\t\t➺ Client Side: it is better to leave the default settings of «Local/Remote Network»\n\n"
                                    + "シ\tYou can use Wireshark filters:\n\n\t\t➺ «Site-to-Site»: udp.dstport=4500 || udp.dstport==500 || esp\n\n\t\t➺ «Client-To-Site»: udp.dstport==1701 || udp.dstport=4500 || udp.dstport==500 || esp\n\n");
                            t2.setFont(Font.font ("Arial", 15));
                            t2.setFill(Color.GRAY);
                            content.setBody(t2);
                            JFXDialog dialog = new JFXDialog(mystackane, content, JFXDialog.DialogTransition.CENTER);
                            dialog.setOverlayClose(false);
                            JFXButton button = new JFXButton("OK");
                            button.setStyle("-fx-background-color: #8A8B8C; -fx-text-fill: black;");
                            button.setButtonType(JFXButton.ButtonType.RAISED);
                            button.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    mystackane.setVisible(false);
                                    dialog.close();
                                }
                            });
                            content.setActions(button);
                            dialog.show();
                        }
                    });
                    notif.show();
                    Q="LIS";
                }else{
                    CWSides.setDisable(true);
                    validatel2tpipsecSButton.setDisable(true);
                    nextButton.setDisable(true);
                    AboutPackage.setVisible(false);
                    InstallationPackage.setVisible(true);
                    
                    l2tpipsecserverWindow.setVisible(true);
                    l2tpipsecclientWindow.setVisible(false);
                    l2tppppipsecserverWindow.setVisible(false);
                    l2tppppipsecclientWindow.setVisible(false);
                    pppoepppipclientWindow.setVisible(false);
                    pppoepppipsecserverWindow.setVisible(false);
                    pppoepppipsecclientWindow.setVisible(false);
                    pptpserverWindow.setVisible(false);
                    pptpclientWindow.setVisible(false);
                    greserverWindow.setVisible(false);
                    pptpimg.setVisible(false);
                    greimg.setVisible(false);
                    l2tppppipsecimg.setVisible(false);
                    l2tpipsecimg.setVisible(true);
                    l2tpipsecclientimg.setVisible(false);
                    pppoeclientimg.setVisible(false);
                    pppoeipsecimg.setVisible(false);
                    stopl2tpipsecSButton.setDisable(true);
                    Image notifimg = new Image(
                        getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
                    Node notimg = new ImageView(notifimg);
                    notif = Notifications.create()
                        .graphic(notimg)
                        .title("Warning")
                        .text("Check if 'L2TP' and 'Strongswan' packages are installed correctly")
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.CENTER)
                        .darkStyle()
                        .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try{
                                Stage stage = (Stage) InstallationButton.getScene().getWindow();
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
                            } catch (IOException ex) {
                                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    notif.show();
                }
            }else if (item.equalsIgnoreCase("L2TP/PPP/IPSec Server") ){
                if(strongswanInstallation.equals("Installed") && xl2tpdInstallation.equals("Installed") && pppInstallation.equals("Installed")){
                    AboutPackage.setVisible(true);
                    InstallationPackage.setVisible(false);
                    l2tpipsecserverWindow.setVisible(false);
                    l2tpipsecclientWindow.setVisible(false);
                    l2tppppipsecserverWindow.setVisible(true);
                    l2tppppipsecclientWindow.setVisible(false);
                    pppoepppipclientWindow.setVisible(false);
                    pppoepppipsecserverWindow.setVisible(false);
                    pppoepppipsecclientWindow.setVisible(false);
                    pptpserverWindow.setVisible(false);
                    pptpclientWindow.setVisible(false);
                    greserverWindow.setVisible(false);
                    pptpimg.setVisible(false);
                    greimg.setVisible(false);
                    l2tppppipsecimg.setVisible(true);
                    l2tpipsecimg.setVisible(false);
                    l2tpipsecclientimg.setVisible(false);
                    pppoeclientimg.setVisible(false);
                    pppoeipsecimg.setVisible(false);
                    stopl2tppppipsecSButton.setDisable(true);
                    Image notifimg = new Image(
                        getClass().getResourceAsStream("/vpnlite/img/About.png"));
                    Node notimg = new ImageView(notifimg);
                    notif = Notifications.create()
                        .graphic(notimg)
                        .title("L2TP/PPP/IPSec Server configuration:")
                        .text(" Press on 'About' button for more information")
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.CENTER)
                        .darkStyle()
                        .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            mystackane.setVisible(true);
                            JFXDialogLayout content = new JFXDialogLayout();
                            content.setStyle("-fx-background-image: url(\"/vpnlite/img/backgroundApp.png\");");
                            Text t1 = new Text();
                            t1.setText("About L2TP/PPP/IPSec Server");
                            t1.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 19));
                            t1.setFill(Color.BLACK);
                            content.setHeading(t1);
                            Text t2 = new Text();
                            t2.setText("シ\tFor L2TP/PPP/IPSec Server, you can use only «Client-To-Server» configuration\n\n"
                                + "✗\tFor «Client-To-Server» Configuration:\n\n\t\t➺ You can use «TRANSPORT» or «TUNNEL» Modes\n\n"
                                + "\t\t➺ Example:\n\t\t\tFrom Server (Select Tunnel/Transport Option)\n\n"
                                + "✗\tServer IP Address:\n\n\t\t➺ The Server IP Address for L2TP/PPP/IPSec configuration\n\n"
                                + "✗\tUsername:\n\n\t\t➺ Username to be used on Client Side\n\n"
                                + "✗\tPassword:\n\n\t\t➺ Password to be used on Client Side\n\n"
                                + "シ\tYou can use Wireshark filters:\n\n\t\t➺ Client-To-Site: « udp.dstport==1701 || udp.dstport=4500 || udp.dstport==500 || esp || ppp»\n\n\n\n"
                                + "➤\tNote:\n\n\t\t➺ You must enter your filter on Wireshark before starting VPN configuration");
                            t2.setFont(Font.font ("Arial", 15));
                            t2.setFill(Color.GRAY);
                            content.setBody(t2);
                            JFXDialog dialog = new JFXDialog(mystackane, content, JFXDialog.DialogTransition.CENTER);
                            dialog.setOverlayClose(false);
                            JFXButton button = new JFXButton("OK");
                            button.setStyle("-fx-background-color: #8A8B8C; -fx-text-fill: black;");
                            button.setButtonType(JFXButton.ButtonType.RAISED);
                            button.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    mystackane.setVisible(false);
                                    dialog.close();
                                }
                            });
                            content.setActions(button);
                            dialog.show();
                        }
                    });
                    notif.show();
                    Q="LPIS";
                }else {
                    l2tppppipsecserveripsecFields.setDisable(true);
                    userpassl2tppppipsecFields.setDisable(true);
                    validatel2tppppipsecSButton.setDisable(true);
                    nextButton.setDisable(true);
                    AboutPackage.setVisible(false);
                    InstallationPackage.setVisible(true);
                    
                    l2tpipsecserverWindow.setVisible(false);
                    l2tpipsecclientWindow.setVisible(false);
                    l2tppppipsecserverWindow.setVisible(true);
                    l2tppppipsecclientWindow.setVisible(false);
                    pppoepppipclientWindow.setVisible(false);
                    pppoepppipsecserverWindow.setVisible(false);
                    pppoepppipsecclientWindow.setVisible(false);
                    pptpserverWindow.setVisible(false);
                    pptpclientWindow.setVisible(false);
                    greserverWindow.setVisible(false);
                    pptpimg.setVisible(false);
                    greimg.setVisible(false);
                    l2tppppipsecimg.setVisible(true);
                    l2tpipsecimg.setVisible(false);
                    l2tpipsecclientimg.setVisible(false);
                    pppoeclientimg.setVisible(false);
                    pppoeipsecimg.setVisible(false);
                    stopl2tppppipsecSButton.setDisable(true);
                    Image notifimg = new Image(
                        getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
                    Node notimg = new ImageView(notifimg);
                    notif = Notifications.create()
                        .graphic(notimg)
                        .title("Warning")
                        .text("Check if 'L2TP', 'Strongswan' and 'PPP' packages are installed correctly")
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.CENTER)
                        .darkStyle()
                        .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try{
                                Stage stage = (Stage) InstallationButton.getScene().getWindow();
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
                            } catch (IOException ex) {
                                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    notif.show();
                }
            }else if (item.equalsIgnoreCase("PPPoE/PPP/IPSec Server") ){
                if(pppoeconfInstallation.equals("Installed") && pppInstallation.equals("Installed") && strongswanInstallation.equals("Installed")){
                    AboutPackage.setVisible(true);
                    InstallationPackage.setVisible(false);
                    l2tpipsecserverWindow.setVisible(false);
                    l2tpipsecclientWindow.setVisible(false);
                    l2tppppipsecserverWindow.setVisible(false);
                    l2tppppipsecclientWindow.setVisible(false);
                    pppoepppipclientWindow.setVisible(false);
                    pppoepppipsecserverWindow.setVisible(true);
                    pppoepppipsecclientWindow.setVisible(false);
                    pptpserverWindow.setVisible(false);
                    pptpclientWindow.setVisible(false);
                    greserverWindow.setVisible(false);
                    pptpimg.setVisible(false);
                    greimg.setVisible(false);
                    l2tppppipsecimg.setVisible(false);
                    l2tpipsecimg.setVisible(false);
                    l2tpipsecclientimg.setVisible(false);
                    pppoeclientimg.setVisible(false);
                    pppoeipsecimg.setVisible(true);
                    rightAdrpppoepppipsecSField.setText("%any");
                    leftsubnetpppoepppipsecSField.setText("0.0.0.0/0");
                    rightsubnetpppoepppipsecSField.setText("0.0.0.0/0");
                    Image notifimg = new Image(
                        getClass().getResourceAsStream("/vpnlite/img/About.png"));
                    Node notimg = new ImageView(notifimg);
                    notif = Notifications.create()
                        .graphic(notimg)
                        .title("PPPoE/PPP/IPSec Server configuration:")
                        .text(" Press on 'About' button for more information")
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.CENTER)
                        .darkStyle()
                        .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            mystackane.setVisible(true);
                            JFXDialogLayout content = new JFXDialogLayout();
                            content.setStyle("-fx-background-image: url(\"/vpnlite/img/backgroundApp.png\");");
                            Text t1 = new Text();
                            t1.setText("About PPPoE/PPP/IPSec Server");
                            t1.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 19));
                            t1.setFill(Color.BLACK);
                            content.setHeading(t1);
                            Text t2 = new Text();
                            t2.setText("シ\tFor PPPoE/PPP/IPSec Server, you can use only «Client-To-Site» configuration\n\n"
                                + "✗\tServer IP: You can choose your own IP with your choice\n\n"
                                + "✗\tDNS 1 & DNS 2: You can choose your own DNS but it's preferable to use the defined DNS\n\n"
                                + "✗\tPAP or CHAP: You MUST choose one of them for your configuration\n\n"
                                + "✗\tFor «Nomade-To-Site» Configuration:\n\n\t\t➺ You can use «TRANSPORT» or «TUNNEL» Modes\n\n"
                                + "\t\t➺ Example:\n\t\t\tFrom Server (Select WAN Side + Tunnel/Transport Option)\n\n"
                                + "✗\tFor «TUNNEL» Mode:\n\n\t\t➺ WAN Side: it is better to leave the default settings of «Local/Remote Network» and «Remote IP Address»\n\n\t\t➺ Client Side: it is better to leave the default settings of «Local/Remote Network»\n\n\n\n"
                                + "シ\tYou can use Wireshark filters:\n\n\t\t➺ Client-to-Site: « pppoe || ppp »\n\n\n\n"
                                + "➤\tNote:\n\n\t\t➺ You must enter your filter on Wireshark before starting VPN configuration");
                            t2.setFont(Font.font ("Arial", 15));
                            t2.setFill(Color.GRAY);
                            content.setBody(t2);
                            JFXDialog dialog = new JFXDialog(mystackane, content, JFXDialog.DialogTransition.CENTER);
                            dialog.setOverlayClose(false);
                            JFXButton button = new JFXButton("OK");
                            button.setStyle("-fx-background-color: #8A8B8C; -fx-text-fill: black;");
                            button.setButtonType(JFXButton.ButtonType.RAISED);
                            button.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    mystackane.setVisible(false);
                                    dialog.close();
                                }
                            });
                            content.setActions(button);
                            dialog.show();
                        }
                    });
                    notif.show();
                    Q="PPIPS";
                }else {
                    
                    nextButton.setDisable(true);
                    AboutPackage.setVisible(false);
                    InstallationPackage.setVisible(true);
                    
                    l2tpipsecserverWindow.setVisible(false);
                    l2tpipsecclientWindow.setVisible(false);
                    l2tppppipsecserverWindow.setVisible(false);
                    l2tppppipsecclientWindow.setVisible(false);
                    pppoepppipclientWindow.setVisible(false);
                    pppoepppipsecserverWindow.setVisible(true);
                    pppoepppipsecclientWindow.setVisible(false);
                    pptpserverWindow.setVisible(false);
                    pptpclientWindow.setVisible(false);
                    greserverWindow.setVisible(false);
                    pptpimg.setVisible(false);
                    greimg.setVisible(false);
                    l2tppppipsecimg.setVisible(false);
                    l2tpipsecimg.setVisible(false);
                    l2tpipsecclientimg.setVisible(false);
                    pppoeclientimg.setVisible(false);
                    pppoeipsecimg.setVisible(true);
                    rightAdrpppoepppipsecSField.setText("%any");
                    leftsubnetpppoepppipsecSField.setText("0.0.0.0/0");
                    rightsubnetpppoepppipsecSField.setText("0.0.0.0/0");
                    Image notifimg = new Image(
                        getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
                    Node notimg = new ImageView(notifimg);
                    notif = Notifications.create()
                        .graphic(notimg)
                        .title("Warning")
                        .text("Check if 'PPPoE', 'PPP' and 'Strongswan' packages are installed correctly")
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.CENTER)
                        .darkStyle()
                        .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try{
                                Stage stage = (Stage) InstallationButton.getScene().getWindow();
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
                            } catch (IOException ex) {
                                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    notif.show();
                }
            }else if (item.equalsIgnoreCase("PPTP Server") ){
                if(pptpInstallation.equals("Installed") && pppInstallation.equals("Installed")){
                    AboutPackage.setVisible(true);
                    InstallationPackage.setVisible(false);
                    l2tpipsecserverWindow.setVisible(false);
                    l2tpipsecclientWindow.setVisible(false);
                    l2tppppipsecserverWindow.setVisible(false);
                    l2tppppipsecclientWindow.setVisible(false);
                    pppoepppipclientWindow.setVisible(false);
                    pppoepppipsecserverWindow.setVisible(false);
                    pppoepppipsecclientWindow.setVisible(false);
                    pptpserverWindow.setVisible(true);
                    pptpclientWindow.setVisible(false);
                    greserverWindow.setVisible(false);
                    pptpimg.setVisible(true);
                    greimg.setVisible(false);
                    l2tppppipsecimg.setVisible(false);
                    l2tpipsecimg.setVisible(false);
                    l2tpipsecclientimg.setVisible(false);
                    pppoeclientimg.setVisible(false);
                    pppoeipsecimg.setVisible(false);
                    stoppptpSButton.setDisable(true);
                    Image notifimg = new Image(
                        getClass().getResourceAsStream("/vpnlite/img/About.png"));
                    Node notimg = new ImageView(notifimg);
                    notif = Notifications.create()
                        .graphic(notimg)
                        .title("PPTP Server configuration:")
                        .text(" Press on 'About' button for more information")
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.CENTER)
                        .darkStyle()
                        .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            mystackane.setVisible(true);
                            JFXDialogLayout content = new JFXDialogLayout();
                            content.setStyle("-fx-background-image: url(\"/vpnlite/img/backgroundApp.png\");");
                            Text t1 = new Text();
                            t1.setText("About PPTP Server");
                            t1.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 19));
                            t1.setFill(Color.BLACK);
                            content.setHeading(t1);
                            Text t2 = new Text();
                            t2.setText("シ\tFor PPTP Server, you can use only «Client-To-Site» configuration\n\n"
                                + "✗\tVPN Server Address:\n\n\t\t➺ An IP from your network that is not used by any computer or the router\n\n"
                                + "✗\tClient Addresses:\n\n\t\t➺ The client IP range. Leave it blank will not work. It must be like a.b.c.xxx-yyy\n\n"
                                + "✗\tVPN Network:\n\n\t\t➺ This is used for iptables configuration - it must be like a.b.c.0/xx\n\n"
                                + "✗\tUsername:\n\n\t\t➺ Username to be used on Client Side\n\n"
                                + "✗\tPassword:\n\n\t\t➺ Password to be used on Client Side\n\n"
                                + "シ\tYou can use Wireshark filters:\n\n\t\t➺ Client-To-Site: « pptp || ppp »\n\n\n\n"
                                + "➤\tNote:\n\n\t\t➺ You must enter your filter on Wireshark before starting VPN configuration");
                            t2.setFont(Font.font ("Arial", 15));
                            t2.setFill(Color.GRAY);
                            content.setBody(t2);
                            JFXDialog dialog = new JFXDialog(mystackane, content, JFXDialog.DialogTransition.CENTER);
                            dialog.setOverlayClose(false);
                            JFXButton button = new JFXButton("OK");
                            button.setStyle("-fx-background-color: #8A8B8C; -fx-text-fill: black;");
                            button.setButtonType(JFXButton.ButtonType.RAISED);
                            button.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    mystackane.setVisible(false);
                                    dialog.close();
                                }
                            });
                            content.setActions(button);
                            dialog.show();
                        }
                    });
                    notif.show();
                    Q="PS";
                }else {
                    nextButton.setDisable(true);
                    AboutPackage.setVisible(false);
                    InstallationPackage.setVisible(true);
                    validatepptpSButton.setDisable(true);
                    pptpserverfields.setDisable(true);
                    
                    l2tpipsecserverWindow.setVisible(false);
                    l2tpipsecclientWindow.setVisible(false);
                    l2tppppipsecserverWindow.setVisible(false);
                    l2tppppipsecclientWindow.setVisible(false);
                    pppoepppipclientWindow.setVisible(false);
                    pppoepppipsecserverWindow.setVisible(false);
                    pppoepppipsecclientWindow.setVisible(false);
                    pptpserverWindow.setVisible(true);
                    pptpclientWindow.setVisible(false);
                    greserverWindow.setVisible(false);
                    pptpimg.setVisible(true);
                    greimg.setVisible(false);
                    l2tppppipsecimg.setVisible(false);
                    l2tpipsecimg.setVisible(false);
                    l2tpipsecclientimg.setVisible(false);
                    pppoeclientimg.setVisible(false);
                    pppoeipsecimg.setVisible(false);
                    stoppptpSButton.setDisable(true);
                    Image notifimg = new Image(
                        getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
                    Node notimg = new ImageView(notifimg);
                    notif = Notifications.create()
                        .graphic(notimg)
                        .title("Warning")
                        .text("Check if 'PPTP' and 'PPP' packages are installed correctly")
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.CENTER)
                        .darkStyle()
                        .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try{
                                Stage stage = (Stage) InstallationButton.getScene().getWindow();
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
                            } catch (IOException ex) {
                                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    notif.show();
                }
            }else if (item.equalsIgnoreCase("GRE Server") ){
                AboutPackage.setVisible(true);
                InstallationPackage.setVisible(false);
                l2tpipsecserverWindow.setVisible(false);
                l2tpipsecclientWindow.setVisible(false);
                l2tppppipsecserverWindow.setVisible(false);
                l2tppppipsecclientWindow.setVisible(false);
                pppoepppipclientWindow.setVisible(false);
                pppoepppipsecserverWindow.setVisible(false);
                pppoepppipsecclientWindow.setVisible(false);
                pptpserverWindow.setVisible(false);
                pptpclientWindow.setVisible(false);
                greserverWindow.setVisible(true);
                pptpimg.setVisible(false);
                greimg.setVisible(true);
                l2tppppipsecimg.setVisible(false);
                l2tpipsecimg.setVisible(false);
                l2tpipsecclientimg.setVisible(false);
                pppoeclientimg.setVisible(false);
                pppoeipsecimg.setVisible(false);
                stopgreSButton.setDisable(true);
                Image notifimg = new Image(
                    getClass().getResourceAsStream("/vpnlite/img/About.png"));
                Node notimg = new ImageView(notifimg);
                notif = Notifications.create()
                    .graphic(notimg)
                    .title("GRE Server configuration:")
                    .text(" Press on 'About' button for more information")
                    .hideAfter(Duration.seconds(10))
                    .position(Pos.CENTER)
                    .darkStyle()
                    .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        mystackane.setVisible(true);
                        JFXDialogLayout content = new JFXDialogLayout();
                        content.setStyle("-fx-background-image: url(\"/vpnlite/img/backgroundApp.png\");");
                        Text t1 = new Text();
                        t1.setText("About GRE Server");
                        t1.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 19));
                        t1.setFill(Color.BLACK);
                        content.setHeading(t1);
                        Text t2 = new Text();
                        t2.setText("シ\tFor GRE, you can use only «Site-To-Site» configuration\n\n"
                            + "✗\tLocal IP Address:\n\n\t\t➺ An IP from your network that is not used by any computer or the router\n\n"
                            + "✗\tRemote Public IP Address:\n\n\t\t➺ IP address of remote tunnel end.\n\n"
                            + "✗\tRemote Network Address:\n\n\t\t➺ The Network of remote tunnel end\n\n"
                            + "✗\tGRE IP Address:\n\n\t\t➺ You can set this field with your choice\n\n"
                            + "\t\t➺ The GRE IP Addresses of both servers must be in the same Network\n\n"
                            + "シ\tYou can use Wireshark filters:\n\n\t\t➺ Site-To-Site: « gre »\n\n\n\n"
                            + "➤\tNote:\n\n\t\t➺ You must enter your filter on Wireshark before starting VPN configuration");
                        t2.setFont(Font.font ("Arial", 15));
                        t2.setFill(Color.GRAY);
                        content.setBody(t2);
                        JFXDialog dialog = new JFXDialog(mystackane, content, JFXDialog.DialogTransition.CENTER);
                        dialog.setOverlayClose(false);
                        JFXButton button = new JFXButton("OK");
                        button.setStyle("-fx-background-color: #8A8B8C; -fx-text-fill: black;");
                        button.setButtonType(JFXButton.ButtonType.RAISED);
                        button.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                mystackane.setVisible(false);
                                dialog.close();
                            }
                        });
                        content.setActions(button);
                        dialog.show();
                    }
                });
                notif.show();
                Q="GS";
            }else if(item.equalsIgnoreCase("L2TP/IPSec Client") ){
                if(strongswanInstallation.equals("Installed") && xl2tpdInstallation.equals("Installed")){
                    AboutPackage.setVisible(true);
                    InstallationPackage.setVisible(false);
                    l2tpipsecserverWindow.setVisible(false);
                    l2tpipsecclientWindow.setVisible(true);
                    l2tppppipsecserverWindow.setVisible(false);
                    l2tppppipsecclientWindow.setVisible(false);
                    pppoepppipclientWindow.setVisible(false);
                    pppoepppipsecserverWindow.setVisible(false);
                    pppoepppipsecclientWindow.setVisible(false);
                    pptpserverWindow.setVisible(false);
                    pptpclientWindow.setVisible(false);
                    greserverWindow.setVisible(false);
                    pptpimg.setVisible(false);
                    greimg.setVisible(false);
                    l2tppppipsecimg.setVisible(false);
                    l2tpipsecimg.setVisible(false);
                    l2tpipsecclientimg.setVisible(true);
                    pppoeclientimg.setVisible(false);
                    pppoeipsecimg.setVisible(false);
                    stopl2tpipsecCButton.setDisable(true);
                    Image notifimg = new Image(
                        getClass().getResourceAsStream("/vpnlite/img/About.png"));
                    Node notimg = new ImageView(notifimg);
                    notif = Notifications.create()
                        .graphic(notimg)
                        .title("L2TP/IPSec Client configuration:")
                        .text(" Press on 'About' button for more information")
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.CENTER)
                        .darkStyle()
                        .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            mystackane.setVisible(true);
                            JFXDialogLayout content = new JFXDialogLayout();
                            content.setStyle("-fx-background-image: url(\"/vpnlite/img/backgroundApp.png\");");
                            Text t1 = new Text();
                            t1.setText("About L2TP/IPSec Client");
                            t1.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 19));
                            t1.setFill(Color.BLACK);
                            content.setHeading(t1);
                            Text t2 = new Text();
                            t2.setText("シ\tFor L2TP/IPSec Client, you can use «Client-To-Server» configuration\n\n"
                                + "✗\tFor «Client-To-Server» Configuration:\n\n\t\t➺ You can use «TRANSPORT» or «TUNNEL» Modes\n\n"
                                + "\t\t➺ Example:\n\t\t\tFrom Client (Select Tunnel/Transport Option)\n\n"
                                + "✗\tKey Life:\n\n\t\t➺ Time when rekeying is initiated. Set to zero to disable.\n\n"
                                + "✗\tIKE Lifetime:\n\n\t\t➺ How long the keying channel of a connection should last before being renegotiated.\n\n"
                                + "✗\tLocal IP Address:\n\n\t\t➺ It's your LAN IP address. Use the default value of this field\n\n"
                                + "✗\tServer IP Address:\n\n\t\t➺ It's the Server IP Address which the configuration must be established\n\n"
                                + "シ\tYou can use Wireshark filters:\n\n\t\t➺ «Client-To-Site»: udp.dstport==1701 || udp.dstport=4500 || udp.dstport==500 || esp\n\n"
                                + "➤\tNote:\n\n\t\t➺ You must enter your filter on Wireshark before starting VPN configuration\n\n\t\t➺ For correct configuration, use the default settings");
                            t2.setFont(Font.font ("Arial", 15));
                            t2.setFill(Color.GRAY);
                            content.setBody(t2);
                            JFXDialog dialog = new JFXDialog(mystackane, content, JFXDialog.DialogTransition.CENTER);
                            dialog.setOverlayClose(false);
                            JFXButton button = new JFXButton("OK");
                            button.setStyle("-fx-background-color: #8A8B8C; -fx-text-fill: black;");
                            button.setButtonType(JFXButton.ButtonType.RAISED);
                            button.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    mystackane.setVisible(false);
                                    dialog.close();
                                }
                            });
                            content.setActions(button);
                            dialog.show();
                        }
                    });
                    notif.show();
                    Q="LIC";
                    userButton.setDisable(false);
                }else{
                    l2tpipsecclientFields.setDisable(true);
                    validatel2tpipsecCButton.setDisable(true);
                    nextButton.setDisable(true);
                    AboutPackage.setVisible(false);
                    InstallationPackage.setVisible(true);
                        
                    l2tpipsecserverWindow.setVisible(false);
                    l2tpipsecclientWindow.setVisible(true);
                    l2tppppipsecserverWindow.setVisible(false);
                    l2tppppipsecclientWindow.setVisible(false);
                    pppoepppipclientWindow.setVisible(false);
                    pppoepppipsecserverWindow.setVisible(false);
                    pppoepppipsecclientWindow.setVisible(false);
                    pptpserverWindow.setVisible(false);
                    pptpclientWindow.setVisible(false);
                    greserverWindow.setVisible(false);
                    pptpimg.setVisible(false);
                    greimg.setVisible(false);
                    l2tppppipsecimg.setVisible(false);
                    l2tpipsecimg.setVisible(false);
                    l2tpipsecclientimg.setVisible(true);
                    pppoeclientimg.setVisible(false);
                    pppoeipsecimg.setVisible(false);
                    stopl2tpipsecCButton.setDisable(true);
                    Image notifimg = new Image(
                        getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
                    Node notimg = new ImageView(notifimg);
                    notif = Notifications.create()
                        .graphic(notimg)
                        .title("Warning")
                        .text("Check if 'L2TP' and 'Strongswan' packages are installed correctly")
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.CENTER)
                        .darkStyle()
                        .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try{
                                Stage stage = (Stage) InstallationButton.getScene().getWindow();
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
                            } catch (IOException ex) {
                                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    notif.show();
                    Q="LIC";
                    userButton.setDisable(false);
                }
            }else if (item.equalsIgnoreCase("L2TP/PPP/IPSec Client") ){
                if(strongswanInstallation.equals("Installed") && xl2tpdInstallation.equals("Installed") && pppInstallation.equals("Installed")){
                    AboutPackage.setVisible(true);
                    InstallationPackage.setVisible(false);
                    l2tpipsecserverWindow.setVisible(false);
                    l2tpipsecclientWindow.setVisible(false);
                    l2tppppipsecserverWindow.setVisible(false);
                    l2tppppipsecclientWindow.setVisible(true);
                    pppoepppipclientWindow.setVisible(false);
                    pppoepppipsecserverWindow.setVisible(false);
                    pppoepppipsecclientWindow.setVisible(false);
                    pptpserverWindow.setVisible(false);
                    pptpclientWindow.setVisible(false);
                    greserverWindow.setVisible(false);
                    pptpimg.setVisible(false);
                    greimg.setVisible(false);
                    l2tppppipsecimg.setVisible(false);
                    l2tpipsecimg.setVisible(false);
                    l2tpipsecclientimg.setVisible(true);
                    pppoeclientimg.setVisible(false);
                    pppoeipsecimg.setVisible(false);
                    stopl2tppppipsecCButton.setDisable(true);
                    Image notifimg = new Image(
                        getClass().getResourceAsStream("/vpnlite/img/About.png"));
                    Node notimg = new ImageView(notifimg);
                    notif = Notifications.create()
                        .graphic(notimg)
                        .title("L2TP/PPP/IPSec Client configuration:")
                        .text(" Press on 'About' button for more information")
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.CENTER)
                        .darkStyle()
                        .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            mystackane.setVisible(true);
                            JFXDialogLayout content = new JFXDialogLayout();
                            content.setStyle("-fx-background-image: url(\"/vpnlite/img/backgroundApp.png\");");
                            Text t1 = new Text();
                            t1.setText("About L2TP/PPP/IPSec Client");
                            t1.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 19));
                            t1.setFill(Color.BLACK);
                            content.setHeading(t1);
                            Text t2 = new Text();
                            t2.setText("シ\tFor L2TP/PPP/IPSec Client, you can use «Client-To-Server» configuration\n\n"
                                + "✗\tFor «Client-To-Server» Configuration: You can use «TRANSPORT» or «TUNNEL» Modes\n\n"
                                + "\t\t➺ Example:\n\t\t\tFrom Client (Select Tunnel/Transport Option)\n\n"
                                + "✗\tKey Life:\n\n\t\t➺ Time when rekeying is initiated. Set to zero to disable.\n\n"
                                + "✗\tIKE Lifetime:\n\n\t\t➺ How long the keying channel of a connection should last before being renegotiated.\n\n"
                                + "✗\tLocal IP Address:\n\n\t\t➺ It's your LAN IP address. Use the default value of this field\n\n"
                                + "✗\tServer IP Address:\n\n\t\t➺ It's the Server IP Address which the configuration must be established\n\n"
                                + "✗\tUsername & Password:\n\n\t\t➺ You have to use the same Username & Password configured on the Server\n\n"
                                + "シ\tYou can use Wireshark filters:\n\n\t\t➺ «Client-To-Site»: udp.dstport==1701 || udp.dstport=4500 || udp.dstport==500 || esp || ppp\n\n"
                                + "➤\tNote:\n\n\t\t➺ You must enter your filter on Wireshark before starting VPN configuration\n\n\t\t➺ For correct configuration, use the default settings");
                            t2.setFont(Font.font ("Arial", 15));
                            t2.setFill(Color.GRAY);
                            content.setBody(t2);
                            JFXDialog dialog = new JFXDialog(mystackane, content, JFXDialog.DialogTransition.CENTER);
                            dialog.setOverlayClose(false);
                            JFXButton button = new JFXButton("OK");
                            button.setStyle("-fx-background-color: #8A8B8C; -fx-text-fill: black;");
                            button.setButtonType(JFXButton.ButtonType.RAISED);
                            button.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    mystackane.setVisible(false);
                                    dialog.close();
                                }
                            });
                            content.setActions(button);
                            dialog.show();
                        }
                    });
                    notif.show();
                    Q="LPIC";
                    userButton.setDisable(false);
                }else {
                    l2tppppipsecclientfields.setDisable(true);
                    l2tppppipsecclientfields2.setDisable(true);
                    validatel2tppppipsecCButton.setDisable(true);
                    nextButton.setDisable(true);
                    AboutPackage.setVisible(false);
                    InstallationPackage.setVisible(true);
                    
                    l2tpipsecserverWindow.setVisible(false);
                    l2tpipsecclientWindow.setVisible(false);
                    l2tppppipsecserverWindow.setVisible(false);
                    l2tppppipsecclientWindow.setVisible(true);
                    pppoepppipclientWindow.setVisible(false);
                    pppoepppipsecserverWindow.setVisible(false);
                    pppoepppipsecclientWindow.setVisible(false);
                    pptpserverWindow.setVisible(false);
                    pptpclientWindow.setVisible(false);
                    greserverWindow.setVisible(false);
                    pptpimg.setVisible(false);
                    greimg.setVisible(false);
                    l2tppppipsecimg.setVisible(false);
                    l2tpipsecimg.setVisible(false);
                    l2tpipsecclientimg.setVisible(true);
                    pppoeclientimg.setVisible(false);
                    pppoeipsecimg.setVisible(false);
                    stopl2tppppipsecCButton.setDisable(true);
                    Image notifimg = new Image(
                        getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
                    Node notimg = new ImageView(notifimg);
                    notif = Notifications.create()
                        .graphic(notimg)
                        .title("Warning")
                        .text("Check if 'L2TP', 'PPP' and 'Strongswan' packages are installed correctly")
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.CENTER)
                        .darkStyle()
                        .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try{
                                Stage stage = (Stage) InstallationButton.getScene().getWindow();
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
                            } catch (IOException ex) {
                                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    notif.show();
                    userButton.setDisable(false);
                }
            }else if (item.equalsIgnoreCase("PPPoE/PPP/IP Client") ){
                if(pppoeconfInstallation.equals("Installed") && pppInstallation.equals("Installed")){
                    AboutPackage.setVisible(true);
                    InstallationPackage.setVisible(false);
                    l2tpipsecserverWindow.setVisible(false);
                    l2tpipsecclientWindow.setVisible(false);
                    l2tppppipsecserverWindow.setVisible(false);
                    l2tppppipsecclientWindow.setVisible(false);
                    pppoepppipclientWindow.setVisible(true);
                    pppoepppipsecserverWindow.setVisible(false);
                    pppoepppipsecclientWindow.setVisible(false);
                    pptpserverWindow.setVisible(false);
                    pptpclientWindow.setVisible(false);
                    greserverWindow.setVisible(false);
                    pptpimg.setVisible(false);
                    greimg.setVisible(false);
                    l2tppppipsecimg.setVisible(false);
                    l2tpipsecimg.setVisible(false);
                    l2tpipsecclientimg.setVisible(false);
                    pppoeclientimg.setVisible(true);
                    pppoeipsecimg.setVisible(false);
                    Image notifimg = new Image(
                        getClass().getResourceAsStream("/vpnlite/img/About.png"));
                    Node notimg = new ImageView(notifimg);
                    notif = Notifications.create()
                        .graphic(notimg)
                        .title("PPPoE/PPP/IP Client configuration:")
                        .text(" Press on 'About' button for more information")
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.CENTER)
                        .darkStyle()
                        .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            mystackane.setVisible(true);
                            JFXDialogLayout content = new JFXDialogLayout();
                            content.setStyle("-fx-background-image: url(\"/vpnlite/img/backgroundApp.png\");");
                            Text t1 = new Text();
                            t1.setText("About PPPoE/PPP/IP Client");
                            t1.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 19));
                            t1.setFill(Color.BLACK);
                            content.setHeading(t1);
                            Text t2 = new Text();
                            t2.setText("シ\tFor PPPoE/PPP/IP, you can use only «Client-To-Site» configuration\n\n"
                                + "✗\tUsername & Password:\n\n\t\t➺ You have to use the same Username & Password configured on the Server\n\n"
                                + "✗\tPAP or CHAP: You MUST choose one of them for your configuration\n\n\n\n"
                                + "シ\tYou can use Wireshark filters:\n\n\t\t➺ Client-to-Site: « pppoe || ppp »\n\n\n\n"
                                + "➤\tNote:\n\n\t\t➺ You must enter your filter on Wireshark before starting VPN configuration");
                            t2.setFont(Font.font ("Arial", 15));
                            t2.setFill(Color.GRAY);
                            content.setBody(t2);
                            JFXDialog dialog = new JFXDialog(mystackane, content, JFXDialog.DialogTransition.CENTER);
                            dialog.setOverlayClose(false);
                            JFXButton button = new JFXButton("OK");
                            button.setStyle("-fx-background-color: #8A8B8C; -fx-text-fill: black;");
                            button.setButtonType(JFXButton.ButtonType.RAISED);
                            button.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    mystackane.setVisible(false);
                                    dialog.close();
                                }
                            });
                            content.setActions(button);
                            dialog.show();
                        }
                    });
                    notif.show();
                    Q="PPIC";
                    userButton.setDisable(false);
                }else {
                    
                    nextButton.setDisable(true);
                    AboutPackage.setVisible(false);
                    InstallationPackage.setVisible(true);
                    
                    l2tpipsecserverWindow.setVisible(false);
                    l2tpipsecclientWindow.setVisible(false);
                    l2tppppipsecserverWindow.setVisible(false);
                    l2tppppipsecclientWindow.setVisible(false);
                    pppoepppipclientWindow.setVisible(true);
                    pppoepppipsecserverWindow.setVisible(false);
                    pppoepppipsecclientWindow.setVisible(false);
                    pptpserverWindow.setVisible(false);
                    pptpclientWindow.setVisible(false);
                    greserverWindow.setVisible(false);
                    pptpimg.setVisible(false);
                    greimg.setVisible(false);
                    l2tppppipsecimg.setVisible(false);
                    l2tpipsecimg.setVisible(false);
                    l2tpipsecclientimg.setVisible(false);
                    pppoeclientimg.setVisible(true);
                    pppoeipsecimg.setVisible(false);
                    Image notifimg = new Image(
                        getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
                    Node notimg = new ImageView(notifimg);
                    notif = Notifications.create()
                        .graphic(notimg)
                        .title("Warning")
                        .text("Check if 'PPPoE' and 'PPP' packages are installed correctly")
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.CENTER)
                        .darkStyle()
                        .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try{
                                Stage stage = (Stage) InstallationButton.getScene().getWindow();
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
                            } catch (IOException ex) {
                                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    notif.show();
                    userButton.setDisable(false);
                }
            }else if (item.equalsIgnoreCase("PPPoE/PPP/IPSec Client") ){
                if(pppoeconfInstallation.equals("Installed") && pppInstallation.equals("Installed") && strongswanInstallation.equals("Installed")){
                    AboutPackage.setVisible(true);
                    InstallationPackage.setVisible(false);
                    l2tpipsecserverWindow.setVisible(false);
                    l2tpipsecclientWindow.setVisible(false);
                    l2tppppipsecserverWindow.setVisible(false);
                    l2tppppipsecclientWindow.setVisible(false);
                    pppoepppipclientWindow.setVisible(false);
                    pppoepppipsecserverWindow.setVisible(false);
                    pppoepppipsecclientWindow.setVisible(true);
                    pptpserverWindow.setVisible(false);
                    pptpclientWindow.setVisible(false);
                    greserverWindow.setVisible(false);
                    pptpimg.setVisible(false);
                    greimg.setVisible(false);
                    l2tppppipsecimg.setVisible(false);
                    l2tpipsecimg.setVisible(false);
                    l2tpipsecclientimg.setVisible(false);
                    pppoeclientimg.setVisible(false);
                    pppoeipsecimg.setVisible(true);
                    rightAdrpppoepppipsecCField.setText("%any");
                    leftsubnetpppoepppipsecCField.setText("0.0.0.0/0");
                    rightsubnetpppoepppipsecCField.setText("0.0.0.0/0");
                    Image notifimg = new Image(
                        getClass().getResourceAsStream("/vpnlite/img/About.png"));
                    Node notimg = new ImageView(notifimg);
                    notif = Notifications.create()
                        .graphic(notimg)
                        .title("PPPoE/PPP/IPSec Client configuration:")
                        .text(" Press on 'About' button for more information")
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.CENTER)
                        .darkStyle()
                        .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            mystackane.setVisible(true);
                            JFXDialogLayout content = new JFXDialogLayout();
                            content.setStyle("-fx-background-image: url(\"/vpnlite/img/backgroundApp.png\");");
                            Text t1 = new Text();
                            t1.setText("About PPPoE/PPP/IPSec Client");
                            t1.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 19));
                            t1.setFill(Color.BLACK);
                            content.setHeading(t1);
                            Text t2 = new Text();
                            t2.setText("シ\tFor PPPoE/PPP/IPSec, you can use only «Client-To-Site» configuration\n\n"
                                + "✗\tFor «Client-To-Server» Configuration:\n\n\t\t➺ You can use «TRANSPORT» or «TUNNEL» Modes\n\n"
                                + "\t\t➺ Example:\n\t\t\tFrom Client (Select Tunnel/Transport Option)\n\n"
                                + "✗\tKey Life:\n\n\t\t➺ Time when rekeying is initiated. Set to zero to disable.\n\n"
                                + "✗\tIKE Lifetime:\n\n\t\t➺ How long the keying channel of a connection should last before being renegotiated.\n\n"
                                + "✗\tServer IP Address:\n\n\t\t➺ It's the Server IP Address which the configuration must be established\n\n"
                                + "✗\tUsername & Password:\n\n\t\t➺ You have to use the same Username & Password configured on the Server\n\n"
                                + "✗\tPAP or CHAP: You MUST choose one of them for your configuration\n\n"
                                + "シ\tYou can use Wireshark filters:\n\n\t\t➺ Client-to-Site: « pppoe || ppp || udp.dstport=4500 || udp.dstport==500 || esp»\n\n"
                                + "➤\tNote:\n\n\t\t➺ You must enter your filter on Wireshark before starting VPN configuration");
                            t2.setFont(Font.font ("Arial", 15));
                            t2.setFill(Color.GRAY);
                            content.setBody(t2);
                            JFXDialog dialog = new JFXDialog(mystackane, content, JFXDialog.DialogTransition.CENTER);
                            dialog.setOverlayClose(false);
                            JFXButton button = new JFXButton("OK");
                            button.setStyle("-fx-background-color: #8A8B8C; -fx-text-fill: black;");
                            button.setButtonType(JFXButton.ButtonType.RAISED);
                            button.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    mystackane.setVisible(false);
                                    dialog.close();
                                }
                            });
                            content.setActions(button);
                            dialog.show();
                        }
                    });
                    notif.show();
                    Q="PPIPC";
                    userButton.setDisable(false);
                }else {
                    
                    nextButton.setDisable(true);
                    AboutPackage.setVisible(false);
                    InstallationPackage.setVisible(true);
                    
                    l2tpipsecserverWindow.setVisible(false);
                    l2tpipsecclientWindow.setVisible(false);
                    l2tppppipsecserverWindow.setVisible(false);
                    l2tppppipsecclientWindow.setVisible(false);
                    pppoepppipclientWindow.setVisible(false);
                    pppoepppipsecserverWindow.setVisible(false);
                    pppoepppipsecclientWindow.setVisible(true);
                    pptpserverWindow.setVisible(false);
                    pptpclientWindow.setVisible(false);
                    greserverWindow.setVisible(false);
                    pptpimg.setVisible(false);
                    greimg.setVisible(false);
                    l2tppppipsecimg.setVisible(false);
                    l2tpipsecimg.setVisible(false);
                    l2tpipsecclientimg.setVisible(false);
                    pppoeclientimg.setVisible(false);
                    pppoeipsecimg.setVisible(true);
                    rightAdrpppoepppipsecCField.setText("%any");
                    leftsubnetpppoepppipsecCField.setText("0.0.0.0/0");
                    rightsubnetpppoepppipsecCField.setText("0.0.0.0/0");
                    Image notifimg = new Image(
                        getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
                    Node notimg = new ImageView(notifimg);
                    notif = Notifications.create()
                        .graphic(notimg)
                        .title("Warning")
                        .text("Check if 'PPPoE', 'PPP' and 'Strongswan' packages are installed correctly")
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.CENTER)
                        .darkStyle()
                        .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try{
                                Stage stage = (Stage) InstallationButton.getScene().getWindow();
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
                            } catch (IOException ex) {
                                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    notif.show();
                    userButton.setDisable(false);
                }
            }else if (item.equalsIgnoreCase("PPTP Client") ){
                if(pptpInstallation.equals("Installed") && pppInstallation.equals("Installed")){
                    AboutPackage.setVisible(true);
                    InstallationPackage.setVisible(false);
                    l2tpipsecserverWindow.setVisible(false);
                    l2tpipsecclientWindow.setVisible(false);
                    l2tppppipsecserverWindow.setVisible(false);
                    l2tppppipsecclientWindow.setVisible(false);
                    pppoepppipclientWindow.setVisible(false);
                    pppoepppipsecserverWindow.setVisible(false);
                    pppoepppipsecclientWindow.setVisible(false);
                    pptpserverWindow.setVisible(false);
                    pptpclientWindow.setVisible(true);
                    greserverWindow.setVisible(false);
                    pptpimg.setVisible(true);
                    greimg.setVisible(false);
                    l2tppppipsecimg.setVisible(false);
                    l2tpipsecimg.setVisible(false);
                    l2tpipsecclientimg.setVisible(false);
                    pppoeclientimg.setVisible(false);
                    pppoeipsecimg.setVisible(false);
                    stoppptpCButton.setDisable(true);
                    Image notifimg = new Image(
                        getClass().getResourceAsStream("/vpnlite/img/About.png"));
                    Node notimg = new ImageView(notifimg);
                    notif = Notifications.create()
                        .graphic(notimg)
                        .title("PPTP Client configuration:")
                        .text(" Press on 'About' button for more information")
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.CENTER)
                        .darkStyle()
                        .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            mystackane.setVisible(true);
                            JFXDialogLayout content = new JFXDialogLayout();
                            content.setStyle("-fx-background-image: url(\"/vpnlite/img/backgroundApp.png\");");
                            Text t1 = new Text();
                            t1.setText("About PPTP Client");
                            t1.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 19));
                            t1.setFill(Color.BLACK);
                            content.setHeading(t1);
                            Text t2 = new Text();
                            t2.setText("シ\tFor PPTP, you can use only «Client-To-Site» configuration\n\n"
                                + "✗\tServer IP Address:\n\n\t\t➺ It's the Server IP Address which the configuration must be established\n\n"
                                + "✗\tUsername:\n\n\t\t➺ Username to be used on Client Side\n\n"
                                + "✗\tPassword:\n\n\t\t➺ Password to be used on Client Side\n\n\n\n"
                                + "シ\tYou can use Wireshark filters:\n\n\t\t➺ Client-To-Site: « pptp || ppp »\n\n\n\n"
                                + "➤\tNote:\n\n\t\t➺ You must enter your filter on Wireshark before starting VPN configuration");
                            t2.setFont(Font.font ("Arial", 15));
                            t2.setFill(Color.GRAY);
                            content.setBody(t2);
                            JFXDialog dialog = new JFXDialog(mystackane, content, JFXDialog.DialogTransition.CENTER);
                            dialog.setOverlayClose(false);
                            JFXButton button = new JFXButton("OK");
                            button.setStyle("-fx-background-color: #8A8B8C; -fx-text-fill: black;");
                            button.setButtonType(JFXButton.ButtonType.RAISED);
                            button.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    mystackane.setVisible(false);
                                    dialog.close();
                                }
                            });
                            content.setActions(button);
                            dialog.show();
                        }
                    });
                    notif.show();
                    Q="PC";
                    userButton.setDisable(false);
                }else {
                    validatepptpCButton.setDisable(true);
                    pptpfieldsClient.setDisable(true);
                    nextButton.setDisable(true);
                    AboutPackage.setVisible(false);
                    InstallationPackage.setVisible(true);
                    
                    l2tpipsecserverWindow.setVisible(false);
                    l2tpipsecclientWindow.setVisible(false);
                    l2tppppipsecserverWindow.setVisible(false);
                    l2tppppipsecclientWindow.setVisible(false);
                    pppoepppipclientWindow.setVisible(false);
                    pppoepppipsecserverWindow.setVisible(false);
                    pppoepppipsecclientWindow.setVisible(false);
                    pptpserverWindow.setVisible(false);
                    pptpclientWindow.setVisible(true);
                    greserverWindow.setVisible(false);
                    pptpimg.setVisible(true);
                    greimg.setVisible(false);
                    l2tppppipsecimg.setVisible(false);
                    l2tpipsecimg.setVisible(false);
                    l2tpipsecclientimg.setVisible(false);
                    pppoeclientimg.setVisible(false);
                    pppoeipsecimg.setVisible(false);
                    stoppptpCButton.setDisable(true);
                    Image notifimg = new Image(
                        getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
                    Node notimg = new ImageView(notifimg);
                    notif = Notifications.create()
                        .graphic(notimg)
                        .title("Warning")
                        .text("Check if 'PPTP' and 'PPP' packages are installed correctly")
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.CENTER)
                        .darkStyle()
                        .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try{
                                Stage stage = (Stage) InstallationButton.getScene().getWindow();
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
                            } catch (IOException ex) {
                                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    notif.show();
                    userButton.setDisable(false);
                }
            }
    }

    private void GetExternalIp() {
        URL whatismyip;
        try {
            whatismyip = new URL("http://checkip.amazonaws.com");
        
        BufferedReader in = new BufferedReader(new InputStreamReader(
                        whatismyip.openStream()));

        ip = in.readLine(); //you get the IP as a String
        System.out.println(ip);
        } catch (MalformedURLException ex) {
            Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void previousWindows(ActionEvent event) {
        if(R2 == true){
            try {
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
            } catch (IOException ex) {
                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(R1 == true){
            try {
                U1 = true;
                Stage stage = (Stage) previousButton.getScene().getWindow();
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
            } catch (IOException ex) {
                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void userPage(ActionEvent event) throws IOException {
        if(R2 == true){
            try {
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
            } catch (IOException ex) {
                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(R1 == true){
            try {
                U1 = true;
                Stage stage = (Stage) previousButton.getScene().getWindow();
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
            } catch (IOException ex) {
                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    @FXML
    private void vpnPage(ActionEvent event) {
        
    }

    @FXML
    private void testsPage(ActionEvent event) {
        
    }

    
    public void activateUser(){
        userButton.setDisable(false);
    }
    public void deactivateUser(){
        userButton.setDisable(true);
    }
    
    @FXML
    private void nextWindows(ActionEvent event) throws IOException {
        if(R2 == true){
            Stage stage = (Stage) nextButton.getScene().getWindow();
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
        }else if(R1 == true){
            Stage stage = (Stage) nextButton.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vpnlite/view/NetworkingTestsOverview.fxml"));
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
    private void validatel2tpipsecSConfiguration(ActionEvent event) throws Throwable {
        if(!transportl2tpipsecSField.isSelected() && !tunnell2tpipsecSField.isSelected())
        {
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Warning");
            tray.setMessage("You must select the IPSec type.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.showAndDismiss(Duration.seconds(2));
            this.finalize();
        }
        
        if(B==true && transportl2tpipsecSField.isSelected()){
            try {
                try {
                    String result ="";
                    p = Runtime.getRuntime().exec("InterfaceName.sh");
                    InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                    BufferedReader br=new BufferedReader(ipsr);
                    String ligne;
                    while ((ligne=br.readLine())!=null)
                    {
                        System.out.println(ligne);
                        result += ligne;
                        p = Runtime.getRuntime().exec("Wireshark.sh "+ligne+" udp.dstport==4500 udp.dstport==500 esp udp.dstport==1701");
                        validatel2tpipsecSButton.setDisable(true);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                p.waitFor(5, TimeUnit.SECONDS);
                p = Runtime.getRuntime().exec("L2TPIPSecSTransport.sh "+leftAdrl2tpipsecSField.getText()+" "+ipStartl2tpipsecSField.getText()+" "+ipEndl2tpipsecSField.getText()+" "+serverIPAdrl2tpipsecSField.getText());
                l2tpipsecserverFields.setDisable(true);
                vpnprotocolField.setDisable(true);
                stopl2tpipsecSButton.setDisable(false);
                previousButton.setDisable(true);
                configurationprogressBar.setVisible(true);
                Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Approved.png"));
                TrayNotification tray = new TrayNotification();
                tray.setTitle("Approved");
                tray.setMessage("Configuration of L2TP/IPSec Server is done successfuly.");
                tray.setRectangleFill(Paint.valueOf("#2A9A84"));
                tray.setImage(approved);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(Duration.seconds(2));
                T1 = true;
                l2tpipsecS = true;
                nextButton.setDisable(false);
                
                clientsidel2tpipsecserver = clientSideL2TPIPSecButton.isSelected();
                clientsidel2tpipsecserverString = clientSideL2TPIPSecButton.getText();
                wansidel2tpipsecserverString = wanSideL2TPIPSecButton.getText();
                transportl2tpipsecserverString = transportl2tpipsecSField.getText();
                tunnell2tpipsecserverString = tunnell2tpipsecSField.getText();
                wansidel2tpipsecserver = wanSideL2TPIPSecButton.isSelected();
                transportl2tpipsecserver = transportl2tpipsecSField.isSelected();
                tunnell2tpipsecserver = tunnell2tpipsecSField.isSelected();
                ikelifetimel2tpipsecserver = keyl2tpipsecSField.getText();
                keylifetimel2tpipsecserver = ikel2tpipsecSField.getText();
                keyexchangel2tpipsecserver = keyexl2tpipsecSField.getValue();
                localipl2tpipsecserver = leftAdrl2tpipsecSField.getText();
                localnetworkl2tpipsecserver = leftsubnetl2tpipsecSField.getText();
                remoteipl2tpipsecserver = rightAdrl2tpipsecSField.getText();
                remotenetworkl2tpipsecserver = rightsubnetl2tpipsecSField.getText();
            } catch (IOException ex) {
                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(B==true && tunnell2tpipsecSField.isSelected()){
            
            
            if (result2.get() == buttonTypeOne){
                System.out.println("Client-to-Server is selected");
                try {
                    try {
                        String result ="";
                        p = Runtime.getRuntime().exec("InterfaceName.sh");
                        InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                        BufferedReader br=new BufferedReader(ipsr);
                        String ligne;
                        while ((ligne=br.readLine())!=null)
                        {
                            System.out.println(ligne);
                            result += ligne;
                            p = Runtime.getRuntime().exec("Wireshark.sh "+ligne+" udp.dstport==4500 udp.dstport==500 esp udp.dstport==1701");
                            validatel2tpipsecSButton.setDisable(true);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    p.waitFor(5, TimeUnit.SECONDS);
                    p = Runtime.getRuntime().exec("L2TPIPSecSTunnel.sh "+leftAdrl2tpipsecSField.getText()+" "+leftsubnetl2tpipsecSField.getText()+" "+rightAdrl2tpipsecSField.getText()+" "+rightsubnetl2tpipsecSField.getText()+" "+keyexl2tpipsecSField.getValue());
                    l2tpipsecserverFields.setDisable(true);
                    vpnprotocolField.setDisable(true);
                    stopl2tpipsecSButton.setDisable(false);
                    previousButton.setDisable(true);
                    configurationprogressBar.setVisible(true);
                    Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Approved.png"));
                    TrayNotification tray = new TrayNotification();
                    tray.setTitle("Approved");
                    tray.setMessage("Configuration of L2TP/IPSec Server is done successfuly.");
                    tray.setRectangleFill(Paint.valueOf("#2A9A84"));
                    tray.setImage(approved);
                    tray.setAnimationType(AnimationType.POPUP);
                    tray.showAndDismiss(Duration.seconds(2));
                    T1 = true;
                    l2tpipsecS = true;
                    nextButton.setDisable(false);
                    
                    clientsidel2tpipsecserverString = clientSideL2TPIPSecButton.getText();
                    wansidel2tpipsecserverString = wanSideL2TPIPSecButton.getText();
                    transportl2tpipsecserverString = transportl2tpipsecSField.getText();
                    tunnell2tpipsecserverString = tunnell2tpipsecSField.getText();
                    clientsidel2tpipsecserver = clientSideL2TPIPSecButton.isSelected();
                    wansidel2tpipsecserver = wanSideL2TPIPSecButton.isSelected();
                    transportl2tpipsecserver = transportl2tpipsecSField.isSelected();
                    tunnell2tpipsecserver = tunnell2tpipsecSField.isSelected();
                    ikelifetimel2tpipsecserver = keyl2tpipsecSField.getText();
                    keylifetimel2tpipsecserver = ikel2tpipsecSField.getText();
                    keyexchangel2tpipsecserver = keyexl2tpipsecSField.getValue();
                    localipl2tpipsecserver = leftAdrl2tpipsecSField.getText();
                    localnetworkl2tpipsecserver = leftsubnetl2tpipsecSField.getText();
                    remoteipl2tpipsecserver = rightAdrl2tpipsecSField.getText();
                    remotenetworkl2tpipsecserver = rightsubnetl2tpipsecSField.getText();
                } catch (IOException ex) {
                    Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (result2.get() == buttonTypeTwo) {
                System.out.println("Server-to-Server is selected");
                try {
                    try {
                        String result ="";
                        p = Runtime.getRuntime().exec("InterfaceName.sh");
                        InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                        BufferedReader br=new BufferedReader(ipsr);
                        String ligne;
                        while ((ligne=br.readLine())!=null)
                        {
                            System.out.println(ligne);
                            result += ligne;
                            p = Runtime.getRuntime().exec("Wireshark.sh "+ligne+" udp.dstport==4500 udp.dstport==500 esp udp.dstport==1701");
                            validatel2tpipsecSButton.setDisable(true);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    p.waitFor(5, TimeUnit.SECONDS);
                    p = Runtime.getRuntime().exec("L2TPIPSecSTunnelS.sh "+leftAdrl2tpipsecSField.getText()+" "+leftsubnetl2tpipsecSField.getText()+" "+rightAdrl2tpipsecSField.getText()+" "+rightsubnetl2tpipsecSField.getText()+" "+keyexl2tpipsecSField.getValue());
                    l2tpipsecserverFields.setDisable(true);
                    vpnprotocolField.setDisable(true);
                    stopl2tpipsecSButton.setDisable(false);
                    previousButton.setDisable(true);
                    configurationprogressBar.setVisible(true);
                    Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Approved.png"));
                    TrayNotification tray = new TrayNotification();
                    tray.setTitle("Approved");
                    tray.setMessage("Configuration of L2TP/IPSec Server is done successfuly.");
                    tray.setRectangleFill(Paint.valueOf("#2A9A84"));
                    tray.setImage(approved);
                    tray.setAnimationType(AnimationType.POPUP);
                    tray.showAndDismiss(Duration.seconds(2));
                    T1 = true;
                    l2tpipsecS = true;
                    nextButton.setDisable(false);
                    
                    clientsidel2tpipsecserverString = clientSideL2TPIPSecButton.getText();
                    wansidel2tpipsecserverString = wanSideL2TPIPSecButton.getText();
                    transportl2tpipsecserverString = transportl2tpipsecSField.getText();
                    tunnell2tpipsecserverString = tunnell2tpipsecSField.getText();
                    clientsidel2tpipsecserver = clientSideL2TPIPSecButton.isSelected();
                    wansidel2tpipsecserver = wanSideL2TPIPSecButton.isSelected();
                    transportl2tpipsecserver = transportl2tpipsecSField.isSelected();
                    tunnell2tpipsecserver = tunnell2tpipsecSField.isSelected();
                    ikelifetimel2tpipsecserver = keyl2tpipsecSField.getText();
                    keylifetimel2tpipsecserver = ikel2tpipsecSField.getText();
                    keyexchangel2tpipsecserver = keyexl2tpipsecSField.getValue();
                    localipl2tpipsecserver = leftAdrl2tpipsecSField.getText();
                    localnetworkl2tpipsecserver = leftsubnetl2tpipsecSField.getText();
                    remoteipl2tpipsecserver = rightAdrl2tpipsecSField.getText();
                    remotenetworkl2tpipsecserver = rightsubnetl2tpipsecSField.getText();
                } catch (IOException ex) {
                    Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }else if(A==true && tunnell2tpipsecSField.isSelected()){
            try {
                try {
                    String result ="";
                    p = Runtime.getRuntime().exec("InterfaceName.sh");
                    InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                    BufferedReader br=new BufferedReader(ipsr);
                    String ligne;
                    while ((ligne=br.readLine())!=null)
                    {
                        System.out.println(ligne);
                        result += ligne;
                        p = Runtime.getRuntime().exec("Wireshark.sh "+ligne+" udp.dstport==4500 udp.dstport==500 esp udp.dstport==1701");
                        validatel2tpipsecSButton.setDisable(true);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                p.waitFor(5, TimeUnit.SECONDS);
                p = Runtime.getRuntime().exec("L2TPIPSecSTunnelC.sh "+leftAdrl2tpipsecSField.getText()+" "+leftsubnetl2tpipsecSField.getText()+" "+rightAdrl2tpipsecSField.getText()+" "+rightsubnetl2tpipsecSField.getText()+" "+keyexl2tpipsecSField.getValue());
                l2tpipsecserverFields.setDisable(true);
                vpnprotocolField.setDisable(true);
                stopl2tpipsecSButton.setDisable(false);
                previousButton.setDisable(true);
                configurationprogressBar.setVisible(true);
                Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Approved.png"));
                TrayNotification tray = new TrayNotification();
                tray.setTitle("Approved");
                tray.setMessage("Configuration of L2TP/IPSec Server is done successfuly.");
                tray.setRectangleFill(Paint.valueOf("#2A9A84"));
                tray.setImage(approved);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(Duration.seconds(2));
                T1 = true;
                l2tpipsecS = true;
                nextButton.setDisable(false);
                
                clientsidel2tpipsecserver = clientSideL2TPIPSecButton.isSelected();
                clientsidel2tpipsecserverString = clientSideL2TPIPSecButton.getText();
                wansidel2tpipsecserverString = wanSideL2TPIPSecButton.getText();
                transportl2tpipsecserverString = transportl2tpipsecSField.getText();
                tunnell2tpipsecserverString = tunnell2tpipsecSField.getText();
                wansidel2tpipsecserver = wanSideL2TPIPSecButton.isSelected();
                transportl2tpipsecserver = transportl2tpipsecSField.isSelected();
                tunnell2tpipsecserver = tunnell2tpipsecSField.isSelected();
                ikelifetimel2tpipsecserver = keyl2tpipsecSField.getText();
                keylifetimel2tpipsecserver = ikel2tpipsecSField.getText();
                keyexchangel2tpipsecserver = keyexl2tpipsecSField.getValue();
                localipl2tpipsecserver = leftAdrl2tpipsecSField.getText();
                localnetworkl2tpipsecserver = leftsubnetl2tpipsecSField.getText();
                remoteipl2tpipsecserver = rightAdrl2tpipsecSField.getText();
                remotenetworkl2tpipsecserver = rightsubnetl2tpipsecSField.getText();
            } catch (IOException ex) {
                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        T1 = true;
    }
    
    @FXML
    private void validatel2tpipsecCConfiguration(ActionEvent event) throws InterruptedException {
        if(transportl2tpipsecCField.isSelected()){
            try {
                    String result ="";
                    p = Runtime.getRuntime().exec("InterfaceName.sh");
                    p1 = Runtime.getRuntime().exec("GWYIPAddress.sh");
                    InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                    InputStreamReader ipsr1=new InputStreamReader(p1.getInputStream());
                    BufferedReader br=new BufferedReader(ipsr);
                    BufferedReader br1=new BufferedReader(ipsr1);
                    String ligne;
                    String ligne1;
                    while ((ligne=br.readLine())!=null)
                    {
                        System.out.println(ligne);
                        System.out.println(br1.readLine());
                        result += ligne;
                        p = Runtime.getRuntime().exec("Wireshark.sh "+ligne+" udp.dstport==4500 udp.dstport==500 esp udp.dstport==1701");
                        validatel2tpipsecCButton.setDisable(true);
                        p.waitFor(5, TimeUnit.SECONDS);
                        p = Runtime.getRuntime().exec("L2TPIPSecCTransport.sh "+leftAdrl2tpipsecCField.getText()+" "+rightAdrl2tpipsecCField.getText()+" "+br1.readLine()+" "+ligne);
                    }
                stopl2tpipsecCButton.setDisable(false);
                l2tpipsecclientFields.setDisable(true);
                vpnprotocolField.setDisable(true);
                previousButton.setDisable(true);
                configurationprogressBar.setVisible(true);
                Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Approved.png"));
                TrayNotification tray = new TrayNotification();
                tray.setTitle("Approved");
                tray.setMessage("Configuration of L2TP/IPSec Client is done successfuly.");
                tray.setRectangleFill(Paint.valueOf("#2A9A84"));
                tray.setImage(approved);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(Duration.seconds(2));
                T1 = true;
                l2tpipsecC = true;
                nextButton.setDisable(false);
                transportl2tpipsecclient = transportl2tpipsecCField.isSelected();
                tunnell2tpipsecclientString = tunnell2tpipsecCField.getText();
                transportl2tpipsecclientString = transportl2tpipsecCField.getText();
                tunnell2tpipsecclient = tunnell2tpipsecCField.isSelected();
                keylifetimel2tpipsecclient = keyl2tpipsecCField.getText();
                ikelifetimel2tpipsecclient = ikel2tpipsecCField.getText();
                localipl2tpipsecclient = leftAdrl2tpipsecCField.getText();
                serveripl2tpipsecclient = rightAdrl2tpipsecCField.getText();
                userButton.setDisable(false);
            } catch (IOException ex) {
                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(tunnell2tpipsecCField.isSelected()){
            try {
                    String result ="";
                    p = Runtime.getRuntime().exec("InterfaceName.sh");
                    InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                    BufferedReader br=new BufferedReader(ipsr);
                    p1 = Runtime.getRuntime().exec("GWYIPAddress.sh");
                    InputStreamReader ipsr1=new InputStreamReader(p1.getInputStream());
                    BufferedReader br1=new BufferedReader(ipsr1);
                    String ligne;
                    while ((ligne=br.readLine())!=null)
                    {
                        System.out.println(ligne);
                        System.out.println(br1.readLine());
                        result += ligne;
                        p = Runtime.getRuntime().exec("Wireshark.sh "+ligne+" udp.dstport==4500 udp.dstport==500 esp udp.dstport==1701");
                        validatel2tpipsecCButton.setDisable(true);
                        p.waitFor(5, TimeUnit.SECONDS);
                        p = Runtime.getRuntime().exec("L2TPIPSecCTunnel.sh "+leftAdrl2tpipsecCField.getText()+" "+rightAdrl2tpipsecCField.getText()+" "+br1.readLine()+" "+ligne);
                    }
                userButton.setDisable(false);
                stopl2tpipsecCButton.setDisable(false);
                l2tpipsecclientFields.setDisable(true);
                vpnprotocolField.setDisable(true);
                previousButton.setDisable(true);
                configurationprogressBar.setVisible(true);
                Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Approved.png"));
                TrayNotification tray = new TrayNotification();
                tray.setTitle("Approved");
                tray.setMessage("Configuration of L2TP/IPSec Client is done successfuly.");
                tray.setRectangleFill(Paint.valueOf("#2A9A84"));
                tray.setImage(approved);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(Duration.seconds(2));
                T1 = true;
                l2tpipsecC = true;
                nextButton.setDisable(false);
                tunnell2tpipsecclientString = tunnell2tpipsecCField.getText();
                transportl2tpipsecclientString = transportl2tpipsecCField.getText();
                transportl2tpipsecclient = transportl2tpipsecCField.isSelected();
                tunnell2tpipsecclient = tunnell2tpipsecCField.isSelected();
                keylifetimel2tpipsecclient = keyl2tpipsecCField.getText();
                ikelifetimel2tpipsecclient = ikel2tpipsecCField.getText();
                localipl2tpipsecclient = leftAdrl2tpipsecCField.getText();
                serveripl2tpipsecclient = rightAdrl2tpipsecCField.getText();
            } catch (IOException ex) {
                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    @FXML
    private void validatel2tppppipsecSConfiguration(ActionEvent event) throws Throwable {
        if(!transportl2tppppipsecSField.isSelected() && !tunnell2tppppipsecSField.isSelected())
        {
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Warning");
            tray.setMessage("You must select the IPSec type.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.showAndDismiss(Duration.seconds(2));
            this.finalize();
        }
        
        if(usernamel2tppppipsecSField.getText() == null || usernamel2tppppipsecSField.getText().trim().isEmpty() || passwordl2tppppipsecSField.getText() == null || passwordl2tppppipsecSField.getText().trim().isEmpty())
        {
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Warning");
            tray.setMessage("You must insert the User and Password.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.showAndDismiss(Duration.seconds(2));
            this.finalize();
        }
        
        if(transportl2tppppipsecSField.isSelected() && !usernamel2tppppipsecSField.getText().trim().isEmpty() && !passwordl2tppppipsecSField.getText().trim().isEmpty()){
            try {
                String result ="";
                p = Runtime.getRuntime().exec("InterfaceName.sh");
                InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                BufferedReader br=new BufferedReader(ipsr);
                String ligne;
                while ((ligne=br.readLine())!=null)
                {
                    System.out.println(ligne);
                    result += ligne;
                    p = Runtime.getRuntime().exec("Wireshark.sh "+ligne+" udp.dstport==4500 udp.dstport==500 esp udp.dstport==1701 ppp");
                    validatel2tppppipsecSButton.setDisable(true);
                }
            } catch (IOException ex) {
                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            p.waitFor(5, TimeUnit.SECONDS);
            p = Runtime.getRuntime().exec("L2TPPPPIPSecSTransport.sh "+leftAdrl2tppppipsecSField.getText()+" "+ipStartl2tppppipsecSField.getText()+" "+ipEndl2tppppipsecSField.getText()+" "+serverIPAdrl2tppppipsecSField.getText()+" "+usernamel2tppppipsecSField.getText()+" "+passwordl2tppppipsecSField.getText());
            stopl2tppppipsecSButton.setDisable(false);
            //validatel2tppppipsecSButton.setDisable(true);
            l2tppppipsecserverpppFields.setDisable(true);
            l2tppppipsecserveripsecFields.setDisable(true);
            previousButton.setDisable(true);
            configurationprogressBar.setVisible(true);
            vpnprotocolField.setDisable(true);
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Approved.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Approved");
            tray.setMessage("Configuration of L2TP/PPP/IPSec Server 'Transport' mode is done successfuly.\n\n"
                    + "Server IP : "+leftAdrl2tppppipsecSField.getText()+"\n"
                    + "Username: "+usernamel2tppppipsecSField.getText()+"\n"
                    + "Password: "+passwordl2tppppipsecSField.getText());
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(2));
            T1 = true;
            l2tppppipsecS = true;
            nextButton.setDisable(false);
            tunnell2tppppipsecserverString = tunnell2tppppipsecSField.getText();
            transportl2tppppipsecserverString = transportl2tppppipsecSField.getText();
            transportl2tppppipsecserver = transportl2tppppipsecSField.isSelected();
            tunnell2tppppipsecserver = tunnell2tppppipsecSField.isSelected();
            serveripl2tppppipsecserver = leftAdrl2tppppipsecSField.getText();
            usernamel2tppppipsecserver = usernamel2tppppipsecSField.getText();
            passwordl2tppppipsecserver = passwordl2tppppipsecSField.getText();
        }else if(tunnell2tppppipsecSField.isSelected() && !usernamel2tppppipsecSField.getText().trim().isEmpty() && !passwordl2tppppipsecSField.getText().trim().isEmpty()){
            try {
                String result ="";
                p = Runtime.getRuntime().exec("InterfaceName.sh");
                InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                BufferedReader br=new BufferedReader(ipsr);
                String ligne;
                while ((ligne=br.readLine())!=null)
                {
                    System.out.println(ligne);
                    result += ligne;
                    p = Runtime.getRuntime().exec("Wireshark.sh "+ligne+" udp.dstport==4500 udp.dstport==500 esp udp.dstport==1701 ppp");
                    validatel2tppppipsecSButton.setDisable(true);
                }
            } catch (IOException ex) {
                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            p.waitFor(5, TimeUnit.SECONDS);
            p = Runtime.getRuntime().exec("L2TPPPPIPSecSTunnel.sh "+leftAdrl2tppppipsecSField.getText()+" "+ipStartl2tppppipsecSField.getText()+" "+ipEndl2tppppipsecSField.getText()+" "+serverIPAdrl2tppppipsecSField.getText()+" "+usernamel2tppppipsecSField.getText()+" "+passwordl2tppppipsecSField.getText());
            stopl2tppppipsecSButton.setDisable(false);
            l2tppppipsecserverpppFields.setDisable(true);
            l2tppppipsecserveripsecFields.setDisable(true);
            vpnprotocolField.setDisable(true);
            previousButton.setDisable(true);
            configurationprogressBar.setVisible(true);
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Approved.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Approved");
            tray.setMessage("Configuration of L2TP/PPP/IPSec Server with 'Tunnel' mode is done successfuly.\n\n"
                    + "Server IP : "+leftAdrl2tppppipsecSField.getText()+"\n"
                    + "Username: "+usernamel2tppppipsecSField.getText()+"\n"
                    + "Password: "+passwordl2tppppipsecSField.getText());
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(2));
            T1 = true;
            l2tppppipsecS = true;
            nextButton.setDisable(false);
            tunnell2tppppipsecserverString = tunnell2tppppipsecSField.getText();
            transportl2tppppipsecserverString = transportl2tppppipsecSField.getText();
            transportl2tppppipsecserver = transportl2tppppipsecSField.isSelected();
            tunnell2tppppipsecserver = tunnell2tppppipsecSField.isSelected();
            serveripl2tppppipsecserver = leftAdrl2tppppipsecSField.getText();
            usernamel2tppppipsecserver = usernamel2tppppipsecSField.getText();
            passwordl2tppppipsecserver = passwordl2tppppipsecSField.getText();
        }
    }
    
    @FXML
    private void validatepptpSConfiguration(ActionEvent event) throws Throwable {
        if(localIppptpSField.getText() == null || localIppptpSField.getText().trim().isEmpty())
        {
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Warning");
            tray.setMessage("The «VPN Sever Address» field is Empty.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.showAndDismiss(Duration.seconds(2));
            this.finalize();
        }else if(remoteIppptpSField.getText() == null || remoteIppptpSField.getText().trim().isEmpty()){
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Warning");
            tray.setMessage("The «Client Addresses» field is Empty.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.showAndDismiss(Duration.seconds(2));
            this.finalize();
        }else if(networkpptpSField.getText() == null || networkpptpSField.getText().trim().isEmpty()){
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Warning");
            tray.setMessage("The «VPN Network» field is Empty.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.showAndDismiss(Duration.seconds(2));
            this.finalize();
        }else if(userNamepptpSField.getText() == null || userNamepptpSField.getText().trim().isEmpty()){
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Warning");
            tray.setMessage("The «Username» field is Empty.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.showAndDismiss(Duration.seconds(2));
            this.finalize();
        }else if(passwordpptpSField.getText() == null || passwordpptpSField.getText().trim().isEmpty()){
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Warning");
            tray.setMessage("The «Password» field is Empty.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.showAndDismiss(Duration.seconds(2));
            this.finalize();
        }
        
        if(!localIppptpSField.getText().trim().isEmpty() && !remoteIppptpSField.getText().trim().isEmpty() && !networkpptpSField.getText().trim().isEmpty() && !userNamepptpSField.getText().trim().isEmpty() && !passwordpptpSField.getText().trim().isEmpty()){
            try {
                String result ="";
                p = Runtime.getRuntime().exec("InterfaceName.sh");
                InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                BufferedReader br=new BufferedReader(ipsr);
                String ligne;
                while ((ligne=br.readLine())!=null)
                {
                    System.out.println(ligne);
                    result += ligne;
                    p = Runtime.getRuntime().exec("Wireshark.sh "+ligne+" pptp ppp");
                    validatepptpSButton.setDisable(true);
                    p.waitFor(5, TimeUnit.SECONDS);
                    p = Runtime.getRuntime().exec("PPTPServer.sh "+localIppptpSField.getText()+" "+remoteIppptpSField.getText()+" "+userNamepptpSField.getText()+" "+passwordpptpSField.getText()+" "+networkpptpSField.getText()+" "+ligne);
                }
                pptpserverfields.setDisable(true);
                vpnprotocolField.setDisable(true);
                previousButton.setDisable(true);
                stoppptpSButton.setDisable(false);
                configurationprogressBar.setVisible(true);
                Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Approved.png"));
                TrayNotification tray = new TrayNotification();
                tray.setTitle("Approved");
                tray.setMessage("Configuration of PPTP Server is done successfuly.\n\n"
                        + "Server IP : "+localIppptpSField.getText()+"\n"
                        + "Username: "+userNamepptpSField.getText()+"\n"
                        + "Password: "+passwordpptpSField.getText());
                tray.setRectangleFill(Paint.valueOf("#2A9A84"));
                tray.setImage(approved);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(Duration.seconds(2));
            } catch (IOException ex) {
                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            T1 = true;
            pptpS = true;
            nextButton.setDisable(false);
            
            serverippptpserver = localIppptpSField.getText();
            clientpoolpptpserver = remoteIppptpSField.getText();
            vpnnetworkpptpserver = networkpptpSField.getText();
            usernamepptpserver = userNamepptpSField.getText();
            passwordpptpserver = passwordpptpSField.getText();
        }
    }

    @FXML
    private void validatepptpCConfiguration(ActionEvent event) throws Throwable {
        try {
            String result ="";
            p = Runtime.getRuntime().exec("InterfaceName.sh");
            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
            BufferedReader br=new BufferedReader(ipsr);
            String ligne;
            while ((ligne=br.readLine())!=null)
            {
                System.out.println(ligne);
                result += ligne;
                p = Runtime.getRuntime().exec("Wireshark.sh "+ligne+" pptp ppp");
                validatepptpCButton.setDisable(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.waitFor(5, TimeUnit.SECONDS);
        p = Runtime.getRuntime().exec("PPTPClient.sh "+serveradrpptpCField.getText()+" "+usernamepptpCField.getText()+" "+passwordpptpCField.getText());
        pptpclientfields.setDisable(true);
        stoppptpCButton.setDisable(false);
        previousButton.setDisable(true);
        vpnprotocolField.setDisable(true);
        configurationprogressBar.setVisible(true);
        Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Approved.png"));
        TrayNotification tray = new TrayNotification();
        tray.setTitle("Approved");
        tray.setMessage("Configuration of PPTP Client is done successfuly.");
        tray.setRectangleFill(Paint.valueOf("#2A9A84"));
        tray.setImage(approved);
        tray.setAnimationType(AnimationType.POPUP);
        tray.showAndDismiss(Duration.seconds(2));
        T1 = true;
        pptpC = true;
        nextButton.setDisable(false);
        userButton.setDisable(false);
        serverippptpclient = serveradrpptpCField.getText();
        usernamepptpclient = usernamepptpCField.getText();
        passwordpptpclient = passwordpptpCField.getText();
    }
    
    @FXML
    private void validategreSConfiguration(ActionEvent event) throws IOException, Throwable {
        if(localIPAdrgreSField.getText() == null || localIPAdrgreSField.getText().trim().isEmpty())
        {
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Warning");
            tray.setMessage("The «Local IP Address» field is Empty.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.showAndDismiss(Duration.seconds(2));
            this.finalize();
        }else if(RemoteIPAdrgreSField.getText() == null || RemoteIPAdrgreSField.getText().trim().isEmpty()){
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Warning");
            tray.setMessage("The «Remote Public IP Address» field is Empty.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.showAndDismiss(Duration.seconds(2));
            this.finalize();
        }else if(remoteNetAdrgreSField.getText() == null || remoteNetAdrgreSField.getText().trim().isEmpty()){
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Warning");
            tray.setMessage("The «Remote Network Address» field is Empty.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.showAndDismiss(Duration.seconds(2));
            this.finalize();
        }else if(greIPAdrgreSField.getText() == null || greIPAdrgreSField.getText().trim().isEmpty()){
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Warning");
            tray.setMessage("The «GRE IP Address» field is Empty.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.showAndDismiss(Duration.seconds(2));
            this.finalize();
        }
        
        if(!localIPAdrgreSField.getText().trim().isEmpty() && !RemoteIPAdrgreSField.getText().trim().isEmpty() && !remoteNetAdrgreSField.getText().trim().isEmpty() && !greIPAdrgreSField.getText().trim().isEmpty()){
            try {
                try {
                    String result ="";
                    p = Runtime.getRuntime().exec("InterfaceName.sh");
                    InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                    BufferedReader br=new BufferedReader(ipsr);
                    String ligne;
                    while ((ligne=br.readLine())!=null)
                    {
                        System.out.println(ligne);
                        result += ligne;
                        p = Runtime.getRuntime().exec("Wireshark.sh "+ligne+" gre");
                        validategreSButton.setDisable(true);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                p.waitFor(5, TimeUnit.SECONDS);
                p = Runtime.getRuntime().exec("GRETunnelS.sh "+greIPAdrgreSField.getText()+" "+RemoteIPAdrgreSField.getText()+" "+localIPAdrgreSField.getText()+" "+remoteNetAdrgreSField.getText());
                greserverFields.setDisable(true);
                stopgreSButton.setDisable(false);
                vpnprotocolField.setDisable(true);
                previousButton.setDisable(true);
                configurationprogressBar.setVisible(true);
                Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Approved.png"));
                TrayNotification tray = new TrayNotification();
                tray.setTitle("Approved");
                tray.setMessage("Configuration of GRE Server is done successfuly.\n\n"
                        + "Local IP : "+localIPAdrgreSField.getText()+"\n"
                        + "GRE IP Addr: "+greIPAdrgreSField.getText());
                tray.setRectangleFill(Paint.valueOf("#2A9A84"));
                tray.setImage(approved);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(Duration.seconds(2));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            T1 = true;
            greS = true;
            nextButton.setDisable(false);
            
            localipgreserver = localIPAdrgreSField.getText();
            remoteipgreserver = RemoteIPAdrgreSField.getText();
            remotenetworkgreserver = remoteNetAdrgreSField.getText();
            greipgreserver = greIPAdrgreSField.getText();
        }
    }
    
    @FXML
    private void validatel2tppppipsecCConfiguration(ActionEvent event) throws Throwable {
        if(transportl2tppppipsecCField.isSelected()){
            try {
                String result ="";
                p = Runtime.getRuntime().exec("InterfaceName.sh");
                p1 = Runtime.getRuntime().exec("GWYIPAddress.sh");
                InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                InputStreamReader ipsr1=new InputStreamReader(p1.getInputStream());
                BufferedReader br=new BufferedReader(ipsr);
                BufferedReader br1=new BufferedReader(ipsr1);
                String ligne;
                while ((ligne=br.readLine())!=null)
                {
                    System.out.println(ligne);
                    System.out.println(br1.readLine());
                    result += ligne;
                    p = Runtime.getRuntime().exec("Wireshark.sh "+ligne+" udp.dstport==1701 udp.dstport==4500 udp.dstport==500 esp ppp");
                    validatel2tppppipsecCButton.setDisable(true);
                    p.waitFor(5, TimeUnit.SECONDS);
                    p = Runtime.getRuntime().exec("L2TPPPPIPSecCTransport.sh "+leftAdrl2tppppipsecCField.getText()+" "+rightAdrl2tppppipsecCField.getText()+" "+usernamel2tppppipsecCField.getText()+" "+passwordl2tppppipsecCField.getText()+" "+br1.readLine()+" "+ligne);
                }
                stopl2tppppipsecCButton.setDisable(false);
                userButton.setDisable(false);
                l2tppppipsecclientpppFields.setDisable(true);
                //l2tppppipsecclientipsecFields.setDisable(true);
                l2tppppipsecclientfields.setDisable(true);
                previousButton.setDisable(true);
                configurationprogressBar.setVisible(true);
                vpnprotocolField.setDisable(true);
                T1 = true;
                l2tppppipsecC = true;
                nextButton.setDisable(false);
                transportl2tppppipsecclientString = transportl2tppppipsecCField.getText();
                tunnell2tppppipsecclientString = tunnell2tppppipsecCField.getText();
                transportl2tppppipsecclient = transportl2tppppipsecCField.isSelected();
                tunnell2tppppipsecclient = tunnell2tppppipsecCField.isSelected();
                keylifetimel2tppppipsecclient = keyl2tppppipsecCField.getText();
                ikelifetimel2tppppipsecclient = ikel2tppppipsecCField.getText();
                localipl2tppppipsecclient = leftAdrl2tppppipsecCField.getText();
                serveripl2tppppipsecclient = rightAdrl2tppppipsecCField.getText();
                usernamel2tppppipsecclient = usernamel2tppppipsecCField.getText();
                passwordl2tppppipsecclient = passwordl2tppppipsecCField.getText();
            } catch (IOException ex) {
                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(tunnell2tppppipsecCField.isSelected()){
            try {
                String result ="";
                p = Runtime.getRuntime().exec("InterfaceName.sh");
                p1 = Runtime.getRuntime().exec("GWYIPAddress.sh");
                InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                InputStreamReader ipsr1=new InputStreamReader(p1.getInputStream());
                BufferedReader br=new BufferedReader(ipsr);
                BufferedReader br1=new BufferedReader(ipsr1);
                String ligne;
                while ((ligne=br.readLine())!=null)
                {
                    System.out.println(ligne);
                    System.out.println(br1.readLine());
                    result += ligne;
                    p = Runtime.getRuntime().exec("Wireshark.sh "+ligne+" udp.dstport==1701 udp.dstport==4500 udp.dstport==500 esp ppp");
                    validatel2tppppipsecCButton.setDisable(true);
                    p.waitFor(5, TimeUnit.SECONDS);
                    p = Runtime.getRuntime().exec("L2TPPPPIPSecCTunnel.sh "+leftAdrl2tppppipsecCField.getText()+" "+rightAdrl2tppppipsecCField.getText()+" "+usernamel2tppppipsecCField.getText()+" "+passwordl2tppppipsecCField.getText());
                }
                userButton.setDisable(false);
                stopl2tppppipsecCButton.setDisable(false);
                l2tppppipsecclientpppFields.setDisable(true);
                l2tppppipsecclientfields.setDisable(true);
                previousButton.setDisable(true);
                configurationprogressBar.setVisible(true);
                vpnprotocolField.setDisable(true);
                T1 = true;
                l2tppppipsecC = true;
                nextButton.setDisable(false);
                transportl2tppppipsecclientString = transportl2tppppipsecCField.getText();
                tunnell2tppppipsecclientString = tunnell2tppppipsecCField.getText();
                transportl2tppppipsecclient = transportl2tppppipsecCField.isSelected();
                tunnell2tppppipsecclient = tunnell2tppppipsecCField.isSelected();
                keylifetimel2tppppipsecclient = keyl2tppppipsecCField.getText();
                ikelifetimel2tppppipsecclient = ikel2tppppipsecCField.getText();
                localipl2tppppipsecclient = leftAdrl2tppppipsecCField.getText();
                serveripl2tppppipsecclient = rightAdrl2tppppipsecCField.getText();
                usernamel2tppppipsecclient = usernamel2tppppipsecCField.getText();
                passwordl2tppppipsecclient = passwordl2tppppipsecCField.getText();
            } catch (IOException ex) {
                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void addUserPPPoE(ActionEvent event) throws IOException {
        try {
            FileWriter pppoe = new FileWriter("/etc/ppp/chap-secrets",true);
            BufferedWriter pppoeuser = new BufferedWriter(pppoe);
            pppoeuser.write("\n\""+usernamepppoepppipSField.getText()+"\" *   \""+passwordpppoepppipSField.getText()+"\"    *");
            pppoeuser.close();
            alert.setTitle("Warning");
            alert.setHeaderText("Look, a Warning Dialog");
            alert.setContentText("User was added successfully");
            alert.showAndWait();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    @FXML
    private void transportl2tpipsecSFunction(ActionEvent event) {
        if(transportl2tpipsecSField.isSelected()){
            keyl2tpipsecSLabel.setDisable(true);
            ikel2tpipsecSLabel.setDisable(true);
            keyexl2tpipsecSLabel.setDisable(true);
            leftAdrl2tpipsecSLabel.setDisable(false);
            leftsubnetl2tpipsecSLabel.setDisable(true);
            rightAdrl2tpipsecSLabel.setDisable(true);
            rightsubnetl2tpipsecSLabel.setDisable(true);
            keyl2tpipsecSField.setDisable(true);
            ikel2tpipsecSField.setDisable(true);
            keyexl2tpipsecSField.setDisable(true);
            leftAdrl2tpipsecSField.setDisable(false);
            leftsubnetl2tpipsecSField.setDisable(true);
            rightAdrl2tpipsecSField.setDisable(true);
            rightsubnetl2tpipsecSField.setDisable(true);
        }
    }

    @FXML
    private void tunnell2tpipsecSFunction(ActionEvent event) {
        if(tunnell2tpipsecSField.isSelected() && B==true){
            keyl2tpipsecSLabel.setDisable(false);
            ikel2tpipsecSLabel.setDisable(false);
            keyexl2tpipsecSLabel.setDisable(false);
            leftAdrl2tpipsecSLabel.setDisable(false);
            leftsubnetl2tpipsecSLabel.setDisable(false);
            rightAdrl2tpipsecSLabel.setDisable(false);
            rightsubnetl2tpipsecSLabel.setDisable(false);
            keyl2tpipsecSField.setDisable(false);
            ikel2tpipsecSField.setDisable(false);
            keyexl2tpipsecSField.setDisable(false);
            leftAdrl2tpipsecSField.setDisable(false);
            leftsubnetl2tpipsecSField.setDisable(false);
            rightAdrl2tpipsecSField.setDisable(false);
            rightsubnetl2tpipsecSField.setDisable(false);
            rightAdrl2tpipsecSField.setText("%any");
            leftsubnetl2tpipsecSField.setText("0.0.0.0/0");
            rightsubnetl2tpipsecSField.setText("0.0.0.0/0");
            alert2.setTitle("Confirmation Dialog");
            alert2.setHeaderText("Choose your Configuration");
            alert2.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
            result2 = alert2.showAndWait();
            
        }else if(tunnell2tpipsecSField.isSelected() && A==true){
            keyl2tpipsecSLabel.setDisable(false);
            ikel2tpipsecSLabel.setDisable(false);
            keyexl2tpipsecSLabel.setDisable(false);
            leftAdrl2tpipsecSLabel.setDisable(false);
            leftsubnetl2tpipsecSLabel.setDisable(false);
            rightAdrl2tpipsecSLabel.setDisable(false);
            rightsubnetl2tpipsecSLabel.setDisable(false);
            keyl2tpipsecSField.setDisable(false);
            ikel2tpipsecSField.setDisable(false);
            keyexl2tpipsecSField.setDisable(false);
            leftAdrl2tpipsecSField.setDisable(false);
            leftsubnetl2tpipsecSField.setDisable(false);
            rightAdrl2tpipsecSField.setDisable(false);
            rightsubnetl2tpipsecSField.setDisable(false);
            rightAdrl2tpipsecSField.setText("");
            leftsubnetl2tpipsecSField.setText("0.0.0.0/0");
            rightsubnetl2tpipsecSField.setText("0.0.0.0/0");
        }
    }

    @FXML
    private void transportl2tppppipsecSFunction(ActionEvent event) {
    }

    @FXML
    private void tunnell2tppppipsecSFunction(ActionEvent event) {
    }

    @FXML
    private void stopl2tpipsecSConfiguration(ActionEvent event) throws IOException {
        try {
            p = Runtime.getRuntime().exec("L2TPIPSecServerStop.sh");
            l2tpipsecserverFields.setDisable(false);
            vpnprotocolField.setDisable(false);
            stopl2tpipsecSButton.setDisable(true);
            validatel2tpipsecSButton.setDisable(false);
            previousButton.setDisable(false);
            configurationprogressBar.setVisible(false);
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Stop.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Stopped");
            tray.setMessage("L2TP/IPSec Server is stopped successfuly.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(2));
            l2tpipsecS = false;
            nextButton.setDisable(true);
        } catch (IOException ex) {
            Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void stopl2tppppipsecSConfiguration(ActionEvent event) throws IOException {
        try {
            p = Runtime.getRuntime().exec("L2TPIPSecServerStop.sh");
            stopl2tppppipsecSButton.setDisable(true);
            validatel2tppppipsecSButton.setDisable(false);
            l2tppppipsecserverpppFields.setDisable(false);
            l2tppppipsecserveripsecFields.setDisable(false);
            validatel2tpipsecSButton.setDisable(false);
            previousButton.setDisable(false);
            configurationprogressBar.setVisible(false);
            vpnprotocolField.setDisable(false);
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Stop.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Stopped");
            tray.setMessage("L2TP/PPP/IPSec Server is stopped successfuly.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(2));
            l2tppppipsecS = false;
            nextButton.setDisable(true);
        } catch (IOException ex) {
            Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void stopl2tpipsecCConfiguration(ActionEvent event) throws IOException {
        try {
            String result ="";
            p = Runtime.getRuntime().exec("InterfaceName.sh");
            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
            BufferedReader br=new BufferedReader(ipsr);
            String ligne;
            while ((ligne=br.readLine())!=null)
            {
                System.out.println(ligne);
                result += ligne;
                p = Runtime.getRuntime().exec("L2TPIPSecClientStop.sh "+ligne);
            }
            stopl2tpipsecCButton.setDisable(true);
            validatel2tpipsecCButton.setDisable(false);
            l2tpipsecclientFields.setDisable(false);
            validatel2tpipsecSButton.setDisable(false);
            previousButton.setDisable(false);
            configurationprogressBar.setVisible(false);
            vpnprotocolField.setDisable(false);
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Stop.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Stopped");
            tray.setMessage("L2TP/IPSec Client is stopped successfuly.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(2));
            l2tpipsecC = false;
            nextButton.setDisable(true);
            userButton.setDisable(true);
        } catch (IOException ex) {
            Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void stopl2tppppipsecCConfiguration(ActionEvent event) throws IOException {
        try {
            String result ="";
            p = Runtime.getRuntime().exec("InterfaceName.sh");
            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
            BufferedReader br=new BufferedReader(ipsr);
            String ligne;
            while ((ligne=br.readLine())!=null)
            {
                System.out.println(ligne);
                result += ligne;
                p = Runtime.getRuntime().exec("L2TPIPSecClientStop.sh "+ligne);
            }
            stopl2tppppipsecCButton.setDisable(true);
            validatel2tppppipsecCButton.setDisable(false);
            l2tppppipsecclientpppFields.setDisable(false);
            l2tppppipsecclientfields.setDisable(false);
            validatel2tpipsecSButton.setDisable(false);
            previousButton.setDisable(false);
            configurationprogressBar.setVisible(false);
            vpnprotocolField.setDisable(false);
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Stop.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Stopped");
            tray.setMessage("L2TP/PPP/IPSec Client is stopped successfuly.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(2));
            l2tppppipsecC = false;
            nextButton.setDisable(true);
            userButton.setDisable(true);
        } catch (IOException ex) {
            Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clientSideFunction(ActionEvent event) {
        if(clientSideL2TPIPSecButton.isSelected()){
            tunnell2tpipsecSField.setDisable(false);
            tunnell2tpipsecSField.setSelected(false);
            transportl2tpipsecSField.setDisable(true);
            transportl2tpipsecSField.setSelected(false);
            A=true;
        }
    }

    @FXML
    private void wanSideFunction(ActionEvent event) {
        if(wanSideL2TPIPSecButton.isSelected()){
            transportl2tpipsecSField.setDisable(false);
            tunnell2tpipsecSField.setDisable(false);
            tunnell2tpipsecSField.setSelected(false);
            transportl2tpipsecSField.setSelected(false);
            B=true;
        }
    }

    @FXML
    private void stoppptpSConfiguration(ActionEvent event) {
        try {
            String result ="";
            p = Runtime.getRuntime().exec("InterfaceName.sh");
            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
            BufferedReader br=new BufferedReader(ipsr);
            String ligne;
            while ((ligne=br.readLine())!=null)
            {
                System.out.println(ligne);
                result += ligne;
                p = Runtime.getRuntime().exec("PPTPServerStop.sh "+ligne);
            }
            pptpserverfields.setDisable(false);
            vpnprotocolField.setDisable(false);
            previousButton.setDisable(false);
            validatepptpSButton.setDisable(false);
            stoppptpSButton.setDisable(true);
            configurationprogressBar.setVisible(false);
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Stop.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Stopped");
            tray.setMessage("PPTP Server is stopped successfuly.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(2));
            pptpS = false;
            nextButton.setDisable(true);
        } catch (IOException ex) {
            Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void stoppptpCConfiguration(ActionEvent event) {
        try {
            String result ="";
            p = Runtime.getRuntime().exec("InterfaceName.sh");
            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
            BufferedReader br=new BufferedReader(ipsr);
            String ligne;
            while ((ligne=br.readLine())!=null)
            {
                System.out.println(ligne);
                result += ligne;
                p = Runtime.getRuntime().exec("PPTPClientStop.sh "+ligne);
            }
            pptpclientfields.setDisable(false);
            stoppptpCButton.setDisable(true);
            validatepptpCButton.setDisable(false);
            previousButton.setDisable(false);
            vpnprotocolField.setDisable(false);
            configurationprogressBar.setVisible(false);
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Stop.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Stopped");
            tray.setMessage("PPTP Client is stopped successfuly.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(2));
            pptpC = false;
            nextButton.setDisable(true);
            userButton.setDisable(true);
        } catch (IOException ex) {
            Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void stopgreSConfiguration(ActionEvent event) {
        try {
            p = Runtime.getRuntime().exec("GREServerStop.sh");
            greserverFields.setDisable(false);
            validategreSButton.setDisable(false);
            stopgreSButton.setDisable(true);
            vpnprotocolField.setDisable(false);
            previousButton.setDisable(false);
            configurationprogressBar.setVisible(false);
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Stop.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Stopped");
            tray.setMessage("GRE Server is stopped successfuly.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(2));
            greS = false;
            nextButton.setDisable(true);
        } catch (IOException ex) {
            Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void aboutAction(ActionEvent event) {
        if(Q.equals("LIS")){
            mystackane.setVisible(true);
            JFXDialogLayout content = new JFXDialogLayout();
            content.setStyle("-fx-background-image: url(\"/vpnlite/img/backgroundApp.png\");");
            Text t1 = new Text();
            t1.setText("About L2TP/IPSec Server");
            t1.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 19));
            t1.setFill(Color.BLACK);
            content.setHeading(t1);
            Text t2 = new Text();
            t2.setText("シ\tFor L2TP/IPSec, you can use «Site-to-Site» or «Nomade-To-Site» configuration\n\n"
                    + "✗\tFor «Site-to-Site» Configuration:\n\n\t\t➺ You can use only «TUNNEL» Mode\n\n"
                    + "\t\t➺ Example:\n\t\t\tFrom Server (Select WAN Side + Tunnel Option) ⇌ From Client (Select Client Side + Tunnel Option)\n\n"
                    + "✗\tFor «Nomade-To-Site» Configuration:\n\n\t\t➺ You can use «TRANSPORT» or «TUNNEL» Modes\n\n"
                    + "\t\t➺ Example:\n\t\t\tFrom Server (Select WAN Side + Tunnel/Transport Option)\n\n"
                    + "✗\tFor «TUNNEL» Mode:\n\n\t\t➺ WAN Side: it is better to leave the default settings of «Local/Remote Network» and «Remote IP Address»\n\n\t\t➺ Client Side: it is better to leave the default settings of «Local/Remote Network»\n\n"
                    + "シ\tYou can use Wireshark filters:\n\n\t\t➺ Site-to-Site: « udp.dstport=4500 || udp.dstport==500 || esp »\n\n\t\t➺ Client-To-Site: « udp.dstport==1701 || udp.dstport=4500 || udp.dstport==500 || esp »\n\n\n\n"
                    + "➤\tNote:\n\n\t\t➺ You must enter your filter on Wireshark before starting VPN configuration");
            t2.setFont(Font.font ("Arial", 15));
            t2.setFill(Color.GRAY);
            content.setBody(t2);
            JFXDialog dialog = new JFXDialog(mystackane, content, JFXDialog.DialogTransition.CENTER);
            dialog.setOverlayClose(false);
            JFXButton button = new JFXButton("OK");
            button.setStyle("-fx-background-color: #8A8B8C; -fx-text-fill: black;");
            button.setButtonType(JFXButton.ButtonType.RAISED);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    mystackane.setVisible(false);
                    dialog.close();
                }
            });
            content.setActions(button);
            dialog.show();
        }else if(Q.equals("LPIS")){
            mystackane.setVisible(true);
            JFXDialogLayout content = new JFXDialogLayout();
            content.setStyle("-fx-background-image: url(\"/vpnlite/img/backgroundApp.png\");");
            Text t1 = new Text();
            t1.setText("About L2TP/PPP/IPSec Server");
            t1.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 19));
            t1.setFill(Color.BLACK);
            content.setHeading(t1);
            Text t2 = new Text();
            t2.setText("シ\tFor L2TP/PPP/IPSec, you can use only «Client-To-Server» configuration\n\n"
                    + "✗\tFor «Client-To-Server» Configuration:\n\n\t\t➺ You can use «TRANSPORT» or «TUNNEL» Modes\n\n"
                    + "\t\t➺ Example:\n\t\t\tFrom Server (Select Tunnel/Transport Option)\n\n"
                    + "✗\tServer IP Address:\n\n\t\t➺ The Server IP Address for L2TP/PPP/IPSec configuration\n\n"
                    + "✗\tUsername:\n\n\t\t➺ Username to be used on Client Side\n\n"
                    + "✗\tPassword:\n\n\t\t➺ Password to be used on Client Side\n\n"
                    + "シ\tYou can use Wireshark filters:\n\n\t\t➺ Client-To-Site: « udp.dstport==1701 || udp.dstport=4500 || udp.dstport==500 || esp || ppp»\n\n\n\n"
                    + "➤\tNote:\n\n\t\t➺ You must enter your filter on Wireshark before starting VPN configuration");
            t2.setFont(Font.font ("Arial", 15));
            t2.setFill(Color.GRAY);
            content.setBody(t2);
            JFXDialog dialog = new JFXDialog(mystackane, content, JFXDialog.DialogTransition.CENTER);
            dialog.setOverlayClose(false);
            JFXButton button = new JFXButton("OK");
            button.setStyle("-fx-background-color: #8A8B8C; -fx-text-fill: black;");
            button.setButtonType(JFXButton.ButtonType.RAISED);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    mystackane.setVisible(false);
                    dialog.close();
                }
            });
            content.setActions(button);
            dialog.show();
        }else if(Q.equals("PPIPS")){
            mystackane.setVisible(true);
            JFXDialogLayout content = new JFXDialogLayout();
            content.setStyle("-fx-background-image: url(\"/vpnlite/img/backgroundApp.png\");");
            Text t1 = new Text();
            t1.setText("About PPPoE/PPP/IPSec Server");
            t1.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 19));
            t1.setFill(Color.BLACK);
            content.setHeading(t1);
            Text t2 = new Text();
            t2.setText("シ\tFor PPPoE/PPP/IPSec, you can use only «Client-To-Site» configuration\n\n"
                    + "✗\tServer IP: You can choose your own IP with your choice\n\n"
                    + "✗\tDNS 1 & DNS 2: You can choose your own DNS but it's preferable to use the defined DNS\n\n"
                    + "✗\tPAP or CHAP: You MUST choose one of them for your configuration\n\n"
                    + "✗\tFor «Nomade-To-Site» Configuration:\n\n\t\t➺ You can use «TRANSPORT» or «TUNNEL» Modes\n\n"
                    + "\t\t➺ Example:\n\t\t\tFrom Server (Select WAN Side + Tunnel/Transport Option)\n\n"
                    + "✗\tFor «TUNNEL» Mode:\n\n\t\t➺ WAN Side: it is better to leave the default settings of «Local/Remote Network» and «Remote IP Address»\n\n\t\t➺ Client Side: it is better to leave the default settings of «Local/Remote Network»\n\n\n\n"
                    + "シ\tYou can use Wireshark filters:\n\n\t\t➺ Client-to-Site: « pppoe || ppp »\n\n\n\n"
                    + "➤\tNote:\n\n\t\t➺ You must enter your filter on Wireshark before starting VPN configuration");
            t2.setFont(Font.font ("Arial", 15));
            t2.setFill(Color.GRAY);
            content.setBody(t2);
            JFXDialog dialog = new JFXDialog(mystackane, content, JFXDialog.DialogTransition.CENTER);
            dialog.setOverlayClose(false);
            JFXButton button = new JFXButton("OK");
            button.setStyle("-fx-background-color: #8A8B8C; -fx-text-fill: black;");
            button.setButtonType(JFXButton.ButtonType.RAISED);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    mystackane.setVisible(false);
                    dialog.close();
                }
            });
            content.setActions(button);
            dialog.show();
        }else if(Q.equals("PS")){
            mystackane.setVisible(true);
            JFXDialogLayout content = new JFXDialogLayout();
            content.setStyle("-fx-background-image: url(\"/vpnlite/img/backgroundApp.png\");");
            Text t1 = new Text();
            t1.setText("About PPTP Server");
            t1.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 19));
            t1.setFill(Color.BLACK);
            content.setHeading(t1);
            Text t2 = new Text();
            t2.setText("シ\tFor PPTP, you can use only «Client-To-Site» configuration\n\n"
                    + "➤\tVPN Server Address:\n\n\t\t➺ An IP from your network that is not used by any computer or the router\n\n"
                    + "➤\tClient Addresses:\n\n\t\t➺ The client IP range. Leave it blank will not work. It must be like a.b.c.xxx-yyy\n\n"
                    + "➤\tVPN Network:\n\n\t\t➺ This is used for iptables configuration - it must be like a.b.c.0/xx\n\n"
                    + "➤\tUsername:\n\n\t\t➺ Username to be used on Client Side\n\n"
                    + "➤\tPassword:\n\n\t\t➺ Password to be used on Client Side\n\n"
                    + "シ\tYou can use Wireshark filters:\n\n\t\t➺ Client-To-Site: « pptp || ppp »\n\n\n\n"
                    + "➤\tNote:\n\n\t\t➺ You must enter your filter on Wireshark before starting VPN configuration");
            t2.setFont(Font.font ("Arial", 15));
            t2.setFill(Color.GRAY);
            content.setBody(t2);
            JFXDialog dialog = new JFXDialog(mystackane, content, JFXDialog.DialogTransition.CENTER);
            dialog.setOverlayClose(false);
            JFXButton button = new JFXButton("OK");
            button.setStyle("-fx-background-color: #8A8B8C; -fx-text-fill: black;");
            button.setButtonType(JFXButton.ButtonType.RAISED);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    mystackane.setVisible(false);
                    dialog.close();
                }
            });
            content.setActions(button);
            dialog.show();
        }else if(Q.equals("GS")){
            mystackane.setVisible(true);
            JFXDialogLayout content = new JFXDialogLayout();
            content.setStyle("-fx-background-image: url(\"/vpnlite/img/backgroundApp.png\");");
            Text t1 = new Text();
            t1.setText("About GRE Server");
            t1.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 19));
            t1.setFill(Color.BLACK);
            content.setHeading(t1);
            Text t2 = new Text();
            t2.setText("シ\tFor GRE, you can use only «Site-To-Site» configuration\n\n"
                    + "➤\tLocal IP Address:\n\n\t\t➺ An IP from your network that is not used by any computer or the router\n\n"
                    + "➤\tRemote Public IP Address:\n\n\t\t➺ IP address of remote tunnel end.\n\n"
                    + "➤\tRemote Network Address:\n\n\t\t➺ The Network of remote tunnel end\n\n"
                    + "➤\tGRE IP Address:\n\n\t\t➺ You can set this field with your choice\n\n"
                    + "\t\t➺ The GRE IP Addresses of both servers must be in the same Network\n\n\n\n"
                    + "シ\tYou can use Wireshark filters:\n\n\t\t➺ Site-To-Site: « gre »\n\n\n\n"
                    + "➤\tNote:\n\n\t\t➺ You must enter your filter on Wireshark before starting VPN configuration");
            t2.setFont(Font.font ("Arial", 15));
            t2.setFill(Color.GRAY);
            content.setBody(t2);
            JFXDialog dialog = new JFXDialog(mystackane, content, JFXDialog.DialogTransition.CENTER);
            dialog.setOverlayClose(false);
            JFXButton button = new JFXButton("OK");
            button.setStyle("-fx-background-color: #8A8B8C; -fx-text-fill: black;");
            button.setButtonType(JFXButton.ButtonType.RAISED);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    mystackane.setVisible(false);
                    dialog.close();
                }
            });
            content.setActions(button);
            dialog.show();
        }else if(Q.equals("LIC")){
            mystackane.setVisible(true);
            JFXDialogLayout content = new JFXDialogLayout();
            content.setStyle("-fx-background-image: url(\"/vpnlite/img/backgroundApp.png\");");
            Text t1 = new Text();
            t1.setText("About L2TP/IPSec Client");
            t1.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 19));
            t1.setFill(Color.BLACK);
            content.setHeading(t1);
            Text t2 = new Text();
            t2.setText("シ\tFor L2TP/IPSec Client, you can use «Client-To-Server» configuration\n\n"
                    + "✗\tFor «Client-To-Server» Configuration:\n\n\t\t➺ You can use «TRANSPORT» or «TUNNEL» Modes\n\n"
                    + "\t\t➺ Example:\n\t\t\tFrom Client (Select Tunnel/Transport Option)\n\n"
                    + "➤\tKey Life:\n\n\t\t➺ Time when rekeying is initiated. Set to zero to disable.\n\n"
                    + "➤\tIKE Lifetime:\n\n\t\t➺ How long the keying channel of a connection should last before being renegotiated.\n\n"
                    + "➤\tLocal IP Address:\n\n\t\t➺ It's your LAN IP address. Use the default value of this field\n\n"
                    + "➤\tServer IP Address:\n\n\t\t➺ It's the Server IP Address which the configuration must be established\n\n"
                    + "シ\tYou can use Wireshark filters:\n\n\t\t➺ «Client-To-Site»: udp.dstport==1701 || udp.dstport=4500 || udp.dstport==500 || esp\n\n"
                    + "➤\tNote:\n\n\t\t➺ You must enter your filter on Wireshark before starting VPN configuration\n\n\t\t➺ For correct configuration, use the default settings");
            t2.setFont(Font.font ("Arial", 15));
            t2.setFill(Color.GRAY);
            content.setBody(t2);
            JFXDialog dialog = new JFXDialog(mystackane, content, JFXDialog.DialogTransition.CENTER);
            dialog.setOverlayClose(false);
            JFXButton button = new JFXButton("OK");
            button.setStyle("-fx-background-color: #8A8B8C; -fx-text-fill: black;");
            button.setButtonType(JFXButton.ButtonType.RAISED);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    mystackane.setVisible(false);
                    dialog.close();
                }
            });
            content.setActions(button);
            dialog.show();
        }else if(Q.equals("LPIC")){
            mystackane.setVisible(true);
            JFXDialogLayout content = new JFXDialogLayout();
            content.setStyle("-fx-background-image: url(\"/vpnlite/img/backgroundApp.png\");");
            Text t1 = new Text();
            t1.setText("About L2TP/PPP/IPSec Client");
            t1.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 19));
            t1.setFill(Color.BLACK);
            content.setHeading(t1);
            Text t2 = new Text();
            t2.setText("シ\tFor L2TP/PPP/IPSec Client, you can use «Client-To-Server» configuration\n\n"
                + "✗\tFor «Client-To-Server» Configuration: You can use «TRANSPORT» or «TUNNEL» Modes\n\n"
                + "\t\t➺ Example:\n\t\t\tFrom Client (Select Tunnel/Transport Option)\n\n"
                + "✗\tKey Life:\n\n\t\t➺ Time when rekeying is initiated. Set to zero to disable.\n\n"
                + "✗\tIKE Lifetime:\n\n\t\t➺ How long the keying channel of a connection should last before being renegotiated.\n\n"
                + "✗\tLocal IP Address:\n\n\t\t➺ It's your LAN IP address. Use the default value of this field\n\n"
                + "✗\tServer IP Address:\n\n\t\t➺ It's the Server IP Address which the configuration must be established\n\n"
                + "✗\tUsername & Password:\n\n\t\t➺ You have to use the same Username & Password configured on the Server\n\n"
                + "シ\tYou can use Wireshark filters:\n\n\t\t➺ «Client-To-Site»: udp.dstport==1701 || udp.dstport=4500 || udp.dstport==500 || esp || ppp\n\n"
                + "➤\tNote:\n\n\t\t➺ You must enter your filter on Wireshark before starting VPN configuration\n\n\t\t➺ For correct configuration, use the default settings");
            t2.setFont(Font.font ("Arial", 15));
            t2.setFill(Color.GRAY);
            content.setBody(t2);
            JFXDialog dialog = new JFXDialog(mystackane, content, JFXDialog.DialogTransition.CENTER);
            dialog.setOverlayClose(false);
            JFXButton button = new JFXButton("OK");
            button.setStyle("-fx-background-color: #8A8B8C; -fx-text-fill: black;");
            button.setButtonType(JFXButton.ButtonType.RAISED);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    mystackane.setVisible(false);
                    dialog.close();
                }
            });
            content.setActions(button);
            dialog.show();
        }else if(Q.equals("PPIC")){
            mystackane.setVisible(true);
            JFXDialogLayout content = new JFXDialogLayout();
            content.setStyle("-fx-background-image: url(\"/vpnlite/img/backgroundApp.png\");");
            Text t1 = new Text();
            t1.setText("About PPPoE/PPP/IP Client");
            t1.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 19));
            t1.setFill(Color.BLACK);
            content.setHeading(t1);
            Text t2 = new Text();
            t2.setText("シ\tFor PPPoE/PPP/IP, you can use only «Client-To-Site» configuration\n\n"
                    + "✗\tUsername & Password:\n\n\t\t➺ You have to use the same Username & Password configured on the Server\n\n"
                    + "✗\tPAP or CHAP: You MUST choose one of them for your configuration\n\n\n\n"
                    + "シ\tYou can use Wireshark filters:\n\n\t\t➺ Client-to-Site: « pppoe || ppp »\n\n\n\n"
                    + "➤\tNote:\n\n\t\t➺ You must enter your filter on Wireshark before starting VPN configuration");
            t2.setFont(Font.font ("Arial", 15));
            t2.setFill(Color.GRAY);
            content.setBody(t2);
            JFXDialog dialog = new JFXDialog(mystackane, content, JFXDialog.DialogTransition.CENTER);
            dialog.setOverlayClose(false);
            JFXButton button = new JFXButton("OK");
            button.setStyle("-fx-background-color: #8A8B8C; -fx-text-fill: black;");
            button.setButtonType(JFXButton.ButtonType.RAISED);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    mystackane.setVisible(false);
                    dialog.close();
                }
            });
            content.setActions(button);
            dialog.show();
        }else if(Q.equals("PPIPC")){
            mystackane.setVisible(true);
            JFXDialogLayout content = new JFXDialogLayout();
            content.setStyle("-fx-background-image: url(\"/vpnlite/img/backgroundApp.png\");");
            Text t1 = new Text();
            t1.setText("About PPPoE/PPP/IPSec Client");
            t1.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 19));
            t1.setFill(Color.BLACK);
            content.setHeading(t1);
            Text t2 = new Text();
            t2.setText("シ\tFor PPPoE/PPP/IPSec, you can use only «Client-To-Site» configuration\n\n"
                + "✗\tFor «Client-To-Server» Configuration:\n\n\t\t➺ You can use «TRANSPORT» or «TUNNEL» Modes\n\n"
                + "\t\t➺ Example:\n\t\t\tFrom Client (Select Tunnel/Transport Option)\n\n"
                + "✗\tKey Life:\n\n\t\t➺ Time when rekeying is initiated. Set to zero to disable.\n\n"
                + "✗\tIKE Lifetime:\n\n\t\t➺ How long the keying channel of a connection should last before being renegotiated.\n\n"
                + "✗\tServer IP Address:\n\n\t\t➺ It's the Server IP Address which the configuration must be established\n\n"
                + "✗\tUsername & Password:\n\n\t\t➺ You have to use the same Username & Password configured on the Server\n\n"
                + "✗\tPAP or CHAP: You MUST choose one of them for your configuration\n\n"
                + "シ\tYou can use Wireshark filters:\n\n\t\t➺ Client-to-Site: « pppoe || ppp || udp.dstport=4500 || udp.dstport==500 || esp»\n\n"
                + "➤\tNote:\n\n\t\t➺ You must enter your filter on Wireshark before starting VPN configuration");
            t2.setFont(Font.font ("Arial", 15));
            t2.setFill(Color.GRAY);
            content.setBody(t2);
            JFXDialog dialog = new JFXDialog(mystackane, content, JFXDialog.DialogTransition.CENTER);
            dialog.setOverlayClose(false);
            JFXButton button = new JFXButton("OK");
            button.setStyle("-fx-background-color: #8A8B8C; -fx-text-fill: black;");
            button.setButtonType(JFXButton.ButtonType.RAISED);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    mystackane.setVisible(false);
                    dialog.close();
                }
            });
            content.setActions(button);
            dialog.show();
        }else if(Q.equals("PC")){
            mystackane.setVisible(true);
            JFXDialogLayout content = new JFXDialogLayout();
            content.setStyle("-fx-background-image: url(\"/vpnlite/img/backgroundApp.png\");");
            Text t1 = new Text();
            t1.setText("About PPTP Client");
            t1.setFont(Font.font ("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 19));
            t1.setFill(Color.BLACK);
            content.setHeading(t1);
            Text t2 = new Text();
            t2.setText("シ\tFor PPTP, you can use only «Client-To-Site» configuration\n\n"
                    + "✗\tServer IP Address:\n\n\t\t➺ It's the Server IP Address which the configuration must be established\n\n"
                    + "✗\tUsername:\n\n\t\t➺ Username to be used on Client Side\n\n"
                    + "✗\tPassword:\n\n\t\t➺ Password to be used on Client Side\n\n\n\n"
                    + "シ\tYou can use Wireshark filters:\n\n\t\t➺ Client-To-Site: « pptp || ppp »\n\n\n\n"
                    + "➤\tNote:\n\n\t\t➺ You must enter your filter on Wireshark before starting VPN configuration");
            t2.setFont(Font.font ("Arial", 15));
            t2.setFill(Color.GRAY);
            content.setBody(t2);
            JFXDialog dialog = new JFXDialog(mystackane, content, JFXDialog.DialogTransition.CENTER);
            dialog.setOverlayClose(false);
            JFXButton button = new JFXButton("OK");
            button.setStyle("-fx-background-color: #8A8B8C; -fx-text-fill: black;");
            button.setButtonType(JFXButton.ButtonType.RAISED);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    mystackane.setVisible(false);
                    dialog.close();
                }
            });
            content.setActions(button);
            dialog.show();
        }
    }

    @FXML
    private void InstallationWindow(ActionEvent event) throws IOException {
        Stage stage = (Stage) InstallationButton.getScene().getWindow();
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
    private void stoppppoepppipCConfiguration(ActionEvent event) throws IOException {
        pppoepppipclientfields.setDisable(false);
        validatepppoepppipCButton.setDisable(false);
        stoppppoepppipCButton.setDisable(true);
        configurationprogressBar.setVisible(false);
        p = Runtime.getRuntime().exec("poff -a");
        pppoepppipC = false;
        nextButton.setDisable(true);
        userButton.setDisable(true);
    }

    @FXML
    private void validatepppoepppipCConfiguration(ActionEvent event) throws Throwable {
        if(!pappppoepppipCField.isSelected() && !chappppoepppipCField.isSelected())
        {
            alert.setTitle("Warning");
            alert.setHeaderText("Look, a Warning Dialog");
            alert.setContentText("You must select PAP/CHAP type");
            alert.showAndWait();
            this.finalize();
        }else if(usernamepppoepppipCField.getText() == null || usernamepppoepppipCField.getText().trim().isEmpty())
        {
            alert.setTitle("Warning");
            alert.setHeaderText("Look, a Warning Dialog");
            alert.setContentText("You must enter the Username");
            alert.showAndWait();
            this.finalize();
        }else if(passwordpppoepppipCField.getText() == null || passwordpppoepppipCField.getText().trim().isEmpty())
        {
            alert.setTitle("Warning");
            alert.setHeaderText("Look, a Warning Dialog");
            alert.setContentText("You must enter the Password");
            alert.showAndWait();
            this.finalize();
        }else {
            try {
                String result ="";
                p = Runtime.getRuntime().exec("InterfaceName.sh");
                InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                BufferedReader br=new BufferedReader(ipsr);
                String ligne;
                while ((ligne=br.readLine())!=null)
                {
                    System.out.println(ligne);
                    result += ligne;
                    p = Runtime.getRuntime().exec("Wireshark.sh eth0 pppoe ppp pppoed");
                    p.waitFor(5, TimeUnit.SECONDS);
                    p = Runtime.getRuntime().exec("PPPoEPPPIPClient.sh eth0 "+usernamepppoepppipCField.getText()+" "+passwordpppoepppipCField.getText());
                }
                pppoepppipclientfields.setDisable(true);
                validatepppoepppipCButton.setDisable(true);
                stoppppoepppipCButton.setDisable(false);
                configurationprogressBar.setVisible(true);
                Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Approved.png"));
                TrayNotification tray = new TrayNotification();
                tray.setTitle("Approved");
                tray.setMessage("Configuration of PPPoE/PPP/IP Client is done successfuly.");
                tray.setRectangleFill(Paint.valueOf("#2A9A84"));
                tray.setImage(approved);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(Duration.seconds(2));
                T1 = true;
                pppoepppipC = true;
                nextButton.setDisable(false);
                userButton.setDisable(false);
                usernamepppoepppipclient = usernamepppoepppipCField.getText();
                passwordpppoepppipclient = passwordpppoepppipCField.getText();
                pappppoepppipclientString = pappppoepppipCField.getText();
                chappppoepppipclientString = chappppoepppipCField.getText();
                pappppoepppipclient = pappppoepppipCField.isSelected();
                chappppoepppipclient = chappppoepppipCField.isSelected();
            } catch (IOException ex) {
                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void stoppppoepppipsecSConfiguration(ActionEvent event) {
        try {
            p = Runtime.getRuntime().exec("L2TPIPSecServerStop.sh");
            pppoepppipsecserverField1.setDisable(false);
            pppoepppipsecserverField2.setDisable(false);
            pppoepppipsecserverField3.setDisable(false);
            pppoepppipsecserverField4.setDisable(false);
            pppoepppipsecserverField5.setDisable(false);
            pppoepppipsecserverField6.setDisable(false);
            pppoepppipsecserverField7.setDisable(false);
            vpnprotocolField.setDisable(false);
            stoppppoepppipsecSButton.setDisable(true);
            previousButton.setDisable(false);
            configurationprogressBar.setVisible(false);
            validatepppoepppipsecSButton.setDisable(false);
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Stop.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Stopped");
            tray.setMessage("L2TP/IPSec Server is stopped successfuly.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(2));
            pppoepppipsecS = false;
            nextButton.setDisable(true);
        } catch (IOException ex) {
            Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void validatepppoepppipsecSConfiguration(ActionEvent event) throws InterruptedException {
        try {
            try {
                String result ="";
                p = Runtime.getRuntime().exec("InterfaceName.sh");
                InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                BufferedReader br=new BufferedReader(ipsr);
                String ligne;
                while ((ligne=br.readLine())!=null)
                {
                    System.out.println(ligne);
                    result += ligne;
                    p = Runtime.getRuntime().exec("Wireshark.sh "+ligne+" udp.dstport==4500 udp.dstport==500 esp");
                    validatepppoepppipsecSButton.setDisable(true);
                }
            } catch (IOException ex) {
                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            p.waitFor(5, TimeUnit.SECONDS);
            p = Runtime.getRuntime().exec("L2TPIPSecSTunnelS.sh "+leftAdrpppoepppipsecSField.getText()+" "+leftsubnetpppoepppipsecSField.getText()+" "+rightAdrpppoepppipsecSField.getText()+" "+rightsubnetpppoepppipsecSField.getText()+" "+keyexpppoepppipsecSField.getValue());
            pppoepppipsecserverField1.setDisable(true);
            pppoepppipsecserverField2.setDisable(true);
            pppoepppipsecserverField3.setDisable(true);
            pppoepppipsecserverField4.setDisable(true);
            pppoepppipsecserverField5.setDisable(true);
            pppoepppipsecserverField6.setDisable(true);
            pppoepppipsecserverField7.setDisable(true);
            vpnprotocolField.setDisable(true);
            stoppppoepppipsecSButton.setDisable(false);
            previousButton.setDisable(true);
            configurationprogressBar.setVisible(true);
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Approved.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Approved");
            tray.setMessage("Configuration of L2TP/IPSec Server is done successfuly.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(2));
            T1 = true;
            pppoepppipsecS = true;
            nextButton.setDisable(false);
            
            ikelifetimepppoepppipsecserver = keypppoepppipsecSField.getText();
            keylifetimepppoepppipsecserver = ikepppoepppipsecSField.getText();
            keyexchangepppoepppipsecserver = keyexpppoepppipsecSField.getValue();
            localippppoepppipsecserver = leftAdrpppoepppipsecSField.getText();
            localnetworkpppoepppipsecserver = leftsubnetpppoepppipsecSField.getText();
            remoteippppoepppipsecserver = rightAdrpppoepppipsecSField.getText();
            remotenetworkpppoepppipsecserver = rightsubnetpppoepppipsecSField.getText();
        } catch (IOException ex) {
            Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void stoppppoepppipsecCConfiguration(ActionEvent event) throws IOException {
        pppoepppipclientfields1.setDisable(false);
        validatepppoepppipsecCButton.setDisable(false);
        stoppppoepppipsecCButton.setDisable(true);
        pppoepppipsecC1.setDisable(true);
        pppoepppipsecC2.setDisable(true);
        pppoepppipsecC3.setDisable(true);
        pppoepppipsecC4.setDisable(true);
        pppoepppipsecC5.setDisable(true);
        pppoepppipsecC6.setDisable(true);
        pppoepppipsecC7.setDisable(true);
        pppoepppipsecC8.setDisable(true);
        //stoppppoepppipsecCButtonIPSec.setDisable(true);
        //validatepppoepppipsecCButtonIPSec.setDisable(true);
        p = Runtime.getRuntime().exec("poff -a");
        pppoepppipsecC = false;
        nextButton.setDisable(true);
        userButton.setDisable(true);
    }

    @FXML
    private void validatepppoepppipsecCConfiguration(ActionEvent event) throws InterruptedException, Throwable {
        if(!pappppoepppipsecCField.isSelected() && !chappppoepppipsecCField.isSelected())
        {
            alert.setTitle("Warning");
            alert.setHeaderText("Look, a Warning Dialog");
            alert.setContentText("You must select PAP/CHAP type");
            alert.showAndWait();
            this.finalize();
        }else if(usernamepppoepppipsecCField.getText() == null || usernamepppoepppipsecCField.getText().trim().isEmpty())
        {
            alert.setTitle("Warning");
            alert.setHeaderText("Look, a Warning Dialog");
            alert.setContentText("You must enter the Username");
            alert.showAndWait();
            this.finalize();
        }else if(passwordpppoepppipsecCField.getText() == null || passwordpppoepppipsecCField.getText().trim().isEmpty())
        {
            alert.setTitle("Warning");
            alert.setHeaderText("Look, a Warning Dialog");
            alert.setContentText("You must enter the Password");
            alert.showAndWait();
            this.finalize();
        }else {
            try {
                String result ="";
                p = Runtime.getRuntime().exec("InterfaceName.sh");
                InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                BufferedReader br=new BufferedReader(ipsr);
                String ligne;
                while ((ligne=br.readLine())!=null)
                {
                    System.out.println(ligne);
                    result += ligne;
                    p = Runtime.getRuntime().exec("Wireshark.sh "+ligne+" udp.dstport==4500 udp.dstport==500 esp pppoe ppp pppoed");
                    p.waitFor(5, TimeUnit.SECONDS);
                    p = Runtime.getRuntime().exec("PPPoEPPPIPClient.sh eth0 "+usernamepppoepppipsecCField.getText()+" "+passwordpppoepppipsecCField.getText());
                    InputStreamReader ipsr1=new InputStreamReader(p.getInputStream());
                    BufferedReader br1=new BufferedReader(ipsr1);
                    System.out.println(br1.readLine());
                    leftAdrpppoepppipsecCField.setText(br1.readLine());
                }
                pppoepppipclientfields1.setDisable(true);
                validatepppoepppipsecCButton.setDisable(true);
                stoppppoepppipsecCButton.setDisable(false);
                pppoepppipsecC1.setDisable(false);
                pppoepppipsecC2.setDisable(false);
                pppoepppipsecC3.setDisable(false);
                pppoepppipsecC4.setDisable(false);
                pppoepppipsecC5.setDisable(false);
                pppoepppipsecC6.setDisable(false);
                pppoepppipsecC7.setDisable(false);
                pppoepppipsecC8.setDisable(false);
                //stoppppoepppipsecCButtonIPSec.setDisable(true);
                //validatepppoepppipsecCButtonIPSec.setDisable(false);
                Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Approved.png"));
                TrayNotification tray = new TrayNotification();
                tray.setTitle("Approved");
                tray.setMessage("Configuration of PPPoE/PPP/IP Client is done successfuly.");
                tray.setRectangleFill(Paint.valueOf("#2A9A84"));
                tray.setImage(approved);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(Duration.seconds(2));
                T1 = true;
                pppoepppipsecC = true;
                nextButton.setDisable(false);
                userButton.setDisable(false);
                
            } catch (IOException ex) {
                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void stoppppoepppipsecCConfigurationIPSec(ActionEvent event) {
        try {
            p = Runtime.getRuntime().exec("L2TPIPSecServerStop.sh");
            validatepppoepppipsecCButtonIPSec.setDisable(false);
            pppoepppipsecC1.setDisable(false);
            pppoepppipsecC2.setDisable(false);
            pppoepppipsecC3.setDisable(false);
            pppoepppipsecC4.setDisable(false);
            pppoepppipsecC5.setDisable(false);
            pppoepppipsecC6.setDisable(false);
            pppoepppipsecC7.setDisable(false);
            stoppppoepppipsecCButton.setDisable(false);
            vpnprotocolField.setDisable(false);
            stoppppoepppipsecCButtonIPSec.setDisable(true);
            previousButton.setDisable(false);
            configurationprogressBar.setVisible(false);
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Stop.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Stopped");
            tray.setMessage("PPPoE/PPP/IPSec Client is stopped successfuly.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(2));
            pppoepppipsecS = false;
            nextButton.setDisable(true);
            userButton.setDisable(true);
        } catch (IOException ex) {
            Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void validatepppoepppipsecCConfigurationIPSec(ActionEvent event) throws InterruptedException {
        try {
            try {
                String result ="";
                p = Runtime.getRuntime().exec("InterfaceName.sh");
                InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                BufferedReader br=new BufferedReader(ipsr);
                String ligne;
                while ((ligne=br.readLine())!=null)
                {
                    System.out.println(ligne);
                    result += ligne;
                    validatepppoepppipsecCButtonIPSec.setDisable(true);
                }
            } catch (IOException ex) {
                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            p = Runtime.getRuntime().exec("L2TPIPSecSTunnelC.sh "+leftAdrpppoepppipsecCField.getText()+" "+leftsubnetpppoepppipsecCField.getText()+" "+rightAdrpppoepppipsecCField.getText()+" "+rightsubnetpppoepppipsecCField.getText()+" "+keyexpppoepppipsecCField.getValue());
            pppoepppipsecC1.setDisable(true);
            pppoepppipsecC2.setDisable(true);
            pppoepppipsecC3.setDisable(true);
            pppoepppipsecC4.setDisable(true);
            pppoepppipsecC5.setDisable(true);
            pppoepppipsecC6.setDisable(true);
            pppoepppipsecC7.setDisable(true);
            stoppppoepppipsecCButton.setDisable(true);
            vpnprotocolField.setDisable(true);
            stoppppoepppipsecCButtonIPSec.setDisable(false);
            sssss = true;
            previousButton.setDisable(true);
            configurationprogressBar.setVisible(true);
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Approved.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Approved");
            tray.setMessage("Configuration of PPPoE/PPP/IPSec Client is done successfuly.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(2));
            T1 = true;
            pppoepppipsecC = true;
            nextButton.setDisable(false);
            userButton.setDisable(false);
            usernamepppoepppipsecclient = usernamepppoepppipsecCField.getText();
            passwordpppoepppipsecclient = passwordpppoepppipsecCField.getText();
            pappppoepppipsecclient = pappppoepppipsecCField.isSelected();
            pappppoepppipsecclientString = pappppoepppipsecCField.getText();
            chappppoepppipsecclientString = chappppoepppipsecCField.getText();
            chappppoepppipsecclient = chappppoepppipsecCField.isSelected();
            ikelifetimepppoepppipsecclient = keypppoepppipsecCField.getText();
            keylifetimepppoepppipsecclient = ikepppoepppipsecCField.getText();
            keyexchangepppoepppipsecclient = keyexpppoepppipsecCField.getValue();
            localippppoepppipsecclient = leftAdrpppoepppipsecCField.getText();
            localnetworkpppoepppipsecclient = leftsubnetpppoepppipsecCField.getText();
            remoteippppoepppipsecclient = rightAdrpppoepppipsecCField.getText();
            remotenetworkpppoepppipsecclient = rightsubnetpppoepppipsecCField.getText();
        } catch (IOException ex) {
            Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void stoppppoepppipSConfiguration(ActionEvent event) {
    }

    @FXML
    private void validatepppoepppipSConfiguration(ActionEvent event) {
    }

    @FXML
    private void stopgreCConfiguration(ActionEvent event) {
    }

    @FXML
    private void validategreCConfiguration(ActionEvent event) {
    }
}