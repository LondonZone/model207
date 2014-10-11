package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classes.User;

public class FileHelper<T extends User> {

	/** An instance of the class being managed by this FileIO manager. */
	private final T classType;

	/** The contents (people) being managed by this FileIO manager. */
	private final List<T> people;

	/**
	 * Populates the list of people using stored data, if it exists.
	 *
	 * @param dir
	 *            The file directory.
	 * @param fileName
	 *            The file name.
	 * @param classType
	 *            The type of Object to create/manage.
	 * @throws IOException
	 *             Thrown if an error occurs with the File.
	 */
	public FileHelper(File dir, String fileName, T classType)
			throws IOException {
		people = new ArrayList<T>();
		this.classType = classType;

		File file = new File(dir, fileName);

		// Read the file if it exists, or create a new one if it doesn't
		if (file.exists())
			read(file.getPath());
		else
			file.createNewFile();
	}

	/**
	 * Reads the CSV file at path filePath, creating the objects and adding them
	 * to the people list.
	 *
	 * @param filePath
	 *            The location path where the file is located.
	 * @throws IOException
	 *             Thrown if there is an error with reading the file.
	 */
	public void read(String filePath) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		Scanner in = new Scanner(fis);
		String[] record;

		// Parse each line: instantiate the user, and add it to the list
		while (in.hasNextLine()) {
			record = in.nextLine().split(", ");
			// addPerson(classType.scan(record));
		}

		fis.close();
		in.close();
	}

	/**
	 * Saves the file to the location filePath. If append is true, appends to
	 * the file, otherwise overwrites the file's contents.
	 *
	 * @param filePath
	 *            The location path where the file is located.
	 * @param append
	 *            Append to the file if true, otherwise overwrite it.
	 * @throws IOException
	 *             Thrown if there is an error while writing.
	 */
	public void save(String filePath, boolean append) throws IOException {
		FileOutputStream out = new FileOutputStream(filePath, append);

		// Write the CSV formatted content to the file, 1 person per line
		for (T t : people)
			out.write((t + "\n").getBytes());

		out.flush();
		out.close();
	}

	/**
	 * Puts the person in the List.
	 *
	 * @param person
	 *            The person to put into the List.
	 */
	public void addPerson(T person) {
		people.add(person);
	}

	/** Clears the list of people. */
	public void clearPeopleList() {
		people.clear();
	}
}
