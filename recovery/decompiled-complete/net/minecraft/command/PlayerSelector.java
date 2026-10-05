package net.minecraft.command;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.block.BlockDoor$EnumDoorHalf;
import net.minecraft.client.stream.TwitchStream;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.JsonToNBT$Any;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings$GameType;
import recovered.unidentified.UnidentifiedClass4638;

public class PlayerSelector {
   public BlockDoor$EnumDoorHalf field_0003;
   public static Pattern intListPattern = Pattern.compile("\\G([-!]?[\\w-]*)(?:$|,)");
   public EntityCow field_0002;
   public JsonToNBT$Any field_0005;
   public TwitchStream field_0000;
   public static Pattern keyValueListPattern = Pattern.compile("\\G(\\w+)=([-!]?[\\w-]*)(?:$|,)");
   public static Set<String> WORLD_BINDING_ARGS = Sets.newHashSet(new String[]{"x", "y", "z", "dx", "dy", "dz", "rm", "r"});
   public static Pattern tokenPattern = Pattern.compile("^@([pare])(?:\\[([\\w=,!-]*)\\])?$");

   public static int func_179650_a(int var0) {
      var0 %= 360;
      if (var0 >= 160) {
         var0 -= 360;
      }

      if (var0 < 0) {
         var0 += 360;
      }

      return var0;
   }

   public static <T extends Entity> List<T> func_179658_a(
      List<T> var0, Map<String, String> var1, ICommandSender var2, Class<? extends T> var3, String var4, BlockPos var5
   ) {
      int var6 = parseIntWithDefault(var1, "c", !var4.equals("a") && !var4.equals("e") ? 1 : 0);
      if (!var4.equals("p") && !var4.equals("a") && !var4.equals("e")) {
         if (var4.equals("r")) {
            Collections.shuffle((List<?>)var0);
         }
      } else if (var5 != null) {
         Collections.sort((List<Entity>)var0, new PlayerSelector$4(var5));
      }

      Entity var7 = var2.p_();
      if (var7 != null && var3.isAssignableFrom(var7.getClass()) && var6 == 1 && var0.contains(var7) && !"r".equals(var4)) {
         var0 = Lists.newArrayList(new Entity[]{var7});
      }

      if (var6 != 0) {
         if (var6 < 0) {
            Collections.reverse((List<?>)var0);
         }

         var0 = var0.subList(0, Math.min(Math.abs(var6), var0.size()));
      }

      return (List<T>)var0;
   }

   public static <T extends Entity> T matchOneEntity(ICommandSender var0, String var1, Class<? extends T> var2) {
      List var3 = matchEntities(var0, var1, var2);
      return (T)(var3.size() == 1 ? var3.get(0) : null);
   }

   public static boolean func_179665_h(Map<String, String> var0) {
      for (String var2 : WORLD_BINDING_ARGS) {
         if (var0.containsKey(var2)) {
            return true;
         }
      }

      return false;
   }

   public static AxisAlignedBB func_179661_a(BlockPos var0, int var1, int var2, int var3) {
      boolean var4 = var1 < 0;
      boolean var5 = var2 < 0;
      boolean var6 = var3 < 0;
      int var7 = var0.getX() + (var4 ? var1 : 0);
      int var8 = var0.getY() + (var5 ? var2 : 0);
      int var9 = var0.getZ() + (var6 ? var3 : 0);
      int var10 = var0.getX() + (var4 ? 0 : var1) + 1;
      int var11 = var0.getY() + (var5 ? 0 : var2) + 1;
      int var12 = var0.getZ() + (var6 ? 0 : var3) + 1;
      return new AxisAlignedBB(var7, var8, var9, var10, var11, var12);
   }

