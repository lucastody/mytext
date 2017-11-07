package com.mytext.pdf;

import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.mytext.pdf.element.ICell;
import com.mytext.pdf.element.IChunk;
import com.mytext.pdf.element.IImage;
import com.mytext.pdf.element.ILineSeparator;
import com.mytext.pdf.element.IParagraph;
import com.mytext.pdf.element.IPhrase;
import com.mytext.pdf.element.ITable;

public interface IDocument {
	
	<T> IDocument add(IElement<T> el) throws DocumentException;
	IDocument add(ICustomDocumentElement el) throws DocumentException;
	IDocument useFont(Font font);
	IDocument clearFont();
	IDocument newLine() throws DocumentException;
	IDocument newPage() throws DocumentException;
	
	IParagraph paragraph(String text);
	IParagraph paragraph(Font font, String text);
	IParagraph paragraph(String pattern, Object ... values);
	IParagraph paragraph(Font font, String pattern, Object ... values);
	IParagraph paragraph(IChunk ... chunk);
	
	IChunk chunk(String text) throws DocumentException;
	IChunk chunk(Font font, String text) throws DocumentException;
	IChunk chunk(String pattern, Object ... values) throws DocumentException;
	IChunk chunk(Font font, String pattern, Object ... values) throws DocumentException;
	
	ITable table(float ... relativeWidths);
	IImage image(String path) throws DocumentException;
	IImage image(byte[] b) throws DocumentException;
	
	ICell cell(String text) throws DocumentException;
	ICell cell(IPhrase phrase) throws DocumentException;
	ICell cell(Font font, String text) throws DocumentException;
	ICell cell(Font font, String pattern, Object ... values) throws DocumentException;
	ICell cell(String pattern, Object ... values) throws DocumentException;
	ICell cell(IChunk... chunk) throws DocumentException;
	ICell cell(ITable table) throws DocumentException;
	ICell cell(IImage image) throws DocumentException;
	ICell cell(IImage image, boolean fit) throws DocumentException;
	
	IPhrase phrase(String text) throws DocumentException;
	IPhrase phrase(Font font, String text) throws DocumentException;
	IPhrase phrase(Font font, String pattern, Object ... values) throws DocumentException;
	IPhrase phrase(String pattern, Object ... values) throws DocumentException;
	IPhrase phrase(IChunk... chunk) throws DocumentException;
	
	ILineSeparator lineSeparator() throws DocumentException;
	
	byte[] close() throws DocumentException, IOException;
}