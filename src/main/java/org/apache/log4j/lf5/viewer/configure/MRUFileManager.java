package org.apache.log4j.lf5.viewer.configure;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;

public class MRUFileManager {
   public static final String recoveredField87 = "mru_file_manager";
   public static final int recoveredField88 = 3;
   public LinkedList _mruFileList;
   public int _maxSize = 0;

   public int size() {
      return this._mruFileList.size();
   }

   public InputStream getInputStream(File var1) throws java.io.IOException, java.io.FileNotFoundException {
      return new BufferedInputStream(new FileInputStream(var1));
   }

   public static void createConfigurationDirectory() {
      String var0 = System.getProperty("user.home");
      String var1 = System.getProperty("file.separator");
      File var2 = new File(var0 + var1 + "lf5");
      if (!var2.exists()) {
         try {
            var2.mkdir();
         } catch (SecurityException var4) {
            var4.printStackTrace();
         }
      }
   }

   public String getFilename() {
      String var1 = System.getProperty("user.home");
      String var2 = System.getProperty("file.separator");
      return var1 + var2 + "lf5" + var2 + "mru_file_manager";
   }

   public String[] getMRUFileList() {
      if (this.size() == 0) {
         return null;
      } else {
         String[] var1 = new String[this.size()];

         for (int var2 = 0; var2 < this.size(); var2++) {
            Object var3 = this.getFile(var2);
            if (var3 instanceof File) {
               var1[var2] = ((File)var3).getAbsolutePath();
            } else {
               var1[var2] = var3.toString();
            }
         }

         return var1;
      }
   }

   public void setMRU(Object var1) {
      int var2 = this._mruFileList.indexOf(var1);
      if (var2 == -1) {
         this._mruFileList.add(0, var1);
         this.setMaxSize(this._maxSize);
      } else {
         this.moveToTop(var2);
      }
   }

   public void load() {
      createConfigurationDirectory();
      File var1 = new File(this.getFilename());
      if (var1.exists()) {
         try {
            ObjectInputStream var2 = new ObjectInputStream(new FileInputStream(var1));
            this._mruFileList = (LinkedList)var2.readObject();
            var2.close();
            Iterator var3 = this._mruFileList.iterator();

            while (var3.hasNext()) {
               Object var4 = var3.next();
               if (!(var4 instanceof File) && !(var4 instanceof URL)) {
                  var3.remove();
               }
            }
         } catch (Exception var5) {
            this._mruFileList = new LinkedList();
         }
      } else {
         this._mruFileList = new LinkedList();
      }
   }

   public MRUFileManager() {
      this.load();
      this.setMaxSize(3);
   }

   public void save() {
      File var1 = new File(this.getFilename());

      try {
         ObjectOutputStream var2 = new ObjectOutputStream(new FileOutputStream(var1));
         var2.writeObject(this._mruFileList);
         var2.flush();
         var2.close();
      } catch (Exception var3) {
         var3.printStackTrace();
      }
   }

   public void moveToTop(int var1) {
      this._mruFileList.add(0, this._mruFileList.remove(var1));
   }

   public MRUFileManager(int var1) {
      this.load();
      this.setMaxSize(var1);
   }

   public InputStream getInputStream(int var1) throws java.io.IOException, java.io.FileNotFoundException {
      if (var1 < this.size()) {
         Object var2 = this.getFile(var1);
         return var2 instanceof File ? this.getInputStream((File)var2) : this.getInputStream((URL)var2);
      } else {
         return null;
      }
   }

   public void set(File var1) {
      this.setMRU(var1);
   }

   public Object getFile(int var1) {
      return var1 < this.size() ? this._mruFileList.get(var1) : null;
   }

   public InputStream getInputStream(URL var1) throws java.io.IOException {
      return var1.openStream();
   }

   public void setMaxSize(int var1) {
      if (var1 < this._mruFileList.size()) {
         for (int var2 = 0; var2 < this._mruFileList.size() - var1; var2++) {
            this._mruFileList.removeLast();
         }
      }

      this._maxSize = var1;
   }

   public void set(URL var1) {
      this.setMRU(var1);
   }
}
