//package com.scrat.loganalyzer.test;
//
//import java.text.ParseException;
//import java.util.List;
//
//import org.apache.commons.lang3.builder.ToStringBuilder;
//import org.apache.commons.lang3.builder.ToStringStyle;
//import org.apache.commons.lang3.time.DateUtils;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.tyyd.loganalyzer.model.LogData;
//import com.tyyd.loganalyzer.service.ParseService;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:log_analyzer-servlet.xml" })
//public class TestParseService {
//	@Autowired
//	private ParseService parseService;
//	@Test
//	public void testParseOneLog() throws ParseException{
//		LogData data1 = new LogData();
//		data1.setIp("127.0.0.1");
//		data1.setIpSum(2130706433);
//		data1.setApi("/api.img");
//		data1.setCode(200);
//		data1.setDate(DateUtils.parseDate("2013-07-03 00:00:10", "yyyy-MM-dd HH:mm:ss"));
//		data1.setMethod("POST");
//		data1.setRequestLength(21.003f);
//		data1.setSize(4);
////		data1.setFileType("img");
//		String log = "127.0.0.1 - - [03/Jul/2013:00:00:10 +0800] \"POST /api.img?age%3D3&name%3Dscrat HTTP/1.1\" 200 21.003 4";
//		String logFormat = "%ip - - %time1 \"%method %api %other\" %code %requestlength %size";
//		try {
//			List<List<String>> parseParams = parseService.getFormatSplitStr(logFormat);
//			List<String> formatSplitStrs = parseParams.get(1);
//			List<String> params = parseParams.get(0);
//			LogData data2 = parseService.parseOneLine(params, formatSplitStrs, log);
////			System.out.println(ToStringBuilder.reflectionToString(data1, ToStringStyle.SHORT_PREFIX_STYLE));
////			System.out.println(ToStringBuilder.reflectionToString(data2, ToStringStyle.SHORT_PREFIX_STYLE));
//			Assert.assertEquals(ToStringBuilder.reflectionToString(data1, ToStringStyle.SHORT_PREFIX_STYLE), 
//					ToStringBuilder.reflectionToString(data2, ToStringStyle.SHORT_PREFIX_STYLE));
//		} catch (ParseException e) {
//			e.printStackTrace();
//			Assert.fail();
//		}
//	}
//}
