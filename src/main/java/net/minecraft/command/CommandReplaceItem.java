package net.minecraft.command;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CommandReplaceItem extends CommandBase {
   public static Map<String, Integer> SHORTCUTS = Maps.newHashMap();

   static {
      for (int var0 = 0; var0 < 54; var0++) {
         SHORTCUTS.put("slot.container." + var0, var0);
      }

      for (int var1 = 0; var1 < 9; var1++) {
         SHORTCUTS.put("slot.hotbar." + var1, var1);
      }

      for (int var2 = 0; var2 < 27; var2++) {
         SHORTCUTS.put("slot.inventory." + var2, 9 + var2);
      }

      for (int var3 = 0; var3 < 27; var3++) {
         SHORTCUTS.put("slot.enderchest." + var3, 200 + var3);
      }

      for (int var4 = 0; var4 < 8; var4++) {
         SHORTCUTS.put("slot.villager." + var4, 300 + var4);
      }

      for (int var5 = 0; var5 < 15; var5++) {
         SHORTCUTS.put("slot.horse." + var5, 500 + var5);
      }

      SHORTCUTS.put("slot.weapon", 99);
      SHORTCUTS.put("slot.armor.head", 103);
      SHORTCUTS.put("slot.armor.chest", 102);
      SHORTCUTS.put("slot.armor.legs", 101);
      SHORTCUTS.put("slot.armor.feet", 100);
      SHORTCUTS.put("slot.horse.saddle", 400);
      SHORTCUTS.put("slot.horse.armor", 401);
      SHORTCUTS.put("slot.horse.chest", 499);
   }

   public String[] getUsernames() {
      return MinecraftServer.getServer().getAllUsernames();
   }

   @Override
   public String getCommandName() {
      return "replaceitem";
   }

   @Override
   public boolean isUsernameIndex(String[] var1, int var2) {
      return var1.length > 0 && var1[0].equals("entity") && var2 == 1;
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) throws net.minecraft.command.CommandException {
      if (var2.length < 1) {
         throw new WrongUsageException("commands.replaceitem.usage");
      } else {
         boolean var3;
         if (var2[0].equals("entity")) {
            var3 = false;
         } else {
            if (!var2[0].equals("block")) {
               throw new WrongUsageException("commands.replaceitem.usage");
            }

            var3 = true;
         }

         int var4;
         if (var3) {
            if (var2.length < 6) {
               throw new WrongUsageException("commands.replaceitem.block.usage");
            }

            var4 = 4;
         } else {
            if (var2.length < 4) {
               throw new WrongUsageException("commands.replaceitem.entity.usage");
            }

            var4 = 2;
         }

         int var5 = this.getSlotForShortcut(var2[var4++]);

         Item var6;
         try {
            var6 = getItemByText(var1, var2[var4]);
         } catch (NumberInvalidException var15) {
            if (Block.getBlockFromName(var2[var4]) != Blocks.air) {
               throw var15;
            }

            var6 = null;
         }

         var4++;
         int var7 = var2.length > var4 ? parseInt(var2[var4++], 1, 64) : 1;
         int var8 = var2.length > var4 ? parseInt(var2[var4++]) : 0;
         ItemStack var9 = new ItemStack(var6, var7, var8);
         if (var2.length > var4) {
            String var10 = getChatComponentFromNthArg(var1, var2, var4).getUnformattedText();

            try {
               var9.setTagCompound(JsonToNBT.getTagFromJson(var10));
            } catch (NBTException var14) {
               throw new CommandException("commands.replaceitem.tagError", var14.getMessage());
            }
         }

         if (var9.getItem() == null) {
            var9 = null;
         }

         if (var3) {
            var1.setCommandStat(CommandResultStats.Type.AFFECTED_ITEMS, 0);
            BlockPos var18 = parseBlockPos(var1, var2, 1, false);
            World var11 = var1.s_();
            TileEntity var12 = var11.getTileEntity(var18);
            if (var12 == null || !(var12 instanceof IInventory)) {
               throw new CommandException("commands.replaceitem.noContainer", var18.getX(), var18.getY(), var18.getZ());
            }

            IInventory var13 = (IInventory)var12;
            if (var5 >= 0 && var5 < var13.getSizeInventory()) {
               var13.setInventorySlotContents(var5, var9);
            }
         } else {
            Entity var19 = getEntity(var1, var2[1]);
            var1.setCommandStat(CommandResultStats.Type.AFFECTED_ITEMS, 0);
            if (var19 instanceof EntityPlayer) {
               ((EntityPlayer)var19).bj.detectAndSendChanges();
            }

            if (!var19.replaceItemInInventory(var5, var9)) {
               throw new CommandException("commands.replaceitem.failed", var5, var7, var9 == null ? "Air" : var9.getChatComponent());
            }

            if (var19 instanceof EntityPlayer) {
               ((EntityPlayer)var19).bj.detectAndSendChanges();
            }
         }

         var1.setCommandStat(CommandResultStats.Type.AFFECTED_ITEMS, var7);
         notifyOperators(var1, this, "commands.replaceitem.success", var5, var7, var9 == null ? "Air" : var9.getChatComponent());
      }
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.replaceitem.usage";
   }

   public int getSlotForShortcut(String var1) throws net.minecraft.command.CommandException {
      if (!SHORTCUTS.containsKey(var1)) {
         throw new CommandException("commands.generic.parameter.invalid", var1);
      } else {
         return SHORTCUTS.get(var1);
      }
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      return var2.length == 1
         ? getListOfStringsMatchingLastWord(var2, "entity", "block")
         : (
            var2.length == 2 && var2[0].equals("entity")
               ? getListOfStringsMatchingLastWord(var2, this.getUsernames())
               : (
                  var2.length >= 2 && var2.length <= 4 && var2[0].equals("block")
                     ? method_02118(var2, 1, var3)
                     : (
                        (var2.length != 3 || !var2[0].equals("entity")) && (var2.length != 5 || !var2[0].equals("block"))
                           ? (
                              var2.length == 4 && var2[0].equals("entity") || var2.length == 6 && var2[0].equals("block")
                                 ? getListOfStringsMatchingLastWord(var2, Item.itemRegistry.getKeys())
                                 : null
                           )
                           : getListOfStringsMatchingLastWord(var2, SHORTCUTS.keySet())
                     )
               )
         );
   }
}
