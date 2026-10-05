package io.netty.handler.ssl;

import javax.net.ssl.SSLException;
import net.minecraft.entity.monster.EntitySpider$AISpiderAttack;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.realms.RealmsServerAddress;
import net.minecraft.world.biome.BiomeGenDesert;
import net.optifine.player.PlayerConfiguration;

public class NotSslRecordException extends SSLException {
   public RealmsServerAddress __junk4674489642102191600;
   public EntityTameable __junk6525801060995110126;
   public EntitySpider$AISpiderAttack __junk101463880541709525;
   public BiomeGenDesert __junk3994825303013543980;
   public PlayerConfiguration __junk557045093896969810;
   public static long serialVersionUID;

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
