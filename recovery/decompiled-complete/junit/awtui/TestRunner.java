package junit.awtui;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$KeySetView;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.image.ImageProducer;
import java.net.URL;
import java.util.Vector;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.runner.BaseTestRunner;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.realms.RealmsServerAddress;
import recovered.unidentified.UnidentifiedClass0122;
import recovered.unidentified.UnidentifiedClass0275;
import recovered.unidentified.UnidentifiedClass1118;
import recovered.unidentified.UnidentifiedClass1163;
import recovered.unidentified.UnidentifiedClass1440;
import recovered.unidentified.UnidentifiedClass1769;
import recovered.unidentified.UnidentifiedClass3543;
import recovered.unidentified.UnidentifiedClass4000;
import recovered.unidentified.UnidentifiedClass4372;

public class TestRunner extends BaseTestRunner {
   public List fFailureList;
   public Label fNumberOfFailures;
   public Logo fLogo;
   public Thread fRunner;
   public TextField fSuiteField;
   public Frame fFrame;
   public Vector fFailedTests;
   public TextArea fTraceArea;
   public ProgressBar fProgressIndicator;
   public Button field_0024;
   public TextField fStatusLine;
   public RealmsServerAddress field_0013;
   public Button fRun;
   public static Font PLAIN_FONT = new Font("dialog", 0, 12);
   public static int field_0017;
   public GuiDisconnected field_0021;
   public Checkbox fUseLoadingRunner;
   public Button field_0009;
   public Vector fExceptions;
   public Label fNumberOfRuns;
   public static Class class$0;
   public ServerAddress field_0001;
   public ConcurrentHashMapV8$KeySetView field_0004;
   public TestResult fTestResult;
   public Label fNumberOfErrors;

   public void testFailed(int var1, Test var2, Throwable var3) {
      switch (var1) {
         case 1:
            this.fNumberOfErrors.setText(Integer.toString(this.fTestResult.errorCount()));
            this.appendFailure("Error", var2, var3);
            break;
         case 2:
            this.fNumberOfFailures.setText(Integer.toString(this.fTestResult.failureCount()));
            this.appendFailure("Failure", var2, var3);
      }
   }

   public Panel createCounterPanel() {
      Panel var1 = new Panel(new GridBagLayout());
      this.addToCounterPanel(var1, new Label("Runs:"), 0, 0, 1, 1, 0.0, 0.0, 10, 0, new Insets(0, 0, 0, 0));
      this.addToCounterPanel(var1, this.fNumberOfRuns, 1, 0, 1, 1, 0.33, 0.0, 10, 2, new Insets(0, 8, 0, 40));
      this.addToCounterPanel(var1, new Label("Errors:"), 2, 0, 1, 1, 0.0, 0.0, 10, 0, new Insets(0, 8, 0, 0));
      this.addToCounterPanel(var1, this.fNumberOfErrors, 3, 0, 1, 1, 0.33, 0.0, 10, 2, new Insets(0, 8, 0, 40));
      this.addToCounterPanel(var1, new Label("Failures:"), 4, 0, 1, 1, 0.0, 0.0, 10, 0, new Insets(0, 8, 0, 0));
      this.addToCounterPanel(var1, this.fNumberOfFailures, 5, 0, 1, 1, 0.33, 0.0, 10, 2, new Insets(0, 8, 0, 0));
      return var1;
   }

   public Thread getRunner() {
      return this.fRunner;
   }

   public void clearStatus() {
      this.showStatus("");
   }

   public void rerunTest(Test var1) {
      if (!(var1 instanceof TestCase)) {
         this.method_28041("Could not reload " + var1.toString());
      } else {
         Test var2 = null;
         TestCase var3 = (TestCase)var1;

         try {
            Class var4 = this.getLoader().reload(var1.getClass());
            var2 = TestSuite.createTest(var4, var3.getName());
         } catch (Exception var6) {
            this.method_28041("Could not reload " + var1.toString());
            return;
         }

         TestResult var8 = new TestResult();
         var2.run(var8);
         String var5 = var2.toString();
         if (var8.wasSuccessful()) {
            this.method_28041(var5 + " was successful");
         } else if (var8.errorCount() == 1) {
            this.showStatus(var5 + " had an error");
         } else {
            this.showStatus(var5 + " had a failure");
         }
      }
   }

   public void setSuiteName(String var1) {
      this.fSuiteField.setText(var1);
   }

   public void setLabelValue(Label var1, int var2) {
      var1.setText(Integer.toString(var2));
      var1.invalidate();
      var1.getParent().validate();
   }

   public void about() {
      UnidentifiedClass1440 var1 = new UnidentifiedClass1440(this.fFrame);
      var1.setModal(true);
      var1.setLocation(300, 300);
      var1.setVisible(true);
   }

   public void runFailed(String var1) {
      this.showStatus(var1);
      this.fRun.setLabel("Run");
      this.fRunner = null;
   }

   public static void main(String[] var0) {
      new TestRunner().start(var0);
   }

