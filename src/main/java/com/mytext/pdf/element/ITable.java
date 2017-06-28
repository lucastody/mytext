package com.mytext.pdf.element;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.mytext.pdf.ICustomTableElement;
import com.mytext.pdf.IElement;

public interface ITable extends IElement<PdfPTable> {

	ITable add(ICell cell);
	ITable add(IPhrase phrase);
	ITable add(ICustomTableElement el) throws DocumentException;
	
	ITable setWidth(float f);
	ITable setWidthPercentage(float f);
	ITable withNoBorder();
	ITable completeRow();
	ITable emptyRow();
	ITable emptyRow(int colspan);
	ITable setSpacingAfter(float f);
	ITable setSpacingBefore(float f);
	ITable alignLeft();
	ITable alignCenter();
	ITable alignRight();
}
