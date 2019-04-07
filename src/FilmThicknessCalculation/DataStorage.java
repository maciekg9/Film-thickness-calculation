package FilmThicknessCalculation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataStorage {
    private static ArrayList<Double> fileA;
    private static ArrayList<Double> fileB;

    private final static Map<String, ArrayList<ArrayList<Double>>> availableFiles = new HashMap<>();

    private static double concentration;

    public static ArrayList<Double> getFileA() {
        return fileA;
    }

    public static void setFileA(ArrayList<Double> fileA) {
        DataStorage.fileA = fileA;
    }

    public static ArrayList<Double> getFileB() {
        return fileB;
    }

    public static void setFileB(ArrayList<Double> fileB) {
        DataStorage.fileB = fileB;
    }

    public static void resetDataA()
    {
        DataStorage.fileA = null;
    }
    public static void resetDataB()
    {
        DataStorage.fileB = null;

    }
    public static boolean checkData()
    {
        return fileA != null && fileB != null;
    }

    public static String[] buildAvailablesList()
    {
        if (availableFiles.size() == 0) {
            init();
        }
        String[] result = new String[availableFiles.size() + 1];
        result[0] = "Please select a custom file";
        int i = 1;
        for (String key : availableFiles.keySet()) {
            result[i] = key;
            i++;
        }
        return result;
    }

    public static void setFilesByKey(String key)
    {
        fileA = availableFiles.get(key).get(0);
        fileB = availableFiles.get(key).get(1);
    }

    public static void init() {
        ArrayList<Double> polymerExample = new ArrayList<>();
        polymerExample.add(303.0);
        polymerExample.add(0.0);
        polymerExample.add(82.06);
        polymerExample.add(0.1553);
        polymerExample.add(0.5474);


        ArrayList<Double> xylene = new ArrayList<>();
        xylene.add(0.088);
        xylene.add(106.2);
        xylene.add(0.013);
        xylene.add(0.074);


        ArrayList<Double> chloroform = new ArrayList<>();
        chloroform.add(1.49);
        chloroform.add(119.4);
        chloroform.add(0.31);
        chloroform.add(0.106);

        ArrayList<Double> toluene = new ArrayList<>();
        toluene.add(0.87);
        toluene.add(92.0);
        toluene.add(0.029);
        toluene.add(0.086);

        ArrayList<ArrayList<Double>> xyleneExample = new ArrayList<>();
        xyleneExample.add(polymerExample);
        xyleneExample.add(xylene);

        ArrayList<ArrayList<Double>> chloroformExample = new ArrayList<>();
        chloroformExample.add(polymerExample);
        chloroformExample.add(chloroform);

        ArrayList<ArrayList<Double>> tolueneExample = new ArrayList<>();
        tolueneExample.add(polymerExample);
        tolueneExample.add(toluene);

        availableFiles.put("Xylene", xyleneExample);
        availableFiles.put("Chloroform", chloroformExample);
        availableFiles.put("Toluene", tolueneExample);
    }
}
