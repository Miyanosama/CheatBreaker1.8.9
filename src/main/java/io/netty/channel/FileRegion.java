package io.netty.channel;

import io.netty.util.ReferenceCounted;
import java.nio.channels.WritableByteChannel;

public interface FileRegion extends ReferenceCounted {
   long position();

   long transferTo(WritableByteChannel var1, long var2) throws java.io.IOException ;

   long count();

   long transfered();
}
