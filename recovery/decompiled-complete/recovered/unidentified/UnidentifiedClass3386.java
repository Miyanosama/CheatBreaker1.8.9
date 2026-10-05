package recovered.unidentified;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiClickableScrolledSelectionListProxy;
import net.minecraft.crash.CrashReportCategory$3;
import net.minecraft.util.Vec3;
import net.optifine.gui.GuiAnimationSettingsOF;

public class UnidentifiedClass3386 {
   public CrashReportCategory$3 field_0001;
   public static UnidentifiedClass3386 field_0003 = new UnidentifiedClass3386();
   public GuiClickableScrolledSelectionListProxy field_0000;
   public GuiAnimationSettingsOF field_0002;

   public Vec3 method_21047() {
      double var1 = Minecraft.getMinecraft().gameSettings.fovSetting;
      double var3 = var1 / 110.0;
      return new Vec3(-var3 + var3 / 2.5 - var3 / 8.0 + 0.16, 0.0, 0.4);
   }
}
