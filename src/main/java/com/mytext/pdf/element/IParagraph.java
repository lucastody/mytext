package com.mytext.pdf.element;

import com.itextpdf.text.Paragraph;
import com.mytext.pdf.IElement;

public interface IParagraph extends IElement<Paragraph> {

	IParagraph center();

	IParagraph setSpacingBefore(float f);

	IParagraph setSpacingAfter(float f);

	IParagraph setIndentationLeft(int i);
	
}