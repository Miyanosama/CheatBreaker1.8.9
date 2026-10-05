package recovered.unidentified;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.management.UserListEntry;
import net.minecraft.util.EnumChatFormatting;

public class UnidentifiedClass0599 extends WSPacket {
   public List<String> field_0004;
   public UserListEntry field_0002;
   public static byte[] field_0003 = new byte[]{36, -70, -63, 3, -116, 46, -121, -127, 117, 64, 58, 5, 75, 96, -63, 36};
   public EntityEnderman field_0000;
   public EnumChatFormatting field_0001;

   public UnidentifiedClass0599(List<String> var1) {
      this.field_0004 = var1;
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeInt(this.field_0004.size());

      for (Object var3 : this.field_0004) {
         var1.writeString((String)var3);
      }
   }

   public UnidentifiedClass0599() {
   }

   @Override
   public void read(PacketBuffer var1) {
      int var2 = var1.readInt();
      this.field_0004 = new ArrayList<>();

      for (int var3 = 0; var3 < var2; var3++) {
         this.field_0004.add(var1.readStringFromBuffer(512));
      }
   }

   @Override
   public void handle(AssetsWebSocket var1) {
   }

   public List<String> method_04417() {
      return this.field_0004;
   }
}
