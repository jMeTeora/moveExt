package jmeteora.moveext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class MoveExt {
	static File dir;

	public static void main(String[] args) {
		if (args.length == 0) {
			dir = new File(new File("").getAbsolutePath());
		} else if (args.length == 1) {
			dir = new File(args[0]);
		} else {
			throw new IllegalArgumentException("args count NOT 0||1 ");
		}
		move(dir);
	}

	private static void move(File input) {
		File[] files = input.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				String name = file.getName();
				String[] data = name.split("\\.");

				String ext = data[data.length - 1];

				File dir = new File(file.getParentFile(), ext);
				try {
					if (!dir.exists()) {
						dir.mkdirs();
					}
					Files.move(file.toPath(), new File(dir, name).toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
