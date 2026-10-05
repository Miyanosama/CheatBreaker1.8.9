package recovered.unidentified;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javazoom.jl.player.AudioDeviceFactory;
import net.minecraft.client.resources.ResourcePackRepository$Entry;
import net.minecraft.util.DamageSource;
import org.apache.log4j.net.ZeroConfSupport;

public class UnidentifiedClass4443 {
   public AudioDeviceFactory field_0003;
   public DamageSource field_0006;
   public List<ResourcePackRepository$Entry> field_0002;
   public List<ResourcePackRepository$Entry> field_0005;
   public String field_0000;
   public ZeroConfSupport field_0001;
   public UnidentifiedClass4443 field_0007;
   public List<UnidentifiedClass4443> field_0004;

   public List<ResourcePackRepository$Entry> method_26763() {
      return this.field_0005;
   }

   public List<UnidentifiedClass4443> method_26765() {
      return this.field_0004;
   }

   public UnidentifiedClass4443(File var1, List<ResourcePackRepository$Entry> var2) {
      this(var1, var2, null);
   }

   public UnidentifiedClass4443(File var1, List<ResourcePackRepository$Entry> var2, UnidentifiedClass4443 var3) {
      this.field_0000 = var1.getName();
      this.field_0005 = new ArrayList<>();
      this.field_0002 = var2;
      this.field_0004 = new ArrayList<>();
      this.field_0007 = var3;
      this.field_0004.add(new UnidentifiedClass4443(var3));

      for (File var7 : Objects.requireNonNull(var1.listFiles())) {
         if (!UnidentifiedClass4327.method_26232(var7)) {
            if (UnidentifiedClass4327.method_26228(var7)) {
               this.field_0004.add(new UnidentifiedClass4443(var7, var2, this));
            }
         } else {
            for (ResourcePackRepository$Entry var9 : this.field_0002) {
               if (var9.getResourcePackName().equals(var7.getName())) {
                  Optional var10 = UnidentifiedClass4327.method_26230(var7);
                  if (var10.isPresent() && ((ResourcePackRepository$Entry)var10.get()).equals(var9)) {
                     break;
                  }
               }
            }

            UnidentifiedClass4327.method_26230(var7).ifPresent(this.field_0005::add);
         }
      }
   }

   public List<ResourcePackRepository$Entry> method_26762() {
      return this.field_0002;
   }

   public UnidentifiedClass4443(UnidentifiedClass4443 var1) {
      this.field_0000 = "Back to " + (var1 == null ? "Main Folder" : var1.field_0000);
      this.field_0005 = new ArrayList<>();
      this.field_0002 = new ArrayList<>();
      this.field_0004 = new ArrayList<>();
      this.field_0007 = var1;
   }

   public String method_26764() {
      return this.field_0000;
   }

   public UnidentifiedClass4443 method_26761() {
      return this.field_0007;
   }
}
