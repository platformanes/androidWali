androidWali
===========

瓦力android平台ANE
* 此ANE可提供aser直接用，无需涉及java端。
* 保留ANE原件 和ANE源码  AS端源码和SWC文件
* 把涉及SDK的jar 文档等去掉（已和官方沟通 官方不允许分享SDK）
*  接SDK的时候 把官方提供的SDK相应文件导入库即可

## 资源

* 官方网站 [Wali](http://www.wali.com)
* 未开放下载地址

## 编译方法
*  A复制 SDK的JAR到`build/makeJar` ，运行`combine.bat `合并jar
*  B 复制A中得到的jar到`build/buildANE/Android-ARM`，运行`ane_packer.bat`得到ANE
*  C 复制B中得到的ANE到 `bulidAPK` 并修改配置 运行`wali_apk.bat`可生成APK


## 作者

* [platformANEs](https://github.com/platformanes)由 [zrong](http://zengrong.net) 和 [rect](http://www.shadowkong.com/) 共同发起并完成。