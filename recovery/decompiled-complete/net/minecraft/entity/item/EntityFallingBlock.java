package net.minecraft.entity.item;

import com.cheatbreaker.client.util.voicechat.CaptureDeviceManager;
import com.google.common.collect.Lists;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$KeyIterator;
import java.util.ArrayList;
import junit.awtui.Logo;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreenCustomizePresets$ListPreset;
import net.minecraft.client.model.ModelWither;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemLilyPad;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S38PacketPlayerListItem$1;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityFallingBlock extends Entity {
   public ConcurrentHashMapV8$KeyIterator field_0006;
   public float fallHurtAmount;
   public ModelWither field_0005;
   public boolean hurtEntities;
   public NBTTagCompound tileEntityData;
   public Logo field_0002;
   public S38PacketPlayerListItem$1 field_0013;
   public GuiScreenCustomizePresets$ListPreset field_0009;
   public boolean shouldDropItem = true;
   public ItemLilyPad field_0014;
   public boolean canSetAsBlock;
   public CaptureDeviceManager field_0007;
   public int fallHurtMax = 40;
   public IBlockState fallTile;
   public int fallTime;

   @Override
   public void addEntityCrashInfo(CrashReportCategory var1) {
      super.addEntityCrashInfo(var1);
      if (this.fallTile != null) {
         Block var2 = this.fallTile.getBlock();
         var1.addCrashSection("Immitating block ID", Block.getIdFromBlock(var2));
         var1.addCrashSection("Immitating block data", var2.getMetaFromState(this.fallTile));
      }
   }

   @Override
   public void fall(float var1, float var2) {
      Block var3 = this.fallTile.getBlock();
      if (this.hurtEntities) {
         int var4 = MathHelper.ceiling_float_int(var1 - 1.0F);
         if (var4 > 0) {
            ArrayList var5 = Lists.newArrayList(this.o.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox()));
            boolean var6 = var3 == Blocks.anvil;
            DamageSource var7 = var6 ? DamageSource.anvil : DamageSource.fallingBlock;

            for (Entity var9 : var5) {
               var9.attackEntityFrom(var7, Math.min(MathHelper.floor_float(var4 * this.fallHurtAmount), this.fallHurtMax));
            }

            if (var6 && this.V.nextFloat() < 0.05F + var4 * 0.05) {
               int var10 = this.fallTile.getValue(BlockAnvil.DAMAGE);
               if (++var10 > 2) {
                  this.canSetAsBlock = true;
               } else {
                  this.fallTile = this.fallTile.withProperty(BlockAnvil.DAMAGE, var10);
               }
            }
         }
      }
   }

   @Override
   public boolean method_10440() {
      return false;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      Block var2 = this.fallTile != null ? this.fallTile.getBlock() : Blocks.air;
      ResourceLocation var3 = Block.blockRegistry.getNameForObject(var2);
      var1.setString("Block", var3 == null ? "" : var3.toString());
      var1.setByte("Data", (byte)var2.getMetaFromState(this.fallTile));
      var1.setByte("Time", (byte)this.fallTime);
      var1.setBoolean("DropItem", this.shouldDropItem);
      var1.setBoolean("HurtEntities", this.hurtEntities);
      var1.setFloat("FallHurtAmount", this.fallHurtAmount);
      var1.setInteger("FallHurtMax", this.fallHurtMax);
      if (this.tileEntityData != null) {
         var1.setTag("TileEntityData", this.tileEntityData);
      }
   }

   @Override
   public void onUpdate() {
      Block var1 = this.fallTile.getBlock();
      if (var1.getMaterial() == Material.air) {
         this.setDead();
      } else {
         this.p = this.s;
         this.q = this.t;
         this.r = this.u;
         if (this.fallTime++ == 0) {
            BlockPos var2 = new BlockPos(this);
            if (this.o.getBlockState(var2).getBlock() == var1) {
               this.o.setBlockToAir(var2);
            } else if (!this.o.D) {
               this.setDead();
               return;
            }
         }

         this.w -= 0.04F;
         this.d(this.v, this.w, this.x);
         this.v *= 0.98F;
         this.w *= 0.98F;
         this.x *= 0.98F;
         if (!this.o.D) {
            BlockPos var8 = new BlockPos(this);
            if (this.C) {
               this.v *= 0.7F;
               this.x *= 0.7F;
               this.w *= -0.5;
               if (this.o.getBlockState(var8).getBlock() != Blocks.piston_extension) {
                  this.setDead();
                  if (!this.canSetAsBlock) {
                     if (this.o.canBlockBePlaced(var1, var8, true, EnumFacing.UP, (Entity)null, (ItemStack)null)
                        && !BlockFalling.canFallInto(this.o, var8.down())
                        && this.o.a(var8, this.fallTile, 3)) {
                        if (var1 instanceof BlockFalling) {
                           ((BlockFalling)var1).onEndFalling(this.o, var8);
                        }

                        if (this.tileEntityData != null && var1 instanceof ITileEntityProvider) {
                           TileEntity var3 = this.o.getTileEntity(var8);
                           if (var3 != null) {
                              NBTTagCompound var4 = new NBTTagCompound();
                              var3.writeToNBT(var4);

                              for (String var6 : this.tileEntityData.getKeySet()) {
                                 NBTBase var7 = this.tileEntityData.getTag(var6);
                                 if (!var6.equals("x") && !var6.equals("y") && !var6.equals("z")) {
                                    var4.setTag(var6, var7.copy());
                                 }
                              }

                              var3.readFromNBT(var4);
                              var3.markDirty();
                           }
                        }
                     } else if (this.shouldDropItem && this.o.Q().getBoolean("doEntityDrops")) {
                        this.a(new ItemStack(var1, 1, var1.damageDropped(this.fallTile)), 0.0F);
                     }
                  }
               }
            } else if (this.fallTime > 100 && !this.o.D && (var8.getY() < 1 || var8.getY() > 256) || this.fallTime > 600) {
               if (this.shouldDropItem && this.o.Q().getBoolean("doEntityDrops")) {
                  this.a(new ItemStack(var1, 1, var1.damageDropped(this.fallTile)), 0.0F);
               }

               this.setDead();
            }
         }
      }
   }

   public EntityFallingBlock(World var1, double var2, double var4, double var6, IBlockState var8) {
      super(var1);
      this.fallHurtAmount = 2.0F;
      this.fallTile = var8;
      this.k = true;
      this.setSize(0.98F, 0.98F);
      this.b(var2, var4, var6);
      this.v = 0.0;
      this.w = 0.0;
      this.x = 0.0;
      this.p = var2;
      this.q = var4;
      this.r = var6;
   }

   public EntityFallingBlock(World var1) {
      super(var1);
      this.fallHurtAmount = 2.0F;
   }

   @Override
   public boolean canBeCollidedWith() {
      return !this.I;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      int var2 = var1.getByte("Data") & 255;
      if (var1.hasKey("Block", 8)) {
         this.fallTile = Block.getBlockFromName(var1.getString("Block")).getStateFromMeta(var2);
      } else if (var1.hasKey("TileID", 99)) {
         this.fallTile = Block.getBlockById(var1.getInteger("TileID")).getStateFromMeta(var2);
      } else {
         this.fallTile = Block.getBlockById(var1.getByte("Tile") & 255).getStateFromMeta(var2);
      }

      this.fallTime = var1.getByte("Time") & 255;
      Block var3 = this.fallTile.getBlock();
      if (var1.hasKey("HurtEntities", 99)) {
         this.hurtEntities = var1.getBoolean("HurtEntities");
         this.fallHurtAmount = var1.getFloat("FallHurtAmount");
         this.fallHurtMax = var1.getInteger("FallHurtMax");
      } else if (var3 == Blocks.anvil) {
         this.hurtEntities = true;
      }

      if (var1.hasKey("DropItem", 99)) {
         this.shouldDropItem = var1.getBoolean("DropItem");
      }

      if (var1.hasKey("TileEntityData", 10)) {
         this.tileEntityData = var1.getCompoundTag("TileEntityData");
      }

      if (var3 == null || var3.getMaterial() == Material.air) {
         this.fallTile = Blocks.sand.getDefaultState();
      }
   }

   @Override
   public boolean l_() {
      return false;
   }

   public void setHurtEntities(boolean var1) {
      this.hurtEntities = var1;
   }

   public IBlockState getBlock() {
      return this.fallTile;
   }

   @Override
   public void k_() {
   }

   public World getWorldObj() {
      return this.o;
   }
}
