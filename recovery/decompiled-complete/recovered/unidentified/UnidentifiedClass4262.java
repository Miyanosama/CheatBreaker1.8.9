package recovered.unidentified;

import com.cheatbreaker.client.ui.AbstractGui;
import com.cheatbreaker.client.ui.fading.CosineFade;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.CBFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.MapGenNetherBridge$Start;
import net.minecraft.world.gen.structure.StructureVillagePieces$Torch;
import org.lwjgl.opengl.GL11;

public class UnidentifiedClass4262 extends AbstractGui {
   public String field_0002;
   public int field_0011;
   public CosineFade field_0001;
   public ResourceLocation field_0005;
   public CBFontRenderer field_0007;
   public MapGenNetherBridge$Start field_0004;
   public int field_0008;
   public ResourceLocation field_0010;
   public Framebuffer field_0000;
   public StructureVillagePieces$Torch field_0003;
   public ResourceLocation field_0006 = new ResourceLocation("client/logo_255_outer.png");
   public InventoryHelper field_0009;

   public void method_25827(float var1, float var2) {
      float var3 = 27.0F;
      float var4 = var1 / 2.0F - var3;
      float var5 = var2 / 2.0F - var3;
      GL11.glPushMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glTranslatef(var4, var5, 1.0F);
      GL11.glTranslatef(var3, var3, var3);
      GL11.glRotatef(180.0F * this.field_0001.method_21227(), 0.0F, 0.0F, 1.0F);
      GL11.glTranslatef(-var3, -var3, -var3);
      RenderUtil.method_22063(this.field_0006, var3, 0.0F, 0.0F);
      GL11.glPopMatrix();
      RenderUtil.method_22063(this.field_0010, var3, var4, var5);
   }

   public UnidentifiedClass4262(int var1) {
      this.field_0010 = new ResourceLocation("client/logo_108_inner.png");
      this.field_0001 = new CosineFade(1812746152L & -3377348267361693774L);
      this.field_0005 = new ResourceLocation("client/logo_108.png");
      this.field_0007 = new CBFontRenderer(new ResourceLocation("client/font/Ubuntu-M.ttf"), 14.0F);
      this.field_0008 = var1;
      this.j = Minecraft.getMinecraft();
      this.resolution = new ScaledResolution(this.j);
      this.field_0000 = new Framebuffer(
         this.resolution.getScaledWidth() * this.resolution.getScaleFactor(), this.resolution.getScaledHeight() * this.resolution.getScaleFactor(), true
      );
   }

   public void method_25824(String var1) {
      this.field_0002 = var1;
      this.field_0011++;
      if (this.field_0008 <= this.field_0011) {
         this.field_0008 = this.field_0011 + 1;
      }

      this.drawMenu(0.0F, 0.0F);
   }

   public void method_25823(float var1, float var2) {
      float var3 = 27.0F;
      float var4 = var1 / 2.0F - var3;
      float var5 = var2 / 2.0F - var3;
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      RenderUtil.method_22063(this.field_0005, var3, var4, var5);
   }

   @Override
   public void drawMenu(float var1, float var2) {
      this.method_25826();
      float var3 = this.getScaleFactor();
      float var4 = this.resolution.getScaledWidth() / var3;
      float var5 = this.resolution.getScaledHeight() / var3;
      GL11.glScaled(var3, var3, var3);
      Gui.drawRect(0.0F, 0.0F, var4, var5, -1);
      this.method_25823(var4, var5);
      float var6 = 160.0F;
      float var7 = var4 / 2.0F - 80.0F;
      float var8 = var5 - 40.0F;
      RenderUtil.method_22054(var7, var8, var7 + var6, var8 + 10.0F, 8.0, -657931);
      float var9 = var6 * ((float)this.field_0011 / this.field_0008);
      if (this.field_0002 != null) {
         this.field_0007.drawCenteredString(this.field_0002.toLowerCase(), var4 / 2.0F, var8 - 11.0F, -3092272);
      }

      RenderUtil.method_22054(var7, var8, var7 + Math.max(var9, 8.0F), var8 + 10.0F, 8.0, -2473389);
      this.method_25822();
      this.j.updateDisplay();
   }

   public void method_25825() {
      this.field_0011++;
      if (this.field_0008 <= this.field_0011) {
         this.field_0008 = this.field_0011 + 1;
      }

      this.drawMenu(0.0F, 0.0F);
   }

   @Override
   public void onMouseClicked(float var1, float var2, int var3) {
   }

   public void method_25826() {
      try {
         this.field_0000.bindFramebuffer(false);
         GL11.glMatrixMode(5889);
         GL11.glLoadIdentity();
         GL11.glOrtho(0.0, this.resolution.getScaledWidth(), this.resolution.getScaledHeight(), 0.0, 1000.0, 3000.0);
         GL11.glMatrixMode(5888);
         GL11.glLoadIdentity();
         GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
         GL11.glDisable(2896);
         GL11.glDisable(2912);
         GL11.glDisable(2929);
         GL11.glEnable(3553);
         GL11.glEnable(3008);
      } catch (RuntimeException var2) {
      }
   }

   public void method_25822() {
      int var1 = this.resolution.getScaleFactor();
      GL11.glDisable(2896);
      GL11.glDisable(2912);
      this.field_0000.unbindFramebuffer();
      this.field_0000.framebufferRender(this.resolution.getScaledWidth() * var1, this.resolution.getScaledHeight() * var1);
      GL11.glAlphaFunc(516, 0.1F);
      GL11.glFlush();
   }

   @Override
   public void onMouseReleased(float var1, float var2, int var3) {
   }
}
