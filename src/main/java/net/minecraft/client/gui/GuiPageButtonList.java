package net.minecraft.client.gui;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IntHashMap;

public class GuiPageButtonList extends GuiListExtended {
   public List<GuiTextField> field_178072_w;
   public GuiPageButtonList.GuiResponder field_178076_z;
   public GuiPageButtonList.GuiListEntry[][] field_178078_x;
   public int field_178077_y;
   public List<GuiPageButtonList.GuiEntry> field_178074_u = Lists.newArrayList();
   public IntHashMap<Gui> field_178073_v = new IntHashMap<>();
   public Gui field_178075_A;

   public Gui func_178056_g() {
      return this.field_178075_A;
   }

   public GuiListButton func_178065_a(int var1, int var2, GuiPageButtonList.GuiButtonEntry var3) {
      GuiListButton var4 = new GuiListButton(this.field_178076_z, var3.func_178935_b(), var1, var2, var3.func_178936_c(), var3.func_178940_a());
      var4.m = var3.func_178934_d();
      return var4;
   }

   public void func_178062_a(char var1, int var2) {
      if (this.field_178075_A instanceof GuiTextField) {
         GuiTextField var3 = (GuiTextField)this.field_178075_A;
         if (!GuiScreen.isKeyComboCtrlV(var2)) {
            if (var2 == 15) {
               var3.setFocused(false);
               int var4 = this.field_178072_w.indexOf(this.field_178075_A);
               if (GuiScreen.isShiftKeyDown()) {
                  if (var4 == 0) {
                     var4 = this.field_178072_w.size() - 1;
                  } else {
                     var4--;
                  }
               } else if (var4 == this.field_178072_w.size() - 1) {
                  var4 = 0;
               } else {
                  var4++;
               }

               this.field_178075_A = this.field_178072_w.get(var4);
               var3 = (GuiTextField)this.field_178075_A;
               var3.setFocused(true);
               int var5 = var3.yPosition + this.slotHeight;
               int var6 = var3.yPosition;
               if (var5 > this.bottom) {
                  this.amountScrolled = this.amountScrolled + (var5 - this.bottom);
               } else if (var6 < this.d) {
                  this.amountScrolled = var6;
               }
            } else {
               var3.textboxKeyTyped(var1, var2);
            }
         } else {
            String var14 = GuiScreen.getClipboardString();
            String[] var15 = var14.split(";");
            int var16 = this.field_178072_w.indexOf(this.field_178075_A);
            int var7 = var16;

            for (String var11 : var15) {
               this.field_178072_w.get(var7).setText(var11);
               if (var7 == this.field_178072_w.size() - 1) {
                  var7 = 0;
               } else {
                  var7++;
               }

               if (var7 == var16) {
                  break;
               }
            }
         }
      }
   }

   public GuiSlider func_178067_a(int var1, int var2, GuiPageButtonList.GuiSlideEntry var3) {
      GuiSlider var4 = new GuiSlider(
         this.field_178076_z,
         var3.func_178935_b(),
         var1,
         var2,
         var3.func_178936_c(),
         var3.func_178943_e(),
         var3.func_178944_f(),
         var3.func_178942_g(),
         var3.func_178945_a()
      );
      var4.m = var3.func_178934_d();
      return var4;
   }

   public int func_178059_e() {
      return this.field_178077_y;
   }

   public void func_178071_h() {
      if (this.field_178077_y > 0) {
         this.func_181156_c(this.field_178077_y - 1);
      }
   }

   public Gui func_178061_c(int var1) {
      return this.field_178073_v.lookup(var1);
   }

   @Override
   public int v_() {
      return 400;
   }

   public Gui func_178058_a(GuiPageButtonList.GuiListEntry var1, int var2, boolean var3) {
      return (Gui)(var1 instanceof GuiPageButtonList.GuiSlideEntry
         ? this.func_178067_a(this.b / 2 - 155 + var2, 0, (GuiPageButtonList.GuiSlideEntry)var1)
         : (
            var1 instanceof GuiPageButtonList.GuiButtonEntry
               ? this.func_178065_a(this.b / 2 - 155 + var2, 0, (GuiPageButtonList.GuiButtonEntry)var1)
               : (
                  var1 instanceof GuiPageButtonList.EditBoxEntry
                     ? this.func_178068_a(this.b / 2 - 155 + var2, 0, (GuiPageButtonList.EditBoxEntry)var1)
                     : (
                        var1 instanceof GuiPageButtonList.GuiLabelEntry
                           ? this.func_178063_a(this.b / 2 - 155 + var2, 0, (GuiPageButtonList.GuiLabelEntry)var1, var3)
                           : null
                     )
               )
         ));
   }

