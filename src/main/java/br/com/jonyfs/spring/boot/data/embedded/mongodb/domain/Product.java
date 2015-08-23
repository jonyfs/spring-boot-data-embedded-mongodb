package br.com.jonyfs.spring.boot.data.embedded.mongodb.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@Document(collection = "products")
@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(Notebook.class), @JsonSubTypes.Type(SmartPhone.class), @JsonSubTypes.Type(Pendrive.class) })
public abstract class Product extends BaseDocument<Integer> {
	String name;
	String company;
	Type type;

	public Type getType() {
		return type;
	}

	public Product(Integer id, String company, String name, Type type) {
		super.id = id;
		this.company = company;
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getCompany() {
		return company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", company=" + company + ", type=" + type + ", id=" + id + "]";
	}

}
