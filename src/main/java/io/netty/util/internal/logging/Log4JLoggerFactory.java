package io.netty.util.internal.logging;

import com.cheatbreaker.client.ui.overlay.friend.FriendRequestElement;
import net.minecraft.enchantment.EnchantmentWaterWalker;
import net.optifine.shaders.SMath;
import org.apache.log4j.Logger;

public class Log4JLoggerFactory extends InternalLoggerFactory {

   @Override
   public InternalLogger newInstance(String var1) {
      return new Log4JLogger(Logger.getLogger(var1));
   }
}
