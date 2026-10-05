package io.netty.handler.codec.http.multipart;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.util.ReferenceCounted;
import org.scijava.nativelib.NativeLibraryUtil;

public interface InterfaceHttpData extends ReferenceCounted, Comparable<InterfaceHttpData> {
   InterfaceHttpData.HttpDataType getHttpDataType();

   String getName();

   public static enum HttpDataType {
      Attribute,
      FileUpload,
      InternalAttribute;

   }
}
