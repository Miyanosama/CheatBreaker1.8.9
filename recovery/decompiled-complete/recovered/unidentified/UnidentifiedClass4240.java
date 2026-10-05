package recovered.unidentified;

import com.cheatbreaker.client.ui.resourcepack.ResourcePackGui;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.ResourcePackRepository$Entry;
import net.minecraft.crash.CrashReport$5;
import net.minecraft.network.ServerStatusResponse$Serializer;
import net.minecraft.util.ResourceLocation;

public class UnidentifiedClass4240 extends UnidentifiedClass0696 {
   public CrashReport$5 field_0002;
   public ResourcePackGui field_0004;
   public static ResourceLocation field_0001 = new ResourceLocation("textures/gui/resource_packs.png");
   public ServerStatusResponse$Serializer field_0003;
   public UnidentifiedEnum4287 field_0000;

   public void method_25688(UnidentifiedClass4443 var1, File var2, ResourcePackRepository$Entry var3) {
      if (var1.method_26764().equals(var2.getName())) {
         if (this.field_0004.method_01959()) {
            if (var1.method_26763().contains(var3)) {
               super.field_0005.method_01948(var3);
            }
         } else {
            if (!var1.method_26763().contains(var3)) {
               var1.method_26763().add(var3);
            }

            this.field_0004.method_01953(var1.method_26763());
         }
      } else {
         for (UnidentifiedClass4443 var5 : var1.method_26765()) {
            this.method_25688(var5, var2, var3);
         }
      }
   }

   public UnidentifiedClass4240(ResourcePackGui var1, int var2, int var3, int var4, int var5, int var6, List<ResourcePackRepository$Entry> var7) {
      super(var1, var2, var3, var4, var5, var6, I18n.format("resourcePack.selected.title"), var7);
      this.field_0004 = var1;
      this.field_0000 = UnidentifiedEnum4287.field_0005;
   }

   public void method_25687(File var1, ResourcePackRepository$Entry var2) {
      for (File var6 : Objects.requireNonNull(var1.listFiles())) {
         if (UnidentifiedClass4327.method_26232(var6)) {
            Optional var7;
            if (var6.getName().equals(var2.getResourcePackName())
               && (var7 = UnidentifiedClass4327.method_26230(var6)).isPresent()
               && ((ResourcePackRepository$Entry)var7.get()).equals(var2)) {
               for (UnidentifiedClass4443 var9 : this.field_0004.method_01947()) {
                  this.method_25688(var9, var1, var2);
               }
            }
         } else if (UnidentifiedClass4327.method_26228(var6)) {
            this.method_25687(var6, var2);
         }
      }
   }

   public void method_25686(ResourcePackRepository$Entry var1) {
      for (File var5 : Objects.requireNonNull(this.field_0000.getResourcePackRepository().getDirResourcepacks().listFiles())) {
         if (UnidentifiedClass4327.method_26232(var5)) {
            Optional var6;
            if (var5.getName().equals(var1.getResourcePackName())
               && (var6 = UnidentifiedClass4327.method_26230(var5)).isPresent()
               && ((ResourcePackRepository$Entry)var6.get()).equals(var1)) {
               if (this.field_0004.method_01959()) {
                  super.field_0005.method_01948(var1);
               } else {
                  super.field_0005.method_01957(var1);
               }
            }
         } else if (UnidentifiedClass4327.method_26228(var5)) {
            this.method_25687(var5, var1);
         }
      }
   }

   @Override
   public void method_00748(int var1, int var2, int var3, int var4, int var5, int var6, boolean var7) {
      if (var7) {
         Gui.a(this.field_0014, var3 - 1, var2 + 1, var3 + var4 + 3, -2134851392);
         UnidentifiedClass4676.method_28200(1.0F, 1.0F, 1.0F, 1.0F);
      }

      super.method_00748(var1, var2, var3, var4, var5, var6, var7);
      if (var7) {
         UnidentifiedClass4676.method_28200(1.0F, 1.0F, 1.0F, 1.0F);
         this.field_0000.getTextureManager().bindTexture(field_0001);
         if (var2 - 40 <= var5 && var5 < var2) {
            if (var3 <= var6 && var6 < var3 + var4 / 2 - 2) {
               if (var1 == 0) {
                  if (this.field_0000 == UnidentifiedEnum4287.field_0002) {
                     this.field_0000 = UnidentifiedEnum4287.field_0005;
                  }
               } else {
                  this.field_0000 = UnidentifiedEnum4287.field_0000;
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
                  if (this.field_0000 == UnidentifiedEnum4287.field_0000) {
                     this.field_0000 = UnidentifiedEnum4287.field_0005;
                  }
               } else {
                  this.field_0000 = UnidentifiedEnum4287.field_0002;
                  Gui.drawModalRectWithCustomSizedTexture(var2 - 40, var3, 64.0F, 32.0F, 32, 32, 256.0F, 256.0F);
               }
            }
         } else {
            this.field_0000 = UnidentifiedEnum4287.field_0005;
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
         switch (UnidentifiedClass1135.field_0000[this.field_0000.ordinal()]) {
            case 1:
               if (var1 - 1 >= 0) {
                  Collections.swap(this.field_0003, var1, var1 - 1);
               }
               break;
            case 2:
               if (var1 + 1 < super.method_00746()) {
                  Collections.swap(this.field_0003, var1, var1 + 1);
               }
               break;
            default:
               ResourcePackRepository$Entry var3 = this.field_0003.get(var1);
               this.method_25686(var3);
               this.field_0003.remove(var3);
         }
      }
   }
}
