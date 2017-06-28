package com.mytext.pdf.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.mytext.pdf.ICustomDocumentElement;
import com.mytext.pdf.IDocument;
import com.mytext.pdf.IElement;
import com.mytext.pdf.element.ICell;
import com.mytext.pdf.element.IChunk;
import com.mytext.pdf.element.IImage;
import com.mytext.pdf.element.ILineSeparator;
import com.mytext.pdf.element.IParagraph;
import com.mytext.pdf.element.IPhrase;
import com.mytext.pdf.element.ITable;
import com.mytext.pdf.element.impl.CellImpl;
import com.mytext.pdf.element.impl.ChunkImpl;
import com.mytext.pdf.element.impl.ImageImpl;
import com.mytext.pdf.element.impl.LineSeparatorImpl;
import com.mytext.pdf.element.impl.ParagraphImpl;
import com.mytext.pdf.element.impl.PhraseImpl;
import com.mytext.pdf.element.impl.TableImpl;

public class PdfDocument implements IDocument {
	private static final Logger LOG = Logger.getLogger(PdfDocument.class.getName());
			
	protected Document document;
	protected PdfWriter writer;
	private ByteArrayOutputStream out;
	private Font defaultFont = new Font(FontFamily.HELVETICA, 10.0f);
	private Font customFont = null;
	
	protected enum Orientation {
		PORTRAIT, LANDSCAPE
	}
	
	public PdfDocument() throws DocumentException {
		super();
		document = new Document();
		out = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, out);
		writer.setPageEvent(new Footer());
		document.open();
	}
	
	public PdfDocument(Orientation orientation) throws DocumentException {
		super();
		
		if(Orientation.LANDSCAPE.equals(orientation)) {
			document = new Document(PageSize.A4.rotate());
		} else {
			document = new Document();
		}
		
		out = new ByteArrayOutputStream();
		writer = PdfWriter.getInstance(document, out);
		writer.setPageEvent(new Footer());
		document.open();
	}
	
	class Footer extends PdfPageEventHelper {
		
		@Override
		public void onEndPage(PdfWriter writer, Document document) {
			PdfContentByte cb = writer.getDirectContent();
	        Phrase footer = new Phrase("" + writer.getPageNumber());
	        
	        float x = document.right();
	        float y = document.bottom() - 10;
	        ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, footer, x, y, 0);
		}
	}
	
	@Override
	public byte[] close() throws DocumentException {
		try {
			document.close();
			out.close();
			return out.toByteArray();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			throw new DocumentException(e);
		}
	}
	
	@Override
	public <T> IDocument add(IElement<T> el) throws DocumentException {
		document.add((Element) el.element());
		return this;
	}
	
	@Override
	public IDocument useFont(Font font) {
		customFont = font;
		return this;
	}
	
	@Override
	public IDocument clearFont() {
		customFont = null;
		return this;
	}
	
	private Font getFont() {
		return getFont(null);
	}
	
	private Font getFont(Font font) {
		if(font != null) {
			return font;
		}
		
		if(customFont != null) {
			return customFont;
		}
		
		return defaultFont;
	}
	
	@Override
	public IDocument add(ICustomDocumentElement el) throws DocumentException {
		el.create(this);
		return this;
	}

	@Override
	public IParagraph paragraph(String text) {
		return new ParagraphImpl(this, getFont(), getValue(text));
	}
	
	@Override
	public IParagraph paragraph(Font font, String text) {
		return new ParagraphImpl(this, getFont(font), getValue(text));
	}
	
	@Override
	public IParagraph paragraph(String pattern, Object ... values) {
		return new ParagraphImpl(this, getFont(), String.format(pattern, getValue(values)));
	}
	
	@Override
	public IParagraph paragraph(Font font, String pattern, Object ... values) {
		return new ParagraphImpl(this, getFont(font), String.format(pattern, getValue(values)));
	}
	
	@Override
	public IParagraph paragraph(IChunk... chunk) {
		return new ParagraphImpl(this, chunk);
	}

	@Override
	public ITable table(float ... relativeWidths) {
		if(relativeWidths == null || relativeWidths.length == 0) {
			throw new IllegalArgumentException("The relative widths for the table columns is required.");
		}
		
		return new TableImpl(this, relativeWidths);
	}
	
	@Override
	public IImage image(String path) throws DocumentException {
		try {
			return new ImageImpl(this, path);
		} catch (IOException e) {
			throw new DocumentException(e);
		}
	}
	
	@Override
	public IImage image(byte[] b) throws DocumentException {
		try {
			return new ImageImpl(this, b);
		} catch (IOException e) {
			throw new DocumentException(e);
		}
	}

	@Override
	public IDocument newLine() throws DocumentException {
		document.add(Chunk.NEWLINE);
		return this;
	}
	
	@Override
	public IDocument newPage() throws DocumentException {
		document.newPage();
		return this;
	}
	
	@Override
	public ILineSeparator lineSeparator() throws DocumentException {
		return new LineSeparatorImpl(this);
	}
	
	@Override
	public ICell cell(String text) throws DocumentException {
		return new CellImpl(this, getFont(), getValue(text));
	}
	
	@Override
	public ICell cell(IPhrase phrase) throws DocumentException {
		return new CellImpl(this, phrase);
	}
	
	@Override
	public ICell cell(Font font, String text) throws DocumentException {
		return new CellImpl(this, getFont(font), getValue(text));
	}
	
	@Override
	public ICell cell(Font font, String pattern, Object ... values) throws DocumentException {
		return new CellImpl(this, getFont(font), String.format(pattern, getValue(values)));
	}
	
	@Override
	public ICell cell(String pattern, Object ... values) throws DocumentException {
		return new CellImpl(this, getFont(), String.format(pattern, getValue(values)));
	}
	
	@Override
	public ICell cell(IChunk... chunk) {
		return new CellImpl(this, chunk);
	}
	
	@Override
	public ICell cell(ITable table) {
		return new CellImpl(this, table);
	}
	
	@Override
	public IPhrase phrase(String text) throws DocumentException {
		return new PhraseImpl(this, getFont(), getValue(text));
	}
	
	@Override
	public IPhrase phrase(Font font, String text) throws DocumentException {
		return new PhraseImpl(this, getFont(font), getValue(text));
	}
	
	@Override
	public IPhrase phrase(Font font, String pattern, Object ... values) throws DocumentException {
		return new PhraseImpl(this, getFont(font), String.format(pattern, getValue(values)));
	}
	
	@Override
	public IPhrase phrase(String pattern, Object ... values) throws DocumentException {
		return new PhraseImpl(this, getFont(), String.format(pattern, getValue(values)));
	}
	
	@Override
	public IPhrase phrase(IChunk... chunk) throws DocumentException {
		return new PhraseImpl(this, chunk);
	}

	@Override
	public IChunk chunk(String text) throws DocumentException {
		return new ChunkImpl(this, getValue(text));
	}

	@Override
	public IChunk chunk(Font font, String text) throws DocumentException {
		return new ChunkImpl(this, font, getValue(text));
	}
	
	@Override
	public IChunk chunk(String pattern, Object ... values) throws DocumentException {
		return new ChunkImpl(this, getFont(), String.format(pattern, getValue(values)));
	}
	
	@Override
	public IChunk chunk(Font font, String pattern, Object ... values) throws DocumentException {
		return new ChunkImpl(this, getFont(font), String.format(pattern, getValue(values)));
	}
	
	private String getValue(String text) {
		return (text != null) ? text : " ";
	}
	
	private Object[] getValue(Object ... values) {
		return (values != null) ? values : new ArrayList<>().toArray();
	}
}