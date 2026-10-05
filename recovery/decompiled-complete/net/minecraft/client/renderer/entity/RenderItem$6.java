package net.minecraft.client.renderer.entity;

import com.cheatbreaker.client.event.type.KeepAliveEvent;
import io.netty.handler.traffic.GlobalTrafficShapingHandler$1;
import io.netty.util.concurrent.SingleThreadEventExecutor$1;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.command.CommandHelp;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$Penthouse;

public class RenderItem$6 implements ItemMeshDefinition {
   public PathNavigateGround field_0003;
   public KeepAliveEvent field_0005;
   public CommandHelp field_0002;
   public StructureOceanMonumentPieces$Penthouse field_0004;
   public SingleThreadEventExecutor$1 field_0001;
   public GlobalTrafficShapingHandler$1 field_0006;

   public RenderItem$6(RenderItem var1) {
      this.this$0 = var1;
      super();
   }

   @Override
   public ModelResourceLocation getModelLocation(ItemStack var1) {
      return new ModelResourceLocation("spawn_egg", "inventory");
   }
}
