package com.cheatbreaker.client.ui.resourcepack;

import com.cheatbreaker.client.util.render.LegacyGlStateManager;

import com.cheatbreaker.client.ui.resourcepack.ResourcePackEntryElement;

import com.cheatbreaker.client.ui.resourcepack.ResourcePackFolder;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.resourcepack.ResourcePackGui;
import java.util.List;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.util.ResourceLocation;

public class ResourcePackFolderEntry extends ResourcePackEntryElement {
   public static ResourceLocation recoveredField3949 = new ResourceLocation("client/icons/folder.png");
   public boolean recoveredField3950;
   public List<ResourcePackFolder> recoveredField3951;

   public void method_03758(ResourcePackFolder var1, int var2) {
      boolean var3 = (Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField548.getValue();
      if (var3) {
         this.recoveredField700.getTextureManager().bindTexture(recoveredField3949);
         LegacyGlStateManager.method_28204();
         Gui.drawScaledCustomSizeModalRect(this.recoveredField1780 + 2, var2 + 3, 0.0F, 0.0F, 256, 256, 32, 32, 256.0F, 256.0F);
         LegacyGlStateManager.method_28199();
      }

      this.recoveredField700
         .fontRendererObj
         .drawString(
            ResourcePackText.method_30076(var1.method_26764(), this.recoveredField1790 - 46),
            this.recoveredField1780 + (var3 ? 36.0F : 2.0F),
            var2 + 2.0F,
            -1,
            true
         );
      int var4 = var1.method_26765().size() - 1;
      if (var4 != -1 && (Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField526.getValue()) {
         float var7 = var2 + 13.0F;
         if (var4 != 0) {
            String var6 = ResourcePackText.method_30076(var4 + (var4 == 1 ? " Subfolder" : " Subfolders"), this.recoveredField1790 - 46);
            this.recoveredField700.fontRendererObj.drawString(var6, this.recoveredField1780 + (var3 ? 36.0F : 2.0F), var7, -5592406, true);
            var7 += 10.0F;
         }

         int var5;
         if ((var5 = var1.method_26763().size()) != 0) {
            String var8 = ResourcePackText.method_30076(var5 + (var5 == 1 ? " Pack" : " Packs"), this.recoveredField1790 - 46);
            this.recoveredField700.fontRendererObj.drawString(var8, this.recoveredField1780 + (var3 ? 36.0F : 2.0F), var7, -5592406, true);
         }
      }
   }

   @Override
   public int method_00746() {
      return this.recoveredField3950 ? this.recoveredField701.size() + this.recoveredField3951.size() : this.recoveredField701.size();
   }

   @Override
   public void method_00748(int var1, int var2, int var3, int var4, int var5, int var6, boolean var7) {
      if (var7) {
         Gui.a(this.recoveredField1780, var3 - 1, var2 + 1, var3 + var4 + 3, -2134851392);
         LegacyGlStateManager.method_28200(1.0F, 1.0F, 1.0F, 1.0F);
      }

      if (this.recoveredField3950) {
         if (0 <= var1 && var1 < this.recoveredField3951.size()) {
            this.method_03758(this.recoveredField3951.get(var1), var3);
         } else if (this.recoveredField3951.size() <= var1 && var1 < this.method_00746()) {
            super.method_00748(var1 - this.recoveredField3951.size(), var2, var3, var4, var5, var6, var7);
         }
      } else if (0 <= var1 && var1 < this.method_00746()) {
         super.method_00748(var1, var2, var3, var4, var5, var6, var7);
      }
   }

   public void method_03757(ResourcePackRepository.Entry var1) {
      if (this.recoveredField699.method_01950(var1)) {
         this.recoveredField701.remove(var1);
      }
   }

   public void method_03759(ResourcePackFolder var1, boolean var2) {
      if (var2) {
         if (var1.method_26764().equals("Back to Main Folder")) {
            this.recoveredField699.method_01954(null);
         } else if (var1.method_26761() != null) {
            this.recoveredField699.method_01954(var1.method_26761());
         } else {
            this.recoveredField699.method_01954(var1);
         }
      } else {
         this.recoveredField699.method_01954(var1);
      }
   }

   public ResourcePackFolderEntry(
      ResourcePackGui var1,
      int var2,
      int var3,
      int var4,
      int var5,
      int var6,
      List<ResourcePackRepository.Entry> var7,
      List<ResourcePackFolder> var8,
      boolean var9
   ) {
      super(var1, var2, var3, var4, var5, var6, I18n.format("resourcePack.available.title"), var7);
      this.recoveredField3951 = var8;
      this.recoveredField3950 = var9;
   }

   @Override
   public void method_00749(int var1, boolean var2) {
      if (this.recoveredField3950) {
         if (0 <= var1 && var1 < this.recoveredField3951.size()) {
            this.method_03759(this.recoveredField3951.get(var1), var1 == 0);
         } else if (this.recoveredField3951.size() <= var1 && var1 < this.method_00746()) {
            this.method_03757(this.recoveredField701.get(var1 - this.recoveredField3951.size()));
         }
      } else if (0 <= var1 && var1 < this.method_00746()) {
         this.method_03757(this.recoveredField701.get(var1));
      }
   }
}
