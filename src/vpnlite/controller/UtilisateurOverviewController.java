/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vpnlite.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import jssc.SerialPort;
import jssc.SerialPortList;
import org.controlsfx.control.Notifications;
import org.ini4j.Ini;
import static vpnlite.controller.PrincipalOverviewController.R1;
import static vpnlite.controller.PrincipalOverviewController.R2;

/**
 * FXML Controller class
 *
 * @author Souhaieb
 */
public class UtilisateurOverviewController extends PrincipalOverviewController implements Initializable {

    @FXML
    private JFXTextField nomprenomField;
    @FXML
    private JFXDatePicker dateField;
    @FXML
    private JFXTextField timeField;
    @FXML
    private JFXTextField companyField;
    @FXML
    private JFXTextField matriculeField;
    @FXML
    private Label nomprenomLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label companyLabel;
    private Label titleLabel;
    @FXML
    private Label matriculeLabel;
    @FXML
    private JFXTextField siteField;
    @FXML
    private JFXTextField environmentField;
    @FXML
    private JFXTextField testField;
    @FXML
    private JFXTextField manufacturerField;
    @FXML
    private JFXComboBox<String> modelField;
    @FXML
    private GridPane scrollUserInformation;
    private File file;
    @FXML
    private JFXTextField listView;
    @FXML
    private Button browseButton;
    private Label configfileLabel;
    @FXML
    private TextField teratermField;
    @FXML
    private TextField syslogField;
    @FXML
    private TextField wiresharkField;
    @FXML
    private Button saveButton;
    @FXML
    private Button initializeButton;
    @FXML
    private Button nextButton;
    private Process p;
    static String variableNomPrenom;
    static String variableMatricule;
    static String variableDate;
    static String variableTime;
    static String variableCompany;
    static String variableSite;
    static String variableEnvironment;
    static String variableTest;
    static String variableManufacturer;
    static String variableModel;
    static String variableSoftVersion;
    static String variableSerialNumber;
    static String variableHWVersion;
    static String variableGWYIPAddr;
    static String variableMACAddr;
    static String variableLogin;
    static String variablePassword;
    static String variableRLogin;
    static String variableRPassword;
    static String variableRCmd;
    static String variableMode;
    static String variableTeratermTrace;
    static String variableSysInterface;
    static String variableSysTrace;
    static String variableWiresharkInterface;
    static String variableWiresharkTrace;
    static boolean variableTeratermCheck;
    static boolean variableSyslogCheck;
    static boolean variableWiresharkCheck;
    static boolean nex = false;
    private Label pathLabel;
    @FXML
    private Button importButton;
    Alert alert = new Alert(AlertType.WARNING);
    private String path;
    @FXML
    private Button dateRefreshButton;
    @FXML
    private Button timeRefreshButton;
    @FXML
    private Button userButton;
    @FXML
    private Button vpnButton;
    @FXML
    private Button testsButton;
    private Button resultsButton;
    @FXML
    private Button backButton;
    @FXML
    private ImageView nextimg;
    @FXML
    private ImageView backimg;
    @FXML
    private ImageView testerinformationImg;
    @FXML
    private ImageView gatewayinformationImg;
    @FXML
    private Label testerinformationLabel;
    @FXML
    private Label gatewayinformationLabel;
    @FXML
    private ImageView testerarrowimg;
    @FXML
    private ImageView gatewayarrowimg;
    private ImageView logsarrowimg;
    @FXML
    private AnchorPane testerAnchorPane;
    @FXML
    private AnchorPane gatewayAnchorPane;
    @FXML
    private AnchorPane logsAnchorPane;
    @FXML
    private JFXTextField softwareField;
    @FXML
    private JFXTextField snField;
    @FXML
    private JFXTextField hwField;
    @FXML
    private JFXTextField gwyIpField;
    @FXML
    private JFXTextField macField;
    @FXML
    private JFXTextField loginField;
    @FXML
    private JFXTextField passwordField;
    @FXML
    private JFXTextField returnLoginField;
    @FXML
    private JFXTextField returnPasswordField;
    @FXML
    private JFXTextField commandField;
    @FXML
    private Label teratermmodeField;
    @FXML
    private Label teratermserialField;
    @FXML
    private Label teratermtraceField;
    @FXML
    private JFXTextField modeField;
    @FXML
    private JFXComboBox<String> serialcomField;
    @FXML
    private JFXComboBox<String> syslogIPaddress;
    @FXML
    private Label wiresharkinterfaceField;
    @FXML
    private JFXComboBox<String> wiresharkInterfaces;
    @FXML
    private JFXCheckBox teratermEnableCheck;
    @FXML
    private JFXCheckBox syslogEnableCheck;
    @FXML
    private JFXCheckBox wiresharkEnableCheck;
    @FXML
    private HBox teratermFields;
    @FXML
    private HBox syslogFields;
    @FXML
    private HBox wiresharkFields;
    public static boolean U1,U2;
    Notifications notif;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if(U1 == true){
            String ip;
            try {
                String command1 = "date +%H:%M:%S";
                p = Runtime.getRuntime().exec(command1);
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                timeField.setText(streamReader.readLine());
            } catch (IOException e) {
                System.out.println(e);
            }
            dateField.setValue(LocalDate.now());
            nomprenomField.setText(variableNomPrenom);
            matriculeField.setText(variableMatricule);
            companyField.setText(variableCompany);
            siteField.setText(variableSite);
            environmentField.setText(variableEnvironment);
            testField.setText(variableTest);
            manufacturerField.setText(variableManufacturer);
            modelField.getItems().addAll("FAST5460", "FAST5355", "BBOX3AC", "BTHH6", "FAST5364");
            modelField.setValue(variableModel);
            softwareField.setText(variableSoftVersion);
            snField.setText(variableSerialNumber);
            hwField.setText(variableHWVersion);
            gwyIpField.setText(variableGWYIPAddr);
            macField.setText(variableMACAddr);
            nextButton.setDisable(false);
        }else if(U2 == true){
            String ip;
            try {
                String command1 = "date +%H:%M:%S";
                p = Runtime.getRuntime().exec(command1);
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                timeField.setText(streamReader.readLine());
            } catch (IOException e) {
                System.out.println(e);
            }
            dateField.setValue(LocalDate.now());
            nomprenomField.setText(variableNomPrenom);
            matriculeField.setText(variableMatricule);
            companyField.setText(variableCompany);
            siteField.setText(variableSite);
            environmentField.setText(variableEnvironment);
            testField.setText(variableTest);
            manufacturerField.setText(variableManufacturer);
            modelField.getItems().addAll("FAST5460", "FAST5355", "BBOX3AC", "BTHH6", "FAST5364");
            modelField.setValue(variableModel);
            softwareField.setText(variableSoftVersion);
            snField.setText(variableSerialNumber);
            hwField.setText(variableHWVersion);
            gwyIpField.setText(variableGWYIPAddr);
            macField.setText(variableMACAddr);
            nextButton.setDisable(false);
            vpnButton.setDisable(false);
        }else {
            try {
                String command1 = "date +%H:%M:%S";
                p = Runtime.getRuntime().exec(command1);
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                timeField.setText(streamReader.readLine());
            } catch (IOException e) {
                System.out.println(e);
            }
            dateField.setValue(LocalDate.now());
            modelField.getItems().addAll("FAST5460", "FAST5355", "BBOX3AC", "BTHH6", "FAST5364");
            modelField.setValue("FAST5460");
            siteField.setText("TN - SST - Pôle ATR - Validation");
            environmentField.setText("Open space");
            manufacturerField.setText("Sagemcom");
            companyField.setText("Sagemcom");
        }
        
