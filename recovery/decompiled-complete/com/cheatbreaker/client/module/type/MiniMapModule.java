package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.module.ModuleRule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import net.minecraft.block.BlockIce;
import net.minecraft.block.state.pattern.BlockPattern$PatternHelper;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.command.CommandKill;
import net.minecraft.util.ResourceLocation;

public class MiniMapModule extends AbstractModule {
   public CommandKill field_0002;
   public BlockPattern$PatternHelper field_0003;
   public AbstractClientPlayer field_0000;
   public BlockIce field_0001;
   public static ModuleRule state = ModuleRule.MINIMAP_NOT_ALLOWED;

   public void onDraw(GuiDrawEvent var1) {
   }

   @Override
   public void addAllEvents() {
      super.addAllEvents();
      if (state == ModuleRule.field_0003) {
         CheatBreaker.getInstance()
            .getModuleManager()
            .notifications
            .queueNotification("Error", "&4Minimap &fis not allowed on this server. Some functions may not work.", 1375965154L & -3735968765557334104L);
      }
   }

   public MiniMapModule() {
      super("Zans Minimap");
      this.setDefaultState(false);
      this.field_0020 = false;
      this.setDefaultAnchor(CBGuiAnchor.RIGHT_TOP);
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/zans.png"), 42, 42);
      this.method_28821("Adds a minimap on the top right and allows you to manage waypoints.");
      this.method_28829("Zan");
      this.method_28820(GuiDrawEvent.class, this::onDraw);
   }
}
