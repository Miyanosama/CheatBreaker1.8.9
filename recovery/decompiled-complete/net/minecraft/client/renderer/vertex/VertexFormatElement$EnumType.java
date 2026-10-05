package net.minecraft.client.renderer.vertex;

import io.netty.buffer.PoolThreadCache;
import io.netty.util.internal.MpscLinkedQueuePad1;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.world.gen.structure.StructureVillagePieces$House2;
import net.optifine.shaders.config.ShaderLine;

public enum VertexFormatElement$EnumType {
   USHORT(2, "Unsigned Short", 5123),
   UBYTE(1, "Unsigned Byte", 5121),
   BYTE(1, "Byte", 5120),
   UINT(4, "Unsigned Int", 5125),
   INT(4, "Int", 5124),
   FLOAT(4, "Float", 5126),
   SHORT(2, "Short", 5122);
   public PoolThreadCache field_0011;
   public MpscLinkedQueuePad1 field_0001;
   public int size;
   public String displayName;
   public StructureVillagePieces$House2 field_0009;
   public ShaderLine field_0003;
   // $VF: synthetic field
   public static VertexFormatElement$EnumType[] $VALUES = new VertexFormatElement$EnumType[]{
      VertexFormatElement$EnumType.FLOAT,
      UBYTE,
      BYTE,
      USHORT,
      VertexFormatElement$EnumType.SHORT,
      VertexFormatElement$EnumType.UINT,
      VertexFormatElement$EnumType.INT
   };
   public EntityCow field_0000;
   public int glConstant;

   public VertexFormatElement$EnumType(int var3, String var4, int var5) {
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
