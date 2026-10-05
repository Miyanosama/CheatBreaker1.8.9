package com.cheatbreaker.client.ui.element.type;

import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import io.netty.channel.epoll.EpollEventLoop;
import net.minecraft.client.gui.Gui;
import recovered.unidentified.UnidentifiedClass5100;

public class ColorPickerColorElement extends AbstractModulesGuiElement {
   public float field_0001;
   public EpollEventLoop field_0002;
   public int color;

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      Gui.method_00887(
         this.x,
         this.y,
         this.x + this.width,
         this.y + this.height,
         1.0F,
         GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0030 : UnidentifiedClass5100.field_0029,
         0
      );
      Gui.a(this.x + 1, this.y + 1, this.x + this.width - 1, this.y + this.height - 1, this.color | 0xFF000000);
   }

   public ColorPickerColorElement(float var1, int var2, float var3) {
      super(var1);
      this.color = var2;
      this.field_0001 = var3;
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
   }
}
