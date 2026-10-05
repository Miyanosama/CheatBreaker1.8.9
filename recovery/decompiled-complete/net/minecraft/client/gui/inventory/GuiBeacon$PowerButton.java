package net.minecraft.client.gui.inventory;

import io.netty.channel.ChannelPromiseNotifier;
import net.minecraft.block.BlockRailBase$EnumRailDirection;
import net.minecraft.client.gui.GuiErrorScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.potion.Potion;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;

public class GuiBeacon$PowerButton extends GuiBeacon$Button {
   public WorldGenBlockBlob field_0001;
   public int field_146149_p;
   public int field_146148_q;
   public BlockRailBase$EnumRailDirection field_0004;
   public ChannelPromiseNotifier field_0005;
   public GuiErrorScreen field_0003;
   public EnumDyeColor field_0007;

   public GuiBeacon$PowerButton(GuiBeacon var1, int var2, int var3, int var4, int var5, int var6) {
      this.field_146150_o = var1;
      super(
         var2,
         var3,
         var4,
         GuiContainer.inventoryBackground,
         0 + Potion.potionTypes[var5].getStatusIconIndex() % 8 * 18,
         198 + Potion.potionTypes[var5].getStatusIconIndex() / 8 * 18
      );
      this.field_146149_p = var5;
      this.field_146148_q = var6;
   }

   @Override
   public void drawButtonForegroundLayer(int var1, int var2) {
      String var3 = I18n.format(Potion.potionTypes[this.field_146149_p].getName());
      if (this.field_146148_q >= 3 && this.field_146149_p != Potion.regeneration.id) {
         var3 = var3 + " II";
      }

      GuiBeacon.access$100(this.field_146150_o, var3, var1, var2);
   }
}
