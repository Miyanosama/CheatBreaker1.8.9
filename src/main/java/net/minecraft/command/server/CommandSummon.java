package net.minecraft.command.server;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class CommandSummon extends CommandBase {
   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) throws net.minecraft.command.CommandException {
      if (var2.length < 1) {
         throw new WrongUsageException("commands.summon.usage");
      } else {
         String var3 = var2[0];
         BlockPos var4 = var1.getPosition();
         Vec3 var5 = var1.q_();
         double var6 = var5.xCoord;
         double var8 = var5.yCoord;
         double var10 = var5.zCoord;
         if (var2.length >= 4) {
            var6 = parseDouble(var6, var2[1], true);
            var8 = parseDouble(var8, var2[2], false);
            var10 = parseDouble(var10, var2[3], true);
            var4 = new BlockPos(var6, var8, var10);
         }

         World var12 = var1.s_();
         if (!var12.e(var4)) {
            throw new CommandException("commands.summon.outOfWorld");
         } else {
            if ("LightningBolt".equals(var3)) {
               var12.addWeatherEffect(new EntityLightningBolt(var12, var6, var8, var10));
               notifyOperators(var1, this, "commands.summon.success");
            } else {
               NBTTagCompound var13 = new NBTTagCompound();
               boolean var14 = false;
               if (var2.length >= 5) {
                  IChatComponent var15 = getChatComponentFromNthArg(var1, var2, 4);

                  try {
                     var13 = JsonToNBT.getTagFromJson(var15.getUnformattedText());
                     var14 = true;
                  } catch (NBTException var20) {
                     throw new CommandException("commands.summon.tagError", var20.getMessage());
                  }
               }

               var13.setString("id", var3);

               Entity var21;
               try {
                  var21 = EntityList.createEntityFromNBT(var13, var12);
               } catch (RuntimeException var19) {
                  throw new CommandException("commands.summon.failed");
               }

               if (var21 == null) {
                  throw new CommandException("commands.summon.failed");
               }

               var21.a_(var6, var8, var10, var21.y, var21.z);
               if (!var14 && var21 instanceof EntityLiving) {
                  ((EntityLiving)var21).onInitialSpawn(var12.E(new BlockPos(var21)), (IEntityLivingData)null);
               }

               var12.spawnEntityInWorld(var21);
               Entity var16 = var21;

               for (NBTTagCompound var17 = var13; var16 != null && var17.hasKey("Riding", 10); var17 = var17.getCompoundTag("Riding")) {
                  Entity var18 = EntityList.createEntityFromNBT(var17.getCompoundTag("Riding"), var12);
                  if (var18 != null) {
                     var18.a_(var6, var8, var10, var18.y, var18.z);
                     var12.spawnEntityInWorld(var18);
                     var16.mountEntity(var18);
                  }

                  var16 = var18;
               }

               notifyOperators(var1, this, "commands.summon.success");
            }
         }
      }
   }

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      return var2.length == 1
         ? getListOfStringsMatchingLastWord(var2, EntityList.getEntityNameList())
         : (var2.length > 1 && var2.length <= 4 ? method_02118(var2, 1, var3) : null);
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.summon.usage";
   }

   @Override
   public String getCommandName() {
      return "summon";
   }
}
