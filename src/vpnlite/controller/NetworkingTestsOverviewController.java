/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vpnlite.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.controlsfx.control.Notifications;
import tray.notification.TrayNotification;
/**
 * FXML Controller class
 *
 * @author souhaieb
 */
public class NetworkingTestsOverviewController extends VPNOverviewController implements Initializable {

    @FXML
    private TextField domainField;
    @FXML
    private TextField nbrePing;
    @FXML
    private TextArea showPingField;
    @FXML
    private Button stopPingButton;
    @FXML
    private Button startPingButton;
    Thread t, t1, t2, t3;
    private Process p,p1;
    BufferedReader br,br1;
    @FXML
    private Button previousButton;
    private TitledPane titledpanePing;
    @FXML
    private Button pingresultButton;
    @FXML
    private Button tracertresultButton;
    @FXML
    private TextField domainTRField;
    @FXML
    private TextArea showTracertField;
    @FXML
    private Button stopTracertButton;
    @FXML
    private Button startTracertButton;
    @FXML
    private Button perfresultButton;
    @FXML
    private RadioButton udpPerfField;
    @FXML
    private RadioButton tcpPerfField;
    @FXML
    private Label portPerfLabel;
    @FXML
    private TextField portPerfField;
    @FXML
    private RadioButton clientPerfField;
    @FXML
    private RadioButton serverPerfField;
    @FXML
    private Label timePerfLabel;
    @FXML
    private TextField timePerfField;
    @FXML
    private Label serverAddressPerfLabel;
    @FXML
    private TextField serverAddressPerfField;
    @FXML
    private Button savePerfButton;
    @FXML
    private Button clearPerfButton;
    @FXML
    private Button stopIPerfButon;
    @FXML
    private Button startIPerfButon;
    @FXML
    private TextArea resultPerf;
    @FXML
    private Button perfautoresultButton;
    @FXML
    private Label lanspeedLabel;
    @FXML
    private CheckBox hundredmbpsCheck;
    @FXML
    private CheckBox thousandmbpsCheck;
    @FXML
    private RadioButton iperfOption;
    @FXML
    private RadioButton iperf3Option;
    @FXML
    private CheckBox uploadOption;
    @FXML
    private CheckBox downloadOption;
    @FXML
    private CheckBox tcpOption;
    @FXML
    private CheckBox udpOption;
    @FXML
    private TextArea testautoResult;
    @FXML
    private Button showResultAutoButton;
    @FXML
    private Button startPerfAutoButton;
    @FXML
    private Label serveripperfautoLabel;
    @FXML
    private TextField serveripperfautoField;
    @FXML
    private JFXButton nextButton;
    @FXML
    private ImageView nextimg;
    @FXML
    private ImageView backimg;
    @FXML
    private GridPane grilleTest;
    @FXML
    private ImageView pingImg;
    @FXML
    private ImageView tracerouteimg;
    @FXML
    private ImageView iperfimg;
    @FXML
    private Separator separate1;
    @FXML
    private Separator separate2;
    @FXML
    private AnchorPane pingtestwindow;
    @FXML
    private AnchorPane traceroutetestwindow;
    @FXML
    private AnchorPane iperftestwindow;
    @FXML
    private Button userButton;
    @FXML
    private Button vpnButton;
    @FXML
    private Button testsButton;
    @FXML
    private JFXButton clearPingButton;
    @FXML
    private JFXButton clearTRButton;
    private String resultPath;
    @FXML
    private HBox iperfFields1;
    @FXML
    private HBox iperfFields2;
    @FXML
    private HBox iperfButton1;
    @FXML
    private HBox iperfButton2;
    @FXML
    private ProgressBar iperfprogressBar;
    Notifications notif;
    @FXML
    private JFXRadioButton megaPerfField;
    @FXML
    private JFXRadioButton gigaPerfField;
    @FXML
    private HBox InstallationPackage;
    @FXML
    private JFXButton InstallationButton;
    @FXML
    private AnchorPane ftptestwindow;
    @FXML
    private JFXTextField ftpserverField;
    @FXML
    private JFXTextField ftploginField;
    @FXML
    private JFXPasswordField ftppasswordField;
    @FXML
    private JFXTextField ftpcmdlineField;
    @FXML
    private JFXTextArea showftpField;
    @FXML
    private JFXButton clearftpButton;
    @FXML
    private JFXButton saveftpButton;
    @FXML
    private ImageView ftpimg;
    @FXML
    private JFXButton ftpresultButton;
    @FXML
    private JFXButton getfileftpbutton;
    @FXML
    private JFXTextField nombreftpTest;
    String ligne;
    String result ="";
    @FXML
    private JFXRadioButton getftpField;
    @FXML
    private JFXRadioButton putftpField;
    @FXML
    private HBox ftpfields;
    @FXML
    private JFXButton graphPerfButton;
    public static boolean MPA;
    public static boolean GPA;
    @FXML
    private JFXButton grapftpButton;
    @FXML
    private JFXButton stopftpbutton;
    @FXML
    private JFXButton listfilesButton;
    @FXML
    private HBox mainmenufields;
    @FXML
    private JFXButton tracertresultButton1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        portPerfField.setText("5001");
        timePerfField.setText("5");
        
        ToggleGroup gender = new ToggleGroup();
        megaPerfField.setUserData("Mega");
        megaPerfField.setToggleGroup(gender);
        gigaPerfField.setUserData("Giga");
        gigaPerfField.setToggleGroup(gender);
        
        ToggleGroup type = new ToggleGroup();
        clientPerfField.setUserData("Client");
        clientPerfField.setToggleGroup(type);
        serverPerfField.setUserData("Server");
        serverPerfField.setToggleGroup(type);
        
        ToggleGroup protocol = new ToggleGroup();
        tcpPerfField.setUserData("TCP");
        tcpPerfField.setToggleGroup(protocol);
        udpPerfField.setUserData("UDP");
        udpPerfField.setToggleGroup(protocol);
        
        ToggleGroup genderAuto = new ToggleGroup();
        iperfOption.setToggleGroup(genderAuto);
        iperf3Option.setToggleGroup(genderAuto);
        
        ToggleGroup ftptoggle = new ToggleGroup();
        putftpField.setToggleGroup(ftptoggle);
        getftpField.setToggleGroup(ftptoggle);
        
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
        
