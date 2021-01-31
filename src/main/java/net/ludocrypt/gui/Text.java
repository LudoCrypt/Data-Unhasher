package main.java.net.ludocrypt.gui;

public class Text {

	public String text = "";

	public Text(String text) {
		this.text = text;
	}

	public void addLog(String log) {
		if (text.equals("")) {
			text = log;
		} else {
			text = String.join("", text, ", and ", log);
		}
	}

	public void clear() {
		text = "";
	}

	@Override
	public String toString() {
		return text;
	}

}
