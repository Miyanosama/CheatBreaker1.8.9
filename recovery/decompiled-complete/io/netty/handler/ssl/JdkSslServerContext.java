package io.netty.handler.ssl;

import com.cheatbreaker.client.module.type.ScoreboardModule;
import com.cheatbreaker.client.ui.fading.AbstractFade;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import java.io.File;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.EncryptedPrivateKeyInfo;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSessionContext;
import net.minecraft.network.play.client.C03PacketPlayer$C06PacketPlayerPosLook;
import net.minecraft.scoreboard.ScoreObjective;
import net.optifine.BlockDir;
import net.optifine.shaders.ShadersTex;
import recovered.unidentified.UnidentifiedClass4951;

public class JdkSslServerContext extends JdkSslContext {
   public ScoreObjective __junk7762064112139472753;
   public C03PacketPlayer$C06PacketPlayerPosLook __junk2984443815931450429;
   public UnidentifiedClass4951 __junk8230740032762791776;
   public ScoreboardModule __junk6223613472461742521;
   public ShadersTex __junk2280579386558904737;
   public List<String> nextProtocols;
   public SSLContext ctx;
   public AbstractFade __junk7477259668353056608;
   public BlockDir __junk3840707766581836543;

   @Override
   public SSLContext context() {
      return this.ctx;
   }

   @Override
   public List<String> nextProtocols() {
      return this.nextProtocols;
   }

   @Override
   public boolean isClient() {
      return false;
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public JdkSslServerContext(File var1, File var2, String var3, Iterable<String> var4, Iterable<String> var5, long var6, long var8) {
      super(var4);
      if (var1 == null) {
         throw new NullPointerException("certChainFile");
      } else if (var2 == null) {
         throw new NullPointerException("keyFile");
      } else {
         if (var3 == null) {
            var3 = "";
         }

         if (var5 != null && var5.iterator().hasNext()) {
            if (!JettyNpnSslEngine.isAvailable()) {
               throw new SSLException("NPN/ALPN unsupported: " + var5);
            }

            ArrayList var10 = new ArrayList();

            for (String var12 : var5) {
               if (var12 == null) {
                  break;
               }

               var10.add(var12);
            }

            this.nextProtocols = Collections.unmodifiableList(var10);
         } else {
            this.nextProtocols = Collections.emptyList();
         }

         String var38 = Security.getProperty("ssl.KeyManagerFactory.algorithm");
         if (var38 == null) {
            var38 = "SunX509";
         }

         try {
            KeyStore var39 = KeyStore.getInstance("JKS");
            var39.load(null, null);
            CertificateFactory var40 = CertificateFactory.getInstance("X.509");
            KeyFactory var13 = KeyFactory.getInstance("RSA");
            KeyFactory var14 = KeyFactory.getInstance("DSA");
            ByteBuf var15 = PemReader.readPrivateKey(var2);
            byte[] var16 = new byte[var15.readableBytes()];
            var15.readBytes(var16).release();
            char[] var17 = var3.toCharArray();
            PKCS8EncodedKeySpec var18 = generateKeySpec(var17, var16);

            PrivateKey var19;
            try {
               var19 = var13.generatePrivate(var18);
            } catch (InvalidKeySpecException var35) {
               var19 = var14.generatePrivate(var18);
            }

            ArrayList var20 = new ArrayList();
            ByteBuf[] var21 = PemReader.readCertificates(var1);
            boolean var34 = false /* VF: Semaphore variable */;

            try {
               var34 = true;

               for (ByteBuf var25 : var21) {
                  var20.add(var40.generateCertificate(new ByteBufInputStream(var25)));
               }

               var34 = false;
            } finally {
               if (var34) {
                  for (ByteBuf var30 : var21) {
                     var30.release();
                  }
               }
            }

            for (ByteBuf var46 : var21) {
               var46.release();
            }

            var39.setKeyEntry("key", var19, var17, var20.toArray(new Certificate[var20.size()]));
            KeyManagerFactory var42 = KeyManagerFactory.getInstance(var38);
            var42.init(var39, var17);
            this.ctx = SSLContext.getInstance("TLS");
            this.ctx.init(var42.getKeyManagers(), null, null);
            SSLSessionContext var44 = this.ctx.getServerSessionContext();
            if (var6 > (-6266481759051572448L & 6266481757312286848L)) {
               var44.setSessionCacheSize((int)Math.min(var6, 2147483647L & 2147483647L));
            }

            if (var8 > (3105371121548001416L & -3105371123500176283L)) {
               var44.setSessionTimeout((int)Math.min(var8, 2147483647L & -1523808008826519553L));
            }
         } catch (Exception var37) {
            throw new SSLException("failed to initialize the server-side SSL context", var37);
         }
      }
   }

   public static PKCS8EncodedKeySpec generateKeySpec(char[] var0, byte[] var1) {
      if (var0 != null && var0.length != 0) {
         EncryptedPrivateKeyInfo var2 = new EncryptedPrivateKeyInfo(var1);
         SecretKeyFactory var3 = SecretKeyFactory.getInstance(var2.getAlgName());
         PBEKeySpec var4 = new PBEKeySpec(var0);
         SecretKey var5 = var3.generateSecret(var4);
         Cipher var6 = Cipher.getInstance(var2.getAlgName());
         var6.init(2, var5, var2.getAlgParameters());
         return var2.getKeySpec(var6);
      } else {
         return new PKCS8EncodedKeySpec(var1);
      }
   }

   public JdkSslServerContext(File var1, File var2, String var3) {
      this(var1, var2, var3, null, null, 604414022L & 406847496L, 3774025473152854032L & 706741612L);
   }

   public JdkSslServerContext(File var1, File var2) {
      this(var1, var2, null);
   }
}
