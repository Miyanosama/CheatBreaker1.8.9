package net.minecraft.command;

import io.netty.handler.codec.http.multipart.InterfaceHttpData$HttpDataType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import recovered.unidentified.UnidentifiedEnum1205;

public class CommandResultStats {
   public static int NUM_RESULT_TYPES = CommandResultStats$Type.values().length;
   public static String[] STRING_RESULT_TYPES = new String[NUM_RESULT_TYPES];
   public String[] entitiesID = STRING_RESULT_TYPES;
   public InterfaceHttpData$HttpDataType field_0004;
   public String[] objectives = STRING_RESULT_TYPES;
   public UnidentifiedEnum1205 field_0001;

   public void readStatsFromNBT(NBTTagCompound var1) {
      if (var1.hasKey("CommandStats", 10)) {
         NBTTagCompound var2 = var1.getCompoundTag("CommandStats");

         for (CommandResultStats$Type var6 : CommandResultStats$Type.values()) {
            String var7 = var6.getTypeName() + "Name";
            String var8 = var6.getTypeName() + "Objective";
            if (var2.hasKey(var7, 8) && var2.hasKey(var8, 8)) {
               String var9 = var2.getString(var7);
               String var10 = var2.getString(var8);
               setScoreBoardStat(this, var6, var9, var10);
            }
         }
      }
   }

   public void writeStatsToNBT(NBTTagCompound var1) {
      NBTTagCompound var2 = new NBTTagCompound();

      for (CommandResultStats$Type var6 : CommandResultStats$Type.values()) {
         String var7 = this.entitiesID[var6.getTypeID()];
         String var8 = this.objectives[var6.getTypeID()];
         if (var7 != null && var8 != null) {
            var2.setString(var6.getTypeName() + "Name", var7);
            var2.setString(var6.getTypeName() + "Objective", var8);
         }
      }

      if (!var2.hasNoTags()) {
         var1.setTag("CommandStats", var2);
      }
   }

   public void setCommandStatScore(ICommandSender var1, CommandResultStats$Type var2, int var3) {
      String var4 = this.entitiesID[var2.getTypeID()];
      if (var4 != null) {
         CommandResultStats$1 var5 = new CommandResultStats$1(this, var1);

         String var6;
         try {
            var6 = CommandBase.getEntityName(var5, var4);
         } catch (EntityNotFoundException var11) {
            return;
         }

         String var7 = this.objectives[var2.getTypeID()];
         if (var7 != null) {
            Scoreboard var8 = var1.s_().Z();
            ScoreObjective var9 = var8.getObjective(var7);
            if (var9 != null && var8.entityHasObjective(var6, var9)) {
               Score var10 = var8.getValueFromObjective(var6, var9);
               var10.setScorePoints(var3);
            }
         }
      }
   }

   public static void setScoreBoardStat(CommandResultStats var0, CommandResultStats$Type var1, String var2, String var3) {
      if (var2 != null && var2.length() != 0 && var3 != null && var3.length() != 0) {
         if (var0.entitiesID == STRING_RESULT_TYPES || var0.objectives == STRING_RESULT_TYPES) {
            var0.entitiesID = new String[NUM_RESULT_TYPES];
            var0.objectives = new String[NUM_RESULT_TYPES];
         }

         var0.entitiesID[var1.getTypeID()] = var2;
         var0.objectives[var1.getTypeID()] = var3;
      } else {
         removeScoreBoardStat(var0, var1);
      }
   }

   public void addAllStats(CommandResultStats var1) {
      for (CommandResultStats$Type var5 : CommandResultStats$Type.values()) {
         setScoreBoardStat(this, var5, var1.entitiesID[var5.getTypeID()], var1.objectives[var5.getTypeID()]);
      }
   }

   public static void removeScoreBoardStat(CommandResultStats var0, CommandResultStats$Type var1) {
      if (var0.entitiesID != STRING_RESULT_TYPES && var0.objectives != STRING_RESULT_TYPES) {
         var0.entitiesID[var1.getTypeID()] = null;
         var0.objectives[var1.getTypeID()] = null;
         boolean var2 = true;

         for (CommandResultStats$Type var6 : CommandResultStats$Type.values()) {
            if (var0.entitiesID[var6.getTypeID()] != null && var0.objectives[var6.getTypeID()] != null) {
               var2 = false;
               break;
            }
         }

         if (var2) {
            var0.entitiesID = STRING_RESULT_TYPES;
            var0.objectives = STRING_RESULT_TYPES;
         }
      }
   }
}
