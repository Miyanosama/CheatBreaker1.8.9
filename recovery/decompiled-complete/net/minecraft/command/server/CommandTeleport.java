package net.minecraft.command.server;

import com.cheatbreaker.client.ui.mainmenu.LegacyMainMenu;
import io.netty.channel.oio.OioByteStreamChannel$1;
import io.netty.handler.ssl.SslHandler$7;
import java.util.EnumSet;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandBase$CoordinateArg;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S08PacketPlayerPosLook$EnumFlags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import org.apache.log4j.helpers.PatternConverter;
import org.java_websocket.extensions.ExtensionRequestData;

public class CommandTeleport extends CommandBase {
   public LegacyMainMenu field_0002;
   public ExtensionRequestData field_0003;
   public SslHandler$7 field_0000;
   public PatternConverter field_0001;
   public OioByteStreamChannel$1 field_0004;

   @Override
   public void processCommand(ICommandSender var1, String[] var2) {
      if (var2.length < 1) {
         throw new WrongUsageException("commands.tp.usage");
      } else {
         byte var3 = 0;
         Object var4;
         if (var2.length != 2 && var2.length != 4 && var2.length != 6) {
            var4 = getCommandSenderAsPlayer(var1);
         } else {
            var4 = getEntity(var1, var2[0]);
            var3 = 1;
         }

         if (var2.length != 1 && var2.length != 2) {
            if (var2.length < var3 + 3) {
               throw new WrongUsageException("commands.tp.usage");
            }

            if (((Entity)var4).o != null) {
               int var14 = var3 + 1;
               CommandBase$CoordinateArg var6 = parseCoordinate(((Entity)var4).s, var2[var3], true);
               CommandBase$CoordinateArg var7 = parseCoordinate(((Entity)var4).t, var2[var14++], 0, 0, false);
               CommandBase$CoordinateArg var8 = parseCoordinate(((Entity)var4).u, var2[var14++], true);
               CommandBase$CoordinateArg var9 = parseCoordinate(((Entity)var4).y, var2.length > var14 ? var2[var14++] : "~", false);
               CommandBase$CoordinateArg var10 = parseCoordinate(((Entity)var4).z, var2.length > var14 ? var2[var14] : "~", false);
               if (var4 instanceof EntityPlayerMP) {
                  EnumSet var17 = EnumSet.noneOf(S08PacketPlayerPosLook$EnumFlags.class);
                  if (var6.func_179630_c()) {
                     var17.add(S08PacketPlayerPosLook$EnumFlags.X);
                  }

                  if (var7.func_179630_c()) {
                     var17.add(S08PacketPlayerPosLook$EnumFlags.Y);
                  }

                  if (var8.func_179630_c()) {
                     var17.add(S08PacketPlayerPosLook$EnumFlags.Z);
                  }

                  if (var10.func_179630_c()) {
                     var17.add(S08PacketPlayerPosLook$EnumFlags.X_ROT);
                  }

                  if (var9.func_179630_c()) {
                     var17.add(S08PacketPlayerPosLook$EnumFlags.Y_ROT);
                  }

                  float var18 = (float)var9.func_179629_b();
                  if (!var9.func_179630_c()) {
                     var18 = MathHelper.wrapAngleTo180_float(var18);
                  }

                  float var13 = (float)var10.func_179629_b();
                  if (!var10.func_179630_c()) {
                     var13 = MathHelper.wrapAngleTo180_float(var13);
                  }

                  if (var13 > 90.0F || var13 < -90.0F) {
                     var13 = MathHelper.wrapAngleTo180_float(180.0F - var13);
                     var18 = MathHelper.wrapAngleTo180_float(var18 + 180.0F);
                  }

                  ((Entity)var4).mountEntity((Entity)null);
                  ((EntityPlayerMP)var4)
                     .playerNetServerHandler
                     .setPlayerLocation(var6.func_179629_b(), var7.func_179629_b(), var8.func_179629_b(), var18, var13, var17);
                  ((Entity)var4).setRotationYawHead(var18);
               } else {
                  float var11 = (float)MathHelper.wrapAngleTo180_double(var9.func_179628_a());
                  float var12 = (float)MathHelper.wrapAngleTo180_double(var10.func_179628_a());
                  if (var12 > 90.0F || var12 < -90.0F) {
                     var12 = MathHelper.wrapAngleTo180_float(180.0F - var12);
                     var11 = MathHelper.wrapAngleTo180_float(var11 + 180.0F);
                  }

                  ((Entity)var4).a_(var6.func_179628_a(), var7.func_179628_a(), var8.func_179628_a(), var11, var12);
                  ((Entity)var4).setRotationYawHead(var11);
               }

               notifyOperators(
                  var1, this, "commands.tp.success.coordinates", ((Entity)var4).z_(), var6.func_179628_a(), var7.func_179628_a(), var8.func_179628_a()
               );
            }
         } else {
            Entity var5 = getEntity(var1, var2[var2.length - 1]);
            if (var5.o != ((Entity)var4).o) {
               throw new CommandException("commands.tp.notSameDimension");
            }

            ((Entity)var4).mountEntity((Entity)null);
            if (var4 instanceof EntityPlayerMP) {
               ((EntityPlayerMP)var4).playerNetServerHandler.setPlayerLocation(var5.s, var5.t, var5.u, var5.y, var5.z);
            } else {
               ((Entity)var4).a_(var5.s, var5.t, var5.u, var5.y, var5.z);
            }

            notifyOperators(var1, this, "commands.tp.success", ((Entity)var4).z_(), var5.z_());
         }
      }
   }

   @Override
   public String getCommandName() {
      return "tp";
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.tp.usage";
   }

   @Override
   public boolean isUsernameIndex(String[] var1, int var2) {
      return var2 == 0;
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      return var2.length != 1 && var2.length != 2 ? null : getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getAllUsernames());
   }
}
