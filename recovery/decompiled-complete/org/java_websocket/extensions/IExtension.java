package org.java_websocket.extensions;

import org.java_websocket.framing.Framedata;

public interface IExtension {
   boolean acceptProvidedExtensionAsClient(String var1);

   @Override
   String toString();

   void encodeFrame(Framedata var1);

   boolean acceptProvidedExtensionAsServer(String var1);

   void isFrameValid(Framedata var1);

   void decodeFrame(Framedata var1);

   IExtension copyInstance();

   void reset();

   String getProvidedExtensionAsServer();

   String getProvidedExtensionAsClient();
}
