package io.netty.channel;

import com.cheatbreaker.client.ui.element.profile.ProfilesListElement;
import io.netty.channel.epoll.Epoll;
import io.netty.util.AbstractReferenceCounted;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import javazoom.jl.decoder.LayerIIDecoder;
import net.minecraft.client.gui.GuiOptionsRowList;
import net.minecraft.network.play.server.S13PacketDestroyEntities;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor$6;

public class DefaultFileRegion extends AbstractReferenceCounted implements FileRegion {
   public static InternalLogger logger = InternalLoggerFactory.getInstance(DefaultFileRegion.class);
   public long transfered;
   public FileChannel file;
   public long count;
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
   public long transferTo(WritableByteChannel var1, long var2) throws java.io.IOException {
      long var4 = this.count - var2;
      if (var4 < 0L || var2 < 0L) {
         throw new IllegalArgumentException("position out of range: " + var2 + " (expected: 0 - " + (this.count - 1L) + ')');
      } else if (var4 == 0L) {
         return 0L;
      } else {
         long var6 = this.file.transferTo(this.position + var2, var4, var1);
         if (var6 > 0L) {
            this.transfered += var6;
         }

         return var6;
      }
   }

   public DefaultFileRegion(FileChannel var1, long var2, long var4) {
      if (var1 == null) {
         throw new NullPointerException("file");
      } else if (var2 < 0L) {
         throw new IllegalArgumentException("position must be >= 0 but was " + var2);
      } else if (var4 < 0L) {
         throw new IllegalArgumentException("count must be >= 0 but was " + var4);
      } else {
         this.file = var1;
         this.position = var2;
         this.count = var4;
      }
   }
}
