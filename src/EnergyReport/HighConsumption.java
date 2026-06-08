package EnergyReport;


import Appliances.Appliance;
import HouseHold.HouseHold;

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

        Appliance[] appliance= houseHold.getAppliances();


        for(int index=0;index< appliance.length;index++){

            if(appliance[index].calculateEnergyConsumption()==0){
                continue;
            }

            if(appliance[index].calculateEnergyConsumption()>high1){

                high3=high2;
                top3=top2;

                high2=high1;
                top2=top1;

                high1=appliance[index].calculateEnergyConsumption();
                top1=appliance[index];

            }else if(appliance[index].calculateEnergyConsumption()>high2){
                high3=high2;
                top3=top2;

                high2=appliance[index].calculateEnergyConsumption();
                top2=appliance[index];


            } else if(appliance[index].calculateEnergyConsumption()>high3) {
                high3=appliance[index].calculateEnergyConsumption();
                top3=appliance[index];

            }



        }
        return new Appliance[]{top1,top2,top3};


    }



}

