package com.mytext.pdf.element.impl;

import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.mytext.pdf.IDocument;
import com.mytext.pdf.element.IImage;
import com.mytext.pdf.impl.PdfElement;

public class ImageImpl extends PdfElement<Image> implements IImage {

	public ImageImpl(IDocument document, String path) throws BadElementException, MalformedURLException, IOException {
		super(document, Image.getInstance(path));
	}
	
	public ImageImpl(IDocument document, byte[] b) throws BadElementException, MalformedURLException, IOException {
		super(document, Image.getInstance(b));
	}

	@Override
	public IImage scaleAbsolute(int i, int j) {
		element.scaleAbsolute(i, j);
		return this;
	}

	@Override
	public IImage center() {
		element.setAlignment(Image.ALIGN_CENTER);
		return this;
	}
}