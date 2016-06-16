/*
 * Copyright (C) Automation Software Engineering Group
 *
 * This software is distributed WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND
 */
package br.ufrn.ase.service.performance.basic;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufrn.ase.analysis.UserScenariosStatistics;
import br.ufrn.ase.dao.DAOFactory;
import br.ufrn.ase.dao.relational.performance.LogOperacaoDao;
import br.ufrn.ase.dao.relational.performance.ScenarioDao;
import br.ufrn.ase.domain.Sistema;
import br.ufrn.ase.util.StringUtil;
import br.ufrn.ase.util.VersionMapUtil;

/**
 * This service is used to calculate all basic mining together.
 * 
 * @author jadson - jadsonjs@gmail.com
 *
 */
public class AllBasicService {
	
	
	public void findAllBasicScenarios(String systemVersion, boolean executeMining, boolean isUserEnabled){
		
		if( executeMining ){
			
			ScenarioDao dao = DAOFactory.getRelationalDAO(ScenarioDao.class);
			LogOperacaoDao logDao = DAOFactory.getRelationalDAO(LogOperacaoDao.class);
			
			Date initialDate   = new VersionMapUtil().getInitialDateOfVersion(systemVersion);
			Date finalDate     = new VersionMapUtil().getFinalDateOfVersion(systemVersion);
			String systemName  = StringUtil.getSystemName(systemVersion);
			int systemId       = Sistema.valueOf(systemName).getValue();
			
			List<String> scenarios = dao.findAllScenariosByDateAndSystem(systemId, initialDate, finalDate);
			
			Map<String, List<Double>> retorno = new HashMap<String, List<Double>>();
			
			Map<String, Double> mapRangeMedian = new HashMap<String, Double>();
			
			Map<String, Double> mapRangeVariation = new HashMap<String, Double>();
			
			Map<String, Double> mapRangeAverage = new HashMap<String, Double>();
			
			Map<String, Double> mapRangeAccessed = new HashMap<String, Double>();
			
			for (String scenario : scenarios) {
				
				retorno = logDao.findAllLogOperacaoOfScenarioInsideIntervalBySystemVersion(scenario, systemId, initialDate, finalDate);
				
				mapRangeMedian = new UserScenariosStatistics().calculateExecutionMedianScenario(retorno);
		
				mapRangeVariation = new UserScenariosStatistics().calculateCoefficientOfVariation(retorno, true);
				
				mapRangeAverage = new UserScenariosStatistics().calculateExecutionMeanScenario(retorno);
				
				mapRangeAccessed = new UserScenariosStatistics().calculateExecutionAmountScenario(retorno);
		
				new HighestMedianService().saveResults(systemVersion, mapRangeMedian);
				
				new HighestVariationService().saveResults(systemVersion, mapRangeVariation);
				
				new HighestAverageService().saveResults(systemVersion, mapRangeAverage);
				
				new MostAccessedScenariosService().saveResults(systemVersion, mapRangeAccessed);
				
			}
			
			dao.close();
			logDao.close();
			
		}
		
	}
	


}
