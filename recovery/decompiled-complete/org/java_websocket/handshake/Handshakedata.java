package org.java_websocket.handshake;

import java.util.Iterator;

public interface Handshakedata {
   boolean hasFieldValue(String var1);

   byte[] getContent();

   String getFieldValue(String var1);

   Iterator<String> iterateHttpFields();
}
