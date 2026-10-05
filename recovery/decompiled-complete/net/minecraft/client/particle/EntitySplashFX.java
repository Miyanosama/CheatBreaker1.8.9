package net.minecraft.client.particle;

import io.netty.channel.socket.oio.DefaultOioSocketChannelConfig;
import net.minecraft.world.World;
import org.apache.log4j.ConsoleAppender;

public class EntitySplashFX extends EntityRainFX {
   public DefaultOioSocketChannelConfig field_0001;
   public ConsoleAppender field_0000;

   public EntitySplashFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6);
      this.i = 0.04F;
      this.k();
      if (var10 == 0.0 && (var8 != 0.0 || var12 != 0.0)) {
         this.v = var8;
         this.w = var10 + 0.1;
         this.x = var12;
      }
   }
}
