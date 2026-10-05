package net.minecraft.tileentity;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.BlockPos$MutableBlockPos;
import net.minecraft.util.ITickable;

public class TileEntityBeacon extends TileEntityLockable implements IInventory, ITickable {
   public ItemStack payment;
   public int levels;
   public float field_146014_j;
   public static Potion[][] effectsList = new Potion[][]{
      {Potion.moveSpeed, Potion.digSpeed}, {Potion.resistance, Potion.jump}, {Potion.damageBoost}, {Potion.regeneration}
   };
   public int primaryEffect;
   public String customName;
   public List<TileEntityBeacon$BeamSegment> beamSegments = Lists.newArrayList();
   public long beamRenderCounter;
   public int secondaryEffect;
   public boolean isComplete;
   public EntityPainting field_0000;

   @Override
   public void setInventorySlotContents(int var1, ItemStack var2) {
      if (var1 == 0) {
         this.payment = var2;
      }
   }

   @Override
   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      this.primaryEffect = this.func_183001_h(var1.getInteger("Primary"));
      this.secondaryEffect = this.func_183001_h(var1.getInteger("Secondary"));
      this.levels = var1.getInteger("Levels");
   }

   @Override
   public void update() {
      if (this.b.K() % (210780881L & 555880530L) == (67040L & 5747476573859103236L)) {
         this.updateBeacon();
      }
   }

   @Override
   public boolean u_() {
      return this.customName != null && this.customName.length() > 0;
   }

   @Override
   public int getSizeInventory() {
      return 1;
   }

   public float shouldBeamRender() {
      if (!this.isComplete) {
         return 0.0F;
      } else {
         int var1 = (int)(this.b.K() - this.beamRenderCounter);
         this.beamRenderCounter = this.b.K();
         if (var1 > 1) {
            this.field_146014_j -= var1 / 40.0F;
            if (this.field_146014_j < 0.0F) {
               this.field_146014_j = 0.0F;
            }
         }

         this.field_146014_j += 0.025F;
         if (this.field_146014_j > 1.0F) {
            this.field_146014_j = 1.0F;
         }

         return this.field_146014_j;
      }
   }

   @Override
   public Packet getDescriptionPacket() {
      NBTTagCompound var1 = new NBTTagCompound();
      this.writeToNBT(var1);
      return new S35PacketUpdateTileEntity(this.c, 3, var1);
   }

   public void updateBeacon() {
      this.updateSegmentColors();
      this.addEffectsToPlayers();
   }

   @Override
   public int getFieldCount() {
      return 3;
   }

   @Override
   public ItemStack removeStackFromSlot(int var1) {
      if (var1 == 0 && this.payment != null) {
         ItemStack var2 = this.payment;
         this.payment = null;
         return var2;
      } else {
         return null;
      }
   }

   @Override
   public String getGuiID() {
      return "minecraft:beacon";
   }

   @Override
   public ItemStack decrStackSize(int var1, int var2) {
      if (var1 != 0 || this.payment == null) {
         return null;
      } else if (var2 >= this.payment.stackSize) {
         ItemStack var3 = this.payment;
         this.payment = null;
         return var3;
      } else {
         this.payment.stackSize -= var2;
         return new ItemStack(this.payment.getItem(), var2, this.payment.getMetadata());
      }
   }

   public void addEffectsToPlayers() {
      if (this.isComplete && this.levels > 0 && !this.b.D && this.primaryEffect > 0) {
         double var1 = this.levels * 10 + 10;
         byte var3 = 0;
         if (this.levels >= 4 && this.primaryEffect == this.secondaryEffect) {
            var3 = 1;
         }

         int var4 = this.c.getX();
         int var5 = this.c.getY();
         int var6 = this.c.getZ();
         AxisAlignedBB var7 = new AxisAlignedBB(var4, var5, var6, var4 + 1, var5 + 1, var6 + 1).expand(var1, var1, var1).addCoord(0.0, this.b.getHeight(), 0.0);
         List var8 = this.b.getEntitiesWithinAABB(EntityPlayer.class, var7);

         for (EntityPlayer var10 : var8) {
            var10.c(new PotionEffect(this.primaryEffect, 180, var3, true, true));
         }

         if (this.levels >= 4 && this.primaryEffect != this.secondaryEffect && this.secondaryEffect > 0) {
            for (EntityPlayer var12 : var8) {
               var12.c(new PotionEffect(this.secondaryEffect, 180, 0, true, true));
            }
         }
      }
   }

   @Override
   public Container createContainer(InventoryPlayer var1, EntityPlayer var2) {
      return new ContainerBeacon(var1, this);
   }

   @Override
   public ItemStack getStackInSlot(int var1) {
      return var1 == 0 ? this.payment : null;
   }

   @Override
   public void setField(int var1, int var2) {
      switch (var1) {
         case 0:
            this.levels = var2;
            break;
         case 1:
            this.primaryEffect = this.func_183001_h(var2);
            break;
         case 2:
            this.secondaryEffect = this.func_183001_h(var2);
      }
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer var1) {
      return this.b.getTileEntity(this.c) != this ? false : var1.e(this.c.getX() + 0.5, this.c.getY() + 0.5, this.c.getZ() + 0.5) <= 64.0;
   }

   public void updateSegmentColors() {
      int var1 = this.levels;
      int var2 = this.c.getX();
      int var3 = this.c.getY();
      int var4 = this.c.getZ();
      this.levels = 0;
      this.beamSegments.clear();
      this.isComplete = true;
      TileEntityBeacon$BeamSegment var5 = new TileEntityBeacon$BeamSegment(EntitySheep.getDyeRgb(EnumDyeColor.WHITE));
      this.beamSegments.add(var5);
      boolean var6 = true;
      BlockPos$MutableBlockPos var7 = new BlockPos$MutableBlockPos();

      for (int var8 = var3 + 1; var8 < 256; var8++) {
         IBlockState var9 = this.b.getBlockState(var7.set(var2, var8, var4));
         float[] var10;
         if (var9.getBlock() == Blocks.stained_glass) {
            var10 = EntitySheep.getDyeRgb(var9.getValue(BlockStainedGlass.COLOR));
         } else {
            if (var9.getBlock() != Blocks.stained_glass_pane) {
               if (var9.getBlock().getLightOpacity() >= 15 && var9.getBlock() != Blocks.bedrock) {
                  this.isComplete = false;
                  this.beamSegments.clear();
                  break;
               }

               var5.incrementHeight();
               continue;
            }

            var10 = EntitySheep.getDyeRgb(var9.getValue(BlockStainedGlassPane.COLOR));
         }

         if (!var6) {
            var10 = new float[]{
               (var5.method_28192()[0] + var10[0]) / 2.0F, (var5.method_28192()[1] + var10[1]) / 2.0F, (var5.method_28192()[2] + var10[2]) / 2.0F
            };
         }

         if (Arrays.equals(var10, var5.method_28192())) {
            var5.incrementHeight();
         } else {
            var5 = new TileEntityBeacon$BeamSegment(var10);
            this.beamSegments.add(var5);
         }

         var6 = false;
      }

      if (this.isComplete) {
         for (int var14 = 1; var14 <= 4; this.levels = var14++) {
            int var16 = var3 - var14;
            if (var16 < 0) {
               break;
            }

            boolean var18 = true;

            for (int var11 = var2 - var14; var11 <= var2 + var14 && var18; var11++) {
               for (int var12 = var4 - var14; var12 <= var4 + var14; var12++) {
                  Block var13 = this.b.getBlockState(new BlockPos(var11, var16, var12)).getBlock();
                  if (var13 != Blocks.emerald_block && var13 != Blocks.gold_block && var13 != Blocks.diamond_block && var13 != Blocks.iron_block) {
                     var18 = false;
                     break;
                  }
               }
            }

            if (!var18) {
               break;
            }
         }

         if (this.levels == 0) {
            this.isComplete = false;
         }
      }

      if (!this.b.D && this.levels == 4 && var1 < this.levels) {
         for (EntityPlayer var17 : this.b
            .getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(var2, var3, var4, var2, var3 - 4, var4).expand(10.0, 5.0, 10.0))) {
            var17.triggerAchievement(AchievementList.field_0008);
         }
      }
   }

   @Override
   public boolean isItemValidForSlot(int var1, ItemStack var2) {
      return var2.getItem() == Items.emerald || var2.getItem() == Items.diamond || var2.getItem() == Items.gold_ingot || var2.getItem() == Items.iron_ingot;
   }

   public int func_183001_h(int var1) {
      if (var1 >= 0 && var1 < Potion.potionTypes.length && Potion.potionTypes[var1] != null) {
         Potion var2 = Potion.potionTypes[var1];
         return var2 != Potion.moveSpeed
               && var2 != Potion.digSpeed
               && var2 != Potion.resistance
               && var2 != Potion.jump
               && var2 != Potion.damageBoost
               && var2 != Potion.regeneration
            ? 0
            : var1;
      } else {
         return 0;
      }
   }

   public List<TileEntityBeacon$BeamSegment> getBeamSegments() {
      return this.beamSegments;
   }

   @Override
   public void closeInventory(EntityPlayer var1) {
   }

   @Override
   public boolean receiveClientEvent(int var1, int var2) {
      if (var1 == 1) {
         this.updateBeacon();
         return true;
      } else {
         return super.receiveClientEvent(var1, var2);
      }
   }

   @Override
   public int getField(int var1) {
      switch (var1) {
         case 0:
            return this.levels;
         case 1:
            return this.primaryEffect;
         case 2:
            return this.secondaryEffect;
         default:
            return 0;
      }
   }

   @Override
   public void clear() {
      this.payment = null;
   }

   @Override
   public void openInventory(EntityPlayer var1) {
   }

   @Override
   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      var1.setInteger("Primary", this.primaryEffect);
      var1.setInteger("Secondary", this.secondaryEffect);
      var1.setInteger("Levels", this.levels);
   }

   public TileEntityBeacon() {
      this.levels = -1;
   }

   public void setName(String var1) {
      this.customName = var1;
   }

   @Override
   public String z_() {
      return this.u_() ? this.customName : "container.beacon";
   }

   @Override
   public double getMaxRenderDistanceSquared() {
      return 65536.0;
   }

   @Override
   public int getInventoryStackLimit() {
      return 1;
   }
}
