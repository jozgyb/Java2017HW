package hu.ppke.itk.java2018.jozgy.hf02;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class Group implements Component {

	private ArrayList<Component> groupMembers = new ArrayList<>();

	public void addComponent(Component newComponent) {
		groupMembers.add(newComponent);
	}

	public Component getLastComponent() {
		return groupMembers.get(groupMembers.size() - 1);
	}

	@Override
	public void write(Writer output) throws IOException {
		BufferedWriter groupBufferedWriter = new BufferedWriter(output);
		groupBufferedWriter.write("<g>");
		groupBufferedWriter.newLine();
		groupBufferedWriter.flush();
		for (Component iterator : groupMembers) {
			iterator.write(output);
		}
		groupBufferedWriter.write("</g>");
		groupBufferedWriter.newLine();
		groupBufferedWriter.flush();

	}

	@Override
	public void translate(float dx, float dy) {
		for (int i = 0; i < groupMembers.size(); i++) {
			groupMembers.get(i).translate(dx, dy);
		}
	}

	@Override
	public void flipHorizontal(float axis) {
		for (int i = 0; i < groupMembers.size(); i++) {
			groupMembers.get(i).flipHorizontal(axis);
		}
	}

	@Override
	public void flipVertical(float axis) {
		for (int i = 0; i < groupMembers.size(); i++) {
			groupMembers.get(i).flipVertical(axis);
		}
	}

}
