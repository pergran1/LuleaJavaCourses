package IntroductionProgramming;

public class firstTask {

    public static double roundToDouble(double testar, int nbrOfDecimals) {
        double scale = 0;
        scale = Math.pow(10, nbrOfDecimals);
        return Math.round(testar * scale) / scale;
    }

    public static void main(String[] args) {

        double BATTERY = 35.8;
        int CURRENT_10 = 10;
        int CURRENT_16 = 16;
        int CURRENT_32 = 32;
        int VOLTAGE_230 = 230;
        int VOLTAGE_400 = 400;

        System.out.print("Batteri " + BATTERY + "(kwh)\nStröm(A) \tSpänning(V) \tLaddeffekt(kw) \tLaddningstid(h)\n");

        for (int i = 0; i < 65; i++) {
            System.out.print("=");
        }
        System.out.print("\n");

        double chargeEffect_1 = roundToDouble((CURRENT_10 * VOLTAGE_230) / 1000.0, 2);
        double chargeEffect_2 = roundToDouble((CURRENT_16 * VOLTAGE_230) / 1000.0, 2);
        double chargeEffect_3 = roundToDouble((CURRENT_10 * VOLTAGE_400) / 1000.0 * Math.sqrt(3), 2);
        double chargeEffect_4 = roundToDouble((CURRENT_16 * VOLTAGE_400) / 1000.0 * Math.sqrt(3), 2);
        double chargeEffect_5 = roundToDouble((CURRENT_32 * VOLTAGE_400) / 1000.0 * Math.sqrt(3), 2);

        System.out.print(CURRENT_10 + "\t\t\t" + VOLTAGE_230 + "\t\t\t\t" + chargeEffect_1 + "\t\t\t\t"
                + roundToDouble(BATTERY / chargeEffect_1, 2) + "\n");
        System.out.print(CURRENT_16 + "\t\t\t" + VOLTAGE_230 + "\t\t\t\t" + chargeEffect_2 + "\t\t\t"
                + roundToDouble(BATTERY / chargeEffect_2, 2) + "\n");
        System.out.print(CURRENT_10 + "\t\t\t" + VOLTAGE_400 + "\t\t\t\t" + chargeEffect_3 + "\t\t\t"
                + roundToDouble(BATTERY / chargeEffect_3, 2) + "\n");
        System.out.print(CURRENT_16 + "\t\t\t" + VOLTAGE_400 + "\t\t\t\t" + chargeEffect_4 + "\t\t\t"
                + roundToDouble(BATTERY / chargeEffect_4, 2) + "\n");
        System.out.print(CURRENT_32 + "\t\t\t" + VOLTAGE_400 + "\t\t\t\t" + chargeEffect_5 + "\t\t\t"
                + roundToDouble(BATTERY / chargeEffect_5, 2));

    }
}
