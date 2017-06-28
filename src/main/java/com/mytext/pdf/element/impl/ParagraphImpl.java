package com.mytext.pdf.element.impl;

import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.mytext.pdf.IDocument;
import com.mytext.pdf.element.IChunk;
import com.mytext.pdf.element.IParagraph;
import com.mytext.pdf.impl.PdfElement;

public class ParagraphImpl extends PdfElement<Paragraph> implements IParagraph {
	
	public ParagraphImpl(IDocument document, Font font, String text) {
		super(document, new Paragraph(text, font));
	}
	
	public ParagraphImpl(IDocument document, String text) {
		super(document, new Paragraph(text));
	}

	public ParagraphImpl(IDocument document, IChunk[] chunk) {
		super(document, new Paragraph());
		
		for(IChunk c : chunk) {
			element.add(c.element());
		}
	}

	@Override
	public IParagraph center() {
		element.setAlignment(Paragraph.ALIGN_CENTER);
		return this;
	}

	@Override
	public IParagraph setSpacingBefore(float f) {
		element.setSpacingBefore(f);
		return this;
	}

	@Override
	public IParagraph setSpacingAfter(float f) {
		element.setSpacingAfter(f);
		return this;
	}

	@Override
	public IParagraph setIndentationLeft(int i) {
		element.setIndentationLeft(i);
		return this;
	}
}