package recovered.unidentified;

import io.netty.handler.codec.http.HttpVersion;
import java.util.List;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition$MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class UnidentifiedClass0173 extends Item {
   public HttpVersion field_0000;
   public PropertyBool field_0001;

   @Override
   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      float var4 = 1.0F;
      float var5 = var3.B + (var3.z - var3.B) * var4;
      float var6 = var3.A + (var3.y - var3.A) * var4;
      double var7 = var3.p + (var3.s - var3.p) * var4;
      double var9 = var3.q + (var3.t - var3.q) * var4 + var3.getEyeHeight();
      double var11 = var3.r + (var3.u - var3.r) * var4;
      Vec3 var13 = new Vec3(var7, var9, var11);
      float var14 = MathHelper.cos(-var6 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float var15 = MathHelper.sin(-var6 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float var16 = -MathHelper.cos(-var5 * (float) (Math.PI / 180.0));
      float var17 = MathHelper.sin(-var5 * (float) (Math.PI / 180.0));
      float var18 = var15 * var16;
      float var19 = var14 * var16;
      double var20 = 5.0;
      Vec3 var22 = var13.addVector(var18 * var20, var17 * var20, var19 * var20);
      MovingObjectPosition var23 = var2.rayTraceBlocks(var13, var22, true);
      if (var23 == null) {
         return var1;
      } else {
         Vec3 var24 = var3.getLook(var4);
         boolean var25 = false;
         float var26 = 1.0F;
         List var27 = var2.getEntitiesWithinAABBExcludingEntity(
            var3, var3.getEntityBoundingBox().addCoord(var24.xCoord * var20, var24.yCoord * var20, var24.zCoord * var20).expand(var26, var26, var26)
         );

         for (int var28 = 0; var28 < var27.size(); var28++) {
            Entity var29 = (Entity)var27.get(var28);
            if (var29.canBeCollidedWith()) {
               float var30 = var29.getCollisionBorderSize();
               AxisAlignedBB var31 = var29.getEntityBoundingBox().expand(var30, var30, var30);
               if (var31.isVecInside(var13)) {
                  var25 = true;
               }
            }
         }

         if (var25) {
            return var1;
         } else {
            if (var23.typeOfHit == MovingObjectPosition$MovingObjectType.BLOCK) {
               BlockPos var32 = var23.getBlockPos();
               if (var2.getBlockState(var32).getBlock() == Blocks.snow_layer) {
                  var32 = var32.down();
               }

               EntityBoat var33 = new EntityBoat(var2, var32.getX() + 0.5F, var32.getY() + 1.0F, var32.getZ() + 0.5F);
               var33.y = ((MathHelper.floor_double(var3.y * 4.0F / 360.0F + 0.5) & 3) - 1) * 90;
               if (!var2.a(var33, var33.getEntityBoundingBox().expand(-0.1, -0.1, -0.1)).isEmpty()) {
                  return var1;
               }

               if (!var2.D) {
                  var2.spawnEntityInWorld(var33);
               }

               if (!var3.bA.isCreativeMode) {
                  var1.stackSize--;
               }

               var3.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
            }

            return var1;
         }
      }
   }

   public UnidentifiedClass0173() {
      this.h = 1;
      this.setCreativeTab(CreativeTabs.tabTransport);
   }
}
