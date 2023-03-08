package service;
import entity.Trajet;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExporter {
    
    private  String[] COLUMN_HEADERS = {"Temps_Parcours", "trajet_id", "pts_departs", "pts_arrivee"};
    
    public void export(List<Trajet> objects, String filename) throws IOException {
        
        // Create a new workbook and sheet
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        
        // Create the header row
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < COLUMN_HEADERS.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(COLUMN_HEADERS[i]);
            
        }
        
        // Populate the data rows
        for (int i = 0; i < objects.size(); i++) {
           Trajet object = objects.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(object.getId());
            row.createCell(1).setCellValue(object.getTemps_parcours());
            row.createCell(2).setCellValue(object.getPts_depart());
            row.createCell(3).setCellValue(object.getPts_arrivee());
            
        }
        
        // Write the workbook to a file
        FileOutputStream outputStream = new FileOutputStream(filename);
        workbook.write(outputStream);
        workbook.close();
        
            
    }

    public ExcelExporter() {
    }

}
