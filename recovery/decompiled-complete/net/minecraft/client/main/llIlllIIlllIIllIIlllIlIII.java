package net.minecraft.client.main;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$SearchMappingsTask;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import net.optifine.shaders.config.MacroExpressionResolver;

public class llIlllIIlllIIllIIlllIlIII extends Authenticator {
   public MacroExpressionResolver field_0003;
   public ConcurrentHashMapV8$SearchMappingsTask field_0002;

   public llIlllIIlllIIllIIlllIlIII(String var1, String var2) {
      this.field_0000 = var1;
      this.field_0001 = var2;
      super();
   }

   @Override
   public PasswordAuthentication getPasswordAuthentication() {
      return new PasswordAuthentication(this.field_0000, this.field_0001.toCharArray());
   }
}
