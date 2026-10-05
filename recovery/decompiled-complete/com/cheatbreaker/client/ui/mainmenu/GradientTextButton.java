package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.util.RenderUtil;
import javax.vecmath.Color4f;
import net.minecraft.client.gui.stream.GuiIngestServers$ServerList;
import net.minecraft.command.CommandGive;

public class GradientTextButton extends AbstractElement {
   public ColorFade field_0003;
   public Color4f field_0006;
   public ColorFade field_0002;
   public int[] field_0005;
   public CommandGive field_0000;
   public ColorFade field_0001;
   public String field_0007;
   public GuiIngestServers$ServerList field_0004;

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      return !var4 ? false : false;
   }

   public String method_25134() {
      return this.field_0007;
   }

   public void method_25132() {
      this.method_25136(new int[]{-11493284, -11493284, -10176146, -10176146, -11164318, -11164318});
   }

   public GradientTextButton(String var1) {
      this.field_0007 = var1;
      this.field_0003 = new ColorFade(-14277082, -11493284);
      this.field_0001 = new ColorFade(-13487566, -10176146);
      this.field_0002 = new ColorFade(-14013910, -11164318);
   }

   public void method_25133() {
      this.method_25136(new int[]{-11119018, -11493284, -10329502, -10176146, -11579569, -11164318});
   }

   public void method_25138() {
      this.method_25136(new int[]{-14277082, -11493284, -13487566, -10176146, -14013910, -11164318});
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      boolean var4 = var3 && this.a_(var1, var2);
      if (this.field_0005 != null && this.field_0003.method_21210()) {
         this.field_0003.method_25067(this.field_0005[0]);
         this.field_0003.method_25068(this.field_0005[1]);
         this.field_0001.method_25067(this.field_0005[2]);
         this.field_0001.method_25068(this.field_0005[3]);
         this.field_0002.method_25067(this.field_0005[4]);
         this.field_0002.method_25068(this.field_0005[5]);
         this.field_0005 = null;
      }

      RenderUtil.method_22057(
         this.x,
         this.y,
         this.x + this.width,
         this.y + this.height,
         this.field_0003.method_25066(var4).getRGB(),
         this.field_0001.method_25066(var4).getRGB(),
         this.field_0002.method_25066(var4).getRGB()
      );
      float var10002 = this.x + this.width / 2.0F;
      float var10003 = this.y + 2.0F;
      CheatBreaker.getInstance().robotoRegular13px.drawCenteredString(this.field_0007, var10002, var10003, -1);
   }

   public void method_25135(String var1) {
      this.field_0007 = var1;
   }

   public void method_25137(float var1, float var2, boolean var3) {
      this.handleElementDraw(var1, var2, var3);
   }

   public void method_25136(int[] var1) {
      this.field_0005 = var1;
   }
}
