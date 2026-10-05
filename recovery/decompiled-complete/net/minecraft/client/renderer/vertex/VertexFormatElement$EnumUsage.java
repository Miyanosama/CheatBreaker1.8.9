package net.minecraft.client.renderer.vertex;

import net.minecraft.world.gen.feature.WorldGeneratorBonusChest;
import org.apache.log4j.net.SocketAppender;

public enum VertexFormatElement$EnumUsage {
   UV("UV"),
   MATRIX("Bone Matrix"),
   COLOR("Vertex Color"),
   PADDING("Padding"),
   BLEND_WEIGHT("Blend Weight"),
   NORMAL("Normal"),
   POSITION("Position");
   public WorldGeneratorBonusChest field_0005;
   // $VF: synthetic field
   public static VertexFormatElement$EnumUsage[] $VALUES = new VertexFormatElement$EnumUsage[]{
      VertexFormatElement$EnumUsage.POSITION,
      VertexFormatElement$EnumUsage.NORMAL,
      VertexFormatElement$EnumUsage.COLOR,
      UV,
      MATRIX,
      VertexFormatElement$EnumUsage.BLEND_WEIGHT,
      VertexFormatElement$EnumUsage.PADDING
   };
   public String displayName;
   public SocketAppender field_0010;

   public String getDisplayName() {
      return this.displayName;
   }

   public VertexFormatElement$EnumUsage(String var3) {
      this.displayName = var3;
   }
}
