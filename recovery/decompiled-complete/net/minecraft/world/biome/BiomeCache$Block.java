package net.minecraft.world.biome;

import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$End;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$DoubleXYRoom;

public class BiomeCache$Block {
   public StructureOceanMonumentPieces$DoubleXYRoom field_0007;
   public StructureNetherBridgePieces$End field_0003;
   public int zPosition;
   public int xPosition;
   public long lastAccessTime;
   public GuiContainerCreative field_0008;
   public BiomeGenBase[] biomes;
   public float[] rainfallValues;

   public BiomeGenBase getBiomeGenAt(int var1, int var2) {
      return this.biomes[var1 & 15 | (var2 & 15) << 4];
   }

   public BiomeCache$Block(BiomeCache var1, int var2, int var3) {
      this.field_76887_g = var1;
      super();
      this.rainfallValues = new float[256];
      this.biomes = new BiomeGenBase[256];
      this.xPosition = var2;
      this.zPosition = var3;
      BiomeCache.access$000(var1).getRainfall(this.rainfallValues, var2 << 4, var3 << 4, 16, 16);
      BiomeCache.access$000(var1).getBiomeGenAt(this.biomes, var2 << 4, var3 << 4, 16, 16, false);
   }
}
