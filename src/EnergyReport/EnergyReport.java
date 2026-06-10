package EnergyReport;

import Appliances.Appliance;

import java.time.LocalDate;

public class EnergyReport {
    HighConsumption highConsumption;
    EnergySavingAdvice energySavingAdvice;

    public EnergyReport(HighConsumption highConsumption){
        this.highConsumption=highConsumption;
        this.energySavingAdvice= new EnergySavingAdvice(highConsumption);
    }

    public void DisplayReport() {

        Appliance[] Highuser = highConsumption.QueryArray();

        if(isApllianceEmpty(Highuser)) {
            System.out.println("No appliances entered!");
            return;
        }

            System.out.println("===============================");
            System.out.println("         ENERGY REPORT         ");
            System.out.println("===============================");
            System.out.println("DATE: " + LocalDate.now());
            System.out.println("-------------------------------");
            System.out.println("---||TOP 3 HIGH CONSUMERS||----");

            for (int i = 0; i < Highuser.length; i++) {
                if(Highuser[i]==null)continue;


                System.out.println(i + "." + Highuser[i].getName() + "total energy consumed: \n" + " "
                        + Highuser[i].calculateEnergyConsumption() + " kwh");
                System.out.println(" ");


            }
            energySavingAdvice.displayAdvice();

        }

        private boolean isApllianceEmpty(Appliance[] array){
            if(array==null || array.length==0){ return true;}
            for(Appliance appliance:array){
                if(appliance!=null){
                    return false;
                }
            }
            return true;

        }

    }



