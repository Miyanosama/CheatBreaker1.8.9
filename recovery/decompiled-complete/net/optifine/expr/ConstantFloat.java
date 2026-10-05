package net.optifine.expr;

import net.minecraft.block.BlockLadder;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;
import net.optifine.shaders.gui.GuiShaders$1;
import recovered.unidentified.UnidentifiedClass3405;

public class ConstantFloat implements IExpressionFloat {
   public BlockLadder field_0002;
   public float value;
   public GuiShaders$1 field_0001;
   public ChunkRenderDispatcher field_0003;
   public UnidentifiedClass3405 field_0000;

   @Override
   public ExpressionType getExpressionType() {
      return ExpressionType.FLOAT;
   }

   public ConstantFloat(float var1) {
      this.value = var1;
   }

   @Override
   public float eval() {
      return this.value;
   }

   @Override
   public String toString() {
      return "" + this.value;
   }
}
