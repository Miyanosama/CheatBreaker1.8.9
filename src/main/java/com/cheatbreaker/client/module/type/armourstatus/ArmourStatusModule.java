package com.cheatbreaker.client.module.type.armourstatus;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.module.CBAnchorHelper;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.module.CBPositionEnum;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.ui.module.ModulePlacementGui;
import com.cheatbreaker.client.event.type.HudPreviewDrawEvent;

public class ArmourStatusModule extends AbstractModule {
   public static Setting equippedItem;
   public Setting recoveredField1881;
   public static RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
   public static Setting recoveredField1882;
   public static Setting recoveredField1883;
   public static Setting recoveredField1884;
   public static Setting recoveredField1885;
   public static Setting recoveredField1886;
   public static List<ArmourStatusDamageComparable> recoveredField1894 = new ArrayList<>();
   public static Setting recoveredField1887;
   public Setting recoveredField1888;
   public static ScaledResolution recoveredField1889;
   public static Setting recoveredField1890;
   public Setting recoveredField1891;
   public static Setting recoveredField1892;
   public static Setting recoveredField1893;
   public static List<ArmourStatusItem> items = new ArrayList<>();
   public static Setting recoveredField1895;
   public static Setting recoveredField1896;

   public void method_05769(GuiDrawEvent var1) {
      if (this.method_28866() && (!this.recoveredField3905 || this.minecraft.currentScreen instanceof CBModulesGui)) {
         if (!(this.minecraft.currentScreen instanceof CBModulesGui)
            && !(this.minecraft.currentScreen instanceof ModulePlacementGui)
            && (!(this.minecraft.currentScreen instanceof GuiChat) || (Boolean)recoveredField1884.getValue())) {
            this.updateItems(this.minecraft);
            if (!items.isEmpty()) {
               GL11.glPushMatrix();
               GlStateManager.enableBlend();
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               recoveredField1889 = var1.getResolution();
               this.scaleAndTranslate(recoveredField1889);
               this.updateDimensions(items);
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               GlStateManager.disableBlend();
               GL11.glPopMatrix();
            }
         }
      }
   }

   public void updateDimensions(List<ArmourStatusItem> var1) {
      if (var1.size() > 0) {
         int var2 = (Boolean)recoveredField1886.getValue() ? 18 : 16;
         if (((String)recoveredField1895.getValue()).equalsIgnoreCase("vertical")) {
            int var3 = 0;
            int var4 = 0;
            boolean var5 = CBAnchorHelper.getHorizontalPositionEnum(this.getGuiAnchor()) == CBPositionEnum.RIGHT;

            for (ArmourStatusItem var7 : var1) {
               var7.method_11373(var5 ? this.recoveredField3889 : 0.0F, var3);
               var3 += var2;
               if (var7.method_11372() > var4) {
                  var4 = var7.method_11372();
               }
            }

            this.recoveredField3894 = var3;
            this.recoveredField3889 = var4;
         } else if (((String)recoveredField1895.getValue()).equalsIgnoreCase("horizontal")) {
            int var8 = 0;
            int var9 = 0;
            boolean var10 = CBAnchorHelper.getHorizontalPositionEnum(this.getGuiAnchor()) == CBPositionEnum.RIGHT;

            for (ArmourStatusItem var12 : var1) {
               if (var10) {
                  var8 += var12.method_11372();
               }

               var12.method_11373(var8, 0.0F);
               if (!var10) {
                  var8 += var12.method_11372();
               }

               if (var12.method_11374() > var9) {
                  var9 += var12.method_11374();
               }
            }

            this.recoveredField3894 = var9;
            this.recoveredField3889 = var8;
         }
      }
   }

