package org.apache.log4j.lf5.viewer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import org.apache.log4j.lf5.LogLevel;
import org.apache.log4j.lf5.LogRecord;
import org.apache.log4j.lf5.LogRecordFilter;
import org.apache.log4j.lf5.util.DateFormatManager;
import org.apache.log4j.lf5.util.LogFileParser;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryExplorerTree;
import org.apache.log4j.lf5.viewer.configure.ConfigurationManager;
import org.apache.log4j.lf5.viewer.configure.MRUFileManager;

public class LogBrokerMonitor {
   public List _displayedLogBrokerProperties;
   public static final String recoveredField2204 = "Detailed";
   public boolean _callSystemExitOnClose;
   public ConfigurationManager _configurationManager;
   public int _logMonitorFrameHeight;
   public JScrollPane _logTableScrollPane;
   public String _searchText;
   public Map _logTableColumnMenuItems;
   public LogTable _table;
   public Map _logLevelMenuItems;
   public int _fontSize;
   public File _fileLocation;
   public Object _lock;
   public List _levels;
   public Dimension _lastTableViewportSize;
   public CategoryExplorerTree _categoryExplorerTree;
   public String _fontName;
   public String _NDCTextFilter;
   public int _logMonitorFrameWidth = 550;
   public MRUFileManager _mruFileManager;
   public JFrame _logMonitorFrame;
   public List _columns;
   public JLabel _statusLabel;
   public JComboBox _fontSizeCombo;
   public boolean _isDisposed;
   public boolean _trackTableScrollPane;
   public String _currentView;
   public LogLevel _leastSevereDisplayedLogLevel;
   public boolean _loadSystemFonts;

   public LogBrokerMonitor(List var1) {
      this._logMonitorFrameHeight = 500;
      this._NDCTextFilter = "";
      this._leastSevereDisplayedLogLevel = LogLevel.DEBUG;
      this._lock = new Object();
      this._fontSize = 10;
      this._fontName = "Dialog";
      this._currentView = "Detailed";
      this._loadSystemFonts = false;
      this._trackTableScrollPane = true;
      this._callSystemExitOnClose = false;
      this._displayedLogBrokerProperties = new Vector();
      this._logLevelMenuItems = new HashMap();
      this._logTableColumnMenuItems = new HashMap();
      this._levels = null;
      this._columns = null;
      this._isDisposed = false;
      this._configurationManager = null;
      this._mruFileManager = null;
      this._fileLocation = null;
      this._levels = var1;
      this._columns = LogTableColumn.getLogTableColumns();
      String var2 = System.getProperty("monitor.exit");
      if (var2 == null) {
         var2 = "false";
      }

      var2 = var2.trim().toLowerCase();
      if (var2.equals("true")) {
         this._callSystemExitOnClose = true;
      }

      this.initComponents();
      this._logMonitorFrame.addWindowListener(new LogBrokerMonitor.LogBrokerMonitorWindowAdaptor(this));
   }

   public void closeAfterConfirm() {
      StringBuffer var1 = new StringBuffer();
      if (!this._callSystemExitOnClose) {
         var1.append("Are you sure you want to close the logging ");
         var1.append("console?\n");
         var1.append("(Note: This will not shut down the Virtual Machine,\n");
         var1.append("or the Swing event thread.)");
      } else {
         var1.append("Are you sure you want to exit?\n");
         var1.append("This will shut down the Virtual Machine.\n");
      }

      String var2 = "Are you sure you want to dispose of the Logging Console?";
      if (this._callSystemExitOnClose) {
         var2 = "Are you sure you want to exit?";
      }

      int var3 = JOptionPane.showConfirmDialog(this._logMonitorFrame, var1.toString(), var2, 2, 3, null);
      if (var3 == 0) {
         this.dispose();
      }
   }

   public JCheckBoxMenuItem createMenuItem(LogLevel var1) {
      JCheckBoxMenuItem var2 = new JCheckBoxMenuItem(var1.toString());
      var2.setSelected(true);
      var2.setMnemonic(var1.toString().charAt(0));
      var2.addActionListener(new LogBrokerMonitor$12(this));
      return var2;
   }

