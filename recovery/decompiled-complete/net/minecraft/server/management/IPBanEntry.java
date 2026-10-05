package net.minecraft.server.management;

import com.google.gson.JsonObject;
import io.netty.channel.AbstractChannelHandlerContext$14;
import java.util.Date;
import net.minecraft.block.BlockPistonMoving;
import net.minecraft.client.particle.EntityLargeExplodeFX$Factory;
import net.optifine.ConnectedTextures;
import net.optifine.shaders.BlockAliases;
import org.apache.log4j.lf5.AppenderFinalizer;

public class IPBanEntry extends BanEntry<String> {
   public ConnectedTextures field_0001;
   public BlockAliases field_0005;
   public AbstractChannelHandlerContext$14 field_0000;
   public BlockPistonMoving field_0003;
   public AppenderFinalizer field_0004;
   public EntityLargeExplodeFX$Factory field_0002;

   public static String getIPFromJson(JsonObject var0) {
      return var0.has("ip") ? var0.get("ip").getAsString() : null;
   }

   public IPBanEntry(String var1) {
      this(var1, (Date)null, (String)null, (Date)null, (String)null);
   }

   @Override
   public void onSerialization(JsonObject var1) {
      if (this.getValue() != null) {
         var1.addProperty("ip", this.getValue());
         super.onSerialization(var1);
      }
   }

   public IPBanEntry(String var1, Date var2, String var3, Date var4, String var5) {
      super(var1, var2, var3, var4, var5);
   }

   public IPBanEntry(JsonObject var1) {
      super(getIPFromJson(var1), var1);
   }
}
