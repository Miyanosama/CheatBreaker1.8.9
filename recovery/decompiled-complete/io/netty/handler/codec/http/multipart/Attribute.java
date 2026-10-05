package io.netty.handler.codec.http.multipart;

public interface Attribute extends HttpData {
   Attribute retain(int var1);

   void setValue(String var1);

   Attribute duplicate();

   Attribute copy();

   Attribute retain();

   String getValue();
}
