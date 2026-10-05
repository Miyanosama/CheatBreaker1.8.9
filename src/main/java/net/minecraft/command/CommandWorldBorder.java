package net.minecraft.command;

import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.world.border.WorldBorder;

public class CommandWorldBorder extends CommandBase {
   @Override
   public String getCommandName() {
      return "worldborder";
   }

   public WorldBorder getWorldBorder() {
      return MinecraftServer.getServer().worldServers[0].af();
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) throws net.minecraft.command.CommandException {
      if (var2.length < 1) {
         throw new WrongUsageException("commands.worldborder.usage");
      } else {
         WorldBorder var3 = this.getWorldBorder();
         if (var2[0].equals("set")) {
            if (var2.length != 2 && var2.length != 3) {
               throw new WrongUsageException("commands.worldborder.set.usage");
            }

            double var4 = var3.getTargetSize();
            double var6 = parseDouble(var2[1], 1.0, 6.0E7);
            long var8 = var2.length > 2 ? parseLong(var2[2], 0L, 9223372036854775L) * 1000L : 0L;
            if (var8 > 0L) {
               var3.setTransition(var4, var6, var8);
               if (var4 > var6) {
                  notifyOperators(
                     var1,
                     this,
                     "commands.worldborder.setSlowly.shrink.success",
                     String.format("%.1f", var6),
                     String.format("%.1f", var4),
                     Long.toString(var8 / 1000L)
                  );
               } else {
                  notifyOperators(
                     var1,
                     this,
                     "commands.worldborder.setSlowly.grow.success",
                     String.format("%.1f", var6),
                     String.format("%.1f", var4),
                     Long.toString(var8 / 1000L)
                  );
               }
            } else {
               var3.setTransition(var6);
               notifyOperators(var1, this, "commands.worldborder.set.success", String.format("%.1f", var6), String.format("%.1f", var4));
            }
         } else if (var2[0].equals("add")) {
            if (var2.length != 2 && var2.length != 3) {
               throw new WrongUsageException("commands.worldborder.add.usage");
            }

            double var10 = var3.getDiameter();
            double var18 = var10 + parseDouble(var2[1], -var10, 6.0E7 - var10);
            long var21 = var3.getTimeUntilTarget() + (var2.length > 2 ? parseLong(var2[2], 0L, 9223372036854775L) * 1000L : 0L);
            if (var21 > 0L) {
               var3.setTransition(var10, var18, var21);
               if (var10 > var18) {
                  notifyOperators(
                     var1,
                     this,
                     "commands.worldborder.setSlowly.shrink.success",
                     String.format("%.1f", var18),
                     String.format("%.1f", var10),
                     Long.toString(var21 / 1000L)
                  );
               } else {
                  notifyOperators(
                     var1,
                     this,
                     "commands.worldborder.setSlowly.grow.success",
                     String.format("%.1f", var18),
                     String.format("%.1f", var10),
                     Long.toString(var21 / 1000L)
                  );
               }
            } else {
               var3.setTransition(var18);
               notifyOperators(var1, this, "commands.worldborder.set.success", String.format("%.1f", var18), String.format("%.1f", var10));
            }
         } else if (var2[0].equals("center")) {
            if (var2.length != 3) {
               throw new WrongUsageException("commands.worldborder.center.usage");
            }

            BlockPos var11 = var1.getPosition();
            double var5 = parseDouble(var11.getX() + 0.5, var2[1], true);
            double var7 = parseDouble(var11.getZ() + 0.5, var2[2], true);
            var3.setCenter(var5, var7);
            notifyOperators(var1, this, "commands.worldborder.center.success", var5, var7);
         } else if (var2[0].equals("damage")) {
            if (var2.length < 2) {
               throw new WrongUsageException("commands.worldborder.damage.usage");
            }

            if (var2[1].equals("buffer")) {
               if (var2.length != 3) {
                  throw new WrongUsageException("commands.worldborder.damage.buffer.usage");
               }

               double var12 = parseDouble(var2[2], 0.0);
               double var19 = var3.getDamageBuffer();
               var3.setDamageBuffer(var12);
               notifyOperators(var1, this, "commands.worldborder.damage.buffer.success", String.format("%.1f", var12), String.format("%.1f", var19));
            } else if (var2[1].equals("amount")) {
               if (var2.length != 3) {
                  throw new WrongUsageException("commands.worldborder.damage.amount.usage");
               }

               double var13 = parseDouble(var2[2], 0.0);
               double var20 = var3.getDamageAmount();
               var3.setDamageAmount(var13);
               notifyOperators(var1, this, "commands.worldborder.damage.amount.success", String.format("%.2f", var13), String.format("%.2f", var20));
            }
         } else if (var2[0].equals("warning")) {
            if (var2.length < 2) {
               throw new WrongUsageException("commands.worldborder.warning.usage");
            }

            int var14 = parseInt(var2[2], 0);
            if (var2[1].equals("time")) {
               if (var2.length != 3) {
                  throw new WrongUsageException("commands.worldborder.warning.time.usage");
               }

               int var16 = var3.getWarningTime();
               var3.setWarningTime(var14);
               notifyOperators(var1, this, "commands.worldborder.warning.time.success", var14, var16);
            } else if (var2[1].equals("distance")) {
               if (var2.length != 3) {
                  throw new WrongUsageException("commands.worldborder.warning.distance.usage");
               }

               int var17 = var3.getWarningDistance();
               var3.setWarningDistance(var14);
               notifyOperators(var1, this, "commands.worldborder.warning.distance.success", var14, var17);
            }
         } else {
            if (!var2[0].equals("get")) {
               throw new WrongUsageException("commands.worldborder.usage");
            }

            double var15 = var3.getDiameter();
            var1.setCommandStat(CommandResultStats.Type.QUERY_RESULT, MathHelper.floor_double(var15 + 0.5));
            var1.addChatMessage(new ChatComponentTranslation("commands.worldborder.get.success", String.format("%.0f", var15)));
         }
      }
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.worldborder.usage";
   }

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      return var2.length == 1
         ? getListOfStringsMatchingLastWord(var2, "set", "center", "damage", "warning", "add", "get")
         : (
            var2.length == 2 && var2[0].equals("damage")
               ? getListOfStringsMatchingLastWord(var2, "buffer", "amount")
               : (
                  var2.length >= 2 && var2.length <= 3 && var2[0].equals("center")
                     ? method_02130(var2, 1, var3)
                     : (var2.length == 2 && var2[0].equals("warning") ? getListOfStringsMatchingLastWord(var2, "time", "distance") : null)
               )
         );
   }
}
