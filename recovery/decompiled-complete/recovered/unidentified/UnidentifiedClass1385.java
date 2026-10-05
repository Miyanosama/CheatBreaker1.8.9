package recovered.unidentified;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.network.status.server.S01PacketPong;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.storage.AnvilSaveConverter;
import org.apache.log4j.jmx.HierarchyDynamicMBean;
import org.lwjgl.opengl.GL11;

public class UnidentifiedClass1385 extends AbstractModule {
   public Setting field_0002;
   public Setting field_0003;
   public WorldChunkManager field_0000;
   public S01PacketPong field_0001;
   public Setting field_0005;
   public HierarchyDynamicMBean field_0004;
   public AnvilSaveConverter field_0006;

   public UnidentifiedClass1385() {
      super("FPS");
      this.setDefaultAnchor(CBGuiAnchor.RIGHT_TOP);
      this.setDefaultTranslations(0.0F, 0.0F);
      this.setState(false);
      this.field_0005 = new Setting(this, "Show Background").setValue(true);
      this.field_0003 = new Setting(this, "Text Color").setValue(-1).setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE);
      this.field_0002 = new Setting(this, "Background Color").setValue(1862270976).setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE);
      this.setPreviewLabel("[144 FPS]", 1.4F);
      this.method_28820(GuiDrawEvent.class, this::method_09503);
   }

   public void method_09503(GuiDrawEvent var1) {
      if (this.method_28866()) {
         GL11.glPushMatrix();
         this.scaleAndTranslate(var1.getResolution());
         if ((Boolean)this.field_0005.getValue()) {
            this.method_28812(56.0F, 18.0F);
            Gui.drawRect(0.0F, 0.0F, 56.0F, 13.0F, this.field_0002.method_08901());
            String var2 = Minecraft.debugFPS + " FPS";
            this.minecraft
               .fontRendererObj
               .drawString(var2, (int)(this.field_0041 / 2.0F - this.minecraft.fontRendererObj.getStringWidth(var2) / 2), 3, this.field_0003.method_08901());
         } else {
            String var3 = "[" + Minecraft.debugFPS + " FPS]";
            this.method_28812(
               this.minecraft
                  .fontRendererObj
                  .drawString(
                     var3, this.field_0041 / 2.0F - this.minecraft.fontRendererObj.getStringWidth(var3) / 2, 0.0F, this.field_0003.method_08901(), true
                  ),
               18.0F
            );
         }

         GL11.glPopMatrix();
      }
   }
}
