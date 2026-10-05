package recovered.unidentified;

import java.io.IOException;
import java.io.InputStream;
import net.minecraft.block.BlockSkull$1;
import net.minecraft.item.ItemBlock;
import org.newsclub.net.unix.AFUNIXSocketImpl;
import org.newsclub.net.unix.NativeUnixSocket;
import org.slf4j.helpers.NamedLoggerBase;

public class UnidentifiedClass1561 extends InputStream {
   public NamedLoggerBase field_0002;
   public BlockSkull$1 field_0001;
   public ItemBlock field_0003;
   public boolean field_0000;

   @Override
   public int available() {
      return NativeUnixSocket.method_25794(AFUNIXSocketImpl.method_06146(this.field_0004));
   }

   @Override
   public int read() {
      byte[] var1 = new byte[1];
      int var2 = this.read(var1, 0, 1);
      return var2 <= 0 ? -1 : var1[0] & 0xFF;
   }

   @Override
   public int read(byte[] var1, int var2, int var3) {
      if (this.field_0000) {
         throw new IOException("This InputStream has already been closed.");
      } else if (var3 == 0) {
         return 0;
      } else {
         int var4 = var1.length - var2;
         if (var3 > var4) {
            var3 = var4;
         }

         try {
            return NativeUnixSocket.method_25797(AFUNIXSocketImpl.method_06139(this.field_0004), var1, var2, var3);
         } catch (IOException var6) {
            throw (IOException)new IOException(var6.getMessage() + " at " + this.field_0004.toString()).initCause(var6);
         }
      }
   }

   @Override
   public void close() {
      if (!this.field_0000) {
         this.field_0000 = true;
         if (AFUNIXSocketImpl.method_06143(this.field_0004).valid()) {
            NativeUnixSocket.method_25795(AFUNIXSocketImpl.method_06122(this.field_0004), 0);
         }

         AFUNIXSocketImpl.method_06144(this.field_0004, true);
         AFUNIXSocketImpl.access$600(this.field_0004);
      }
   }

   public UnidentifiedClass1561(AFUNIXSocketImpl var1) {
      this.field_0004 = var1;
      super();
      this.field_0000 = false;
   }
}
