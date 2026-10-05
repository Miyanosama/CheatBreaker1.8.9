package org.apache.log4j.chainsaw;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main extends JFrame {
   public static Class class$org$apache$log4j$chainsaw$Main;
   public static final int recoveredField806 = 4445;
   public static final String recoveredField807 = "chainsaw.port";
   public static Logger LOG = Logger.getLogger(
      class$org$apache$log4j$chainsaw$Main == null
         ? (class$org$apache$log4j$chainsaw$Main = class$("org.apache.log4j.chainsaw.Main"))
         : class$org$apache$log4j$chainsaw$Main
   );

   public void setupReceiver(MyTableModel var1) {
      int var2 = 4445;
      String var3 = System.getProperty("chainsaw.port");
      if (var3 != null) {
         try {
            var2 = Integer.parseInt(var3);
         } catch (NumberFormatException var6) {
            LOG.fatal("Unable to parse chainsaw.port property with value " + var3 + ".");
            JOptionPane.showMessageDialog(this, "Unable to parse port number from '" + var3 + "', quitting.", "CHAINSAW", 0);
            System.exit(1);
         }
      }

      try {
         LoggingReceiver var4 = new LoggingReceiver(var1, var2);
         var4.start();
      } catch (IOException var5) {
         LOG.fatal("Unable to connect to socket server, quiting", var5);
         JOptionPane.showMessageDialog(this, "Unable to create socket on port " + var2 + ", quitting.", "CHAINSAW", 0);
         System.exit(1);
      }
   }

   public static void main(String[] var0) {
      initLog4J();
      new Main();
   }

   public Main() {
      super("CHAINSAW - Log4J Log Viewer");
      MyTableModel var1 = new MyTableModel();
      JMenuBar var2 = new JMenuBar();
      this.setJMenuBar(var2);
      JMenu var3 = new JMenu("File");
      var2.add(var3);

      try {
         LoadXMLAction var4 = new LoadXMLAction(this, var1);
         JMenuItem var5 = new JMenuItem("Load file...");
         var3.add(var5);
         var5.addActionListener(var4);
      } catch (NoClassDefFoundError var10) {
         LOG.info("Missing classes for XML parser", var10);
         JOptionPane.showMessageDialog(this, "XML parser not in classpath - unable to load XML events.", "CHAINSAW", 0);
      } catch (Exception var11) {
         LOG.info("Unable to create the action to load XML files", var11);
         JOptionPane.showMessageDialog(this, "Unable to create a XML parser - unable to load XML events.", "CHAINSAW", 0);
      }

      JMenuItem var12 = new JMenuItem("Exit");
      var3.add(var12);
      var12.addActionListener(ExitAction.INSTANCE);
      ControlPanel var13 = new ControlPanel(var1);
      this.getContentPane().add(var13, "North");
      JTable var6 = new JTable(var1);
      var6.setSelectionMode(0);
      JScrollPane var7 = new JScrollPane(var6);
      var7.setBorder(BorderFactory.createTitledBorder("Events: "));
      var7.setPreferredSize(new Dimension(900, 300));
      DetailPanel var8 = new DetailPanel(var6, var1);
      var8.setPreferredSize(new Dimension(900, 300));
      JSplitPane var9 = new JSplitPane(0, var7, var8);
      this.getContentPane().add(var9, "Center");
      this.addWindowListener(new Main$1(this));
      this.pack();
      this.setVisible(true);
      this.setupReceiver(var1);
   }

   // $VF: synthetic method
   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw (NoClassDefFoundError)new NoClassDefFoundError().initCause(var2);
      }
   }

   public static void initLog4J() {
      Properties var0 = new Properties();
      var0.setProperty("log4j.rootLogger", "DEBUG, A1");
      var0.setProperty("log4j.appender.A1", "org.apache.log4j.ConsoleAppender");
      var0.setProperty("log4j.appender.A1.layout", "org.apache.log4j.TTCCLayout");
      PropertyConfigurator.configure(var0);
   }
}
