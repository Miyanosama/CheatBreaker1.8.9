package com.cheatbreaker.client.websocket.server;

import com.cheatbreaker.client.util.ClientResourceManager;
import com.cheatbreaker.client.util.cosmetic.CosmeticType;
import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.network.PacketBuffer;

public class WSPacketCosmetics extends WSPacket {
   public List<ClientResourceManager> recoveredField1291 = new ArrayList<>();
   public String recoveredField1292;
   public static boolean recoveredField1293 = !WSPacketCosmetics.class.desiredAssertionStatus();
   public String recoveredField1294;
   public int[] recoveredField1295;
   public boolean recoveredField1296;

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.method_10134(this);
   }

   @Override
   public void read(PacketBuffer var1) {
      this.recoveredField1294 = var1.readStringFromBuffer(52);
      int var2 = var1.readInt();

      for (int var3 = 0; var3 < var2; var3++) {
         long var4 = var1.readLong();
         float var6 = var1.readFloat();
         boolean var7 = var1.readBoolean();
         String var8 = var1.readStringFromBuffer(512);
         String var9 = var1.readStringFromBuffer(128);
         CosmeticType var10 = CosmeticType.method_00486(var1.readStringFromBuffer(128));
         if (!recoveredField1293 && var10 == null) {
            throw new AssertionError();
         }

         if (var10 == CosmeticType.EMOTE) {
            this.recoveredField1291.add(new ClientResourceManager(this.recoveredField1294, Integer.parseInt(var9), var10));
         } else {
            this.recoveredField1291.add(new ClientResourceManager(var4, this.recoveredField1294, var9, var10, var6, var7, var8));
         }
      }

      this.recoveredField1292 = var1.readStringFromBuffer(16);
      this.recoveredField1296 = var1.readBoolean();
      this.recoveredField1295 = new int[]{var1.readInt(), var1.readInt()};
   }

   public boolean method_28750() {
      return this.recoveredField1296;
   }

   @Override
   public void write(PacketBuffer var1) {
   }

   public WSPacketCosmetics(List<ClientResourceManager> var1, String var2, String var3, boolean var4, int[] var5) {
      this.recoveredField1291 = var1;
      this.recoveredField1294 = var2;
      this.recoveredField1292 = var3;
      this.recoveredField1296 = var4;
      this.recoveredField1295 = var5;
   }

   public List<ClientResourceManager> method_28752() {
      return this.recoveredField1291;
   }

   public String method_28749() {
      return this.recoveredField1292;
   }

   public int[] method_28751() {
      return this.recoveredField1295;
   }

   public String method_28748() {
      return this.recoveredField1294;
   }

   public WSPacketCosmetics() {
   }
}
