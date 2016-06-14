------------------------------------------------

# Steam-Json-Convert-MySQL-Database-And-Scraper

###### Want to scrape reviews automatically from steam?

###### This can help you to finish you want!

------------------------------------------------

For steam game review --> (*appid(ex:Steam NBA 2K16 appid is 370240) *sample size *.json file export path)

For steam user profile review --> (*user_profile_index(ex:http://steamcommunity.com/id/xxx/ or http://steamcommunity.com/profiles/xxx/) *.json file export path)

# Usage:

Call "Steam_review_scraper.java" class function：

function --> Steam_review_scraper.do_steam_review_scraper_now("appid", "review_sample_size", "json file export path");


Call "Steam_user_profile_review_jsoup.java" class function：

function --> Steam_user_profile_review_jsoup.do_scraper_user_profile_review("user_profile_index", "json file export path");

------------------------------------------------

# Requirement :

json-simple jar lib

jsoup jar lib

selenium-java jar lib

selenium-server-standalone jar lib

------------------------------------------------

------------------------------------------------

## *Work record*:

------------------------------------------------

## 2016/05/16

透過Java的json-simple將著名的
Steam遊戲平台的遊戲清單json檔轉換處理後
再利用JDBC送進自己電腦的Mysql資料庫中並建立Table

------------------------------------------------

## 2016/05/28

增加Steam遊戲屬性標籤scraper功能

增加Steam評論scraper功能 (*可自訂抓取需求數)

------------------------------------------------

## 2016/05/30

Steam評論scraper呼叫類別功能完成 

------------------------------------------------

appid = Steam的遊戲id編號

review_sample_size = 指定抓取評論內容之數量

json_export_path = 指定json檔案輸出路徑

------------------------------------------------

## 2016/05/31

開始進行有效遊戲與無效遊戲清理淨化

------------------------------------------------

## 2016/06/02

將Steam官方一共(25715)筆遊戲id進行淨化篩選清理處理後剩為(8000)多筆有效遊戲

(*刪除的包含DLC 原聲帶 等附加內容 還有軟體)

------------------------------------------------

## 2016/06/11

刪除Steam_valid資料夾 將處理完畢的遊戲清單json檔集中

Steam_json資料夾中包含:

1.clean_finish資料夾

2.raw資料夾

3.review_250_sample_list資料夾

4.review_250_sample_user_list資料夾

第1個資料夾中的json檔為淨化過後刪除雜七雜八無效id且確定都有評論可以抓的遊戲總清單

第2個資料夾中的json檔為Steam原始未淨化的json檔

第3個資料夾中的json檔為評論數確定至少有250筆的遊戲清單

第4個資料夾中的json檔為已過濾掉重複評論使用者並給予id的全部評論使用者清單

------------------------------------------------

## 2016/06/12

自動抓取指定steam使用者所屬全部評論功能完成

------------------------------------------------