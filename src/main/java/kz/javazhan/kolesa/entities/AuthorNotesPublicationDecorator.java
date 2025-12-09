package kz.javazhan.kolesa.entities;

public class AuthorNotesPublicationDecorator extends PublicationDecorator{
    String authorNotes;

    public AuthorNotesPublicationDecorator(PublicationI decoratedPublication, String authorNotes) {
        super(decoratedPublication);
        this.authorNotes = authorNotes;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " | Author Notes: " + authorNotes;
    }
}
