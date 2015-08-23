package br.com.jonyfs.spring.boot.data.embedded.mongodb.domain;

public class Pendrive extends Product {

	Integer gigabytes;

	public Pendrive(Integer id, String company, String name, Integer gigabytes) {
		super(id, company, name, Type.PENDRIVE);
		this.gigabytes = gigabytes;
	}

	public Integer getGigabytes() {
		return gigabytes;
	}

}
