package net.optifine.shaders.config;

import io.netty.buffer.PoolThreadCache$SubPageMemoryRegionCache;
import io.netty.channel.nio.SelectedSelectionKeySet;
import io.netty.channel.socket.ChannelInputShutdownEvent;
import net.minecraft.command.server.CommandTestFor;
import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpressionBool;
import org.json.Cookie;

public class ExpressionShaderOptionSwitch implements IExpressionBool {
   public ShaderOptionSwitch shaderOption;
   public PoolThreadCache$SubPageMemoryRegionCache field_0005;
   public CommandTestFor field_0002;
   public ChannelInputShutdownEvent field_0004;
   public Cookie field_0000;
   public SelectedSelectionKeySet field_0001;

   @Override
   public ExpressionType getExpressionType() {
      return ExpressionType.BOOL;
   }

   @Override
   public boolean eval() {
      return ShaderOptionSwitch.isTrue(this.shaderOption.getValue());
   }

   @Override
   public String toString() {
      return "" + this.shaderOption;
   }

   public ExpressionShaderOptionSwitch(ShaderOptionSwitch var1) {
      this.shaderOption = var1;
   }
}
