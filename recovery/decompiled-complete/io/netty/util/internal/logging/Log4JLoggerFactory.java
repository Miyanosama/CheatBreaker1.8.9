package io.netty.util.internal.logging;

import com.cheatbreaker.client.ui.overlay.friend.FriendRequestElement;
import net.minecraft.block.BlockNewLog$2;
import net.minecraft.client.particle.EffectRenderer$2;
import net.minecraft.enchantment.EnchantmentWaterWalker;
import net.optifine.shaders.SMath;
import org.apache.log4j.Logger;

public class Log4JLoggerFactory extends InternalLoggerFactory {
   public SMath __junk6433551095749873394;
   public EnchantmentWaterWalker __junk3497981747210381418;
   public EffectRenderer$2 __junk2802805107513727871;
   public BlockNewLog$2 __junk3955939915913924940;
   public FriendRequestElement __junk7937114314158203790;

   @Override
   public InternalLogger newInstance(String var1) {
      return new Log4JLogger(Logger.getLogger(var1));
   }
}