        Image refresh = new Image(
            getClass().getResourceAsStream("/vpnlite/img/Refresh.png"));
        dateRefreshButton.setGraphic(new ImageView(refresh));
        dateRefreshButton.setStyle(
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 30px; " +
                "-fx-min-height: 30px; " +
                "-fx-max-width: 30px; " +
                "-fx-max-height: 30px;"
        );
        timeRefreshButton.setGraphic(new ImageView(refresh));
        timeRefreshButton.setStyle(
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 30px; " +
                "-fx-min-height: 30px; " +
                "-fx-max-width: 30px; " +
                "-fx-max-height: 30px;"
        );
        
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
        
        userButton.setStyle("-fx-base: #b6e7c9;");
        
        if(R2 == true){
            vpnButton.setDisable(false);
        }
        SerialPort arduinoPort = null;
        String[] serialPortNames = SerialPortList.getPortNames();
        for(String name: serialPortNames){
            serialcomField.getItems().addAll(name);
        }
    }

    public void vsetVariables(){
        variableNomPrenom = nomprenomField.getText();
        variableMatricule = matriculeField.getText();
        variableDate = dateField.getValue().toString();
        variableTime = timeField.getText();
        variableCompany = companyField.getText();
        variableSite = siteField.getText()  ;
        variableEnvironment = environmentField.getText();
        variableTest = testField.getText();
        variableManufacturer = manufacturerField.getText();
        variableModel = modelField.getValue();
    }
    
    @FXML
    public void browseClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Configuration File", "*.ini")
        );
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            listView.setText(selectedFile.getAbsolutePath());
            //listView.getItems().add(selectedFile.getAbsolutePath());
            path = selectedFile.getAbsolutePath();
        } else {
            System.out.println("Choose a file");
        }
    }

    private boolean checkFonction() {

        if (nomprenomField.getText() == null || nomprenomField.getText().trim().isEmpty()) {
            Image notifimg = new Image(
                getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            Node notimg = new ImageView(notifimg);
            notif = Notifications.create()
                .graphic(notimg)
                .title("Warning")
                .text("Field nom is empty")
                .hideAfter(Duration.seconds(3))
                .position(Pos.CENTER)
                .darkStyle()
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    }
                });
            notif.show();
            return false;
        } else if (matriculeField.getText() == null || matriculeField.getText().trim().isEmpty()) {
            Image notifimg = new Image(
                getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            Node notimg = new ImageView(notifimg);
            notif = Notifications.create()
                .graphic(notimg)
                .title("Warning")
                .text("Field Matricule is empty")
                .hideAfter(Duration.seconds(3))
                .position(Pos.CENTER)
                .darkStyle()
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    }
                });
            notif.show();
            return false;
        } else if (!dateField.getValue().toString().equals(LocalDate.now().toString())) {
            Image notifimg = new Image(
                getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            Node notimg = new ImageView(notifimg);
            notif = Notifications.create()
                .graphic(notimg)
                .title("Warning")
                .text("Field Date is wrong")
                .hideAfter(Duration.seconds(3))
                .position(Pos.CENTER)
                .darkStyle()
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    }
                });
            notif.show();
            return false;
        } else if (companyField.getText() == null || companyField.getText().trim().isEmpty()) {
            Image notifimg = new Image(
                getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            Node notimg = new ImageView(notifimg);
            notif = Notifications.create()
                .graphic(notimg)
                .title("Warning")
                .text("Field Company is wrong")
                .hideAfter(Duration.seconds(3))
                .position(Pos.CENTER)
                .darkStyle()
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    }
                });
            notif.show();
            return false;
        } else if (siteField.getText() == null || siteField.getText().trim().isEmpty()) {
            Image notifimg = new Image(
                getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            Node notimg = new ImageView(notifimg);
            notif = Notifications.create()
                .graphic(notimg)
                .title("Warning")
                .text("Field Site is wrong")
                .hideAfter(Duration.seconds(3))
                .position(Pos.CENTER)
                .darkStyle()
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    }
                });
            notif.show();
            return false;
        } else if (environmentField.getText() == null || environmentField.getText().trim().isEmpty()) {
            Image notifimg = new Image(
                getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            Node notimg = new ImageView(notifimg);
            notif = Notifications.create()
                .graphic(notimg)
                .title("Warning")
                .text("Field Environment is wrong")
                .hideAfter(Duration.seconds(3))
                .position(Pos.CENTER)
                .darkStyle()
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    }
                });
            notif.show();
            return false;
        } else if (testField.getText() == null || testField.getText().trim().isEmpty()) {
            Image notifimg = new Image(
                getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            Node notimg = new ImageView(notifimg);
            notif = Notifications.create()
                .graphic(notimg)
                .title("Warning")
                .text("Field Test is wrong")
                .hideAfter(Duration.seconds(3))
                .position(Pos.CENTER)
                .darkStyle()
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    }
                });
            notif.show();
            return false;
        } else if (manufacturerField.getText() == null || manufacturerField.getText().trim().isEmpty()) {
            Image notifimg = new Image(
                getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            Node notimg = new ImageView(notifimg);
            notif = Notifications.create()
                .graphic(notimg)
                .title("Warning")
                .text("Field Manufacturer is wrong")
                .hideAfter(Duration.seconds(3))
                .position(Pos.CENTER)
                .darkStyle()
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    }
                });
            notif.show();
            return false;
        } if (softwareField.getText() == null || softwareField.getText().trim().isEmpty()) {
            Image notifimg = new Image(
                getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            Node notimg = new ImageView(notifimg);
            notif = Notifications.create()
                .graphic(notimg)
                .title("Warning")
                .text("Field 'Software version Field' is Empty")
                .hideAfter(Duration.seconds(3))
                .position(Pos.CENTER)
                .darkStyle()
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    }
                });
            notif.show();
            return false;
        } else if (snField.getText() == null || snField.getText().trim().isEmpty()) {
            Image notifimg = new Image(
                getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            Node notimg = new ImageView(notifimg);
            notif = Notifications.create()
                .graphic(notimg)
                .title("Warning")
                .text("Field 'Serial Number Field' is Empty")
                .hideAfter(Duration.seconds(3))
                .position(Pos.CENTER)
                .darkStyle()
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    }
                });
            notif.show();
            return false;
        } else if(hwField.getText() == null || hwField.getText().trim().isEmpty()) {
            Image notifimg = new Image(
                getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            Node notimg = new ImageView(notifimg);
            notif = Notifications.create()
                .graphic(notimg)
                .title("Warning")
                .text("Field 'Hardware Version Field' is Empty")
                .hideAfter(Duration.seconds(3))
                .position(Pos.CENTER)
                .darkStyle()
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    }
                });
            notif.show();
            return false;
        } else if(gwyIpField.getText() == null || gwyIpField.getText().trim().isEmpty()) {
            Image notifimg = new Image(
                getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            Node notimg = new ImageView(notifimg);
            notif = Notifications.create()
                .graphic(notimg)
                .title("Warning")
                .text("Field 'Gateway IP Address Field' is Empty")
                .hideAfter(Duration.seconds(3))
                .position(Pos.CENTER)
                .darkStyle()
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    }
                });
            notif.show();
            return false;
        } else if(macField.getText() == null || macField.getText().trim().isEmpty()) {
            Image notifimg = new Image(
                getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            Node notimg = new ImageView(notifimg);
            notif = Notifications.create()
                .graphic(notimg)
                .title("Warning")
                .text("Field 'MAC Address Field' is Empty")
                .hideAfter(Duration.seconds(3))
                .position(Pos.CENTER)
                .darkStyle()
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    }
                });
            notif.show();
            return false;
        } else if((syslogEnableCheck.isSelected() && syslogIPaddress.getSelectionModel().getSelectedItem() == null) || (syslogEnableCheck.isSelected() && syslogIPaddress.getSelectionModel().getSelectedItem().isEmpty())) {
                Image notifimg = new Image(
                    getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
                Node notimg = new ImageView(notifimg);
                notif = Notifications.create()
                    .graphic(notimg)
                    .title("Warning")
                    .text("Field 'Syslog IP' is not selected")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.CENTER)
                    .darkStyle()
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                        }
                    });
                notif.show();
                return false;
        } else if((syslogEnableCheck.isSelected() && syslogField.getText() == null) || (syslogEnableCheck.isSelected() && syslogField.getText().trim().isEmpty())){
                Image notifimg = new Image(
                    getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
                Node notimg = new ImageView(notifimg);
                notif = Notifications.create()
                    .graphic(notimg)
                    .title("Warning")
                    .text("Field 'Log File Syslog' is Empty")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.CENTER)
                    .darkStyle()
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                        }
                    });
                notif.show();
                return false;
        }else if(wiresharkEnableCheck.isSelected() && wiresharkInterfaces.getSelectionModel().getSelectedIndex() == -1){
                Image notifimg = new Image(
                    getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
                Node notimg = new ImageView(notifimg);
                notif = Notifications.create()
                    .graphic(notimg)
                    .title("Warning")
                    .text("Field 'Wireshark Interface' is not selected")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.CENTER)
                    .darkStyle()
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                        }
                    });
                notif.show();
                return false;
        } else if((wiresharkEnableCheck.isSelected() && wiresharkField.getText() == null) || (wiresharkEnableCheck.isSelected() && wiresharkField.getText().trim().isEmpty())){
                Image notifimg = new Image(
                    getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
                Node notimg = new ImageView(notifimg);
                notif = Notifications.create()
                    .graphic(notimg)
                    .title("Warning")
                    .text("Field 'Wireshark Trace' is Empty")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.CENTER)
                    .darkStyle()
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                        }
                    });
                notif.show();
                return false;
        } else if((teratermEnableCheck.isSelected() && loginField.getText() == null) || (teratermEnableCheck.isSelected() && loginField.getText().trim().isEmpty())){
                Image notifimg = new Image(
                    getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
                Node notimg = new ImageView(notifimg);
                notif = Notifications.create()
                    .graphic(notimg)
                    .title("Warning")
                    .text("Field 'Login Field' is Empty")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.CENTER)
                    .darkStyle()
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                        }
                    });
                notif.show();
                return false;
        } else if((teratermEnableCheck.isSelected() && passwordField.getText() == null) || (teratermEnableCheck.isSelected() && passwordField.getText().trim().isEmpty())){
                Image notifimg = new Image(
                    getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
                Node notimg = new ImageView(notifimg);
                notif = Notifications.create()
                    .graphic(notimg)
                    .title("Warning")
                    .text("Field 'Password Field' is Empty")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.CENTER)
                    .darkStyle()
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                        }
                    });
                notif.show();
                return false;
        } else if((teratermEnableCheck.isSelected() && returnLoginField.getText() == null) || (teratermEnableCheck.isSelected() &&  returnLoginField.getText().trim().isEmpty())){
                Image notifimg = new Image(
                    getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
                Node notimg = new ImageView(notifimg);
                notif = Notifications.create()
                    .graphic(notimg)
                    .title("Warning")
                    .text("Field 'Return Login Field' is Empty")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.CENTER)
                    .darkStyle()
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                        }
                    });
                notif.show();
                return false;
        } else if((teratermEnableCheck.isSelected() && returnPasswordField.getText() == null) || (teratermEnableCheck.isSelected() && returnPasswordField.getText().trim().isEmpty())){
                Image notifimg = new Image(
                    getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
                Node notimg = new ImageView(notifimg);
                notif = Notifications.create()
                    .graphic(notimg)
                    .title("Warning")
                    .text("Field 'Return Password Field' is Empty")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.CENTER)
                    .darkStyle()
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                        }
                    });
                notif.show();
                return false;
        } else if((teratermEnableCheck.isSelected() && commandField.getText() == null) || (teratermEnableCheck.isSelected() && commandField.getText().trim().isEmpty())){
                Image notifimg = new Image(
                    getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
                Node notimg = new ImageView(notifimg);
                notif = Notifications.create()
                    .graphic(notimg)
                    .title("Warning")
                    .text("Field 'Command Field' is Empty")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.CENTER)
                    .darkStyle()
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                        }
                    });
                notif.show();
                return false;
        } else if((teratermEnableCheck.isSelected() && modeField.getText() == null) || (teratermEnableCheck.isSelected() && modeField.getText().trim().isEmpty())){
                Image notifimg = new Image(
                    getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
                Node notimg = new ImageView(notifimg);
                notif = Notifications.create()
                    .graphic(notimg)
                    .title("Warning")
                    .text("Field 'Mode Field' is Empty")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.CENTER)
                    .darkStyle()
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                        }
                    });
                notif.show();
                return false;
        } else if((teratermEnableCheck.isSelected() && teratermField.getText() == null) || (teratermEnableCheck.isSelected() && teratermField.getText().trim().isEmpty())){
                Image notifimg = new Image(
                    getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
                Node notimg = new ImageView(notifimg);
                notif = Notifications.create()
                    .graphic(notimg)
                    .title("Warning")
                    .text("Field 'Trace File Teraterm' is Empty")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.CENTER)
                    .darkStyle()
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                        }
                    });
                notif.show();
                return false;
        } else {
            variableNomPrenom = nomprenomField.getText();
            variableMatricule = matriculeField.getText();
            variableDate = dateField.getValue().toString();
            variableCompany = companyField.getText();
            variableSite = siteField.getText();
            variableEnvironment = environmentField.getText();
            variableTest = testField.getText();
            variableManufacturer = manufacturerField.getText();
            variableModel = modelField.getValue();
            variableSoftVersion = softwareField.getText();
            variableSerialNumber = snField.getText();
            variableHWVersion = hwField.getText();
            variableGWYIPAddr = gwyIpField.getText();
            variableMACAddr = macField.getText();
            variableLogin = loginField.getText();
            variablePassword = passwordField.getText();
            variableRLogin = returnLoginField.getText();
            variableRPassword = returnPasswordField.getText();
            variableRCmd = commandField.getText();
            variableMode = modeField.getText();
            variableTeratermTrace = teratermField.getText();
            variableSysInterface = syslogIPaddress.getSelectionModel().getSelectedItem();
            variableSysTrace = syslogField.getText();
            variableWiresharkInterface = wiresharkInterfaces.getSelectionModel().getSelectedItem();
            variableWiresharkTrace = wiresharkField.getText();
            variableTeratermCheck = teratermEnableCheck.isSelected();
            variableSyslogCheck = syslogEnableCheck.isSelected();
            variableWiresharkCheck = syslogEnableCheck.isSelected();
            return true;
        }
    }

    @FXML
    private void saveconfigurationButton(ActionEvent event) {
        try {
            if(checkFonction() == false){
                System.out.println("False");
            }else if (checkFonction() == true){
                PrintWriter writer = new PrintWriter(modelField.getValue() + "-" + matriculeField.getText() + "-" + dateField.getValue() + ".ini");
                writer.println("[User_INFO]");
                writer.println("Nom_Prenom = " + nomprenomField.getText());
                writer.println("Matricule = " + matriculeField.getText());
                writer.println("Date = " + dateField.getValue());
                writer.println("Time = " + timeField.getText());
                writer.println("Company = " + companyField.getText());
                writer.println("Site = " + siteField.getText());
                writer.println("Environnement = " + environmentField.getText());
                writer.println("Test = " + testField.getText());
                writer.println("Manufacturer = " + manufacturerField.getText());
                writer.println("Model = " + modelField.getValue());
                writer.println("\n[Gateway_INFO]");
                writer.println("SoftwareVersion = " + softwareField.getText());
                writer.println("SerialNumber = " + snField.getText());
                writer.println("HWVersion = " + hwField.getText());
                writer.println("GWYIPAddress = " + gwyIpField.getText());
                writer.println("MACAddress = " + macField.getText());
                writer.close();
                alert.setTitle("Save");
                alert.setHeaderText("Saving config file");
                alert.setContentText("All parameters are saved");
                alert.showAndWait();
                nextButton.setDisable(false);
            }
        } catch (IOException e) {
            // do something
        }
    }

    @FXML
    private void initializeButtonClick(ActionEvent event) {
        if(testerAnchorPane.isVisible()){
            nomprenomField.clear();
            matriculeField.clear();
            testField.clear();
            dateField.setValue(LocalDate.now());
            try {
                String command1 = "date +%H:%M:%S";
                p = Runtime.getRuntime().exec(command1);
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                timeField.setText(streamReader.readLine());
            } catch (IOException e) {
                System.out.println(e);
            }
            modelField.setValue("FAST5460");
            siteField.setText("TN - SST - Pôle ATR - Validation");
            environmentField.setText("Open space");
            manufacturerField.setText("Sagemcom");
            companyField.setText("Sagemcom");
        }
        if(gatewayAnchorPane.isVisible()){
            softwareField.clear();
            snField.clear();
            hwField.clear();
            gwyIpField.clear();
            macField.clear();
        }
        if(logsAnchorPane.isVisible()){
            loginField.clear();
            passwordField.clear();
            returnLoginField.clear();
            returnPasswordField.clear();
            commandField.clear();
            syslogField.clear();
            wiresharkField.clear();
            modeField.clear();
            teratermField.clear();
            wiresharkEnableCheck.setSelected(false);
            teratermEnableCheck.setSelected(false);
            syslogEnableCheck.setSelected(false);
            wiresharkFields.setDisable(true);
            syslogFields.setDisable(true);
            teratermFields.setDisable(true);
        }
    }

    @FXML
    private void nextButtonClick(ActionEvent event) throws IOException {
        if (R2 == true){
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
        }else if (R1 == true){
            Stage stage = (Stage) nextButton.getScene().getWindow();
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
    private void importClick(ActionEvent event) throws IOException {
        if(listView.getText().isEmpty() || listView.getText().trim().isEmpty()){
            Image notifimg = new Image(
                getClass().getResourceAsStream("/vpnlite/img/Warning.png"));
            Node notimg = new ImageView(notifimg);
            notif = Notifications.create()
                .graphic(notimg)
                .title("Warning")
                .text("The path of file is unknown")
                .hideAfter(Duration.seconds(3))
                .position(Pos.CENTER)
                .darkStyle()
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    }
                });
            notif.show();
        }else{
            Ini ini = new Ini(new File(path));
            nomprenomField.setText(ini.get("User_INFO", "Nom_Prenom"));
            matriculeField.setText(ini.get("User_INFO", "Matricule"));
            dateField.setValue(LocalDate.parse(ini.get("User_INFO", "Date")));
            timeField.setText(ini.get("User_INFO", "Time"));
            companyField.setText(ini.get("User_INFO", "Company"));
            siteField.setText(ini.get("User_INFO", "Site"));
            environmentField.setText(ini.get("User_INFO", "Environnement"));
            testField.setText(ini.get("User_INFO", "Test"));
            manufacturerField.setText(ini.get("User_INFO", "Manufacturer"));

            softwareField.setText(ini.get("Gateway_INFO", "SoftwareVersion"));
            snField.setText(ini.get("Gateway_INFO", "SerialNumber"));
            hwField.setText(ini.get("Gateway_INFO", "HWVersion"));
            gwyIpField.setText(ini.get("Gateway_INFO", "GWYIPAddress"));
            macField.setText(ini.get("Gateway_INFO", "MACAddress"));
        }
    }
    
    @FXML
    private void dateRefresh(ActionEvent event) {
        dateField.setValue(LocalDate.now());
    }

    @FXML
    private void timeRefresh(ActionEvent event) {
        try {
            String command1 = "date +%H:%M:%S";
            p = Runtime.getRuntime().exec(command1);
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
            timeField.setText(streamReader.readLine());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    private void userPage(ActionEvent event) {
    }


    @FXML
    private void vpnPage(ActionEvent event) throws IOException {
        if(R2 == true){
            Stage stage = (Stage) backButton.getScene().getWindow();
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
        }else if(R1 == true){
            Stage stage = (Stage) backButton.getScene().getWindow();
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
    }

    @FXML
    private void testsPage(ActionEvent event) {
    }


    @FXML
    private void previousWindow(ActionEvent event) throws IOException {
        if(R2 == true){
            Stage stage = (Stage) backButton.getScene().getWindow();
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
        }else if(R1 == true){
            Stage stage = (Stage) backButton.getScene().getWindow();
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
    }

    @FXML
    private void testerinformationdisplay(MouseEvent event) {
        testerAnchorPane.setVisible(true);
        gatewayAnchorPane.setVisible(false);
        logsAnchorPane.setVisible(false);
        testerarrowimg.setVisible(true);
        gatewayarrowimg.setVisible(false);
    }

    @FXML
    private void gatewayinformationdisplay(MouseEvent event) {
        testerAnchorPane.setVisible(false);
        gatewayAnchorPane.setVisible(true);
        logsAnchorPane.setVisible(false);
        testerarrowimg.setVisible(false);
        gatewayarrowimg.setVisible(true);
    }

    @FXML
    private void testerinformationundisplay(MouseEvent event) {
        testerAnchorPane.setVisible(false);
        testerarrowimg.setVisible(false);
    }

    @FXML
    private void gatewayinformationundisplay(MouseEvent event) {
        gatewayAnchorPane.setVisible(false);
        gatewayarrowimg.setVisible(false);
    }

    private void logsinformationundisplay(MouseEvent event) {
        logsAnchorPane.setVisible(false);
        logsarrowimg.setVisible(false);
    }

    @FXML
    private void enableTeratermFields(ActionEvent event) {
        if(teratermEnableCheck.isSelected()){
            teratermFields.setDisable(false);
        }else {
            teratermFields.setDisable(true);
        }
    }

    @FXML
    private void enableSyslogFields(ActionEvent event) {
        if(syslogEnableCheck.isSelected()){
            syslogFields.setDisable(false);
        }else {
            syslogFields.setDisable(true);
        }
    }

    @FXML
    private void enableWiresharkFields(ActionEvent event) {
        if(wiresharkEnableCheck.isSelected()){
            wiresharkFields.setDisable(false);
        }else {
            wiresharkFields.setDisable(true);
        }
    }
}