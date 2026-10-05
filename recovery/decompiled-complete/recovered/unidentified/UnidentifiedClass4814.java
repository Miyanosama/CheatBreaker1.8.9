package recovered.unidentified;

import com.cheatbreaker.client.util.ClientResourceManager;
import com.cheatbreaker.client.util.cosmetic.CosmeticType;
import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.network.PacketBuffer;
import org.java_websocket.framing.FramedataImpl1$1;

public class UnidentifiedClass4814 extends WSPacket {
   public List<ClientResourceManager> field_0006 = new ArrayList<>();
   public String field_0003;
   public EntityAIBreakDoor field_0005;
   public FramedataImpl1$1 field_0001;
   public String field_0007;
   public UnidentifiedClass3584 field_0004;
   public int[] field_0002;
   public boolean field_0008;

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.method_10134(this);
   }

   @Override
   public void read(PacketBuffer var1) {
      this.field_0007 = var1.readStringFromBuffer(52);
      int var2 = var1.readInt();

      for (int var3 = 0; var3 < var2; var3++) {
         long var4 = var1.readLong();
         float var6 = var1.readFloat();
         boolean var7 = var1.readBoolean();
         String var8 = var1.readStringFromBuffer(512);
         String var9 = var1.readStringFromBuffer(128);
         CosmeticType var10 = CosmeticType.method_00486(var1.readStringFromBuffer(128));
         if (!field_0000 && var10 == null) {
            throw new AssertionError();
         }

         if (var10 == CosmeticType.field_0002) {
            this.field_0006.add(new ClientResourceManager(this.field_0007, Integer.parseInt(var9), var10));
         } else {
            this.field_0006.add(new ClientResourceManager(var4, this.field_0007, var9, var10, var6, var7, var8));
         }
      }

      this.field_0003 = var1.readStringFromBuffer(16);
      this.field_0008 = var1.readBoolean();
      this.field_0002 = new int[]{var1.readInt(), var1.readInt()};
   }

   public boolean method_28750() {
      return this.field_0008;
   }

   @Override
   public void write(PacketBuffer var1) {
   }

   public UnidentifiedClass4814(List<ClientResourceManager> var1, String var2, String var3, boolean var4, int[] var5) {
      this.field_0006 = var1;
      this.field_0007 = var2;
      this.field_0003 = var3;
      this.field_0008 = var4;
      this.field_0002 = var5;
   }

   public List<ClientResourceManager> method_28752() {
      return this.field_0006;
   }

   public String method_28749() {
      return this.field_0003;
   }

   public int[] method_28751() {
      return this.field_0002;
   }

   public String method_28748() {
      return this.field_0007;
   }

   public UnidentifiedClass4814() {
   }
}
