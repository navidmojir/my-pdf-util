package ir.mojir.my_pdf_util;

public class PdfGenerationConfig {
    private String pdfBoldFontFilePath = "XB_ZarBd.ttf";

    private String pdfFontFilePath = "XB_Zar.ttf";

    private int rowHeight = 20;

    public String getPdfBoldFontFilePath() {
        return pdfBoldFontFilePath;
    }

    public void setPdfBoldFontFilePath(String pdfBoldFontFilePath) {
        this.pdfBoldFontFilePath = pdfBoldFontFilePath;
    }

    public String getPdfFontFilePath() {
        return pdfFontFilePath;
    }

    public void setPdfFontFilePath(String pdfFontFilePath) {
        this.pdfFontFilePath = pdfFontFilePath;
    }

    public int getRowHeight() {
        return rowHeight;
    }

    public void setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
    }
}
