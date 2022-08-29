package br.com.joao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "book")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name= "author", nullable = false, length = 180)
	private String author;
	@Column
	private String title;
	@Column(name="launch_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date launchDate;
	@Column
	private Double price;
	@Transient
	private String currency;
	@Transient
	private String environment;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Book bookModel = (Book) o;

		if (id != bookModel.id) return false;
		if (author != null ? !author.equals(bookModel.author) : bookModel.author != null) return false;
		if (launchDate != null ? !launchDate.equals(bookModel.launchDate) : bookModel.launchDate != null) return false;
		if (price != null ? !price.equals(bookModel.price) : bookModel.price != null) return false;
		if (title != null ? !title.equals(bookModel.title) : bookModel.title != null) return false;
		if (currency != null ? !currency.equals(bookModel.currency) : bookModel.currency != null) return false;
		return environment != null ? environment.equals(bookModel.environment) : bookModel.environment == null;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (author != null ? author.hashCode() : 0);
		result = 31 * result + (launchDate != null ? launchDate.hashCode() : 0);
		result = 31 * result + (price != null ? price.hashCode() : 0);
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (currency != null ? currency.hashCode() : 0);
		result = 31 * result + (environment != null ? environment.hashCode() : 0);
		return result;
	}
}
