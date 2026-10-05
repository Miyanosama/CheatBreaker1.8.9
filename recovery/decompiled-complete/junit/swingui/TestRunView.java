package junit.swingui;

import javax.swing.JTabbedPane;
import junit.framework.Test;
import junit.framework.TestResult;

public interface TestRunView {
   Test getSelectedTest();

   void runFinished(Test var1, TestResult var2);

   void addTab(JTabbedPane var1);

   void activate();

   void revealFailure(Test var1);

   void aboutToStart(Test var1, TestResult var2);
}
