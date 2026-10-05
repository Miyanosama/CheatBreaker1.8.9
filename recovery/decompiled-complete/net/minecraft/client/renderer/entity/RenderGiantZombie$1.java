package net.minecraft.client.renderer.entity;

import io.netty.channel.udt.nio.NioUdtMessageAcceptorChannel;
import javax.vecmath.Color4f;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.world.gen.feature.WorldGenSwamp;
import net.optifine.BlockDir;

public class RenderGiantZombie$1 extends LayerBipedArmor {
   public WorldGenSwamp field_0002;
   public BlockDir field_0004;
   public Color4f field_0001;
   public NioUdtMessageAcceptorChannel field_0000;

   public RenderGiantZombie$1(RenderGiantZombie var1, RendererLivingEntity var2) {
      this.field_177197_a = var1;
      super(var2);
   }

   @Override
   public void initArmor() {
      this.c = new ModelZombie(0.5F, true);
      this.d = new ModelZombie(1.0F, true);
   }
}
