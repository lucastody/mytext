package com.mytext.main;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;

import com.itextpdf.text.DocumentException;
import com.mytext.pdf.impl.PdfDocument;

public class MyPdfTest extends PdfDocument {
	
	public MyPdfTest() throws DocumentException {
		super();
	}

	public void create() throws Exception {
		add(paragraph("Teste"));
		add(paragraph("Teste 2"));
		
		byte[] b = close();
		FileOutputStream f = new FileOutputStream(new File("C:/testePdf.pdf"));
		IOUtils.write(b, f);
	}
}
