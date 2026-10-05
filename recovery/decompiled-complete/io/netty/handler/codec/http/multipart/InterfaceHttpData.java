package io.netty.handler.codec.http.multipart;

import io.netty.util.ReferenceCounted;

public interface InterfaceHttpData extends ReferenceCounted, Comparable<InterfaceHttpData> {
   InterfaceHttpData$HttpDataType getHttpDataType();

   String getName();
}
