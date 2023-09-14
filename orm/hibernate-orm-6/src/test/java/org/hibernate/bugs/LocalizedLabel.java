package org.hibernate.bugs;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Locale;

@Embeddable
public class LocalizedLabel {
    @Column(name = "IDENTIFIER")
    String identifier;

    @Column(name = "LANGUAGE")
    Locale language;

    @Column(name = "LABEL")
    String label;

    public LocalizedLabel(){
        super();
    }

    public LocalizedLabel(String identifier,Locale locale,String label){
        this.identifier= identifier;
        this.language = locale;
        this.label=label;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Locale getLanguage() {
        return language;
    }

    public String getLabel() {
        return label;
    }
}
