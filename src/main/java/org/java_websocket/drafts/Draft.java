package org.java_websocket.drafts;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.enums.CloseHandshakeType;
import org.java_websocket.enums.HandshakeState;
import org.java_websocket.enums.Opcode;
import org.java_websocket.enums.Role;
import org.java_websocket.exceptions.IncompleteHandshakeException;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.framing.BinaryFrame;
import org.java_websocket.framing.ContinuousFrame;
import org.java_websocket.framing.DataFrame;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.TextFrame;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.HandshakeBuilder;
import org.java_websocket.handshake.HandshakeImpl1Client;
import org.java_websocket.handshake.HandshakeImpl1Server;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.util.Charsetfunctions;

public abstract class Draft {
   public Opcode continuousFrameType;
   public Role role = null;

   public abstract CloseHandshakeType getCloseHandshakeType();

   public abstract ClientHandshakeBuilder postProcessHandshakeRequestAsClient(ClientHandshakeBuilder var1) throws org.java_websocket.exceptions.InvalidHandshakeException ;

   public static ByteBuffer readLine(ByteBuffer var0) {
      ByteBuffer var1 = ByteBuffer.allocate(var0.remaining());
      byte var3 = 48;

      while (var0.hasRemaining()) {
         byte var2 = var3;
         var3 = var0.get();
         var1.put(var3);
         if (var2 == 13 && var3 == 10) {
            ((Buffer)var1).limit(var1.position() - 2);
            ((Buffer)var1).position(0);
            return var1;
         }
      }

      ((Buffer)var0).position(var0.position() - var1.position());
      return null;
   }

   public abstract HandshakeState acceptHandshakeAsServer(ClientHandshake var1) throws org.java_websocket.exceptions.InvalidHandshakeException ;

   public boolean basicAccept(Handshakedata var1) {
      return var1.getFieldValue("Upgrade").equalsIgnoreCase("websocket") && var1.getFieldValue("Connection").toLowerCase(Locale.ENGLISH).contains("upgrade");
   }

   public List<ByteBuffer> createHandshake(Handshakedata var1, Role var2) {
      return this.createHandshake(var1);
   }

   public void setParseMode(Role var1) {
      this.role = var1;
   }

   public abstract HandshakeBuilder postProcessHandshakeResponseAsServer(ClientHandshake var1, ServerHandshakeBuilder var2) throws org.java_websocket.exceptions.InvalidHandshakeException ;

   public abstract List<Framedata> translateFrame(ByteBuffer var1) throws org.java_websocket.exceptions.InvalidDataException ;

   public abstract List<Framedata> createFrames(String var1, boolean var2);

   public abstract List<Framedata> createFrames(ByteBuffer var1, boolean var2);

   public static String readStringLine(ByteBuffer var0) {
      ByteBuffer var1 = readLine(var0);
      return var1 == null ? null : Charsetfunctions.stringAscii(var1.array(), 0, var1.limit());
   }

   public Handshakedata translateHandshake(ByteBuffer var1) throws org.java_websocket.exceptions.InvalidHandshakeException {
      return translateHandshakeHttp(var1, this.role);
   }

   public Role getRole() {
      return this.role;
   }

   public int readVersion(Handshakedata var1) {
      String var2 = var1.getFieldValue("Sec-WebSocket-Version");
      if (var2.length() > 0) {
         try {
            return new Integer(var2.trim());
         } catch (NumberFormatException var5) {
            return -1;
         }
      } else {
         return -1;
      }
   }

   public abstract HandshakeState acceptHandshakeAsClient(ClientHandshake var1, ServerHandshake var2) throws org.java_websocket.exceptions.InvalidHandshakeException ;

   public Draft() {
      this.continuousFrameType = null;
   }

   @Override
   public String toString() {
      return this.getClass().getSimpleName();
   }

   public static HandshakeBuilder translateHandshakeHttp(ByteBuffer var0, Role var1) throws org.java_websocket.exceptions.InvalidHandshakeException {
      String var3 = readStringLine(var0);
      if (var3 == null) {
         throw new IncompleteHandshakeException(var0.capacity() + 128);
      } else {
         String[] var4 = var3.split(" ", 3);
         if (var4.length != 3) {
            throw new InvalidHandshakeException();
         } else {
            HandshakeBuilder var2;
            if (var1 == Role.CLIENT) {
               var2 = translateHandshakeHttpClient(var4, var3);
            } else {
               var2 = translateHandshakeHttpServer(var4, var3);
            }

            for (var3 = readStringLine(var0); var3 != null && var3.length() > 0; var3 = readStringLine(var0)) {
               String[] var5 = var3.split(":", 2);
               if (var5.length != 2) {
                  throw new InvalidHandshakeException("not an http header");
               }

               if (var2.hasFieldValue(var5[0])) {
                  var2.put(var5[0], var2.getFieldValue(var5[0]) + "; " + var5[1].replaceFirst("^ +", ""));
               } else {
                  var2.put(var5[0], var5[1].replaceFirst("^ +", ""));
               }
            }

            if (var3 == null) {
               throw new IncompleteHandshakeException();
            } else {
               return var2;
            }
         }
      }
   }

