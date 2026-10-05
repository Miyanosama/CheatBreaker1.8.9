package org.java_websocket.extensions;

import org.java_websocket.framing.Framedata;

public interface IExtension {
   boolean acceptProvidedExtensionAsClient(String var1);

   @Override
   String toString();

   void encodeFrame(Framedata var1);

   boolean acceptProvidedExtensionAsServer(String var1);

   void isFrameValid(Framedata var1) throws org.java_websocket.exceptions.InvalidDataException ;

   void decodeFrame(Framedata var1) throws org.java_websocket.exceptions.InvalidDataException ;

   IExtension copyInstance();

   void reset();

   String getProvidedExtensionAsServer();

   String getProvidedExtensionAsClient();
}
