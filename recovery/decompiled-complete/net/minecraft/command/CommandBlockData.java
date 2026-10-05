package net.minecraft.command;

import java.util.List;
import net.minecraft.block.BlockNewLeaf$1;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.java_websocket.WebSocketAdapter;

public class CommandBlockData extends CommandBase {
   public BlockNewLeaf$1 field_0000;
   public WebSocketAdapter field_0001;

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      return var2.length > 0 && var2.length <= 3 ? method_02118(var2, 0, var3) : null;
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandName() {
      return "blockdata";
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) {
      if (var2.length < 4) {
         throw new WrongUsageException("commands.blockdata.usage");
      } else {
         var1.setCommandStat(CommandResultStats$Type.AFFECTED_BLOCKS, 0);
         BlockPos var3 = parseBlockPos(var1, var2, 0, false);
         World var4 = var1.s_();
         if (!var4.e(var3)) {
            throw new CommandException("commands.blockdata.outOfWorld");
         } else {
            TileEntity var5 = var4.getTileEntity(var3);
            if (var5 == null) {
               throw new CommandException("commands.blockdata.notValid");
            } else {
               NBTTagCompound var6 = new NBTTagCompound();
               var5.writeToNBT(var6);
               NBTTagCompound var7 = (NBTTagCompound)var6.copy();

               NBTTagCompound var8;
               try {
                  var8 = JsonToNBT.getTagFromJson(getChatComponentFromNthArg(var1, var2, 3).getUnformattedText());
               } catch (NBTException var10) {
                  throw new CommandException("commands.blockdata.tagError", var10.getMessage());
               }

               var6.merge(var8);
               var6.setInteger("x", var3.getX());
               var6.setInteger("y", var3.getY());
               var6.setInteger("z", var3.getZ());
               if (var6.equals(var7)) {
                  throw new CommandException("commands.blockdata.failed", var6.toString());
               } else {
                  var5.readFromNBT(var6);
                  var5.markDirty();
                  var4.h(var3);
                  var1.setCommandStat(CommandResultStats$Type.AFFECTED_BLOCKS, 1);
                  notifyOperators(var1, this, "commands.blockdata.success", var6.toString());
               }
            }
         }
      }
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.blockdata.usage";
   }
}
