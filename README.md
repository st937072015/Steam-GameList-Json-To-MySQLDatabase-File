------------------------------------------------

# Steam-Review-Scraper

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


## *Work record*:


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

## 2016/06/22

終於抓完了所有可怕的巨量資料

加入Lucene StandAnalyzer之功能

------------------------------------------------

## 2016/06/24

前置處理功能完成

(特殊符號只保留 1.逗號 2.句號 3.問號 4.驚嘆號)

(刪除26個僅單一英文字母)

(字型全形轉半形)

第1種以250篇評論對映方法之所有評論已淨化完成(Steam_game_review_clean)

------------------------------------------------

## 2016/06/25

修正斷詞功能
第2種以評論作者之評論對映方法之所有評論已淨化完成(Steam_user_review_clean)

------------------------------------------------

## 2016/06/29

產生2種對映方法的字彙集字典(給予每個字詞index索引值以加快字詞比對速度)

完成判斷字彙集字典中字詞的種類與5大人格特質之權重P值

------------------------------------------------

## 2016/06/30

用一般方法計算tf與count 但發現記憶體會負荷不了 所以必須更換與優化計算方法

------------------------------------------------

## 2016/07/01

tfidf function優化完成 將字典矩陣為0的字詞排除拿掉不計 以節省記憶體空間

------------------------------------------------

## 2016/07/02

加入idf分數參考字典計算功能

------------------------------------------------

## 2016/07/03

進階改善tfidf計算功能的速度及所耗費的記憶體效能 將字彙集字典讀取功能移至最外區域只讀一次 而不是一直重複讀

------------------------------------------------

## 2016/07/15

優化idf分數參考字典計算功能(利用index索引值比對加速計算[tf * idf])

2種對映方法的評論資料count和tf和idf皆已經計算完成

加入 Jackson JSON lib

SimpleJSON lib不適用大資料量的json輸出 改換Jackson JSON lib 完成idf字典輸出

容量大的json資料改以Jackson JSON lib輸出

------------------------------------------------

## 2016/07/16

2種對映方法的評論資料[tf * idf]皆已經計算完成

修改先前字詞人格特質字典 加入index索引值

------------------------------------------------