package recovered.unidentified;

import com.cheatbreaker.client.ui.AbstractGui;
import com.cheatbreaker.client.ui.mainmenu.LegacyMainMenu;
import com.cheatbreaker.client.ui.mainmenu.MainMenu;
import io.netty.channel.CompleteChannelFuture;
import net.minecraft.client.gui.GuiScreen;
import net.optifine.config.ConnectedParser$1;

public enum UnidentifiedEnum3640 {
   field_0003,
   field_0000,
   field_0001;

   public AbstractGui field_0006;
   public UnidentifiedClass4671 field_0002;
   public CompleteChannelFuture field_0005;
   public ConnectedParser$1 field_0004;

   public static GuiScreen method_22258(UnidentifiedEnum3640 var0) {
      switch (UnidentifiedClass4985.field_0001[var0.ordinal()]) {
         case 1:
            return new UnidentifiedClass0717();
         case 2:
            return new LegacyMainMenu();
         default:
            return new MainMenu();
      }
   }

   public static UnidentifiedEnum3640 method_22257(String var0) {
      return Enum.valueOf(UnidentifiedEnum3640.class, var0);
   }
}
