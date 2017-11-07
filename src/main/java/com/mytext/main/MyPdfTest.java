package com.mytext.main;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.mytext.pdf.element.IImage;
import com.mytext.pdf.element.ITable;
import com.mytext.pdf.impl.PdfDocument;

public class MyPdfTest extends PdfDocument {
	
	public MyPdfTest() throws DocumentException {
		super();
	}

	public void create() throws Exception {
		IImage logoDgp = image("http://www.dgp.eb.mil.br/images/simbolo_DGP/logo.png").scaleAbsolute(100, 100).center();
		
		add(table(25f, 75f).setWidthPercentage(100f).withNoBorder()
			.add(cell(logoDgp).center())
			.add(cell(new Font(FontFamily.HELVETICA, 35, Font.BOLDITALIC), "Relatório Teste").alignMiddle()));
		
		ITable table = table(50f, 25f, 25f).setWidthPercentage(100f);
		
		table
			.add(cell("Tabela A").center().colspan(3).alignMiddle()).completeRow()
			.add(cell("Coluna A").alignMiddle().backgroundColor(BaseColor.GREEN))
			.add(cell("Coluna B").alignMiddle().backgroundColor(BaseColor.CYAN))
			.add(cell("Coluna C").alignMiddle().backgroundColor(BaseColor.CYAN));
		
		for(int i = 0; i < 10; i++) {
			table
				.add(cell("Coluna A").alignMiddle())
				.add(cell((i+1 + "")).alignMiddle())
				.add(cell("Coluna C").alignMiddle());
		}
		
		add(table);
		
		byte[] b = close();
		FileOutputStream f = new FileOutputStream(new File("/home/dgpcamara/Área de Trabalho/teste.pdf"));
		IOUtils.write(b, f);
	}
}