   public static List<Predicate<Entity>> getRotationsPredicates(Map<String, String> var0) {
      ArrayList var1 = Lists.newArrayList();
      if (var0.containsKey("rym") || var0.containsKey("ry")) {
         int var2 = func_179650_a(parseIntWithDefault(var0, "rym", 0));
         int var3 = func_179650_a(parseIntWithDefault(var0, "ry", 359));
         var1.add(new PlayerSelector$12(var2, var3));
      }

      if (var0.containsKey("rxm") || var0.containsKey("rx")) {
         int var4 = func_179650_a(parseIntWithDefault(var0, "rxm", 0));
         int var5 = func_179650_a(parseIntWithDefault(var0, "rx", 359));
         var1.add(new PlayerSelector$2(var4, var5));
      }

      return var1;
   }

   public static List<Predicate<Entity>> func_179663_a(Map<String, String> var0, String var1) {
      ArrayList var2 = Lists.newArrayList();
      String var3 = func_179651_b(var0, "type");
      boolean var4 = var3 != null && var3.startsWith("!");
      if (var4) {
         var3 = var3.substring(1);
      }

      boolean var5 = !var1.equals("e");
      boolean var6 = var1.equals("r") && var3 != null;
      if ((var3 == null || !var1.equals("e")) && !var6) {
         if (var5) {
            var2.add(new PlayerSelector$5());
         }
      } else {
         var2.add(new PlayerSelector$1(var3, var4));
      }

      return var2;
   }

   public static List<Predicate<Entity>> func_180698_a(Map<String, String> var0, BlockPos var1) {
      ArrayList var2 = Lists.newArrayList();
      int var3 = parseIntWithDefault(var0, "rm", -1);
      int var4 = parseIntWithDefault(var0, "r", -1);
      if (var1 != null && (var3 >= 0 || var4 >= 0)) {
         int var5 = var3 * var3;
         int var6 = var4 * var4;
         var2.add(new PlayerSelector$11(var1, var3, var5, var4, var6));
      }

      return var2;
   }

   public static List<World> getWorlds(ICommandSender var0, Map<String, String> var1) {
      ArrayList var2 = Lists.newArrayList();
      if (func_179665_h(var1)) {
         var2.add(var0.s_());
      } else {
         Collections.addAll(var2, MinecraftServer.getServer().worldServers);
      }

      return var2;
   }

   public static boolean matchesMultiplePlayers(String var0) {
      Matcher var1 = tokenPattern.matcher(var0);
      if (!var1.matches()) {
         return false;
      } else {
         Map var2 = getArgumentMap(var1.group(2));
         String var3 = var1.group(1);
         int var4 = !"a".equals(var3) && !"e".equals(var3) ? 1 : 0;
         return parseIntWithDefault(var2, "c", var4) != 1;
      }
   }

   public static List<Predicate<Entity>> getScorePredicates(Map<String, String> var0) {
      ArrayList var1 = Lists.newArrayList();
      Map var2 = func_96560_a(var0);
      if (var2 != null && var2.size() > 0) {
         var1.add(new PlayerSelector$9(var2));
      }

      return var1;
   }

   public static List<Predicate<Entity>> getXpLevelPredicates(Map<String, String> var0) {
      ArrayList var1 = Lists.newArrayList();
      int var2 = parseIntWithDefault(var0, "lm", -1);
      int var3 = parseIntWithDefault(var0, "l", -1);
      if (var2 > -1 || var3 > -1) {
         var1.add(new PlayerSelector$6(var2, var3));
      }

      return var1;
   }

   public static Map<String, String> getArgumentMap(String var0) {
      HashMap var1 = Maps.newHashMap();
      if (var0 == null) {
         return var1;
      } else {
         int var2 = 0;
         int var3 = -1;

         for (Matcher var4 = intListPattern.matcher(var0); var4.find(); var3 = var4.end()) {
            String var5 = null;
            switch (var2++) {
               case 0:
                  var5 = "x";
                  break;
               case 1:
                  var5 = "y";
                  break;
               case 2:
                  var5 = "z";
                  break;
               case 3:
                  var5 = "r";
            }

            if (var5 != null && var4.group(1).length() > 0) {
               var1.put(var5, var4.group(1));
            }
         }

         if (var3 < var0.length()) {
            Matcher var6 = keyValueListPattern.matcher(var3 == -1 ? var0 : var0.substring(var3));

            while (var6.find()) {
               var1.put(var6.group(1), var6.group(2));
            }
         }

         return var1;
      }
   }

