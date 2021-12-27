package si.uni_lj.fri.pbd.mojkvizko;

import java.util.List;

public class KvizModel {
    private String imeKviza;
    private String uporabnik;
    private List<VprasanjeModel> vprasanjeModelList;


    public KvizModel(String imeKviza, String uporabnik, List<VprasanjeModel> vprasanjeModelList) {
        this.imeKviza = imeKviza;
        this.uporabnik = uporabnik;
        this.vprasanjeModelList = vprasanjeModelList;
    }

    public String getImeKviza() {
        return imeKviza;
    }

    public void setImeKviza(String imeKviza) {
        this.imeKviza = imeKviza;
    }

    public List<VprasanjeModel> getVprasanjeModelList() {
        return vprasanjeModelList;
    }

    public void setVprasanjeModelList(List<VprasanjeModel> vprasanjeModelList) {
        this.vprasanjeModelList = vprasanjeModelList;
    }

    public String getUporabnik() {
        return uporabnik;
    }

    public void setUporabnik(String uporabnik) {
        this.uporabnik = uporabnik;
    }
}
