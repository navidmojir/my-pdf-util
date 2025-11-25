import ir.mojir.my_pdf_util.PdfGenerator;
import ir.mojir.my_pdf_util.dtos.PdfGenerationRequest;
import ir.mojir.my_pdf_util.dtos.PdfTableGenReq;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PdfGeneratorTest {
    @Test
    public void generatePdfTest() throws Exception {
        try {
            PdfGenerator pdfGenerator = new PdfGenerator();

            PdfGenerationRequest req = new PdfGenerationRequest();
            List<PdfTableGenReq> tables = new ArrayList<>();
            PdfTableGenReq table1 = new PdfTableGenReq();

            List<PdfTableGenReq.TableRow> rows = new ArrayList<>();

            List<PdfTableGenReq.TableColumn> columns = new ArrayList<>();
            columns.add(new PdfTableGenReq.TableColumn("عنوان", true));
            columns.add(new PdfTableGenReq.TableColumn("محتوا"));
//            columns.add(new PdfTableGenReq.TableColumn("عنوان دو", true));
            columns.add(new PdfTableGenReq.TableColumn("محتوای دو", false, 2));

            rows.add(new PdfTableGenReq.TableRow(columns));

            table1.setColumnWidths(new int[]{1,2,1,2});
            table1.setTitle("عنوان تستی");
            table1.setRows(rows);
            tables.add(table1);
            req.setTables(tables);

            byte[] bytes = pdfGenerator.generatePdf(req);
            Files.write(Path.of("output.pdf"), bytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
