package com.cheatbreaker.client.ui.mainmenu.element;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import io.netty.bootstrap.ServerBootstrap;
import net.minecraft.entity.item.EntityMinecartHopper;

public class TextButtonElement extends AbstractElement {
   public ServerBootstrap field_0001;
   public ColorFade field_0003;
   public String field_0000;
   public EntityMinecartHopper field_0002;

   public TextButtonElement(String var1) {
      this.field_0000 = var1;
      this.field_0003 = new ColorFade(-1879048193, -1);
   }

   public ColorFade method_26722() {
      return this.field_0003;
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      float var10002 = this.x + 6.0F;
      float var10003 = this.y + 6.0F;
      CheatBreaker.getInstance()
         .robotoBold14px
         .drawString(this.field_0000, var10002, var10003, this.field_0003.method_25066(this.a_(var1, var2) && var3).getRGB());
   }

   public String method_26721() {
      return this.field_0000;
   }
}
