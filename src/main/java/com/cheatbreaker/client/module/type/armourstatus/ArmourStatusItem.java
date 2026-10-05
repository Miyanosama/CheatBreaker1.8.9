package com.cheatbreaker.client.module.type.armourstatus;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.module.CBAnchorHelper;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBPositionEnum;
import com.cheatbreaker.client.ui.util.HudUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class ArmourStatusItem {
   public int recoveredField2611;
   public int recoveredField2612;
   public int recoveredField2613;
   public int recoveredField2614;
   public boolean recoveredField2615;
   public String recoveredField2616 = "";
   public Minecraft recoveredField2617;
   public int recoveredField2618;
   public ItemStack recoveredField2619;
   public int recoveredField2620;
   public String recoveredField2621 = "";
   public int recoveredField2622;

   public int method_11372() {
      return this.recoveredField2611;
   }

   public int method_11374() {
      return this.recoveredField2613;
   }

   public ArmourStatusItem(ItemStack var1, int var2, int var3, int var4, boolean var5) {
      this.recoveredField2617 = Minecraft.getMinecraft();
      this.recoveredField2619 = var1;
      this.recoveredField2620 = var2;
      this.recoveredField2622 = var3;
      this.recoveredField2614 = var4;
      this.recoveredField2615 = var5;
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
         ArmourStatusModule.renderItem
            .renderItemAndEffectIntoGUI(this.recoveredField2619, (int)(var1 - (this.recoveredField2620 + this.recoveredField2614)), (int)var2);
         HudUtil.renderItemOverlayIntoGUI(
            this.recoveredField2617.fontRendererObj,
            this.recoveredField2619,
            (int)(var1 - (this.recoveredField2620 + this.recoveredField2614)),
            (int)var2,
            (Boolean)ArmourStatusModule.recoveredField1882.getValue(),
            (Boolean)ArmourStatusModule.recoveredField1883.getValue()
         );
         RenderHelper.disableStandardItemLighting();
         GL11.glDisable(32826);
         GlStateManager.disableBlend();
         this.recoveredField2617
            .fontRendererObj
            .drawStringWithShadow(
               this.recoveredField2616 + "§r",
               var1 - (this.recoveredField2614 + this.recoveredField2620 + this.recoveredField2614) - this.recoveredField2612,
               var2,
               16777215
            );
         this.recoveredField2617
            .fontRendererObj
            .drawStringWithShadow(
               this.recoveredField2621 + "§r",
               var1 - (this.recoveredField2614 + this.recoveredField2620 + this.recoveredField2614) - this.recoveredField2618,
               var2 + ((Boolean)ArmourStatusModule.recoveredField1886.getValue() ? this.recoveredField2613 / 2 : this.recoveredField2613 / 4),
               16777215
            );
      } else {
         ArmourStatusModule.renderItem.renderItemAndEffectIntoGUI(this.recoveredField2619, (int)var1, (int)var2);
         HudUtil.renderItemOverlayIntoGUI(
            this.recoveredField2617.fontRendererObj,
            this.recoveredField2619,
            (int)var1,
            (int)var2,
            ArmourStatusModule.recoveredField1882.method_08908(),
            (Boolean)ArmourStatusModule.recoveredField1883.getValue()
         );
         RenderHelper.disableStandardItemLighting();
         GL11.glDisable(32826);
         GlStateManager.disableBlend();
         this.recoveredField2617
            .fontRendererObj
            .drawStringWithShadow(this.recoveredField2616 + "§r", var1 + this.recoveredField2620 + this.recoveredField2614, var2, 16777215);
         this.recoveredField2617
            .fontRendererObj
            .drawStringWithShadow(
               this.recoveredField2621 + "§r",
               var1 + this.recoveredField2620 + this.recoveredField2614,
               var2 + ((Boolean)ArmourStatusModule.recoveredField1886.getValue() ? this.recoveredField2613 / 2 : this.recoveredField2613 / 4),
               16777215
            );
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public void method_11371() {
      this.recoveredField2613 = (Boolean)ArmourStatusModule.recoveredField1886.getValue()
         ? Math.max(Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT * 2, this.recoveredField2622)
         : Math.max(this.recoveredField2617.fontRendererObj.FONT_HEIGHT, this.recoveredField2622);
      if (this.recoveredField2619 != null) {
         boolean var3 = ArmourStatusModule.recoveredField1890.getValue().equals("ON")
            ? this.recoveredField2619.isItemStackDamageable()
            : this.recoveredField2619.isItemDamaged();
         if ((
               this.recoveredField2615 && (Boolean)ArmourStatusModule.recoveredField1892.getValue()
                  || !this.recoveredField2615 && (Boolean)ArmourStatusModule.recoveredField1885.getValue()
            )
            && var3) {
            int var2 = this.recoveredField2619.getMaxDamage() + 1;
            int var1 = var2 - this.recoveredField2619.getItemDamage();
            if (((String)ArmourStatusModule.recoveredField1887.getValue()).equalsIgnoreCase("value")) {
               this.recoveredField2621 = "§"
                  + ArmourStatusDamageComparable.getDamageColor(
                     ArmourStatusModule.recoveredField1894,
                     ((String)ArmourStatusModule.recoveredField1893.getValue()).equalsIgnoreCase("percent") ? var1 * 100 / var2 : var1
                  )
                  + var1
                  + ((Boolean)ArmourStatusModule.recoveredField1896.getValue() ? "/" + var2 : "");
            } else if (((String)ArmourStatusModule.recoveredField1887.getValue()).equalsIgnoreCase("percent")) {
               this.recoveredField2621 = "§"
                  + ArmourStatusDamageComparable.getDamageColor(
                     ArmourStatusModule.recoveredField1894,
                     ((String)ArmourStatusModule.recoveredField1893.getValue()).equalsIgnoreCase("percent") ? var1 * 100 / var2 : var1
                  )
                  + var1 * 100 / var2
                  + "%";
            }
         }

         this.recoveredField2618 = this.recoveredField2617.fontRendererObj.getStringWidth(HudUtil.method_03744(this.recoveredField2621));
         this.recoveredField2611 = this.recoveredField2614 + this.recoveredField2620 + this.recoveredField2614 + this.recoveredField2618;
         if ((Boolean)ArmourStatusModule.recoveredField1886.getValue()) {
            this.recoveredField2616 = this.recoveredField2619.getDisplayName();
            this.recoveredField2611 = this.recoveredField2614
               + this.recoveredField2620
               + this.recoveredField2614
               + Math.max(this.recoveredField2617.fontRendererObj.getStringWidth(HudUtil.method_03744(this.recoveredField2616)), this.recoveredField2618);
         }

         this.recoveredField2612 = this.recoveredField2617.fontRendererObj.getStringWidth(HudUtil.method_03744(this.recoveredField2616));
      }
   }
}
