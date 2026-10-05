package org.apache.log4j;

import com.cheatbreaker.client.websocket.shared.WSPacketFriendRequest;
import net.minecraft.block.BlockRailPowered$2;

public class NameValue {
   public String value;
   public WSPacketFriendRequest field_0003;
   public BlockRailPowered$2 field_0000;
   public String key;

   public NameValue(String var1, String var2) {
      this.key = var1;
      this.value = var2;
   }

   public String toString() {
      return this.key + "=" + this.value;
   }
}
