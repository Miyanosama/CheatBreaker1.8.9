package org.newsclub.net.unix;

import java.io.IOException;
import java.io.OutputStream;
import net.minecraft.item.ItemPiston;
import org.apache.log4j.EnhancedPatternLayout;
import recovered.unidentified.UnidentifiedClass1587;

public class AFUNIXSocketImpl$AFUNIXOutputStream extends OutputStream {
   public EnhancedPatternLayout field_0001;
   public ItemPiston field_0003;
   public boolean streamClosed;

   @Override
   public void close() {
      if (!this.streamClosed) {
         this.streamClosed = true;
         if (AFUNIXSocketImpl.method_06120(this.this$0).valid()) {
            NativeUnixSocket.method_25795(AFUNIXSocketImpl.method_06135(this.this$0), 1);
         }

         AFUNIXSocketImpl.method_06136(this.this$0, true);
         AFUNIXSocketImpl.access$600(this.this$0);
      }
   }

   @Override
   public void write(byte[] var1, int var2, int var3) {
      if (this.streamClosed) {
         throw new UnidentifiedClass1587("This OutputStream has already been closed.");
      } else if (var3 > var1.length - var2) {
         throw new IndexOutOfBoundsException();
      } else {
         try {
            while (var3 > 0 && !Thread.interrupted()) {
               int var4 = NativeUnixSocket.method_25808(AFUNIXSocketImpl.method_06121(this.this$0), var1, var2, var3);
               if (var4 == -1) {
                  throw new IOException("Unspecific error while writing");
               }

               var3 -= var4;
               var2 += var4;
            }
         } catch (IOException var5) {
            throw (IOException)new IOException(var5.getMessage() + " at " + this.this$0.toString()).initCause(var5);
         }
      }
   }

   public AFUNIXSocketImpl$AFUNIXOutputStream(AFUNIXSocketImpl var1) {
      this.this$0 = var1;
      super();
      this.streamClosed = false;
   }

   @Override
   public void write(int var1) {
      byte[] var2 = new byte[]{(byte)var1};
      this.write(var2, 0, 1);
   }
}
