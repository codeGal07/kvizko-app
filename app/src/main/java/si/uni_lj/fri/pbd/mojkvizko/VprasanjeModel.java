package si.uni_lj.fri.pbd.mojkvizko;

public class VprasanjeModel {
    private String vprasanje;
    private String odgovor;


    public VprasanjeModel(String vprasanje, String odgovor) {
        this.vprasanje = vprasanje;
        this.odgovor = odgovor;

    }

    public String getVprasanje() {
        return vprasanje;
    }

    public void setVprasanje(String vprasanje) {
        this.vprasanje = vprasanje;
    }

    public String getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(String odgovor) {
        this.odgovor = odgovor;
    }
}
