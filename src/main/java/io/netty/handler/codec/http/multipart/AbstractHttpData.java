package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelException;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.handler.codec.socks.UnknownSocksRequest;
import io.netty.handler.ssl.util.SimpleTrustManagerFactory;
import io.netty.util.AbstractReferenceCounted;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.regex.Pattern;
import net.minecraft.block.BlockRedstoneLight;
import net.minecraft.item.ItemLeaves;

public abstract class AbstractHttpData extends AbstractReferenceCounted implements HttpData {
   public String name;
   public boolean completed;
   public static Pattern STRIP_PATTERN = Pattern.compile("(?:^\\s+|\\s+$|\\n)");
   public long size;
   public long definedSize;
   public static Pattern REPLACE_PATTERN = Pattern.compile("[\\r\\t]");
   public Charset charset = HttpConstants.DEFAULT_CHARSET;

   @Override
   public void setCharset(Charset var1) {
      if (var1 == null) {
         throw new NullPointerException("charset");
      } else {
         this.charset = var1;
      }
   }

   @Override
   public Charset getCharset() {
      return this.charset;
   }

   @Override
   public HttpData retain() {
      super.retain();
      return this;
   }

   @Override
   public ByteBuf content() {
      try {
         return this.getByteBuf();
      } catch (IOException var2) {
         throw new ChannelException(var2);
      }
   }

   @Override
   public boolean isCompleted() {
      return this.completed;
   }

   public AbstractHttpData(String var1, Charset var2, long var3) {
      if (var1 == null) {
         throw new NullPointerException("name");
      } else {
         var1 = REPLACE_PATTERN.matcher(var1).replaceAll(" ");
         var1 = STRIP_PATTERN.matcher(var1).replaceAll("");
         if (var1.isEmpty()) {
            throw new IllegalArgumentException("empty name");
         } else {
            this.name = var1;
            if (var2 != null) {
               this.setCharset(var2);
            }

            this.definedSize = var3;
         }
      }
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public void deallocate() {
      this.delete();
   }

   @Override
   public HttpData retain(int var1) {
      super.retain(var1);
      return this;
   }

   @Override
   public long length() {
      return this.size;
   }
}
