package junit.swingui;

import javax.swing.ListModel;
import junit.framework.Test;

public interface TestRunContext {
   ListModel getFailures();

   void handleTestSelected(Test var1);
}
