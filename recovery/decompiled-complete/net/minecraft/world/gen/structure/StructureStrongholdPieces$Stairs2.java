package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import io.netty.util.ResourceLeakException;
import java.util.List;
import java.util.Random;
import junit.swingui.DefaultFailureDetailView;
import net.minecraft.client.renderer.entity.layers.LayerMooshroomMushroom;
import net.minecraft.command.CommandShowSeed;
import net.minecraft.util.BlockPos;

public class StructureStrongholdPieces$Stairs2 extends StructureStrongholdPieces$Stairs {
   public DefaultFailureDetailView field_0004;
   public ResourceLeakException field_0000;
   public StructureStrongholdPieces$PortalRoom strongholdPortalRoom;
   public StructureStrongholdPieces$PieceWeight strongholdPieceWeight;
   public CommandShowSeed field_0003;
   public LayerMooshroomMushroom field_0002;
   public List<StructureComponent> field_75026_c = Lists.newArrayList();

   public StructureStrongholdPieces$Stairs2() {
   }

   @Override
   public BlockPos getBoundingBoxCenter() {
      return this.strongholdPortalRoom != null ? this.strongholdPortalRoom.getBoundingBoxCenter() : super.getBoundingBoxCenter();
   }

   public StructureStrongholdPieces$Stairs2(int var1, Random var2, int var3, int var4) {
      super(0, var2, var3, var4);
   }
}