   public JMenuItem createHelpProperties() {
      String var1 = "LogFactor5 Properties";
      JMenuItem var2 = new JMenuItem("LogFactor5 Properties");
      var2.setMnemonic('l');
      var2.addActionListener(new LogBrokerMonitor$24(this));
      return var2;
   }

   public void setNDCTextFilter(String var1) {
      if (var1 == null) {
         this._NDCTextFilter = "";
      } else {
         this._NDCTextFilter = var1;
      }
   }

   public void setMaxNumberOfLogRecords(int var1) {
      this._table.getFilteredLogTableModel().setMaxNumberOfLogRecords(var1);
   }

   public JPanel createStatusArea() {
      JPanel var1 = new JPanel();
      JLabel var2 = new JLabel("No log records to display.");
      this._statusLabel = var2;
      var2.setHorizontalAlignment(2);
      var1.setBorder(BorderFactory.createEtchedBorder());
      var1.setLayout(new FlowLayout(0, 0, 0));
      var1.add(var2);
      return var1;
   }

   public void createMRUFileListMI(JMenu var1) {
      String[] var2 = this._mruFileManager.getMRUFileList();
      if (var2 != null) {
         var1.addSeparator();

         for (int var3 = 0; var3 < var2.length; var3++) {
            JMenuItem var4 = new JMenuItem(var3 + 1 + " " + var2[var3]);
            var4.setMnemonic(var3 + 1);
            var4.addActionListener(new LogBrokerMonitor$19(this));
            var1.add(var4);
         }
      }
   }

   public void setLeastSevereDisplayedLogLevel(LogLevel var1) {
      if (var1 != null && this._leastSevereDisplayedLogLevel != var1) {
         this._leastSevereDisplayedLogLevel = var1;
         this._table.getFilteredLogTableModel().refresh();
         this.updateStatusLabel();
      }
   }

   public void sortByNDC() {
      String var1 = this._NDCTextFilter;
      if (var1 != null && var1.length() != 0) {
         this._table.getFilteredLogTableModel().setLogRecordFilter(this.createNDCLogRecordFilter(var1));
      }
   }

   public void setTitle(String var1) {
      this._logMonitorFrame.setTitle(var1 + " - LogFactor5");
   }

   public JMenuItem createEditSortNDCMI() {
      JMenuItem var1 = new JMenuItem("Sort by NDC");
      var1.setMnemonic('s');
      var1.addActionListener(new LogBrokerMonitor$27(this));
      return var1;
   }

   public JMenuItem createResetLogLevelColorMenuItem() {
      JMenuItem var1 = new JMenuItem("Reset LogLevel Colors");
      var1.setMnemonic('r');
      var1.addActionListener(new LogBrokerMonitor$10(this));
      return var1;
   }

   public void selectRow(int var1) {
      if (var1 == -1) {
         String var2 = this._searchText + " not found.";
         JOptionPane.showMessageDialog(this._logMonitorFrame, var2, "Text not found", 1);
      } else {
         LF5SwingUtils.selectRow(var1, this._table, this._logTableScrollPane);
      }
   }

   public void setCallSystemExitOnClose(boolean var1) {
      this._callSystemExitOnClose = var1;
   }

   public JMenuItem createNoLogLevelsMenuItem() {
      JMenuItem var1 = new JMenuItem("Hide all LogLevels");
      var1.setMnemonic('h');
      var1.addActionListener(new LogBrokerMonitor$9(this));
      return var1;
   }

   public void requestOpenMRU(ActionEvent var1) {
      String var2 = var1.getActionCommand();
      StringTokenizer var3 = new StringTokenizer(var2);
      String var4 = var3.nextToken().trim();
      var2 = var3.nextToken("\n");

      try {
         int var5 = Integer.parseInt(var4) - 1;
         InputStream var6 = this._mruFileManager.getInputStream(var5);
         LogFileParser var7 = new LogFileParser(var6);
         var7.parse(this);
         this._mruFileManager.moveToTop(var5);
         this.updateMRUList();
      } catch (Exception var8) {
         new LogFactor5ErrorDialog(this.getBaseFrame(), "Unable to load file " + var2);
      }
   }

   public void clearDetailTextArea() {
      this._table._detailTextArea.setText("");
   }

