package org.newsclub.net.unix;

import java.io.IOException;
import java.io.InputStream;
import org.newsclub.net.unix.AFUNIXSocketImpl;
import org.newsclub.net.unix.NativeUnixSocket;

public class AFUNIXSocketImpl$AFUNIXInputStream extends InputStream {
   public AFUNIXSocketImpl recoveredField3511;
   public boolean recoveredField3512;

   @Override
   public int available() throws IOException {
      return NativeUnixSocket.available(AFUNIXSocketImpl.method_06146(this.recoveredField3511));
   }

   @Override
   public int read() throws IOException {
      byte[] var1 = new byte[1];
      int var2 = this.read(var1, 0, 1);
      return var2 <= 0 ? -1 : var1[0] & 0xFF;
   }

   @Override
   public int read(byte[] var1, int var2, int var3) throws java.io.IOException {
      if (this.recoveredField3512) {
         throw new IOException("This InputStream has already been closed.");
      } else if (var3 == 0) {
         return 0;
      } else {
         int var4 = var1.length - var2;
         if (var3 > var4) {
            var3 = var4;
         }

         try {
            return NativeUnixSocket.read(AFUNIXSocketImpl.method_06139(this.recoveredField3511), var1, var2, var3);
         } catch (IOException var6) {
            throw (IOException)new IOException(var6.getMessage() + " at " + this.recoveredField3511.toString()).initCause(var6);
         }
      }
   }

   @Override
   public void close() throws java.io.IOException {
      if (!this.recoveredField3512) {
         this.recoveredField3512 = true;
         if (AFUNIXSocketImpl.method_06143(this.recoveredField3511).valid()) {
            NativeUnixSocket.shutdown(AFUNIXSocketImpl.method_06122(this.recoveredField3511), 0);
         }

         AFUNIXSocketImpl.method_06144(this.recoveredField3511, true);
         this.recoveredField3511.checkClose();
      }
   }

   public AFUNIXSocketImpl$AFUNIXInputStream(AFUNIXSocketImpl var1) {
      this.recoveredField3511 = var1;
      this.recoveredField3512 = false;
   }
}
