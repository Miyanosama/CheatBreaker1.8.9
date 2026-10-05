package com.jagrosh.discordipc;

import com.jagrosh.discordipc.entities.Callback;
import com.jagrosh.discordipc.entities.DiscordBuild;
import com.jagrosh.discordipc.entities.Packet;
import com.jagrosh.discordipc.entities.Packet$OpCode;
import com.jagrosh.discordipc.entities.RichPresence;
import com.jagrosh.discordipc.entities.User;
import com.jagrosh.discordipc.entities.pipe.Pipe;
import com.jagrosh.discordipc.entities.pipe.PipeStatus;
import java.io.Closeable;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IPCClient implements Closeable {
   public IPCListener recoveredField3425;
   public Thread recoveredField3426;
   public HashMap<String, Callback> recoveredField3427 = new HashMap<>();
   public volatile Pipe recoveredField3428;
   public long recoveredField3429;
   public static Logger recoveredField3430 = LoggerFactory.getLogger(IPCClient.class);

   public void method_13318(IPCClient$Event var1, Callback var2) {
      this.method_13322(true);
      if (!var1.method_13329()) {
         throw new IllegalStateException("Cannot subscribe to " + var1 + " event!");
      } else {
         recoveredField3430.method_02650(String.format("Subscribing to Event: %s", var1.name()));
         this.recoveredField3428.method_13412(Packet$OpCode.FRAME, new JSONObject().put("cmd", "SUBSCRIBE").put("evt", var1.name()), var2);
      }
   }

   public void method_13320(RichPresence var1) {
      this.method_13321(var1, null);
   }

   public void method_13321(RichPresence var1, Callback var2) {
      this.method_13322(true);
      recoveredField3430.method_02650("Sending RichPresence to discord: " + (var1 == null ? null : var1.method_13367().toString()));
      this.recoveredField3428
         .method_13412(
            Packet$OpCode.FRAME,
            new JSONObject()
               .put("cmd", "SET_ACTIVITY")
               .put("args", new JSONObject().method_07215("pid", method_13324()).put("activity", var1 == null ? null : var1.method_13367())),
            var2
         );
   }

   public void method_13323(DiscordBuild... var1) throws com.jagrosh.discordipc.exceptions.NoDiscordClientException {
      this.method_13322(false);
      this.recoveredField3427.clear();
      this.recoveredField3428 = null;
      this.recoveredField3428 = Pipe.method_13409(this, this.recoveredField3429, this.recoveredField3427, var1);
      recoveredField3430.method_02650("Client is now connected and ready!");
      if (this.recoveredField3425 != null) {
         this.recoveredField3425.method_13333(this);
      }

      this.method_13325();
   }

   public DiscordBuild method_13316() {
      return this.recoveredField3428 == null ? null : this.recoveredField3428.method_13415();
   }

   public void method_13317(IPCClient$Event var1) {
      this.method_13318(var1, null);
   }

   public void method_13325() {
      this.recoveredField3426 = new Thread(
         () -> {
            try {
               while (true) {
                  Packet var1;
                  if ((var1 = this.recoveredField3428.method_13416()).method_13359() != Packet$OpCode.CLOSE) {
                     JSONObject var2 = var1.method_13358();
                     IPCClient$Event var3 = IPCClient$Event.method_13330(var2.optString("evt", null));
                     String var4 = var2.optString("nonce", null);
                     switch (IPCClient$1.recoveredField658[var3.ordinal()]) {
                        case 1:
                           if (var4 != null && this.recoveredField3427.containsKey(var4)) {
                              this.recoveredField3427.remove(var4).method_13347(var1);
                           }
                           break;
                        case 2:
                           if (var4 != null && this.recoveredField3427.containsKey(var4)) {
                              this.recoveredField3427.remove(var4).method_13349(var2.getJSONObject("data").optString("message", null));
                           }
                           break;
                        case 3:
                           recoveredField3430.method_02650("Reading thread received a 'join' event.");
                           break;
                        case 4:
                           recoveredField3430.method_02650("Reading thread received a 'spectate' event.");
                           break;
                        case 5:
                           recoveredField3430.method_02650("Reading thread received a 'join request' event.");
                           break;
                        case 6:
                           recoveredField3430.method_02650("Reading thread encountered an event with an unknown type: " + var2.getString("evt"));
                     }

                     if (this.recoveredField3425 != null && var2.has("cmd") && var2.getString("cmd").equals("DISPATCH")) {
                        try {
                           JSONObject var5 = var2.getJSONObject("data");
                           switch (IPCClient$1.recoveredField658[IPCClient$Event.method_13330(var2.getString("evt")).ordinal()]) {
                              case 3:
                                 this.recoveredField3425.method_13336(this, var5.getString("secret"));
                                 break;
                              case 4:
                                 this.recoveredField3425.method_13340(this, var5.getString("secret"));
                                 break;
                              case 5:
                                 JSONObject var6 = var5.getJSONObject("user");
                                 User var7 = new User(
                                    var6.getString("username"),
                                    var6.getString("discriminator"),
                                    Long.parseLong(var6.getString("id")),
                                    var6.optString("avatar", null)
                                 );
                                 this.recoveredField3425.method_13337(this, var5.optString("secret", null), var7);
                           }
                        } catch (Exception var8) {
                           recoveredField3430.error("Exception when handling event: ", (Throwable)var8);
                        }
                     }
                  } else {
                     this.recoveredField3428.method_13413(PipeStatus.DISCONNECTED);
                     if (this.recoveredField3425 != null) {
                        this.recoveredField3425.method_13334(this, var1.method_13358());
                     }
                     break;
                  }
               }
            } catch (JSONException | IOException var9) {
               if (var9 instanceof IOException) {
                  recoveredField3430.error("Reading thread encountered an IOException", (Throwable)var9);
               } else {
                  recoveredField3430.error("Reading thread encountered an JSONException", (Throwable)var9);
               }

               this.recoveredField3428.method_13413(PipeStatus.DISCONNECTED);
               if (this.recoveredField3425 != null) {
                  this.recoveredField3425.method_13338(this, var9);
               }
            }
         }
      );
      recoveredField3430.method_02650("Starting IPCClient reading thread!");
      this.recoveredField3426.start();
   }

   public IPCClient(long var1) {
      this.recoveredField3425 = null;
      this.recoveredField3426 = null;
      this.recoveredField3429 = var1;
   }

   public void method_13322(boolean var1) {
      if (var1 && this.method_13314() != PipeStatus.CONNECTED) {
         throw new IllegalStateException(String.format("IPCClient (ID: %d) is not connected!", this.recoveredField3429));
      } else if (!var1 && this.method_13314() == PipeStatus.CONNECTED) {
         throw new IllegalStateException(String.format("IPCClient (ID: %d) is already connected!", this.recoveredField3429));
      }
   }

   public PipeStatus method_13314() {
      return this.recoveredField3428 == null ? PipeStatus.UNINITIALIZED : this.recoveredField3428.method_13407();
   }

   public void method_13319(IPCListener var1) {
      this.recoveredField3425 = var1;
      if (this.recoveredField3428 != null) {
         this.recoveredField3428.method_13411(var1);
      }
   }

   public static int method_13324() {
      String var0 = ManagementFactory.getRuntimeMXBean().getName();
      return Integer.parseInt(var0.substring(0, var0.indexOf(64)));
   }

   @Override
   public void close() {
      this.method_13322(true);

      try {
         this.recoveredField3428.method_13405();
      } catch (IOException var2) {
         recoveredField3430.debug("Failed to close pipe", (Throwable)var2);
      }
   }
}
