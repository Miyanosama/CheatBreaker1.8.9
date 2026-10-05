package net.minecraft.entity.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockRailBase$EnumRailDirection;
import net.minecraft.block.BlockRailPowered;
import net.minecraft.block.BlockSilverfish$EnumType$1;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBlock$1;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMinecartCommandBlock;
import net.minecraft.entity.ai.EntityMinecartMobSpawner;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.IWorldNameable;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class EntityMinecart extends Entity implements IWorldNameable {
   public boolean isInReverse;
   public double minecartY;
   public double minecartX;
   public double velocityZ;
   public double velocityX;
   public BlockSilverfish$EnumType$1 field_0010;
   public double minecartYaw;
   public double velocityY;
   public int turnProgress;
   public double minecartZ;
   public ModelBlock$1 field_0011;
   public double minecartPitch;
   public static int[][][] matrix = new int[][][]{
      {{0, 0, -1}, {0, 0, 1}},
      {{-1, 0, 0}, {1, 0, 0}},
      {{-1, -1, 0}, {1, 0, 0}},
      {{-1, 0, 0}, {1, -1, 0}},
      {{0, 0, -1}, {0, -1, 1}},
      {{0, -1, -1}, {0, 0, 1}},
      {{0, 0, 1}, {1, 0, 0}},
      {{0, 0, 1}, {-1, 0, 0}},
      {{0, 0, -1}, {-1, 0, 0}},
      {{0, 0, -1}, {1, 0, 0}}
   };
   public String entityName;

   public EntityMinecart(World var1) {
      super(var1);
      this.k = true;
      this.setSize(0.98F, 0.7F);
   }

   @Override
   public void applyEntityCollision(Entity var1) {
      if (!this.o.D && !var1.T && !this.T && var1 != this.l) {
         if (var1 instanceof EntityLivingBase
            && !(var1 instanceof EntityPlayer)
            && !(var1 instanceof EntityIronGolem)
            && this.getMinecartType() == EntityMinecart$EnumMinecartType.RIDEABLE
            && this.v * this.v + this.x * this.x > 0.01
            && this.l == null
            && var1.m == null) {
            var1.mountEntity(this);
         }

         double var2 = var1.s - this.s;
         double var4 = var1.u - this.u;
         double var6 = var2 * var2 + var4 * var4;
         if (var6 >= 1.0E-4F) {
            var6 = MathHelper.sqrt_double(var6);
            var2 /= var6;
            var4 /= var6;
            double var8 = 1.0 / var6;
            if (var8 > 1.0) {
               var8 = 1.0;
            }

            var2 *= var8;
            var4 *= var8;
            var2 *= 0.1F;
            var4 *= 0.1F;
            var2 *= 1.0F - this.field_0005;
            var4 *= 1.0F - this.field_0005;
            var2 *= 0.5;
            var4 *= 0.5;
            if (var1 instanceof EntityMinecart) {
               double var10 = var1.s - this.s;
               double var12 = var1.u - this.u;
               Vec3 var14 = new Vec3(var10, 0.0, var12).normalize();
               Vec3 var15 = new Vec3(MathHelper.cos(this.y * (float) Math.PI / 180.0F), 0.0, MathHelper.sin(this.y * (float) Math.PI / 180.0F)).normalize();
               double var16 = Math.abs(var14.dotProduct(var15));
               if (var16 < 0.8F) {
                  return;
               }

               double var18 = var1.v + this.v;
               double var20 = var1.x + this.x;
               if (((EntityMinecart)var1).getMinecartType() == EntityMinecart$EnumMinecartType.FURNACE
                  && this.getMinecartType() != EntityMinecart$EnumMinecartType.FURNACE) {
                  this.v *= 0.2F;
                  this.x *= 0.2F;
                  this.addVelocity(var1.v - var2, 0.0, var1.x - var4);
                  var1.v *= 0.95F;
                  var1.x *= 0.95F;
               } else if (((EntityMinecart)var1).getMinecartType() != EntityMinecart$EnumMinecartType.FURNACE
                  && this.getMinecartType() == EntityMinecart$EnumMinecartType.FURNACE) {
                  var1.v *= 0.2F;
                  var1.x *= 0.2F;
                  var1.addVelocity(this.v + var2, 0.0, this.x + var4);
                  this.v *= 0.95F;
                  this.x *= 0.95F;
               } else {
                  var18 /= 2.0;
                  var20 /= 2.0;
                  this.v *= 0.2F;
                  this.x *= 0.2F;
                  this.addVelocity(var18 - var2, 0.0, var20 - var4);
                  var1.v *= 0.2F;
                  var1.x *= 0.2F;
                  var1.addVelocity(var18 + var2, 0.0, var20 + var4);
               }
            } else {
               this.addVelocity(-var2, 0.0, -var4);
               var1.addVelocity(var2 / 4.0, 0.0, var4 / 4.0);
            }
         }
      }
   }

   public void moveDerailedMinecart() {
      double var1 = this.getMaximumSpeed();
      this.v = MathHelper.clamp_double(this.v, -var1, var1);
      this.x = MathHelper.clamp_double(this.x, -var1, var1);
      if (this.C) {
         this.v *= 0.5;
         this.w *= 0.5;
         this.x *= 0.5;
      }

      this.d(this.v, this.w, this.x);
      if (!this.C) {
         this.v *= 0.95F;
         this.w *= 0.95F;
         this.x *= 0.95F;
      }
   }

   public int getDefaultDisplayTileOffset() {
      return 6;
   }

   public void setHasDisplayTile(boolean var1) {
      this.H().updateObject(22, (byte)(var1 ? 1 : 0));
   }

   @Override
   public void b(double var1, double var3, double var5) {
      this.s = var1;
      this.t = var3;
      this.u = var5;
      float var7 = this.J / 2.0F;
      float var8 = this.K;
      this.setEntityBoundingBox(new AxisAlignedBB(var1 - var7, var3, var5 - var7, var1 + var7, var3 + var8, var5 + var7));
   }

   public IBlockState getDefaultDisplayTile() {
      return Blocks.air.getDefaultState();
   }

   @Override
   public boolean m_() {
      return true;
   }

   public int getDisplayTileOffset() {
      return !this.hasDisplayTile() ? this.getDefaultDisplayTileOffset() : this.H().getWatchableObjectInt(21);
   }

   public abstract EntityMinecart$EnumMinecartType getMinecartType();

   @Override
   public String aM() {
      return this.entityName;
   }

   public Vec3 func_70489_a(double var1, double var3, double var5) {
      int var7 = MathHelper.floor_double(var1);
      int var8 = MathHelper.floor_double(var3);
      int var9 = MathHelper.floor_double(var5);
      if (BlockRailBase.isRailBlock(this.o, new BlockPos(var7, var8 - 1, var9))) {
         var8--;
      }

      IBlockState var10 = this.o.getBlockState(new BlockPos(var7, var8, var9));
      if (BlockRailBase.isRailBlock(var10)) {
         BlockRailBase$EnumRailDirection var11 = var10.getValue(((BlockRailBase)var10.getBlock()).getShapeProperty());
         int[][] var12 = matrix[var11.getMetadata()];
         double var13 = 0.0;
         double var15 = var7 + 0.5 + var12[0][0] * 0.5;
         double var17 = var8 + 0.0625 + var12[0][1] * 0.5;
         double var19 = var9 + 0.5 + var12[0][2] * 0.5;
         double var21 = var7 + 0.5 + var12[1][0] * 0.5;
         double var23 = var8 + 0.0625 + var12[1][1] * 0.5;
         double var25 = var9 + 0.5 + var12[1][2] * 0.5;
         double var27 = var21 - var15;
         double var29 = (var23 - var17) * 2.0;
         double var31 = var25 - var19;
         if (var27 == 0.0) {
            var1 = var7 + 0.5;
            var13 = var5 - var9;
         } else if (var31 == 0.0) {
            var5 = var9 + 0.5;
            var13 = var1 - var7;
         } else {
            double var33 = var1 - var15;
            double var35 = var5 - var19;
            var13 = (var33 * var27 + var35 * var31) * 2.0;
         }

         var1 = var15 + var27 * var13;
         var3 = var17 + var29 * var13;
         var5 = var19 + var31 * var13;
         if (var29 < 0.0) {
            var3++;
         }

         if (var29 > 0.0) {
            var3 += 0.5;
         }

         return new Vec3(var1, var3, var5);
      } else {
         return null;
      }
   }

   @Override
   public boolean canBeCollidedWith() {
      return !this.I;
   }

   @Override
   public double getMountedYOffset() {
      return 0.0;
   }

   @Override
   public void setDead() {
      super.setDead();
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      if (var1.getBoolean("CustomDisplayTile")) {
         int var2 = var1.getInteger("DisplayData");
         if (var1.hasKey("DisplayTile", 8)) {
            Block var3 = Block.getBlockFromName(var1.getString("DisplayTile"));
            if (var3 == null) {
               this.func_174899_a(Blocks.air.getDefaultState());
            } else {
               this.func_174899_a(var3.getStateFromMeta(var2));
            }
         } else {
            Block var4 = Block.getBlockById(var1.getInteger("DisplayTile"));
            if (var4 == null) {
               this.func_174899_a(Blocks.air.getDefaultState());
            } else {
               this.func_174899_a(var4.getStateFromMeta(var2));
            }
         }

         this.setDisplayTileOffset(var1.getInteger("DisplayOffset"));
      }

      if (var1.hasKey("CustomName", 8) && var1.getString("CustomName").length() > 0) {
         this.entityName = var1.getString("CustomName");
      }
   }

   @Override
   public void k_() {
      this.ac.addObject(17, new Integer(0));
      this.ac.addObject(18, new Integer(1));
      this.ac.addObject(19, new Float(0.0F));
      this.ac.addObject(20, new Integer(0));
      this.ac.addObject(21, new Integer(6));
      this.ac.addObject(22, (byte)0);
   }

   public Vec3 func_70495_a(double var1, double var3, double var5, double var7) {
      int var9 = MathHelper.floor_double(var1);
      int var10 = MathHelper.floor_double(var3);
      int var11 = MathHelper.floor_double(var5);
      if (BlockRailBase.isRailBlock(this.o, new BlockPos(var9, var10 - 1, var11))) {
         var10--;
      }

      IBlockState var12 = this.o.getBlockState(new BlockPos(var9, var10, var11));
      if (BlockRailBase.isRailBlock(var12)) {
         BlockRailBase$EnumRailDirection var13 = var12.getValue(((BlockRailBase)var12.getBlock()).getShapeProperty());
         var3 = var10;
         if (var13.isAscending()) {
            var3 = var10 + 1;
         }

         int[][] var14 = matrix[var13.getMetadata()];
         double var15 = var14[1][0] - var14[0][0];
         double var17 = var14[1][2] - var14[0][2];
         double var19 = Math.sqrt(var15 * var15 + var17 * var17);
         var15 /= var19;
         var17 /= var19;
         var1 += var15 * var7;
         var5 += var17 * var7;
         if (var14[0][1] != 0 && MathHelper.floor_double(var1) - var9 == var14[0][0] && MathHelper.floor_double(var5) - var11 == var14[0][2]) {
            var3 += var14[0][1];
         } else if (var14[1][1] != 0 && MathHelper.floor_double(var1) - var9 == var14[1][0] && MathHelper.floor_double(var5) - var11 == var14[1][2]) {
            var3 += var14[1][1];
         }

         return this.func_70489_a(var1, var3, var5);
      } else {
         return null;
      }
   }

   public int getRollingAmplitude() {
      return this.ac.getWatchableObjectInt(17);
   }

   public float getDamage() {
      return this.ac.getWatchableObjectFloat(19);
   }

   public double getMaximumSpeed() {
      return 0.4;
   }

   public boolean hasDisplayTile() {
      return this.H().getWatchableObjectByte(22) == 1;
   }

   @Override
   public void a(String var1) {
      this.entityName = var1;
   }

   @Override
   public void setPositionAndRotation2(double var1, double var3, double var5, float var7, float var8, int var9, boolean var10) {
      this.minecartX = var1;
      this.minecartY = var3;
      this.minecartZ = var5;
      this.minecartYaw = var7;
      this.minecartPitch = var8;
      this.turnProgress = var9 + 2;
      this.v = this.velocityX;
      this.w = this.velocityY;
      this.x = this.velocityZ;
   }

   @Override
   public AxisAlignedBB t_() {
      return null;
   }

   public IBlockState getDisplayTile() {
      return !this.hasDisplayTile() ? this.getDefaultDisplayTile() : Block.getStateById(this.H().getWatchableObjectInt(20));
   }

   public void func_180460_a(BlockPos var1, IBlockState var2) {
      this.O = 0.0F;
      Vec3 var3 = this.func_70489_a(this.s, this.t, this.u);
      this.t = var1.getY();
      boolean var4 = false;
      boolean var5 = false;
      BlockRailBase var6 = (BlockRailBase)var2.getBlock();
      if (var6 == Blocks.golden_rail) {
         var4 = var2.getValue(BlockRailPowered.POWERED);
         var5 = !var4;
      }

      double var7 = 0.0078125;
      BlockRailBase$EnumRailDirection var9 = var2.getValue(var6.getShapeProperty());
      switch (EntityMinecart$1.field_180036_b[var9.ordinal()]) {
         case 1:
            this.v -= 0.0078125;
            this.t++;
            break;
         case 2:
            this.v += 0.0078125;
            this.t++;
            break;
         case 3:
            this.x += 0.0078125;
            this.t++;
            break;
         case 4:
            this.x -= 0.0078125;
            this.t++;
      }

      int[][] var10 = matrix[var9.getMetadata()];
      double var11 = var10[1][0] - var10[0][0];
      double var13 = var10[1][2] - var10[0][2];
      double var15 = Math.sqrt(var11 * var11 + var13 * var13);
      double var17 = this.v * var11 + this.x * var13;
      if (var17 < 0.0) {
         var11 = -var11;
         var13 = -var13;
      }

      double var19 = Math.sqrt(this.v * this.v + this.x * this.x);
      if (var19 > 2.0) {
         var19 = 2.0;
      }

      this.v = var19 * var11 / var15;
      this.x = var19 * var13 / var15;
      if (this.l instanceof EntityLivingBase) {
         double var21 = ((EntityLivingBase)this.l).ba;
         if (var21 > 0.0) {
            double var23 = -Math.sin(this.l.y * (float) Math.PI / 180.0F);
            double var25 = Math.cos(this.l.y * (float) Math.PI / 180.0F);
            double var27 = this.v * this.v + this.x * this.x;
            if (var27 < 0.01) {
               this.v += var23 * 0.1;
               this.x += var25 * 0.1;
               var5 = false;
            }
         }
      }

      if (var5) {
         double var48 = Math.sqrt(this.v * this.v + this.x * this.x);
         if (var48 < 0.03) {
            this.v *= 0.0;
            this.w *= 0.0;
            this.x *= 0.0;
         } else {
            this.v *= 0.5;
            this.w *= 0.0;
            this.x *= 0.5;
         }
      }

      double var49 = 0.0;
      double var51 = var1.getX() + 0.5 + var10[0][0] * 0.5;
      double var52 = var1.getZ() + 0.5 + var10[0][2] * 0.5;
      double var53 = var1.getX() + 0.5 + var10[1][0] * 0.5;
      double var29 = var1.getZ() + 0.5 + var10[1][2] * 0.5;
      var11 = var53 - var51;
      var13 = var29 - var52;
      if (var11 == 0.0) {
         this.s = var1.getX() + 0.5;
         var49 = this.u - var1.getZ();
      } else if (var13 == 0.0) {
         this.u = var1.getZ() + 0.5;
         var49 = this.s - var1.getX();
      } else {
         double var31 = this.s - var51;
         double var33 = this.u - var52;
         var49 = (var31 * var11 + var33 * var13) * 2.0;
      }

      this.s = var51 + var11 * var49;
      this.u = var52 + var13 * var49;
      this.b(this.s, this.t, this.u);
      double var54 = this.v;
      double var56 = this.x;
      if (this.l != null) {
         var54 *= 0.75;
         var56 *= 0.75;
      }

      double var35 = this.getMaximumSpeed();
      var54 = MathHelper.clamp_double(var54, -var35, var35);
      var56 = MathHelper.clamp_double(var56, -var35, var35);
      this.d(var54, 0.0, var56);
      if (var10[0][1] != 0 && MathHelper.floor_double(this.s) - var1.getX() == var10[0][0] && MathHelper.floor_double(this.u) - var1.getZ() == var10[0][2]) {
         this.b(this.s, this.t + var10[0][1], this.u);
      } else if (var10[1][1] != 0
         && MathHelper.floor_double(this.s) - var1.getX() == var10[1][0]
         && MathHelper.floor_double(this.u) - var1.getZ() == var10[1][2]) {
         this.b(this.s, this.t + var10[1][1], this.u);
      }

      this.applyDrag();
      Vec3 var37 = this.func_70489_a(this.s, this.t, this.u);
      if (var37 != null && var3 != null) {
         double var38 = (var3.yCoord - var37.yCoord) * 0.05;
         var19 = Math.sqrt(this.v * this.v + this.x * this.x);
         if (var19 > 0.0) {
            this.v = this.v / var19 * (var19 + var38);
            this.x = this.x / var19 * (var19 + var38);
         }

         this.b(this.s, var37.yCoord, this.u);
      }

      int var58 = MathHelper.floor_double(this.s);
      int var39 = MathHelper.floor_double(this.u);
      if (var58 != var1.getX() || var39 != var1.getZ()) {
         var19 = Math.sqrt(this.v * this.v + this.x * this.x);
         this.v = var19 * (var58 - var1.getX());
         this.x = var19 * (var39 - var1.getZ());
      }

      if (var4) {
         double var40 = Math.sqrt(this.v * this.v + this.x * this.x);
         if (var40 > 0.01) {
            double var42 = 0.06;
            this.v = this.v + this.v / var40 * var42;
            this.x = this.x + this.x / var40 * var42;
         } else if (var9 == BlockRailBase$EnumRailDirection.EAST_WEST) {
            if (this.o.getBlockState(var1.west()).getBlock().isNormalCube()) {
               this.v = 0.02;
            } else if (this.o.getBlockState(var1.east()).getBlock().isNormalCube()) {
               this.v = -0.02;
            }
         } else if (var9 == BlockRailBase$EnumRailDirection.NORTH_SOUTH) {
            if (this.o.getBlockState(var1.north()).getBlock().isNormalCube()) {
               this.x = 0.02;
            } else if (this.o.getBlockState(var1.south()).getBlock().isNormalCube()) {
               this.x = -0.02;
            }
         }
      }
   }

   public void setRollingAmplitude(int var1) {
      this.ac.updateObject(17, var1);
   }

   @Override
   public boolean l_() {
      return false;
   }

   @Override
   public void onUpdate() {
      if (this.getRollingAmplitude() > 0) {
         this.setRollingAmplitude(this.getRollingAmplitude() - 1);
      }

      if (this.getDamage() > 0.0F) {
         this.setDamage(this.getDamage() - 1.0F);
      }

      if (this.t < -64.0) {
         this.kill();
      }

      if (!this.o.D && this.o instanceof WorldServer) {
         this.o.B.startSection("portal");
         MinecraftServer var1 = ((WorldServer)this.o).getMinecraftServer();
         int var2 = this.getMaxInPortalTime();
         if (this.inPortal) {
            if (var1.getAllowNether()) {
               if (this.m == null && this.portalCounter++ >= var2) {
                  this.portalCounter = var2;
                  this.timeUntilPortal = this.getPortalCooldown();
                  byte var3;
                  if (this.o.t.getDimensionId() == -1) {
                     var3 = 0;
                  } else {
                     var3 = -1;
                  }

                  this.travelToDimension(var3);
               }

               this.inPortal = false;
            }
         } else {
            if (this.portalCounter > 0) {
               this.portalCounter -= 4;
            }

            if (this.portalCounter < 0) {
               this.portalCounter = 0;
            }
         }

         if (this.timeUntilPortal > 0) {
            this.timeUntilPortal--;
         }

         this.o.B.endSection();
      }

      if (this.o.D) {
         if (this.turnProgress > 0) {
            double var14 = this.s + (this.minecartX - this.s) / this.turnProgress;
            double var17 = this.t + (this.minecartY - this.t) / this.turnProgress;
            double var5 = this.u + (this.minecartZ - this.u) / this.turnProgress;
            double var7 = MathHelper.wrapAngleTo180_double(this.minecartYaw - this.y);
            this.y = (float)(this.y + var7 / this.turnProgress);
            this.z = (float)(this.z + (this.minecartPitch - this.z) / this.turnProgress);
            this.turnProgress--;
            this.b(var14, var17, var5);
            this.setRotation(this.y, this.z);
         } else {
            this.b(this.s, this.t, this.u);
            this.setRotation(this.y, this.z);
         }
      } else {
         this.p = this.s;
         this.q = this.t;
         this.r = this.u;
         this.w -= 0.04F;
         int var15 = MathHelper.floor_double(this.s);
         int var16 = MathHelper.floor_double(this.t);
         int var18 = MathHelper.floor_double(this.u);
         if (BlockRailBase.isRailBlock(this.o, new BlockPos(var15, var16 - 1, var18))) {
            var16--;
         }

         BlockPos var4 = new BlockPos(var15, var16, var18);
         IBlockState var19 = this.o.getBlockState(var4);
         if (BlockRailBase.isRailBlock(var19)) {
            this.func_180460_a(var4, var19);
            if (var19.getBlock() == Blocks.activator_rail) {
               this.onActivatorRailPass(var15, var16, var18, var19.getValue(BlockRailPowered.POWERED));
            }
         } else {
            this.moveDerailedMinecart();
         }

         this.doBlockCollisions();
         this.z = 0.0F;
         double var6 = this.p - this.s;
         double var8 = this.r - this.u;
         if (var6 * var6 + var8 * var8 > 0.001) {
            this.y = (float)(MathHelper.atan2(var8, var6) * 180.0 / Math.PI);
            if (this.isInReverse) {
               this.y += 180.0F;
            }
         }

         double var10 = MathHelper.wrapAngleTo180_float(this.y - this.A);
         if (var10 < -170.0 || var10 >= 170.0) {
            this.y += 180.0F;
            this.isInReverse = !this.isInReverse;
         }

         this.setRotation(this.y, this.z);

         for (Entity var13 : this.o.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(0.2F, 0.0, 0.2F))) {
            if (var13 != this.l && var13.m_() && var13 instanceof EntityMinecart) {
               var13.applyEntityCollision(this);
            }
         }

         if (this.l != null && this.l.I) {
            if (this.l.m == this) {
               this.l.m = null;
            }

            this.l = null;
         }

         this.handleWaterMovement();
      }
   }

   @Override
   public IChatComponent getDisplayName() {
      if (this.u_()) {
         ChatComponentText var2 = new ChatComponentText(this.entityName);
         var2.getChatStyle().setChatHoverEvent(this.getHoverEvent());
         var2.getChatStyle().setInsertion(this.aK().toString());
         return var2;
      } else {
         ChatComponentTranslation var1 = new ChatComponentTranslation(this.z_());
         var1.getChatStyle().setChatHoverEvent(this.getHoverEvent());
         var1.getChatStyle().setInsertion(this.aK().toString());
         return var1;
      }
   }

   public static EntityMinecart getMinecart(World var0, double var1, double var3, double var5, EntityMinecart$EnumMinecartType var7) {
      switch (EntityMinecart$1.field_180037_a[var7.ordinal()]) {
         case 1:
            return new EntityMinecartChest(var0, var1, var3, var5);
         case 2:
            return new EntityMinecartFurnace(var0, var1, var3, var5);
         case 3:
            return new EntityMinecartTNT(var0, var1, var3, var5);
         case 4:
            return new EntityMinecartMobSpawner(var0, var1, var3, var5);
         case 5:
            return new EntityMinecartHopper(var0, var1, var3, var5);
         case 6:
            return new EntityMinecartCommandBlock(var0, var1, var3, var5);
         default:
            return new EntityMinecartEmpty(var0, var1, var3, var5);
      }
   }

   @Override
   public String z_() {
      return this.entityName != null ? this.entityName : super.z_();
   }

   @Override
   public void performHurtAnimation() {
      this.setRollingDirection(-this.getRollingDirection());
      this.setRollingAmplitude(10);
      this.setDamage(this.getDamage() + this.getDamage() * 10.0F);
   }

   public void onActivatorRailPass(int var1, int var2, int var3, boolean var4) {
   }

   public int getRollingDirection() {
      return this.ac.getWatchableObjectInt(18);
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (this.o.D || this.I) {
         return true;
      } else if (this.isEntityInvulnerable(var1)) {
         return false;
      } else {
         this.setRollingDirection(-this.getRollingDirection());
         this.setRollingAmplitude(10);
         this.setBeenAttacked();
         this.setDamage(this.getDamage() + var2 * 10.0F);
         boolean var3 = var1.getEntity() instanceof EntityPlayer && ((EntityPlayer)var1.getEntity()).bA.isCreativeMode;
         if (var3 || this.getDamage() > 40.0F) {
            if (this.l != null) {
               this.l.mountEntity((Entity)null);
            }

            if (var3 && !this.u_()) {
               this.setDead();
            } else {
               this.killMinecart(var1);
            }
         }

         return true;
      }
   }

   @Override
   public AxisAlignedBB getCollisionBox(Entity var1) {
      return var1.m_() ? var1.getEntityBoundingBox() : null;
   }

   public void func_174899_a(IBlockState var1) {
      this.H().updateObject(20, Block.getStateId(var1));
      this.setHasDisplayTile(true);
   }

   public void setDisplayTileOffset(int var1) {
      this.H().updateObject(21, var1);
      this.setHasDisplayTile(true);
   }

   public void setRollingDirection(int var1) {
      this.ac.updateObject(18, var1);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      if (this.hasDisplayTile()) {
         var1.setBoolean("CustomDisplayTile", true);
         IBlockState var2 = this.getDisplayTile();
         ResourceLocation var3 = Block.blockRegistry.getNameForObject(var2.getBlock());
         var1.setString("DisplayTile", var3 == null ? "" : var3.toString());
         var1.setInteger("DisplayData", var2.getBlock().getMetaFromState(var2));
         var1.setInteger("DisplayOffset", this.getDisplayTileOffset());
      }

      if (this.entityName != null && this.entityName.length() > 0) {
         var1.setString("CustomName", this.entityName);
      }
   }

   @Override
   public void setVelocity(double var1, double var3, double var5) {
      this.velocityX = this.v = var1;
      this.velocityY = this.w = var3;
      this.velocityZ = this.x = var5;
   }

   @Override
   public boolean u_() {
      return this.entityName != null;
   }

   public EntityMinecart(World var1, double var2, double var4, double var6) {
      this(var1);
      this.b(var2, var4, var6);
      this.v = 0.0;
      this.w = 0.0;
      this.x = 0.0;
      this.p = var2;
      this.q = var4;
      this.r = var6;
   }

   public void killMinecart(DamageSource var1) {
      this.setDead();
      if (this.o.Q().getBoolean("doEntityDrops")) {
         ItemStack var2 = new ItemStack(Items.minecart, 1);
         if (this.entityName != null) {
            var2.setStackDisplayName(this.entityName);
         }

         this.a(var2, 0.0F);
      }
   }

   public void applyDrag() {
      if (this.l != null) {
         this.v *= 0.997F;
         this.w *= 0.0;
         this.x *= 0.997F;
      } else {
         this.v *= 0.96F;
         this.w *= 0.0;
         this.x *= 0.96F;
      }
   }

   public void setDamage(float var1) {
      this.ac.updateObject(19, var1);
   }
}
