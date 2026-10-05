package net.minecraft.world.gen.structure;

import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandshakeHandler$1;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockSign;
import net.minecraft.client.resources.data.LanguageMetadataSection;
import net.minecraft.init.Blocks;
import net.minecraft.realms.RealmsClickableScrolledSelectionList;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.apache.log4j.pattern.SequenceNumberPatternConverter;

public class StructureStrongholdPieces$Prison extends StructureStrongholdPieces$Stronghold {
   public BlockSign field_0002;
   public LanguageMetadataSection field_0004;
   public WebSocketClientProtocolHandshakeHandler$1 field_0001;
   public RealmsClickableScrolledSelectionList field_0003;
   public SequenceNumberPatternConverter field_0000;

   public StructureStrongholdPieces$Prison() {
   }

   public static StructureStrongholdPieces$Prison func_175860_a(
      List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5, int var6
   ) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, -1, 0, 9, 5, 11, var5);
      return canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(var0, var7) == null
         ? new StructureStrongholdPieces$Prison(var6, var1, var7, var5)
         : null;
   }

   public StructureStrongholdPieces$Prison(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
      super(var1);
      this.m = var4;
      this.d = this.a(var2);
      this.l = var3;
   }

   @Override
   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if (this.a(var1, var3)) {
         return false;
      } else {
         this.a(var1, var3, 0, 0, 0, 8, 4, 10, true, var2, StructureStrongholdPieces.access$200());
         this.a(var1, var2, var3, this.d, 1, 1, 0);
         this.a(var1, var3, 1, 1, 10, 3, 3, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 4, 1, 1, 4, 3, 1, false, var2, StructureStrongholdPieces.access$200());
         this.a(var1, var3, 4, 1, 3, 4, 3, 3, false, var2, StructureStrongholdPieces.access$200());
         this.a(var1, var3, 4, 1, 7, 4, 3, 7, false, var2, StructureStrongholdPieces.access$200());
         this.a(var1, var3, 4, 1, 9, 4, 3, 9, false, var2, StructureStrongholdPieces.access$200());
         this.a(var1, var3, 4, 1, 4, 4, 3, 6, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
         this.a(var1, var3, 5, 1, 5, 7, 3, 5, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
         this.a(var1, Blocks.iron_bars.getDefaultState(), 4, 3, 2, var3);
         this.a(var1, Blocks.iron_bars.getDefaultState(), 4, 3, 8, var3);
         this.a(var1, Blocks.iron_door.getStateFromMeta(this.a(Blocks.iron_door, 3)), 4, 1, 2, var3);
         this.a(var1, Blocks.iron_door.getStateFromMeta(this.a(Blocks.iron_door, 3) + 8), 4, 2, 2, var3);
         this.a(var1, Blocks.iron_door.getStateFromMeta(this.a(Blocks.iron_door, 3)), 4, 1, 8, var3);
         this.a(var1, Blocks.iron_door.getStateFromMeta(this.a(Blocks.iron_door, 3) + 8), 4, 2, 8, var3);
         return true;
      }
   }

   @Override
   public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
      this.a((StructureStrongholdPieces$Stairs2)var1, var2, var3, 1, 1);
   }
}