   public static <T extends Entity> List<T> filterResults(
      Map<String, String> var0, Class<? extends T> var1, List<Predicate<Entity>> var2, String var3, World var4, BlockPos var5
   ) {
      ArrayList var6 = Lists.newArrayList();
      String var7 = func_179651_b(var0, "type");
      var7 = var7 != null && var7.startsWith("!") ? var7.substring(1) : var7;
      boolean var8 = !var3.equals("e");
      boolean var9 = var3.equals("r") && var7 != null;
      int var10 = parseIntWithDefault(var0, "dx", 0);
      int var11 = parseIntWithDefault(var0, "dy", 0);
      int var12 = parseIntWithDefault(var0, "dz", 0);
      int var13 = parseIntWithDefault(var0, "r", -1);
      Predicate var14 = Predicates.and(var2);
      Predicate var15 = Predicates.and(EntitySelectors.selectAnything, var14);
      if (var5 != null) {
         int var16 = var4.j.size();
         int var17 = var4.f.size();
         boolean var18 = var16 < var17 / 16;
         if (var0.containsKey("dx") || var0.containsKey("dy") || var0.containsKey("dz")) {
            AxisAlignedBB var22 = func_179661_a(var5, var10, var11, var12);
            if (var8 && var18 && !var9) {
               UnidentifiedClass4638 var20 = new UnidentifiedClass4638(var22);
               var6.addAll(var4.method_10010(var1, Predicates.and(var15, var20)));
            } else {
               var6.addAll(var4.getEntitiesWithinAABB(var1, var22, var15));
            }
         } else if (var13 >= 0) {
            AxisAlignedBB var19 = new AxisAlignedBB(
               var5.getX() - var13, var5.getY() - var13, var5.getZ() - var13, var5.getX() + var13 + 1, var5.getY() + var13 + 1, var5.getZ() + var13 + 1
            );
            if (var8 && var18 && !var9) {
               var6.addAll(var4.method_10010(var1, var15));
            } else {
               var6.addAll(var4.getEntitiesWithinAABB(var1, var19, var15));
            }
         } else if (var3.equals("a")) {
            var6.addAll(var4.method_10010(var1, var14));
         } else if (var3.equals("p") || var3.equals("r") && !var9) {
            var6.addAll(var4.method_10010(var1, var15));
         } else {
            var6.addAll(var4.method_09943(var1, var15));
         }
      } else if (var3.equals("a")) {
         var6.addAll(var4.method_10010(var1, var14));
      } else if (var3.equals("p") || var3.equals("r") && !var9) {
         var6.addAll(var4.method_10010(var1, var15));
      } else {
         var6.addAll(var4.method_09943(var1, var15));
      }

      return var6;
   }

   public static String func_179651_b(Map<String, String> var0, String var1) {
      return (String)var0.get(var1);
   }

   public static EntityPlayerMP matchOnePlayer(ICommandSender var0, String var1) {
      return matchOneEntity(var0, var1, EntityPlayerMP.class);
   }

   public static IChatComponent matchEntitiesToChatComponent(ICommandSender var0, String var1) {
      List var2 = matchEntities(var0, var1, Entity.class);
      if (var2.isEmpty()) {
         return null;
      } else {
         ArrayList var3 = Lists.newArrayList();

         for (Entity var5 : var2) {
            var3.add(var5.getDisplayName());
         }

         return CommandBase.join(var3);
      }
   }

   public static List<Predicate<Entity>> method_01788(Map<String, String> var0) {
      ArrayList var1 = Lists.newArrayList();
      String var2 = func_179651_b(var0, "name");
      boolean var3 = var2 != null && var2.startsWith("!");
      if (var3) {
         var2 = var2.substring(1);
      }

      if (var2 != null) {
         var1.add(new PlayerSelector$10(var2, var3));
      }

      return var1;
   }

