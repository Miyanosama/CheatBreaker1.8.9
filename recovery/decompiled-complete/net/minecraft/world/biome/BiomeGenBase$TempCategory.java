package net.minecraft.world.biome;

import io.netty.channel.oio.AbstractOioMessageChannel;
import net.minecraft.client.gui.MapItemRenderer$1;
import net.minecraft.realms.Realms;
import net.minecraft.world.gen.structure.MapGenNetherBridge$Start;
import net.optifine.ConnectedTextures;

public enum BiomeGenBase$TempCategory {
   OCEAN,
   MEDIUM,
   WARM,
   COLD;

   public ConnectedTextures field_0004;
   public MapGenNetherBridge$Start field_0007;
   public MapItemRenderer$1 field_0006;
   public Realms field_0000;
   // $VF: synthetic field
   public static BiomeGenBase$TempCategory[] $VALUES = new BiomeGenBase$TempCategory[]{OCEAN, BiomeGenBase$TempCategory.COLD, MEDIUM, WARM};
   public AbstractOioMessageChannel field_0009;
}
