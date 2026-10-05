package javazoom.jl.decoder;

import com.cheatbreaker.client.ui.mainmenu.ChangelogMenu;
import com.cheatbreaker.client.ui.overlay.element.FlatButtonElement;
import java.io.PrintStream;
import net.minecraft.world.chunk.storage.NibbleArrayReader;

public class JavaLayerException extends Exception {
   public Throwable exception;
   public NibbleArrayReader __junk8677332581813485636;
   public FlatButtonElement __junk6194006566717818783;
   public ChangelogMenu __junk2498804005324271060;

   public Throwable getException() {
      return this.exception;
   }

   @Override
   public void printStackTrace() {
      this.printStackTrace(System.err);
   }

   public JavaLayerException(String var1) {
      super(var1);
   }

   public JavaLayerException() {
   }

   public JavaLayerException(String var1, Throwable var2) {
      super(var1);
      this.exception = var2;
   }

   @Override
   public void printStackTrace(PrintStream var1) {
      if (this.exception == null) {
         super.printStackTrace(var1);
      } else {
         this.exception.printStackTrace();
      }
   }
}
