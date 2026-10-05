package org.java_websocket.drafts;

import java.math.BigInteger;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.enums.CloseHandshakeType;
import org.java_websocket.enums.HandshakeState;
import org.java_websocket.enums.Opcode;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.enums.Role;
import org.java_websocket.exceptions.IncompleteException;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.exceptions.LimitExceededException;
import org.java_websocket.exceptions.NotSendableException;
import org.java_websocket.extensions.DefaultExtension;
import org.java_websocket.extensions.IExtension;
import org.java_websocket.framing.BinaryFrame;
import org.java_websocket.framing.CloseFrame;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.FramedataImpl1;
import org.java_websocket.framing.TextFrame;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.HandshakeBuilder;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.protocols.IProtocol;
import org.java_websocket.protocols.Protocol;
import org.java_websocket.util.Base64;
import org.java_websocket.util.Charsetfunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Draft_6455 extends Draft {
   public static final String recoveredField3254 = "Connection";
   public static final String recoveredField3255 = "Sec-WebSocket-Key";
   public Random recoveredField3256;
   public static final String recoveredField3257 = "Sec-WebSocket-Extensions";
   public Logger log = LoggerFactory.getLogger(Draft_6455.class);
   public static final String recoveredField3258 = "Sec-WebSocket-Accept";
   public static final String recoveredField3259 = "Upgrade";
   public int maxFrameSize;
   public static final String recoveredField3260 = "Sec-WebSocket-Protocol";
   public List<ByteBuffer> byteBufferList;
   public List<IProtocol> knownProtocols;
   public ByteBuffer incompleteframe;
   public IProtocol protocol;
   public Framedata currentContinuousFrame;
   public List<IExtension> knownExtensions;
   public static final boolean $assertionsDisabled = !Draft_6455.class.desiredAssertionStatus();
   public IExtension extension = new DefaultExtension();

   public Draft_6455.TranslatedPayloadMetaData translateSingleFramePayloadLength(ByteBuffer var1, Opcode var2, int var3, int var4, int var5) throws org.java_websocket.exceptions.InvalidFrameException, org.java_websocket.exceptions.IncompleteException, org.java_websocket.exceptions.LimitExceededException {
      if (var2 != Opcode.PING && var2 != Opcode.PONG && var2 != Opcode.CLOSING) {
         int var6;
         int var7;
         if (var3 == 126) {
            var7 = var5 + 2;
            this.translateSingleFrameCheckPacketSize(var4, var7);
            byte[] var8 = new byte[]{0, var1.get(), var1.get()};
            var6 = new BigInteger(var8).intValue();
         } else {
            var7 = var5 + 8;
            this.translateSingleFrameCheckPacketSize(var4, var7);
            byte[] var11 = new byte[8];

            for (int var9 = 0; var9 < 8; var9++) {
               var11[var9] = var1.get();
            }

            long var12 = new BigInteger(var11).longValue();
            this.translateSingleFrameCheckLengthLimit(var12);
            var6 = (int)var12;
         }

         return new Draft_6455.TranslatedPayloadMetaData(var6, var7);
      } else {
         this.log.trace("Invalid frame: more than 125 octets");
         throw new InvalidFrameException("more than 125 octets");
      }
   }

   @Override
   public HandshakeState acceptHandshakeAsClient(ClientHandshake var1, ServerHandshake var2) throws org.java_websocket.exceptions.InvalidHandshakeException {
      if (!this.basicAccept(var2)) {
         this.log.trace("acceptHandshakeAsClient - Missing/wrong upgrade or connection in handshake.");
         return HandshakeState.NOT_MATCHED;
      } else if (var1.hasFieldValue("Sec-WebSocket-Key") && var2.hasFieldValue("Sec-WebSocket-Accept")) {
         String var3 = var2.getFieldValue("Sec-WebSocket-Accept");
         String var4 = var1.getFieldValue("Sec-WebSocket-Key");
         var4 = this.generateFinalKey(var4);
         if (!var4.equals(var3)) {
            this.log.trace("acceptHandshakeAsClient - Wrong key for Sec-WebSocket-Key.");
            return HandshakeState.NOT_MATCHED;
         } else {
            HandshakeState var5 = HandshakeState.NOT_MATCHED;
            String var6 = var2.getFieldValue("Sec-WebSocket-Extensions");

            for (IExtension var8 : this.knownExtensions) {
               if (var8.acceptProvidedExtensionAsClient(var6)) {
                  this.extension = var8;
                  var5 = HandshakeState.MATCHED;
                  this.log.trace("acceptHandshakeAsClient - Matching extension found: {}", this.extension);
                  break;
               }
            }

            HandshakeState var10 = this.containsRequestedProtocol(var2.getFieldValue("Sec-WebSocket-Protocol"));
            if (var10 == HandshakeState.MATCHED && var5 == HandshakeState.MATCHED) {
               return HandshakeState.MATCHED;
            } else {
               this.log.trace("acceptHandshakeAsClient - No matching extension or protocol found.");
               return HandshakeState.NOT_MATCHED;
            }
         }
      } else {
         this.log.trace("acceptHandshakeAsClient - Missing Sec-WebSocket-Key or Sec-WebSocket-Accept");
         return HandshakeState.NOT_MATCHED;
      }
   }

   public void processFrameText(WebSocketImpl var1, Framedata var2) throws org.java_websocket.exceptions.InvalidDataException {
      try {
         var1.getWebSocketListener().onWebsocketMessage(var1, Charsetfunctions.stringUtf8(var2.getPayloadData()));
      } catch (RuntimeException var4) {
         this.logRuntimeException(var1, var4);
      }
   }

   public void logRuntimeException(WebSocketImpl var1, RuntimeException var2) {
      this.log.error("Runtime exception during onWebsocketMessage", (Throwable)var2);
      var1.getWebSocketListener().onWebsocketError(var1, var2);
   }

   public Draft_6455(List<IExtension> var1, List<IProtocol> var2, int var3) {
      this.recoveredField3256 = new Random();
      if (var1 != null && var2 != null && var3 >= 1) {
         this.knownExtensions = new ArrayList<>(var1.size());
         this.knownProtocols = new ArrayList<>(var2.size());
         boolean var4 = false;
         this.byteBufferList = new ArrayList<>();

         for (IExtension var6 : var1) {
            if (var6.getClass().equals(DefaultExtension.class)) {
               var4 = true;
            }
         }

         this.knownExtensions.addAll(var1);
         if (!var4) {
            this.knownExtensions.add(this.knownExtensions.size(), this.extension);
         }

         this.knownProtocols.addAll(var2);
         this.maxFrameSize = var3;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public void translateSingleFrameCheckPacketSize(int var1, int var2) throws org.java_websocket.exceptions.IncompleteException {
      if (var1 < var2) {
         this.log.trace("Incomplete frame: maxpacketsize < realpacketsize");
         throw new IncompleteException(var2);
      }
   }

   public List<IExtension> getKnownExtensions() {
      return this.knownExtensions;
   }

   @Override
   public int hashCode() {
      int var1 = this.extension != null ? this.extension.hashCode() : 0;
      var1 = 31 * var1 + (this.protocol != null ? this.protocol.hashCode() : 0);
      return 31 * var1 + (this.maxFrameSize ^ this.maxFrameSize >>> 32);
   }

   public byte fromOpcode(Opcode var1) {
      if (var1 == Opcode.CONTINUOUS) {
         return 0;
      } else if (var1 == Opcode.TEXT) {
         return 1;
      } else if (var1 == Opcode.BINARY) {
         return 2;
      } else if (var1 == Opcode.CLOSING) {
         return 8;
      } else if (var1 == Opcode.PING) {
         return 9;
      } else if (var1 == Opcode.PONG) {
         return 10;
      } else {
         throw new IllegalArgumentException("Don't know how to handle " + var1.toString());
      }
   }

   public int getSizeBytes(ByteBuffer var1) {
      if (var1.remaining() <= 125) {
         return 1;
      } else {
         return var1.remaining() <= 65535 ? 2 : 8;
      }
   }

   public Opcode toOpcode(byte var1) throws org.java_websocket.exceptions.InvalidFrameException {
      switch (var1) {
         case 0:
            return Opcode.CONTINUOUS;
         case 1:
            return Opcode.TEXT;
         case 2:
            return Opcode.BINARY;
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         default:
            throw new InvalidFrameException("Unknown opcode " + var1);
         case 8:
            return Opcode.CLOSING;
         case 9:
            return Opcode.PING;
         case 10:
            return Opcode.PONG;
      }
   }

   public String generateFinalKey(String var1) {
      String var2 = var1.trim();
      String var3 = var2 + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";

      MessageDigest var4;
      try {
         var4 = MessageDigest.getInstance("SHA1");
      } catch (NoSuchAlgorithmException var6) {
         throw new IllegalStateException(var6);
      }

      return Base64.encodeBytes(var4.digest(var3.getBytes()));
   }

   public void addToBufferList(ByteBuffer var1) {
      synchronized (this.byteBufferList) {
         this.byteBufferList.add(var1);
      }
   }

   public void processFrameIsNotFin(Framedata var1) throws org.java_websocket.exceptions.InvalidDataException {
      if (this.currentContinuousFrame != null) {
         this.log.trace("Protocol error: Previous continuous frame sequence not completed.");
         throw new InvalidDataException(1002, "Previous continuous frame sequence not completed.");
      } else {
         this.currentContinuousFrame = var1;
         this.addToBufferList(var1.getPayloadData());
         this.checkBufferLimit();
      }
   }

   public void processFrameIsFin(WebSocketImpl var1, Framedata var2) throws org.java_websocket.exceptions.InvalidDataException {
      if (this.currentContinuousFrame == null) {
         this.log.trace("Protocol error: Previous continuous frame sequence not completed.");
         throw new InvalidDataException(1002, "Continuous frame sequence was not started.");
      } else {
         this.addToBufferList(var2.getPayloadData());
         this.checkBufferLimit();
         if (this.currentContinuousFrame.getOpcode() == Opcode.TEXT) {
            ((FramedataImpl1)this.currentContinuousFrame).setPayload(this.getPayloadFromByteBufferList());
            ((FramedataImpl1)this.currentContinuousFrame).isValid();

            try {
               var1.getWebSocketListener().onWebsocketMessage(var1, Charsetfunctions.stringUtf8(this.currentContinuousFrame.getPayloadData()));
            } catch (RuntimeException var5) {
               this.logRuntimeException(var1, var5);
            }
         } else if (this.currentContinuousFrame.getOpcode() == Opcode.BINARY) {
            ((FramedataImpl1)this.currentContinuousFrame).setPayload(this.getPayloadFromByteBufferList());
            ((FramedataImpl1)this.currentContinuousFrame).isValid();

            try {
               var1.getWebSocketListener().onWebsocketMessage(var1, this.currentContinuousFrame.getPayloadData());
            } catch (RuntimeException var4) {
               this.logRuntimeException(var1, var4);
            }
         }

         this.currentContinuousFrame = null;
         this.clearBufferList();
      }
   }

   public HandshakeState containsRequestedProtocol(String var1) {
      for (IProtocol var3 : this.knownProtocols) {
         if (var3.acceptProvidedProtocol(var1)) {
            this.protocol = var3;
            this.log.trace("acceptHandshake - Matching protocol found: {}", this.protocol);
            return HandshakeState.MATCHED;
         }
      }

      return HandshakeState.NOT_MATCHED;
   }

   public long getByteBufferListSize() {
      long var1 = 0L;
      synchronized (this.byteBufferList) {
         for (ByteBuffer var5 : this.byteBufferList) {
            var1 += var5.limit();
         }

         return var1;
      }
   }

   public int getMaxFrameSize() {
      return this.maxFrameSize;
   }

   @Override
   public CloseHandshakeType getCloseHandshakeType() {
      return CloseHandshakeType.TWOWAY;
   }

   public void processFrameClosing(WebSocketImpl var1, Framedata var2) {
      int var3 = 1005;
      String var4 = "";
      if (var2 instanceof CloseFrame) {
         CloseFrame var5 = (CloseFrame)var2;
         var3 = var5.getCloseCode();
         var4 = var5.getMessage();
      }

      if (var1.getReadyState() == ReadyState.CLOSING) {
         var1.closeConnection(var3, var4, true);
      } else if (this.getCloseHandshakeType() == CloseHandshakeType.TWOWAY) {
         var1.close(var3, var4, true);
      } else {
         var1.flushAndClose(var3, var4, false);
      }
   }

   @Override
   public List<Framedata> createFrames(String var1, boolean var2) {
      TextFrame var3 = new TextFrame();
      var3.setPayload(ByteBuffer.wrap(Charsetfunctions.utf8Bytes(var1)));
      var3.setTransferemasked(var2);

      try {
         var3.isValid();
      } catch (InvalidDataException var5) {
         throw new NotSendableException(var5);
      }

      return Collections.singletonList(var3);
   }

   public ByteBuffer createByteBufferFromFramedata(Framedata var1) {
      ByteBuffer var2 = var1.getPayloadData();
      boolean var3 = this.role == Role.CLIENT;
      int var4 = this.getSizeBytes(var2);
      ByteBuffer var5 = ByteBuffer.allocate(1 + (var4 > 1 ? var4 + 1 : var4) + (var3 ? 4 : 0) + var2.remaining());
      byte var6 = this.fromOpcode(var1.getOpcode());
      byte var7 = (byte)(var1.isFin() ? -128 : 0);
      var7 = (byte)(var7 | var6);
      if (var1.isRSV1()) {
         var7 |= this.getRSVByte(1);
      }

      if (var1.isRSV2()) {
         var7 |= this.getRSVByte(2);
      }

      if (var1.isRSV3()) {
         var7 |= this.getRSVByte(3);
      }

      var5.put(var7);
      byte[] var8 = this.toByteArray(var2.remaining(), var4);
      if (!$assertionsDisabled && var8.length != var4) {
         throw new AssertionError();
      } else {
         if (var4 == 1) {
            var5.put((byte)(var8[0] | this.getMaskByte(var3)));
         } else if (var4 == 2) {
            var5.put((byte)(126 | this.getMaskByte(var3)));
            var5.put(var8);
         } else {
            if (var4 != 8) {
               throw new IllegalStateException("Size representation not supported/specified");
            }

            var5.put((byte)(127 | this.getMaskByte(var3)));
            var5.put(var8);
         }

         if (var3) {
            ByteBuffer var9 = ByteBuffer.allocate(4);
            var9.putInt(this.recoveredField3256.nextInt());
            var5.put(var9.array());

            for (int var10 = 0; var2.hasRemaining(); var10++) {
               var5.put((byte)(var2.get() ^ var9.get(var10 % 4)));
            }
         } else {
            var5.put(var2);
            ((Buffer)var2).flip();
         }

         if (!$assertionsDisabled && var5.remaining() != 0) {
            throw new AssertionError(var5.remaining());
         } else {
            ((Buffer)var5).flip();
            return var5;
         }
      }
   }

   public ByteBuffer getPayloadFromByteBufferList() throws org.java_websocket.exceptions.LimitExceededException {
      long var1 = 0L;
      ByteBuffer var3;
      synchronized (this.byteBufferList) {
         for (ByteBuffer var6 : this.byteBufferList) {
            var1 += var6.limit();
         }

         this.checkBufferLimit();
         var3 = ByteBuffer.allocate((int)var1);

         for (ByteBuffer var10 : this.byteBufferList) {
            var3.put(var10);
         }
      }

      ((Buffer)var3).flip();
      return var3;
   }

   @Override
   public ClientHandshakeBuilder postProcessHandshakeRequestAsClient(ClientHandshakeBuilder var1) {
      var1.put("Upgrade", "websocket");
      var1.put("Connection", "Upgrade");
      byte[] var2 = new byte[16];
      this.recoveredField3256.nextBytes(var2);
      var1.put("Sec-WebSocket-Key", Base64.encodeBytes(var2));
      var1.put("Sec-WebSocket-Version", "13");
      StringBuilder var3 = new StringBuilder();

      for (IExtension var5 : this.knownExtensions) {
         if (var5.getProvidedExtensionAsClient() != null && var5.getProvidedExtensionAsClient().length() != 0) {
            if (var3.length() > 0) {
               var3.append(", ");
            }

            var3.append(var5.getProvidedExtensionAsClient());
         }
      }

      if (var3.length() != 0) {
         var1.put("Sec-WebSocket-Extensions", var3.toString());
      }

      StringBuilder var7 = new StringBuilder();

      for (IProtocol var6 : this.knownProtocols) {
         if (var6.getProvidedProtocol().length() != 0) {
            if (var7.length() > 0) {
               var7.append(", ");
            }

            var7.append(var6.getProvidedProtocol());
         }
      }

      if (var7.length() != 0) {
         var1.put("Sec-WebSocket-Protocol", var7.toString());
      }

      return var1;
   }

   public void translateSingleFrameCheckLengthLimit(long var1) throws org.java_websocket.exceptions.LimitExceededException {
      if (var1 > 2147483647L) {
         this.log.trace("Limit exedeed: Payloadsize is to big...");
         throw new LimitExceededException("Payloadsize is to big...");
      } else if (var1 > this.maxFrameSize) {
         this.log.trace("Payload limit reached. Allowed: {} Current: {}", this.maxFrameSize, var1);
         throw new LimitExceededException("Payload limit reached.", this.maxFrameSize);
      } else if (var1 < 0L) {
         this.log.trace("Limit underflow: Payloadsize is to little...");
         throw new LimitExceededException("Payloadsize is to little...");
      }
   }

   public IExtension getExtension() {
      return this.extension;
   }

   public Draft_6455(List<IExtension> var1, int var2) {
      this(var1, Collections.singletonList(new Protocol("")), var2);
   }

   @Override
   public List<Framedata> createFrames(ByteBuffer var1, boolean var2) {
      BinaryFrame var3 = new BinaryFrame();
      var3.setPayload(var1);
      var3.setTransferemasked(var2);

      try {
         var3.isValid();
      } catch (InvalidDataException var5) {
         throw new NotSendableException(var5);
      }

      return Collections.singletonList(var3);
   }

   @Override
   public String toString() {
      String var1 = super.toString();
      if (this.getExtension() != null) {
         var1 = var1 + " extension: " + this.getExtension().toString();
      }

      if (this.getProtocol() != null) {
         var1 = var1 + " protocol: " + this.getProtocol().toString();
      }

      return var1 + " max frame size: " + this.maxFrameSize;
   }

   public Framedata translateSingleFrame(ByteBuffer var1) throws org.java_websocket.exceptions.IncompleteException, org.java_websocket.exceptions.InvalidDataException {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         int var2 = var1.remaining();
         int var3 = 2;
         this.translateSingleFrameCheckPacketSize(var2, var3);
         byte var4 = var1.get();
         boolean var5 = var4 >> 8 != 0;
         boolean var6 = (var4 & 64) != 0;
         boolean var7 = (var4 & 32) != 0;
         boolean var8 = (var4 & 16) != 0;
         byte var9 = var1.get();
         boolean var10 = (var9 & -128) != 0;
         int var11 = (byte)(var9 & 127);
         Opcode var12 = this.toOpcode((byte)(var4 & 15));
         if (var11 < 0 || var11 > 125) {
            Draft_6455.TranslatedPayloadMetaData var13 = this.translateSingleFramePayloadLength(var1, var12, var11, var2, var3);
            var11 = var13.getPayloadLength();
            var3 = var13.getRealPackageSize();
         }

         this.translateSingleFrameCheckLengthLimit(var11);
         var3 += var10 ? 4 : 0;
         var3 += var11;
         this.translateSingleFrameCheckPacketSize(var2, var3);
         ByteBuffer var18 = ByteBuffer.allocate(this.checkAlloc(var11));
         if (var10) {
            byte[] var14 = new byte[4];
            var1.get(var14);

            for (int var15 = 0; var15 < var11; var15++) {
               var18.put((byte)(var1.get() ^ var14[var15 % 4]));
            }
         } else {
            var18.put(var1.array(), var1.position(), var18.limit());
            ((Buffer)var1).position(var1.position() + var18.limit());
         }

         FramedataImpl1 var19 = FramedataImpl1.get(var12);
         var19.setFin(var5);
         var19.setRSV1(var6);
         var19.setRSV2(var7);
         var19.setRSV3(var8);
         ((Buffer)var18).flip();
         var19.setPayload(var18);
         this.getExtension().isFrameValid(var19);
         this.getExtension().decodeFrame(var19);
         if (this.log.isTraceEnabled()) {
            this.log
               .trace(
                  "afterDecoding({}): {}",
                  var19.getPayloadData().remaining(),
                  var19.getPayloadData().remaining() > 1000 ? "too big to display" : new String(var19.getPayloadData().array())
               );
         }

         var19.isValid();
         return var19;
      }
   }

   public IProtocol getProtocol() {
      return this.protocol;
   }

   @Override
   public void processFrame(WebSocketImpl var1, Framedata var2) throws org.java_websocket.exceptions.InvalidDataException {
      Opcode var3 = var2.getOpcode();
      if (var3 == Opcode.CLOSING) {
         this.processFrameClosing(var1, var2);
      } else if (var3 == Opcode.PING) {
         var1.getWebSocketListener().onWebsocketPing(var1, var2);
      } else if (var3 == Opcode.PONG) {
         var1.updateLastPong();
         var1.getWebSocketListener().onWebsocketPong(var1, var2);
      } else if (var2.isFin() && var3 != Opcode.CONTINUOUS) {
         if (this.currentContinuousFrame != null) {
            this.log.error("Protocol error: Continuous frame sequence not completed.");
            throw new InvalidDataException(1002, "Continuous frame sequence not completed.");
         }

         if (var3 == Opcode.TEXT) {
            this.processFrameText(var1, var2);
         } else {
            if (var3 != Opcode.BINARY) {
               this.log.error("non control or continious frame expected");
               throw new InvalidDataException(1002, "non control or continious frame expected");
            }

            this.processFrameBinary(var1, var2);
         }
      } else {
         this.processFrameContinuousAndNonFin(var1, var2, var3);
      }
   }

   @Override
   public HandshakeBuilder postProcessHandshakeResponseAsServer(ClientHandshake var1, ServerHandshakeBuilder var2) throws org.java_websocket.exceptions.InvalidHandshakeException {
      var2.put("Upgrade", "websocket");
      var2.put("Connection", var1.getFieldValue("Connection"));
      String var3 = var1.getFieldValue("Sec-WebSocket-Key");
      if (var3 == null) {
         throw new InvalidHandshakeException("missing Sec-WebSocket-Key");
      } else {
         var2.put("Sec-WebSocket-Accept", this.generateFinalKey(var3));
         if (this.getExtension().getProvidedExtensionAsServer().length() != 0) {
            var2.put("Sec-WebSocket-Extensions", this.getExtension().getProvidedExtensionAsServer());
         }

         if (this.getProtocol() != null && this.getProtocol().getProvidedProtocol().length() != 0) {
            var2.put("Sec-WebSocket-Protocol", this.getProtocol().getProvidedProtocol());
         }

         var2.setHttpStatusMessage("Web Socket Protocol Handshake");
         var2.put("Server", "TooTallNate Java-WebSocket");
         var2.put("Date", this.getServerTime());
         return var2;
      }
   }

   @Override
   public ByteBuffer createBinaryFrame(Framedata var1) {
      this.getExtension().encodeFrame(var1);
      if (this.log.isTraceEnabled()) {
         this.log
            .trace(
               "afterEnconding({}): {}",
               var1.getPayloadData().remaining(),
               var1.getPayloadData().remaining() > 1000 ? "too big to display" : new String(var1.getPayloadData().array())
            );
      }

      return this.createByteBufferFromFramedata(var1);
   }

   public Draft_6455(IExtension var1) {
      this(Collections.singletonList(var1));
   }

   public byte getMaskByte(boolean var1) {
      return (byte)(var1 ? -128 : 0);
   }

   public List<IProtocol> getKnownProtocols() {
      return this.knownProtocols;
   }

   public Draft_6455(List<IExtension> var1) {
      this(var1, Collections.singletonList(new Protocol("")));
   }

   public String getServerTime() {
      Calendar var1 = Calendar.getInstance();
      SimpleDateFormat var2 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
      var2.setTimeZone(TimeZone.getTimeZone("GMT"));
      return var2.format(var1.getTime());
   }

   @Override
   public void reset() {
      this.incompleteframe = null;
      if (this.extension != null) {
         this.extension.reset();
      }

      this.extension = new DefaultExtension();
      this.protocol = null;
   }

   public void checkBufferLimit() throws org.java_websocket.exceptions.LimitExceededException {
      long var1 = this.getByteBufferListSize();
      if (var1 > this.maxFrameSize) {
         this.clearBufferList();
         this.log.trace("Payload limit reached. Allowed: {} Current: {}", this.maxFrameSize, var1);
         throw new LimitExceededException(this.maxFrameSize);
      }
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         Draft_6455 var2 = (Draft_6455)var1;
         if (this.maxFrameSize != var2.getMaxFrameSize()) {
            return false;
         } else if (this.extension != null ? this.extension.equals(var2.getExtension()) : var2.getExtension() == null) {
            return this.protocol != null ? this.protocol.equals(var2.getProtocol()) : var2.getProtocol() == null;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public byte[] toByteArray(long var1, int var3) {
      byte[] var4 = new byte[var3];
      int var5 = 8 * var3 - 8;

      for (int var6 = 0; var6 < var3; var6++) {
         var4[var6] = (byte)(var1 >>> var5 - 8 * var6);
      }

      return var4;
   }

   public Draft_6455() {
      this(Collections.emptyList());
   }

   public Draft_6455(List<IExtension> var1, List<IProtocol> var2) {
      this(var1, var2, Integer.MAX_VALUE);
   }

   @Override
   public Draft copyInstance() {
      ArrayList var1 = new ArrayList();

      for (IExtension var3 : this.getKnownExtensions()) {
         var1.add(var3.copyInstance());
      }

      ArrayList var5 = new ArrayList();

      for (IProtocol var4 : this.getKnownProtocols()) {
         var5.add(var4.copyInstance());
      }

      return new Draft_6455(var1, var5, this.maxFrameSize);
   }

   public void processFrameContinuousAndNonFin(WebSocketImpl var1, Framedata var2, Opcode var3) throws org.java_websocket.exceptions.InvalidDataException {
      if (var3 != Opcode.CONTINUOUS) {
         this.processFrameIsNotFin(var2);
      } else if (var2.isFin()) {
         this.processFrameIsFin(var1, var2);
      } else if (this.currentContinuousFrame == null) {
         this.log.error("Protocol error: Continuous frame sequence was not started.");
         throw new InvalidDataException(1002, "Continuous frame sequence was not started.");
      }

      if (var3 == Opcode.TEXT && !Charsetfunctions.isValidUTF8(var2.getPayloadData())) {
         this.log.error("Protocol error: Payload is not UTF8");
         throw new InvalidDataException(1007);
      } else {
         if (var3 == Opcode.CONTINUOUS && this.currentContinuousFrame != null) {
            this.addToBufferList(var2.getPayloadData());
         }
      }
   }

   public byte getRSVByte(int var1) {
      if (var1 == 1) {
         return 64;
      } else if (var1 == 2) {
         return 32;
      } else {
         return (byte)(var1 == 3 ? 16 : 0);
      }
   }

   @Override
   public HandshakeState acceptHandshakeAsServer(ClientHandshake var1) throws org.java_websocket.exceptions.InvalidHandshakeException {
      int var2 = this.readVersion(var1);
      if (var2 != 13) {
         this.log.trace("acceptHandshakeAsServer - Wrong websocket version.");
         return HandshakeState.NOT_MATCHED;
      } else {
         HandshakeState var3 = HandshakeState.NOT_MATCHED;
         String var4 = var1.getFieldValue("Sec-WebSocket-Extensions");

         for (IExtension var6 : this.knownExtensions) {
            if (var6.acceptProvidedExtensionAsServer(var4)) {
               this.extension = var6;
               var3 = HandshakeState.MATCHED;
               this.log.trace("acceptHandshakeAsServer - Matching extension found: {}", this.extension);
               break;
            }
         }

         HandshakeState var7 = this.containsRequestedProtocol(var1.getFieldValue("Sec-WebSocket-Protocol"));
         if (var7 == HandshakeState.MATCHED && var3 == HandshakeState.MATCHED) {
            return HandshakeState.MATCHED;
         } else {
            this.log.trace("acceptHandshakeAsServer - No matching extension or protocol found.");
            return HandshakeState.NOT_MATCHED;
         }
      }
   }

   public void clearBufferList() {
      synchronized (this.byteBufferList) {
         this.byteBufferList.clear();
      }
   }

   @Override
   public List<Framedata> translateFrame(ByteBuffer var1) throws org.java_websocket.exceptions.InvalidDataException {
      while (true) {
         LinkedList var2 = new LinkedList();
         if (this.incompleteframe != null) {
            try {
               ((Buffer)var1).mark();
               int var4 = var1.remaining();
               int var9 = this.incompleteframe.remaining();
               if (var9 > var4) {
                  this.incompleteframe.put(var1.array(), var1.position(), var4);
                  ((Buffer)var1).position(var1.position() + var4);
                  return Collections.emptyList();
               }

               this.incompleteframe.put(var1.array(), var1.position(), var9);
               ((Buffer)var1).position(var1.position() + var9);
               Framedata var3 = this.translateSingleFrame((ByteBuffer)((Buffer)this.incompleteframe.duplicate()).position(0));
               var2.add(var3);
               this.incompleteframe = null;
            } catch (IncompleteException var6) {
               ByteBuffer var5 = ByteBuffer.allocate(this.checkAlloc(var6.getPreferredSize()));
               if (!$assertionsDisabled && var5.limit() <= this.incompleteframe.limit()) {
                  throw new AssertionError();
               }

               ((Buffer)this.incompleteframe).rewind();
               var5.put(this.incompleteframe);
               this.incompleteframe = var5;
               continue;
            }
         }

         while (var1.hasRemaining()) {
            ((Buffer)var1).mark();

            try {
               Framedata var8 = this.translateSingleFrame(var1);
               var2.add(var8);
            } catch (IncompleteException var7) {
               ((Buffer)var1).reset();
               int var10 = var7.getPreferredSize();
               this.incompleteframe = ByteBuffer.allocate(this.checkAlloc(var10));
               this.incompleteframe.put(var1);
               break;
            }
         }

         return var2;
      }
   }

   public void processFrameBinary(WebSocketImpl var1, Framedata var2) {
      try {
         var1.getWebSocketListener().onWebsocketMessage(var1, var2.getPayloadData());
      } catch (RuntimeException var4) {
         this.logRuntimeException(var1, var4);
      }
   }

   public class TranslatedPayloadMetaData {
      public int realPackageSize;
      public int payloadLength;

      public int getRealPackageSize() {
         return this.realPackageSize;
      }

      public int getPayloadLength() {
         return this.payloadLength;
      }

      public TranslatedPayloadMetaData(int var2, int var3) {
         this.payloadLength = var2;
         this.realPackageSize = var3;
      }
   }
}
