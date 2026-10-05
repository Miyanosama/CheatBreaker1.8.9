package net.minecraft.client.renderer.vertex;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VertexFormatElement {
   public VertexFormatElement.EnumUsage usage;
   public static Logger LOGGER = LogManager.getLogger();
   public int elementCount;
   public VertexFormatElement.EnumType type;
   public int index;

   public VertexFormatElement.EnumUsage getUsage() {
      return this.usage;
   }

   @Override
   public int hashCode() {
      int var1 = this.type.hashCode();
      var1 = 31 * var1 + this.usage.hashCode();
      var1 = 31 * var1 + this.index;
      return 31 * var1 + this.elementCount;
   }

   public VertexFormatElement.EnumType getType() {
      return this.type;
   }

   public int getIndex() {
      return this.index;
   }

   public VertexFormatElement(int var1, VertexFormatElement.EnumType var2, VertexFormatElement.EnumUsage var3, int var4) {
      if (!this.func_177372_a(var1, var3)) {
         LOGGER.warn("Multiple vertex elements of the same type other than UVs are not supported. Forcing type to UV.");
         this.usage = VertexFormatElement.EnumUsage.UV;
      } else {
         this.usage = var3;
      }

      this.type = var2;
      this.index = var1;
      this.elementCount = var4;
   }

   public int getSize() {
      return this.type.getSize() * this.elementCount;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         VertexFormatElement var2 = (VertexFormatElement)var1;
         return this.elementCount != var2.elementCount
            ? false
            : (this.index != var2.index ? false : (this.type != var2.type ? false : this.usage == var2.usage));
      } else {
         return false;
      }
   }

   public int getElementCount() {
      return this.elementCount;
   }

   public boolean func_177372_a(int var1, VertexFormatElement.EnumUsage var2) {
      return var1 == 0 || var2 == VertexFormatElement.EnumUsage.UV;
   }

   @Override
   public String toString() {
      return this.elementCount + "," + this.usage.getDisplayName() + "," + this.type.getDisplayName();
   }

   public boolean isPositionElement() {
      return this.usage == VertexFormatElement.EnumUsage.POSITION;
   }

   public static enum EnumType {
      FLOAT(4, "Float", 5126),
      UBYTE(1, "Unsigned Byte", 5121),
      BYTE(1, "Byte", 5120),
      USHORT(2, "Unsigned Short", 5123),
      SHORT(2, "Short", 5122),
      UINT(4, "Unsigned Int", 5125),
      INT(4, "Int", 5124);
      public int size;
      public String displayName;
      // $VF: synthetic field
      public static VertexFormatElement.EnumType[] $VALUES = new VertexFormatElement.EnumType[]{
         VertexFormatElement.EnumType.FLOAT,
         UBYTE,
         BYTE,
         USHORT,
         VertexFormatElement.EnumType.SHORT,
         VertexFormatElement.EnumType.UINT,
         VertexFormatElement.EnumType.INT
      };
      public int glConstant;

      EnumType(int var3, String var4, int var5) {
         this.size = var3;
         this.displayName = var4;
         this.glConstant = var5;
      }

      public int getGlConstant() {
         return this.glConstant;
      }

      public String getDisplayName() {
         return this.displayName;
      }

      public int getSize() {
         return this.size;
      }
   }

   public static enum EnumUsage {
      POSITION("Position"),
      NORMAL("Normal"),
      COLOR("Vertex Color"),
      UV("UV"),
      MATRIX("Bone Matrix"),
      BLEND_WEIGHT("Blend Weight"),
      PADDING("Padding");
      // $VF: synthetic field
      public static VertexFormatElement.EnumUsage[] $VALUES = new VertexFormatElement.EnumUsage[]{
         VertexFormatElement.EnumUsage.POSITION,
         VertexFormatElement.EnumUsage.NORMAL,
         VertexFormatElement.EnumUsage.COLOR,
         UV,
         MATRIX,
         VertexFormatElement.EnumUsage.BLEND_WEIGHT,
         VertexFormatElement.EnumUsage.PADDING
      };
      public String displayName;

      public String getDisplayName() {
         return this.displayName;
      }

      EnumUsage(String var3) {
         this.displayName = var3;
      }
   }
}
