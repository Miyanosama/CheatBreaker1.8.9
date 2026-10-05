package net.minecraft.network;

import com.cheatbreaker.client.ui.overlay.element.ElementListElement;
import com.mojang.authlib.GameProfile;
import javazoom.jl.decoder.LayerIIIDecoder$temporaire;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.optifine.override.ChunkCacheOF;
import org.apache.log4j.Logger;

public class ServerStatusResponse$PlayerCountData {
   public Logger field_0004;
   public WorldGenTaiga1 field_0007;
   public int onlinePlayerCount;
   public GameProfile[] players;
   public int maxPlayers;
   public ElementListElement field_0001;
   public LayerIIIDecoder$temporaire field_0008;
   public ChunkCacheOF field_0005;
   public EnumConnectionState$1 field_0002;

   public int getMaxPlayers() {
      return this.maxPlayers;
   }

   public void setPlayers(GameProfile[] var1) {
      this.players = var1;
   }

   public GameProfile[] getPlayers() {
      return this.players;
   }

   public int getOnlinePlayerCount() {
      return this.onlinePlayerCount;
   }

   public ServerStatusResponse$PlayerCountData(int var1, int var2) {
      this.maxPlayers = var1;
      this.onlinePlayerCount = var2;
   }
}
