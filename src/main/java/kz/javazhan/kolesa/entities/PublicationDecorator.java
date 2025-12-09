package kz.javazhan.kolesa.entities;


public class PublicationDecorator implements PublicationI{
    PublicationEntity publication;


    public PublicationDecorator(PublicationEntity decoratedPublication){
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

}
