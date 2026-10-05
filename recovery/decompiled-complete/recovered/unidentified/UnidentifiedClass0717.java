package recovered.unidentified;

import com.cheatbreaker.client.ui.mainmenu.MainMenuBase;
import com.cheatbreaker.client.ui.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$ZDoubleRoomFitHelper;
import org.lwjgl.opengl.GL11;

public class UnidentifiedClass0717 extends GuiMainMenu {
   public UnidentifiedClass5030 field_0003;
   public double field_0005;
   public float field_0002 = 0.0F;
   public ResourceLocation field_0004 = new ResourceLocation("client/logo_outer.png");
   public ResourceLocation field_0000 = new ResourceLocation("client/logo_inner.png");
   public StructureOceanMonumentPieces$ZDoubleRoomFitHelper field_0001;

   @Override
   public void updateScreen() {
      super.updateScreen();
      this.field_0005 += 0.06283185307179587;
      this.field_0002 = (float)((Math.sin(this.field_0005) / 2.0 + 0.5) * 180.0);
   }

   @Override
   public void keyTyped(char var1, int var2) {
      if (var2 != 1 || !(Minecraft.getMinecraft().currentScreen instanceof UnidentifiedClass0717)) {
         super.keyTyped(var1, var2);
      }
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      super.drawScreen(var1, var2, var3);
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, 20.0F, 0.0F);
      Gui.drawRect(this.l / 2.0F - 71.0F, this.m / 4.0F - 40.0F, this.l / 2.0F + 71.0F, this.m / 4.0F + 110.0F, -1342177281);
      Gui.drawRect(this.l / 2.0F - 73.0F, this.m / 4.0F - 42.0F, this.l / 2.0F - 71.0F, this.m / 4.0F + 112.0F, 1073741823);
      Gui.drawRect(this.l / 2.0F + 71.0F, this.m / 4.0F - 42.0F, this.l / 2.0F + 73.0F, this.m / 4.0F + 112.0F, 1073741823);
      Gui.drawRect(this.l / 2.0F - 71.0F, this.m / 4.0F + 110.0F, this.l / 2.0F + 71.0F, this.m / 4.0F + 112.0F, 1073741823);
      Gui.drawRect(this.l / 2.0F - 71.0F, this.m / 4.0F - 42.0F, this.l / 2.0F + 71.0F, this.m / 4.0F - 40.0F, 1073741823);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.9F);
      GL11.glPushMatrix();
      float var4 = 0.65F;
      GL11.glScalef(var4, var4, var4);
      GL11.glPushMatrix();
      GL11.glTranslatef((this.l / 2 - 40.0F * var4) / var4, (this.m / 4 - 40.0F * var4) / var4, 0.0F);
      byte var5 = 40;
      GL11.glTranslatef(var5, var5, var5);
      GL11.glRotatef(this.field_0002, 0.0F, 0.0F, 1.0F);
      GL11.glTranslatef(-var5, -var5, -var5);
      RenderUtil.drawIcon(this.field_0004, var5, 0.0F, 0.0F);
      GL11.glPopMatrix();
      RenderUtil.drawIcon(this.field_0000, 40.0F, (this.l / 2 - 40.0F * var4) / var4, (this.m / 4 - 39.0F * var4) / var4);
      GL11.glPopMatrix();
      GL11.glPopMatrix();
      super.method_22417(var1, var2);
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      if (var1 >= 7 && var1 <= 39 && var2 >= 5 && var2 <= 20) {
         MainMenuBase.method_11936();
      }
   }

   @Override
   public void initGui() {
      int var1 = this.m / 4 + 48;
      this.method_22416(var1, 24);
      super.initGui();
   }
}
