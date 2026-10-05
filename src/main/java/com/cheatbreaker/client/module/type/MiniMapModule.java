package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.module.ModuleRule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import net.minecraft.util.ResourceLocation;

public class MiniMapModule extends AbstractModule {
   public static ModuleRule state = ModuleRule.NEUTRAL;

   public void onDraw(GuiDrawEvent var1) {
   }

   @Override
   public void addAllEvents() {
      super.addAllEvents();
      if (state == ModuleRule.FORCED_OFF) {
         CheatBreaker.getInstance()
            .getModuleManager()
            .notifications
            .queueNotification("Error", "&4Minimap &fis not allowed on this server. Some functions may not work.", 4000L);
      }
   }

   public MiniMapModule() {
      super("Zans Minimap");
      this.setDefaultState(false);
      this.recoveredField3912 = false;
      this.setDefaultAnchor(CBGuiAnchor.RIGHT_TOP);
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/zans.png"), 42, 42);
      this.method_28821("Adds a minimap on the top right and allows you to manage waypoints.");
      this.method_28829("Zan");
      this.method_28820(GuiDrawEvent.class, this::onDraw);
   }
}
