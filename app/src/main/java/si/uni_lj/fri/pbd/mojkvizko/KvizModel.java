package si.uni_lj.fri.pbd.mojkvizko;

import java.util.List;

public class KvizModel {
    private String imeKviza;
    private List<VprasanjeModel> vprasanjeModelList;

    public KvizModel(String imeKviza, List<VprasanjeModel> vprasanjeModelList) {
        this.imeKviza = imeKviza;
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
}
