package net.minecraft.client.stream;

import com.cheatbreaker.client.module.ModuleRule;

public enum ChatController$ChatState {
   Initializing,
   Initialized,
   Uninitialized,
   ShuttingDown;
   // $VF: synthetic field
   public static ChatController$ChatState[] $VALUES = new ChatController$ChatState[]{
      ChatController$ChatState.Uninitialized,
      ChatController$ChatState.Initializing,
      ChatController$ChatState.Initialized,
      ChatController$ChatState.ShuttingDown
   };
   public ModuleRule field_0002;
}
