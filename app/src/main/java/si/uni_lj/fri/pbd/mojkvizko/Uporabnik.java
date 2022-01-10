package si.uni_lj.fri.pbd.mojkvizko;

public class Uporabnik {
    private String email;
    private String ime;

    public Uporabnik() {

    }

    public Uporabnik(String email, String ime) {
        this.email = email;
        this.ime = ime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
}
