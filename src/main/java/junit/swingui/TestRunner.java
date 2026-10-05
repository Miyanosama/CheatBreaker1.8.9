package junit.swingui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestFailure;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.runner.BaseTestRunner;
import junit.runner.FailureDetailView;
import junit.runner.SimpleTestCollector;
import junit.runner.TestCollector;
import junit.swingui.TestRunner$10;
import junit.swingui.MacProgressBar;
import junit.swingui.TestRunner$17;
import junit.swingui.TestRunner$8;
import junit.swingui.TestRunner$4;
import junit.swingui.TestRunner$19;
import junit.swingui.TestRunner$3;
import junit.swingui.TestRunner$14;
import junit.swingui.TestRunner$6;
import junit.swingui.AboutDialog;
import junit.swingui.TestRunner$9;
import junit.swingui.TestRunner$15;
import junit.swingui.TestRunner$16;
import junit.runner.Version;
import junit.swingui.TestRunner$5;

public class TestRunner extends BaseTestRunner implements TestRunContext {
   public Vector fTestRunViews = new Vector();
   public JButton recoveredField3704;
   public static final String recoveredField3705 = "TestCollectorClass";
   public JLabel fLogo;
   public JTabbedPane fTestViewTab;
   public static final String recoveredField3706 = "FailureViewClass";
   public CounterPanel fCounterPanel;
   public DefaultListModel fFailures;
   public TestResult fTestResult;
   public static Class class$0;
   public JComboBox fSuiteCombo;
   public StatusLine fStatusLine;
   public static final int recoveredField3707 = 4;
   public Thread fRunner;
   public JFrame fFrame;
   public JButton fRun;
   public JCheckBox fUseLoadingRunner;
   public JButton recoveredField3708;
   public static final int recoveredField3709 = 5;
   public FailureDetailView fFailureView;
   public ProgressBar fProgressIndicator;

   // $VF: synthetic method
   public static void access$9(TestRunner var0, Test var1) {
      var0.start(var1);
   }

   public static void method_00067(TestRunner var0, String var1) {
      var0.method_00035(var1);
   }

   public void aboutToStart(Test var1) {
      Enumeration var2 = this.fTestRunViews.elements();

      while (var2.hasMoreElements()) {
         TestRunView var3 = (TestRunView)var2.nextElement();
         var3.aboutToStart(var1, this.fTestResult);
      }
   }

   public void terminate() {
      this.fFrame.dispose();

      try {
         this.saveHistory();
      } catch (IOException var2) {
         System.out.println("Couldn't save test run history");
      }

      System.exit(0);
   }

   public void appendFailure(Test var1, Throwable var2) {
      this.fFailures.addElement(new TestFailure(var1, var2));
      if (this.fFailures.size() == 1) {
         this.revealFailure(var1);
      }
   }

   public void testFailed(int var1, Test var2, Throwable var3) {
      SwingUtilities.invokeLater(new TestRunner$1(this, var1, var2, var3));
   }

   public TestResult createTestResult() {
      return new TestResult();
   }

   public JComboBox createSuiteCombo() {
      JComboBox var1 = new JComboBox();
      var1.setEditable(true);
      var1.setLightWeightPopupEnabled(false);
      var1.getEditor().getEditorComponent().addKeyListener(new TestRunner$11(this));

      try {
         this.loadHistory(var1);
      } catch (IOException var3) {
      }

      var1.addItemListener(new TestRunner$12(this));
      return var1;
   }

   public ListModel getFailures() {
      return this.fFailures;
   }

   public void revealFailure(Test var1) {
      Enumeration var2 = this.fTestRunViews.elements();

      while (var2.hasMoreElements()) {
         TestRunView var3 = (TestRunView)var2.nextElement();
         var3.revealFailure(var1);
      }
   }

   public void testEnded(String var1) {
      this.synchUI();
      SwingUtilities.invokeLater(new TestRunner$2(this));
   }

   public void createMenus(JMenuBar var1) {
      var1.add(this.createJUnitMenu());
   }

