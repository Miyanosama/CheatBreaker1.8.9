package com.jagrosh.discordipc.entities.pipe;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.entities.Callback;
import com.jagrosh.discordipc.entities.Packet;
import com.jagrosh.discordipc.entities.Packet$OpCode;
import io.netty.util.internal.chmv8.ForkJoinPool;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.stats.StatBase;
import net.minecraft.world.biome.BiomeGenSavanna;
import org.apache.log4j.lf5.viewer.LogFactor5ErrorDialog$1;
import org.json.JSONObject;
import org.newsclub.net.unix.AFUNIXSocket;
import org.newsclub.net.unix.AFUNIXSocketAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.SubstituteLogger;

public class UnixPipe extends Pipe {
   public AFUNIXSocket field_0004 = AFUNIXSocket.method_25943();
   public StatBase field_0006;
   public GuiBeacon field_0008;
   public SubstituteLogger field_0002;
   public static Logger field_0003 = LoggerFactory.getLogger(UnixPipe.class);
   public LogFactor5ErrorDialog$1 field_0005;
   public BiomeGenSavanna field_0007;
   public ForkJoinPool field_0001;
   public StateMap field_0000;

   @Override
   public Packet method_13416() {
      InputStream var1 = this.field_0004.getInputStream();

      while (var1.available() == 0 && this.field_0012 == PipeStatus.field_0000) {
         try {
            Thread.sleep(-8009449664640155466L & 540021370L);
         } catch (InterruptedException var6) {
         }
      }

      if (this.field_0012 == PipeStatus.field_0005) {
         throw new IOException("Disconnected!");
      } else if (this.field_0012 == PipeStatus.field_0002) {
         return new Packet(Packet$OpCode.field_0003, null);
      } else {
         byte[] var2 = new byte[8];
         var1.read(var2);
         ByteBuffer var3 = ByteBuffer.wrap(var2);
         Packet$OpCode var4 = Packet$OpCode.values()[Integer.reverseBytes(var3.getInt())];
         var2 = new byte[Integer.reverseBytes(var3.getInt())];
         var1.read(var2);
         Packet var5 = new Packet(var4, new JSONObject(new String(var2)));
         field_0003.method_02650(String.format("Received packet: %s", var5.toString()));
         if (this.field_0003 != null) {
            this.field_0003.method_13335(this.field_0000, var5);
         }

         return var5;
      }
   }

   @Override
   public void method_13414(byte[] var1) {
      this.field_0004.getOutputStream().write(var1);
   }

   public UnixPipe(IPCClient var1, HashMap<String, Callback> var2, String var3) {
      super(var1, var2);
      this.field_0004.connect(new AFUNIXSocketAddress(new File(var3)));
   }

   @Override
   public void method_13405() {
      field_0003.method_02650("Closing IPC pipe...");
      this.method_13412(Packet$OpCode.field_0003, new JSONObject(), null);
      this.field_0012 = PipeStatus.field_0002;
      this.field_0004.close();
   }
}
