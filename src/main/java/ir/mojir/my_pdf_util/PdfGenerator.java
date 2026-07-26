package ir.mojir.my_pdf_util;

import java.io.ByteArrayOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ir.mojir.my_pdf_util.dtos.PdfGenerationRequest;
import ir.mojir.my_pdf_util.dtos.PdfTableGenReq;

public class PdfGenerator {
//    private final static String ONE_PIXEL_IMAGE_B64 = "iVBORw0KGgoAAAANSUhEUgAAAJ0AAADICAYAAADyZeOSAAAABGdBTUEAALGPC/xhBQAAAAlwSFlzAAAOxAAADsQBlSsOGwAAA6xJREFUeF7t2NFx4joAQFHqoiDqoRo3QzFeGxxisyR5H8l9G3POjGeII8QHdyTkwwgx0ZETHTnRkRMdOdGREx050ZETHTnRkRMdOdGREx050ZETHTnRkRMdOdGREx050ZETHTnRkRMdOdGREx050ZETHTnRkRMdOdGREx050ZETHTnRkRMdOdGREx050ZETHTnRkRMdOdGREx050ZETHTnRkRMdOdGREx050ZETHTnRkRMdOdGREx050ZETHTnRkRMdOdGREx050ZETHTnRkRMdOdGREx050ZETHTnRkRMdOdGREx050ZETHTnRkRMdOdGREx050ZETHTnRkRMdOdGREx050ZETHTnRkRPd2nAaD4fTOCx/8jNEN7mcj1Nsh28ObhhP03znee7TJ7O+YOgvHd1wmkObruN5vCz3vs3lPB6vIU/XV9H9xOf/w142umtwn8XwIy7j+fRagT3zotHNW9/3R3c5T9vppqjlc+4r2W3L3X7q45j9e93t9W37+yS8+/Z7OD7E9Nw8fj3dcJoDm1a342E8XieYX2+j+3vM/r34QeLjFW8+XLxHcBmH4esgtu+Z5l7mvR5Urq8fo3s2Zv9ePLrJsuJtV5k5jv+2um3Mh4J7OKut9H5YeJz32Zj92210t21rMYV1un7T05f+5If87ZHJOobbdrd+lPF0JboM42YBnANehXObd9mil/cOp23Mz8bs3Y6je/99dT+pXle19fY2u2151+dpm/8tW+9nQUyr0/r2fJA4TrE+Dp3DeltJ16/XPrq/RzuIborj2bY0b1dLMMfzsKxc0yoznN9DWq5NnJv/PQb64O0wslzXaKZ72zmma1PhQ8xPx+zb749uiuvpCjEH8UJf5G/y66P7cFuaorv9juNfs4uV7q/naNd7X2yN/G92cZDYnABf7PfRb7SL6PhdREdOdORER0505ERHTnTkREdOdORER0505ERHTnTkREdOdORER0505ERHTnTkREdOdORER0505ERHTnTkREdOdORER0505ERHTnTkREdOdORER0505ERHTnTkREdOdORER0505ERHTnTkREdOdORER0505ERHTnTkREdOdORER0505ERHTnTkREdOdORER0505ERHTnTkREdOdORER0505ERHTnTkREdOdORER0505ERHTnTkREdOdORER0505ERHTnTkREdOdORER0505ERHTnTkREdOdORER0505ERHTnTkREdOdORER0505ERHTnTExvEPrr1+J2LIYBEAAAAASUVORK5CYII=";

    private PdfGenerationConfig config;

    private Font titleFont = null;
    private Font boldFont = null;
    private Font normalFont = null;

    private final BaseColor gray = new BaseColor(233, 233, 233);
    private final BaseColor white = new BaseColor(255, 255, 255);

    public PdfGenerator() throws Exception {
        this.config = new PdfGenerationConfig();
        initFonts();
    }

    public PdfGenerator(PdfGenerationConfig config) throws Exception {
        this.config = config;
        initFonts();
    }

    public byte[] generatePdf(PdfGenerationRequest req) {
        try(ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, os);

            document.open();

            for(PdfTableGenReq table: req.getTables()) {
            	if(table.isOnNewPage())
            		document.newPage();
                document.add(makeTable(table));
            }

            document.close();
            return os.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Pdf generation failed.", e);
        }
    }

