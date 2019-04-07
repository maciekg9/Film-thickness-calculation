package FilmThicknessCalculation;

import goodJ.Annotation.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;


@Service(name = "calculation")
public class Calculation {
    public Object[][] calculate(double concentration, double speedmin, double speedmax, double poise) {
        Object[][] result = new Object[51][2];

        if (concentration == 0.0 || speedmin == 0.0 || speedmax == 0 || poise == 0) {
            PopupMessage.showErrorMessage("All fields are required");
            return result;
        }
        if (speedmax < speedmin) {
            PopupMessage.showErrorMessage("Maximum speed cannot be lower than minimum speed");
            return result;
        }
            if (concentration > 100){
                PopupMessage.showErrorMessage("Solution concentration cannot be higher than 100% ");
                return result;
        }
        double spin = (speedmax - speedmin)/50;
        double currentSpeed = speedmin;
        for (int i = 0; i <= 50; i++) {
            BigDecimal b1 = new BigDecimal(currentSpeed);
            BigDecimal b2 = b1.setScale(2, RoundingMode.HALF_UP);
            double currentspeedRounded = b2.doubleValue();
            result[i][0] = currentspeedRounded;
            result[i][1] = getLayerThickness( concentration, currentSpeed, poise);
            currentSpeed += spin; }
        return result; }



    private double getLayerThickness(double concentration, double speed, double poise) {


        double w = speed / 60.0 * 2 * Math.PI;
            // parametry dla roztworu
        double T = DataStorage.getFileA().get(0);     // K; temperatura
        double x1inf = DataStorage.getFileA().get(1); // brak jednostki; końcowa część masy rozpuszczalnika w roztworze
        double R = DataStorage.getFileA().get(2);   // atm * cm^3 / (mol * K); stała gazu doskonałego
        double vg = DataStorage.getFileA().get(3); // cm^2/s; kinematyczna lepkość szczytowej fazy gazowej
        double c = DataStorage.getFileA().get(4);  // brak jednostki; stała zależna od liczby Schmidta
        // parametry dla polimeru
            double rho = DataStorage.getFileB().get(0);  // g/cm^3; gęstość cieczy
            double M1 = DataStorage.getFileB().get(1);     // g/mol; masa cząsteczkowa rozpuszczalnika
        double p10 = DataStorage.getFileB().get(2); // atm; prężność par czystego rozpuszczalnika
            double Dg = DataStorage.getFileB().get(3);  // cm^2/s; binarna dyfuzyjność rozpuszczalnika w szczytowej fazie gazowej


            // równanie 3;
            double k = ((c * Dg) / (Math.sqrt(vg) * rho)) * ((p10 * M1) / (R * T)) * Math.sqrt(w);

            // z rysunku 3;

            double x10 = 1.0 - concentration / 100.0;    // no unit; początkowa część masy rozpuszczalnika w roztworze

            // równanie 4;
            double hw = Math.pow(((3.0 * poise) / (2.0 * rho * w * w)) * k * (x10 - x1inf), 0.3333);


        double finalResult = (1.0 - x10) * hw * 10000000;
        BigDecimal b1 = new BigDecimal(finalResult);
        BigDecimal b2 = b1.setScale(2, RoundingMode.HALF_UP);
        double finalResultRounded = b2.doubleValue();
        return finalResultRounded;
        }
}

