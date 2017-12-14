package org.dummycreator.helperutils;

public class TestPojo {
	private Long id;
	
	public TestPojo(Integer id) {
		this.id = id.longValue();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return String.format("{id : %s}", this.id);
	}
}