   public JFrame createUI(String var1) {
      JFrame var2 = this.createFrame();
      JMenuBar var3 = new JMenuBar();
      this.createMenus(var3);
      var2.setJMenuBar(var3);
      JLabel var4 = new JLabel("Test class name:");
      this.fSuiteCombo = this.createSuiteCombo();
      this.fRun = this.createRunButton();
      var2.getRootPane().setDefaultButton(this.fRun);
      Component var5 = this.createBrowseButton();
      this.fUseLoadingRunner = this.createUseLoaderCheckBox();
      this.fStatusLine = this.createStatusLine();
      if (inMac()) {
         this.fProgressIndicator = new MacProgressBar(this.fStatusLine);
      } else {
         this.fProgressIndicator = new ProgressBar();
      }

      this.fCounterPanel = this.createCounterPanel();
      this.fFailures = new DefaultListModel();
      this.fTestViewTab = this.createTestRunViews();
      JPanel var6 = this.createFailedPanel();
      this.fFailureView = this.createFailureDetailView();
      JScrollPane var7 = new JScrollPane(this.fFailureView.getComponent(), 22, 32);
      this.recoveredField3704 = this.createQuitButton();
      this.fLogo = this.createLogo();
      JPanel var8 = new JPanel(new GridBagLayout());
      this.addGrid(var8, var4, 0, 0, 2, 2, 1.0, 17);
      this.addGrid(var8, this.fSuiteCombo, 0, 1, 1, 2, 1.0, 17);
      this.addGrid(var8, var5, 1, 1, 1, 0, 0.0, 17);
      this.addGrid(var8, this.fRun, 2, 1, 1, 2, 0.0, 10);
      this.addGrid(var8, this.fUseLoadingRunner, 0, 2, 3, 0, 1.0, 17);
      this.addGrid(var8, this.fProgressIndicator, 0, 3, 2, 2, 1.0, 17);
      this.addGrid(var8, this.fLogo, 2, 3, 1, 0, 0.0, 11);
      this.addGrid(var8, this.fCounterPanel, 0, 4, 2, 0, 0.0, 17);
      this.addGrid(var8, new JSeparator(), 0, 5, 2, 2, 1.0, 17);
      this.addGrid(var8, new JLabel("Results:"), 0, 6, 2, 2, 1.0, 17);
      JSplitPane var9 = new JSplitPane(0, this.fTestViewTab, var7);
      this.addGrid(var8, var9, 0, 7, 2, 1, 1.0, 17);
      this.addGrid(var8, var6, 2, 7, 1, 2, 0.0, 11);
      this.addGrid(var8, this.fStatusLine, 0, 9, 2, 2, 1.0, 10);
      this.addGrid(var8, this.recoveredField3704, 2, 9, 1, 2, 0.0, 10);
      var2.setContentPane(var8);
      var2.pack();
      var2.setLocation(200, 200);
      return var2;
   }

   public static void method_00100(TestRunner var0, String var1) {
      var0.method_00097(var1);
   }

   public JLabel createLogo() {
      Icon var2 = getIconResource(class$0 == null ? (class$0 = class$("junit.runner.BaseTestRunner")) : class$0, "logo.gif");
      JLabel var1;
      if (var2 != null) {
         var1 = new JLabel(var2);
      } else {
         var1 = new JLabel("JV");
      }

      var1.setToolTipText("JUnit Version " + Version.method_29434());
      return var1;
   }

   public JCheckBox createUseLoaderCheckBox() {
      boolean var1 = this.useReloadingTestSuiteLoader();
      JCheckBox var2 = new JCheckBox("Reload classes every run", var1);
      var2.setToolTipText("Use a custom class loader to reload the classes for every run");
      if (inVAJava()) {
         var2.setVisible(false);
      }

      return var2;
   }

   public JFrame createFrame() {
      JFrame var1 = new JFrame("JUnit");
      Image var2 = this.loadFrameIcon();
      if (var2 != null) {
         var1.setIconImage(var2);
      }

      var1.getContentPane().setLayout(new BorderLayout(0, 0));
      var1.addWindowListener(new TestRunner$7(this));
      return var1;
   }

   public synchronized void runTest(Test var1) {
      if (this.fRunner != null) {
         this.fTestResult.stop();
      } else {
         this.reset();
         if (var1 != null) {
            this.doRunTest(var1);
         }
      }
   }

   public void handleTestSelected(Test var1) {
      this.recoveredField3708.setEnabled(var1 != null && var1 instanceof TestCase);
      this.showFailureDetail(var1);
   }

   public static void method_00064(TestRunner var0) {
      var0.rerun();
   }

   public StatusLine createStatusLine() {
      return new StatusLine(380);
   }

   public Component createBrowseButton() {
      JButton var1 = new JButton("...");
      var1.setToolTipText("Select a Test class");
      var1.addActionListener(new TestRunner$10(this));
      return var1;
   }

