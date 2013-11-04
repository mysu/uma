package models.uma;


public enum Gender {
	Female(0), Male(1);
	private int id;

	private Gender(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static Gender int2Gender(int id) {
		switch (id) {
		case 0:
			return Female;
		case 1:
			return Male;
		default:
			throw new IllegalArgumentException("Gender ID unknown");
		}
	}
}
