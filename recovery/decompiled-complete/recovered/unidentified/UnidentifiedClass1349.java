package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.event.type.TickEvent;
import io.netty.handler.codec.http.ComposedLastHttpContent;
import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.java_websocket.framing.CloseFrame;
import org.lwjgl.input.Keyboard;

public class UnidentifiedClass1349 {
   public ComposedLastHttpContent field_0003;
   public CloseFrame field_0005;
   public UnidentifiedClass4541 field_0002;
   public UnidentifiedClass3543 field_0004;
   public boolean field_0000;
   public Minecraft field_0001 = Minecraft.getMinecraft();

   public UnidentifiedClass1349() {
      CheatBreaker.getInstance().method_19817().method_21938(TickEvent.class, this::method_09303);
   }

   public void method_09303(TickEvent var1) {
      boolean var2 = this.field_0001.currentScreen != null;
      GlobalSettings var3 = CheatBreaker.getInstance().getGlobalSettings();
      if (!var2 && this.field_0000 && var3.field_0092.method_08908()) {
         Class<GameSettings> var4 = GameSettings.class;
         Field[] var5 = var4.getDeclaredFields();

         for (Field var9 : var5) {
            if (var9.getType().equals(KeyBinding.class)) {
               var9.setAccessible(true);

               try {
                  KeyBinding var10 = (KeyBinding)var9.get(this.field_0001.gameSettings);
                  boolean var11 = !var3.field_0045.method_08908() || !var10.getKeyDescription().equalsIgnoreCase("key.sneak");
                  boolean var12 = !var3.field_0011.method_08908() || !var10.getKeyDescription().equalsIgnoreCase("key.use");
                  if (!var10.getKeyDescription().equalsIgnoreCase("key.inventory")
                     && !var10.getKeyDescription().equalsIgnoreCase("key.chat")
                     && !var10.getKeyDescription().equalsIgnoreCase("key.command")
                     && var11
                     && var12
                     && var10.getKeyCode() > 0
                     && Keyboard.isKeyDown(var10.getKeyCode())) {
                     KeyBinding.setKeyBindState(var10.getKeyCode(), true);
                     KeyBinding.onTick(var10.getKeyCode());
                  }
               } catch (Exception var13) {
                  var13.printStackTrace();
               }
            }
         }
      }

      this.field_0000 = var2;
   }
}
