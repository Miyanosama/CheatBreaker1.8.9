package net.minecraft.network.play.server;

import com.cheatbreaker.client.module.type.cooldowns.CooldownRenderer;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import net.minecraft.block.BlockSand$EnumType;
import net.minecraft.client.renderer.BlockRendererDispatcher;

// $VF: synthetic class
public class S38PacketPlayerListItem$1 {
   public CooldownRenderer field_0002;
   public OverlayGui field_0004;
   public BlockRendererDispatcher field_0001;
   public BlockSand$EnumType field_0003;

   static {
      try {
         field_0000[S38PacketPlayerListItem$Action.ADD_PLAYER.ordinal()] = 1;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field_0000[S38PacketPlayerListItem$Action.UPDATE_GAME_MODE.ordinal()] = 2;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_0000[S38PacketPlayerListItem$Action.UPDATE_LATENCY.ordinal()] = 3;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_0000[S38PacketPlayerListItem$Action.UPDATE_DISPLAY_NAME.ordinal()] = 4;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_0000[S38PacketPlayerListItem$Action.REMOVE_PLAYER.ordinal()] = 5;
      } catch (NoSuchFieldError var1) {
      }
   }
}
