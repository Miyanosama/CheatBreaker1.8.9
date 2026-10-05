package junit.runner;

import java.awt.Component;
import junit.framework.TestFailure;

public interface FailureDetailView {
   Component getComponent();

   void clear();

   void showFailure(TestFailure var1);
}
