package com.mytext.pdf;

import com.itextpdf.text.DocumentException;

public interface ICustomDocumentElement {
	void create(IDocument document) throws DocumentException;
}