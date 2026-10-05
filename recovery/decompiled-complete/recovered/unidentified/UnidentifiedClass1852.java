package recovered.unidentified;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;
import net.minecraft.item.ItemColored;

public class UnidentifiedClass1852 implements ProfileLookupCallback {
   public ItemColored field_0000;

   public void onProfileLookupSucceeded(GameProfile var1) {
      this.field_0001[0] = var1;
   }

   public void onProfileLookupFailed(GameProfile var1, Exception var2) {
      this.field_0001[0] = null;
   }

   public UnidentifiedClass1852(GameProfile[] var1) {
      this.field_0001 = var1;
      super();
   }
}
