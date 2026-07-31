package fraud_detection.service;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import fraud_detection.dto.ReportDTO;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PDFService {

    public ByteArrayInputStream generateReport(ReportDTO report) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);

            document.open();

            document.add(new Paragraph("Fraud Detection System"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Summary Report"));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Total Transactions : " + report.getTotalTransactions()));
            document.add(new Paragraph("Successful Transactions : " + report.getSuccessfulTransactions()));
            document.add(new Paragraph("Failed Transactions : " + report.getFailedTransactions()));
            document.add(new Paragraph("Suspicious Transactions : " + report.getSuspiciousTransactions()));
            document.add(new Paragraph("Total Fraud Alerts : " + report.getTotalFraudAlerts()));

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}