//    private PdfPTable makeSampleTable() {
//        float[] columnWidths = {2,2,2,2,2,2,3};
//        PdfPTable table = makeTable(columnWidths);
//
//        table.addCell(makeHeaderCell("مشخصات استعلام", 7));
//
//        table.addCell(makeValueCell("دارد" , 2));
//        table.addCell(makeTitleCell("پیوست"));
//        table.addCell(makeValueCell("1234"));
//        table.addCell(makeTitleCell("شماره نامه"));
//        table.addCell(makeValueCell("1402/02/05"));
//        table.addCell(makeTitleCell("تاریخ نامه"));
//        table.addCell(makeValueCell("مشاغل حساس"));
//        table.addCell(makeValueCell("انتصاب"));
//        table.addCell(makeTitleCell("علت استعلام"));
//        table.addCell(makeValueCell("نوید",3));
//        table.addCell(makeTitleCell("فرستنده"));
//        table.addCell(makeValueCell("953113", 6));
//        table.addCell(makeTitleCell("کد رهگیری"));
//        table.addCell(makeValueCell("متن توضیح", 6));
//        table.addCell(makeTitleCell("توضیحات/نظریه مرجع"));
//        return table;
//    }

    private float[] prepareColumnWidths(int[] columnWidths) {
        float[] result = new float[columnWidths.length];
        for(int i = 0; i < columnWidths.length; i++) {
            result[i] = (float)columnWidths[columnWidths.length - 1 - i];
        }
        return result;
    }
    private PdfPTable makeTable(PdfTableGenReq req) {

        PdfPTable table = makeTable(prepareColumnWidths(req.getColumnWidths()));

        table.addCell(makeHeaderCell(req.getTitle(), req.getColumnWidths().length));

        for(PdfTableGenReq.TableRow row: req.getRows()) {
//            for(PdfTableGenReq.TableColumn column: row.getColumns()) {
            for(int i = row.getColumns().size() - 1; i >= 0; i--) {
                PdfTableGenReq.TableColumn column = row.getColumns().get(i);
                if(column.isHeader())
                    table.addCell(makeTitleCell(column.getValue(), column.getColSpan()));
                else if(column.isImageColumn())
                	table.addCell(makeImageCell(column.getImageBytes(), column.getImageRowSpan(), 
                			column.getColSpan(), column.getImageAbsoluteWidth(), column.getImageAbsoluteHeight()));
                else
                    table.addCell(makeValueCell(column.getValue(), column.getColSpan()));
            }
        }
        return table;
    }

    private void initFonts() throws Exception {
        BaseFont bf = BaseFont.createFont(config.getPdfBoldFontFilePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        BaseFont f = BaseFont.createFont(config.getPdfFontFilePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        titleFont = new Font(bf, 13);
        boldFont = new Font(bf, 9);
        normalFont = new Font(f, 10);
    }

    private PdfPTable makeTable(float[] columnWidths) {
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        return table;
    }

    private PdfPCell makeHeaderCell(String text, int colspan) {
    	PdfPCell cell = makeCell(text, titleFont, Element.ALIGN_LEFT, Element.ALIGN_TOP,
                25, white, colspan);
    	cell.disableBorderSide(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
    	return cell;
    }

//    private PdfPCell makeTitleCell(String text) {
//        return makeTitleCell(text, config.getRowHeight());
//    }

    protected PdfPCell makeTitleCell(String text, int colspan) {
    	return  makeCell(text, boldFont, Element.ALIGN_LEFT, Element.ALIGN_TOP, config.getRowHeight(), gray, colspan);
    	
    }

    protected PdfPCell makeValueCell(String text) {
        return makeValueCell(text, 1);
    }

    protected PdfPCell makeValueCell(String text, int colspan) {
        return makeCell(text, normalFont, Element.ALIGN_LEFT, Element.ALIGN_TOP, config.getRowHeight(), white, colspan);
    }

    protected PdfPCell makeCell(String text, Font font, int horizontalAlignment,
                                int verticalAlignment, float fixedHeight, BaseColor backgroundColor, int colspan) {
        if(text == null || text.trim().length() == 0)
            text = "";
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setColspan(colspan);
        cell.setHorizontalAlignment(horizontalAlignment);
        cell.setVerticalAlignment(verticalAlignment);
        cell.setBackgroundColor(backgroundColor);
        cell.setMinimumHeight(fixedHeight);
        cell.setPaddingBottom(10);
        cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        return cell;
    }
    
    private PdfPCell makeImageCell(byte[] imageBytes, int rowSpan, int colSpan, float absoluteWidth, float absoluteHeight) {
		try {
			Image image = Image.getInstance(imageBytes);
			image.scaleAbsolute(absoluteWidth, absoluteHeight);
			image.setBorder(Rectangle.BOX);
			image.setBorderWidth(1f);
			PdfPCell cell = new PdfPCell(image);
			cell.setRowspan(rowSpan);
			cell.setColspan(colSpan);
			return cell;
		} catch(Exception e) {
			throw new RuntimeException("Failed to make image cell", e);
		}
	}
}
