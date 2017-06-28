package com.mytext.pdf.element;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.mytext.pdf.IElement;

public interface ICell extends IElement<PdfPCell> {
	
	ICell withNoBorder();
	ICell center();
	ICell right();
	ICell alignMiddle();
	ICell colspan(Integer i);
	ICell rowspan(Integer i);
	ICell add(Font font, String text);
	ICell padding(float i);
	ICell alignTop();
	ICell nowrap();
	ICell backgroundColor(BaseColor baseColor);
	ICell borderColor(BaseColor white);
}