   public JMenu createViewMenu() {
      JMenu var1 = new JMenu("View");
      var1.setMnemonic('v');
      Iterator var2 = this.getLogTableColumns();

      while (var2.hasNext()) {
         var1.add((JMenuItem)this.getLogTableColumnMenuItem((LogTableColumn)var2.next()));
      }

      var1.addSeparator();
      var1.add(this.createAllLogTableColumnsMenuItem());
      var1.add(this.createNoLogTableColumnsMenuItem());
      return var1;
   }

   public Iterator getLogTableColumns() {
      return this._columns.iterator();
   }

   public void setView(String var1, LogTable var2) {
      if ("Detailed".equals(var1)) {
         var2.setDetailedView();
         this._currentView = var1;
      } else {
         String var3 = var1 + "does not match a supported view.";
         throw new IllegalArgumentException(var3);
      }
   }

   public void selectAllLogLevels(boolean var1) {
      Iterator var2 = this.getLogLevels();

      while (var2.hasNext()) {
         this.getMenuItem((LogLevel)var2.next()).setSelected(var1);
      }
   }

   public JComboBox createLogLevelCombo() {
      JComboBox var1 = new JComboBox();
      Iterator var2 = this.getLogLevels();

      while (var2.hasNext()) {
         var1.addItem(var2.next());
      }

      var1.setSelectedItem(this._leastSevereDisplayedLogLevel);
      var1.addActionListener(new LogBrokerMonitor$32(this));
      var1.setMaximumSize(var1.getPreferredSize());
      return var1;
   }

   public JCheckBoxMenuItem getTableColumnMenuItem(LogTableColumn var1) {
      return this.getLogTableColumnMenuItem(var1);
   }

   public void showPropertiesDialog(String var1) {
      JOptionPane.showMessageDialog(this._logMonitorFrame, this._displayedLogBrokerProperties.toArray(), var1, -1);
   }

   public JMenuItem createNoLogTableColumnsMenuItem() {
      JMenuItem var1 = new JMenuItem("Hide all Columns");
      var1.setMnemonic('h');
      var1.addActionListener(new LogBrokerMonitor$15(this));
      return var1;
   }

   public void setFontSizeSilently(int var1) {
      this._fontSize = var1;
      this.setFontSize(this._table._detailTextArea, var1);
      this.selectRow(0);
      this.setFontSize(this._table, var1);
   }

   public void requestExit() {
      this._mruFileManager.save();
      this.setCallSystemExitOnClose(true);
      this.closeAfterConfirm();
   }

   public void hide() {
      this._logMonitorFrame.setVisible(false);
   }

   public JMenuItem createOpenURLMI() {
      JMenuItem var1 = new JMenuItem("Open URL...");
      var1.setMnemonic('u');
      var1.addActionListener(new LogBrokerMonitor$17(this));
      return var1;
   }

   public JCheckBoxMenuItem getMenuItem(LogLevel var1) {
      JCheckBoxMenuItem var2 = (JCheckBoxMenuItem)this._logLevelMenuItems.get(var1);
      if (var2 == null) {
         var2 = this.createMenuItem(var1);
         this._logLevelMenuItems.put(var1, var2);
      }

      return var2;
   }

   public void setFontSize(Component var1, int var2) {
      Font var3 = var1.getFont();
      Font var4 = new Font(var3.getFontName(), var3.getStyle(), var2);
      var1.setFont(var4);
   }

   public JMenu createLogLevelColorMenu() {
      JMenu var1 = new JMenu("Configure LogLevel Colors");
      var1.setMnemonic('c');
      Iterator var2 = this.getLogLevels();

      while (var2.hasNext()) {
         var1.add(this.createSubMenuItem((LogLevel)var2.next()));
      }

      return var1;
   }

   public boolean loadLogFile(File var1) {
      boolean var2 = false;

      try {
         LogFileParser var3 = new LogFileParser(var1);
         var3.parse(this);
         var2 = true;
      } catch (IOException var5) {
         new LogFactor5ErrorDialog(this.getBaseFrame(), "Error reading " + var1.getName());
      }

      return var2;
   }

   public Iterator getLogLevels() {
      return this._levels.iterator();
   }

   public void requestClose() {
      this.setCallSystemExitOnClose(false);
      this.closeAfterConfirm();
   }

