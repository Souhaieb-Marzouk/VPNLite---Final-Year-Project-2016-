/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vpnlite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Souhaieb
 */
public class VPNLite extends Application {
    
    private Process p;
    private String app = "VPNLite.jar";
    
    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        
        try {
            String result ="";
            p = Runtime.getRuntime().exec("find -name VPNLite.jar");
            p.waitFor();
            InputStreamReader ipsr=new InputStreamReader(p.getInputStream());
            BufferedReader br=new BufferedReader(ipsr);
            String ligne;
            while ((ligne=br.readLine())!=null)
            {
                p = Runtime.getRuntime().exec("unzip -o "+ligne+" -d /tmp/VPNLite");
                p.waitFor();
                System.out.println ("exit: " + p.exitValue());
                System.out.println(ligne);
                result += ligne;
            }
        } catch (IOException ex) {
            Logger.getLogger(VPNLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*int len;
        if ((len = p.getErrorStream().available()) > 0) {
          byte[] buf = new byte[len];
          p.getErrorStream().read(buf);
          System.err.println("Command error:\t\""+new String(buf)+"\"");
        }*/
        
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/ExternalIPAddress.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/CheckProgram.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/InterfaceName.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/InternalIPAddress.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/OsRelease.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/PingTest.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/TracerouteTest.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/remoteippptp.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/pptpd-options /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/ethpool.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/killprocess.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/PingResultTest.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/TRResultTest.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/DelStrongswan.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/InstallStrongswan.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/DelSNMP.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/UPDATE.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/L2TPPPPIPSecSTunnel.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/L2TPPPPIPSecSTransport.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/L2TPPPPIPSecCTransport.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/L2TPPPPIPSecCTunnel.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/GRETunnelC.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/GRETunnelS.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/L2TPIPSecSTransport.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/L2TPIPSecSTunnel.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/L2TPIPSecSTunnelC.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/L2TPIPSecCTransport.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/L2TPIPSecServerStop.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/L2TPIPSecClientStop.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/PPTPClient.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/PPTPServer.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/PPTPClientStop.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/PPTPServerStop.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/GREClientStop.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/GREServerStop.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/L2TPIPSecCTunnel.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/IPerf3ClientTCP.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/IPerf3ClientUDP.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/IPerf3Server.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/ResultToExcel.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/InstallPPPoE.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/DelPPPoE.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/PPPoEPPPIPServer.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/PPPoEPPPIPClient.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/Wireshark.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/GWYIPAddress.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/RouteAdd.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/L2TPIPSecSTunnelS.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/InterfacePPP0.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/FTPTest.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/WGETTest.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/CurlTest.sh /usr/local/bin");
        p = Runtime.getRuntime().exec("mv -f /tmp/VPNLite/vpnlite/scripts/CurlResult.sh /usr/local/bin");
        p.waitFor();
        /*int len;
        if ((len = p.getErrorStream().available()) > 0) {
          byte[] buf = new byte[len];
          p.getErrorStream().read(buf);
          System.err.println("Command error:\t\""+new String(buf)+"\"");
        }*/
        System.out.println ("exit: " + p.exitValue());
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/ExternalIPAddress.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/CheckProgram.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/InterfaceName.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/InternalIPAddress.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/OsRelease.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/PingTest.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/TracerouteTest.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/remoteippptp.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/pptpd-options");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/ethpool.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/killprocess.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/PingResultTest.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/TRResultTest.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/InstallStrongswan.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/DelStrongswan.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/DelSNMP.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/UPDATE.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/L2TPPPPIPSecSTunnel.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/L2TPPPPIPSecSTransport.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/L2TPPPPIPSecCTransport.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/L2TPPPPIPSecCTunnel.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/GRETunnelC.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/GRETunnelS.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/L2TPIPSecSTransport.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/L2TPIPSecSTunnel.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/L2TPIPSecSTunnelC.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/L2TPIPSecCTransport.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/L2TPIPSecServerStop.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/L2TPIPSecClientStop.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/PPTPClient.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/PPTPServer.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/PPTPClientStop.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/PPTPServerStop.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/GREServerStop.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/GREClientStop.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/L2TPIPSecCTunnel.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/IPerf3ClientTCP.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/IPerf3ClientUDP.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/IPerf3Server.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/ResultToExcel.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/InstallPPPoE.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/DelPPPoE.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/PPPoEPPPIPServer.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/PPPoEPPPIPClient.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/Wireshark.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/GWYIPAddress.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/RouteAdd.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/L2TPIPSecSTunnelS.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/InterfacePPP0.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/FTPTest.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/WGETTest.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/CurlTest.sh");
        p = Runtime.getRuntime().exec("chmod 755 /usr/local/bin/CurlResult.sh");
        p.waitFor();
        System.out.println ("exit: " + p.exitValue());
        p = Runtime.getRuntime().exec("rm -r /tmp/VPNLite");
        p.waitFor();
        System.out.println ("exit: " + p.exitValue());
        
        
        Runnable t1 = new Runnable(){
            public void run(){
                try {
                    //Parent root = FXMLLoader.load(getClass().getResource("view/NetworkingTestsOverview.fxml"));
                    Parent root = FXMLLoader.load(getClass().getResource("view/PrincipalOverview.fxml"));
                    Scene scene = new Scene(root);
                    System.out.println("Name of OS: " + System.getProperty("os.name"));
                    System.out.println("Version of OS: " + System.getProperty("os.version"));
                    System.out.println("Architecture of OS: " + System.getProperty("os.arch"));
                    System.out.println("Architecture of OS: " + System.getProperty("line.separator"));
                    //primaryStage.initStyle(StageStyle.UTILITY); desactive "minimize" & "maximize"
                    primaryStage.setMaximized(true);
                    primaryStage.setTitle("VPNLite");
                    primaryStage.setScene(scene);
                    //primaryStage.initStyle(StageStyle.TRANSPARENT);
                    primaryStage.show();
                    
                } catch (IOException ex) {
                    Logger.getLogger(VPNLite.class.getName()).log(Level.SEVERE, null, ex);
                }
            };
        };
        new Thread(t1).run();
        
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    } 
}
