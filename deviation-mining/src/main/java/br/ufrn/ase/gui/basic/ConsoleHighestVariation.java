/*
 * Copyright (C) Automation Software Engineering Group
 *
 * This software is distributed WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND
 */
package br.ufrn.ase.gui.basic;

import java.util.List;
import java.util.Map;

import br.ufrn.ase.analysis.UserScenariosStatistics;
import br.ufrn.ase.r.GraphicPlot;
import br.ufrn.ase.service.performance.UserScenariosPerformanceService;
import br.ufrn.ase.util.MapUtil;

/**
 * This class calculate the highest scenarios variations of a specific system version 
 * using Coefficient of variation
 * 
 * https://en.wikipedia.org/wiki/Coefficient_of_variation
 * 
 * @author jadson - jadsonjs@gmail.com
 *
 */
public class ConsoleHighestVariation {

	public final static int QTD = 10;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Starting ... ");
		
		long start = System.currentTimeMillis();
		
		
		UserScenariosPerformanceService userScenariosService = new UserScenariosPerformanceService();
		
		
		Map<String, List<Double>> retorno_3_21 = userScenariosService.findTimesExecutionOfUserScenarios("SIGAA-3.21.0", false);
		Map<String, Double> mapRange_3_21 = new UserScenariosStatistics().calculateCoefficientOfVariation(retorno_3_21, true);

		mapRange_3_21 = MapUtil.cutOff(mapRange_3_21, QTD);
		
		
		GraphicPlot plot = new GraphicPlot();
		
		plot.drawColumnChart(mapRange_3_21, "Variation", "Scenario", "Times");
		plot.drawBoxPlotChart(mapRange_3_21);

		System.out.println("Time: " + (System.currentTimeMillis() - start) / 1000.0 + " seconds");

	}

}
