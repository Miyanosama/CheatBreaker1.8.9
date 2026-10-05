package junit.swingui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import junit.runner.Sorter_Swapper;
import junit.runner.TestCollector;
import junit.runner.Sorter;
import junit.swingui.TestSelector$1;
import junit.swingui.TestSelector$4;
import junit.swingui.TestSelector$2;

public class TestSelector extends JDialog {
   public String fSelectedItem;
   public JList fList;
   public JLabel fDescription;
   public JButton recoveredField3308;
   public JButton recoveredField3309;
   public JScrollPane fScrolledList;

   public boolean isEmpty() {
      return this.fList.getModel().getSize() == 0;
   }

   public String getSelectedItem() {
      return this.fSelectedItem;
   }

   public void defineLayout() {
      this.getContentPane().setLayout(new GridBagLayout());
      GridBagConstraints var1 = new GridBagConstraints();
      var1.gridx = 0;
      var1.gridy = 0;
      var1.gridwidth = 1;
      var1.gridheight = 1;
      var1.fill = 1;
      var1.anchor = 17;
      var1.weightx = 1.0;
      var1.weighty = 0.0;
      var1.insets = new Insets(8, 8, 0, 8);
      this.getContentPane().add(this.fDescription, var1);
      GridBagConstraints var2 = new GridBagConstraints();
      var2.gridx = 0;
      var2.gridy = 1;
      var2.gridwidth = 4;
      var2.gridheight = 1;
      var2.fill = 1;
      var2.anchor = 10;
      var2.weightx = 1.0;
      var2.weighty = 1.0;
      var2.insets = new Insets(8, 8, 8, 8);
      this.getContentPane().add(this.fScrolledList, var2);
      GridBagConstraints var3 = new GridBagConstraints();
      var3.gridx = 2;
      var3.gridy = 2;
      var3.gridwidth = 1;
      var3.gridheight = 1;
      var3.anchor = 13;
      var3.insets = new Insets(0, 8, 8, 8);
      this.getContentPane().add(this.recoveredField3308, var3);
      GridBagConstraints var4 = new GridBagConstraints();
      var4.gridx = 3;
      var4.gridy = 2;
      var4.gridwidth = 1;
      var4.gridheight = 1;
      var4.anchor = 13;
      var4.insets = new Insets(0, 8, 8, 8);
      this.getContentPane().add(this.recoveredField3309, var4);
   }

   public void addListeners() {
      this.recoveredField3309.addActionListener(new TestSelector$1(this));
      this.recoveredField3308.addActionListener(new TestSelector$2(this));
      this.fList.addMouseListener(new TestSelector.DoubleClickListener(this));
      this.fList.addKeyListener(new TestSelector.KeySelectListener(this));
      this.fList.addListSelectionListener(new TestSelector$3(this));
      this.addWindowListener(new TestSelector$4(this));
   }

   public TestSelector(Frame var1, TestCollector var2) {
      super(var1, true);
      this.setSize(350, 300);
      this.setResizable(false);

      try {
         this.setLocationRelativeTo(var1);
      } catch (NoSuchMethodError var8) {
         centerWindow(this);
      }

      this.setTitle("Test Selector");
      Object var3 = null;

      try {
         var1.setCursor(Cursor.getPredefinedCursor(3));
         var3 = this.createTestList(var2);
      } finally {
         var1.setCursor(Cursor.getDefaultCursor());
      }

      this.fList = new JList((Vector)var3);
      this.fList.setSelectionMode(0);
      this.fList.setCellRenderer(new TestSelector.TestCellRenderer());
      this.fScrolledList = new JScrollPane(this.fList);
      this.recoveredField3309 = new JButton("Cancel");
      this.fDescription = new JLabel("Select the Test class:");
      this.recoveredField3308 = new JButton("OK");
      this.recoveredField3308.setEnabled(false);
      this.getRootPane().setDefaultButton(this.recoveredField3308);
      this.defineLayout();
      this.addListeners();
   }