   public static void method_00099(TestRunner var0) {
      var0.about();
   }

   public void showInfo(String var1) {
      this.fStatusLine.showInfo(var1);
   }

   public static void main(String[] var0) {
      new TestRunner().start(var0);
   }

   public TestCollector createTestCollector() {
      String var1 = BaseTestRunner.getPreference("TestCollectorClass");
      if (var1 != null) {
         Class<?> var2 = null;

         try {
            var2 = Class.forName(var1);
            return (TestCollector)var2.newInstance();
         } catch (Exception var4) {
            JOptionPane.showMessageDialog(this.fFrame, "Could not create TestCollector - using default collector");
         }
      }

      return new SimpleTestCollector();
   }

   public void testViewChanged() {
      TestRunView var1 = (TestRunView)this.fTestRunViews.elementAt(this.fTestViewTab.getSelectedIndex());
      var1.activate();
   }

   public void method_00046(String var1) {
      SwingUtilities.invokeLater(new TestRunner$14(this, var1));
   }

   public static void run(Class var0) {
      String[] var1 = new String[]{var0.getName()};
      main(var1);
   }

   public static Icon getIconResource(Class var0, String var1) {
      URL var2 = var0.getResource(var1);
      if (var2 == null) {
         System.err.println("Warning: could not load \"" + var1 + "\" icon");
         return null;
      } else {
         return new ImageIcon(var2);
      }
   }

   public Image loadFrameIcon() {
      ImageIcon var1 = (ImageIcon)getIconResource(class$0 == null ? (class$0 = class$("junit.runner.BaseTestRunner")) : class$0, "smalllogo.gif");
      return var1 != null ? var1.getImage() : null;
   }

   public void setButtonLabel(JButton var1, String var2) {
      SwingUtilities.invokeLater(new TestRunner$17(this, var1, var2));
   }

   public void synchUI() {
      try {
         SwingUtilities.invokeAndWait(new TestRunner$19(this));
      } catch (Exception var2) {
      }
   }

   public void method_00079(DocumentEvent var1) {
      this.textChanged();
   }

   public JTabbedPane createTestRunViews() {
      JTabbedPane var1 = new JTabbedPane(3);
      FailureRunView var2 = new FailureRunView(this);
      this.fTestRunViews.addElement(var2);
      var2.addTab(var1);
      TestHierarchyRunView var3 = new TestHierarchyRunView(this);
      this.fTestRunViews.addElement(var3);
      var3.addTab(var1);
      var1.addChangeListener(new TestRunner$13(this));
      return var1;
   }

   public void start(Test var1) {
      SwingUtilities.invokeLater(new TestRunner$18(this, var1));
   }

   public void rerunTest(Test var1) {
      if (!(var1 instanceof TestCase)) {
         this.showInfo("Could not reload " + var1.toString());
      } else {
         Test var2 = null;
         TestCase var3 = (TestCase)var1;

         try {
            Class var4 = this.getLoader().reload(var1.getClass());
            var2 = TestSuite.createTest(var4, var3.getName());
         } catch (Exception var6) {
            this.showInfo("Could not reload " + var1.toString());
            return;
         }

         TestResult var8 = new TestResult();
         var2.run(var8);
         String var5 = var2.toString();
         if (var8.wasSuccessful()) {
            this.showInfo(var5 + " was successful");
         } else if (var8.errorCount() == 1) {
            this.method_00097(var5 + " had an error");
         } else {
            this.method_00097(var5 + " had a failure");
         }
      }
   }

   public void reset() {
      this.fCounterPanel.reset();
      this.fProgressIndicator.reset();
      this.recoveredField3708.setEnabled(false);
      this.fFailureView.clear();
      this.fFailures.clear();
   }

   public void clearStatus() {
      this.fStatusLine.clear();
   }

   public void runFailed(String var1) {
      this.method_00097(var1);
      this.fRun.setText("Run");
      this.fRunner = null;
   }

   // $VF: synthetic method
   public static void access$13(TestRunner var0, JButton var1, String var2) {
      var0.setButtonLabel(var1, var2);
   }

   public static void method_00059(TestRunner var0, String var1) {
      var0.method_00046(var1);
   }

