package net.minecraft.entity;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.module.type.ParticlesModule;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.HoverEvent;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class Entity implements ICommandSender {
   public double t;
   public boolean inPortal;
   public double p;
   public boolean G;
   public boolean I;
   public double R;
   public int fireResistance;
   public boolean invulnerable;
   public boolean aa;
   public boolean ab;
   public int am;
   public float B;
   public int chunkCoordZ;
   public float S;
   public Random V;
   public BlockPos an;
   public double u;
   public static AxisAlignedBB ZERO_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
   public boolean ai;
   public boolean T;
   public int nextStepDistance;
   public float recoveredField1640;
   public boolean ah;
   public double j;
   public int fire;
   public Vec3 ao;
   public float recoveredField1641;
   public boolean n;
   public float M;
   public EnumFacing ap;
   public int timeUntilPortal;
   public boolean k;
   public float z;
   public int entityId;
   public int portalCounter;
   public double w;
   public DataWatcher ac;
   public Entity m;
   public static int nextEntityID;
   public World o;
   public float J;
   public boolean addedToChunk;
   public int Z;
   public double v;
   public int bW;
   public boolean C;
   public boolean Y;
   public double r;
   public Entity l;
   public float y;
   public float O;
   public double recoveredField1642;
   public boolean recoveredField1643;
   public double P;
   public int bY;
   public int bX;
   public double Q;
   public boolean isInWeb;
   public boolean recoveredField1644;
   public int chunkCoordY;
   public UUID aq;
   public CommandResultStats cmdResultStats;
   public float L;
   public float A;
   public int W;
   public double x;
   public boolean isOutsideBorder;
   public AxisAlignedBB boundingBox;
   public double q;
   public float K;
   public int chunkCoordX;
   public boolean D;
   public double s;
   public double recoveredField1645;

   public void setSilent(boolean var1) {
      this.ac.updateObject(4, (byte)(var1 ? 1 : 0));
   }

   public boolean isOutsideBorder() {
      return this.isOutsideBorder;
   }

   public float getExplosionResistance(Explosion var1, World var2, BlockPos var3, IBlockState var4) {
      return var4.getBlock().getExplosionResistance(this);
   }

   public void clientUpdateEntityNBT(NBTTagCompound var1) {
   }

   public abstract void readEntityFromNBT(NBTTagCompound var1);

   public boolean isInvisible() {
      return this.getFlag(5);
   }

   public EntityItem dropItemWithOffset(Item var1, int var2, float var3) {
      return this.a(new ItemStack(var1, var2, 0), var3);
   }

   @Override
   public BlockPos getPosition() {
      return new BlockPos(this.s, this.t + 0.5, this.u);
   }

   public void setInWeb() {
      this.isInWeb = true;
      this.O = 0.0F;
   }

   public void setCommandStats(Entity var1) {
      this.cmdResultStats.addAllStats(var1.getCommandStats());
   }

   public Vec3 method_10465() {
      return this.ao;
   }

   public void b_(EntityPlayer var1) {
   }

   @Override
   public Entity p_() {
      return this;
   }

   public void setRotation(float var1, float var2) {
      this.y = var1 % 360.0F;
      this.z = var2 % 360.0F;
   }

   public void setPositionAndRotation2(double var1, double var3, double var5, float var7, float var8, int var9, boolean var10) {
      this.b(var1, var3, var5);
      this.setRotation(var7, var8);
      List var11 = this.o.a(this, this.getEntityBoundingBox().contract(0.03125, 0.0, 0.03125));
      if (!var11.isEmpty()) {
         double var12 = 0.0;

         for (AxisAlignedBB var15 : (Iterable<AxisAlignedBB>)(Iterable<?>)(var11)) {
            if (var15.e > var12) {
               var12 = var15.e;
            }
         }

         var3 += var12 - this.getEntityBoundingBox().b;
         this.b(var1, var3, var5);
      }
   }

   public boolean interactAt(EntityPlayer var1, Vec3 var2) {
      return false;
   }

   public boolean writeMountToNBT(NBTTagCompound var1) {
      String var2 = this.getEntityString();
      if (!this.I && var2 != null) {
         var1.setString("id", var2);
         this.e(var1);
         return true;
      } else {
         return false;
      }
   }

   public void onDataWatcherUpdate(int var1) {
   }

   public String getSplashSound() {
      return "game.neutral.swim.splash";
   }

   public EnumFacing getHorizontalFacing() {
      return EnumFacing.getHorizontal(MathHelper.floor_double(this.y * 4.0F / 360.0F + 0.5) & 3);
   }

   public void a_(double var1, double var3, double var5, float var7, float var8) {
      this.P = this.p = this.s = var1;
      this.Q = this.q = this.t = var3;
      this.R = this.r = this.u = var5;
      this.y = var7;
      this.z = var8;
      this.b(this.s, this.t, this.u);
   }

   public double getYOffset() {
      return 0.0;
   }

   public MovingObjectPosition rayTrace(double var1, float var3) {
      Vec3 var4 = this.getPositionEyes(var3);
      Vec3 var5 = this.getLook(var3);
      Vec3 var6 = var4.addVector(var5.xCoord * var1, var5.yCoord * var1, var5.zCoord * var1);
      return this.o.rayTraceBlocks(var4, var6, false, false, true);
   }

   public void setFire(int var1) {
      int var2 = var1 * 20;
      var2 = EnchantmentProtection.getFireTimeForEntity(this, var2);
      if (this.fire < var2) {
         this.fire = var2;
      }
   }

   public boolean R() {
      return this.ac.getWatchableObjectByte(4) == 1;
   }

   public boolean writeToNBTOptional(NBTTagCompound var1) {
      String var2 = this.getEntityString();
      if (!this.I && var2 != null && this.l == null) {
         var1.setString("id", var2);
         this.e(var1);
         return true;
      } else {
         return false;
      }
   }

   public boolean isEntityAlive() {
      return !this.I;
   }

   public Entity[] getParts() {
      return null;
   }

   public double getDistanceSq(BlockPos var1) {
      return var1.distanceSq(this.s, this.t, this.u);
   }

   public void copyDataFromOld(Entity var1) {
      NBTTagCompound var2 = new NBTTagCompound();
      var1.e(var2);
      this.f(var2);
      this.timeUntilPortal = var1.timeUntilPortal;
      this.an = var1.an;
      this.ao = var1.ao;
      this.ap = var1.ap;
   }

   public void performHurtAnimation() {
   }

   public boolean method_10440() {
      return this.isBurning();
   }

   public NBTTagList newFloatNBTList(float... var1) {
      NBTTagList var2 = new NBTTagList();

      for (float var6 : var1) {
         var2.appendTag(new NBTTagFloat(var6));
      }

      return var2;
   }

   public AxisAlignedBB getEntityBoundingBox() {
      return this.boundingBox;
   }

   public CommandResultStats getCommandStats() {
      return this.cmdResultStats;
   }

   public UUID aK() {
      return this.aq;
   }

   public boolean isBurning() {
      boolean var1 = this.o != null && this.o.D;
      return !this.ab && (this.fire > 0 || var1 && this.getFlag(0));
   }

   public void playStepSound(BlockPos var1, Block var2) {
      Block.SoundType var3 = var2.stepSound;
      if (this.o.getBlockState(var1.up()).getBlock() == Blocks.snow_layer) {
         var3 = Blocks.snow_layer.stepSound;
         this.playSound(var3.getStepSound(), var3.getVolume() * 0.15F, var3.getFrequency());
      } else if (!var2.getMaterial().isLiquid()) {
         this.playSound(var3.getStepSound(), var3.getVolume() * 0.15F, var3.getFrequency());
      }
   }

   public double getDistance(double var1, double var3, double var5) {
      double var7 = this.s - var1;
      double var9 = this.t - var3;
      double var11 = this.u - var5;
      return MathHelper.sqrt_double(var7 * var7 + var9 * var9 + var11 * var11);
   }

   public boolean V() {
      return this.Y;
   }

   public void setOutsideBorder(boolean var1) {
      this.isOutsideBorder = var1;
   }

   public void setInvisible(boolean var1) {
      this.setFlag(5, var1);
   }

   @Override
   public boolean equals(Object var1) {
      return var1 instanceof Entity ? ((Entity)var1).entityId == this.entityId : false;
   }

   @Override
   public Vec3 q_() {
      return new Vec3(this.s, this.t, this.u);
   }

   public void setCurrentItemOrArmor(int var1, ItemStack var2) {
   }

   public Vec3 getLook(float var1) {
      if (var1 == 1.0F) {
         return this.getVectorForRotation(this.z, this.y);
      } else {
         float var2 = this.B + (this.z - this.B) * var1;
         float var3 = this.A + (this.y - this.A) * var1;
         return this.getVectorForRotation(var2, var3);
      }
   }

   public void setSprinting(boolean var1) {
      this.setFlag(3, var1);
   }

   public int F() {
      return this.entityId;
   }

   @Override
   public String z_() {
      if (this.u_()) {
         return this.aM();
      } else {
         String var1 = EntityList.getEntityString(this);
         if (var1 == null) {
            var1 = "generic";
         }

         return StatCollector.translateToLocal("entity." + var1 + ".name");
      }
   }

   public void resetPositionToBB() {
      this.s = (this.getEntityBoundingBox().a + this.getEntityBoundingBox().d) / 2.0;
      this.t = this.getEntityBoundingBox().b;
      this.u = (this.getEntityBoundingBox().c + this.getEntityBoundingBox().f) / 2.0;
   }

   public Vec3 getLookVec() {
      return null;
   }

   public void setWorld(World var1) {
      this.o = var1;
   }

   public int getAir() {
      return this.ac.getWatchableObjectShort(1);
   }

   public float getEyeHeight() {
      return this.K * 0.85F;
   }

   public void setBeenAttacked() {
      this.G = true;
   }

   public void setAlwaysRenderNameTag(boolean var1) {
      this.ac.updateObject(3, (byte)(var1 ? 1 : 0));
   }

   public String getEntityString() {
      return EntityList.getEntityString(this);
   }

   public float a_(float var1) {
      BlockPos var2 = new BlockPos(this.s, this.t + this.getEyeHeight(), this.u);
      return this.o.e(var2) ? this.o.o(var2) : 0.0F;
   }

   public boolean r_() {
      return true;
   }

   public Vec3 getPositionEyes(float var1) {
      if (var1 == 1.0F) {
         return new Vec3(this.s, this.t + this.getEyeHeight(), this.u);
      } else {
         double var2 = this.p + (this.s - this.p) * var1;
         double var4 = this.q + (this.t - this.q) * var1 + this.getEyeHeight();
         double var6 = this.r + (this.u - this.r) * var1;
         return new Vec3(var2, var4, var6);
      }
   }

   public void setRotationYawHead(float var1) {
   }

   public boolean j(double var1, double var3, double var5) {
      BlockPos var7 = new BlockPos(var1, var3, var5);
      double var8 = var1 - var7.getX();
      double var10 = var3 - var7.getY();
      double var12 = var5 - var7.getZ();
      List var14 = this.o.getCollisionBoxes(this.getEntityBoundingBox());
      if (var14.isEmpty() && !this.o.isBlockFullCube(var7)) {
         return false;
      } else {
         byte var15 = 3;
         double var16 = 9999.0;
         if (!this.o.isBlockFullCube(var7.west()) && var8 < var16) {
            var16 = var8;
            var15 = 0;
         }

         if (!this.o.isBlockFullCube(var7.east()) && 1.0 - var8 < var16) {
            var16 = 1.0 - var8;
            var15 = 1;
         }

         if (!this.o.isBlockFullCube(var7.up()) && 1.0 - var10 < var16) {
            var16 = 1.0 - var10;
            var15 = 3;
         }

         if (!this.o.isBlockFullCube(var7.north()) && var12 < var16) {
            var16 = var12;
            var15 = 4;
         }

         if (!this.o.isBlockFullCube(var7.south()) && 1.0 - var12 < var16) {
            var16 = 1.0 - var12;
            var15 = 5;
         }

         float var18 = this.V.nextFloat() * 0.2F + 0.1F;
         if (var15 == 0) {
            this.v = -var18;
         }

         if (var15 == 1) {
            this.v = var18;
         }

         if (var15 == 3) {
            this.w = var18;
         }

         if (var15 == 4) {
            this.x = -var18;
         }

         if (var15 == 5) {
            this.x = var18;
         }

         return true;
      }
   }

   public float g(Entity var1) {
      float var2 = (float)(this.s - var1.s);
      float var3 = (float)(this.t - var1.t);
      float var4 = (float)(this.u - var1.u);
      return MathHelper.sqrt_float(var2 * var2 + var3 * var3 + var4 * var4);
   }

   public NBTTagList newDoubleNBTList(double... var1) {
      NBTTagList var2 = new NBTTagList();

      for (double var6 : var1) {
         var2.appendTag(new NBTTagDouble(var6));
      }

      return var2;
   }

   public boolean isPushedByWater() {
      return true;
   }

   public boolean isOffsetPositionInLiquid(double var1, double var3, double var5) {
      AxisAlignedBB var7 = this.getEntityBoundingBox().offset(var1, var3, var5);
      return this.isLiquidPresentInAABB(var7);
   }

   public void applyEntityCollision(Entity var1) {
      if (var1.l != this && var1.m != this && !var1.T && !this.T) {
         double var2 = var1.s - this.s;
         double var4 = var1.u - this.u;
         double var6 = MathHelper.abs_max(var2, var4);
         if (var6 >= 0.01F) {
            var6 = MathHelper.sqrt_double(var6);
            var2 /= var6;
            var4 /= var6;
            double var8 = 1.0 / var6;
            if (var8 > 1.0) {
               var8 = 1.0;
            }

            var2 *= var8;
            var4 *= var8;
            var2 *= 0.05F;
            var4 *= 0.05F;
            var2 *= 1.0F - this.recoveredField1640;
            var4 *= 1.0F - this.recoveredField1640;
            if (this.l == null) {
               this.addVelocity(-var2, 0.0, -var4);
            }

            if (var1.l == null) {
               var1.addVelocity(var2, 0.0, var4);
            }
         }
      }
   }

   public boolean isLiquidPresentInAABB(AxisAlignedBB var1) {
      return this.o.a(this, var1).isEmpty() && !this.o.isAnyLiquid(var1);
   }

   public boolean hitByEntity(Entity var1) {
      return false;
   }

   public boolean a_(EntityPlayer var1) {
      return false;
   }

   public void setEntityBoundingBox(AxisAlignedBB var1) {
      this.boundingBox = var1;
   }

   public void a(double var1, double var3, double var5, float var7, float var8) {
      this.p = this.s = var1;
      this.q = this.t = var3;
      this.r = this.u = var5;
      this.A = this.y = var7;
      this.B = this.z = var8;
      double var9 = this.A - var7;
      if (var9 < -180.0) {
         this.A += 360.0F;
      }

      if (var9 >= 180.0) {
         this.A -= 360.0F;
      }

      this.b(this.s, this.t, this.u);
      this.setRotation(var7, var8);
   }

   public void setVelocity(double var1, double var3, double var5) {
      this.v = var1;
      this.w = var3;
      this.x = var5;
   }

   public void f(NBTTagCompound var1) {
      try {
         NBTTagList var2 = var1.getTagList("Pos", 6);
         NBTTagList var6 = var1.getTagList("Motion", 6);
         NBTTagList var7 = var1.getTagList("Rotation", 5);
         this.v = var6.getDoubleAt(0);
         this.w = var6.getDoubleAt(1);
         this.x = var6.getDoubleAt(2);
         if (Math.abs(this.v) > 10.0) {
            this.v = 0.0;
         }

         if (Math.abs(this.w) > 10.0) {
            this.w = 0.0;
         }

         if (Math.abs(this.x) > 10.0) {
            this.x = 0.0;
         }

         this.p = this.P = this.s = var2.getDoubleAt(0);
         this.q = this.Q = this.t = var2.getDoubleAt(1);
         this.r = this.R = this.u = var2.getDoubleAt(2);
         this.A = this.y = var7.getFloatAt(0);
         this.B = this.z = var7.getFloatAt(1);
         this.setRotationYawHead(this.y);
         this.setRenderYawOffset(this.y);
         this.O = var1.getFloat("FallDistance");
         this.fire = var1.getShort("Fire");
         this.setAir(var1.getShort("Air"));
         this.C = var1.getBoolean("OnGround");
         this.am = var1.getInteger("Dimension");
         this.invulnerable = var1.getBoolean("Invulnerable");
         this.timeUntilPortal = var1.getInteger("PortalCooldown");
         if (var1.hasKey("UUIDMost", 4) && var1.hasKey("UUIDLeast", 4)) {
            this.aq = new UUID(var1.getLong("UUIDMost"), var1.getLong("UUIDLeast"));
         } else if (var1.hasKey("UUID", 8)) {
            this.aq = UUID.fromString(var1.getString("UUID"));
         }

         this.b(this.s, this.t, this.u);
         this.setRotation(this.y, this.z);
         if (var1.hasKey("CustomName", 8) && var1.getString("CustomName").length() > 0) {
            this.a(var1.getString("CustomName"));
         }

         this.setAlwaysRenderNameTag(var1.getBoolean("CustomNameVisible"));
         this.cmdResultStats.readStatsFromNBT(var1);
         this.setSilent(var1.getBoolean("Silent"));
         this.readEntityFromNBT(var1);
         if (this.shouldSetPosAfterLoading()) {
            this.b(this.s, this.t, this.u);
         }
      } catch (Throwable var5) {
         CrashReport var3 = CrashReport.makeCrashReport(var5, "Loading entity NBT");
         CrashReportCategory var4 = var3.makeCategory("Entity being loaded");
         this.addEntityCrashInfo(var4);
         throw new ReportedException(var3);
      }
   }

   public void setPortal(BlockPos var1) {
      if (this.timeUntilPortal > 0) {
         this.timeUntilPortal = this.getPortalCooldown();
      } else {
         if (!this.o.D && !var1.equals(this.an)) {
            this.an = var1;
            BlockPattern.PatternHelper var2 = Blocks.portal.func_181089_f(this.o, var1);
            double var3 = var2.getFinger().getAxis() == EnumFacing.Axis.X ? var2.getPos().getZ() : var2.getPos().getX();
            double var5 = var2.getFinger().getAxis() == EnumFacing.Axis.X ? this.u : this.s;
            var5 = Math.abs(
               MathHelper.func_181160_c(
                  var5 - (var2.getFinger().rotateY().getAxisDirection() == EnumFacing.AxisDirection.NEGATIVE ? 1 : 0), var3, var3 - var2.func_181118_d()
               )
            );
            double var7 = MathHelper.func_181160_c(this.t - 1.0, var2.getPos().getY(), var2.getPos().getY() - var2.func_181119_e());
            this.ao = new Vec3(var5, var7, 0.0);
            this.ap = var2.getFinger();
         }

         this.inPortal = true;
      }
   }

   public void e(NBTTagCompound var1) {
      try {
         var1.setTag("Pos", this.newDoubleNBTList(this.s, this.t, this.u));
         var1.setTag("Motion", this.newDoubleNBTList(this.v, this.w, this.x));
         var1.setTag("Rotation", this.newFloatNBTList(this.y, this.z));
         var1.setFloat("FallDistance", this.O);
         var1.setShort("Fire", (short)this.fire);
         var1.setShort("Air", (short)this.getAir());
         var1.setBoolean("OnGround", this.C);
         var1.setInteger("Dimension", this.am);
         var1.setBoolean("Invulnerable", this.invulnerable);
         var1.setInteger("PortalCooldown", this.timeUntilPortal);
         var1.setLong("UUIDMost", this.aK().getMostSignificantBits());
         var1.setLong("UUIDLeast", this.aK().getLeastSignificantBits());
         if (this.aM() != null && this.aM().length() > 0) {
            var1.setString("CustomName", this.aM());
            var1.setBoolean("CustomNameVisible", this.getAlwaysRenderNameTag());
         }

         this.cmdResultStats.writeStatsToNBT(var1);
         if (this.R()) {
            var1.setBoolean("Silent", this.R());
         }

         this.writeEntityToNBT(var1);
         if (this.m != null) {
            NBTTagCompound var2 = new NBTTagCompound();
            if (this.m.writeMountToNBT(var2)) {
               var1.setTag("Riding", var2);
            }
         }
      } catch (Throwable var5) {
         CrashReport var3 = CrashReport.makeCrashReport(var5, "Saving entity NBT");
         CrashReportCategory var4 = var3.makeCategory("Entity being saved");
         this.addEntityCrashInfo(var4);
         throw new ReportedException(var3);
      }
   }

   public Entity(World var1) {
      this.entityId = nextEntityID++;
      this.j = 1.0;
      this.boundingBox = ZERO_AABB;
      this.J = 0.6F;
      this.K = 1.8F;
      this.nextStepDistance = 1;
      this.V = new Random();
      this.fireResistance = 1;
      this.aa = true;
      this.aq = MathHelper.getRandomUuid(this.V);
      this.cmdResultStats = new CommandResultStats();
      this.o = var1;
      this.b(0.0, 0.0, 0.0);
      if (var1 != null) {
         this.am = var1.t.getDimensionId();
      }

      this.ac = new DataWatcher(this);
      this.ac.addObject(0, (byte)0);
      this.ac.addObject(1, (short)300);
      this.ac.addObject(3, (byte)0);
      this.ac.addObject(2, "");
      this.ac.addObject(4, (byte)0);
      this.k_();
   }

   public void kill() {
      this.setDead();
   }

   @Override
   public boolean canCommandSenderUseCommand(int var1, String var2) {
      return true;
   }

   public void a(String var1) {
      this.ac.updateObject(2, var1);
   }

   public boolean isEntityInvulnerable(DamageSource var1) {
      return this.invulnerable && var1 != DamageSource.outOfWorld && !var1.isCreativePlayer();
   }

   public void setEating(boolean var1) {
      this.setFlag(4, var1);
   }

   public void X() {
      float var1 = MathHelper.sqrt_double(this.v * this.v * 0.2F + this.w * this.w + this.x * this.x * 0.2F) * 0.2F;
      if (var1 > 1.0F) {
         var1 = 1.0F;
      }

      this.playSound(this.getSplashSound(), var1, 1.0F + (this.V.nextFloat() - this.V.nextFloat()) * 0.4F);
      float var2 = MathHelper.floor_double(this.getEntityBoundingBox().b);

      for (int var3 = 0; var3 < 1.0F + this.J * 20.0F; var3++) {
         float var4 = (this.V.nextFloat() * 2.0F - 1.0F) * this.J;
         float var5 = (this.V.nextFloat() * 2.0F - 1.0F) * this.J;
         this.o.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.s + var4, var2 + 1.0F, this.u + var5, this.v, this.w - this.V.nextFloat() * 0.2F, this.x);
      }

      for (int var6 = 0; var6 < 1.0F + this.J * 20.0F; var6++) {
         float var7 = (this.V.nextFloat() * 2.0F - 1.0F) * this.J;
         float var8 = (this.V.nextFloat() * 2.0F - 1.0F) * this.J;
         this.o.spawnParticle(EnumParticleTypes.WATER_SPLASH, this.s + var7, var2 + 1.0F, this.u + var8, this.v, this.w, this.x);
      }
   }

   public boolean isSprinting() {
      return this.getFlag(3);
   }

   public void setAngles(float var1, float var2) {
      float var3 = this.z;
      float var4 = this.y;
      this.y = (float)(this.y + var1 * 0.15);
      this.z = (float)(this.z - var2 * 0.15);
      this.z = MathHelper.clamp_float(this.z, -90.0F, 90.0F);
      this.B = this.B + (this.z - var3);
      this.A = this.A + (this.y - var4);
   }

   public void d(double var1, double var3, double var5) {
      if (this.T) {
         this.setEntityBoundingBox(this.getEntityBoundingBox().offset(var1, var3, var5));
         this.resetPositionToBB();
      } else {
         this.o.B.startSection("move");
         double var7 = this.s;
         double var9 = this.t;
         double var11 = this.u;
         if (this.isInWeb) {
            this.isInWeb = false;
            var1 *= 0.25;
            var3 *= 0.05F;
            var5 *= 0.25;
            this.v = 0.0;
            this.w = 0.0;
            this.x = 0.0;
         }

         double var13 = var1;
         double var15 = var3;
         double var17 = var5;
         boolean var19 = this.C && this.isSneaking() && this instanceof EntityPlayer;
         if (var19) {
            double var20;
            for (var20 = 0.05; var1 != 0.0 && this.o.a(this, this.getEntityBoundingBox().offset(var1, -1.0, 0.0)).isEmpty(); var13 = var1) {
               if (var1 < var20 && var1 >= -var20) {
                  var1 = 0.0;
               } else if (var1 > 0.0) {
                  var1 -= var20;
               } else {
                  var1 += var20;
               }
            }

            for (; var5 != 0.0 && this.o.a(this, this.getEntityBoundingBox().offset(0.0, -1.0, var5)).isEmpty(); var17 = var5) {
               if (var5 < var20 && var5 >= -var20) {
                  var5 = 0.0;
               } else if (var5 > 0.0) {
                  var5 -= var20;
               } else {
                  var5 += var20;
               }
            }

            for (; var1 != 0.0 && var5 != 0.0 && this.o.a(this, this.getEntityBoundingBox().offset(var1, -1.0, var5)).isEmpty(); var17 = var5) {
               if (var1 < var20 && var1 >= -var20) {
                  var1 = 0.0;
               } else if (var1 > 0.0) {
                  var1 -= var20;
               } else {
                  var1 += var20;
               }

               var13 = var1;
               if (var5 < var20 && var5 >= -var20) {
                  var5 = 0.0;
               } else if (var5 > 0.0) {
                  var5 -= var20;
               } else {
                  var5 += var20;
               }
            }
         }

         List var54 = this.o.a(this, this.getEntityBoundingBox().addCoord(var1, var3, var5));
         AxisAlignedBB var21 = this.getEntityBoundingBox();

         for (AxisAlignedBB var23 : (Iterable<AxisAlignedBB>)(Iterable<?>)(var54)) {
            var3 = var23.method_09646(this.getEntityBoundingBox(), var3);
         }

         this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0, var3, 0.0));
         boolean var55 = this.C || var15 != var3 && var15 < 0.0;

         for (AxisAlignedBB var24 : (Iterable<AxisAlignedBB>)(Iterable<?>)(var54)) {
            var1 = var24.method_09632(this.getEntityBoundingBox(), var1);
         }

         this.setEntityBoundingBox(this.getEntityBoundingBox().offset(var1, 0.0, 0.0));

         for (AxisAlignedBB var60 : (Iterable<AxisAlignedBB>)(Iterable<?>)(var54)) {
            var5 = var60.method_09638(this.getEntityBoundingBox(), var5);
         }

         this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0, 0.0, var5));
         if (this.S > 0.0F && var55 && (var13 != var1 || var17 != var5)) {
            double var58 = var1;
            double var25 = var3;
            double var27 = var5;
            AxisAlignedBB var29 = this.getEntityBoundingBox();
            this.setEntityBoundingBox(var21);
            var3 = this.S;
            List var30 = this.o.a(this, this.getEntityBoundingBox().addCoord(var13, var3, var17));
            AxisAlignedBB var31 = this.getEntityBoundingBox();
            AxisAlignedBB var32 = var31.addCoord(var13, 0.0, var17);
            double var33 = var3;

            for (AxisAlignedBB var36 : (Iterable<AxisAlignedBB>)(Iterable<?>)(var30)) {
               var33 = var36.method_09646(var32, var33);
            }

            var31 = var31.offset(0.0, var33, 0.0);
            double var73 = var13;

            for (AxisAlignedBB var38 : (Iterable<AxisAlignedBB>)(Iterable<?>)(var30)) {
               var73 = var38.method_09632(var31, var73);
            }

            var31 = var31.offset(var73, 0.0, 0.0);
            double var74 = var17;

            for (AxisAlignedBB var40 : (Iterable<AxisAlignedBB>)(Iterable<?>)(var30)) {
               var74 = var40.method_09638(var31, var74);
            }

            var31 = var31.offset(0.0, 0.0, var74);
            AxisAlignedBB var75 = this.getEntityBoundingBox();
            double var79 = var3;

            for (AxisAlignedBB var43 : (Iterable<AxisAlignedBB>)(Iterable<?>)(var30)) {
               var79 = var43.method_09646(var75, var79);
            }

            var75 = var75.offset(0.0, var79, 0.0);
            double var80 = var13;

            for (AxisAlignedBB var45 : (Iterable<AxisAlignedBB>)(Iterable<?>)(var30)) {
               var80 = var45.method_09632(var75, var80);
            }

            var75 = var75.offset(var80, 0.0, 0.0);
            double var81 = var17;

            for (AxisAlignedBB var47 : (Iterable<AxisAlignedBB>)(Iterable<?>)(var30)) {
               var81 = var47.method_09638(var75, var81);
            }

            var75 = var75.offset(0.0, 0.0, var81);
            double var82 = var73 * var73 + var74 * var74;
            double var48 = var80 * var80 + var81 * var81;
            if (var82 > var48) {
               var1 = var73;
               var5 = var74;
               var3 = -var33;
               this.setEntityBoundingBox(var31);
            } else {
               var1 = var80;
               var5 = var81;
               var3 = -var79;
               this.setEntityBoundingBox(var75);
            }

            for (AxisAlignedBB var51 : (Iterable<AxisAlignedBB>)(Iterable<?>)(var30)) {
               var3 = var51.method_09646(this.getEntityBoundingBox(), var3);
            }

            this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0, var3, 0.0));
            if (var58 * var58 + var27 * var27 >= var1 * var1 + var5 * var5) {
               var1 = var58;
               var3 = var25;
               var5 = var27;
               this.setEntityBoundingBox(var29);
            }
         }

         this.o.B.endSection();
         this.o.B.startSection("rest");
         this.resetPositionToBB();
         this.D = var13 != var1 || var17 != var5;
         this.recoveredField1643 = var15 != var3;
         this.C = this.recoveredField1643 && var15 < 0.0;
         this.recoveredField1644 = this.D || this.recoveredField1643;
         int var59 = MathHelper.floor_double(this.s);
         int var61 = MathHelper.floor_double(this.t - 0.2F);
         int var62 = MathHelper.floor_double(this.u);
         BlockPos var26 = new BlockPos(var59, var61, var62);
         Block var63 = this.o.getBlockState(var26).getBlock();
         if (var63.getMaterial() == Material.air) {
            Block var28 = this.o.getBlockState(var26.down()).getBlock();
            if (var28 instanceof BlockFence || var28 instanceof BlockWall || var28 instanceof BlockFenceGate) {
               var63 = var28;
               var26 = var26.down();
            }
         }

         this.updateFallState(var3, this.C, var63, var26);
         if (var13 != var1) {
            this.v = 0.0;
         }

         if (var17 != var5) {
            this.x = 0.0;
         }

         if (var15 != var3) {
            var63.onLanded(this.o, this);
         }

         if (this.l_() && !var19 && this.m == null) {
            double var64 = this.s - var7;
            double var67 = this.t - var9;
            double var72 = this.u - var11;
            if (var63 != Blocks.ladder) {
               var67 = 0.0;
            }

            if (var63 != null && this.C) {
               var63.onEntityCollidedWithBlock(this.o, var26, this);
            }

            this.M = (float)(this.M + MathHelper.sqrt_double(var64 * var64 + var72 * var72) * 0.6);
            this.recoveredField1641 = (float)(this.recoveredField1641 + MathHelper.sqrt_double(var64 * var64 + var67 * var67 + var72 * var72) * 0.6);
            if (this.recoveredField1641 > this.nextStepDistance && var63.getMaterial() != Material.air) {
               this.nextStepDistance = (int)this.recoveredField1641 + 1;
               if (this.V()) {
                  float var34 = MathHelper.sqrt_double(this.v * this.v * 0.2F + this.w * this.w + this.x * this.x * 0.2F) * 0.35F;
                  if (var34 > 1.0F) {
                     var34 = 1.0F;
                  }

                  this.playSound(this.getSwimSound(), var34, 1.0F + (this.V.nextFloat() - this.V.nextFloat()) * 0.4F);
               }

               this.playStepSound(var26, var63);
            }
         }

         try {
            this.doBlockCollisions();
         } catch (Throwable var52) {
            CrashReport var66 = CrashReport.makeCrashReport(var52, "Checking entity block collision");
            CrashReportCategory var68 = var66.makeCategory("Entity being checked for collision");
            this.addEntityCrashInfo(var68);
            throw new ReportedException(var66);
         }

         boolean var65 = this.U();
         if (this.o.isFlammableWithin(this.getEntityBoundingBox().contract(0.001, 0.001, 0.001))) {
            this.dealFireDamage(1);
            if (!var65) {
               this.fire++;
               if (this.fire == 0) {
                  this.setFire(8);
               }
            }
         } else if (this.fire <= 0) {
            this.fire = -this.fireResistance;
         }

         if (var65 && this.fire > 0) {
            this.playSound("random.fizz", 0.7F, 1.6F + (this.V.nextFloat() - this.V.nextFloat()) * 0.4F);
            this.fire = -this.fireResistance;
         }

         this.o.B.endSection();
      }
   }

   public void spawnRunningParticles() {
      if (this.isSprinting() && !this.V()) {
         this.Z();
      }
   }

   public HoverEvent getHoverEvent() {
      NBTTagCompound var1 = new NBTTagCompound();
      String var2 = EntityList.getEntityString(this);
      var1.setString("id", this.aK().toString());
      if (var2 != null) {
         var1.setString("type", var2);
      }

      var1.setString("name", this.z_());
      return new HoverEvent(HoverEvent.Action.SHOW_ENTITY, new ChatComponentText(var1.toString()));
   }

   public void setSize(float var1, float var2) {
      if (var1 != this.J || var2 != this.K) {
         float var3 = this.J;
         this.J = var1;
         this.K = var2;
         this.setEntityBoundingBox(
            new AxisAlignedBB(
               this.getEntityBoundingBox().a,
               this.getEntityBoundingBox().b,
               this.getEntityBoundingBox().c,
               this.getEntityBoundingBox().a + this.J,
               this.getEntityBoundingBox().b + this.K,
               this.getEntityBoundingBox().c + this.J
            )
         );
         if (this.J > var3 && !this.aa && !this.o.D) {
            this.d(var3 - this.J, 0.0, var3 - this.J);
         }
      }
   }

   public void I() {
      if (this.o != null) {
         while (true) {
            if (this.t > 0.0 && this.t < 256.0) {
               this.b(this.s, this.t, this.u);
               if (!this.o.a(this, this.getEntityBoundingBox()).isEmpty()) {
                  this.t++;
                  continue;
               }
            }

            this.v = this.w = this.x = 0.0;
            this.z = 0.0F;
            break;
         }
      }
   }

   public void a(float var1, float var2, float var3) {
      float var4 = var1 * var1 + var2 * var2;
      if (var4 >= 1.0E-4F) {
         var4 = MathHelper.sqrt_float(var4);
         if (var4 < 1.0F) {
            var4 = 1.0F;
         }

         var4 = var3 / var4;
         var1 *= var4;
         var2 *= var4;
         float var5 = MathHelper.sin(this.y * (float) Math.PI / 180.0F);
         float var6 = MathHelper.cos(this.y * (float) Math.PI / 180.0F);
         this.v += var1 * var6 - var2 * var5;
         this.x += var2 * var6 + var1 * var5;
      }
   }

   public void doBlockCollisions() {
      BlockPos var1 = new BlockPos(this.getEntityBoundingBox().a + 0.001, this.getEntityBoundingBox().b + 0.001, this.getEntityBoundingBox().c + 0.001);
      BlockPos var2 = new BlockPos(this.getEntityBoundingBox().d - 0.001, this.getEntityBoundingBox().e - 0.001, this.getEntityBoundingBox().f - 0.001);
      if (this.o.isAreaLoaded(var1, var2)) {
         for (int var3 = var1.getX(); var3 <= var2.getX(); var3++) {
            for (int var4 = var1.getY(); var4 <= var2.getY(); var4++) {
               for (int var5 = var1.getZ(); var5 <= var2.getZ(); var5++) {
                  BlockPos var6 = new BlockPos(var3, var4, var5);
                  IBlockState var7 = this.o.getBlockState(var6);

                  try {
                     var7.getBlock().onEntityCollidedWithBlock(this.o, var6, var7, this);
                  } catch (Throwable var11) {
                     CrashReport var9 = CrashReport.makeCrashReport(var11, "Colliding entity with block");
                     CrashReportCategory var10 = var9.makeCategory("Block being collided with");
                     CrashReportCategory.addBlockInfo(var10, var6, var7);
                     throw new ReportedException(var9);
                  }
               }
            }
         }
      }
   }

   public float getRotationYawHead() {
      return 0.0F;
   }

   public ItemStack[] getInventory() {
      return null;
   }

   public boolean replaceItemInInventory(int var1, ItemStack var2) {
      return false;
   }

   public void dealFireDamage(int var1) {
      if (!this.ab) {
         this.attackEntityFrom(DamageSource.inFire, var1);
      }
   }

   public void applyEnchantments(EntityLivingBase var1, Entity var2) {
      if (var2 instanceof EntityLivingBase) {
         EnchantmentHelper.applyThornEnchantments((EntityLivingBase)var2, var1);
      }

      EnchantmentHelper.applyArthropodEnchantments(var1, var2);
   }

   public String aM() {
      return this.ac.getWatchableObjectString(2);
   }

   @Override
   public void setCommandStat(CommandResultStats.Type var1, int var2) {
      this.cmdResultStats.setCommandStatScore(this, var1, var2);
   }

   public void extinguish() {
      this.fire = 0;
   }

   public int getMaxInPortalTime() {
      return 0;
   }

   public void onStruckByLightning(EntityLightningBolt var1) {
      this.attackEntityFrom(DamageSource.lightningBolt, 5.0F);
      this.fire++;
      if (this.fire == 0) {
         this.setFire(8);
      }
   }

   public void onEntityUpdate() {
      this.o.B.startSection("entityBaseTick");
      if (this.m != null && this.m.I) {
         this.m = null;
      }

      this.L = this.M;
      this.p = this.s;
      this.q = this.t;
      this.r = this.u;
      this.B = this.z;
      this.A = this.y;
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

      this.spawnRunningParticles();
      this.handleWaterMovement();
      if (this.o.D) {
         this.fire = 0;
      } else if (this.fire > 0) {
         if (this.ab) {
            this.fire -= 4;
            if (this.fire < 0) {
               this.fire = 0;
            }
         } else {
            if (this.fire % 20 == 0) {
               this.attackEntityFrom(DamageSource.onFire, 1.0F);
            }

            this.fire--;
         }
      }

      if (this.ab()) {
         this.setOnFireFromLava();
         this.O *= 0.5F;
      }

      if (this.t < -64.0) {
         this.kill();
      }

      if (!this.o.D) {
         this.setFlag(0, this.fire > 0);
      }

      this.aa = false;
      this.o.B.endSection();
   }

   public abstract void writeEntityToNBT(NBTTagCompound var1);

   public void fall(float var1, float var2) {
      if (this.l != null) {
         this.l.fall(var1, var2);
      }
   }

   @Override
   public World s_() {
      return this.o;
   }

   public boolean ab() {
      return this.o.isMaterialInBB(this.getEntityBoundingBox().expand(-0.1F, -0.4F, -0.1F), Material.lava);
   }

   public boolean doesEntityNotTriggerPressurePlate() {
      return false;
   }

   public void playSound(String var1, float var2, float var3) {
      if (!this.R()) {
         this.o.a(this, var1, var2, var3);
      }
   }

   @Override
   public int hashCode() {
      return this.entityId;
   }

   public boolean isSpectatedByPlayer(EntityPlayerMP var1) {
      return true;
   }

   public boolean method_10521() {
      return false;
   }

   public double e(double var1, double var3, double var5) {
      double var7 = this.s - var1;
      double var9 = this.t - var3;
      double var11 = this.u - var5;
      return var7 * var7 + var9 * var9 + var11 * var11;
   }

   public NBTTagCompound getNBTTagCompound() {
      return null;
   }

   public boolean isImmuneToFire() {
      return this.ab;
   }

   public boolean l_() {
      return true;
   }

   public boolean isEntityEqual(Entity var1) {
      return this == var1;
   }

   public void updateRidden() {
      if (this.m.I) {
         this.m = null;
      } else {
         this.v = 0.0;
         this.w = 0.0;
         this.x = 0.0;
         this.onUpdate();
         if (this.m != null) {
            this.m.updateRiderPosition();
            this.recoveredField1645 = this.recoveredField1645 + (this.m.y - this.m.A);
            this.recoveredField1642 = this.recoveredField1642 + (this.m.z - this.m.B);

            while (this.recoveredField1645 >= 180.0) {
               this.recoveredField1645 -= 360.0;
            }

            while (this.recoveredField1645 < -180.0) {
               this.recoveredField1645 += 360.0;
            }

            while (this.recoveredField1642 >= 180.0) {
               this.recoveredField1642 -= 360.0;
            }

            while (this.recoveredField1642 < -180.0) {
               this.recoveredField1642 += 360.0;
            }

            double var1 = this.recoveredField1645 * 0.5;
            double var3 = this.recoveredField1642 * 0.5;
            float var5 = 10.0F;
            if (var1 > var5) {
               var1 = var5;
            }

            if (var1 < -var5) {
               var1 = -var5;
            }

            if (var3 > var5) {
               var3 = var5;
            }

            if (var3 < -var5) {
               var3 = -var5;
            }

            this.recoveredField1645 -= var1;
            this.recoveredField1642 -= var3;
         }
      }
   }

   public void addEntityCrashInfo(CrashReportCategory var1) {
      var1.addCrashSectionCallable("Entity Type", new Callable<String>() {
         public String call() throws java.lang.Exception {
            return EntityList.getEntityString(Entity.this) + " (" + Entity.this.getClass().getCanonicalName() + ")";
         }
      });
      var1.addCrashSection("Entity ID", this.entityId);
      var1.addCrashSectionCallable("Entity Name", new Callable<String>() {
         public String call() throws java.lang.Exception {
            return Entity.this.z_();
         }
      });
      var1.addCrashSection("Entity's Exact location", String.format("%.2f, %.2f, %.2f", this.s, this.t, this.u));
      var1.addCrashSection(
         "Entity's Block location",
         CrashReportCategory.getCoordinateInfo(MathHelper.floor_double(this.s), MathHelper.floor_double(this.t), MathHelper.floor_double(this.u))
      );
      var1.addCrashSection("Entity's Momentum", String.format("%.2f, %.2f, %.2f", this.v, this.w, this.x));
      var1.addCrashSectionCallable("Entity's Rider", new Callable<String>() {
         public String call() throws java.lang.Exception {
            return Entity.this.l.toString();
         }
      });
      var1.addCrashSectionCallable("Entity's Vehicle", new Callable<String>() {
         public String call() throws java.lang.Exception {
            return Entity.this.m.toString();
         }
      });
   }

   public int getMaxFallHeight() {
      return 3;
   }

   public abstract void k_();

   public void mountEntity(Entity var1) {
      this.recoveredField1642 = 0.0;
      this.recoveredField1645 = 0.0;
      if (var1 == null) {
         if (this.m != null) {
            this.a_(this.m.s, this.m.getEntityBoundingBox().b + this.m.K, this.m.u, this.y, this.z);
            this.m.l = null;
         }

         this.m = null;
      } else {
         if (this.m != null) {
            this.m.l = null;
         }

         if (var1 != null) {
            for (Entity var2 = var1.m; var2 != null; var2 = var2.m) {
               if (var2 == this) {
                  return;
               }
            }
         }

         this.m = var1;
         var1.l = this;
      }
   }

   public void setAir(int var1) {
      this.ac.updateObject(1, (short)var1);
   }

   public EntityItem a(ItemStack var1, float var2) {
      if (var1.stackSize != 0 && var1.getItem() != null) {
         EntityItem var3 = new EntityItem(this.o, this.s, this.t + var2, this.u, var1);
         var3.setDefaultPickupDelay();
         this.o.spawnEntityInWorld(var3);
         return var3;
      } else {
         return null;
      }
   }

   public void onKillCommand() {
      this.setDead();
   }

   public boolean isInRangeToRender3d(double var1, double var3, double var5) {
      double var7 = this.s - var1;
      double var9 = this.t - var3;
      double var11 = this.u - var5;
      double var13 = var7 * var7 + var9 * var9 + var11 * var11;
      return this.isInRangeToRenderDist(var13);
   }

   @Override
   public String toString() {
      return String.format(
         "%s['%s'/%d, l='%s', x=%.2f, y=%.2f, z=%.2f]",
         this.getClass().getSimpleName(),
         this.z_(),
         this.entityId,
         this.o == null ? "~NULL~" : this.o.P().getWorldName(),
         this.s,
         this.t,
         this.u
      );
   }

   public void handleStatusUpdate(byte var1) {
   }

   public EntityItem dropItem(Item var1, int var2) {
      return this.dropItemWithOffset(var1, var2, 0.0F);
   }

   public void setSneaking(boolean var1) {
      this.setFlag(1, var1);
   }

   public Vec3 getVectorForRotation(float var1, float var2) {
      float var3 = MathHelper.cos(-var2 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float var4 = MathHelper.sin(-var2 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float var5 = -MathHelper.cos(-var1 * (float) (Math.PI / 180.0));
      float var6 = MathHelper.sin(-var1 * (float) (Math.PI / 180.0));
      return new Vec3(var4 * var5, var6, var3 * var5);
   }

   public void setFlag(int var1, boolean var2) {
      byte var3 = this.ac.getWatchableObjectByte(0);
      if (var2) {
         this.ac.updateObject(0, (byte)(var3 | 1 << var1));
      } else {
         this.ac.updateObject(0, (byte)(var3 & ~(1 << var1)));
      }
   }

   public void setDead() {
      this.I = true;
   }

   public double getDistanceSqToCenter(BlockPos var1) {
      return var1.distanceSqToCenter(this.s, this.t, this.u);
   }

   @Override
   public boolean C_() {
      return false;
   }

   public DataWatcher H() {
      return this.ac;
   }

   public AxisAlignedBB t_() {
      return null;
   }

   public void b(double var1, double var3, double var5) {
      this.s = var1;
      this.t = var3;
      this.u = var5;
      float var7 = this.J / 2.0F;
      float var8 = this.K;
      this.setEntityBoundingBox(new AxisAlignedBB(var1 - var7, var3, var5 - var7, var1 + var7, var3 + var8, var5 + var7));
   }

   public void Z() {
      int var1 = MathHelper.floor_double(this.s);
      int var2 = MathHelper.floor_double(this.t - 0.2F);
      int var3 = MathHelper.floor_double(this.u);
      BlockPos var4 = new BlockPos(var1, var2, var3);
      IBlockState var5 = this.o.getBlockState(var4);
      Block var6 = var5.getBlock();
      ParticlesModule var7 = CheatBreaker.getInstance().getModuleManager().recoveredField1730;
      if ((!var7.isEnabled() || var7.recoveredField1626.method_08908()) && var6.getRenderType() != -1) {
         this.o
            .spawnParticle(
               EnumParticleTypes.BLOCK_CRACK,
               this.s + (this.V.nextFloat() - 0.5) * this.J,
               this.getEntityBoundingBox().b + 0.1,
               this.u + (this.V.nextFloat() - 0.5) * this.J,
               -this.v * 4.0,
               1.5,
               -this.x * 4.0,
               Block.getStateId(var5)
            );
      }
   }

   public void onChunkLoad() {
   }

   public boolean handleWaterMovement() {
      if (this.o.handleMaterialAcceleration(this.getEntityBoundingBox().expand(0.0, -0.4F, 0.0).contract(0.001, 0.001, 0.001), Material.water, this)) {
         if (!this.Y && !this.aa) {
            this.X();
         }

         this.O = 0.0F;
         this.Y = true;
         this.fire = 0;
      } else {
         this.Y = false;
      }

      return this.Y;
   }

   public void addToPlayerScore(Entity var1, int var2) {
   }

   public boolean isEntityInsideOpaqueBlock() {
      if (this.T) {
         return false;
      } else {
         BlockPos.MutableBlockPos var1 = new BlockPos.MutableBlockPos(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);

         for (int var2 = 0; var2 < 8; var2++) {
            int var3 = MathHelper.floor_double(this.t + ((var2 >> 0) % 2 - 0.5F) * 0.1F + this.getEyeHeight());
            int var4 = MathHelper.floor_double(this.s + ((var2 >> 1) % 2 - 0.5F) * this.J * 0.8F);
            int var5 = MathHelper.floor_double(this.u + ((var2 >> 2) % 2 - 0.5F) * this.J * 0.8F);
            if (var1.getX() != var4 || var1.getY() != var3 || var1.getZ() != var5) {
               var1.set(var4, var3, var5);
               if (this.o.getBlockState(var1).getBlock().isVisuallyOpaque()) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public double getMountedYOffset() {
      return this.K * 0.75;
   }

   public boolean aO() {
      return this.getAlwaysRenderNameTag();
   }

   public boolean getFlag(int var1) {
      return (this.ac.getWatchableObjectByte(0) & 1 << var1) != 0;
   }

   public AxisAlignedBB getCollisionBox(Entity var1) {
      return null;
   }

   public void moveToBlockPosAndAngles(BlockPos var1, float var2, float var3) {
      this.a_(var1.getX() + 0.5, var1.getY(), var1.getZ() + 0.5, var2, var3);
   }

   public boolean a(Material var1) {
      double var2 = this.t + this.getEyeHeight();
      BlockPos var4 = new BlockPos(this.s, var2, this.u);
      IBlockState var5 = this.o.getBlockState(var4);
      Block var6 = var5.getBlock();
      if (var6.getMaterial() == var1) {
         float var7 = BlockLiquid.getLiquidHeightPercent(var5.getBlock().getMetaFromState(var5)) - 0.11111111F;
         float var8 = var4.getY() + 1 - var7;
         boolean var9 = var2 < var8;
         return !var9 && this instanceof EntityPlayer ? false : var9;
      } else {
         return false;
      }
   }

   public void setPositionAndUpdate(double var1, double var3, double var5) {
      this.a_(var1, var3, var5, this.y, this.z);
   }

   public boolean u_() {
      return this.ac.getWatchableObjectString(2).length() > 0;
   }

   public void copyLocationAndAnglesFrom(Entity var1) {
      this.a_(var1.s, var1.t, var1.u, var1.y, var1.z);
   }

   public boolean getAlwaysRenderNameTag() {
      return this.ac.getWatchableObjectByte(3) == 1;
   }

   public boolean isSneaking() {
      return this.getFlag(1);
   }

   public boolean canBeCollidedWith() {
      return false;
   }

   public double h(Entity var1) {
      double var2 = this.s - var1.s;
      double var4 = this.t - var1.t;
      double var6 = this.u - var1.u;
      return var2 * var2 + var4 * var4 + var6 * var6;
   }

   public void addVelocity(double var1, double var3, double var5) {
      this.v += var1;
      this.w += var3;
      this.x += var5;
      this.ai = true;
   }

   @Override
   public void addChatMessage(IChatComponent var1) {
   }

   public boolean shouldSetPosAfterLoading() {
      return true;
   }

   public boolean f(EntityPlayer var1) {
      return var1.isSpectator() ? false : this.isInvisible();
   }

   public void updateRiderPosition() {
      if (this.l != null) {
         this.l.b(this.s, this.t + this.getMountedYOffset() + this.l.getYOffset(), this.u);
      }
   }

   public boolean isInRangeToRenderDist(double var1) {
      double var3 = this.getEntityBoundingBox().getAverageEdgeLength();
      if (Double.isNaN(var3)) {
         var3 = 1.0;
      }

      var3 = var3 * 64.0 * this.j;
      return var1 < var3 * var3;
   }

   public boolean verifyExplosion(Explosion var1, World var2, BlockPos var3, IBlockState var4, float var5) {
      return true;
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (this.isEntityInvulnerable(var1)) {
         return false;
      } else {
         this.setBeenAttacked();
         return false;
      }
   }

   public String getSwimSound() {
      return "game.neutral.swim";
   }

   public void onUpdate() {
      this.onEntityUpdate();
   }

   public void updateFallState(double var1, boolean var3, Block var4, BlockPos var5) {
      if (var3) {
         if (this.O > 0.0F) {
            if (var4 != null) {
               var4.onFallenUpon(this.o, var5, this, this.O);
            } else {
               this.fall(this.O, 1.0F);
            }

            this.O = 0.0F;
         }
      } else if (var1 < 0.0) {
         this.O = (float)(this.O - var1);
      }
   }

   public float getCollisionBorderSize() {
      return 0.1F;
   }

   public int getPortalCooldown() {
      return 300;
   }

   public void travelToDimension(int var1) {
      if (!this.o.D && !this.I) {
         this.o.B.startSection("changeDimension");
         MinecraftServer var2 = MinecraftServer.getServer();
         int var3 = this.am;
         WorldServer var4 = var2.worldServerForDimension(var3);
         WorldServer var5 = var2.worldServerForDimension(var1);
         this.am = var1;
         if (var3 == 1 && var1 == 1) {
            var5 = var2.worldServerForDimension(0);
            this.am = 0;
         }

         this.o.removeEntity(this);
         this.I = false;
         this.o.B.startSection("reposition");
         var2.getConfigurationManager().transferEntityToWorld(this, var3, var4, var5);
         this.o.B.endStartSection("reloading");
         Entity var6 = EntityList.createEntityByName(EntityList.getEntityString(this), var5);
         if (var6 != null) {
            var6.copyDataFromOld(this);
            if (var3 == 1 && var1 == 1) {
               BlockPos var7 = this.o.getTopSolidOrLiquidBlock(var5.M());
               var6.moveToBlockPosAndAngles(var7, var6.y, var6.z);
            }

            var5.spawnEntityInWorld(var6);
         }

         this.I = true;
         this.o.B.endSection();
         var4.resetUpdateEntityTick();
         var5.resetUpdateEntityTick();
         this.o.B.endSection();
      }
   }

   public void setEntityId(int var1) {
      this.entityId = var1;
   }

   public boolean isEating() {
      return this.getFlag(4);
   }

   public boolean U() {
      return this.Y || this.o.isRainingAt(new BlockPos(this.s, this.t, this.u)) || this.o.isRainingAt(new BlockPos(this.s, this.t + this.K, this.u));
   }

   public int b_(float var1) {
      BlockPos var2 = new BlockPos(this.s, this.t + this.getEyeHeight(), this.u);
      return this.o.e(var2) ? this.o.getCombinedLight(var2, 0) : 0;
   }

   public boolean au() {
      return this.m != null;
   }

   public void setRenderYawOffset(float var1) {
   }

   public void setOnFireFromLava() {
      if (!this.ab) {
         this.attackEntityFrom(DamageSource.lava, 4.0F);
         this.setFire(15);
      }
   }

   public boolean m_() {
      return false;
   }

   public void onKillEntity(EntityLivingBase var1) {
   }

   @Override
   public IChatComponent getDisplayName() {
      ChatComponentText var1 = new ChatComponentText(this.z_());
      var1.getChatStyle().setChatHoverEvent(this.getHoverEvent());
      var1.getChatStyle().setInsertion(this.aK().toString());
      return var1;
   }

   public EnumFacing getTeleportDirection() {
      return this.ap;
   }
}
