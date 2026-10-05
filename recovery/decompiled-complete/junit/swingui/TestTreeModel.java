package junit.swingui;

import io.netty.channel.local.LocalChannel$4;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import junit.extensions.TestDecorator;
import junit.framework.Test;
import junit.framework.TestSuite;
import net.minecraft.inventory.ContainerRepair$1;
import net.optifine.shaders.config.MacroProcessor;
import net.optifine.shaders.gui.GuiButtonDownloadShaders;
import org.apache.log4j.lf5.util.ResourceUtils;

public class TestTreeModel implements TreeModel {
   public MacroProcessor field_0004;
   public ContainerRepair$1 field_0007;
   public ResourceUtils field_0003;
   public Test fRoot;
   public Hashtable fFailures;
   public Vector fModelListeners = new Vector();
   public Hashtable fRunTests;
   public LocalChannel$4 field_0005;
   public GuiButtonDownloadShaders field_0002;
   public Hashtable fErrors;

   public void addFailure(Test var1) {
      this.fFailures.put(var1, var1);
   }

   public Object getRoot() {
      return this.fRoot;
   }

   public boolean isLeaf(Object var1) {
      return this.isTestSuite(var1) == null;
   }

   public boolean method_25920(Test var1) {
      return this.fErrors != null && this.fErrors.get(var1) != null;
   }

   public TestTreeModel(Test var1) {
      this.fFailures = new Hashtable();
      this.fErrors = new Hashtable();
      this.fRunTests = new Hashtable();
      this.fRoot = var1;
   }

   public boolean method_25907(Test var1) {
      return this.fFailures != null && this.fFailures.get(var1) != null;
   }

   public TestSuite isTestSuite(Object var1) {
      if (var1 instanceof TestSuite) {
         return (TestSuite)var1;
      } else if (var1 instanceof TestDecorator) {
         Test var2 = ((TestDecorator)var1).getTest();
         return this.isTestSuite(var2);
      } else {
         return null;
      }
   }

   public void fireNodeChanged(TreePath var1, int var2) {
      int[] var3 = new int[]{var2};
      Object[] var4 = new Object[]{this.getChild(var1.getLastPathComponent(), var2)};
      TreeModelEvent var5 = new TreeModelEvent(this, var1, var3, var4);
      Enumeration var6 = this.fModelListeners.elements();

      while (var6.hasMoreElements()) {
         TreeModelListener var7 = (TreeModelListener)var6.nextElement();
         var7.treeNodesChanged(var5);
      }
   }

   public boolean method_25919(Test var1) {
      return this.fRunTests.get(var1) != null;
   }

   public void valueForPathChanged(TreePath var1, Object var2) {
      System.out.println("TreeModel.valueForPathChanged: not implemented");
   }

   public void addError(Test var1) {
      this.fErrors.put(var1, var1);
   }

   public void resetResults() {
      this.fFailures = new Hashtable();
      this.fRunTests = new Hashtable();
      this.fErrors = new Hashtable();
   }

   public int getIndexOfChild(Object var1, Object var2) {
      TestSuite var3 = this.isTestSuite(var1);
      if (var3 != null) {
         int var4 = 0;

         for (Enumeration var5 = var3.tests(); var5.hasMoreElements(); var4++) {
            if (var2.equals(var5.nextElement())) {
               return var4;
            }
         }
      }

      return -1;
   }

   public int findTest(Test var1, Test var2, Vector var3) {
      if (var1.equals(var2)) {
         return 0;
      } else {
         TestSuite var4 = this.isTestSuite(var2);

         for (int var5 = 0; var5 < this.getChildCount(var2); var5++) {
            Test var6 = var4.testAt(var5);
            int var7 = this.findTest(var1, var6, var3);
            if (var7 >= 0) {
               var3.insertElementAt(var2, 0);
               if (var3.size() == 1) {
                  return var5;
               }

               return var7;
            }
         }

         return -1;
      }
   }

   public void addTreeModelListener(TreeModelListener var1) {
      if (!this.fModelListeners.contains(var1)) {
         this.fModelListeners.addElement(var1);
      }
   }

   public void addRunTest(Test var1) {
      this.fRunTests.put(var1, var1);
   }

   public int getChildCount(Object var1) {
      TestSuite var2 = this.isTestSuite(var1);
      return var2 != null ? var2.testCount() : 0;
   }

   public Object getChild(Object var1, int var2) {
      TestSuite var3 = this.isTestSuite(var1);
      return var3 != null ? var3.testAt(var2) : null;
   }

   public void removeTreeModelListener(TreeModelListener var1) {
      this.fModelListeners.removeElement(var1);
   }
}