   public ArmourStatusModule() {
      super("Armor Status");
      this.setDefaultAnchor(CBGuiAnchor.RIGHT_BOTTOM);
      this.setDefaultState(false);
      this.recoveredField1891 = new Setting(this, "label").setValue("General Options").method_08914(SettingsDetailLevel.SIMPLE);
      recoveredField1895 = new Setting(this, "List Mode")
         .setValue("Vertical")
         .acceptedValues("Vertical", "Horizontal")
         .method_08914(SettingsDetailLevel.SIMPLE);
      recoveredField1886 = new Setting(this, "Item Name").setValue(false).method_08914(SettingsDetailLevel.SIMPLE);
      recoveredField1883 = new Setting(this, "Item Count")
         .setValue(true)
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> (Boolean)equippedItem.getValue());
      equippedItem = new Setting(this, "Equipped Item").setValue(true).method_08914(SettingsDetailLevel.SIMPLE);
      recoveredField1884 = new Setting(this, "Show While Typing").setValue(false).method_08914(SettingsDetailLevel.ADVANCED);
      this.recoveredField1881 = new Setting(this, "label").setValue("Damage Options").method_08914(SettingsDetailLevel.SIMPLE);
      recoveredField1882 = new Setting(this, "Damage Overlay").setValue(true).method_08914(SettingsDetailLevel.MEDIUM);
      recoveredField1890 = new Setting(this, "Show Damage Amount")
         .setValue("ON")
         .acceptedValues("ON", "If Damaged")
         .method_08914(SettingsDetailLevel.SIMPLE);
      recoveredField1885 = new Setting(this, "Show Item Damage")
         .setValue(true)
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> (Boolean)equippedItem.getValue());
      recoveredField1892 = new Setting(this, "Show Armor Damage").setValue(true).method_08914(SettingsDetailLevel.SIMPLE);
      recoveredField1896 = new Setting(this, "Show Max Damage")
         .setValue(false)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)recoveredField1885.getValue() || (Boolean)recoveredField1892.getValue());
      this.recoveredField1888 = new Setting(this, "label")
         .setValue("Damage Display")
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> (Boolean)equippedItem.getValue() && (Boolean)recoveredField1885.getValue() || (Boolean)recoveredField1892.getValue());
      recoveredField1887 = new Setting(this, "Damage Display Type")
         .setValue("Value")
         .acceptedValues("Value", "Percent", "None")
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> (Boolean)equippedItem.getValue() && (Boolean)recoveredField1885.getValue() || (Boolean)recoveredField1892.getValue());
      recoveredField1893 = new Setting(this, "Damage Threshold Type")
         .setValue("Percent")
         .acceptedValues("Percent", "Value")
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> (Boolean)equippedItem.getValue() && (Boolean)recoveredField1885.getValue() || (Boolean)recoveredField1892.getValue());
      recoveredField1894.add(new ArmourStatusDamageComparable(10, "4"));
      recoveredField1894.add(new ArmourStatusDamageComparable(25, "c"));
      recoveredField1894.add(new ArmourStatusDamageComparable(40, "6"));
      recoveredField1894.add(new ArmourStatusDamageComparable(60, "e"));
      recoveredField1894.add(new ArmourStatusDamageComparable(80, "7"));
      recoveredField1894.add(new ArmourStatusDamageComparable(100, "f"));
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/diamond_chestplate.png"), 34, 34);
      this.method_28821("Displays your current armor and holding item durability.");
      this.method_28829("bspkrs", "jadedcat");
      this.method_28820(HudPreviewDrawEvent.class, this::method_05767);
      this.method_28820(GuiDrawEvent.class, this::method_05769);
   }

   public void method_05767(HudPreviewDrawEvent var1) {
      ArrayList var2 = new ArrayList();
      if (this.method_28866()) {
         for (int var3 = 3; var3 >= 0; var3--) {
            ItemStack var4 = this.minecraft.thePlayer.bi.armorInventory[var3];
            if (var4 != null) {
               var2.add(new ArmourStatusItem(var4, 16, 16, 2, var3 > -1));
            }
         }

         if (var2.isEmpty()) {
            var2.add(new ArmourStatusItem(new ItemStack(Item.getItemById(310)), 16, 16, 2, true));
            var2.add(new ArmourStatusItem(new ItemStack(Item.getItemById(311)), 16, 16, 2, true));
            var2.add(new ArmourStatusItem(new ItemStack(Item.getItemById(312)), 16, 16, 2, true));
            var2.add(new ArmourStatusItem(new ItemStack(Item.getItemById(313)), 16, 16, 2, true));
         }

         if ((Boolean)equippedItem.getValue() && this.minecraft.thePlayer.getCurrentEquippedItem() != null) {
            var2.add(new ArmourStatusItem(this.minecraft.thePlayer.getCurrentEquippedItem(), 16, 16, 2, false));
         } else if ((Boolean)equippedItem.getValue()) {
            var2.add(new ArmourStatusItem(new ItemStack(Item.getItemById(276)), 16, 16, 2, false));
         }

         GL11.glPushMatrix();
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         recoveredField1889 = var1.method_01054();
         this.scaleAndTranslate(recoveredField1889);
         this.updateDimensions(var2);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glPopMatrix();
      }
   }

   public void updateItems(Minecraft var1) {
      items.clear();

      for (int var2 = 3; var2 >= -1; var2--) {
         ItemStack var3 = null;
         if (var2 == -1 && (Boolean)equippedItem.getValue()) {
            var3 = var1.thePlayer.getCurrentEquippedItem();
         } else if (var2 != -1) {
            var3 = var1.thePlayer.bi.armorInventory[var2];
         }

         if (var3 != null) {
            items.add(new ArmourStatusItem(var3, 16, 16, 2, var2 > -1));
         }
      }
   }
}
