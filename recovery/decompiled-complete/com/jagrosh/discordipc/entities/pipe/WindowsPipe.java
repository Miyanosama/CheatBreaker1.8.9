package com.jagrosh.discordipc.entities.pipe;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.entities.Callback;
import com.jagrosh.discordipc.entities.Packet;
import com.jagrosh.discordipc.entities.Packet$OpCode;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$PieceWeight;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WindowsPipe extends Pipe {
   public static Logger field_0000 = LoggerFactory.getLogger(WindowsPipe.class);
   public RandomAccessFile field_0001;
   public StructureNetherBridgePieces$PieceWeight field_0002;

   @Override
   public void method_13405() {
      field_0000.method_02650("Closing IPC pipe...");
      this.method_13412(Packet$OpCode.field_0003, new JSONObject(), null);
      this.field_0012 = PipeStatus.field_0002;
      this.field_0001.close();
   }

   @Override
   public Packet method_13416() {
      while (this.field_0001.length() == (32928L & -8419968865595673068L) && this.field_0012 == PipeStatus.field_0000) {
         try {
            Thread.sleep(4345375975247188027L & -4345375976380626254L);
         } catch (InterruptedException var5) {
         }
      }

      if (this.field_0012 == PipeStatus.field_0005) {
         throw new IOException("Disconnected!");
      } else if (this.field_0012 == PipeStatus.field_0002) {
         return new Packet(Packet$OpCode.field_0003, null);
      } else {
         Packet$OpCode var1 = Packet$OpCode.values()[Integer.reverseBytes(this.field_0001.readInt())];
         int var2 = Integer.reverseBytes(this.field_0001.readInt());
         byte[] var3 = new byte[var2];
         this.field_0001.readFully(var3);
         Packet var4 = new Packet(var1, new JSONObject(new String(var3)));
         field_0000.method_02650(String.format("Received packet: %s", var4.toString()));
         if (this.field_0003 != null) {
            this.field_0003.method_13335(this.field_0000, var4);
         }

         return var4;
      }
   }

   @Override
   public void method_13414(byte[] var1) {
      this.field_0001.write(var1);
   }

   public WindowsPipe(IPCClient var1, HashMap<String, Callback> var2, String var3) {
      super(var1, var2);

      try {
         this.field_0001 = new RandomAccessFile(var3, "rw");
      } catch (FileNotFoundException var5) {
         throw new RuntimeException(var5);
      }
   }
}
