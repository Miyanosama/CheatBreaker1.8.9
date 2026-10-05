package net.minecraft.client.gui.achievement;

import io.netty.util.concurrent.AbstractEventExecutor;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.particle.EntityNoteFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatCrafting;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.StructureMineshaftPieces$Stairs;
import org.apache.log4j.helpers.FileWatchdog;
import org.lwjgl.input.Mouse;

public abstract class GuiStats$Stats extends GuiSlot {
   public List<StatCrafting> w;
   public AbstractEventExecutor field_0008;
   public int y;
   public Comparator<StatCrafting> x;
   public FileWatchdog field_0001;
   public int v;
   public EntityNoteFX field_0009;
   public ChatComponentTranslation field_0006;
   public StructureMineshaftPieces$Stairs field_0003;
   public int z;

   public void func_148209_a(StatBase var1, int var2, int var3, boolean var4) {
      if (var1 != null) {
         String var5 = var1.format(GuiStats.access$100(this.field_148214_q).a(var1));
         this.field_148214_q
            .drawString(
               GuiStats.access$500(this.field_148214_q),
               var5,
               var2 - GuiStats.access$600(this.field_148214_q).getStringWidth(var5),
               var3 + 5,
               var4 ? 16777215 : 9474192
            );
      } else {
         String var6 = "-";
         this.field_148214_q
            .drawString(
               GuiStats.access$700(this.field_148214_q),
               var6,
               var2 - GuiStats.access$800(this.field_148214_q).getStringWidth(var6),
               var3 + 5,
               var4 ? 16777215 : 9474192
            );
      }
   }

   @Override
   public boolean isSelected(int var1) {
      return false;
   }

   @Override
   public void elementClicked(int var1, boolean var2, int var3, int var4) {
   }

   @Override
   public void drawBackground() {
      this.field_148214_q.drawDefaultBackground();
   }

   @Override
   public int getSize() {
      return this.w.size();
   }

   public GuiStats$Stats(GuiStats var1, Minecraft var2) {
      this.field_148214_q = var1;
      super(var2, var1.l, var1.m, 32, var1.m - 64, 20);
      this.v = -1;
      this.y = -1;
      this.setShowSelectionBox(false);
      this.setHasListHeader(true, 20);
   }

   public void func_148212_h(int var1) {
      if (var1 != this.y) {
         this.y = var1;
         this.z = -1;
      } else if (this.z == -1) {
         this.z = 1;
      } else {
         this.y = -1;
         this.z = 0;
      }

      Collections.sort(this.w, this.x);
   }

   @Override
   public void func_148132_a(int var1, int var2) {
      this.v = -1;
      if (var1 >= 79 && var1 < 115) {
         this.v = 0;
      } else if (var1 >= 129 && var1 < 165) {
         this.v = 1;
      } else if (var1 >= 179 && var1 < 215) {
         this.v = 2;
      }

      if (this.v >= 0) {
         this.func_148212_h(this.v);
         this.a.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
      }
   }

   public void func_148213_a(StatCrafting var1, int var2, int var3) {
      if (var1 != null) {
         Item var4 = var1.func_150959_a();
         ItemStack var5 = new ItemStack(var4);
         String var6 = var5.getUnlocalizedName();
         String var7 = ("" + I18n.format(var6 + ".name")).trim();
         if (var7.length() > 0) {
            int var8 = var2 + 12;
            int var9 = var3 - 12;
            int var10 = GuiStats.method_28236(this.field_148214_q).getStringWidth(var7);
            GuiStats.method_28253(this.field_148214_q, var8 - 3, var9 - 3, var8 + var10 + 3, var9 + 8 + 3, -1073741824, -1073741824);
            GuiStats.method_28233(this.field_148214_q).drawStringWithShadow(var7, var8, var9, -1);
         }
      }
   }

   public StatCrafting c(int var1) {
      return this.w.get(var1);
   }

   @Override
   public void func_148142_b(int var1, int var2) {
      if (var2 >= this.d && var2 <= this.bottom) {
         int var3 = this.c(var1, var2);
         int var4 = this.b / 2 - 92 - 16;
         if (var3 >= 0) {
            if (var1 < var4 + 40 || var1 > var4 + 40 + 20) {
               return;
            }

            StatCrafting var11 = this.c(var3);
            this.func_148213_a(var11, var1, var2);
         } else {
            String var5 = "";
            if (var1 >= var4 + 115 - 18 && var1 <= var4 + 115) {
               var5 = this.func_148210_b(0);
            } else if (var1 >= var4 + 165 - 18 && var1 <= var4 + 165) {
               var5 = this.func_148210_b(1);
            } else {
               if (var1 < var4 + 215 - 18 || var1 > var4 + 215) {
                  return;
               }

               var5 = this.func_148210_b(2);
            }

            var5 = ("" + I18n.format(var5)).trim();
            if (var5.length() > 0) {
               int var6 = var1 + 12;
               int var7 = var2 - 12;
               int var8 = GuiStats.method_28255(this.field_148214_q).getStringWidth(var5);
               GuiStats.method_28243(this.field_148214_q, var6 - 3, var7 - 3, var6 + var8 + 3, var7 + 8 + 3, -1073741824, -1073741824);
               GuiStats.method_28247(this.field_148214_q).drawStringWithShadow(var5, var6, var7, -1);
            }
         }
      }
   }

   public abstract String func_148210_b(int var1);

   @Override
   public void drawListHeader(int var1, int var2, Tessellator var3) {
      if (!Mouse.isButtonDown(0)) {
         this.v = -1;
      }

      if (this.v == 0) {
         GuiStats.access$400(this.field_148214_q, var1 + 115 - 18, var2 + 1, 0, 0);
      } else {
         GuiStats.access$400(this.field_148214_q, var1 + 115 - 18, var2 + 1, 0, 18);
      }

      if (this.v == 1) {
         GuiStats.access$400(this.field_148214_q, var1 + 165 - 18, var2 + 1, 0, 0);
      } else {
         GuiStats.access$400(this.field_148214_q, var1 + 165 - 18, var2 + 1, 0, 18);
      }

      if (this.v == 2) {
         GuiStats.access$400(this.field_148214_q, var1 + 215 - 18, var2 + 1, 0, 0);
      } else {
         GuiStats.access$400(this.field_148214_q, var1 + 215 - 18, var2 + 1, 0, 18);
      }

      if (this.y != -1) {
         short var4 = 79;
         byte var5 = 18;
         if (this.y == 1) {
            var4 = 129;
         } else if (this.y == 2) {
            var4 = 179;
         }

         if (this.z == 1) {
            var5 = 36;
         }

         GuiStats.access$400(this.field_148214_q, var1 + var4, var2 + 1, var5, 0);
      }
   }
}
