package file;

import jxl.Sheet;
import jxl.Workbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by zhaoxu on 2017/7/6.
 */
public class FileExcelUtil extends FileCommonUtil{

    public FileExcelUtil(String fileName){
        this.fileName = fileName;
        this.fileContent = new FileContent();
        this.fileType = "Excel";
    }


    @Override
    public FileCommonUtil writeFileContent(FileContent fileContent) {
        if (fileName.endsWith("xls")) {
            FileOutputStream out = null;
            HSSFWorkbook workbook = null;
            try {
                workbook = new HSSFWorkbook(new FileInputStream(new File(filePath + fileName)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            HSSFSheet sheet = workbook.getSheetAt(0);
            Row row;
            Cell cell;
            int rowCount = fileContent.getContentBody().size();
            int columnCount = fileContent.getContentBody().get(0).size();
            for (int rowNum = 0; rowNum < rowCount; rowNum++) {
                row = sheet.createRow(rowNum);
                for (int columnNum = 0; columnNum < columnCount; columnNum++) {
                    cell = row.createCell(columnNum);
                    cell.setCellValue(fileContent.getContentBody().get(rowNum).get(columnNum).toString());
                }
            }

            try {
                out = new FileOutputStream(filePath + fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                workbook.write(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.gc();
            return this;
        }else if (fileName.endsWith("xlsx")){
            XSSFWorkbook xssfWorkbook = null;
            XSSFSheet xssfSheet = null;
            FileOutputStream out = null;
            try {
                xssfWorkbook = new XSSFWorkbook(new FileInputStream(new File(filePath + fileName)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            xssfSheet = xssfWorkbook.getSheetAt(0);
            Row row;
            Cell cell;
            int rowCount = fileContent.getContentBody().size();
            int columnCount = fileContent.getContentBody().get(0).size();
            for (int rowNum = 0; rowNum < rowCount; rowNum++) {
                row = xssfSheet.createRow(rowNum);
                for (int columnNum = 0; columnNum < columnCount; columnNum++) {
                    cell = row.createCell(columnNum);
                    cell.setCellValue(fileContent.getContentBody().get(rowNum).get(columnNum).toString());
                }
            }

            try {
                out = new FileOutputStream(filePath + fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                xssfWorkbook.write(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.gc();
            return this;

        }else {

        }
        return this;
    }

    @Override
    public FileContent readFileContent() {
        if (new File(filePath+fileName).exists()) {
            if (fileName.endsWith("xls")) {
                Workbook book = null;
                Sheet sheet = null;
                try {
                    book = Workbook.getWorkbook(new File(filePath+fileName));
                    sheet = book.getSheet(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int rowCount = sheet.getRows();
                int columnCount = sheet.getColumns();
                for (int rowNum = 0; rowNum < rowCount; rowNum++) {
                    ArrayList contextByRow = new ArrayList();
                    for (int columnNum = 0; columnNum < columnCount; columnNum++) {
                        //jar包中row和column是反的
                        contextByRow.add(sheet.getCell(columnNum, rowNum).getContents());
                    }
                    fileContent.addContentBodyInfo(rowNum, contextByRow);
                }
                return fileContent;
            } else if (fileName.endsWith("xlsx")) {
                XSSFWorkbook xssfWorkbook = null;
                XSSFSheet xssfSheet = null;
                InputStream is = null;
                try {
                    is = new FileInputStream(filePath+fileName);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    xssfWorkbook = new XSSFWorkbook(is);
                    xssfSheet = xssfWorkbook.getSheetAt(0);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int rowCount = xssfSheet.getLastRowNum()+1;
                int columnCount = xssfSheet.getRow(0).getPhysicalNumberOfCells();
                for (int rowNum=0;rowNum<rowCount;rowNum++){
                    ArrayList contextByRow = new ArrayList();
                    for (int columnNum=0;columnNum<columnCount;columnNum++) {
                        contextByRow.add(xssfSheet.getRow(rowNum).getCell(columnNum));
                    }
                    fileContent.addContentBodyInfo(rowNum,contextByRow);
                }
                return fileContent;
            } else{

            }
        } else {

        }
        return fileContent;
    }

    public void removeFileContent(){
        System.out.println(fileName);
        System.out.println(filePath);
        if (fileName.endsWith("xls")) {
            HSSFWorkbook workbook = null;
            try {
                workbook = new HSSFWorkbook(new FileInputStream(new File(filePath + fileName)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i=0;i<workbook.getNumberOfSheets();i++){
                System.out.println(i);
                workbook.removeSheetAt(i);
            }
        }else if (fileName.endsWith("xlsx")){
            XSSFWorkbook xssfWorkbook = null;
            try {
                xssfWorkbook = new XSSFWorkbook(new FileInputStream(new File(filePath + fileName)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i=0;i<xssfWorkbook.getNumberOfSheets();i++){
                xssfWorkbook.removeSheetAt(i);
            }
        }
    }

    public static void main(String[] args) {
        new FileExcelUtil("test.xls").removeFileContent();
    }

}
