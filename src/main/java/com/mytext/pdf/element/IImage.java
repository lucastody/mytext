package com.mytext.pdf.element;

import com.itextpdf.text.Image;
import com.mytext.pdf.IElement;

public interface IImage extends IElement<Image> {

	IImage scaleAbsolute(int i, int j);
	IImage center();
	
}