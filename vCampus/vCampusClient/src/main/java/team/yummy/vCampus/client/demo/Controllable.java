package team.yummy.vCampus.client.demo;

import team.yummy.vCampus.client.api.Api;

public interface Controllable {
    public void setStageController(StageController stageController);
    public void setApi(Api api);
    public void setAuthData(String authData);
}
