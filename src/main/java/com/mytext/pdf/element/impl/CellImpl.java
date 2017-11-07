package com.mytext.pdf.element.impl;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.mytext.pdf.IDocument;
import com.mytext.pdf.element.ICell;
import com.mytext.pdf.element.IChunk;
import com.mytext.pdf.element.IImage;
import com.mytext.pdf.element.IPhrase;
import com.mytext.pdf.element.ITable;
import com.mytext.pdf.impl.PdfElement;

public class CellImpl extends PdfElement<PdfPCell> implements ICell {

	public CellImpl(IDocument document, String text) {
		super(document, new PdfPCell(new Phrase(text)));
	}
	
	public CellImpl(IDocument document, Font font, String text) {
		super(document, new PdfPCell(new Phrase(text, font)));
	}
	
	public CellImpl(IDocument document, ITable table) {
		super(document, new PdfPCell(table.element()));
	}
	
	public CellImpl(IDocument document, IChunk[] chunk) {
		super(document, new PdfPCell());
		element.setNoWrap(true);
		
		for(IChunk c : chunk) {
			element.addElement(c.element());
		}
	}

	public CellImpl(IDocument document, IPhrase phrase) {
		super(document, new PdfPCell(phrase.element()));
	}
	
	public CellImpl(IDocument document, IImage image) {
		super(document, new PdfPCell(image.element()));
	}
	
	public CellImpl(IDocument document, IImage image, boolean fit) {
		super(document, new PdfPCell(image.element(), fit));
	}

	@Override
	public ICell withNoBorder() {
		element.setBorder(PdfPCell.NO_BORDER);
		return this;
	}

	@Override
	public ICell alignMiddle() {
		element.setVerticalAlignment(Element.ALIGN_MIDDLE);
		return this;
	}
	
	@Override
	public ICell alignTop() {
		element.setVerticalAlignment(Element.ALIGN_TOP);
		return this;
	}

	@Override
	public ICell center() {
		element.setHorizontalAlignment(Element.ALIGN_CENTER);
		return this;
	}
	
	@Override
	public ICell right() {
		element.setHorizontalAlignment(Element.ALIGN_RIGHT);
		return this;
	}

	@Override
	public ICell colspan(Integer i) {
		if(i != null) {
			element.setColspan(i);
		}
		
		return this;
	}
	
	@Override
	public ICell rowspan(Integer i) {
		if(i != null) {
			element.setRowspan(i);
		}
		
		return this;
	}
	
	@Override
	public ICell add(Font font, String text) {
		element.addElement(new Chunk(text, font));
		return this;
	}

	@Override
	public ICell padding(float i) {
		element.setPadding(i);
		return this;
	}

	@Override
	public ICell nowrap() {
		element.setNoWrap(true);
		return this;
	}

	@Override
	public ICell backgroundColor(BaseColor baseColor) {
		element.setBackgroundColor(baseColor);
		return this;
	}
	
	@Override
	public ICell borderColor(BaseColor baseColor) {
		element.setBorderColor(baseColor);
		return this;
	}
}