package org.apache.log4j;

import java.io.OutputStream;
import net.minecraft.client.gui.GuiScreenCustomizePresets$Info;

public class ConsoleAppender$SystemErrStream extends OutputStream {
   public GuiScreenCustomizePresets$Info field_0000;

   public void write(int var1) {
      System.err.write(var1);
   }

   public void write(byte[] var1) {
      System.err.write(var1);
   }

   public void close() {
   }

   public void flush() {
      System.err.flush();
   }

   public void write(byte[] var1, int var2, int var3) {
      System.err.write(var1, var2, var3);
   }
}
