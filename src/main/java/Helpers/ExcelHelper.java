package Helpers;

import org.apache.poi.ss.formula.functions.Rows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelHelper {


    private static int currentRowIndex = 0;  // Starting from 1 (as row 0 is header)
    private static List<Row> rows = new ArrayList<>();
    private static Row headerRow;


    // Synchronization object to control flow
    private static final Object lock = new Object();

    // Initialize the Excel reader with a given file path
    public static void init(String filePath) throws IOException {

        readExcelFile2(filePath);
        System.out.println("Row size " + rows.size());

    }





    public static void readExcelFile2(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        headerRow = sheet.getRow(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            rows.add(sheet.getRow(i));
        }

        // Close the workbook and input stream
        workbook.close();
        fis.close();
    }



    public static Map<String, String> getNextRow() {
        System.out.println("Getting row at: " + currentRowIndex);
        if (currentRowIndex >= rows.size()) {
            System.out.println("No more rows to read.");
            return null;  // Return null if no more rows
        }

        Row row = rows.get(currentRowIndex);
        Map<String, String> rowData = new LinkedHashMap<>();
        for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
            String columnName = headerRow.getCell(i).toString().trim();
            Cell cell = row.getCell(i);
            String cellValue = (cell != null) ? cell.toString().trim() : "";
            rowData.put(columnName, cellValue);
        }

        System.out.println("Returning row data: " + rowData);
        currentRowIndex++;
        return rowData;
    }

    public static boolean hasNextRow() {

        return currentRowIndex < rows.size();
    }

    // Function to get the next row of data as a Map
    public static Map<String, String> getNextRowData() {
        return getNextRow();

    }

    // Notify to proceed to the next row after Gherkin steps are done
    public static void notifyNextRow() {
        synchronized (lock) {
            lock.notify(); // Notify the waiting thread to continue processing
        }
    }


}