   public static BlockPos func_179664_b(Map<String, String> var0, BlockPos var1) {
      return new BlockPos(parseIntWithDefault(var0, "x", var1.getX()), parseIntWithDefault(var0, "y", var1.getY()), parseIntWithDefault(var0, "z", var1.getZ()));
   }

   public static boolean hasArguments(String var0) {
      return tokenPattern.matcher(var0).matches();
   }

   public static List<Predicate<Entity>> method_01789(Map<String, String> var0) {
      ArrayList var1 = Lists.newArrayList();
      String var2 = func_179651_b(var0, "team");
      boolean var3 = var2 != null && var2.startsWith("!");
      if (var3) {
         var2 = var2.substring(1);
      }

      if (var2 != null) {
         var1.add(new PlayerSelector$8(var2, var3));
      }

      return var1;
   }

   public static <T extends Entity> List<T> matchEntities(ICommandSender var0, String var1, Class<? extends T> var2) {
      Matcher var3 = tokenPattern.matcher(var1);
      if (var3.matches() && var0.canCommandSenderUseCommand(1, "@")) {
         Map var4 = getArgumentMap(var3.group(2));
         if (!isEntityTypeValid(var0, var4)) {
            return Collections.emptyList();
         } else {
            String var5 = var3.group(1);
            BlockPos var6 = func_179664_b(var4, var0.getPosition());
            List var7 = getWorlds(var0, var4);
            ArrayList var8 = Lists.newArrayList();

            for (World var10 : var7) {
               if (var10 != null) {
                  ArrayList var11 = Lists.newArrayList();
                  var11.addAll(func_179663_a(var4, var5));
                  var11.addAll(getXpLevelPredicates(var4));
                  var11.addAll(getGamemodePredicates(var4));
                  var11.addAll(method_01789(var4));
                  var11.addAll(getScorePredicates(var4));
                  var11.addAll(method_01788(var4));
                  var11.addAll(func_180698_a(var4, var6));
                  var11.addAll(getRotationsPredicates(var4));
                  var8.addAll(filterResults(var4, var2, var11, var5, var10, var6));
               }
            }

            return func_179658_a(var8, var4, var0, var2, var5, var6);
         }
      } else {
         return Collections.emptyList();
      }
   }

   public static int parseIntWithDefault(Map<String, String> var0, String var1, int var2) {
      return var0.containsKey(var1) ? MathHelper.parseIntWithDefault((String)var0.get(var1), var2) : var2;
   }

   public static Map<String, Integer> func_96560_a(Map<String, String> var0) {
      HashMap var1 = Maps.newHashMap();

      for (String var3 : var0.keySet()) {
         if (var3.startsWith("score_") && var3.length() > "score_".length()) {
            var1.put(var3.substring("score_".length()), MathHelper.parseIntWithDefault((String)var0.get(var3), 1));
         }
      }

      return var1;
   }

   public static List<Predicate<Entity>> getGamemodePredicates(Map<String, String> var0) {
      ArrayList var1 = Lists.newArrayList();
      int var2 = parseIntWithDefault(var0, "m", WorldSettings$GameType.NOT_SET.getID());
      if (var2 != WorldSettings$GameType.NOT_SET.getID()) {
         var1.add(new PlayerSelector$7(var2));
      }

      return var1;
   }

   public static <T extends Entity> boolean isEntityTypeValid(ICommandSender var0, Map<String, String> var1) {
      String var2 = func_179651_b(var1, "type");
      var2 = var2 != null && var2.startsWith("!") ? var2.substring(1) : var2;
      if (var2 != null && !EntityList.isStringValidEntityName(var2)) {
         ChatComponentTranslation var3 = new ChatComponentTranslation("commands.generic.entity.invalidType", var2);
         var3.getChatStyle().setColor(EnumChatFormatting.RED);
         var0.addChatMessage(var3);
         return false;
      } else {
         return true;
      }
   }
}