   public void addGrid(JPanel var1, Component var2, int var3, int var4, int var5, int var6, double var7, int var9) {
      GridBagConstraints var10 = new GridBagConstraints();
      var10.gridx = var3;
      var10.gridy = var4;
      var10.gridwidth = var5;
      var10.anchor = var9;
      var10.weightx = var7;
      var10.fill = var6;
      if (var6 == 1 || var6 == 3) {
         var10.weighty = 1.0;
      }

      var10.insets = new Insets(var4 == 0 ? 10 : 0, var3 == 0 ? 10 : 4, 4, 4);
      var1.add(var2, var10);
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError(var2.getMessage());
      }
   }

   public boolean shouldReload() {
      return !inVAJava() && this.fUseLoadingRunner.isSelected();
   }

   public static Thread access$1402(TestRunner var0, Thread var1) {
      return var0.fRunner = var1;
   }

   public void about() {
      AboutDialog var1 = new AboutDialog(this.fFrame);
      var1.show();
   }

   public static void method_00094(TestRunner var0, String var1) {
      var0.showInfo(var1);
   }

   public void addToHistory(String var1) {
      for (int var2 = 0; var2 < this.fSuiteCombo.getItemCount(); var2++) {
         if (var1.equals(this.fSuiteCombo.getItemAt(var2))) {
            this.fSuiteCombo.removeItemAt(var2);
            this.fSuiteCombo.insertItemAt(var1, 0);
            this.fSuiteCombo.setSelectedIndex(0);
            return;
         }
      }

      this.fSuiteCombo.insertItemAt(var1, 0);
      this.fSuiteCombo.setSelectedIndex(0);
      this.pruneHistory();
   }

   public void method_00103(DocumentEvent var1) {
      this.textChanged();
   }

   public void pruneHistory() {
      int var1 = getPreference("maxhistory", 5);
      if (var1 < 1) {
         var1 = 1;
      }

      for (int var2 = this.fSuiteCombo.getItemCount() - 1; var2 > var1 - 1; var2--) {
         this.fSuiteCombo.removeItemAt(var2);
      }
   }

   // $VF: synthetic method
   public static CounterPanel access$0(TestRunner var0) {
      return var0.fCounterPanel;
   }

   // $VF: synthetic method
   public static void access$2(TestRunner var0, Test var1, Throwable var2) {
      var0.appendFailure(var1, var2);
   }

   public void rerun() {
      TestRunView var1 = (TestRunView)this.fTestRunViews.elementAt(this.fTestViewTab.getSelectedIndex());
      Test var2 = var1.getSelectedTest();
      if (var2 != null) {
         this.rerunTest(var2);
      }
   }

   public void method_00097(String var1) {
      this.fStatusLine.showError(var1);
   }

   public JMenu createJUnitMenu() {
      JMenu var1 = new JMenu("JUnit");
      var1.setMnemonic('J');
      JMenuItem var2 = new JMenuItem("About...");
      var2.addActionListener(new TestRunner$5(this));
      var2.setMnemonic('A');
      var1.add(var2);
      var1.addSeparator();
      JMenuItem var3 = new JMenuItem(" Exit ");
      var3.addActionListener(new TestRunner$6(this));
      var3.setMnemonic('x');
      var1.add(var3);
      return var1;
   }

   public synchronized void runSuite() {
      if (this.fRunner != null) {
         this.fTestResult.stop();
      } else {
         this.setLoading(this.shouldReload());
         this.reset();
         this.showInfo("Load Test Case...");
         String var1 = this.getSuiteText();
         Test var2 = this.getTest(var1);
         if (var2 != null) {
            this.addToHistory(var1);
            this.doRunTest(var2);
         }
      }
   }

   public void method_00048(Test var1) {
      SwingUtilities.invokeLater(new TestRunner$3(this, var1));
   }

   public void saveHistory() throws java.io.IOException {
      BufferedWriter var1 = new BufferedWriter(new FileWriter(this.getSettingsFile()));

      try {
         for (int var2 = 0; var2 < this.fSuiteCombo.getItemCount(); var2++) {
            String var3 = this.fSuiteCombo.getItemAt(var2).toString();
            var1.write(var3, 0, var3.length());
            var1.newLine();
         }
      } finally {
         var1.close();
      }
   }

   public CounterPanel createCounterPanel() {
      return new CounterPanel();
   }

   public void doRunTest(Test var1) {
      this.setButtonLabel(this.fRun, "Stop");
      this.fRunner = new TestRunner$16(this, "TestRunner-Thread", var1);
      this.fTestResult = this.createTestResult();
      this.fTestResult.addListener(this);
      this.aboutToStart(var1);
      this.fRunner.start();
   }

   public String getSuiteText() {
      return this.fSuiteCombo == null ? "" : (String)this.fSuiteCombo.getEditor().getItem();
   }

   public void showFailureDetail(Test var1) {
      if (var1 != null) {
         ListModel var2 = this.getFailures();

         for (int var3 = 0; var3 < var2.getSize(); var3++) {
            TestFailure var4 = (TestFailure)var2.getElementAt(var3);
            if (var4.failedTest() == var1) {
               this.fFailureView.showFailure(var4);
               return;
            }
         }
      }

      this.fFailureView.clear();
   }

   public void textChanged() {
      this.fRun.setEnabled(this.getSuiteText().length() > 0);
      this.clearStatus();
   }

   public JButton createQuitButton() {
      JButton var1 = new JButton(" Exit ");
      var1.addActionListener(new TestRunner$8(this));
      return var1;
   }

   public void testStarted(String var1) {
      this.method_00046("Running: " + var1);
   }

   public void start(String[] var1) {
      String var2 = this.processArguments(var1);
      this.fFrame = this.createUI(var2);
      this.fFrame.pack();
      this.fFrame.setVisible(true);
      if (var2 != null) {
         this.setSuite(var2);
         this.runSuite();
      }
   }

   public void loadHistory(JComboBox var1) throws java.io.IOException {
      BufferedReader var2 = new BufferedReader(new FileReader(this.getSettingsFile()));
      int var3 = 0;

      try {
         String var4;
         while ((var4 = var2.readLine()) != null) {
            var1.addItem(var4);
            var3++;
         }

         if (var3 > 0) {
            var1.setSelectedIndex(0);
         }
      } finally {
         var2.close();
      }
   }

   public JPanel createFailedPanel() {
      JPanel var1 = new JPanel(new GridLayout(0, 1, 0, 2));
      this.recoveredField3708 = new JButton("Run");
      this.recoveredField3708.setEnabled(false);
      this.recoveredField3708.addActionListener(new TestRunner$4(this));
      var1.add(this.recoveredField3708);
      return var1;
   }

   public FailureDetailView createFailureDetailView() {
      String var1 = BaseTestRunner.getPreference("FailureViewClass");
      if (var1 != null) {
         Class<?> var2 = null;

         try {
            var2 = Class.forName(var1);
            return (FailureDetailView)var2.newInstance();
         } catch (Exception var4) {
            JOptionPane.showMessageDialog(this.fFrame, "Could not create Failure DetailView - using default view");
         }
      }

      return new DefaultFailureDetailView();
   }

   // $VF: synthetic method
   public static Vector access$4(TestRunner var0) {
      return var0.fTestRunViews;
   }

   public void method_00035(String var1) {
      SwingUtilities.invokeLater(new TestRunner$15(this, var1));
   }

   public void setSuite(String var1) {
      this.fSuiteCombo.getEditor().setItem(var1);
   }

   public void browseTestClasses() {
      TestCollector var1 = this.createTestCollector();
      TestSelector var2 = new TestSelector(this.fFrame, var1);
      if (var2.isEmpty()) {
         JOptionPane.showMessageDialog(this.fFrame, "No Test Cases found.\nCheck that the configured 'TestCollector' is supported on this platform.");
      } else {
         var2.show();
         String var3 = var2.getSelectedItem();
         if (var3 != null) {
            this.setSuite(var3);
         }
      }
   }

   // $VF: synthetic method
   public static ProgressBar access$3(TestRunner var0) {
      return var0.fProgressIndicator;
   }

   public Object instanciateClass(String var1, Object var2) {
      try {
         Class var3 = Class.forName(var1);
         if (var2 == null) {
            return var3.newInstance();
         } else {
            Class[] var4 = new Class[]{var2.getClass()};
            Constructor var5 = var3.getConstructor(var4);
            Object[] var6 = new Object[]{var2};
            return var5.newInstance(var6);
         }
      } catch (Exception var7) {
         var7.printStackTrace();
         return null;
      }
   }

   public File getSettingsFile() {
      String var1 = System.getProperty("user.home");
      return new File(var1, ".junitsession");
   }

   public JButton createRunButton() {
      JButton var1 = new JButton("Run");
      var1.setEnabled(true);
      var1.addActionListener(new TestRunner$9(this));
      return var1;
   }

   // $VF: synthetic method
   public static JButton access$12(TestRunner var0) {
      return var0.fRun;
   }

   // $VF: synthetic method
   public static TestResult access$1(TestRunner var0) {
      return var0.fTestResult;
   }
}
