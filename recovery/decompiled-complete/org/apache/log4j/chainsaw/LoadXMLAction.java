package org.apache.log4j.chainsaw;

import io.netty.util.internal.PendingWrite$1;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.StringReader;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.SAXParserFactory;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$1;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class LoadXMLAction extends AbstractAction {
   public static Class class$org$apache$log4j$chainsaw$LoadXMLAction;
   public XMLReader mParser;
   public static Logger LOG = Logger.getLogger(
      class$org$apache$log4j$chainsaw$LoadXMLAction == null
         ? (class$org$apache$log4j$chainsaw$LoadXMLAction = class$("org.apache.log4j.chainsaw.LoadXMLAction"))
         : class$org$apache$log4j$chainsaw$LoadXMLAction
   );
   public XMLFileHandler mHandler;
   public PendingWrite$1 field_0000;
   public StructureOceanMonumentPieces$1 field_0001;
   public JFileChooser mChooser = new JFileChooser();
   public JFrame mParent;

   public int loadFile(String var1) {
      synchronized (this.mParser) {
         StringBuffer var3 = new StringBuffer();
         var3.append("<?xml version=\"1.0\" standalone=\"yes\"?>\n");
         var3.append("<!DOCTYPE log4j:eventSet ");
         var3.append("[<!ENTITY data SYSTEM \"file:///");
         var3.append(var1);
         var3.append("\">]>\n");
         var3.append("<log4j:eventSet xmlns:log4j=\"Claira\">\n");
         var3.append("&data;\n");
         var3.append("</log4j:eventSet>\n");
         InputSource var4 = new InputSource(new StringReader(var3.toString()));
         this.mParser.parse(var4);
         return this.mHandler.getNumEvents();
      }
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }

   public void actionPerformed(ActionEvent var1) {
      LOG.info("load file called");
      if (this.mChooser.showOpenDialog(this.mParent) == 0) {
         LOG.info("Need to load a file");
         File var2 = this.mChooser.getSelectedFile();
         LOG.info("loading the contents of " + var2.getAbsolutePath());

         try {
            int var3 = this.loadFile(var2.getAbsolutePath());
            JOptionPane.showMessageDialog(this.mParent, "Loaded " + var3 + " events.", "CHAINSAW", 1);
         } catch (Exception var4) {
            LOG.warn("caught an exception loading the file", var4);
            JOptionPane.showMessageDialog(this.mParent, "Error parsing file - " + var4.getMessage(), "CHAINSAW", 0);
         }
      }
   }

   public LoadXMLAction(JFrame var1, MyTableModel var2) {
      this.mChooser.setMultiSelectionEnabled(false);
      this.mChooser.setFileSelectionMode(0);
      this.mParent = var1;
      this.mHandler = new XMLFileHandler(var2);
      this.mParser = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
      this.mParser.setContentHandler(this.mHandler);
   }
}
