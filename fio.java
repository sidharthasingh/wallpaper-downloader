import java.nio.*;
import java.nio.file.*;
import java.util.Scanner;
import java.io.*;
import java.net.*;
import java.lang.Object;
public class fio {
   static String getlink(String s)
   {
      if(s.indexOf("-desktop-wallpapers.html")!=-1)
         return null;
      else if(s.indexOf("-wallpapers.html")!=-1 && s.indexOf("href")!=-1)
      {
         int temp=s.indexOf("\"/");
         s=s.substring(temp+2);
         s=s.substring(0,s.indexOf(".html"));
         // System.out.println(s);
         return s;
      }
      return null;
   }
   static boolean checkpresence(String  n)
   {
      String arr[]={"1024x768",
      "",
"1152x864",
"1280x960",
"1400x1050",
"1440x1080",
"1600x1200",
"1680x1260",
"1920x1440",
"2048x1536",
"2560x1920",
"2800x2100",
"3200x2400",
"4096x3072",
"6400x4800",
"1280x1024",
"2560x2048",
"3750x3000",
"5120x4096",
"1152x768",
"1440x960",
"1920x1280",
"2000x1333",
"2160x1440",
"2736x1824",
"3000x2000"};
      for(int i=0;i<arr.length;i++)
      {
         if(new File(n+"-"+arr[i]+".jpg").exists())
            return true;
         return false;
      }
      return false;
   }
   static String getsize(int i)
   {
      String arr[]={"1024x768",
      "",
"1152x864",
"1280x960",
"1400x1050",
"1440x1080",
"1600x1200",
"1680x1260",
"1920x1440",
"2048x1536",
"2560x1920",
"2800x2100",
"3200x2400",
"4096x3072",
"6400x4800",
"1280x1024",
"2560x2048",
"3750x3000",
"5120x4096",
"1152x768",
"1440x960",
"1920x1280",
"2000x1333",
"2160x1440",
"2736x1824",
"3000x2000"};
      return arr[i];
   }
   static void download(String fn,int j) throws IOException
   {
      if(fio.checkpresence(fn))
      {
        
      }
      else
      {
         String fn1=fn+"-"+fio.getsize(j)+".jpg";
         System.out.println("http://wallpaperswide.com/download/"+fn1);
         System.out.println("Starting download of wallpaper : "+fn1);
         URL website = new URL("http://wallpaperswide.com/download/"+fn1);
         Path target=Paths.get(fn1);
         try (InputStream in = website.openStream()) 
         {
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);     
            System.out.println("Download of- "+fn+" -Completed!");
         }
         catch(Exception e)
         {
             fio.download(fn,j+1);
         }
      }
   }
   public static void main(String args[]) throws IOException 
   {  
      Scanner sc=new Scanner(System.in);
      System.out.println("Enter the number of pages to load : ");
      int n=sc.nextInt();
      InputStream in = null;
      FileOutputStream out = null;
      String line,words[],link;
      for(int i=1;i<n;i++)
      {
         in = new URL("http://wallpaperswide.com/page/"+i).openStream();
         try
         {
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) 
            {
                  // Do your thing with line
                   words=line.split(" ");
               for(int j=0;j<words.length;j++)
               {
                  link=fio.getlink(words[j]);
                  if(link!=null)
                  {
                     try
                     {
                            // System.out.println("Starting download of wallpaper : "+link);
                            fio.download(link,0);
                        // System.out.println("Download of- "+link+" -Completed!");
                     }
                     catch(Exception e)
                     {
                        System.out.println("Some error occured while downloading : "+link);
                     }
                  }
               }
            }
         }
         finally
         {

         }
      }
   }
}