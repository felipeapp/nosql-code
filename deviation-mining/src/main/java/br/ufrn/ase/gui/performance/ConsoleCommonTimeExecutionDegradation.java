/*
 * Copyright (C) Automation Software Engineering Group
 *
 * This software is distributed WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND
 */
package br.ufrn.ase.gui.performance;

import java.util.List;
import java.util.Map;

import br.ufrn.ase.gui.basic.ConsoleHighestAverageMostSignificant;
import br.ufrn.ase.service.performance.CommonTimeExecutionDegradationService;

/**
 * 
 * (1) Identificar horarios de carga no sistema durante a semana e quais
 * cenarios sao relacionados a tais horarios de carga;
 *      
 * 
 * @author jadson - jadsonjs@gmail.com
 *
 */
public class ConsoleCommonTimeExecutionDegradation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String systemVersion ="SIGAA-3.21.0";
		
		long start = System.currentTimeMillis();
		
		System.out.println("Starting ... ");
		
		getCommonTimeExecutionDegradation(systemVersion);
		
		System.out.println("Time: " + (System.currentTimeMillis() - start) / 1000.0 + " seconds");

	}

	private static void getCommonTimeExecutionDegradation(String systemVersion) {
		
		Map<String, Double> topScenarios = new ConsoleHighestAverageMostSignificant().getHighestAverageMostSignificantScenario(systemVersion, 10);
		
		List<String> scenarioDegradation = new CommonTimeExecutionDegradationService().calculateCommonTimeExecutionDegradation(systemVersion, topScenarios);
		
		// we find the potential degradation scenarios
		for (String string : scenarioDegradation) {
			System.out.println("*****"+string+"*****");
		}
	}

}
