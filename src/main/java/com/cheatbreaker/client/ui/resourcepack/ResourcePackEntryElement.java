package com.cheatbreaker.client.ui.resourcepack;

import com.cheatbreaker.client.ui.resourcepack.ResourcePackSlotElement;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.resourcepack.ResourcePackGui;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.resources.ResourcePackRepository;

public abstract class ResourcePackEntryElement extends ResourcePackSlotElement {
   public ResourcePackGui recoveredField699;
   public Minecraft recoveredField700;
   public List<ResourcePackRepository.Entry> recoveredField701;

   @Override
   public void method_00748(int var1, int var2, int var3, int var4, int var5, int var6, boolean var7) {
      if (0 <= var1 && var1 < this.method_00746()) {
         ResourcePackRepository.Entry var8 = this.recoveredField701.get(var1);
         boolean var9 = (Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField489.getValue();
         if (var9) {
            var8.bindTexturePackIcon(this.recoveredField700.getTextureManager());
            Gui.drawModalRectWithCustomSizedTexture(this.recoveredField1780 + 2, var3 + 1, 0.0F, 0.0F, 32, 32, 32.0F, 32.0F);
         }

         this.recoveredField700
            .fontRendererObj
            .drawString(
               ResourcePackText.method_30076(var8.getResourcePackName(), this.recoveredField1790 - 46),
               this.recoveredField1780 + (var9 ? 36.0F : 2.0F),
               var3 + 2.0F,
               -1,
               true
            );
         if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField525.getValue()) {
            List var10 = this.recoveredField700.fontRendererObj.listFormattedStringToWidth(var8.getTexturePackDescription(), this.recoveredField1790 - 46);

            for (int var11 = 0; var11 < var10.size(); var11++) {
               String var12 = (String)var10.get(var11);
               if (var11 == 1 && var10.size() > 2) {
                  var12 = ResourcePackText.method_30076(var12, this.recoveredField1790 - 46);
               }

               this.recoveredField700
                  .fontRendererObj
                  .drawString(var12, this.recoveredField1780 + (var9 ? 36.0F : 2.0F), var3 + 13.0F + 10.0F * var11, -5592406, true);
               if (var11 == 1) {
                  break;
               }
            }
         }
      }
   }

   @Override
   public void method_00751() {
      Gui.a(
         this.recoveredField1780,
         this.recoveredField1787,
         this.recoveredField1792,
         this.recoveredField1797,
         CheatBreaker.getInstance().getGlobalSettings().recoveredField568.method_08901()
      );
   }

   @Override
   public int method_00746() {
      return this.recoveredField701.size();
   }

   public List<ResourcePackRepository.Entry> method_04862() {
      return Collections.unmodifiableList(this.recoveredField701);
   }

   public ResourcePackEntryElement(ResourcePackGui var1, int var2, int var3, int var4, int var5, int var6, String var7, List<ResourcePackRepository.Entry> var8) {
      super(var1.j, var2, var3, var4, var5, var6, var7);
      this.recoveredField699 = var1;
      this.recoveredField700 = var1.j;
      this.recoveredField701 = var8;
   }

   @Override
   public abstract void method_00749(int var1, boolean var2);
}
