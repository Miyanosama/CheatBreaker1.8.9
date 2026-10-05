package net.minecraft.client.renderer;

import net.minecraft.block.BlockBeacon$1$1;
import net.minecraft.client.renderer.block.model.BlockPart$Deserializer;
import net.minecraft.client.resources.data.AnimationFrame;
import net.minecraft.util.EnumFacing;
import recovered.unidentified.UnidentifiedClass3605;

public enum EnumFaceDirection {
   WEST(
      new EnumFaceDirection$VertexInformation[]{
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.WEST_INDEX, EnumFaceDirection$Constants.UP_INDEX, EnumFaceDirection$Constants.NORTH_INDEX, null
         ),
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.WEST_INDEX, EnumFaceDirection$Constants.DOWN_INDEX, EnumFaceDirection$Constants.NORTH_INDEX, null
         ),
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.WEST_INDEX, EnumFaceDirection$Constants.DOWN_INDEX, EnumFaceDirection$Constants.SOUTH_INDEX, null
         ),
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.WEST_INDEX, EnumFaceDirection$Constants.UP_INDEX, EnumFaceDirection$Constants.SOUTH_INDEX, null
         )
      }
   ),
   DOWN(
      new EnumFaceDirection$VertexInformation[]{
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.WEST_INDEX, EnumFaceDirection$Constants.DOWN_INDEX, EnumFaceDirection$Constants.SOUTH_INDEX, null
         ),
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.WEST_INDEX, EnumFaceDirection$Constants.DOWN_INDEX, EnumFaceDirection$Constants.NORTH_INDEX, null
         ),
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.EAST_INDEX, EnumFaceDirection$Constants.DOWN_INDEX, EnumFaceDirection$Constants.NORTH_INDEX, null
         ),
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.EAST_INDEX, EnumFaceDirection$Constants.DOWN_INDEX, EnumFaceDirection$Constants.SOUTH_INDEX, null
         )
      }
   ),
   NORTH(
      new EnumFaceDirection$VertexInformation[]{
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.EAST_INDEX, EnumFaceDirection$Constants.UP_INDEX, EnumFaceDirection$Constants.NORTH_INDEX, null
         ),
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.EAST_INDEX, EnumFaceDirection$Constants.DOWN_INDEX, EnumFaceDirection$Constants.NORTH_INDEX, null
         ),
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.WEST_INDEX, EnumFaceDirection$Constants.DOWN_INDEX, EnumFaceDirection$Constants.NORTH_INDEX, null
         ),
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.WEST_INDEX, EnumFaceDirection$Constants.UP_INDEX, EnumFaceDirection$Constants.NORTH_INDEX, null
         )
      }
   ),
   SOUTH(
      new EnumFaceDirection$VertexInformation[]{
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.WEST_INDEX, EnumFaceDirection$Constants.UP_INDEX, EnumFaceDirection$Constants.SOUTH_INDEX, null
         ),
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.WEST_INDEX, EnumFaceDirection$Constants.DOWN_INDEX, EnumFaceDirection$Constants.SOUTH_INDEX, null
         ),
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.EAST_INDEX, EnumFaceDirection$Constants.DOWN_INDEX, EnumFaceDirection$Constants.SOUTH_INDEX, null
         ),
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.EAST_INDEX, EnumFaceDirection$Constants.UP_INDEX, EnumFaceDirection$Constants.SOUTH_INDEX, null
         )
      }
   ),
   EAST(
      new EnumFaceDirection$VertexInformation[]{
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.EAST_INDEX, EnumFaceDirection$Constants.UP_INDEX, EnumFaceDirection$Constants.SOUTH_INDEX, null
         ),
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.EAST_INDEX, EnumFaceDirection$Constants.DOWN_INDEX, EnumFaceDirection$Constants.SOUTH_INDEX, null
         ),
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.EAST_INDEX, EnumFaceDirection$Constants.DOWN_INDEX, EnumFaceDirection$Constants.NORTH_INDEX, null
         ),
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.EAST_INDEX, EnumFaceDirection$Constants.UP_INDEX, EnumFaceDirection$Constants.NORTH_INDEX, null
         )
      }
   ),
   UP(
      new EnumFaceDirection$VertexInformation[]{
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.WEST_INDEX, EnumFaceDirection$Constants.UP_INDEX, EnumFaceDirection$Constants.NORTH_INDEX, null
         ),
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.WEST_INDEX, EnumFaceDirection$Constants.UP_INDEX, EnumFaceDirection$Constants.SOUTH_INDEX, null
         ),
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.EAST_INDEX, EnumFaceDirection$Constants.UP_INDEX, EnumFaceDirection$Constants.SOUTH_INDEX, null
         ),
         new EnumFaceDirection$VertexInformation(
            EnumFaceDirection$Constants.EAST_INDEX, EnumFaceDirection$Constants.UP_INDEX, EnumFaceDirection$Constants.NORTH_INDEX, null
         )
      }
   );
   public AnimationFrame field_0005;
   public UnidentifiedClass3605 field_0010;
   // $VF: synthetic field
   public static EnumFaceDirection[] $VALUES = new EnumFaceDirection[]{
      DOWN, EnumFaceDirection.UP, NORTH, EnumFaceDirection.SOUTH, WEST, EnumFaceDirection.EAST
   };
   public EnumFaceDirection$VertexInformation[] vertexInfos;
   public BlockPart$Deserializer field_0008;
   public BlockBeacon$1$1 field_0012;
   public static EnumFaceDirection[] facings = new EnumFaceDirection[6];

   public EnumFaceDirection(EnumFaceDirection$VertexInformation[] var3) {
      this.vertexInfos = var3;
   }

   public static EnumFaceDirection getFacing(EnumFacing var0) {
      return facings[var0.getIndex()];
   }

   public EnumFaceDirection$VertexInformation getVertexInformation(int var1) {
      return this.vertexInfos[var1];
   }

   static {
      facings[EnumFaceDirection$Constants.DOWN_INDEX] = DOWN;
      facings[EnumFaceDirection$Constants.UP_INDEX] = UP;
      facings[EnumFaceDirection$Constants.NORTH_INDEX] = NORTH;
      facings[EnumFaceDirection$Constants.SOUTH_INDEX] = SOUTH;
      facings[EnumFaceDirection$Constants.WEST_INDEX] = WEST;
      facings[EnumFaceDirection$Constants.EAST_INDEX] = EAST;
   }
}
