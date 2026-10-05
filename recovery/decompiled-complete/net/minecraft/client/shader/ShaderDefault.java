package net.minecraft.client.shader;

import com.cheatbreaker.client.module.AbstractModule$PreviewType;
import com.jagrosh.discordipc.IPCClient$Event;
import io.netty.handler.codec.DecoderException;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.village.Village$VillageAggressor;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$DoubleYZRoom;
import org.lwjgl.util.vector.Matrix4f;

public class ShaderDefault extends ShaderUniform {
   public AbstractModule$PreviewType field_0003;
   public DecoderException field_0005;
   public StructureOceanMonumentPieces$DoubleYZRoom field_0002;
   public Village$VillageAggressor field_0004;
   public IPCClient$Event field_0000;
   public BlockOldLeaf field_0001;

   @Override
   public void set(float var1, float var2, float var3, float var4) {
   }

   @Override
   public void set(float var1) {
   }

   @Override
   public void set(
      float var1,
      float var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11,
      float var12,
      float var13,
      float var14,
      float var15,
      float var16
   ) {
   }

   @Override
   public void set(float var1, float var2) {
   }

   public ShaderDefault() {
      super("dummy", 4, 1, (ShaderManager)null);
   }

   @Override
   public void set(Matrix4f var1) {
   }

   @Override
   public void set(int var1, int var2, int var3, int var4) {
   }

   @Override
   public void set(float var1, float var2, float var3) {
   }

   @Override
   public void func_148092_b(float var1, float var2, float var3, float var4) {
   }

   @Override
   public void set(float[] var1) {
   }
}
