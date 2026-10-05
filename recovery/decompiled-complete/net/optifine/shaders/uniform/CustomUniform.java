package net.optifine.shaders.uniform;

import io.netty.buffer.PoolThreadCache$MemoryRegionCache$Entry;
import net.minecraft.world.chunk.Chunk;
import net.optifine.expr.IExpression;
import net.optifine.reflect.Reflector;
import net.optifine.shaders.SMCLog;
import recovered.unidentified.UnidentifiedClass4617;

public class CustomUniform {
   public PoolThreadCache$MemoryRegionCache$Entry field_0003;
   public ShaderUniformBase shaderUniform;
   public IExpression expression;
   public UnidentifiedClass4617 field_0005;
   public Reflector field_0000;
   public Chunk field_0001;
   public String name;
   public UniformType type;

   public IExpression getExpression() {
      return this.expression;
   }

   public String getName() {
      return this.name;
   }

   public ShaderUniformBase getShaderUniform() {
      return this.shaderUniform;
   }

   public UniformType getType() {
      return this.type;
   }

   public void reset() {
      this.shaderUniform.reset();
   }

   @Override
   public String toString() {
      return this.type.name().toLowerCase() + " " + this.name;
   }

   public CustomUniform(String var1, UniformType var2, IExpression var3) {
      this.name = var1;
      this.type = var2;
      this.expression = var3;
      this.shaderUniform = var2.makeShaderUniform(var1);
   }

   public void update() {
      if (this.shaderUniform.isDefined()) {
         try {
            this.type.updateUniform(this.expression, this.shaderUniform);
         } catch (RuntimeException var2) {
            SMCLog.severe("Error updating custom uniform: " + this.shaderUniform.getName());
            SMCLog.severe(var2.getClass().getName() + ": " + var2.getMessage());
            this.shaderUniform.disable();
            SMCLog.severe("Custom uniform disabled: " + this.shaderUniform.getName());
         }
      }
   }

   public void setProgram(int var1) {
      this.shaderUniform.setProgram(var1);
   }
}