   public List<Framedata> continuousFrame(Opcode var1, ByteBuffer var2, boolean var3) {
      if (var1 != Opcode.BINARY && var1 != Opcode.TEXT) {
         throw new IllegalArgumentException("Only Opcode.BINARY or  Opcode.TEXT are allowed");
      } else {
         Object var4 = null;
         if (this.continuousFrameType != null) {
            var4 = new ContinuousFrame();
         } else {
            this.continuousFrameType = var1;
            if (var1 == Opcode.BINARY) {
               var4 = new BinaryFrame();
            } else if (var1 == Opcode.TEXT) {
               var4 = new TextFrame();
            }
         }

         ((DataFrame)var4).setPayload(var2);
         ((DataFrame)var4).setFin(var3);

         try {
            ((DataFrame)var4).isValid();
         } catch (InvalidDataException var6) {
            throw new IllegalArgumentException(var6);
         }

         if (var3) {
            this.continuousFrameType = null;
         } else {
            this.continuousFrameType = var1;
         }

         return Collections.singletonList((Framedata)var4);
      }
   }

   public static HandshakeBuilder translateHandshakeHttpClient(String[] var0, String var1) throws org.java_websocket.exceptions.InvalidHandshakeException {
      if (!"101".equals(var0[1])) {
         throw new InvalidHandshakeException(String.format("Invalid status code received: %s Status line: %s", var0[1], var1));
      } else if (!"HTTP/1.1".equalsIgnoreCase(var0[0])) {
         throw new InvalidHandshakeException(String.format("Invalid status line received: %s Status line: %s", var0[0], var1));
      } else {
         HandshakeImpl1Server var2 = new HandshakeImpl1Server();
         ServerHandshakeBuilder var3 = var2;
         var3.setHttpStatus(Short.parseShort(var0[1]));
         var3.setHttpStatusMessage(var0[2]);
         return var2;
      }
   }

   public abstract void processFrame(WebSocketImpl var1, Framedata var2) throws org.java_websocket.exceptions.InvalidDataException ;

   public List<ByteBuffer> createHandshake(Handshakedata var1) {
      return this.createHandshake(var1, true);
   }

   public List<ByteBuffer> createHandshake(Handshakedata var1, Role var2, boolean var3) {
      return this.createHandshake(var1, var3);
   }

   public List<ByteBuffer> createHandshake(Handshakedata var1, boolean var2) {
      StringBuilder var3 = new StringBuilder(100);
      if (var1 instanceof ClientHandshake) {
         var3.append("GET ").append(((ClientHandshake)var1).getResourceDescriptor()).append(" HTTP/1.1");
      } else {
         if (!(var1 instanceof ServerHandshake)) {
            throw new IllegalArgumentException("unknown role");
         }

         var3.append("HTTP/1.1 101 ").append(((ServerHandshake)var1).getHttpStatusMessage());
      }

      var3.append("\r\n");
      Iterator var4 = var1.iterateHttpFields();

      while (var4.hasNext()) {
         String var5 = (String)var4.next();
         String var6 = var1.getFieldValue(var5);
         var3.append(var5);
         var3.append(": ");
         var3.append(var6);
         var3.append("\r\n");
      }

      var3.append("\r\n");
      byte[] var8 = Charsetfunctions.asciiBytes(var3.toString());
      byte[] var9 = var2 ? var1.getContent() : null;
      ByteBuffer var7 = ByteBuffer.allocate((var9 == null ? 0 : var9.length) + var8.length);
      var7.put(var8);
      if (var9 != null) {
         var7.put(var9);
      }

      ((Buffer)var7).flip();
      return Collections.singletonList(var7);
   }

   public abstract Draft copyInstance();

   public abstract void reset();

   public abstract ByteBuffer createBinaryFrame(Framedata var1);

   public static HandshakeBuilder translateHandshakeHttpServer(String[] var0, String var1) throws org.java_websocket.exceptions.InvalidHandshakeException {
      if (!"GET".equalsIgnoreCase(var0[0])) {
         throw new InvalidHandshakeException(String.format("Invalid request method received: %s Status line: %s", var0[0], var1));
      } else if (!"HTTP/1.1".equalsIgnoreCase(var0[2])) {
         throw new InvalidHandshakeException(String.format("Invalid status line received: %s Status line: %s", var0[2], var1));
      } else {
         HandshakeImpl1Client var2 = new HandshakeImpl1Client();
         var2.setResourceDescriptor(var0[1]);
         return var2;
      }
   }

   public int checkAlloc(int var1) throws org.java_websocket.exceptions.InvalidDataException {
      if (var1 < 0) {
         throw new InvalidDataException(1002, "Negative count");
      } else {
         return var1;
      }
   }
}
