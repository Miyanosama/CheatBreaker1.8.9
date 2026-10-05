package io.netty.handler.ssl;

import com.cheatbreaker.client.ui.mainmenu.element.TextButtonElement;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import java.io.File;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.TrustManagerFactory;
import javax.security.auth.x500.X500Principal;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.renderer.texture.Stitcher$Slot;
import net.minecraft.command.server.CommandAchievement;

public class JdkSslClientContext extends JdkSslContext {
   public CommandAchievement __junk6601503255234210874;
   public TextButtonElement __junk8648412151918449946;
   public List<String> nextProtocols;
   public SSLContext ctx;
   public EntityBreakingFX __junk8378367813565744065;
   public Stitcher$Slot __junk3532252641597108500;

   @Override
   public boolean isClient() {
      return true;
   }

   public JdkSslClientContext(File var1) {
      this(var1, null);
   }

   @Override
   public SSLContext context() {
      return this.ctx;
   }

   public JdkSslClientContext() {
      this(null, null, null, null, 6325264L & 1376854080L, -4126780637170059252L & 4126780636762490849L);
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public JdkSslClientContext(File var1, TrustManagerFactory var2, Iterable<String> var3, Iterable<String> var4, long var5, long var7) {
      super(var3);
      if (var4 != null && var4.iterator().hasNext()) {
         if (!JettyNpnSslEngine.isAvailable()) {
            throw new SSLException("NPN/ALPN unsupported: " + var4);
         }

         ArrayList var9 = new ArrayList();

         for (String var11 : var4) {
            if (var11 == null) {
               break;
            }

            var9.add(var11);
         }

         this.nextProtocols = Collections.unmodifiableList(var9);
      } else {
         this.nextProtocols = Collections.emptyList();
      }

      try {
         if (var1 == null) {
            this.ctx = SSLContext.getInstance("TLS");
            if (var2 == null) {
               this.ctx.init(null, null, null);
            } else {
               var2.init((KeyStore)null);
               this.ctx.init(null, var2.getTrustManagers(), null);
            }
         } else {
            KeyStore var28 = KeyStore.getInstance("JKS");
            var28.load(null, null);
            CertificateFactory var30 = CertificateFactory.getInstance("X.509");
            ByteBuf[] var31 = PemReader.readCertificates(var1);
            boolean var25 = false /* VF: Semaphore variable */;

            try {
               var25 = true;

               for (ByteBuf var15 : var31) {
                  X509Certificate var16 = (X509Certificate)var30.generateCertificate(new ByteBufInputStream(var15));
                  X500Principal var17 = var16.getSubjectX500Principal();
                  var28.setCertificateEntry(var17.getName("RFC2253"), var16);
               }

               var25 = false;
            } finally {
               if (var25) {
                  for (ByteBuf var22 : var31) {
                     var22.release();
                  }
               }
            }

            for (ByteBuf var35 : var31) {
               var35.release();
            }

            if (var2 == null) {
               var2 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            }

            var2.init(var28);
            this.ctx = SSLContext.getInstance("TLS");
            this.ctx.init(null, var2.getTrustManagers(), null);
         }

         SSLSessionContext var29 = this.ctx.getClientSessionContext();
         if (var5 > (437263409L & 1691255554L)) {
            var29.setSessionCacheSize((int)Math.min(var5, -865989960925185L & 865992108408831L));
         }

         if (var7 > (541065474L & 1083752456L)) {
            var29.setSessionTimeout((int)Math.min(var7, 2147483647L & 2147483647L));
         }
      } catch (Exception var27) {
         throw new SSLException("failed to initialize the server-side SSL context", var27);
      }
   }

   public JdkSslClientContext(TrustManagerFactory var1) {
      this(null, var1);
   }

   public JdkSslClientContext(File var1, TrustManagerFactory var2) {
      this(var1, var2, null, null, -5030133766400243640L & 1143185940L, -3153230944940709616L & 3153230944421302888L);
   }

   @Override
   public List<String> nextProtocols() {
      return this.nextProtocols;
   }
}
