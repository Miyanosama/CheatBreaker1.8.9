package net.minecraft.client.stream;

import javazoom.jl.converter.jlc;
import junit.framework.TestResult;

public enum ChatController$EnumChannelState {
   Connecting,
   Disconnecting,
   Disconnected,
   Created,
   Connected;

   // $VF: synthetic field
   public static ChatController$EnumChannelState[] $VALUES = new ChatController$EnumChannelState[]{
      ChatController$EnumChannelState.Created, Connecting, ChatController$EnumChannelState.Connected, Disconnecting, Disconnected
   };
   public jlc field_0000;
   public TestResult field_0004;
}
