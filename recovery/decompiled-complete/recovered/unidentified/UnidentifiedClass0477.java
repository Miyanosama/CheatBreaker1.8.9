package recovered.unidentified;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import java.security.PublicKey;
import net.minecraft.block.BlockLiquid;
import net.minecraft.enchantment.EnchantmentDigging;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.CryptManager;

public class UnidentifiedClass0477 extends WSPacket {
   public PublicKey field_0003;
   public byte[] field_0001;
   public EnchantmentDigging field_0002;
   public BlockLiquid field_0000;

   @Override
   public void write(PacketBuffer var1) {
   }

   public PublicKey method_03517() {
      return this.field_0003;
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.method_10115(this);
   }

   public UnidentifiedClass0477(PublicKey var1, byte[] var2) {
      this.field_0003 = var1;
      this.field_0001 = var2;
   }

   public UnidentifiedClass0477() {
   }

   public byte[] method_03518() {
      return this.field_0001;
   }

   @Override
   public void read(PacketBuffer var1) {
      this.field_0003 = CryptManager.decodePublicKey(this.readKey(var1));
      this.field_0001 = this.readKey(var1);
   }
}