   public void func_178064_i() {
      if (this.field_178077_y < this.field_178078_x.length - 1) {
         this.func_181156_c(this.field_178077_y + 1);
      }
   }

   public void func_178060_e(int var1, int var2) {
      for (GuiPageButtonList.GuiListEntry var6 : this.field_178078_x[var1]) {
         if (var6 != null) {
            this.func_178066_a(this.field_178073_v.lookup(var6.func_178935_b()), false);
         }
      }

      for (GuiPageButtonList.GuiListEntry var10 : this.field_178078_x[var2]) {
         if (var10 != null) {
            this.func_178066_a(this.field_178073_v.lookup(var10.func_178935_b()), true);
         }
      }
   }

   public void func_178069_s() {
      for (GuiPageButtonList.GuiListEntry[] var4 : this.field_178078_x) {
         for (int var5 = 0; var5 < var4.length; var5 += 2) {
            GuiPageButtonList.GuiListEntry var6 = var4[var5];
            GuiPageButtonList.GuiListEntry var7 = var5 < var4.length - 1 ? var4[var5 + 1] : null;
            Gui var8 = this.func_178058_a(var6, 0, var7 == null);
            Gui var9 = this.func_178058_a(var7, 160, var6 == null);
            GuiPageButtonList.GuiEntry var10 = new GuiPageButtonList.GuiEntry(var8, var9);
            this.field_178074_u.add(var10);
            if (var6 != null && var8 != null) {
               this.field_178073_v.addKey(var6.func_178935_b(), var8);
               if (var8 instanceof GuiTextField) {
                  this.field_178072_w.add((GuiTextField)var8);
               }
            }

            if (var7 != null && var9 != null) {
               this.field_178073_v.addKey(var7.func_178935_b(), var9);
               if (var9 instanceof GuiTextField) {
                  this.field_178072_w.add((GuiTextField)var9);
               }
            }
         }
      }
   }

   public int func_178057_f() {
      return this.field_178078_x.length;
   }

   public void func_181155_a(boolean var1) {
      for (GuiPageButtonList.GuiEntry var3 : this.field_178074_u) {
         if (var3.field_178029_b instanceof GuiButton) {
            ((GuiButton)var3.field_178029_b).l = var1;
         }

         if (var3.field_178030_c instanceof GuiButton) {
            ((GuiButton)var3.field_178030_c).l = var1;
         }
      }
   }

   @Override
   public int getScrollBarX() {
      return super.getScrollBarX() + 32;
   }

   public GuiTextField func_178068_a(int var1, int var2, GuiPageButtonList.EditBoxEntry var3) {
      GuiTextField var4 = new GuiTextField(var3.func_178935_b(), this.a.fontRendererObj, var1, var2, 150, 20);
      var4.setText(var3.func_178936_c());
      var4.func_175207_a(this.field_178076_z);
      var4.setVisible(var3.func_178934_d());
      var4.setValidator(var3.func_178950_a());
      return var4;
   }

   public GuiPageButtonList.GuiEntry getListEntry(int var1) {
      return this.field_178074_u.get(var1);
   }

   public GuiPageButtonList(
      Minecraft var1, int var2, int var3, int var4, int var5, int var6, GuiPageButtonList.GuiResponder var7, GuiPageButtonList.GuiListEntry[]... var8
   ) {
      super(var1, var2, var3, var4, var5, var6);
      this.field_178072_w = Lists.newArrayList();
      this.field_178076_z = var7;
      this.field_178078_x = var8;
      this.k = false;
      this.func_178069_s();
      this.func_178055_t();
   }

   public void func_181156_c(int var1) {
      if (var1 != this.field_178077_y) {
         int var2 = this.field_178077_y;
         this.field_178077_y = var1;
         this.func_178055_t();
         this.func_178060_e(var2, var1);
         this.amountScrolled = 0.0F;
      }
   }

   public void func_178066_a(Gui var1, boolean var2) {
      if (var1 instanceof GuiButton) {
         ((GuiButton)var1).m = var2;
      } else if (var1 instanceof GuiTextField) {
         ((GuiTextField)var1).setVisible(var2);
      } else if (var1 instanceof GuiLabel) {
         ((GuiLabel)var1).visible = var2;
      }
   }

