package com.mytext.pdf.element.impl;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.mytext.pdf.IDocument;
import com.mytext.pdf.element.IChunk;
import com.mytext.pdf.impl.PdfElement;

public class ChunkImpl extends PdfElement<Chunk> implements IChunk {

	public ChunkImpl(IDocument document, String text) {
		super(document, new Chunk(text));
	}
	
	public ChunkImpl(IDocument document, Font font, String text) {
		super(document, new Chunk(text, font));
	}

	@Override
	public IChunk underline() {
		element.setUnderline(0.1f, -2f);
		return this;
	}
}