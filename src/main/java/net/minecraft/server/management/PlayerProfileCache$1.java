package net.minecraft.server.management;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;

public class PlayerProfileCache$1 implements ProfileLookupCallback {
   public GameProfile[] recoveredField2610;

   @Override
   public void onProfileLookupSucceeded(GameProfile var1) {
      this.recoveredField2610[0] = var1;
   }

   @Override
   public void onProfileLookupFailed(GameProfile var1, Exception var2) {
      this.recoveredField2610[0] = null;
   }

   public PlayerProfileCache$1(GameProfile[] var1) {
      this.recoveredField2610 = var1;
   }
}
