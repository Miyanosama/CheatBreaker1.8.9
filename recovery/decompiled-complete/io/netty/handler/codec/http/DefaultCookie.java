package io.netty.handler.codec.http;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import javazoom.jl.decoder.SampleBuffer;
import net.minecraft.item.Item$8;

public class DefaultCookie implements Cookie {
   public Item$8 __junk5189248841174161006;
   public boolean discard;
   public String domain;
   public String path;
   public int version;
   public boolean secure;
   public SampleBuffer __junk6047142481393591866;
   public String value;
   public boolean httpOnly;
   public String name;
   public String comment;
   public Set<Integer> ports = Collections.emptySet();
   public long maxAge;
   public Set<Integer> unmodifiablePorts = this.ports;
   public String commentUrl;

   @Override
   public void setValue(String var1) {
      if (var1 == null) {
         throw new NullPointerException("value");
      } else {
         this.value = var1;
      }
   }

   @Override
   public void setSecure(boolean var1) {
      this.secure = var1;
   }

   @Override
   public String getCommentUrl() {
      return this.commentUrl;
   }

   @Override
   public String getPath() {
      return this.path;
   }

   @Override
   public void setVersion(int var1) {
      this.version = var1;
   }

   @Override
   public void setDiscard(boolean var1) {
      this.discard = var1;
   }

   @Override
   public boolean isSecure() {
      return this.secure;
   }

   @Override
   public void setComment(String var1) {
      this.comment = validateValue("comment", var1);
   }

   @Override
   public void setPorts(Iterable<Integer> var1) {
      TreeSet var2 = new TreeSet();

      for (int var4 : var1) {
         if (var4 <= 0 || var4 > 65535) {
            throw new IllegalArgumentException("port out of range: " + var4);
         }

         var2.add(var4);
      }

      if (var2.isEmpty()) {
         this.unmodifiablePorts = this.ports = Collections.emptySet();
      } else {
         this.ports = var2;
         this.unmodifiablePorts = null;
      }
   }

   @Override
   public void setPorts(int... var1) {
      if (var1 == null) {
         throw new NullPointerException("ports");
      } else {
         int[] var2 = (int[])var1.clone();
         if (var2.length == 0) {
            this.unmodifiablePorts = this.ports = Collections.emptySet();
         } else {
            TreeSet var3 = new TreeSet();

            for (int var7 : var2) {
               if (var7 <= 0 || var7 > 65535) {
                  throw new IllegalArgumentException("port out of range: " + var7);
               }

               var3.add(var7);
            }

            this.ports = var3;
            this.unmodifiablePorts = null;
         }
      }
   }

   @Override
   public String getDomain() {
      return this.domain;
   }

   public DefaultCookie(String var1, String var2) {
      this.maxAge = -1842840180335966208L & -7380531856857345975L;
      if (var1 == null) {
         throw new NullPointerException("name");
      } else {
         var1 = var1.trim();
         if (var1.isEmpty()) {
            throw new IllegalArgumentException("empty name");
         } else {
            for (int var3 = 0; var3 < var1.length(); var3++) {
               char var4 = var1.charAt(var3);
               if (var4 > 127) {
                  throw new IllegalArgumentException("name contains non-ascii character: " + var1);
               }

               switch (var4) {
                  case '\t':
                  case '\n':
                  case '\u000b':
                  case '\f':
                  case '\r':
                  case ' ':
                  case ',':
                  case ';':
                  case '=':
                     throw new IllegalArgumentException("name contains one of the following prohibited characters: =,; \\t\\r\\n\\v\\f: " + var1);
               }
            }

            if (var1.charAt(0) == '$') {
               throw new IllegalArgumentException("name starting with '$' not allowed: " + var1);
            } else {
               this.name = var1;
               this.setValue(var2);
            }
         }
      }
   }

   @Override
   public void setDomain(String var1) {
      this.domain = validateValue("domain", var1);
   }

