------------------------------------------------

Steam-Json-Convert-MySQL-Database-And-Scraper

------------------------------------------------

Requirement :

json-simple jar lib

jsoup jar lib

mysql-connector-java jar lib

selenium-java jar lib

selenium-server-standalone jar lib

------------------------------------------------

2016/05/16

透過Java的json-simple將著名的
Steam遊戲平台的遊戲清單json檔轉換處理後
再利用JDBC送進自己電腦的Mysql資料庫中並建立Table

------------------------------------------------

2016/05/28

增加Steam遊戲屬性標籤scraper功能

增加Steam評論scraper功能 (*可自訂抓取需求數)

------------------------------------------------

2016/05/30

Steam評論scraper呼叫類別功能完成 

(*可自訂遊戲的appid(例如Steam NBA 2K16的appid為370240) *可自訂抓取需求數 *Export成.json檔案 *指定輸出路徑)

使用方式:

直接呼叫 Steam_review_scraper.java 類別function：

Steam_review_scraper.do_steam_review_scraper_now("appid", review_number, "json_export_path");

------------------------------------------------

appid = Steam的遊戲id編號

review_number = 指定抓取評論內容之數量

json_export_path = 指定json檔案輸出路徑

------------------------------------------------

2016/05/31

開始進行有效遊戲與無效遊戲清理淨化

------------------------------------------------