package com.tyyd.scheduler.exec;

public class RunShell {


    /**
     * 运行shell脚本
     * 
     * @param shell 需要运行的shell脚本
     */
    public static void execShell(String shell) {
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec(shell);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 运行shell脚本
     * 
     * @param shell 需要运行的shell脚本
     */
    public static void execShellMain(String shell,String input) {
        try {
            ExecRunner runner =new ExecRunner();
            StringBuffer sBuffer= new StringBuffer();
            sBuffer.append("sh ");
            sBuffer.append(shell+" ");
            sBuffer.append(input+" ");
            runner.exec(sBuffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String shpath = "/home/taobao/datax/bin/datax.sh";
        String fileName = "/home/taobao/datax/jobs/hongxiureader_to_hongxiuwriter_1387500928254.xml ";
        System.out.println("==============start==============");
//        Process process = Runtime.getRuntime().exec("sh /usr/local/jboss5/server/default/deploy/sanguo_root.war/test.sh 11 aa");
        try {
            StringBuffer sBuffer= new StringBuffer();
            sBuffer.append("sh ");
            sBuffer.append(shpath+" ");
            sBuffer.append(fileName+" ");
            execShell(sBuffer.toString());
            System.out.println("flag:"+sBuffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
