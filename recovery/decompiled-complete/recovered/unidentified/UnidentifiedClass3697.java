package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import io.netty.util.ResourceLeakDetector$Level;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockPlanks$EnumType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.optifine.entity.model.ModelAdapterPig;
import net.optifine.util.RenderChunkUtils;

public class UnidentifiedClass3697 extends Render<EntityTNTPrimed> {
   public ResourceLeakDetector$Level field_0001;
   public ModelAdapterPig field_0003;
   public BlockPane field_0004;
   public RenderChunkUtils field_0000;
   public BlockPlanks$EnumType field_0002;

   public UnidentifiedClass3697(RenderManager var1) {
      super(var1);
      this.c = 0.5F;
   }

   public void method_22537(EntityTNTPrimed var1, double var2, double var4, double var6, float var8, float var9) {
      if (CheatBreaker.getInstance().getModuleManager().field_0036.isEnabled()) {
         CheatBreaker.getInstance().getModuleManager().field_0036.method_23093(this, var1, var2, var4, var6, var9);
      }

      BlockRendererDispatcher var10 = Minecraft.getMinecraft().getBlockRendererDispatcher();
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)var2, (float)var4 + 0.5F, (float)var6);
      if (var1.fuse - var9 + 1.0F < 10.0F) {
         float var11 = 1.0F - (var1.fuse - var9 + 1.0F) / 10.0F;
         var11 = MathHelper.clamp_float(var11, 0.0F, 1.0F);
         var11 *= var11;
         var11 *= var11;
         float var12 = 1.0F + var11 * 0.3F;
         GlStateManager.scale(var12, var12, var12);
      }

      float var16 = (1.0F - (var1.fuse - var9 + 1.0F) / 100.0F) * 0.8F;
      this.bindEntityTexture(var1);
      GlStateManager.translate(-0.5F, -0.5F, 0.5F);
      var10.renderBlockBrightness(Blocks.tnt.getDefaultState(), var1.a_(var9));
      GlStateManager.translate(0.0F, 0.0F, 1.0F);
      if (var1.fuse / 5 % 2 == 0) {
         GlStateManager.disableTexture2D();
         GlStateManager.disableLighting();
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 772);
         GlStateManager.color(1.0F, 1.0F, 1.0F, var16);
         GlStateManager.doPolygonOffset(-3.0F, -3.0F);
         GlStateManager.enablePolygonOffset();
         var10.renderBlockBrightness(Blocks.tnt.getDefaultState(), 1.0F);
         GlStateManager.doPolygonOffset(0.0F, 0.0F);
         GlStateManager.disablePolygonOffset();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.disableBlend();
         GlStateManager.enableLighting();
         GlStateManager.enableTexture2D();
      }

      GlStateManager.popMatrix();
      super.doRender(var1, var2, var4, var6, var8, var9);
   }

   public ResourceLocation method_22536(EntityTNTPrimed var1) {
      return TextureMap.locationBlocksTexture;
   }
}
