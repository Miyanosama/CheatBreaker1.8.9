package com.jagrosh.discordipc.entities.pipe;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.Callback;
import com.jagrosh.discordipc.entities.DiscordBuild;
import com.jagrosh.discordipc.entities.Packet;
import com.jagrosh.discordipc.entities.Packet$OpCode;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Pipe {
   public static final int recoveredField3652 = 1;
   public static Logger recoveredField3651 = LoggerFactory.getLogger(Pipe.class);
   public HashMap<String, Callback> recoveredField3653;
   public DiscordBuild recoveredField3654;
   public IPCListener recoveredField3655;
   public PipeStatus recoveredField3656 = PipeStatus.CONNECTING;
   public IPCClient recoveredField3657;
   public static String[] recoveredField3658 = new String[]{"XDG_RUNTIME_DIR", "TMPDIR", "TMP", "TEMP"};

   public PipeStatus method_13407() {
      return this.recoveredField3656;
   }

   public abstract Packet method_13416() throws IOException;

   public static Pipe method_13410(IPCClient var0, HashMap<String, Callback> var1, String var2) throws java.io.IOException {
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
            this.recoveredField3653.put(var4, var3);
         }

         this.method_13414(var5.method_13360());
         recoveredField3651.method_02650(String.format("Sent packet: %s", var5.toString()));
         if (this.recoveredField3655 != null) {
            this.recoveredField3655.method_13339(this.recoveredField3657, var5);
         }
      } catch (IOException var6) {
         recoveredField3651.error("Encountered an IOException while sending a packet and disconnected!");
         this.recoveredField3656 = PipeStatus.DISCONNECTED;
      }
   }

   public void method_13413(PipeStatus var1) {
      this.recoveredField3656 = var1;
   }

   public static Pipe method_13409(IPCClient var0, long var1, HashMap<String, Callback> var3, DiscordBuild... var4) throws com.jagrosh.discordipc.exceptions.NoDiscordClientException {
      if (var4 == null || var4.length == 0) {
         var4 = new DiscordBuild[]{DiscordBuild.ANY};
      }

      Pipe var5 = null;
      Pipe[] var6 = new Pipe[DiscordBuild.values().length];

      for (int var7 = 0; var7 < 10; var7++) {
         try {
            String var8 = method_13408(var7);
            recoveredField3651.method_02650(String.format("Searching for IPC: %s", var8));
            var5 = method_13410(var0, var3, var8);
            var5.method_13412(Packet$OpCode.HANDSHAKE, new JSONObject().method_07215("v", 1).put("client_id", Long.toString(var1)), null);
            Packet var9 = var5.method_13416();
            var5.recoveredField3654 = DiscordBuild.method_13354(var9.method_13358().getJSONObject("data").getJSONObject("config").getString("api_endpoint"));
            recoveredField3651.method_02650(String.format("Found a valid client (%s) with packet: %s", var5.recoveredField3654.name(), var9.toString()));
            if (var5.recoveredField3654 == var4[0] || DiscordBuild.ANY == var4[0]) {
               recoveredField3651.info(String.format("Found preferred client: %s", var5.recoveredField3654.name()));
               break;
            }

            var6[var5.recoveredField3654.ordinal()] = var5;
            var6[DiscordBuild.ANY.ordinal()] = var5;
            var5.recoveredField3654 = null;
            var5 = null;
         } catch (JSONException | IOException var11) {
            var5 = null;
         }
      }

      if (var5 == null) {
         for (int var12 = 1; var12 < var4.length; var12++) {
            DiscordBuild var14 = var4[var12];
            recoveredField3651.method_02650(String.format("Looking for client build: %s", var14.name()));
            if (var6[var14.ordinal()] != null) {
               var5 = var6[var14.ordinal()];
               var6[var14.ordinal()] = null;
               if (var14 == DiscordBuild.ANY) {
                  for (int var15 = 0; var15 < var6.length; var15++) {
                     if (var6[var15] == var5) {
                        var5.recoveredField3654 = DiscordBuild.values()[var15];
                        var6[var15] = null;
                     }
                  }
               } else {
                  var5.recoveredField3654 = var14;
               }

               recoveredField3651.info(String.format("Found preferred client: %s", var5.recoveredField3654.name()));
               break;
            }
         }

         if (var5 == null) {
            throw new NoDiscordClientException();
         }
      }

      for (int var13 = 0; var13 < var6.length; var13++) {
         if (var13 != DiscordBuild.ANY.ordinal() && var6[var13] != null) {
            try {
               var6[var13].method_13405();
            } catch (IOException var10) {
               recoveredField3651.debug("Failed to close an open IPC pipe!", (Throwable)var10);
            }
         }
      }

      var5.recoveredField3656 = PipeStatus.CONNECTED;
      return var5;
   }

   public Pipe(IPCClient var1, HashMap<String, Callback> var2) {
      this.recoveredField3657 = var1;
      this.recoveredField3653 = var2;
   }

   public abstract void method_13414(byte[] var1) throws IOException;

   public static String method_13406() {
      return UUID.randomUUID().toString();
   }

   public static String method_13408(int var0) {
      if (System.getProperty("os.name").contains("Win")) {
         return "\\\\?\\pipe\\discord-ipc-" + var0;
      } else {
         String var1 = null;

         for (String var5 : recoveredField3658) {
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
      this.recoveredField3655 = var1;
   }

   public DiscordBuild method_13415() {
      return this.recoveredField3654;
   }

   public abstract void method_13405() throws IOException;
}
