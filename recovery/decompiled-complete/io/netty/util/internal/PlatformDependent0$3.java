package io.netty.util.internal;

import java.security.PrivilegedAction;
import net.minecraft.client.gui.GuiScreenOptionsSounds$Button;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.resources.SkinManager$2;
import net.minecraft.entity.passive.EntitySquid;
import net.optifine.render.AabbFrame;
import net.optifine.render.VboRegion;

public class PlatformDependent0$3 implements PrivilegedAction<ClassLoader> {
   public EntitySquid __junk7255362582018454934;
   public VboRegion __junk3631056599024370363;
   public SkinManager$2 __junk9054973619394359735;
   public AabbFrame __junk1840487683288248688;
   public GuiScreenOptionsSounds$Button __junk1356044263342779609;
   public TileEntityItemStackRenderer __junk7202739893934470250;

   public ClassLoader run() {
      return ClassLoader.getSystemClassLoader();
   }
}