   public void testEnded(String var1) {
      this.setLabelValue(this.fNumberOfRuns, this.fTestResult.runCount());
      synchronized (this) {
         this.fProgressIndicator.step(this.fTestResult.wasSuccessful());
      }
   }

   public void addGrid(Panel var1, Component var2, int var3, int var4, int var5, int var6, double var7, int var9) {
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

      var10.insets = new Insets(var4 == 0 ? 4 : 0, var3 == 0 ? 4 : 0, 4, 4);
      var1.add(var2, var10);
   }

   public void createMenus(MenuBar var1) {
      var1.add(this.createJUnitMenu());
   }

   public void method_28014() {
      this.setLabelValue(this.fNumberOfErrors, 0);
      this.setLabelValue(this.fNumberOfFailures, 0);
      this.setLabelValue(this.fNumberOfRuns, 0);
      this.fProgressIndicator.reset();
      this.field_0024.setEnabled(false);
      this.fFailureList.removeAll();
      this.fExceptions = new Vector(10);
      this.fFailedTests = new Vector(10);
      this.fTraceArea.setText("");
   }

   public void method_28016() {
      int var1 = this.fFailureList.getSelectedIndex();
      if (var1 != -1) {
         Throwable var2 = (Throwable)this.fExceptions.elementAt(var1);
         this.fTraceArea.setText(getFilteredTrace(var2));
      }
   }

   public boolean method_28040() {
      return !inVAJava() && this.fUseLoadingRunner.getState();
   }

   public void method_00096(String var1) {
      this.method_28041("Running: " + var1);
   }

   public static void method_28030(TestRunner var0, String var1) {
      var0.showStatus(var1);
   }

