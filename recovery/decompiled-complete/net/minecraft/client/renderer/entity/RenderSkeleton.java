package net.minecraft.client.renderer.entity;

import io.netty.channel.udt.nio.NioUdtAcceptorChannel;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces$PieceWeight;
import org.json.XML;

public class RenderSkeleton extends RenderBiped<EntitySkeleton> {
   public StructureNetherBridgePieces field_0003;
   public static ResourceLocation skeletonTextures = new ResourceLocation("textures/entity/skeleton/skeleton.png");
   public static ResourceLocation witherSkeletonTextures = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");
   public GuiCreateWorld field_0001;
   public NioUdtAcceptorChannel field_0002;
   public XML field_0000;
   public StructureVillagePieces$PieceWeight field_0005;

   @Override
   public void y_() {
      GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
   }

   public RenderSkeleton(RenderManager var1) {
      super(var1, new ModelSkeleton(), 0.5F);
      this.a(new LayerHeldItem(this));
      this.a(new RenderSkeleton$1(this, this));
   }

   public ResourceLocation getEntityTexture(EntitySkeleton var1) {
      return var1.getSkeletonType() == 1 ? witherSkeletonTextures : skeletonTextures;
   }

   public void preRenderCallback(EntitySkeleton var1, float var2) {
      if (var1.getSkeletonType() == 1) {
         GlStateManager.scale(1.2F, 1.2F, 1.2F);
      }
   }
}
