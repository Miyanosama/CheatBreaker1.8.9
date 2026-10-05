package org.java_websocket.enums;

import io.netty.channel.socket.DefaultServerSocketChannelConfig;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.inventory.ContainerRepair;

public enum Opcode {
   BINARY,
   CONTINUOUS,
   PING,
   PONG,
   TEXT,
   CLOSING;
   // $VF: synthetic field
   public static Opcode[] $VALUES = new Opcode[]{CONTINUOUS, Opcode.TEXT, BINARY, Opcode.PING, Opcode.PONG, Opcode.CLOSING};
   public GuiStats field_0006;
   public DefaultServerSocketChannelConfig field_0000;
   public ContainerRepair field_0005;
}
