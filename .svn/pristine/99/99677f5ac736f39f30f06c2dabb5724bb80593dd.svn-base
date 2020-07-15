package cn.com.eju.deal.reportbase.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import cn.com.eju.deal.base.support.SystemParam;

/*import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;*/

import cn.com.eju.deal.core.support.SystemCfg;



import sun.misc.BASE64Decoder;



public class UrlUtil
{
    private static final String DATE_FORMAT = "HHmmssSSS";
    
    private  static final SimpleDateFormat DATE_DIR_SDF = new SimpleDateFormat("yyyyMMdd");
    
    public static String unescape(String src)
    {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length())
        {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos)
            {
                if (src.charAt(pos + 1) == 'u')
                {
                    ch = (char)Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                }
                else
                {
                    ch = (char)Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            }
            else
            {
                if (pos == -1)
                {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                }
                else
                {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }
    
    /**
     * 杞崲BASE4缂栫爜 鑾峰緱鍥剧墖娴�
     * @param imgStr 鍥剧墖娴�
     * @param list  瀛樺偍鍥剧墖闆嗗悎
     * @return  瑙ｆ瀽鍥剧墖鍦板潃
     */
    public static void decodeBase4(String imgStr, List<String> list)
    {
        if (imgStr.length() > 0)
        {
            String str =
                UrlUtil.unescape(imgStr).replaceAll(" ", "+").substring(imgStr.indexOf("base64,") + 7, imgStr.length());
            list.add(str);
        }
        
    }
    
    /**
     * 鐢熸垚鍥剧墖
     * @param imgStr 鍥剧墖鍦板潃
     * @return
     */
    @SuppressWarnings("restriction")
    public static File GenerateImage(String imgStr)
    {
        //瀵瑰瓧鑺傛暟缁勫瓧绗︿覆杩涜Base64瑙ｇ爜骞剁敓鎴愬浘鐗�
        File file = null;
        if (imgStr == null) //鍥惧儚鏁版嵁涓虹┖
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        FileOutputStream write = null;
        try
        {
            file = new File("D:/" + UUID.randomUUID() + ".png");
            write = new FileOutputStream(file);
            byte[] decoderBytes = decoder.decodeBuffer(imgStr.toString());
            write.write(decoderBytes);
            
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                write.close();
                file.delete();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return file;
    }
    
    /**
     * 
     * @param list 鍥剧墖娴佸湴鍧�
     * @param pdfFile PDF鏂囦欢璺緞
     */
   /* public static void addPdfInsertImg(List<String> list, File pdfFile)
    {
        try
        {
            
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile.getAbsolutePath()));
            document.open();
            for (String imgStr : list)
            {
                File file = UrlUtil.GenerateImage(imgStr);
                Image img = Image.getInstance(file.getAbsolutePath());//閫夋嫨鍥剧墖
                img.setAlignment(Image.MIDDLE);//灞呬腑
                img.scaleAbsolute(400, 500);//鎺у埗鍥剧墖澶у皬  
                document.add(img);
            }
            document.close();
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (DocumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    */
    
    public static String getFilePath(HttpServletRequest request,String fileName ){
        Date date = new Date();
        String tempFilePath = SystemCfg.getString("outputFilePath");
        tempFilePath = MessageFormat.format(tempFilePath, DATE_DIR_SDF.format(date));
        int index = fileName.lastIndexOf(".");
        if (index > 0)
        {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            fileName = fileName.substring(0,index)+sdf.format(new Date()) + fileName.substring(index);
        }
        // 鍒涘缓鐩綍
        File uploadDir = new File(tempFilePath);
        if (!uploadDir.exists())
        {
            uploadDir.mkdirs();
        }
        return uploadDir + File.separator + fileName;
   
          
    }
    
    
    public static String getFilePath(String fileName ){
        Date date = new Date();
        //String tempFilePath = SystemCfg.getString("outputFilePath");
        String tempFilePath = SystemParam.getWebConfigValue("CRMReportPath");
        tempFilePath = MessageFormat.format(tempFilePath, DATE_DIR_SDF.format(date));
        int index = fileName.lastIndexOf(".");
        if (index > 0)
        {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            fileName = fileName.substring(0,index)+sdf.format(new Date()) + fileName.substring(index);
        }
        // 鍒涘缓鐩綍
        File uploadDir = new File(tempFilePath);
        if (!uploadDir.exists())
        {
            uploadDir.mkdirs();
        }
        return uploadDir + File.separator + fileName+DateUtil.getDateFileFormat(new Date());

    }
}
