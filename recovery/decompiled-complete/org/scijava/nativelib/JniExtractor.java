package org.scijava.nativelib;

import java.io.File;

public interface JniExtractor {
   void extractRegistered();

   File extractJni(String var1, String var2);
}