   public static Class method_28015(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError(var2.getMessage());
      }
   }

   public void addToCounterPanel(
      Panel var1, Component var2, int var3, int var4, int var5, int var6, double var7, double var9, int var11, int var12, Insets var13
   ) {
      GridBagConstraints var14 = new GridBagConstraints();
      var14.gridx = var3;
      var14.gridy = var4;
      var14.gridwidth = var5;
      var14.gridheight = var6;
      var14.weightx = var7;
      var14.weighty = var9;
      var14.anchor = var11;
      var14.fill = var12;
      var14.insets = var13;
      var1.add(var2, var14);
   }

   public TestResult createTestResult() {
      return new TestResult();
   }

   public Image loadFrameIcon() {
      Toolkit var1 = Toolkit.getDefaultToolkit();

      try {
         URL var2 = (class$0 == null ? (class$0 = method_28015("junit.runner.BaseTestRunner")) : class$0).getResource("smalllogo.gif");
         return var1.createImage((ImageProducer)var2.getContent());
      } catch (Exception var3) {
         return null;
      }
   }

   public void showStatus(String var1) {
      this.fStatusLine.setFont(PLAIN_FONT);
      this.fStatusLine.setForeground(Color.red);
      this.fStatusLine.setText(var1);
   }

   public void method_28041(String var1) {
      this.fStatusLine.setFont(PLAIN_FONT);
      this.fStatusLine.setForeground(Color.black);
      this.fStatusLine.setText(var1);
   }

   public void method_28042() {
      this.field_0024.setEnabled(this.method_28033());
      this.method_28016();
   }

   public static void run(Class var0) {
      String[] var1 = new String[]{var0.getName()};
      main(var1);
   }

   public static void access$0(TestRunner var0) {
      var0.about();
   }

   public static void method_28038(TestRunner var0, String var1) {
      var0.method_28041(var1);
   }

   public void start(String[] var1) {
      String var2 = this.processArguments(var1);
      this.fFrame = this.createUI(var2);
      this.fFrame.setLocation(200, 200);
      this.fFrame.setVisible(true);
      if (var2 != null) {
         this.setSuiteName(var2);
         this.method_28032();
      }
   }

   public Menu createJUnitMenu() {
      Menu var1 = new Menu("JUnit");
      MenuItem var2 = new MenuItem("About...");
      var2.addActionListener(new TestRunner$1(this));
      var1.add(var2);
      var1.addSeparator();
      var2 = new MenuItem("Exit");
      var2.addActionListener(new UnidentifiedClass0122(this));
      var1.add(var2);
      return var1;
   }

   public synchronized void method_28032() {
      if (this.fRunner != null && this.fTestResult != null) {
         this.fTestResult.stop();
      } else {
         this.setLoading(this.method_28040());
         this.fRun.setLabel("Stop");
         this.method_28041("Initializing...");
         this.method_28014();
         this.method_28041("Load Test Case...");
         Test var1 = this.getTest(this.fSuiteField.getText());
         if (var1 != null) {
            this.fRunner = new UnidentifiedClass0275(this, var1);
            this.fRunner.start();
         }
      }
   }

   public boolean method_28033() {
      return this.fFailureList.getSelectedIndex() != -1;
   }

   public Frame createUI(String var1) {
      Frame var2 = new Frame("JUnit");
      Image var3 = this.loadFrameIcon();
      if (var3 != null) {
         var2.setIconImage(var3);
      }

      var2.setLayout(new BorderLayout(0, 0));
      var2.setBackground(SystemColor.control);
      var2.addWindowListener(new UnidentifiedClass1163(this, var2));
      MenuBar var5 = new MenuBar();
      this.createMenus(var5);
      var2.setMenuBar(var5);
      Label var6 = new Label("Test class name:");
      this.fSuiteField = new TextField(var1 != null ? var1 : "");
      this.fSuiteField.selectAll();
      this.fSuiteField.requestFocus();
      this.fSuiteField.setFont(PLAIN_FONT);
      this.fSuiteField.setColumns(40);
      this.fSuiteField.addActionListener(new UnidentifiedClass4372(this));
      this.fSuiteField.addTextListener(new UnidentifiedClass1769(this));
      this.fRun = new Button("Run");
      this.fRun.setEnabled(false);
      this.fRun.addActionListener(new UnidentifiedClass1118(this));
      boolean var7 = this.useReloadingTestSuiteLoader();
      this.fUseLoadingRunner = new Checkbox("Reload classes every run", var7);
      if (inVAJava()) {
         this.fUseLoadingRunner.setVisible(false);
      }

      this.fProgressIndicator = new ProgressBar();
      this.fNumberOfErrors = new Label("0000", 2);
      this.fNumberOfErrors.setText("0");
      this.fNumberOfErrors.setFont(PLAIN_FONT);
      this.fNumberOfFailures = new Label("0000", 2);
      this.fNumberOfFailures.setText("0");
      this.fNumberOfFailures.setFont(PLAIN_FONT);
      this.fNumberOfRuns = new Label("0000", 2);
      this.fNumberOfRuns.setText("0");
      this.fNumberOfRuns.setFont(PLAIN_FONT);
      Panel var8 = this.createCounterPanel();
      Label var9 = new Label("Errors and Failures:");
      this.fFailureList = new List(5);
      this.fFailureList.addItemListener(new TestRunner$7(this));
      this.field_0024 = new Button("Run");
      this.field_0024.setEnabled(false);
      this.field_0024.addActionListener(new UnidentifiedClass3543(this));
      Panel var10 = new Panel(new GridLayout(0, 1, 0, 2));
      var10.add(this.field_0024);
      this.fTraceArea = new TextArea();
      this.fTraceArea.setRows(5);
      this.fTraceArea.setColumns(60);
      this.fStatusLine = new TextField();
      this.fStatusLine.setFont(PLAIN_FONT);
      this.fStatusLine.setEditable(false);
      this.fStatusLine.setForeground(Color.red);
      this.field_0009 = new Button("Exit");
      this.field_0009.addActionListener(new UnidentifiedClass4000(this));
      this.fLogo = new Logo();
      Panel var11 = new Panel(new GridBagLayout());
      this.addGrid(var11, var6, 0, 0, 2, 2, 1.0, 17);
      this.addGrid(var11, this.fSuiteField, 0, 1, 2, 2, 1.0, 17);
      this.addGrid(var11, this.fRun, 2, 1, 1, 2, 0.0, 10);
      this.addGrid(var11, this.fUseLoadingRunner, 0, 2, 2, 0, 1.0, 17);
      this.addGrid(var11, this.fProgressIndicator, 0, 3, 2, 2, 1.0, 17);
      this.addGrid(var11, this.fLogo, 2, 3, 1, 0, 0.0, 11);
      this.addGrid(var11, var8, 0, 4, 2, 0, 0.0, 17);
      this.addGrid(var11, var9, 0, 5, 2, 2, 1.0, 17);
      this.addGrid(var11, this.fFailureList, 0, 6, 2, 1, 1.0, 17);
      this.addGrid(var11, var10, 2, 6, 1, 2, 0.0, 10);
      this.addGrid(var11, this.fTraceArea, 0, 7, 2, 1, 1.0, 17);
      this.addGrid(var11, this.fStatusLine, 0, 8, 2, 2, 1.0, 10);
      this.addGrid(var11, this.field_0009, 2, 8, 1, 2, 0.0, 10);
      var2.add(var11, "Center");
      var2.pack();
      return var2;
   }

   public void appendFailure(String var1, Test var2, Throwable var3) {
      var1 = var1 + ": " + var2;
      String var4 = var3.getMessage();
      if (var4 != null) {
         var1 = var1 + ":" + method_29475(var4);
      }

      this.fFailureList.add(var1);
      this.fExceptions.addElement(var3);
      this.fFailedTests.addElement(var2);
      if (this.fFailureList.getItemCount() == 1) {
         this.fFailureList.select(0);
         this.method_28042();
      }
   }

   public void rerun() {
      int var1 = this.fFailureList.getSelectedIndex();
      if (var1 != -1) {
         Test var2 = (Test)this.fFailedTests.elementAt(var1);
         this.rerunTest(var2);
      }
   }
}
