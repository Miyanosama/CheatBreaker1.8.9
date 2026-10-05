package net.minecraft.block;

import com.google.common.base.Predicate;
import io.netty.handler.ssl.util.FingerprintTrustManagerFactory$2;
import net.minecraft.client.settings.GameSettings$2;
import net.minecraft.entity.passive.EntityRabbit$AIEvilAttack;
import net.minecraft.util.EnumFacing;
import recovered.unidentified.UnidentifiedClass0156;
import recovered.unidentified.UnidentifiedClass4013;

public class BlockTorch$1 implements Predicate<EnumFacing> {
   public UnidentifiedClass4013 field_0002;
   public FingerprintTrustManagerFactory$2 field_0004;
   public UnidentifiedClass0156 field_0001;
   public GameSettings$2 field_0003;
   public EntityRabbit$AIEvilAttack field_0000;

   public boolean apply(EnumFacing var1) {
      return var1 != EnumFacing.DOWN;
   }
}
