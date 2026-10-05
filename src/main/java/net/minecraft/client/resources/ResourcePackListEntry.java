package net.minecraft.client.resources;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;

public abstract class ResourcePackListEntry implements GuiListExtended.IGuiListEntry {
   public static ResourceLocation RESOURCE_PACKS_TEXTURE = new ResourceLocation("textures/gui/resource_packs.png");
   public static IChatComponent field_183020_d = new ChatComponentTranslation("resourcePack.incompatible");
   public Minecraft a;
   public static IChatComponent field_183021_e = new ChatComponentTranslation("resourcePack.incompatible.old");
   public static IChatComponent field_183022_f = new ChatComponentTranslation("resourcePack.incompatible.new");
   public GuiScreenResourcePacks resourcePacksGUI;

   public boolean func_148314_g() {
      List var1 = this.resourcePacksGUI.getListContaining(this);
      int var2 = var1.indexOf(this);
      return var2 > 0 && ((ResourcePackListEntry)var1.get(var2 - 1)).func_148310_d();
   }

   public boolean func_148309_e() {
      return !this.resourcePacksGUI.hasResourcePackEntry(this);
   }

   @Override
   public boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6) {
      if (this.func_148310_d() && var5 <= 32) {
         if (this.func_148309_e()) {
            this.resourcePacksGUI.markChanged();
            int var11 = this.func_183019_a();
            if (var11 != 1) {
               String var13 = I18n.format("resourcePack.incompatible.confirm.title");
               String var9 = I18n.format("resourcePack.incompatible.confirm." + (var11 > 1 ? "new" : "old"));
               this.a.displayGuiScreen(new GuiYesNo(new GuiYesNoCallback() {
                  @Override
                  public void confirmClicked(boolean var1, int var2x) {
                     List var3x = ResourcePackListEntry.this.resourcePacksGUI.getListContaining(ResourcePackListEntry.this);
                     ResourcePackListEntry.this.a.displayGuiScreen(ResourcePackListEntry.this.resourcePacksGUI);
                     if (var1) {
                        var3x.remove(ResourcePackListEntry.this);
                        ResourcePackListEntry.this.resourcePacksGUI.getSelectedResourcePacks().add(0, ResourcePackListEntry.this);
                     }
                  }
               }, var13, var9, 0));
            } else {
               this.resourcePacksGUI.getListContaining(this).remove(this);
               this.resourcePacksGUI.getSelectedResourcePacks().add(0, this);
            }

            return true;
         }

         if (var5 < 16 && this.func_148308_f()) {
            this.resourcePacksGUI.getListContaining(this).remove(this);
            this.resourcePacksGUI.getAvailableResourcePacks().add(0, this);
            this.resourcePacksGUI.markChanged();
            return true;
         }

         if (var5 > 16 && var6 < 16 && this.func_148314_g()) {
            List var10 = this.resourcePacksGUI.getListContaining(this);
            int var12 = var10.indexOf(this);
            var10.remove(this);
            var10.add(var12 - 1, this);
            this.resourcePacksGUI.markChanged();
            return true;
         }

         if (var5 > 16 && var6 > 16 && this.func_148307_h()) {
            List var7 = this.resourcePacksGUI.getListContaining(this);
            int var8 = var7.indexOf(this);
            var7.remove(this);
            var7.add(var8 + 1, this);
            this.resourcePacksGUI.markChanged();
            return true;
         }
      }

      return false;
   }

   @Override
   public void mouseReleased(int var1, int var2, int var3, int var4, int var5, int var6) {
   }

   public abstract String func_148312_b();

   public boolean func_148307_h() {
      List var1 = this.resourcePacksGUI.getListContaining(this);
      int var2 = var1.indexOf(this);
      return var2 >= 0 && var2 < var1.size() - 1 && ((ResourcePackListEntry)var1.get(var2 + 1)).func_148310_d();
   }

   public ResourcePackListEntry(GuiScreenResourcePacks var1) {
      this.resourcePacksGUI = var1;
      this.a = Minecraft.getMinecraft();
   }

   public boolean func_148308_f() {
      return this.resourcePacksGUI.hasResourcePackEntry(this);
   }

   public abstract String func_148311_a();

   public boolean func_148310_d() {
      return true;
   }

   @Override
   public void drawEntry(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
      int var9 = this.func_183019_a();
      if (var9 != 1) {
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         Gui.a(var2 - 1, var3 - 1, var2 + var4 - 9, var3 + var5 + 1, -8978432);
      }

      this.func_148313_c();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      Gui.drawModalRectWithCustomSizedTexture(var2, var3, 0.0F, 0.0F, 32, 32, 32.0F, 32.0F);
      String var10 = this.func_148312_b();
      String var11 = this.func_148311_a();
      if ((this.a.gameSettings.touchscreen || var8) && this.func_148310_d()) {
         this.a.getTextureManager().bindTexture(RESOURCE_PACKS_TEXTURE);
         Gui.a(var2, var3, var2 + 32, var3 + 32, -1601138544);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         int var12 = var6 - var2;
         int var13 = var7 - var3;
         if (var9 < 1) {
            var10 = field_183020_d.getFormattedText();
            var11 = field_183021_e.getFormattedText();
         } else if (var9 > 1) {
            var10 = field_183020_d.getFormattedText();
            var11 = field_183022_f.getFormattedText();
         }

         if (this.func_148309_e()) {
            if (var12 < 32) {
               Gui.drawModalRectWithCustomSizedTexture(var2, var3, 0.0F, 32.0F, 32, 32, 256.0F, 256.0F);
            } else {
               Gui.drawModalRectWithCustomSizedTexture(var2, var3, 0.0F, 0.0F, 32, 32, 256.0F, 256.0F);
            }
         } else {
            if (this.func_148308_f()) {
               if (var12 < 16) {
                  Gui.drawModalRectWithCustomSizedTexture(var2, var3, 32.0F, 32.0F, 32, 32, 256.0F, 256.0F);
               } else {
                  Gui.drawModalRectWithCustomSizedTexture(var2, var3, 32.0F, 0.0F, 32, 32, 256.0F, 256.0F);
               }
            }

            if (this.func_148314_g()) {
               if (var12 < 32 && var12 > 16 && var13 < 16) {
                  Gui.drawModalRectWithCustomSizedTexture(var2, var3, 96.0F, 32.0F, 32, 32, 256.0F, 256.0F);
               } else {
                  Gui.drawModalRectWithCustomSizedTexture(var2, var3, 96.0F, 0.0F, 32, 32, 256.0F, 256.0F);
               }
            }

            if (this.func_148307_h()) {
               if (var12 < 32 && var12 > 16 && var13 > 16) {
                  Gui.drawModalRectWithCustomSizedTexture(var2, var3, 64.0F, 32.0F, 32, 32, 256.0F, 256.0F);
               } else {
                  Gui.drawModalRectWithCustomSizedTexture(var2, var3, 64.0F, 0.0F, 32, 32, 256.0F, 256.0F);
               }
            }
         }
      }

      int var15 = this.a.fontRendererObj.getStringWidth(var10);
      if (var15 > 157) {
         var10 = this.a.fontRendererObj.trimStringToWidth(var10, 157 - this.a.fontRendererObj.getStringWidth("...")) + "...";
      }

      this.a.fontRendererObj.drawStringWithShadow(var10, var2 + 32 + 2, var3 + 1, 16777215);
      List var16 = this.a.fontRendererObj.listFormattedStringToWidth(var11, 157);

      for (int var14 = 0; var14 < 2 && var14 < var16.size(); var14++) {
         this.a.fontRendererObj.drawStringWithShadow((String)var16.get(var14), var2 + 32 + 2, var3 + 12 + 10 * var14, 8421504);
      }
   }

   public abstract int func_183019_a();

   public abstract void func_148313_c();

   @Override
   public void setSelected(int var1, int var2, int var3) {
   }
}
