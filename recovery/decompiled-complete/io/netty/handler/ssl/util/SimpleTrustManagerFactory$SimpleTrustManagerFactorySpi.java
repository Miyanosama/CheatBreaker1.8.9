package io.netty.handler.ssl.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactorySpi;
import net.minecraft.client.particle.EntityBlockDustFX;
import net.minecraft.client.renderer.texture.TextureClock;

public class SimpleTrustManagerFactory$SimpleTrustManagerFactorySpi extends TrustManagerFactorySpi {
   public EntityBlockDustFX __junk49838962619500204;
   public SimpleTrustManagerFactory parent;
   public TextureClock __junk5611471566663784421;

   @Override
   public TrustManager[] engineGetTrustManagers() {
      return this.parent.engineGetTrustManagers();
   }

   @Override
   public void engineInit(ManagerFactoryParameters var1) {
      try {
         this.parent.engineInit(var1);
      } catch (InvalidAlgorithmParameterException var3) {
         throw var3;
      } catch (Exception var4) {
         throw new InvalidAlgorithmParameterException(var4);
      }
   }

   public void init(SimpleTrustManagerFactory var1) {
      this.parent = var1;
   }

   @Override
   public void engineInit(KeyStore var1) {
      try {
         this.parent.engineInit(var1);
      } catch (KeyStoreException var3) {
         throw var3;
      } catch (Exception var4) {
         throw new KeyStoreException(var4);
      }
   }
}
