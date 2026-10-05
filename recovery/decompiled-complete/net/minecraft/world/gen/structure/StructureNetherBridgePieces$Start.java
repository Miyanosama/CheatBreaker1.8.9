package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import io.netty.channel.nio.AbstractNioChannel$AbstractNioUnsafe$1;
import java.util.List;
import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.optifine.expr.FunctionFloat$1;
import recovered.unidentified.UnidentifiedClass0525;

public class StructureNetherBridgePieces$Start extends StructureNetherBridgePieces$Crossing3 {
   public List<StructureComponent> field_74967_d = Lists.newArrayList();
   public List<StructureNetherBridgePieces$PieceWeight> secondaryWeights;
   public List<StructureNetherBridgePieces$PieceWeight> primaryWeights;
   public FunctionFloat$1 field_0005;
   public StructureNetherBridgePieces$PieceWeight theNetherBridgePieceWeight;
   public UnidentifiedClass0525 field_0001;
   public AbstractNioChannel$AbstractNioUnsafe$1 field_0004;

   public StructureNetherBridgePieces$Start() {
   }

   @Override
   public void readStructureFromNBT(NBTTagCompound var1) {
      super.readStructureFromNBT(var1);
   }

   @Override
   public void writeStructureToNBT(NBTTagCompound var1) {
      super.writeStructureToNBT(var1);
   }

   public StructureNetherBridgePieces$Start(Random var1, int var2, int var3) {
      super(var1, var2, var3);
      this.primaryWeights = Lists.newArrayList();

      for (StructureNetherBridgePieces$PieceWeight var7 : StructureNetherBridgePieces.access$100()) {
         var7.field_78827_c = 0;
         this.primaryWeights.add(var7);
      }

      this.secondaryWeights = Lists.newArrayList();

      for (StructureNetherBridgePieces$PieceWeight var11 : StructureNetherBridgePieces.access$200()) {
         var11.field_78827_c = 0;
         this.secondaryWeights.add(var11);
      }
   }
}
