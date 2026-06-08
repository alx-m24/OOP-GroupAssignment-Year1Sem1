package EnergyReport;


import Appliances.Appliance;
import HouseHold.HouseHold;

import java.util.ArrayList;

public class HighConsumption {
    HouseHold houseHold;


    public HighConsumption(HouseHold houseHold){
        this.houseHold=houseHold;

    }

    public Appliance[] QueryArray(){
        double high1=0;
        double high2=0;
        double high3=0;

        Appliance top1=null;
        Appliance top2=null;
        Appliance top3=null;

        ArrayList<Appliance> appliance= houseHold.getAppliances();


        for(int index=0;index< appliance.size();index++){

            if(appliance.get(index).calculateEnergyConsumption()==0){
                continue;
            }

            if(appliance.get(index).calculateEnergyConsumption()>high1){

                high3=high2;
                top3=top2;

                high2=high1;
                top2=top1;

                high1=appliance.get(index).calculateEnergyConsumption();
                top1=appliance.get(index);

            }else if(appliance.get(index).calculateEnergyConsumption()>high2){
                high3=high2;
                top3=top2;

                high2=appliance.get(index).calculateEnergyConsumption();
                top2=appliance.get(index);


            } else if(appliance.get(index).calculateEnergyConsumption()>high3) {
                high3=appliance.get(index).calculateEnergyConsumption();
                top3=appliance.get(index);

            }



        }
        return new Appliance[]{top1,top2,top3};


    }



}

