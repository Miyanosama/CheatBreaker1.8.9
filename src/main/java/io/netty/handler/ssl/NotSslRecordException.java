package io.netty.handler.ssl;

import javax.net.ssl.SSLException;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.realms.RealmsServerAddress;
import net.minecraft.world.biome.BiomeGenDesert;
import net.optifine.player.PlayerConfiguration;

public class NotSslRecordException extends SSLException {
   public static final long serialVersionUID = -4316784434770656841L;

   public NotSslRecordException(String var1) {
      super(var1);
   }

   public NotSslRecordException(String var1, Throwable var2) {
      super(var1, var2);
   }

   public NotSslRecordException() {
      super("");
   }

   public NotSslRecordException(Throwable var1) {
      super(var1);
   }
}
