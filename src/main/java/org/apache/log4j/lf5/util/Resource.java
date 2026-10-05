package org.apache.log4j.lf5.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Resource {
   public String _name;

   public InputStreamReader getInputStreamReader() {
      InputStream var1 = ResourceUtils.getResourceAsStream(this, this);
      return var1 == null ? null : new InputStreamReader(var1);
   }

   public String getName() {
      return this._name;
   }

   public Resource() {
   }

   public InputStream getInputStream() {
      return ResourceUtils.getResourceAsStream(this, this);
   }

   public URL getURL() {
      return ResourceUtils.getResourceAsURL(this, this);
   }

   public Resource(String var1) {
      this._name = var1;
   }

   public void setName(String var1) {
      this._name = var1;
   }
}
