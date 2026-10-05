package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.item.EntityXPOrb;
import org.lwjgl.opengl.GL11;

public class UnidentifiedClass4376 extends GuiButton {
   public EntityXPOrb field_0000;
   public boolean field_0001 = true;

   public UnidentifiedClass4376(int var1, int var2, int var3, int var4, int var5, String var6, boolean var7) {
      this(var1, var2, var3, var4, var5, var6);
      this.field_0001 = var7;
   }

   @Override
   public void drawButton(Minecraft var1, int var2, int var3) {
      if (this.m) {
         FontRenderer var4 = var1.fontRendererObj;
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.hovered = var2 >= this.h && var3 >= this.i && var2 < this.h + this.f && var3 < this.i + this.height;
         int var5 = this.getHoverState(this.hovered);
         if (this.field_0001) {
            Gui.a(this.h, this.i, this.h + this.f, this.i + this.height, this.hovered ? -15395563 : -14540254);
         }

         this.mouseDragged(var1, var2, var3);
         int var6 = -3092272;
         if (!this.l) {
            var6 = -986896;
         } else if (this.hovered) {
            var6 = -1;
         }

         float var10002 = this.h + this.f / 2.0F;
         float var10003 = this.i + this.height / 2.0F - (this.field_0001 ? 5 : 4);
         CheatBreaker.getInstance().field_0036.drawCenteredString(this.j, var10002, var10003, var6);
      }
   }

   public UnidentifiedClass4376(int var1, int var2, int var3, int var4, int var5, String var6) {
      super(var1, var2, var3, var4, var5, var6);
   }
}
