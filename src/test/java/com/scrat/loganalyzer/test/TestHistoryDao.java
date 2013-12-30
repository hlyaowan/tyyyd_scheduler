//package com.scrat.loganalyzer.test;
//
//import java.io.IOException;
//import java.text.ParseException;
//import java.util.Date;
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
//import com.tyyd.loganalyzer.dao.HistoryDao;
//import com.tyyd.loganalyzer.model.History;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:log_analyzer-servlet.xml" })
//public class TestHistoryDao {
//	@Autowired
//	private HistoryDao historyDao;
//	
//	private History initHistory() throws IOException, ParseException{
//		String pathname = "/data/2013/10/28/this_is_a_test.txt";
//		Date dt = DateUtils.parseDate("2013-11-11 11:11:11", "yyyy-MM-dd HH:mm:ss");
////		File file = new File(pathname);
////		int totalLine = FileUtils.readLines(file).size();
////		String md5Code = DigestUtils.md2Hex(new FileInputStream(file));
//		History history = new History();
//		history.setDt(dt);
//		history.setMd5Code("d2afb299e12e42427defa3889f0c193c");
//		history.setPath(pathname);
//		history.setSize(100);
//		history.setStatus("1");
//		history.setTotalLine(1235);
//		return history;
//	}
//	
//	private void testSaveHistory(History history) {
//		try {
//			int result = historyDao.saveHistory(history);
//			Assert.assertEquals(result, 1);
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail();
//		}
//	}
//	
//	private void testGetHistory(History history) {
//		try {
//			History temp = historyDao.getHistory(history.getPath());
//			temp.setDt(new Date(temp.getDt().getTime()));
//			String historyStr = ToStringBuilder.reflectionToString(history, ToStringStyle.SHORT_PREFIX_STYLE);
//			String tempStr = ToStringBuilder.reflectionToString(temp, ToStringStyle.SHORT_PREFIX_STYLE);
//			Assert.assertEquals(historyStr, tempStr);
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail();
//		}
//	}
//	
//	private void testUpdateHistory(History history) {
//		try {
//			int result = historyDao.updateHistory(history);
//			Assert.assertEquals(result, 1);
//			History temp = historyDao.getHistory(history.getPath());
//			temp.setDt(new Date(temp.getDt().getTime()));
//			String historyStr = ToStringBuilder.reflectionToString(history, ToStringStyle.SHORT_PREFIX_STYLE);
//			String tempStr = ToStringBuilder.reflectionToString(temp, ToStringStyle.SHORT_PREFIX_STYLE);
//			Assert.assertEquals(historyStr, tempStr);
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail();
//		}
//	}
//	
//	private void testDeleteHistory(History history) {
//		try {
//			int result = historyDao.deleteHistory(history.getPath());
//			Assert.assertEquals(result, 1);
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail();
//		}
//	}
//	
//	@Test
//	public void test() throws IOException, ParseException {
//		try {
//			History history = initHistory();
//			testSaveHistory(history);
//			testGetHistory(history);
//			history.setSize(555);
//			testUpdateHistory(history);
//			testDeleteHistory(history);
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail();
//		}
//	}
//}
