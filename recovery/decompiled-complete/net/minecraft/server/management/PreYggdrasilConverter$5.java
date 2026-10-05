package net.minecraft.server.management;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;
import java.util.List;
import net.minecraft.block.state.BlockState;
import net.minecraft.client.renderer.GlStateManager$1;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.RenderMooshroom;
import net.minecraft.server.MinecraftServer;
import recovered.unidentified.UnidentifiedClass3398;

public class PreYggdrasilConverter$5 implements ProfileLookupCallback {
   public RenderMooshroom field_0003;
   public ClippingHelper field_0005;
   public UnidentifiedClass3398 field_0002;
   public BlockState field_0004;
   public GlStateManager$1 field_0000;

   public PreYggdrasilConverter$5(MinecraftServer var1, List var2) {
      this.field_152741_a = var1;
      this.field_152742_b = var2;
      super();
   }

   public void onProfileLookupSucceeded(GameProfile var1) {
      this.field_152741_a.getPlayerProfileCache().addEntry(var1);
      this.field_152742_b.add(var1);
   }

   public void onProfileLookupFailed(GameProfile var1, Exception var2) {
      PreYggdrasilConverter.access$000().warn("Could not lookup user whitelist entry for " + var1.getName(), var2);
   }
}
