package com.mytext.pdf.element.impl;

import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.mytext.pdf.IDocument;
import com.mytext.pdf.element.ILineSeparator;
import com.mytext.pdf.impl.PdfElement;

public class LineSeparatorImpl extends PdfElement<LineSeparator> implements ILineSeparator {

	public LineSeparatorImpl(IDocument document) {
		super(document, new LineSeparator());
	}

	@Override
	public ILineSeparator dotted() {
		element = new DottedLineSeparator();
		return this;
	}
}