package kz.javazhan.kolesa.entities;

public class AuthorNotesPublicationDecorator extends PublicationDecorator{
    String authorNotes;

    public AuthorNotesPublicationDecorator(PublicationEntity decoratedPublication, String notes){
        super(decoratedPublication);
        this.authorNotes = notes;
    }

    public String getDescription(){
        return super.getDescription() + " | Author Notes: " + authorNotes;
    }
}
