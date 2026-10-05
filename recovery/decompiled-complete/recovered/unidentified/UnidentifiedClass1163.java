package recovered.unidentified;

import com.cheatbreaker.client.ui.overlay.friend.FriendRequest;
import io.netty.handler.codec.http.HttpContentDecompressor;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import junit.awtui.TestRunner;

public class UnidentifiedClass1163 extends WindowAdapter {
   public Frame field_0001;
   public FriendRequest field_0003;
   public TestRunner field_0000;
   public HttpContentDecompressor field_0002;

   public UnidentifiedClass1163(TestRunner var1, Frame var2) {
      this.field_0000 = var1;
      this.field_0001 = var2;
   }

   public void windowClosing(WindowEvent var1) {
      this.field_0001.dispose();
      System.exit(0);
   }
}
