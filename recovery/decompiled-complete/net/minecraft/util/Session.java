package net.minecraft.util;

import com.mojang.authlib.GameProfile;
import com.mojang.util.UUIDTypeAdapter;
import java.util.UUID;
import javazoom.jl.decoder.LayerIIIDecoder$gr_info_s;
import net.minecraft.entity.monster.EntityEnderman;

public class Session {
   public LayerIIIDecoder$gr_info_s field_0003;
   public String token;
   public String playerID;
   public Session$Type sessionType;
   public String username;
   public EntityEnderman field_0001;

   public Session$Type getSessionType() {
      return this.sessionType;
   }

   public String getPlayerID() {
      return this.playerID;
   }

   public GameProfile getProfile() {
      try {
         UUID var1 = UUIDTypeAdapter.fromString(this.getPlayerID());
         return new GameProfile(var1, this.getUsername());
      } catch (IllegalArgumentException var2) {
         return new GameProfile((UUID)null, this.getUsername());
      }
   }

   public String getToken() {
      return this.token;
   }

   public String getUsername() {
      return this.username;
   }

   public Session(String var1, String var2, String var3, String var4) {
      this.username = var1;
      this.playerID = var2;
      this.token = var3;
      this.sessionType = Session$Type.setSessionType(var4);
   }

   public String getSessionID() {
      return "token:" + this.token + ":" + this.playerID;
   }
}
