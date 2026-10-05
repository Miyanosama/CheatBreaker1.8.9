package net.minecraft.command;

import io.netty.channel.nio.AbstractNioChannel$AbstractNioUnsafe$2;
import net.minecraft.client.renderer.chunk.ChunkRenderWorker$2;

public enum CommandResultStats$Type {
   AFFECTED_ITEMS(3, "AffectedItems"),
   AFFECTED_ENTITIES(2, "AffectedEntities"),
   QUERY_RESULT(4, "QueryResult"),
   AFFECTED_BLOCKS(1, "AffectedBlocks"),
   SUCCESS_COUNT(0, "SuccessCount");
   // $VF: synthetic field
   public static CommandResultStats$Type[] $VALUES = new CommandResultStats$Type[]{
      CommandResultStats$Type.SUCCESS_COUNT,
      CommandResultStats$Type.AFFECTED_BLOCKS,
      CommandResultStats$Type.AFFECTED_ENTITIES,
      CommandResultStats$Type.AFFECTED_ITEMS,
      CommandResultStats$Type.QUERY_RESULT
   };
   public ChunkRenderWorker$2 field_0003;
   public String typeName;
   public AbstractNioChannel$AbstractNioUnsafe$2 field_0005;
   public int typeID;

   public CommandResultStats$Type(int var3, String var4) {
      this.typeID = var3;
      this.typeName = var4;
   }

   public static String[] getTypeNames() {
      String[] var0 = new String[values().length];
      int var1 = 0;

      for (CommandResultStats$Type var5 : values()) {
         var0[var1++] = var5.getTypeName();
      }

      return var0;
   }

   public int getTypeID() {
      return this.typeID;
   }

   public static CommandResultStats$Type getTypeByName(String var0) {
      for (CommandResultStats$Type var4 : values()) {
         if (var4.getTypeName().equals(var0)) {
            return var4;
         }
      }

      return null;
   }

   public String getTypeName() {
      return this.typeName;
   }
}
