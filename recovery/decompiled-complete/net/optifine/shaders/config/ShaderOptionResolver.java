package net.optifine.shaders.config;

import com.cheatbreaker.client.module.type.SprintResetCounterModule;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.BlockReed;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$DoubleZRoom;
import net.optifine.ClearWater;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionResolver;
import recovered.unidentified.UnidentifiedClass3405;

public class ShaderOptionResolver implements IExpressionResolver {
   public Map<String, ExpressionShaderOptionSwitch> mapOptions = new HashMap<>();
   public UnidentifiedClass3405 field_0005;
   public StructureOceanMonumentPieces$DoubleZRoom field_0002;
   public WorldGenHugeTrees field_0004;
   public BlockReed field_0000;
   public ClearWater field_0001;
   public SprintResetCounterModule field_0006;

   public ShaderOptionResolver(ShaderOption[] var1) {
      for (int var2 = 0; var2 < var1.length; var2++) {
         ShaderOption var3 = var1[var2];
         if (var3 instanceof ShaderOptionSwitch) {
            ShaderOptionSwitch var4 = (ShaderOptionSwitch)var3;
            ExpressionShaderOptionSwitch var5 = new ExpressionShaderOptionSwitch(var4);
            this.mapOptions.put(var3.getName(), var5);
         }
      }
   }

   @Override
   public IExpression getExpression(String var1) {
      return this.mapOptions.get(var1);
   }
}
