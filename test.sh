REM VIEWDATA 
 REM �������������"DOS POWERTOOLS"
 REM                           PAUL SOMERSON��д 
 @ECHO OFF
  
 IF !%1==! GOTO VIEWDATA
 REM  ���û�������в���...
 FIND "%1" C:\BOOKLIST.TXT
 GOTO EXIT0
 REM  ��ӡ���ַ���ƥ�����, Ȼ���˳�. 
 
 :VIEWDATA
 TYPE C:\BOOKLIST.TXT | MORE
 REM  ��ʾ�����ļ�, һ��һҳ. 
 :EXIT0