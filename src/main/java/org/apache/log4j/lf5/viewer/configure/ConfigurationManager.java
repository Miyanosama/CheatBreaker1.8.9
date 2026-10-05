package org.apache.log4j.lf5.viewer.configure;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.tree.TreePath;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.log4j.lf5.LogLevel;
import org.apache.log4j.lf5.LogLevelFormatException;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor;
import org.apache.log4j.lf5.viewer.LogTable;
import org.apache.log4j.lf5.viewer.LogTableColumn;
import org.apache.log4j.lf5.viewer.LogTableColumnFormatException;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryExplorerModel;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryExplorerTree;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNode;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryPath;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConfigurationManager {
   public static final String recoveredField910 = "category";
   public static final String recoveredField911 = "colorlevel";
   public static final String recoveredField912 = "path";
   public static final String recoveredField913 = "column";
   public static final String recoveredField914 = "expanded";
   public static final String recoveredField915 = "lf5_configuration.xml";
   public static final String recoveredField916 = "Categories";
   public static final String recoveredField917 = "red";
   public static final String recoveredField918 = "searchtext";
   public static final String recoveredField919 = "selected";
   public LogBrokerMonitor _monitor = null;
   public static final String recoveredField920 = "name";
   public static final String recoveredField921 = "level";
   public LogTable _table = null;
   public static final String recoveredField922 = "blue";
   public static final String recoveredField923 = "green";

   public void save() {
      CategoryExplorerModel var1 = this._monitor.getCategoryExplorerTree().getExplorerModel();
      CategoryNode var2 = var1.getRootCategoryNode();
      StringBuffer var3 = new StringBuffer(2048);
      this.openXMLDocument(var3);
      this.openConfigurationXML(var3);
      this.processLogRecordFilter(this._monitor.getNDCTextFilter(), var3);
      this.processLogLevels(this._monitor.getLogLevelMenuItems(), var3);
      this.processLogLevelColors(this._monitor.getLogLevelMenuItems(), LogLevel.getLogLevelColorMap(), var3);
      this.processLogTableColumns(LogTableColumn.getLogTableColumns(), var3);
      this.processConfigurationNode(var2, var3);
      this.closeConfigurationXML(var3);
      this.store(var3.toString());
   }

   public void reset() {
      this.deleteConfigurationFile();
      this.collapseTree();
      this.selectAllNodes();
   }

   public String getValue(NamedNodeMap var1, String var2) {
      Node var3 = var1.getNamedItem(var2);
      return var3.getNodeValue();
   }

   public void processRecordFilter(Document var1) {
      NodeList var2 = var1.getElementsByTagName("searchtext");
      Node var3 = var2.item(0);
      if (var3 != null) {
         NamedNodeMap var4 = var3.getAttributes();
         String var5 = this.getValue(var4, "name");
         if (var5 != null && !var5.equals("")) {
            this._monitor.setNDCLogRecordFilter(var5);
         }
      }
   }

   public static String treePathToString(TreePath var0) {
      StringBuffer var1 = new StringBuffer();
      Object var2 = null;
      Object[] var3 = var0.getPath();

      for (int var4 = 1; var4 < var3.length; var4++) {
         var2 = (CategoryNode)var3[var4];
         if (var4 > 1) {
            var1.append(".");
         }

         var1.append(((CategoryNode)var2).getTitle());
      }

      return var1.toString();
   }

   public void processLogLevels(Document var1) {
      NodeList var2 = var1.getElementsByTagName("level");
      Map var3 = this._monitor.getLogLevelMenuItems();

      for (int var4 = 0; var4 < var2.getLength(); var4++) {
         Node var5 = var2.item(var4);
         NamedNodeMap var6 = var5.getAttributes();
         String var7 = this.getValue(var6, "name");

         try {
            JCheckBoxMenuItem var8 = (JCheckBoxMenuItem)var3.get(LogLevel.valueOf(var7));
            var8.setSelected(this.getValue(var6, "selected").equalsIgnoreCase("true"));
         } catch (LogLevelFormatException var9) {
         }
      }
   }

   public void collapseTree() {
      CategoryExplorerTree var1 = this._monitor.getCategoryExplorerTree();

      for (int var2 = var1.getRowCount() - 1; var2 > 0; var2--) {
         var1.collapseRow(var2);
      }
   }

   public void exportLogLevelColorXMLElement(String var1, Color var2, StringBuffer var3) {
      var3.append("\t\t<").append("colorlevel").append(" ").append("name");
      var3.append("=\"").append(var1).append("\" ");
      var3.append("red").append("=\"").append(var2.getRed()).append("\" ");
      var3.append("green").append("=\"").append(var2.getGreen()).append("\" ");
      var3.append("blue").append("=\"").append(var2.getBlue());
      var3.append("\"/>\r\n");
   }

   public void selectAllNodes() {
      CategoryExplorerModel var1 = this._monitor.getCategoryExplorerTree().getExplorerModel();
      CategoryNode var2 = var1.getRootCategoryNode();
      Enumeration var3 = var2.breadthFirstEnumeration();
      CategoryNode var4 = null;

      while (var3.hasMoreElements()) {
         var4 = (CategoryNode)var3.nextElement();
         var4.setSelected(true);
      }
   }

   public void store(String var1) {
      try {
         PrintWriter var2 = new PrintWriter(new FileWriter(this.getFilename()));
         var2.print(var1);
         var2.close();
      } catch (IOException var3) {
         var3.printStackTrace();
      }
   }

   public void processLogLevelColors(Map var1, Map var2, StringBuffer var3) {
      var3.append("\t<loglevelcolors>\r\n");

      for (LogLevel var5 : (Iterable<LogLevel>)(Iterable<?>)(var1.keySet())) {
         Color var6 = (Color)var2.get(var5);
         this.exportLogLevelColorXMLElement(var5.getLabel(), var6, var3);
      }

      var3.append("\t</loglevelcolors>\r\n");
   }

   public void processLogLevels(Map var1, StringBuffer var2) {
      var2.append("\t<loglevels>\r\n");

      for (LogLevel var4 : (Iterable<LogLevel>)(Iterable<?>)(var1.keySet())) {
         JCheckBoxMenuItem var5 = (JCheckBoxMenuItem)var1.get(var4);
         this.exportLogLevelXMLElement(var4.getLabel(), var5.isSelected(), var2);
      }

      var2.append("\t</loglevels>\r\n");
   }

   public void exportXMLElement(CategoryNode var1, TreePath var2, StringBuffer var3) {
      CategoryExplorerTree var4 = this._monitor.getCategoryExplorerTree();
      var3.append("\t<").append("category").append(" ");
      var3.append("name").append("=\"").append(var1.getTitle()).append("\" ");
      var3.append("path").append("=\"").append(treePathToString(var2)).append("\" ");
      var3.append("expanded").append("=\"").append(var4.isExpanded(var2)).append("\" ");
      var3.append("selected").append("=\"").append(var1.isSelected()).append("\"/>\r\n");
   }

   public void closeConfigurationXML(StringBuffer var1) {
      var1.append("</configuration>\r\n");
   }

   public void processLogRecordFilter(String var1, StringBuffer var2) {
      var2.append("\t<").append("searchtext").append(" ");
      var2.append("name").append("=\"").append(var1).append("\"");
      var2.append("/>\r\n");
   }

   public void exportLogTableColumnXMLElement(String var1, boolean var2, StringBuffer var3) {
      var3.append("\t\t<").append("column").append(" ").append("name");
      var3.append("=\"").append(var1).append("\" ");
      var3.append("selected").append("=\"").append(var2);
      var3.append("\"/>\r\n");
   }

   public void load() {
      File var1 = new File(this.getFilename());
      if (var1.exists()) {
         try {
            DocumentBuilderFactory var2 = DocumentBuilderFactory.newInstance();
            DocumentBuilder var3 = var2.newDocumentBuilder();
            Document var4 = var3.parse(var1);
            this.processRecordFilter(var4);
            this.processCategories(var4);
            this.processLogLevels(var4);
            this.processLogLevelColors(var4);
            this.processLogTableColumns(var4);
         } catch (Exception var5) {
            System.err.println("Unable process configuration file at " + this.getFilename() + ". Error Message=" + var5.getMessage());
         }
      }
   }

   public void openXMLDocument(StringBuffer var1) {
      var1.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n");
   }

   public void openConfigurationXML(StringBuffer var1) {
      var1.append("<configuration>\r\n");
   }

   public void processLogTableColumns(Document var1) {
      NodeList var2 = var1.getElementsByTagName("column");
      Map var3 = this._monitor.getLogTableColumnMenuItems();
      ArrayList var4 = new ArrayList();

      for (int var5 = 0; var5 < var2.getLength(); var5++) {
         Node var6 = var2.item(var5);
         if (var6 == null) {
            return;
         }

         NamedNodeMap var7 = var6.getAttributes();
         String var8 = this.getValue(var7, "name");

         try {
            LogTableColumn var9 = LogTableColumn.valueOf(var8);
            JCheckBoxMenuItem var10 = (JCheckBoxMenuItem)var3.get(var9);
            var10.setSelected(this.getValue(var7, "selected").equalsIgnoreCase("true"));
            if (var10.isSelected()) {
               var4.add(var9);
            }
         } catch (LogTableColumnFormatException var11) {
         }

         if (var4.isEmpty()) {
            this._table.setDetailedView();
         } else {
            this._table.setView(var4);
         }
      }
   }

   public void processCategories(Document var1) {
      CategoryExplorerTree var2 = this._monitor.getCategoryExplorerTree();
      CategoryExplorerModel var3 = var2.getExplorerModel();
      NodeList var4 = var1.getElementsByTagName("category");
      NamedNodeMap var5 = var4.item(0).getAttributes();
      int var6 = this.getValue(var5, "name").equalsIgnoreCase("Categories") ? 1 : 0;

      for (int var7 = var4.getLength() - 1; var7 >= var6; var7--) {
         Node var8 = var4.item(var7);
         var5 = var8.getAttributes();
         CategoryNode var9 = var3.addCategory(new CategoryPath(this.getValue(var5, "path")));
         var9.setSelected(this.getValue(var5, "selected").equalsIgnoreCase("true"));
         if (this.getValue(var5, "expanded").equalsIgnoreCase("true")) {
         }

         var2.expandPath(var3.getTreePathToRoot(var9));
      }
   }

   public void exportLogLevelXMLElement(String var1, boolean var2, StringBuffer var3) {
      var3.append("\t\t<").append("level").append(" ").append("name");
      var3.append("=\"").append(var1).append("\" ");
      var3.append("selected").append("=\"").append(var2);
      var3.append("\"/>\r\n");
   }

   public String getFilename() {
      String var1 = System.getProperty("user.home");
      String var2 = System.getProperty("file.separator");
      return var1 + var2 + "lf5" + var2 + "lf5_configuration.xml";
   }

   public void processConfigurationNode(CategoryNode var1, StringBuffer var2) {
      CategoryExplorerModel var3 = this._monitor.getCategoryExplorerTree().getExplorerModel();
      Enumeration var4 = var1.breadthFirstEnumeration();
      Object var5 = null;

      while (var4.hasMoreElements()) {
         var5 = (CategoryNode)var4.nextElement();
         this.exportXMLElement((CategoryNode)var5, var3.getTreePathToRoot((CategoryNode)var5), var2);
      }
   }

   public void processLogLevelColors(Document var1) {
      NodeList var2 = var1.getElementsByTagName("colorlevel");
      LogLevel.getLogLevelColorMap();

      for (int var3 = 0; var3 < var2.getLength(); var3++) {
         Node var4 = var2.item(var3);
         if (var4 == null) {
            return;
         }

         NamedNodeMap var5 = var4.getAttributes();
         String var6 = this.getValue(var5, "name");

         try {
            LogLevel var7 = LogLevel.valueOf(var6);
            int var8 = Integer.parseInt(this.getValue(var5, "red"));
            int var9 = Integer.parseInt(this.getValue(var5, "green"));
            int var10 = Integer.parseInt(this.getValue(var5, "blue"));
            Color var11 = new Color(var8, var9, var10);
            if (var7 != null) {
               var7.setLogLevelColorMap(var7, var11);
            }
         } catch (LogLevelFormatException var12) {
         }
      }
   }

   public ConfigurationManager(LogBrokerMonitor var1, LogTable var2) {
      this._monitor = var1;
      this._table = var2;
      this.load();
   }

   public void processLogTableColumns(List var1, StringBuffer var2) {
      var2.append("\t<logtablecolumns>\r\n");

      for (LogTableColumn var4 : (Iterable<LogTableColumn>)(Iterable<?>)(var1)) {
         JCheckBoxMenuItem var5 = this._monitor.getTableColumnMenuItem(var4);
         this.exportLogTableColumnXMLElement(var4.getLabel(), var5.isSelected(), var2);
      }

      var2.append("\t</logtablecolumns>\r\n");
   }

   public void deleteConfigurationFile() {
      try {
         File var1 = new File(this.getFilename());
         if (var1.exists()) {
            var1.delete();
         }
      } catch (SecurityException var2) {
         System.err.println("Cannot delete " + this.getFilename() + " because a security violation occured.");
      }
   }
}
