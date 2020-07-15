package cn.com.eju.deal.poi.common;

import cn.com.eju.deal.common.util.ExcelUtil;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 13-12-3
 * Time: 上午10:18
 * To change this template use File | Settings | File Templates.
 */
public class ExcelUseXmlAbstract {

    protected int nowRowIndex = 0;
    protected int nowColIndex = 0;
    protected short headerStyle = 0;

    protected final void substitute(File zipfile, File tmpfile, String entry,
                                    OutputStream out) throws IOException {
        ZipFile zip = new ZipFile(zipfile);
        ZipOutputStream zos = new ZipOutputStream(out);
        try {
            @SuppressWarnings("unchecked")
            Enumeration<ZipEntry> en = (Enumeration<ZipEntry>) zip.entries();
            while (en.hasMoreElements()) {
                ZipEntry ze = en.nextElement();
                if (!ze.getName().equals(entry)) {
                    zos.putNextEntry(new ZipEntry(ze.getName()));
                    InputStream is = zip.getInputStream(ze);
                    copyStream(is, zos);
                    is.close();
                }
            }
            zos.putNextEntry(new ZipEntry(entry));
            InputStream is = new FileInputStream(tmpfile);
            try {
                copyStream(is, zos);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zip.close();
            zos.close();

        }
    }

    protected final void copyStream(InputStream in, OutputStream out)
            throws IOException {
        byte[] chunk = new byte[1024];
        int count;
        while ((count = in.read(chunk)) >= 0) {
            out.write(chunk, 0, count);
        }
    }

    /**
     * 创建一个标题单元格，并且进行合并
     *
     * @param name       标题显示字，如果没有或者为空字符串，也不会添加
     * @param spanColumn 合并列的数量，如果小于等于零就不合并
     * @param spanRow    合并行的数量，如果小于等于零就不合并
     * @throws IOException
     */
    protected int createBlock(SpeedSheetWriter sw, String name, int spanColumn, int spanRow, List<String> listMerge) throws IOException {
        String beginColumnChar = ExcelUtil.getColumnChar(nowColIndex + 1);
        int endColIndex = nowColIndex + spanColumn;
        if (name != null) {
            while (nowColIndex < endColIndex) {
                sw.createCell(nowColIndex++, name, headerStyle);
            }
        }
        String endColumnChar = ExcelUtil.getColumnChar(endColIndex);
        if (spanRow > 0 && spanColumn > 0 && listMerge != null) {
            listMerge.add(beginColumnChar + (nowRowIndex + 1) + ":" + endColumnChar + (nowRowIndex + spanRow));
        }
        //nowColIndex = endColIndex;
        return endColIndex;
    }

    protected Map<String, XSSFCellStyle> createStyles(XSSFWorkbook wb) {
        Map<String, XSSFCellStyle> styles = new HashMap<String, XSSFCellStyle>();
        XSSFDataFormat fmt = wb.createDataFormat();
        XSSFFont bodyFont = wb.createFont();
        bodyFont.setFontName("微软雅黑");
        XSSFCellStyle style1 = wb.createCellStyle();
        style1.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
        style1.setDataFormat(fmt.getFormat("0.00%"));
        style1.setBorderBottom(CellStyle.BORDER_THIN);
        style1.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style1.setBorderTop(CellStyle.BORDER_THIN);
        style1.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style1.setBorderLeft(CellStyle.BORDER_THIN);
        style1.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style1.setBorderRight(CellStyle.BORDER_THIN);
        style1.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style1.setFont(bodyFont);
        styles.put(ExcelStyleEnum.RATE_RIGHT.getValue(), style1);

        XSSFCellStyle style17 = wb.createCellStyle();
        style17.setAlignment(XSSFCellStyle.ALIGN_LEFT);
        style17.setDataFormat(fmt.getFormat("0.00%"));
        style17.setBorderBottom(CellStyle.BORDER_THIN);
        style17.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style17.setBorderTop(CellStyle.BORDER_THIN);
        style17.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style17.setBorderLeft(CellStyle.BORDER_THIN);
        style17.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style17.setBorderRight(CellStyle.BORDER_THIN);
        style17.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style17.setFont(bodyFont);
        styles.put(ExcelStyleEnum.RATE_LEFT.getValue(), style17);

        XSSFCellStyle style2 = wb.createCellStyle();
        style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        style2.setDataFormat(fmt.getFormat("0.00%"));
        style2.setBorderBottom(CellStyle.BORDER_THIN);
        style2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style2.setBorderTop(CellStyle.BORDER_THIN);
        style2.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style2.setBorderLeft(CellStyle.BORDER_THIN);
        style2.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style2.setBorderRight(CellStyle.BORDER_THIN);
        style2.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style2.setFont(bodyFont);
        styles.put(ExcelStyleEnum.RATE_CENTER.getValue(), style2);

        XSSFCellStyle style3 = wb.createCellStyle();
        style3.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style3.setDataFormat(fmt.getFormat("_ * #,##0.00_ ;_ * -#,##0.00_ ;_ * -_ ;_ @_ "));
        style3.setBorderBottom(CellStyle.BORDER_THIN);
        style3.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style3.setBorderTop(CellStyle.BORDER_THIN);
        style3.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style3.setBorderLeft(CellStyle.BORDER_THIN);
        style3.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style3.setBorderRight(CellStyle.BORDER_THIN);
        style3.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style3.setFont(bodyFont);
        styles.put(ExcelStyleEnum.BODY.getValue(), style3);

        XSSFCellStyle style4 = wb.createCellStyle();
        style4.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style4.setDataFormat(fmt.getFormat("_ * #,##0_ ;_ * -#,##0_ ;_ * -_ ;_ @_ "));
        style4.setBorderBottom(CellStyle.BORDER_THIN);
        style4.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style4.setBorderTop(CellStyle.BORDER_THIN);
        style4.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style4.setBorderLeft(CellStyle.BORDER_THIN);
        style4.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style4.setBorderRight(CellStyle.BORDER_THIN);
        style4.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style4.setFont(bodyFont);
        styles.put(ExcelStyleEnum.BODY_INT.getValue(), style4);

        XSSFCellStyle style5 = wb.createCellStyle();
        XSSFFont headerFont = wb.createFont();
        headerFont.setBold(true);
        headerFont.setFontName("微软雅黑");
        headerFont.setFontHeightInPoints((short) 9);
        style5.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style5.setBorderBottom(CellStyle.BORDER_THIN);
        style5.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style5.setBorderTop(CellStyle.BORDER_THIN);
        style5.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style5.setBorderLeft(CellStyle.BORDER_THIN);
        style5.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style5.setBorderRight(CellStyle.BORDER_THIN);
        style5.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style5.setWrapText(true);
        style5.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        style5.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        style5.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style5.setFont(headerFont);
        styles.put(ExcelStyleEnum.HEADER.getValue(), style5);

        //文字格式黑色
        XSSFCellStyle style6 = wb.createCellStyle();
        XSSFFont font = wb.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 11);
        style6.setBorderBottom(CellStyle.BORDER_THIN);
        style6.setBorderTop(CellStyle.BORDER_THIN);
        style6.setBorderLeft(CellStyle.BORDER_THIN);
        style6.setBorderRight(CellStyle.BORDER_THIN);
        style6.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        style6.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style6.setFont(font);
        styles.put(ExcelStyleEnum.BODY_BLACK.getValue(), style6);
        //文字格式红色
        XSSFCellStyle style7 = wb.createCellStyle();
        XSSFFont font7 = wb.createFont();
        font7.setFontName("微软雅黑");
        font7.setColor(XSSFFont.COLOR_RED);
        font7.setFontHeightInPoints((short) 11);
        style7.setBorderBottom(CellStyle.BORDER_THIN);
        style7.setBorderTop(CellStyle.BORDER_THIN);
        style7.setBorderLeft(CellStyle.BORDER_THIN);
        style7.setBorderRight(CellStyle.BORDER_THIN);
        style7.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        style7.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style7.setFont(font7);
        styles.put(ExcelStyleEnum.BODY_RED.getValue(), style7);

        //金额格式黑色非锁定状态
        XSSFCellStyle style8 = wb.createCellStyle();
        style8.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style8.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        style8.setDataFormat(fmt.getFormat("#,##0.00;-#,##0.00;-"));
        style8.setBorderBottom(CellStyle.BORDER_THIN);
        style8.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style8.setBorderTop(CellStyle.BORDER_THIN);
        style8.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style8.setBorderLeft(CellStyle.BORDER_THIN);
        style8.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style8.setBorderRight(CellStyle.BORDER_THIN);
        style8.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style8.setFont(bodyFont);
        style8.setLocked(false);
        styles.put(ExcelStyleEnum.MONEY_UNLOCKED_BLACK.getValue(), style8);
        //金额格式红色非锁定状态
        XSSFCellStyle style9 = wb.createCellStyle();
        XSSFFont font9 = wb.createFont();
        font9.setFontName("微软雅黑");
        font9.setColor(XSSFFont.COLOR_RED);
        style9.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style9.setDataFormat(fmt.getFormat("#,##0.00;-#,##0.00;-"));
        style9.setBorderBottom(CellStyle.BORDER_THIN);
        style9.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style9.setBorderTop(CellStyle.BORDER_THIN);
        style9.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style9.setBorderLeft(CellStyle.BORDER_THIN);
        style9.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style9.setBorderRight(CellStyle.BORDER_THIN);
        style9.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style9.setFont(font9);
        style9.setLocked(false);
        styles.put(ExcelStyleEnum.MONEY_UNLOCKED_RED.getValue(), style9);

        //金额格式黑色锁定状态
        XSSFCellStyle style10 = wb.createCellStyle();
        style10.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style10.setDataFormat(fmt.getFormat("#,##0.00;-#,##0.00;-"));
        style10.setBorderBottom(CellStyle.BORDER_THIN);
        style10.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style10.setBorderTop(CellStyle.BORDER_THIN);
        style10.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style10.setBorderLeft(CellStyle.BORDER_THIN);
        style10.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style10.setBorderRight(CellStyle.BORDER_THIN);
        style10.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style10.setFont(bodyFont);
        styles.put(ExcelStyleEnum.MONEY_LOCKED_BLACK.getValue(), style10);
        //金额格式红色锁定状态
        XSSFCellStyle style11 = wb.createCellStyle();
        XSSFFont font11 = wb.createFont();
        font11.setFontName("微软雅黑");
        font11.setColor(XSSFFont.COLOR_RED);
        style11.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style11.setDataFormat(fmt.getFormat("#,##0.00;-#,##0.00;-"));
        style11.setBorderBottom(CellStyle.BORDER_THIN);
        style11.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style11.setBorderTop(CellStyle.BORDER_THIN);
        style11.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style11.setBorderLeft(CellStyle.BORDER_THIN);
        style11.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style11.setBorderRight(CellStyle.BORDER_THIN);
        style11.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style11.setFont(font11);
        styles.put(ExcelStyleEnum.MONEY_LOCKED_RED.getValue(), style11);

        XSSFCellStyle style12 = wb.createCellStyle();
        style12.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style12.setDataFormat(fmt.getFormat("yyyy-MM-dd"));
        style12.setBorderBottom(CellStyle.BORDER_THIN);
        style12.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style12.setBorderTop(CellStyle.BORDER_THIN);
        style12.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style12.setBorderLeft(CellStyle.BORDER_THIN);
        style12.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style12.setBorderRight(CellStyle.BORDER_THIN);
        style12.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style12.setFont(bodyFont);
        styles.put(ExcelStyleEnum.DATE_BLACK.getValue(), style12);

        XSSFCellStyle style13 = wb.createCellStyle();
        style13.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style13.setDataFormat(fmt.getFormat("yyyy-MM-dd"));
        style13.setBorderBottom(CellStyle.BORDER_THIN);
        style13.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style13.setBorderTop(CellStyle.BORDER_THIN);
        style13.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style13.setBorderLeft(CellStyle.BORDER_THIN);
        style13.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style13.setBorderRight(CellStyle.BORDER_THIN);
        style13.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style13.setFont(font11);
        styles.put(ExcelStyleEnum.DATE_RED.getValue(), style13);

        //金额格式绿色状态
        XSSFCellStyle style14 = wb.createCellStyle();
        XSSFFont font14 = wb.createFont();
        font14.setFontName("微软雅黑");
        byte[] b = new byte[3];
        b[0] = (byte) 0x0;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x0;
        font14.setColor(new XSSFColor(b));
        style14.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style14.setDataFormat(fmt.getFormat("#,##0.00;-#,##0.00;-"));
        style14.setBorderBottom(CellStyle.BORDER_THIN);
        style14.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style14.setBorderTop(CellStyle.BORDER_THIN);
        style14.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style14.setBorderLeft(CellStyle.BORDER_THIN);
        style14.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style14.setBorderRight(CellStyle.BORDER_THIN);
        style14.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style14.setFont(font14);
        style9.setLocked(false);
        styles.put(ExcelStyleEnum.MONEY_UNLOCKED_GREEN.getValue(), style14);

        XSSFCellStyle style15 = wb.createCellStyle();
        style15.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style15.setDataFormat(fmt.getFormat("yyyy-MM-dd"));
        style15.setBorderBottom(CellStyle.BORDER_THIN);
        style15.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style15.setBorderTop(CellStyle.BORDER_THIN);
        style15.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style15.setBorderLeft(CellStyle.BORDER_THIN);
        style15.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style15.setBorderRight(CellStyle.BORDER_THIN);
        style15.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style15.setFont(font14);
        styles.put(ExcelStyleEnum.DATE_GREEN.getValue(), style15);

        XSSFCellStyle style16 = wb.createCellStyle();
        XSSFFont font16 = wb.createFont();
        font16.setFontName("微软雅黑");
        font16.setColor(new XSSFColor(b));
        font16.setFontHeightInPoints((short) 11);
        style16.setBorderBottom(CellStyle.BORDER_THIN);
        style16.setBorderTop(CellStyle.BORDER_THIN);
        style16.setBorderLeft(CellStyle.BORDER_THIN);
        style16.setBorderRight(CellStyle.BORDER_THIN);
        style16.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        style16.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style16.setFont(font16);
        styles.put(ExcelStyleEnum.BODY_GREEN.getValue(), style16);

        return styles;
    }

}
