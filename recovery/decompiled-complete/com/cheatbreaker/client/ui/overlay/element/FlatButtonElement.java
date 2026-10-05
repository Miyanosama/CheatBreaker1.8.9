package com.cheatbreaker.client.ui.overlay.element;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.realms.RealmsEditBox;
import net.optifine.model.BlockModelUtils;
import recovered.unidentified.UnidentifiedClass3906;

public class FlatButtonElement extends AbstractElement {
   public String field_0002;
   public GuiMerchant field_0004;
   public UnidentifiedClass3906 field_0001;
   public RealmsEditBox field_0003;
   public BlockModelUtils field_0000;

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      return !var4 ? false : false;
   }

   public void method_23820(String var1) {
      this.field_0002 = var1;
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      this.method_23821(this.field_0002, var1, var2, var3);
   }

   public FlatButtonElement(String var1) {
      this.field_0002 = var1;
   }

   public void method_23821(String var1, float var2, float var3, boolean var4) {
      Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, var4 && this.a_(var2, var3) ? -16747106 : -13158601);
      float var10002 = this.x + this.width / 2.0F;
      CheatBreaker.getInstance().playRegular14px.drawCenteredString(var1, var10002, this.y + this.height / 2.0F - 5.0F, -1);
   }

   public String method_23819() {
      return this.field_0002;
   }
}