   public void updateMRUList() {
      JMenu var1 = this._logMonitorFrame.getJMenuBar().getMenu(0);
      var1.removeAll();
      var1.add(this.createOpenMI());
      var1.add(this.createOpenURLMI());
      var1.addSeparator();
      var1.add(this.createCloseMI());
      this.createMRUFileListMI(var1);
      var1.addSeparator();
      var1.add(this.createExitMI());
   }

   public CategoryExplorerTree getCategoryExplorerTree() {
      return this._categoryExplorerTree;
   }

   public List updateView() {
      ArrayList var1 = new ArrayList();

      for (LogTableColumn var3 : (Iterable<LogTableColumn>)(Iterable<?>)(this._columns)) {
         JCheckBoxMenuItem var4 = this.getLogTableColumnMenuItem(var3);
         if (var4.isSelected()) {
            var1.add(var3);
         }
      }

      return var1;
   }

   public void requestOpen() {
      JFileChooser var1;
      if (this._fileLocation == null) {
         var1 = new JFileChooser();
      } else {
         var1 = new JFileChooser(this._fileLocation);
      }

      int var2 = var1.showOpenDialog(this._logMonitorFrame);
      if (var2 == 0) {
         File var3 = var1.getSelectedFile();
         if (this.loadLogFile(var3)) {
            this._fileLocation = var1.getSelectedFile();
            this._mruFileManager.set(var3);
            this.updateMRUList();
         }
      }
   }

   public boolean getCallSystemExitOnClose() {
      return this._callSystemExitOnClose;
   }

   public String getStatusText(int var1, int var2) {
      StringBuffer var3 = new StringBuffer();
      var3.append("Displaying: ");
      var3.append(var1);
      var3.append(" records out of a total of: ");
      var3.append(var2);
      var3.append(" records.");
      return var3.toString();
   }

   public LogRecordFilter createNDCLogRecordFilter(String var1) {
      this._NDCTextFilter = var1;
      return new LogBrokerMonitor$4(this);
   }

   public void setNDCLogRecordFilter(String var1) {
      this._table.getFilteredLogTableModel().setLogRecordFilter(this.createNDCLogRecordFilter(var1));
   }

   public boolean matches(LogRecord var1, String var2) {
      String var3 = var1.getMessage();
      String var4 = var1.getNDC();
      return (var3 != null || var4 != null) && var2 != null
         ? var3.toLowerCase().indexOf(var2.toLowerCase()) != -1 || var4.toLowerCase().indexOf(var2.toLowerCase()) != -1
         : false;
   }

   public void setSearchText(String var1) {
      this._searchText = var1;
   }

   public Map getLogLevelMenuItems() {
      return this._logLevelMenuItems;
   }

   public void initComponents() {
      this._logMonitorFrame = new JFrame("LogFactor5");
      this._logMonitorFrame.setDefaultCloseOperation(0);
      String var1 = "/org/apache/log4j/lf5/viewer/images/lf5_small_icon.gif";
      URL var2 = this.getClass().getResource(var1);
      if (var2 != null) {
         this._logMonitorFrame.setIconImage(new ImageIcon(var2).getImage());
      }

      this.updateFrameSize();
      JTextArea var3 = this.createDetailTextArea();
      JScrollPane var4 = new JScrollPane(var3);
      this._table = new LogTable(var3);
      this.setView(this._currentView, this._table);
      this._table.setFont(new Font(this._fontName, 0, this._fontSize));
      this._logTableScrollPane = new JScrollPane(this._table);
      if (this._trackTableScrollPane) {
         this._logTableScrollPane.getVerticalScrollBar().addAdjustmentListener(new TrackingAdjustmentListener());
      }

      JSplitPane var5 = new JSplitPane();
      var5.setOneTouchExpandable(true);
      var5.setOrientation(0);
      var5.setLeftComponent(this._logTableScrollPane);
      var5.setRightComponent(var4);
      var5.setDividerLocation(350);
      this._categoryExplorerTree = new CategoryExplorerTree();
      this._table.getFilteredLogTableModel().setLogRecordFilter(this.createLogRecordFilter());
      JScrollPane var6 = new JScrollPane(this._categoryExplorerTree);
      var6.setPreferredSize(new Dimension(130, 400));
      this._mruFileManager = new MRUFileManager();
      JSplitPane var7 = new JSplitPane();
      var7.setOneTouchExpandable(true);
      var7.setRightComponent(var5);
      var7.setLeftComponent(var6);
      var7.setDividerLocation(130);
      this._logMonitorFrame.getRootPane().setJMenuBar(this.createMenuBar());
      this._logMonitorFrame.getContentPane().add(var7, "Center");
      this._logMonitorFrame.getContentPane().add(this.createToolBar(), "North");
      this._logMonitorFrame.getContentPane().add(this.createStatusArea(), "South");
      this.makeLogTableListenToCategoryExplorer();
      this.addTableModelProperties();
      this._configurationManager = new ConfigurationManager(this, this._table);
   }

