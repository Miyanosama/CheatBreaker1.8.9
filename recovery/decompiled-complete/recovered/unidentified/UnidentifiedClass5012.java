package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.util.RenderUtil;
import io.netty.channel.DefaultChannelPipeline$TailContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.apache.log4j.pattern.PatternParser$ReadOnlyMap;
import org.lwjgl.opengl.GL11;

public class UnidentifiedClass5012 extends GuiButton {
   public ResourceLocation field_0001;
   public PatternParser$ReadOnlyMap field_0003;
   public boolean field_0000 = true;
   public DefaultChannelPipeline$TailContext field_0002;

   public UnidentifiedClass5012(int var1, int var2, int var3, int var4, int var5, String var6) {
      super(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public void drawButton(Minecraft var1, int var2, int var3) {
      if (this.m) {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.hovered = var2 >= this.h && var3 >= this.i && var2 < this.h + this.f && var3 < this.i + this.height;
         int var4 = this.getHoverState(this.hovered);
         if (this.field_0000) {
            Gui.a(this.h, this.i, this.h + this.f, this.i + this.height, this.hovered ? -15395563 : -14540254);
         }

         this.mouseDragged(var1, var2, var3);
         int var5 = -3092272;
         if (!this.l) {
            var5 = -986896;
         } else if (this.hovered) {
            var5 = -1;
         }

         float var10002 = this.h + this.f / 2.0F;
         float var10003 = this.i + this.height / 2.0F - (this.field_0000 ? 6 : 5);
         CheatBreaker.getInstance().field_0036.drawCenteredString(this.j, var10002, var10003, var5);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.3F);
         RenderUtil.drawIcon(this.field_0001, 7.0F, this.h + this.f - 20, this.i + 5);
      }
   }

   public UnidentifiedClass5012(int var1, ResourceLocation var2, int var3, int var4, int var5, int var6, String var7, boolean var8) {
      this(var1, var3, var4, var5, var6, var7);
      this.field_0000 = var8;
      this.field_0001 = var2;
   }
}
