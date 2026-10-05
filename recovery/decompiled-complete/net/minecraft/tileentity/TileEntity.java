package net.minecraft.tileentity;

import com.google.common.collect.Maps;
import io.netty.util.HashedWheelTimer$1;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockJukebox$TileEntityJukebox;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.command.PlayerSelector$12;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.apache.log4j.pattern.LineSeparatorPatternConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class TileEntity {
   public static Logger logger = LogManager.getLogger();
   public HashedWheelTimer$1 field_0003;
   public GuiInventory field_0011;
   public static Map<Class<? extends TileEntity>, String> classToNameMap = Maps.newHashMap();
   public World b;
   public PlayerSelector$12 field_0012;
   public int blockMetadata;
   public LineSeparatorPatternConverter field_0006;
   public static Map<String, Class<? extends TileEntity>> nameToClassMap = Maps.newHashMap();
   public boolean tileEntityInvalid;
   public GuiIngame field_0009;
   public BlockPos c = BlockPos.ORIGIN;
   public Block blockType;

   public Block w() {
      if (this.blockType == null) {
         this.blockType = this.b.getBlockState(this.c).getBlock();
      }

      return this.blockType;
   }

   public void readFromNBT(NBTTagCompound var1) {
      this.c = new BlockPos(var1.getInteger("x"), var1.getInteger("y"), var1.getInteger("z"));
   }

   public void setPos(BlockPos var1) {
      this.c = var1;
   }

   public void writeToNBT(NBTTagCompound var1) {
      String var2 = classToNameMap.get(this.getClass());
      if (var2 == null) {
         throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
      } else {
         var1.setString("id", var2);
         var1.setInteger("x", this.c.getX());
         var1.setInteger("y", this.c.getY());
         var1.setInteger("z", this.c.getZ());
      }
   }

   public int u() {
      if (this.blockMetadata == -1) {
         IBlockState var1 = this.b.getBlockState(this.c);
         this.blockMetadata = var1.getBlock().getMetaFromState(var1);
      }

      return this.blockMetadata;
   }

   public double a(double var1, double var3, double var5) {
      double var7 = this.c.getX() + 0.5 - var1;
      double var9 = this.c.getY() + 0.5 - var3;
      double var11 = this.c.getZ() + 0.5 - var5;
      return var7 * var7 + var9 * var9 + var11 * var11;
   }

   public void addInfoToCrashReport(CrashReportCategory var1) {
      var1.addCrashSectionCallable("Name", new TileEntity$1(this));
      if (this.b != null) {
         CrashReportCategory.addBlockInfo(var1, this.c, this.w(), this.u());
         var1.addCrashSectionCallable("Actual block type", new TileEntity$2(this));
         var1.addCrashSectionCallable("Actual block data value", new TileEntity$3(this));
      }
   }

   public static void addMapping(Class<? extends TileEntity> var0, String var1) {
      if (nameToClassMap.containsKey(var1)) {
         throw new IllegalArgumentException("Duplicate id: " + var1);
      } else {
         nameToClassMap.put(var1, var0);
         classToNameMap.put(var0, var1);
      }
   }

   public Packet getDescriptionPacket() {
      return null;
   }

   public void invalidate() {
      this.tileEntityInvalid = true;
   }

   public void setWorldObj(World var1) {
      this.b = var1;
   }

   public double getMaxRenderDistanceSquared() {
      return 4096.0;
   }

   public World z() {
      return this.b;
   }

   public TileEntity() {
      this.blockMetadata = -1;
   }

   public void updateContainingBlockInfo() {
      this.blockType = null;
      this.blockMetadata = -1;
   }

   public void validate() {
      this.tileEntityInvalid = false;
   }

   public boolean t() {
      return this.b != null;
   }

   static {
      addMapping(TileEntityFurnace.class, "Furnace");
      addMapping(TileEntityChest.class, "Chest");
      addMapping(TileEntityEnderChest.class, "EnderChest");
      addMapping(BlockJukebox$TileEntityJukebox.class, "RecordPlayer");
      addMapping(TileEntityDispenser.class, "Trap");
      addMapping(TileEntityDropper.class, "Dropper");
      addMapping(TileEntitySign.class, "Sign");
      addMapping(TileEntityMobSpawner.class, "MobSpawner");
      addMapping(TileEntityNote.class, "Music");
      addMapping(TileEntityPiston.class, "Piston");
      addMapping(TileEntityBrewingStand.class, "Cauldron");
      addMapping(TileEntityEnchantmentTable.class, "EnchantTable");
      addMapping(TileEntityEndPortal.class, "Airportal");
      addMapping(TileEntityCommandBlock.class, "Control");
      addMapping(TileEntityBeacon.class, "Beacon");
      addMapping(TileEntitySkull.class, "Skull");
      addMapping(TileEntityDaylightDetector.class, "DLDetector");
      addMapping(TileEntityHopper.class, "Hopper");
      addMapping(TileEntityComparator.class, "Comparator");
      addMapping(TileEntityFlowerPot.class, "FlowerPot");
      addMapping(TileEntityBanner.class, "Banner");
   }

   public void markDirty() {
      if (this.b != null) {
         IBlockState var1 = this.b.getBlockState(this.c);
         this.blockMetadata = var1.getBlock().getMetaFromState(var1);
         this.b.markChunkDirty(this.c, this);
         if (this.w() != Blocks.air) {
            this.b.updateComparatorOutputLevel(this.c, this.w());
         }
      }
   }

   public static TileEntity createAndLoadEntity(NBTTagCompound var0) {
      TileEntity var1 = null;

      try {
         Class var2 = nameToClassMap.get(var0.getString("id"));
         if (var2 != null) {
            var1 = (TileEntity)var2.newInstance();
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      if (var1 != null) {
         var1.readFromNBT(var0);
      } else {
         logger.warn("Skipping BlockEntity with id " + var0.getString("id"));
      }

      return var1;
   }

   public BlockPos v() {
      return this.c;
   }

   public boolean func_183000_F() {
      return false;
   }

   public boolean receiveClientEvent(int var1, int var2) {
      return false;
   }

   public boolean isInvalid() {
      return this.tileEntityInvalid;
   }
}
