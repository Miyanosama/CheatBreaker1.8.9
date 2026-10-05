package io.netty.channel.rxtx;

import com.cheatbreaker.client.util.SessionServer;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import io.netty.channel.AbstractChannel;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultChannelProgressivePromise;
import io.netty.channel.oio.OioByteStreamChannel;
import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.util.RegistryNamespaced;
import net.optifine.util.CompoundKey;
import junit.swingui.AboutDialog$1;
import com.cheatbreaker.client.util.render.LegacyGlStateManager;

public class RxtxChannel extends OioByteStreamChannel {
   public RxtxChannelConfig config;
   public SerialPort serialPort;
   public boolean open = true;
   public RxtxDeviceAddress deviceAddress;
   public static RxtxDeviceAddress LOCAL_ADDRESS = new RxtxDeviceAddress("localhost");

   public RxtxDeviceAddress remoteAddress() {
      return (RxtxDeviceAddress)super.remoteAddress();
   }

   @Override
   public void doDisconnect() throws java.lang.Exception {
      this.doClose();
   }

   @Override
   public AbstractChannel.AbstractUnsafe newUnsafe() {
      return new RxtxChannel.RxtxUnsafe();
   }

   @Override
   public void doBind(SocketAddress var1) throws java.lang.Exception {
      throw new UnsupportedOperationException();
   }

   public void doInit() throws java.lang.Exception {
      this.serialPort
         .setSerialPortParams(
            this.config().getOption(RxtxChannelOption.BAUD_RATE),
            this.config().getOption(RxtxChannelOption.DATA_BITS).value(),
            this.config().getOption(RxtxChannelOption.STOP_BITS).value(),
            this.config().getOption(RxtxChannelOption.PARITY_BIT).value()
         );
      this.serialPort.setDTR(this.config().getOption(RxtxChannelOption.DTR));
      this.serialPort.setRTS(this.config().getOption(RxtxChannelOption.RTS));
      this.activate(this.serialPort.getInputStream(), this.serialPort.getOutputStream());
   }

   public RxtxDeviceAddress remoteAddress0() {
      return this.deviceAddress;
   }

   public RxtxChannel() {
      super(null);
      this.config = new DefaultRxtxChannelConfig(this);
   }

   public RxtxChannelConfig config() {
      return this.config;
   }

   @Override
   public void doClose() throws java.lang.Exception {
      this.open = false;

      try {
         super.doClose();
      } finally {
         if (this.serialPort != null) {
            this.serialPort.removeEventListener();
            this.serialPort.close();
            this.serialPort = null;
         }
      }
   }

   @Override
   public boolean isOpen() {
      return this.open;
   }

   public RxtxDeviceAddress localAddress0() {
      return LOCAL_ADDRESS;
   }

   @Override
   public void doConnect(SocketAddress var1, SocketAddress var2) throws java.lang.Exception {
      RxtxDeviceAddress var3 = (RxtxDeviceAddress)var1;
      CommPortIdentifier var4 = CommPortIdentifier.getPortIdentifier(var3.value());
      CommPort var5 = var4.open(this.getClass().getName(), 1000);
      var5.enableReceiveTimeout(this.config().getOption(RxtxChannelOption.READ_TIMEOUT));
      this.deviceAddress = var3;
      this.serialPort = (SerialPort)var5;
   }

   public RxtxDeviceAddress localAddress() {
      return (RxtxDeviceAddress)super.localAddress();
   }

   public final class RxtxUnsafe extends AbstractChannel.AbstractUnsafe {

      @Override
      public void connect(SocketAddress var1, SocketAddress var2, final ChannelPromise var3) {
         if (var3.setUncancellable() && this.ensureOpen(var3)) {
            try {
               final boolean var4 = RxtxChannel.this.isActive();
               RxtxChannel.this.doConnect(var1, var2);
               int var5 = RxtxChannel.this.config().getOption(RxtxChannelOption.WAIT_TIME);
               if (var5 > 0) {
                  RxtxChannel.this.eventLoop().schedule(new Runnable() {

                     @Override
                     public void run() {
                        try {
                           RxtxChannel.this.doInit();
                           RxtxUnsafe.this.safeSetSuccess(var3);
                           if (!var4 && RxtxChannel.this.isActive()) {
                              RxtxChannel.this.pipeline().fireChannelActive();
                           }
                        } catch (Throwable var2x) {
                           RxtxUnsafe.this.safeSetFailure(var3, var2x);
                           RxtxUnsafe.this.closeIfClosed();
                        }
                     }
                  }, var5, TimeUnit.MILLISECONDS);
               } else {
                  RxtxChannel.this.doInit();
                  this.safeSetSuccess(var3);
                  if (!var4 && RxtxChannel.this.isActive()) {
                     RxtxChannel.this.pipeline().fireChannelActive();
                  }
               }
            } catch (Throwable var6) {
               this.safeSetFailure(var3, var6);
               this.closeIfClosed();
            }
         }
      }

      public RxtxUnsafe() {
      }
   }
}
