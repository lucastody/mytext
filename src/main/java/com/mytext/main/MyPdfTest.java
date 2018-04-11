package com.mytext.main;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.mytext.pdf.ICustomDocumentElement;
import com.mytext.pdf.IDocument;
import com.mytext.pdf.impl.PdfDocument;

public class MyPdfTest extends PdfDocument {
	
	public MyPdfTest() throws DocumentException {
		super();
	}

	public void create() throws Exception {
		Font bold = new Font();
		bold.setStyle(Font.BOLD);
		
		add(new Header());
		
		lineSeparator();
		
		add(
			table(25f, 25f, 25f, 25f).setWidthPercentage(100f).withNoBorder()
				.add(cell("Coluna A", bold).center().alignMiddle().backgroundColor(BaseColor.LIGHT_GRAY))
				.add(cell("Coluna B", bold).center().alignMiddle().backgroundColor(BaseColor.LIGHT_GRAY))
				.add(cell("Coluna C", bold).center().alignMiddle().backgroundColor(BaseColor.LIGHT_GRAY))
				.add(cell("Coluna D", bold).center().alignMiddle().backgroundColor(BaseColor.LIGHT_GRAY))
				
				.add(cell("Valor A").center())
				.add(cell("Valor B").center())
				.add(cell("Valor C").center())
				.add(cell("Valor D").center())
		);
		
		byte[] b = close();
		FileOutputStream f = new FileOutputStream(new File("/home/dgpcamara/testePdf.pdf"));
		IOUtils.write(b, f);
	}
}


class Header implements ICustomDocumentElement {

	@Override
	public void create(IDocument document) throws DocumentException {
		document.add(document.paragraph(new Font(FontFamily.HELVETICA, 20f, Font.BOLD), "Header").center().setSpacingAfter(10f));
	}
	
}