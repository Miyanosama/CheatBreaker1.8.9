package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.module.type.ComboCounterModule;
import com.cheatbreaker.client.ui.resourcepack.ResourcePackGui;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.resources.ResourcePackRepository$Entry;
import net.minecraft.init.Bootstrap$14;
import net.minecraft.server.network.NetHandlerHandshakeTCP;
import net.minecraft.util.LoggingPrintStream;

public abstract class UnidentifiedClass0696 extends UnidentifiedClass0089 {
   public ComboCounterModule field_0006;
   public ResourcePackGui field_0005;
   public LoggingPrintStream field_0001;
   public NetHandlerHandshakeTCP field_0007;
   public Minecraft field_0000;
   public List<ResourcePackRepository$Entry> field_0003;
   public Bootstrap$14 field_0004;
   public UnidentifiedClass1381 field_0002;

   @Override
   public void method_00748(int var1, int var2, int var3, int var4, int var5, int var6, boolean var7) {
      if (0 <= var1 && var1 < this.method_00746()) {
         ResourcePackRepository$Entry var8 = this.field_0003.get(var1);
         boolean var9 = (Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0061.getValue();
         if (var9) {
            var8.bindTexturePackIcon(this.field_0000.getTextureManager());
            Gui.drawModalRectWithCustomSizedTexture(this.field_0014 + 2, var3 + 1, 0.0F, 0.0F, 32, 32, 32.0F, 32.0F);
         }

         this.field_0000
            .fontRendererObj
            .drawString(
               UnidentifiedClass5065.method_30076(var8.getResourcePackName(), this.field_0011 - 46),
               this.field_0014 + (var9 ? 36.0F : 2.0F),
               var3 + 2.0F,
               -1,
               true
            );
         if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0030.getValue()) {
            List var10 = this.field_0000.fontRendererObj.listFormattedStringToWidth(var8.getTexturePackDescription(), this.field_0011 - 46);

            for (int var11 = 0; var11 < var10.size(); var11++) {
               String var12 = (String)var10.get(var11);
               if (var11 == 1 && var10.size() > 2) {
                  var12 = UnidentifiedClass5065.method_30076(var12, this.field_0011 - 46);
               }

               this.field_0000.fontRendererObj.drawString(var12, this.field_0014 + (var9 ? 36.0F : 2.0F), var3 + 13.0F + 10.0F * var11, -5592406, true);
               if (var11 == 1) {
                  break;
               }
            }
         }
      }
   }

   @Override
   public void method_00751() {
      Gui.a(this.field_0014, this.field_0004, this.field_0009, this.field_0008, CheatBreaker.getInstance().getGlobalSettings().field_0050.method_08901());
   }

   @Override
   public int method_00746() {
      return this.field_0003.size();
   }

   public List<ResourcePackRepository$Entry> method_04862() {
      return Collections.unmodifiableList(this.field_0003);
   }

   public UnidentifiedClass0696(ResourcePackGui var1, int var2, int var3, int var4, int var5, int var6, String var7, List<ResourcePackRepository$Entry> var8) {
      super(var1.j, var2, var3, var4, var5, var6, var7);
      this.field_0005 = var1;
      this.field_0000 = var1.j;
      this.field_0003 = var8;
   }

   @Override
   public abstract void method_00749(int var1, boolean var2);
}
