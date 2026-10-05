package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.module.type.ToggleSprintModule;
import java.text.DecimalFormat;
import javazoom.jl.decoder.Decoder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.world.gen.feature.WorldGenerator;

public class UnidentifiedClass1316 extends MovementInputFromOptions {
   public static boolean field_0006 = false;
   public static boolean field_0013 = false;
   public static long field_0005;
   public static boolean field_0011;
   public static boolean field_0001 = true;
   public static boolean field_0002;
   public static long field_0014;
   public Decoder field_0009;
   public static boolean field_0003 = false;
   public static boolean field_0015;
   public static boolean field_0000;
   public static boolean field_0007;
   public static String field_0008 = "";
   public WorldGenerator field_0004;
   public static boolean field_0010;
   public static boolean field_0012;

   public void method_09086(boolean var1, boolean var2) {
      field_0001 = var1;
      field_0006 = var2;
   }

   public static void method_09084(MovementInputFromOptions var0, EntityPlayerSP var1, GameSettings var2) {
      Object var3 = "";
      boolean var4 = var1.bA.isFlying;
      boolean var5 = var1.au();
      boolean var6 = var2.keyBindSneak.isKeyDown();
      boolean var7 = var2.field_0080.isKeyDown();
      if (var4) {
         DecimalFormat var8 = new DecimalFormat("#.00");
         var3 = ToggleSprintModule.field_0002.getValue() && var7 && var1.bA.isCreativeMode
            ? var3
               + ((String)CheatBreaker.getInstance().getModuleManager().field_0033.field_0003.getValue())
                  .replaceAll("%BOOST%", var8.format(ToggleSprintModule.field_0020.getValue()))
            : var3 + CheatBreaker.getInstance().getModuleManager().field_0033.field_0007.getValue();
      }

      if (var5) {
         var3 = var3 + CheatBreaker.getInstance().getModuleManager().field_0033.field_0011.getValue();
      }

      if (var0.sneak) {
         var3 = var4
            ? CheatBreaker.getInstance().getModuleManager().field_0033.field_0021.getValue().toString()
            : (
               var5
                  ? CheatBreaker.getInstance().getModuleManager().field_0033.field_0012.getValue().toString()
                  : (
                     var6
                        ? var3 + CheatBreaker.getInstance().getModuleManager().field_0033.field_0015.getValue()
                        : var3 + CheatBreaker.getInstance().getModuleManager().field_0033.field_0019.getValue()
                  )
            );
      } else if (field_0001 && !var4 && !var5) {
         boolean var9 = field_0013 || field_0011 || field_0006;
         var3 = var7
            ? var3 + CheatBreaker.getInstance().getModuleManager().field_0033.field_0016.getValue()
            : (
               var9
                  ? var3 + CheatBreaker.getInstance().getModuleManager().field_0033.field_0001.getValue()
                  : var3 + CheatBreaker.getInstance().getModuleManager().field_0033.field_0009.getValue()
            );
      }

      field_0008 = (String)var3;
   }

   public UnidentifiedClass1316(GameSettings var1) {
      super(var1);
   }

   public static boolean method_09083() {
      return !CheatBreaker.getInstance().getModuleManager().field_0033.method_28796() && ToggleSprintModule.field_0010.method_08908();
   }

   public static void method_09085(Minecraft var0, MovementInputFromOptions var1, EntityPlayerSP var2) {
      var1.field_0003 = 0.0F;
      var1.field_0002 = 0.0F;
      GameSettings var3 = var0.gameSettings;
      if (var3.keyBindForward.isKeyDown()) {
         var1.field_0002++;
      }

      if (var3.keyBindBack.isKeyDown()) {
         var1.field_0002--;
      }

      if (var3.keyBindLeft.isKeyDown()) {
         var1.field_0003++;
      }

      if (var3.keyBindRight.isKeyDown()) {
         var1.field_0003--;
      }

      if (var2.au() && !field_0015) {
         field_0015 = true;
         field_0000 = field_0001;
      } else if (field_0015 && !var2.au()) {
         field_0015 = false;
         if (field_0000 && !field_0001) {
            field_0001 = true;
            field_0005 = System.currentTimeMillis();
            field_0007 = true;
            field_0013 = false;
         }
      }

      var1.jump = var3.keyBindJump.isKeyDown();
      if ((Boolean)ToggleSprintModule.field_0000.getValue() && CheatBreaker.getInstance().getModuleManager().field_0033.isEnabled()) {
         if (var3.keyBindSneak.isKeyDown() && !field_0002) {
            if (!var2.au() && !var2.bA.isFlying) {
               var1.sneak = !var1.sneak;
            } else {
               var1.sneak = true;
               field_0012 = var2.au();
            }

            field_0014 = System.currentTimeMillis();
            field_0002 = true;
         }

         if (!var3.keyBindSneak.isKeyDown() && field_0002) {
            if (!var2.bA.isFlying && !field_0012) {
               if (System.currentTimeMillis() - field_0014 > (1079316780L & 2461799740173093166L)) {
                  var1.sneak = false;
               }
            } else {
               var1.sneak = false;
            }

            field_0002 = false;
         }

         if (!method_09083()) {
            if (Minecraft.getMinecraft().currentScreen instanceof GuiContainer && var1.sneak) {
               field_0003 = true;
               var1.sneak = false;
            } else if (field_0003 && !(Minecraft.getMinecraft().currentScreen instanceof GuiContainer)) {
               field_0003 = false;
               var1.sneak = true;
            }
         }
      } else {
         var1.sneak = var3.keyBindSneak.isKeyDown();
      }

      if (var1.sneak) {
         var1.field_0003 = (float)(var1.field_0003 * 0.3);
         var1.field_0002 = (float)(var1.field_0002 * 0.3);
      }

      boolean var4 = var2.getFoodStats().getFoodLevel() > 6.0F || var2.bA.isFlying;
      boolean var5 = !var1.sneak && !var2.bA.isFlying && var4;
      field_0011 = !(Boolean)ToggleSprintModule.field_0014.getValue();
      field_0010 = (Boolean)ToggleSprintModule.field_0013.getValue();
      if ((var5 || field_0011) && var3.field_0080.isKeyDown() && !field_0007 && !var2.bA.isFlying && !field_0011) {
         field_0001 = !field_0001;
         field_0005 = System.currentTimeMillis();
         field_0007 = true;
         field_0013 = false;
      }

      if ((var5 || field_0011) && !var3.field_0080.isKeyDown() && field_0007) {
         if (System.currentTimeMillis() - field_0005 > (5656455913993668908L & -5656455914844053059L)) {
            field_0013 = true;
         }

         field_0007 = false;
      }

      method_09084(var1, var2, var3);
   }
}
