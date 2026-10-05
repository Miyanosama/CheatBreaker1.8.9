package io.netty.channel.sctp;

import com.sun.nio.sctp.AbstractNotificationHandler;
import com.sun.nio.sctp.AssociationChangeNotification;
import com.sun.nio.sctp.HandlerResult;
import com.sun.nio.sctp.Notification;
import com.sun.nio.sctp.PeerAddressChangeNotification;
import com.sun.nio.sctp.SendFailedNotification;
import com.sun.nio.sctp.ShutdownNotification;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$Traverser;
import net.minecraft.item.ItemSoup;
import net.minecraft.world.gen.layer.GenLayerRiverMix;

public class SctpNotificationHandler extends AbstractNotificationHandler<Object> {
   public GenLayerRiverMix __junk4649070085817493975;
   public ConcurrentHashMapV8$Traverser __junk594765500336558818;
   public ItemSoup __junk9155208042231544002;
   public SctpChannel sctpChannel;

   public SctpNotificationHandler(SctpChannel var1) {
      if (var1 == null) {
         throw new NullPointerException("sctpChannel");
      } else {
         this.sctpChannel = var1;
      }
   }

   public void fireEvent(Notification var1) {
      this.sctpChannel.pipeline().fireUserEventTriggered(var1);
   }

   @Override
   public HandlerResult handleNotification(AssociationChangeNotification var1, Object var2) {
      this.fireEvent(var1);
      return HandlerResult.CONTINUE;
   }

   @Override
   public HandlerResult handleNotification(SendFailedNotification var1, Object var2) {
      this.fireEvent(var1);
      return HandlerResult.CONTINUE;
   }

   @Override
   public HandlerResult handleNotification(ShutdownNotification var1, Object var2) {
      this.fireEvent(var1);
      this.sctpChannel.close();
      return HandlerResult.RETURN;
   }

   @Override
   public HandlerResult handleNotification(PeerAddressChangeNotification var1, Object var2) {
      this.fireEvent(var1);
      return HandlerResult.CONTINUE;
   }
}
