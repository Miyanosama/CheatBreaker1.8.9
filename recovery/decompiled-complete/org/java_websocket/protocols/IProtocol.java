package org.java_websocket.protocols;

public interface IProtocol {
   IProtocol copyInstance();

   @Override
   String toString();

   boolean acceptProvidedProtocol(String var1);

   String getProvidedProtocol();
}
