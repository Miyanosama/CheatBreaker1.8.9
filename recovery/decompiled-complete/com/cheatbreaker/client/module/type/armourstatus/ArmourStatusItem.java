package com.cheatbreaker.client.module.type.armourstatus;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.module.CBAnchorHelper;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBPositionEnum;
import com.cheatbreaker.client.ui.util.HudUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.statemap.BlockStateMapper;
import net.minecraft.item.ItemStack;
import net.minecraft.realms.RealmsDefaultVertexFormat;
import org.lwjgl.opengl.GL11;

public class ArmourStatusItem {
   public int field_0006;
   public int field_0011;
   public int field_0005;
   public BlockStateMapper field_0010;
   public int field_0001;
   public boolean field_0002;
   public String field_0012 = "";
   public RealmsDefaultVertexFormat field_0009;
   public Minecraft field_0003;
   public int field_0013;
   public ItemStack field_0000;
   public int field_0007;
   public String field_0008 = "";
   public int field_0004;

   public int method_11372() {
      return this.field_0006;
   }

   public int method_11374() {
      return this.field_0005;
   }

   public ArmourStatusItem(ItemStack var1, int var2, int var3, int var4, boolean var5) {
      this.field_0003 = Minecraft.getMinecraft();
      this.field_0000 = var1;
      this.field_0007 = var2;
      this.field_0004 = var3;
      this.field_0001 = var4;
      this.field_0002 = var5;
      this.method_11371();
   }

   public void method_11373(float var1, float var2) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(32826);
      RenderHelper.enableStandardItemLighting();
      RenderHelper.enableGUIStandardItemLighting();
      ArmourStatusModule.renderItem.zLevel = -160.0F;
      CBGuiAnchor var4 = CheatBreaker.getInstance().getModuleManager().armourStatus.getGuiAnchor();
      boolean var3 = CBAnchorHelper.getHorizontalPositionEnum(var4) == CBPositionEnum.RIGHT;
      if (var3) {
         ArmourStatusModule.renderItem.renderItemAndEffectIntoGUI(this.field_0000, (int)(var1 - (this.field_0007 + this.field_0001)), (int)var2);
         HudUtil.renderItemOverlayIntoGUI(
            this.field_0003.fontRendererObj,
            this.field_0000,
            (int)(var1 - (this.field_0007 + this.field_0001)),
            (int)var2,
            (Boolean)ArmourStatusModule.field_0005.getValue(),
            (Boolean)ArmourStatusModule.field_0024.getValue()
         );
         RenderHelper.disableStandardItemLighting();
         GL11.glDisable(32826);
         GlStateManager.disableBlend();
         this.field_0003
            .fontRendererObj
            .drawStringWithShadow(this.field_0012 + "§r", var1 - (this.field_0001 + this.field_0007 + this.field_0001) - this.field_0011, var2, 16777215);
         this.field_0003
            .fontRendererObj
            .drawStringWithShadow(
               this.field_0008 + "§r",
               var1 - (this.field_0001 + this.field_0007 + this.field_0001) - this.field_0013,
               var2 + (ArmourStatusModule.field_0012.getValue() ? this.field_0005 / 2 : this.field_0005 / 4),
               16777215
            );
      } else {
         ArmourStatusModule.renderItem.renderItemAndEffectIntoGUI(this.field_0000, (int)var1, (int)var2);
         HudUtil.renderItemOverlayIntoGUI(
            this.field_0003.fontRendererObj,
            this.field_0000,
            (int)var1,
            (int)var2,
            ArmourStatusModule.field_0005.method_08908(),
            (Boolean)ArmourStatusModule.field_0024.getValue()
         );
         RenderHelper.disableStandardItemLighting();
         GL11.glDisable(32826);
         GlStateManager.disableBlend();
         this.field_0003.fontRendererObj.drawStringWithShadow(this.field_0012 + "§r", var1 + this.field_0007 + this.field_0001, var2, 16777215);
         this.field_0003
            .fontRendererObj
            .drawStringWithShadow(
               this.field_0008 + "§r",
               var1 + this.field_0007 + this.field_0001,
               var2 + (ArmourStatusModule.field_0012.getValue() ? this.field_0005 / 2 : this.field_0005 / 4),
               16777215
            );
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public void method_11371() {
      this.field_0005 = ArmourStatusModule.field_0012.getValue()
         ? Math.max(Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT * 2, this.field_0004)
         : Math.max(this.field_0003.fontRendererObj.FONT_HEIGHT, this.field_0004);
      if (this.field_0000 != null) {
         boolean var3 = ArmourStatusModule.field_0014.getValue().equals("ON") ? this.field_0000.isItemStackDamageable() : this.field_0000.isItemDamaged();
         if ((this.field_0002 && (Boolean)ArmourStatusModule.field_0009.getValue() || !this.field_0002 && (Boolean)ArmourStatusModule.field_0008.getValue())
            && var3) {
            int var2 = this.field_0000.getMaxDamage() + 1;
            int var1 = var2 - this.field_0000.getItemDamage();
            if (((String)ArmourStatusModule.field_0002.getValue()).equalsIgnoreCase("value")) {
               this.field_0008 = "§"
                  + ArmourStatusDamageComparable.getDamageColor(
                     ArmourStatusModule.field_0023, ((String)ArmourStatusModule.field_0017.getValue()).equalsIgnoreCase("percent") ? var1 * 100 / var2 : var1
                  )
                  + var1
                  + (ArmourStatusModule.field_0018.getValue() ? "/" + var2 : "");
            } else if (((String)ArmourStatusModule.field_0002.getValue()).equalsIgnoreCase("percent")) {
               this.field_0008 = "§"
                  + ArmourStatusDamageComparable.getDamageColor(
                     ArmourStatusModule.field_0023, ((String)ArmourStatusModule.field_0017.getValue()).equalsIgnoreCase("percent") ? var1 * 100 / var2 : var1
                  )
                  + var1 * 100 / var2
                  + "%";
            }
         }

         this.field_0013 = this.field_0003.fontRendererObj.getStringWidth(HudUtil.method_03744(this.field_0008));
         this.field_0006 = this.field_0001 + this.field_0007 + this.field_0001 + this.field_0013;
         if ((Boolean)ArmourStatusModule.field_0012.getValue()) {
            this.field_0012 = this.field_0000.getDisplayName();
            this.field_0006 = this.field_0001
               + this.field_0007
               + this.field_0001
               + Math.max(this.field_0003.fontRendererObj.getStringWidth(HudUtil.method_03744(this.field_0012)), this.field_0013);
         }

         this.field_0011 = this.field_0003.fontRendererObj.getStringWidth(HudUtil.method_03744(this.field_0012));
      }
   }
}
