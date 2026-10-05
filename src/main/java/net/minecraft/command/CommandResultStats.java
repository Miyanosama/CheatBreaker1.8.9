package net.minecraft.command;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class CommandResultStats {
   public static int NUM_RESULT_TYPES = CommandResultStats.Type.values().length;
   public static String[] STRING_RESULT_TYPES = new String[NUM_RESULT_TYPES];
   public String[] entitiesID = STRING_RESULT_TYPES;
   public String[] objectives = STRING_RESULT_TYPES;

   public void readStatsFromNBT(NBTTagCompound var1) {
      if (var1.hasKey("CommandStats", 10)) {
         NBTTagCompound var2 = var1.getCompoundTag("CommandStats");

         for (CommandResultStats.Type var6 : CommandResultStats.Type.values()) {
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

      for (CommandResultStats.Type var6 : CommandResultStats.Type.values()) {
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

   public void setCommandStatScore(final ICommandSender var1, CommandResultStats.Type var2, int var3) {
      String var4 = this.entitiesID[var2.getTypeID()];
      if (var4 != null) {
         ICommandSender var5 = new ICommandSender() {
            @Override
            public IChatComponent getDisplayName() {
               return var1.getDisplayName();
            }

            @Override
            public boolean canCommandSenderUseCommand(int var1x, String var2x) {
               return true;
            }

            @Override
            public void addChatMessage(IChatComponent var1x) {
               var1.addChatMessage(var1x);
            }

            @Override
            public void setCommandStat(CommandResultStats.Type var1x, int var2x) {
               var1.setCommandStat(var1x, var2x);
            }

            @Override
            public String z_() {
               return var1.z_();
            }

            @Override
            public boolean C_() {
               return var1.C_();
            }

            @Override
            public World s_() {
               return var1.s_();
            }

            @Override
            public Entity p_() {
               return var1.p_();
            }

            @Override
            public Vec3 q_() {
               return var1.q_();
            }

            @Override
            public BlockPos getPosition() {
               return var1.getPosition();
            }
         };

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

   public static void setScoreBoardStat(CommandResultStats var0, CommandResultStats.Type var1, String var2, String var3) {
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
      for (CommandResultStats.Type var5 : CommandResultStats.Type.values()) {
         setScoreBoardStat(this, var5, var1.entitiesID[var5.getTypeID()], var1.objectives[var5.getTypeID()]);
      }
   }

   public static void removeScoreBoardStat(CommandResultStats var0, CommandResultStats.Type var1) {
      if (var0.entitiesID != STRING_RESULT_TYPES && var0.objectives != STRING_RESULT_TYPES) {
         var0.entitiesID[var1.getTypeID()] = null;
         var0.objectives[var1.getTypeID()] = null;
         boolean var2 = true;

         for (CommandResultStats.Type var6 : CommandResultStats.Type.values()) {
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

   public static enum Type {
      SUCCESS_COUNT(0, "SuccessCount"),
      AFFECTED_BLOCKS(1, "AffectedBlocks"),
      AFFECTED_ENTITIES(2, "AffectedEntities"),
      AFFECTED_ITEMS(3, "AffectedItems"),
      QUERY_RESULT(4, "QueryResult");
      // $VF: synthetic field
      public static CommandResultStats.Type[] $VALUES = new CommandResultStats.Type[]{
         CommandResultStats.Type.SUCCESS_COUNT,
         CommandResultStats.Type.AFFECTED_BLOCKS,
         CommandResultStats.Type.AFFECTED_ENTITIES,
         CommandResultStats.Type.AFFECTED_ITEMS,
         CommandResultStats.Type.QUERY_RESULT
      };
      public String typeName;
      public int typeID;

      Type(int var3, String var4) {
         this.typeID = var3;
         this.typeName = var4;
      }

      public static String[] getTypeNames() {
         String[] var0 = new String[values().length];
         int var1 = 0;

         for (CommandResultStats.Type var5 : values()) {
            var0[var1++] = var5.getTypeName();
         }

         return var0;
      }

      public int getTypeID() {
         return this.typeID;
      }

      public static CommandResultStats.Type getTypeByName(String var0) {
         for (CommandResultStats.Type var4 : values()) {
            if (var4.getTypeName().equals(var0)) {
               return var4;
            }
         }

         return null;
      }

      public String getTypeName() {
         return this.typeName;
      }
   }
}
