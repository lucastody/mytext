package com.mytext.pdf.element;

import com.itextpdf.text.pdf.draw.LineSeparator;
import com.mytext.pdf.IElement;

public interface ILineSeparator extends IElement<LineSeparator> {

	ILineSeparator dotted();
	
}