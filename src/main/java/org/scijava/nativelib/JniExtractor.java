package org.scijava.nativelib;

import java.io.File;

public interface JniExtractor {
   void extractRegistered() throws java.io.IOException ;

   File extractJni(String var1, String var2) throws java.io.IOException ;
}
