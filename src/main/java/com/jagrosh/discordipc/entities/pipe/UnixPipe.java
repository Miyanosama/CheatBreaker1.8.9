package com.jagrosh.discordipc.entities.pipe;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.entities.Callback;
import com.jagrosh.discordipc.entities.Packet;
import com.jagrosh.discordipc.entities.Packet$OpCode;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import org.json.JSONObject;
import org.newsclub.net.unix.AFUNIXSocket;
import org.newsclub.net.unix.AFUNIXSocketAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnixPipe extends Pipe {
   public AFUNIXSocket recoveredField3745 = AFUNIXSocket.method_25943();
   public static Logger recoveredField3746 = LoggerFactory.getLogger(UnixPipe.class);

   @Override
   public Packet method_13416() throws java.io.IOException {
      InputStream var1 = this.recoveredField3745.getInputStream();

      while (var1.available() == 0 && this.recoveredField3656 == PipeStatus.CONNECTED) {
         try {
            Thread.sleep(50L);
         } catch (InterruptedException var6) {
         }
      }

      if (this.recoveredField3656 == PipeStatus.DISCONNECTED) {
         throw new IOException("Disconnected!");
      } else if (this.recoveredField3656 == PipeStatus.CLOSED) {
         return new Packet(Packet$OpCode.CLOSE, null);
      } else {
         byte[] var2 = new byte[8];
         var1.read(var2);
         ByteBuffer var3 = ByteBuffer.wrap(var2);
         Packet$OpCode var4 = Packet$OpCode.values()[Integer.reverseBytes(var3.getInt())];
         var2 = new byte[Integer.reverseBytes(var3.getInt())];
         var1.read(var2);
         Packet var5 = new Packet(var4, new JSONObject(new String(var2)));
         recoveredField3746.method_02650(String.format("Received packet: %s", var5.toString()));
         if (this.recoveredField3655 != null) {
            this.recoveredField3655.method_13335(this.recoveredField3657, var5);
         }

         return var5;
      }
   }

   @Override
   public void method_13414(byte[] var1) throws java.io.IOException {
      this.recoveredField3745.getOutputStream().write(var1);
   }

   public UnixPipe(IPCClient var1, HashMap<String, Callback> var2, String var3) throws java.io.IOException {
      super(var1, var2);
      this.recoveredField3745.connect(new AFUNIXSocketAddress(new File(var3)));
   }

   @Override
   public void method_13405() throws java.io.IOException {
      recoveredField3746.method_02650("Closing IPC pipe...");
      this.method_13412(Packet$OpCode.CLOSE, new JSONObject(), null);
      this.recoveredField3656 = PipeStatus.CLOSED;
      this.recoveredField3745.close();
   }
}
