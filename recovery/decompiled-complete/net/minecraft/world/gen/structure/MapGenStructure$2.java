package net.minecraft.world.gen.structure;

import java.util.concurrent.Callable;
import net.minecraft.client.renderer.GlStateManager$FogState;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.monster.EntityBlaze$AIFireballAttack;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.storage.WorldInfo$3;

public class MapGenStructure$2 implements Callable<String> {
   public WorldInfo$3 field_0003;
   public EntityBlaze$AIFireballAttack field_0005;
   public GlStateManager$FogState field_0002;
   public DynamicTexture field_0006;

   public MapGenStructure$2(MapGenStructure var1, int var2, int var3) {
      this.field_0001 = var1;
      this.field_0004 = var2;
      this.field_0000 = var3;
      super();
   }

   public String method_28385() {
      return String.valueOf(ChunkCoordIntPair.chunkXZ2Int(this.field_0004, this.field_0000));
   }
}
