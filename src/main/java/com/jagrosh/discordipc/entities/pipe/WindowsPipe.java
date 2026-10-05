package com.jagrosh.discordipc.entities.pipe;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.entities.Callback;
import com.jagrosh.discordipc.entities.Packet;
import com.jagrosh.discordipc.entities.Packet$OpCode;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WindowsPipe extends Pipe {
   public static Logger recoveredField1774 = LoggerFactory.getLogger(WindowsPipe.class);
   public RandomAccessFile recoveredField1775;

   @Override
   public void method_13405() throws java.io.IOException {
      recoveredField1774.method_02650("Closing IPC pipe...");
      this.method_13412(Packet$OpCode.CLOSE, new JSONObject(), null);
      this.recoveredField3656 = PipeStatus.CLOSED;
      this.recoveredField1775.close();
   }

   @Override
   public Packet method_13416() throws java.io.IOException {
      while (this.recoveredField1775.length() == 0L && this.recoveredField3656 == PipeStatus.CONNECTED) {
         try {
            Thread.sleep(50L);
         } catch (InterruptedException var5) {
         }
      }

      if (this.recoveredField3656 == PipeStatus.DISCONNECTED) {
         throw new IOException("Disconnected!");
      } else if (this.recoveredField3656 == PipeStatus.CLOSED) {
         return new Packet(Packet$OpCode.CLOSE, null);
      } else {
         Packet$OpCode var1 = Packet$OpCode.values()[Integer.reverseBytes(this.recoveredField1775.readInt())];
         int var2 = Integer.reverseBytes(this.recoveredField1775.readInt());
         byte[] var3 = new byte[var2];
         this.recoveredField1775.readFully(var3);
         Packet var4 = new Packet(var1, new JSONObject(new String(var3)));
         recoveredField1774.method_02650(String.format("Received packet: %s", var4.toString()));
         if (this.recoveredField3655 != null) {
            this.recoveredField3655.method_13335(this.recoveredField3657, var4);
         }

         return var4;
      }
   }

   @Override
   public void method_13414(byte[] var1) throws java.io.IOException {
      this.recoveredField1775.write(var1);
   }

   public WindowsPipe(IPCClient var1, HashMap<String, Callback> var2, String var3) throws java.io.IOException {
      super(var1, var2);

      try {
         this.recoveredField1775 = new RandomAccessFile(var3, "rw");
      } catch (FileNotFoundException var5) {
         throw new RuntimeException(var5);
      }
   }
}
