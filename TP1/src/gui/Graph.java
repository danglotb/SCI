package gui;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.xeiam.xchart.Chart;
import com.xeiam.xchart.SeriesMarker;
import com.xeiam.xchart.XChartPanel;

import core.Engine;

public abstract class Graph extends JPanel implements Observer {
	
	private Chart chart;
	private XChartPanel chartPanel;
	private Map<String, List<Double>> x;
	private Map<String, List<Double>> y;
	
	
	public Graph(int width, int height)  {
		this.chart = new Chart(width, height);
		this.chartPanel = new XChartPanel(this.chart);
		this.x = new ConcurrentHashMap<String, List<Double>>();
		this.y = new ConcurrentHashMap<String, List<Double>>();
		this.setPreferredSize(new Dimension(width, height));
	}
	
	public void addPoint(String name, Point p) {

		if(this.x.size() == 0) {
			_addPoint(name, p);
		//	chart.getStyleManager().set
			this.add(this.chartPanel);
		}
		else {
			_addPoint(name, p);
		}
	}
	
	public void _addPoint(String name, Point p) {
		if(this.x.containsKey(name)) {
			this.x.get(name).add(new Double(p.x));
			this.y.get(name).add(new Double(p.y));
			this.chartPanel.updateSeries(name, this.x.get(name), this.y.get(name));

		}
		else {
			List<Double> arrayX = new CopyOnWriteArrayList<Double>();
			arrayX.add(new Double(p.x));
			this.x.put(name, arrayX);
			List<Double> arrayY = new CopyOnWriteArrayList<Double>();
			arrayY.add(new Double(p.y));
			this.y.put(name, arrayY);
			this.chart.addSeries(name, this.x.get(name), this.y.get(name)).setMarker(SeriesMarker.NONE);
		}	
	}
	
	public abstract void update(Engine engine);

	public void update(Observable o, Object arg) {
		update((Engine)o);
	}

}
