package net.minecraft.command;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.NextTickListEntry;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class CommandClone extends CommandBase {
   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      return var2.length > 0 && var2.length <= 3
         ? method_02118(var2, 0, var3)
         : (
            var2.length > 3 && var2.length <= 6
               ? method_02118(var2, 3, var3)
               : (
                  var2.length > 6 && var2.length <= 9
                     ? method_02118(var2, 6, var3)
                     : (
                        var2.length == 10
                           ? getListOfStringsMatchingLastWord(var2, "replace", "masked", "filtered")
                           : (
                              var2.length == 11
                                 ? getListOfStringsMatchingLastWord(var2, "normal", "force", "move")
                                 : (
                                    var2.length == 12 && "filtered".equals(var2[9])
                                       ? getListOfStringsMatchingLastWord(var2, Block.blockRegistry.getKeys())
                                       : null
                                 )
                           )
                     )
               )
         );
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) throws net.minecraft.command.CommandException {
      if (var2.length < 9) {
         throw new WrongUsageException("commands.clone.usage");
      } else {
         var1.setCommandStat(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
         BlockPos var3 = parseBlockPos(var1, var2, 0, false);
         BlockPos var4 = parseBlockPos(var1, var2, 3, false);
         BlockPos var5 = parseBlockPos(var1, var2, 6, false);
         StructureBoundingBox var6 = new StructureBoundingBox(var3, var4);
         StructureBoundingBox var7 = new StructureBoundingBox(var5, var5.add(var6.func_175896_b()));
         int var8 = var6.getXSize() * var6.getYSize() * var6.getZSize();
         if (var8 > 32768) {
            throw new CommandException("commands.clone.tooManyBlocks", var8, 32768);
         } else {
            boolean var9 = false;
            Block var10 = null;
            int var11 = -1;
            if ((var2.length < 11 || !var2[10].equals("force") && !var2[10].equals("move")) && var6.intersectsWith(var7)) {
               throw new CommandException("commands.clone.noOverlap");
            } else {
               if (var2.length >= 11 && var2[10].equals("move")) {
                  var9 = true;
               }

               if (var6.minY >= 0 && var6.maxY < 256 && var7.minY >= 0 && var7.maxY < 256) {
                  World var12 = var1.s_();
                  if (var12.isAreaLoaded(var6) && var12.isAreaLoaded(var7)) {
                     boolean var13 = false;
                     if (var2.length >= 10) {
                        if (var2[9].equals("masked")) {
                           var13 = true;
                        } else if (var2[9].equals("filtered")) {
                           if (var2.length < 12) {
                              throw new WrongUsageException("commands.clone.usage");
                           }

                           var10 = getBlockByText(var1, var2[11]);
                           if (var2.length >= 13) {
                              var11 = parseInt(var2[12], 0, 15);
                           }
                        }
                     }

                     ArrayList var14 = Lists.newArrayList();
                     ArrayList var15 = Lists.newArrayList();
                     ArrayList var16 = Lists.newArrayList();
                     LinkedList var17 = Lists.newLinkedList();
                     BlockPos var18 = new BlockPos(var7.minX - var6.minX, var7.minY - var6.minY, var7.minZ - var6.minZ);

                     for (int var19 = var6.minZ; var19 <= var6.maxZ; var19++) {
                        for (int var20 = var6.minY; var20 <= var6.maxY; var20++) {
                           for (int var21 = var6.minX; var21 <= var6.maxX; var21++) {
                              BlockPos var22 = new BlockPos(var21, var20, var19);
                              BlockPos var23 = var22.add(var18);
                              IBlockState var24 = var12.getBlockState(var22);
                              if ((!var13 || var24.getBlock() != Blocks.air)
                                 && (var10 == null || var24.getBlock() == var10 && (var11 < 0 || var24.getBlock().getMetaFromState(var24) == var11))) {
                                 TileEntity var25 = var12.getTileEntity(var22);
                                 if (var25 != null) {
                                    NBTTagCompound var26 = new NBTTagCompound();
                                    var25.writeToNBT(var26);
                                    var15.add(new CommandClone.StaticCloneData(var23, var24, var26));
                                    var17.addLast(var22);
                                 } else if (!var24.getBlock().isFullBlock() && !var24.getBlock().isFullCube()) {
                                    var16.add(new CommandClone.StaticCloneData(var23, var24, (NBTTagCompound)null));
                                    var17.addFirst(var22);
                                 } else {
                                    var14.add(new CommandClone.StaticCloneData(var23, var24, (NBTTagCompound)null));
                                    var17.addLast(var22);
                                 }
                              }
                           }
                        }
                     }

                     if (var9) {
                        for (BlockPos var31 : (Iterable<BlockPos>)(Iterable<?>)(var17)) {
                           TileEntity var34 = var12.getTileEntity(var31);
                           if (var34 instanceof IInventory) {
                              ((IInventory)var34).clear();
                           }

                           var12.a(var31, Blocks.barrier.getDefaultState(), 2);
                        }

                        for (BlockPos var32 : (Iterable<BlockPos>)(Iterable<?>)(var17)) {
                           var12.a(var32, Blocks.air.getDefaultState(), 3);
                        }
                     }

                     ArrayList var30 = Lists.newArrayList();
                     var30.addAll(var14);
                     var30.addAll(var15);
                     var30.addAll(var16);
                     List var33 = Lists.reverse(var30);

                     for (CommandClone.StaticCloneData var40 : (Iterable<CommandClone.StaticCloneData>)(Iterable<?>)(var33)) {
                        TileEntity var45 = var12.getTileEntity(var40.recoveredField1449);
                        if (var45 instanceof IInventory) {
                           ((IInventory)var45).clear();
                        }

                        var12.a(var40.recoveredField1449, Blocks.barrier.getDefaultState(), 2);
                     }

                     var8 = 0;

                     for (CommandClone.StaticCloneData var41 : (Iterable<CommandClone.StaticCloneData>)(Iterable<?>)(var30)) {
                        if (var12.a(var41.recoveredField1449, var41.recoveredField1451, 2)) {
                           var8++;
                        }
                     }

                     for (CommandClone.StaticCloneData var42 : (Iterable<CommandClone.StaticCloneData>)(Iterable<?>)(var15)) {
                        TileEntity var46 = var12.getTileEntity(var42.recoveredField1449);
                        if (var42.recoveredField1450 != null && var46 != null) {
                           var42.recoveredField1450.setInteger("x", var42.recoveredField1449.getX());
                           var42.recoveredField1450.setInteger("y", var42.recoveredField1449.getY());
                           var42.recoveredField1450.setInteger("z", var42.recoveredField1449.getZ());
                           var46.readFromNBT(var42.recoveredField1450);
                           var46.markDirty();
                        }

                        var12.a(var42.recoveredField1449, var42.recoveredField1451, 2);
                     }

                     for (CommandClone.StaticCloneData var43 : (Iterable<CommandClone.StaticCloneData>)(Iterable<?>)(var33)) {
                        var12.notifyNeighborsRespectDebug(var43.recoveredField1449, var43.recoveredField1451.getBlock());
                     }

                     List var39 = var12.func_175712_a(var6, false);
                     if (var39 != null) {
                        for (NextTickListEntry var47 : (Iterable<NextTickListEntry>)(Iterable<?>)(var39)) {
                           if (var6.isVecInside(var47.position)) {
                              BlockPos var48 = var47.position.add(var18);
                              var12.scheduleBlockUpdate(var48, var47.getBlock(), (int)(var47.scheduledTime - var12.P().getWorldTotalTime()), var47.priority);
                           }
                        }
                     }

                     if (var8 <= 0) {
                        throw new CommandException("commands.clone.failed");
                     } else {
                        var1.setCommandStat(CommandResultStats.Type.AFFECTED_BLOCKS, var8);
                        notifyOperators(var1, this, "commands.clone.success", var8);
                     }
                  } else {
                     throw new CommandException("commands.clone.outOfWorld");
                  }
               } else {
                  throw new CommandException("commands.clone.outOfWorld");
               }
            }
         }
      }
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.clone.usage";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandName() {
      return "clone";
   }

   public static class StaticCloneData {
      public BlockPos recoveredField1449;
      public NBTTagCompound recoveredField1450;
      public IBlockState recoveredField1451;

      public StaticCloneData(BlockPos var1, IBlockState var2, NBTTagCompound var3) {
         this.recoveredField1449 = var1;
         this.recoveredField1451 = var2;
         this.recoveredField1450 = var3;
      }
   }
}
