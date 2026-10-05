package net.minecraft.client.renderer.entity;

import io.netty.channel.socket.oio.DefaultOioServerSocketChannelConfig;
import io.netty.util.internal.chmv8.ForkJoinPool$DefaultForkJoinWorkerThreadFactory;
import net.minecraft.client.model.ModelArmorStandArmor;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Start;
import org.apache.log4j.or.ThreadGroupRenderer;

public class ArmorStandRenderer$1 extends LayerBipedArmor {
   public ThreadGroupRenderer field_0002;
   public ForkJoinPool$DefaultForkJoinWorkerThreadFactory field_0004;
   public StructureNetherBridgePieces$Start field_0001;
   public DefaultOioServerSocketChannelConfig field_0003;

   @Override
   public void initArmor() {
      this.c = new ModelArmorStandArmor(0.5F);
      this.d = new ModelArmorStandArmor(1.0F);
   }

   public ArmorStandRenderer$1(ArmorStandRenderer var1, RendererLivingEntity var2) {
      this.field_177196_a = var1;
      super(var2);
   }
}
