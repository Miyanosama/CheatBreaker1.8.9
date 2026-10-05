package io.netty.channel;

import io.netty.util.Recycler;
import io.netty.util.Recycler$Handle;
import javax.vecmath.TexCoord2f;
import net.minecraft.client.resources.data.FontMetadataSection;
import org.apache.log4j.config.PropertyPrinter;
import org.slf4j.LoggerFactory;

public class AbstractChannelHandlerContext$WriteTask$1 extends Recycler<AbstractChannelHandlerContext$WriteTask> {
   public LoggerFactory __junk1630062006897433524;
   public PropertyPrinter __junk1291434249463381649;
   public TexCoord2f __junk7937197810858823095;
   public FontMetadataSection __junk6510143227642893149;

   public AbstractChannelHandlerContext$WriteTask newObject(Recycler$Handle var1) {
      return new AbstractChannelHandlerContext$WriteTask(var1, null);
   }
}
