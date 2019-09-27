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

        ChartSeries entradas = new ChartSeries();
        entradas.setLabel("Entradas");
        entradas.set("Janeiro", 120);
        entradas.set("Fevereiro", 100);
        entradas.set("Março", 44);
        entradas.set("Abril", 150);
        entradas.set("Maio", 25);
        entradas.set("Junho", 40);
        entradas.set("Julho", 85);
        entradas.set("Agosto", 100);
        entradas.set("Setembro", 120);
        entradas.set("Outubro", 10);
        entradas.set("Novembro", 120);
        entradas.set("Dezembro", 75);

        ChartSeries saidas = new ChartSeries();
        saidas.setLabel("Saídas");
        saidas.set("Janeiro", 52);
        saidas.set("Fevereiro", 60);
        saidas.set("Março", 110);
        saidas.set("Abril", 135);
        saidas.set("Maio", 77);
        saidas.set("Junho", 88);
        saidas.set("Julho", 20);
        saidas.set("Agosto", 60);
        saidas.set("Setembro", 47);
        saidas.set("Outubro", 120);
        saidas.set("Novembro", 77);
        saidas.set("Dezembro", 15);

        model.addSeries(entradas);
        model.addSeries(saidas);

        return model;
    }

    private void createAnimatedModels() {
        animatedModel2 = initBarModel();
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");


        Axis yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);
    }


}