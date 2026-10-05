package recovered.unidentified;

import java.util.Random;
import net.minecraft.client.renderer.entity.layers.LayerIronGolemFlower;
import net.minecraft.item.ItemArmor;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.biome.BiomeGenMutated;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.optifine.render.Blender;

public class UnidentifiedClass4511 extends BiomeGenMutated {
   public ItemArmor field_0000;
   public LayerIronGolemFlower field_0003;
   public Blender field_0002;

   public UnidentifiedClass4511(BiomeGenForest var1, int var2, BiomeGenBase var3) {
      this.field_0001 = var1;
      super(var2, var3);
   }

   @Override
   public WorldGenAbstractTree genBigTreeChance(Random var1) {
      return var1.nextBoolean() ? BiomeGenForest.field_150629_aC : BiomeGenForest.field_150630_aD;
   }
}
