package recovered.unidentified;

import junit.awtui.TestRunner;
import junit.framework.Test;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.renderer.GlStateManager;
import org.apache.log4j.or.DefaultRenderer;

public class UnidentifiedClass0275 extends Thread {
   public TestRunner field_0002;
   public Test field_0004;
   public GlStateManager field_0001;
   public DefaultRenderer field_0003;
   public GuiLabel field_0000;

   public void run() {
      this.field_0002.fTestResult = this.field_0002.createTestResult();
      this.field_0002.fTestResult.addListener(this.field_0002);
      this.field_0002.fProgressIndicator.start(this.field_0004.j_());
      TestRunner.method_28038(this.field_0002, "Running...");
      long var1 = System.currentTimeMillis();
      this.field_0004.run(this.field_0002.fTestResult);
      if (this.field_0002.fTestResult.shouldStop()) {
         TestRunner.method_28030(this.field_0002, "Stopped");
      } else {
         long var3 = System.currentTimeMillis();
         long var5 = var3 - var1;
         TestRunner.method_28038(this.field_0002, "Finished: " + this.field_0002.elapsedTimeAsString(var5) + " seconds");
      }

      this.field_0002.fTestResult = null;
      this.field_0002.fRun.setLabel("Run");
      this.field_0002.fRunner = null;
      System.gc();
   }

   public UnidentifiedClass0275(TestRunner var1, Test var2) {
      this.field_0002 = var1;
      this.field_0004 = var2;
   }
}