   public void keySelectTestClass(char var1) {
      ListModel var2 = this.fList.getModel();
      if (Character.isJavaIdentifierStart(var1)) {
         for (int var3 = 0; var3 < var2.getSize(); var3++) {
            String var4 = (String)var2.getElementAt(var3);
            if (TestSelector.TestCellRenderer.matchesKey(var4, Character.toUpperCase(var1))) {
               this.fList.setSelectedIndex(var3);
               this.fList.ensureIndexIsVisible(var3);
               return;
            }
         }

         Toolkit.getDefaultToolkit().beep();
      }
   }

   public void okSelected() {
      this.fSelectedItem = (String)this.fList.getSelectedValue();
      this.dispose();
   }

   public static void centerWindow(Component var0) {
      Dimension var1 = var0.getSize();
      Dimension var2 = var0.getToolkit().getScreenSize();
      var0.setLocation((var2.width - var1.width) / 2, (var2.height - var1.height) / 2);
   }

   public Vector createTestList(TestCollector var1) {
      Enumeration var2 = var1.collectTests();
      Vector var3 = new Vector(200);
      Vector var4 = new Vector(var3.size());

      while (var2.hasMoreElements()) {
         String var5 = (String)var2.nextElement();
         var3.addElement(var5);
         var4.addElement(TestSelector.TestCellRenderer.displayString(var5));
      }

      if (var3.size() > 0) {
         Sorter.method_22132(var4, 0, var4.size() - 1, new TestSelector.ParallelSwapper(var3));
      }

      return var3;
   }

   public void checkEnableOK(ListSelectionEvent var1) {
      this.recoveredField3308.setEnabled(this.fList.getSelectedIndex() != -1);
   }

   public class DoubleClickListener extends MouseAdapter {
      public TestSelector recoveredField2938;

      public void mouseClicked(MouseEvent var1) {
         if (var1.getClickCount() == 2) {
            this.recoveredField2938.okSelected();
         }
      }

      public DoubleClickListener(TestSelector var1) {
         this.recoveredField2938 = var1;
      }
   }

   public class KeySelectListener extends KeyAdapter {
      public TestSelector recoveredField1612;

      public void keyTyped(KeyEvent var1) {
         this.recoveredField1612.keySelectTestClass(var1.getKeyChar());
      }

      public KeySelectListener(TestSelector var1) {
         this.recoveredField1612 = var1;
      }
   }

   public class ParallelSwapper implements Sorter_Swapper {
      public Vector fOther;

      public void swap(Vector var1, int var2, int var3) {
         Object var4 = var1.elementAt(var2);
         var1.setElementAt(var1.elementAt(var3), var2);
         var1.setElementAt(var4, var3);
         Object var5 = this.fOther.elementAt(var2);
         this.fOther.setElementAt(this.fOther.elementAt(var3), var2);
         this.fOther.setElementAt(var5, var3);
      }

      public ParallelSwapper(Vector var2) {
         this.fOther = var2;
      }
   }

   public static class TestCellRenderer extends DefaultListCellRenderer {
      public Icon fSuiteIcon;
      public Icon fLeafIcon = UIManager.getIcon("Tree.leafIcon");

      public static int typeIndex(String var0) {
         int var1 = var0.lastIndexOf(46);
         int var2 = 0;
         if (var1 > 0) {
            var2 = var1 + 1;
         }

         return var2;
      }

      public static String displayString(String var0) {
         int var1 = var0.lastIndexOf(46);
         return var1 < 0 ? var0 : var0.substring(var1 + 1) + " - " + var0.substring(0, var1);
      }

      public TestCellRenderer() {
         this.fSuiteIcon = UIManager.getIcon("Tree.closedIcon");
      }

      public Component getListCellRendererComponent(JList var1, Object var2, int var3, boolean var4, boolean var5) {
         Component var6 = super.getListCellRendererComponent(var1, var2, var3, var4, var5);
         String var7 = displayString((String)var2);
         if (var7.startsWith("AllTests")) {
            this.setIcon(this.fSuiteIcon);
         } else {
            this.setIcon(this.fLeafIcon);
         }

         this.setText(var7);
         return var6;
      }

      public static boolean matchesKey(String var0, char var1) {
         return var1 == Character.toUpperCase(var0.charAt(typeIndex(var0)));
      }
   }
}