   public int getFirstSelectedRow() {
      return this._table.getSelectionModel().getMinSelectionIndex();
   }

   public void setMaxRecordConfiguration() {
      LogFactor5InputDialog var1 = new LogFactor5InputDialog(this.getBaseFrame(), "Set Max Number of Records", "", 10);
      String var2 = var1.getText();
      if (var2 != null) {
         try {
            this.setMaxNumberOfLogRecords(Integer.parseInt(var2));
         } catch (NumberFormatException var5) {
            new LogFactor5ErrorDialog(this.getBaseFrame(), "'" + var2 + "' is an invalid parameter.\nPlease try again.");
            this.setMaxRecordConfiguration();
         }
      }
   }

   public void addDisplayedProperty(Object var1) {
      this._displayedLogBrokerProperties.add(var1);
   }

   public JMenuItem createCloseMI() {
      JMenuItem var1 = new JMenuItem("Close");
      var1.setMnemonic('c');
      var1.setAccelerator(KeyStroke.getKeyStroke("control Q"));
      var1.addActionListener(new LogBrokerMonitor$18(this));
      return var1;
   }

   public void selectAllLogTableColumns(boolean var1) {
      Iterator var2 = this.getLogTableColumns();

      while (var2.hasNext()) {
         this.getLogTableColumnMenuItem((LogTableColumn)var2.next()).setSelected(var1);
      }
   }

   public JFrame getBaseFrame() {
      return this._logMonitorFrame;
   }

   public JMenuItem createAllLogTableColumnsMenuItem() {
      JMenuItem var1 = new JMenuItem("Show all Columns");
      var1.setMnemonic('s');
      var1.addActionListener(new LogBrokerMonitor$14(this));
      return var1;
   }

   public JMenuItem createConfigureSave() {
      JMenuItem var1 = new JMenuItem("Save");
      var1.setMnemonic('s');
      var1.addActionListener(new LogBrokerMonitor$21(this));
      return var1;
   }

   public JMenuItem createConfigureReset() {
      JMenuItem var1 = new JMenuItem("Reset");
      var1.setMnemonic('r');
      var1.addActionListener(new LogBrokerMonitor$22(this));
      return var1;
   }

   public JMenu createLogLevelMenu() {
      JMenu var1 = new JMenu("Log Level");
      var1.setMnemonic('l');
      Iterator var2 = this.getLogLevels();

      while (var2.hasNext()) {
         var1.add((JMenuItem)this.getMenuItem((LogLevel)var2.next()));
      }

      var1.addSeparator();
      var1.add(this.createAllLogLevelsMenuItem());
      var1.add(this.createNoLogLevelsMenuItem());
      var1.addSeparator();
      var1.add((JMenuItem)this.createLogLevelColorMenu());
      var1.add(this.createResetLogLevelColorMenuItem());
      return var1;
   }

   public JCheckBoxMenuItem createLogTableColumnMenuItem(LogTableColumn var1) {
      JCheckBoxMenuItem var2 = new JCheckBoxMenuItem(var1.toString());
      var2.setSelected(true);
      var2.setMnemonic(var1.toString().charAt(0));
      var2.addActionListener(new LogBrokerMonitor$13(this));
      return var2;
   }

   public void centerFrame(JFrame var1) {
      Dimension var2 = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension var3 = var1.getSize();
      var1.setLocation((var2.width - var3.width) / 2, (var2.height - var3.height) / 2);
   }

   public JMenuItem createEditFindNextMI() {
      JMenuItem var1 = new JMenuItem("Find Next");
      var1.setMnemonic('n');
      var1.setAccelerator(KeyStroke.getKeyStroke("F3"));
      var1.addActionListener(new LogBrokerMonitor$25(this));
      return var1;
   }

