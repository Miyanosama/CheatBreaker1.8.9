package recovered.unidentified;

import com.jagrosh.discordipc.entities.Packet;
import io.netty.channel.oio.AbstractOioChannel$DefaultOioUnsafe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.renderer.entity.RenderItem$8;

public class UnidentifiedClass3330 implements GuiYesNoCallback {
   public AbstractOioChannel$DefaultOioUnsafe field_0001;
   public Packet field_0003;
   public RenderItem$8 field_0000;

   @Override
   public void confirmClicked(boolean var1, int var2) {
      if (var1) {
         this.field_0002.getTwitchStream().method_02395();
      }

      this.field_0002.displayGuiScreen((GuiScreen)null);
   }

   public UnidentifiedClass3330(Minecraft var1) {
      this.field_0002 = var1;
      super();
   }
}
