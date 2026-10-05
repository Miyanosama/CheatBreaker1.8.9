package io.netty.channel;

import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;
import io.netty.util.internal.StringUtil;
import java.net.SocketAddress;
import com.cheatbreaker.client.util.render.LegacyTessellator;

public class DefaultAddressedEnvelope<M, A extends SocketAddress> implements AddressedEnvelope<M, A> {
   public A sender;
   public M message;
   public A recipient;

   public AddressedEnvelope<M, A> retain(int var1) {
      ReferenceCountUtil.retain(this.message, var1);
      return this;
   }

   @Override
   public A sender() {
      return this.sender;
   }

   @Override
   public String toString() {
      return this.sender != null
         ? StringUtil.simpleClassName(this) + '(' + this.sender + " => " + this.recipient + ", " + this.message + ')'
         : StringUtil.simpleClassName(this) + "(=> " + this.recipient + ", " + this.message + ')';
   }

   public DefaultAddressedEnvelope(M var1, A var2) {
      this((M)var1, (A)var2, null);
   }

   public AddressedEnvelope<M, A> retain() {
      ReferenceCountUtil.retain(this.message);
      return this;
   }

   @Override
   public boolean release(int var1) {
      return ReferenceCountUtil.release(this.message, var1);
   }

   @Override
   public boolean release() {
      return ReferenceCountUtil.release(this.message);
   }

   @Override
   public M content() {
      return this.message;
   }

   public DefaultAddressedEnvelope(M var1, A var2, A var3) {
      if (var1 == null) {
         throw new NullPointerException("message");
      } else {
         this.message = (M)var1;
         this.sender = (A)var3;
         this.recipient = (A)var2;
      }
   }

   @Override
   public A recipient() {
      return this.recipient;
   }

   @Override
   public int refCnt() {
      return this.message instanceof ReferenceCounted ? ((ReferenceCounted)this.message).refCnt() : 1;
   }
}
