package com.mytext.pdf;

import com.itextpdf.text.DocumentException;
import com.mytext.pdf.element.ITable;

public interface ICustomTableElement {
	void create(IDocument document, ITable table) throws DocumentException;
}