   public Map getLogTableColumnMenuItems() {
      return this._logTableColumnMenuItems;
   }

   public JMenuItem createExitMI() {
      JMenuItem var1 = new JMenuItem("Exit");
      var1.setMnemonic('x');
      var1.addActionListener(new LogBrokerMonitor$20(this));
      return var1;
   }

   public void pause(int var1) {
      try {
         Thread.sleep(var1);
      } catch (InterruptedException var3) {
      }
   }

   public void updateStatusLabel() {
      this._statusLabel.setText(this.getRecordsDisplayedMessage());
   }

   public void show(int var1) {
      if (!this._logMonitorFrame.isVisible()) {
         SwingUtilities.invokeLater(new LogBrokerMonitor$1(this, var1));
      }
   }

   public void trackTableScrollPane() {
   }

   public void makeLogTableListenToCategoryExplorer() {
      LogBrokerMonitor$7 var1 = new LogBrokerMonitor$7(this);
      this._categoryExplorerTree.getExplorerModel().addActionListener(var1);
   }

   public LogRecordFilter createLogRecordFilter() {
      return new LogBrokerMonitor$3(this);
   }

   public String getNDCTextFilter() {
      return this._NDCTextFilter;
   }

   public JTextArea createDetailTextArea() {
      JTextArea var1 = new JTextArea();
      var1.setFont(new Font("Monospaced", 0, 14));
      var1.setTabSize(3);
      var1.setLineWrap(true);
      var1.setWrapStyleWord(false);
      return var1;
   }

   public void refreshDetailTextArea() {
      this.refresh(this._table._detailTextArea);
   }

   public boolean loadLogFile(URL var1) {
      boolean var2 = false;

      try {
         LogFileParser var3 = new LogFileParser(var1.openStream());
         var3.parse(this);
         var2 = true;
      } catch (IOException var5) {
         new LogFactor5ErrorDialog(this.getBaseFrame(), "Error reading URL:" + var1.getFile());
      }

      return var2;
   }

   public void resetConfiguration() {
      this._configurationManager.reset();
   }

   public void setDateFormatManager(DateFormatManager var1) {
      this._table.setDateFormatManager(var1);
   }

   public JToolBar createToolBar() {
      JToolBar var1 = new JToolBar();
      var1.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
      JComboBox var2 = new JComboBox();
      JComboBox var3 = new JComboBox();
      this._fontSizeCombo = var3;
      ClassLoader var4 = this.getClass().getClassLoader();
      if (var4 == null) {
         var4 = ClassLoader.getSystemClassLoader();
      }

      URL var5 = var4.getResource("org/apache/log4j/lf5/viewer/images/channelexplorer_new.gif");
      ImageIcon var6 = null;
      if (var5 != null) {
         var6 = new ImageIcon(var5);
      }

      JButton var7 = new JButton("Clear Log Table");
      if (var6 != null) {
         var7.setIcon(var6);
      }

      var7.setToolTipText("Clear Log Table.");
      var7.addActionListener(new LogBrokerMonitor$29(this));
      Toolkit var8 = Toolkit.getDefaultToolkit();
      String[] var9;
      if (this._loadSystemFonts) {
         var9 = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
      } else {
         var9 = var8.getFontList();
      }

      for (int var10 = 0; var10 < var9.length; var10++) {
         var2.addItem(var9[var10]);
      }

      var2.setSelectedItem(this._fontName);
      var2.addActionListener(new LogBrokerMonitor$30(this));
      var3.addItem("8");
      var3.addItem("9");
      var3.addItem("10");
      var3.addItem("12");
      var3.addItem("14");
      var3.addItem("16");
      var3.addItem("18");
      var3.addItem("24");
      var3.setSelectedItem(String.valueOf(this._fontSize));
      var3.addActionListener(new LogBrokerMonitor$31(this));
      var1.add(new JLabel(" Font: "));
      var1.add(var2);
      var1.add(var3);
      var1.addSeparator();
      var1.addSeparator();
      var1.add(var7);
      var7.setAlignmentY(0.5F);
      var7.setAlignmentX(0.5F);
      var2.setMaximumSize(var2.getPreferredSize());
      var3.setMaximumSize(var3.getPreferredSize());
      return var1;
   }

