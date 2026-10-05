package com.cheatbreaker.client.ui.resourcepack;

import com.cheatbreaker.client.ui.resourcepack.SelectedResourcePackEntry$EnumSwitch;

import com.cheatbreaker.client.util.render.LegacyGlStateManager;

import com.cheatbreaker.client.ui.resourcepack.ResourcePackEntryElement;

import com.cheatbreaker.client.ui.resourcepack.ResourcePackFolder;

import com.cheatbreaker.client.ui.resourcepack.ResourcePackGui;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.util.ResourceLocation;

public class SelectedResourcePackEntry extends ResourcePackEntryElement {
   public ResourcePackGui recoveredField1776;
   public static ResourceLocation recoveredField1777 = new ResourceLocation("textures/gui/resource_packs.png");
   public ResourcePackEntryAction recoveredField1778;

   public void method_25688(ResourcePackFolder var1, File var2, ResourcePackRepository.Entry var3) {
      if (var1.method_26764().equals(var2.getName())) {
         if (this.recoveredField1776.method_01959()) {
            if (var1.method_26763().contains(var3)) {
               super.recoveredField699.method_01948(var3);
            }
         } else {
            if (!var1.method_26763().contains(var3)) {
               var1.method_26763().add(var3);
            }

            this.recoveredField1776.method_01953(var1.method_26763());
         }
      } else {
         for (ResourcePackFolder var5 : var1.method_26765()) {
            this.method_25688(var5, var2, var3);
         }
      }
   }

   public SelectedResourcePackEntry(ResourcePackGui var1, int var2, int var3, int var4, int var5, int var6, List<ResourcePackRepository.Entry> var7) {
      super(var1, var2, var3, var4, var5, var6, I18n.format("resourcePack.selected.title"), var7);
      this.recoveredField1776 = var1;
      this.recoveredField1778 = ResourcePackEntryAction.MOVE_BACK;
   }

   public void method_25687(File var1, ResourcePackRepository.Entry var2) {
      for (File var6 : Objects.requireNonNull(var1.listFiles())) {
         if (ResourcePackFolderScanner.method_26232(var6)) {
            Optional var7;
            if (var6.getName().equals(var2.getResourcePackName())
               && (var7 = ResourcePackFolderScanner.method_26230(var6)).isPresent()
               && ((ResourcePackRepository.Entry)var7.get()).equals(var2)) {
               for (ResourcePackFolder var9 : this.recoveredField1776.method_01947()) {
                  this.method_25688(var9, var1, var2);
               }
            }
         } else if (ResourcePackFolderScanner.method_26228(var6)) {
            this.method_25687(var6, var2);
         }
      }
   }

   public void method_25686(ResourcePackRepository.Entry var1) {
      for (File var5 : Objects.requireNonNull(this.recoveredField700.getResourcePackRepository().getDirResourcepacks().listFiles())) {
         if (ResourcePackFolderScanner.method_26232(var5)) {
            Optional var6;
            if (var5.getName().equals(var1.getResourcePackName())
               && (var6 = ResourcePackFolderScanner.method_26230(var5)).isPresent()
               && ((ResourcePackRepository.Entry)var6.get()).equals(var1)) {
               if (this.recoveredField1776.method_01959()) {
                  super.recoveredField699.method_01948(var1);
               } else {
                  super.recoveredField699.method_01957(var1);
               }
            }
         } else if (ResourcePackFolderScanner.method_26228(var5)) {
            this.method_25687(var5, var1);
         }
      }
   }

   @Override
   public void method_00748(int var1, int var2, int var3, int var4, int var5, int var6, boolean var7) {
      if (var7) {
         Gui.a(this.recoveredField1780, var3 - 1, var2 + 1, var3 + var4 + 3, -2134851392);
         LegacyGlStateManager.method_28200(1.0F, 1.0F, 1.0F, 1.0F);
      }

      super.method_00748(var1, var2, var3, var4, var5, var6, var7);
      if (var7) {
         LegacyGlStateManager.method_28200(1.0F, 1.0F, 1.0F, 1.0F);
         this.recoveredField700.getTextureManager().bindTexture(recoveredField1777);
         if (var2 - 40 <= var5 && var5 < var2) {
            if (var3 <= var6 && var6 < var3 + var4 / 2 - 2) {
               if (var1 == 0) {
                  if (this.recoveredField1778 == ResourcePackEntryAction.MOVE_DOWN) {
                     this.recoveredField1778 = ResourcePackEntryAction.MOVE_BACK;
                  }
               } else {
                  this.recoveredField1778 = ResourcePackEntryAction.MOVE_UP;
                  Gui.drawModalRectWithCustomSizedTexture(var2 - 40, var3, 96.0F, 32.0F, 32, 32, 256.0F, 256.0F);
               }

               if (var1 != super.method_00746() - 1) {
                  Gui.drawModalRectWithCustomSizedTexture(var2 - 40, var3, 64.0F, 0.0F, 32, 32, 256.0F, 256.0F);
               }
            } else {
               if (var1 != 0) {
                  Gui.drawModalRectWithCustomSizedTexture(var2 - 40, var3, 96.0F, 0.0F, 32, 32, 256.0F, 256.0F);
               }

               if (var1 == super.method_00746() - 1) {
                  if (this.recoveredField1778 == ResourcePackEntryAction.MOVE_UP) {
                     this.recoveredField1778 = ResourcePackEntryAction.MOVE_BACK;
                  }
               } else {
                  this.recoveredField1778 = ResourcePackEntryAction.MOVE_DOWN;
                  Gui.drawModalRectWithCustomSizedTexture(var2 - 40, var3, 64.0F, 32.0F, 32, 32, 256.0F, 256.0F);
               }
            }
         } else {
            this.recoveredField1778 = ResourcePackEntryAction.MOVE_BACK;
            if (var1 != 0) {
               Gui.drawModalRectWithCustomSizedTexture(var2 - 40, var3, 96.0F, 0.0F, 32, 32, 256.0F, 256.0F);
            }

            if (var1 != super.method_00746() - 1) {
               Gui.drawModalRectWithCustomSizedTexture(var2 - 40, var3, 64.0F, 0.0F, 32, 32, 256.0F, 256.0F);
            }
         }
      }
   }

   @Override
   public void method_00749(int var1, boolean var2) {
      if (0 <= var1 && var1 < super.method_00746()) {
         switch (SelectedResourcePackEntry$EnumSwitch.recoveredField263[this.recoveredField1778.ordinal()]) {
            case 1:
               if (var1 - 1 >= 0) {
                  Collections.swap(this.recoveredField701, var1, var1 - 1);
               }
               break;
            case 2:
               if (var1 + 1 < super.method_00746()) {
                  Collections.swap(this.recoveredField701, var1, var1 + 1);
               }
               break;
            default:
               ResourcePackRepository.Entry var3 = this.recoveredField701.get(var1);
               this.method_25686(var3);
               this.recoveredField701.remove(var3);
         }
      }
   }
}
