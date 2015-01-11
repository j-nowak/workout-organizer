package models;

public class MuscleGroup {

	private String muscleName;

	public String getMuscleName() {
		return muscleName;
	}

	public void setMuscleName(String muscleName) {
		this.muscleName = muscleName;
	}
	
	@Override
	public String toString() {
		return muscleName;
	}

}
