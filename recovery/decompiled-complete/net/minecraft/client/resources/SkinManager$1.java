package net.minecraft.client.resources;

import com.google.common.cache.CacheLoader;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSnooper$List;
import net.minecraft.network.play.server.S20PacketEntityProperties$Snapshot;
import recovered.unidentified.UnidentifiedClass1318;
import recovered.unidentified.UnidentifiedClass3434;

public class SkinManager$1 extends CacheLoader<GameProfile, Map<Type, MinecraftProfileTexture>> {
   public UnidentifiedClass3434 field_0002;
   public GuiSnooper$List field_0004;
   public UnidentifiedClass1318 field_0003;
   public S20PacketEntityProperties$Snapshot field_0000;

   public SkinManager$1(SkinManager var1) {
      this.field_0001 = var1;
      super();
   }

   public Map<Type, MinecraftProfileTexture> load(GameProfile var1) {
      return Minecraft.getMinecraft().getSessionService().getTextures(var1, false);
   }
}
