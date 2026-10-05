package io.netty.handler.codec.http;

import java.util.Set;

public interface Cookie extends Comparable<Cookie> {
   Set<Integer> getPorts();

   void setPorts(int... var1);

   void setValue(String var1);

   void setCommentUrl(String var1);

   void setSecure(boolean var1);

   void setComment(String var1);

   long getMaxAge();

   void setHttpOnly(boolean var1);

   String getComment();

   boolean isDiscard();

   void setDomain(String var1);

   boolean isHttpOnly();

   void setPorts(Iterable<Integer> var1);

   boolean isSecure();

   String getPath();

   String getName();

   void setMaxAge(long var1);

   String getCommentUrl();

   void setDiscard(boolean var1);

   String getValue();

   void setPath(String var1);

   void setVersion(int var1);

   int getVersion();

   String getDomain();
}
