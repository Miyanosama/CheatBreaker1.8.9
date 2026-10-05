package net.minecraft.command;

import com.cheatbreaker.client.ui.overlay.Alert;
import com.google.common.collect.Lists;
import io.netty.handler.codec.socks.SocksCommonUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$RightTurn;
import net.minecraft.world.storage.MapData$MapInfo;
import net.optifine.util.TextureUtils$2;

public class CommandStats extends CommandBase {
   public Alert field_0002;
   public TextureUtils$2 field_0003;
   public StructureStrongholdPieces$RightTurn field_0000;
   public SocksCommonUtils field_0001;
   public MapData$MapInfo field_0004;

   public String[] func_175776_d() {
      return MinecraftServer.getServer().getAllUsernames();
   }

   @Override
   public String getCommandName() {
      return "stats";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) {
      if (var2.length < 1) {
         throw new WrongUsageException("commands.stats.usage");
      } else {
         boolean var3;
         if (var2[0].equals("entity")) {
            var3 = false;
         } else {
            if (!var2[0].equals("block")) {
               throw new WrongUsageException("commands.stats.usage");
            }

            var3 = true;
         }

         int var4;
         if (var3) {
            if (var2.length < 5) {
               throw new WrongUsageException("commands.stats.block.usage");
            }

            var4 = 4;
         } else {
            if (var2.length < 3) {
               throw new WrongUsageException("commands.stats.entity.usage");
            }

            var4 = 2;
         }

         String var5 = var2[var4++];
         if ("set".equals(var5)) {
            if (var2.length < var4 + 3) {
               if (var4 == 5) {
                  throw new WrongUsageException("commands.stats.block.set.usage");
               }

               throw new WrongUsageException("commands.stats.entity.set.usage");
            }
         } else {
            if (!"clear".equals(var5)) {
               throw new WrongUsageException("commands.stats.usage");
            }

            if (var2.length < var4 + 1) {
               if (var4 == 5) {
                  throw new WrongUsageException("commands.stats.block.clear.usage");
               }

               throw new WrongUsageException("commands.stats.entity.clear.usage");
            }
         }

         CommandResultStats$Type var6 = CommandResultStats$Type.getTypeByName(var2[var4++]);
         if (var6 == null) {
            throw new CommandException("commands.stats.failed");
         } else {
            World var7 = var1.s_();
            CommandResultStats var8;
            if (var3) {
               BlockPos var9 = parseBlockPos(var1, var2, 1, false);
               TileEntity var10 = var7.getTileEntity(var9);
               if (var10 == null) {
                  throw new CommandException("commands.stats.noCompatibleBlock", var9.getX(), var9.getY(), var9.getZ());
               }

               if (var10 instanceof TileEntityCommandBlock) {
                  var8 = ((TileEntityCommandBlock)var10).getCommandResultStats();
               } else {
                  if (!(var10 instanceof TileEntitySign)) {
                     throw new CommandException("commands.stats.noCompatibleBlock", var9.getX(), var9.getY(), var9.getZ());
                  }

                  var8 = ((TileEntitySign)var10).getStats();
               }
            } else {
               Entity var14 = getEntity(var1, var2[1]);
               var8 = var14.getCommandStats();
            }

            if ("set".equals(var5)) {
               String var15 = var2[var4++];
               String var17 = var2[var4];
               if (var15.length() == 0 || var17.length() == 0) {
                  throw new CommandException("commands.stats.failed");
               }

               CommandResultStats.setScoreBoardStat(var8, var6, var15, var17);
               notifyOperators(var1, this, "commands.stats.success", var6.getTypeName(), var17, var15);
            } else if ("clear".equals(var5)) {
               CommandResultStats.setScoreBoardStat(var8, var6, (String)null, (String)null);
               notifyOperators(var1, this, "commands.stats.cleared", var6.getTypeName());
            }

            if (var3) {
               BlockPos var16 = parseBlockPos(var1, var2, 1, false);
               TileEntity var18 = var7.getTileEntity(var16);
               var18.markDirty();
            }
         }
      }
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.stats.usage";
   }

   @Override
   public boolean isUsernameIndex(String[] var1, int var2) {
      return var1.length > 0 && var1[0].equals("entity") && var2 == 1;
   }

   public List<String> func_175777_e() {
      Collection var1 = MinecraftServer.getServer().worldServerForDimension(0).Z().getScoreObjectives();
      ArrayList var2 = Lists.newArrayList();

      for (ScoreObjective var4 : var1) {
         if (!var4.getCriteria().isReadOnly()) {
            var2.add(var4.getName());
         }
      }

      return var2;
   }

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      return var2.length == 1
         ? getListOfStringsMatchingLastWord(var2, "entity", "block")
         : (
            var2.length == 2 && var2[0].equals("entity")
               ? getListOfStringsMatchingLastWord(var2, this.func_175776_d())
               : (
                  var2.length >= 2 && var2.length <= 4 && var2[0].equals("block")
                     ? method_02118(var2, 1, var3)
                     : (
                        (var2.length != 3 || !var2[0].equals("entity")) && (var2.length != 5 || !var2[0].equals("block"))
                           ? (
                              (var2.length != 4 || !var2[0].equals("entity")) && (var2.length != 6 || !var2[0].equals("block"))
                                 ? (
                                    var2.length == 6 && var2[0].equals("entity") || var2.length == 8 && var2[0].equals("block")
                                       ? getListOfStringsMatchingLastWord(var2, this.func_175777_e())
                                       : null
                                 )
                                 : getListOfStringsMatchingLastWord(var2, CommandResultStats$Type.getTypeNames())
                           )
                           : getListOfStringsMatchingLastWord(var2, "set", "clear")
                     )
               )
         );
   }
}
