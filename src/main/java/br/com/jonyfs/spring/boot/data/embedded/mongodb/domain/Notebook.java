package br.com.jonyfs.spring.boot.data.embedded.mongodb.domain;

public class Notebook extends Product {

	Integer screenSize;

	public Notebook(Integer id, String company, String name, Integer screenSize) {
		super(id, company, name, Type.NOTEBOOK);
		this.screenSize = screenSize;
	}

	public Integer getScreenSize() {
		return screenSize;
	}
}
