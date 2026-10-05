package net.minecraft.world.biome;

import net.minecraft.client.renderer.block.model.ItemModelGenerator$SpanFacing;
import net.minecraft.tileentity.TileEntityNote;
import org.apache.log4j.lf5.util.LogFileParser$1;

public class BiomeGenBase$Height {
   public LogFileParser$1 field_0002;
   public float rootHeight;
   public TileEntityNote field_0001;
   public ItemModelGenerator$SpanFacing field_0003;
   public float variation;

   public BiomeGenBase$Height(float var1, float var2) {
      this.rootHeight = var1;
      this.variation = var2;
   }

   public BiomeGenBase$Height attenuate() {
      return new BiomeGenBase$Height(this.rootHeight * 0.8F, this.variation * 0.6F);
   }
}
