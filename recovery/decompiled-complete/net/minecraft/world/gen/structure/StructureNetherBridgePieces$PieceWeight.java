package net.minecraft.world.gen.structure;

import io.netty.handler.codec.spdy.DefaultSpdySettingsFrame;
import net.minecraft.block.BlockSkull$2;
import net.minecraft.network.status.server.S01PacketPong;
import recovered.unidentified.UnidentifiedClass1349;

public class StructureNetherBridgePieces$PieceWeight {
   public int field_78826_b;
   public S01PacketPong field_0007;
   public boolean field_78825_e;
   public UnidentifiedClass1349 field_0006;
   public int field_78827_c;
   public int field_78824_d;
   public Class<? extends StructureNetherBridgePieces$Piece> weightClass;
   public BlockSkull$2 field_0005;
   public DefaultSpdySettingsFrame field_0002;

   public boolean func_78822_a(int var1) {
      return this.field_78824_d == 0 || this.field_78827_c < this.field_78824_d;
   }

   public boolean func_78823_a() {
      return this.field_78824_d == 0 || this.field_78827_c < this.field_78824_d;
   }

   public StructureNetherBridgePieces$PieceWeight(Class<? extends StructureNetherBridgePieces$Piece> var1, int var2, int var3) {
      this(var1, var2, var3, false);
   }

   public StructureNetherBridgePieces$PieceWeight(Class<? extends StructureNetherBridgePieces$Piece> var1, int var2, int var3, boolean var4) {
      this.weightClass = var1;
      this.field_78826_b = var2;
      this.field_78824_d = var3;
      this.field_78825_e = var4;
   }
}
