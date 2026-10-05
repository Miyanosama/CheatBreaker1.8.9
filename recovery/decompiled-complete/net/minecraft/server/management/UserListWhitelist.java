package net.minecraft.server.management;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition$Variant;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.optifine.entity.model.ModelAdapterWitherSkull;
import net.optifine.util.SmoothFloat;
import recovered.unidentified.UnidentifiedClass3688;

public class UserListWhitelist extends UserList<GameProfile, UserListWhitelistEntry> {
   public ModelBlockDefinition$Variant field_0001;
   public ModelAdapterWitherSkull field_0005;
   public S19PacketEntityStatus field_0002;
   public C10PacketCreativeInventoryAction field_0003;
   public UnidentifiedClass3688 field_0000;
   public SmoothFloat field_0004;

   public UserListWhitelist(File var1) {
      super(var1);
   }

   @Override
   public String[] getKeys() {
      String[] var1 = new String[this.getValues().size()];
      int var2 = 0;

      for (UserListWhitelistEntry var4 : this.getValues().values()) {
         var1[var2++] = var4.getValue().getName();
      }

      return var1;
   }

   public String getObjectKey(GameProfile var1) {
      return var1.getId().toString();
   }

   public GameProfile getBannedProfile(String var1) {
      for (UserListWhitelistEntry var3 : this.getValues().values()) {
         if (var1.equalsIgnoreCase(var3.getValue().getName())) {
            return var3.getValue();
         }
      }

      return null;
   }

   @Override
   public UserListEntry<GameProfile> createEntry(JsonObject var1) {
      return new UserListWhitelistEntry(var1);
   }
}
