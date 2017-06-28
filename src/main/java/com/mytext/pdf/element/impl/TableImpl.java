package com.mytext.pdf.element.impl;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.mytext.pdf.ICustomTableElement;
import com.mytext.pdf.IDocument;
import com.mytext.pdf.element.ICell;
import com.mytext.pdf.element.IPhrase;
import com.mytext.pdf.element.ITable;
import com.mytext.pdf.impl.PdfElement;

public class TableImpl extends PdfElement<PdfPTable> implements ITable {

	public TableImpl(IDocument document, float[] relativeWidths) {
		super(document, new PdfPTable(relativeWidths));
		alignLeft();
	}

	@Override
	public ITable setWidth(float f) {
		element.setTotalWidth(f);
		return this;
	}
	
	@Override
	public ITable setWidthPercentage(float f) {
		element.setWidthPercentage(f);
		return this;
	}

	@Override
	public ITable withNoBorder() {
		element.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		return this;
	}

	@Override
	public ITable add(ICell cell) {
		element.addCell(cell.element());
		return this;
	}

	@Override
	public ITable completeRow() {
		element.completeRow();
		return this;
	}

	@Override
	public ITable add(IPhrase phrase) {
		element.addCell(phrase.element());
		return this;
	}

	@Override
	public ITable emptyRow() {
		emptyRow(0);
		return this;
	}
	
	@Override
	public ITable emptyRow(int colspan) {
		PdfPCell defaultCell = element.getDefaultCell();
		defaultCell.setFixedHeight(15f);
		
		if(colspan > 0) {
			defaultCell.setColspan(colspan);
		}
		
		element.addCell(defaultCell);
		completeRow();
		return this;
	}

	@Override
	public ITable setSpacingAfter(float f) {
		element.setSpacingAfter(f);
		return this;
	}
	
	@Override
	public ITable setSpacingBefore(float f) {
		element.setSpacingBefore(f);
		return this;
	}

	@Override
	public ITable add(ICustomTableElement el) throws DocumentException {
		el.create(document, this);
		return this;
	}

	@Override
	public ITable alignLeft() {
		element.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		return this;
	}

	@Override
	public ITable alignCenter() {
		element.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);
		return this;
	}

	@Override
	public ITable alignRight() {
		element.setHorizontalAlignment(PdfPTable.ALIGN_RIGHT);
		return this;
	}
}