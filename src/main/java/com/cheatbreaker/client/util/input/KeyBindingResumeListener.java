package com.cheatbreaker.client.util.input;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.event.type.TickEvent;
import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyBindingResumeListener {
   public boolean recoveredField3022;
   public Minecraft recoveredField3023 = Minecraft.getMinecraft();

   public KeyBindingResumeListener() {
      CheatBreaker.getInstance().method_19817().method_21938(TickEvent.class, this::method_09303);
   }

   public void method_09303(TickEvent var1) {
      boolean var2 = this.recoveredField3023.currentScreen != null;
      GlobalSettings var3 = CheatBreaker.getInstance().getGlobalSettings();
      if (!var2 && this.recoveredField3022 && var3.recoveredField492.method_08908()) {
         Class<GameSettings> var4 = GameSettings.class;
         Field[] var5 = var4.getDeclaredFields();

         for (Field var9 : var5) {
            if (var9.getType().equals(KeyBinding.class)) {
               var9.setAccessible(true);

               try {
                  KeyBinding var10 = (KeyBinding)var9.get(this.recoveredField3023.gameSettings);
                  boolean var11 = !var3.recoveredField588.method_08908() || !var10.getKeyDescription().equalsIgnoreCase("key.sneak");
                  boolean var12 = !var3.recoveredField579.method_08908() || !var10.getKeyDescription().equalsIgnoreCase("key.use");
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

      this.recoveredField3022 = var2;
   }
}
