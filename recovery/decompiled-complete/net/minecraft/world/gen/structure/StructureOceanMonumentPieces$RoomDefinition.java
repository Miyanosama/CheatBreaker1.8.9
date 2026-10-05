package net.minecraft.world.gen.structure;

import javazoom.jl.player.AudioDeviceFactory;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.command.CommandExecuteAt;
import net.minecraft.potion.Potion;
import net.minecraft.realms.RealmsScreen;
import net.minecraft.util.EnumFacing;
import net.optifine.player.CapeImageBuffer;
import net.optifine.reflect.ReflectorResolver;

public class StructureOceanMonumentPieces$RoomDefinition {
   public RealmsScreen field_0005;
   public CommandExecuteAt field_0010;
   public AudioDeviceFactory field_0004;
   public boolean field_175964_e;
   public boolean[] field_175966_c;
   public ReflectorResolver field_0002;
   public GuiChest field_0011;
   public int field_175967_a;
   public int field_175962_f;
   public StructureOceanMonumentPieces$RoomDefinition[] field_175965_b = new StructureOceanMonumentPieces$RoomDefinition[6];
   public boolean field_175963_d;
   public CapeImageBuffer field_0006;
   public Potion field_0007;

   public boolean func_175961_b() {
      return this.field_175967_a >= 75;
   }

   public int func_175960_c() {
      int var1 = 0;

      for (int var2 = 0; var2 < 6; var2++) {
         if (this.field_175966_c[var2]) {
            var1++;
         }
      }

      return var1;
   }

   public boolean func_175959_a(int var1) {
      if (this.field_175964_e) {
         return true;
      } else {
         this.field_175962_f = var1;

         for (int var2 = 0; var2 < 6; var2++) {
            if (this.field_175965_b[var2] != null
               && this.field_175966_c[var2]
               && this.field_175965_b[var2].field_175962_f != var1
               && this.field_175965_b[var2].func_175959_a(var1)) {
               return true;
            }
         }

         return false;
      }
   }

   public void func_175958_a() {
      for (int var1 = 0; var1 < 6; var1++) {
         this.field_175966_c[var1] = this.field_175965_b[var1] != null;
      }
   }

   public void func_175957_a(EnumFacing var1, StructureOceanMonumentPieces$RoomDefinition var2) {
      this.field_175965_b[var1.getIndex()] = var2;
      var2.field_175965_b[var1.getOpposite().getIndex()] = this;
   }

   public StructureOceanMonumentPieces$RoomDefinition(int var1) {
      this.field_175966_c = new boolean[6];
      this.field_175967_a = var1;
   }
}
