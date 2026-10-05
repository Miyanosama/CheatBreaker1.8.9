package net.optifine.shaders.uniform;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$PieceWeight;

public class ShaderUniforms {
   public List<ShaderUniformBase> listUniforms = new ArrayList<>();
   public StructureStrongholdPieces$PieceWeight field_0001;

   public ShaderUniform1f make1f(String var1) {
      ShaderUniform1f var2 = new ShaderUniform1f(var1);
      this.listUniforms.add(var2);
      return var2;
   }

   public ShaderUniform4i make4i(String var1) {
      ShaderUniform4i var2 = new ShaderUniform4i(var1);
      this.listUniforms.add(var2);
      return var2;
   }

   public ShaderUniformM4 makeM4(String var1) {
      ShaderUniformM4 var2 = new ShaderUniformM4(var1);
      this.listUniforms.add(var2);
      return var2;
   }

   public ShaderUniform3f make3f(String var1) {
      ShaderUniform3f var2 = new ShaderUniform3f(var1);
      this.listUniforms.add(var2);
      return var2;
   }

   public void reset() {
      for (int var1 = 0; var1 < this.listUniforms.size(); var1++) {
         ShaderUniformBase var2 = this.listUniforms.get(var1);
         var2.reset();
      }
   }

   public ShaderUniform1i make1i(String var1) {
      ShaderUniform1i var2 = new ShaderUniform1i(var1);
      this.listUniforms.add(var2);
      return var2;
   }

   public void setProgram(int var1) {
      for (int var2 = 0; var2 < this.listUniforms.size(); var2++) {
         ShaderUniformBase var3 = this.listUniforms.get(var2);
         var3.setProgram(var1);
      }
   }

   public ShaderUniform2i make2i(String var1) {
      ShaderUniform2i var2 = new ShaderUniform2i(var1);
      this.listUniforms.add(var2);
      return var2;
   }

   public ShaderUniform4f make4f(String var1) {
      ShaderUniform4f var2 = new ShaderUniform4f(var1);
      this.listUniforms.add(var2);
      return var2;
   }
}
