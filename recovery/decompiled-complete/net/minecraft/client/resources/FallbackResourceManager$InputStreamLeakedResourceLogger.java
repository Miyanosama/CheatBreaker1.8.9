package net.minecraft.client.resources;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import net.minecraft.util.ResourceLocation;

public class FallbackResourceManager$InputStreamLeakedResourceLogger extends InputStream {
   public boolean isClosed = false;
   public InputStream inputStream;
   public String message;

   @Override
   public int read() {
      return this.inputStream.read();
   }

   public FallbackResourceManager$InputStreamLeakedResourceLogger(InputStream var1, ResourceLocation var2, String var3) {
      this.inputStream = var1;
      ByteArrayOutputStream var4 = new ByteArrayOutputStream();
      new Exception().printStackTrace(new PrintStream(var4));
      this.message = "Leaked resource: '" + var2 + "' loaded from pack: '" + var3 + "'\n" + var4.toString();
   }

   @Override
   public void finalize() {
      if (!this.isClosed) {
         FallbackResourceManager.access$000().warn(this.message);
      }

      super.finalize();
   }

   @Override
   public void close() {
      this.inputStream.close();
      this.isClosed = true;
   }
}
