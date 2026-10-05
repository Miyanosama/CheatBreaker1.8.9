package net.minecraft.world.gen.structure;

import io.netty.util.internal.MpscLinkedQueueNode;
import io.netty.util.internal.chmv8.ForkJoinTask;
import java.util.List;
import java.util.Random;
import net.minecraft.client.model.ModelRabbit;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class MapGenNetherBridge$Start extends StructureStart {
   public MpscLinkedQueueNode field_0001;
   public TileEntityChest field_0003;
   public ForkJoinTask field_0000;
   public ModelRabbit field_0002;

   public MapGenNetherBridge$Start() {
   }

   public MapGenNetherBridge$Start(World var1, Random var2, int var3, int var4) {
      super(var3, var4);
      StructureNetherBridgePieces$Start var5 = new StructureNetherBridgePieces$Start(var2, (var3 << 4) + 2, (var4 << 4) + 2);
      this.a.add(var5);
      var5.buildComponent(var5, this.a, var2);
      List var6 = var5.field_74967_d;

      while (!var6.isEmpty()) {
         int var7 = var2.nextInt(var6.size());
         StructureComponent var8 = (StructureComponent)var6.remove(var7);
         var8.buildComponent(var5, this.a, var2);
      }

      this.c();
      this.setRandomHeight(var1, var2, 48, 70);
   }
}
