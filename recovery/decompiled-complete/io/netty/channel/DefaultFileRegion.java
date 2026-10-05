package io.netty.channel;

import com.cheatbreaker.client.ui.element.profile.ProfilesListElement;
import io.netty.channel.epoll.Epoll;
import io.netty.util.AbstractReferenceCounted;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import javazoom.jl.decoder.LayerIIDecoder$SubbandLayer2Stereo;
import net.minecraft.client.gui.GuiOptionsRowList;
import net.minecraft.network.play.server.S13PacketDestroyEntities;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor$6;

public class DefaultFileRegion extends AbstractReferenceCounted implements FileRegion {
   public static InternalLogger logger = InternalLoggerFactory.getInstance(DefaultFileRegion.class);
   public long transfered;
   public LayerIIDecoder$SubbandLayer2Stereo __junk858071062760719951;
   public CategoryNodeEditor$6 __junk5735441308124915479;
   public FileChannel file;
   public Epoll __junk7546842118255562920;
   public long count;
   public S3BPacketScoreboardObjective __junk3544320087408405480;
   public S13PacketDestroyEntities __junk7219326824603304797;
   public ProfilesListElement __junk8663108040575754832;
   public GuiOptionsRowList __junk5092535210396281659;
   public long position;

   @Override
   public long position() {
      return this.position;
   }

   @Override
   public void deallocate() {
      try {
         this.file.close();
      } catch (IOException var2) {
         if (logger.isWarnEnabled()) {
            logger.warn("Failed to close a file.", (Throwable)var2);
         }
      }
   }

   @Override
   public long count() {
      return this.count;
   }

   @Override
   public long transfered() {
      return this.transfered;
   }

   @Override
   public long transferTo(WritableByteChannel var1, long var2) {
      long var4 = this.count - var2;
      if (var4 < (471902352L & -9118712584080190904L) || var2 < (-9202592838376744946L & 9202592837236090672L)) {
         throw new IllegalArgumentException("position out of range: " + var2 + " (expected: 0 - " + (this.count - (4510601L & 19406867L)) + ')');
      } else if (var4 == (1409319232L & 689772680L)) {
         return 8025475497809018912L & -8025475498471280064L;
      } else {
         long var6 = this.file.transferTo(this.position + var2, var4, var1);
         if (var6 > (-7799777160379317822L & 7799777159917410313L)) {
            this.transfered += var6;
         }

         return var6;
      }
   }

   public DefaultFileRegion(FileChannel var1, long var2, long var4) {
      if (var1 == null) {
         throw new NullPointerException("file");
      } else if (var2 < (218366339L & 1624382528L)) {
         throw new IllegalArgumentException("position must be >= 0 but was " + var2);
      } else if (var4 < (-2820659751560301816L & 440401953L)) {
         throw new IllegalArgumentException("count must be >= 0 but was " + var4);
      } else {
         this.file = var1;
         this.position = var2;
         this.count = var4;
      }
   }
}
