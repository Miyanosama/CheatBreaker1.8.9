package javax.vecmath;

import com.cheatbreaker.client.module.type.cooldowns.CooldownRenderer;
import io.netty.util.internal.MpscLinkedQueueHeadRef;
import net.minecraft.command.CommandBlockData;
import net.minecraft.enchantment.EnchantmentHelper$DamageIterator;
import org.apache.log4j.AppenderSkeleton;

public class MismatchedSizeException extends RuntimeException {
   public EnchantmentHelper$DamageIterator field_0002;
   public CooldownRenderer field_0004;
   public MpscLinkedQueueHeadRef field_0001;
   public CommandBlockData field_0003;
   public AppenderSkeleton field_0000;

   public MismatchedSizeException(String var1) {
      super(var1);
   }

   public MismatchedSizeException() {
   }
}
