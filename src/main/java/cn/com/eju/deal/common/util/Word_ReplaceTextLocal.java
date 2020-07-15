package cn.com.eju.deal.common.util;

import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Word_ReplaceTextLocal {

    public static boolean searchAndReplace(String srcPath, String destPath,Map<String, String> map) {
    	Boolean flag = true;
    	
        FileOutputStream outStream = null;
        InputStream is = null;
        try {
        	//本地文件地址
        	is = new FileInputStream(new File(srcPath));
            
            XWPFDocument document = new XWPFDocument(is);
            /**
             * 替换段落中的指定文字
             */
            Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
            while (itPara.hasNext()) {
                XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
                Set<String> set = map.keySet();
                Iterator<String> iterator = set.iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    List<XWPFRun> run=paragraph.getRuns();
                    for(int i=0;i<run.size();i++)
                    {
                        if(run.get(i).getText(run.get(i).getTextPosition())!=null &&
                                run.get(i).getText(run.get(i).getTextPosition()).contains(key))
                        {
                            /**
                             * 参数0表示生成的文字是要从哪一个地方开始放置,设置文字从位置0开始
                             * 就可以把原来的文字全部替换掉了
                             */
                            String text = run.get(i).getText(run.get(i).getTextPosition());
                            text = text.replaceAll(key,map.get(key));
                            run.get(i).setText(text,0);
                        }
                    }
                }
            }
 
            /**
             * 替换表格中的指定文字
             */
            Iterator<XWPFTable> itTable = document.getTablesIterator();
            while (itTable.hasNext()) {
                XWPFTable table = (XWPFTable) itTable.next();
                int count = table.getNumberOfRows();
                for (int i = 0; i < count; i++) {
                    XWPFTableRow row = table.getRow(i);
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (XWPFTableCell cell : cells) {
                        for (Map.Entry<String, String> e : map.entrySet()) {
                            if (cell.getText().equals(e.getKey())) {
                                cell.removeParagraph(0);
                                cell.setText(e.getValue());
                            }
                        }
                    }
                }
            }
            outStream = new FileOutputStream(destPath); 
            document.write(outStream);
//            URLConnection conn = url.openConnection();
//            conn.setDoOutput(true);
//            outStream = conn.getOutputStream();
//            document.write(outStream);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }finally {
            try {
                if(null != is){
                    is.close();
                }
                if(null != outStream){
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
}
