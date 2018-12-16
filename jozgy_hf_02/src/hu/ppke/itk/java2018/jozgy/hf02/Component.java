package hu.ppke.itk.java2018.jozgy.hf02;

import java.io.IOException;
import java.io.Writer;

public interface Component {
	public void write(Writer output) throws IOException;
	public void translate(float dx, float dy);
	public void flipHorizontal(float axis);
	public void flipVertical(float axis);
}
