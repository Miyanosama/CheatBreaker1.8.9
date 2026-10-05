package org.java_websocket;

import org.java_websocket.drafts.Draft;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.PingFrame;
import org.java_websocket.framing.PongFrame;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.HandshakeImpl1Server;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;

public abstract class WebSocketAdapter implements WebSocketListener {
   public PingFrame pingFrame;

   @Override
   public void onWebsocketPong(WebSocket var1, Framedata var2) {
   }

   @Override
   public void onWebsocketHandshakeSentAsClient(WebSocket var1, ClientHandshake var2) throws org.java_websocket.exceptions.InvalidDataException {
   }

   @Override
   public PingFrame onPreparePing(WebSocket var1) {
      if (this.pingFrame == null) {
         this.pingFrame = new PingFrame();
      }

      return this.pingFrame;
   }

   @Override
   public void onWebsocketPing(WebSocket var1, Framedata var2) {
      var1.sendFrame(new PongFrame((PingFrame)var2));
   }

   @Override
   public void onWebsocketHandshakeReceivedAsClient(WebSocket var1, ClientHandshake var2, ServerHandshake var3) throws org.java_websocket.exceptions.InvalidDataException {
   }

   @Override
   public ServerHandshakeBuilder onWebsocketHandshakeReceivedAsServer(WebSocket var1, Draft var2, ClientHandshake var3) throws org.java_websocket.exceptions.InvalidDataException {
      return new HandshakeImpl1Server();
   }
}
