package main.java.net.ludocrypt.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;

import com.google.common.io.Files;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import main.java.net.ludocrypt.gui.Text;

public class Indexer {

	private Text consoleText;

	public Indexer(Text consoleText) {
		this.consoleText = consoleText;
	}

	public void run(File assetsFolder, File index, File destination) {
		File objects = new File(assetsFolder, "objects");
		BufferedReader buffer = null;

		try {
			buffer = Files.newReader(index, StandardCharsets.UTF_8);
			JsonObject json = GsonMagic.parse((Reader) buffer);
			JsonObject jsonObjects = GsonMagic.getAsJsonObject(json, "objects", (JsonObject) null);
			if (jsonObjects != null) {
				Iterator<Entry<String, JsonElement>> var8 = jsonObjects.entrySet().iterator();
				while (var8.hasNext()) {
					Entry<String, JsonElement> hashIndex = (Entry<String, JsonElement>) var8.next();
					JsonObject jsonHashIndex = (JsonObject) hashIndex.getValue();
					String outputName = (String) hashIndex.getKey();
					String hash = GsonMagic.getAsString(jsonHashIndex, "hash");
					File objectFile = new File(objects, hash.substring(0, 2) + "/" + hash);
					copyFile(objectFile, destination, outputName);
				}
			}
		} catch (JsonParseException var19) {
			System.out.println(consoleText);
			consoleText.addLog("Json broken or not a Json file");
		} catch (FileNotFoundException var20) {
			consoleText.addLog("Object Index not found");
		} finally {
			consoleText.addLog("Finished Indexing");
		}
	}

	public void copyFile(File file, File destination, String name) {
		File dest = new File(destination, name);
		try {
			FileUtils.copyFile(file, dest);
			System.out.println("Copied \"" + file.getPath() + "\" to \"" + dest.getAbsolutePath() + "\" Succesfully");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
