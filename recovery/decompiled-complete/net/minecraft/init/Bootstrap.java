package net.minecraft.init;

import java.io.PrintStream;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockFire;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.RecipeBookCloning;
import net.minecraft.stats.StatList;
import net.minecraft.util.LoggingPrintStream;
import net.minecraft.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bootstrap {
   public RecipeBookCloning field_0002;
   public static Logger LOGGER = LogManager.getLogger();
   public Util field_0001;
   public static boolean alreadyRegistered = false;
   public static PrintStream SYSOUT = System.out;

   public static boolean isRegistered() {
      return alreadyRegistered;
   }

   public static void registerDispenserBehaviors() {
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.arrow, new Bootstrap$1());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.egg, new Bootstrap$9());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.snowball, new Bootstrap$10());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.experience_bottle, new Bootstrap$11());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.potionitem, new Bootstrap$12());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.spawn_egg, new Bootstrap$13());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.fireworks, new Bootstrap$14());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.fire_charge, new Bootstrap$15());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.boat, new Bootstrap$16());
      Bootstrap$2 var0 = new Bootstrap$2();
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.lava_bucket, var0);
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.water_bucket, var0);
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.bucket, new Bootstrap$3());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.flint_and_steel, new Bootstrap$4());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.dye, new Bootstrap$5());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Item.getItemFromBlock(Blocks.tnt), new Bootstrap$6());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.skull, new Bootstrap$7());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Item.getItemFromBlock(Blocks.pumpkin), new Bootstrap$8());
   }

   public static void redirectOutputToLog() {
      System.setErr(new LoggingPrintStream("STDERR", System.err));
      System.setOut(new LoggingPrintStream("STDOUT", SYSOUT));
   }

   public static void printToSYSOUT(String var0) {
      SYSOUT.println(var0);
   }

   public static void register() {
      if (!alreadyRegistered) {
         alreadyRegistered = true;
         if (LOGGER.isDebugEnabled()) {
            redirectOutputToLog();
         }

         Block.registerBlocks();
         BlockFire.init();
         Item.registerItems();
         StatList.init();
         registerDispenserBehaviors();
      }
   }
}
