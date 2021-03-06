package hu.ppke.itk.java2018.jozgy.hf02;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class PolyLine implements Component {
	private ArrayList<Float> pointX = new ArrayList<>();
	private ArrayList<Float> pointY = new ArrayList<>();

	public int getNumberOfPoints() {
		return (pointX.size());
	}

	public float getPointX(int index) {
		return pointX.get(index);
	}

	// Meglévő koordináta módosításához
	public void setPointX(int index, float value) {
		pointX.set(index, value);
	}

	// Új koordináta hozzáadásához
	public void setPointX(float value) {
		pointX.add(value);
	}

	public float getPointY(int index) {
		return pointY.get(index);
	}

	// Meglévő koordináta módosításához
	public void setPointY(int index, float value) {
		pointY.set(index, value);
	}

	// Új koordináta hozzáadásához
	public void setPointY(float value) {
		pointY.add(value);
	}

	@Override
	public void write(Writer output) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(output);
		StringBuilder codeBuilder = new StringBuilder("<polyline points=\"");
		codeBuilder.append(getPointX(0)).append(',').append(getPointY(0));
		for (int i = 1; i < getNumberOfPoints(); i++) {
			codeBuilder.append(' ').append(getPointX(i)).append(',').append(getPointY(i));
		}
		codeBuilder.append("\" style='fill:none;stroke:black;stroke-width:2'/>");
		String code = codeBuilder.toString();
		bufferedWriter.write(code, 0, code.length());
		bufferedWriter.newLine();
		bufferedWriter.flush();
	}

	@Override
	public void translate(float dx, float dy) {
		for (int i = 0; i < pointX.size(); i++) {
			pointX.set(i, pointX.get(i) + dx);
		}

		for (int i = 0; i < pointY.size(); i++) {
			pointY.set(i, pointY.get(i) + dy);
		}
	}

	@Override
	public void flipHorizontal(float axis) {
		for (int i = 0; i < pointY.size(); i++) {
			float newCoordinate = (pointY.get(i) > axis) ? pointY.get(i) + 2 * (axis - pointY.get(i))
					: pointY.get(i) - 2 * (pointY.get(i) - axis);
			pointY.set(i, newCoordinate);
		}
	}

	@Override
	public void flipVertical(float axis) {
		for (int i = 0; i < pointX.size(); i++) {
			float newCoordinate = (pointX.get(i) > axis) ? pointX.get(i) + 2 * (axis - pointX.get(i))
					: pointX.get(i) - 2 * (pointX.get(i) - axis);
			pointX.set(i, newCoordinate);
		}
	}
}
