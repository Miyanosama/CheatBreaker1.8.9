package net.minecraft.client.network;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import net.minecraft.client.resources.SkinManager$SkinAvailableCallback;
import net.minecraft.client.stream.TwitchStream;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.util.ResourceLocation;

public class NetworkPlayerInfo$1 implements SkinManager$SkinAvailableCallback {
   public TwitchStream field_0001;
   public EntitySkeleton field_0002;

   @Override
   public void skinAvailable(Type var1, ResourceLocation var2, MinecraftProfileTexture var3) {
      switch (NetworkPlayerInfo$2.field_178875_a[var1.ordinal()]) {
         case 1:
            NetworkPlayerInfo.access$002(this.field_177224_a, var2);
            NetworkPlayerInfo.access$102(this.field_177224_a, var3.getMetadata("model"));
            if (NetworkPlayerInfo.access$100(this.field_177224_a) == null) {
               NetworkPlayerInfo.access$102(this.field_177224_a, "default");
            }
            break;
         case 2:
            NetworkPlayerInfo.access$202(this.field_177224_a, var2);
      }
   }

   public NetworkPlayerInfo$1(NetworkPlayerInfo var1) {
      this.field_177224_a = var1;
      super();
   }
}
