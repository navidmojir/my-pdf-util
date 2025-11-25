package ir.mojir.my_pdf_util.dtos;

import java.util.List;

public class PdfGenerationRequest {

    private List<PdfTableGenReq> tables;

    public List<PdfTableGenReq> getTables() {
        return tables;
    }

    public void setTables(List<PdfTableGenReq> tables) {
        this.tables = tables;
    }
}
