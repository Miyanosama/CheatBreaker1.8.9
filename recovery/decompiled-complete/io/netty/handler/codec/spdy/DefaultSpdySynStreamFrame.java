package io.netty.handler.codec.spdy;

import com.cheatbreaker.client.ui.overlay.element.PrivateMessageElement;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import net.minecraft.command.server.CommandPardonIp;
import net.minecraft.network.play.server.S3CPacketUpdateScore$Action;
import net.minecraft.world.gen.ChunkProviderSettings$Serializer;
import org.apache.log4j.lf5.Log4JLogRecord;

public class DefaultSpdySynStreamFrame extends DefaultSpdyHeadersFrame implements SpdySynStreamFrame {
   public Log4JLogRecord __junk8471572910475682660;
   public int associatedStreamId;
   public S3CPacketUpdateScore$Action __junk3154244105402057571;
   public ChunkProviderSettings$Serializer __junk2467976294722838033;
   public CommandPardonIp __junk5273149703279433985;
   public PrivateMessageElement __junk3876981952177189118;
   public boolean unidirectional;
   public byte priority;
   public PlatformDependent __junk1369971559543446410;

   public DefaultSpdySynStreamFrame(int var1, int var2, byte var3) {
      super(var1);
      this.setAssociatedStreamId(var2);
      this.setPriority(var3);
   }

   @Override
   public SpdySynStreamFrame setAssociatedStreamId(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("Associated-To-Stream-ID cannot be negative: " + var1);
      } else {
         this.associatedStreamId = var1;
         return this;
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(StringUtil.simpleClassName(this));
      var1.append("(last: ");
      var1.append(this.isLast());
      var1.append("; unidirectional: ");
      var1.append(this.isUnidirectional());
      var1.append(')');
      var1.append(StringUtil.NEWLINE);
      var1.append("--> Stream-ID = ");
      var1.append(this.streamId());
      var1.append(StringUtil.NEWLINE);
      if (this.associatedStreamId != 0) {
         var1.append("--> Associated-To-Stream-ID = ");
         var1.append(this.associatedStreamId());
         var1.append(StringUtil.NEWLINE);
      }

      var1.append("--> Priority = ");
      var1.append(this.priority());
      var1.append(StringUtil.NEWLINE);
      var1.append("--> Headers:");
      var1.append(StringUtil.NEWLINE);
      this.appendHeaders(var1);
      var1.setLength(var1.length() - StringUtil.NEWLINE.length());
      return var1.toString();
   }

   @Override
   public SpdySynStreamFrame setInvalid() {
      super.setInvalid();
      return this;
   }

   @Override
   public boolean isUnidirectional() {
      return this.unidirectional;
   }

   @Override
   public int associatedStreamId() {
      return this.associatedStreamId;
   }

   @Override
   public SpdySynStreamFrame setPriority(byte var1) {
      if (var1 >= 0 && var1 <= 7) {
         this.priority = var1;
         return this;
      } else {
         throw new IllegalArgumentException("Priority must be between 0 and 7 inclusive: " + var1);
      }
   }

   @Override
   public SpdySynStreamFrame setStreamId(int var1) {
      super.setStreamId(var1);
      return this;
   }

   @Override
   public SpdySynStreamFrame setLast(boolean var1) {
      super.setLast(var1);
      return this;
   }

   @Override
   public SpdySynStreamFrame setUnidirectional(boolean var1) {
      this.unidirectional = var1;
      return this;
   }

   @Override
   public byte priority() {
      return this.priority;
   }
}
