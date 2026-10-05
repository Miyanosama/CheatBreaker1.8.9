package net.minecraft.server.management;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.WebSocket13FrameEncoder;
import java.io.File;

public class UserListBans extends UserList<GameProfile, UserListBansEntry> {
   public WebSocket13FrameEncoder field_0000;
   public ChannelOutboundHandlerAdapter field_0001;

   @Override
   public UserListEntry<GameProfile> createEntry(JsonObject var1) {
      return new UserListBansEntry(var1);
   }

   public UserListBans(File var1) {
      super(var1);
   }

   @Override
   public String[] getKeys() {
      String[] var1 = new String[this.getValues().size()];
      int var2 = 0;

      for (UserListBansEntry var4 : this.getValues().values()) {
         var1[var2++] = var4.getValue().getName();
      }

      return var1;
   }

   public String getObjectKey(GameProfile var1) {
      return var1.getId().toString();
   }

   public boolean isBanned(GameProfile var1) {
      return this.hasEntry(var1);
   }

   public GameProfile isUsernameBanned(String var1) {
      for (UserListBansEntry var3 : this.getValues().values()) {
         if (var1.equalsIgnoreCase(var3.getValue().getName())) {
            return var3.getValue();
         }
      }

      return null;
   }
}
