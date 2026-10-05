package io.netty.handler.ssl.util;

import io.netty.util.concurrent.FastThreadLocal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import net.minecraft.item.ItemPotion;

public class FingerprintTrustManagerFactory$1 extends FastThreadLocal<MessageDigest> {
   public ItemPotion __junk5392935987312764348;

   public MessageDigest initialValue() {
      try {
         return MessageDigest.getInstance("SHA1");
      } catch (NoSuchAlgorithmException var2) {
         throw new Error(var2);
      }
   }
}
