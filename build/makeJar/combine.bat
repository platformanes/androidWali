@echo off
::ת����ǰ�̷�
%~d0
::�򿪵�ǰĿ¼
cd %~dp0
::��������JAR����·��
set MainJar=waliane.jar
::������JAR����·��
set ExternalJar=WALI_SDK_2.0.0.jar
::������JAR������������
set packageName=com
set packageName2=cn
set packageName3=assets
echo =========== start combin ==============
::��ѹ��������
jar -xf %ExternalJar%
::�ϲ���JAR��
jar -uf %MainJar% %packageName% 
::������б�Ķ��������Խ��źϲ������磺
::jar -uf %MainJar% %packageName2%
::jar -uf %MainJar% %packageName3%
echo =========== over ==============
echo �ٵ�һ�¾ͽ�����--СQ
pause