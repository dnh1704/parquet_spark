import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.xddf.usermodel.chart.AxisCrosses;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.BarDirection;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;
import org.apache.poi.xddf.usermodel.chart.XDDFBarChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFCategoryAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dashboard {
    public static void drawExecution1() throws FileNotFoundException, IOException {
		
		File folder = new File("result/exe1csv");
		int i = 1;
		for (File fileEntry : folder.listFiles()){
		
			String file = "result/exe1csv/" + fileEntry.getName();
			BufferedReader reader = null;
			String line = "";
			
			try {
				HashMap<String, Integer> urlAccessCount = new HashMap<String, Integer>();
				reader = new BufferedReader(new FileReader(file));
				while((line = reader.readLine()) != null){
					String [] row = line.split(",");
					Integer value = urlAccessCount.get(row[1]);

					if(value == null)
						urlAccessCount.put(row[1], 1);
					else
						urlAccessCount.put(row[1], value + Integer.valueOf(row[2]));
				}

				//fasfsafsafsaf
				XSSFWorkbook wb = new XSSFWorkbook();
				String sheetName = "Chart";
				
				XSSFSheet sheet = wb.createSheet(sheetName);

				XSSFDrawing drawing = sheet.createDrawingPatriarch();
				XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 1, 1, 9, 77);

				XSSFChart chart = drawing.createChart(anchor);
				chart.setTitleText("Area-wise Top Seven Countries");
				chart.setTitleOverlay(false);

				XDDFChartLegend legend = chart.getOrAddLegend();
				legend.setPosition(LegendPosition.TOP_RIGHT);

				XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
				bottomAxis.setTitle("URL");
				XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
				leftAxis.setTitle("Count");
				leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

				String key[] = new String[urlAccessCount.keySet().size()];
				Integer value[] = new Integer[urlAccessCount.values().size()];
				XDDFDataSource<String> url = XDDFDataSourcesFactory.fromArray(urlAccessCount.keySet().toArray(key));
				XDDFNumericalDataSource<Integer> count = XDDFDataSourcesFactory.fromArray(urlAccessCount.values().toArray(value));

				XDDFChartData data = chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
				XDDFChartData.Series series1 = data.addSeries(url, count);
				series1.setTitle("heh", null);
				data.setVaryColors(true);
				chart.plot(data);

				XDDFBarChartData bar = (XDDFBarChartData) data;
				bar.setBarDirection(BarDirection.BAR);

				String filename = "dashboard/exe1csv/part"+ i + ".xlsx";
				i++;
				FileOutputStream fileOut = new FileOutputStream(filename);
				wb.write(fileOut);
				wb.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally {
				reader.close();
			}
		}
	}

	public static void drawExecution2(int numOfGuid) throws FileNotFoundException, IOException{
		
		File folder = new File("result/exe2csv");
		int i = 0;
		for (File fileEntry : folder.listFiles()){
		
			String file = "result/exe2csv/" + fileEntry.getName();
			System.out.println(fileEntry.getName());
			BufferedReader reader = null;
			String line = "";

			try {
				ArrayList<String> guid = new ArrayList<String>();
				ArrayList<Integer> accessCount = new ArrayList<Integer>();
				int j = 0;
				reader = new BufferedReader(new FileReader(file));
				while((line = reader.readLine()) != null && j++ < numOfGuid){
					String [] row = line.split(",");
					
					guid.add(row[0]);
					accessCount.add(Integer.valueOf(row[1]));
				}

				//fasfsafsafsaf
				XSSFWorkbook wb = new XSSFWorkbook();
				String sheetName = "Chart";
				
				XSSFSheet sheet = wb.createSheet(sheetName);

				XSSFDrawing drawing = sheet.createDrawingPatriarch();
				XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 1, 1, 9, 20);

				XSSFChart chart = drawing.createChart(anchor);
				chart.setTitleText("Guid với lượng truy cập nhiều domain nhất");
				chart.setTitleOverlay(false);

				XDDFChartLegend legend = chart.getOrAddLegend();
				legend.setPosition(LegendPosition.TOP_RIGHT);

				XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
				bottomAxis.setTitle("Guid");
				XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
				leftAxis.setTitle("Count");
				leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

				String key[] = new String[guid.size()];
				Integer value[] = new Integer[accessCount.size()];
				XDDFDataSource<String> url = XDDFDataSourcesFactory.fromArray(guid.toArray(key));
				XDDFNumericalDataSource<Integer> count = XDDFDataSourcesFactory.fromArray(accessCount.toArray(value));

				XDDFChartData data = chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
				XDDFChartData.Series series1 = data.addSeries(url, count);
				series1.setTitle("heh", null);
				data.setVaryColors(true);
				chart.plot(data);

				XDDFBarChartData bar = (XDDFBarChartData) data;
				bar.setBarDirection(BarDirection.BAR);

				String filename = "dashboard/exe2csv/part" + i + ".xlsx";
				i++;
				FileOutputStream fileOut = new FileOutputStream(filename);
				wb.write(fileOut);
				wb.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally {
				reader.close();
			}
		}
	}
}
