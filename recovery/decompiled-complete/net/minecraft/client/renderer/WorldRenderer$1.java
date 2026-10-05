package net.minecraft.client.renderer;

import com.google.common.primitives.Floats;
import java.util.Comparator;
import net.minecraft.block.BlockBanner$1;
import net.minecraft.village.VillageCollection;
import org.apache.log4j.chainsaw.LoadXMLAction;

public class WorldRenderer$1 implements Comparator<Integer> {
   public BlockBanner$1 field_0002;
   public LoadXMLAction field_0001;
   public VillageCollection field_0000;

   public int compare(Integer var1, Integer var2) {
      return Floats.compare(this.val$afloat[var2], this.val$afloat[var1]);
   }

   public WorldRenderer$1(WorldRenderer var1, float[] var2) {
      this.this$0 = var1;
      this.val$afloat = var2;
      super();
   }
}
