package net.minecraft.client.gui;

import com.cheatbreaker.client.util.voicechat.VoiceChannel;
import com.google.common.collect.Lists;
import io.netty.util.concurrent.SingleThreadEventExecutor;
import java.util.List;
import javazoom.jl.converter.WaveFile$WaveFormat_Chunk;
import net.minecraft.block.BlockTripWireHook$1;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.stream.MetadataPlayerDeath;
import net.optifine.shaders.uniform.ShaderParameterFloat$1;

public class GuiLabel extends Gui {
   public WaveFile$WaveFormat_Chunk field_0009;
   public int field_146166_p;
   public boolean centered;
   public FontRenderer fontRenderer;
   public int field_175204_i;
   public BlockTripWireHook$1 field_0004;
   public VoiceChannel field_0019;
   public boolean visible;
   public int field_146163_s;
   public int field_146165_q;
   public int field_146169_o;
   public int field_146168_n;
   public int field_146174_h;
   public int field_146162_g;
   public SingleThreadEventExecutor field_0014;
   public MetadataPlayerDeath field_0017;
   public List<String> field_146173_k;
   public boolean labelBgEnabled;
   public int field_146161_f;
   public ShaderParameterFloat$1 field_0016;
   public int field_146167_a = 200;

   public void func_175202_a(String var1) {
      this.field_146173_k.add(I18n.format(var1));
   }

   public GuiLabel(FontRenderer var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      this.field_146161_f = 20;
      this.visible = true;
      this.fontRenderer = var1;
      this.field_175204_i = var2;
      this.field_146162_g = var3;
      this.field_146174_h = var4;
      this.field_146167_a = var5;
      this.field_146161_f = var6;
      this.field_146173_k = Lists.newArrayList();
      this.centered = false;
      this.labelBgEnabled = false;
      this.field_146168_n = var7;
      this.field_146169_o = -1;
      this.field_146166_p = -1;
      this.field_146165_q = -1;
      this.field_146163_s = 0;
   }

   public GuiLabel setCentered() {
      this.centered = true;
      return this;
   }

   public void drawLabelBackground(Minecraft var1, int var2, int var3) {
      if (this.labelBgEnabled) {
         int var4 = this.field_146167_a + this.field_146163_s * 2;
         int var5 = this.field_146161_f + this.field_146163_s * 2;
         int var6 = this.field_146162_g - this.field_146163_s;
         int var7 = this.field_146174_h - this.field_146163_s;
         a(var6, var7, var6 + var4, var7 + var5, this.field_146169_o);
         this.drawHorizontalLine(var6, var6 + var4, var7, this.field_146166_p);
         this.drawHorizontalLine(var6, var6 + var4, var7 + var5, this.field_146165_q);
         this.drawVerticalLine(var6, var7, var7 + var5, this.field_146166_p);
         this.drawVerticalLine(var6 + var4, var7, var7 + var5, this.field_146165_q);
      }
   }

   public void drawLabel(Minecraft var1, int var2, int var3) {
      if (this.visible) {
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         this.drawLabelBackground(var1, var2, var3);
         int var4 = this.field_146174_h + this.field_146161_f / 2 + this.field_146163_s / 2;
         int var5 = var4 - this.field_146173_k.size() * 10 / 2;

         for (int var6 = 0; var6 < this.field_146173_k.size(); var6++) {
            if (this.centered) {
               this.drawCenteredString(
                  this.fontRenderer, this.field_146173_k.get(var6), this.field_146162_g + this.field_146167_a / 2, var5 + var6 * 10, this.field_146168_n
               );
            } else {
               this.drawString(this.fontRenderer, this.field_146173_k.get(var6), this.field_146162_g, var5 + var6 * 10, this.field_146168_n);
            }
         }
      }
   }
}