        testsButton.setStyle("-fx-base: #b6e7c9;");
        try {
            p = Runtime.getRuntime().exec("mkdir /tmp/VPNResults");
        } catch (IOException ex) {
            Logger.getLogger(NetworkingTestsOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(R2 == true){
            vpnButton.setDisable(true);
        }else if(R1 == true){
            userButton.setDisable(true);
        }
        if(iperf3Installation.equals("Installed") && tracerouteInstallation.equals("Installed") && ethtoolInstallation.equals("Installed")){
            iperftestwindow.setDisable(false);
            InstallationPackage.setVisible(false);
            traceroutetestwindow.setDisable(false);
            pingtestwindow.setDisable(false);
        }else{
            iperftestwindow.setDisable(true);
            InstallationPackage.setVisible(true);
            traceroutetestwindow.setDisable(true);
            pingtestwindow.setDisable(true);
            Image about = new Image(getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Warning");
            tray.setMessage("Check if 'IPerf3', 'Ethtool' and 'Traceroute' packages are installed correctly");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(about);
            tray.showAndDismiss(Duration.seconds(10));
        }
    }    

    private void displayGraph(ActionEvent event) {
        showPingField.clear();
        showPingField.setWrapText(true);
        t2 = new Thread(){
        @Override
        public void run(){
            try {
                String result ="";
                int compteur = 0;
                p = Runtime.getRuntime().exec("PingTest.sh "+showPingField.getText());
                InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                br = new BufferedReader(ipsr);
                String ligne;
                while ((ligne=br.readLine())!=null)
                {
                    showPingField.appendText(ligne + "\n");
                    result += ligne;
                    compteur++;
                }
                t1.interrupt();
                if(Thread.currentThread().isInterrupted()){
                    startPingButton.setDisable(false);
                }
                titledpanePing.setStyle("-fx-focus-color: red;");
            } catch (IOException ex) {
                Logger.getLogger(NetworkingTestsOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        };
        t2.start();
    }

    @FXML
    private void stopPing(ActionEvent event) throws IOException {
        p1 = Runtime.getRuntime().exec("killprocess.sh "+domainField.getText());
        t1.interrupt();
        System.out.println(t1.isInterrupted());
        System.out.println("The Thread was stopped");
        startPingButton.setDisable(false);
        iperfprogressBar.setVisible(false);
    }

    @FXML
    private void pingTest(ActionEvent event) {
        if (nbrePing.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning !!");
            alert.setHeaderText(null);
            alert.setContentText("You have to enter a value");
            alert.showAndWait();
            Thread.currentThread().interrupt();
            startPingButton.setDisable(false);
            mainmenufields.setDisable(false);
            domainField.setDisable(false);
            nbrePing.setDisable(false);
            stopPingButton.setDisable(true);
            clearPingButton.setDisable(false);
        }
        
        t1 = new Thread(){
        @Override
        public void run(){
            try {
                startPingButton.setDisable(true);
                showPingField.clear();
                showPingField.setWrapText(true);
                pingresultButton.setStyle("-fx-base: #e7e7e7;");
                mainmenufields.setDisable(true);
                domainField.setDisable(true);
                nbrePing.setDisable(true);
                stopPingButton.setDisable(false);
                iperfprogressBar.setVisible(true);
                    String result ="";
                    int compteur = 0;
                    p = Runtime.getRuntime().exec("PingResultTest.sh "+nbrePing.getText()+" "+domainField.getText());
                    InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                    br = new BufferedReader(ipsr);
                    String ligne;
                    while ((ligne=br.readLine())!=null)
                    {
                        showPingField.appendText(ligne + "\n");
                        if(ligne.equals("Test PASSED")){
                            pingresultButton.setStyle("-fx-base: #009900;");
                        }else if(ligne.equals("Test FAILED")){
                            pingresultButton.setStyle("-fx-base: #ec5a5a;");
                        }
                        result += ligne;
                        compteur++;
                    }
                    t1.interrupt();
                    startPingButton.setDisable(false);
                    mainmenufields.setDisable(false);
                    domainField.setDisable(false);
                    nbrePing.setDisable(false);
                    stopPingButton.setDisable(true);
                    clearPingButton.setDisable(false);
                    iperfprogressBar.setVisible(false);
                } catch (IOException ex) {
                    Logger.getLogger(NetworkingTestsOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        t1.start();
    }

    @FXML
    private void previousWindows(ActionEvent event) throws IOException {
        if(R2 == true){
            try {
                U2 = true;
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
        }else if(R1 == true){
            try {
                Stage stage = (Stage) previousButton.getScene().getWindow();
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
            } catch (IOException ex) {
                Logger.getLogger(VPNOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void stopTraceR(ActionEvent event) throws IOException {
        p1 = Runtime.getRuntime().exec("killprocess.sh "+domainTRField.getText());
        t3.interrupt();
        System.out.println(t3.isInterrupted());
        System.out.println("The Thread was stopped");
        startTracertButton.setDisable(false);
        iperfprogressBar.setVisible(false);
    }

    @FXML
    private void tracertTest(ActionEvent event) {
        t3 = new Thread(){
        @Override
        public void run(){
            try {
                startTracertButton.setDisable(true);
                showTracertField.clear();
                showTracertField.setWrapText(true);
                tracertresultButton.setStyle("-fx-base: #e7e7e7;");
                stopTracertButton.setDisable(false);
                domainTRField.setDisable(true);
                mainmenufields.setDisable(true);
                iperfprogressBar.setVisible(true);
                    String result ="";
                    int compteur = 0;
                    p = Runtime.getRuntime().exec("TRResultTest.sh "+domainTRField.getText()+" | tee /tmp/VPNResults/ResultTraceRoute.txt");
                    InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                    br = new BufferedReader(ipsr);
                    String ligne, ligne1;
                    while ((ligne=br.readLine())!=null)
                    {
                        showTracertField.appendText(ligne + "\n");
                        if(ligne.equals("Test PASSED")){
                            tracertresultButton.setStyle("-fx-base: #009900;");
                        }else if(ligne.equals("Test FAILED")){
                            tracertresultButton.setStyle("-fx-base: #ec5a5a;");
                        }
                        result += ligne;
                    }
                    t3.interrupt();
                    startTracertButton.setDisable(false);
                    stopTracertButton.setDisable(true);
                    domainTRField.setDisable(false);
                    mainmenufields.setDisable(false);
                    clearTRButton.setDisable(false);
                    iperfprogressBar.setVisible(false);
                } catch (IOException ex) {
                    Logger.getLogger(NetworkingTestsOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        t3.start();
    }

    
    @FXML
    private void savePerfFunction(ActionEvent event) {
        SaveFile();
        Image notifimg = new Image(
            getClass().getResourceAsStream("/vpnlite/img/Approved.png"));
        Node notimg = new ImageView(notifimg);
        notif = Notifications.create()
            .graphic(notimg)
            .title("Saved Results")
            .text("Your result was saved correctly")
            .hideAfter(Duration.seconds(2))
            .position(Pos.CENTER)
            .darkStyle()
            .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Notification OK");
            }
        });
        notif.show();
        iperfFields1.setDisable(false);
        iperfFields2.setDisable(false);
    }

    @FXML
    private void clearPerfFunction(ActionEvent event) throws IOException {
        resultPerf.clear();
        p = Runtime.getRuntime().exec("rm -r /tmp/VPNResults/IPerfClientTCP.txt");
        p = Runtime.getRuntime().exec("rm -r /tmp/VPNResults/IPerf3ClientTCP.txt");
        p = Runtime.getRuntime().exec("rm -r /tmp/VPNResults/IPerf3ClientUDP.txt");
        p = Runtime.getRuntime().exec("rm -r /tmp/VPNResults/IPerf3ClientTCPJSon.txt");
        p = Runtime.getRuntime().exec("rm -r /tmp/VPNResults/IPerf3ClientUDPJSon.txt");
        p = Runtime.getRuntime().exec("rm -r /tmp/VPNResults/IPerf3ClientUDPBurst.txt");
        p = Runtime.getRuntime().exec("rm -r /tmp/VPNResults/IPerf3ClientUDPBurstJSon.txt");
        p = Runtime.getRuntime().exec("rm -r /tmp/VPNResults/IPerfServerTCP.txt");
        p = Runtime.getRuntime().exec("rm -r /tmp/VPNResults/IPerfServerUDP.txt");
        p = Runtime.getRuntime().exec("rm -r /tmp/VPNResults/IPerf3ServerUDP.txt");
        p = Runtime.getRuntime().exec("rm -r /tmp/VPNResults/IPerfClientUDP.txt");
        p = Runtime.getRuntime().exec("rm -r /tmp/VPNResults/IPerf3ServerTCP.txt");
    }

    @FXML
    private void stopIPerfMode(ActionEvent event) throws IOException {
            p1 = Runtime.getRuntime().exec("killprocess.sh iperf3");
            p1 = Runtime.getRuntime().exec("killprocess.sh iperf3");
            t.interrupt();
            System.out.println(t.isInterrupted());
            System.out.println("The Thread was stopped");
            iperfButton1.setDisable(false);
            startIPerfButon.setDisable(false);
            iperfFields1.setDisable(false);
            iperfFields2.setDisable(false);
            iperfprogressBar.setVisible(false);
            stopIPerfButon.setDisable(true);
            mainmenufields.setDisable(false);
            graphPerfButton.setDisable(false);
            savePerfButton.setDisable(false);
            clearPerfButton.setDisable(false);
            Image about = new Image(getClass().getResourceAsStream("/vpnlite/img/Stop.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("IPerf Test");
            tray.setMessage("Your IPerf Test is stopped correctly");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(about);
            tray.showAndDismiss(Duration.seconds(1));
    }

    @FXML
    private void startIPerfMode(ActionEvent event) {
        t = new Thread(){
        @Override
        public void run(){
        try {
            if (tcpPerfField.isSelected() && clientPerfField.isSelected()){
                iperfButton1.setDisable(true);
                startIPerfButon.setDisable(true);
                iperfFields1.setDisable(true);
                iperfFields2.setDisable(true);
                iperfprogressBar.setVisible(true);
                resultPerf.setWrapText(true);
                stopIPerfButon.setDisable(false);
                mainmenufields.setDisable(true);
                String result ="";
                String perfClientcmd = "IPerf3ClientTCP.sh "+serverAddressPerfField.getText()+" "+timePerfField.getText()+" "+portPerfField.getText();
                p = Runtime.getRuntime().exec(perfClientcmd);
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                String ligne;
                while ((ligne=streamReader.readLine())!=null)
                {
                    resultPerf.appendText(ligne + "\n");
                    result += ligne;
                }
                resultPath = "IPerf3ClientTCP.txt";
                iperfButton1.setDisable(false);
                startIPerfButon.setDisable(false);
                iperfprogressBar.setVisible(false);
                stopIPerfButon.setDisable(true);
                mainmenufields.setDisable(false);
                graphPerfButton.setDisable(false);
                savePerfButton.setDisable(false);
                clearPerfButton.setDisable(false);
                iperfFields1.setDisable(false);
                iperfFields2.setDisable(false);
            }
            if (udpPerfField.isSelected() && clientPerfField.isSelected()){
                iperfButton1.setDisable(true);
                startIPerfButon.setDisable(true);
                iperfFields1.setDisable(true);
                iperfFields2.setDisable(true);
                iperfprogressBar.setVisible(true);
                resultPerf.setWrapText(true);
                stopIPerfButon.setDisable(false);
                mainmenufields.setDisable(true);
                String result ="";
                String perfClientcmd = "IPerf3ClientUDP.sh "+serverAddressPerfField.getText()+" "+timePerfField.getText()+" "+portPerfField.getText();
                p = Runtime.getRuntime().exec(perfClientcmd);
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                String ligne;
                while ((ligne=streamReader.readLine())!=null)
                {
                    resultPerf.appendText(ligne + "\n");
                    result += ligne;
                }
                resultPath = "IPerf3ClientUDP.txt";
                iperfButton1.setDisable(false);
                startIPerfButon.setDisable(false);
                iperfprogressBar.setVisible(false);
                stopIPerfButon.setDisable(true);
                mainmenufields.setDisable(false);
                graphPerfButton.setDisable(false);
                savePerfButton.setDisable(false);
                clearPerfButton.setDisable(false);
                iperfFields1.setDisable(false);
                iperfFields2.setDisable(false);
            }
            if (serverPerfField.isSelected()){
                iperfButton1.setDisable(true);
                startIPerfButon.setDisable(true);
                iperfFields1.setDisable(true);
                iperfFields2.setDisable(true);
                iperfprogressBar.setVisible(true);
                resultPerf.setWrapText(true);
                stopIPerfButon.setDisable(false);
                mainmenufields.setDisable(true);
                String result ="";
                String perfClientcmd = "IPerf3Server.sh "+portPerfField.getText();
                p = Runtime.getRuntime().exec(perfClientcmd);
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                String ligne;
                while ((ligne=streamReader.readLine())!=null)
                {
                    resultPerf.appendText(ligne + "\n");
                    result += ligne;
                }
                resultPath = "IPerf3ServerUDP.txt";
                iperfButton1.setDisable(false);
                startIPerfButon.setDisable(false);
                iperfprogressBar.setVisible(false);
                stopIPerfButon.setDisable(true);
                mainmenufields.setDisable(false);
                graphPerfButton.setDisable(false);
                savePerfButton.setDisable(false);
                clearPerfButton.setDisable(false);
                iperfFields1.setDisable(false);
                iperfFields2.setDisable(false);
            }
        }catch (IOException ex) {
                    Logger.getLogger(NetworkingTestsOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        t.start();
        Image about = new Image(getClass().getResourceAsStream("/vpnlite/img/Approved.png"));
        TrayNotification tray = new TrayNotification();
        tray.setTitle("IPerf Test");
        tray.setMessage("Your IPerf Test is started correctly");
        tray.setRectangleFill(Paint.valueOf("#2A9A84"));
        tray.setImage(about);
        tray.showAndDismiss(Duration.seconds(1));
    }

    @FXML
    private void serverPerfAction(ActionEvent event) {
        if(serverPerfField.isSelected()){
            timePerfField.setDisable(true);
            timePerfLabel.setDisable(true);
            serverAddressPerfField.setDisable(true);
            serverAddressPerfLabel.setDisable(true);
            udpPerfField.setDisable(true);
            tcpPerfField.setDisable(true);
        }
    }

    @FXML
    private void clientPerfAction(ActionEvent event) {
        if(clientPerfField.isSelected()){
            timePerfField.setDisable(false);
            timePerfLabel.setDisable(false);
            serverAddressPerfField.setDisable(false);
            serverAddressPerfLabel.setDisable(false);
            udpPerfField.setDisable(false);
            tcpPerfField.setDisable(false);
        }
    }
    
    @FXML
    private void showAutoResults(ActionEvent event) {
    }

    @FXML
    private void nextWindow(ActionEvent event) {
    }

    @FXML
    private void pingtestdisplay(MouseEvent event) {
        pingtestwindow.setVisible(true);
        traceroutetestwindow.setVisible(false);
        iperftestwindow.setVisible(false);
        ftptestwindow.setVisible(false);
    }

    @FXML
    private void traceroutetestdisplay(MouseEvent event) {
        pingtestwindow.setVisible(false);
        traceroutetestwindow.setVisible(true);
        iperftestwindow.setVisible(false);
        ftptestwindow.setVisible(false);
    }

    
    @FXML
    private void iperftestdisplay(MouseEvent event) {
        pingtestwindow.setVisible(false);
        traceroutetestwindow.setVisible(false);
        iperftestwindow.setVisible(true);
        ftptestwindow.setVisible(false);
    }

    private void pingtestundisplay(MouseEvent event) {
        pingtestwindow.setVisible(false);
        traceroutetestwindow.setVisible(false);
        iperftestwindow.setVisible(false);
    }

    private void traceroutetestundisplay(MouseEvent event) {
        pingtestwindow.setVisible(false);
        traceroutetestwindow.setVisible(false);
        iperftestwindow.setVisible(false);
        
    }

    
    private void iperftestundisplay(MouseEvent event) {
        pingtestwindow.setVisible(false);
        traceroutetestwindow.setVisible(false);
        iperftestwindow.setVisible(false);
    }

    private void traceroutetestundisplay(SwipeEvent event) {
        
    }

    @FXML
    private void userPage(ActionEvent event) throws IOException {
        if(R2 == true){
            try {
                U2 = true;
                Stage stage = (Stage) userButton.getScene().getWindow();
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
        }else if(R1 == true){
            try {
                U1 = true;
                Stage stage = (Stage) userButton.getScene().getWindow();
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
    private void vpnPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) vpnButton.getScene().getWindow();
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

    @FXML
    private void testsPage(ActionEvent event) {
    }
    
    
    @SuppressWarnings("all") // Ligne 414
    private void SaveFile(){
        try {
            FileOutputStream fileOut = new FileOutputStream(resultPath+".xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("Exported Results");

            // index from 0,0... cell A1 is cell(0,0)
            HSSFRow row1 = worksheet.createRow((short) 0);
            
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            HSSFCellStyle cellStyle1 = workbook.createCellStyle();
            HSSFCellStyle cellStyle2 = workbook.createCellStyle();
            HSSFCellStyle cellStyle3 = workbook.createCellStyle();
            HSSFCellStyle cellStyle4 = workbook.createCellStyle();
            
            HSSFCell cellA1 = row1.createCell((short) 0);
            cellA1.setCellValue("Date");
            cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellA1.setCellStyle(cellStyle);

            HSSFCell cellB1 = row1.createCell((short) 1);
            cellB1.setCellValue("Matricule");
            cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellB1.setCellStyle(cellStyle);
            
            HSSFCell cellC1 = row1.createCell((short) 2);
            cellC1.setCellValue("Nom & Prenom");
            cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellC1.setCellStyle(cellStyle);
            
            HSSFCell cellD1 = row1.createCell((short) 3);
            cellD1.setCellValue("Company");
            cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellD1.setCellStyle(cellStyle);
            
            HSSFCell cellE1 = row1.createCell((short) 4);
            cellE1.setCellValue("Site");
            cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellE1.setCellStyle(cellStyle);
            
            HSSFCell cellF1 = row1.createCell((short) 5);
            cellF1.setCellValue("Model");
            cellStyle1.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
            cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellF1.setCellStyle(cellStyle1);
            
            HSSFCell cellG1 = row1.createCell((short) 6);
            cellG1.setCellValue("Software Version");
            cellStyle1.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
            cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellG1.setCellStyle(cellStyle1);
            
            HSSFCell cellH1 = row1.createCell((short) 7);
            cellH1.setCellValue("S/N");
            cellStyle1.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
            cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellH1.setCellStyle(cellStyle1);
            
            HSSFCell cellI1 = row1.createCell((short) 8);
            cellI1.setCellValue("HW Version");
            cellStyle1.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
            cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellI1.setCellStyle(cellStyle1);
            
            HSSFCell cellJ1 = row1.createCell((short) 9);
            cellJ1.setCellValue("GWY IP");
            cellStyle1.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
            cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellJ1.setCellStyle(cellStyle1);
            
            HSSFCell cellK1 = row1.createCell((short) 10);
            cellK1.setCellValue("GWY MAC");
            cellStyle1.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
            cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellK1.setCellStyle(cellStyle1);
            
            if(l2tpipsecS == true){
                HSSFCell cellL1 = row1.createCell((short) 11);
                cellL1.setCellValue("Configuration Side");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellL1.setCellStyle(cellStyle2);
                
                HSSFCell cellM1 = row1.createCell((short) 12);
                cellM1.setCellValue("Configuration Type");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellM1.setCellStyle(cellStyle2);
                
                HSSFCell cellN1 = row1.createCell((short) 13);
                cellN1.setCellValue("IKE Lifetime");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellN1.setCellStyle(cellStyle2);
                
                HSSFCell cellO1 = row1.createCell((short) 14);
                cellO1.setCellValue("KEY Lifetime");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellO1.setCellStyle(cellStyle2);
                
                HSSFCell cellP1 = row1.createCell((short) 15);
                cellP1.setCellValue("KEY Exchange");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellP1.setCellStyle(cellStyle2);
                
                HSSFCell cellQ1 = row1.createCell((short) 16);
                cellQ1.setCellValue("Local IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle2);
                
                HSSFCell cellR1 = row1.createCell((short) 17);
                cellR1.setCellValue("Local Network");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellR1.setCellStyle(cellStyle2);
                
                HSSFCell cellS1 = row1.createCell((short) 18);
                cellS1.setCellValue("Remote IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellS1.setCellStyle(cellStyle2);
                
                HSSFCell cellT1 = row1.createCell((short) 19);
                cellT1.setCellValue("Remote Network");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellT1.setCellStyle(cellStyle2);
                
                HSSFCell cellU1 = row1.createCell((short) 20);
                cellU1.setCellValue("IPerf Version");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellU1.setCellStyle(cellStyle3);

                HSSFCell cellV1 = row1.createCell((short) 21);
                cellV1.setCellValue("Protocol");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellV1.setCellStyle(cellStyle3);

                HSSFCell cellW1 = row1.createCell((short) 22);
                cellW1.setCellValue("Side");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellW1.setCellStyle(cellStyle3);

                HSSFCell cellX1 = row1.createCell((short) 23);
                cellX1.setCellValue("Port Number");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellX1.setCellStyle(cellStyle3);

                HSSFCell cellY1 = row1.createCell((short) 24);
                cellY1.setCellValue("IPerf Time");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellY1.setCellStyle(cellStyle3);

                HSSFCell cellZ1 = row1.createCell((short) 25);
                cellZ1.setCellValue("Bandwidth");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellZ1.setCellStyle(cellStyle4);
            }else if(l2tppppipsecS == true){
                HSSFCell cellL1 = row1.createCell((short) 11);
                cellL1.setCellValue("Configuration Type");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellL1.setCellStyle(cellStyle2);
                
                HSSFCell cellM1 = row1.createCell((short) 12);
                cellM1.setCellValue("Server IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellM1.setCellStyle(cellStyle2);
                
                HSSFCell cellN1 = row1.createCell((short) 13);
                cellN1.setCellValue("Username");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellN1.setCellStyle(cellStyle2);
                
                HSSFCell cellO1 = row1.createCell((short) 14);
                cellO1.setCellValue("Password");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellO1.setCellStyle(cellStyle2);
                
                HSSFCell cellP1 = row1.createCell((short) 15);
                cellP1.setCellValue("IPerf Version");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellP1.setCellStyle(cellStyle3);

                HSSFCell cellQ1 = row1.createCell((short) 16);
                cellQ1.setCellValue("Protocol");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle3);

                HSSFCell cellR1 = row1.createCell((short) 17);
                cellR1.setCellValue("Side");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellR1.setCellStyle(cellStyle3);

                HSSFCell cellS1 = row1.createCell((short) 18);
                cellS1.setCellValue("Port Number");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellS1.setCellStyle(cellStyle3);

                HSSFCell cellT1 = row1.createCell((short) 19);
                cellT1.setCellValue("IPerf Time");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellT1.setCellStyle(cellStyle3);

                HSSFCell cellU1 = row1.createCell((short) 20);
                cellU1.setCellValue("Bandwidth");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellU1.setCellStyle(cellStyle4);
            }else if(pppoepppipsecS == true){
                HSSFCell cellL1 = row1.createCell((short) 11);
                cellL1.setCellValue("IKE Lifetime");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellL1.setCellStyle(cellStyle2);
                
                HSSFCell cellM1 = row1.createCell((short) 12);
                cellM1.setCellValue("KEY Lifetime");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellM1.setCellStyle(cellStyle2);
                
                HSSFCell cellN1 = row1.createCell((short) 13);
                cellN1.setCellValue("KEY Exchange");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellN1.setCellStyle(cellStyle2);
                
                HSSFCell cellO1 = row1.createCell((short) 14);
                cellO1.setCellValue("Local IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellO1.setCellStyle(cellStyle2);
                
                HSSFCell cellP1 = row1.createCell((short) 15);
                cellP1.setCellValue("Local Network");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellP1.setCellStyle(cellStyle2);
                
                HSSFCell cellQ1 = row1.createCell((short) 16);
                cellQ1.setCellValue("Remote IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle2);
                
                HSSFCell cellR1 = row1.createCell((short) 17);
                cellR1.setCellValue("Remote Network");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellR1.setCellStyle(cellStyle2);
                
                HSSFCell cellS1 = row1.createCell((short) 18);
                cellS1.setCellValue("IPerf Version");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellS1.setCellStyle(cellStyle3);

                HSSFCell cellT1 = row1.createCell((short) 19);
                cellT1.setCellValue("Protocol");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellT1.setCellStyle(cellStyle3);

                HSSFCell cellU1 = row1.createCell((short) 20);
                cellU1.setCellValue("Side");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellU1.setCellStyle(cellStyle3);

                HSSFCell cellV1 = row1.createCell((short) 21);
                cellV1.setCellValue("Port Number");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellV1.setCellStyle(cellStyle3);

                HSSFCell cellW1 = row1.createCell((short) 22);
                cellW1.setCellValue("IPerf Time");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellW1.setCellStyle(cellStyle3);

                HSSFCell cellX1 = row1.createCell((short) 23);
                cellX1.setCellValue("Bandwidth");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellX1.setCellStyle(cellStyle4);
            }else if(pptpS == true){
                HSSFCell cellL1 = row1.createCell((short) 11);
                cellL1.setCellValue("Server IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellL1.setCellStyle(cellStyle2);
                
                HSSFCell cellM1 = row1.createCell((short) 12);
                cellM1.setCellValue("Client Pool");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellM1.setCellStyle(cellStyle2);
                
                HSSFCell cellN1 = row1.createCell((short) 13);
                cellN1.setCellValue("VPN Network");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellN1.setCellStyle(cellStyle2);
                
                HSSFCell cellO1 = row1.createCell((short) 14);
                cellO1.setCellValue("Username");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellO1.setCellStyle(cellStyle2);
                
                HSSFCell cellP1 = row1.createCell((short) 15);
                cellP1.setCellValue("Password");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellP1.setCellStyle(cellStyle2);
                
                HSSFCell cellQ1 = row1.createCell((short) 16);
                cellQ1.setCellValue("IPerf Version");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle3);

                HSSFCell cellR1 = row1.createCell((short) 17);
                cellR1.setCellValue("Protocol");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellR1.setCellStyle(cellStyle3);

                HSSFCell cellS1 = row1.createCell((short) 18);
                cellS1.setCellValue("Side");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellS1.setCellStyle(cellStyle3);

                HSSFCell cellT1 = row1.createCell((short) 19);
                cellT1.setCellValue("Port Number");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellT1.setCellStyle(cellStyle3);

                HSSFCell cellU1 = row1.createCell((short) 20);
                cellU1.setCellValue("IPerf Time");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellU1.setCellStyle(cellStyle3);

                HSSFCell cellV1 = row1.createCell((short) 21);
                cellV1.setCellValue("Bandwidth");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellV1.setCellStyle(cellStyle4);
            }else if(greS == true){
                HSSFCell cellL1 = row1.createCell((short) 11);
                cellL1.setCellValue("Local IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellL1.setCellStyle(cellStyle2);
                
                HSSFCell cellM1 = row1.createCell((short) 12);
                cellM1.setCellValue("Remote IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellM1.setCellStyle(cellStyle2);
                
                HSSFCell cellN1 = row1.createCell((short) 13);
                cellN1.setCellValue("Remote Network");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellN1.setCellStyle(cellStyle2);
                
                HSSFCell cellO1 = row1.createCell((short) 14);
                cellO1.setCellValue("GRE IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellO1.setCellStyle(cellStyle2);
                
                HSSFCell cellP1 = row1.createCell((short) 15);
                cellP1.setCellValue("IPerf Version");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellP1.setCellStyle(cellStyle3);

                HSSFCell cellQ1 = row1.createCell((short) 16);
                cellQ1.setCellValue("Protocol");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle3);

                HSSFCell cellR1 = row1.createCell((short) 17);
                cellR1.setCellValue("Side");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellR1.setCellStyle(cellStyle3);

                HSSFCell cellS1 = row1.createCell((short) 18);
                cellS1.setCellValue("Port Number");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellS1.setCellStyle(cellStyle3);

                HSSFCell cellT1 = row1.createCell((short) 19);
                cellT1.setCellValue("IPerf Time");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellT1.setCellStyle(cellStyle3);

                HSSFCell cellU1 = row1.createCell((short) 20);
                cellU1.setCellValue("Bandwidth");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellU1.setCellStyle(cellStyle4);
            }else if(l2tpipsecC == true){
                HSSFCell cellL1 = row1.createCell((short) 11);
                cellL1.setCellValue("Configuration Type");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellL1.setCellStyle(cellStyle2);
                
                HSSFCell cellM1 = row1.createCell((short) 12);
                cellM1.setCellValue("IKE Lifetime");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellM1.setCellStyle(cellStyle2);
                
                HSSFCell cellN1 = row1.createCell((short) 13);
                cellN1.setCellValue("KEY Lifetime");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellN1.setCellStyle(cellStyle2);
                
                HSSFCell cellO1 = row1.createCell((short) 14);
                cellO1.setCellValue("Local IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellO1.setCellStyle(cellStyle2);
                
                HSSFCell cellP1 = row1.createCell((short) 15);
                cellP1.setCellValue("Server IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellP1.setCellStyle(cellStyle2);
                
                HSSFCell cellQ1 = row1.createCell((short) 16);
                cellQ1.setCellValue("IPerf Version");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle3);

                HSSFCell cellR1 = row1.createCell((short) 17);
                cellR1.setCellValue("Protocol");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellR1.setCellStyle(cellStyle3);

                HSSFCell cellS1 = row1.createCell((short) 18);
                cellS1.setCellValue("Side");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellS1.setCellStyle(cellStyle3);

                HSSFCell cellT1 = row1.createCell((short) 19);
                cellT1.setCellValue("Port Number");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellT1.setCellStyle(cellStyle3);

                HSSFCell cellU1 = row1.createCell((short) 20);
                cellU1.setCellValue("IPerf Time");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellU1.setCellStyle(cellStyle3);

                HSSFCell cellV1 = row1.createCell((short) 21);
                cellV1.setCellValue("Bandwidth");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellV1.setCellStyle(cellStyle4);
            }else if(l2tppppipsecC == true){
                HSSFCell cellL1 = row1.createCell((short) 11);
                cellL1.setCellValue("Configuration Type");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellL1.setCellStyle(cellStyle2);
                
                HSSFCell cellM1 = row1.createCell((short) 12);
                cellM1.setCellValue("IKE Lifetime");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellM1.setCellStyle(cellStyle2);
                
                HSSFCell cellN1 = row1.createCell((short) 13);
                cellN1.setCellValue("KEY Lifetime");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellN1.setCellStyle(cellStyle2);
                
                HSSFCell cellO1 = row1.createCell((short) 14);
                cellO1.setCellValue("Local IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellO1.setCellStyle(cellStyle2);
                
                HSSFCell cellP1 = row1.createCell((short) 15);
                cellP1.setCellValue("Server IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellP1.setCellStyle(cellStyle2);
                
                HSSFCell cellQ1 = row1.createCell((short) 16);
                cellQ1.setCellValue("Username");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle2);
                
                HSSFCell cellR1 = row1.createCell((short) 17);
                cellQ1.setCellValue("Password");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle2);
                
                HSSFCell cellS1 = row1.createCell((short) 18);
                cellS1.setCellValue("IPerf Version");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellS1.setCellStyle(cellStyle3);

                HSSFCell cellT1 = row1.createCell((short) 19);
                cellT1.setCellValue("Protocol");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellT1.setCellStyle(cellStyle3);

                HSSFCell cellU1 = row1.createCell((short) 20);
                cellU1.setCellValue("Side");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellU1.setCellStyle(cellStyle3);

                HSSFCell cellV1 = row1.createCell((short) 21);
                cellV1.setCellValue("Port Number");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellV1.setCellStyle(cellStyle3);

                HSSFCell cellW1 = row1.createCell((short) 22);
                cellW1.setCellValue("IPerf Time");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellW1.setCellStyle(cellStyle3);

                HSSFCell cellX1 = row1.createCell((short) 23);
                cellX1.setCellValue("Bandwidth");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellX1.setCellStyle(cellStyle4);
            }else if(pppoepppipC == true){
                HSSFCell cellL1 = row1.createCell((short) 11);
                cellL1.setCellValue("Authentication Type");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellL1.setCellStyle(cellStyle2);
                
                HSSFCell cellM1 = row1.createCell((short) 12);
                cellM1.setCellValue("Username");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellM1.setCellStyle(cellStyle2);
                
                HSSFCell cellN1 = row1.createCell((short) 13);
                cellN1.setCellValue("Password");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellN1.setCellStyle(cellStyle2);
                
                HSSFCell cellO1 = row1.createCell((short) 14);
                cellO1.setCellValue("IPerf Version");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellO1.setCellStyle(cellStyle3);

                HSSFCell cellP1 = row1.createCell((short) 15);
                cellP1.setCellValue("Protocol");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellP1.setCellStyle(cellStyle3);

                HSSFCell cellQ1 = row1.createCell((short) 16);
                cellQ1.setCellValue("Side");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle3);

                HSSFCell cellR1 = row1.createCell((short) 17);
                cellR1.setCellValue("Port Number");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellR1.setCellStyle(cellStyle3);

                HSSFCell cellS1 = row1.createCell((short) 18);
                cellS1.setCellValue("IPerf Time");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellS1.setCellStyle(cellStyle3);

                HSSFCell cellT1 = row1.createCell((short) 19);
                cellT1.setCellValue("Bandwidth");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellT1.setCellStyle(cellStyle4);
            }else if(pppoepppipsecC == true){
                HSSFCell cellL1 = row1.createCell((short) 11);
                cellL1.setCellValue("Authentication Type");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellL1.setCellStyle(cellStyle2);
                
                HSSFCell cellM1 = row1.createCell((short) 12);
                cellM1.setCellValue("Username");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellM1.setCellStyle(cellStyle2);
                
                HSSFCell cellN1 = row1.createCell((short) 13);
                cellN1.setCellValue("Password");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellN1.setCellStyle(cellStyle2);
                
                HSSFCell cellO1 = row1.createCell((short) 14);
                cellO1.setCellValue("IKE Lifetime");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellO1.setCellStyle(cellStyle2);
                
                HSSFCell cellP1 = row1.createCell((short) 15);
                cellP1.setCellValue("KEY Lifetime");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellP1.setCellStyle(cellStyle2);
                
                HSSFCell cellQ1 = row1.createCell((short) 16);
                cellQ1.setCellValue("KEY Exchange");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle2);
                
                HSSFCell cellR1 = row1.createCell((short) 17);
                cellR1.setCellValue("Local IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellR1.setCellStyle(cellStyle2);
                
                HSSFCell cellS1 = row1.createCell((short) 18);
                cellS1.setCellValue("Local Network");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellS1.setCellStyle(cellStyle2);
                
                HSSFCell cellT1 = row1.createCell((short) 19);
                cellT1.setCellValue("Remote IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellT1.setCellStyle(cellStyle2);
                
                HSSFCell cellU1 = row1.createCell((short) 20);
                cellU1.setCellValue("Remote Network");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellU1.setCellStyle(cellStyle2);
                
                HSSFCell cellV1 = row1.createCell((short) 21);
                cellV1.setCellValue("IPerf Version");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellV1.setCellStyle(cellStyle3);

                HSSFCell cellW1 = row1.createCell((short) 22);
                cellW1.setCellValue("Protocol");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellW1.setCellStyle(cellStyle3);

                HSSFCell cellX1 = row1.createCell((short) 23);
                cellX1.setCellValue("Side");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellX1.setCellStyle(cellStyle3);

                HSSFCell cellY1 = row1.createCell((short) 24);
                cellY1.setCellValue("Port Number");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellY1.setCellStyle(cellStyle3);

                HSSFCell cellZ1 = row1.createCell((short) 25);
                cellZ1.setCellValue("IPerf Time");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellZ1.setCellStyle(cellStyle3);

                HSSFCell cellAA1 = row1.createCell((short) 26);
                cellAA1.setCellValue("Bandwidth");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellAA1.setCellStyle(cellStyle4);
            }else if(pptpC == true){
                HSSFCell cellL1 = row1.createCell((short) 11);
                cellL1.setCellValue("Server IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellL1.setCellStyle(cellStyle2);
                
                HSSFCell cellM1 = row1.createCell((short) 12);
                cellM1.setCellValue("Username");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellM1.setCellStyle(cellStyle2);
                
                HSSFCell cellN1 = row1.createCell((short) 13);
                cellN1.setCellValue("Password");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellN1.setCellStyle(cellStyle2);
                
                HSSFCell cellO1 = row1.createCell((short) 14);
                cellO1.setCellValue("IPerf Version");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellO1.setCellStyle(cellStyle3);

                HSSFCell cellP1 = row1.createCell((short) 15);
                cellP1.setCellValue("Protocol");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellP1.setCellStyle(cellStyle3);

                HSSFCell cellQ1 = row1.createCell((short) 16);
                cellQ1.setCellValue("Side");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle3);

                HSSFCell cellR1 = row1.createCell((short) 17);
                cellR1.setCellValue("Port Number");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellR1.setCellStyle(cellStyle3);

                HSSFCell cellS1 = row1.createCell((short) 18);
                cellS1.setCellValue("IPerf Time");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellS1.setCellStyle(cellStyle3);

                HSSFCell cellT1 = row1.createCell((short) 19);
                cellT1.setCellValue("Bandwidth");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellT1.setCellStyle(cellStyle4);
            }else if(greC == true){
                HSSFCell cellL1 = row1.createCell((short) 11);
                cellL1.setCellValue("Local IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellL1.setCellStyle(cellStyle2);
                
                HSSFCell cellM1 = row1.createCell((short) 12);
                cellM1.setCellValue("Remote IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellM1.setCellStyle(cellStyle2);
                
                HSSFCell cellN1 = row1.createCell((short) 13);
                cellN1.setCellValue("Remote Network");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellN1.setCellStyle(cellStyle2);
                
                HSSFCell cellO1 = row1.createCell((short) 14);
                cellO1.setCellValue("GRE IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellO1.setCellStyle(cellStyle2);
                
                HSSFCell cellP1 = row1.createCell((short) 15);
                cellP1.setCellValue("IPerf Version");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellP1.setCellStyle(cellStyle3);

                HSSFCell cellQ1 = row1.createCell((short) 16);
                cellQ1.setCellValue("Protocol");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle3);

                HSSFCell cellR1 = row1.createCell((short) 17);
                cellR1.setCellValue("Side");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellR1.setCellStyle(cellStyle3);

                HSSFCell cellS1 = row1.createCell((short) 18);
                cellS1.setCellValue("Port Number");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellS1.setCellStyle(cellStyle3);

                HSSFCell cellT1 = row1.createCell((short) 19);
                cellT1.setCellValue("IPerf Time");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellT1.setCellStyle(cellStyle3);

                HSSFCell cellU1 = row1.createCell((short) 20);
                cellU1.setCellValue("Bandwidth");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellU1.setCellStyle(cellStyle4);
            }
            
            for(int i=1; i<=Integer.parseInt(timePerfField.getText())+2; i++){
                
                HSSFRow row = worksheet.createRow((short) i);
                
                HSSFCell cellA2 = row.createCell((short) 0);
                cellA2.setCellValue(variableDate);
                cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                cellA2.setCellStyle(cellStyle);
                
                HSSFCell cellB2 = row.createCell((short) 1);
                cellB2.setCellValue(variableMatricule);
                cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                cellB2.setCellStyle(cellStyle);

                HSSFCell cellC2 = row.createCell((short) 2);
                cellC2.setCellValue(variableNomPrenom);
                cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                cellC2.setCellStyle(cellStyle);
                
                HSSFCell cellD2 = row.createCell((short) 3);
                cellD2.setCellValue(variableCompany);
                cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                cellD2.setCellStyle(cellStyle);
                
                HSSFCell cellE2 = row.createCell((short) 4);
                cellE2.setCellValue(variableSite);
                cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                cellE2.setCellStyle(cellStyle);
                
                HSSFCell cellF2 = row.createCell((short) 5);
                cellF2.setCellValue(variableModel);
                cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                cellF2.setCellStyle(cellStyle);
                
                HSSFCell cellG2 = row.createCell((short) 6);
                cellG2.setCellValue(variableSoftVersion);
                cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                cellG2.setCellStyle(cellStyle);
                
                HSSFCell cellH2 = row.createCell((short) 7);
                cellH2.setCellValue(variableSerialNumber);
                cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                cellH2.setCellStyle(cellStyle);
                
                HSSFCell cellI2 = row.createCell((short) 8);
                cellI2.setCellValue(variableHWVersion);
                cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                cellI2.setCellStyle(cellStyle);
                
                HSSFCell cellJ2 = row.createCell((short) 9);
                cellJ2.setCellValue(variableGWYIPAddr);
                cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                cellJ2.setCellStyle(cellStyle);
                
                HSSFCell cellK2 = row.createCell((short) 10);
                cellK2.setCellValue(variableMACAddr);
                cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                cellK2.setCellStyle(cellStyle);
                
                if(l2tpipsecS == true){
                    if(clientsidel2tpipsecserver = true){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(clientsidel2tpipsecserverString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }else if(wansidel2tpipsecserver = true){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(wansidel2tpipsecserverString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }
                    
                    if(transportl2tpipsecserver = true){
                        HSSFCell cellM2 = row.createCell((short) 12);
                        cellM2.setCellValue(transportl2tpipsecserverString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellM2.setCellStyle(cellStyle);
                    }else if(tunnell2tpipsecserver = true){
                        HSSFCell cellM2 = row.createCell((short) 12);
                        cellM2.setCellValue(tunnell2tpipsecserverString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellM2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellN2 = row.createCell((short) 13);
                    cellN2.setCellValue(ikelifetimel2tpipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellN2.setCellStyle(cellStyle);

                    HSSFCell cellO2 = row.createCell((short) 14);
                    cellO2.setCellValue(keylifetimel2tpipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellO2.setCellStyle(cellStyle);

                    HSSFCell cellP2 = row.createCell((short) 15);
                    cellP2.setCellValue(keyexchangel2tpipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellP2.setCellStyle(cellStyle);

                    HSSFCell cellQ2 = row.createCell((short) 16);
                    cellQ2.setCellValue(localipl2tpipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellQ2.setCellStyle(cellStyle);

                    HSSFCell cellR2 = row.createCell((short) 17);
                    cellR2.setCellValue(localnetworkl2tpipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellR2.setCellStyle(cellStyle);

                    HSSFCell cellS2 = row.createCell((short) 18);
                    cellS2.setCellValue(remoteipl2tpipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellS2.setCellStyle(cellStyle);

                    HSSFCell cellT2 = row.createCell((short) 19);
                    cellT2.setCellValue(remotenetworkl2tpipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellT2.setCellStyle(cellStyle);
                    
                    HSSFCell cellU2 = row.createCell((short) 20);
                    cellU2.setCellValue("IPerf3");
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellU2.setCellStyle(cellStyle);

                    if(udpPerfField.isSelected()){
                        HSSFCell cellV2 = row.createCell((short) 21);
                        cellV2.setCellValue(udpPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellV2.setCellStyle(cellStyle);
                    }else if(tcpPerfField.isSelected()){
                        HSSFCell cellV2 = row.createCell((short) 21);
                        cellV2.setCellValue(tcpPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellV2.setCellStyle(cellStyle);
                    }

                    if(clientPerfField.isSelected()){
                        HSSFCell cellW2 = row.createCell((short) 22);
                        cellW2.setCellValue(clientPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellW2.setCellStyle(cellStyle);
                    }else if(serverPerfField.isSelected()){
                        HSSFCell cellW2 = row.createCell((short) 22);
                        cellW2.setCellValue(serverPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellW2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellX2 = row.createCell((short) 23);
                    cellX2.setCellValue(portPerfField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellX2.setCellStyle(cellStyle);

                    HSSFCell cellY2 = row.createCell((short) 24);
                    cellY2.setCellValue(timePerfField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellY2.setCellStyle(cellStyle);

                    System.out.println(resultPath);

                    String perfClientcmd = "ResultToExcel.sh "+resultPath+" "+i+"p";
                    p = Runtime.getRuntime().exec(perfClientcmd);
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String ligne;
                    while ((ligne=streamReader.readLine())!=null)
                    {
                        HSSFCell cellZ2 = row.createCell((short) 25);
                        cellZ2.setCellValue(ligne);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellZ2.setCellStyle(cellStyle);
                    }

                    if(i == Integer.parseInt(timePerfField.getText())+1){
                        HSSFCell cellAA2 = row.createCell((short) 26);
                        cellAA2.setCellValue("Sender");
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellAA2.setCellStyle(cellStyle);
                    }else if(i == Integer.parseInt(timePerfField.getText())+2){
                        HSSFCell cellAA2 = row.createCell((short) 26);
                        cellAA2.setCellValue("Receiver");
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellAA2.setCellStyle(cellStyle);
                    }
                }else if(l2tppppipsecS == true){
                    if(transportl2tppppipsecserver = true){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(transportl2tppppipsecserverString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }else if(tunnell2tppppipsecserver = true){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(tunnell2tppppipsecserverString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellM2 = row.createCell((short) 12);
                    cellM2.setCellValue(serveripl2tppppipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellM2.setCellStyle(cellStyle);

                    HSSFCell cellN2 = row.createCell((short) 13);
                    cellN2.setCellValue(usernamel2tppppipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellN2.setCellStyle(cellStyle);

                    HSSFCell cellO2 = row.createCell((short) 14);
                    cellO2.setCellValue(passwordl2tppppipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellO2.setCellStyle(cellStyle);
                
                    HSSFCell cellP2 = row.createCell((short) 15);
                    cellP2.setCellValue("IPerf3");
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellP2.setCellStyle(cellStyle);

                    if(udpPerfField.isSelected()){
                        HSSFCell cellQ2 = row.createCell((short) 16);
                        cellQ2.setCellValue(udpPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellQ2.setCellStyle(cellStyle);
                    }else if(tcpPerfField.isSelected()){
                        HSSFCell cellQ2 = row.createCell((short) 16);
                        cellQ2.setCellValue(tcpPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellQ2.setCellStyle(cellStyle);
                    }

                    if(clientPerfField.isSelected()){
                        HSSFCell cellR2 = row.createCell((short) 17);
                        cellR2.setCellValue(clientPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellR2.setCellStyle(cellStyle);
                    }else if(serverPerfField.isSelected()){
                        HSSFCell cellR2 = row.createCell((short) 17);
                        cellR2.setCellValue(serverPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellR2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellS2 = row.createCell((short) 18);
                    cellS2.setCellValue(portPerfField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellS2.setCellStyle(cellStyle);

                    HSSFCell cellT2 = row.createCell((short) 19);
                    cellT2.setCellValue(timePerfField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellT2.setCellStyle(cellStyle);

                    System.out.println(resultPath);

                    String perfClientcmd = "ResultToExcel.sh "+resultPath+" "+i+"p";
                    p = Runtime.getRuntime().exec(perfClientcmd);
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String ligne;
                    while ((ligne=streamReader.readLine())!=null)
                    {
                        HSSFCell cellU2 = row.createCell((short) 20);
                        cellU2.setCellValue(ligne);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellU2.setCellStyle(cellStyle);
                    }

                    if(i == Integer.parseInt(timePerfField.getText())+1){
                        HSSFCell cellV2 = row.createCell((short) 21);
                        cellV2.setCellValue("Sender");
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellV2.setCellStyle(cellStyle);
                    }else if(i == Integer.parseInt(timePerfField.getText())+2){
                        HSSFCell cellV2 = row.createCell((short) 21);
                        cellV2.setCellValue("Receiver");
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellV2.setCellStyle(cellStyle);
                    }
                }else if(pppoepppipsecS == true){
                    HSSFCell cellL2 = row.createCell((short) 11);
                    cellL2.setCellValue(ikelifetimepppoepppipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellL2.setCellStyle(cellStyle);
                    
                    HSSFCell cellM2 = row.createCell((short) 12);
                    cellM2.setCellValue(keylifetimepppoepppipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellM2.setCellStyle(cellStyle);
                    
                    HSSFCell cellN2 = row.createCell((short) 13);
                    cellN2.setCellValue(keyexchangepppoepppipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellN2.setCellStyle(cellStyle);
                    
                    HSSFCell cellO2 = row.createCell((short) 14);
                    cellO2.setCellValue(localippppoepppipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellO2.setCellStyle(cellStyle);
                    
                    HSSFCell cellP2 = row.createCell((short) 15);
                    cellP2.setCellValue(localnetworkpppoepppipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellP2.setCellStyle(cellStyle);
                    
                    HSSFCell cellQ2 = row.createCell((short) 16);
                    cellQ2.setCellValue(remoteippppoepppipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellQ2.setCellStyle(cellStyle);
                    
                    HSSFCell cellR2 = row.createCell((short) 17);
                    cellR2.setCellValue(remotenetworkpppoepppipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellR2.setCellStyle(cellStyle);
                
                    HSSFCell cellS2 = row.createCell((short) 18);
                    cellS2.setCellValue("IPerf3");
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellS2.setCellStyle(cellStyle);

                    if(udpPerfField.isSelected()){
                        HSSFCell cellT2 = row.createCell((short) 19);
                        cellT2.setCellValue(udpPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellT2.setCellStyle(cellStyle);
                    }else if(tcpPerfField.isSelected()){
                        HSSFCell cellT2 = row.createCell((short) 19);
                        cellT2.setCellValue(tcpPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellT2.setCellStyle(cellStyle);
                    }

                    if(clientPerfField.isSelected()){
                        HSSFCell cellU2 = row.createCell((short) 20);
                        cellU2.setCellValue(clientPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellU2.setCellStyle(cellStyle);
                    }else if(serverPerfField.isSelected()){
                        HSSFCell cellU2 = row.createCell((short) 20);
                        cellU2.setCellValue(serverPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellU2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellV2 = row.createCell((short) 21);
                    cellV2.setCellValue(portPerfField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellV2.setCellStyle(cellStyle);

                    HSSFCell cellW2 = row.createCell((short) 22);
                    cellW2.setCellValue(timePerfField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellW2.setCellStyle(cellStyle);

                    System.out.println(resultPath);

                    String perfClientcmd = "ResultToExcel.sh "+resultPath+" "+i+"p";
                    p = Runtime.getRuntime().exec(perfClientcmd);
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String ligne;
                    while ((ligne=streamReader.readLine())!=null)
                    {
                        HSSFCell cellX2 = row.createCell((short) 23);
                        cellX2.setCellValue(ligne);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellX2.setCellStyle(cellStyle);
                    }

                    if(i == Integer.parseInt(timePerfField.getText())+1){
                        HSSFCell cellY2 = row.createCell((short) 24);
                        cellY2.setCellValue("Sender");
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellY2.setCellStyle(cellStyle);
                    }else if(i == Integer.parseInt(timePerfField.getText())+2){
                        HSSFCell cellY2 = row.createCell((short) 24);
                        cellY2.setCellValue("Receiver");
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellY2.setCellStyle(cellStyle);
                    }
                }else if(pptpS == true){
                    HSSFCell cellL2 = row.createCell((short) 11);
                    cellL2.setCellValue(serverippptpserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellL2.setCellStyle(cellStyle);
                    
                    HSSFCell cellM2 = row.createCell((short) 12);
                    cellM2.setCellValue(clientpoolpptpserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellM2.setCellStyle(cellStyle);
                    
                    HSSFCell cellN2 = row.createCell((short) 13);
                    cellN2.setCellValue(vpnnetworkpptpserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellN2.setCellStyle(cellStyle);
                    
                    HSSFCell cellO2 = row.createCell((short) 14);
                    cellO2.setCellValue(usernamepptpserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellO2.setCellStyle(cellStyle);
                    
                    HSSFCell cellP2 = row.createCell((short) 15);
                    cellP2.setCellValue(passwordpptpserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellP2.setCellStyle(cellStyle);
                
                    HSSFCell cellQ2 = row.createCell((short) 16);
                    cellQ2.setCellValue("IPerf3");
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellQ2.setCellStyle(cellStyle);

                    if(udpPerfField.isSelected()){
                        HSSFCell cellR2 = row.createCell((short) 17);
                        cellR2.setCellValue(udpPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellR2.setCellStyle(cellStyle);
                    }else if(tcpPerfField.isSelected()){
                        HSSFCell cellR2 = row.createCell((short) 17);
                        cellR2.setCellValue(tcpPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellR2.setCellStyle(cellStyle);
                    }

                    if(clientPerfField.isSelected()){
                        HSSFCell cellS2 = row.createCell((short) 18);
                        cellS2.setCellValue(clientPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellS2.setCellStyle(cellStyle);
                    }else if(serverPerfField.isSelected()){
                        HSSFCell cellS2 = row.createCell((short) 18);
                        cellS2.setCellValue(serverPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellS2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellT2 = row.createCell((short) 19);
                    cellT2.setCellValue(portPerfField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellT2.setCellStyle(cellStyle);

                    HSSFCell cellU2 = row.createCell((short) 20);
                    cellU2.setCellValue(timePerfField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellU2.setCellStyle(cellStyle);

                    System.out.println(resultPath);

                    String perfClientcmd = "ResultToExcel.sh "+resultPath+" "+i+"p";
                    p = Runtime.getRuntime().exec(perfClientcmd);
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String ligne;
                    while ((ligne=streamReader.readLine())!=null)
                    {
                        HSSFCell cellV2 = row.createCell((short) 21);
                        cellV2.setCellValue(ligne);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellV2.setCellStyle(cellStyle);
                    }

                    if(i == Integer.parseInt(timePerfField.getText())+1){
                        HSSFCell cellW2 = row.createCell((short) 22);
                        cellW2.setCellValue("Sender");
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellW2.setCellStyle(cellStyle);
                    }else if(i == Integer.parseInt(timePerfField.getText())+2){
                        HSSFCell cellW2 = row.createCell((short) 22);
                        cellW2.setCellValue("Receiver");
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellW2.setCellStyle(cellStyle);
                    }
                }else if(greS == true){
                    HSSFCell cellL2 = row.createCell((short) 11);
                    cellL2.setCellValue(localipgreserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellL2.setCellStyle(cellStyle);
                    
                    HSSFCell cellM2 = row.createCell((short) 12);
                    cellL2.setCellValue(remoteipgreserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellL2.setCellStyle(cellStyle);
                    
                    HSSFCell cellN2 = row.createCell((short) 13);
                    cellL2.setCellValue(remotenetworkgreserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellL2.setCellStyle(cellStyle);
                    
                    HSSFCell cellO2 = row.createCell((short) 14);
                    cellL2.setCellValue(greipgreserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellL2.setCellStyle(cellStyle);
                
                    HSSFCell cellP2 = row.createCell((short) 15);
                    cellP2.setCellValue("IPerf3");
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellP2.setCellStyle(cellStyle);

                    if(udpPerfField.isSelected()){
                        HSSFCell cellQ2 = row.createCell((short) 16);
                        cellQ2.setCellValue(udpPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellQ2.setCellStyle(cellStyle);
                    }else if(tcpPerfField.isSelected()){
                        HSSFCell cellQ2 = row.createCell((short) 16);
                        cellQ2.setCellValue(tcpPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellQ2.setCellStyle(cellStyle);
                    }

                    if(clientPerfField.isSelected()){
                        HSSFCell cellR2 = row.createCell((short) 17);
                        cellR2.setCellValue(clientPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellR2.setCellStyle(cellStyle);
                    }else if(serverPerfField.isSelected()){
                        HSSFCell cellR2 = row.createCell((short) 17);
                        cellR2.setCellValue(serverPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellR2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellS2 = row.createCell((short) 18);
                    cellS2.setCellValue(portPerfField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellS2.setCellStyle(cellStyle);

                    HSSFCell cellT2 = row.createCell((short) 19);
                    cellT2.setCellValue(timePerfField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellT2.setCellStyle(cellStyle);

                    System.out.println(resultPath);

                    String perfClientcmd = "ResultToExcel.sh "+resultPath+" "+i+"p";
                    p = Runtime.getRuntime().exec(perfClientcmd);
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String ligne;
                    while ((ligne=streamReader.readLine())!=null)
                    {
                        HSSFCell cellU2 = row.createCell((short) 20);
                        cellU2.setCellValue(ligne);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellU2.setCellStyle(cellStyle);
                    }

                    if(i == Integer.parseInt(timePerfField.getText())+1){
                        HSSFCell cellV2 = row.createCell((short) 21);
                        cellV2.setCellValue("Sender");
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellV2.setCellStyle(cellStyle);
                    }else if(i == Integer.parseInt(timePerfField.getText())+2){
                        HSSFCell cellV2 = row.createCell((short) 21);
                        cellV2.setCellValue("Receiver");
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellV2.setCellStyle(cellStyle);
                    }
                }else if(l2tpipsecC == true){
                    if(transportl2tpipsecclient = true){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(transportl2tpipsecclientString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }else if(tunnell2tpipsecclient = true){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(tunnell2tpipsecclientString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }
                    
                    HSSFCell cellM2 = row.createCell((short) 12);
                    cellM2.setCellValue(ikelifetimel2tpipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellM2.setCellStyle(cellStyle);
                    
                    HSSFCell cellN2 = row.createCell((short) 13);
                    cellN2.setCellValue(keylifetimel2tpipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellN2.setCellStyle(cellStyle);
                    
                    HSSFCell cellP2 = row.createCell((short) 14);
                    cellP2.setCellValue(localipl2tpipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellP2.setCellStyle(cellStyle);
                    
                    HSSFCell cellQ2 = row.createCell((short) 15);
                    cellQ2.setCellValue(serveripl2tpipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellQ2.setCellStyle(cellStyle);
                    
                    HSSFCell cellR2 = row.createCell((short) 16);
                    cellR2.setCellValue("IPerf3");
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellR2.setCellStyle(cellStyle);

                    if(udpPerfField.isSelected()){
                        HSSFCell cellS2 = row.createCell((short) 17);
                        cellS2.setCellValue(udpPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellS2.setCellStyle(cellStyle);
                    }else if(tcpPerfField.isSelected()){
                        HSSFCell cellS2 = row.createCell((short) 17);
                        cellS2.setCellValue(tcpPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellS2.setCellStyle(cellStyle);
                    }

                    if(clientPerfField.isSelected()){
                        HSSFCell cellT2 = row.createCell((short) 18);
                        cellT2.setCellValue(clientPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellT2.setCellStyle(cellStyle);
                    }else if(serverPerfField.isSelected()){
                        HSSFCell cellT2 = row.createCell((short) 18);
                        cellT2.setCellValue(serverPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellT2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellU2 = row.createCell((short) 19);
                    cellU2.setCellValue(portPerfField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellU2.setCellStyle(cellStyle);

                    HSSFCell cellV2 = row.createCell((short) 20);
                    cellV2.setCellValue(timePerfField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellV2.setCellStyle(cellStyle);

                    System.out.println(resultPath);

                    String perfClientcmd = "ResultToExcel.sh "+resultPath+" "+i+"p";
                    p = Runtime.getRuntime().exec(perfClientcmd);
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String ligne;
                    while ((ligne=streamReader.readLine())!=null)
                    {
                        HSSFCell cellW2 = row.createCell((short) 21);
                        cellW2.setCellValue(ligne);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellW2.setCellStyle(cellStyle);
                    }

                    if(i == Integer.parseInt(timePerfField.getText())+1){
                        HSSFCell cellX2 = row.createCell((short) 22);
                        cellX2.setCellValue("Sender");
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellX2.setCellStyle(cellStyle);
                    }else if(i == Integer.parseInt(timePerfField.getText())+2){
                        HSSFCell cellX2 = row.createCell((short) 22);
                        cellX2.setCellValue("Receiver");
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellX2.setCellStyle(cellStyle);
                    }
                }else if(l2tppppipsecC == true){
                    if(transportl2tppppipsecclient = true){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(transportl2tppppipsecclientString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }else if(tunnell2tppppipsecclient = true){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(tunnell2tppppipsecclientString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }
                    
                    HSSFCell cellM2 = row.createCell((short) 12);
                    cellM2.setCellValue(ikelifetimel2tppppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellM2.setCellStyle(cellStyle);
                    
                    HSSFCell cellN2 = row.createCell((short) 13);
                    cellN2.setCellValue(keylifetimel2tppppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellN2.setCellStyle(cellStyle);
                    
                    HSSFCell cellO2 = row.createCell((short) 14);
                    cellO2.setCellValue(localipl2tppppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellO2.setCellStyle(cellStyle);
                    
                    HSSFCell cellP2 = row.createCell((short) 15);
                    cellP2.setCellValue(serveripl2tppppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellP2.setCellStyle(cellStyle);
                    
                    HSSFCell cellQ2 = row.createCell((short) 16);
                    cellQ2.setCellValue(usernamel2tppppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellQ2.setCellStyle(cellStyle);
                    
                    HSSFCell cellR2 = row.createCell((short) 17);
                    cellR2.setCellValue(passwordl2tppppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellR2.setCellStyle(cellStyle);
                
                    HSSFCell cellS2 = row.createCell((short) 18);
                    cellS2.setCellValue("IPerf3");
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellS2.setCellStyle(cellStyle);

                    if(udpPerfField.isSelected()){
                        HSSFCell cellT2 = row.createCell((short) 19);
                        cellT2.setCellValue(udpPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellT2.setCellStyle(cellStyle);
                    }else if(tcpPerfField.isSelected()){
                        HSSFCell cellT2 = row.createCell((short) 19);
                        cellT2.setCellValue(tcpPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellT2.setCellStyle(cellStyle);
                    }

                    if(clientPerfField.isSelected()){
                        HSSFCell cellU2 = row.createCell((short) 20);
                        cellU2.setCellValue(clientPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellU2.setCellStyle(cellStyle);
                    }else if(serverPerfField.isSelected()){
                        HSSFCell cellU2 = row.createCell((short) 20);
                        cellU2.setCellValue(serverPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellU2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellV2 = row.createCell((short) 21);
                    cellV2.setCellValue(portPerfField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellV2.setCellStyle(cellStyle);

                    HSSFCell cellW2 = row.createCell((short) 22);
                    cellW2.setCellValue(timePerfField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellW2.setCellStyle(cellStyle);

                    System.out.println(resultPath);

                    String perfClientcmd = "ResultToExcel.sh "+resultPath+" "+i+"p";
                    p = Runtime.getRuntime().exec(perfClientcmd);
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String ligne;
                    while ((ligne=streamReader.readLine())!=null)
                    {
                        HSSFCell cellX2 = row.createCell((short) 23);
                        cellX2.setCellValue(ligne);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellX2.setCellStyle(cellStyle);
                    }

                    if(i == Integer.parseInt(timePerfField.getText())+1){
                        HSSFCell cellY2 = row.createCell((short) 24);
                        cellY2.setCellValue("Sender");
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellY2.setCellStyle(cellStyle);
                    }else if(i == Integer.parseInt(timePerfField.getText())+2){
                        HSSFCell cellY2 = row.createCell((short) 24);
                        cellY2.setCellValue("Receiver");
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellY2.setCellStyle(cellStyle);
                    }
                }else if(pppoepppipC == true){
                    if(pappppoepppipclient){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(pappppoepppipclientString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }else if(chappppoepppipclient){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(chappppoepppipclientString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }
                    
                    HSSFCell cellM2 = row.createCell((short) 12);
                    cellM2.setCellValue(usernamepppoepppipclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellM2.setCellStyle(cellStyle);
                    
                    HSSFCell cellN2 = row.createCell((short) 13);
                    cellN2.setCellValue(passwordpppoepppipclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellN2.setCellStyle(cellStyle);
                    
                    HSSFCell cellP2 = row.createCell((short) 14);
                    cellP2.setCellValue("IPerf3");
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellP2.setCellStyle(cellStyle);

                    if(udpPerfField.isSelected()){
                        HSSFCell cellQ2 = row.createCell((short) 15);
                        cellQ2.setCellValue(udpPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellQ2.setCellStyle(cellStyle);
                    }else if(tcpPerfField.isSelected()){
                        HSSFCell cellQ2 = row.createCell((short) 15);
                        cellQ2.setCellValue(tcpPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellQ2.setCellStyle(cellStyle);
                    }

                    if(clientPerfField.isSelected()){
                        HSSFCell cellR2 = row.createCell((short) 16);
                        cellR2.setCellValue(clientPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellR2.setCellStyle(cellStyle);
                    }else if(serverPerfField.isSelected()){
                        HSSFCell cellR2 = row.createCell((short) 16);
                        cellR2.setCellValue(serverPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellR2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellS2 = row.createCell((short) 17);
                    cellS2.setCellValue(portPerfField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellS2.setCellStyle(cellStyle);

                    HSSFCell cellT2 = row.createCell((short) 17);
                    cellT2.setCellValue(timePerfField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellT2.setCellStyle(cellStyle);

                    System.out.println(resultPath);

                    String perfClientcmd = "ResultToExcel.sh "+resultPath+" "+i+"p";
                    p = Runtime.getRuntime().exec(perfClientcmd);
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String ligne;
                    while ((ligne=streamReader.readLine())!=null)
                    {
                        HSSFCell cellU2 = row.createCell((short) 18);
                        cellU2.setCellValue(ligne);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellU2.setCellStyle(cellStyle);
                    }

                    if(i == Integer.parseInt(timePerfField.getText())+1){
                        HSSFCell cellV2 = row.createCell((short) 19);
                        cellV2.setCellValue("Sender");
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellV2.setCellStyle(cellStyle);
                    }else if(i == Integer.parseInt(timePerfField.getText())+2){
                        HSSFCell cellV2 = row.createCell((short) 19);
                        cellV2.setCellValue("Receiver");
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellV2.setCellStyle(cellStyle);
                    }
                }else if(pppoepppipsecC == true){
                    if(pappppoepppipsecclient){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(pappppoepppipsecclientString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }else if(chappppoepppipsecclient){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(chappppoepppipsecclientString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }
                    
                    HSSFCell cellM2 = row.createCell((short) 12);
                    cellM2.setCellValue(usernamepppoepppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellM2.setCellStyle(cellStyle);
                    
                    HSSFCell cellN2 = row.createCell((short) 13);
                    cellN2.setCellValue(passwordpppoepppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellN2.setCellStyle(cellStyle);
                    
                    HSSFCell cellO2 = row.createCell((short) 14);
                    cellO2.setCellValue(ikelifetimepppoepppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellO2.setCellStyle(cellStyle);
                    
                    HSSFCell cellP2 = row.createCell((short) 15);
                    cellP2.setCellValue(keylifetimepppoepppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellP2.setCellStyle(cellStyle);
                    
                    HSSFCell cellQ2 = row.createCell((short) 16);
                    cellQ2.setCellValue(keyexchangepppoepppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellQ2.setCellStyle(cellStyle);
                    
                    HSSFCell cellR2 = row.createCell((short) 17);
                    cellR2.setCellValue(localippppoepppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellR2.setCellStyle(cellStyle);
                    
                    HSSFCell cellS2 = row.createCell((short) 18);
                    cellS2.setCellValue(localnetworkpppoepppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellS2.setCellStyle(cellStyle);
                    
                    HSSFCell cellT2 = row.createCell((short) 19);
                    cellT2.setCellValue(remoteippppoepppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellT2.setCellStyle(cellStyle);
                    
                    HSSFCell cellU2 = row.createCell((short) 20);
                    cellU2.setCellValue(remotenetworkpppoepppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellU2.setCellStyle(cellStyle);
                
                    HSSFCell cellV2 = row.createCell((short) 21);
                    cellV2.setCellValue("IPerf3");
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellV2.setCellStyle(cellStyle);

                    if(udpPerfField.isSelected()){
                        HSSFCell cellW2 = row.createCell((short) 22);
                        cellW2.setCellValue(udpPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellW2.setCellStyle(cellStyle);
                    }else if(tcpPerfField.isSelected()){
                        HSSFCell cellW2 = row.createCell((short) 22);
                        cellW2.setCellValue(tcpPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellW2.setCellStyle(cellStyle);
                    }

                    if(clientPerfField.isSelected()){
                        HSSFCell cellX2 = row.createCell((short) 23);
                        cellX2.setCellValue(clientPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellX2.setCellStyle(cellStyle);
                    }else if(serverPerfField.isSelected()){
                        HSSFCell cellX2 = row.createCell((short) 23);
                        cellX2.setCellValue(serverPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellX2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellY2 = row.createCell((short) 24);
                    cellY2.setCellValue(portPerfField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellY2.setCellStyle(cellStyle);

                    HSSFCell cellZ2 = row.createCell((short) 25);
                    cellZ2.setCellValue(timePerfField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellZ2.setCellStyle(cellStyle);

                    System.out.println(resultPath);

                    String perfClientcmd = "ResultToExcel.sh "+resultPath+" "+i+"p";
                    p = Runtime.getRuntime().exec(perfClientcmd);
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String ligne;
                    while ((ligne=streamReader.readLine())!=null)
                    {
                        HSSFCell cellAA2 = row.createCell((short) 26);
                        cellAA2.setCellValue(ligne);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellAA2.setCellStyle(cellStyle);
                    }

                    if(i == Integer.parseInt(timePerfField.getText())+1){
                        HSSFCell cellAB2 = row.createCell((short) 27);
                        cellAB2.setCellValue("Sender");
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellAB2.setCellStyle(cellStyle);
                    }else if(i == Integer.parseInt(timePerfField.getText())+2){
                        HSSFCell cellAB2 = row.createCell((short) 27);
                        cellAB2.setCellValue("Receiver");
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellAB2.setCellStyle(cellStyle);
                    }
                }else if(pptpC == true){
                    HSSFCell cellL2 = row.createCell((short) 11);
                    cellL2.setCellValue(serverippptpclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellL2.setCellStyle(cellStyle);
                    
                    HSSFCell cellM2 = row.createCell((short) 12);
                    cellM2.setCellValue(usernamepptpclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellM2.setCellStyle(cellStyle);
                    
                    HSSFCell cellN2 = row.createCell((short) 13);
                    cellN2.setCellValue(passwordpptpclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellN2.setCellStyle(cellStyle);
                
                    HSSFCell cellO2 = row.createCell((short) 14);
                    cellO2.setCellValue("IPerf3");
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellO2.setCellStyle(cellStyle);

                    if(udpPerfField.isSelected()){
                        HSSFCell cellP2 = row.createCell((short) 15);
                        cellP2.setCellValue(udpPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellP2.setCellStyle(cellStyle);
                    }else if(tcpPerfField.isSelected()){
                        HSSFCell cellP2 = row.createCell((short) 15);
                        cellP2.setCellValue(tcpPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellP2.setCellStyle(cellStyle);
                    }

                    if(clientPerfField.isSelected()){
                        HSSFCell cellQ2 = row.createCell((short) 16);
                        cellQ2.setCellValue(clientPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellQ2.setCellStyle(cellStyle);
                    }else if(serverPerfField.isSelected()){
                        HSSFCell cellQ2 = row.createCell((short) 16);
                        cellQ2.setCellValue(serverPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellQ2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellR2 = row.createCell((short) 17);
                    cellR2.setCellValue(portPerfField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellR2.setCellStyle(cellStyle);

                    HSSFCell cellS2 = row.createCell((short) 18);
                    cellS2.setCellValue(timePerfField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellS2.setCellStyle(cellStyle);

                    System.out.println(resultPath);

                    String perfClientcmd = "ResultToExcel.sh "+resultPath+" "+i+"p";
                    p = Runtime.getRuntime().exec(perfClientcmd);
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String ligne;
                    while ((ligne=streamReader.readLine())!=null)
                    {
                        HSSFCell cellT2 = row.createCell((short) 19);
                        cellT2.setCellValue(ligne);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellT2.setCellStyle(cellStyle);
                    }

                    if(i == Integer.parseInt(timePerfField.getText())+1){
                        HSSFCell cellU2 = row.createCell((short) 20);
                        cellU2.setCellValue("Sender");
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellU2.setCellStyle(cellStyle);
                    }else if(i == Integer.parseInt(timePerfField.getText())+2){
                        HSSFCell cellU2 = row.createCell((short) 20);
                        cellU2.setCellValue("Receiver");
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellU2.setCellStyle(cellStyle);
                    }
                }else if(greC == true){
                    HSSFCell cellL2 = row.createCell((short) 11);
                    cellL2.setCellValue(localipgreclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellL2.setCellStyle(cellStyle);
                    
                    HSSFCell cellM2 = row.createCell((short) 12);
                    cellM2.setCellValue(remoteipgreclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellM2.setCellStyle(cellStyle);
                    
                    HSSFCell cellN2 = row.createCell((short) 13);
                    cellN2.setCellValue(remotenetworkgreclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellN2.setCellStyle(cellStyle);
                
                    HSSFCell cellO2 = row.createCell((short) 14);
                    cellO2.setCellValue(greipgreclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellO2.setCellStyle(cellStyle);
                
                    HSSFCell cellP2 = row.createCell((short) 15);
                    cellP2.setCellValue("IPerf3");
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellP2.setCellStyle(cellStyle);

                    if(udpPerfField.isSelected()){
                        HSSFCell cellQ2 = row.createCell((short) 16);
                        cellQ2.setCellValue(udpPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellQ2.setCellStyle(cellStyle);
                    }else if(tcpPerfField.isSelected()){
                        HSSFCell cellQ2 = row.createCell((short) 16);
                        cellQ2.setCellValue(tcpPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellQ2.setCellStyle(cellStyle);
                    }

                    if(clientPerfField.isSelected()){
                        HSSFCell cellR2 = row.createCell((short) 17);
                        cellR2.setCellValue(clientPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellR2.setCellStyle(cellStyle);
                    }else if(serverPerfField.isSelected()){
                        HSSFCell cellR2 = row.createCell((short) 17);
                        cellR2.setCellValue(serverPerfField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellR2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellS2 = row.createCell((short) 18);
                    cellS2.setCellValue(portPerfField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellS2.setCellStyle(cellStyle);

                    HSSFCell cellT2 = row.createCell((short) 19);
                    cellT2.setCellValue(timePerfField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellT2.setCellStyle(cellStyle);

                    System.out.println(resultPath);

                    String perfClientcmd = "ResultToExcel.sh "+resultPath+" "+i+"p";
                    p = Runtime.getRuntime().exec(perfClientcmd);
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String ligne;
                    while ((ligne=streamReader.readLine())!=null)
                    {
                        HSSFCell cellU2 = row.createCell((short) 20);
                        cellU2.setCellValue(ligne);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellU2.setCellStyle(cellStyle);
                    }

                    if(i == Integer.parseInt(timePerfField.getText())+1){
                        HSSFCell cellV2 = row.createCell((short) 21);
                        cellV2.setCellValue("Sender");
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellV2.setCellStyle(cellStyle);
                    }else if(i == Integer.parseInt(timePerfField.getText())+2){
                        HSSFCell cellV2 = row.createCell((short) 21);
                        cellV2.setCellValue("Receiver");
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellV2.setCellStyle(cellStyle);
                    }
                }
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    @SuppressWarnings("all") // Ligne 414
    private void SaveFileFTP(){
        try {
            FileOutputStream fileOut = new FileOutputStream("FTPResults.xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("Exported Results");

            // index from 0,0... cell A1 is cell(0,0)
            HSSFRow row1 = worksheet.createRow((short) 0);
            
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            HSSFCellStyle cellStyle1 = workbook.createCellStyle();
            HSSFCellStyle cellStyle2 = workbook.createCellStyle();
            HSSFCellStyle cellStyle3 = workbook.createCellStyle();
            HSSFCellStyle cellStyle4 = workbook.createCellStyle();
            
            HSSFCell cellA1 = row1.createCell((short) 0);
            cellA1.setCellValue("Date");
            cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellA1.setCellStyle(cellStyle);

            HSSFCell cellB1 = row1.createCell((short) 1);
            cellB1.setCellValue("Matricule");
            cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellB1.setCellStyle(cellStyle);
            
            HSSFCell cellC1 = row1.createCell((short) 2);
            cellC1.setCellValue("Nom & Prenom");
            cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellC1.setCellStyle(cellStyle);
            
            HSSFCell cellD1 = row1.createCell((short) 3);
            cellD1.setCellValue("Company");
            cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellD1.setCellStyle(cellStyle);
            
            HSSFCell cellE1 = row1.createCell((short) 4);
            cellE1.setCellValue("Site");
            cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellE1.setCellStyle(cellStyle);
            
            HSSFCell cellF1 = row1.createCell((short) 5);
            cellF1.setCellValue("Model");
            cellStyle1.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
            cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellF1.setCellStyle(cellStyle1);
            
            HSSFCell cellG1 = row1.createCell((short) 6);
            cellG1.setCellValue("Software Version");
            cellStyle1.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
            cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellG1.setCellStyle(cellStyle1);
            
            HSSFCell cellH1 = row1.createCell((short) 7);
            cellH1.setCellValue("S/N");
            cellStyle1.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
            cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellH1.setCellStyle(cellStyle1);
            
            HSSFCell cellI1 = row1.createCell((short) 8);
            cellI1.setCellValue("HW Version");
            cellStyle1.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
            cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellI1.setCellStyle(cellStyle1);
            
            HSSFCell cellJ1 = row1.createCell((short) 9);
            cellJ1.setCellValue("GWY IP");
            cellStyle1.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
            cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellJ1.setCellStyle(cellStyle1);
            
            HSSFCell cellK1 = row1.createCell((short) 10);
            cellK1.setCellValue("GWY MAC");
            cellStyle1.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
            cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellK1.setCellStyle(cellStyle1);
            
            if(l2tpipsecS == true){
                HSSFCell cellL1 = row1.createCell((short) 11);
                cellL1.setCellValue("Configuration Side");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellL1.setCellStyle(cellStyle2);
                
                HSSFCell cellM1 = row1.createCell((short) 12);
                cellM1.setCellValue("Configuration Type");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellM1.setCellStyle(cellStyle2);
                
                HSSFCell cellN1 = row1.createCell((short) 13);
                cellN1.setCellValue("IKE Lifetime");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellN1.setCellStyle(cellStyle2);
                
                HSSFCell cellO1 = row1.createCell((short) 14);
                cellO1.setCellValue("KEY Lifetime");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellO1.setCellStyle(cellStyle2);
                
                HSSFCell cellP1 = row1.createCell((short) 15);
                cellP1.setCellValue("KEY Exchange");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellP1.setCellStyle(cellStyle2);
                
                HSSFCell cellQ1 = row1.createCell((short) 16);
                cellQ1.setCellValue("Local IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle2);
                
                HSSFCell cellR1 = row1.createCell((short) 17);
                cellR1.setCellValue("Local Network");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellR1.setCellStyle(cellStyle2);
                
                HSSFCell cellS1 = row1.createCell((short) 18);
                cellS1.setCellValue("Remote IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellS1.setCellStyle(cellStyle2);
                
                HSSFCell cellT1 = row1.createCell((short) 19);
                cellT1.setCellValue("Remote Network");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellT1.setCellStyle(cellStyle2);
                
                HSSFCell cellU1 = row1.createCell((short) 20);
                cellU1.setCellValue("Type of Test");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellU1.setCellStyle(cellStyle3);

                HSSFCell cellV1 = row1.createCell((short) 21);
                cellV1.setCellValue("File Name");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellV1.setCellStyle(cellStyle3);

                HSSFCell cellW1 = row1.createCell((short) 22);
                cellW1.setCellValue("File Size");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellW1.setCellStyle(cellStyle3);

                HSSFCell cellX1 = row1.createCell((short) 23);
                cellX1.setCellValue("Average Download");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellX1.setCellStyle(cellStyle4);

                HSSFCell cellY1 = row1.createCell((short) 24);
                cellY1.setCellValue("Speed Upload");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellY1.setCellStyle(cellStyle4);
            }else if(l2tppppipsecS == true){
                HSSFCell cellL1 = row1.createCell((short) 11);
                cellL1.setCellValue("Configuration Type");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellL1.setCellStyle(cellStyle2);
                
                HSSFCell cellM1 = row1.createCell((short) 12);
                cellM1.setCellValue("Server IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellM1.setCellStyle(cellStyle2);
                
                HSSFCell cellN1 = row1.createCell((short) 13);
                cellN1.setCellValue("Username");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellN1.setCellStyle(cellStyle2);
                
                HSSFCell cellO1 = row1.createCell((short) 14);
                cellO1.setCellValue("Password");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellO1.setCellStyle(cellStyle2);
                
                HSSFCell cellP1 = row1.createCell((short) 15);
                cellP1.setCellValue("Type of Test");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellP1.setCellStyle(cellStyle3);

                HSSFCell cellQ1 = row1.createCell((short) 16);
                cellQ1.setCellValue("File Name");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle3);

                HSSFCell cellR1 = row1.createCell((short) 17);
                cellR1.setCellValue("File Size");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellR1.setCellStyle(cellStyle3);

                HSSFCell cellS1 = row1.createCell((short) 18);
                cellS1.setCellValue("Average Download");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellS1.setCellStyle(cellStyle4);

                HSSFCell cellT1 = row1.createCell((short) 19);
                cellT1.setCellValue("Speed Upload");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellT1.setCellStyle(cellStyle4);
            }else if(pppoepppipsecS == true){
                HSSFCell cellL1 = row1.createCell((short) 11);
                cellL1.setCellValue("IKE Lifetime");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellL1.setCellStyle(cellStyle2);
                
                HSSFCell cellM1 = row1.createCell((short) 12);
                cellM1.setCellValue("KEY Lifetime");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellM1.setCellStyle(cellStyle2);
                
                HSSFCell cellN1 = row1.createCell((short) 13);
                cellN1.setCellValue("KEY Exchange");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellN1.setCellStyle(cellStyle2);
                
                HSSFCell cellO1 = row1.createCell((short) 14);
                cellO1.setCellValue("Local IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellO1.setCellStyle(cellStyle2);
                
                HSSFCell cellP1 = row1.createCell((short) 15);
                cellP1.setCellValue("Local Network");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellP1.setCellStyle(cellStyle2);
                
                HSSFCell cellQ1 = row1.createCell((short) 16);
                cellQ1.setCellValue("Remote IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle2);
                
                HSSFCell cellR1 = row1.createCell((short) 17);
                cellR1.setCellValue("Remote Network");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellR1.setCellStyle(cellStyle2);
                
                HSSFCell cellS1 = row1.createCell((short) 18);
                cellS1.setCellValue("Type of Test");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellS1.setCellStyle(cellStyle3);

                HSSFCell cellT1 = row1.createCell((short) 19);
                cellT1.setCellValue("File Name");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellT1.setCellStyle(cellStyle3);

                HSSFCell cellU1 = row1.createCell((short) 20);
                cellU1.setCellValue("File Size");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellU1.setCellStyle(cellStyle3);

                HSSFCell cellV1 = row1.createCell((short) 21);
                cellV1.setCellValue("Average Download");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellV1.setCellStyle(cellStyle4);

                HSSFCell cellW1 = row1.createCell((short) 22);
                cellW1.setCellValue("Speed Upload");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellW1.setCellStyle(cellStyle4);
            }else if(pptpS == true){
                HSSFCell cellL1 = row1.createCell((short) 11);
                cellL1.setCellValue("Server IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellL1.setCellStyle(cellStyle2);
                
                HSSFCell cellM1 = row1.createCell((short) 12);
                cellM1.setCellValue("Client Pool");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellM1.setCellStyle(cellStyle2);
                
                HSSFCell cellN1 = row1.createCell((short) 13);
                cellN1.setCellValue("VPN Network");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellN1.setCellStyle(cellStyle2);
                
                HSSFCell cellO1 = row1.createCell((short) 14);
                cellO1.setCellValue("Username");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellO1.setCellStyle(cellStyle2);
                
                HSSFCell cellP1 = row1.createCell((short) 15);
                cellP1.setCellValue("Password");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellP1.setCellStyle(cellStyle2);
                
                HSSFCell cellQ1 = row1.createCell((short) 16);
                cellQ1.setCellValue("Type of Test");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle3);

                HSSFCell cellR1 = row1.createCell((short) 17);
                cellR1.setCellValue("File Name");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellR1.setCellStyle(cellStyle3);

                HSSFCell cellS1 = row1.createCell((short) 18);
                cellS1.setCellValue("File Size");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellS1.setCellStyle(cellStyle3);
                
                HSSFCell cellT1 = row1.createCell((short) 19);
                cellT1.setCellValue("Average Download");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellT1.setCellStyle(cellStyle4);

                HSSFCell cellU1 = row1.createCell((short) 20);
                cellU1.setCellValue("Speed Upload");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellU1.setCellStyle(cellStyle4);
            }else if(greS == true){
                HSSFCell cellL1 = row1.createCell((short) 11);
                cellL1.setCellValue("Local IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellL1.setCellStyle(cellStyle2);
                
                HSSFCell cellM1 = row1.createCell((short) 12);
                cellM1.setCellValue("Remote IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellM1.setCellStyle(cellStyle2);
                
                HSSFCell cellN1 = row1.createCell((short) 13);
                cellN1.setCellValue("Remote Network");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellN1.setCellStyle(cellStyle2);
                
                HSSFCell cellO1 = row1.createCell((short) 14);
                cellO1.setCellValue("GRE IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellO1.setCellStyle(cellStyle2);
                
                HSSFCell cellP1 = row1.createCell((short) 15);
                cellP1.setCellValue("Type of Test");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellP1.setCellStyle(cellStyle3);

                HSSFCell cellQ1 = row1.createCell((short) 16);
                cellQ1.setCellValue("File Name");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle3);

                HSSFCell cellR1 = row1.createCell((short) 17);
                cellR1.setCellValue("File Size");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellR1.setCellStyle(cellStyle3);

                HSSFCell cellS1 = row1.createCell((short) 18);
                cellS1.setCellValue("Average Download");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellS1.setCellStyle(cellStyle4);

                HSSFCell cellT1 = row1.createCell((short) 19);
                cellT1.setCellValue("Speed Upload");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellT1.setCellStyle(cellStyle4);
            }else if(l2tpipsecC == true){
                HSSFCell cellL1 = row1.createCell((short) 11);
                cellL1.setCellValue("Configuration Type");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellL1.setCellStyle(cellStyle2);
                
                HSSFCell cellM1 = row1.createCell((short) 12);
                cellM1.setCellValue("IKE Lifetime");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellM1.setCellStyle(cellStyle2);
                
                HSSFCell cellN1 = row1.createCell((short) 13);
                cellN1.setCellValue("KEY Lifetime");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellN1.setCellStyle(cellStyle2);
                
                HSSFCell cellO1 = row1.createCell((short) 14);
                cellO1.setCellValue("Local IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellO1.setCellStyle(cellStyle2);
                
                HSSFCell cellP1 = row1.createCell((short) 15);
                cellP1.setCellValue("Server IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellP1.setCellStyle(cellStyle2);
                
                HSSFCell cellQ1 = row1.createCell((short) 16);
                cellQ1.setCellValue("Type of Test");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle3);

                HSSFCell cellR1 = row1.createCell((short) 17);
                cellR1.setCellValue("File Name");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellR1.setCellStyle(cellStyle3);

                HSSFCell cellS1 = row1.createCell((short) 18);
                cellS1.setCellValue("File Size");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellS1.setCellStyle(cellStyle3);

                HSSFCell cellT1 = row1.createCell((short) 19);
                cellT1.setCellValue("Average Download");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellT1.setCellStyle(cellStyle4);

                HSSFCell cellU1 = row1.createCell((short) 20);
                cellU1.setCellValue("Speed Upload");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellU1.setCellStyle(cellStyle4);
            }else if(l2tppppipsecC == true){
                HSSFCell cellL1 = row1.createCell((short) 11);
                cellL1.setCellValue("Configuration Type");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellL1.setCellStyle(cellStyle2);
                
                HSSFCell cellM1 = row1.createCell((short) 12);
                cellM1.setCellValue("IKE Lifetime");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellM1.setCellStyle(cellStyle2);
                
                HSSFCell cellN1 = row1.createCell((short) 13);
                cellN1.setCellValue("KEY Lifetime");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellN1.setCellStyle(cellStyle2);
                
                HSSFCell cellO1 = row1.createCell((short) 14);
                cellO1.setCellValue("Local IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellO1.setCellStyle(cellStyle2);
                
                HSSFCell cellP1 = row1.createCell((short) 15);
                cellP1.setCellValue("Server IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellP1.setCellStyle(cellStyle2);
                
                HSSFCell cellQ1 = row1.createCell((short) 16);
                cellQ1.setCellValue("Username");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle2);
                
                HSSFCell cellR1 = row1.createCell((short) 17);
                cellQ1.setCellValue("Password");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle2);
                
                HSSFCell cellS1 = row1.createCell((short) 18);
                cellS1.setCellValue("Type of Test");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellS1.setCellStyle(cellStyle3);

                HSSFCell cellT1 = row1.createCell((short) 19);
                cellT1.setCellValue("File Name");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellT1.setCellStyle(cellStyle3);

                HSSFCell cellU1 = row1.createCell((short) 20);
                cellU1.setCellValue("File Size");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellU1.setCellStyle(cellStyle3);

                HSSFCell cellV1 = row1.createCell((short) 21);
                cellV1.setCellValue("Average Download");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellV1.setCellStyle(cellStyle4);

                HSSFCell cellW1 = row1.createCell((short) 22);
                cellW1.setCellValue("Speed Upload");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellW1.setCellStyle(cellStyle4);
            }else if(pppoepppipC == true){
                HSSFCell cellL1 = row1.createCell((short) 11);
                cellL1.setCellValue("Authentication Type");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellL1.setCellStyle(cellStyle2);
                
                HSSFCell cellM1 = row1.createCell((short) 12);
                cellM1.setCellValue("Username");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellM1.setCellStyle(cellStyle2);
                
                HSSFCell cellN1 = row1.createCell((short) 13);
                cellN1.setCellValue("Password");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellN1.setCellStyle(cellStyle2);
                
                HSSFCell cellO1 = row1.createCell((short) 14);
                cellO1.setCellValue("Type of Test");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellO1.setCellStyle(cellStyle3);

                HSSFCell cellP1 = row1.createCell((short) 15);
                cellP1.setCellValue("File Name");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellP1.setCellStyle(cellStyle3);

                HSSFCell cellQ1 = row1.createCell((short) 16);
                cellQ1.setCellValue("File Size");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle3);

                HSSFCell cellR1 = row1.createCell((short) 17);
                cellR1.setCellValue("Average Download");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellR1.setCellStyle(cellStyle4);

                HSSFCell cellS1 = row1.createCell((short) 18);
                cellS1.setCellValue("Speed Upload");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellS1.setCellStyle(cellStyle4);
            }else if(pppoepppipsecC == true){
                HSSFCell cellL1 = row1.createCell((short) 11);
                cellL1.setCellValue("Authentication Type");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellL1.setCellStyle(cellStyle2);
                
                HSSFCell cellM1 = row1.createCell((short) 12);
                cellM1.setCellValue("Username");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellM1.setCellStyle(cellStyle2);
                
                HSSFCell cellN1 = row1.createCell((short) 13);
                cellN1.setCellValue("Password");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellN1.setCellStyle(cellStyle2);
                
                HSSFCell cellO1 = row1.createCell((short) 14);
                cellO1.setCellValue("IKE Lifetime");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellO1.setCellStyle(cellStyle2);
                
                HSSFCell cellP1 = row1.createCell((short) 15);
                cellP1.setCellValue("KEY Lifetime");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellP1.setCellStyle(cellStyle2);
                
                HSSFCell cellQ1 = row1.createCell((short) 16);
                cellQ1.setCellValue("KEY Exchange");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle2);
                
                HSSFCell cellR1 = row1.createCell((short) 17);
                cellR1.setCellValue("Local IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellR1.setCellStyle(cellStyle2);
                
                HSSFCell cellS1 = row1.createCell((short) 18);
                cellS1.setCellValue("Local Network");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellS1.setCellStyle(cellStyle2);
                
                HSSFCell cellT1 = row1.createCell((short) 19);
                cellT1.setCellValue("Remote IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellT1.setCellStyle(cellStyle2);
                
                HSSFCell cellU1 = row1.createCell((short) 20);
                cellU1.setCellValue("Remote Network");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellU1.setCellStyle(cellStyle2);
                
                HSSFCell cellV1 = row1.createCell((short) 21);
                cellV1.setCellValue("Type of Test");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellV1.setCellStyle(cellStyle3);

                HSSFCell cellW1 = row1.createCell((short) 22);
                cellW1.setCellValue("File Name");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellW1.setCellStyle(cellStyle3);

                HSSFCell cellX1 = row1.createCell((short) 23);
                cellX1.setCellValue("File Size");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellX1.setCellStyle(cellStyle3);

                HSSFCell cellY1 = row1.createCell((short) 24);
                cellY1.setCellValue("Average Download");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellY1.setCellStyle(cellStyle4);

                HSSFCell cellZ1 = row1.createCell((short) 25);
                cellZ1.setCellValue("Speed Upload");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellZ1.setCellStyle(cellStyle4);
            }else if(pptpC == true){
                HSSFCell cellL1 = row1.createCell((short) 11);
                cellL1.setCellValue("Server IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellL1.setCellStyle(cellStyle2);
                
                HSSFCell cellM1 = row1.createCell((short) 12);
                cellM1.setCellValue("Username");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellM1.setCellStyle(cellStyle2);
                
                HSSFCell cellN1 = row1.createCell((short) 13);
                cellN1.setCellValue("Password");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellN1.setCellStyle(cellStyle2);
                
                HSSFCell cellO1 = row1.createCell((short) 14);
                cellO1.setCellValue("Type of Test");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellO1.setCellStyle(cellStyle3);

                HSSFCell cellP1 = row1.createCell((short) 15);
                cellP1.setCellValue("File Name");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellP1.setCellStyle(cellStyle3);

                HSSFCell cellQ1 = row1.createCell((short) 16);
                cellQ1.setCellValue("File Size");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle3);

                HSSFCell cellR1 = row1.createCell((short) 17);
                cellR1.setCellValue("Average Download");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellR1.setCellStyle(cellStyle4);

                HSSFCell cellS1 = row1.createCell((short) 18);
                cellS1.setCellValue("Speed Upload");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellS1.setCellStyle(cellStyle4);
            }else if(greC == true){
                HSSFCell cellL1 = row1.createCell((short) 11);
                cellL1.setCellValue("Local IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellL1.setCellStyle(cellStyle2);
                
                HSSFCell cellM1 = row1.createCell((short) 12);
                cellM1.setCellValue("Remote IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellM1.setCellStyle(cellStyle2);
                
                HSSFCell cellN1 = row1.createCell((short) 13);
                cellN1.setCellValue("Remote Network");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellN1.setCellStyle(cellStyle2);
                
                HSSFCell cellO1 = row1.createCell((short) 14);
                cellO1.setCellValue("GRE IP");
                cellStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellO1.setCellStyle(cellStyle2);
                
                HSSFCell cellP1 = row1.createCell((short) 15);
                cellP1.setCellValue("Type of Test");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellP1.setCellStyle(cellStyle3);

                HSSFCell cellQ1 = row1.createCell((short) 16);
                cellQ1.setCellValue("File Name");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellQ1.setCellStyle(cellStyle3);

                HSSFCell cellR1 = row1.createCell((short) 17);
                cellR1.setCellValue("File Size");
                cellStyle3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellR1.setCellStyle(cellStyle3);

                HSSFCell cellS1 = row1.createCell((short) 18);
                cellS1.setCellValue("Average Download");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellS1.setCellStyle(cellStyle4);

                HSSFCell cellT1 = row1.createCell((short) 19);
                cellT1.setCellValue("Speed Upload");
                cellStyle4.setFillForegroundColor(HSSFColor.MAROON.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellT1.setCellStyle(cellStyle4);
            }
            
            for(int i=1; i<=Integer.parseInt(nombreftpTest.getText()); i++){
                
                HSSFRow row = worksheet.createRow((short) i);
                
                HSSFCell cellA2 = row.createCell((short) 0);
                cellA2.setCellValue(variableDate);
                cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                cellA2.setCellStyle(cellStyle);

                HSSFCell cellB2 = row.createCell((short) 1);
                cellB2.setCellValue(variableMatricule);
                cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                cellB2.setCellStyle(cellStyle);

                HSSFCell cellC2 = row.createCell((short) 2);
                cellC2.setCellValue(variableNomPrenom);
                cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                cellC2.setCellStyle(cellStyle);
                
                HSSFCell cellD2 = row.createCell((short) 3);
                cellD2.setCellValue(variableCompany);
                cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                cellD2.setCellStyle(cellStyle);
                
                HSSFCell cellE2 = row.createCell((short) 4);
                cellE2.setCellValue(variableSite);
                cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                cellE2.setCellStyle(cellStyle);
                
                HSSFCell cellF2 = row.createCell((short) 5);
                cellF2.setCellValue(variableModel);
                cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                cellF2.setCellStyle(cellStyle);
                
                HSSFCell cellG2 = row.createCell((short) 6);
                cellG2.setCellValue(variableSoftVersion);
                cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                cellG2.setCellStyle(cellStyle);
                
                HSSFCell cellH2 = row.createCell((short) 7);
                cellH2.setCellValue(variableSerialNumber);
                cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                cellH2.setCellStyle(cellStyle);
                
                HSSFCell cellI2 = row.createCell((short) 8);
                cellI2.setCellValue(variableHWVersion);
                cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                cellI2.setCellStyle(cellStyle);
                
                HSSFCell cellJ2 = row.createCell((short) 9);
                cellJ2.setCellValue(variableGWYIPAddr);
                cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                cellJ2.setCellStyle(cellStyle);
                
                HSSFCell cellK2 = row.createCell((short) 10);
                cellK2.setCellValue(variableMACAddr);
                cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                cellK2.setCellStyle(cellStyle);
                
                if(l2tpipsecS == true){
                    if(clientsidel2tpipsecserver = true){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(clientsidel2tpipsecserverString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }else if(wansidel2tpipsecserver = true){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(wansidel2tpipsecserverString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }
                    
                    if(transportl2tpipsecserver = true){
                        HSSFCell cellM2 = row.createCell((short) 12);
                        cellM2.setCellValue(transportl2tpipsecserverString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellM2.setCellStyle(cellStyle);
                    }else if(tunnell2tpipsecserver = true){
                        HSSFCell cellM2 = row.createCell((short) 12);
                        cellM2.setCellValue(tunnell2tpipsecserverString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellM2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellN2 = row.createCell((short) 13);
                    cellN2.setCellValue(ikelifetimel2tpipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellN2.setCellStyle(cellStyle);

                    HSSFCell cellO2 = row.createCell((short) 14);
                    cellO2.setCellValue(keylifetimel2tpipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellO2.setCellStyle(cellStyle);

                    HSSFCell cellP2 = row.createCell((short) 15);
                    cellP2.setCellValue(keyexchangel2tpipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellP2.setCellStyle(cellStyle);

                    HSSFCell cellQ2 = row.createCell((short) 16);
                    cellQ2.setCellValue(localipl2tpipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellQ2.setCellStyle(cellStyle);

                    HSSFCell cellR2 = row.createCell((short) 17);
                    cellR2.setCellValue(localnetworkl2tpipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellR2.setCellStyle(cellStyle);

                    HSSFCell cellS2 = row.createCell((short) 18);
                    cellS2.setCellValue(remoteipl2tpipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellS2.setCellStyle(cellStyle);

                    HSSFCell cellT2 = row.createCell((short) 19);
                    cellT2.setCellValue(remotenetworkl2tpipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellT2.setCellStyle(cellStyle);
                    
                    if(getftpField.isSelected()){
                        HSSFCell cellU2 = row.createCell((short) 20);
                        cellU2.setCellValue("FTP "+getftpField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellU2.setCellStyle(cellStyle);
                    }else if(putftpField.isSelected()){
                        HSSFCell cellU2 = row.createCell((short) 20);
                        cellU2.setCellValue("FTP "+putftpField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellU2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellV2 = row.createCell((short) 21);
                    cellV2.setCellValue(ftpcmdlineField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellV2.setCellStyle(cellStyle);

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" SIZE");
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String ligne;
                    while ((ligne=streamReader.readLine())!=null)
                    {
                        HSSFCell cellW2 = row.createCell((short) 22);
                        cellW2.setCellValue(ligne);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellW2.setCellStyle(cellStyle);
                    }

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" DOWN");
                    BufferedReader srDown = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String lignesrDown;
                    while ((lignesrDown=srDown.readLine())!=null)
                    {
                        HSSFCell cellX2 = row.createCell((short) 23);
                        cellX2.setCellValue(lignesrDown);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellX2.setCellStyle(cellStyle);
                    }

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" UP");
                    BufferedReader srUP = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String lignesrUP;
                    while ((lignesrUP=srUP.readLine())!=null)
                    {
                        HSSFCell cellY2 = row.createCell((short) 24);
                        cellY2.setCellValue(lignesrUP);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellY2.setCellStyle(cellStyle);
                    }
                }else if(l2tppppipsecS == true){
                    if(transportl2tppppipsecserver = true){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(transportl2tppppipsecserverString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }else if(tunnell2tppppipsecserver = true){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(tunnell2tppppipsecserverString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellM2 = row.createCell((short) 12);
                    cellM2.setCellValue(serveripl2tppppipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellM2.setCellStyle(cellStyle);

                    HSSFCell cellN2 = row.createCell((short) 13);
                    cellN2.setCellValue(usernamel2tppppipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellN2.setCellStyle(cellStyle);

                    HSSFCell cellO2 = row.createCell((short) 14);
                    cellO2.setCellValue(passwordl2tppppipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellO2.setCellStyle(cellStyle);
                
                    if(getftpField.isSelected()){
                        HSSFCell cellP2 = row.createCell((short) 15);
                        cellP2.setCellValue("FTP "+getftpField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellP2.setCellStyle(cellStyle);
                    }else if(putftpField.isSelected()){
                        HSSFCell cellP2 = row.createCell((short) 15);
                        cellP2.setCellValue("FTP "+putftpField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellP2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellQ2 = row.createCell((short) 16);
                    cellQ2.setCellValue(ftpcmdlineField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellQ2.setCellStyle(cellStyle);

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" SIZE");
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String ligne;
                    while ((ligne=streamReader.readLine())!=null)
                    {
                        HSSFCell cellR2 = row.createCell((short) 17);
                        cellR2.setCellValue(ligne);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellR2.setCellStyle(cellStyle);
                    }

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" DOWN");
                    BufferedReader srDown = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String lignesrDown;
                    while ((lignesrDown=srDown.readLine())!=null)
                    {
                        HSSFCell cellS2 = row.createCell((short) 18);
                        cellS2.setCellValue(lignesrDown);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellS2.setCellStyle(cellStyle);
                    }

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" UP");
                    BufferedReader srUP = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String lignesrUP;
                    while ((lignesrUP=srUP.readLine())!=null)
                    {
                        HSSFCell cellT2 = row.createCell((short) 19);
                        cellT2.setCellValue(lignesrUP);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellT2.setCellStyle(cellStyle);
                    }
                }else if(pppoepppipsecS == true){
                    HSSFCell cellL2 = row.createCell((short) 11);
                    cellL2.setCellValue(ikelifetimepppoepppipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellL2.setCellStyle(cellStyle);
                    
                    HSSFCell cellM2 = row.createCell((short) 12);
                    cellM2.setCellValue(keylifetimepppoepppipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellM2.setCellStyle(cellStyle);
                    
                    HSSFCell cellN2 = row.createCell((short) 13);
                    cellN2.setCellValue(keyexchangepppoepppipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellN2.setCellStyle(cellStyle);
                    
                    HSSFCell cellO2 = row.createCell((short) 14);
                    cellO2.setCellValue(localippppoepppipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellO2.setCellStyle(cellStyle);
                    
                    HSSFCell cellP2 = row.createCell((short) 15);
                    cellP2.setCellValue(localnetworkpppoepppipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellP2.setCellStyle(cellStyle);
                    
                    HSSFCell cellQ2 = row.createCell((short) 16);
                    cellQ2.setCellValue(remoteippppoepppipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellQ2.setCellStyle(cellStyle);
                    
                    HSSFCell cellR2 = row.createCell((short) 17);
                    cellR2.setCellValue(remotenetworkpppoepppipsecserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellR2.setCellStyle(cellStyle);
                
                    if(getftpField.isSelected()){
                        HSSFCell cellS2 = row.createCell((short) 18);
                        cellS2.setCellValue("FTP "+getftpField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellS2.setCellStyle(cellStyle);
                    }else if(putftpField.isSelected()){
                        HSSFCell cellS2 = row.createCell((short) 18);
                        cellS2.setCellValue("FTP "+putftpField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellS2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellT2 = row.createCell((short) 19);
                    cellT2.setCellValue(ftpcmdlineField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellT2.setCellStyle(cellStyle);

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" SIZE");
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String ligne;
                    while ((ligne=streamReader.readLine())!=null)
                    {
                        HSSFCell cellU2 = row.createCell((short) 20);
                        cellU2.setCellValue(ligne);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellU2.setCellStyle(cellStyle);
                    }

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" DOWN");
                    BufferedReader srDown = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String lignesrDown;
                    while ((lignesrDown=srDown.readLine())!=null)
                    {
                        HSSFCell cellV2 = row.createCell((short) 21);
                        cellV2.setCellValue(lignesrDown);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellV2.setCellStyle(cellStyle);
                    }

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" UP");
                    BufferedReader srUP = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String lignesrUP;
                    while ((lignesrUP=srUP.readLine())!=null)
                    {
                        HSSFCell cellW2 = row.createCell((short) 22);
                        cellW2.setCellValue(lignesrUP);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellW2.setCellStyle(cellStyle);
                    }
                }else if(pptpS == true){
                    HSSFCell cellL2 = row.createCell((short) 11);
                    cellL2.setCellValue(serverippptpserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellL2.setCellStyle(cellStyle);
                    
                    HSSFCell cellM2 = row.createCell((short) 12);
                    cellM2.setCellValue(clientpoolpptpserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellM2.setCellStyle(cellStyle);
                    
                    HSSFCell cellN2 = row.createCell((short) 13);
                    cellN2.setCellValue(vpnnetworkpptpserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellN2.setCellStyle(cellStyle);
                    
                    HSSFCell cellO2 = row.createCell((short) 14);
                    cellO2.setCellValue(usernamepptpserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellO2.setCellStyle(cellStyle);
                    
                    HSSFCell cellP2 = row.createCell((short) 15);
                    cellP2.setCellValue(passwordpptpserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellP2.setCellStyle(cellStyle);
                
                    if(getftpField.isSelected()){
                        HSSFCell cellQ2 = row.createCell((short) 16);
                        cellQ2.setCellValue("FTP "+getftpField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellQ2.setCellStyle(cellStyle);
                    }else if(putftpField.isSelected()){
                        HSSFCell cellQ2 = row.createCell((short) 16);
                        cellQ2.setCellValue("FTP "+putftpField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellQ2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellR2 = row.createCell((short) 17);
                    cellR2.setCellValue(ftpcmdlineField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellR2.setCellStyle(cellStyle);

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" SIZE");
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String ligne;
                    while ((ligne=streamReader.readLine())!=null)
                    {
                        HSSFCell cellS2 = row.createCell((short) 18);
                        cellS2.setCellValue(ligne);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellS2.setCellStyle(cellStyle);
                    }

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" DOWN");
                    BufferedReader srDown = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String lignesrDown;
                    while ((lignesrDown=srDown.readLine())!=null)
                    {
                        HSSFCell cellT2 = row.createCell((short) 19);
                        cellT2.setCellValue(lignesrDown);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellT2.setCellStyle(cellStyle);
                    }

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" UP");
                    BufferedReader srUP = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String lignesrUP;
                    while ((lignesrUP=srUP.readLine())!=null)
                    {
                        HSSFCell cellU2 = row.createCell((short) 20);
                        cellU2.setCellValue(lignesrUP);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellU2.setCellStyle(cellStyle);
                    }
                }else if(greS == true){
                    HSSFCell cellL2 = row.createCell((short) 11);
                    cellL2.setCellValue(localipgreserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellL2.setCellStyle(cellStyle);
                    
                    HSSFCell cellM2 = row.createCell((short) 12);
                    cellL2.setCellValue(remoteipgreserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellL2.setCellStyle(cellStyle);
                    
                    HSSFCell cellN2 = row.createCell((short) 13);
                    cellL2.setCellValue(remotenetworkgreserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellL2.setCellStyle(cellStyle);
                    
                    HSSFCell cellO2 = row.createCell((short) 14);
                    cellL2.setCellValue(greipgreserver);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellL2.setCellStyle(cellStyle);
                
                    if(getftpField.isSelected()){
                        HSSFCell cellP2 = row.createCell((short) 15);
                        cellP2.setCellValue("FTP "+getftpField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellP2.setCellStyle(cellStyle);
                    }else if(putftpField.isSelected()){
                        HSSFCell cellP2 = row.createCell((short) 15);
                        cellP2.setCellValue("FTP "+putftpField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellP2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellQ2 = row.createCell((short) 16);
                    cellQ2.setCellValue(ftpcmdlineField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellQ2.setCellStyle(cellStyle);

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" SIZE");
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String ligne;
                    while ((ligne=streamReader.readLine())!=null)
                    {
                        HSSFCell cellR2 = row.createCell((short) 17);
                        cellR2.setCellValue(ligne);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellR2.setCellStyle(cellStyle);
                    }

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" DOWN");
                    BufferedReader srDown = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String lignesrDown;
                    while ((lignesrDown=srDown.readLine())!=null)
                    {
                        HSSFCell cellS2 = row.createCell((short) 18);
                        cellS2.setCellValue(lignesrDown);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellS2.setCellStyle(cellStyle);
                    }

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" UP");
                    BufferedReader srUP = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String lignesrUP;
                    while ((lignesrUP=srUP.readLine())!=null)
                    {
                        HSSFCell cellT2 = row.createCell((short) 19);
                        cellT2.setCellValue(lignesrUP);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellT2.setCellStyle(cellStyle);
                    }
                }else if(l2tpipsecC == true){
                    if(transportl2tpipsecclient = true){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(transportl2tpipsecclientString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }else if(tunnell2tpipsecclient = true){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(tunnell2tpipsecclientString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }
                    
                    HSSFCell cellM2 = row.createCell((short) 12);
                    cellM2.setCellValue(ikelifetimel2tpipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellM2.setCellStyle(cellStyle);
                    
                    HSSFCell cellN2 = row.createCell((short) 13);
                    cellN2.setCellValue(keylifetimel2tpipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellN2.setCellStyle(cellStyle);
                    
                    HSSFCell cellP2 = row.createCell((short) 14);
                    cellP2.setCellValue(localipl2tpipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellP2.setCellStyle(cellStyle);
                    
                    HSSFCell cellQ2 = row.createCell((short) 15);
                    cellQ2.setCellValue(serveripl2tpipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellQ2.setCellStyle(cellStyle);
                    
                    if(getftpField.isSelected()){
                        HSSFCell cellR2 = row.createCell((short) 16);
                        cellR2.setCellValue("FTP "+getftpField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellR2.setCellStyle(cellStyle);
                    }else if(putftpField.isSelected()){
                        HSSFCell cellR2 = row.createCell((short) 16);
                        cellR2.setCellValue("FTP "+putftpField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellR2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellS2 = row.createCell((short) 17);
                    cellS2.setCellValue(ftpcmdlineField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellS2.setCellStyle(cellStyle);

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" SIZE");
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String ligne;
                    while ((ligne=streamReader.readLine())!=null)
                    {
                        HSSFCell cellT2 = row.createCell((short) 18);
                        cellT2.setCellValue(ligne);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellT2.setCellStyle(cellStyle);
                    }

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" DOWN");
                    BufferedReader srDown = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String lignesrDown;
                    while ((lignesrDown=srDown.readLine())!=null)
                    {
                        HSSFCell cellU2 = row.createCell((short) 19);
                        cellU2.setCellValue(lignesrDown);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellU2.setCellStyle(cellStyle);
                    }

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" UP");
                    BufferedReader srUP = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String lignesrUP;
                    while ((lignesrUP=srUP.readLine())!=null)
                    {
                        HSSFCell cellV2 = row.createCell((short) 20);
                        cellV2.setCellValue(lignesrUP);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellV2.setCellStyle(cellStyle);
                    }
                }else if(l2tppppipsecC == true){
                    if(transportl2tppppipsecclient = true){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(transportl2tppppipsecclientString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }else if(tunnell2tppppipsecclient = true){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(tunnell2tppppipsecclientString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }
                    
                    HSSFCell cellM2 = row.createCell((short) 12);
                    cellM2.setCellValue(ikelifetimel2tppppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellM2.setCellStyle(cellStyle);
                    
                    HSSFCell cellN2 = row.createCell((short) 13);
                    cellN2.setCellValue(keylifetimel2tppppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellN2.setCellStyle(cellStyle);
                    
                    HSSFCell cellO2 = row.createCell((short) 14);
                    cellO2.setCellValue(localipl2tppppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellO2.setCellStyle(cellStyle);
                    
                    HSSFCell cellP2 = row.createCell((short) 15);
                    cellP2.setCellValue(serveripl2tppppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellP2.setCellStyle(cellStyle);
                    
                    HSSFCell cellQ2 = row.createCell((short) 16);
                    cellQ2.setCellValue(usernamel2tppppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellQ2.setCellStyle(cellStyle);
                    
                    HSSFCell cellR2 = row.createCell((short) 17);
                    cellR2.setCellValue(passwordl2tppppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellR2.setCellStyle(cellStyle);
                
                    if(getftpField.isSelected()){
                        HSSFCell cellS2 = row.createCell((short) 18);
                        cellS2.setCellValue("FTP "+getftpField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellS2.setCellStyle(cellStyle);
                    }else if(putftpField.isSelected()){
                        HSSFCell cellS2 = row.createCell((short) 18);
                        cellS2.setCellValue("FTP "+putftpField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellS2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellT2 = row.createCell((short) 19);
                    cellT2.setCellValue(ftpcmdlineField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellT2.setCellStyle(cellStyle);

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" SIZE");
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String ligne;
                    while ((ligne=streamReader.readLine())!=null)
                    {
                        HSSFCell cellU2 = row.createCell((short) 20);
                        cellU2.setCellValue(ligne);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellU2.setCellStyle(cellStyle);
                    }

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" DOWN");
                    BufferedReader srDown = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String lignesrDown;
                    while ((lignesrDown=srDown.readLine())!=null)
                    {
                        HSSFCell cellV2 = row.createCell((short) 21);
                        cellV2.setCellValue(lignesrDown);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellV2.setCellStyle(cellStyle);
                    }

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" UP");
                    BufferedReader srUP = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String lignesrUP;
                    while ((lignesrUP=srUP.readLine())!=null)
                    {
                        HSSFCell cellW2 = row.createCell((short) 22);
                        cellW2.setCellValue(lignesrUP);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellW2.setCellStyle(cellStyle);
                    }
                }else if(pppoepppipC == true){
                    if(pappppoepppipclient){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(pappppoepppipclientString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }else if(chappppoepppipclient){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(chappppoepppipclientString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }
                    
                    HSSFCell cellM2 = row.createCell((short) 12);
                    cellM2.setCellValue(usernamepppoepppipclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellM2.setCellStyle(cellStyle);
                    
                    HSSFCell cellN2 = row.createCell((short) 13);
                    cellN2.setCellValue(passwordpppoepppipclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellN2.setCellStyle(cellStyle);
                    
                    if(getftpField.isSelected()){
                        HSSFCell cellO2 = row.createCell((short) 14);
                        cellO2.setCellValue("FTP "+getftpField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellO2.setCellStyle(cellStyle);
                    }else if(putftpField.isSelected()){
                        HSSFCell cellO2 = row.createCell((short) 14);
                        cellO2.setCellValue("FTP "+putftpField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellO2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellP2 = row.createCell((short) 15);
                    cellP2.setCellValue(ftpcmdlineField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellP2.setCellStyle(cellStyle);

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" SIZE");
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String ligne;
                    while ((ligne=streamReader.readLine())!=null)
                    {
                        HSSFCell cellQ2 = row.createCell((short) 16);
                        cellQ2.setCellValue(ligne);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellQ2.setCellStyle(cellStyle);
                    }

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" DOWN");
                    BufferedReader srDown = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String lignesrDown;
                    while ((lignesrDown=srDown.readLine())!=null)
                    {
                        HSSFCell cellR2 = row.createCell((short) 17);
                        cellR2.setCellValue(lignesrDown);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellR2.setCellStyle(cellStyle);
                    }

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" UP");
                    BufferedReader srUP = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String lignesrUP;
                    while ((lignesrUP=srUP.readLine())!=null)
                    {
                        HSSFCell cellS2 = row.createCell((short) 18);
                        cellS2.setCellValue(lignesrUP);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellS2.setCellStyle(cellStyle);
                    }
                }else if(pppoepppipsecC == true){
                    if(pappppoepppipsecclient){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(pappppoepppipsecclientString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }else if(chappppoepppipsecclient){
                        HSSFCell cellL2 = row.createCell((short) 11);
                        cellL2.setCellValue(chappppoepppipsecclientString);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellL2.setCellStyle(cellStyle);
                    }
                    
                    HSSFCell cellM2 = row.createCell((short) 12);
                    cellM2.setCellValue(usernamepppoepppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellM2.setCellStyle(cellStyle);
                    
                    HSSFCell cellN2 = row.createCell((short) 13);
                    cellN2.setCellValue(passwordpppoepppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellN2.setCellStyle(cellStyle);
                    
                    HSSFCell cellO2 = row.createCell((short) 14);
                    cellO2.setCellValue(ikelifetimepppoepppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellO2.setCellStyle(cellStyle);
                    
                    HSSFCell cellP2 = row.createCell((short) 15);
                    cellP2.setCellValue(keylifetimepppoepppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellP2.setCellStyle(cellStyle);
                    
                    HSSFCell cellQ2 = row.createCell((short) 16);
                    cellQ2.setCellValue(keyexchangepppoepppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellQ2.setCellStyle(cellStyle);
                    
                    HSSFCell cellR2 = row.createCell((short) 17);
                    cellR2.setCellValue(localippppoepppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellR2.setCellStyle(cellStyle);
                    
                    HSSFCell cellS2 = row.createCell((short) 18);
                    cellS2.setCellValue(localnetworkpppoepppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellS2.setCellStyle(cellStyle);
                    
                    HSSFCell cellT2 = row.createCell((short) 19);
                    cellT2.setCellValue(remoteippppoepppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellT2.setCellStyle(cellStyle);
                    
                    HSSFCell cellU2 = row.createCell((short) 20);
                    cellU2.setCellValue(remotenetworkpppoepppipsecclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellU2.setCellStyle(cellStyle);
                
                    if(getftpField.isSelected()){
                        HSSFCell cellV2 = row.createCell((short) 21);
                        cellV2.setCellValue("FTP "+getftpField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellV2.setCellStyle(cellStyle);
                    }else if(putftpField.isSelected()){
                        HSSFCell cellV2 = row.createCell((short) 21);
                        cellV2.setCellValue("FTP "+putftpField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellV2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellW2 = row.createCell((short) 22);
                    cellW2.setCellValue(ftpcmdlineField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellW2.setCellStyle(cellStyle);

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" SIZE");
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String ligne;
                    while ((ligne=streamReader.readLine())!=null)
                    {
                        HSSFCell cellX2 = row.createCell((short) 23);
                        cellX2.setCellValue(ligne);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellX2.setCellStyle(cellStyle);
                    }

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" DOWN");
                    BufferedReader srDown = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String lignesrDown;
                    while ((lignesrDown=srDown.readLine())!=null)
                    {
                        HSSFCell cellY2 = row.createCell((short) 24);
                        cellY2.setCellValue(lignesrDown);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellY2.setCellStyle(cellStyle);
                    }

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" UP");
                    BufferedReader srUP = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String lignesrUP;
                    while ((lignesrUP=srUP.readLine())!=null)
                    {
                        HSSFCell cellZ2 = row.createCell((short) 25);
                        cellZ2.setCellValue(lignesrUP);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellZ2.setCellStyle(cellStyle);
                    }
                }else if(pptpC == true){
                    HSSFCell cellL2 = row.createCell((short) 11);
                    cellL2.setCellValue(serverippptpclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellL2.setCellStyle(cellStyle);
                    
                    HSSFCell cellM2 = row.createCell((short) 12);
                    cellM2.setCellValue(usernamepptpclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellM2.setCellStyle(cellStyle);
                    
                    HSSFCell cellN2 = row.createCell((short) 13);
                    cellN2.setCellValue(passwordpptpclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellN2.setCellStyle(cellStyle);
                
                    if(getftpField.isSelected()){
                        HSSFCell cellO2 = row.createCell((short) 14);
                        cellO2.setCellValue("FTP "+getftpField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellO2.setCellStyle(cellStyle);
                    }else if(putftpField.isSelected()){
                        HSSFCell cellO2 = row.createCell((short) 14);
                        cellO2.setCellValue("FTP "+putftpField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellO2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellP2 = row.createCell((short) 15);
                    cellP2.setCellValue(ftpcmdlineField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellP2.setCellStyle(cellStyle);

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" SIZE");
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String ligne;
                    while ((ligne=streamReader.readLine())!=null)
                    {
                        HSSFCell cellQ2 = row.createCell((short) 16);
                        cellQ2.setCellValue(ligne);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellQ2.setCellStyle(cellStyle);
                    }

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" DOWN");
                    BufferedReader srDown = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String lignesrDown;
                    while ((lignesrDown=srDown.readLine())!=null)
                    {
                        HSSFCell cellR2 = row.createCell((short) 17);
                        cellR2.setCellValue(lignesrDown);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellR2.setCellStyle(cellStyle);
                    }

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" UP");
                    BufferedReader srUP = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String lignesrUP;
                    while ((lignesrUP=srUP.readLine())!=null)
                    {
                        HSSFCell cellS2 = row.createCell((short) 18);
                        cellS2.setCellValue(lignesrUP);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellS2.setCellStyle(cellStyle);
                    }
                }else if(greC == true){
                    HSSFCell cellL2 = row.createCell((short) 11);
                    cellL2.setCellValue(localipgreclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellL2.setCellStyle(cellStyle);
                    
                    HSSFCell cellM2 = row.createCell((short) 12);
                    cellM2.setCellValue(remoteipgreclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellM2.setCellStyle(cellStyle);
                    
                    HSSFCell cellN2 = row.createCell((short) 13);
                    cellN2.setCellValue(remotenetworkgreclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellN2.setCellStyle(cellStyle);
                
                    HSSFCell cellO2 = row.createCell((short) 14);
                    cellO2.setCellValue(greipgreclient);
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellO2.setCellStyle(cellStyle);
                
                    if(getftpField.isSelected()){
                        HSSFCell cellP2 = row.createCell((short) 15);
                        cellP2.setCellValue("FTP "+getftpField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellP2.setCellStyle(cellStyle);
                    }else if(putftpField.isSelected()){
                        HSSFCell cellP2 = row.createCell((short) 15);
                        cellP2.setCellValue("FTP "+putftpField.getText());
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellP2.setCellStyle(cellStyle);
                    }

                    HSSFCell cellQ2 = row.createCell((short) 16);
                    cellQ2.setCellValue(ftpcmdlineField.getText());
                    cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                    cellQ2.setCellStyle(cellStyle);

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" SIZE");
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String ligne;
                    while ((ligne=streamReader.readLine())!=null)
                    {
                        HSSFCell cellR2 = row.createCell((short) 17);
                        cellR2.setCellValue(ligne);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellR2.setCellStyle(cellStyle);
                    }

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" DOWN");
                    BufferedReader srDown = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String lignesrDown;
                    while ((lignesrDown=srDown.readLine())!=null)
                    {
                        HSSFCell cellS2 = row.createCell((short) 18);
                        cellS2.setCellValue(lignesrDown);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellS2.setCellStyle(cellStyle);
                    }

                    p = Runtime.getRuntime().exec("CurlResult.sh "+i+" UP");
                    BufferedReader srUP = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                    String lignesrUP;
                    while ((lignesrUP=srUP.readLine())!=null)
                    {
                        HSSFCell cellT2 = row.createCell((short) 19);
                        cellT2.setCellValue(lignesrUP);
                        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
                        cellT2.setCellStyle(cellStyle);
                    }
                }
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clearPingResult(ActionEvent event) throws IOException {
        p = Runtime.getRuntime().exec("rm -r /tmp/ResultPing.txt");
        showPingField.clear();
    }

    @FXML
    private void clearTRResult(ActionEvent event) throws IOException {
        p = Runtime.getRuntime().exec("rm -r /tmp/ResultTraceRoute.txt");
        showTracertField.clear();
    }

    @FXML
    private void perfAutoTests(ActionEvent event) {
    }

    
    @FXML
    private void megaPerfAction(ActionEvent event) throws IOException {
        if(megaPerfField.isSelected()){
            try {
                String result ="";
                p = Runtime.getRuntime().exec("ethtool -s eth0 speed 100 duplex full autoneg off");
                InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                BufferedReader br=new BufferedReader(ipsr);
                String ligne;
                while ((ligne=br.readLine())!=null)
                {
                    result += ligne;
                }
                MPA = true;
            } catch (IOException ex) {
                Logger.getLogger(NetworkingTestsOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    @FXML
    private void gigaPerfAction(ActionEvent event) throws IOException {
        if(gigaPerfField.isSelected()){
            try {
                String result ="";
                p = Runtime.getRuntime().exec("ethtool -s eth0 speed 1000 duplex full autoneg off");
                InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
                BufferedReader br=new BufferedReader(ipsr);
                String ligne;
                while ((ligne=br.readLine())!=null)
                {
                    result += ligne;
                }
                GPA = true;
            } catch (IOException ex) {
                Logger.getLogger(NetworkingTestsOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    private void clearftpResult(ActionEvent event) {
        showftpField.clear();
    }


    @FXML
    private void ftptestdisplay(MouseEvent event) {
        pingtestwindow.setVisible(false);
        traceroutetestwindow.setVisible(false);
        iperftestwindow.setVisible(false);
        ftptestwindow.setVisible(true);
    }

    private void ftptestundisplay(MouseEvent event) {
        pingtestwindow.setVisible(false);
        traceroutetestwindow.setVisible(false);
        iperftestwindow.setVisible(false);
    }

    @FXML
    private void getfileftpFunction(ActionEvent event) {
        t = new Thread(){
            @Override
            public void run(){
                try {
                    if (putftpField.isSelected()){
                        ftpfields.setDisable(true);
                        getfileftpbutton.setDisable(true);
                        showftpField.clear();
                        showftpField.setWrapText(true);
                        showftpField.setDisable(false);
                        iperfprogressBar.setVisible(true);
                        stopftpbutton.setDisable(false);
                        listfilesButton.setDisable(true);
                        mainmenufields.setDisable(true);
                        String result ="";
                        p = Runtime.getRuntime().exec("CurlTest.sh "+ftpserverField.getText()+" "+ftploginField.getText()+" "+ftppasswordField.getText()+" "+ftpcmdlineField.getText()+" "+nombreftpTest.getText()+" PUT");
                        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                        String ligne;
                        while ((ligne=br.readLine())!=null)
                        {
                            showftpField.appendText(ligne + "\n");
                            result += ligne;
                        }
                        clearftpButton.setDisable(false);
                        saveftpButton.setDisable(false);
                        iperfprogressBar.setVisible(false);
                        ftpfields.setDisable(false);
                        getfileftpbutton.setDisable(false);
                        grapftpButton.setDisable(false);
                        stopftpbutton.setDisable(true);
                        listfilesButton.setDisable(false);
                        mainmenufields.setDisable(false);
                    }
                    if (getftpField.isSelected()){
                        ftpfields.setDisable(true);
                        getfileftpbutton.setDisable(true);
                        showftpField.clear();
                        showftpField.setWrapText(true);
                        showftpField.setDisable(false);
                        iperfprogressBar.setVisible(true);
                        stopftpbutton.setDisable(false);
                        listfilesButton.setDisable(true);
                        mainmenufields.setDisable(true);
                        String result ="";
                        p = Runtime.getRuntime().exec("CurlTest.sh "+ftpserverField.getText()+" "+ftploginField.getText()+" "+ftppasswordField.getText()+" "+ftpcmdlineField.getText()+" "+nombreftpTest.getText()+" GET");
                        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                        String ligne;
                        while ((ligne=br.readLine())!=null)
                        {
                            showftpField.appendText(ligne + "\n");
                            result += ligne;
                        }
                        clearftpButton.setDisable(false);
                        saveftpButton.setDisable(false);
                        iperfprogressBar.setVisible(false);
                        ftpfields.setDisable(false);
                        getfileftpbutton.setDisable(false);
                        grapftpButton.setDisable(false);
                        stopftpbutton.setDisable(true);
                        listfilesButton.setDisable(false);
                        mainmenufields.setDisable(false);
                    }
                }catch (IOException ex) {
                    Logger.getLogger(NetworkingTestsOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        t.start();
        Image about = new Image(getClass().getResourceAsStream("/vpnlite/img/Approved.png"));
        TrayNotification tray = new TrayNotification();
        tray.setTitle("FTP Test");
        tray.setMessage("Your FTP Test is started correctly");
        tray.setRectangleFill(Paint.valueOf("#2A9A84"));
        tray.setImage(about);
        tray.showAndDismiss(Duration.seconds(1));
    }

    @FXML
    private void saveftpTest(ActionEvent event) {
        SaveFileFTP();
        Image notifimg = new Image(
            getClass().getResourceAsStream("/vpnlite/img/Approved.png"));
        Node notimg = new ImageView(notifimg);
        notif = Notifications.create()
            .graphic(notimg)
            .title("Saved Results")
            .text("Your result was saved correctly")
            .hideAfter(Duration.seconds(2))
            .position(Pos.CENTER)
            .darkStyle()
            .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Notification OK");
            }
        });
        notif.show();
    }

    
    @FXML
    private void graphPerfFunction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("iPerf Result");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time (s)");       
        yAxis.setLabel("Bandwidth (Mbits)");
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
        XYChart.Data<String,Number> data = new XYChart.Data<>();  
        lineChart.setTitle("iPerf Result");
                                
        XYChart.Series series1 = new XYChart.Series();
        
        for(int i=1; i<=Integer.parseInt(timePerfField.getText())+2; i++){
            String perfresult = "ResultToExcel.sh "+resultPath+" "+i+"p";
            p = Runtime.getRuntime().exec(perfresult);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
            String ligne;
            while ((ligne=br.readLine())!=null)
            {
                if(i <= Integer.parseInt(timePerfField.getText())){
                    data = new XYChart.Data<String,Number>(Integer.toString(i), Float.parseFloat(ligne));
                    series1.getData().add(data);
                }else if(i == Integer.parseInt(timePerfField.getText())+1){
                    series1.setName("Sender : "+ligne);
                }else if(i == Integer.parseInt(timePerfField.getText())+2){
                    series1.setName(series1.getName()+" Receiver : "+ligne);
                }
            }
        }
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series1);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void graphftpTest(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("FTP Result");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Nombre of Test");       
        yAxis.setLabel("Bandwidth (Mbits)");
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
        XYChart.Data<String,Number> data = new XYChart.Data<>();  
        lineChart.setTitle("FTP Result");
                                
        XYChart.Series series1 = new XYChart.Series();
        
        if(getftpField.isSelected()){
            for(int i=1; i<=Integer.parseInt(nombreftpTest.getText()); i++){
                p = Runtime.getRuntime().exec("CurlResult.sh "+i+" DOWNGRAPH");
                BufferedReader srDown = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                String lignesrDown;
                while ((lignesrDown=srDown.readLine())!=null)
                {
                    data = new XYChart.Data<String,Number>("Test : "+Integer.toString(i), Float.parseFloat(lignesrDown));
                    series1.getData().add(data);
                }
                series1.setName("Average Download Speed");
            }
        }else if(putftpField.isSelected()){
            for(int i=1; i<=Integer.parseInt(nombreftpTest.getText()); i++){
                p = Runtime.getRuntime().exec("CurlResult.sh "+i+" UPGRAPH");
                BufferedReader srDown = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                String lignesrDown;
                while ((lignesrDown=srDown.readLine())!=null)
                {
                    data = new XYChart.Data<String,Number>("Test : "+Integer.toString(i), Float.parseFloat(lignesrDown));
                    series1.getData().add(data);
                }
                series1.setName("Average Upload Speed");
            }
        }
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series1);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void stopftpFunction(ActionEvent event) throws IOException {
        for(int i=1; i<=Integer.parseInt(nombreftpTest.getText()); i++){
            p1 = Runtime.getRuntime().exec("killprocess.sh curl");
        }
        p1 = Runtime.getRuntime().exec("killprocess.sh ftp");
        Image about = new Image(getClass().getResourceAsStream("/vpnlite/img/Stop.png"));
        TrayNotification tray = new TrayNotification();
        tray.setTitle("FTP Test");
        tray.setMessage("Your FTP Test is stopped correctly");
        tray.setRectangleFill(Paint.valueOf("#2A9A84"));
        tray.setImage(about);
        tray.showAndDismiss(Duration.seconds(1));
        stopftpbutton.setDisable(true);
        getfileftpbutton.setDisable(false);
        clearftpButton.setDisable(false);
        saveftpButton.setDisable(false);
        grapftpButton.setDisable(false);
        mainmenufields.setDisable(false);
        listfilesButton.setDisable(false);
        iperfprogressBar.setVisible(false);
    }

    @FXML
    private void listfileResult(ActionEvent event) throws Throwable {
        if(ftpserverField.getText() == null || ftpserverField.getText().trim().isEmpty()){
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Warning");
            tray.setMessage("The «FTP Sever Address» field is Empty.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.showAndDismiss(Duration.seconds(2));
            this.finalize();
        }else if(ftploginField.getText() == null || ftploginField.getText().trim().isEmpty()){
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Warning");
            tray.setMessage("The «FTP Login» field is Empty.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.showAndDismiss(Duration.seconds(2));
            this.finalize();
        }else if(ftppasswordField.getText() == null || ftppasswordField.getText().trim().isEmpty()){
            Image approved = new Image(getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Warning");
            tray.setMessage("The «FTP Password» field is Empty.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setImage(approved);
            tray.showAndDismiss(Duration.seconds(2));
            this.finalize();
        }else {
            showftpField.setDisable(false);
            ftpfields.setDisable(true);
            getfileftpbutton.setDisable(true);
            iperfprogressBar.setVisible(true);
            stopftpbutton.setDisable(false);
            t = new Thread(){
                @Override
                public void run(){
                    try {
                        String result ="";
                        p = Runtime.getRuntime().exec("FTPTest.sh "+ftpserverField.getText()+" "+ftploginField.getText()+" "+ftppasswordField.getText());
                        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                        String ligne;
                        while ((ligne=br.readLine())!=null)
                        {
                            showftpField.appendText(ligne + "\n");
                            result += ligne;
                        }
                        ftpfields.setDisable(false);
                        getfileftpbutton.setDisable(false);
                        iperfprogressBar.setVisible(false);
                    }catch (IOException ex) {
                        Logger.getLogger(NetworkingTestsOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            t.start();
        }
    }
}