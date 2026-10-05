package net.minecraft.command;

import net.minecraft.network.PacketBuffer;
import recovered.unidentified.UnidentifiedClass4298;

public class CommandBase$CoordinateArg {
   public boolean field_179632_c;
   public double field_179633_a;
   public UnidentifiedClass4298 field_0001;
   public double field_179631_b;
   public PacketBuffer field_0000;

   public double func_179628_a() {
      return this.field_179633_a;
   }

   public boolean func_179630_c() {
      return this.field_179632_c;
   }

   public double func_179629_b() {
      return this.field_179631_b;
   }

   public CommandBase$CoordinateArg(double var1, double var3, boolean var5) {
      this.field_179633_a = var1;
      this.field_179631_b = var3;
      this.field_179632_c = var5;
   }
}
