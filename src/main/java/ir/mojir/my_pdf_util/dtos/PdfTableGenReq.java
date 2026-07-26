package ir.mojir.my_pdf_util.dtos;

import java.util.List;

public class PdfTableGenReq {

    public static class TableColumn {
        private boolean header = false;
        private boolean imageColumn = false;
        private String value;
        private byte[] imageBytes = null;
        private float imageAbsoluteWidth;
        private float imageAbsoluteHeight;
        private int imageRowSpan = 1;

        private int colSpan = 1;

        public TableColumn(String value) {
            this.value = value;
        }

        public TableColumn(String value, boolean header) {
            this.header = header;
            this.value = value;
        }

        public TableColumn(String value, boolean header, int colSpan) {
            this.header = header;
            this.value = value;
            this.colSpan = colSpan;
        }

        public boolean isHeader() {
            return header;
        }

        public void setHeader(boolean header) {
            this.header = header;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getColSpan() {
            return colSpan;
        }

        public void setColSpan(int colSpan) {
            this.colSpan = colSpan;
        }

		public boolean isImageColumn() {
			return imageColumn;
		}

		public void setImageColumn(boolean imageColumn) {
			this.imageColumn = imageColumn;
		}

		public byte[] getImageBytes() {
			return imageBytes;
		}

		public void setImageBytes(byte[] imageBytes) {
			this.imageBytes = imageBytes;
		}

		public float getImageAbsoluteWidth() {
			return imageAbsoluteWidth;
		}

		public void setImageAbsoluteWidth(float imageAbsoluteWidth) {
			this.imageAbsoluteWidth = imageAbsoluteWidth;
		}

		public float getImageAbsoluteHeight() {
			return imageAbsoluteHeight;
		}

		public void setImageAbsoluteHeight(float imageAbsoluteHeight) {
			this.imageAbsoluteHeight = imageAbsoluteHeight;
		}

		public int getImageRowSpan() {
			return imageRowSpan;
		}

		public void setImageRowSpan(int imageRowSpan) {
			this.imageRowSpan = imageRowSpan;
		}
        
        
    }

    public static class TableRow {
        private List<TableColumn> columns;

        private int rowSpan = 1;

        public TableRow() {
        }

        public TableRow(List<TableColumn> columns) {
            this.columns = columns;
        }

        public TableRow(List<TableColumn> columns, int rowSpan) {
            this.columns = columns;
            this.rowSpan = rowSpan;
        }

        public List<TableColumn> getColumns() {
            return columns;
        }

        public void setColumns(List<TableColumn> columns) {
            this.columns = columns;
        }

        public int getRowSpan() {
            return rowSpan;
        }

        public void setRowSpan(int rowSpan) {
            this.rowSpan = rowSpan;
        }
    }
    
    private String title;

    private int[] columnWidths;

    private List<TableRow> rows;
    
    private boolean onNewPage = false;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int[] getColumnWidths() {
        return columnWidths;
    }

    public void setColumnWidths(int[] columnWidths) {
        this.columnWidths = columnWidths;
    }

    public List<TableRow> getRows() {
        return rows;
    }

    public void setRows(List<TableRow> rows) {
        this.rows = rows;
    }

	public boolean isOnNewPage() {
		return onNewPage;
	}

	public void setOnNewPage(boolean onNewPage) {
		this.onNewPage = onNewPage;
	}
    
    
}
