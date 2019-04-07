package FilmThicknessCalculation;

import goodJ.Abstract.AbstractController;
import goodJ.Annotation.Action;
import goodJ.Annotation.Controller;

@Controller(name="mainController")
public class MainController extends AbstractController
{
    @Action(name="mainAction")
    public void mainAction()
    {
        DataStorage.init();

        this.getApplication().buildView("mainView");
    }
}