   public JMenuItem createEditRestoreAllNDCMI() {
      JMenuItem var1 = new JMenuItem("Restore all NDCs");
      var1.setMnemonic('r');
      var1.addActionListener(new LogBrokerMonitor$28(this));
      return var1;
   }

   public int changeFontSizeCombo(JComboBox var1, int var2) {
      int var3 = var1.getItemCount();
      Object var6 = var1.getItemAt(0);
      int var7 = Integer.parseInt(String.valueOf(var6));

      for (int var8 = 0; var8 < var3; var8++) {
         Object var5 = var1.getItemAt(var8);
         int var4 = Integer.parseInt(String.valueOf(var5));
         if (var7 < var4 && var4 <= var2) {
            var7 = var4;
            var6 = var5;
         }
      }

      var1.setSelectedItem(var6);
      return var7;
   }

   public void findSearchText() {
      String var1 = this._searchText;
      if (var1 != null && var1.length() != 0) {
         int var2 = this.getFirstSelectedRow();
         int var3 = this.findRecord(var2, var1, this._table.getFilteredLogTableModel().getFilteredRecords());
         this.selectRow(var3);
      }
   }

   public void saveConfiguration() {
      this._configurationManager.save();
   }

   public String getRecordsDisplayedMessage() {
      FilteredLogTableModel var1 = this._table.getFilteredLogTableModel();
      return this.getStatusText(var1.getRowCount(), var1.getTotalRowCount());
   }

   public void updateFrameSize() {
      this._logMonitorFrame.setSize(this._logMonitorFrameWidth, this._logMonitorFrameHeight);
      this.centerFrame(this._logMonitorFrame);
   }

   public void dispose() {
      this._logMonitorFrame.dispose();
      this._isDisposed = true;
      if (this._callSystemExitOnClose) {
         System.exit(0);
      }
   }

   public JMenu createHelpMenu() {
      JMenu var1 = new JMenu("Help");
      var1.setMnemonic('h');
      var1.add(this.createHelpProperties());
      return var1;
   }

   public JMenu createFileMenu() {
      JMenu var1 = new JMenu("File");
      var1.setMnemonic('f');
      var1.add(this.createOpenMI());
      var1.add(this.createOpenURLMI());
      var1.addSeparator();
      var1.add(this.createCloseMI());
      this.createMRUFileListMI(var1);
      var1.addSeparator();
      var1.add(this.createExitMI());
      return var1;
   }

   public JMenu createConfigureMenu() {
      JMenu var1 = new JMenu("Configure");
      var1.setMnemonic('c');
      var1.add(this.createConfigureSave());
      var1.add(this.createConfigureReset());
      var1.add(this.createConfigureMaxRecords());
      return var1;
   }

   public JMenuItem createSubMenuItem(LogLevel var1) {
      JMenuItem var2 = new JMenuItem(var1.toString());
      var2.setMnemonic(var1.toString().charAt(0));
      var2.addActionListener(new LogBrokerMonitor$11(this, var2, var1));
      return var2;
   }

   public JMenuItem createEditFindMI() {
      JMenuItem var1 = new JMenuItem("Find");
      var1.setMnemonic('f');
      var1.setAccelerator(KeyStroke.getKeyStroke("control F"));
      var1.addActionListener(new LogBrokerMonitor$26(this));
      return var1;
   }

   public void showLogLevelColorChangeDialog(JMenuItem var1, LogLevel var2) {
      Color var4 = JColorChooser.showDialog(this._logMonitorFrame, "Choose LogLevel Color", var1.getForeground());
      if (var4 != null) {
         var2.setLogLevelColorMap(var2, var4);
         this._table.getFilteredLogTableModel().refresh();
      }
   }

   public void setFontSize(int var1) {
      this.changeFontSizeCombo(this._fontSizeCombo, var1);
   }

   public JCheckBoxMenuItem getLogTableColumnMenuItem(LogTableColumn var1) {
      JCheckBoxMenuItem var2 = (JCheckBoxMenuItem)this._logTableColumnMenuItems.get(var1);
      if (var2 == null) {
         var2 = this.createLogTableColumnMenuItem(var1);
         this._logTableColumnMenuItems.put(var1, var2);
      }

      return var2;
   }

