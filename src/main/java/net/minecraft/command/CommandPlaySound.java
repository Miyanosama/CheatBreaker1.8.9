package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class CommandPlaySound extends CommandBase {
   @Override
   public void processCommand(ICommandSender var1, String[] var2) throws net.minecraft.command.CommandException {
      if (var2.length < 2) {
         throw new WrongUsageException(this.getCommandUsage(var1));
      } else {
         int var3 = 0;
         String var4 = var2[var3++];
         EntityPlayerMP var5 = getPlayer(var1, var2[var3++]);
         Vec3 var6 = var1.q_();
         double var7 = var6.xCoord;
         if (var2.length > var3) {
            var7 = parseDouble(var7, var2[var3++], true);
         }

         double var9 = var6.yCoord;
         if (var2.length > var3) {
            var9 = parseDouble(var9, var2[var3++], 0, 0, false);
         }

         double var11 = var6.zCoord;
         if (var2.length > var3) {
            var11 = parseDouble(var11, var2[var3++], true);
         }

         double var13 = 1.0;
         if (var2.length > var3) {
            var13 = parseDouble(var2[var3++], 0.0, Float.MAX_VALUE);
         }

         double var15 = 1.0;
         if (var2.length > var3) {
            var15 = parseDouble(var2[var3++], 0.0, 2.0);
         }

         double var17 = 0.0;
         if (var2.length > var3) {
            var17 = parseDouble(var2[var3], 0.0, 1.0);
         }

         double var19 = var13 > 1.0 ? var13 * 16.0 : 16.0;
         double var21 = var5.getDistance(var7, var9, var11);
         if (var21 > var19) {
            if (var17 <= 0.0) {
               throw new CommandException("commands.playsound.playerTooFar", var5.z_());
            }

            double var23 = var7 - var5.s;
            double var25 = var9 - var5.t;
            double var27 = var11 - var5.u;
            double var29 = Math.sqrt(var23 * var23 + var25 * var25 + var27 * var27);
            if (var29 > 0.0) {
               var7 = var5.s + var23 / var29 * 2.0;
               var9 = var5.t + var25 / var29 * 2.0;
               var11 = var5.u + var27 / var29 * 2.0;
            }

            var13 = var17;
         }

         var5.playerNetServerHandler.sendPacket(new S29PacketSoundEffect(var4, var7, var9, var11, (float)var13, (float)var15));
         notifyOperators(var1, this, "commands.playsound.success", var4, var5.z_());
      }
   }

   @Override
   public String getCommandName() {
      return "playsound";
   }

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      return var2.length == 2
         ? getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getAllUsernames())
         : (var2.length > 2 && var2.length <= 5 ? method_02118(var2, 2, var3) : null);
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public boolean isUsernameIndex(String[] var1, int var2) {
      return var2 == 1;
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.playsound.usage";
   }
}
