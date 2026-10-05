package net.minecraft.client.resources.data;

import io.netty.handler.timeout.IdleStateHandler$AllIdleTimeoutTask;
import net.minecraft.client.gui.spectator.categories.SpectatorDetails;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.optifine.CrashReporter$1;

public class IMetadataSerializer$Registration<T extends IMetadataSection> {
   public SpectatorDetails field_0003;
   public BiomeGenTaiga field_0005;
   public CrashReporter$1 field_0002;
   public IdleStateHandler$AllIdleTimeoutTask field_0004;
   public Class<T> clazz;
   public IMetadataSectionSerializer<T> section;

   public IMetadataSerializer$Registration(IMetadataSectionSerializer<T> var1, Class<T> var2, Class var3) {
      this.field_110501_c = var1;
      super();
      this.section = var2;
      this.clazz = var3;
   }
}
