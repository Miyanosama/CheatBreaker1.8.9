package net.minecraft.command.server;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats$Type;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.status.server.S01PacketPong;
import net.minecraft.stats.StatCrafting;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.optifine.entity.model.ModelAdapterCaveSpider;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$25;
import org.apache.log4j.lf5.viewer.LogFactor5InputDialog$2;
import recovered.unidentified.UnidentifiedClass0599;

public class CommandTestForBlock extends CommandBase {
   public UnidentifiedClass0599 field_0002;
   public ModelAdapterCaveSpider field_0004;
   public LogFactor5InputDialog$2 field_0000;
   public StatCrafting field_0001;
   public S01PacketPong field_0005;
   public LogBrokerMonitor$25 field_0003;

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) {
      if (var2.length < 4) {
         throw new WrongUsageException("commands.testforblock.usage");
      } else {
         var1.setCommandStat(CommandResultStats$Type.AFFECTED_BLOCKS, 0);
         BlockPos var3 = parseBlockPos(var1, var2, 0, false);
         Block var4 = Block.getBlockFromName(var2[3]);
         if (var4 == null) {
            throw new NumberInvalidException("commands.setblock.notFound", var2[3]);
         } else {
            int var5 = -1;
            if (var2.length >= 5) {
               var5 = parseInt(var2[4], -1, 15);
            }

            World var6 = var1.s_();
            if (!var6.e(var3)) {
               throw new CommandException("commands.testforblock.outOfWorld");
            } else {
               NBTTagCompound var7 = new NBTTagCompound();
               boolean var8 = false;
               if (var2.length >= 6 && var4.hasTileEntity()) {
                  String var9 = getChatComponentFromNthArg(var1, var2, 5).getUnformattedText();

                  try {
                     var7 = JsonToNBT.getTagFromJson(var9);
                     var8 = true;
                  } catch (NBTException var13) {
                     throw new CommandException("commands.setblock.tagError", var13.getMessage());
                  }
               }

               IBlockState var14 = var6.getBlockState(var3);
               Block var10 = var14.getBlock();
               if (var10 != var4) {
                  throw new CommandException(
                     "commands.testforblock.failed.tile", var3.getX(), var3.getY(), var3.getZ(), var10.getLocalizedName(), var4.getLocalizedName()
                  );
               } else {
                  if (var5 > -1) {
                     int var11 = var14.getBlock().getMetaFromState(var14);
                     if (var11 != var5) {
                        throw new CommandException("commands.testforblock.failed.data", var3.getX(), var3.getY(), var3.getZ(), var11, var5);
                     }
                  }

                  if (var8) {
                     TileEntity var15 = var6.getTileEntity(var3);
                     if (var15 == null) {
                        throw new CommandException("commands.testforblock.failed.tileEntity", var3.getX(), var3.getY(), var3.getZ());
                     }

                     NBTTagCompound var12 = new NBTTagCompound();
                     var15.writeToNBT(var12);
                     if (!NBTUtil.func_181123_a(var7, var12, true)) {
                        throw new CommandException("commands.testforblock.failed.nbt", var3.getX(), var3.getY(), var3.getZ());
                     }
                  }

                  var1.setCommandStat(CommandResultStats$Type.AFFECTED_BLOCKS, 1);
                  notifyOperators(var1, this, "commands.testforblock.success", var3.getX(), var3.getY(), var3.getZ());
               }
            }
         }
      }
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.testforblock.usage";
   }

   @Override
   public String getCommandName() {
      return "testforblock";
   }

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      return var2.length > 0 && var2.length <= 3
         ? method_02118(var2, 0, var3)
         : (var2.length == 4 ? getListOfStringsMatchingLastWord(var2, Block.blockRegistry.getKeys()) : null);
   }
}
