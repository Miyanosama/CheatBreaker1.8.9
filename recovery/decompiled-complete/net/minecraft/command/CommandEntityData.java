package net.minecraft.command;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S1CPacketEntityMetadata;
import net.minecraft.world.gen.feature.WorldGenReed;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryAbstractCellEditor;
import recovered.unidentified.UnidentifiedClass3330;

public class CommandEntityData extends CommandBase {
   public WorldGenReed field_0002;
   public UnidentifiedClass3330 field_0003;
   public CategoryAbstractCellEditor field_0000;
   public S1CPacketEntityMetadata field_0001;

   @Override
   public boolean isUsernameIndex(String[] var1, int var2) {
      return var2 == 0;
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.entitydata.usage";
   }

   @Override
   public String getCommandName() {
      return "entitydata";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) {
      if (var2.length < 2) {
         throw new WrongUsageException("commands.entitydata.usage");
      } else {
         Entity var3 = getEntity(var1, var2[0]);
         if (var3 instanceof EntityPlayer) {
            throw new CommandException("commands.entitydata.noPlayers", var3.getDisplayName());
         } else {
            NBTTagCompound var4 = new NBTTagCompound();
            var3.e(var4);
            NBTTagCompound var5 = (NBTTagCompound)var4.copy();

            NBTTagCompound var6;
            try {
               var6 = JsonToNBT.getTagFromJson(getChatComponentFromNthArg(var1, var2, 1).getUnformattedText());
            } catch (NBTException var8) {
               throw new CommandException("commands.entitydata.tagError", var8.getMessage());
            }

            var6.removeTag("UUIDMost");
            var6.removeTag("UUIDLeast");
            var4.merge(var6);
            if (var4.equals(var5)) {
               throw new CommandException("commands.entitydata.failed", var4.toString());
            } else {
               var3.f(var4);
               notifyOperators(var1, this, "commands.entitydata.success", var4.toString());
            }
         }
      }
   }
}
