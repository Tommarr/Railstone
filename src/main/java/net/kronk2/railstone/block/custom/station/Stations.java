package net.kronk2.railstone.block.custom.station;

import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class Stations {
    private static Stations INSTANCE;
    private List<StationModel> placedStations;

    public Stations(){
        this.placedStations = new ArrayList<>();
    }

    public static Stations getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Stations();
        }
        return INSTANCE;
    }

    public static Stations getINSTANCE() {
        return INSTANCE;
    }

    public static void setINSTANCE(Stations INSTANCE) {
        Stations.INSTANCE = INSTANCE;
    }

    public List<StationModel> getPlacedStations() {
        return placedStations;
    }

    public boolean removeStation(BlockPos pos){
        int index = 0;
        boolean deleted = false;

        for (StationModel s:placedStations) {
            index++;
            if(s.getPos().getX() == pos.getX() && s.getPos().getY() == pos.getY() && s.getPos().getZ() == pos.getZ() ){
                placedStations.remove(index - 1);
                deleted = true;
                break;
            }
        }

        return deleted;
    }

    public boolean addToStation(StationModel station){
        return this.placedStations.add(station);
    }

    public void setPlacedStations(List<StationModel> placedStations) {
        this.placedStations = placedStations;
    }
}