   @Override
   public int getVersion() {
      return this.version;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public long getMaxAge() {
      return this.maxAge;
   }

   @Override
   public String getComment() {
      return this.comment;
   }

   @Override
   public String getValue() {
      return this.value;
   }

   public static String validateValue(String var0, String var1) {
      if (var1 == null) {
         return null;
      } else {
         var1 = var1.trim();
         if (var1.isEmpty()) {
            return null;
         } else {
            for (int var2 = 0; var2 < var1.length(); var2++) {
               char var3 = var1.charAt(var2);
               switch (var3) {
                  case '\n':
                  case '\u000b':
                  case '\f':
                  case '\r':
                  case ';':
                     throw new IllegalArgumentException(var0 + " contains one of the following prohibited characters: " + ";\\r\\n\\f\\v (" + var1 + ')');
               }
            }

            return var1;
         }
      }
   }

   @Override
   public void setMaxAge(long var1) {
      this.maxAge = var1;
   }

   @Override
   public boolean isHttpOnly() {
      return this.httpOnly;
   }

   public int compareTo(Cookie var1) {
      int var2 = this.getName().compareToIgnoreCase(var1.getName());
      if (var2 != 0) {
         return var2;
      } else {
         if (this.getPath() == null) {
            if (var1.getPath() != null) {
               return -1;
            }
         } else {
            if (var1.getPath() == null) {
               return 1;
            }

            var2 = this.getPath().compareTo(var1.getPath());
            if (var2 != 0) {
               return var2;
            }
         }

         if (this.getDomain() == null) {
            return var1.getDomain() != null ? -1 : 0;
         } else {
            return var1.getDomain() == null ? 1 : this.getDomain().compareToIgnoreCase(var1.getDomain());
         }
      }
   }

   @Override
   public int hashCode() {
      return this.getName().hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      if (!(var1 instanceof Cookie)) {
         return false;
      } else {
         Cookie var2 = (Cookie)var1;
         if (!this.getName().equalsIgnoreCase(var2.getName())) {
            return false;
         } else {
            if (this.getPath() == null) {
               if (var2.getPath() != null) {
                  return false;
               }
            } else {
               if (var2.getPath() == null) {
                  return false;
               }

               if (!this.getPath().equals(var2.getPath())) {
                  return false;
               }
            }

            if (this.getDomain() == null) {
               return var2.getDomain() == null;
            } else {
               return var2.getDomain() == null ? false : this.getDomain().equalsIgnoreCase(var2.getDomain());
            }
         }
      }
   }

   @Override
   public Set<Integer> getPorts() {
      if (this.unmodifiablePorts == null) {
         this.unmodifiablePorts = Collections.unmodifiableSet(this.ports);
      }

      return this.unmodifiablePorts;
   }

   @Override
   public void setPath(String var1) {
      this.path = validateValue("path", var1);
   }

   @Override
   public void setCommentUrl(String var1) {
      this.commentUrl = validateValue("commentUrl", var1);
   }

   @Override
   public void setHttpOnly(boolean var1) {
      this.httpOnly = var1;
   }

   @Override
   public boolean isDiscard() {
      return this.discard;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(this.getName());
      var1.append('=');
      var1.append(this.getValue());
      if (this.getDomain() != null) {
         var1.append(", domain=");
         var1.append(this.getDomain());
      }

      if (this.getPath() != null) {
         var1.append(", path=");
         var1.append(this.getPath());
      }

      if (this.getComment() != null) {
         var1.append(", comment=");
         var1.append(this.getComment());
      }

      if (this.getMaxAge() >= (810559513L & -1152553357189790522L)) {
         var1.append(", maxAge=");
         var1.append(this.getMaxAge());
         var1.append('s');
      }

      if (this.isSecure()) {
         var1.append(", secure");
      }

      if (this.isHttpOnly()) {
         var1.append(", HTTPOnly");
      }

      return var1.toString();
   }
}
