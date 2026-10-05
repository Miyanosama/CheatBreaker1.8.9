package net.optifine.util;

import io.netty.handler.codec.http.HttpObjectAggregator;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.Chunk$EnumCreateEntityType;
import net.optifine.entity.model.ModelAdapterEnderCrystal;

public class IntegratedServerUtils {
   public HttpObjectAggregator field_0000;
   public ModelAdapterEnderCrystal field_0001;

   public static Entity getEntity(UUID var0) {
      WorldServer var1 = getWorldServer();
      return var1 == null ? null : var1.getEntityFromUuid(var0);
   }

   public static WorldServer getWorldServer() {
      Minecraft var0 = Config.getMinecraft();
      WorldClient var1 = var0.theWorld;
      if (var1 == null) {
         return null;
      } else if (!var0.isIntegratedServerRunning()) {
         return null;
      } else {
         IntegratedServer var2 = var0.getIntegratedServer();
         if (var2 == null) {
            return null;
         } else {
            WorldProvider var3 = var1.t;
            if (var3 == null) {
               return null;
            } else {
               int var4 = var3.getDimensionId();

               try {
                  return var2.worldServerForDimension(var4);
               } catch (NullPointerException var6) {
                  return null;
               }
            }
         }
      }
   }

   public static TileEntity getTileEntity(BlockPos var0) {
      WorldServer var1 = getWorldServer();
      if (var1 == null) {
         return null;
      } else {
         Chunk var2 = var1.N().provideChunk(var0.getX() >> 4, var0.getZ() >> 4);
         return var2 == null ? null : var2.getTileEntity(var0, Chunk$EnumCreateEntityType.CHECK);
      }
   }
}
