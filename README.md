﻿androidWali
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
* 复制 `build/example.build.config` 为 `build/build.config`
* 修改其中的 `flex.sdk` 和 `android.sdk` 变量为正确的路径
* 运行 `ant android-ane`


## 作者

* [platformANEs](https://github.com/platformanes)由 [zrong](http://zengrong.net) 和 [rect](http://www.shadowkong.com/) 共同发起并完成。