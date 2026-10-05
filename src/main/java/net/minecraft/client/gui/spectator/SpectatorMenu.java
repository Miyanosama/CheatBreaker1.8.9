package net.minecraft.client.gui.spectator;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiSpectator;
import net.minecraft.client.gui.spectator.categories.SpectatorDetails;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class SpectatorMenu {
   public static ISpectatorMenuObject field_178655_b = new SpectatorMenu.EndSpectatorObject();
   public ISpectatorMenuRecipient field_178651_f;
   public static ISpectatorMenuObject field_178656_c = new SpectatorMenu.MoveMenuObject(-1, true);
   public ISpectatorMenuView field_178659_h;
   public static ISpectatorMenuObject field_178653_d = new SpectatorMenu.MoveMenuObject(1, true);
   public static ISpectatorMenuObject field_178654_e = new SpectatorMenu.MoveMenuObject(1, false);
   public int field_178658_j;
   public int field_178660_i;
   public static ISpectatorMenuObject field_178657_a = new ISpectatorMenuObject() {
      @Override
      public boolean func_178662_A_() {
         return false;
      }

      @Override
      public void func_178663_a(float var1, int var2) {
      }

      @Override
      public void func_178661_a(SpectatorMenu var1) {
      }

      @Override
      public IChatComponent getSpectatorName() {
         return new ChatComponentText("");
      }
   };
   public List<SpectatorDetails> field_178652_g = Lists.newArrayList();

   public void func_178644_b(int var1) {
      ISpectatorMenuObject var2 = this.func_178643_a(var1);
      if (var2 != field_178657_a) {
         if (this.field_178660_i == var1 && var2.func_178662_A_()) {
            var2.func_178661_a(this);
         } else {
            this.field_178660_i = var1;
         }
      }
   }

   public SpectatorMenu(ISpectatorMenuRecipient var1) {
      this.field_178659_h = new BaseSpectatorGroup();
      this.field_178660_i = -1;
      this.field_178651_f = var1;
   }

   public List<ISpectatorMenuObject> func_178642_a() {
      ArrayList var1 = Lists.newArrayList();

      for (int var2 = 0; var2 <= 8; var2++) {
         var1.add(this.func_178643_a(var2));
      }

      return var1;
   }

   public int func_178648_e() {
      return this.field_178660_i;
   }

   public ISpectatorMenuObject func_178645_b() {
      return this.func_178643_a(this.field_178660_i);
   }

   public void func_178647_a(ISpectatorMenuView var1) {
      this.field_178652_g.add(this.func_178646_f());
      this.field_178659_h = var1;
      this.field_178660_i = -1;
      this.field_178658_j = 0;
   }

   public ISpectatorMenuObject func_178643_a(int var1) {
      int var2 = var1 + this.field_178658_j * 6;
      return this.field_178658_j > 0 && var1 == 0
         ? field_178656_c
         : (
            var1 == 7
               ? (var2 < this.field_178659_h.func_178669_a().size() ? field_178653_d : field_178654_e)
               : (
                  var1 == 8
                     ? field_178655_b
                     : (
                        var2 >= 0 && var2 < this.field_178659_h.func_178669_a().size()
                           ? Objects.firstNonNull(this.field_178659_h.func_178669_a().get(var2), field_178657_a)
                           : field_178657_a
                     )
               )
         );
   }

   public SpectatorDetails func_178646_f() {
      return new SpectatorDetails(this.field_178659_h, this.func_178642_a(), this.field_178660_i);
   }

   public void func_178641_d() {
      this.field_178651_f.func_175257_a(this);
   }

   public ISpectatorMenuView func_178650_c() {
      return this.field_178659_h;
   }

   public static class EndSpectatorObject implements ISpectatorMenuObject {
      public EndSpectatorObject() {
      }

      @Override
      public void func_178661_a(SpectatorMenu var1) {
         var1.func_178641_d();
      }

      @Override
      public void func_178663_a(float var1, int var2) {
         Minecraft.getMinecraft().getTextureManager().bindTexture(GuiSpectator.field_175269_a);
         Gui.drawModalRectWithCustomSizedTexture(0, 0, 128.0F, 0.0F, 16, 16, 256.0F, 256.0F);
      }

      @Override
      public IChatComponent getSpectatorName() {
         return new ChatComponentText("Close menu");
      }

      @Override
      public boolean func_178662_A_() {
         return true;
      }
   }

   public static class MoveMenuObject implements ISpectatorMenuObject {
      public boolean field_178665_b;
      public int field_178666_a;

      @Override
      public void func_178663_a(float var1, int var2) {
         Minecraft.getMinecraft().getTextureManager().bindTexture(GuiSpectator.field_175269_a);
         if (this.field_178666_a < 0) {
            Gui.drawModalRectWithCustomSizedTexture(0, 0, 144.0F, 0.0F, 16, 16, 256.0F, 256.0F);
         } else {
            Gui.drawModalRectWithCustomSizedTexture(0, 0, 160.0F, 0.0F, 16, 16, 256.0F, 256.0F);
         }
      }

      @Override
      public boolean func_178662_A_() {
         return this.field_178665_b;
      }

      @Override
      public IChatComponent getSpectatorName() {
         return this.field_178666_a < 0 ? new ChatComponentText("Previous Page") : new ChatComponentText("Next Page");
      }

      public MoveMenuObject(int var1, boolean var2) {
         this.field_178666_a = var1;
         this.field_178665_b = var2;
      }

      @Override
      public void func_178661_a(SpectatorMenu var1) {
         var1.field_178658_j = this.field_178666_a;
      }
   }
}
