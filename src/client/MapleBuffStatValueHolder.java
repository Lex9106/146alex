package client;

import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import server.MapleStatEffect;

public class MapleBuffStatValueHolder {

    public MapleStatEffect effect;
    public long startTime;
    public int value, localDuration, cid;
    public ScheduledFuture<?> schedule;
    Map<MapleBuffStat, Map<Integer, MapleBuffStatValueHolder>> indie_stats;
    public MapleBuffStatValueHolder(MapleStatEffect effect, long startTime, ScheduledFuture<?> schedule, int value, int localDuration, int cid) {
        super();
        this.effect = effect;
        this.startTime = startTime;
        this.schedule = schedule;
        this.value = value;
        this.localDuration = localDuration;
        this.cid = cid;
    }
}
