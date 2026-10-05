package io.netty.handler.ssl.util;

import com.cheatbreaker.client.util.dash.DashPlayer;
import io.netty.util.concurrent.FastThreadLocal;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.Provider;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.TrustManagerFactorySpi;
import net.minecraft.block.BlockMycelium;
import net.minecraft.client.particle.EntityBlockDustFX;
import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraft.client.renderer.texture.TextureClock;
import net.minecraft.util.ObjectIntIdentityMap;
import org.apache.log4j.varia.Roller;

public abstract class SimpleTrustManagerFactory extends TrustManagerFactory {
   public static Provider PROVIDER = new Provider("", 0.0, "") {
      public static final long serialVersionUID = -2680540247105807895L;
   };
   public static FastThreadLocal<SimpleTrustManagerFactory.SimpleTrustManagerFactorySpi> CURRENT_SPI = new FastThreadLocal<SimpleTrustManagerFactory.SimpleTrustManagerFactorySpi>() {

      public SimpleTrustManagerFactory.SimpleTrustManagerFactorySpi initialValue() {
         return new SimpleTrustManagerFactory.SimpleTrustManagerFactorySpi();
      }
   };

   public SimpleTrustManagerFactory() {
      this("");
   }

   public abstract void engineInit(ManagerFactoryParameters var1) throws Exception;

   public abstract TrustManager[] engineGetTrustManagers();

   public SimpleTrustManagerFactory(String var1) {
      super(CURRENT_SPI.get(), PROVIDER, var1);
      CURRENT_SPI.get().init(this);
      CURRENT_SPI.remove();
      if (var1 == null) {
         throw new NullPointerException("name");
      }
   }

   public abstract void engineInit(KeyStore var1) throws Exception;

   public static final class SimpleTrustManagerFactorySpi extends TrustManagerFactorySpi {
      public SimpleTrustManagerFactory parent;

      @Override
      public TrustManager[] engineGetTrustManagers() {
         return this.parent.engineGetTrustManagers();
      }

      @Override
      public void engineInit(ManagerFactoryParameters var1) throws java.security.InvalidAlgorithmParameterException {
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
      public void engineInit(KeyStore var1) throws java.security.KeyStoreException {
         try {
            this.parent.engineInit(var1);
         } catch (KeyStoreException var3) {
            throw var3;
         } catch (Exception var4) {
            throw new KeyStoreException(var4);
         }
      }
   }
}
