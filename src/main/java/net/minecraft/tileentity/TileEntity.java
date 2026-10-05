package net.minecraft.tileentity;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class TileEntity {
   public static Logger logger = LogManager.getLogger();
   public static Map<String, Class<? extends TileEntity>> nameToClassMap = Maps.newHashMap();
   public World b;
   public int blockMetadata;
   public static Map<Class<? extends TileEntity>, String> classToNameMap = Maps.newHashMap();
   public boolean tileEntityInvalid;
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
      var1.addCrashSectionCallable("Name", new Callable<String>() {
         public String call() throws java.lang.Exception {
            return TileEntity.classToNameMap.get(TileEntity.this.getClass()) + " // " + TileEntity.this.getClass().getCanonicalName();
         }
      });
      if (this.b != null) {
         CrashReportCategory.addBlockInfo(var1, this.c, this.w(), this.u());
         var1.addCrashSectionCallable(
            "Actual block type",
            new Callable<String>() {
               public String call() throws java.lang.Exception {
                  int var1 = Block.getIdFromBlock(TileEntity.this.b.getBlockState(TileEntity.this.c).getBlock());

                  try {
                     return String.format(
                        "ID #%d (%s // %s)", var1, Block.getBlockById(var1).getUnlocalizedName(), Block.getBlockById(var1).getClass().getCanonicalName()
                     );
                  } catch (Throwable var3) {
                     return "ID #" + var1;
                  }
               }
            }
         );
         var1.addCrashSectionCallable("Actual block data value", new Callable<String>() {
            public String call() throws java.lang.Exception {
               IBlockState var1 = TileEntity.this.b.getBlockState(TileEntity.this.c);
               int var2 = var1.getBlock().getMetaFromState(var1);
               if (var2 < 0) {
                  return "Unknown? (Got " + var2 + ")";
               } else {
                  String var3 = String.format("%4s", Integer.toBinaryString(var2)).replace(" ", "0");
                  return String.format("%1$d / 0x%1$X / 0b%2$s", var2, var3);
               }
            }
         });
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

   public World getWorld() {
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
      addMapping(BlockJukebox.TileEntityJukebox.class, "RecordPlayer");
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
