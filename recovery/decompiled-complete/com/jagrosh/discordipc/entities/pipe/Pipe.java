package com.jagrosh.discordipc.entities.pipe;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.Callback;
import com.jagrosh.discordipc.entities.DiscordBuild;
import com.jagrosh.discordipc.entities.Packet;
import com.jagrosh.discordipc.entities.Packet$OpCode;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;
import io.netty.channel.PendingWriteQueue$PendingWrite;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import net.minecraft.client.gui.spectator.categories.TeleportToPlayer;
import org.java_websocket.client.WebSocketClient$WebsocketWriteThread;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recovered.unidentified.UnidentifiedClass0882;

public abstract class Pipe {
   public WebSocketClient$WebsocketWriteThread field_0005;
   public static Logger field_0010 = LoggerFactory.getLogger(Pipe.class);
   public static int field_0004;
   public InvalidHandshakeException field_0009;
   public UnidentifiedClass0882 field_0001;
   public HashMap<String, Callback> field_0002;
   public TeleportToPlayer field_0011;
   public DiscordBuild field_0008;
   public IPCListener field_0003;
   public PipeStatus field_0012 = PipeStatus.field_0001;
   public IPCClient field_0000;
   public PendingWriteQueue$PendingWrite field_0006;
   public static String[] field_0007 = new String[]{"XDG_RUNTIME_DIR", "TMPDIR", "TMP", "TEMP"};

   public PipeStatus method_13407() {
      return this.field_0012;
   }

   public abstract Packet method_13416();

   public static Pipe method_13410(IPCClient var0, HashMap<String, Callback> var1, String var2) {
      String var3 = System.getProperty("os.name").toLowerCase();
      if (var3.contains("win")) {
         return new WindowsPipe(var0, var1, var2);
      } else if (!var3.contains("linux") && !var3.contains("mac")) {
         throw new RuntimeException("Unsupported OS: " + var3);
      } else {
         try {
            return new UnixPipe(var0, var1, var2);
         } catch (IOException var5) {
            throw new RuntimeException(var5);
         }
      }
   }

   public void method_13412(Packet$OpCode var1, JSONObject var2, Callback var3) {
      try {
         String var4 = method_13406();
         Packet var5 = new Packet(var1, var2.put("nonce", var4));
         if (var3 != null && !var3.method_13346()) {
            this.field_0002.put(var4, var3);
         }

         this.method_13414(var5.method_13360());
         field_0010.method_02650(String.format("Sent packet: %s", var5.toString()));
         if (this.field_0003 != null) {
            this.field_0003.method_13339(this.field_0000, var5);
         }
      } catch (IOException var6) {
         field_0010.error("Encountered an IOException while sending a packet and disconnected!");
         this.field_0012 = PipeStatus.field_0005;
      }
   }

   public void method_13413(PipeStatus var1) {
      this.field_0012 = var1;
   }

   public static Pipe method_13409(IPCClient var0, long var1, HashMap<String, Callback> var3, DiscordBuild... var4) {
      if (var4 == null || var4.length == 0) {
         var4 = new DiscordBuild[]{DiscordBuild.field_0006};
      }

      Pipe var5 = null;
      Pipe[] var6 = new Pipe[DiscordBuild.values().length];

      for (int var7 = 0; var7 < 10; var7++) {
         try {
            String var8 = method_13408(var7);
            field_0010.method_02650(String.format("Searching for IPC: %s", var8));
            var5 = method_13410(var0, var3, var8);
            var5.method_13412(Packet$OpCode.field_0002, new JSONObject().method_07215("v", 1).put("client_id", Long.toString(var1)), null);
            Packet var9 = var5.method_13416();
            var5.field_0008 = DiscordBuild.method_13354(var9.method_13358().method_07181("data").method_07181("config").getString("api_endpoint"));
            field_0010.method_02650(String.format("Found a valid client (%s) with packet: %s", var5.field_0008.name(), var9.toString()));
            if (var5.field_0008 == var4[0] || DiscordBuild.field_0006 == var4[0]) {
               field_0010.info(String.format("Found preferred client: %s", var5.field_0008.name()));
               break;
            }

            var6[var5.field_0008.ordinal()] = var5;
            var6[DiscordBuild.field_0006.ordinal()] = var5;
            var5.field_0008 = null;
            var5 = null;
         } catch (JSONException | IOException var11) {
            var5 = null;
         }
      }

      if (var5 == null) {
         for (int var12 = 1; var12 < var4.length; var12++) {
            DiscordBuild var14 = var4[var12];
            field_0010.method_02650(String.format("Looking for client build: %s", var14.name()));
            if (var6[var14.ordinal()] != null) {
               var5 = var6[var14.ordinal()];
               var6[var14.ordinal()] = null;
               if (var14 == DiscordBuild.field_0006) {
                  for (int var15 = 0; var15 < var6.length; var15++) {
                     if (var6[var15] == var5) {
                        var5.field_0008 = DiscordBuild.values()[var15];
                        var6[var15] = null;
                     }
                  }
               } else {
                  var5.field_0008 = var14;
               }

               field_0010.info(String.format("Found preferred client: %s", var5.field_0008.name()));
               break;
            }
         }

         if (var5 == null) {
            throw new NoDiscordClientException();
         }
      }

      for (int var13 = 0; var13 < var6.length; var13++) {
         if (var13 != DiscordBuild.field_0006.ordinal() && var6[var13] != null) {
            try {
               var6[var13].method_13405();
            } catch (IOException var10) {
               field_0010.debug("Failed to close an open IPC pipe!", (Throwable)var10);
            }
         }
      }

      var5.field_0012 = PipeStatus.field_0000;
      return var5;
   }

   public Pipe(IPCClient var1, HashMap<String, Callback> var2) {
      this.field_0000 = var1;
      this.field_0002 = var2;
   }

   public abstract void method_13414(byte[] var1);

   public static String method_13406() {
      return UUID.randomUUID().toString();
   }

   public static String method_13408(int var0) {
      if (System.getProperty("os.name").contains("Win")) {
         return "\\\\?\\pipe\\discord-ipc-" + var0;
      } else {
         String var1 = null;

         for (String var5 : field_0007) {
            var1 = System.getenv(var5);
            if (var1 != null) {
               break;
            }
         }

         if (var1 == null) {
            var1 = "/tmp";
         }

         return var1 + "/discord-ipc-" + var0;
      }
   }

   public void method_13411(IPCListener var1) {
      this.field_0003 = var1;
   }

   public DiscordBuild method_13415() {
      return this.field_0008;
   }

   public abstract void method_13405();
}
