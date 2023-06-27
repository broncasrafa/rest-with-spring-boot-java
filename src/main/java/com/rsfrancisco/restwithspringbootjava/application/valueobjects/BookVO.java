package com.rsfrancisco.restwithspringbootjava.application.valueobjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@JsonPropertyOrder({ "id", "title", "author", "price", "launch_date" })
public class BookVO extends RepresentationModel<BookVO> implements Serializable {
    private static final long serialVersionUID = 1L;


    @Mapping("id")
    @JsonProperty("id")
    private Long key;
    private String author;
    private String title;
    private Double price;
    @JsonProperty("launch_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
    private Date lauchDate;

    public BookVO() {}

    public Long getKey() {
        return key;
    }
    public void setKey(Long key) {
        this.key = key;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getLauchDate() {
        return lauchDate;
    }
    public void setLauchDate(Date lauchDate) {
        this.lauchDate = lauchDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BookVO bookVO = (BookVO) o;
        return Objects.equals(key, bookVO.key) &&
                Objects.equals(author, bookVO.author) &&
                Objects.equals(title, bookVO.title) &&
                Objects.equals(price, bookVO.price) &&
                Objects.equals(lauchDate, bookVO.lauchDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key, author, title, price, lauchDate);
    }
}
