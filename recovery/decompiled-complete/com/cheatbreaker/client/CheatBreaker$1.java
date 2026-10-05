package com.cheatbreaker.client;

import com.cheatbreaker.client.module.type.TeammatesModule;
import com.cheatbreaker.client.util.friend.Status;
import io.netty.handler.codec.http.HttpRequestDecoder;
import junit.extensions.TestDecorator;
import net.minecraft.block.BlockAnvil$Anvil;
import net.minecraft.client.gui.GuiPlayerTabOverlay$1;
import net.minecraft.command.CommandTrigger;

// $VF: synthetic class
public class CheatBreaker$1 {
   public GuiPlayerTabOverlay$1 field_0005;
   public TestDecorator field_0002;
   public HttpRequestDecoder field_0004;
   public BlockAnvil$Anvil field_0000;
   public CommandTrigger field_0001;
   public TeammatesModule field_0006;

   static {
      try {
         field_0003[Status.AWAY.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_0003[Status.BUSY.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_0003[Status.HIDDEN.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
