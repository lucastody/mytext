package com.mytext.pdf.element;

import com.itextpdf.text.Chunk;
import com.mytext.pdf.IElement;

public interface IChunk extends IElement<Chunk> {
	
	IChunk underline();
	
}