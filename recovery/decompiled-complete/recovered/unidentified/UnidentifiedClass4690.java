package recovered.unidentified;

import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.renderer.entity.RenderSkeleton$1;
import net.minecraft.tileentity.TileEntityBanner$EnumBannerPattern;
import net.minecraft.util.IChatComponent;

public class UnidentifiedClass4690 extends ChatLine {
   public TileEntityBanner$EnumBannerPattern field_0001;
   public RenderSkeleton$1 field_0002;
   public boolean field_0000;

   public void method_28279(boolean var1) {
      this.field_0000 = var1;
   }

   public boolean method_28277() {
      return this.field_0000;
   }

   public static UnidentifiedClass4690 method_28278(ChatLine var0) {
      return new UnidentifiedClass4690(var0.getUpdatedCounter(), var0.getChatComponent(), var0.getChatLineID());
   }

   public UnidentifiedClass4690(int var1, IChatComponent var2, int var3) {
      super(var1, var2, var3);
   }
}