   public DateFormatManager getDateFormatManager() {
      return this._table.getDateFormatManager();
   }

   public void addTableModelProperties() {
      FilteredLogTableModel var1 = this._table.getFilteredLogTableModel();
      this.addDisplayedProperty(new LogBrokerMonitor$5(this));
      this.addDisplayedProperty(new LogBrokerMonitor$6(this, var1));
   }

   public void addMessage(LogRecord var1) {
      if (!this._isDisposed) {
         SwingUtilities.invokeLater(new LogBrokerMonitor$2(this, var1));
      }
   }

   public JMenuItem createAllLogLevelsMenuItem() {
      JMenuItem var1 = new JMenuItem("Show all LogLevels");
      var1.setMnemonic('s');
      var1.addActionListener(new LogBrokerMonitor$8(this));
      return var1;
   }

   public int findRecord(int var1, String var2, List var3) {
      if (var1 < 0) {
         var1 = 0;
      } else {
         var1++;
      }

      int var4 = var3.size();

      for (int var5 = var1; var5 < var4; var5++) {
         if (this.matches((LogRecord)var3.get(var5), var2)) {
            return var5;
         }
      }

      var4 = var1;

      for (int var8 = 0; var8 < var4; var8++) {
         if (this.matches((LogRecord)var3.get(var8), var2)) {
            return var8;
         }
      }

      return -1;
   }

   public JMenu createEditMenu() {
      JMenu var1 = new JMenu("Edit");
      var1.setMnemonic('e');
      var1.add(this.createEditFindMI());
      var1.add(this.createEditFindNextMI());
      var1.addSeparator();
      var1.add(this.createEditSortNDCMI());
      var1.add(this.createEditRestoreAllNDCMI());
      return var1;
   }

   public void show() {
      this.show(0);
   }

   public void requestOpenURL() {
      LogFactor5InputDialog var1 = new LogFactor5InputDialog(this.getBaseFrame(), "Open URL", "URL:");
      String var2 = var1.getText();
      if (var2 != null) {
         if (var2.indexOf("://") == -1) {
            var2 = "http://" + var2;
         }

         try {
            URL var3 = new URL(var2);
            if (this.loadLogFile(var3)) {
               this._mruFileManager.set(var3);
               this.updateMRUList();
            }
         } catch (MalformedURLException var5) {
            new LogFactor5ErrorDialog(this.getBaseFrame(), "Error reading URL.");
         }
      }
   }

   public JMenuBar createMenuBar() {
      JMenuBar var1 = new JMenuBar();
      var1.add(this.createFileMenu());
      var1.add(this.createEditMenu());
      var1.add(this.createLogLevelMenu());
      var1.add(this.createViewMenu());
      var1.add(this.createConfigureMenu());
      var1.add(this.createHelpMenu());
      return var1;
   }

   public void setFrameSize(int var1, int var2) {
      Dimension var3 = Toolkit.getDefaultToolkit().getScreenSize();
      if (0 < var1 && var1 < var3.width) {
         this._logMonitorFrameWidth = var1;
      }

      if (0 < var2 && var2 < var3.height) {
         this._logMonitorFrameHeight = var2;
      }

      this.updateFrameSize();
   }

   public JMenuItem createConfigureMaxRecords() {
      JMenuItem var1 = new JMenuItem("Set Max Number of Records");
      var1.setMnemonic('m');
      var1.addActionListener(new LogBrokerMonitor$23(this));
      return var1;
   }

   public JMenuItem createOpenMI() {
      JMenuItem var1 = new JMenuItem("Open...");
      var1.setMnemonic('o');
      var1.addActionListener(new LogBrokerMonitor$16(this));
      return var1;
   }

   public void refresh(JTextArea var1) {
      String var2 = var1.getText();
      var1.setText("");
      var1.setText(var2);
   }

   public class LogBrokerMonitorWindowAdaptor extends WindowAdapter {
      public LogBrokerMonitor _monitor;

      public void windowClosing(WindowEvent var1) {
         this._monitor.requestClose();
      }

      public LogBrokerMonitorWindowAdaptor(LogBrokerMonitor var2) {
         this._monitor = var2;
      }
   }
}