   public void func_178055_t() {
      this.field_178074_u.clear();

      for (int var1 = 0; var1 < this.field_178078_x[this.field_178077_y].length; var1 += 2) {
         GuiPageButtonList.GuiListEntry var2 = this.field_178078_x[this.field_178077_y][var1];
         GuiPageButtonList.GuiListEntry var3 = var1 < this.field_178078_x[this.field_178077_y].length - 1
            ? this.field_178078_x[this.field_178077_y][var1 + 1]
            : null;
         Gui var4 = this.field_178073_v.lookup(var2.func_178935_b());
         Gui var5 = var3 != null ? this.field_178073_v.lookup(var3.func_178935_b()) : null;
         GuiPageButtonList.GuiEntry var6 = new GuiPageButtonList.GuiEntry(var4, var5);
         this.field_178074_u.add(var6);
      }
   }

   @Override
   public int getSize() {
      return this.field_178074_u.size();
   }

   @Override
   public boolean b(int var1, int var2, int var3) {
      boolean var4 = super.b(var1, var2, var3);
      int var5 = this.c(var1, var2);
      if (var5 >= 0) {
         GuiPageButtonList.GuiEntry var6 = this.getListEntry(var5);
         if (this.field_178075_A != var6.field_178028_d && this.field_178075_A != null && this.field_178075_A instanceof GuiTextField) {
            ((GuiTextField)this.field_178075_A).setFocused(false);
         }

         this.field_178075_A = var6.field_178028_d;
      }

      return var4;
   }

   public GuiLabel func_178063_a(int var1, int var2, GuiPageButtonList.GuiLabelEntry var3, boolean var4) {
      GuiLabel var5;
      if (var4) {
         var5 = new GuiLabel(this.a.fontRendererObj, var3.func_178935_b(), var1, var2, this.b - var1 * 2, 20, -1);
      } else {
         var5 = new GuiLabel(this.a.fontRendererObj, var3.func_178935_b(), var1, var2, 150, 20, -1);
      }

      var5.visible = var3.func_178934_d();
      var5.func_175202_a(var3.func_178936_c());
      var5.setCentered();
      return var5;
   }

   public static class EditBoxEntry extends GuiPageButtonList.GuiListEntry {
      public Predicate<String> field_178951_a;

      public Predicate<String> func_178950_a() {
         return this.field_178951_a;
      }

      public EditBoxEntry(int var1, String var2, boolean var3, Predicate<String> var4) {
         super(var1, var2, var3);
         this.field_178951_a = Objects.firstNonNull(var4, Predicates.alwaysTrue());
      }
   }

   public static class GuiButtonEntry extends GuiPageButtonList.GuiListEntry {
      public boolean field_178941_a;

      public boolean func_178940_a() {
         return this.field_178941_a;
      }

      public GuiButtonEntry(int var1, String var2, boolean var3, boolean var4) {
         super(var1, var2, var3);
         this.field_178941_a = var4;
      }
   }

   public static class GuiEntry implements GuiListExtended.IGuiListEntry {
      public Gui field_178030_c;
      public Gui field_178028_d;
      public Gui field_178029_b;
      public Minecraft field_178031_a = Minecraft.getMinecraft();

      public void func_178027_a(GuiTextField var1, int var2, boolean var3) {
         var1.yPosition = var2;
         if (!var3) {
            var1.drawTextBox();
         }
      }

      public void func_178017_a(Gui var1, int var2, int var3, int var4, boolean var5) {
         if (var1 != null) {
            if (var1 instanceof GuiButton) {
               this.func_178024_a((GuiButton)var1, var2, var3, var4, var5);
            } else if (var1 instanceof GuiTextField) {
               this.func_178027_a((GuiTextField)var1, var2, var5);
            } else if (var1 instanceof GuiLabel) {
               this.func_178025_a((GuiLabel)var1, var2, var3, var4, var5);
            }
         }
      }

      public void func_178025_a(GuiLabel var1, int var2, int var3, int var4, boolean var5) {
         var1.field_146174_h = var2;
         if (!var5) {
            var1.drawLabel(this.field_178031_a, var3, var4);
         }
      }

