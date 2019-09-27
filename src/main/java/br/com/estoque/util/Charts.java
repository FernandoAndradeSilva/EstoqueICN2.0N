package br.com.estoque.util;


import org.primefaces.model.chart.*;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;


@Named
@ViewScoped
public class Charts implements Serializable {

    private static final long serialVersionUID = 1L;


    private BarChartModel animatedModel2;

    @PostConstruct
    public void init() {
        createAnimatedModels();
    }

    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Entradas");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);
        boys.set("2009", 40);
        boys.set("2010", 85);
        boys.set("2011", 100);
        boys.set("2012", 120);
        boys.set("2013", 10);

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Saídas");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 77);
        girls.set("2009", 88);
        girls.set("2010", 20);
        girls.set("2011", 60);
        girls.set("2012", 47);
        girls.set("2013", 120);


        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }

    private void createAnimatedModels() {
        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Gráfico de Movimentações");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");


        Axis yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);
    }


}