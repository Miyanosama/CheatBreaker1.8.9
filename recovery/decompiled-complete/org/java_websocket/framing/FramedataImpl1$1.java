package org.java_websocket.framing;

import io.netty.channel.socket.DefaultSocketChannelConfig;
import net.minecraft.client.gui.GuiCustomizeWorldScreen$1;
import net.minecraft.client.renderer.RenderGlobal$ContainerLocalRenderInformation;
import net.minecraft.command.CommandShowSeed;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0APacketAnimation;
import org.java_websocket.enums.Opcode;

// $VF: synthetic class
public class FramedataImpl1$1 {
   public GuiCustomizeWorldScreen$1 field_0003;
   public RenderGlobal$ContainerLocalRenderInformation field_0002;
   public DefaultSocketChannelConfig field_0004;
   public C0APacketAnimation field_0000;
   public C03PacketPlayer field_0001;
   public CommandShowSeed field_0006;

   static {
      try {
         $SwitchMap$org$java_websocket$enums$Opcode[Opcode.PING.ordinal()] = 1;
      } catch (NoSuchFieldError var6) {
      }

      try {
         $SwitchMap$org$java_websocket$enums$Opcode[Opcode.PONG.ordinal()] = 2;
      } catch (NoSuchFieldError var5) {
      }

      try {
         $SwitchMap$org$java_websocket$enums$Opcode[Opcode.TEXT.ordinal()] = 3;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$org$java_websocket$enums$Opcode[Opcode.BINARY.ordinal()] = 4;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$org$java_websocket$enums$Opcode[Opcode.CLOSING.ordinal()] = 5;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$org$java_websocket$enums$Opcode[Opcode.CONTINUOUS.ordinal()] = 6;
      } catch (NoSuchFieldError var1) {
      }
   }
}
