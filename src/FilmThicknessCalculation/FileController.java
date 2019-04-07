package FilmThicknessCalculation;

import goodJ.Abstract.AbstractController;
import goodJ.Annotation.Action;
import goodJ.Annotation.Controller;

@Controller(name="fileController")
public class FileController extends AbstractController
{
    @Action(name = "reset")
    public  void resetData(){
        MainView view = (MainView) this.getApplication().getBuiltViews("mainView").get(0);
        MainView.resetLabels();
        DataStorage.resetDataA();
        DataStorage.resetDataB();
        MainView.resetExamples();
        MainView.calculate.setEnabled(false);
        if (DataStorage.checkData()) {
            view.enableCalculate();
        }
}

    @Action(name = "loadFileA")
    public void readFile() {
        MainView view = (MainView) this.getApplication().getBuiltViews("mainView").get(0);
        FileChooser service = (FileChooser) this.getApplication().getService("fileChooser");

        service.loadFileA();

        if (DataStorage.checkData()) {
            view.enableCalculate();
        }
    }


    @Action(name = "loadFileB")
    public void readSpeed()
    {
        MainView view = (MainView) this.getApplication().getBuiltViews("mainView").get(0);
        FileChooser service = (FileChooser) this.getApplication().getService("fileChooser");

        service.loadFileB();

        if (DataStorage.checkData()) {
            view.enableCalculate();
        }
    }



    @Action(name = "calculate")
    public void calculate()
    {
        MainView view = (MainView) this.getApplication().getBuiltViews("mainView").get(0);

        Calculation service = (Calculation) this.getApplication().getService("calculation");
            Object[][] result = service.calculate(
                    view.getConcentrationValue(),
                    view.getMinSpeedValue(),
                    view.getMaxSpeedValue(),
                    view.getPoiseValue()
            );

        String cv = (String) view.getComboValue();

        view.rebuildTable(result);

        }



    @Action(name = "changeFiles")
    public void changeFiles()
    {
        MainView view = (MainView) this.getApplication().getBuiltViews("mainView").get(0);
        String selectedValue = view.getSelectedValue();
        DataStorage.setFilesByKey(selectedValue);
        view.enableCalculate();
    }

}

