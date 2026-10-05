package net.minecraft.entity.item;

import com.google.common.collect.Maps;
import io.netty.channel.sctp.nio.NioSctpChannel$1;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder$1;
import java.util.Map;
import net.optifine.shaders.config.Property;

public enum EntityMinecart$EnumMinecartType {
   FURNACE(2, "MinecartFurnace"),
   TNT(3, "MinecartTNT"),
   HOPPER(5, "MinecartHopper"),
   COMMAND_BLOCK(6, "MinecartCommandBlock"),
   SPAWNER(4, "MinecartSpawner"),
   RIDEABLE(0, "MinecartRideable"),
   CHEST(1, "MinecartChest");

   public Property field_0006;
   public static Map<Integer, EntityMinecart$EnumMinecartType> ID_LOOKUP = Maps.newHashMap();
   public String name;
   public int networkID;
   public HttpPostRequestEncoder$1 field_0008;
   public NioSctpChannel$1 field_0004;

   public static EntityMinecart$EnumMinecartType byNetworkID(int var0) {
      EntityMinecart$EnumMinecartType var1 = ID_LOOKUP.get(var0);
      return var1 == null ? RIDEABLE : var1;
   }

   static {
      for (EntityMinecart$EnumMinecartType var3 : values()) {
         ID_LOOKUP.put(var3.getNetworkID(), var3);
      }
   }

   public String getName() {
      return this.name;
   }

   public EntityMinecart$EnumMinecartType(int var3, String var4) {
      this.networkID = var3;
      this.name = var4;
   }

   public int getNetworkID() {
      return this.networkID;
   }
}
