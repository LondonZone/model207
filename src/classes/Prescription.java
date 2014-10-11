package classes;

public class Prescription extends Record {

	private String name;
	private String instructions;

	public Prescription(String name, String instructions) {
		this.name = name;
		this.instructions = instructions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

}
