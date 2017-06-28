package com.mytext.pdf.impl;

import com.mytext.pdf.IDocument;
import com.mytext.pdf.IElement;

public abstract class PdfElement<T> implements IElement<T> {
	protected IDocument document;
	protected T element;
	
	protected PdfElement(IDocument document, T element) {
		super();
		this.document = document;
		this.element = element;
	}
	
	public PdfElement() {
		super();
	}
	
	public T element() {
		return element;
	}
}