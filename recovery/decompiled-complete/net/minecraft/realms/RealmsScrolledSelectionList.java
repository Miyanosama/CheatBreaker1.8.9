package net.minecraft.realms;

import io.netty.handler.codec.socks.UnknownSocksRequest;
import net.minecraft.block.BlockTallGrass$EnumType;
import net.minecraft.client.gui.GuiSlotRealmsProxy;
import net.optifine.entity.model.ModelAdapterIronGolem;

public class RealmsScrolledSelectionList {
   public ModelAdapterIronGolem field_0001;
   public GuiSlotRealmsProxy field_0003;
   public UnknownSocksRequest field_0000;
   public BlockTallGrass$EnumType field_0002;

   public int method_06327() {
      return this.field_0003.getAmountScrolled();
   }

   public int method_06336() {
      return this.field_0003.getMouseX();
   }

   public void selectItem(int var1, boolean var2, int var3, int var4) {
   }

   public int method_06326() {
      return this.field_0003.method_29928() / 2 + 124;
   }

   public void renderBackground() {
   }

   public void method_06328(int var1) {
      this.field_0003.scrollBy(var1);
   }

   public void renderList(int var1, int var2, int var3, int var4) {
   }

   public RealmsScrolledSelectionList(int var1, int var2, int var3, int var4, int var5) {
      this.field_0003 = new GuiSlotRealmsProxy(this, var1, var2, var3, var4, var5);
   }

   public int method_06323() {
      return 0;
   }

   public int method_06324() {
      return 0;
   }

   public boolean isSelectedItem(int var1) {
      return false;
   }

   public void method_06338() {
      this.field_0003.handleMouseInput();
   }

   public void method_06329(int var1, int var2, float var3) {
      this.field_0003.a(var1, var2, var3);
   }

   public void renderItem(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.renderItem(var1, var2, var3, var4, Tezzelator.instance, var5, var6);
   }

   public void renderItem(int var1, int var2, int var3, int var4, Tezzelator var5, int var6, int var7) {
   }

   public int method_06334() {
      return this.field_0003.getMouseY();
   }

   public int method_06325() {
      return this.field_0003.method_29928();
   }
}
