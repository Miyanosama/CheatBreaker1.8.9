package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.module.type.AnimationsModule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType;
import net.minecraft.init.Bootstrap$3;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition$MovingObjectType;
import org.apache.log4j.lf5.util.LogFileParser$1;

public class UnidentifiedClass4786 {
   public static UnidentifiedClass4786 field_0004 = new UnidentifiedClass4786();
   public Minecraft field_0007 = Minecraft.getMinecraft();
   public LogFileParser$1 field_0003;
   public Bootstrap$3 field_0006;
   public float field_0000;
   public S1FPacketSetExperience field_0001;
   public float field_0008;
   public boolean field_0005;
   public int field_0002;

   public void method_28639(TickEvent var1) {
      this.method_28634();
   }

   public int method_28638(EntityPlayerSP var1) {
      return var1.isPotionActive(Potion.digSpeed)
         ? 5 - var1.getActivePotionEffect(Potion.digSpeed).getAmplifier()
         : (var1.isPotionActive(Potion.digSlowdown) ? 8 + var1.getActivePotionEffect(Potion.digSlowdown).getAmplifier() * 2 : 6);
   }

   public void method_28635() {
      GlStateManager.translate(-0.5F, 0.2F, 0.0F);
      GlStateManager.rotate(30.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-80.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(60.0F, 0.0F, 1.0F, 0.0F);
   }

   public boolean method_28641(ItemStack var1) {
      AnimationsModule var2 = CheatBreaker.getInstance().getModuleManager().field_0041;
      switch (UnidentifiedClass4776.field_0002[var1.getItemUseAction().ordinal()]) {
         case 1:
         case 2:
            if (!var2.field_0007.method_08908()) {
               return true;
            }
            break;
         case 3:
            if (!var2.field_0000.method_08908()) {
               return true;
            }
            break;
         case 4:
            if (!var2.field_0011.method_08908()) {
               return true;
            }
            break;
         case 5:
            if (!var2.field_0005.method_08908()) {
               return true;
            }
      }

      GlStateManager.translate(0.58800083F, 0.36999986F, -0.77000016F);
      GlStateManager.translate(0.0F, -0.3F, 0.0F);
      GlStateManager.scale(1.5F, 1.5F, 1.5F);
      GlStateManager.rotate(50.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(335.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.translate(-0.9375F, -0.0625F, 0.0F);
      GlStateManager.scale(-2.0F, 2.0F, -2.0F);
      if (this.field_0007.getRenderItem().shouldRenderItemIn3D(var1)) {
         GlStateManager.scale(0.58823526F, 0.58823526F, 0.58823526F);
         GlStateManager.rotate(-25.0F, 0.0F, 0.0F, 1.0F);
         GlStateManager.rotate(0.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.translate(0.0F, -0.25F, -0.125F);
         GlStateManager.scale(0.5F, 0.5F, 0.5F);
         return true;
      } else {
         GlStateManager.scale(0.5F, 0.5F, 0.5F);
         return false;
      }
   }

   public float method_28636(float var1) {
      float var2 = this.field_0000 - this.field_0008;
      if (!this.field_0005) {
         return this.field_0007.thePlayer.getSwingProgress(var1);
      } else {
         if (var2 < 0.0F) {
            var2++;
         }

         return this.field_0008 + var2 * var1;
      }
   }

   public void method_28644(float var1) {
      float var2 = MathHelper.sin(var1 * (float) Math.PI);
      float var3 = MathHelper.sin(MathHelper.sqrt_float(var1) * (float) Math.PI);
      GlStateManager.translate(-var3 * 0.4F, MathHelper.sin(MathHelper.sqrt_float(var1) * (float) Math.PI * 2.0F) * 0.2F, -var2 * 0.2F);
   }

   public void method_28643() {
      AnimationsModule var1 = CheatBreaker.getInstance().getModuleManager().field_0041;
      if (var1.field_0016.method_08908()) {
         GlStateManager.translate(-0.15F, -0.2F, 0.0F);
         GlStateManager.rotate(70.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.translate(0.119F, 0.2F, -0.024F);
      }
   }

   public void method_28642(ItemStack var1, int var2, float var3) {
      AnimationsModule var4 = CheatBreaker.getInstance().getModuleManager().field_0041;
      if (var4.field_0007.method_08908()) {
         float var5 = var2 - var3 + 1.0F;
         float var6 = 1.0F - var5 / var1.getMaxItemUseDuration();
         float var7 = 1.0F - var6;
         var7 = var7 * var7 * var7;
         var7 = var7 * var7 * var7;
         var7 = var7 * var7 * var7;
         float var8 = 1.0F - var7;
         GlStateManager.translate(0.0F, MathHelper.abs(MathHelper.cos(var5 / 4.0F * (float) Math.PI) * 0.1F) * (var6 > 0.2 ? 1 : 0), 0.0F);
         GlStateManager.translate(var8 * 0.6F, -var8 * 0.5F, 0.0F);
         GlStateManager.rotate(var8 * 90.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(var8 * 10.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.rotate(var8 * 30.0F, 0.0F, 0.0F, 1.0F);
      } else {
         float var9 = var2 - var3 + 1.0F;
         float var10 = var9 / var1.getMaxItemUseDuration();
         float var14 = MathHelper.abs(MathHelper.cos(var9 / 4.0F * (float) Math.PI) * 0.1F);
         if (var10 >= 0.8F) {
            var14 = 0.0F;
         }

         GlStateManager.translate(0.0F, var14, 0.0F);
         float var15 = 1.0F - (float)Math.pow(var10, 27.0);
         GlStateManager.translate(var15 * 0.6F, var15 * -0.5F, var15 * 0.0F);
         GlStateManager.rotate(var15 * 90.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(var15 * 10.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.rotate(var15 * 30.0F, 0.0F, 0.0F, 1.0F);
      }
   }

   public boolean method_28640(ItemRenderer var1, ItemStack var2, float var3, float var4) {
      AnimationsModule var5 = CheatBreaker.getInstance().getModuleManager().field_0041;
      if (var2 == null) {
         return false;
      } else {
         Item var6 = var2.getItem();
         if (var6 != Items.filled_map && !this.field_0007.getRenderItem().shouldRenderItemIn3D(var2)) {
            EnumAction var7 = var2.getItemUseAction();
            if ((var6 != Items.fishing_rod || var5.field_0012.method_08908())
               && (var7 != EnumAction.NONE || var5.field_0005.method_08908())
               && (var7 != EnumAction.BLOCK || var5.field_0000.method_08908())
               && (var7 != EnumAction.BOW || var5.field_0011.method_08908())) {
               EntityPlayerSP var8 = this.field_0007.thePlayer;
               float var9 = var8.B + (var8.z - var8.B) * var4;
               GlStateManager.pushMatrix();
               GlStateManager.rotate(var9, 1.0F, 0.0F, 0.0F);
               GlStateManager.rotate(var8.A + (var8.y - var8.A) * var4, 0.0F, 1.0F, 0.0F);
               RenderHelper.enableStandardItemLighting();
               GlStateManager.popMatrix();
               float var10 = var8.prevRenderArmPitch + (var8.renderArmPitch - var8.prevRenderArmPitch) * var4;
               float var11 = var8.prevRenderArmYaw + (var8.renderArmYaw - var8.prevRenderArmYaw) * var4;
               GlStateManager.rotate((var8.z - var10) * 0.1F, 1.0F, 0.0F, 0.0F);
               GlStateManager.rotate((var8.y - var11) * 0.1F, 0.0F, 1.0F, 0.0F);
               GlStateManager.enableRescaleNormal();
               if (var6 instanceof ItemCloth) {
                  GlStateManager.enableBlend();
                  GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
               }

               int var12 = this.field_0007.theWorld.getCombinedLight(new BlockPos(var8.s, var8.t + var8.getEyeHeight(), var8.u), 0);
               float var13 = var12 & 65535;
               float var14 = var12 >> 16;
               OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var13, var14);
               int var15 = var6.getColorFromItemStack(var2, 0);
               float var16 = (var15 >> 16 & 0xFF) / 255.0F;
               float var17 = (var15 >> 8 & 0xFF) / 255.0F;
               float var18 = (var15 & 0xFF) / 255.0F;
               GlStateManager.color(var16, var17, var18, 1.0F);
               GlStateManager.pushMatrix();
               int var19 = var8.getItemInUseCount();
               float var20 = this.method_28636(var4);
               boolean var21 = false;
               if (var5.field_0008.method_08908()
                  && var19 <= 0
                  && this.field_0007.gameSettings.field_0071.isKeyDown()
                  && CheatBreaker.getInstance().getModuleManager().field_0041.method_28796()) {
                  boolean var22 = var7 == EnumAction.BLOCK;
                  boolean var23 = false;
                  if (var6 instanceof ItemFood && var8.canEat(((ItemFood)var6).method_03551())) {
                     var23 = var7 == EnumAction.EAT || var7 == EnumAction.DRINK;
                  }

                  if (var22 || var23) {
                     var21 = true;
                  }
               }

               if ((var19 > 0 || var21) && var7 != EnumAction.NONE && this.field_0007.thePlayer.isUsingItem()) {
                  switch (UnidentifiedClass4776.field_0002[var7.ordinal()]) {
                     case 1:
                     case 2:
                        this.method_28642(var2, var19, var4);
                        this.method_28637(var3, var5.field_0013.method_08908() ? var20 : 0.0F);
                        break;
                     case 3:
                        this.method_28637(var3, var5.field_0013.method_08908() ? var20 : 0.0F);
                        this.method_28635();
                        break;
                     case 4:
                        this.method_28637(var3, var5.field_0013.method_08908() ? var20 : 0.0F);
                        this.method_28645(var2, var19, var4);
                  }
               } else {
                  this.method_28644(var20);
                  this.method_28637(var3, var20);
               }

               if (var6.method_12601()) {
                  GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
               }

               if (this.method_28641(var2)) {
                  var1.renderItem(var8, var2, ItemCameraTransforms$TransformType.FIRST_PERSON);
               } else {
                  var1.renderItem(var8, var2, ItemCameraTransforms$TransformType.NONE);
               }

               GlStateManager.popMatrix();
               if (var6 instanceof ItemCloth) {
                  GlStateManager.disableBlend();
               }

               GlStateManager.disableRescaleNormal();
               RenderHelper.disableStandardItemLighting();
               return true;
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   public void method_28645(ItemStack var1, int var2, float var3) {
      AnimationsModule var4 = CheatBreaker.getInstance().getModuleManager().field_0041;
      GlStateManager.rotate(-18.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(-12.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-8.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.translate(-0.9F, 0.2F, 0.0F);
      float var5 = var1.getMaxItemUseDuration() - (var2 - var3 + 1.0F);
      float var6 = var5 / 20.0F;
      var6 = (var6 * var6 + var6 * 2.0F) / 3.0F;
      if (var6 > 1.0F) {
         var6 = 1.0F;
      }

      if (var6 > 0.1F) {
         GlStateManager.translate(0.0F, MathHelper.sin((var5 - 0.1F) * 1.3F) * 0.01F * (var6 - 0.1F), 0.0F);
      }

      GlStateManager.translate(0.0F, 0.0F, var6 * 0.1F);
      if (var4.field_0011.method_08908()) {
         GlStateManager.rotate(-335.0F, 0.0F, 0.0F, 1.0F);
         GlStateManager.rotate(-50.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.translate(0.0F, 0.5F, 0.0F);
      }

      float var7 = 1.0F + var6 * 0.2F;
      GlStateManager.scale(1.0F, 1.0F, var7);
      if (var4.field_0011.method_08908()) {
         GlStateManager.translate(0.0F, -0.5F, 0.0F);
         GlStateManager.rotate(50.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(335.0F, 0.0F, 0.0F, 1.0F);
      }
   }

   public UnidentifiedClass4786() {
      CheatBreaker.getInstance().method_19817().method_21938(TickEvent.class, this::method_28639);
   }

   public void method_28634() {
      AnimationsModule var1 = CheatBreaker.getInstance().getModuleManager().field_0041;
      EntityPlayerSP var2 = this.field_0007.thePlayer;
      if (var2 != null) {
         this.field_0008 = this.field_0000;
         int var3 = this.method_28638(var2);
         if (var1.field_0008.method_08908()
            && this.field_0007.gameSettings.field_0073.isKeyDown()
            && this.field_0007.objectMouseOver != null
            && this.field_0007.objectMouseOver.typeOfHit == MovingObjectPosition$MovingObjectType.BLOCK
            && (!this.field_0005 || this.field_0002 >= var3 >> 1 || this.field_0002 < 0)) {
            this.field_0005 = true;
            this.field_0002 = -1;
         }

         if (this.field_0005) {
            this.field_0002++;
            if (this.field_0002 >= var3) {
               this.field_0002 = 0;
               this.field_0005 = false;
            }
         } else {
            this.field_0002 = 0;
         }

         this.field_0000 = (float)this.field_0002 / var3;
      }
   }

   public void method_28637(float var1, float var2) {
      GlStateManager.translate(0.56F, -0.52F - (1.0F - var1) * 0.6F, -0.72F);
      GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
      float var3 = MathHelper.sin(var2 * var2 * (float) Math.PI);
      float var4 = MathHelper.sin(MathHelper.sqrt_float(var2) * (float) Math.PI);
      GlStateManager.rotate(-var3 * 20.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-var4 * 20.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(-var4 * 80.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.scale(0.4F, 0.4F, 0.4F);
   }
}
