package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandGive extends CommandBase {
   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      return var2.length == 1
         ? getListOfStringsMatchingLastWord(var2, this.getPlayers())
         : (var2.length == 2 ? getListOfStringsMatchingLastWord(var2, Item.itemRegistry.getKeys()) : null);
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandName() {
      return "give";
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.give.usage";
   }

   @Override
   public boolean isUsernameIndex(String[] var1, int var2) {
      return var2 == 0;
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) throws net.minecraft.command.CommandException {
      if (var2.length < 2) {
         throw new WrongUsageException("commands.give.usage");
      } else {
         EntityPlayerMP var3 = getPlayer(var1, var2[0]);
         Item var4 = getItemByText(var1, var2[1]);
         int var5 = var2.length >= 3 ? parseInt(var2[2], 1, 64) : 1;
         int var6 = var2.length >= 4 ? parseInt(var2[3]) : 0;
         ItemStack var7 = new ItemStack(var4, var5, var6);
         if (var2.length >= 5) {
            String var8 = getChatComponentFromNthArg(var1, var2, 4).getUnformattedText();

            try {
               var7.setTagCompound(JsonToNBT.getTagFromJson(var8));
            } catch (NBTException var10) {
               throw new CommandException("commands.give.tagError", var10.getMessage());
            }
         }

         boolean var11 = var3.bi.addItemStackToInventory(var7);
         if (var11) {
            var3.o.a(var3, "random.pop", 0.2F, ((var3.getRNG().nextFloat() - var3.getRNG().nextFloat()) * 0.7F + 1.0F) * 2.0F);
            var3.bj.detectAndSendChanges();
         }

         if (var11 && var7.stackSize <= 0) {
            var7.stackSize = 1;
            var1.setCommandStat(CommandResultStats.Type.AFFECTED_ITEMS, var5);
            EntityItem var12 = var3.dropPlayerItemWithRandomChoice(var7, false);
            if (var12 != null) {
               var12.func_174870_v();
            }
         } else {
            var1.setCommandStat(CommandResultStats.Type.AFFECTED_ITEMS, var5 - var7.stackSize);
            EntityItem var9 = var3.dropPlayerItemWithRandomChoice(var7, false);
            if (var9 != null) {
               var9.setNoPickupDelay();
               var9.setOwner(var3.z_());
            }
         }

         notifyOperators(var1, this, "commands.give.success", var7.getChatComponent(), var5, var3.z_());
      }
   }

   public String[] getPlayers() {
      return MinecraftServer.getServer().getAllUsernames();
   }
}
