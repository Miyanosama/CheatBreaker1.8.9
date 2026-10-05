package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.resourcepack.ResourcePackGui;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapEntry;
import java.util.List;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiScreenHorseInventory;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.ResourcePackRepository$Entry;
import net.minecraft.util.ResourceLocation;

public class UnidentifiedClass0517 extends UnidentifiedClass0696 {
   public static ResourceLocation field_0003 = new ResourceLocation("client/icons/folder.png");
   public ConcurrentHashMapV8$MapEntry field_0005;
   public UnidentifiedClass5128 field_0002;
   public GuiScreenHorseInventory field_0004;
   public boolean field_0000;
   public List<UnidentifiedClass4443> field_0001;

   public void method_03758(UnidentifiedClass4443 var1, int var2) {
      boolean var3 = (Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0040.getValue();
      if (var3) {
         this.field_0000.getTextureManager().bindTexture(field_0003);
         UnidentifiedClass4676.method_28204();
         Gui.drawScaledCustomSizeModalRect(this.field_0014 + 2, var2 + 3, 0.0F, 0.0F, 256, 256, 32, 32, 256.0F, 256.0F);
         UnidentifiedClass4676.method_28199();
      }

      this.field_0000
         .fontRendererObj
         .drawString(
            UnidentifiedClass5065.method_30076(var1.method_26764(), this.field_0011 - 46), this.field_0014 + (var3 ? 36.0F : 2.0F), var2 + 2.0F, -1, true
         );
      int var4 = var1.method_26765().size() - 1;
      if (var4 != -1 && (Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0103.getValue()) {
         float var7 = var2 + 13.0F;
         if (var4 != 0) {
            String var6 = UnidentifiedClass5065.method_30076(var4 + (var4 == 1 ? " Subfolder" : " Subfolders"), this.field_0011 - 46);
            this.field_0000.fontRendererObj.drawString(var6, this.field_0014 + (var3 ? 36.0F : 2.0F), var7, -5592406, true);
            var7 += 10.0F;
         }

         int var5;
         if ((var5 = var1.method_26763().size()) != 0) {
            String var8 = UnidentifiedClass5065.method_30076(var5 + (var5 == 1 ? " Pack" : " Packs"), this.field_0011 - 46);
            this.field_0000.fontRendererObj.drawString(var8, this.field_0014 + (var3 ? 36.0F : 2.0F), var7, -5592406, true);
         }
      }
   }

   @Override
   public int method_00746() {
      return this.field_0000 ? this.field_0003.size() + this.field_0001.size() : this.field_0003.size();
   }

   @Override
   public void method_00748(int var1, int var2, int var3, int var4, int var5, int var6, boolean var7) {
      if (var7) {
         Gui.a(this.field_0014, var3 - 1, var2 + 1, var3 + var4 + 3, -2134851392);
         UnidentifiedClass4676.method_28200(1.0F, 1.0F, 1.0F, 1.0F);
      }

      if (this.field_0000) {
         if (0 <= var1 && var1 < this.field_0001.size()) {
            this.method_03758(this.field_0001.get(var1), var3);
         } else if (this.field_0001.size() <= var1 && var1 < this.method_00746()) {
            super.method_00748(var1 - this.field_0001.size(), var2, var3, var4, var5, var6, var7);
         }
      } else if (0 <= var1 && var1 < this.method_00746()) {
         super.method_00748(var1, var2, var3, var4, var5, var6, var7);
      }
   }

   public void method_03757(ResourcePackRepository$Entry var1) {
      if (this.field_0005.method_01950(var1)) {
         this.field_0003.remove(var1);
      }
   }

   public void method_03759(UnidentifiedClass4443 var1, boolean var2) {
      if (var2) {
         if (var1.method_26764().equals("Back to Main Folder")) {
            this.field_0005.method_01954(null);
         } else if (var1.method_26761() != null) {
            this.field_0005.method_01954(var1.method_26761());
         } else {
            this.field_0005.method_01954(var1);
         }
      } else {
         this.field_0005.method_01954(var1);
      }
   }

   public UnidentifiedClass0517(
      ResourcePackGui var1,
      int var2,
      int var3,
      int var4,
      int var5,
      int var6,
      List<ResourcePackRepository$Entry> var7,
      List<UnidentifiedClass4443> var8,
      boolean var9
   ) {
      super(var1, var2, var3, var4, var5, var6, I18n.format("resourcePack.available.title"), var7);
      this.field_0001 = var8;
      this.field_0000 = var9;
   }

   @Override
   public void method_00749(int var1, boolean var2) {
      if (this.field_0000) {
         if (0 <= var1 && var1 < this.field_0001.size()) {
            this.method_03759(this.field_0001.get(var1), var1 == 0);
         } else if (this.field_0001.size() <= var1 && var1 < this.method_00746()) {
            this.method_03757(this.field_0003.get(var1 - this.field_0001.size()));
         }
      } else if (0 <= var1 && var1 < this.method_00746()) {
         this.method_03757(this.field_0003.get(var1));
      }
   }
}
