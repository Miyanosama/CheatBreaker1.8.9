package net.minecraft.realms;

import io.netty.channel.socket.nio.ProtocolFamilyConverter;
import net.minecraft.block.BlockSkull;
import net.minecraft.client.gui.GuiKeyBindingList$CategoryEntry;
import recovered.unidentified.UnidentifiedClass3707;

public class RealmsSimpleScrolledSelectionList {
   public UnidentifiedClass3707 field_0001;
   public BlockSkull field_0003;
   public GuiKeyBindingList$CategoryEntry field_0000;
   public ProtocolFamilyConverter field_0002;

   public int method_01316() {
      return this.field_0001.method_22572();
   }

   public int method_01325() {
      return this.field_0001.method_22573();
   }

   public void selectItem(int var1, boolean var2, int var3, int var4) {
   }

   public int method_01315() {
      return this.field_0001.method_22572() / 2 + 124;
   }

   public int method_01324() {
      return this.field_0001.method_22576();
   }

   public int method_01312() {
      return this.field_0001.getAmountScrolled();
   }

   public boolean isSelectedItem(int var1) {
      return false;
   }

   public void renderBackground() {
   }

   public void renderItem(int var1, int var2, int var3, int var4, Tezzelator var5, int var6, int var7) {
   }

   public RealmsSimpleScrolledSelectionList(int var1, int var2, int var3, int var4, int var5) {
      this.field_0001 = new UnidentifiedClass3707(this, var1, var2, var3, var4, var5);
   }

   public void renderList(int var1, int var2, int var3, int var4) {
   }

   public int method_01327() {
      return 0;
   }

   public void renderItem(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.renderItem(var1, var2, var3, var4, Tezzelator.instance, var5, var6);
   }

   public void method_01323() {
      this.field_0001.handleMouseInput();
   }

   public void method_01318(int var1, int var2, float var3) {
      this.field_0001.a(var1, var2, var3);
   }

   public void method_01326(int var1) {
      this.field_0001.scrollBy(var1);
   }

   public int method_01314() {
      return 0;
   }
}
