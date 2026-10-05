package io.netty.buffer;

import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.HttpObjectEncoder;
import io.netty.handler.codec.http.websocketx.WebSocketFrameAggregator;
import io.netty.handler.codec.socks.SocksInitRequestDecoder;
import io.netty.util.NetUtil;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import junit.awtui.TestRunner;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.item.EntityMinecartFurnace;
import net.optifine.entity.model.ModelAdapterHeadSkeleton;
import net.optifine.render.RenderEnv;
import org.apache.log4j.helpers.BoundedFIFO;
import org.apache.log4j.pattern.PatternParser;
import org.json.CookieList;
import org.json.JSONPointer;

public interface ByteBufProcessor {
   ByteBufProcessor FIND_NUL = new ByteBufProcessor() {

      @Override
      public boolean process(byte var1) throws java.lang.Exception {
         return var1 != 0;
      }
   };
   ByteBufProcessor FIND_NON_NUL = new ByteBufProcessor() {

      @Override
      public boolean process(byte var1) throws java.lang.Exception {
         return var1 == 0;
      }
   };
   ByteBufProcessor FIND_CR = new ByteBufProcessor() {

      @Override
      public boolean process(byte var1) throws java.lang.Exception {
         return var1 != 13;
      }
   };
   ByteBufProcessor FIND_NON_CR = new ByteBufProcessor() {

      @Override
      public boolean process(byte var1) throws java.lang.Exception {
         return var1 == 13;
      }
   };
   ByteBufProcessor FIND_LF = new ByteBufProcessor() {

      @Override
      public boolean process(byte var1) throws java.lang.Exception {
         return var1 != 10;
      }
   };
   ByteBufProcessor FIND_NON_LF = new ByteBufProcessor() {

      @Override
      public boolean process(byte var1) throws java.lang.Exception {
         return var1 == 10;
      }
   };
   ByteBufProcessor FIND_CRLF = new ByteBufProcessor() {
      @Override
      public boolean process(byte var1) throws java.lang.Exception {
         return var1 != 13 && var1 != 10;
      }
   };
   ByteBufProcessor FIND_NON_CRLF = new ByteBufProcessor() {

      @Override
      public boolean process(byte var1) throws java.lang.Exception {
         return var1 == 13 || var1 == 10;
      }
   };
   ByteBufProcessor FIND_LINEAR_WHITESPACE = new ByteBufProcessor() {

      @Override
      public boolean process(byte var1) throws java.lang.Exception {
         return var1 != 32 && var1 != 9;
      }
   };
   ByteBufProcessor FIND_NON_LINEAR_WHITESPACE = new ByteBufProcessor() {

      @Override
      public boolean process(byte var1) throws java.lang.Exception {
         return var1 == 32 || var1 == 9;
      }
   };

   boolean process(byte var1) throws java.lang.Exception ;
}
