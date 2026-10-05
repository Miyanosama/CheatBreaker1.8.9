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
import net.minecraft.client.gui.ServerListEntryNormal;
import net.minecraft.entity.passive.EntityVillager$EmeraldForItems;
import net.optifine.shaders.config.MacroProcessor;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recovered.unidentified.UnidentifiedClass3584;

public class IPCClient implements Closeable {
   public IPCListener field_0004;
   public MacroProcessor field_0007;
   public UnidentifiedClass3584 field_0003;
   public Thread field_0006;
   public HashMap<String, Callback> field_0000 = new HashMap<>();
   public volatile Pipe field_0001;
   public EntityVillager$EmeraldForItems field_0008;
   public long field_0005;
   public ServerListEntryNormal field_0002;
   public static Logger field_0009 = LoggerFactory.getLogger(IPCClient.class);

   public void method_13318(IPCClient$Event var1, Callback var2) {
      this.method_13322(true);
      if (!var1.method_13329()) {
         throw new IllegalStateException("Cannot subscribe to " + var1 + " event!");
      } else {
         field_0009.method_02650(String.format("Subscribing to Event: %s", var1.name()));
         this.field_0001.method_13412(Packet$OpCode.field_0007, new JSONObject().put("cmd", "SUBSCRIBE").put("evt", var1.name()), var2);
      }
   }

   public void method_13320(RichPresence var1) {
      this.method_13321(var1, null);
   }

   public void method_13321(RichPresence var1, Callback var2) {
      this.method_13322(true);
      field_0009.method_02650("Sending RichPresence to discord: " + (var1 == null ? null : var1.method_13367().toString()));
      this.field_0001
         .method_13412(
            Packet$OpCode.field_0007,
            new JSONObject()
               .put("cmd", "SET_ACTIVITY")
               .put("args", new JSONObject().method_07215("pid", method_13324()).put("activity", var1 == null ? null : var1.method_13367())),
            var2
         );
   }

   public void method_13323(DiscordBuild... var1) {
      this.method_13322(false);
      this.field_0000.clear();
      this.field_0001 = null;
      this.field_0001 = Pipe.method_13409(this, this.field_0005, this.field_0000, var1);
      field_0009.method_02650("Client is now connected and ready!");
      if (this.field_0004 != null) {
         this.field_0004.method_13333(this);
      }

      this.method_13325();
   }

   public DiscordBuild method_13316() {
      return this.field_0001 == null ? null : this.field_0001.method_13415();
   }

   public void method_13317(IPCClient$Event var1) {
      this.method_13318(var1, null);
   }

   public void method_13325() {
      this.field_0006 = new Thread(
         () -> {
            try {
               while (true) {
                  Packet var1;
                  if ((var1 = this.field_0001.method_13416()).method_13359() != Packet$OpCode.field_0003) {
                     JSONObject var2 = var1.method_13358();
                     IPCClient$Event var3 = IPCClient$Event.method_13330(var2.method_07220("evt", null));
                     String var4 = var2.method_07220("nonce", null);
                     switch (IPCClient$1.field_0001[var3.ordinal()]) {
                        case 1:
                           if (var4 != null && this.field_0000.containsKey(var4)) {
                              this.field_0000.remove(var4).method_13347(var1);
                           }
                           break;
                        case 2:
                           if (var4 != null && this.field_0000.containsKey(var4)) {
                              this.field_0000.remove(var4).method_13349(var2.method_07181("data").method_07220("message", null));
                           }
                           break;
                        case 3:
                           field_0009.method_02650("Reading thread received a 'join' event.");
                           break;
                        case 4:
                           field_0009.method_02650("Reading thread received a 'spectate' event.");
                           break;
                        case 5:
                           field_0009.method_02650("Reading thread received a 'join request' event.");
                           break;
                        case 6:
                           field_0009.method_02650("Reading thread encountered an event with an unknown type: " + var2.getString("evt"));
                     }

                     if (this.field_0004 != null && var2.has("cmd") && var2.getString("cmd").equals("DISPATCH")) {
                        try {
                           JSONObject var5 = var2.method_07181("data");
                           switch (IPCClient$1.field_0001[IPCClient$Event.method_13330(var2.getString("evt")).ordinal()]) {
                              case 3:
                                 this.field_0004.method_13336(this, var5.getString("secret"));
                                 break;
                              case 4:
                                 this.field_0004.method_13340(this, var5.getString("secret"));
                                 break;
                              case 5:
                                 JSONObject var6 = var5.method_07181("user");
                                 User var7 = new User(
                                    var6.getString("username"),
                                    var6.getString("discriminator"),
                                    Long.parseLong(var6.getString("id")),
                                    var6.method_07220("avatar", null)
                                 );
                                 this.field_0004.method_13337(this, var5.method_07220("secret", null), var7);
                           }
                        } catch (Exception var8) {
                           field_0009.error("Exception when handling event: ", (Throwable)var8);
                        }
                     }
                  } else {
                     this.field_0001.method_13413(PipeStatus.field_0005);
                     if (this.field_0004 != null) {
                        this.field_0004.method_13334(this, var1.method_13358());
                     }
                     break;
                  }
               }
            } catch (JSONException | IOException var9) {
               if (var9 instanceof IOException) {
                  field_0009.error("Reading thread encountered an IOException", (Throwable)var9);
               } else {
                  field_0009.error("Reading thread encountered an JSONException", (Throwable)var9);
               }

               this.field_0001.method_13413(PipeStatus.field_0005);
               if (this.field_0004 != null) {
                  this.field_0004.method_13338(this, var9);
               }
            }
         }
      );
      field_0009.method_02650("Starting IPCClient reading thread!");
      this.field_0006.start();
   }

   public IPCClient(long var1) {
      this.field_0004 = null;
      this.field_0006 = null;
      this.field_0005 = var1;
   }

   public void method_13322(boolean var1) {
      if (var1 && this.method_13314() != PipeStatus.field_0000) {
         throw new IllegalStateException(String.format("IPCClient (ID: %d) is not connected!", this.field_0005));
      } else if (!var1 && this.method_13314() == PipeStatus.field_0000) {
         throw new IllegalStateException(String.format("IPCClient (ID: %d) is already connected!", this.field_0005));
      }
   }

   public PipeStatus method_13314() {
      return this.field_0001 == null ? PipeStatus.field_0003 : this.field_0001.method_13407();
   }

   public void method_13319(IPCListener var1) {
      this.field_0004 = var1;
      if (this.field_0001 != null) {
         this.field_0001.method_13411(var1);
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
         this.field_0001.method_13405();
      } catch (IOException var2) {
         field_0009.debug("Failed to close pipe", (Throwable)var2);
      }
   }
}
