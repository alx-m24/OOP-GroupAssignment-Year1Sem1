package CostRegion.Service;

import CostRegion.Entity.CostRegionEntity;
import CostRegion.Entity.UrbanRegion;
import CostRegion.Entity.SuburbanRegion;
import CostRegion.Entity.RuralRegion;
import Utils.ID.CostRegionID;
import Utils.Units.KiloWattsHour;

import java.util.ArrayList;

public class CostRegionService {
    final private ArrayList<CostRegionEntity> regions = new ArrayList<>();

    public CostRegionService() {
        // Pre-register the available region types
        regions.add(new UrbanRegion());
        regions.add(new SuburbanRegion());
        regions.add(new RuralRegion());
    }

    public ArrayList<CostRegionEntity> getAllRegions() {
        return regions;
    }

    public CostRegionEntity findByID(CostRegionID regionID) {
        for (CostRegionEntity r : regions) {
            if (r.getID().equals(regionID)) return r;
        }
        return null;
    }

    public double calculateCostForRegion(CostRegionID regionID, KiloWattsHour energy) {
        CostRegionEntity region = findByID(regionID);
        if (region == null) return -1; // controller handles error case
        return region.calculateCost(energy); // polymorphic call
    }
}
