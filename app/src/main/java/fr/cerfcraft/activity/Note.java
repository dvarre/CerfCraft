package fr.cerfcraft.activity;

public class Note {
    private String name;
    private String icone;
    private String noteTxt;



    public Note(String name, String icone, String noteTxt){
        this.name = name;
        this.icone = icone;
        this.noteTxt = noteTxt;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
