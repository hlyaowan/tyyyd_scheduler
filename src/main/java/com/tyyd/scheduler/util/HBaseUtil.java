package com.tyyd.scheduler.util;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseUtil {
	public static Set<String> distinctRowkey(HTable table, Scan scan) throws IOException{
		Set<String> set = new HashSet<String>();
		ResultScanner rs = null;
		Result result = null;
		try {
			rs = table.getScanner(scan);
			while ((result=rs.next()) != null) {
				set.add(Bytes.toString(result.getRow()));
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		return set;
	}
	
	public static Set<String> distinct(HTable table, Scan scan, byte[] family, byte[] qualifier) throws IOException{
		Set<String> set = new HashSet<String>();
		ResultScanner rs = null;
		Result result = null;
		try {
			rs = table.getScanner(scan);
			while ((result=rs.next()) != null) {
				byte[] value = result.getValue(family, qualifier);
				set.add(Bytes.toString(value));
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		return set;
	}
}
