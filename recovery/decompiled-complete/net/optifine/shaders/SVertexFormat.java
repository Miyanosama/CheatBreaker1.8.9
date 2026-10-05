package net.optifine.shaders;

import io.netty.buffer.PoolChunk;
import io.netty.channel.FixedRecvByteBufAllocator$HandleImpl;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.client.renderer.vertex.VertexFormatElement$EnumType;
import net.minecraft.client.renderer.vertex.VertexFormatElement$EnumUsage;

public class SVertexFormat {
   public PoolChunk field_0003;
   public static VertexFormat defVertexFormatTextured = makeDefVertexFormatTextured();
   public static int field_0002;
   public static int field_0004;
   public static int field_0000;
   public FixedRecvByteBufAllocator$HandleImpl field_0001;
   public static int field_0006;

   public static VertexFormat duplicate(VertexFormat var0) {
      if (var0 == null) {
         return null;
      } else {
         VertexFormat var1 = new VertexFormat();
         copy(var0, var1);
         return var1;
      }
   }

   public static VertexFormat makeDefVertexFormatTextured() {
      VertexFormat var0 = new VertexFormat();
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.FLOAT, VertexFormatElement$EnumUsage.POSITION, 3));
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.UBYTE, VertexFormatElement$EnumUsage.PADDING, 4));
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.FLOAT, VertexFormatElement$EnumUsage.UV, 2));
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.SHORT, VertexFormatElement$EnumUsage.PADDING, 2));
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.BYTE, VertexFormatElement$EnumUsage.NORMAL, 3));
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.BYTE, VertexFormatElement$EnumUsage.PADDING, 1));
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.FLOAT, VertexFormatElement$EnumUsage.PADDING, 2));
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.SHORT, VertexFormatElement$EnumUsage.PADDING, 4));
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.SHORT, VertexFormatElement$EnumUsage.PADDING, 4));
      return var0;
   }

   public static VertexFormat makeDefVertexFormatBlock() {
      VertexFormat var0 = new VertexFormat();
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.FLOAT, VertexFormatElement$EnumUsage.POSITION, 3));
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.UBYTE, VertexFormatElement$EnumUsage.COLOR, 4));
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.FLOAT, VertexFormatElement$EnumUsage.UV, 2));
      var0.addElement(new VertexFormatElement(1, VertexFormatElement$EnumType.SHORT, VertexFormatElement$EnumUsage.UV, 2));
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.BYTE, VertexFormatElement$EnumUsage.NORMAL, 3));
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.BYTE, VertexFormatElement$EnumUsage.PADDING, 1));
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.FLOAT, VertexFormatElement$EnumUsage.PADDING, 2));
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.SHORT, VertexFormatElement$EnumUsage.PADDING, 4));
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.SHORT, VertexFormatElement$EnumUsage.PADDING, 4));
      return var0;
   }

   public static void copy(VertexFormat var0, VertexFormat var1) {
      if (var0 != null && var1 != null) {
         var1.clear();

         for (int var2 = 0; var2 < var0.getElementCount(); var2++) {
            var1.addElement(var0.getElement(var2));
         }
      }
   }

   public static VertexFormat makeDefVertexFormatItem() {
      VertexFormat var0 = new VertexFormat();
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.FLOAT, VertexFormatElement$EnumUsage.POSITION, 3));
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.UBYTE, VertexFormatElement$EnumUsage.COLOR, 4));
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.FLOAT, VertexFormatElement$EnumUsage.UV, 2));
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.SHORT, VertexFormatElement$EnumUsage.PADDING, 2));
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.BYTE, VertexFormatElement$EnumUsage.NORMAL, 3));
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.BYTE, VertexFormatElement$EnumUsage.PADDING, 1));
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.FLOAT, VertexFormatElement$EnumUsage.PADDING, 2));
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.SHORT, VertexFormatElement$EnumUsage.PADDING, 4));
      var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.SHORT, VertexFormatElement$EnumUsage.PADDING, 4));
      return var0;
   }

   public static void setDefBakedFormat(VertexFormat var0) {
      if (var0 != null) {
         var0.clear();
         var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.FLOAT, VertexFormatElement$EnumUsage.POSITION, 3));
         var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.UBYTE, VertexFormatElement$EnumUsage.COLOR, 4));
         var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.FLOAT, VertexFormatElement$EnumUsage.UV, 2));
         var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.SHORT, VertexFormatElement$EnumUsage.PADDING, 2));
         var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.BYTE, VertexFormatElement$EnumUsage.NORMAL, 3));
         var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.BYTE, VertexFormatElement$EnumUsage.PADDING, 1));
         var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.FLOAT, VertexFormatElement$EnumUsage.PADDING, 2));
         var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.SHORT, VertexFormatElement$EnumUsage.PADDING, 4));
         var0.addElement(new VertexFormatElement(0, VertexFormatElement$EnumType.SHORT, VertexFormatElement$EnumUsage.PADDING, 4));
      }
   }
}
