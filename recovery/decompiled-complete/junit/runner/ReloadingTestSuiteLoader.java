package junit.runner;

import com.cheatbreaker.client.util.friend.Friend;
import io.netty.handler.codec.rtsp.RtspMethods;
import net.minecraft.client.stream.BroadcastController$3;

public class ReloadingTestSuiteLoader implements TestSuiteLoader {
   public BroadcastController$3 field_0001;
   public Friend field_0002;
   public RtspMethods field_0000;

   public TestCaseClassLoader createLoader() {
      return new TestCaseClassLoader();
   }

   public Class reload(Class var1) {
      return this.createLoader().loadClass(var1.getName(), true);
   }

   public Class load(String var1) {
      return this.createLoader().loadClass(var1, true);
   }
}
