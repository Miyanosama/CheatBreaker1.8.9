package net.minecraft.util;

public interface IProgressUpdate {
   void resetProgressAndMessage(String var1);

   void displaySavingString(String var1);

   void setDoneWorking();

   void setLoadingProgress(int var1);

   void displayLoadingString(String var1);
}
