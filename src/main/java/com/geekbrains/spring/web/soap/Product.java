package com.geekbrains.spring.web.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "product", propOrder = {
        "id",
        "title",
        "price"
})

public class Product {

    protected long id;
    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected Integer price;

    /**
     * Gets the value of the id property.
     *
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the username property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the username property.
     *
     * @param title
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the value of the password property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Sets the value of the password property.
     *
     * @param price
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPrice(Integer price) {
        this.price = price;
    }




}
