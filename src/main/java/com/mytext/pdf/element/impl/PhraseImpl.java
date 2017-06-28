package com.mytext.pdf.element.impl;

import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.mytext.pdf.IDocument;
import com.mytext.pdf.element.IChunk;
import com.mytext.pdf.element.IPhrase;
import com.mytext.pdf.impl.PdfElement;

public class PhraseImpl extends PdfElement<Phrase> implements IPhrase {

	public PhraseImpl(IDocument document, String text) {
		super(document, new Phrase(text));
	}
	
	public PhraseImpl(IDocument document, Font font, String text) {
		super(document, new Phrase(text, font));
	}

	public PhraseImpl(IDocument document, IChunk[] chunk) {
		super(document, new Phrase());
		
		for(IChunk c : chunk) {
			element.add(c.element());
		}
	}
}