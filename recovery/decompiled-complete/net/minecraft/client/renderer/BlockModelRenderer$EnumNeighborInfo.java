package net.minecraft.client.renderer;

import io.netty.handler.codec.http.multipart.HttpPostBodyUtil;
import net.minecraft.client.renderer.block.model.BlockPart$Deserializer;
import net.minecraft.client.renderer.entity.RenderGiantZombie$1;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.util.EnumFacing;
import net.optifine.entity.model.CustomModelRenderer;
import net.optifine.entity.model.ModelAdapterHeadHumanoid;

public enum BlockModelRenderer$EnumNeighborInfo {
   SOUTH(
      new EnumFacing[]{EnumFacing.WEST, EnumFacing.EAST, EnumFacing.DOWN, EnumFacing.UP},
      0.8F,
      true,
      new BlockModelRenderer$Orientation[]{
         BlockModelRenderer$Orientation.UP,
         BlockModelRenderer$Orientation.FLIP_WEST,
         BlockModelRenderer$Orientation.FLIP_UP,
         BlockModelRenderer$Orientation.FLIP_WEST,
         BlockModelRenderer$Orientation.FLIP_UP,
         BlockModelRenderer$Orientation.WEST,
         BlockModelRenderer$Orientation.UP,
         BlockModelRenderer$Orientation.WEST
      },
      new BlockModelRenderer$Orientation[]{
         BlockModelRenderer$Orientation.DOWN,
         BlockModelRenderer$Orientation.FLIP_WEST,
         BlockModelRenderer$Orientation.FLIP_DOWN,
         BlockModelRenderer$Orientation.FLIP_WEST,
         BlockModelRenderer$Orientation.FLIP_DOWN,
         BlockModelRenderer$Orientation.WEST,
         BlockModelRenderer$Orientation.DOWN,
         BlockModelRenderer$Orientation.WEST
      },
      new BlockModelRenderer$Orientation[]{
         BlockModelRenderer$Orientation.DOWN,
         BlockModelRenderer$Orientation.FLIP_EAST,
         BlockModelRenderer$Orientation.FLIP_DOWN,
         BlockModelRenderer$Orientation.FLIP_EAST,
         BlockModelRenderer$Orientation.FLIP_DOWN,
         BlockModelRenderer$Orientation.EAST,
         BlockModelRenderer$Orientation.DOWN,
         BlockModelRenderer$Orientation.EAST
      },
      new BlockModelRenderer$Orientation[]{
         BlockModelRenderer$Orientation.UP,
         BlockModelRenderer$Orientation.FLIP_EAST,
         BlockModelRenderer$Orientation.FLIP_UP,
         BlockModelRenderer$Orientation.FLIP_EAST,
         BlockModelRenderer$Orientation.FLIP_UP,
         BlockModelRenderer$Orientation.EAST,
         BlockModelRenderer$Orientation.UP,
         BlockModelRenderer$Orientation.EAST
      }
   ),
   EAST(
      new EnumFacing[]{EnumFacing.DOWN, EnumFacing.UP, EnumFacing.NORTH, EnumFacing.SOUTH},
      0.6F,
      true,
      new BlockModelRenderer$Orientation[]{
         BlockModelRenderer$Orientation.FLIP_DOWN,
         BlockModelRenderer$Orientation.SOUTH,
         BlockModelRenderer$Orientation.FLIP_DOWN,
         BlockModelRenderer$Orientation.FLIP_SOUTH,
         BlockModelRenderer$Orientation.DOWN,
         BlockModelRenderer$Orientation.FLIP_SOUTH,
         BlockModelRenderer$Orientation.DOWN,
         BlockModelRenderer$Orientation.SOUTH
      },
      new BlockModelRenderer$Orientation[]{
         BlockModelRenderer$Orientation.FLIP_DOWN,
         BlockModelRenderer$Orientation.NORTH,
         BlockModelRenderer$Orientation.FLIP_DOWN,
         BlockModelRenderer$Orientation.FLIP_NORTH,
         BlockModelRenderer$Orientation.DOWN,
         BlockModelRenderer$Orientation.FLIP_NORTH,
         BlockModelRenderer$Orientation.DOWN,
         BlockModelRenderer$Orientation.NORTH
      },
      new BlockModelRenderer$Orientation[]{
         BlockModelRenderer$Orientation.FLIP_UP,
         BlockModelRenderer$Orientation.NORTH,
         BlockModelRenderer$Orientation.FLIP_UP,
         BlockModelRenderer$Orientation.FLIP_NORTH,
         BlockModelRenderer$Orientation.UP,
         BlockModelRenderer$Orientation.FLIP_NORTH,
         BlockModelRenderer$Orientation.UP,
         BlockModelRenderer$Orientation.NORTH
      },
      new BlockModelRenderer$Orientation[]{
         BlockModelRenderer$Orientation.FLIP_UP,
         BlockModelRenderer$Orientation.SOUTH,
         BlockModelRenderer$Orientation.FLIP_UP,
         BlockModelRenderer$Orientation.FLIP_SOUTH,
         BlockModelRenderer$Orientation.UP,
         BlockModelRenderer$Orientation.FLIP_SOUTH,
         BlockModelRenderer$Orientation.UP,
         BlockModelRenderer$Orientation.SOUTH
      }
   ),
   DOWN(
      new EnumFacing[]{EnumFacing.WEST, EnumFacing.EAST, EnumFacing.NORTH, EnumFacing.SOUTH},
      0.5F,
      false,
      new BlockModelRenderer$Orientation[0],
      new BlockModelRenderer$Orientation[0],
      new BlockModelRenderer$Orientation[0],
      new BlockModelRenderer$Orientation[0]
   ),
   NORTH(
      new EnumFacing[]{EnumFacing.UP, EnumFacing.DOWN, EnumFacing.EAST, EnumFacing.WEST},
      0.8F,
      true,
      new BlockModelRenderer$Orientation[]{
         BlockModelRenderer$Orientation.UP,
         BlockModelRenderer$Orientation.FLIP_WEST,
         BlockModelRenderer$Orientation.UP,
         BlockModelRenderer$Orientation.WEST,
         BlockModelRenderer$Orientation.FLIP_UP,
         BlockModelRenderer$Orientation.WEST,
         BlockModelRenderer$Orientation.FLIP_UP,
         BlockModelRenderer$Orientation.FLIP_WEST
      },
      new BlockModelRenderer$Orientation[]{
         BlockModelRenderer$Orientation.UP,
         BlockModelRenderer$Orientation.FLIP_EAST,
         BlockModelRenderer$Orientation.UP,
         BlockModelRenderer$Orientation.EAST,
         BlockModelRenderer$Orientation.FLIP_UP,
         BlockModelRenderer$Orientation.EAST,
         BlockModelRenderer$Orientation.FLIP_UP,
         BlockModelRenderer$Orientation.FLIP_EAST
      },
      new BlockModelRenderer$Orientation[]{
         BlockModelRenderer$Orientation.DOWN,
         BlockModelRenderer$Orientation.FLIP_EAST,
         BlockModelRenderer$Orientation.DOWN,
         BlockModelRenderer$Orientation.EAST,
         BlockModelRenderer$Orientation.FLIP_DOWN,
         BlockModelRenderer$Orientation.EAST,
         BlockModelRenderer$Orientation.FLIP_DOWN,
         BlockModelRenderer$Orientation.FLIP_EAST
      },
      new BlockModelRenderer$Orientation[]{
         BlockModelRenderer$Orientation.DOWN,
         BlockModelRenderer$Orientation.FLIP_WEST,
         BlockModelRenderer$Orientation.DOWN,
         BlockModelRenderer$Orientation.WEST,
         BlockModelRenderer$Orientation.FLIP_DOWN,
         BlockModelRenderer$Orientation.WEST,
         BlockModelRenderer$Orientation.FLIP_DOWN,
         BlockModelRenderer$Orientation.FLIP_WEST
      }
   ),
   WEST(
      new EnumFacing[]{EnumFacing.UP, EnumFacing.DOWN, EnumFacing.NORTH, EnumFacing.SOUTH},
      0.6F,
      true,
      new BlockModelRenderer$Orientation[]{
         BlockModelRenderer$Orientation.UP,
         BlockModelRenderer$Orientation.SOUTH,
         BlockModelRenderer$Orientation.UP,
         BlockModelRenderer$Orientation.FLIP_SOUTH,
         BlockModelRenderer$Orientation.FLIP_UP,
         BlockModelRenderer$Orientation.FLIP_SOUTH,
         BlockModelRenderer$Orientation.FLIP_UP,
         BlockModelRenderer$Orientation.SOUTH
      },
      new BlockModelRenderer$Orientation[]{
         BlockModelRenderer$Orientation.UP,
         BlockModelRenderer$Orientation.NORTH,
         BlockModelRenderer$Orientation.UP,
         BlockModelRenderer$Orientation.FLIP_NORTH,
         BlockModelRenderer$Orientation.FLIP_UP,
         BlockModelRenderer$Orientation.FLIP_NORTH,
         BlockModelRenderer$Orientation.FLIP_UP,
         BlockModelRenderer$Orientation.NORTH
      },
      new BlockModelRenderer$Orientation[]{
         BlockModelRenderer$Orientation.DOWN,
         BlockModelRenderer$Orientation.NORTH,
         BlockModelRenderer$Orientation.DOWN,
         BlockModelRenderer$Orientation.FLIP_NORTH,
         BlockModelRenderer$Orientation.FLIP_DOWN,
         BlockModelRenderer$Orientation.FLIP_NORTH,
         BlockModelRenderer$Orientation.FLIP_DOWN,
         BlockModelRenderer$Orientation.NORTH
      },
      new BlockModelRenderer$Orientation[]{
         BlockModelRenderer$Orientation.DOWN,
         BlockModelRenderer$Orientation.SOUTH,
         BlockModelRenderer$Orientation.DOWN,
         BlockModelRenderer$Orientation.FLIP_SOUTH,
         BlockModelRenderer$Orientation.FLIP_DOWN,
         BlockModelRenderer$Orientation.FLIP_SOUTH,
         BlockModelRenderer$Orientation.FLIP_DOWN,
         BlockModelRenderer$Orientation.SOUTH
      }
   ),
   UP(
      new EnumFacing[]{EnumFacing.EAST, EnumFacing.WEST, EnumFacing.NORTH, EnumFacing.SOUTH},
      1.0F,
      false,
      new BlockModelRenderer$Orientation[0],
      new BlockModelRenderer$Orientation[0],
      new BlockModelRenderer$Orientation[0],
      new BlockModelRenderer$Orientation[0]
   );
   public EnumFacing[] field_178276_g;
   public BlockModelRenderer$Orientation[] field_178285_m;
   public boolean field_178289_i;
   public TileEntityDropper field_0003;
   public RenderGiantZombie$1 field_0004;
   public float field_178288_h;
   public BlockModelRenderer$Orientation[] field_178286_j;
   public HttpPostBodyUtil field_0020;
   public CustomModelRenderer field_0002;
   public ModelAdapterHeadHumanoid field_0010;
   // $VF: synthetic field
   public static BlockModelRenderer$EnumNeighborInfo[] $VALUES = new BlockModelRenderer$EnumNeighborInfo[]{
      BlockModelRenderer$EnumNeighborInfo.DOWN,
      BlockModelRenderer$EnumNeighborInfo.UP,
      BlockModelRenderer$EnumNeighborInfo.NORTH,
      SOUTH,
      BlockModelRenderer$EnumNeighborInfo.WEST,
      EAST
   };
   public BlockModelRenderer$Orientation[] field_178287_k;
   public BlockPart$Deserializer field_0006;
   public BlockModelRenderer$Orientation[] field_178284_l;
   public static BlockModelRenderer$EnumNeighborInfo[] VALUES = new BlockModelRenderer$EnumNeighborInfo[6];

   public BlockModelRenderer$EnumNeighborInfo(
      EnumFacing[] var3,
      float var4,
      boolean var5,
      BlockModelRenderer$Orientation[] var6,
      BlockModelRenderer$Orientation[] var7,
      BlockModelRenderer$Orientation[] var8,
      BlockModelRenderer$Orientation[] var9
   ) {
      this.field_178276_g = var3;
      this.field_178288_h = var4;
      this.field_178289_i = var5;
      this.field_178286_j = var6;
      this.field_178287_k = var7;
      this.field_178284_l = var8;
      this.field_178285_m = var9;
   }

   static {
      VALUES[EnumFacing.DOWN.getIndex()] = DOWN;
      VALUES[EnumFacing.UP.getIndex()] = UP;
      VALUES[EnumFacing.NORTH.getIndex()] = NORTH;
      VALUES[EnumFacing.SOUTH.getIndex()] = SOUTH;
      VALUES[EnumFacing.WEST.getIndex()] = WEST;
      VALUES[EnumFacing.EAST.getIndex()] = EAST;
   }

   public static BlockModelRenderer$EnumNeighborInfo getNeighbourInfo(EnumFacing var0) {
      return VALUES[var0.getIndex()];
   }
}
