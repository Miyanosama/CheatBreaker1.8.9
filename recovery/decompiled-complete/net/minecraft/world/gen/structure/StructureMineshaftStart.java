package net.minecraft.world.gen.structure;

import io.netty.handler.codec.compression.Snappy;
import java.util.Random;
import net.minecraft.world.World;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$13;
import recovered.unidentified.UnidentifiedClass4788;

public class StructureMineshaftStart extends StructureStart {
   public UnidentifiedClass4788 field_0001;
   public Snappy field_0002;
   public LogBrokerMonitor$13 field_0000;

   public StructureMineshaftStart(World var1, Random var2, int var3, int var4) {
      super(var3, var4);
      StructureMineshaftPieces$Room var5 = new StructureMineshaftPieces$Room(0, var2, (var3 << 4) + 2, (var4 << 4) + 2);
      this.a.add(var5);
      var5.buildComponent(var5, this.a, var2);
      this.c();
      this.a(var1, var2, 10);
   }

   public StructureMineshaftStart() {
   }
}
