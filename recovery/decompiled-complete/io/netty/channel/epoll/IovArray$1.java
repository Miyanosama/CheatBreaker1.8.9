package io.netty.channel.epoll;

import io.netty.channel.PendingWriteQueue;
import io.netty.handler.codec.socks.SocksAuthResponse;
import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ValueIterator;
import junit.runner.BaseTestRunner;
import recovered.unidentified.UnidentifiedClass0313;

public class IovArray$1 extends FastThreadLocal<IovArray> {
   public BaseTestRunner __junk8193140147355820358;
   public SocksAuthResponse __junk6270518112142206146;
   public PendingWriteQueue __junk6667607442611896798;
   public UnidentifiedClass0313 __junk4987163830038443664;
   public ConcurrentHashMapV8$ValueIterator __junk5057128794867988721;

   public void onRemoval(IovArray var1) {
      PlatformDependent.freeMemory(IovArray.access$100(var1));
   }

   public IovArray initialValue() {
      return new IovArray(null);
   }
}
