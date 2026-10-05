package net.minecraft.client;

import io.netty.channel.AbstractChannelHandlerContext$15;
import java.util.concurrent.Callable;
import net.minecraft.block.BlockSilverfish$EnumType$1;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$EntryRoom;

public class Minecraft$18 implements Callable<String> {
   public AbstractChannelHandlerContext$15 field_0003;
   public BlockSilverfish$EnumType$1 field_0000;
   public StructureOceanMonumentPieces$EntryRoom field_0002;

   public String call() {
      return this.field_82887_a.gameSettings.useVbo ? "Yes" : "No";
   }

   public Minecraft$18(Minecraft var1) {
      this.field_82887_a = var1;
      super();
   }
}
