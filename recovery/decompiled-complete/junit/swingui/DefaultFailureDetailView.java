package junit.swingui;

import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JList;
import junit.framework.TestFailure;
import junit.runner.BaseTestRunner;
import junit.runner.FailureDetailView;
import net.minecraft.client.particle.EntityNoteFX$Factory;
import recovered.unidentified.UnidentifiedClass0499;

public class DefaultFailureDetailView implements FailureDetailView {
   public HttpPostRequestEncoder field_0001;
   public EntityNoteFX$Factory field_0002;
   public JList fList;

   public void showFailure(TestFailure var1) {
      this.getModel().setTrace(BaseTestRunner.getFilteredTrace(var1.trace()));
   }

   public void clear() {
      this.getModel().clear();
   }

   public Component getComponent() {
      if (this.fList == null) {
         this.fList = new JList(new DefaultFailureDetailView$StackTraceListModel());
         this.fList.setFont(new Font("Dialog", 0, 12));
         this.fList.setSelectionMode(0);
         this.fList.setVisibleRowCount(5);
         this.fList.setCellRenderer(new UnidentifiedClass0499());
      }

      return this.fList;
   }

   public DefaultFailureDetailView$StackTraceListModel getModel() {
      return (DefaultFailureDetailView$StackTraceListModel)this.fList.getModel();
   }
}