      @Override
      public void setSelected(int var1, int var2, int var3) {
         this.func_178017_a(this.field_178029_b, var3, 0, 0, true);
         this.func_178017_a(this.field_178030_c, var3, 0, 0, true);
      }

      public boolean func_178023_a(GuiButton var1, int var2, int var3, int var4) {
         boolean var5 = var1.mousePressed(this.field_178031_a, var2, var3);
         if (var5) {
            this.field_178028_d = var1;
         }

         return var5;
      }

      public void func_178024_a(GuiButton var1, int var2, int var3, int var4, boolean var5) {
         var1.i = var2;
         if (!var5) {
            var1.drawButton(this.field_178031_a, var3, var4);
         }
      }

      @Override
      public boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6) {
         boolean var7 = this.func_178026_a(this.field_178029_b, var2, var3, var4);
         boolean var8 = this.func_178026_a(this.field_178030_c, var2, var3, var4);
         return var7 || var8;
      }

      public Gui func_178021_b() {
         return this.field_178030_c;
      }

      public void func_178019_b(GuiButton var1, int var2, int var3, int var4) {
         var1.mouseReleased(var2, var3);
      }

      public void func_178018_a(GuiTextField var1, int var2, int var3, int var4) {
         var1.mouseClicked(var2, var3, var4);
         if (var1.isFocused()) {
            this.field_178028_d = var1;
         }
      }

      @Override
      public void drawEntry(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
         this.func_178017_a(this.field_178029_b, var3, var6, var7, false);
         this.func_178017_a(this.field_178030_c, var3, var6, var7, false);
      }

      @Override
      public void mouseReleased(int var1, int var2, int var3, int var4, int var5, int var6) {
         this.func_178016_b(this.field_178029_b, var2, var3, var4);
         this.func_178016_b(this.field_178030_c, var2, var3, var4);
      }

      public GuiEntry(Gui var1, Gui var2) {
         this.field_178029_b = var1;
         this.field_178030_c = var2;
      }

      public Gui func_178022_a() {
         return this.field_178029_b;
      }

      public void func_178016_b(Gui var1, int var2, int var3, int var4) {
         if (var1 != null && var1 instanceof GuiButton) {
            this.func_178019_b((GuiButton)var1, var2, var3, var4);
         }
      }

      public boolean func_178026_a(Gui var1, int var2, int var3, int var4) {
         if (var1 == null) {
            return false;
         } else if (var1 instanceof GuiButton) {
            return this.func_178023_a((GuiButton)var1, var2, var3, var4);
         } else {
            if (var1 instanceof GuiTextField) {
               this.func_178018_a((GuiTextField)var1, var2, var3, var4);
            }

            return false;
         }
      }
   }

   public static class GuiLabelEntry extends GuiPageButtonList.GuiListEntry {
      public GuiLabelEntry(int var1, String var2, boolean var3) {
         super(var1, var2, var3);
      }
   }

   public static class GuiListEntry {
      public String field_178937_b;
      public int field_178939_a;
      public boolean field_178938_c;

      public int func_178935_b() {
         return this.field_178939_a;
      }

      public boolean func_178934_d() {
         return this.field_178938_c;
      }

      public GuiListEntry(int var1, String var2, boolean var3) {
         this.field_178939_a = var1;
         this.field_178937_b = var2;
         this.field_178938_c = var3;
      }

      public String func_178936_c() {
         return this.field_178937_b;
      }
   }

   public interface GuiResponder {
      void onTick(int var1, float var2);

      void func_175321_a(int var1, boolean var2);

      void func_175319_a(int var1, String var2);
   }

   public static class GuiSlideEntry extends GuiPageButtonList.GuiListEntry {
      public float field_178947_b;
      public float field_178948_c;
      public GuiSlider.FormatHelper field_178949_a;
      public float field_178946_d;

      public float func_178944_f() {
         return this.field_178948_c;
      }

      public GuiSlideEntry(int var1, String var2, boolean var3, GuiSlider.FormatHelper var4, float var5, float var6, float var7) {
         super(var1, var2, var3);
         this.field_178949_a = var4;
         this.field_178947_b = var5;
         this.field_178948_c = var6;
         this.field_178946_d = var7;
      }

      public float func_178943_e() {
         return this.field_178947_b;
      }

      public float func_178942_g() {
         return this.field_178946_d;
      }

      public GuiSlider.FormatHelper func_178945_a() {
         return this.field_178949_a;
      }
   }
}
