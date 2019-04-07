package FilmThicknessCalculation;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import goodJ.Abstract.AbstractView;
import goodJ.Annotation.Bind;
import goodJ.Annotation.View;
import goodJ.Enum.ViewType;

@View(name = "mainView", type = ViewType.SINGLE_VIEW)
public class MainView extends AbstractView
{

    private JFrame frame;
    private JTextArea textArea = new JTextArea();

    @Bind(name = "fileController.changeFiles")
    public static JComboBox examples = new JComboBox(DataStorage.buildAvailablesList());

    @Bind(name = "fileController.reset")
    public JButton reset = new JButton("Reset");

    @Bind(name = "fileController.loadFileA")
    public JButton readA = new JButton("Load polymer data");

    @Bind(name = "fileController.loadFileB")
    public JButton readB = new JButton("Load solvent data");


    @Bind(name = "fileController.calculate")
    public static JButton calculate = new JButton("Calculate");


    private static JTextField concentration;
    private static JTextField speedmin;
    private static JTextField speedmax;
    private static JTextField poise;
    private static JTable table;
    private static JScrollPane scrollPane;
    
    @Override
    public void buildView(Object... arg0)
    {
        this.frame = new JFrame();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocation(dim.width / 2 - 800 / 2, dim.height / 2 - 600 / 2);
        frame.setTitle("Film thickness calculation");
        frame.setVisible(true);

        JPanel bottomPanel = new JPanel();
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints cst = new GridBagConstraints();

        topPanel.setSize(100,100);
        JLabel concentrationLabel = new JLabel("Polymer concentration [%]");
        JLabel speedminLabel = new JLabel("Min speed [rpm]");
        JLabel speedmaxLabel = new JLabel("Max speed [rpm]");
        JLabel poiseLabel = new JLabel("Initial viscosity [0.1 Pa * s]");

         this.concentration = new DoubleJTextField();
        this.speedmin = new DoubleJTextField();
        this.speedmax = new DoubleJTextField();
        this.poise = new DoubleJTextField();

        this.frame.add(examples);
        this.frame.setVisible(true);


        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridy = 1;
        cst.insets = new Insets(30, 3, 3, 45);
        topPanel.add(concentrationLabel,cst);

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 1;
        cst.gridy = 1;
        cst.insets = new Insets(30, 3, 3, 45);
        topPanel.add(concentration,cst);

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridy = 2;
        cst.insets = new Insets(30, 3, 3, 45);
        topPanel.add(speedminLabel,cst);

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 1;
        cst.gridy = 2;
        cst.ipadx = 40;
        cst.insets = new Insets(30, 3, 3, 45);
        topPanel.add(speedmin,cst);

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridy = 3;
        cst.insets = new Insets(30, 3, 3, 45);
        topPanel.add(speedmaxLabel,cst);

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 1;
        cst.gridy = 3;
        cst.insets = new Insets(30, 3, 3, 45);
        topPanel.add(speedmax,cst);

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridy = 4;
        cst.insets = new Insets(30, 3, 3, 45);
        topPanel.add(poiseLabel,cst);

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 1;
        cst.gridy = 4;
        cst.insets = new Insets(30, 3, 3, 45);
        topPanel.add(poise,cst);

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridy = 5;
        cst.insets = new Insets(30, 3, 3, 45);
        topPanel.add(examples,cst);

        this.calculate.setEnabled(false);


        this.table = new JTable();
        this.rebuildTable(new Object[0][]);
        this.table.getTableHeader().setReorderingAllowed(false);
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        this.scrollPane = new JScrollPane(this.table);
        this.frame.add(this.scrollPane, BorderLayout.CENTER);

        this.frame.add(topPanel, BorderLayout.EAST);
        this.frame.add(bottomPanel, BorderLayout.SOUTH);

//        add(this.help);
//        setLayout(null);
//        help.setLocation(20,200);

        bottomPanel.add(this.reset);
        bottomPanel.add(this.readA);
        bottomPanel.add(this.readB);
        bottomPanel.add(this.calculate);

    }

    public String getSelectedValue()
    {
        return (String) examples.getSelectedItem();
    }

    public Object getComboValue()
    {
        return this.examples.getSelectedItem();
    }

    @Override
    public void close()
    {
        this.frame.dispose();
    }

    @Override
    public void focus()
    {
    }

    @Override
    public boolean isVisible()
    {
        return this.frame.isVisible();
    }
    
    public String getTextFromTextArea()
    {
        return this.textArea.getText();
    }

    public Double getConcentrationValue()
    {
        try {
            return Double.valueOf(this.concentration.getText());

        } catch (Exception e) {
            return 0.0;
        }
    }

    public Double getMinSpeedValue()
    {
        try {
            return Double.valueOf(this.speedmin.getText());
        } catch (Exception e) {

            return 0.0;
        }

    }


    public Double getMaxSpeedValue()
    {
        try {
            return Double.valueOf(this.speedmax.getText());
        } catch (Exception e) {
            return 0.0;
        }

    }
    public Double getPoiseValue()
    {
        try {
            return Double.valueOf(this.poise.getText());
        } catch (Exception e) {

            return 0.0;
        }

    }
    public void enableCalculate()
    {
        this.calculate.setEnabled(true);
    }

    public static void resetLabels(){
        concentration.setText("");
        speedmax.setText("");
        speedmin.setText("");
        poise.setText("");
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }
    public  static void resetExamples() {
        examples.setSelectedIndex(0);
    }

    public void rebuildTable(Object[][] data)
    {
        this.table.setModel(new DefaultTableModel(data, new Object[] { "Spin speed [rpm]", "Layer thickness [nm]" }) {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
    }
    public void replacePage(JPanel parent, JPanel child)
    {
        parent.removeAll();
        parent.add(child);
        parent.revalidate();
        parent.repaint();
    }

}
