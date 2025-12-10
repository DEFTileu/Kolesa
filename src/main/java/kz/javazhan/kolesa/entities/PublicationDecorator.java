package kz.javazhan.kolesa.entities;


import java.time.LocalDateTime;

public class PublicationDecorator implements PublicationI{
    PublicationI publication;


    public PublicationDecorator(PublicationI decoratedPublication){
        this.publication = decoratedPublication;
    }
    @Override
    public String getTitle(){
        return publication.getTitle();
    }

    @Override
    public String getDescription(){
        return publication.getDescription();
    }

    @Override
    public String getContent(){
        return publication.getContent();
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return publication.getCreatedAt();
    }


}
