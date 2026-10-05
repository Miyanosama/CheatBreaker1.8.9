package net.minecraft.client.renderer;

import java.util.concurrent.Callable;
import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
import net.minecraft.util.EntitySelectors;
import net.minecraft.world.gen.feature.WorldGenIceSpike;

public class EntityRenderer$2 implements Callable<String> {
   public TileEntitySignRenderer field_0001;
   public EntitySelectors field_0003;
   public WorldGenIceSpike field_0000;

   public String method_07458() {
      return EntityRenderer.access$000(this.field_0002).currentScreen.getClass().getCanonicalName();
   }

   public EntityRenderer$2(EntityRenderer var1) {
      this.field_0002 = var1;
      super();
   }
}
