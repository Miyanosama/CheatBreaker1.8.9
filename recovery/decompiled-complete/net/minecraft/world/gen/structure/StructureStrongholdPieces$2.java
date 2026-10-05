package net.minecraft.world.gen.structure;

import com.cheatbreaker.client.websocket.shared.WSPacketFriendRequest;
import net.minecraft.command.PlayerSelector;

public class StructureStrongholdPieces$2 extends StructureStrongholdPieces$PieceWeight {
   public WSPacketFriendRequest field_0000;
   public PlayerSelector field_0001;

   @Override
   public boolean canSpawnMoreStructuresOfType(int var1) {
      return super.canSpawnMoreStructuresOfType(var1) && var1 > 5;
   }

   public StructureStrongholdPieces$2(Class var1, int var2, int var3) {
      super(var1, var2, var3);
   }
}
