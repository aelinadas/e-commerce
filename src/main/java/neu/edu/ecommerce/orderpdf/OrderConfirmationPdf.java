/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neu.edu.ecommerce.orderpdf;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.log4j.Logger;
/**
 *
 * @author aelinadas
 */
public class OrderConfirmationPdf extends AbstractPdfView {
    private static final Logger logger = Logger.getLogger(OrderConfirmationPdf.class);
    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Map<String, String> revenueData = (Map<String, String>) map.get("downloadPdf");
            document.add(new Paragraph("Order Details"));
            Table table = new Table(2);
            for (Map.Entry<String, String> entry : revenueData.entrySet()) {
                table.addCell(entry.getKey());
                table.addCell(entry.getValue());
            }
            document.add(table);
        } catch (Exception e) {
            logger.error(e);
            System.out.println(e.getMessage());
        } 
    }
}
