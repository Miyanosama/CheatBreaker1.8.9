package net.minecraft.util;

import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.util.UUIDTypeAdapter;
import java.util.Map;
import java.util.UUID;

public class Session {
   public String token;
   public String playerID;
   public Session.Type sessionType;
   public String username;

   public Session.Type getSessionType() {
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
      this.sessionType = Session.Type.setSessionType(var4);
   }

   public String getSessionID() {
      return "token:" + this.token + ":" + this.playerID;
   }

   public static enum Type {
      LEGACY("legacy"),
      MOJANG("mojang");
      // $VF: synthetic field
      public static Session.Type[] $VALUES = new Session.Type[]{Session.Type.LEGACY, Session.Type.MOJANG};
      public String sessionType;
      public static Map<String, Session.Type> SESSION_TYPES = Maps.newHashMap();

      Type(String var3) {
         this.sessionType = var3;
      }

      public static Session.Type setSessionType(String var0) {
         return SESSION_TYPES.get(var0.toLowerCase());
      }

      static {
         for (Session.Type var3 : values()) {
            SESSION_TYPES.put(var3.sessionType, var3);
         }
      }
   }
}
