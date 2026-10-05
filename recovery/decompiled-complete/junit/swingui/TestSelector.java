package junit.swingui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import junit.runner.TestCollector;
import net.minecraft.block.BlockPressurePlate;
import net.optifine.shaders.SVertexFormat;
import recovered.unidentified.UnidentifiedClass3609;
import recovered.unidentified.UnidentifiedClass3613;
import recovered.unidentified.UnidentifiedClass3786;
import recovered.unidentified.UnidentifiedClass4851;

public class TestSelector extends JDialog {
   public String fSelectedItem;
   public BlockPressurePlate field_0006;
   public JList fList;
   public JLabel fDescription;
   public JButton field_0000;
   public JButton field_0001;
   public JScrollPane fScrolledList;
   public SVertexFormat field_0004;

   public boolean isEmpty() {
      return this.fList.getModel().getSize() == 0;
   }

   public String getSelectedItem() {
      return this.fSelectedItem;
   }

   public void method_22001() {
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
      this.getContentPane().add(this.field_0000, var3);
      GridBagConstraints var4 = new GridBagConstraints();
      var4.gridx = 3;
      var4.gridy = 2;
      var4.gridwidth = 1;
      var4.gridheight = 1;
      var4.anchor = 13;
      var4.insets = new Insets(0, 8, 8, 8);
      this.getContentPane().add(this.field_0001, var4);
   }

   public void method_22007() {
      this.field_0001.addActionListener(new UnidentifiedClass3613(this));
      this.field_0000.addActionListener(new UnidentifiedClass4851(this));
      this.fList.addMouseListener(new TestSelector$DoubleClickListener(this));
      this.fList.addKeyListener(new TestSelector$KeySelectListener(this));
      this.fList.addListSelectionListener(new TestSelector$3(this));
      this.addWindowListener(new UnidentifiedClass3786(this));
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
      this.fList.setCellRenderer(new TestSelector$TestCellRenderer());
      this.fScrolledList = new JScrollPane(this.fList);
      this.field_0001 = new JButton("Cancel");
      this.fDescription = new JLabel("Select the Test class:");
      this.field_0000 = new JButton("OK");
      this.field_0000.setEnabled(false);
      this.getRootPane().setDefaultButton(this.field_0000);
      this.method_22001();
      this.method_22007();
   }

   public void keySelectTestClass(char var1) {
      ListModel var2 = this.fList.getModel();
      if (Character.isJavaIdentifierStart(var1)) {
         for (int var3 = 0; var3 < var2.getSize(); var3++) {
            String var4 = (String)var2.getElementAt(var3);
            if (TestSelector$TestCellRenderer.matchesKey(var4, Character.toUpperCase(var1))) {
               this.fList.setSelectedIndex(var3);
               this.fList.ensureIndexIsVisible(var3);
               return;
            }
         }

         Toolkit.getDefaultToolkit().beep();
      }
   }

   public void method_22000() {
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
         var4.addElement(TestSelector$TestCellRenderer.displayString(var5));
      }

      if (var3.size() > 0) {
         UnidentifiedClass3609.method_22132(var4, 0, var4.size() - 1, new TestSelector$ParallelSwapper(this, var3));
      }

      return var3;
   }

   public void checkEnableOK(ListSelectionEvent var1) {
      this.field_0000.setEnabled(this.fList.getSelectedIndex() != -1);
   }
}
