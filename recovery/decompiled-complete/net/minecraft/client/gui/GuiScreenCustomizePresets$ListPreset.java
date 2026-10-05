package net.minecraft.client.gui;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.crash.CrashReport$7;
import net.minecraft.util.ResourceLocation;
import org.java_websocket.handshake.HandshakeImpl1Client;

public class GuiScreenCustomizePresets$ListPreset extends GuiSlot {
   public int field_178053_u;
   public CrashReport$7 field_0000;
   public HandshakeImpl1Client field_0002;

   @Override
   public int getSize() {
      return GuiScreenCustomizePresets.access$000().size();
   }

   @Override
   public boolean isSelected(int var1) {
      return var1 == this.field_178053_u;
   }

   @Override
   public void drawBackground() {
   }

   public GuiScreenCustomizePresets$ListPreset(GuiScreenCustomizePresets var1) {
      this.field_178052_v = var1;
      super(var1.j, var1.l, var1.m, 80, var1.m - 32, 38);
      this.field_178053_u = -1;
   }

   @Override
   public void elementClicked(int var1, boolean var2, int var3, int var4) {
      this.field_178053_u = var1;
      this.field_178052_v.func_175304_a();
      GuiScreenCustomizePresets.access$200(this.field_178052_v)
         .setText(
            ((GuiScreenCustomizePresets$Info)GuiScreenCustomizePresets.access$000()
                  .get(GuiScreenCustomizePresets.access$100(this.field_178052_v).field_178053_u))
               .field_178954_c
               .toString()
         );
   }

   @Override
   public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
      GuiScreenCustomizePresets$Info var7 = (GuiScreenCustomizePresets$Info)GuiScreenCustomizePresets.access$000().get(var1);
      this.func_178051_a(var2, var3, var7.field_178953_b);
      this.field_178052_v.q.drawString(var7.field_178955_a, var2 + 32 + 10, var3 + 14, 16777215);
   }

   public void func_178051_a(int var1, int var2, ResourceLocation var3) {
      int var4 = var1 + 5;
      this.field_178052_v.drawHorizontalLine(var4 - 1, var4 + 32, var2 - 1, -2039584);
      this.field_178052_v.drawHorizontalLine(var4 - 1, var4 + 32, var2 + 32, -6250336);
      this.field_178052_v.drawVerticalLine(var4 - 1, var2 - 1, var2 + 32, -2039584);
      this.field_178052_v.drawVerticalLine(var4 + 32, var2 - 1, var2 + 32, -6250336);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.a.getTextureManager().bindTexture(var3);
      byte var5 = 32;
      byte var6 = 32;
      Tessellator var7 = Tessellator.getInstance();
      WorldRenderer var8 = var7.getWorldRenderer();
      var8.begin(7, DefaultVertexFormats.POSITION_TEX);
      var8.pos(var4 + 0, var2 + 32, 0.0).tex(0.0, 1.0).endVertex();
      var8.pos(var4 + 32, var2 + 32, 0.0).tex(1.0, 1.0).endVertex();
      var8.pos(var4 + 32, var2 + 0, 0.0).tex(1.0, 0.0).endVertex();
      var8.pos(var4 + 0, var2 + 0, 0.0).tex(0.0, 0.0).endVertex();
      var7.draw();
   }
}
