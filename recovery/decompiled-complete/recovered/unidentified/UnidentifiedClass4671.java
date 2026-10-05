package recovered.unidentified;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import net.minecraft.client.gui.GuiPageButtonList$GuiLabelEntry;
import net.minecraft.client.particle.EntityLavaFX$Factory;
import net.minecraft.client.renderer.GlStateManager$CullState;
import net.optifine.shaders.IShaderPack;
import net.optifine.util.StrUtils;

public class UnidentifiedClass4671 implements IShaderPack {
   public EntityLavaFX$Factory field_0001;
   public GlStateManager$CullState field_0003;
   public GuiPageButtonList$GuiLabelEntry field_0000;
   public File field_0002;

   @Override
   public InputStream getResourceAsStream(String var1) {
      try {
         String var2 = StrUtils.removePrefixSuffix(var1, "/", "/");
         File var3 = new File(this.field_0002, var2);
         return !var3.exists() ? null : new BufferedInputStream(new FileInputStream(var3));
      } catch (Exception var4) {
         return null;
      }
   }

   @Override
   public String getName() {
      return this.field_0002.getName();
   }

   @Override
   public boolean hasDirectory(String var1) {
      File var2 = new File(this.field_0002, var1.substring(1));
      return !var2.exists() ? false : var2.isDirectory();
   }

   @Override
   public void close() {
   }

   public UnidentifiedClass4671(String var1, File var2) {
      this.field_0002 = var2;
   }
}
