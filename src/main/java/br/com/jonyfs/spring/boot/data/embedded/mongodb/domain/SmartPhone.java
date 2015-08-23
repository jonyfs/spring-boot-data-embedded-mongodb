package br.com.jonyfs.spring.boot.data.embedded.mongodb.domain;

public class SmartPhone extends Product {
	Integer screenSize;
	boolean camera;
	boolean frontalCamera;

	public SmartPhone(Integer id, String company, String name, Integer screenSize, boolean camera, boolean frontalCamera) {
		super(id, company, name, Type.SMARTPHONE);
		this.company = company;
		this.screenSize = screenSize;
		this.camera = camera;
		this.frontalCamera = frontalCamera;
	}

}
