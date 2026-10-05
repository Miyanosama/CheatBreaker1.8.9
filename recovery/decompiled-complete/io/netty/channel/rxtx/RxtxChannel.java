package io.netty.channel.rxtx;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import io.netty.channel.AbstractChannel$AbstractUnsafe;
import io.netty.channel.oio.OioByteStreamChannel;
import java.net.SocketAddress;

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
   public void doDisconnect() {
      this.doClose();
   }

   @Override
   public AbstractChannel$AbstractUnsafe newUnsafe() {
      return new RxtxChannel$RxtxUnsafe(this, null);
   }

   @Override
   public void doBind(SocketAddress var1) {
      throw new UnsupportedOperationException();
   }

   public void doInit() {
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
   public void doClose() {
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
   public void doConnect(SocketAddress var1, SocketAddress var2) {
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
}
